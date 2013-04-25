package com.zjxd.vehichectr.activity.main.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjxd.vehichectr.LogUtils;
import com.zjxd.vehichectr.MainApplication;
import com.zjxd.vehichectr.R;
import com.zjxd.vehichectr.activity.main.AppChoiceActivity;
import com.zjxd.vehichectr.activity.main.HomeAppInfo;
import com.zjxd.vehichectr.activity.utils.SharedPreferenceUtils;


public class AppGroupManager implements OnItemClickListener, OnItemLongClickListener{
	public static final String TAG = "AppSelectManager ";
	public static final String EXTRA_LOAD_TYPE = "LOAD_TYPE";
	public static final String LOAD_RADIO_APP_LIST = "RADIO_APP_LIST";
	public static final String LOAD_TV_APP_LIST = "TV_APP_LIST";
	public static final String LOAD_PHONE_APP_LIST = "PHONE_APP_LIST";
	public static final String LOAD_PLAYER_APP_LIST = "PLAYER__APP_LIST";
	public static final String LOAD_MAP_APP_LIST = "MAP_APP_LIST";
	public static final String LOAD_CLIMATE_APP_LIST = "CLIMATE_APP_LIST";
	public static final String LOAD_OTHER_APP_LIST = "OTHER_APP_LIST";
	
	private Context mContext;
	private GridView mAppGridView;
	private AppGridAdapter mAppAdapter;
	
	private List<HomeAppInfo> mAppData = new ArrayList<HomeAppInfo>();
	private String mLoadAppType = null;
	private String app_list = null;
	
	public AppGroupManager(Context context, GridView gridView){
		mContext = context;
		mAppGridView = gridView;
		
		init();
	}
	
    private void init(){
    	mAppGridView.setOnItemClickListener(this);
    	mAppGridView.setOnItemLongClickListener(this);
    	
    	mAppAdapter = new AppGridAdapter(mContext, null);
    	mAppGridView.setAdapter(mAppAdapter);
    }
    
    public void show(String appType){
    	mLoadAppType = appType;
    	mAppData.clear();
    	app_list = SharedPreferenceUtils.getAppList(mContext, mLoadAppType);
    	if (app_list != null){
	    	String[] list = app_list.split("##");
	    	for (int i=0, size=list.length; i<size; i++){
	    		String[] detail = list[i].split("/");
	    		if (detail != null && detail.length == 2){
		    		HomeAppInfo info = new HomeAppInfo();
		    		info.packageName = detail[0];
		    		info.className = detail[1];
		    		mAppData.add(info);
	    		}
	    	}
    	}
    	
    	showAppList(mAppData);
    }
    
    private void showAppList(List<HomeAppInfo> data){
    	if (mAppAdapter != null){
    		mAppAdapter.update(data);
    		mAppAdapter.notifyDataSetChanged();
    	}
    }
    
    private void startAppChoice(){
    	Intent intent = new Intent(mContext, AppChoiceActivity.class);
    	((Activity) mContext).startActivityForResult(intent, 1000);
    }
    

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (data == null || resultCode != Activity.RESULT_OK){
    		return;
    	}
    	
    	if (requestCode == 1000){
    		HomeAppInfo info = (HomeAppInfo) data.getSerializableExtra(AppChoiceActivity.EXTRA_CHOICE_RESULT);
    		mAppData.add(info);
    		if (app_list == null){
    			app_list = info.packageName + "/" + info.className + "##";
    		}else{
    			app_list = app_list + info.packageName + "/" + info.className + "##";
    		}
    		SharedPreferenceUtils.savaAppList(mContext, mLoadAppType, app_list);
    		showAppList(mAppData);
    	}
    }
    
    
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (position == mAppAdapter.getCount()-1){
			startAppChoice();
		}else{
			HomeAppInfo info = mAppAdapter.getItem(position);
			String pkg = info.packageName;
			String cls = info.className;
			
			try{
				ComponentName mComponentName = new ComponentName(pkg, cls);
				Intent intent = new Intent();
				intent.setComponent(mComponentName);
				mContext.startActivity(intent);
			}catch(Exception e){
				MainApplication.makeToast("��Ӧ�ò�����");
			}
		}
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		if (position != mAppAdapter.getCount()-1){
			showDeleteDialog(position);
			return true;
		}
		
		return false;
	}
	
	
	private void showDeleteDialog(final int position){
		new AlertDialog.Builder(mContext)
		.setTitle("��ʾ").setMessage("�Ƿ�ɾ���ÿ�ݷ�ʽ��")
		.setNegativeButton("ȡ��", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		})
		.setPositiveButton("ȷ��", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				doDeleteApp(position);
			}
		}).create().show();
	}
	
	private void doDeleteApp(int position){
		try{
			HomeAppInfo info  = mAppData.remove(position);
			showAppList(mAppData);
			app_list = app_list.replace(info.packageName + "/" + info.className + "##", "");
			SharedPreferenceUtils.savaAppList(mContext, mLoadAppType, app_list);
		}catch(Exception e){
			MainApplication.makeToast("ɾ����ݷ�ʽ����");
		}
	}

	private class AppGridAdapter extends BaseAdapter {
		private Context mContext ;
		private List<HomeAppInfo> mData ;
		private PackageManager mPackageManager;
		AppGridAdapter(Context context , List<HomeAppInfo> data) {
			mContext = context ;
			mData = data;
			mPackageManager = context.getPackageManager();
		}
		
		public void update(List<HomeAppInfo> data){
			mData = data;
		}
		
		@Override
		public int getCount() {
			int size = 0;
			if (mData != null){
				size = mData.size();
			}
			size = size + 1;
			return size;
		}

		@Override
		public HomeAppInfo getItem(int position) {
			if (mData != null){
				return mData.get(position);
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null){
				convertView = LayoutInflater.from(mContext).inflate(R.layout.app_gridview_item, null);
				holder = new ViewHolder();
				holder.icon = (ImageView)convertView.findViewById(R.id.app_icon);
				holder.title = (TextView)convertView.findViewById(R.id.app_title);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			if (position == getCount()-1){
				holder.icon.setImageResource(R.drawable.main_icon_add);
				holder.title.setText("������");
			}else{
				HomeAppInfo res = getItem(position);
				if (res != null){
					PackageInfo info = null;
					try {
						LogUtils.LOGD("123", "res.packageName = " + res.packageName);
						LogUtils.LOGD("123", "res.className = " + res.className);
						info = mContext.getPackageManager().getPackageInfo(res.packageName, 0);
						holder.icon.setImageDrawable(info.applicationInfo.loadIcon(mPackageManager));
						holder.title.setText(info.applicationInfo.loadLabel(mPackageManager));
					} catch (NameNotFoundException e1) {
						e1.printStackTrace();
						LogUtils.LOGD("123", "res.NameNotFoundException");
					}
				}
			}
			return convertView;
		}
		
		private class ViewHolder{
			public ImageView icon;
			public TextView title;
		}
	}
}