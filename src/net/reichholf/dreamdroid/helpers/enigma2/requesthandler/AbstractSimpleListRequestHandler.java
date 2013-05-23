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

import net.reichholf.dreamdroid.helpers.SimpleHttpClient;
import net.reichholf.dreamdroid.helpers.enigma2.Request;
import net.reichholf.dreamdroid.parsers.enigma2.saxhandler.E2SimpleListHandler;

import java.util.ArrayList;

/**
 * @author sre
 *
 */
public abstract class AbstractSimpleListRequestHandler {
	private String mUri;
	private E2SimpleListHandler mHandler;
	
	/**
	 * @param uri
	 * @param handler
	 */
	public AbstractSimpleListRequestHandler(String uri, E2SimpleListHandler handler){
		mUri = uri;
		mHandler = handler;
	}
	
	/**
	 * @param shc
	 * @return
	 */
	public String getList(SimpleHttpClient shc){
		return Request.get(shc, mUri);
	}
	
	/**
	 * @param xml
	 * @param list
	 * @return
	 */
	public boolean parseList(String xml, ArrayList<String> list){
		return Request.parseList(xml, list, mHandler);
	}
}
