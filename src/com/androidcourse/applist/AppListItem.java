package com.androidcourse.applist;

import android.graphics.drawable.Drawable;

public class AppListItem {
	
	private String mAppName;
	private String mPackageName;
	private Drawable mIcon;
	
	public AppListItem(String appName, String packageName, Drawable icon) {
		this.mAppName = appName;
		this.mPackageName = packageName;
		this.mIcon = icon;
	}
	
	public String getAppName() {
		return this.mAppName;
	}
	
	public void setAppName(String appName){
		this.mAppName = appName;
	}
	
	public String getPackageName() {
		return this.mPackageName;
	}
	
	public void setPackageName(String packageName){
		this.mPackageName = packageName;
	}
	
	public void setIcon(Drawable icon) {
		this.mIcon = icon;
	}
	
	public Drawable getIcon() {
		return this.mIcon;
	}
}
