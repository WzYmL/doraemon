package org.salever.draw2d.extension.fake;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.StackLayout;

/**
 * 便签页容器
 * 
 * @author salever@126.com
 * 
 */
public class TabFolderFigure extends Panel {

	/**
	 * @return the items
	 */
	public List<TabItemFigure> getItems() {
		return items;
	}

	public static final int TAB_ITEM_HEIGHT = 25;

	/**
	 * 标题部分
	 */
	private Figure itemPanel;

	/**
	 * 控件区域部分
	 */
	private Panel contentPanel;

	private StackLayout stackLayout;

	private List<TabItemFigure> items;

	public TabFolderFigure() {
		super();
		init();
	}

	private void init() {

		items = new ArrayList<TabItemFigure>();
		GridLayout gridLayout;
		gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 0;
		stackLayout = new StackLayout();
		contentPanel = new Panel();
		contentPanel.setBackgroundColor(ColorConstants.white);

		itemPanel = new Figure();

		this.setLayoutManager(gridLayout);
		this.add(itemPanel);
		this.add(contentPanel);
		this.setBorder(new LineBorder());

		GridData item_gd = new GridData(GridData.FILL_HORIZONTAL);
		item_gd.heightHint = TAB_ITEM_HEIGHT;
		gridLayout.setConstraint(itemPanel, item_gd);
		FlowLayout flowlayout = new FlowLayout();
		flowlayout.setMajorSpacing(0);// 填充无间隔
		flowlayout.setMinorSpacing(0);// 填充无间隔
		itemPanel.setLayoutManager(flowlayout);

		GridData content_gd = new GridData(GridData.FILL_BOTH);
		gridLayout.setConstraint(contentPanel, content_gd);
		contentPanel.setLayoutManager(stackLayout);
	}

	/**
	 * 添加TabItem
	 * 
	 * @param item
	 */
	public void addItem(TabItemFigure item) {
		itemPanel.add(item.getItem());
		contentPanel.add(item.getContentArea());
		item.getContentArea().setVisible(true);
		items.add(item);
	}

	/**
	 * 选中某个便签项
	 * 
	 * @param item
	 */
	public void select(TabItemFigure item) {
		for (TabItemFigure tif : items) {
			if (tif.equals(item)) {
				tif.onTop();
			} else {
				tif.disOnTop();
			}
		}
	}
}
