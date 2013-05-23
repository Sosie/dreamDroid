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
public class Movie{
	public static final String KEY_REFERENCE = "reference";
	public static final String KEY_TITLE = "title";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_DESCRIPTION_EXTENDED = "descriptionEx";
	public static final String KEY_SERVICE_NAME = "servicename";
	public static final String KEY_TIME = "time";
	public static final String KEY_TIME_READABLE = "time_readable";
	public static final String KEY_LENGTH = "length";
	public static final String KEY_TAGS = "tags";
	public static final String KEY_FILE_NAME = "filename";
	public static final String KEY_FILE_SIZE = "filesize";
	public static final String KEY_FILE_SIZE_READABLE = "filesize_readable";

	public static ArrayList<NameValuePair> getDeleteParams(ExtendedHashMap movie){
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sRef", movie.getString(Movie.KEY_REFERENCE)));
		
		return params;
	}
	
}
