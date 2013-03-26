/**
 * 
 */
package org.salever.rcp.tools.topology_viewer.system;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

/**
 * @author salever@126.com
 * 
 */
public class ImageFactory {

	public static Image mergeImage(Image srcImage, Color color, double ratio) {

		Image newImage;
		Rectangle rect = srcImage.getBounds();
		ImageData data = srcImage.getImageData();
		PaletteData palette = data.palette;
		ImageData newData = data;
		if (!palette.isDirect) {
			/* Convert the palette entries to gray. */
			RGB[] rgbs = palette.getRGBs();
			for (int i = 0; i < rgbs.length; i++) {
				if (data.transparentPixel != i) {
					RGB rgb = rgbs[i];
					int red = rgb.red;
					int green = rgb.green;
					int blue = rgb.blue;
					int intensity = (red + red + green + green + green + green
							+ green + blue) >> 3;
					rgb.red = rgb.green = rgb.blue = intensity;
				}
			}
			newData.palette = new PaletteData(rgbs);
		} else {
			/* Create a 8 bit depth image data with a gray palette. */
			RGB[] rgbs = new RGB[256];
			for (int i = 0; i < rgbs.length; i++) {
				rgbs[i] = new RGB(color.getRed(), color.getGreen(), color
						.getBlue());
			}
			newData = new ImageData(rect.width, rect.height, 8,
					new PaletteData(rgbs));
			newData.alpha = data.alpha;
			newData.alphaData = data.alphaData;
			newData.maskData = data.maskData;
			newData.maskPad = data.maskPad;
			if (data.transparentPixel != -1)
				newData.transparentPixel = 254;

			/* Convert the pixels. */
			int[] scanline = new int[rect.width];
			int redMask = palette.redMask;
			int greenMask = palette.greenMask;
			int blueMask = palette.blueMask;
			int redShift = palette.redShift;
			int greenShift = palette.greenShift;
			int blueShift = palette.blueShift;
			for (int y = 0; y < rect.height; y++) {
				int offset = y * newData.bytesPerLine;
				data.getPixels(0, y, rect.width, scanline, 0);
				for (int x = 0; x < rect.width; x++) {
					int pixel = scanline[x];
					if (pixel != data.transparentPixel) {
						int red = pixel & redMask;
						red = (redShift < 0) ? red >>> -redShift
								: red << redShift;
						int green = pixel & greenMask;
						green = (greenShift < 0) ? green >>> -greenShift
								: green << greenShift;
						int blue = pixel & blueMask;
						blue = (blueShift < 0) ? blue >>> -blueShift
								: blue << blueShift;
						int intensity = (red + red + green + green + green
								+ green + green + blue) >> 3;
						if (newData.transparentPixel == intensity)
							intensity = 255;
						newData.data[offset] = (byte) intensity;
					} else {
						newData.data[offset] = (byte) 254;
					}
					offset++;
				}
			}
		}
		newImage = new Image(srcImage.getDevice(), newData);
		return newImage;
	}
}
