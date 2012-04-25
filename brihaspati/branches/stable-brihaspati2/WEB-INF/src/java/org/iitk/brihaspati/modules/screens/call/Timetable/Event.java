package org.iitk.brihaspati.modules.screens.call.Timetable;

import java.util.ArrayList;
import java.io.Serializable;
 
public class Event implements Serializable {
	Faculty faculty;
	ArrayList<Batch> batchList;
	private int id;
	Room room;
	Course course;
	int type;
	int slotsRequired;
	int slot;
	int fixedSchedule;
//	ArrayList<Student> studentList;
	String fixedRoom;
	
	public Event(Course course, Faculty faculty, ArrayList<Batch> batchList,
									int type, int duration,String fixedRoom, 
									int fixedSchedule, int eventId){
		this.course = course;
		this.faculty = faculty;
		this.batchList = batchList;
		this.type = type;
		this.slotsRequired = duration;
		this.fixedRoom = fixedRoom;
		this.fixedSchedule = fixedSchedule; 
		this.id = eventId;
	}
	
	public Event(Course course, Faculty faculty, ArrayList<Batch> batchList,int type) {
		this.course = course;
		this.faculty = faculty;
		this.batchList = batchList;
		this.type = type;
		this.slotsRequired = Constants.DEFAULT_SLOTS;
	}
	
	public Event(Event e) {
		this.course = e.course;
		this.faculty = e.faculty;
		this.batchList = e.batchList;
		this.type = e.type;
		this.slotsRequired = e.slotsRequired;
		this.fixedRoom = e.fixedRoom;
		this.fixedSchedule = e.fixedSchedule;
		this.id = e.id;
		this.slot = e.slot;
		this.room = e.room;
		
	}
	
	public Faculty getProfessor() {
		return faculty;
	}
	
	/*public Batch getBatch() {
		return batch;
	}*/
	
	public Faculty getFaculty(){
		return faculty;
	}
	public int getId() {
		return id;
	}
	
	public int getType() {
		return type;
	}
	
	public String getStringType() {
		return String.valueOf(Constants.CLASS_CODES[type]);
	}
	
	public Course getCourse() {
		return course;
	}
	
	public int getRequiredSlots() {
		return slotsRequired;
	}
	
	public Room getRoom(){
		return room;
	}
	
	public int getSlot() {
		return slot;
	}
	
	public void setRoom (Room room) {
		this.room = room;
	}
	
	public void setSlot(int slot) {
		this.slot = slot;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean hasRoom() {
		if(room == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public ArrayList<Batch> getBatchList() {
		return batchList;
	}

	public String getFixedRoom() {
		return this.fixedRoom;
	}
	
	public boolean hasFixedSchedule() {
		return (fixedSchedule == 1); 
	}
	
	public String toString() {
		String string = "Id: " + id + " course: " + course + " type: " + type +
						" faculty: " + faculty + " slot: " + slot + " room: ";
		if(hasFixedSchedule()) {
			string += fixedRoom;
		}
		else {
			string += room;
		}
		return string;
	}
	
}
