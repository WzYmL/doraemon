package org.eclipse.mylyn.htmltext.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.mylyn.htmltext.HtmlComposer;
import org.eclipse.mylyn.htmltext.example.actions.BackgroundAction;
import org.eclipse.mylyn.htmltext.example.actions.BoldAction;
import org.eclipse.mylyn.htmltext.example.actions.BulletlistAction;
import org.eclipse.mylyn.htmltext.example.actions.GetHtmlAction;
import org.eclipse.mylyn.htmltext.example.actions.IndentAction;
import org.eclipse.mylyn.htmltext.example.actions.InsertEditAnchorAction;
import org.eclipse.mylyn.htmltext.example.actions.InsertEditImageAction;
import org.eclipse.mylyn.htmltext.example.actions.InsertEditLinkAction;
import org.eclipse.mylyn.htmltext.example.actions.InsertHrAction;
import org.eclipse.mylyn.htmltext.example.actions.ItalicAction;
import org.eclipse.mylyn.htmltext.example.actions.JustifyBlockAction;
import org.eclipse.mylyn.htmltext.example.actions.JustifyCenterAction;
import org.eclipse.mylyn.htmltext.example.actions.JustifyLeftAction;
import org.eclipse.mylyn.htmltext.example.actions.JustifyRightAction;
import org.eclipse.mylyn.htmltext.example.actions.NumlistAction;
import org.eclipse.mylyn.htmltext.example.actions.OutdentAction;
import org.eclipse.mylyn.htmltext.example.actions.SetFontfamilyDropdownAction;
import org.eclipse.mylyn.htmltext.example.actions.SetFormatDropdownAction;
import org.eclipse.mylyn.htmltext.example.actions.SetHtmlAction;
import org.eclipse.mylyn.htmltext.example.actions.SetSizeDropdownAction;
import org.eclipse.mylyn.htmltext.example.actions.StrikeAction;
import org.eclipse.mylyn.htmltext.example.actions.SubscriptAction;
import org.eclipse.mylyn.htmltext.example.actions.SuperscriptAction;
import org.eclipse.mylyn.htmltext.example.actions.UnderlineAction;
import org.eclipse.mylyn.htmltext.example.actions.UnlinkAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "org.eclipse.remus.ckeditor.sample.view";

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
	public void createPartControl(final Composite parent) {

		final Composite comp = new Composite(parent, SWT.NO_FOCUS);
		comp.setLayout(new GridLayout(1, false));
		comp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		final HtmlComposer composer = new HtmlComposer(comp, SWT.NONE);
		composer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composer.setHtml("<p>\n"
				+ "	This is some <strong>sample text</strong>. You are using <a href=\"http://ckeditor.com/\">CKEditor</a>.</p>");

		CoolBar coolbar = new CoolBar(comp, SWT.NONE);

		GridData gd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		gd.widthHint = 100;
		coolbar.setLayoutData(gd);

		coolbar.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event event) {
				comp.getShell().layout();
			}
		});

		ToolBar menu = new ToolBar(coolbar, SWT.HORIZONTAL | SWT.FLAT);
		ToolBarManager manager = new ToolBarManager(menu);
		CoolItem item = new CoolItem(coolbar, SWT.NONE);
		item.setControl(menu);

		manager.add(new SetFormatDropdownAction("--[Format]--", composer));
		manager.add(new SetSizeDropdownAction("--[Size]--", composer));
		manager.add(new SetFontfamilyDropdownAction("--[Font]--", composer));
		manager.update(true);

		menu = new ToolBar(coolbar, SWT.HORIZONTAL | SWT.FLAT);
		manager = new ToolBarManager(menu);
		item = new CoolItem(coolbar, SWT.NONE);
		item.setControl(menu);

		final Label outputLabel = new Label(comp, SWT.NONE);
		gd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		outputLabel.setText("HTML-Output");
		outputLabel.setLayoutData(gd);

		final Text htmlText = new Text(comp, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		gd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		gd.heightHint = 200;
		htmlText.setLayoutData(gd);

		//
		//

		manager.add(new BoldAction(composer));
		manager.add(new ItalicAction(composer));
		manager.add(new UnderlineAction(composer));
		manager.add(new StrikeAction(composer));
		manager.add(new Separator());
		manager.add(new JustifyLeftAction(composer));
		manager.add(new JustifyCenterAction(composer));
		manager.add(new JustifyRightAction(composer));
		manager.add(new JustifyBlockAction(composer));
		manager.add(new Separator());
		manager.add(new BulletlistAction(composer));
		manager.add(new NumlistAction(composer));
		manager.add(new Separator());
		manager.add(new OutdentAction(composer));
		manager.add(new IndentAction(composer));
		manager.add(new Separator());
		manager.add(new SubscriptAction(composer));
		manager.add(new SuperscriptAction(composer));
		manager.update(true);

		menu = new ToolBar(coolbar, SWT.HORIZONTAL | SWT.FLAT);
		manager = new ToolBarManager(menu);
		item = new CoolItem(coolbar, SWT.NONE);
		item.setControl(menu);

		manager.add(new GetHtmlAction(composer, htmlText));
		manager.add(new SetHtmlAction(composer));
		manager.update(true);

		menu = new ToolBar(coolbar, SWT.HORIZONTAL | SWT.FLAT);
		manager = new ToolBarManager(menu);
		item = new CoolItem(coolbar, SWT.NONE);
		item.setControl(menu);

		manager.add(new InsertEditImageAction(composer));
		manager.add(new InsertEditAnchorAction(composer));
		manager.add(new InsertEditLinkAction(composer));
		manager.add(new UnlinkAction(composer));
		manager.add(new InsertHrAction(composer));
		// manager.add(new InsertNonBreakingWhitespace(composer));
		// manager.add(new Separator());
		// manager.add(new CleanupAction(composer));
		// manager.add(new RemoveFormatAction(composer));
		// manager.add(new ToggleVisualAidAction(composer));
		// manager.add(new Separator());
		// manager.add(new UndoAction(composer));
		// manager.add(new RedoAction(composer));
		manager.update(true);

		menu = new ToolBar(coolbar, SWT.HORIZONTAL | SWT.FLAT);
		manager = new ToolBarManager(menu);
		item = new CoolItem(coolbar, SWT.NONE);
		item.setControl(menu);

		// manager.add(new ForegroundAction(composer));
		 manager.add(new BackgroundAction(composer));
		manager.update(true);

		menu = new ToolBar(coolbar, SWT.HORIZONTAL | SWT.FLAT);
		manager = new ToolBarManager(menu);
		item = new CoolItem(coolbar, SWT.NONE);
		item.setControl(menu);

		// manager.add(new InsertLayerAction(composer));
		// manager.add(new MoveLayerBackwardAction(composer));
		// manager.add(new MoveLayerForwardAction(composer));
		// manager.add(new MakeLayerAbsoluteAction(composer));
		manager.update(true);
		//
		menu = new ToolBar(coolbar, SWT.HORIZONTAL | SWT.FLAT);
		manager = new ToolBarManager(menu);
		item = new CoolItem(coolbar, SWT.NONE);
		item.setControl(menu);

		// manager.add(new InsertEditTableAction(composer));
		// manager.add(new Separator());
		// manager.add(new InsertRowBeforeAction(composer));
		// manager.add(new InsertRowAfterAction(composer));
		// manager.add(new DeleteRowAction(composer));
		// manager.add(new Separator());
		// manager.add(new InsertColumnBeforeAction(composer));
		// manager.add(new InsertColumnAfterAction(composer));
		// manager.add(new DeleteColumnAction(composer));

		manager.update(true);

		// /* Set the sizes after adding all cool items */
		CoolItem[] coolItems = coolbar.getItems();
		for (int i = 0; i < coolItems.length; i++) {
			CoolItem coolItem = coolItems[i];
			Control control = coolItem.getControl();
			Point size = control.computeSize(SWT.DEFAULT, SWT.DEFAULT);
			Point coolSize = coolItem.computeSize(size.x, size.y);
			if (control instanceof ToolBar) {
				ToolBar bar = (ToolBar) control;
				for (int j = 0, n = bar.getItemCount(); j < n; j++) {
					size.x += bar.getSize().x;
				}
			}
			coolItem.setMinimumSize(size);
			coolItem.setPreferredSize(coolSize);
			coolItem.setSize(coolSize);
		}

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		// viewer.getControl().setFocus();
	}

	private static String[] getFontList() {
		Set s = new HashSet();
		// Add names of all bitmap fonts.
		FontData[] fds = Display.getCurrent().getFontList(null, false);
		for (int i = 0; i < fds.length; ++i) {
			s.add(fds[i].getName());
		}
		// Add names of all scalable fonts.
		fds = Display.getCurrent().getFontList(null, true);
		for (int i = 0; i < fds.length; ++i) {
			s.add(fds[i].getName());
		}
		// Sort the result and print it.
		String[] answer = new String[s.size()];
		s.toArray(answer);
		Arrays.sort(answer);
		return answer;
	}
}