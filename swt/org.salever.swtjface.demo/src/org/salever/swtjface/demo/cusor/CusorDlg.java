package org.salever.swtjface.demo.cusor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CusorDlg {

	public static void main(String[] args) {
		Display display = new Display();

		Cursor cursor = new Cursor(display, SWT.CURSOR_HAND);

		Shell shell = new Shell(display);
		shell.open();
		final Button b = new Button(shell, 0);
		b.setBounds(10, 10, 200, 200);
		b.setCursor(cursor);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		cursor.dispose();
		display.dispose();
	}
}