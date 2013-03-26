/**
 * 
 */
package org.salever.rcp.tools.wordcounts.models;

import java.text.DecimalFormat;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author LiXP
 * 
 */
public class ModelTableLabelProvider extends LabelProvider implements
		ITableLabelProvider {
	private static final int COL_WORD = 0;
	private static final int COL_COUNT = 1;
	private static final int COL_TOTAL = 2;
	private static final int COL_RATE = 3;

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {

		if (element instanceof CountModel) {
			CountModel model = (CountModel) element;
			switch (columnIndex) {
			case COL_WORD:
				return model.getWord();
			case COL_COUNT:
				return model.getCounts() + ""; //$NON-NLS-1$
			case COL_TOTAL:
				return model.getTotalCounts() + ""; //$NON-NLS-1$
			case COL_RATE:
				return format(model.getRate());
			default:
				break;
			}
		}
		return element.toString();
	}

	protected String format(double rate) {
		DecimalFormat df5 = new DecimalFormat("0.000"); //$NON-NLS-1$
		String result = df5.format(rate * 100);
		return result + "%"; //$NON-NLS-1$
	}
}