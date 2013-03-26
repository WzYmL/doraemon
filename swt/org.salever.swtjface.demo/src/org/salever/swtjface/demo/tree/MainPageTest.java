/**
 * 
 */
package org.salever.swtjface.demo.tree;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;

/**
 * @author LiXiaopeng
 *
 */
public class MainPageTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				MainDialog dialog = new MainDialog(null);
				dialog.open();
			}
		});
		
	}

}
