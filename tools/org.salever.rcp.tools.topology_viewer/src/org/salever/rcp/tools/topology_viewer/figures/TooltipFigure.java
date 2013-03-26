package org.salever.rcp.tools.topology_viewer.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;

public class TooltipFigure extends FlowPage {
	public static interface ITextProvider {

		public abstract String getTooltipText();
	}

	private ITextProvider textProvider;
	private String text;
	private final TextFlow textFlow = new TextFlow();

	public TooltipFigure() {
		setBorder(new MarginBorder(2));
		setBackgroundColor(ColorConstants.tooltipBackground);
		setForegroundColor(ColorConstants.tooltipForeground);
		textFlow.setText(" ");
		textFlow.setLayoutManager(new ParagraphTextLayout(textFlow, 1));
		setMaximumSize(new Dimension(120, 60));
		add(textFlow);
		setHorizontalAligment(1);
	}

	@Override
	public Dimension getPreferredSize(int w, int h) {
		Dimension d = super.getPreferredSize(-1, -1);
		if (d.width > 400)
			d = super.getPreferredSize(400, -1);
		return d;
	}

	public String getText() {
		return text;
	}

	@Override
	public void repaint() {
		if (textFlow != null)
			textFlow.setText(text == null ? textProvider == null ? null
					: textProvider.getTooltipText() : text);
		super.repaint();
	}

	public void setText(String newText) {
		text = newText;
	}

	public void setTextProvider(ITextProvider textProvider) {
		this.textProvider = textProvider;
	}
}
