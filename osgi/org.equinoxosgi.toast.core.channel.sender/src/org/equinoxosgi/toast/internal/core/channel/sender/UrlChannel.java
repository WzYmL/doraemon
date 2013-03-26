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
 * Create on Apr 28, 2012 5:03:45 PM
 *******************************************************************************/
package org.equinoxosgi.toast.internal.core.channel.sender;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.equinoxosgi.toast.core.ICoreConstants;
import org.equinoxosgi.toast.core.LogUtility;
import org.equinoxosgi.toast.core.PropertyManager;
import org.equinoxosgi.toast.core.UrlBuilder;
import org.equinoxosgi.toast.core.channel.sender.ChannelMessage;
import org.equinoxosgi.toast.core.channel.sender.IChannel;

/**
 * @author LiXP
 * 
 */
public class UrlChannel implements IChannel {

	private String urlSpec;

	public UrlChannel() {
		urlSpec = PropertyManager.getProperty(
				ICoreConstants.BACK_END_URL_PROPERTY,
				ICoreConstants.BACK_END_URL_DEFAULT);

	}

	@Override
	public InputStream send(ChannelMessage message) throws IOException {
		URL url = createUrl(message);
		LogUtility.logInfo(this, "Sending message: " + message.getFunction());
		return url.openStream();
	}

	private URL createUrl(ChannelMessage message) throws MalformedURLException {
		UrlBuilder urlBuilder = new UrlBuilder(urlSpec);
		urlBuilder.appendPath(message.getFunction());

		for (Iterator<?> i = message.getParametersIterator(); i.hasNext();) {
			String parameter = (String) i.next();
			String name = message.valueForParameter(parameter);
			urlBuilder.addParameter(parameter, name);
		}

		URL url = urlBuilder.toUrl();
		String value = urlBuilder.toString();

		LogUtility.logInfo(this, value);

		return url;
	}

}
