package com.zjxd.vehichectr.activity.main.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.RelativeLayout;

import com.zjxd.vehichectr.R;
import com.zjxd.vehichectr.activity.main.control.IControl;


public class ContentArea {
	public static final String TAG = "ContentArea ";
	private static final int MESSAGE_INIT_SUB_CONTENT = 0;
	private final Context mContext;
	private IControl mIControl;
	private RelativeLayout mMainParentLayout;
	private RelativeLayout mSubParentLayout;
	private ContentMain mContentMain;
	private ContentSub mContentSub;
	
	public ContentArea(Context context, IControl control){
		mContext = context;
		mIControl = control;
		
		initMainLayout();
		//initSubLayout();
		mHandler.sendEmptyMessageDelayed(MESSAGE_INIT_SUB_CONTENT, 3000);
	}
	
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (mContentMain != null){
    		mContentMain.onActivityResult(requestCode, resultCode, data);
    	}
    }
    
	private void initMainLayout(){
		if (mMainParentLayout == null){
			mMainParentLayout = (RelativeLayout) ((Activity)mContext).findViewById(R.id.main_root);
			if (mMainParentLayout != null){
				mContentMain = new ContentMain(mContext, mIControl, mMainParentLayout);
			}
		}
	}
	
	private void initSubLayout(){
		if (mSubParentLayout == null){
			mSubParentLayout = (RelativeLayout) ((Activity)mContext).findViewById(R.id.sub_root);
			if (mSubParentLayout != null){
				mContentSub = new ContentSub(mContext, mIControl, mSubParentLayout);
			}
		}
	}
	
	public void updateMainStatus(int event, Object obj){
		initMainLayout();
		if (mContentMain != null){
			mContentMain.updateStatus(event, obj);
		}
	}
	
	public void showSubLayout(boolean visible){
		initSubLayout();
		if (mContentSub != null){
			mContentSub.setVisible(visible);
		}
	}
	
	public void updateSubStatus(int event, Object obj){
		initSubLayout();
		if (mContentSub != null){
			mContentSub.updateStatus(event, obj);
		}
	}
	
	public boolean isContentSubVisible(){
		if (mContentSub == null || !mContentSub.isVisible()){
			return false;
		}
		return true;
	}
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MESSAGE_INIT_SUB_CONTENT:
				initSubLayout();
				break;
			default:
				break;
			}
		};
	};
}