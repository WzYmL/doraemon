/**
 * File: MidpointOffsetLocator.java
 *
 * Author: salever@126.com
 *
 * Create on: 2009-11-24 上午10:36:19
 */
package org.salever.rcp.tools.topology_viewer.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

/**
 * @author salever@126.com
 * 
 */
public class ConnectionOffsetLocator extends ConnectionLocator {

	public ConnectionOffsetLocator(Connection connection, int align) {
		super(connection, align);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Point getLocation(PointList points) {
		// TODO Auto-generated method stub
		switch (getAlignment()) {
		case SOURCE:
			if (points.size() % 2 == 0) {
				int i = points.size() / 2;
				int j = i - 1;
				Point p1 = points.getPoint(j);
				Point p2 = points.getPoint(i);
				Dimension d = p2.getDifference(p1);
				return Point.SINGLETON.setLocation(p1.x + d.width / 4, p1.y
						+ d.height / 4);
			} else {
				Point dstPoint = points.getLastPoint();
				Point midPoint = points.getMidpoint();
				Dimension d = dstPoint.getDifference(midPoint);
				return Point.SINGLETON.setLocation(midPoint.x + d.width / 4,
						midPoint.y + d.height / 4);
			}

		case TARGET:
			if (points.size() % 2 == 0) {
				int i = points.size() / 2;
				int j = i - 1;
				Point p1 = points.getPoint(j);
				Point p2 = points.getPoint(i);
				Dimension d = p2.getDifference(p1);
				return Point.SINGLETON.setLocation(p1.x + d.width * 3 / 4, p1.y
						+ d.height * 3 / 4);
			} else {
				Point dstPoint = points.getLastPoint();
				Point midPoint = points.getMidpoint();
				Dimension d = dstPoint.getDifference(midPoint);
				return Point.SINGLETON.setLocation(
						midPoint.x + d.width * 3 / 4, midPoint.y + d.height * 3
								/ 4);
			}

		case MIDDLE:
			if (points.size() % 2 == 0) {
				int i = points.size() / 2;
				int j = i - 1;
				Point p1 = points.getPoint(j);
				Point p2 = points.getPoint(i);
				Dimension d = p2.getDifference(p1);
				return Point.SINGLETON.setLocation(p1.x + d.width / 3, p1.y
						+ d.height / 3);
			} else {
				Point dstPoint = points.getLastPoint();
				Point midPoint = points.getMidpoint();
				Dimension d = dstPoint.getDifference(midPoint);
				return Point.SINGLETON.setLocation(midPoint.x + d.width / 3,
						midPoint.y + d.height / 3);
			}

		default:
			return new Point();
		}
	}

}
