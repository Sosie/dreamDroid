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

package net.reichholf.dreamdroid.parsers.enigma2.saxhandler;

import net.reichholf.dreamdroid.helpers.enigma2.Mediaplayer;

import org.xml.sax.Attributes;

public class E2MediaplayerCurrentHandler extends E2SimpleHandler {

	protected static final String TAG_E2ARTIST = "e2artist";
	protected static final String TAG_E2TITLE = "e2title";
	protected static final String TAG_E2ALBUM = "e2album";
	protected static final String TAG_E2YEAR = "e2year";
	protected static final String TAG_E2GENRE = "e2genre";
	protected static final String TAG_E2COVERFILE = "e2coverfile";

	private boolean inArtist;
	private boolean inTitle;
	private boolean inAlbum;
	private boolean inYear;
	private boolean inGenre;
	private boolean inCoverfile;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String namespaceUri, String localName, String qName, Attributes attrs) {
		if (localName.equals(TAG_E2ARTIST)) {
			inArtist = true;
		} else if (localName.equals(TAG_E2TITLE)) {
			inTitle = true;
		} else if (localName.equals(TAG_E2ALBUM)) {
			inAlbum = true;
		} else if (localName.equals(TAG_E2YEAR)) {
			inYear = true;
		} else if (localName.equals(TAG_E2GENRE)) {
			inGenre = true;
		} else if (localName.equals(TAG_E2COVERFILE)) {
			inCoverfile = true;
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
		if (localName.equals(TAG_E2ARTIST)) {
			inArtist = false;
		} else if (localName.equals(TAG_E2TITLE)) {
			inTitle = false;
		} else if (localName.equals(TAG_E2ALBUM)) {
			inAlbum = false;
		} else if (localName.equals(TAG_E2YEAR)) {
			inYear = false;
		} else if (localName.equals(TAG_E2GENRE)) {
			inGenre = false;
		} else if (localName.equals(TAG_E2COVERFILE)) {
			inCoverfile = false;
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

		if (inArtist) {
			mResult.putOrConcat(Mediaplayer.KEY_ARTIST, value);
		} else if (inTitle) {
			mResult.putOrConcat(Mediaplayer.KEY_TITLE, value);
		} else if (inAlbum) {
			mResult.putOrConcat(Mediaplayer.KEY_ALBUM, value);
		} else if (inYear) {
			mResult.putOrConcat(Mediaplayer.KEY_YEAR, value);
		} else if (inGenre) {
			mResult.putOrConcat(Mediaplayer.KEY_GENRE, value);
		} else if (inCoverfile) {
			mResult.putOrConcat(Mediaplayer.KEY_COVERFILE, value);
		}
	}
}
