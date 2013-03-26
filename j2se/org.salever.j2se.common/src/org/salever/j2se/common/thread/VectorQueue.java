/*
 * VectorQueue.java
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

import java.util.*;

public class VectorQueue implements IQueue {
	private Vector<Object> myVec = new Vector<Object>();

	public Vector<Object> getVector() {
		return myVec;
	}

	public boolean isEmpty() {
		return myVec.isEmpty();
	}

	public void clear() {
		myVec.clear();
	}

	public void add(Object o) {
		myVec.add(o);
	}

	public Object remove() {
		Object o = myVec.get(0);
		myVec.remove(o);
		return o;
	}

	public int size() {
		return myVec.size();
	}
}
