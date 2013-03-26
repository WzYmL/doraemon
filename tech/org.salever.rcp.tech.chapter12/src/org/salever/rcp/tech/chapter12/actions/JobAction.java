package org.salever.rcp.tech.chapter12.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class JobAction implements IWorkbenchWindowActionDelegate {

	public void dispose() {
		// TODO Auto-generated method stub
	}

	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
	}

	public void run(IAction action) {
		// TODO Auto-generated method stub
		Job job = new Job("myJob") {

			public IStatus run(IProgressMonitor monitor) {
				System.out.println("moin");
				monitor.beginTask("Long running thing...", 100);
				for (int i = 0; i < 10; i++) {
					monitor.subTask("I'm doing something here " + i);
					mysleep(1000);
					monitor.worked(i);
				}
				monitor.done();
				return Status.OK_STATUS;

			}
		};
		job.setUser(true);
		job.schedule();
	}
	
	private void mysleep(Integer waitTime) {
		try {
			System.out.println("Waiting");
			Thread.sleep(waitTime);
		} catch (Throwable t) {
			System.out.println("Wait time interrupted");
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}


}
