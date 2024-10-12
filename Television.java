package com.pranjal.house;

public class Television extends Device implements VolumeControl{

	int volume = 5;
	
	public Television(int deviceId, String deviceName) {
		super(deviceId, deviceName);
	}

	public void increaseVolume() {
        if (volume < 20) 
        {
            volume++;
            System.out.println("Volume increased to " + volume);
        } 
        else 
        {
            System.out.println("Volume is already at maximum.");
        }
	}

	public void decreaseVolume() {
        if (volume > 0) 
        {
            volume--;
            System.out.println("Volume decreased to " + volume);
        } 
        else 
        {
            System.out.println("Volume is already at minimum.");
        }
	}

	public int getCurrentVolume() {
		return volume;
	}
}
