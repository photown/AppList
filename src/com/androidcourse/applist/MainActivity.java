package com.androidcourse.applist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import com.androidcourse.applist.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		PackageManager pm = getPackageManager();
		List<ResolveInfo> pkgAppsList = pm.queryIntentActivities( mainIntent, 0);
		List<AppListItem> apps = new ArrayList<AppListItem>();
		
		for(ResolveInfo res : pkgAppsList) {
			ActivityInfo activityInfo = res.activityInfo;
			String appName = activityInfo.applicationInfo.loadLabel(pm).toString();
			String packageName = activityInfo.packageName;
			Drawable icon = res.loadIcon(pm);
			apps.add(new AppListItem(appName, packageName, icon));
		}
		
		ListView list = (ListView) findViewById(R.id.list);
		final AppListAdapter adapter = new AppListAdapter(this, apps);
		list.setAdapter(adapter);
		
		EditText header = (EditText) getLayoutInflater().inflate(R.layout.list_header, null, false);
		list.addHeaderView(header);
		
		header.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				adapter.getFilter().filter(s);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) { }
			
			@Override
			public void afterTextChanged(Editable s) { }
		});
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AppListItem item = (AppListItem) parent.getItemAtPosition(position);
				Intent intent = getPackageManager().getLaunchIntentForPackage(item.getPackageName());
				startActivity(intent);
			}
		});
	}
}
