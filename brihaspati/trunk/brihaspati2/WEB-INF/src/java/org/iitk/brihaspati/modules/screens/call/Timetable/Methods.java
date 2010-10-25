package org.iitk.brihaspati.modules.screens.call.Timetable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.io.File;

public class Methods {
	private static ArrayList<String> ERROR_MSGS = new ArrayList<String>();
	
	public static String getFormattedData(ArrayList<Event> list) {
		String eventSlotMap = new String();
		for(int i = 0; i < list.size(); i++) {
			eventSlotMap += list.get(i).getId();
			eventSlotMap += "::";
			eventSlotMap += list.get(i).getSlot();
			eventSlotMap += "::::";
		}
		return eventSlotMap;
	}

	public static Hashtable<Integer, Integer> getEventSlotHash(String eventSlotMap) {
		String[] eventSlotPairs = eventSlotMap.split("::::");
		String[] eventSlotPair = null;
		Hashtable<Integer, Integer> eventSlotHash = new Hashtable<Integer, Integer>();
		
		for(int i = 0; i < eventSlotPairs.length; i++) {
			eventSlotPair = eventSlotPairs[i].split("::");
			eventSlotHash.put(Integer.parseInt(eventSlotPair[0]), Integer.parseInt(eventSlotPair[1]));
		}
		return eventSlotHash;
	}
	
	public static boolean checkAvailability(ArrayList<Event> eventsToVerify, 
			ArrayList<Event> eventsInDB, ArrayList<Event> eventsInNewTimetable,
			ArrayList<Event> eventsInOldTimetable) 
			throws TimetableException {
		Event newEvent;
		Event oldEvent;
		ArrayList<Event> eventList = new ArrayList<Event>();
		int slot2, slotsReq;
		boolean success = false;
		for(int i = 0; i < eventsToVerify.size(); i++) {
			newEvent = eventsToVerify.get(i);
			slot2 = newEvent.getSlot();
			slotsReq = newEvent.getRequiredSlots();
			for(int j = 0; j < slotsReq; j++) {
				int tempSlot = slot2 - j;
				oldEvent = getBySlot(tempSlot, eventsInOldTimetable);
				eventList = getEventListBySlot(tempSlot, eventsInDB);
				if(oldEvent != null) {
					// It is a case of swap.
					System.out.println("It is case of swap."+j);
					success = eventList.remove(oldEvent);
				}
				System.out.println(newEvent);
				success = checkInSlot(eventList, newEvent);
			}
		}
		return success;
	}
	
	public static boolean checkInSlot(ArrayList<Event> list, Event newEvent) throws TimetableException{
		Event temp;
		for(int i = 0; i < list.size(); i++) {
			temp = list.get(i);
			if(!temp.hasFixedSchedule()) {
				if(!hasFacultyCommon(newEvent, temp)) {
					if(!hasBatchCommon(newEvent, temp)) {
						if(!hasRoomCommon(newEvent, temp)) {
						}
					}
				}
			}
		}
		return true;
	}

	public static boolean hasRoomCommon(Event event1, Event event2) 
	throws TimetableException{
		System.out.println(event1.getRoom().getCode()+event2.getRoom().getCode());
		if(event1.getRoom().getCode().equals(event2.getRoom().getCode())) {
			System.out.println("Room: " + event1.getRoom().getCode());
			throw new TimetableException("Room: " + event1.getRoom().getCode() + 
					" is unavailable for event: " + event1);
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean hasBatchCommon(Event event1, Event event2) 
	throws TimetableException{
		ArrayList commonBatches = getCommonList(event1.getBatchList(), event2.getBatchList());
		if(commonBatches != null) {
			System.out.println("Batches: " + event1.getFaculty().getName());
			throw new TimetableException("Batches: " + event1.getFaculty().getName() + 
					" are unavailable for event: " + event1);
		}
		return false;
	}
	
	public static boolean hasFacultyCommon(Event event1, Event event2) 
	throws TimetableException{
		if(event1.getFaculty().getId().equals(event2.getFaculty().getId())) {
			System.out.println("Faculty: " + event1.getFaculty().getName());
			throw new TimetableException("Faculty: " + event1.getFaculty().getName() + 
					" is unavailable for event: " + event1);
		}
		return false;
	}

	public static ArrayList<Event> getEventListBySlot(int slot, ArrayList<Event> list) {
		ArrayList<Event> retList = new ArrayList<Event>();
		for(int i = 0; i < list.size(); i++) {
			Event e = list.get(i);
			if(slot == e.getSlot()) {
				retList.add(e);
			}
			if(e.getRequiredSlots() > 1) {
				for(int j = 1; j < e.getRequiredSlots(); j++) {
					int otherSlot = e.getSlot() - j;
					if(otherSlot == slot) {
						retList.add(e);
					}
				}
			}
		}
		return retList;
	}

	public static ArrayList<Batch> getBatchList(ArrayList<Event> list){
		HashSet<String> code = new HashSet<String>();
		ArrayList<Batch> batchList = new ArrayList<Batch>();
		Event event;
		for(int i = 0; i < list.size(); i++) {
			event = list.get(i);
			if(event.getBatchList() != null) {
				for(int j = 0; j < event.getBatchList().size(); j++) {
					if(code.add(event.getBatchList().get(j).getId())) {
						batchList.add(event.getBatchList().get(j));
					}
				}
			}
		}
		return batchList;
	}

	public static ArrayList<Faculty> getFacultyList(ArrayList<Event> list){
		HashSet<String> code = new HashSet<String>();
		ArrayList<Faculty> facultyList = new ArrayList<Faculty>();
		Event event;
		for(int i = 0; i < list.size(); i++) {
			event = list.get(i);
			if(event.getFaculty() != null) {
				if(code.add(event.getFaculty().getId())) {
					facultyList.add(event.getFaculty());
				}
			}
		}
		return facultyList;
	}
	
	public static ArrayList<Room> getRoomList(ArrayList<Event> list){
		HashSet<String> code = new HashSet<String>();
		ArrayList<Room> roomList = new ArrayList<Room>();
		Event event;
		for(int i = 0; i < list.size(); i++) {
			event = list.get(i);
			if(event.getRoom() != null) {
				if(code.add(event.getRoom().getCode())) {
					roomList.add(event.getRoom());
				}
			}
		}
		return roomList;
	}
	
	public static Event getBySlot(int slot, ArrayList<Event> list) {
		for(int i = 0; i < list.size(); i++) {
			Event e = list.get(i);
			if(slot == e.getSlot()) {
				return e;
			}
		}
		return null;
	}
	
	public static Event getById(int id, ArrayList<Event> list) {
		for(int i = 0; i < list.size(); i++) {
			if(id == list.get(i).getId()) {
				return list.get(i);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList getCommonList(ArrayList list1, ArrayList list2){
		ArrayList common = new ArrayList();
		int size = 0; 
		if(list2.size() > list1.size()) {
			size = list2.size();
			for(int i = 0; i < size; i++) {
				if(list2.get(i) != null) {
					if(list1.contains(list2.get(i))) {
						if(!common.contains(list2.get(i))) {
							common.add(list2.get(i));
						}
					}
				}
			}
		}
		else {
			size = list1.size();
			for(int i = 0; i < size; i++) {
				if(list1.get(i) != null) {
					if(list2.contains(list1.get(i))) {
						if(!common.contains(list1.get(i))) {
							common.add(list1.get(i));
						}
					}
				}
			}
		}
		return common.size() > 0 ? common : null;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean hasCommonElements(ArrayList list1, ArrayList list2){
		ArrayList common = new ArrayList();
		int size = 0; 
		if(list2.size() > list1.size()) {
			size = list2.size();
			for(int i = 0; i < size; i++) {
				if(list1.contains(list2.get(i))) {
					if(!common.contains(list2.get(i))) {
						common.add(list2.get(i));
					}
				}
			}
		}
		else {
			size = list1.size();
			for(int i = 0; i < size; i++) {
				if(list2.contains(list1.get(i))) {
					if(!common.contains(list1.get(i))) {
						common.add(list1.get(i));
					}
				}
			}
		}
		return common.size() > 0 ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean isArrayListEqual(ArrayList list1, ArrayList list2) {
		if(list1.size() == list2.size()){
			if(list1.containsAll(list2)){
				if(list2.containsAll(list1)){
					if(list1 == getCommonList(list1, list2) && list2 == getCommonList(list1, list2)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean isArrayListEquivalent(ArrayList list1, ArrayList list2) {
		if(list1.size() == list2.size()){
			if(list1.containsAll(list2)){
				if(list2.containsAll(list1)){
					return true;
				}
			}
		}
		return false;
	}
	
	public static void addErrorMsg(String arg) {
		ERROR_MSGS.add(arg);
	}
	
	public static ArrayList<String> getErrorMsgs() {
		return ERROR_MSGS;
	}
	
	 public static boolean checkDirectoryPath(String path) throws TimetableException {
		String rootPath = path;
		boolean created = false;
		File root = new File(rootPath);
		created = createDirectory(root);
		return created;
	}
	
	public static boolean createDirectory(File file) throws TimetableException {
		String message = "Error while creating directory: ";
		boolean created = false;
		if(!file.isDirectory()) {
			if(!file.mkdirs()) {
				throw new TimetableException(message + file.toString());
			}
			created = true;
		}
		return created;
	}

}
