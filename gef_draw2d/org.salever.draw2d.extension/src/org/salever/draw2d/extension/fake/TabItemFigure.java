package org.salever.draw2d.extension.fake;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.ButtonModel;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.Panel;
import org.eclipse.jface.resource.JFaceResources;

/**
 * 便签页
 * 
 * @author salever@126.com
 * 
 */
public class TabItemFigure {

	private static final int WIDTH_DIFF = 10;

	private Button item;

	private Panel contentArea;

	private GridLayout gridLayout;

	private TabFolderFigure tabFolder;

	private boolean onTop;

	/**
	 * 
	 * @param tabFolder
	 * @param name
	 */
	public TabItemFigure(TabFolderFigure tabFolder, String name) {
		this.tabFolder = tabFolder;
		item = new Button(name);
		item.setPreferredSize(
				FigureUtilities.getTextWidth(name,
						JFaceResources.getDefaultFont())
						+ WIDTH_DIFF, TabFolderFigure.TAB_ITEM_HEIGHT);
		contentArea = new Panel();
		gridLayout = new GridLayout();
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		contentArea.setLayoutManager(gridLayout);
		onTop = false;
		init();

	}

	private void init() {
		ButtonModel model = new ButtonModel();
		model.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				tabFolder.select(TabItemFigure.this);
			}
		});
		item.setModel(model);

	}

	/**
	 * 刷新组件状态
	 */
	protected void refreshState() {
		if (onTop) {
			getItem().setBackgroundColor(ColorConstants.white);
			getContentArea().setVisible(true);
		} else {
			getItem().setBackgroundColor(ColorConstants.button);
			getContentArea().setVisible(false);
		}
	}

	/**
	 * @return the item
	 */
	public Button getItem() {
		return item;
	}

	/**
	 * @return the contantArea
	 */
	public Panel getContentArea() {
		return contentArea;
	}

	/**
	 * 设置主控件
	 * 
	 * @param figure
	 */
	public void setContent(Figure figure) {
		contentArea.add(figure);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gridLayout.setConstraint(figure, gd);
	}

	/**
	 * 最前
	 */
	public void onTop() {
		onTop = true;
		refreshState();
	}

	/**
	 * 取消最前
	 */
	public void disOnTop() {
		onTop = false;
		refreshState();
	}
}
