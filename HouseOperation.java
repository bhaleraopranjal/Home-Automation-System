package com.pranjal.house;

import java.time.Duration;
import java.util.*;

public class HouseOperation {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void addRoom(List<Room> rooms) {
		
		System.out.println("Enter room number: ");
		int roomNo = sc.nextInt();
		sc.nextLine();
		
		// Check if the room number is already present
		for(Room x : rooms) 
		{
			if(x.getRoomNo() == roomNo) 
			{
				System.out.println("Room number already present. "
						+ "\nPlease enter another room number.");
				return; 
			}
		}

		System.out.println("Enter room name: ");
		String roomName = sc.nextLine();
				
		Room newRoom = new Room(roomNo, roomName);
		rooms.add(newRoom);
		System.out.println("Room added successfully\n");
				
		// Display all rooms in the house
		System.out.println("\nTotal rooms in house: ");
		for(Room room : rooms) 
		{
			System.out.println(room);
		}
	}   // addRoom ends here
	
	public static void addDevice(List<Device> device) {
		
		System.out.println("Enter device id: ");
		int did = sc.nextInt();
		sc.nextLine();
		
		// Check if the device id is already present
		for(Device x : device) 
		{
			if(x.getDeviceId() == did) 
			{
				System.out.println("Device id already present. "
						+ "\nPlease enter another device id.");
				return; 
			}
		}
		
		System.out.println("Enter device name: ");
		String deviceName = sc.nextLine();
				
		Device newDevice = new Device(did, deviceName);
		device.add(newDevice);
		System.out.println("Device added successfully\n");
				
		System.out.print("\nAll devices: \n");
		for(Device x: device)
		{
			System.out.println(x);
		}
	}   // addDevice ends here

	public static void addDevicesInRoom(List<Device> devices, List<Room> rooms) {

		//display all rooms in house
		System.out.print("\nAll rooms in house: \n");
		for(Room x: rooms)
		{
			System.out.println(x);
		}
		
		System.out.println("\nEnter room no. to which you want to add device: ");
		int roomNo = sc.nextInt();
		
		Room selectedRoom = null;
		for (Room room : rooms) 
		{
			if (room.getRoomNo() == roomNo) 
			{
				selectedRoom = room;
				break;
			}
		}
		
		if(selectedRoom == null)
		{
			System.out.println("Room not found");
		}
		else
		{
			//display all devices
			System.out.print("\nAll devices list: \n");
			for(Device x: devices)
			{
				System.out.println(x);
			}
			
			System.out.println("Enter device ID you want to add to the room: ");
			int deviceId = sc.nextInt();
			sc.nextLine(); 

			Device selectedDevice = null;
			for (Device device : devices) 
			{
				if (device.getDeviceId() == deviceId) 
				{
					selectedDevice = device;
					break;
				}
			}

			if (selectedDevice == null) 
			{
				System.out.println("Device not found");
			} 
			else 
			{
		        // Create a new instance of the device for this room so all devices initial status will be off
	            Device newDevice;
	            if (selectedDevice instanceof AirConditioner) 
	            {
	                newDevice = new AirConditioner(selectedDevice.getDeviceId(), selectedDevice.getDeviceName());
	            } 
	            else if (selectedDevice instanceof Television) 
	            {
	                newDevice = new Television(selectedDevice.getDeviceId(), selectedDevice.getDeviceName());
	            }
	            else 
	            {
	                newDevice = new Device(selectedDevice.getDeviceId(), selectedDevice.getDeviceName());
	            }

	            selectedRoom.addDevice(newDevice);
	            System.out.println(newDevice.getDeviceName() + " added to the room successfully\n");
			}
		}		
	} // addDevicesInRoom ends here

	public static void operateDevice(List<Device> devices, List<Room> rooms) {

	    System.out.println("Enter room number which device you want to operate: ");
	    int roomNo = sc.nextInt();

	    Room selectedRoom = null;
	    for (Room room : rooms) {
	        if (room.getRoomNo() == roomNo) 
	        {
	            selectedRoom = room;
	            break;
	        }
	    }

	    if (selectedRoom == null) 
	    {
	        System.out.println("Room not found");
	        return;
	    }

	    List<Device> devicesOfRoom = new ArrayList<>(selectedRoom.getDevices());
	    if (devicesOfRoom.isEmpty()) 
	    {
	        System.out.println("Dear user, \n\tNo device in the room to operate"
	                + "\n\tYou can add a device to the room");
	        return;
	    }

	    final int ID_WIDTH = 10;		
	    final int NAME_WIDTH = 20;		
	    final int STATUS_WIDTH = 10;	
	    final int DURATION_WIDTH = 15;	
	    final int TEMP_VOLUME_WIDTH = 15;  

	    // Printing table header
	    System.out.printf("%-" + ID_WIDTH + "s %-" + NAME_WIDTH + "s %-" 
	    			+ STATUS_WIDTH + "s %-" + DURATION_WIDTH + "s %-" + TEMP_VOLUME_WIDTH + "s%n",
	    			"Device ID", "Device Name", "Status", "On Duration", "Temp/Volume");

	    // Printing device information in tabular format
	    for (Device device : devicesOfRoom) 
	    {
	        String status = device.isOn() ? "ON" : "OFF";
	        Duration onDuration = device.getOnDuration();
	        long durationMinutes = onDuration.toMinutes();

	        String tempVolume = "-";

	        if (device instanceof TemperatureControl) 
	        {
	            TemperatureControl tempControl = (TemperatureControl) device;
	            tempVolume = String.format("%d°C", ((AirConditioner) tempControl).getCurrentTemperature());
	        } 
	        else if (device instanceof VolumeControl) 
	        {
	            VolumeControl volControl = (VolumeControl) device;
	            tempVolume = String.format("%d", ((Television) volControl).getCurrentVolume());
	        }

	        System.out.printf("%-" + ID_WIDTH + "d %-" + NAME_WIDTH + "s %-" + STATUS_WIDTH 
	        			+ "s %-" + DURATION_WIDTH + "d %-" + TEMP_VOLUME_WIDTH + "s%n",
	            device.getDeviceId(),
	            device.getDeviceName(),
	            status,
	            durationMinutes,
	            tempVolume
	        );
	    }

	    System.out.println("Enter device ID you want to operate: ");
	    int deviceId = sc.nextInt();

	    Device selectedDevice = null;
	    for (Device device : devicesOfRoom) 
	    {
	        if (device.getDeviceId() == deviceId) 
	        {
	            selectedDevice = device;
	            break;
	        }
	    }

	    if (selectedDevice == null) 
	    {
	        System.out.println("Entered device is not present in the room");
	        return;
	    }

	    System.out.println("\n\t1. Turn on \n\t2. Turn off");

	    if (selectedDevice instanceof VolumeControl) 
	    {
	        System.out.println("\t3. Increase volume \n\t4. Decrease volume");
	    }

	    if (selectedDevice instanceof TemperatureControl) 
	    {
	        System.out.println("\t3. Increase temperature \n\t4. Decrease temperature");
	    }

	    System.out.print("Enter which operation you want to perform: ");
	    int op = sc.nextInt();

	    switch (op) 
	    {
	        case 1:
	            selectedDevice.turnOn();
	            break;

	        case 2:
	            selectedDevice.turnOff();
	            break;

	        case 3:
	            if (selectedDevice instanceof VolumeControl) 
	            {
	                if (!selectedDevice.isOn()) 
	                {
	                    System.out.println("Device is OFF. Please turn it on first.");
	                    return;
	                }
	                ((VolumeControl) selectedDevice).increaseVolume();
	            } 
	            else if (selectedDevice instanceof TemperatureControl) 
	            {
	                if (!selectedDevice.isOn()) 
	                {
	                    System.out.println("Device is OFF. Please turn it on first.");
	                    return;
	                }
	                ((TemperatureControl) selectedDevice).increaseTemperature();
	            } 
	            else 
	            {
	                System.out.println("Invalid choice: Device does not support this operation.");
	            }
	            break;

	        case 4:
	            if (selectedDevice instanceof VolumeControl) 
	            {
	                ((VolumeControl) selectedDevice).decreaseVolume();
	            } 
	            else if (selectedDevice instanceof TemperatureControl) 
	            {
	                ((TemperatureControl) selectedDevice).decreaseTemperature();
	            } 
	            else 
	            {
	                System.out.println("Invalid choice: Device does not support this operation.");
	            }
	            break;

	        default:
	            System.out.println("Invalid choice");
	            break;
	    }
	}   //operateDevice ends here

	public static void checkDeviceOnTime(List<Device> devices, List<Room> rooms) {
		
		 System.out.println("Enter room number to check device on-time:");
		    int roomNo = sc.nextInt();

		    Room selectedRoom = null;
		    for (Room room : rooms) 
		    {
		        if (room.getRoomNo() == roomNo) 
		        {
		            selectedRoom = room;
		            break;
		        }
		    }

		    if (selectedRoom == null) 
		    {
		        System.out.println("Room not found");
		    } 
		    else 
		    {
		    	if(selectedRoom.devices.isEmpty())
		    	{
		    		System.out.println("No any device in room");
		    		return;
		    	}
		    		
		        System.out.println("Devices in room " + selectedRoom.getRoomName() + ":");
		        		
	        	final int ID_WIDTH = 10;		
	     	    final int NAME_WIDTH = 20;		
	     	    final int STATUS_WIDTH = 10;
	     	    final int DURATION_WIDTH = 15;	 
	     	    final int TEMP_VOLUME_WIDTH = 15;  

	     	    // Printing table header
	     	    System.out.printf("%-" + ID_WIDTH + "s %-" + NAME_WIDTH + "s %-" 
	     	    			+ STATUS_WIDTH + "s %-" + DURATION_WIDTH + "s %-" + TEMP_VOLUME_WIDTH + "s%n",
	     	    			"Device ID", "Device Name", "Status", "On Duration", "Temp/Volume");

	     	    // Printing device information in tabular format
	     	    for (Device device : selectedRoom.getDevices()) 
	     	    {
	     	        String status = device.isOn() ? "ON" : "OFF";
	     	        Duration onDuration = device.getOnDuration();
	     	        long durationMinutes = onDuration.toMinutes();

	     	        String tempVolume = "-";

	     	        if (device instanceof TemperatureControl) 
	     	        {
	     	            TemperatureControl tempControl = (TemperatureControl) device;
	     	            tempVolume = String.format("%d°C", ((AirConditioner) tempControl).getCurrentTemperature());
	     	        } 
	     	        else if (device instanceof VolumeControl) 
	     	        {
	     	            VolumeControl volControl = (VolumeControl) device;
	     	            tempVolume = String.format("%d", ((Television) volControl).getCurrentVolume());
	     	        }

	     	        System.out.printf("%-" + ID_WIDTH + "d %-" + NAME_WIDTH + "s %-" + STATUS_WIDTH 
	     	        			+ "s %-" + DURATION_WIDTH + "d %-" + TEMP_VOLUME_WIDTH + "s%n",
	     	            device.getDeviceId(),
	     	            device.getDeviceName(),
	     	            status,
	     	            durationMinutes,
	     	            tempVolume
	     	        );
	     	    }		     
		    }		
	}   //checkDeviceOnTime ends here

	public static void currentStatusHouse(List<Room> rooms, List<Device> devices) {

		System.out.println("Rooms available in house: ");
		for(Room r : rooms)
		{
			System.out.println(r);
		}
		
		System.out.println("Devices available in house: ");
		for(Device d : devices)
		{
			System.out.println(d);
		}
	}  //currentStatusHouse ends here


}  //HouseOperation ends here
























































//public static void checkDeviceStatus(List<Device> devices, List<Room> rooms) {
//
//	System.out.println("Enter room number to check device status:");
//    int roomNo = sc.nextInt();
//    sc.nextLine(); 
//
//    Room selectedRoom = null;
//    for (Room room : rooms) {
//        if (room.getRoomNo() == roomNo) {
//            selectedRoom = room;
//            break;
//        }
//    }
//
//    if (selectedRoom == null) {
//        System.out.println("Room not found");
//    } 
//    else 
//    {
////    	if(devices.isEmpty())
////    	{
////    		System.out.println("No any device in room");
////    		return;
////    	}
//        System.out.println("Devices in room " + selectedRoom.getRoomName() + ":");
//        List<Device> devicesInRoom = selectedRoom.getDevices();
//        
//        if (devicesInRoom.isEmpty()) 
//        {
//            System.out.println("No devices found in this room.");
//        } 
//        else 
//        {
//        	final int ID_WIDTH = 10;		
//     	    final int NAME_WIDTH = 20;		
//     	    final int STATUS_WIDTH = 10;	
//     	    final int DURATION_WIDTH = 15;	 
//     	    final int TEMP_VOLUME_WIDTH = 15;  
//
//     	    // Printing table header
//     	    System.out.printf("%-" + ID_WIDTH + "s %-" + NAME_WIDTH + "s %-" 
//     	    			+ STATUS_WIDTH + "s %-" + DURATION_WIDTH + "s %-" + TEMP_VOLUME_WIDTH + "s%n",
//     	    			"Device ID", "Device Name", "Status", "On Duration", "Temp/Volume");
//
//     	    // Printing device information in tabular format
//     	    for (Device device : devicesInRoom) 
//     	    {
//     	        String status = device.isOn() ? "ON" : "OFF";
//     	        Duration onDuration = device.getOnDuration();
//     	        long durationMinutes = onDuration.toMinutes();
//
//     	        String tempVolume = "-";
//     	           	       
//     	        if (device instanceof TemperatureControl) 
//     	        {
//     	            TemperatureControl tempControl = (TemperatureControl) device;
//     	            tempVolume = String.format("%d°C", ((AirConditioner) tempControl).getCurrentTemperature());
//     	        } 
//     	        else if (device instanceof VolumeControl) 
//     	        {
//     	        		 VolumeControl volControl = (VolumeControl) device;
// 	     	            tempVolume = String.format("%d", ((Television) volControl).getCurrentVolume());
//     	        }
//
//     	        System.out.printf("%-" + ID_WIDTH + "d %-" + NAME_WIDTH + "s %-" + STATUS_WIDTH 
//     	        			+ "s %-" + DURATION_WIDTH + "d %-" + TEMP_VOLUME_WIDTH + "s%n",
//     	            device.getDeviceId(),
//     	            device.getDeviceName(),
//     	            status,
//     	            durationMinutes,
//     	            tempVolume
//     	        );
//     	    }
//        }
//    }
//	
//} // checkDeviceStatus ends here
