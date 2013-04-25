package com.zjxd.vehichectr.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.zjxd.vehichectr.R;
import com.zjxd.vehichectr.activity.main.control.MainControl;
import com.zjxd.vehichectr.activity.utils.ActivityUtils;

public class MainActivity extends Activity {
	private MainControl mMainControl;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initScreenSize();
        setContentView(R.layout.activity_main);
        
        mMainControl = new MainControl(this);
    }
    
    private void initScreenSize() {
    	/** Get screen width and height */
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
			
		int w = display.getWidth() < display.getHeight()? display.getHeight() :  display.getWidth();
		int h = display.getWidth() < display.getHeight()? display.getWidth() :  display.getHeight();
    
		/** Get Android DPI from system*/
		DisplayMetrics dm = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(dm);
		ActivityUtils.scaleInit(w, h, dm.densityDpi, 1280, 800, 240);
    }
    
    @Override
    public void onBackPressed() {
       	if (mMainControl != null){
    		mMainControl.onBackPressed();
    	}
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (mMainControl != null){
    		mMainControl.onActivityResult(requestCode, resultCode, data);
    	}
    }
}