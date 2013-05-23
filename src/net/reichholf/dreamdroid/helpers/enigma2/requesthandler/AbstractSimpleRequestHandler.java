/*
 * Copyright © 2013. Stephan Reichholf
 *
 * Unless stated otherwise in a files head all java and xml-code of this Project is:
 *
 * Licensed under the Create-Commons Attribution-Noncommercial-Share Alike 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-sa/3.0/
 *
 * All grahpics, except the dreamdroid icon, can be used for any other non-commercial purposes.
 * The dreamdroid icon may not be used in any other projects than dreamdroid itself.
 */

package net.reichholf.dreamdroid.helpers.enigma2.requesthandler;

import net.reichholf.dreamdroid.helpers.ExtendedHashMap;
import net.reichholf.dreamdroid.helpers.SimpleHttpClient;
import net.reichholf.dreamdroid.helpers.enigma2.Request;
import net.reichholf.dreamdroid.helpers.enigma2.requestinterfaces.SimpleRequestInterface;
import net.reichholf.dreamdroid.parsers.enigma2.saxhandler.E2SimpleHandler;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

/**
 * @author sre
 * 
 */
public abstract class AbstractSimpleRequestHandler implements SimpleRequestInterface {
	protected String mUri;
	private E2SimpleHandler mHandler;

	/**
	 * @param uri
	 * @param handler
	 */
	public AbstractSimpleRequestHandler(String uri, E2SimpleHandler handler) {
		mUri = uri;
		mHandler = handler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.reichholf.dreamdroid.helpers.enigma2.requesthandler.
	 * SimpleRequestParamInterface
	 * #get(net.reichholf.dreamdroid.helpers.SimpleHttpClient,
	 * java.util.ArrayList)
	 */
	public String get(SimpleHttpClient shc) {
		return Request.get(shc, mUri);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.reichholf.dreamdroid.helpers.enigma2.requesthandler.
	 * SimpleRequestParamInterface
	 * #get(net.reichholf.dreamdroid.helpers.SimpleHttpClient,
	 * java.util.ArrayList)
	 */
	public String get(SimpleHttpClient shc, ArrayList<NameValuePair> params) {
		return Request.get(shc, mUri, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.reichholf.dreamdroid.helpers.enigma2.requesthandler.
	 * SimpleRequestParamInterface#parse(java.lang.String)
	 */
	public boolean parse(String xml, ExtendedHashMap result) {
		if (Request.parse(xml, result, mHandler)) {
			return true;
		} else {
			result.clear();
			result.putAll(getDefault());
			return false;
		}
	}

	public ExtendedHashMap getDefault() {
		return new ExtendedHashMap();
	}
}
