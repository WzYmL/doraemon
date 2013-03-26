/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation
 *
 * Create on Apr 24, 2012 3:18:17 PM
 *******************************************************************************/
package org.equinoxogsi.toast.internal.dev.airbag;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.equinoxogsi.toast.dev.airbag.IAirbag;
import org.equinoxogsi.toast.dev.airbag.IAirbagListener;

/**
 * @author LiXP
 * 
 */
public class FakeAirbag implements IAirbag {

	private List<IAirbagListener> listeners;

	private Job job;

	private boolean isRunning;

	public FakeAirbag() {
		listeners = new ArrayList<IAirbagListener>();
	}

	public synchronized void addListener(IAirbagListener listener) {
		listeners.add(listener);
	}

	public synchronized void removeListener(IAirbagListener listener) {
		listeners.remove(listener);
	}

	public void deploy() {
		for (IAirbagListener listener : listeners) {
			listener.deployed();
		}
	}

	public synchronized void startup() {
		isRunning = true;
		job = new Job("FakeAirbag") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				deploy();
				if (isRunning) {
					schedule(5000);
				}
				return Status.OK_STATUS;
			}
		};
		job.schedule(5000);
	}

	public synchronized void shutdown() {
		isRunning = false;
		job.cancel();
		try {
			job.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
