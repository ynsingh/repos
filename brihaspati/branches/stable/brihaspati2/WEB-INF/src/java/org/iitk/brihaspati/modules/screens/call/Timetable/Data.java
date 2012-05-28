package org.iitk.brihaspati.modules.screens.call.Timetable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.BatchPeer;
import org.iitk.brihaspati.om.FacInfo;
import org.iitk.brihaspati.om.FacInfoPeer;
import org.iitk.brihaspati.om.Venue;
import org.iitk.brihaspati.om.VenuePeer;



/**
 * 
 * @author Abhishek Agarwal
 * This class is singleton class which will contain all data,events,time-slots and room list.
 * This data is constant and does not change as the application runs.
 */
public class Data implements Constants {
	private static Data dataObject  = null;
	private ArrayList<Batch> batches;
	private ArrayList<Room> roomList;
	private ArrayList<Faculty> facultyList;
	private ArrayList<TimeSlot> timeSlots;
	private ArrayList<Event> allEvents;
	private ArrayList<Course> courses;
	private ArrayList<ArrayList<Integer>> courseToEvents;	//List of events common to one course
	private int eventToCourse[];							//Index of corresponding course
	private boolean eventsMatrix[][];						//adjacency matrix for each event in eventsList
	private ArrayList<Integer> roomsForEvent[];				// A matrix between events and roomList
	ArrayList<Integer> eventsWithMultiSlots[];
	ArrayList<Integer> fixedEvents;
	private int eventId;
	
	public static Data getInstance() throws TimetableException {
		if(null == dataObject) {
			dataObject = new Data();
			System.out.println("Got dataObject");
//			dataObject.loadData();
		}
		return dataObject;
	}
	
	private Data() throws TimetableException {
		loadData();
	}

	public void reloadData() throws TimetableException {
		loadData();
	}

	/**
	 * Load information about all events, batches, students and rooms.
	 * Major modifications in code are required which will be possible
	 * once we have database definitions.
	 * @throws TimetableException
	 */
	private void loadData() throws TimetableException {
		System.out.println("Entering loadData Data");
		batches = new ArrayList<Batch>();
		roomList = new ArrayList<Room>();
		allEvents = new ArrayList<Event>();
		courses = new ArrayList<Course>();
		timeSlots = new ArrayList<TimeSlot>();
		courseToEvents = new ArrayList<ArrayList<Integer>>();
		facultyList = new ArrayList<Faculty>();
		
//		Connection conn = getConnection();
		
		loadRoomList();
		loadFacultyList();
		loadEventList();
		loadBatchList();
		loadSlotList();
		initialize();
		/*try {
			conn.close();
		} catch (SQLException e) {
			throw new TimetableException("Error while closing the connection");
		}*/
		System.out.println("Leaving loadData Data");
	}
	
	

	public ArrayList<Event> getEventList() {
		return allEvents;
	}

	public ArrayList<Faculty> getFacultyList(){
		return facultyList;
	}
	public ArrayList<Room> getRoomList() {
		return roomList;
	}
	
	public ArrayList<Batch> getBatchList() {
		return batches;
	}
	public ArrayList<TimeSlot> getTimeSlots() {
		return timeSlots;
	}
	
	public boolean[][] getEventsMatrix() {
		return eventsMatrix;
	}
	
	public Event getEventObject(int event) {
		return allEvents.get(event);
	}
	
	public void setEventList(ArrayList<Event> allEvents) {
		this.allEvents = allEvents;
	}
	
	public ArrayList<Integer>[] getRoomsForEventList() {
		return roomsForEvent;
	}
	//TODO: check if events and roomlist is null
	/**
	 * Initialize the matrices for conflicts between events and adjacency matrix for event-room
	 */
	public void initialize() {
		eventsWithMultiSlots = new ArrayList[MAX_SLOTS_FOR_EVENT + 1];
		for(int i = MAX_SLOTS_FOR_EVENT;i >=1 ;i--)
			eventsWithMultiSlots[i] = new ArrayList<Integer>();
		fixedEvents = new ArrayList<Integer>();

		//initialize event*room matrix and eventsWithMultiSlots

		int roomsLength = roomList.size();
		int eventsLength = allEvents.size();
		roomsForEvent = new ArrayList[eventsLength];
		for(int i = 0;i < eventsLength; i++) {
			if(getEventObject(i).hasFixedSchedule())
				fixedEvents.add(i);
			else
				eventsWithMultiSlots[getEventObject(i).getRequiredSlots()].add(i);
			roomsForEvent[i] = new ArrayList<Integer>();
			for(int j = 0;j < roomsLength;j++) {
				if(isRoomFit(allEvents.get(i),roomList.get(j)))
					roomsForEvent[i].add(j);
			}
		}	

		//initialize event*event matrix
		eventsMatrix = new boolean[eventsLength][];
		for(int i = 0;i < eventsLength;i++) {
			eventsMatrix[i] = new boolean[eventsLength];
			for(int j = 0; j < i;j++) 
				eventsMatrix[i][j] = eventsMatrix[j][i];
			eventsMatrix[i][i] = true;
			for(int j = 0;j < eventsLength;j++) {
				eventsMatrix[i][j] = haveConflict(i,j);
			}
		}


	}

	/**
	 * check if two events conflict with each other. This function is the definition of event conflict.
	 * They will also conflict if they can be held in only one room.
	 * @param event1
	 * @param event2
	 * @return
	 */
	private boolean haveConflict(int event1, int event2) {
		return ((allEvents.get(event1).getProfessor().getId() 
									.equals(allEvents.get(event2).getProfessor().getId()))
				|| (haveCommonStudent(allEvents.get(event1),allEvents.get(event2)))
				|| haveCommonRoom(event1,event2));
	}
	/*
	 * currently this is O(n^2) but it can be made O(nlogn) if students are keep sorted
	 * in a batch
	 *  
	 */

	/**
	 * check whether two batches have any common student
	 */
	private boolean haveCommonStudent(Event event1, Event event2) {
		ArrayList<Batch> batchList1 = event1.getBatchList();
		ArrayList<Batch> batchList2 = event2.getBatchList();
		if(null == batchList1 || null == batchList2)
			return false;
		for(Batch batch1:batchList1) 
			for(Batch batch2:batchList2)
				if(batch1.getId().equals(batch2.getId()))
					return true;
		return false;
		/*ArrayList<Student> studentList1 = event1.getStudentList();
		ArrayList<Student> studentList2 = event2.getStudentList();
		if(null == studentList1 || null == studentList2)
			return false;
		for(Student student1:studentList1) 
			for(Student student2:studentList2) 
				if(student1.getEnrolment().equals(student2.getEnrolment()))
					return true;
		return false;*/
	}
	
	private boolean haveCommonRoom(int event1,int event2) {
		ArrayList<Integer> adj1 = roomsForEvent[event1];
		ArrayList<Integer> adj2 = roomsForEvent[event2];
		if(null == adj1 || null == adj2) return false;
		return (adj1.size() == 1 && adj2.size() == 1 && adj1.get(0) == adj2.get(0));
	}

	/**
	 * check if a room is fit for particular event
	 * @param event
	 * @param room
	 * @return
	 */
	private boolean isRoomFit(Event event, Room room) {
		// TODO Add more constraints such as computers and projectors
		int eventStrength = 0;
		for(Batch batch:event.getBatchList())
			eventStrength += batch.getStrength();
		if(event.getType() == LECTURE && room.getType() != 1)
			return false;
		if(event.getType() == LABORATORY && room.getType() != 2)
			return false;
		if(eventStrength <= room.getCapacity()) { 
			if(null == event.getFixedRoom()
					|| 0 == event.getFixedRoom().trim().length()
					|| room.getCode().equals(event.getFixedRoom()))
				return true;
			
		}
		return false;
	}

	 
	/**
	 * This was the old function used to do database interaction.
	 * @return
	 */
	/*public static Connection getConnection() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/timetable","root", "mysql");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception: " + e.getMessage());
		} 		
		return con;
	}*/
	
	private void loadSlotList() {
		for(int day = MONDAY;day <= FRIDAY;day++) {
			for(int slot = SLOT_1;slot <= SLOT_8;slot++) {
				TimeSlot timeslot = new TimeSlot(day,slot);
				timeSlots.add(timeslot);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void loadRoomList() throws TimetableException {
		try {
			Criteria crit = new Criteria();
			List l = VenuePeer.doSelect(crit);
			Iterator i = l.iterator();
			while(i.hasNext()) {
				Venue v = (Venue)i.next();
				Room room = new Room(v.getCode(), v.getType(),
						v.getCapacity(), 0, v.getNcomputers(), (v.getProjector() == 1));
				roomList.add(room);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TimetableException(e,"SQL Exception while loading list of rooms");
		} 
		
	}
	
	@SuppressWarnings("unchecked")
	private void loadFacultyList() throws TimetableException {
		try {
			Criteria crit = new Criteria();
			List l = FacInfoPeer.doSelect(crit);
			Iterator i = l.iterator();
			while(i.hasNext()) {
				FacInfo fi = (FacInfo)i.next();
				Faculty faculty = new Faculty(fi.getFacId(), fi.getName(),
						 fi.getDepartment(), fi.getInstitute(), fi.getId());
				facultyList.add(faculty);
			}
		} catch (Exception e) {
			throw new TimetableException(e,"SQL Exception while loading list of rooms");
		} 
		
	}
	
	/**
	 * This function needs to be modified as per the database. The database 
	 * will tell how to handle this. As earlier torque database integration 
	 * was not done, this is an inefficient way to do database interaction.
	 * There should be a table called "Event" in DB that will contain 
	 * foreign key to other tables like "Venue", "Batch" etc.
	 * @throws TimetableException
	 */
	private void loadEventList() throws TimetableException {
			eventId = 0;
			try {
				int course_num = -1;
				String last_course_code = null;
				ArrayList<Batch> batchList = null;
				Course course = null;
				String queryString = "SELECT C.COURSE_CODE,C.COURSE_TYPE,F.FACULTY_ID," +
				"C.EVENTS_PER_WEEK,C.DURATION,C.COMPUTER,C.PROJECTOR, C.VENUE_CODE, C.SCHEDULED FROM COURSE_INFO C," +
				"FACULTY_COURSE F  WHERE C.COURSE_CODE = F.COURSE_CODE  " +
				"ORDER BY COURSE_CODE";
				Connection con = Torque.getConnection();
				if(null == con) {
					throw new TimetableException("Null Connection given in loadEventlist");
				}
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(queryString);
				while(rs.next()) {
					
					String course_code = rs.getString(1).trim();
					String event_type = rs.getString(2).trim();
					String facultyId = rs.getString(3).trim();
					int num_per_week = rs.getInt(4);
					int duration = rs.getInt(5);
					int ncomputers = rs.getInt(6);
					boolean projector = (rs.getInt(7) == 1);
					String fixed_room = rs.getString(8);
					int fixedSchedule = rs.getInt(9);
					fixed_room = (null == fixed_room)?fixed_room
													 :fixed_room.trim();
					
					if(null == last_course_code || false == last_course_code.equals(course_code)) {
						course = new Course(course_code);
						last_course_code = course_code;
						course_num++;
						courses.add(course_num,course);
						courseToEvents.add(course_num,new ArrayList<Integer>());
											
						PreparedStatement ps = con.prepareStatement("SELECT B.BATCH_CODE,B.STRENGTH,B.BATCH_NAME" +
								" FROM BATCH_COURSE BC,BATCH B WHERE" +
								" BC.COURSE_CODE = ? AND BC.BATCH_CODE = B.BATCH_CODE");
						ps.setString(1, course_code);
						ResultSet rs2 = ps.executeQuery();
						
						batchList = new ArrayList<Batch>();
						while(rs2.next()) 
							batchList.add(new Batch(rs2.getString(1).trim(),rs2.getInt(2), rs2.getString(3)));
						rs2.close();
						ps.close();
					}
					
					if(event_type.equals("L"))
						course.setNumLectues(num_per_week);
					else if(event_type.equals("T")) {
	//					num_per_week = 2;
						course.setNumTutorials(num_per_week);
					}
					else if(event_type.equals("P"))
						course.setNumPracticals(num_per_week);
					else if(event_type.equals("S"))
						course.setNumSeminars(num_per_week);
					
					ArrayList<Integer> eventIndices = createAndAddEvents(con, course,num_per_week, 
															event_type,duration,fixed_room, fixedSchedule, facultyId, 
															batchList);
					courseToEvents.get(course_num).addAll(eventIndices);				
					
					
				}
				
				rs.close();
				
				eventToCourse = new int[allEvents.size()];
				for(int i = 0;i< courseToEvents.size();i++) {
					for(Integer eventId:courseToEvents.get(i))
						eventToCourse[eventId] = i;
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new TimetableException(e,"SQL Exception while loading list of events");
			} catch (TorqueException e) {
				e.printStackTrace();
			} 
			
		}

	@SuppressWarnings("unchecked")
	public void loadBatchList() throws TimetableException {
		try {
			Criteria crit = new Criteria();
			List l = BatchPeer.doSelect(crit);
			Iterator i = l.iterator();
			while(i.hasNext()) {
				org.iitk.brihaspati.om.Batch b = (org.iitk.brihaspati.om.Batch)i.next();
				String batchCode = b.getBatchCode();
				int strength = b.getStrength();
				String batchName = b.getBatchName();
				if(null == batches) {
					batches = new ArrayList<Batch>();
				}
				batches.add(new Batch(batchCode,strength, batchName));
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new TimetableException(e,"Exception while loading batch list");
		}
	
	}

	@SuppressWarnings("unchecked")
	private Faculty getFaculty(String facultyId) {
		try{
			try {
					Criteria crit = new Criteria();
					crit.add(FacInfoPeer.ID, facultyId);
					List l = FacInfoPeer.doSelect(crit);
					Iterator i = l.iterator();
					FacInfo faculty = new FacInfo();
					while(i.hasNext()) {
						faculty = (FacInfo)i.next();
					}
					Faculty f = new Faculty(0, faculty.getName(), faculty.getDepartment(), faculty.getInstitute(), facultyId);
					return f;
			} catch (Exception e) {
				throw new TimetableException(e,"SQL Exception while loading list of faculties");
			} 
		}
		catch(TimetableException e){
			System.out.println("The following error occured while getFaculty: ");
			System.out.println(e.getMessage());
		}
		catch(Exception e){
			System.out.println("The following error occured while getFaculty: ");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private ArrayList<Integer> createAndAddEvents(Connection conn,Course course,int numPerWeek,
			String eventType,int duration,String fixedRoom, int fixedSchedule, 
			String facultyId,
			ArrayList<Batch> batchList) 
			throws SQLException {

		ArrayList<Integer> eventIndices = new ArrayList<Integer>();
		int intEventType = getEventType(eventType);
		
		Faculty faculty = getFaculty(facultyId);
		if( null == faculty ) {
			faculty=new Faculty(0, "NA", "NA", "NA", "NA");
			System.out.println("fac_info was not found for facultyId: " + facultyId);
		}
		for(int i = 0;i < numPerWeek;i++) {
			Event event = new Event(course, faculty, batchList, intEventType,
										duration, fixedRoom, fixedSchedule, eventId++);
			eventIndices.add(allEvents.size()); 
			allEvents.add(event);
		}

		return eventIndices;
	}
	
	private int getEventType(String eventType) {
		if(eventType.equals("L"))
			return Constants.LECTURE;
		else if(eventType.equals("T"))
			return Constants.TUTORIAL;
		else if(eventType.equals("P"))
			return Constants.LABORATORY;
		else if(eventType.equals("S"))
			return Constants.SEMINAR;
		else
			return -1;
	}

	public static void main(String args[]) {
		Data data;
		try {
			data = Data.getInstance();
			System.out.println(data.getEventList().size());
		} catch (TimetableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> getEventsWithMultiSlots(int slotsRequired) {
		return eventsWithMultiSlots[slotsRequired];
	}
	
	public ArrayList<Integer> getEventsWithOneSlot() {
		return eventsWithMultiSlots[1];
	}
	
	public ArrayList<Integer> getFixedEvents() {
		return fixedEvents;
	}
	
	public ArrayList<ArrayList<Integer>> getCourseToEventMapping() {
		return courseToEvents;
	}

	public int[] getEventToCourseMapping() {
		return eventToCourse;
	}

	public ArrayList<Course> getAllCourses() {
		return courses;
	}
	
	public String getRoomCode(int roomIndex) {
		return roomList.get(roomIndex).getCode();
	}
	
 }
