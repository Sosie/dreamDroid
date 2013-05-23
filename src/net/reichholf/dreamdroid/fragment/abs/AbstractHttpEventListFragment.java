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

package net.reichholf.dreamdroid.fragment.abs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import net.reichholf.dreamdroid.R;
import net.reichholf.dreamdroid.fragment.dialogs.ActionDialog;
import net.reichholf.dreamdroid.fragment.dialogs.EpgDetailDialog;
import net.reichholf.dreamdroid.helpers.ExtendedHashMap;
import net.reichholf.dreamdroid.helpers.Statics;
import net.reichholf.dreamdroid.helpers.enigma2.Timer;
import net.reichholf.dreamdroid.helpers.enigma2.requesthandler.TimerAddByEventIdRequestHandler;
import net.reichholf.dreamdroid.intents.IntentFactory;

/**
 * @author sreichholf
 * 
 */
public abstract class AbstractHttpEventListFragment extends AbstractHttpListFragment implements
		ActionDialog.DialogActionListener {

	protected String mReference;
	protected String mName;

	protected ProgressDialog mProgress;
	protected ExtendedHashMap mCurrentItem;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			mReference = savedInstanceState.getString("reference");
			mName = savedInstanceState.getString("name");
			mCurrentItem = (ExtendedHashMap) savedInstanceState.getParcelable("currentItem");
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString("reference", mReference);
		outState.putString("name", mName);
		outState.putParcelable("currentItem", mCurrentItem);

		super.onSaveInstanceState(outState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		mCurrentItem = mMapList.get((int) id);
		Bundle args = new Bundle();
		args.putParcelable("currentItem", mCurrentItem);
		getMultiPaneHandler().showDialogFragment(EpgDetailDialog.class, args, "epg_detail_dialog");
	}

	/**
	 * @param event
	 */
	protected void setTimerById(ExtendedHashMap event) {
		if (mProgress != null) {
			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}
		}

		mProgress = ProgressDialog.show(getSherlockActivity(), "", getText(R.string.saving), true);
		execSimpleResultTask(new TimerAddByEventIdRequestHandler(), Timer.getEventIdParams(event));
	}

	@Override
	public void onSimpleResult(boolean success, ExtendedHashMap result) {
		if (mProgress != null) {
			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}
		}
		super.onSimpleResult(success, result);
	}

	/**
	 * @param event
	 */
	protected void setTimerByEventData(ExtendedHashMap event) {
		Timer.editUsingEvent(getMultiPaneHandler(), event, this);
	}

	public void onDialogAction(int action, Object details, String dialogTag) {
		switch (action) {
		case Statics.ACTION_SET_TIMER:
			setTimerById(mCurrentItem);
			break;
		case Statics.ACTION_EDIT_TIMER:
			setTimerByEventData(mCurrentItem);
			break;
		case Statics.ACTION_FIND_SIMILAR:
			mHttpHelper.findSimilarEvents(mCurrentItem);
			break;
		case Statics.ACTION_IMDB:
			IntentFactory.queryIMDb(getSherlockActivity(), mCurrentItem);
			break;
		}
	}
}
