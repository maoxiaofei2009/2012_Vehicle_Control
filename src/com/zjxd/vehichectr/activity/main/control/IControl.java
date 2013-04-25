package com.zjxd.vehichectr.activity.main.control;


public interface IControl {
	public static final String TAG = "MainControl ";
	public static final int EVENT_SHOW_CAR_CONTROL = 100;
	public static final int EVENT_VOLUME_CHANGE = 101;
	public static final int EVENT_VOLIME_MUTE = 102;
	
	public static final int EVENT_AC_STATUS_CHANGE = 112;
	public static final int EVENT_AC_MODEL_CHANGE = 113;
	public static final int EVENT_AC_RUN_STYLE_CHANGE = 114;
	public static final int EVENT_AC_WIND_STYLE_CHANGE = 115;
	
	public static final int EVENT_AC_TEMPERATURE_CHANGE = 116;
	public static final int EVENT_AC_WIND_STAGE_CHANGE = 117;
	
	public static final int EVENT_CAR_WINDOW_OPEN = 1;
	public static final int EVENT_RAIN_WIPER_OPEN = 2;
	public static final int EVENT_FAR_LIGHT_LAMP_CHANGE = 3;
	public static final int EVENT_NEAR_LIGHT_LAMP_CHANGE = 4;
	public static final int EVENT_FRONT_FLOG_LAMP_CHANGE = 5;
	public static final int EVENT_BEHIND_FLOG_LAMP_CHANGE = 6;
	public static final int EVENT_FRONT_CAR_DOOR_CHANGE = 7;
	public static final int EVENT_BEHIND_CAR_DOOR_CHANGE = 8;
	public static final int EVENT_GO_MAIN_PAGE = 9;
	
	public static final int EVENT_FRONT_RIGHT_DOOR_CAHNEG =  11;
	public static final int EVENT_FRONT_LEFT_DOOR_CAHNEG =  12;
	public static final int EVENT_BEHIND_RIGHT_DOOR_CAHNEG =  13;
	public static final int EVENT_BEHIND_LEFT_DOOR_CAHNEG =  14;
	
	public static final int EVENT_FRONT_RIGHT_WINDOW_CAHNEG =  21;
	public static final int EVENT_FRONT_LEFT_WINDOW_CAHNEG =  22;
	public static final int EVENT_BEHIND_RIGHT_WINDOW_CAHNEG =  23;
	public static final int EVENT_BEHIND_LEFT_WINDOW_CAHNEG =  24;
	
	public static final int EVENT_RAIN_WIPER_CHANGE =  31;
	public static final int EVENT_RIGHT_ROTATE_LAMP_CHANGE = 32;
	public static final int EVENT_LEFT_ROTATE_LAMP_CHANGE = 33;
	
	public void updateMainContentStatus(int event, Object obj);
	public void updateSubContentStatus(int event, Object obj);
	
	public void actionMainContentEvent(int event, Object obj);
	public void actionSubContentEvent(int event, Object obj);
}