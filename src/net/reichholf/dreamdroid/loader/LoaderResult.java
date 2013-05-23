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

package net.reichholf.dreamdroid.loader;

/**
 * @author sre
 * 
 */
public class LoaderResult<T> {
	private T mResult;
	private String mErrorText;
	private boolean mError;

	public LoaderResult() {
		mError = true;
		mErrorText = null;
		mResult = null;
	}

	public void set(T result) {
		mResult = result;
		mError = false;
		mErrorText = null;
	}

	public void set(String errorText) {
		mResult = null;
		mError = true;
		mErrorText = errorText;
	}

	public T getResult() {
		return mResult;
	}

	public String getErrorText() {
		return mErrorText;
	}

	public boolean isError() {
		return mError;
	}
}
