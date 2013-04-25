package com.zjxd.vehichectr.activity.main.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zjxd.vehichectr.R;
import com.zjxd.vehichectr.activity.main.ui.view.PercentImageView.Direction;
import com.zjxd.vehichectr.activity.utils.ActivityUtils;

public class WindLevelView extends RelativeLayout{
	private int mWidth  = 0;
	private int mHeight = 0;	
	private float mPercent = 0.0f;
	private Context mContext = null;
	private PercentImageView mWindLevel = null;
	private float mTouchDownY = 0;
	
	public WindLevelView(Context context) {
		super(context);
		onCreate(context);
	}

	public WindLevelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		onCreate(context);
	}

	public WindLevelView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		onCreate(context);
	}
		
	private void onCreate(Context context) {
		if (mContext != null)
			return;
		mContext = context;
		mWidth = ActivityUtils.scaleX(getLayoutWidth());
		mHeight = ActivityUtils.scaleY(getLayoutHeight());	

		PercentImageView inter = new PercentImageView(mContext);
		inter.setDirection(Direction.CUT_RIGHT); 
		inter.setSize((int)(mWidth), (int)(mHeight));  
        inter.setResourceId(R.drawable.main_wind_progress_bakcground);
        inter.setLayoutParams(new LayoutParams(mWidth, mHeight));
        inter.setPercent(0.0f);
		addView(inter);
		
		mWindLevel = new PercentImageView(mContext);  
		mWindLevel.setDirection(Direction.CUT_RIGHT);      
		mWindLevel.setSize((int)(mWidth), (int)(mHeight));   
		mWindLevel.setResourceId(R.drawable.main_wind_progress_drawable);
		mWindLevel.setLayoutParams(new LayoutParams(mWidth, mHeight));
        
		setPercent(0.5f);
        addView(mWindLevel); 		
	}
	
	public float getPercent() {
		return mPercent;
	}
	
	public void setPercent(float f) {
		mPercent = (f >= 0.0f && f < 1.0f)? f : (f < 0.0f)? 0.0f : 1.0f;			
		mWindLevel.setPercent(mPercent);
	}
	
	static public int getLayoutWidth() {
		return ((int)315);
	}
	
	static public int getLayoutHeight() {
		return ((int)105);
	}
}
