package org.iitk.brihaspati.modules.screens.call.Timetable;

import java.io.Serializable;

public class Room implements Serializable {
	int type;
	int capacity;
	int building;
	int numComputers;
	boolean hasProjector;
	String code;
	
	public Room(String code,int type,int capacity,int building,int numComputers, boolean hasProjector ) {
		this.code = new String(code);
		this.type = type;
		this.capacity = capacity;
		this.building = building;
		this.numComputers = numComputers;
		this.hasProjector = hasProjector;
	}
	
	public Room(Room room) {
		this.code = room.code;
		this.type = room.type;
		this.capacity = room.capacity;
		this.building = room.building;
		this.numComputers = room.numComputers;
		this.hasProjector = room.hasProjector;
	}
	
	public String getCode() {
		return code;
	}
	
	public int getBuilding() {
		return building;
	}
	
	public int getType() {
		return type;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getComputers() {
		return numComputers;
	}
	
	public boolean hasProjector() {
		return hasProjector;
	}
	
	public void setCode(String code) {
		this.code = new String(code);
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public void displayTimeTable() {
		return;
	}
	
	public String toString() {
		return code;
	}
}
