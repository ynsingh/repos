package org.iitk.brihaspati.modules.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Date;
import java.util.*;

import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.screens.call.Timetable.*;

import com.lowagie.text.DocumentException;
 
public class GenerateTimeTable extends VelocityAction {
	
	static STimetable best;
	static SingleSlotTimetable sST;
	static MultiSlotTimetable mST;
	static STimetable univT;
	PDFGenerator reportGen;
	
	public GenerateTimeTable() {
		initialize();
		System.out.println("Leaving constructor GenerateTimeTable");
	}
	
	public static void initialize() {
		try {
			best = new STimetable();
			//univT = new STimetable();
			//univT.loadFromDB(Data.getConnection(), Data.getInstance().getFixedEvents());
//			Data.getInstance().reloadData();
			STimetable parent = null;
			for(int i = Constants.MAX_SLOTS_FOR_EVENT; i >= 2;i--) {
				mST = new MultiSlotTimetable(i);
				mST.setParentTimeTable(parent);
				mST.generateFirstTimetable();
				parent = mST.mergeWithParent();
			}
			sST = new SingleSlotTimetable();
			sST.setParentTimeTable(parent);
			sST.generateFirstTimetable();
			SingleSlotTimetable mySST = new SingleSlotTimetable(sST);
			best = mySST.mergeWithParent();
		} catch (TimetableException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void searchBestTimetable() throws TimetableException {
		if(null == best) initialize();
		SingleSlotTimetable tmp = new SingleSlotTimetable(sST);
		long start  = System.currentTimeMillis();
		// Swap slots
		for(int i=1;i<100000;i++) {
			tmp.sequenceSlots();
			if(tmp.isBetterThan(sST)) {
				sST = new SingleSlotTimetable(tmp);
				best = new SingleSlotTimetable(tmp).mergeWithParent();
			}
		}
		System.out.println("After Sequence slots ");
		best.displayPenalty();
		System.out.println("Total time taken : " + (System.currentTimeMillis() - start)/60 + " seconds\n");
		start = System.currentTimeMillis();

//		 Exchange events between slots 
		tmp = new SingleSlotTimetable(sST); 
		for(int i=1;i < 100000;i++) {
			tmp.shuffleEvents();
			if(tmp.isBetterThan(sST)) {
				sST = new SingleSlotTimetable(tmp);
				best = new SingleSlotTimetable(tmp).mergeWithParent();
			}
		}
		
		System.out.println("After shuffle events");
		best.displayPenalty();
		System.out.println("Total time taken : " + (System.currentTimeMillis() - start)/60 + " seconds\n");
		start = System.currentTimeMillis();
		
	}
	
	public STimetable getBest() {
		return best;
	}
	
	public void doGenerate(RunData data, Context context) throws Exception {
		System.out.println("------------------------- Entering Generate------------------------------");
		String msg = "There was an error in generating Timetables.<br/>";
		boolean error = false;
		String xmlFile = (String)data.getParameters().getString("xmlFile", "");
		String uploadDir = (String)data.getParameters().getString("uploadDir", "");
		String department =(String)data.getSession().getAttribute("department");
		if(xmlFile.equals("") || uploadDir.equals("") || department.equals("")) {
			context.put("msg", msg);
			return;
		}
		
		User user = data.getUser();
		String username = user.getName();
		String tableId = "";	
		Date date = new Date();
		String newDir = date.getDate() + "-" + date.getMonth() + "-" + new String("" + (date.getYear() + 1900))
		+ " " + date.getHours() + ":" + date.getMinutes()+ ":" + date.getSeconds()	;
		String path = data.getServletContext().getRealPath("/reports/" + username + "/" + department + "/" +
								uploadDir + "/report~" + newDir + "/");
		data.getSession().setAttribute("reportDir", ("report~"+newDir));
		data.getSession().setAttribute("uploadDir", uploadDir);
		data.getSession().setAttribute("path", username + "/" + department + "/" + uploadDir + "/report~" + newDir );
		try {
			Utils.checkDirectoryPath(path);
		} catch (Exception e) {
			context.put("msg","Unable to create directory for this instance");
			return;
		}
		
		xmlFile = data.getServletContext().getRealPath("/reports/" + username + "/" + department + "/" + uploadDir) + "/" + xmlFile;
		
		try {
			System.out.println("Loading "+ xmlFile + " in the database");
			new ParseXml(xmlFile);
			System.out.println("Loading completed.");
		} catch(Throwable t) {
			t.printStackTrace();
			System.out.println("SEVERE: Error while loading into database.");
			context.put("msg", "The uploaded file is not compatible with our database.");
			return;
		}
		
		
		try {
			/*
			 * This line is important. Somehow some events get duplicated
			 * which results in error. This happens when we call doGenerate 
			 * with recompiling.
			 */
			Data.getInstance().reloadData();
			initialize();
//			best.display();
			best.displayPenalty();
			System.out.println("\n");
			searchBestTimetable(); best.setEventData();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * Here the path of the file in which Timetable is stored is to be given.
		 */
		try {
			tableId = best.savePath(username, department, path);
			best.saveTable(tableId, username, department, path, Data.getInstance().getEventList());
			System.out.println("TableId saved is: " + tableId);
		} catch (Exception e) {
			System.out.println("ErrorId2: "+e);
		}

		/*
		 * This is in separate try/catch as it throws error messages 
		 * that need to be shown to the user.
		 */
		try {
			reportGen = new PDFGenerator(path, tableId);
			if(!error) {
				msg = "Success";
			}
		} catch(TimetableException e) {
			if(error) {
				msg += "<br/>" + e.getMessage();
			}
			else {
				msg = "<br/>" + e.getMessage();
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		context.put("msg", msg);
		System.out.println("------------------------- Leaving Generate------------------------------");
	}

	public void doCommit(RunData data, Context context) {
		System.out.println("------------------------- Entering Commit------------------------------");
		String msg = "All changes were made successfully.";
		User user = data.getUser();
		String username = user.getName();
		String department =(String)data.getSession().getAttribute("department");
		String eventSlotMap = data.getRequest().getParameter("eventSlotMap");
		String tableId = data.getRequest().getParameter("tableId");
		String path = Methods.getPath(tableId);
		Hashtable<Integer, Integer> eventSlotHash = Methods.getEventSlotHash(eventSlotMap);
		System.out.println(eventSlotHash);
		ArrayList<Event> eventsInDB = new ArrayList<Event>();
		Event event;
		try {
			System.out.println("Table id: " + tableId);
			eventsInDB = best.loadFromFile(tableId);
			if(eventsInDB == null) {
				context.put("msg", "Error while retreiving data");
				context.put("commit", "commit");
				return;
			}
			Set<Integer> eventIds = eventSlotHash.keySet();
			Iterator<Integer> iter = eventIds.iterator();
			while(iter.hasNext()) {
				int id = iter.next();
				event = Methods.getById(id, eventsInDB);
				eventsInDB.remove(event);
				event.setSlot(eventSlotHash.get(id));
				eventsInDB.add(event);
			}
			best.saveTable(tableId, username, department, path, eventsInDB);
			PDFGenerator pdfg = new PDFGenerator();
			pdfg.generateFromDB(path, tableId, eventsInDB);
		} catch (TimetableException e1) {
			msg = "There was some error in processing you request.";
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		} catch (Exception e) {
			msg = "There was some error in processing you request.";
			e.printStackTrace();
		}
		context.put("msg", msg);
		context.put("commit", "commit");
		context.put("errorMsgs", Utils.getErrorMsgs());
		System.out.println("------------------------- Leaving Commit------------------------------");
	}
	
	public void doVerification(RunData data, Context context) {
		System.out.println("------------------------- Entering Verification------------------------------");
		boolean valid = false;
		String msg = "There was error in processing your request.";
		System.out.println("wearehere");
		String eventSlotMap = data.getRequest().getParameter("eventSlotMap");
		String tableId = data.getRequest().getParameter("tableId");
		Hashtable<Integer, Integer> eventSlotHash = Methods.getEventSlotHash(eventSlotMap);
		ArrayList<Event> eventsInDB = new ArrayList<Event>();
		ArrayList<Event> eventsInNewTimetable = new ArrayList<Event>();
		ArrayList<Event> eventsInOldTimetable = new ArrayList<Event>();
		ArrayList<Event> eventsToVerify = new ArrayList<Event>();
		Event event = null;
		eventSlotMap = null;
		
	        String filePath = Methods.getPath(tableId);	
		String []output = filePath.split("/");
		Vector qwer = new Vector();
		String finals = "";
		for(int i = output.length - 1; i>=0; i--) {
			if(output[i].equals("reports")) break;
			qwer.add(output[i]);
		}
		for(int i = qwer.size() - 1; i >= 0; i--) {
			finals += qwer.get(i) + "/";
		}

		data.getSession().setAttribute("path", finals);

		System.out.println("TableId while verification: " + tableId);
		try {
			System.out.println("weareherehere");
			eventsInDB = best.loadFromFile(tableId);
			if(eventsInDB == null) {
				context.put("msg", "Error while retreiving data");
				context.put("valid", "false");
				return;
			}
			System.out.println("weareheretoo");
		} catch (Exception e1) {
			System.out.println(e1);
			System.out.println(e1.getMessage());
		}
		
		try{
			System.out.println("weareheretoo3");
			for(int j = 0; j < eventsInDB.size(); j++) {
				event = eventsInDB.get(j);
				int id = event.getId();
				if(eventSlotHash.containsKey(id)) {
					int oldSlot = event.getSlot();
					int newSlot = eventSlotHash.get(id);
					eventsInOldTimetable.add(event);
					Event e = new Event(event);
					e.setSlot(newSlot);
					eventsInNewTimetable.add(e);
					if(newSlot != oldSlot) {
						eventsToVerify.add(e);
						System.out.println("Slots for eventId:" + id +" do not match. " +
								"Old slot:" + oldSlot + " and New slot:" + newSlot);
					}
				}
			}
			if(eventsToVerify.size() > 0) {
				valid = Methods.checkAvailability(eventsToVerify, eventsInDB, eventsInNewTimetable, eventsInOldTimetable);
				System.out.println("weareheretoo4");
				if(valid) {
					System.out.println("weareheretoo5");
					eventSlotMap = Methods.getFormattedData(eventsToVerify);
				}
			}
			else {
				System.out.println("weareheretoo6");
				throw new TimetableException("You made no changes to the Timetable.");
			}
		} catch (TimetableException e) {
			valid = false;
			msg = e.getMessage();
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("error while validating changed Timetable");
			e.printStackTrace();
		}
		System.out.println("Exiting validation: " + valid);
		context.put("valid", Boolean.toString(valid));
		context.put("eventSlotMap", eventSlotMap);
		context.put("tableId", tableId);
		context.put("msg", msg);
		System.out.println("------------------------- Leaving Verification ------------------------------");
	}

	static public void main(String args[]) {
		try {
			System.out.println("Before sequencing slots");
			initialize();
//			best.display();
			best.displayPenalty();
//			System.out.println("\n");
			searchBestTimetable();
//			best = new STimetable();
			try {
				ArrayList<Integer> eventList = Data.getInstance().getEventsWithOneSlot();
				for(int i = Constants.MAX_SLOTS_FOR_EVENT; i >= 2;i--) {
					eventList.addAll(Data.getInstance().getEventsWithMultiSlots(i));
				}
				
//				best.loadFromDB(Data.getConnection(), eventList);
				//best.newInsertIntoDB(Data.getInstance().getEventList());
				String path = "E:\\course_Timetables\\";
				PDFGenerator reportGen = new PDFGenerator();
				reportGen.publishAllBatchesTimetables(path);
				reportGen.publishAllFacultyTimetables(path);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			System.out.println(Test.counter);
//			best.displayPenalty();
		} catch (TimetableException e) {
			e.printStackTrace();
		}
	}

	//@Override
	public void doPerform(RunData arg0, Context arg1) {
	}

	
}
