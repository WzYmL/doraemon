package org.salever.j2se.common.xml;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This program shows how to write an XML file. It saves a file describing a
 * modern drawing in SVG format.
 */
public class XMLWriteTest {
	public static void main(String[] args) {
		XMLWriteFrame frame = new XMLWriteFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/**
 * A frame with a panel for showing a modern drawing.
 */
class XMLWriteFrame extends JFrame {
	public XMLWriteFrame() {
		setTitle("XMLWriteTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		chooser = new JFileChooser();

		// add panel to frame

		panel = new RectanglePanel();
		add(panel);

		// set up menu bar

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("File");
		menuBar.add(menu);

		JMenuItem newItem = new JMenuItem("New");
		menu.add(newItem);
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel.newDrawing();
			}
		});

		JMenuItem saveItem = new JMenuItem("Save");
		menu.add(saveItem);
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					saveDocument();
				} catch (TransformerException e) {
					JOptionPane.showMessageDialog(XMLWriteFrame.this,
							e.toString());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(XMLWriteFrame.this,
							e.toString());
				}
			}
		});

		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
	}

	/**
	 * Saves the drawing in SVG format.
	 */
	public void saveDocument() throws TransformerException, IOException {
		if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
			return;
		File f = chooser.getSelectedFile();
		Document doc = panel.buildDocument();
		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
				"http://www.worg/TR/2000/CR-SVG-20000802/DTD/svg-dtd");
		t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,
				"-//W3C//DTD SVG 20000802//EN");

		t.transform(new DOMSource(doc), new StreamResult(
				new FileOutputStream(f)));
	}

	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;

	private RectanglePanel panel;
	private JFileChooser chooser;
}

/**
 * A panel that shows a set of colored rectangles
 */
class RectanglePanel extends JPanel {
	public RectanglePanel() {
		rects = new ArrayList<Rectangle2D>();
		colors = new ArrayList<Color>();
		generator = new Random();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create a new random drawing.
	 */
	public void newDrawing() {
		int n = 10 + generator.nextInt(20);
		rects.clear();
		colors.clear();
		for (int i = 1; i <= n; i++) {
			int x = generator.nextInt(getWidth());
			int y = generator.nextInt(getHeight());
			int width = generator.nextInt(getWidth() - x);
			int height = generator.nextInt(getHeight() - y);
			rects.add(new Rectangle(x, y, width, height));
			int r = generator.nextInt(256);
			int g = generator.nextInt(256);
			int b = generator.nextInt(256);
			colors.add(new Color(r, g, b));
		}
		repaint();
	}

	public void paintComponent(Graphics g) {
		if (rects.size() == 0)
			newDrawing();
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// draw all rectangles
		for (int i = 0; i < rects.size(); i++) {
			g2.setPaint(colors.get(i));
			g2.fill(rects.get(i));
		}
	}

	/**
	 * Creates an SVG document of the current drawing.
	 * 
	 * @return the DOM tree of the SVG document
	 */
	public Document buildDocument() {

		Document doc = builder.newDocument();
		Element svgElement = doc.createElement("svg");
		doc.appendChild(svgElement);
		svgElement.setAttribute("width", "" + getWidth());
		svgElement.setAttribute("height", "" + getHeight());
		for (int i = 0; i < rects.size(); i++) {
			Color c = colors.get(i);
			Rectangle2D r = rects.get(i);
			Element rectElement = doc.createElement("rect");
			rectElement.setAttribute("x", "" + r.getX());
			rectElement.setAttribute("y", "" + r.getY());
			rectElement.setAttribute("width", "" + r.getWidth());
			rectElement.setAttribute("height", "" + r.getHeight());
			rectElement.setAttribute("fill", colorToString(c));
			svgElement.appendChild(rectElement);
		}
		return doc;
	}

	/**
	 * Converts a color to a hex value.
	 * 
	 * @param c
	 *            a color
	 * @return a string of the form #rrggbb
	 */
	private static String colorToString(Color c) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(Integer.toHexString(c.getRGB() & 0xFFFFFF));
		while (buffer.length() < 6)
			buffer.insert(0, '0');
		buffer.insert(0, '#');
		return buffer.toString();
	}

	private ArrayList<Rectangle2D> rects;
	private ArrayList<Color> colors;
	private Random generator;
	private DocumentBuilder builder;
}
