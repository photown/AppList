package com.androidcourse.applist;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.widget.Filter;

public class AppListFilter extends Filter {

	private Filterable mFilterable;
	
	public AppListFilter(Filterable filterable) {
		this.mFilterable = filterable;
	}
	
	@Override
	protected FilterResults performFiltering(CharSequence constraint) {
		FilterResults results = new FilterResults();
		String query = constraint.toString().trim().toLowerCase(Locale.ENGLISH);
		List<AppListItem> items = mFilterable.getItems();
		
		if(query.length() > 1) {
			List<AppListItem> filtered = new ArrayList<AppListItem>();
			
			for(AppListItem item : items) {
				if(item.getAppName().toLowerCase(Locale.ENGLISH).contains(query)) {
					filtered.add(item);
				}
			}				
			
			results.count = filtered.size();
			results.values = filtered;
		}
		else {
			results.count = items.size();
			results.values = items;
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void publishResults(CharSequence constraint, FilterResults results) {
		mFilterable.setFilteredItems((List<AppListItem>) results.values);
	}
}
