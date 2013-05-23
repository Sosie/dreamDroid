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
public class RefStore {
	public static String TV_BOUQUETS = "1:7:1:0:0:0:0:0:0:0:(type == 1) || (type == 17) || (type == 195) || (type == 25) FROM BOUQUET \"bouquets.tv\" ORDER BY bouquet";
	public static String TV_PROVIDER = "1:7:1:0:0:0:0:0:0:0:(type == 1) || (type == 17) || (type == 195) || (type == 25) FROM PROVIDERS ORDER BY name";
	public static String TV_ALL = "1:7:1:0:0:0:0:0:0:0:(type == 1) || (type == 17) || (type == 195) || (type == 25) ORDER BY name";

	public static String RADIO_BOUQUETS = "1:7:2:0:0:0:0:0:0:0:(type == 2)FROM BOUQUET \"bouquets.radio\" ORDER BY bouquet";
	public static String RADIO_PROVIDERS = "1:7:2:0:0:0:0:0:0:0:(type == 2) FROM PROVIDERS ORDER BY name";
	public static String RADIO_ALL = "1:7:2:0:0:0:0:0:0:0:(type == 2) ORDER BY name";
}
