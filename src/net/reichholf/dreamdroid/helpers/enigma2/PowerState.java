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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


/**
 * @author sre
 *
 */
public class PowerState {
	public static String KEY_IN_STANDBY = "standby";
	
	public static String CMD_SET = "set";
	
	public static String STATE_GET = "-1";
	public static String STATE_TOGGLE = "0";
	public static String STATE_SHUTDOWN = "1";
	public static String STATE_SYSTEM_REBOOT = "2";
	public static String STATE_GUI_RESTART = "3";
	
	public static ArrayList<NameValuePair> getStateParams(String state){
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();		
		params.add(new BasicNameValuePair("newstate", state) );
		
		return params;
	}
}
