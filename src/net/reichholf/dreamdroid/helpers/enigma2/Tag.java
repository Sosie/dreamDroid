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

import java.util.ArrayList;

public class Tag {
	/**
	 * @param selectedTags
	 * @return
	 */
	public static String implodeTags(ArrayList<String> selectedTags){
		String tags = "";
		for (String tag : selectedTags) {
			if ("".equals(tags)) {
				tags = tags.concat(tag);
			} else {
				tags = tags.concat(" ").concat(tag);
			}
		}
		
		return tags;
	}
}
