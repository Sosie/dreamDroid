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

package net.reichholf.dreamdroid.helpers.enigma2.requesthandler;

import net.reichholf.dreamdroid.helpers.enigma2.URIStore;
import net.reichholf.dreamdroid.parsers.enigma2.saxhandler.E2MediaPlayerListHandler;

/**
 * @author sre
 * 
 */
public class MediaplayerListRequestHandler extends AbstractListRequestHandler {
	public MediaplayerListRequestHandler(){
		super(URIStore.MEDIA_PLAYER_LIST, new E2MediaPlayerListHandler());
	}
}
