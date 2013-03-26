/**
 * 
 */
package org.salever.common.swtjface.richhtml4eclipse.widgets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.salever.common.swtjface.richhtml4eclipse.Messages;
import org.salever.common.swtjface.richhtml4eclipse.actions.BackColorAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.BoldAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.BulletListAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.CleanupAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.ForegroundAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.GetHtmlAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.HrAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.IndentAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.InsertEditAnchorAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.InsertEditImageAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.InsertEditLinkAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.InsertNonBreakingWhitespace;
import org.salever.common.swtjface.richhtml4eclipse.actions.ItalicAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.JustifyCenterAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.JustifyFullAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.JustifyLeftAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.JustifyRightAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.NumListAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.OutdentAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.RedoAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.RemoveFormatAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.SelectFontAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.SelectFontSizeAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.SelectFormatAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.StrikeThroughAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.SubAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.SupAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.ToggleVisualAidAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.UnderLineAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.UndoAction;
import org.salever.common.swtjface.richhtml4eclipse.actions.UnlinkAction;

/**
 * A composite contains HTML composer and tool bar menu.
 * 
 * @author LiXiaopeng
 * 
 */
public class SimpleHtmlEditComposite extends Composite {

	protected HtmlComposer composer;
	private GetHtmlAction getHtmAction;
	private Text htmlText;
	private String html = "";
	private String tinyMCEPath;

	public SimpleHtmlEditComposite(Composite parent, int style, String html,String tinyMCEPath) {
		super(parent, style);
		this.html = html;
		this.tinyMCEPath = tinyMCEPath;
		init();
	}

	private void init() {
		this.setLayout(new GridLayout(1, false));
		this.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		CoolBar coolbar = new CoolBar(this, SWT.NONE);

		GridData gd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		gd.widthHint = 100;
		coolbar.setLayoutData(gd);

		coolbar.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event event) {
				SimpleHtmlEditComposite.this.getShell().layout();
			}
		});
		final Composite comboComp = new Composite(coolbar, SWT.NONE);
		comboComp.setLayout(new GridLayout(3, false));

		final Combo formatCombo = new Combo(comboComp, SWT.SINGLE);

		formatCombo.setItems(SelectFormatAction.formatMappings.values()
				.toArray(new String[0]));
		formatCombo.add(Messages.HtmlEditComposite_0, 0);
		formatCombo.select(0);
		formatCombo.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				new SelectFormatAction(composer, formatCombo).run();
			}
		});
		Point formatTextSize = formatCombo
				.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		formatTextSize = formatCombo.computeSize(formatTextSize.x,
				formatTextSize.y);

		final Combo fontCombo = new Combo(comboComp, SWT.SINGLE);

		fontCombo.setItems(getFontList());
		fontCombo.add(Messages.HtmlEditComposite_1, 0);
		fontCombo.select(0);
		fontCombo.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				new SelectFontAction(composer, fontCombo).run();
			}
		});
		Point fontTextSize = fontCombo.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		fontTextSize = fontCombo.computeSize(fontTextSize.x, fontTextSize.y);

		final Combo fontsizeCombo = new Combo(comboComp, SWT.SINGLE);
		fontsizeCombo.setItems(new String[] { Messages.HtmlEditComposite_2,
				"1", "2", "3", "4", //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				"5", "6" }); //$NON-NLS-1$ //$NON-NLS-2$
		fontsizeCombo.select(0);
		fontsizeCombo.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				new SelectFontSizeAction(composer, fontsizeCombo).run();
			}
		});
		CoolItem comboItem = new CoolItem(coolbar, SWT.NONE);
		comboItem.setControl(comboComp);

		Point fontSizetextSize = fontsizeCombo.computeSize(SWT.DEFAULT,
				SWT.DEFAULT);
		fontSizetextSize = fontsizeCombo.computeSize(fontSizetextSize.x,
				fontSizetextSize.y);
		comboItem.setMinimumSize(formatTextSize.x + fontSizetextSize.x
				+ fontTextSize.x, formatTextSize.y + fontSizetextSize.y
				+ fontTextSize.y);
		comboItem.setPreferredSize(formatTextSize.x + fontSizetextSize.x
				+ fontTextSize.x, formatTextSize.y + fontSizetextSize.y
				+ fontTextSize.y);
		comboItem.setSize(formatTextSize.x + fontSizetextSize.x
				+ fontTextSize.x, formatTextSize.y + fontSizetextSize.y
				+ fontTextSize.y);

		ToolBar menu = new ToolBar(coolbar, SWT.HORIZONTAL | SWT.FLAT);
		ToolBarManager manager = new ToolBarManager(menu);
		CoolItem item = new CoolItem(coolbar, SWT.NONE);
		item.setControl(menu);

		final TabFolder tabFolder = new TabFolder(this, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));

		TabItem htmlTab = new TabItem(tabFolder, SWT.NONE);
		htmlTab.setText("HTML"); //$NON-NLS-1$
		Composite htmlCom = new Composite(tabFolder, SWT.NONE);
		htmlTab.setControl(htmlCom);
		htmlCom.setLayout(new GridLayout(1, false));

		Composite sourceCom = new Composite(tabFolder, SWT.NONE);
		TabItem sourceTab = new TabItem(tabFolder, SWT.NONE);
		sourceTab.setText(Messages.HtmlEditComposite_10);
		sourceTab.setControl(sourceCom);
		sourceCom.setLayout(new GridLayout(1, false));

		composer = new HtmlComposer(htmlCom, SWT.NONE, tinyMCEPath);
		composer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		htmlText = new Text(sourceCom, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		gd = new GridData(GridData.FILL_BOTH);
		htmlText.setLayoutData(gd);

		initHtmlContent();

		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (tabFolder.getSelectionIndex() == 1) {
					getHtmAction.run();
				}
			}
		});

		manager.add(new BoldAction(composer));
		manager.add(new ItalicAction(composer));
		manager.add(new UnderLineAction(composer));
		manager.add(new StrikeThroughAction(composer));
		manager.add(new Separator());
		manager.add(new ForegroundAction(composer));
		manager.add(new BackColorAction(composer));
		manager.add(new Separator());
		manager.add(new JustifyLeftAction(composer));
		manager.add(new JustifyCenterAction(composer));
		manager.add(new JustifyRightAction(composer));
		manager.add(new JustifyFullAction(composer));
		manager.add(new Separator());
		manager.add(new BulletListAction(composer));
		manager.add(new NumListAction(composer));
		manager.add(new Separator());
		manager.add(new OutdentAction(composer));
		manager.add(new IndentAction(composer));
		manager.add(new Separator());
		manager.add(new SubAction(composer));
		manager.add(new SupAction(composer));
		manager.update(true);

		getHtmAction = new GetHtmlAction(composer, htmlText);

		// 不需要HTML设置菜单
		// menu = new ToolBar(coolbar, SWT.HORIZONTAL | SWT.FLAT);
		// manager = new ToolBarManager(menu);
		// item = new CoolItem(coolbar, SWT.NONE);
		// item.setControl(menu);
		//
		// manager.add(getHtmAction);
		// manager.add(setHtmlAction);
		// manager.update(true);

		menu = new ToolBar(coolbar, SWT.HORIZONTAL | SWT.FLAT);
		manager = new ToolBarManager(menu);
		item = new CoolItem(coolbar, SWT.NONE);
		item.setControl(menu);

		manager.add(new InsertEditImageAction(composer));
		manager.add(new InsertEditAnchorAction(composer));
		manager.add(new InsertEditLinkAction(composer));
		manager.add(new UnlinkAction(composer));
		manager.add(new HrAction(composer));
		manager.add(new InsertNonBreakingWhitespace(composer));
		manager.add(new Separator());
		manager.add(new CleanupAction(composer));
		manager.add(new RemoveFormatAction(composer));
		manager.add(new ToggleVisualAidAction(composer));
		manager.add(new Separator());
		manager.add(new UndoAction(composer));
		manager.add(new RedoAction(composer));
		manager.update(true);

		// 修改前景色背景色菜单位置
		// menu = new ToolBar(coolbar, SWT.HORIZONTAL | SWT.FLAT);
		// manager = new ToolBarManager(menu);
		// item = new CoolItem(coolbar, SWT.NONE);
		// item.setControl(menu);
		//
		// manager.add(new ForegroundAction(composer));
		// manager.add(new BackColorAction(composer));
		// manager.update(true);

		// 不需要Layer菜单
		// menu = new ToolBar(coolbar, SWT.HORIZONTAL | SWT.FLAT);
		// manager = new ToolBarManager(menu);
		// item = new CoolItem(coolbar, SWT.NONE);
		// item.setControl(menu);
		//
		// manager.add(new InsertLayerAction(composer));
		// manager.add(new MoveLayerBackwardAction(composer));
		// manager.add(new MoveLayerForwardAction(composer));
		// manager.add(new MakeLayerAbsoluteAction(composer));
		manager.update(true);
		// 不需要最后一行表格的操作菜单
		// menu = new ToolBar(coolbar, SWT.HORIZONTAL | SWT.FLAT);
		// manager = new ToolBarManager(menu);
		// item = new CoolItem(coolbar, SWT.NONE);
		// item.setControl(menu);
		//
		// manager.add(new InsertEditTableAction(composer));
		// manager.add(new Separator());
		// manager.add(new InsertRowBeforeAction(composer));
		// manager.add(new InsertRowAfterAction(composer));
		// manager.add(new DeleteRowAction(composer));
		// manager.add(new Separator());
		// manager.add(new InsertColumnBeforeAction(composer));
		// manager.add(new InsertColumnAfterAction(composer));
		// manager.add(new DeleteColumnAction(composer));
		//
		// manager.update(true);

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

	private static String[] getFontList() {
		Set<String> s = new HashSet<String>();
		// Add names of all bitmap fonts.
		FontData[] fds = Display.getCurrent().getFontList(null, false);
		for (int i = 0; i < fds.length; ++i)
			s.add(fds[i].getName());
		// Add names of all scalable fonts.
		fds = Display.getCurrent().getFontList(null, true);
		for (int i = 0; i < fds.length; ++i)
			s.add(fds[i].getName());
		// Sort the result and print it.
		String[] answer = new String[s.size()];
		s.toArray(answer);
		Arrays.sort(answer);
		return answer;
	}

	/**
	 * @return the composer
	 */
	public HtmlComposer getComposer() {
		return composer;
	}

	public String getHtml() {
		return composer.getHtml();
	}

	protected void initHtmlContent() {
		this.composer.execute(JavaScriptCommands.SET_HTML(html));
	}

	/**
	 * 重新设置内容
	 * @param html
	 */
	public void resetHtml(String html){
		this.composer.execute(JavaScriptCommands.SET_HTML(html));
	}
}
