/* © 2010 Stephan Reichholf <stephan at reichholf dot net>
 * 
 * Licensed under the Create-Commons Attribution-Noncommercial-Share Alike 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-sa/3.0/
 */

package net.reichholf.dreamdroid.parsers.enigma2.saxhandler;

import net.reichholf.dreamdroid.helpers.ExtendedHashMap;
import net.reichholf.dreamdroid.helpers.enigma2.Mediaplayer;

import org.xml.sax.Attributes;

import java.util.ArrayList;

public class E2MediaPlayerListHandler extends E2ListHandler {
	protected static final String TAG_E2ROOT = "e2root";
	protected static final String TAG_E2ISDIRECTORY = "e2isdirectory";
	protected static final String TAG_E2SERVICEREFERENCE = "e2servicereference";
	protected static final String TAG_E2FILE = "e2file";

	private boolean inFile;
	private boolean inRef;
	private boolean inIsDirectory;
	private boolean inRoot;

	private ExtendedHashMap mItem;
	private ArrayList<ExtendedHashMap> mList;

	/**
	 * @param list
	 */
	public E2MediaPlayerListHandler() {
		mItem = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String namespaceUri, String localName, String qName, Attributes attrs) {
		if (localName.equals(TAG_E2FILE)) {
			inFile = true;
			mItem = new ExtendedHashMap();
		} else if (localName.equals(TAG_E2SERVICEREFERENCE)) {
			inRef = true;
		} else if (localName.equals(TAG_E2ISDIRECTORY)) {
			inIsDirectory = true;
		} else if (localName.equals(TAG_E2ROOT)) {
			inRoot = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String namespaceURI, String localName, String qName) {
		if (localName.equals(TAG_E2FILE)) {
			inFile = false;
			if (mItem != null) {
				mList.add(mItem);
				mItem = null;
			}
		} else if (localName.equals(TAG_E2SERVICEREFERENCE)) {
			inRef = false;
		} else if (localName.equals(TAG_E2ISDIRECTORY)) {
			inIsDirectory = false;
		} else if (localName.equals(TAG_E2ROOT)) {
			inRoot = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char ch[], int start, int length) {
		String value = new String(ch, start, length);

		if (inFile) {
			if (inRef) {
				mItem.putOrConcat(Mediaplayer.KEY_SERVICE_REFERENCE, value);
			} else if (inIsDirectory) {
				mItem.putOrConcat(Mediaplayer.KEY_IS_DIRECTORY, value);
			} else if (inRoot) {
				mItem.putOrConcat(Mediaplayer.KEY_ROOT, value);
			}
		}
	}
}
