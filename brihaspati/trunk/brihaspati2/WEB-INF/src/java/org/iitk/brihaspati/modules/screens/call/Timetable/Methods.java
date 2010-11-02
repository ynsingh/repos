package org.iitk.brihaspati.modules.screens.call.Timetable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.io.File;

import org.apache.torque.Torque;

public class Methods implements Constants{
	
	
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
	
	/**
	 * run a maximal matching algorithm to match events in a timeslot to available rooms
	 * @throws TimetableException 
	 */
	public static void assignRoomsToEvents(int numRooms,
			ArrayList<Integer> eventsWithoutR,
			ArrayList<Integer> eventsWithR,
			ArrayList<Integer> busyRooms,
			ArrayList<Integer> preOccupiedRooms,
			ArrayList<Integer> roomsForEvent[],
			int eventRoom[]) throws TimetableException {
		if(null == eventsWithoutR) 
		{
			throw new TimetableException("list of events without room is null");
		}
		if(eventsWithoutR.size() == 0)
			return;				/* No events to assign 		*/

		if(null == roomsForEvent || roomsForEvent.length == 0)
			throw new TimetableException("List of rooms for each event is empty");

		if(null == eventsWithR)
			throw new TimetableException("List for storing events with rooms is null");

		if(null == busyRooms)
			throw new TimetableException("list for storing occupied rooms is null");

		int numEvents = eventsWithoutR.size();
		int numVertices = numEvents + numRooms;

		int color[] = new int[numVertices];		// Stores the color of all vertices
		int match[] = new int[numVertices];		//stores the number of event that is matched to this room
		int label[] = new int[numVertices];
		int parent[] = new int[numVertices];



		//currently every event and room is unmatched
		for(int i = 0;i < numVertices;i++) {
			color[i] = WHITE;
			parent[i] = -1;
		}

		//put all white vertices representing event into queue
		for(int i = 0;i < numEvents;i++) {
			label[i] = 0;
			//	queue.add(i);
		}

		for(int i = numEvents;i < numVertices;i++) {
			match[i] = -1;
			label[i] = -1;
		}

		//iteratively find an augmenting path
		for(int i = 0;i < numEvents;i++) {
			if(WHITE == color[i]) {
				//re-initialize the labels and parent fields
				for(int j = 0;j < numVertices;j++) {
					label[j] = -1;
					parent[j] = -1;
				}
				Queue<Integer> queue = new LinkedList<Integer>();
				queue.add(i);

				int root = i;
				label[root] = 0;
				int leaf = -1;

				outer:
					while(!queue.isEmpty()) {
						int eventV = queue.remove();
						ArrayList<Integer> adjacent = new ArrayList<Integer>
						(roomsForEvent[eventsWithoutR.get(eventV)]);
						if(null != preOccupiedRooms)
							adjacent.removeAll(preOccupiedRooms);	// ignore rooms that are preoccupied
						for(int j = 0;j < adjacent.size();j++) {
							int roomIndex = numEvents + adjacent.get(j);
							if(label[roomIndex] >= 0)
								continue;										//continue if already labeled
							parent[roomIndex] = eventV;
							label[roomIndex] = label[eventV] + 1;

							if(WHITE == color[roomIndex]) {
								leaf = roomIndex;
								break outer;
							}
							else {
								label[match[roomIndex]] = label[roomIndex] + 1;
								parent[match[roomIndex]] = roomIndex;
								queue.add(match[roomIndex]);
							}
						}
					}
				for(int j = leaf;j > 0; j = parent[parent[j]]) {
					match[j] = parent[j];
					color[j] = GREEN;
					color[parent[j]] = GREEN;
				}
			}
		}


		for(int i = numEvents;i < numVertices;i++) {
			if(color[i] == GREEN) {
				int roomIndex = i - numEvents;
				int event = eventsWithoutR.get(match[i]);
				eventRoom[event] = roomIndex;
				eventsWithR.add(event);
				busyRooms.add(roomIndex);
			}
		}
		eventsWithoutR.removeAll(eventsWithR);
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws TimetableException {
		ArrayList<Integer> eventsWithoutR = new ArrayList<Integer>();
		eventsWithoutR.add(0);
		eventsWithoutR.add(1);
		eventsWithoutR.add(2);
		ArrayList<Integer> eventsWithR = new ArrayList<Integer>();
		ArrayList<Integer> busyRooms = new ArrayList<Integer>();
		int eventRoom[] = new int[3];
		ArrayList<Integer> roomsForEvent[] = new ArrayList[3];
		ArrayList<Integer> preOccupiedRooms = new ArrayList<Integer>();
		preOccupiedRooms.add(0);
		preOccupiedRooms.add(1);
		for(int i = 0;i<eventsWithoutR.size();i++)
			roomsForEvent[i] = new ArrayList<Integer>();
		roomsForEvent[0].add(0);
		roomsForEvent[0].add(1);
		roomsForEvent[1].add(1);
		roomsForEvent[1].add(2);
		roomsForEvent[2].add(3);
		roomsForEvent[2].add(3);
		int numRooms = 4;
		assignRoomsToEvents(numRooms, eventsWithoutR, eventsWithR, 
												busyRooms,preOccupiedRooms, roomsForEvent, eventRoom);
		
		for(int i=0;i<eventsWithR.size();i++) {
			System.out.println(eventsWithR.get(i) + " " + eventRoom[eventsWithR.get(i)]);
		}
		
	}

    public static String getDepartment(String tableId) {
        String department = null;
        if(null == tableId || tableId.equals("")){
            System.out.println("Empty table id passed in getDepartment");
            return "";
        }
        String queryString = "select DEPARTMENT from TABLE_ID where TAB_ID=" + tableId;
        try{
            Connection con = Torque.getConnection();
            if(null == con) {
                System.out.println("Null Connection given in loadFromFile");
                return "";
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryString);
            rs.next();
            department = rs.getString(1).trim();
            System.out.println("Load from file department: " + department);
        } catch (Exception e) {
            System.out.println("Error while fetching department: " + e);
            return "";
        }
        return department;
    }
    
    public static String getPath(String tableId) {
        String path = null;
        if(null == tableId || tableId.equals("")){
            System.out.println("Empty table id passed in getPath");
            return null;
        }
        String queryString = "select PATH from TABLE_ID where TAB_ID=" + tableId;
        try{
            Connection con = Torque.getConnection();
            if(null == con) {
                System.out.println("Null Connection given in loadFromFile");
                return null;
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryString);
            rs.next();
            path = rs.getString(1).trim();
            System.out.println("Load from file path: " + path);
        } catch (Exception e) {
            System.out.println("Error while fetching path: " + e);
            return null;
        }
        return path;
    }

}
