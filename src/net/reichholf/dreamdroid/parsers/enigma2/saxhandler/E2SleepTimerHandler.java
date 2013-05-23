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

import net.reichholf.dreamdroid.helpers.enigma2.SleepTimer;

import org.xml.sax.Attributes;

public class E2SleepTimerHandler extends E2SimpleHandler {

	protected static final String TAG_E2SLEEPTIMER = "e2sleeptimer";
	protected static final String TAG_E2ENABLED = "e2enabled";
	protected static final String TAG_E2MINUTES = "e2minutes";
	protected static final String TAG_E2ACTION = "e2action";
	protected static final String TAG_E2TEXT = "e2text";

	private boolean inSleeptimer = false;
	private boolean inEnabled = false;
	private boolean inMinutes = false;
	private boolean inAction = false;
	private boolean inText = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String namespaceUri, String localName, String qName, Attributes attrs) {
		if (localName.equals(TAG_E2SLEEPTIMER)) {
			inSleeptimer = true;
		} else if (inSleeptimer) {
			if (localName.equals(TAG_E2ENABLED)) {
				inEnabled = true;
			} else if (localName.equals(TAG_E2MINUTES)) {
				inMinutes = true;
			} else if (localName.equals(TAG_E2ACTION)) {
				inAction = true;
			} else if (localName.equals(TAG_E2TEXT)) {
				inText = true;
			}
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
		if (localName.equals(TAG_E2SLEEPTIMER)) {
			inSleeptimer = false;
		} else if (inSleeptimer) {
			if (localName.equals(TAG_E2ENABLED)) {
				inEnabled = false;
			} else if (localName.equals(TAG_E2MINUTES)) {
				inMinutes = false;
			} else if (localName.equals(TAG_E2ACTION)) {
				inAction = false;
			} else if (localName.equals(TAG_E2TEXT)) {
				inText = false;
			}
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

		if (inEnabled) {
			mResult.putOrConcat(SleepTimer.KEY_ENABLED, value);
		} else if (inMinutes) {
			mResult.putOrConcat(SleepTimer.KEY_MINUTES, value);
		} else if (inAction) {
			mResult.putOrConcat(SleepTimer.KEY_ACTION, value);
		} else if (inText) {
			mResult.putOrConcat(SleepTimer.KEY_TEXT, value);
		}
	}
}
