package com.pranjal.house;

import java.time.LocalDateTime;
import java.time.Duration;

public class Device implements deviceControl{
	
	int deviceId;
	String deviceName;
	private boolean isOn;
    private LocalDateTime turnOnTime;
    private LocalDateTime turnOffTime;
    
	public Device( int deviceId, String deviceName) {
		super();
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.isOn = false;             //initially all devices are off
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Override
	public String toString() {
	    return String.format("%-10d %-20s", 
	        this.deviceId,
	        this.deviceName);
	}

	public void turnOn() {
	    if (!isOn) 
	    {
	        isOn = true;
	        turnOnTime = LocalDateTime.now();
	        System.out.println(deviceName + " is now ON.");
	    } 
	    else 
	    {
	        System.out.println(deviceName + " is already ON.");
	    }
	}
	
	public void turnOff() {
	    if (isOn) 
	    {
	        isOn = false;
	        turnOffTime = LocalDateTime.now();
	        System.out.println(deviceName + " is now OFF.");
	    } 
	    else 
	    {
	        System.out.println(deviceName + " is already OFF.");
	    }
	}
	
	public Duration getOnDuration() {
		 if (isOn) 
		 {
			 return Duration.between(turnOnTime, LocalDateTime.now());
	     } 
		 else if (turnOffTime != null)
		 {
	         return Duration.between(turnOnTime, turnOffTime);
	     } 
		 else 
		 {
	         return Duration.ZERO;
	     }
	}

	public boolean isOn() {
		return isOn;
	}

}
