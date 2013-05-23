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

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author sre
 *
 */
public class ExtendedHashMapHelper {
	
	public static ArrayList<ExtendedHashMap> restoreListFromBundle(Bundle bundle, String key){
		ArrayList<ExtendedHashMap> l = new ArrayList<ExtendedHashMap>();
		
		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) bundle
				.getSerializable(key);
		
		Iterator<HashMap<String, Object>> iter = list.iterator();
		while (iter.hasNext()) {
			l.add(new ExtendedHashMap(iter.next()));
		}
		
		return l;
	}
}
