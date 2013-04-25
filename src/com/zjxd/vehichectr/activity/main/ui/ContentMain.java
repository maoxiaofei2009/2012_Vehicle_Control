package com.zjxd.vehichectr.activity.main.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.zjxd.vehichectr.R;
import com.zjxd.vehichectr.activity.main.control.CarStatusHelper;
import com.zjxd.vehichectr.activity.main.control.IControl;
import com.zjxd.vehichectr.activity.main.ui.view.WindLevelView;
import com.zjxd.vehichectr.activity.utils.ActivityUtils;


public class ContentMain implements View.OnClickListener{
	public static final String TAG = "ContentArea ";
	private final Context mContext;
	private IControl mIControl;
	private RelativeLayout mParentLayout;
	private float SIDE_TEXT_SIZE = 0;
	
	private ImageView mTopArea;
	private RelativeLayout mContentLayout;
	private int mThemeIndex = 0;
	
	private static final int THEME_RES_ID[] = {
		R.drawable.main_big_background_0,
		R.drawable.main_big_background_1,
		R.drawable.main_big_background_2,
		R.drawable.main_big_background_3,
		R.drawable.main_big_background_4,
		R.drawable.main_big_background_5,
		R.drawable.main_big_background_6,
		R.drawable.main_big_background_7
	}; 

	private AudioManager mAudioManager;
	public ContentMain(Context context, IControl control, RelativeLayout parentLayout){
		mContext = context;
		mIControl = control;
		mParentLayout = parentLayout;
		SIDE_TEXT_SIZE  = mContext.getResources().getDimension(R.dimen.main_text_small);
		mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		
		initLayout();
		initStatus();
		initListener();
	}
	
	private void initLayout(){
		mParentLayout.setBackgroundResource(THEME_RES_ID[mThemeIndex]);
		if (mParentLayout != null){
			initTopArea();
			initContentArea();
		}
	}
	
	private void initStatus(){
		updateAcRunStyle();
		updateAcModel();
		updateAcWindStyle();
		updateAirConditionOnOff();
		updateAcWindStage();
		updateAcTemperature();
		updateVolumeMute();
		updateVolumeProgress();
	}
	
	private void initListener(){
		mBtnChangeTheme.setOnClickListener(this);
		mBtnCarControl.setOnClickListener(this);
		mBtnLockScreen.setOnClickListener(this);
		
		//APP
		mLeftText1.setOnClickListener(this);
		mLeftText1.setTag(AppGroupManager.LOAD_RADIO_APP_LIST);
		mLeftText2.setOnClickListener(this);
		mLeftText2.setTag(AppGroupManager.LOAD_TV_APP_LIST);
		mLeftText3.setOnClickListener(this);
		mLeftText3.setTag(AppGroupManager.LOAD_PHONE_APP_LIST);
		mLeftText4.setOnClickListener(this);
		
		mRightText1.setOnClickListener(this);
		mRightText1.setTag(AppGroupManager.LOAD_PLAYER_APP_LIST);
		mRightText2.setOnClickListener(this);
		mRightText2.setTag(AppGroupManager.LOAD_MAP_APP_LIST);
		mRightText3.setOnClickListener(this);
		mRightText3.setTag(AppGroupManager.LOAD_CLIMATE_APP_LIST);
		mRightText4.setOnClickListener(this);
		mRightText4.setTag(AppGroupManager.LOAD_OTHER_APP_LIST);
		
		
		//AC
		mLeftAction1.setOnClickListener(this);
		mLeftAction1.setTag(CarStatusHelper.AC_RUN_STYLE_AC);
		mLeftAction2.setOnClickListener(this);
		mLeftAction2.setTag(CarStatusHelper.AC_RUN_STYLE_AUTO);
		mLeftAction3.setOnClickListener(this);
		mLeftAction3.setTag(CarStatusHelper.AC_RUN_STYLE_ECO);
		mLeftAction4.setOnClickListener(this);
		mLeftAction4.setTag(CarStatusHelper.AC_RUN_STYLE_SINGLE);
		
		mRightAction1.setOnClickListener(this);
		mRightAction1.setTag(CarStatusHelper.AC_MODEL_HOT);
		mRightAction2.setOnClickListener(this);
		mRightAction2.setTag(CarStatusHelper.AC_MODEL_COLD);
		mRightAction3.setOnClickListener(this);
		mRightAction3.setTag(CarStatusHelper.AC_MODEL_WIND);
		
		mBottomAction1.setOnClickListener(this);
		mBottomAction1.setTag(CarStatusHelper.AC_WIND_STYLE_FACE);
		mBottomAction2.setOnClickListener(this);
		mBottomAction2.setTag(CarStatusHelper.AC_WIND_STYLE_FACE_FOOT);
		mBottomAction3.setOnClickListener(this);
		mBottomAction3.setTag(CarStatusHelper.AC_WIND_STYLE_FOOT);
		mBottomAction4.setOnClickListener(this);
		mBottomAction4.setTag(CarStatusHelper.AC_WIND_STYLE_FOOT_DEFROST);
		mBottomAction5.setOnClickListener(this);
		mBottomAction5.setTag(CarStatusHelper.AC_WIND_STYLE_DEFROST);
		
		//AC on/off
		mBottomAction6.setOnClickListener(this);
		
		
		mBtnReduce.setOnClickListener(this);
		mBtnAdd.setOnClickListener(this);
		mBtnVolumeMute.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		if (v == mBtnChangeTheme){
			mThemeIndex ++;
			if (mThemeIndex == THEME_RES_ID.length){
				mThemeIndex = 0;
			}
			mParentLayout.setBackgroundResource(THEME_RES_ID[mThemeIndex]);
		}
		
		if (v == mBtnLockScreen){
		}
		
		if (v == mBtnCarControl){
			mIControl.updateMainContentStatus(IControl.EVENT_SHOW_CAR_CONTROL, null);
		}
		
		if (v == mBtnVolumeMute){
			int model = mAudioManager.getRingerMode();
			if (model == AudioManager.RINGER_MODE_NORMAL){
				mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			}else{
				mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			}
			updateVolumeMute();
		}
		
		if (v == mLeftText1 || v == mLeftText2 || v == mLeftText3 
			|| v == mRightText1 || v == mRightText2 || v == mRightText3 ||  v == mRightText4){
	    	if (mAppGridView != null){
	    		mAppGridView.setVisibility(View.VISIBLE);
	    	}
			if (mAppGroupManager != null){
	    		mAppGroupManager.show((String) v.getTag());
	    	}
		}
		
		if (v == mLeftText4){
	    	if (mAppGridView != null){
	    		mAppGridView.setVisibility(View.GONE);
	    	}
		}
		
		
		if (v == mBottomAction6){
			CarStatusHelper.getInstance().setAirConditionStatus(
					!CarStatusHelper.getInstance().getAirConditionStatus());
			mIControl.actionSubContentEvent(IControl.EVENT_AC_STATUS_CHANGE, null);
			mIControl.updateMainContentStatus(IControl.EVENT_AC_STATUS_CHANGE, null);
		}
		
		if (v == mLeftAction1 || v == mLeftAction2 || v == mLeftAction3 || v == mLeftAction4){
			CarStatusHelper.getInstance().setAirConditionRunStyle((Integer) v.getTag());
			mIControl.actionSubContentEvent(IControl.EVENT_AC_RUN_STYLE_CHANGE, null);
			mIControl.updateMainContentStatus(IControl.EVENT_AC_RUN_STYLE_CHANGE, null);
		}
		
		if (v == mRightAction1 || v == mRightAction2 || v == mRightAction3){
			CarStatusHelper.getInstance().setAirConditionModel((Integer) v.getTag());
			mIControl.actionSubContentEvent(IControl.EVENT_AC_MODEL_CHANGE, null);
			mIControl.updateMainContentStatus(IControl.EVENT_AC_MODEL_CHANGE, null);
		}
		
		if (v == mBottomAction1 || v == mBottomAction2 || v == mBottomAction3
			|| v == mBottomAction4 || v == mBottomAction5){
			CarStatusHelper.getInstance().setAirConditionWindStyle((Integer) v.getTag());
			mIControl.actionSubContentEvent(IControl.EVENT_AC_WIND_STYLE_CHANGE, null);
			mIControl.updateMainContentStatus(IControl.EVENT_AC_WIND_STYLE_CHANGE, null);
		}
		
		if (v == mBtnReduce){
			if (mWindLevelView.getVisibility() == View.VISIBLE){
				CarStatusHelper.getInstance().setAirConditionWindStage(
						CarStatusHelper.getInstance().getAirConditionWindStage() - 1);
				mIControl.actionSubContentEvent(IControl.EVENT_AC_WIND_STAGE_CHANGE, null);
				mIControl.updateMainContentStatus(IControl.EVENT_AC_WIND_STAGE_CHANGE, null);
			}else{
				CarStatusHelper.getInstance().setAirConditionTemperature(
						CarStatusHelper.getInstance().getAirConditionTemperature() - 1);
				mIControl.actionSubContentEvent(IControl.EVENT_AC_TEMPERATURE_CHANGE, null);
				mIControl.updateMainContentStatus(IControl.EVENT_AC_TEMPERATURE_CHANGE, null);
			}
		}
		
		if (v == mBtnAdd){
			if (mWindLevelView.getVisibility() == View.VISIBLE){
				CarStatusHelper.getInstance().setAirConditionWindStage(
						CarStatusHelper.getInstance().getAirConditionWindStage() + 1);
				mIControl.actionSubContentEvent(IControl.EVENT_AC_WIND_STAGE_CHANGE, null);
				mIControl.updateMainContentStatus(IControl.EVENT_AC_WIND_STAGE_CHANGE, null);
			}else{
				CarStatusHelper.getInstance().setAirConditionTemperature(
						CarStatusHelper.getInstance().getAirConditionTemperature() + 1);
				mIControl.actionSubContentEvent(IControl.EVENT_AC_TEMPERATURE_CHANGE, null);
				mIControl.updateMainContentStatus(IControl.EVENT_AC_TEMPERATURE_CHANGE, null);
			}
		}
	}
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (mAppGroupManager != null){
    		mAppGroupManager.onActivityResult(requestCode, resultCode, data);
    	}
    }
    
	
	private void initTopArea(){
		mTopArea = new ImageView(mContext);
		mTopArea.setId(mTopArea.hashCode());
		mTopArea.setImageResource(R.drawable.sub_title_icon);
		mTopArea.setBackgroundResource(R.drawable.sub_top_background);
		LayoutParams params = ActivityUtils.getLayoutParams(1280, 106);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		mParentLayout.addView(mTopArea, params);
	}
	
	private void initContentArea(){
		mContentLayout = new RelativeLayout(mContext);
		mContentLayout.setId(mContentLayout.hashCode());
		LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT, 
				RelativeLayout.LayoutParams.FILL_PARENT);
		params.addRule(RelativeLayout.BELOW, mTopArea.getId());
		mParentLayout.addView(mContentLayout, params);
		
		
		initLeftSide();
		initRightSide();
		initCenterArea();
		initBottomSide();
		
		initAppArea();
	}
	
	private RelativeLayout mLeftSide;
	private TextView mLeftText1;
	private TextView mLeftText2;
	private TextView mLeftText3;
	private TextView mLeftText4;
	private ImageView mBtnCarControl;
	private void initLeftSide(){
		mLeftSide = new RelativeLayout(mContext);
		mLeftSide.setId(mLeftSide.hashCode());
		mLeftSide.setBackgroundResource(R.drawable.main_side_background);
		LayoutParams params = ActivityUtils.getLayoutParams(131, 380);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.leftMargin = ActivityUtils.scaleX(60);
		params.topMargin = ActivityUtils.scaleY(50);
		mContentLayout.addView(mLeftSide, params);
		
		mBtnCarControl = new ImageView(mContext);
		mBtnCarControl.setId(mBtnCarControl.hashCode());
		mBtnCarControl.setImageResource(R.drawable.main_btn_car_control);
		mBtnCarControl.setBackgroundResource(R.drawable.main_btn_background);
		params = ActivityUtils.getLayoutParams(107, 103);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.BELOW, mLeftSide.getId());
		params.leftMargin = ActivityUtils.scaleX(72);
		params.topMargin = ActivityUtils.scaleY(25);
		mContentLayout.addView(mBtnCarControl, params);
		
		mLeftText1 = new TextView(mContext);
		mLeftText1.setId(mLeftText1.hashCode());
		mLeftText1.setGravity(Gravity.CENTER);
		mLeftText1.setText("FM/AM");
		mLeftText1.setTextColor(Color.WHITE);
		mLeftText1.setTextSize(SIDE_TEXT_SIZE);
		mLeftText1.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(125, 90);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.topMargin = ActivityUtils.scaleY(10);
		mLeftSide.addView(mLeftText1, params);
		
		mLeftText2 = new TextView(mContext);
		mLeftText2.setId(mLeftText2.hashCode());
		mLeftText2.setGravity(Gravity.CENTER);
		mLeftText2.setText("TV");
		mLeftText2.setTextColor(Color.WHITE);
		mLeftText2.setTextSize(SIDE_TEXT_SIZE);
		mLeftText2.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(125, 90);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, mLeftText1.getId());
		mLeftSide.addView(mLeftText2, params);
		
		mLeftText3 = new TextView(mContext);
		mLeftText3.setId(mLeftText3.hashCode());
		mLeftText3.setGravity(Gravity.CENTER);
		mLeftText3.setText("PHONE");
		mLeftText3.setTextColor(Color.WHITE);
		mLeftText3.setTextSize(SIDE_TEXT_SIZE);
		mLeftText3.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(125, 90);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, mLeftText2.getId());
		mLeftSide.addView(mLeftText3, params);
		
		mLeftText4 = new TextView(mContext);
		mLeftText4.setId(mLeftText4.hashCode());
		mLeftText4.setGravity(Gravity.CENTER);
		mLeftText4.setText("A/C");
		mLeftText4.setTextColor(Color.WHITE);
		mLeftText4.setTextSize(SIDE_TEXT_SIZE);
		mLeftText4.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(125, 90);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, mLeftText3.getId());
		mLeftSide.addView(mLeftText4, params);
	}
	
	private RelativeLayout mRightSide;
	private TextView mRightText1;
	private TextView mRightText2;
	private TextView mRightText3;
	private TextView mRightText4;
	private ImageView mBtnLockScreen;
	private ImageView mBtnChangeTheme;
	private void initRightSide(){
		mRightSide = new RelativeLayout(mContext);
		mRightSide.setId(mLeftSide.hashCode());
		mRightSide.setBackgroundResource(R.drawable.main_side_background);
		LayoutParams params = ActivityUtils.getLayoutParams(131, 380);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.rightMargin = ActivityUtils.scaleX(60);
		params.topMargin = ActivityUtils.scaleY(50);
		mContentLayout.addView(mRightSide, params);
		
		mBtnLockScreen = new ImageView(mContext);
		mBtnLockScreen.setId(mBtnLockScreen.hashCode());
		mBtnLockScreen.setImageResource(R.drawable.main_btn_lock_screen);
		mBtnLockScreen.setBackgroundResource(R.drawable.main_btn_background);
		params = ActivityUtils.getLayoutParams(107, 103);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.BELOW, mRightSide.getId());
		params.rightMargin = ActivityUtils.scaleX(72);
		params.topMargin = ActivityUtils.scaleY(25);
		mContentLayout.addView(mBtnLockScreen, params);
		
		mBtnChangeTheme = new ImageView(mContext);
		mBtnChangeTheme.setId(mBtnChangeTheme.hashCode());
		mBtnChangeTheme.setBackgroundResource(R.drawable.main_change_theme_drawable);
		params = ActivityUtils.getLayoutParams(102, 96);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.BELOW, mBtnLockScreen.getId());
		params.rightMargin = ActivityUtils.scaleX(74);
		params.topMargin = ActivityUtils.scaleY(15);
		mContentLayout.addView(mBtnChangeTheme, params);
		
		
		mRightText1 = new TextView(mContext);
		mRightText1.setId(mRightText1.hashCode());
		mRightText1.setGravity(Gravity.CENTER);
		mRightText1.setText("PLAYER");
		mRightText1.setTextColor(Color.WHITE);
		mRightText1.setTextSize(SIDE_TEXT_SIZE);
		mRightText1.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(125, 90);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.topMargin = ActivityUtils.scaleY(10);
		mRightSide.addView(mRightText1, params);
		
		mRightText2 = new TextView(mContext);
		mRightText2.setId(mRightText2.hashCode());
		mRightText2.setGravity(Gravity.CENTER);
		mRightText2.setText("MAP");
		mRightText2.setTextColor(Color.WHITE);
		mRightText2.setTextSize(SIDE_TEXT_SIZE);
		mRightText2.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(125, 90);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, mRightText1.getId());
		mRightSide.addView(mRightText2, params);
		
		mRightText3 = new TextView(mContext);
		mRightText3.setId(mLeftText3.hashCode());
		mRightText3.setGravity(Gravity.CENTER);
		mRightText3.setText("CLIMATE");
		mRightText3.setTextColor(Color.WHITE);
		mRightText3.setTextSize(SIDE_TEXT_SIZE);
		mRightText3.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(125, 90);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, mRightText2.getId());
		mRightSide.addView(mRightText3, params);
		
		mRightText4 = new TextView(mContext);
		mRightText4.setId(mRightText4.hashCode());
		mRightText4.setGravity(Gravity.CENTER);
		mRightText4.setText("OTHER");
		mRightText4.setTextColor(Color.WHITE);
		mRightText4.setTextSize(SIDE_TEXT_SIZE);
		mRightText4.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(125, 90);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, mRightText3.getId());
		mRightSide.addView(mRightText4, params);
	}
	
	private RelativeLayout mCenterArea;
	private RelativeLayout mCenterMiddle;
	private RelativeLayout mCenterLeft;
	private ImageView mLeftAction1;
	private ImageView mLeftAction2;
	private ImageView mLeftAction3;
	private ImageView mLeftAction4;
	private RelativeLayout mCenterRight;
	private ImageView mRightAction1;
	private ImageView mRightAction2;
	private ImageView mRightAction3;

	private RelativeLayout mCenterBottom;
	private ImageView mBottomAction1;
	private ImageView mBottomAction2;
	private ImageView mBottomAction3;
	private ImageView mBottomAction4;
	private ImageView mBottomAction5;
	private ImageView mBottomAction6;
	
	private WindLevelView mWindLevelView;
	private TextView mTemperatureText;
	private ImageView mBtnReduce;
	private ImageView mBtnAdd;
	
	private void initCenterArea(){
		mCenterArea = new RelativeLayout(mContext);
		mCenterArea.setId(mCenterArea.hashCode());
		mCenterArea.setBackgroundResource(R.drawable.main_inside_background);
		LayoutParams params = ActivityUtils.getLayoutParams(836, 516);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.topMargin = ActivityUtils.scaleY(40);
		mContentLayout.addView(mCenterArea, params);
		
		
		RelativeLayout centerMiddleBg = new RelativeLayout(mContext);
		centerMiddleBg.setId(centerMiddleBg.hashCode());
		centerMiddleBg.setBackgroundResource(R.drawable.main_inside_middle_background);
		params = ActivityUtils.getLayoutParams(501, 294);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.topMargin = ActivityUtils.scaleY(40);
		mCenterArea.addView(centerMiddleBg, params);
		
		mCenterMiddle = new RelativeLayout(mContext);
		mCenterMiddle.setId(mCenterMiddle.hashCode());
		params = ActivityUtils.getLayoutParams(501, 294);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.topMargin = ActivityUtils.scaleY(40);
		mCenterArea.addView(mCenterMiddle, params);
		
		mCenterLeft = new RelativeLayout(mContext);
		mCenterLeft.setId(mCenterLeft.hashCode());
		mCenterLeft.setBackgroundResource(R.drawable.main_inside_left_right_background);
		params = ActivityUtils.getLayoutParams(109, 306);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.leftMargin = ActivityUtils.scaleX(50);
		params.topMargin = ActivityUtils.scaleY(40);
		mCenterArea.addView(mCenterLeft, params);
		
		
		mCenterRight = new RelativeLayout(mContext);
		mCenterRight.setId(mCenterRight.hashCode());
		mCenterRight.setBackgroundResource(R.drawable.main_inside_left_right_background);
		params = ActivityUtils.getLayoutParams(109, 306);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.rightMargin = ActivityUtils.scaleX(50);
		params.topMargin = ActivityUtils.scaleY(40);
		mCenterArea.addView(mCenterRight, params);
		
		mCenterBottom = new RelativeLayout(mContext);
		mCenterBottom.setId(mCenterBottom.hashCode());
		mCenterBottom.setBackgroundResource(R.drawable.main_inside_bottom_background);
		params = ActivityUtils.getLayoutParams(574, 101);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, centerMiddleBg.getId());
		params.topMargin = ActivityUtils.scaleY(35);
		mCenterArea.addView(mCenterBottom, params);
		
		//left 
		mLeftAction1 = new ImageView(mContext);
		mLeftAction1.setId(mLeftAction1.hashCode());
		mLeftAction1.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(86, 66);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.topMargin = ActivityUtils.scaleY(30);
		mCenterLeft.addView(mLeftAction1, params);
		
		mLeftAction2 = new ImageView(mContext);
		mLeftAction2.setId(mLeftAction2.hashCode());
		mLeftAction2.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(86, 66);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, mLeftAction1.getId());
		mCenterLeft.addView(mLeftAction2, params);
		
		mLeftAction3 = new ImageView(mContext);
		mLeftAction3.setId(mLeftAction3.hashCode());
		mLeftAction3.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(86, 66);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, mLeftAction2.getId());
		mCenterLeft.addView(mLeftAction3, params);
		
		mLeftAction4 = new ImageView(mContext);
		mLeftAction4.setId(mLeftAction4.hashCode());
		mLeftAction4.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(86, 66);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, mLeftAction3.getId());
		mCenterLeft.addView(mLeftAction4, params);
		
		//right
		mRightAction1 = new ImageView(mContext);
		mRightAction1.setId(mRightAction1.hashCode());
		mRightAction1.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(86, 86);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.topMargin = ActivityUtils.scaleY(20);
		mCenterRight.addView(mRightAction1, params);
		
		mRightAction2 = new ImageView(mContext);
		mRightAction2.setId(mRightAction2.hashCode());
		mRightAction2.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(86, 86);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, mRightAction1.getId());
		params.topMargin = ActivityUtils.scaleY(5);
		mCenterRight.addView(mRightAction2, params);
		
		mRightAction3 = new ImageView(mContext);
		mRightAction3.setId(mRightAction3.hashCode());
		mRightAction3.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(86, 86);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, mRightAction2.getId());
		params.topMargin = ActivityUtils.scaleY(5);
		mCenterRight.addView(mRightAction3, params);
		
		//bottom
		mBottomAction1 = new ImageView(mContext);
		mBottomAction1.setId(mBottomAction1.hashCode());
		mBottomAction1.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(66, 66);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.leftMargin = ActivityUtils.scaleX(40);
		mCenterBottom.addView(mBottomAction1, params);
		
		mBottomAction2 = new ImageView(mContext);
		mBottomAction2.setId(mBottomAction2.hashCode());
		mBottomAction2.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(66, 66);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomAction1.getId());
		params.leftMargin = ActivityUtils.scaleX(10);
		mCenterBottom.addView(mBottomAction2, params);
		
		mBottomAction3 = new ImageView(mContext);
		mBottomAction3.setId(mBottomAction3.hashCode());
		mBottomAction3.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(66, 66);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomAction2.getId());
		params.leftMargin = ActivityUtils.scaleX(10);
		mCenterBottom.addView(mBottomAction3, params);
		
		mBottomAction4 = new ImageView(mContext);
		mBottomAction4.setId(mBottomAction4.hashCode());
		mBottomAction4.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(66, 66);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomAction3.getId());
		params.leftMargin = ActivityUtils.scaleX(10);
		mCenterBottom.addView(mBottomAction4, params);
		
		mBottomAction5 = new ImageView(mContext);
		mBottomAction5.setId(mBottomAction5.hashCode());
		mBottomAction5.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(66, 66);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomAction4.getId());
		params.leftMargin = ActivityUtils.scaleX(10);
		mCenterBottom.addView(mBottomAction5, params);
		
		mBottomAction6 = new ImageView(mContext);
		mBottomAction6.setId(mBottomAction6.hashCode());
		mBottomAction6.setBackgroundResource(R.drawable.main_text_background);
		params = ActivityUtils.getLayoutParams(72, 72);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.rightMargin = ActivityUtils.scaleX(40);
		mCenterBottom.addView(mBottomAction6, params);
		
		mWindLevelView = new WindLevelView(mContext);
		mWindLevelView.setId(mWindLevelView.hashCode());
		params = ActivityUtils.getLayoutParams(WindLevelView.getLayoutWidth(),
				WindLevelView.getLayoutHeight());
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		mCenterMiddle.addView(mWindLevelView, params);
		
		mTemperatureText = new TextView(mContext);
		mTemperatureText.setGravity(Gravity.CENTER);
		mTemperatureText.setTextColor(Color.WHITE);
		mTemperatureText.setTextSize((mContext.getResources().getDimension(R.dimen.main_text_air_condition)));
		mTemperatureText.setId(mTemperatureText.hashCode());
		params = ActivityUtils.getLayoutParams(WindLevelView.getLayoutWidth(),
				WindLevelView.getLayoutHeight());
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		mCenterMiddle.addView(mTemperatureText, params);
		
		mBtnReduce = new ImageView(mContext);
		mBtnReduce.setId(mBtnReduce.hashCode());
		mBtnReduce.setBackgroundResource(R.drawable.main_air_add_reduce_background);
		mBtnReduce.setImageResource(R.drawable.main_air_condition_btn_reduce);
		params = ActivityUtils.getLayoutParams(64, 64);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.leftMargin = ActivityUtils.scaleX(130);
		params.bottomMargin = ActivityUtils.scaleY(20);
		mCenterMiddle.addView(mBtnReduce, params);
		
		mBtnAdd = new ImageView(mContext);
		mBtnAdd.setId(mBtnAdd.hashCode());
		mBtnAdd.setBackgroundResource(R.drawable.main_air_add_reduce_background);
		mBtnAdd.setImageResource(R.drawable.main_air_condition_btn_add);
		params = ActivityUtils.getLayoutParams(64, 64);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mBtnReduce.getId());
		params.leftMargin = ActivityUtils.scaleX(100);
		params.bottomMargin = ActivityUtils.scaleY(20);
		mCenterMiddle.addView(mBtnAdd, params);
	}
	
	private ImageView mBtnVolumeMute;
	private SeekBar mVolumeSeekBar;
	private ImageView mVolumeSeekBarBg;
	private void initBottomSide(){
		mBtnVolumeMute = new ImageView(mContext);
		mBtnVolumeMute.setId(mBtnVolumeMute.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(81, 71);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.leftMargin = ActivityUtils.scaleX(240);
		params.bottomMargin = ActivityUtils.scaleY(45);
		mContentLayout.addView(mBtnVolumeMute, params);
		
		
		mVolumeSeekBarBg = new ImageView(mContext);
		mVolumeSeekBarBg.setId(mVolumeSeekBarBg.hashCode());
		mVolumeSeekBarBg.setBackgroundResource(R.drawable.main_volume_progress_background);
		params = ActivityUtils.getLayoutParams(657, 35);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.leftMargin = ActivityUtils.scaleX(330);
		params.bottomMargin = ActivityUtils.scaleY(60);
		mContentLayout.addView(mVolumeSeekBarBg, params);
		
		mVolumeSeekBar = new SeekBar(mContext);
		mVolumeSeekBar.setId(mBtnVolumeMute.hashCode());
		Drawable drawable = mContext.getResources().getDrawable(
				R.drawable.main_volume_progress_drawable);
		ClipDrawable progressDrawable = new ClipDrawable(drawable,
				Gravity.LEFT, ClipDrawable.HORIZONTAL);
		progressDrawable.setBounds(0, 0, ActivityUtils.scaleX(657), ActivityUtils.scaleY(35));
		mVolumeSeekBar.setProgressDrawable(progressDrawable);
		
		Drawable thumbDrawable= mContext.getResources()
				.getDrawable(R.drawable.main_volume_progress_thumb);	
		thumbDrawable.setBounds(0, 0, ActivityUtils.scaleX(45), ActivityUtils.scaleY(35));
		mVolumeSeekBar.setThumb(thumbDrawable);	
		mVolumeSeekBar.setThumbOffset(5);

		mVolumeSeekBar.setMax(100);
		mVolumeSeekBar.setProgress(45);
		params = ActivityUtils.getLayoutParams(657, 65);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.leftMargin = ActivityUtils.scaleX(330);
		params.bottomMargin = ActivityUtils.scaleY(45);
		mContentLayout.addView(mVolumeSeekBar, params);
		
		mVolumeSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar arg0) {
				int progress = mVolumeSeekBar.getProgress();
				mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 
						progress,  
						AudioManager.FLAG_PLAY_SOUND);
			}
			public void onStartTrackingTouch(SeekBar arg0) {
			}
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			}
		});
	}
	
	private AppGroupManager mAppGroupManager;
	private GridView mAppGridView;
	private void initAppArea(){
		mAppGridView = (GridView)LayoutInflater.from(mContext).inflate(R.layout.app_gridview, null);
		mAppGridView.setId(mAppGridView.hashCode());
		mAppGridView.setBackgroundResource(R.drawable.main_app_background);
		LayoutParams params = ActivityUtils.getLayoutParams(825, 507);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		mCenterArea.addView(mAppGridView, params);
		mAppGridView.setVisibility(View.GONE);
		
		mAppGroupManager = new AppGroupManager(mContext, mAppGridView);
	}
	
	
	public void updateStatus(int event, Object obj){
		switch (event) {
		case IControl.EVENT_SHOW_CAR_CONTROL:
			break;
		case IControl.EVENT_VOLUME_CHANGE:
			break;
		case IControl.EVENT_VOLIME_MUTE:
			break;
			
		case IControl.EVENT_AC_STATUS_CHANGE:
			updateAirConditionOnOff();
			break;
		case IControl.EVENT_AC_MODEL_CHANGE:
			updateAcModel();
			break;
		case IControl.EVENT_AC_RUN_STYLE_CHANGE:
			updateAcRunStyle();
			break;
		case IControl.EVENT_AC_WIND_STYLE_CHANGE:
			updateAcWindStyle();
			break;
			
			
		case IControl.EVENT_AC_TEMPERATURE_CHANGE:
			updateAcTemperature();
			break;
		case IControl.EVENT_AC_WIND_STAGE_CHANGE:
			updateAcWindStage();
			break;
		default:
			break;
		}
	}
	
	private void updateAcRunStyle(){
		int style = CarStatusHelper.getInstance().getAirConditionRunStyle();
		mLeftAction1.setImageResource(style == CarStatusHelper.AC_RUN_STYLE_AC ?
				R.drawable.main_air_ac_select : R.drawable.main_air_ac_normal);
		
		mLeftAction2.setImageResource(style == CarStatusHelper.AC_RUN_STYLE_AUTO ?
				R.drawable.main_air_auto_select : R.drawable.main_air_auto_normal);
		
		mLeftAction3.setImageResource(style == CarStatusHelper.AC_RUN_STYLE_ECO ?
				R.drawable.main_air_eco_select : R.drawable.main_air_eco_normal);
		
		mLeftAction4.setImageResource(style == CarStatusHelper.AC_RUN_STYLE_SINGLE ?
				R.drawable.main_air_single_select : R.drawable.main_air_single_normal);
	}
	
	private void updateAcModel(){
		int model = CarStatusHelper.getInstance().getAirConditionModel();
		mRightAction1.setImageResource(model == CarStatusHelper.AC_MODEL_HOT ? 
				R.drawable.main_air_hot_select : R.drawable.main_air_hot_normal);
		
		mRightAction2.setImageResource(model == CarStatusHelper.AC_MODEL_COLD ?
				R.drawable.main_air_cold_select : R.drawable.main_air_cold_normal);
		
		mRightAction3.setImageResource(model == CarStatusHelper.AC_MODEL_WIND ?
				R.drawable.main_air_wind_select : R.drawable.main_air_wind_normal);
		
		mTemperatureText.setVisibility(model == CarStatusHelper.AC_MODEL_WIND ? 
				View.GONE : View.VISIBLE);
		mWindLevelView.setVisibility(model == CarStatusHelper.AC_MODEL_WIND ? 
				View.VISIBLE : View.GONE);
	}
	
	private void updateAcWindStyle(){
		int style = CarStatusHelper.getInstance().getAirConditionWindStyle();
		mBottomAction1.setImageResource(style == CarStatusHelper.AC_WIND_STYLE_FACE ?
				R.drawable.main_air_wind_mode_face_select : 
				R.drawable.main_air_wind_mode_face_normal);
		
		mBottomAction2.setImageResource(style == CarStatusHelper.AC_WIND_STYLE_FACE_FOOT ?
				R.drawable.main_air_wind_mode_face_foot_select : 
				R.drawable.main_air_wind_mode_face_foot_normal);
		
		mBottomAction3.setImageResource(style == CarStatusHelper.AC_WIND_STYLE_FOOT ?
				R.drawable.main_air_wind_mode_foot_select :
				R.drawable.main_air_wind_mode_foot_normal);
		
		mBottomAction4.setImageResource(style == CarStatusHelper.AC_WIND_STYLE_FOOT_DEFROST ?
				R.drawable.main_air_wind_mode_foot_defrost_select : 
				R.drawable.main_air_wind_mode_foot_defrost_normal);
		
		mBottomAction5.setImageResource(style == CarStatusHelper.AC_WIND_STYLE_DEFROST ?
				R.drawable.main_air_wind_mode_defrost_select :
				R.drawable.main_air_wind_mode_defrost_normal);
	}
	
	private void updateAirConditionOnOff(){
		boolean open = CarStatusHelper.getInstance().getAirConditionStatus();
		mBottomAction6.setImageResource(open? R.drawable.main_air_off : R.drawable.main_air_on);
		
		mCenterMiddle.setVisibility(open? View.VISIBLE: View.GONE);
		mBtnAdd.setEnabled(open);
		mBtnReduce.setEnabled(open);
		
		mLeftAction1.setEnabled(open);
		mLeftAction2.setEnabled(open);
		mLeftAction3.setEnabled(open);
		mLeftAction4.setEnabled(open);
		
		mRightAction1.setEnabled(open);
		mRightAction2.setEnabled(open);
		mRightAction3.setEnabled(open);
		
		mBottomAction1.setEnabled(open);
		mBottomAction2.setEnabled(open);
		mBottomAction3.setEnabled(open);
		mBottomAction4.setEnabled(open);
		mBottomAction5.setEnabled(open);
	}
	
	private void updateAcWindStage(){
		int stage = CarStatusHelper.getInstance().getAirConditionWindStage();
		switch (stage) {
		case CarStatusHelper.AC_WIND_STAGE_1:
			mWindLevelView.setPercent(0.8f);
			break;
		case CarStatusHelper.AC_WIND_STAGE_2:
			mWindLevelView.setPercent(0.7f);
			break;
		case CarStatusHelper.AC_WIND_STAGE_3:
			mWindLevelView.setPercent(0.58f);
			break;
		case CarStatusHelper.AC_WIND_STAGE_4:
			mWindLevelView.setPercent(0.45f);
			break;
		case CarStatusHelper.AC_WIND_STAGE_5:
			mWindLevelView.setPercent(0.3f);
			break;
		case CarStatusHelper.AC_WIND_STAGE_6:
			mWindLevelView.setPercent(0.2f);
			break;
		case CarStatusHelper.AC_WIND_STAGE_7:
			mWindLevelView.setPercent(0.0f);
			break;
		default:
			break;
		}
	}
	
	private void updateAcTemperature(){
		int temperature = CarStatusHelper.getInstance().getAirConditionTemperature();
		mTemperatureText.setText("" + temperature + " .¡æ");
	}
	
	private void updateVolumeMute(){
		int mode = mAudioManager.getRingerMode();
		if (mode == AudioManager.RINGER_MODE_NORMAL){
			mBtnVolumeMute.setImageResource(R.drawable.main_btn_mute_normal);
		}else{
			mBtnVolumeMute.setImageResource(R.drawable.main_btn_mute_press);
		}
	}
	
	private void updateVolumeProgress(){
		int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		mVolumeSeekBar.setMax(max);
		mVolumeSeekBar.setProgress(current);
	}
}