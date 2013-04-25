package com.zjxd.vehichectr.activity.main.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zjxd.vehichectr.LogUtils;
import com.zjxd.vehichectr.MainApplication;
import com.zjxd.vehichectr.R;
import com.zjxd.vehichectr.activity.main.control.CarStatusHelper;
import com.zjxd.vehichectr.activity.main.control.IControl;
import com.zjxd.vehichectr.activity.main.ui.view.BehindDoorSash;
import com.zjxd.vehichectr.activity.main.ui.view.FrontDoorSash;
import com.zjxd.vehichectr.activity.utils.ActivityUtils;
import com.zjxd.vehichectr.activity.utils.GestureUtils;


public class ContentSub implements View.OnClickListener{
	public static final String TAG = "ContentArea ";
	private final Context mContext;
	private IControl mIControl;
	private RelativeLayout mParentLayout;
	private float BOTTOM_TEXT_SIZE = 0;
	
	private ImageView mTopArea;
	private RelativeLayout mCenterArea;
	private ImageView mFrontDoor;
	private ImageView mBehindDoor;
	private ImageView mFrontRightDoor;
	private ImageView mFrontLeftDoor;
	private ImageView mBehindRightDoor;
	private ImageView mBehindLeftDoor;
	private ImageView mRightFarLightLamp;
	private ImageView mLeftFarLightLamp;
	private ImageView mRightNearLightLamp;
	private ImageView mLeftNearLightLamp;
	
	private ImageView mFrontRightFlogLamp;	
	private ImageView mFrontLeftFlogLamp;	
	private ImageView mBehindRightFlogLamp;	
	private ImageView mBehindLeftFlogLamp;
	private ImageView mFrontRightRotateLamp;	
	private ImageView mFrontLeftRotateLamp;
	private ImageView mBehindRightRotateLamp;
	private ImageView mBehindLeftRotateLamp;
	
	private RelativeLayout mRainWiperLayout;
	private ImageView mRainWiper1;
	private ImageView mRainWiper2;
	private ImageView mRainWiper3;
	private ImageView mRainWiper4;
	private TextView mRainWiperText1;
	private TextView mRainWiperText2;
	private TextView mRainWiperText3;
	private TextView mRainWiperText4;
	
	private RelativeLayout mDoorSashLayout;
	private FrontDoorSash mFRightDoorSash;
	private FrontDoorSash mFLeftDoorSash;
	private BehindDoorSash mBRightDoorSash;
	private BehindDoorSash mBLeftDoorSash;
	
	private RelativeLayout mBottomArea;
	private ImageView mBottomDock1;
	private ImageView mBottomDock2;
	private ImageView mBottomDock3;
	private ImageView mBottomDock4;
	private ImageView mBottomDock5;
	private ImageView mBottomDock6;
	private ImageView mBottomDock7;
	private ImageView mBottomDock8;
	private ImageView mBottomDock9;

	private ImageView mAlertRotateLeft;
	private ImageView mAlertRotateRight;
	public ContentSub(Context context, IControl control, RelativeLayout parentLayout){
		mContext = context;
		mIControl = control;
		mParentLayout = parentLayout;
		BOTTOM_TEXT_SIZE  = mContext.getResources().getDimension(R.dimen.main_text_small);
		initLayout();
		initListener();
		initTouchArea();
		initStatus();
		setVisible(false);
	}
	
	private void initLayout(){
		if (mParentLayout != null){
			initTopArea();
			initBottomArea();
			initCenterArea();
			initRainWiper();
			initCarSash();
			initAlertRotate();
		}
	}
	
	private void initListener(){
		mBottomDock1.setOnClickListener(this);
		mBottomDock2.setOnClickListener(this);
		mBottomDock3.setOnClickListener(this);
		mBottomDock4.setOnClickListener(this);
		mBottomDock5.setOnClickListener(this);
		mBottomDock6.setOnClickListener(this);
		mBottomDock7.setOnClickListener(this);
		mBottomDock8.setOnClickListener(this);
		mBottomDock9.setOnClickListener(this);
		mRainWiper1.setOnClickListener(this);
		mRainWiper2.setOnClickListener(this);
		mRainWiper3.setOnClickListener(this);
		mRainWiper4.setOnClickListener(this);
	}
	
	
	private float mTouchDownX;
	private float mTouchDownY;
	private boolean mTouchMove = false;
	private boolean mTouchTwoPointer = false;
	private int mDirection;
	private void initTouchArea(){
		mFrontDoor.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction() & MotionEvent.ACTION_MASK;
				//VehicleApp.makeToast("onTouch " + action);
				if (mCenterArea.getVisibility() != View.VISIBLE){
					return false;
				}
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					mTouchDownX = event.getX();
					mTouchDownY = event.getY();
					//VehicleApp.makeToast("onTouch 1_DOWN");
					mTouchTwoPointer = false;
					break;
				case MotionEvent.ACTION_POINTER_DOWN:
					mTouchTwoPointer = true;
					//VehicleApp.makeToast("onTouch 2_DOWN");
					break;
				case MotionEvent.ACTION_MOVE:
					mDirection = GestureUtils.getDirection((int)event.getX(), (int)event.getY(), 
							(int)mTouchDownX, (int)mTouchDownY);
					//VehicleApp.makeToast("onTouch direction = " + direction);
					break;	
				//case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
					//VehicleApp.makeToast("onTouch 1_UP");
					LogUtils.LOGD(TAG, "onTouch mDirection  = " + mDirection);
					if (!mTouchTwoPointer){
						if (mDirection == GestureUtils.DIRECTION_LEFT){
							float distanceX = Math.abs(event.getX()- mTouchDownX);
							float distanceY = Math.abs(event.getY()- mTouchDownY);
							if (distanceX > ActivityUtils.scaleX(120) 
								|| distanceY > ActivityUtils.scaleY(120)){
								CarStatusHelper.getInstance().setLampFarlightStatus(true);
								if (mIControl != null){
									mIControl.actionSubContentEvent(IControl.EVENT_FAR_LIGHT_LAMP_CHANGE, null);
									mIControl.updateSubContentStatus(IControl.EVENT_FAR_LIGHT_LAMP_CHANGE, null);
								}
							}else{
								CarStatusHelper.getInstance().setLampNearlightStatus(true);
								if (mIControl != null){
									mIControl.actionSubContentEvent(IControl.EVENT_NEAR_LIGHT_LAMP_CHANGE, null);
									mIControl.updateSubContentStatus(IControl.EVENT_NEAR_LIGHT_LAMP_CHANGE, null);
								}
							}
						}
						
						if (mDirection == GestureUtils.DIRECTION_RIGHT){
							float distanceX = Math.abs(event.getX()- mTouchDownX);
							float distanceY = Math.abs(event.getY()- mTouchDownY);
							if (distanceX > ActivityUtils.scaleX(120) 
									|| distanceY > ActivityUtils.scaleY(120)){
								if (CarStatusHelper.getInstance().getLampFarlightStatus()){
									CarStatusHelper.getInstance().setLampFarlightStatus(false);
									if (mIControl != null){
										mIControl.actionSubContentEvent(IControl.EVENT_FAR_LIGHT_LAMP_CHANGE, null);
										mIControl.updateSubContentStatus(IControl.EVENT_FAR_LIGHT_LAMP_CHANGE, null);
									}
								}
							}else{
								if (CarStatusHelper.getInstance().getLampNearlightStatus()){
									CarStatusHelper.getInstance().setLampNearlightStatus(false);
									if (mIControl != null){
										mIControl.actionSubContentEvent(IControl.EVENT_NEAR_LIGHT_LAMP_CHANGE, null);
										mIControl.updateSubContentStatus(IControl.EVENT_NEAR_LIGHT_LAMP_CHANGE, null);
									}
								}
							}
						}
						
	
						if (mDirection == GestureUtils.DIRECTION_UP
							|| mDirection == GestureUtils.DIRECTION_UP_LEFT){
							if (!CarStatusHelper.getInstance().getCarLampRotateLeftStatus()){
								CarStatusHelper.getInstance().setCarLampRotateRightStatus(true);
								if (mIControl != null){
									mIControl.actionSubContentEvent(IControl.EVENT_RIGHT_ROTATE_LAMP_CHANGE, null);
									mIControl.updateSubContentStatus(IControl.EVENT_RIGHT_ROTATE_LAMP_CHANGE, null);
								}
							}
						}
						
						if (mDirection == GestureUtils.DIRECTION_DOWN_RIGHT){
							if (CarStatusHelper.getInstance().getCarLampRotateRightStatus()){
								CarStatusHelper.getInstance().setCarLampRotateRightStatus(false);
								if (mIControl != null){
									mIControl.actionSubContentEvent(IControl.EVENT_RIGHT_ROTATE_LAMP_CHANGE, null);
									mIControl.updateSubContentStatus(IControl.EVENT_RIGHT_ROTATE_LAMP_CHANGE, null);
								}
							}
						}
						
						if (mDirection == GestureUtils.DIRECTION_UP_RIGHT){
							if (CarStatusHelper.getInstance().getCarLampRotateLeftStatus()){
								CarStatusHelper.getInstance().setCarLampRotateLeftStatus(false);
								if (mIControl != null){
									mIControl.actionSubContentEvent(IControl.EVENT_LEFT_ROTATE_LAMP_CHANGE, null);
									mIControl.updateSubContentStatus(IControl.EVENT_LEFT_ROTATE_LAMP_CHANGE, null);
								}
							}	
						}
						
						if (mDirection == GestureUtils.DIRECTION_DOWN
							|| mDirection == GestureUtils.DIRECTION_DOWN_LEFT){
							if (!CarStatusHelper.getInstance().getCarLampRotateRightStatus()){
								CarStatusHelper.getInstance().setCarLampRotateLeftStatus(true);
								if (mIControl != null){
									mIControl.actionSubContentEvent(IControl.EVENT_LEFT_ROTATE_LAMP_CHANGE, null);
									mIControl.updateSubContentStatus(IControl.EVENT_LEFT_ROTATE_LAMP_CHANGE, null);
								}
							}
						}
					}
					break;
				case MotionEvent.ACTION_POINTER_UP:
					//VehicleApp.makeToast("onTouch 2_UP");
					if (mDirection == GestureUtils.DIRECTION_LEFT){
						CarStatusHelper.getInstance().setLampFrontflogStatus(true);
						if (mIControl != null){
							mIControl.actionSubContentEvent(IControl.EVENT_FRONT_FLOG_LAMP_CHANGE, null);
							mIControl.updateSubContentStatus(IControl.EVENT_FRONT_FLOG_LAMP_CHANGE, null);
						}
					}
					
					if (mDirection == GestureUtils.DIRECTION_RIGHT){
						CarStatusHelper.getInstance().setLampFrontflogStatus(false);
						if (mIControl != null){
							mIControl.actionSubContentEvent(IControl.EVENT_FRONT_FLOG_LAMP_CHANGE, null);
							mIControl.updateSubContentStatus(IControl.EVENT_FRONT_FLOG_LAMP_CHANGE, null);
						}
					}
					break;
				default:
					break;
				}
				return true;
			}
		});
		
        mBehindDoor.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction() & MotionEvent.ACTION_MASK;
				if (mCenterArea.getVisibility() != View.VISIBLE){
					return false;
				}
				LogUtils.LOGD(TAG, "onTouch action  = " + action);
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					mTouchDownX = event.getX();
					mTouchDownY = event.getY();
					mTouchTwoPointer = false;
					break;
				case MotionEvent.ACTION_POINTER_DOWN:
					mTouchTwoPointer = true;
					break;
				case MotionEvent.ACTION_MOVE:
					mDirection = GestureUtils.getDirection((int)event.getX(), (int)event.getY(), 
							(int)mTouchDownX, (int)mTouchDownY);
					break;	
				case MotionEvent.ACTION_UP:
					//VehicleApp.makeToast("onTouch 1_UP");
					//VehicleApp.makeToast("onTouch mDirection  = " + mDirection);
					break;
				case MotionEvent.ACTION_POINTER_UP:
					LogUtils.LOGD(TAG, "onTouch mDirection  = " + mDirection);
					if (mDirection == GestureUtils.DIRECTION_RIGHT){
						CarStatusHelper.getInstance().setLampBehindflogStatus(true);
						if (mIControl != null){
							mIControl.actionSubContentEvent(IControl.EVENT_BEHIND_FLOG_LAMP_CHANGE, null);
							mIControl.updateSubContentStatus(IControl.EVENT_BEHIND_FLOG_LAMP_CHANGE, null);
						}
					}
					
					if (mDirection == GestureUtils.DIRECTION_LEFT){
						CarStatusHelper.getInstance().setLampBehindflogStatus(false);
						if (mIControl != null){
							mIControl.actionSubContentEvent(IControl.EVENT_BEHIND_FLOG_LAMP_CHANGE, null);
							mIControl.updateSubContentStatus(IControl.EVENT_BEHIND_FLOG_LAMP_CHANGE, null);
						}
					}
					break;
				default:
					break;
				}
				return true;
			}
		});
		
		mFrontRightDoor.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction() & MotionEvent.ACTION_MASK;
				if (mCenterArea.getVisibility() != View.VISIBLE){
					return false;
				}
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					mTouchDownX = event.getX();
					mTouchDownY = event.getY();
					mTouchMove = false;
				break;
				case MotionEvent.ACTION_MOVE:
					mTouchMove = true;
					mDirection = GestureUtils.getDirection((int)event.getX(), (int)event.getY(), 
							(int)mTouchDownX, (int)mTouchDownY);
					break;
				case MotionEvent.ACTION_UP:
					if (mDirection == GestureUtils.DIRECTION_UP 
						|| mDirection == GestureUtils.DIRECTION_UP_LEFT
						|| mDirection == GestureUtils.DIRECTION_UP_RIGHT){
						CarStatusHelper.getInstance().setCarFrontDoorRightStatus(true);
						mIControl.actionSubContentEvent(IControl.EVENT_FRONT_RIGHT_DOOR_CAHNEG, null);
						mIControl.updateSubContentStatus(IControl.EVENT_FRONT_RIGHT_DOOR_CAHNEG, null);
					}
		
					if (mDirection == GestureUtils.DIRECTION_DOWN
						|| mDirection == GestureUtils.DIRECTION_DOWN_LEFT
						|| mDirection == GestureUtils.DIRECTION_DOWN_RIGHT){
						CarStatusHelper.getInstance().setCarFrontDoorRightStatus(false);
						mIControl.actionSubContentEvent(IControl.EVENT_FRONT_RIGHT_DOOR_CAHNEG, null);
						mIControl.updateSubContentStatus(IControl.EVENT_FRONT_RIGHT_DOOR_CAHNEG, null);
					}
					break;	
				default:
					break;
				}			
				return true;
			}
        }); 
		
		mFrontLeftDoor.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction() & MotionEvent.ACTION_MASK;
				if (mCenterArea.getVisibility() != View.VISIBLE){
					return false;
				}
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					mTouchDownX = event.getX();
					mTouchDownY = event.getY();
					mTouchMove = false;
				break;
				case MotionEvent.ACTION_MOVE:
					mTouchMove = true;
					mDirection = GestureUtils.getDirection((int)event.getX(), (int)event.getY(), 
							(int)mTouchDownX, (int)mTouchDownY);
					break;
				case MotionEvent.ACTION_UP:
					if (mDirection == GestureUtils.DIRECTION_UP 
					|| mDirection == GestureUtils.DIRECTION_UP_LEFT
					|| mDirection == GestureUtils.DIRECTION_UP_RIGHT){
						CarStatusHelper.getInstance().setCarFrontDoorLeftStatus(false);
						mIControl.actionSubContentEvent(IControl.EVENT_FRONT_LEFT_DOOR_CAHNEG, null);
						mIControl.updateSubContentStatus(IControl.EVENT_FRONT_LEFT_DOOR_CAHNEG, null);
					}
		
					if (mDirection == GestureUtils.DIRECTION_DOWN
							|| mDirection == GestureUtils.DIRECTION_DOWN_LEFT
							|| mDirection == GestureUtils.DIRECTION_DOWN_RIGHT){
						CarStatusHelper.getInstance().setCarFrontDoorLeftStatus(true);
						mIControl.actionSubContentEvent(IControl.EVENT_FRONT_LEFT_DOOR_CAHNEG, null);
						mIControl.updateSubContentStatus(IControl.EVENT_FRONT_LEFT_DOOR_CAHNEG, null);
					}

					break;	
				default:
					break;
				}			
				return true;
			}
        });
		
		mBehindRightDoor.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction() & MotionEvent.ACTION_MASK;
				if (mCenterArea.getVisibility() != View.VISIBLE){
					return false;
				}
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					mTouchDownX = event.getX();
					mTouchDownY = event.getY();
					mTouchMove = false;
				break;
				case MotionEvent.ACTION_MOVE:
					mTouchMove = true;
					mDirection = GestureUtils.getDirection((int)event.getX(), (int)event.getY(), 
							(int)mTouchDownX, (int)mTouchDownY);
					break;
				case MotionEvent.ACTION_UP:
					if (mDirection == GestureUtils.DIRECTION_UP 
						|| mDirection == GestureUtils.DIRECTION_UP_LEFT
						|| mDirection == GestureUtils.DIRECTION_UP_RIGHT){
						CarStatusHelper.getInstance().setCarBehindDoorRightStatus(true);
						mIControl.actionSubContentEvent(IControl.EVENT_BEHIND_RIGHT_DOOR_CAHNEG, null);
						mIControl.updateSubContentStatus(IControl.EVENT_BEHIND_RIGHT_DOOR_CAHNEG, null);
					}
		
					if (mDirection == GestureUtils.DIRECTION_DOWN
						|| mDirection == GestureUtils.DIRECTION_DOWN_LEFT
						|| mDirection == GestureUtils.DIRECTION_DOWN_RIGHT){
						CarStatusHelper.getInstance().setCarBehindDoorRightStatus(false);
						mIControl.actionSubContentEvent(IControl.EVENT_BEHIND_RIGHT_DOOR_CAHNEG, null);
						mIControl.updateSubContentStatus(IControl.EVENT_BEHIND_RIGHT_DOOR_CAHNEG, null);
					}
					break;	
				default:
					break;
				}			
				return true;
			}
        }); 
		
		mBehindLeftDoor.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction() & MotionEvent.ACTION_MASK;
				if (mCenterArea.getVisibility() != View.VISIBLE){
					return false;
				}
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					mTouchDownX = event.getX();
					mTouchDownY = event.getY();
					mTouchMove = false;
				break;
				case MotionEvent.ACTION_MOVE:
					mTouchMove = true;
					mDirection = GestureUtils.getDirection((int)event.getX(), (int)event.getY(), 
							(int)mTouchDownX, (int)mTouchDownY);
					break;
				case MotionEvent.ACTION_UP:
					if (mDirection == GestureUtils.DIRECTION_UP 
					|| mDirection == GestureUtils.DIRECTION_UP_LEFT
					|| mDirection == GestureUtils.DIRECTION_UP_RIGHT){
						CarStatusHelper.getInstance().setCarBehindDoorLeftStatus(false);
						mIControl.actionSubContentEvent(IControl.EVENT_BEHIND_LEFT_DOOR_CAHNEG, null);
						mIControl.updateSubContentStatus(IControl.EVENT_BEHIND_LEFT_DOOR_CAHNEG, null);
					}
		
					if (mDirection == GestureUtils.DIRECTION_DOWN
							|| mDirection == GestureUtils.DIRECTION_DOWN_LEFT
							|| mDirection == GestureUtils.DIRECTION_DOWN_RIGHT){
						CarStatusHelper.getInstance().setCarBehindDoorLeftStatus(true);
						mIControl.actionSubContentEvent(IControl.EVENT_BEHIND_LEFT_DOOR_CAHNEG, null);
						mIControl.updateSubContentStatus(IControl.EVENT_BEHIND_LEFT_DOOR_CAHNEG, null);
					}

					break;	
				default:
					break;
				}			
				return true;
			}
        });
	}
	
	
	private void initTopArea(){
		mTopArea = new ImageView(mContext);
		mTopArea.setId(mTopArea.hashCode());
		mTopArea.setImageResource(R.drawable.sub_title_icon);
		mTopArea.setBackgroundResource(R.drawable.sub_top_background);
		LayoutParams params = ActivityUtils.getLayoutParams(1280, 93);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		mParentLayout.addView(mTopArea, params);
	}
	
	private void initBottomArea(){
		mBottomArea = new RelativeLayout(mContext);
		mBottomArea.setId(mBottomArea.hashCode());
		mBottomArea.setBackgroundResource(R.drawable.sub_bottom_background);
		LayoutParams params = ActivityUtils.getLayoutParams(1280, 106);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		mParentLayout.addView(mBottomArea, params);
		

		mBottomDock1 = new ImageView(mContext);
		mBottomDock1.setId(mBottomDock1.hashCode());
		mBottomDock1.setBackgroundResource(R.drawable.sub_icon_car_window_normal);
		params = ActivityUtils.getLayoutParams(86, 86);
		params.leftMargin = ActivityUtils.scaleX(160);
		mBottomArea.addView(mBottomDock1, params);
		
		TextView mBottonText1 = new TextView(mContext);
		mBottonText1.setGravity(Gravity.CENTER);
		mBottonText1.setText("车窗");
		mBottonText1.setId(mBottonText1.hashCode());
		mBottonText1.setTextSize(BOTTOM_TEXT_SIZE);
		params = ActivityUtils.getLayoutParams(86, 30);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.leftMargin = ActivityUtils.scaleX(160);
		mBottomArea.addView(mBottonText1, params);
		
		mBottomDock2 = new ImageView(mContext);
		mBottomDock2.setId(mBottomDock2.hashCode());
		mBottomDock2.setBackgroundResource(R.drawable.sub_icon_rain_wiper_normal);
		params = ActivityUtils.getLayoutParams(86, 86);
		params.leftMargin = ActivityUtils.scaleX(5);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock1.getId());
		mBottomArea.addView(mBottomDock2, params);
		
		TextView mBottonText2 = new TextView(mContext);
		mBottonText2.setGravity(Gravity.CENTER);
		mBottonText2.setText("雨刮");
		mBottonText2.setId(mBottonText2.hashCode());
		mBottonText2.setTextSize(BOTTOM_TEXT_SIZE);
		params = ActivityUtils.getLayoutParams(76, 30);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock1.getId());
		params.leftMargin = ActivityUtils.scaleX(5);
		mBottomArea.addView(mBottonText2, params);
		
		mBottomDock3 = new ImageView(mContext);
		mBottomDock3.setId(mBottomDock3.hashCode());
		params = ActivityUtils.getLayoutParams(86, 86);
		params.leftMargin = ActivityUtils.scaleX(80);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock2.getId());
		mBottomArea.addView(mBottomDock3, params);
		
		TextView mBottonText3 = new TextView(mContext);
		mBottonText3.setGravity(Gravity.CENTER);
		mBottonText3.setText("远光灯");
		mBottonText3.setId(mBottonText3.hashCode());
		mBottonText3.setTextSize(BOTTOM_TEXT_SIZE);
		params = ActivityUtils.getLayoutParams(76, 30);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock2.getId());
		params.leftMargin = ActivityUtils.scaleX(80);
		mBottomArea.addView(mBottonText3, params);
		
		
		mBottomDock4 = new ImageView(mContext);
		mBottomDock4.setId(mBottomDock4.hashCode());
		params = ActivityUtils.getLayoutParams(86, 86);
		params.leftMargin = ActivityUtils.scaleX(5);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock3.getId());
		mBottomArea.addView(mBottomDock4, params);
		
		TextView mBottonText4 = new TextView(mContext);
		mBottonText4.setGravity(Gravity.CENTER);
		mBottonText4.setText("近光灯");
		mBottonText4.setId(mBottonText4.hashCode());
		mBottonText4.setTextSize(BOTTOM_TEXT_SIZE);
		params = ActivityUtils.getLayoutParams(86, 30);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock3.getId());
		params.leftMargin = ActivityUtils.scaleX(5);
		mBottomArea.addView(mBottonText4, params);
		
		mBottomDock5 = new ImageView(mContext);
		mBottomDock5.setId(mBottomDock5.hashCode());
		params = ActivityUtils.getLayoutParams(86, 86);
		params.leftMargin = ActivityUtils.scaleX(5);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock4.getId());
		mBottomArea.addView(mBottomDock5, params);
		
		TextView mBottonText5 = new TextView(mContext);
		mBottonText5.setGravity(Gravity.CENTER);
		mBottonText5.setText("前雾灯");
		mBottonText5.setId(mBottonText5.hashCode());
		mBottonText5.setTextSize(BOTTOM_TEXT_SIZE);
		params = ActivityUtils.getLayoutParams(86, 30);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock4.getId());
		params.leftMargin = ActivityUtils.scaleX(5);
		mBottomArea.addView(mBottonText5, params);
		
		mBottomDock6 = new ImageView(mContext);
		mBottomDock6.setId(mBottomDock6.hashCode());
		params = ActivityUtils.getLayoutParams(86, 86);
		params.leftMargin = ActivityUtils.scaleX(5);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock5.getId());
		mBottomArea.addView(mBottomDock6, params);
		
		TextView mBottonText6 = new TextView(mContext);
		mBottonText6.setGravity(Gravity.CENTER);
		mBottonText6.setText("后雾灯");
		mBottonText6.setId(mBottonText6.hashCode());
		mBottonText6.setTextSize(BOTTOM_TEXT_SIZE);
		params = ActivityUtils.getLayoutParams(86, 30);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock5.getId());
		params.leftMargin = ActivityUtils.scaleX(5);
		mBottomArea.addView(mBottonText6, params);
		
		mBottomDock7 = new ImageView(mContext);
		mBottomDock7.setId(mBottomDock7.hashCode());
		params = ActivityUtils.getLayoutParams(86, 86);
		params.leftMargin = ActivityUtils.scaleX(5);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock6.getId());
		mBottomArea.addView(mBottomDock7, params);
		
		TextView mBottonText7 = new TextView(mContext);
		mBottonText7.setGravity(Gravity.CENTER);
		mBottonText7.setText("前舱门");
		mBottonText7.setId(mBottonText7.hashCode());
		mBottonText7.setTextSize(BOTTOM_TEXT_SIZE);
		params = ActivityUtils.getLayoutParams(86, 30);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock6.getId());
		params.leftMargin = ActivityUtils.scaleX(5);
		mBottomArea.addView(mBottonText7, params);
		
		mBottomDock8 = new ImageView(mContext);
		mBottomDock8.setId(mBottomDock8.hashCode());
		params = ActivityUtils.getLayoutParams(86, 86);
		params.leftMargin = ActivityUtils.scaleX(5);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock7.getId());
		mBottomArea.addView(mBottomDock8, params);
		
		TextView mBottonText8 = new TextView(mContext);
		mBottonText8.setGravity(Gravity.CENTER);
		mBottonText8.setText("后舱门");
		mBottonText8.setId(mBottonText8.hashCode());
		mBottonText8.setTextSize(BOTTOM_TEXT_SIZE);
		params = ActivityUtils.getLayoutParams(86, 30);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock7.getId());
		params.leftMargin = ActivityUtils.scaleX(5);
		mBottomArea.addView(mBottonText8, params);
		
		mBottomDock9 = new ImageView(mContext);
		mBottomDock9.setId(mBottomDock9.hashCode());
		mBottomDock9.setBackgroundResource(R.drawable.sub_icon_main_page_normal);
		params = ActivityUtils.getLayoutParams(86, 86);
		params.leftMargin = ActivityUtils.scaleX(80);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock8.getId());
		mBottomArea.addView(mBottomDock9, params);
		
		TextView mBottonText9 = new TextView(mContext);
		mBottonText9.setGravity(Gravity.CENTER);
		mBottonText9.setText("主页");
		mBottonText9.setId(mBottonText9.hashCode());
		mBottonText9.setTextSize(BOTTOM_TEXT_SIZE);
		params = ActivityUtils.getLayoutParams(86, 30);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomDock8.getId());
		params.leftMargin = ActivityUtils.scaleX(80);
		mBottomArea.addView(mBottonText9, params);
	}
	
	private void initCenterArea(){
		RelativeLayout centerAreaBackGround = new RelativeLayout(mContext);
		centerAreaBackGround.setId(centerAreaBackGround.hashCode());
		centerAreaBackGround.setBackgroundResource(R.drawable.sub_center_background);
		LayoutParams params = ActivityUtils.getLayoutParams(1280, 553);
		params.addRule(RelativeLayout.BELOW, mTopArea.getId());
		params.addRule(RelativeLayout.ABOVE, mBottomArea.getId());
		mParentLayout.addView(centerAreaBackGround, params);
		
		mCenterArea = new RelativeLayout(mContext);
		mCenterArea.setId(mCenterArea.hashCode());
		params = ActivityUtils.getLayoutParams(1280, 553);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		centerAreaBackGround.addView(mCenterArea, params);
		
		//init car door
		ImageView mCarBody = new ImageView(mContext);
		mCarBody.setId(mCarBody.hashCode());
		mCarBody.setBackgroundResource(R.drawable.sub_car_body);
		params = ActivityUtils.getLayoutParams(1280, 553);
		mCenterArea.addView(mCarBody, params);
		
		mFrontDoor = new ImageView(mContext);
		mFrontDoor.setId(mFrontDoor.hashCode());
		params = ActivityUtils.getLayoutParams(465, 553);
		mCenterArea.addView(mFrontDoor, params);
		
		mBehindDoor = new ImageView(mContext);
		mBehindDoor.setId(mBehindDoor.hashCode());
		params = ActivityUtils.getLayoutParams(386, 553);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		mCenterArea.addView(mBehindDoor, params);
		
		mFrontRightDoor = new ImageView(mContext);
		mFrontRightDoor.setId(mFrontRightDoor.hashCode());
		//mFrontRightDoor.setBackgroundColor(Color.RED);
		params = ActivityUtils.getLayoutParams(241, 282);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.RIGHT_OF, mFrontDoor.getId());
		mCenterArea.addView(mFrontRightDoor, params);
		
		mFrontLeftDoor = new ImageView(mContext);
		mFrontLeftDoor.setId(mFrontLeftDoor.hashCode());
		params = ActivityUtils.getLayoutParams(245, 270);
		params.addRule(RelativeLayout.RIGHT_OF, mFrontDoor.getId());
		params.addRule(RelativeLayout.BELOW, mFrontRightDoor.getId());
		mCenterArea.addView(mFrontLeftDoor, params);
		
		mBehindRightDoor = new ImageView(mContext);
		mBehindRightDoor.setId(mBehindRightDoor.hashCode());
		params = ActivityUtils.getLayoutParams(234, 280);
		params.topMargin = ActivityUtils.scaleY(2);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.LEFT_OF, mBehindDoor.getId());
		mCenterArea.addView(mBehindRightDoor, params);
		
		mBehindLeftDoor = new ImageView(mContext);
		mBehindLeftDoor.setId(mBehindLeftDoor.hashCode());
		params = ActivityUtils.getLayoutParams(234, 270);
		//mBehindLeftDoor.setBackgroundColor(Color.GREEN);
		params.addRule(RelativeLayout.LEFT_OF, mBehindDoor.getId());
		params.addRule(RelativeLayout.BELOW, mBehindRightDoor.getId());
		mCenterArea.addView(mBehindLeftDoor, params);
		
		
		//init car light lamp
		mRightFarLightLamp = new ImageView(mContext);
		mRightFarLightLamp.setId(mRightFarLightLamp.hashCode());
		params = ActivityUtils.getLayoutParams(464, 281);
		mCenterArea.addView(mRightFarLightLamp, params);
		
		mLeftFarLightLamp = new ImageView(mContext);
		mLeftFarLightLamp.setId(mLeftFarLightLamp.hashCode());
		params = ActivityUtils.getLayoutParams(464, 268);
		params.addRule(RelativeLayout.BELOW, mRightFarLightLamp.getId());
		mCenterArea.addView(mLeftFarLightLamp, params);
		
		mRightNearLightLamp = new ImageView(mContext);
		mRightNearLightLamp.setId(mRightNearLightLamp.hashCode());
		params = ActivityUtils.getLayoutParams(464, 281);
		mCenterArea.addView(mRightNearLightLamp, params);
		
		mLeftNearLightLamp = new ImageView(mContext);
		mLeftNearLightLamp.setId(mLeftFarLightLamp.hashCode());
		params = ActivityUtils.getLayoutParams(464, 268);
		params.leftMargin = ActivityUtils.scaleX(-10);
		params.addRule(RelativeLayout.BELOW, mRightNearLightLamp.getId());
		mCenterArea.addView(mLeftNearLightLamp, params);
		
		//init car flog lamp
		mFrontRightFlogLamp = new ImageView(mContext);
		mFrontRightFlogLamp.setId(mFrontRightFlogLamp.hashCode());
		mFrontRightFlogLamp.setBackgroundResource(R.drawable.sub_car_front_right_flog_lamp);
		params = ActivityUtils.getLayoutParams(197, 197);
		params.topMargin = ActivityUtils.scaleY(80);
		params.leftMargin = ActivityUtils.scaleX(120);
		mCenterArea.addView(mFrontRightFlogLamp, params);
		
		mFrontLeftFlogLamp = new ImageView(mContext);
		mFrontLeftFlogLamp.setId(mFrontLeftFlogLamp.hashCode());
		mFrontLeftFlogLamp.setBackgroundResource(R.drawable.sub_car_front_left_flog_lamp);
		params = ActivityUtils.getLayoutParams(197, 197);
		params.addRule(RelativeLayout.BELOW, mFrontRightFlogLamp.getId());
		params.topMargin = ActivityUtils.scaleY(80);
		params.leftMargin = ActivityUtils.scaleX(130);
		mCenterArea.addView(mFrontLeftFlogLamp, params);
		
		mBehindRightFlogLamp = new ImageView(mContext);
		mBehindRightFlogLamp.setId(mBehindRightFlogLamp.hashCode());
		mBehindRightFlogLamp.setBackgroundResource(R.drawable.sub_car_behind_right_flog_lamp);
		params = ActivityUtils.getLayoutParams(197, 197);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.topMargin = ActivityUtils.scaleY(60);
		params.rightMargin = ActivityUtils.scaleX(120);
		mCenterArea.addView(mBehindRightFlogLamp, params);
		
		mBehindLeftFlogLamp = new ImageView(mContext);
		mBehindLeftFlogLamp.setId(mBehindLeftFlogLamp.hashCode());
		mBehindLeftFlogLamp.setBackgroundResource(R.drawable.sub_car_behind_left_flog_lamp);
		params = ActivityUtils.getLayoutParams(197, 197);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.BELOW, mBehindRightFlogLamp.getId());
		params.topMargin = ActivityUtils.scaleY(100);
		params.rightMargin = ActivityUtils.scaleX(120);
		mCenterArea.addView(mBehindLeftFlogLamp, params);
		
		//init rotate lamp
		mFrontRightRotateLamp = new ImageView(mContext);
		mFrontRightRotateLamp.setId(mFrontRightFlogLamp.hashCode());
		params = ActivityUtils.getLayoutParams(108, 108);
		params.topMargin = ActivityUtils.scaleY(110);
		params.leftMargin = ActivityUtils.scaleX(180);
		mCenterArea.addView(mFrontRightRotateLamp, params);
		
		mFrontLeftRotateLamp = new ImageView(mContext);
		mFrontLeftRotateLamp.setId(mFrontLeftRotateLamp.hashCode());
		params = ActivityUtils.getLayoutParams(108, 108);
		params.addRule(RelativeLayout.BELOW, mFrontRightRotateLamp.getId());
		params.topMargin = ActivityUtils.scaleY(120);
		params.leftMargin = ActivityUtils.scaleX(180);
		mCenterArea.addView(mFrontLeftRotateLamp, params);
		
		mBehindRightRotateLamp = new ImageView(mContext);
		mBehindRightRotateLamp.setId(mBehindRightFlogLamp.hashCode());
		params = ActivityUtils.getLayoutParams(108, 108);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.topMargin = ActivityUtils.scaleY(100);
		params.rightMargin = ActivityUtils.scaleX(160);
		mCenterArea.addView(mBehindRightRotateLamp, params);
		
		mBehindLeftRotateLamp = new ImageView(mContext);
		mBehindLeftRotateLamp.setId(mBehindLeftRotateLamp.hashCode());
		params = ActivityUtils.getLayoutParams(108, 108);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.BELOW, mBehindRightRotateLamp.getId());
		params.topMargin = ActivityUtils.scaleY(140);
		params.rightMargin = ActivityUtils.scaleX(160);
		mCenterArea.addView(mBehindLeftRotateLamp, params);
		
	}
	
	private void initRainWiper(){
		mRainWiperLayout = new RelativeLayout(mContext);
		mRainWiperLayout.setId(mRainWiperLayout.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(120, 553);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.BELOW, mTopArea.getId());
		params.addRule(RelativeLayout.ABOVE, mBottomArea.getId());
		mParentLayout.addView(mRainWiperLayout, params);
		
		//======================
		mRainWiperText1  = new TextView(mContext);
		mRainWiperText1.setId(mRainWiperText1.hashCode());
		mRainWiperText1.setGravity(Gravity.CENTER);
		mRainWiperText1.setTextSize(BOTTOM_TEXT_SIZE);
		mRainWiperText1.setText("雨刮关");
		params = ActivityUtils.getLayoutParams(100, 30);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.topMargin = ActivityUtils.scaleY(140);
		params.rightMargin =  ActivityUtils.scaleX(20);
		mRainWiperLayout.addView(mRainWiperText1, params);
		
		mRainWiperText2  = new TextView(mContext);
		mRainWiperText2.setId(mRainWiperText2.hashCode());
		mRainWiperText2.setGravity(Gravity.CENTER);
		mRainWiperText2.setTextSize(BOTTOM_TEXT_SIZE);
		mRainWiperText2.setText("雨刮慢档");
		params = ActivityUtils.getLayoutParams(100, 30);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.topMargin = ActivityUtils.scaleY(240);
		params.rightMargin =  ActivityUtils.scaleX(20);
		mRainWiperLayout.addView(mRainWiperText2, params);
		
		mRainWiperText3  = new TextView(mContext);
		mRainWiperText3.setId(mRainWiperText3.hashCode());
		mRainWiperText3.setGravity(Gravity.CENTER);
		mRainWiperText3.setTextSize(BOTTOM_TEXT_SIZE);
		mRainWiperText3.setText("雨刮中档");
		params = ActivityUtils.getLayoutParams(100, 30);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.topMargin = ActivityUtils.scaleY(340);
		params.rightMargin =  ActivityUtils.scaleX(20);
		mRainWiperLayout.addView(mRainWiperText3, params);
		
		mRainWiperText4  = new TextView(mContext);
		mRainWiperText4.setId(mRainWiperText4.hashCode());
		mRainWiperText4.setGravity(Gravity.CENTER);
		mRainWiperText4.setTextSize(BOTTOM_TEXT_SIZE);
		mRainWiperText4.setText("雨刮快档");
		params = ActivityUtils.getLayoutParams(100, 30);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.topMargin = ActivityUtils.scaleY(440);
		params.rightMargin =  ActivityUtils.scaleX(20);
		mRainWiperLayout.addView(mRainWiperText4, params);
		
		mRainWiper1 = new ImageView(mContext);
		mRainWiper1.setId(mRainWiper1.hashCode());
		mRainWiper1.setBackgroundResource(R.drawable.sub_rain_wiper_background);
		params = ActivityUtils.getLayoutParams(56, 56);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.topMargin = ActivityUtils.scaleY(90);
		params.rightMargin =  ActivityUtils.scaleX(40);
		mRainWiperLayout.addView(mRainWiper1, params);
		
		mRainWiper2 = new ImageView(mContext);
		mRainWiper2.setId(mRainWiper2.hashCode());
		mRainWiper2.setBackgroundResource(R.drawable.sub_rain_wiper_background);
		params = ActivityUtils.getLayoutParams(56, 56);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.topMargin = ActivityUtils.scaleY(190);
		params.rightMargin =  ActivityUtils.scaleX(40);
		mRainWiperLayout.addView(mRainWiper2, params);
		
		mRainWiper3 = new ImageView(mContext);
		mRainWiper3.setId(mRainWiper3.hashCode());
		mRainWiper3.setBackgroundResource(R.drawable.sub_rain_wiper_background);
		params = ActivityUtils.getLayoutParams(56, 56);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.topMargin = ActivityUtils.scaleY(290);
		params.rightMargin =  ActivityUtils.scaleX(40);
		mRainWiperLayout.addView(mRainWiper3, params);
		
		mRainWiper4 = new ImageView(mContext);
		mRainWiper4.setId(mRainWiper4.hashCode());
		mRainWiper4.setBackgroundResource(R.drawable.sub_rain_wiper_background);
		params = ActivityUtils.getLayoutParams(56, 56);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.topMargin = ActivityUtils.scaleY(390);
		params.rightMargin =  ActivityUtils.scaleX(40);
		mRainWiperLayout.addView(mRainWiper4, params);
	}

	private void initCarSash(){
		mDoorSashLayout = new RelativeLayout(mContext);
		mDoorSashLayout.setId(mDoorSashLayout.hashCode());
		mDoorSashLayout.setBackgroundResource(R.drawable.sub_car_window_background);
		LayoutParams params = ActivityUtils.getLayoutParams(1088, 553);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, mTopArea.getId());
		params.addRule(RelativeLayout.ABOVE, mBottomArea.getId());
		mParentLayout.addView(mDoorSashLayout, params);
		
		
		TextView mFRightDoorSashText  = new TextView(mContext);
		mFRightDoorSashText.setId(mFRightDoorSashText.hashCode());
		mFRightDoorSashText.setGravity(Gravity.CENTER);
		mFRightDoorSashText.setTextSize(BOTTOM_TEXT_SIZE);
		mFRightDoorSashText.setText("右前车窗");
		params = ActivityUtils.getLayoutParams(100, 30);
		params.topMargin = ActivityUtils.scaleY(260);
		params.leftMargin =  ActivityUtils.scaleX(260);
		mDoorSashLayout.addView(mFRightDoorSashText, params);
		
		TextView mFLeftDoorSashText  = new TextView(mContext);
		mFLeftDoorSashText.setId(mFLeftDoorSashText.hashCode());
		mFLeftDoorSashText.setGravity(Gravity.CENTER);
		mFLeftDoorSashText.setTextSize(BOTTOM_TEXT_SIZE);
		mFLeftDoorSashText.setText("左前车窗");
		params = ActivityUtils.getLayoutParams(100, 30);
		params.topMargin = ActivityUtils.scaleY(500);
		params.leftMargin =  ActivityUtils.scaleX(260);
		mDoorSashLayout.addView(mFLeftDoorSashText, params);
		
		TextView mBRightDoorSashText  = new TextView(mContext);
		mBRightDoorSashText.setId(mBRightDoorSashText.hashCode());
		mBRightDoorSashText.setGravity(Gravity.CENTER);
		mBRightDoorSashText.setTextSize(BOTTOM_TEXT_SIZE);
		mBRightDoorSashText.setText("右后车窗");
		params = ActivityUtils.getLayoutParams(100, 30);
		params.topMargin = ActivityUtils.scaleY(260);
		params.leftMargin =  ActivityUtils.scaleX(710);
		mDoorSashLayout.addView(mBRightDoorSashText, params);
		
		TextView mBLeftDoorSashText  = new TextView(mContext);
		mBLeftDoorSashText.setId(mBRightDoorSashText.hashCode());
		mBLeftDoorSashText.setGravity(Gravity.CENTER);
		mBLeftDoorSashText.setTextSize(BOTTOM_TEXT_SIZE);
		mBLeftDoorSashText.setText("左后车窗");
		params = ActivityUtils.getLayoutParams(100, 30);
		params.topMargin = ActivityUtils.scaleY(500);
		params.leftMargin =  ActivityUtils.scaleX(710);
		mDoorSashLayout.addView(mBLeftDoorSashText, params);
		
		mFRightDoorSash = new FrontDoorSash(mContext);
		params = ActivityUtils.getLayoutParams(FrontDoorSash.getLayoutWidth(), 
				FrontDoorSash.getLayoutHeight());
		params.leftMargin = ActivityUtils.scaleX(100);
		params.topMargin =  ActivityUtils.scaleY(80);
		mDoorSashLayout.addView(mFRightDoorSash, params);
		
		mFLeftDoorSash = new FrontDoorSash(mContext);
		params = ActivityUtils.getLayoutParams(FrontDoorSash.getLayoutWidth(), 
				FrontDoorSash.getLayoutHeight());
		params.leftMargin = ActivityUtils.scaleX(100);
		params.topMargin =  ActivityUtils.scaleY(320);
		mDoorSashLayout.addView(mFLeftDoorSash, params);
		
		
		mBRightDoorSash = new BehindDoorSash(mContext);
		params = ActivityUtils.getLayoutParams(BehindDoorSash.getLayoutWidth(), 
				BehindDoorSash.getLayoutHeight());
		params.leftMargin = ActivityUtils.scaleX(600);
		params.topMargin =  ActivityUtils.scaleY(80);
		mDoorSashLayout.addView(mBRightDoorSash, params);
		
		mBLeftDoorSash = new BehindDoorSash(mContext);
		params = ActivityUtils.getLayoutParams(BehindDoorSash.getLayoutWidth(), 
				BehindDoorSash.getLayoutHeight());
		params.leftMargin = ActivityUtils.scaleX(600);
		params.topMargin =  ActivityUtils.scaleY(320);
		mDoorSashLayout.addView(mBLeftDoorSash, params);
		
		
		mFRightDoorSash.setStatusListener(new FrontDoorSash.StatusListener() {
			public void OnStatusChange(float percent) {
				CarStatusHelper.getInstance().setFRightWindowPercent(percent);
				mIControl.actionSubContentEvent(IControl.EVENT_FRONT_RIGHT_WINDOW_CAHNEG, null);
				mIControl.updateSubContentStatus(IControl.EVENT_FRONT_RIGHT_WINDOW_CAHNEG, null);
				MainApplication.makeToast("" + (int)(100 * percent) + "%");
			}
		});
		
		mFLeftDoorSash.setStatusListener(new FrontDoorSash.StatusListener() {
			public void OnStatusChange(float percent) {
				CarStatusHelper.getInstance().setFLeftWindowPercent(percent);
				mIControl.actionSubContentEvent(IControl.EVENT_FRONT_LEFT_WINDOW_CAHNEG, null);
				mIControl.updateSubContentStatus(IControl.EVENT_FRONT_LEFT_WINDOW_CAHNEG, null);
				MainApplication.makeToast("" + (int)(100 * percent) + "%");
			}
		});
		
		mBRightDoorSash.setStatusListener(new BehindDoorSash.StatusListener() {
			public void OnStatusChange(float percent) {
				CarStatusHelper.getInstance().setBRightWindowPercent(percent);
				mIControl.actionSubContentEvent(IControl.EVENT_BEHIND_RIGHT_WINDOW_CAHNEG, null);
				mIControl.updateSubContentStatus(IControl.EVENT_BEHIND_RIGHT_WINDOW_CAHNEG, null);
				MainApplication.makeToast("" + (int)(100 * percent) + "%");
			}
		});
		
		mBLeftDoorSash.setStatusListener(new BehindDoorSash.StatusListener() {
			public void OnStatusChange(float percent) {
				CarStatusHelper.getInstance().setBLeftWindowPercent(percent);
				mIControl.actionSubContentEvent(IControl.EVENT_BEHIND_LEFT_WINDOW_CAHNEG, null);
				mIControl.updateSubContentStatus(IControl.EVENT_BEHIND_LEFT_WINDOW_CAHNEG, null);
				MainApplication.makeToast("" + (int)(100 * percent) + "%");
			}
		});
	}
	
	private void initAlertRotate(){
		mAlertRotateLeft = new ImageView(mContext);
		mAlertRotateLeft.setId(mAlertRotateLeft.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(156, 156);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.topMargin = ActivityUtils.scaleY(80);
		params.leftMargin =  ActivityUtils.scaleX(120);
		mParentLayout.addView(mAlertRotateLeft, params);
		
		mAlertRotateRight = new ImageView(mContext);
		mAlertRotateRight.setId(mAlertRotateRight.hashCode());
		params = ActivityUtils.getLayoutParams(156, 156);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.topMargin = ActivityUtils.scaleY(80);
		params.rightMargin =  ActivityUtils.scaleX(120);
		mParentLayout.addView(mAlertRotateRight, params);
	}
	
	private void initStatus(){
		CarStatusHelper.getInstance().setLampFarlightStatus(false);
		CarStatusHelper.getInstance().setLampNearlightStatus(false);
		CarStatusHelper.getInstance().setLampFrontflogStatus(false);
		CarStatusHelper.getInstance().setLampBehindflogStatus(false);
		CarStatusHelper.getInstance().setCarFrontDoorStatus(false);
		CarStatusHelper.getInstance().setCarBehindDoorStatus(false);
		
		updateStatus(IControl.EVENT_CAR_WINDOW_OPEN, null);
		updateStatus(IControl.EVENT_RAIN_WIPER_OPEN, null);
		
		updateStatus(IControl.EVENT_FAR_LIGHT_LAMP_CHANGE, null);
		updateStatus(IControl.EVENT_NEAR_LIGHT_LAMP_CHANGE, null);
		updateStatus(IControl.EVENT_FRONT_FLOG_LAMP_CHANGE, null);
		updateStatus(IControl.EVENT_BEHIND_FLOG_LAMP_CHANGE, null);
		
		updateStatus(IControl.EVENT_FRONT_CAR_DOOR_CHANGE, null);
		updateStatus(IControl.EVENT_BEHIND_CAR_DOOR_CHANGE, null);
		updateStatus(IControl.EVENT_FRONT_RIGHT_DOOR_CAHNEG, null);
		updateStatus(IControl.EVENT_FRONT_LEFT_DOOR_CAHNEG, null);
		updateStatus(IControl.EVENT_BEHIND_RIGHT_DOOR_CAHNEG, null);
		updateStatus(IControl.EVENT_BEHIND_LEFT_DOOR_CAHNEG, null);
		
		updateStatus(IControl.EVENT_FRONT_RIGHT_WINDOW_CAHNEG, null);
		updateStatus(IControl.EVENT_FRONT_LEFT_WINDOW_CAHNEG, null);
		updateStatus(IControl.EVENT_BEHIND_RIGHT_WINDOW_CAHNEG, null);
		updateStatus(IControl.EVENT_BEHIND_LEFT_WINDOW_CAHNEG, null);
		
		updateStatus(IControl.EVENT_RAIN_WIPER_CHANGE, null);
		updateStatus(IControl.EVENT_RIGHT_ROTATE_LAMP_CHANGE, null);
		updateStatus(IControl.EVENT_LEFT_ROTATE_LAMP_CHANGE, null);
	}
	
	@Override
	public void onClick(View v) {
		if (v == mBottomDock1){
			CarStatusHelper.getInstance().setCarWindowStatus(
					!CarStatusHelper.getInstance().getCarWindowStatus());
			mIControl.updateSubContentStatus(IControl.EVENT_CAR_WINDOW_OPEN, null);
		}
		
		if (v == mBottomDock2){
			CarStatusHelper.getInstance().setCarRainWiperStatus(
					!CarStatusHelper.getInstance().getCarRainWiperStatus());
			mIControl.updateSubContentStatus(IControl.EVENT_RAIN_WIPER_OPEN, null);
		}
		
		if (v == mBottomDock3){
			CarStatusHelper.getInstance().setLampFarlightStatus(
					!CarStatusHelper.getInstance().getLampFarlightStatus());
			mIControl.updateSubContentStatus(IControl.EVENT_FAR_LIGHT_LAMP_CHANGE, null);
		}
		
		if (v == mBottomDock4){
			CarStatusHelper.getInstance().setLampNearlightStatus(
					!CarStatusHelper.getInstance().getLampNearlightStatus());
			mIControl.updateSubContentStatus(IControl.EVENT_NEAR_LIGHT_LAMP_CHANGE, null);
		}
		
		if (v == mBottomDock5){
			CarStatusHelper.getInstance().setLampFrontflogStatus(
					 !CarStatusHelper.getInstance().getLampFrontflogStatus());
			mIControl.updateSubContentStatus(IControl.EVENT_FRONT_FLOG_LAMP_CHANGE, null);
		}
		
		if (v == mBottomDock6){
			CarStatusHelper.getInstance().setLampBehindflogStatus(
					 !CarStatusHelper.getInstance().getLampBehindflogStatus());
			mIControl.updateSubContentStatus(IControl.EVENT_BEHIND_FLOG_LAMP_CHANGE, null);
		}
		
		if (v == mBottomDock7){
			CarStatusHelper.getInstance().setCarFrontDoorStatus(
					 !CarStatusHelper.getInstance().getCarFrontDoorStatus());
			mIControl.updateSubContentStatus(IControl.EVENT_FRONT_CAR_DOOR_CHANGE, null);
		}
		
		if (v == mBottomDock8){
			CarStatusHelper.getInstance().setCarBehindDoorStatus(
					 !CarStatusHelper.getInstance().getCarBehindDoorStatus());
			mIControl.updateSubContentStatus(IControl.EVENT_BEHIND_CAR_DOOR_CHANGE, null);
		}
		
		if (v == mBottomDock9){
			mIControl.updateSubContentStatus(IControl.EVENT_GO_MAIN_PAGE, null);
		}
		
		if (v == mRainWiper1){
			CarStatusHelper.getInstance().setCarRainWiperStage(CarStatusHelper.RAIN_WIPER_CLOSE);
			mIControl.updateSubContentStatus(IControl.EVENT_RAIN_WIPER_CHANGE, null);
		}
		
		if (v == mRainWiper2){
			CarStatusHelper.getInstance().setCarRainWiperStage(CarStatusHelper.RAIN_WIPER_SLOW);
			mIControl.updateSubContentStatus(IControl.EVENT_RAIN_WIPER_CHANGE, null);
		}
		
		if (v == mRainWiper3){
			CarStatusHelper.getInstance().setCarRainWiperStage(CarStatusHelper.RAIN_WIPER_MIDDLE);
			mIControl.updateSubContentStatus(IControl.EVENT_RAIN_WIPER_CHANGE, null);
		}
		
		if (v == mRainWiper4){
			CarStatusHelper.getInstance().setCarRainWiperStage(CarStatusHelper.RAIN_WIPER_FAST);
			mIControl.updateSubContentStatus(IControl.EVENT_RAIN_WIPER_CHANGE, null);
		}
	}
	
	public void setVisible(boolean visible){
		mParentLayout.setVisibility(visible? View.VISIBLE:View.GONE);
	}
	
	public boolean isVisible(){
		if (mParentLayout.getVisibility() == View.VISIBLE){
			return true;
		}
		return false;
	}
	
	public void updateStatus(int event, Object obj){
		switch (event) {
		case IControl.EVENT_GO_MAIN_PAGE:
			setVisible(false);
			break;
			
		case IControl.EVENT_CAR_WINDOW_OPEN:
			updateCarWindowVisible();
			break;
		case IControl.EVENT_RAIN_WIPER_OPEN:
			updateRainWiperVisible();
			break;
		case IControl.EVENT_FAR_LIGHT_LAMP_CHANGE:
			updateFarLightLamp();
			break;
		case IControl.EVENT_NEAR_LIGHT_LAMP_CHANGE:
			updateNearLightLamp();
			break;
		case IControl.EVENT_FRONT_FLOG_LAMP_CHANGE:
			updateFrontFlogLamp();
			break;
		case IControl.EVENT_BEHIND_FLOG_LAMP_CHANGE:
			updateBehindFlogLamp();
			break;
		case IControl.EVENT_FRONT_CAR_DOOR_CHANGE:
			updateFrontDoor();
			break;
		case IControl.EVENT_BEHIND_CAR_DOOR_CHANGE:
			updateBehindDoor();
			break;
			
			
		case IControl.EVENT_FRONT_RIGHT_DOOR_CAHNEG:
			updateFrontRightDoor();
			break;
		case IControl.EVENT_FRONT_LEFT_DOOR_CAHNEG:
			updateFrontLeftDoor();
			break;
		case IControl.EVENT_BEHIND_RIGHT_DOOR_CAHNEG:
			updateBehindRightDoor();
			break;
		case IControl.EVENT_BEHIND_LEFT_DOOR_CAHNEG:
			updateBehindLeftDoor();
			break;
			
		case IControl.EVENT_FRONT_RIGHT_WINDOW_CAHNEG:
			updateCarWindowFRight();
			break;
		case IControl.EVENT_FRONT_LEFT_WINDOW_CAHNEG:
			updateCarWindowFLeft();
			break;
		case IControl.EVENT_BEHIND_RIGHT_WINDOW_CAHNEG:
			updateCarWindowBRight();
			break;
		case IControl.EVENT_BEHIND_LEFT_WINDOW_CAHNEG:
			updateCarWindowBLeft();
			break;
			
			
		case IControl.EVENT_RAIN_WIPER_CHANGE:
			updateRainWiperModel();
			break;

		case IControl.EVENT_RIGHT_ROTATE_LAMP_CHANGE:
			updateRotateRightLamp();
			break;
		case IControl.EVENT_LEFT_ROTATE_LAMP_CHANGE:
			updateRotateLeftLamp();
			break;
		default:
			break;
		}
	}
	
	private void updateFarLightLamp(){
		boolean open = CarStatusHelper.getInstance().getLampFarlightStatus();
		mBottomDock3.setBackgroundResource(open ? 
				R.drawable.sub_icon_far_light_lamp_open :
				R.drawable.sub_icon_far_light_lamp_close);

		mRightFarLightLamp.setBackgroundResource(open?
				R.drawable.sub_car_front_right_far_light_lamp : 0);
		mLeftFarLightLamp.setBackgroundResource(open?
				R.drawable.sub_car_front_left_far_light_lamp : 0);

	}
	
	private void updateNearLightLamp(){
		boolean open = CarStatusHelper.getInstance().getLampNearlightStatus();
		mBottomDock4.setBackgroundResource(open ? 
				R.drawable.sub_icon_near_light_lamp_open :
				R.drawable.sub_icon_near_light_lamp_close);
		
		mRightNearLightLamp.setBackgroundResource(open?
				R.drawable.sub_car_front_right_near_light_lamp : 0);
		mLeftNearLightLamp.setBackgroundResource(open?
				R.drawable.sub_car_front_left_near_light_lamp : 0);
	}
	
	private void updateFrontFlogLamp(){
		boolean open = CarStatusHelper.getInstance().getLampFrontflogStatus();
		mBottomDock5.setBackgroundResource(open ? 
				R.drawable.sub_icon_front_flog_lamp_open :
				R.drawable.sub_icon_front_flog_lamp_close);
		
		mFrontRightFlogLamp.setVisibility(open ? View.VISIBLE: View.GONE);
		mFrontLeftFlogLamp.setVisibility(open ? View.VISIBLE: View.GONE);
	}
	
	private void updateBehindFlogLamp(){
		boolean open = CarStatusHelper.getInstance().getLampBehindflogStatus();
		mBottomDock6.setBackgroundResource(open ? 
				R.drawable.sub_icon_behind_flog_lamp_open :
				R.drawable.sub_icon_behind_flog_lamp_close);
		
		mBehindRightFlogLamp.setVisibility(open ? View.VISIBLE: View.GONE);
		mBehindLeftFlogLamp.setVisibility(open ? View.VISIBLE: View.GONE);
	}
	
	private void updateFrontDoor(){
		boolean open = CarStatusHelper.getInstance().getCarFrontDoorStatus();
		mBottomDock7.setBackgroundResource(open ? 
				R.drawable.sub_icon_front_door_open :
				R.drawable.sub_icon_front_door_close);
		
		mFrontDoor.setBackgroundResource(open?
				R.drawable.sub_car_front_door_open :
				R.drawable.sub_car_front_door_close);
	}
	
	private void updateBehindDoor(){
		boolean open = CarStatusHelper.getInstance().getCarBehindDoorStatus();
		mBottomDock8.setBackgroundResource(open ? 
				R.drawable.sub_icon_behind_door_open :
				R.drawable.sub_icon_behind_door_close);
		
		mBehindDoor.setBackgroundResource(open?
				R.drawable.sub_car_behind_door_open :
				R.drawable.sub_car_behind_door_close);
	}
	
	
	private void updateFrontRightDoor(){
		boolean open = CarStatusHelper.getInstance().getCarFrontDoorRightStatus();
		mFrontRightDoor.setBackgroundResource(open ? 
				R.drawable.sub_car_front_right_door_open :
				R.drawable.sub_car_front_right_door_close);
	}
	
	private void updateFrontLeftDoor(){
		boolean open = CarStatusHelper.getInstance().getCarFrontDoorLeftStatus();
		mFrontLeftDoor.setBackgroundResource(open ? 
				R.drawable.sub_car_front_left_door_open :
				R.drawable.sub_car_front_left_door_close);
	}
	
	private void updateBehindRightDoor(){
		boolean open = CarStatusHelper.getInstance().getCarBehindDoorRightStatus();
		mBehindRightDoor.setBackgroundResource(open ? 
				R.drawable.sub_car_behind_right_door_open :
				R.drawable.sub_car_behind_right_door_close);
	}
	
	private void updateBehindLeftDoor(){
		boolean open = CarStatusHelper.getInstance().getCarBehindDoorLeftStatus();
		mBehindLeftDoor.setBackgroundResource(open ? 
				R.drawable.sub_car_behind_left_door_open :
				R.drawable.sub_car_behind_left_door_close);
	}

	private void updateRainWiperVisible(){
		boolean open = CarStatusHelper.getInstance().getCarRainWiperStatus();
		mRainWiperLayout.setVisibility(open? View.VISIBLE : View.GONE);
	}
	
	private void updateRainWiperModel(){
		int state = CarStatusHelper.getInstance().getCarRainWiperStage();
		if (state == CarStatusHelper.RAIN_WIPER_CLOSE){
			mRainWiper1.setImageResource(R.drawable.sub_rain_wiper_select);
			mRainWiper2.setImageResource(R.drawable.sub_rain_wiper_normal);
			mRainWiper3.setImageResource(R.drawable.sub_rain_wiper_normal);
			mRainWiper4.setImageResource(R.drawable.sub_rain_wiper_normal);
		}else if (state == CarStatusHelper.RAIN_WIPER_SLOW){
			mRainWiper1.setImageResource(R.drawable.sub_rain_wiper_normal);
			mRainWiper2.setImageResource(R.drawable.sub_rain_wiper_select);
			mRainWiper3.setImageResource(R.drawable.sub_rain_wiper_normal);
			mRainWiper4.setImageResource(R.drawable.sub_rain_wiper_normal);
		}else if (state == CarStatusHelper.RAIN_WIPER_MIDDLE){
			mRainWiper1.setImageResource(R.drawable.sub_rain_wiper_normal);
			mRainWiper2.setImageResource(R.drawable.sub_rain_wiper_normal);
			mRainWiper3.setImageResource(R.drawable.sub_rain_wiper_select);
			mRainWiper4.setImageResource(R.drawable.sub_rain_wiper_normal);
		}else if (state == CarStatusHelper.RAIN_WIPER_FAST){
			mRainWiper1.setImageResource(R.drawable.sub_rain_wiper_normal);
			mRainWiper2.setImageResource(R.drawable.sub_rain_wiper_normal);
			mRainWiper3.setImageResource(R.drawable.sub_rain_wiper_normal);
			mRainWiper4.setImageResource(R.drawable.sub_rain_wiper_select);
		}
	}
	
	private AnimationDrawable mFRightLampRotateDrawable;
	private AnimationDrawable mBRightLampRotateDrawable;
	private AnimationDrawable mAlertRotateRightDrawable;
	private void updateRotateRightLamp(){
		if (mFrontRightRotateLamp != null && mBehindRightRotateLamp != null){
			boolean open = CarStatusHelper.getInstance().getCarLampRotateRightStatus();
			LogUtils.LOGD(TAG, "onTouch updateRotateRightLamp open = " + open);
			if (mFRightLampRotateDrawable != null){
				mFRightLampRotateDrawable.stop();
			}
			if (mBRightLampRotateDrawable != null){
				mBRightLampRotateDrawable.stop();
			}
			if (mAlertRotateRightDrawable != null){
				mAlertRotateRightDrawable.stop();
			}
			
			mFrontRightRotateLamp.setImageResource(open ? 
					R.drawable.sub_rotate_right_front_lamp : 0);
			mBehindRightRotateLamp.setImageResource(open ? 
					R.drawable.sub_rotate_right_behind_lamp : 0);
			mAlertRotateRight.setImageResource(open? R.drawable.sub_rotate_right_alert_lamp
					: R.drawable.sub_lamp_rotate_right_normal);
			if (open){
				mFRightLampRotateDrawable = (AnimationDrawable) mFrontRightRotateLamp.getDrawable();
				mBRightLampRotateDrawable = (AnimationDrawable) mBehindRightRotateLamp.getDrawable();
				mAlertRotateRightDrawable = (AnimationDrawable) mAlertRotateRight.getDrawable();
				mFRightLampRotateDrawable.start();
				mBRightLampRotateDrawable.start();
				mAlertRotateRightDrawable.start();
			}
		}
	}
	
	private AnimationDrawable mFLeftLampRotateDrawable;
	private AnimationDrawable mBLeftLampRotateDrawable;
	private AnimationDrawable mAlertRotateLeftDrawable;
	private void updateRotateLeftLamp(){
		if (mFrontLeftRotateLamp != null && mBehindLeftRotateLamp != null){
			boolean open = CarStatusHelper.getInstance().getCarLampRotateLeftStatus();
			LogUtils.LOGD(TAG, "onTouch updateRotateLeftLamp open = " + open);
			if (mFLeftLampRotateDrawable != null){
				mFLeftLampRotateDrawable.stop();
			}
			if (mBLeftLampRotateDrawable != null){
				mBLeftLampRotateDrawable.stop();
			}
			if (mAlertRotateLeftDrawable != null){
				mAlertRotateLeftDrawable.stop();
			}
			
			mFrontLeftRotateLamp.setImageResource(open ? 
					R.drawable.sub_rotate_left_front_lamp : 0);
			mBehindLeftRotateLamp.setImageResource(open ? 
					R.drawable.sub_rotate_left_behind_lamp : 0);
			mAlertRotateLeft.setImageResource(open? R.drawable.sub_rotate_left_alert_lamp
					: R.drawable.sub_lamp_rotate_left_normal);
			if (open){
				mFLeftLampRotateDrawable = (AnimationDrawable) mFrontLeftRotateLamp.getDrawable();
				mBLeftLampRotateDrawable = (AnimationDrawable) mBehindLeftRotateLamp.getDrawable();
				mAlertRotateLeftDrawable = (AnimationDrawable) mAlertRotateLeft.getDrawable();
				mFLeftLampRotateDrawable.start();
				mBLeftLampRotateDrawable.start();
				mAlertRotateLeftDrawable.start();
			}
		}
	}
	
	private void updateCarWindowVisible(){
		boolean open = CarStatusHelper.getInstance().getCarWindowStatus();
		mDoorSashLayout.setVisibility(open? View.VISIBLE : View.GONE);
		mCenterArea.setVisibility(!open? View.VISIBLE : View.GONE);
	}
	
	private void updateCarWindowFRight(){
		float percent = CarStatusHelper.getInstance().getFRightWindowPercent();
		mFRightDoorSash.setSash(percent);
	}
	
	private void updateCarWindowFLeft(){
		float percent = CarStatusHelper.getInstance().getFLeftWindowPercent();
		mFLeftDoorSash.setSash(percent);
	}
	
	private void updateCarWindowBRight(){
		float percent = CarStatusHelper.getInstance().getBRightWindowPercent();
		mBRightDoorSash.setSash(percent);
	}
	
	private void updateCarWindowBLeft(){
		float percent = CarStatusHelper.getInstance().getBLeftWindowPercent();
		mBLeftDoorSash.setSash(percent);
	}
}