package com.zjxd.vehichectr.activity.main.control;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.zjxd.vehichectr.activity.main.ui.ContentArea;


public class MainControl implements IControl{
	public static final String TAG = "MainControl ";
	private Context mContext; 
	public ContentArea mContentArea;
	public MainControl(Context context){
		mContext = context;
		mContentArea = new ContentArea(context, this);
	}
	
    public void onBackPressed() {
    	if (mContentArea.isContentSubVisible()){
    		mContentArea.showSubLayout(false);
    	}else{
    		((Activity)mContext).finish();
    	}
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (mContentArea != null){
    		mContentArea.onActivityResult(requestCode, resultCode, data);
    	}
    }
	
	@Override
	public void actionMainContentEvent(int event, Object obj) {
		switch (event) {	
		case IControl.EVENT_AC_STATUS_CHANGE:
			boolean open = CarStatusHelper.getInstance().getAirConditionStatus();
			obj = open;
			break;
		case IControl.EVENT_AC_MODEL_CHANGE:
			int model = CarStatusHelper.getInstance().getAirConditionModel();
			obj = model;
			break;
			
		case IControl.EVENT_AC_RUN_STYLE_CHANGE:
			int runStyle = CarStatusHelper.getInstance().getAirConditionRunStyle();
			obj = runStyle;
			break;
		case IControl.EVENT_AC_WIND_STYLE_CHANGE:
			int windStyle = CarStatusHelper.getInstance().getAirConditionWindStyle();
			obj = windStyle;
			break;
			
		case IControl.EVENT_AC_TEMPERATURE_CHANGE:
			int temperature = CarStatusHelper.getInstance().getAirConditionTemperature();
			obj = temperature;
			break;
		case IControl.EVENT_AC_WIND_STAGE_CHANGE:
			int windStage = CarStatusHelper.getInstance().getAirConditionWindStage();
			obj = windStage;
			break;
		default:
			break;
		}
		
	}
	
	@Override
	public void actionSubContentEvent(int event, Object obj) {
		switch (event) {
		case IControl.EVENT_FAR_LIGHT_LAMP_CHANGE:{ //远光灯
			boolean open = CarStatusHelper.getInstance().getLampFarlightStatus();
		}
			break;
		case IControl.EVENT_NEAR_LIGHT_LAMP_CHANGE:{//近光灯
			boolean open = CarStatusHelper.getInstance().getLampNearlightStatus();
		}
			break;
		case IControl.EVENT_FRONT_FLOG_LAMP_CHANGE:{//前雾灯
			boolean open = CarStatusHelper.getInstance().getLampFrontflogStatus();
		}
			break;
		case IControl.EVENT_BEHIND_FLOG_LAMP_CHANGE:{//后雾灯
			boolean open = CarStatusHelper.getInstance().getLampBehindflogStatus();
		}
			break;
		case IControl.EVENT_FRONT_CAR_DOOR_CHANGE:{//前舱门
			boolean open = CarStatusHelper.getInstance().getCarFrontDoorStatus();
		}
			break;
		case IControl.EVENT_BEHIND_CAR_DOOR_CHANGE:{//后舱门
			boolean open = CarStatusHelper.getInstance().getCarBehindDoorStatus();
		}
			break;
		
			
			
		case IControl.EVENT_FRONT_RIGHT_DOOR_CAHNEG:{//前右车门
			boolean open = CarStatusHelper.getInstance().getCarFrontDoorRightStatus();
		}
			break;
		case IControl.EVENT_FRONT_LEFT_DOOR_CAHNEG:{//前左车门
			boolean open = CarStatusHelper.getInstance().getCarFrontDoorLeftStatus();
		}
			break;
		case IControl.EVENT_BEHIND_RIGHT_DOOR_CAHNEG:{//后右车门
			boolean open = CarStatusHelper.getInstance().getCarBehindDoorRightStatus();
		}
			break;
		case IControl.EVENT_BEHIND_LEFT_DOOR_CAHNEG:{//后左车门
			boolean open = CarStatusHelper.getInstance().getCarBehindDoorLeftStatus();
		}
			break;
			
			
		case IControl.EVENT_FRONT_RIGHT_WINDOW_CAHNEG:{//前右车窗
			float percent = CarStatusHelper.getInstance().getFRightWindowPercent();
		}
			break;
		case IControl.EVENT_FRONT_LEFT_WINDOW_CAHNEG:{//前左车窗
			float percent = CarStatusHelper.getInstance().getFLeftWindowPercent();
		}
			break;
		case IControl.EVENT_BEHIND_RIGHT_WINDOW_CAHNEG:{//后右车窗
			float percent = CarStatusHelper.getInstance().getBRightWindowPercent();
		}
			break;
		case IControl.EVENT_BEHIND_LEFT_WINDOW_CAHNEG:{//后左车窗
			float percent = CarStatusHelper.getInstance().getBLeftWindowPercent();
		}
			break;
		case IControl.EVENT_RAIN_WIPER_CHANGE:{//雨刮档位：0，关；1，慢挡；2，中档；3，快档。
			int stage = CarStatusHelper.getInstance().getCarRainWiperStage();
		}
			break;
			
			
		case IControl.EVENT_RIGHT_ROTATE_LAMP_CHANGE:{//右转灯
			boolean open = CarStatusHelper.getInstance().getCarLampRotateRightStatus();
		}
			break;
		case IControl.EVENT_LEFT_ROTATE_LAMP_CHANGE:{//左转灯
			boolean open = CarStatusHelper.getInstance().getCarLampRotateLeftStatus();
		}
			break;

		default:
			break;
		}
	}


	@Override
	public void updateMainContentStatus(int event, Object obj) {
		switch (event) {
		case IControl.EVENT_SHOW_CAR_CONTROL:
			mContentArea.showSubLayout(true);
			break;
		case IControl.EVENT_VOLUME_CHANGE:
			mContentArea.updateMainStatus(event, obj);
			break;
			
			
		case IControl.EVENT_AC_STATUS_CHANGE:
			if (obj != null && obj instanceof Boolean){
				CarStatusHelper.getInstance().setAirConditionStatus((Boolean) obj);
			}
			mContentArea.updateMainStatus(event, obj);
			break;
		case IControl.EVENT_AC_MODEL_CHANGE:
			if (obj != null && obj instanceof Integer){
				CarStatusHelper.getInstance().setAirConditionModel((Integer) obj);
			}
			mContentArea.updateMainStatus(event, obj);
			break;
			
		case IControl.EVENT_AC_RUN_STYLE_CHANGE:
			if (obj != null && obj instanceof Integer){
				CarStatusHelper.getInstance().setAirConditionRunStyle((Integer) obj);
			}
			mContentArea.updateMainStatus(event, obj);
			break;
		case IControl.EVENT_AC_WIND_STYLE_CHANGE:
			if (obj != null && obj instanceof Integer){
				CarStatusHelper.getInstance().setAirConditionWindStyle((Integer) obj);
			}
			mContentArea.updateMainStatus(event, obj);
			break;
			
		case IControl.EVENT_AC_TEMPERATURE_CHANGE:
			if (obj != null && obj instanceof Integer){
				CarStatusHelper.getInstance().setAirConditionTemperature((Integer) obj);
			}
			mContentArea.updateMainStatus(event, obj);
			break;
		case IControl.EVENT_AC_WIND_STAGE_CHANGE:
			if (obj != null && obj instanceof Integer){
				CarStatusHelper.getInstance().setAirConditionWindStage((Integer) obj);
			}
			mContentArea.updateMainStatus(event, obj);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void updateSubContentStatus(int event, Object obj) {
		switch (event) {
		case IControl.EVENT_GO_MAIN_PAGE:
		case IControl.EVENT_CAR_WINDOW_OPEN:
		case IControl.EVENT_RAIN_WIPER_OPEN:
			mContentArea.updateSubStatus(event, obj);
			break;
			
		//car lamp
		case IControl.EVENT_FAR_LIGHT_LAMP_CHANGE:
			if (obj != null && obj instanceof Boolean){
				CarStatusHelper.getInstance().setLampFarlightStatus((Boolean) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
		case IControl.EVENT_NEAR_LIGHT_LAMP_CHANGE:
			if (obj != null && obj instanceof Boolean){
				CarStatusHelper.getInstance().setLampNearlightStatus((Boolean) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
		case IControl.EVENT_FRONT_FLOG_LAMP_CHANGE:
			if (obj != null && obj instanceof Boolean){
				CarStatusHelper.getInstance().setLampFrontflogStatus((Boolean) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
		case IControl.EVENT_BEHIND_FLOG_LAMP_CHANGE:
			if (obj != null && obj instanceof Boolean){
				CarStatusHelper.getInstance().setLampBehindflogStatus((Boolean) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
			
		//car door
		case IControl.EVENT_FRONT_CAR_DOOR_CHANGE:
			if (obj != null && obj instanceof Boolean){
				CarStatusHelper.getInstance().setCarFrontDoorStatus((Boolean) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
		case IControl.EVENT_BEHIND_CAR_DOOR_CHANGE:
			if (obj != null && obj instanceof Boolean){
				CarStatusHelper.getInstance().setCarBehindDoorStatus((Boolean) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
			
			
		case IControl.EVENT_FRONT_RIGHT_DOOR_CAHNEG:
			if (obj != null && obj instanceof Boolean){
				CarStatusHelper.getInstance().setCarFrontDoorRightStatus((Boolean) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
		case IControl.EVENT_FRONT_LEFT_DOOR_CAHNEG:
			if (obj != null && obj instanceof Boolean){
				CarStatusHelper.getInstance().setCarFrontDoorLeftStatus((Boolean) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
		case IControl.EVENT_BEHIND_RIGHT_DOOR_CAHNEG:
			if (obj != null && obj instanceof Boolean){
				CarStatusHelper.getInstance().setCarBehindDoorRightStatus((Boolean) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
		case IControl.EVENT_BEHIND_LEFT_DOOR_CAHNEG:
			if (obj != null && obj instanceof Boolean){
				CarStatusHelper.getInstance().setCarBehindDoorLeftStatus((Boolean) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
			
		//car window
		case IControl.EVENT_FRONT_RIGHT_WINDOW_CAHNEG:
			if (obj != null && obj instanceof Float){
				CarStatusHelper.getInstance().setFRightWindowPercent((Float) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
		case IControl.EVENT_FRONT_LEFT_WINDOW_CAHNEG:
			if (obj != null && obj instanceof Float){
				CarStatusHelper.getInstance().setFLeftWindowPercent((Float) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
		case IControl.EVENT_BEHIND_RIGHT_WINDOW_CAHNEG:
			if (obj != null && obj instanceof Float){
				CarStatusHelper.getInstance().setBRightWindowPercent((Float) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
		case IControl.EVENT_BEHIND_LEFT_WINDOW_CAHNEG:
			if (obj != null && obj instanceof Float){
				CarStatusHelper.getInstance().setBLeftWindowPercent((Float) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
			
		//rain wiper
		case IControl.EVENT_RAIN_WIPER_CHANGE:
			if (obj != null && obj instanceof Integer){
				CarStatusHelper.getInstance().setCarRainWiperStage((Integer) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
			
		//rotate lamp
		case IControl.EVENT_RIGHT_ROTATE_LAMP_CHANGE:
			if (obj != null && obj instanceof Boolean){
				CarStatusHelper.getInstance().setCarLampRotateRightStatus((Boolean) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;
		case IControl.EVENT_LEFT_ROTATE_LAMP_CHANGE:
			if (obj != null && obj instanceof Boolean){
				CarStatusHelper.getInstance().setCarLampRotateLeftStatus((Boolean) obj);
			}
			mContentArea.updateSubStatus(event, obj);
			break;

		default:
			break;
		}
	}
}