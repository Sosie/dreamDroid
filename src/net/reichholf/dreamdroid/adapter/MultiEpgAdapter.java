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
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import net.reichholf.dreamdroid.helpers.ExtendedHashMap;
import net.reichholf.dreamdroid.helpers.enigma2.Event;

import java.util.ArrayList;

/**
 * @author sre
 * 
 */
public class MultiEpgAdapter extends ArrayAdapter<ExtendedHashMap> {
	private ArrayList<ExtendedHashMap> mItems;
	private int mFactor;
	private int mWidth;

	/**
	 * @param context
	 * @param textViewResourceId
	 * @param objects
	 */
	public MultiEpgAdapter(Context context, int textViewResourceId, ArrayList<ExtendedHashMap> items, int minutes,
			int width) {

		super(context, textViewResourceId, items);
		mItems = items;
		mFactor = width / minutes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		// View view = convertView;
		// if(view == null){
		// LayoutInflater li = (LayoutInflater)
		// getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// view = li.inflate(R.layout.timer_list_item, null);
		// }

		Context ctx = getContext();
		TableLayout tab = new TableLayout(ctx);

		ExtendedHashMap services = mItems.get(position);

		// this is where the almighty layout-voodoo happens....
		if (services != null) {
			TableRow row = new TableRow(ctx);
			@SuppressWarnings("unchecked")
			ArrayList<ExtendedHashMap> items = (ArrayList<ExtendedHashMap>) services.get("items");
			int remaining = mWidth;
			for (ExtendedHashMap item : items) {
				String duration = item.getString(Event.KEY_EVENT_DURATION);
				long d = Long.valueOf(duration);

				d /= 60;
				int width = d <= remaining ? (int) d : remaining;
				width *= mFactor;
				remaining -= width;

				View v = getItemTextView(ctx, item, width);
				row.addView(v);

				// Timeline filled?
				if (remaining == 0) {
					break;
				}
			}

			tab.addView(row);
		}

		return tab;
	}

	private View getItemTextView(Context ctx, ExtendedHashMap item, int width) {
		LinearLayout ll = new LinearLayout(ctx);
		ll.setLayoutParams(new LinearLayout.LayoutParams(width, LayoutParams.WRAP_CONTENT));

		TextView view = new TextView(ctx);
		view.setText(item.getString(Event.KEY_EVENT_NAME));
		ll.addView(view);

		return ll;
	}

}
