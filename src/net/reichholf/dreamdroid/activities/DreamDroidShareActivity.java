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

package net.reichholf.dreamdroid.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListActivity;

import net.reichholf.dreamdroid.DatabaseHelper;
import net.reichholf.dreamdroid.Profile;
import net.reichholf.dreamdroid.R;
import net.reichholf.dreamdroid.helpers.ExtendedHashMap;
import net.reichholf.dreamdroid.helpers.SimpleHttpClient;
import net.reichholf.dreamdroid.helpers.enigma2.URIStore;
import net.reichholf.dreamdroid.helpers.enigma2.requesthandler.SimpleResultRequestHandler;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author sre
 * 
 */
public class DreamDroidShareActivity extends SherlockListActivity {
	public static String LOG_TAG = DreamDroidShareActivity.class.getSimpleName();

	private SimpleResultTask mSimpleResultTask;
	private SimpleHttpClient mShc;
	private SimpleAdapter mAdapter;
	private ArrayList<ExtendedHashMap> mProfileMapList;
	private ProgressDialog mProgress;
	private String mTitle;

	ArrayList<Profile> mProfiles;

	protected class SimpleResultTask extends AsyncTask<ArrayList<NameValuePair>, Void, Boolean> {
		private ExtendedHashMap mResult;
		private SimpleResultRequestHandler mHandler;

		public SimpleResultTask(SimpleResultRequestHandler handler) {
			mHandler = handler;
		}

		@Override
		protected Boolean doInBackground(ArrayList<NameValuePair>... params) {
			if (isCancelled())
				return false;
			publishProgress();
			String xml = mHandler.get(mShc, params[0]);

			if (xml != null) {
				ExtendedHashMap result = mHandler.parseSimpleResult(xml);

				String stateText = result.getString("statetext");

				if (stateText != null) {
					mResult = result;
					return true;
				}
			}

			return false;
		}

		@Override
		protected void onProgressUpdate(Void... progress) {
			if (!isCancelled())
				DreamDroidShareActivity.this.setProgressBarIndeterminateVisibility(true);
		}

		protected void onPostExecute(Boolean result) {
			DreamDroidShareActivity.this.setProgressBarIndeterminateVisibility(false);

			if (!result || mResult == null) {
				mResult = new ExtendedHashMap();
			}

			DreamDroidShareActivity.this.onSimpleResult(result, mResult);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_list_content);
		setTitle(getText(R.string.watch_on_dream));
		load();
	}

	@Override
	public void onDestroy() {
		if (mProgress != null) {
			mProgress.dismiss();
			mProgress = null;
		}
		if (mSimpleResultTask != null)
			mSimpleResultTask.cancel(true);
		super.onDestroy();
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Profile profile = mProfiles.get(position);
		playOnDream(profile);
	}

	@SuppressWarnings("deprecation")
	private void playOnDream(Profile p) {
		String url = null;
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		mShc = SimpleHttpClient.getInstance(p);
		if (Intent.ACTION_SEND.equals(i.getAction()))
			url = extras.getString(Intent.EXTRA_TEXT);
		else if (Intent.ACTION_VIEW.equals(i.getAction()))
			url = i.getDataString();

		if (url != null) {
			Log.i(LOG_TAG, url);
			Log.i(LOG_TAG, p.getHost());

			String time = DateFormat.getDateFormat(this).format(new Date());
			String title = getString(R.string.sent_from_dreamdroid, time);
			if (extras != null) {
				// semperVidLinks sends "artist" and "song" attributes for the
				// youtube video titles
				String song = extras.getString("song");
				if (song != null) {
					String artist = extras.getString("artist");
					if (artist != null)
						title = artist + " - " + song;
				} else {
					String tmp = extras.getString("title");
					if(tmp != null)
						title = tmp;
				}
			}
			mTitle = new String(title);

			url = URLEncoder.encode(url).replace("+", "%20");
			title = URLEncoder.encode(title).replace("+", "%20");

			String ref = "4097:0:1:0:0:0:0:0:0:0:" + url + ":" + title;
			Log.i(LOG_TAG, ref);
			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("file", ref));
			execSimpleResultTask(params);
		} else {
			finish();
		}
	}

	public void load() {
		DatabaseHelper dbh = DatabaseHelper.getInstance(this);
		mProfileMapList = new ArrayList<ExtendedHashMap>();
		mProfileMapList.clear();
		mProfiles = dbh.getProfiles();
		if (mProfiles.size() > 1) {
			for (Profile m : mProfiles) {
				ExtendedHashMap map = new ExtendedHashMap();
				map.put(DatabaseHelper.KEY_PROFILE, m.getName());
				map.put(DatabaseHelper.KEY_HOST, m.getHost());
				mProfileMapList.add(map);
			}

			mAdapter = new SimpleAdapter(this, mProfileMapList, android.R.layout.two_line_list_item, new String[] {
					DatabaseHelper.KEY_PROFILE, DatabaseHelper.KEY_HOST }, new int[] { android.R.id.text1,
					android.R.id.text2 });
			setListAdapter(mAdapter);
			mAdapter.notifyDataSetChanged();
		} else {
			if (mProfiles.size() == 1) {
				playOnDream(mProfiles.get(0));
			} else {
				showToast(getString(R.string.no_profile_available));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void execSimpleResultTask(ArrayList<NameValuePair> params) {
		if (mSimpleResultTask != null) {
			mSimpleResultTask.cancel(true);
		}
		mProgress = ProgressDialog.show(this, getString(R.string.loading), getString(R.string.loading));
		SimpleResultRequestHandler handler = new SimpleResultRequestHandler(URIStore.MEDIA_PLAYER_PLAY);
		mSimpleResultTask = new SimpleResultTask(handler);
		mSimpleResultTask.execute(params);
	}

	public void onSimpleResult(boolean success, ExtendedHashMap result) {
		if (mProgress != null) {
			mProgress.dismiss();
			mProgress = null;
		}

		if (mTitle == null)
			mTitle = "...";
		String toastText = getString(R.string.sent_as, mTitle);
		if (mShc.hasError()) {
			toastText = mShc.getErrorText();
		}

		showToast(toastText);
		finish();
	}

	public void showToast(String text) {
		Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
		toast.show();
	}
}
