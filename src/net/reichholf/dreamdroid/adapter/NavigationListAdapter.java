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

package net.reichholf.dreamdroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.reichholf.dreamdroid.R;

/**
 * @author sre
 *
 */
public class NavigationListAdapter extends ArrayAdapter<int[]> {
		
	/**
	 * @param context
	 * @param textViewResourceId
	 * @param objects
	 */
	public NavigationListAdapter(Context context, int[][] items) {
		super(context, R.layout.nav_list_item, items);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null){
			LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = li.inflate(R.layout.nav_list_item, null);
		}

		int[] item = getItem(position);
		TextView text = (TextView) view.findViewById(android.R.id.text1);
		text.setText(item[1]);
		text.setCompoundDrawablesWithIntrinsicBounds(item[2], 0, 0, 0);
		return view;
	}
}
