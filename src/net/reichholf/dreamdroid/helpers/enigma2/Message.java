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

package net.reichholf.dreamdroid.helpers.enigma2;

import net.reichholf.dreamdroid.helpers.ExtendedHashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**
 * @author sreichholf
 *
 */
public class Message extends SimpleResult {
	public static final String KEY_TEXT = "message";
	public static final String KEY_TYPE = "type";
	public static final String KEY_TIMEOUT = "timeout";	
	public static final String MESSAGE_TYPE_WARNING = "1";
	public static final String MESSAGE_TYPE_INFO = "2";
	public static final String MESSAGE_TYPE_ERROR = "3";
	
	public static ArrayList<NameValuePair> getParams(ExtendedHashMap message){
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("text", message.getString(KEY_TEXT)));
		params.add(new BasicNameValuePair("type", message.getString(KEY_TYPE)));
		params.add(new BasicNameValuePair("timeout", message.getString(KEY_TIMEOUT)));
		
		return params;
	}
}
