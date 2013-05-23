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

package net.reichholf.dreamdroid.helpers.enigma2.requestinterfaces;

import net.reichholf.dreamdroid.helpers.ExtendedHashMap;
import net.reichholf.dreamdroid.helpers.SimpleHttpClient;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

/**
 * @author sre
 *
 */
public interface SimpleRequestInterface {
	public String get(SimpleHttpClient shc);
	public String get(SimpleHttpClient shc, ArrayList<NameValuePair> params);
	public boolean parse(String xml, ExtendedHashMap result);
	public ExtendedHashMap getDefault();
}
