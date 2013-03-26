/*
 * IQueue.java
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

public interface IQueue {
	public boolean isEmpty();

	public void clear();

	public void add(Object o);

	public Object remove();
}
