package com.pranjal.house;

public class AirConditioner extends Device implements TemperatureControl{

	int temperature = 18;
	
	public AirConditioner(int deviceId, String deviceName) {
		super(deviceId, deviceName);
	}

	public void increaseTemperature() {
	    temperature++;
	    System.out.println("Temperature increased to " + temperature + "°C");
	}

	public void decreaseTemperature() {
	    temperature--;
	    System.out.println("Temperature decreased to " + temperature + "°C");
	}

	public Object getCurrentTemperature() {
		return temperature;
	}
	
}
