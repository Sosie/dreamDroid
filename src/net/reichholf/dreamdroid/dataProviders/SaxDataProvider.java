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

package net.reichholf.dreamdroid.dataProviders;

import net.reichholf.dreamdroid.parsers.GenericSaxParser;

import org.xml.sax.helpers.DefaultHandler;

/**
 * @author sreichholf
 * 
 */
public class SaxDataProvider extends AbstractDataProvider {

	/**
	 * @param dp
	 */
	public SaxDataProvider(GenericSaxParser dp) {
		super(dp);
	}

	/**
	 * @param dp
	 */
	public void setParser(GenericSaxParser dp) {
		mParser = dp;
	}
	
	public void setHandler(DefaultHandler handler){
		getParser().setHandler(handler);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.reichholf.dreamdroid.dataProviders.AbstractDataProvider#getParser()
	 */
	public GenericSaxParser getParser() {
		return (GenericSaxParser) mParser;
	}

}
