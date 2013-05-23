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

package net.reichholf.dreamdroid.abstivities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

/**
 * @author sre
 * 
 */
public interface MultiPaneHandler {
	public void showDetails(Fragment fragment);

	public void showDetails(Fragment fragment, boolean addToBackStack);

	public void showDetails(Class<? extends Fragment> fragmentClass);

	public void onFragmentResume(Fragment fragment);

	public void onFragmentPause(Fragment fragment);

	public void showDialogFragment(Class<? extends DialogFragment> fragmentClass, Bundle args, String tag);

	public void showDialogFragment(DialogFragment fragment, String tag);

	public boolean isMultiPane();
	
	public boolean isSlidingMenu();

	public void finish();
}
