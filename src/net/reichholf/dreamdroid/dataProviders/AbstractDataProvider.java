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

import net.reichholf.dreamdroid.dataProviders.interfaces.DataParser;

/**
 * @author sreichholf
 * 
 */
public abstract class AbstractDataProvider {
	protected DataParser mParser;

	/**
	 * @param dp
	 */
	public AbstractDataProvider(DataParser dp) {
		mParser = dp;
	}

	/**
	 * @param dp
	 */
	public void setParser(DataParser dp) {
		mParser = dp;
	}

	/**
	 * @return
	 */
	public DataParser getParser() {
		return mParser;
	}

	/**
	 * @param input
	 * @return
	 */
	public boolean parse(String input) {
		return mParser.parse(input);
	}
}
