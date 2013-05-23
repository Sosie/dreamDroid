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

import net.reichholf.dreamdroid.helpers.ExtendedHashMap;

import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * @author sre
 *
 */
public abstract class E2ListHandler extends DefaultHandler {
	protected ArrayList<ExtendedHashMap> mList;
	
	public E2ListHandler(){
		mList = null;
	}
	
	public void setList(ArrayList<ExtendedHashMap> list){
		mList = list;
	}
}