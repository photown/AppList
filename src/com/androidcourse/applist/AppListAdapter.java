package com.androidcourse.applist;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import com.androidcourse.applist.R;

public class AppListAdapter extends ArrayAdapter<AppListItem> implements Filterable {

	private AppListFilter mFilter;
	private List<AppListItem> mItems;

	public AppListAdapter(Context context, List<AppListItem> objects) {
		super(context, R.layout.list_item, objects);
		mItems = new ArrayList<AppListItem>(objects);
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rootView = convertView;
		AppListItem item = getItem(position);
		ViewHolder holder = null;

		if (rootView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rootView = inflater.inflate(R.layout.list_item, null, false);
			
			TextView tv = (TextView) rootView.findViewById(R.id.text);
			ImageView iv = (ImageView) rootView.findViewById(R.id.image);
			holder = new ViewHolder(tv, iv);
			rootView.setTag(holder);
		}
		else {
			holder = (ViewHolder) rootView.getTag();
		}

		holder.textView.setText(item.getAppName());
		holder.imageView.setImageDrawable(item.getIcon());

		return rootView;
	}

	@Override
	public Filter getFilter() {
		if (mFilter == null) {
			mFilter = new AppListFilter(this);
		}

		return mFilter;
	}

	@Override
	public List<AppListItem> getItems() {
		return mItems;
	}

	@Override
	public void setFilteredItems(List<AppListItem> filteredItems) {
		this.clear();
		this.addAll(filteredItems);
	}
	
	private class ViewHolder {
		public TextView textView;
		public ImageView imageView;
		
		public ViewHolder(TextView tv, ImageView iv) {
			this.textView = tv;
			this.imageView = iv;
		}
	}
}
