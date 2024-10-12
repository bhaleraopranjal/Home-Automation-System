package com.pranjal.house;

import java.util.*;

public class MainApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Room> rooms = new ArrayList<>();
		List<Device> devices = new ArrayList<>();
		
		System.out.println("\n+-----------------------------------------------------------------------+"
						 + "\n|                            Home Automation                            |"
						 + "\n+-----------------------------------------------------------------------+");
					
		//add rooms in Room list
		rooms.add(new Room(1, "Living Area"));
		rooms.add(new Room(2, "Kitchen"));
		rooms.add(new Room(3, "Bedroom"));
		rooms.add(new Room(4, "Washroom"));
		
		//add devices in Device list
		devices.add(new AirConditioner(1, "Air Condtioner"));   //to make instance of TemperatureControl Interface
		devices.add(new Device(2, "Heater"));
		devices.add(new Device(3, "Refrigerator"));
		devices.add(new Television(4, "Television"));
		devices.add(new Device(5, "Light"));
		devices.add(new Device(6, "Shower"));
		devices.add(new Device(7, "Mixer"));
		devices.add(new Device(8, "Microwave"));
		devices.add(new Device(9, "Hair Dryer"));
		
		HouseOperation.currentStatusHouse(rooms,devices);
		int ch = 0;
		while(ch != 6)
		{
			System.out.print("\n\t\t1.Add Room \n\t\t2.Add Device \n\t\t3.Add device in Rooms "
					+ "\n\t\t4.Device Operation \n\t\t5.Device Turn on time duration "
					+ "\n\t\t6.Exit \nEnter your choice: ");
			ch = sc.nextInt();
			
			switch(ch)
			{
				case 1:
					HouseOperation.addRoom(rooms);
					break;
					
				case 2:
					HouseOperation.addDevice(devices);
					break;
					
				case 3:
					HouseOperation.addDevicesInRoom(devices,rooms);
					break;
					
				case 4:
					HouseOperation.operateDevice(devices,rooms);
					break;
					
				case 5:
					HouseOperation.checkDeviceOnTime(devices, rooms);
					break;
					
				case 6:
					System.out.println("\n-------------Thank You!!-------------");
					break;
					
				default:
					System.out.println("\nInvalid choice");
					break;
			}		
		}		
		sc.close();
	}
}
