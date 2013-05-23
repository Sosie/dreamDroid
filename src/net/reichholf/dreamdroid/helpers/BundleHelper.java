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

package net.reichholf.dreamdroid.helpers;

import java.util.ArrayList;

/**
 * @author sre
 *
 */
public class BundleHelper {
	public static ArrayList<String> toStringArrayList(CharSequence[] strings){
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < strings.length; ++i){
			list.add(strings[i].toString());
		}
		return list;
	}
	
	public static CharSequence[] toCharSequenceArray(ArrayList<String> strings){
		CharSequence[] list = new CharSequence[strings.size()];
		for(int i = 0; i < strings.size(); ++i){
			list[i] = strings.get(i);
		}
		return list;
	}
}
