package com.zjxd.vehichectr.activity.main.control;


public class CarStatusHelper{
	private static CarStatusHelper mInstance;
	public static CarStatusHelper getInstance(){
		if (mInstance == null){
			mInstance = new CarStatusHelper();
		}
		return mInstance;
	}
	
	private CarStatusHelper(){
		
	}
	
	//================ FAR/NEAR LAMP ==============
	private boolean mLampFarlightOpen = false;
	private boolean mLampNearlightOpen = false;
	public void setLampFarlightStatus(boolean open){
		mLampFarlightOpen = open;
	}
	
	public boolean getLampFarlightStatus(){
		return mLampFarlightOpen;
	}

	public void setLampNearlightStatus(boolean open){
		mLampNearlightOpen = open;
	}
	
	public boolean getLampNearlightStatus(){
		return mLampNearlightOpen;
	}
	
	//============== FLOG LAMP ==================
	private boolean mLampFrontflogOpen = false;
	private boolean mLampBehindflogOpen = false;
	public void setLampFrontflogStatus(boolean open){
		mLampFrontflogOpen = open;
	}
	
	public boolean getLampFrontflogStatus(){
		return mLampFrontflogOpen;
	}
	
	public void setLampBehindflogStatus(boolean open){
		mLampBehindflogOpen = open;
	}
	
	public boolean getLampBehindflogStatus(){
		return mLampBehindflogOpen;
	}
	
	//=============== FRONT/BEHIND DOOR============
	private boolean mCarFrontDoorOpen = false;
	private boolean mCarBehindDoorOpen = false;
	public void setCarFrontDoorStatus(boolean open){
		mCarFrontDoorOpen = open;
	}
	
	public boolean getCarFrontDoorStatus(){
		return mCarFrontDoorOpen;
	}
	
	public void setCarBehindDoorStatus(boolean open){
		mCarBehindDoorOpen = open;
	}
	
	public boolean getCarBehindDoorStatus(){
		return mCarBehindDoorOpen;
	}
	
	//================ RIGHT/LEFT DOOR ==============
	private boolean mCarFrontDoorRightOpen = false;
	private boolean mCarFrontDoorLeftOpen = false;
	private boolean mCarBehindDoorRightOpen = false;
	private boolean mCarBehindDoorLeftOpen = false;
	public void setCarFrontDoorRightStatus(boolean open){
		mCarFrontDoorRightOpen = open;
	}
	
	public boolean getCarFrontDoorRightStatus(){
		return mCarFrontDoorRightOpen;
	}
	
	public void setCarFrontDoorLeftStatus(boolean open){
		mCarFrontDoorLeftOpen = open;
	}
	
	public boolean getCarFrontDoorLeftStatus(){
		return mCarFrontDoorLeftOpen;
	}
	
	public void setCarBehindDoorRightStatus(boolean open){
		mCarBehindDoorRightOpen = open;
	}
	
	public boolean getCarBehindDoorRightStatus(){
		return mCarBehindDoorRightOpen;
	}
	
	public void setCarBehindDoorLeftStatus(boolean open){
		mCarBehindDoorLeftOpen = open;
	}
	
	public boolean getCarBehindDoorLeftStatus(){
		return mCarBehindDoorLeftOpen;
	}
	
	//================================
	private boolean mCarWindowOpen = false;
	public void setCarWindowStatus(boolean open){
		mCarWindowOpen = open;
	}
	
	public boolean getCarWindowStatus(){
		return mCarWindowOpen;
	}
	
	private float mFRightWindowPercent = 0;
	private float mFLeftWindowPercent = 0;
	private float mBRightWindowPercent = 0;
	private float mBLeftWindowPercent = 0;
	public void setFRightWindowPercent(float percent){
		mFRightWindowPercent = percent;
	}
	
	public float getFRightWindowPercent(){
		return mFRightWindowPercent;
	}
	
	public void setFLeftWindowPercent(float percent){
		mFLeftWindowPercent = percent;
	}
	
	public float getFLeftWindowPercent(){
		return mFLeftWindowPercent;
	}
	
	public void setBRightWindowPercent(float percent){
		mBRightWindowPercent = percent;
	}
	
	public float getBRightWindowPercent(){
		return mBRightWindowPercent;
	}
	
	public void setBLeftWindowPercent(float percent){
		mBLeftWindowPercent = percent;
	}
	
	public float getBLeftWindowPercent(){
		return mBLeftWindowPercent;
	}
	
	//================RIGHT/LEFT ROTATE LAMP ==========
	private boolean mCarLampRotateRightOpen = false;
	private boolean mCarLampRotateLeftOpen = false;
	public void setCarLampRotateRightStatus(boolean open){
		mCarLampRotateRightOpen = open;
	}
	
	public boolean getCarLampRotateRightStatus(){
		return mCarLampRotateRightOpen;
	}
	
	public void setCarLampRotateLeftStatus(boolean open){
		mCarLampRotateLeftOpen = open;
	}
	
	public boolean getCarLampRotateLeftStatus(){
		return mCarLampRotateLeftOpen;
	}
	
	//============== CAR RAIN WIPER =============
	private int mCarRainWiperStage = RAIN_WIPER_CLOSE;
	private boolean mCarRainWiperOpen = false;
	public static final int RAIN_WIPER_CLOSE = 0;
	public static final int RAIN_WIPER_SLOW = 1;
	public static final int RAIN_WIPER_MIDDLE = 2;
	public static final int RAIN_WIPER_FAST = 3;
	public void setCarRainWiperStage(int stage){
		mCarRainWiperStage = stage;
	}
	
	public int getCarRainWiperStage(){
		return mCarRainWiperStage;
	}
	
	public void setCarRainWiperStatus(boolean open){
		mCarRainWiperOpen = open;
	}
	
	public boolean getCarRainWiperStatus(){
		return mCarRainWiperOpen;
	}
	
	//============== Air Conditioning ===========
	private int mAcModeL = AC_MODEL_WIND;
	//model
	public static final int AC_MODEL_HOT = 0;
	public static final int AC_MODEL_COLD = 1;
	public static final int AC_MODEL_WIND = 2;
	public void setAirConditionModel(int model){
		mAcModeL = model;
	}
	
	public int getAirConditionModel(){
		return mAcModeL;
	}
	
	
	//run style
	private int mAcRunStyle = AC_RUN_STYLE_AC;
	public static final int AC_RUN_STYLE_AC = 0;
	public static final int AC_RUN_STYLE_AUTO = 1;
	public static final int AC_RUN_STYLE_ECO = 2;
	public static final int AC_RUN_STYLE_SINGLE = 3;
	public void setAirConditionRunStyle(int style){
		mAcRunStyle = style;
	}
	
	public int getAirConditionRunStyle(){
		return mAcRunStyle;
	}
	
	
	//wind style
	private int mAcWindStyle = AC_WIND_STYLE_FACE;
	public static final int AC_WIND_STYLE_FACE = 0;
	public static final int AC_WIND_STYLE_FACE_FOOT = 1;
	public static final int AC_WIND_STYLE_FOOT = 2;
	public static final int AC_WIND_STYLE_FOOT_DEFROST = 3;
	public static final int AC_WIND_STYLE_DEFROST= 4;
	public void setAirConditionWindStyle(int style){
		mAcWindStyle = style;
	}
	
	public int getAirConditionWindStyle(){
		return mAcWindStyle;
	}
	
	//wind stage
	private int mAcWindStage = AC_WIND_STAGE_1;
	public static final int AC_WIND_STAGE_1 = 1;
	public static final int AC_WIND_STAGE_2 = 2;
	public static final int AC_WIND_STAGE_3 = 3;
	public static final int AC_WIND_STAGE_4 = 4;
	public static final int AC_WIND_STAGE_5 = 5;
	public static final int AC_WIND_STAGE_6 = 6;
	public static final int AC_WIND_STAGE_7 = 7;
	public void setAirConditionWindStage(int stage){
		if (stage < AC_WIND_STAGE_1){
			mAcWindStage = AC_WIND_STAGE_1;
		}else if (stage > AC_WIND_STAGE_7){
			mAcWindStage = AC_WIND_STAGE_7;
		}else{
			mAcWindStage = stage;
		}
	}
	
	public int getAirConditionWindStage(){
		return mAcWindStage;
	}


	
	//AC enable/disable
	public boolean mAirConditionOpen = false;
	public void setAirConditionStatus(boolean open){
		mAirConditionOpen = open;
	}
	
	public boolean getAirConditionStatus(){
		return mAirConditionOpen;
	}
	
	//AC temperature
	public int mAirConditionTemperature = 24;
	public void setAirConditionTemperature(int temperature){
		if (temperature < 17){
			mAirConditionTemperature = 17;
		}else if (temperature > 34){
			mAirConditionTemperature = 34;
		}else{
			mAirConditionTemperature = temperature;
		}
	}
	
	public int getAirConditionTemperature(){
		return mAirConditionTemperature;
	}
}