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

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import net.reichholf.dreamdroid.DreamDroid;
import net.reichholf.dreamdroid.R;
import net.reichholf.dreamdroid.helpers.SimpleHttpClient;
import net.reichholf.dreamdroid.helpers.enigma2.Request;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

/**
 * @author sre
 * 
 */
public class AsyncByteLoader extends AsyncTaskLoader<LoaderResult<byte[]>> {

	private SimpleHttpClient mShc;
	protected ArrayList<NameValuePair> mParams;
	protected String mUri;

	public AsyncByteLoader(Context context, Bundle args) {
		super(context);
		init(context, args);
	}

	@SuppressWarnings("unchecked")
	private void init(Context context, Bundle args) {
		DreamDroid.loadCurrentProfile(context);
		mShc = new SimpleHttpClient();
		if (args != null && args.containsKey("params"))
			mParams = (ArrayList<NameValuePair>) args.getSerializable("params");
		else
			mParams = null;
		
		mUri = args.getString("uri");
	}

	@Override
	protected void onStartLoading() {
		forceLoad();
	}

	@Override
	protected void onStopLoading() {
		// Attempt to cancel the current load task if possible.
		cancelLoad();
	}

	@Override
	public LoaderResult<byte[]> loadInBackground() {
		byte[] data = null;

		if (mParams == null)
			data = Request.getBytes(mShc, mUri);
		else
			data = Request.getBytes(mShc, mUri, mParams);

		LoaderResult<byte[]> result = new LoaderResult<byte[]>();
		if (data.length > 0) {
			result.set(data);
		} else {
			if (mShc.hasError())
				result.set(mShc.getErrorText());
			else
				result.set(getContext().getString(R.string.error));
		}
		return result;
	}

}
