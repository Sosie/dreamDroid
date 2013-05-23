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


/**
 * @author sreichholf
 * 
 */
public class Service {
	public static final String KEY_NAME = "name";
	public static final String KEY_REFERENCE = "reference";
	
	public static boolean isBouquet(String ref){
		return ref.startsWith("1:7:");
	}
	
	public static boolean isMarker(String ref){
		return ref.startsWith("1:64");
	}
}
