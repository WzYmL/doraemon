package org.salever.draw2d.extension.fake;

public class RadioButtonFigure extends CheckedFigure {

	public RadioButtonFigure() {
		this("RadioButton");
	}

	public RadioButtonFigure(String text) {
		super(text);
		setDecrotorImage(RADIO_UNCHECKED);
	}

	/**
	 * @param selection
	 *            the selection to set
	 */
	public void setSelection(boolean selection) {
		this.selection = selection;
		setDecrotorImage(selection ? RADIO_CHECKED : RADIO_UNCHECKED);
		repaint();
	}
}