/* © 2010 Stephan Reichholf <stephan at reichholf dot net>
 * 
 * Licensed under the Create-Commons Attribution-Noncommercial-Share Alike 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-sa/3.0/
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
