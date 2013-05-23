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

package net.reichholf.dreamdroid;

import android.annotation.TargetApi;
import android.app.backup.BackupAgentHelper;
import android.app.backup.FileBackupHelper;
import android.app.backup.SharedPreferencesBackupHelper;
import android.os.Build;

/**
 * @author sre
 *
 */
@TargetApi(Build.VERSION_CODES.FROYO)
public class DreamDroidBackupAgent extends BackupAgentHelper {
	public static final String PREFS = "net.reichholf.dreamdroid_preferences";
	
	public static final String DATABASE_BACKUP_KEY = "database";
	public static final String PREFS_BACKUP_KEY ="preferences";
	public void onCreate(){
		SharedPreferencesBackupHelper spbh = new SharedPreferencesBackupHelper(this, PREFS);
		addHelper(PREFS_BACKUP_KEY, spbh);
		FileBackupHelper dbfbh = new FileBackupHelper(this, "../databases/" + DatabaseHelper.DATABASE_NAME);
		addHelper(DATABASE_BACKUP_KEY, dbfbh);
	}
}
