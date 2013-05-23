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

package net.reichholf.dreamdroid.helpers;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import net.reichholf.dreamdroid.DreamDroid;
import net.reichholf.dreamdroid.Profile;

import java.io.File;
import java.lang.ref.WeakReference;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPFile;

/**
 * @author sre
 * 
 */
public class PiconDownloadTask extends AsyncTask<String, Integer, Void> {
	private static String LOG_TAG = "PiconDownloadTask";

	private DownloadProgress progress;
	private WeakReference<PiconDownloadProgressListener> progressListener;

	public PiconDownloadTask(PiconDownloadProgressListener listener) {
		progress = new DownloadProgress();
		progressListener = new WeakReference<PiconDownloadProgressListener>(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Void doInBackground(String... params) {
		String basePath = String.format("%s%sdreamDroid%spicons%s", Environment.getExternalStorageDirectory()
				.getAbsolutePath(), File.separator, File.separator, File.separator);

		String remotePath = params[0];
		FTPClient client = new FTPClient();
		Profile p = DreamDroid.getCurrentProfile();

		try {
			// Check for the required directories
			File tmpFile = new File(basePath);
			if (!tmpFile.exists())
				tmpFile.mkdirs();

			publishProgress(DownloadProgress.EVENT_ID_CONNECTING);
			client.connect(p.getHost());
			publishProgress(DownloadProgress.EVENT_ID_CONNECTED);
			client.login(p.getUser(), p.getPass());
			publishProgress(DownloadProgress.EVENT_ID_LOGIN_SUCCEEDED);
			client.setType(FTPClient.TYPE_BINARY);

			client.changeDirectory(remotePath);
			publishProgress(DownloadProgress.EVENT_ID_LISTING);
			FTPFile[] fileList = client.list("*.png");
			progress.totalFiles = fileList.length;
			publishProgress(DownloadProgress.EVENT_ID_LISTING_READY);
			for (FTPFile remoteFile : fileList) {
				if (isCancelled())
					return null;
				if (remoteFile.getType() != FTPFile.TYPE_FILE)
					continue;
				String fileName = remoteFile.getName();

				progress.currentFile = fileName;
				publishProgress(DownloadProgress.EVENT_ID_DOWNLOADING_FILE);

				File localFile = new File(String.format("%s%s", basePath, fileName));
				localFile.createNewFile();

				try {
					client.download(fileName, localFile);
				} catch (Exception e) {
					e.printStackTrace();
					Log.e(LOG_TAG, "Failed to download picon with filename " + fileName);
				}
				progress.downloadedFiles++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			progress.error = true;
			progress.errorText = e.getMessage();
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... eventid) {
		raiseEvent(eventid[0]);
	}

	@Override
	protected void onPostExecute(Void unused) {
		raiseEvent(DownloadProgress.EVENT_ID_FINISHED);
	}

	protected void raiseEvent(int eventid) {
		PiconDownloadProgressListener listener = progressListener.get();
		if (listener != null)
			listener.updatePiconDownloadProgress(eventid, progress);
	}

	public interface PiconDownloadProgressListener {
		public void updatePiconDownloadProgress(int eventid, DownloadProgress progress);
	}

	public class DownloadProgress {
		public static final int EVENT_ID_CONNECTING = 0;
		public static final int EVENT_ID_CONNECTED = 1;
		public static final int EVENT_ID_LOGIN_SUCCEEDED = 2;
		public static final int EVENT_ID_LISTING = 3;
		public static final int EVENT_ID_LISTING_READY = 4;
		public static final int EVENT_FILE_COUNT = 5;
		public static final int EVENT_ID_DOWNLOADING_FILE = 6;
		public static final int EVENT_ID_FINISHED = 7;

		public boolean connected;
		public boolean error;
		public int totalFiles;
		public int downloadedFiles;
		public String currentFile;
		public String errorText;

		public DownloadProgress() {
			connected = false;
			error = false;
			totalFiles = 0;
			downloadedFiles = 0;
			currentFile = "";
			errorText = "";
		}
	}

}
