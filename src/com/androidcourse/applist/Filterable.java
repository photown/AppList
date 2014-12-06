package com.androidcourse.applist;

import java.util.List;

public interface Filterable {
	public List<AppListItem> getItems();
	public void setFilteredItems(List<AppListItem> filteredItems);
}
