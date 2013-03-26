package org.salever.rcp.tech.chapter4;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {
	
	private TrayItem trayItem;
	private Image trayImage;
	private IWorkbenchWindow window;
	public ApplicationActionBarAdvisor	actionBarAdvisor;

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
    	actionBarAdvisor = new ApplicationActionBarAdvisor(configurer);
		return actionBarAdvisor;

    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(400, 300));
        configurer.setShowCoolBar(false);
        configurer.setShowStatusLine(false);
    }
	@Override

	public void postWindowOpen() {
		super.postWindowOpen();
		window = getWindowConfigurer().getWindow();
		trayItem = initTaskItem(window);
		if (trayItem !=null){
			// The following coding will will the program if the program is minimized
			// Not always desired
			createMinimize();
			// Create exit and about action on the icon
			hookPopupMenu(window);
		}
	}
	
	private void hookPopupMenu(IWorkbenchWindow window2) {
		
		trayItem.addListener(SWT.MenuDetect, new Listener(){
			public void handleEvent(Event event) {
				MenuManager trayMenu = new MenuManager();
				Menu menu = trayMenu.createContextMenu(window.getShell());
				actionBarAdvisor.fillTrayItem(trayMenu);
				menu.setVisible(true);
			}
		});
	}
		
		private void createMinimize() {
			window.getShell().addShellListener(new ShellAdapter(){
				public void shellIconified(ShellEvent e){
					window.getShell().setVisible(false);
				}
			});
			trayItem.addListener(SWT.DefaultSelection, new Listener(){
				public void handleEvent(Event event){
					Shell shell = window.getShell();
					if (!shell.isVisible()){
						shell.setVisible(true);
						window.getShell().setMinimized(false);
					}
				}
			});
	}

		private TrayItem initTaskItem(IWorkbenchWindow window) {
			final Tray tray = window.getShell().getDisplay().getSystemTray();
			TrayItem trayItem = new TrayItem(tray, SWT.NONE);
			trayImage = AbstractUIPlugin.imageDescriptorFromPlugin(
					Activator.PLUGIN_ID, "/icons/alt_about.gif").createImage();
			trayItem.setImage(trayImage);
			trayItem.setToolTipText("TrayItem");
			return trayItem;
		}

		public void dispose(){
			if (trayImage!=null){
				trayImage.dispose();
				trayItem.dispose();
			}
		}
}
