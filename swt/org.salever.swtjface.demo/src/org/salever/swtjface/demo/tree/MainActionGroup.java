/**
 * 
 */
package org.salever.swtjface.demo.tree;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.actions.ActionGroup;

/**
 * @author LiXiaopeng
 * 
 */
public class MainActionGroup extends ActionGroup {

	private static final String ADD_IMAGE_PATH = "icons/add.gif";
	private static final String DELETE_IMAGE_PATH = "icons/delete.gif";
	private static final String REFRESH_IMAGE_PATH = "icons/refresh.gif";

	private static final Image ADD_IMAGE = new Image(null,
			MainActionGroup.class.getResourceAsStream(ADD_IMAGE_PATH));
	private static final Image DELETE_IMAGE = new Image(null,
			MainActionGroup.class.getResourceAsStream(DELETE_IMAGE_PATH));
	private static final Image REFRESH_IMAGE = new Image(null,
			MainActionGroup.class.getResourceAsStream(REFRESH_IMAGE_PATH));
	
	private TreeViewer tv;
	private MainDialog page;

	// 在Action 要使用到TreeViewer 对象，所以通过构造函数把它传进来
	public MainActionGroup(TreeViewer tv, MainDialog page) {
		this.tv = tv;
		this.page = page;
	}

	// 生成菜单Menu，并将两个Action 传入
	public void fillContextMenu(IMenuManager mgr) {
		MenuManager menuManager = (MenuManager) mgr;
		final Action addAction = new AddAction();
		final Action deleteAction = new DeleteAction();
		// final Action refershAction = new RefreshAction();
		menuManager.add(addAction);
		menuManager.add(deleteAction);
		// menuManager.add(refershAction);
		// menuManager.addMenuListener(new IMenuListener() {
		// @Override
		// public void menuAboutToShow(IMenuManager manager) {
		// manager.setRemoveAllWhenShown(true);
		// Object entry = getSelTreeEntry();// 取得当前树结点
		// if (entry instanceof StyleBoxBean) {
		// manager.add(deleteAction);
		// } else if (entry instanceof NineSquareGridBean) {
		// manager.add(addAction);
		// }
		// manager.add(refershAction);
		// }
		//
		// });

		// 生成Menu 并挂在tree 上。menu 和tree 两个对象互为对方的参数
		Tree tree = tv.getTree();
		Menu menu = menuManager.createContextMenu(tree);
		tree.setMenu(menu);
	}

	public void fillActionToolBars(ToolBarManager actionBarManager) {

		Action addAction = new AddAction();
		Action deleteAction = new DeleteAction();
		Action refreshAction = new RefreshAction();
		actionBarManager.add(createContributionItem(addAction));
		actionBarManager.add(createContributionItem(deleteAction));
	//	actionBarManager.add(createContributionItem(refreshAction));
		actionBarManager.update(true); // 更新工具栏，否则工具栏不显示任何按钮
	}

	private IContributionItem createContributionItem(Action action) {
		ActionContributionItem aci = new ActionContributionItem(action);
		// aci.setMode(ActionContributionItem.MODE_FORCE_TEXT);// 显示图像+文字
		return aci;
	}

	private Object getSelTreeEntry() {
		IStructuredSelection selection = (IStructuredSelection) tv
				.getSelection();
		return selection.getFirstElement();
	}

	private class AddAction extends Action {
		public AddAction() {
			setText("添加");
			setImageDescriptor(ImageDescriptor.createFromImage(ADD_IMAGE));
		}

		public void run() {

		}
	}

	private class DeleteAction extends Action {
		public DeleteAction() {
			setText("删除");
			setImageDescriptor(ImageDescriptor.createFromImage(DELETE_IMAGE));

		}

		public void run() {
		
		
		}
	}

	private class RefreshAction extends Action {
		public RefreshAction() {
			setText("Refresh");
			setImageDescriptor(ImageDescriptor.createFromImage(REFRESH_IMAGE));

		}

		public void run() {
			tv.refresh(); // 刷新方法，界面会重新读取数据并显示

		}
	}
}