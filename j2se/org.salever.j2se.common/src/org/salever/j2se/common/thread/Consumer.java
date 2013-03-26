/*
 * Consumer.java
 *
 * Copyright (c) 2001 GlobaLoop LTD., Joseph Hartal, Ze'ev Bubis.
 * All Rights Reserved.
 *
 * You may study, use, modify, and distribute this software for any
 * purpose provided that this copyright notice appears in all copies.
 *
 * This software is provided WITHOUT WARRANTY either expressed or
 * implied.
 *
 */

package org.salever.j2se.common.thread;

/**
 * This is an Abstract Consumer class. This class can be used when a
 * implementing a consumer-producer scenario.
 * <p>
 * Usage: Subclass <code>Consumer</code> and provide an implementation to the
 * <code>OnCosume(Object o)</code> method. This method is called automatically
 * in the consumer's thread context. The producer should call the
 * <code>add(Object o)</code> method to add new "items" to the consumer's queue.
 * 
 * @author Joseph Hartal & Ze'ev Bubis
 */
abstract public class Consumer {

	private static final ILogger logger = SimpleLogger.getLogger();

	private boolean _isTerminated = true;
	private boolean _isSleeping = false;

	private boolean isSleeping() {
		return _isSleeping;
	}

	// the default implementaion of queue done by a vector queue (based on
	// java.util.Vector)
	private IQueue _queue = new VectorQueue();
	private boolean _waitForTermination = true;
	private String _name;

	// Monitors - for wait\synchronize producer\consumer\termination\other
	// threads
	private Object _waitForJobsMonitor = new Object();
	private Object _waitForFinishedMonitor = new Object();
	private Object _terminationMonitor = new Object();

	// The consumer's thread
	private Thread _thread = null;

	/**
	 * Set a buffer that will be used as the consumer's queue
	 * 
	 * @param a
	 *            Queue that implements the <code>IQueue</code> interface
	 * @return a reference to this <code>Consumer</code> object.
	 */
	public Consumer setQueue(IQueue buf) {
		synchronized (_queue) {
			_queue = buf;
		}
		return this;
	}

	/**
	 * Returns the buffer used as this Consumer's queue
	 * 
	 * @return the buffer used as this Consumer's queue
	 */
	protected IQueue getQueue() {
		return (_queue);
	}

	/**
	 * Returns whether the consumer has more items to consume.
	 * 
	 * @return <code>true</code> if there are more items to consume, otherwise
	 *         <code>false</code>
	 */
	public boolean hasNothingToDo() {
		return _queue.isEmpty();
	}

	/**
	 * Lazy creation of the Consumer's thread.
	 * 
	 * @return the Consumer's thread
	 */
	private Thread getThread() {
		if (_thread == null) {
			_thread = new Thread() {
				public void run() {
					Consumer.this.run();
				}
			};
		}
		return _thread;
	}

	/**
	 * Add an object to consume without waking the thread
	 * 
	 * @param the
	 *            object to consume
	 */
	public void addNoNotify(Object o) {
		_queue.add(o);
	}

	/**
	 * Wake up the thread (without adding new stuff to consume)
	 * 
	 */
	public void kickThread() {
		if (!this._thread.isInterrupted()) {
			synchronized (_waitForJobsMonitor) {
				_waitForJobsMonitor.notifyAll();
			}
		}
	}

	/**
	 * Add an object to the Consumer. This is the entry point for the producer.
	 * After the item is added, the consumer's thread will be notified.
	 * 
	 * @param the
	 *            object to be 'consumed' by this consumer
	 */
	public void add(Object o) {
		_queue.add(o);
		kickThread();
	}

	/**
	 * Notification of termination
	 * 
	 */
	public void onThreadTerminate() {
		System.out.println("Consumer " + _name + " : Thread terminated.");
	}

	/**
	 * This method is called before notifiying that current batch was done and
	 * the queue is empty.
	 */
	synchronized public void goingToNotifyThatFinished() {
	}

	/**
	 * This method is called after notifiying that current batch was done and
	 * the queue is empty and just before the consumer's thread is put to sleep
	 * (wait).
	 */
	synchronized public void goingToRest() {
	}

	/**
	 * This method is called before remove new one from queue the consumer's
	 * thread has just woken up from sleep (wait).
	 */
	synchronized public void goingToWakeup() {
	}

	/**
	 * This method will cause the calling thread to wait until the current
	 * conumer batch is finished (i.e. queue is finished).
	 */
	public void waitTillCurrentBatchFinished() {
		// make sure the thread is in a steady state of sleeping or working
		synchronized (_waitForJobsMonitor) {
		}

		// going to wait on _waitForFinishedMonitor
		synchronized (_waitForFinishedMonitor) {
			// Is the tread already sleeping and there are no jobs to be done?
			// then there is no need to wait.
			if (isSleeping() && hasNothingToDo())
				return;

			try {
				// wait for finished notification
				_waitForFinishedMonitor.wait();
			} catch (java.lang.InterruptedException ex) {
			}

			// the thread is not doing any work.
			return;
		}
	}

	/**
	 * Terminate Consumer. Un-consumed objects will be lost.
	 * 
	 */
	public void terminate() {
		_isTerminated = true;

		// don't work anymore
		_queue.clear();

		// wake thread if sleeping so it would terminate.
		synchronized (_waitForJobsMonitor) {
			// release the waiting thread
			_waitForJobsMonitor.notifyAll();
		}
	}

	/**
	 * Terminate Consumer and wait until the consumer's thread finishes it's
	 * job.
	 * 
	 */
	public void terminateWait() {
		if (_isTerminated) {
			return;
		}

		// prevent queue substitution
		synchronized (_queue) {
			// terminate now
			terminate();

			// if needed, wait till thread has finished
			synchronized (_terminationMonitor) {
				if (_waitForTermination) {
					try {
						_terminationMonitor.wait();
					} catch (java.lang.InterruptedException e) {
					}
				}
			}
		}
	}// terminateWait()

	/**
	 * Clears the consumer's queue.
	 */
	public void clear() {
		_queue.clear();
	}

	/**
	 * Main consumer's thread method.
	 */
	private void run() {
		while (!_isTerminated) {
			// job handling loop
			while (true) {
				Object o;
				synchronized (_queue) {
					if (_queue.isEmpty())
						break;
					o = _queue.remove();
				}
				if (o == null)
					break;

				try {
					onConsume(o);
				} catch (Exception ex) {
					logger.critical("Consumer '" + _name
							+ "' : Exception while calling onConsume()!", ex);
				} catch (Error err) {
					logger.critical("Consumer '" + _name
							+ "' : Error while calling onConsume()!!", err);
				} catch (Throwable th) {
					logger.critical("Consumer '" + _name
							+ "' : Throwable while calling onConsume()!!!", th);
				}
			}

			// if we are not terminated and queue is still empty - wait until
			// new jobs arrive.

			synchronized (_waitForJobsMonitor) {
				if (_isTerminated)
					break;
				if (_queue.isEmpty()) {
					try {
						_isSleeping = true;

						// queue is now empty - notify that finished
						goingToNotifyThatFinished();

						// note all waiting that all is done
						synchronized (_waitForFinishedMonitor) {
							_waitForFinishedMonitor.notifyAll();
						}

						goingToRest();

						// sleep-py
						_waitForJobsMonitor.wait();

						_isSleeping = false;
					} catch (InterruptedException ex) {
					}
				}
			}
			goingToWakeup();
		}// while (!_isTerminated)

		// out of while loop - about to terminate

		// Notify final termination
		synchronized (_terminationMonitor) {
			_waitForTermination = false;
			_terminationMonitor.notifyAll();
		}
		onThreadTerminate();
	}// run()

	/**
	 * This is were the actual Consumer work is done.
	 */
	abstract protected void onConsume(Object o);

	public String toString() {
		return _name;
	}

	/**
	 * Sets the consumer's name (and the thread's name).
	 * 
	 * @param name
	 *            the consumer's name.
	 * @return this consumer (for chaining).
	 */
	public Consumer setName(String name) {
		_name = name;
		getThread().setName(_name + " Consumer");
		return this;
	}

	/**
	 * Changes the priority of the consumer's thread.
	 * 
	 * @param prio
	 *            the new priority of the consumer's thread.
	 * @see java.lang.Thread#MAX_PRIORITY
	 * @see java.lang.Thread#MIN_PRIORITY
	 * @return this consumer (for chaining).
	 */
	public Consumer setPriority(int prio) {
		getThread().setPriority(prio);
		return this;
	}

	/**
	 * Start the Consumer.
	 * 
	 * @return this consumer (for chaining).
	 */
	public Consumer init() {
		if (!_isTerminated) {
			logger.warning("Consumner " + this.toString()
					+ ": init() called on a alread running consumer.");
			return this;
		}

		_isTerminated = false;
		_waitForTermination = true;
		getThread().setPriority(Thread.NORM_PRIORITY);
		getThread().start();
		return this;
	}

	/**
	 * Allocates a new <code>Consumer</code> object and sets it's name to it's
	 * class name.
	 */
	public Consumer() {
		setName(this.getClass().toString());
	}

	public static void main(String[] args) {
		// A simple consumer test

		Consumer consumer = new Consumer() {
			public void onConsume(Object o) {
				Integer i = (Integer) o;
				System.out.println(">>> " + i);
			}
		}.init().setName("tester").setQueue(new VectorQueue());

		consumer.add(new Integer(0));

		int i = 0;
		while (true) {
			Integer next = new Integer(i++);
			consumer.add(next);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}
}
