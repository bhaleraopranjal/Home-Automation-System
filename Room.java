package com.pranjal.house;

import java.util.*;

public class Room {
	int roomNo;
	String roomName;
	List<Device> devices;
	
	public Room(int roomNo, String roomName) {
		super();
		this.roomNo = roomNo;
		this.roomName = roomName;
		this.devices = new ArrayList<>();
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	//to add devices in room
	public void addDevice(Device selectedDevice) {
		devices.add(selectedDevice);		
	}

	public List<Device> getDevices() {
		return devices;
	}

	public String toString() {
		return "Room No : " + this.roomNo +
				"\nRoom Name: " + this.roomName + 
				"\nDevices: " + devices + "\n";				
	}	
	
}
