/**
 * 
 */
package org.iitk.brihaspati.modules.screens.call.Timetable;

import java.io.*; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author Aniket
 *
 */

public class PDFGenerator implements Constants {
	/* Set this variable to "Yes" if the Timetables are generated correctly,
	 * but you want to show an error msg by throwing a new Timetable exception.
	*/
	private String ERROR_MSG = "";
	private Image iitrLogo;
	String[] days = {"", "Monday", "Tueday","Wednesday","Thursday","Friday" };
	String[] months = {"Jan","Feb","March","April","May","June","July","Aug","Sep","Oct","Nov","Dec"};
	String[] slots = {"", "9-10 AM","10-11 AM","11-12 Noon","12-1 PM", "1-2 PM", 
						"2-3 PM","3-4 PM","4-5 PM","5-6 PM","","","",""};
	int noOfCols = 6;
	int noOfRows = 10;
	int breakSlot = 5;
	private ArrayList<Event> eventList = null;
	private ArrayList<Room> roomList = null;
	private ArrayList<Batch> batchList = null;
	private ArrayList<Faculty> facultyList = null;
	private float[] colWidths = {1.7f,2.2f,2.2f,2.2f,2.2f,2.2f};
	
	String department = null;
	public PDFGenerator () throws TimetableException {
		eventList = new ArrayList<Event>();
		roomList = new ArrayList<Room>();
		batchList = new ArrayList<Batch>();
		facultyList = new ArrayList<Faculty>();
		eventList = Data.getInstance().getEventList();
		roomList = Data.getInstance().getRoomList();
		batchList = Data.getInstance().getBatchList();
		facultyList = Data.getInstance().getFacultyList();
		System.out.println("-------------Empty Constructor-----------------------");
		System.out.println("events: " + eventList.size());
		System.out.println("faculty: " + facultyList.size());
		System.out.println("batch: " + batchList.size());
		System.out.println("room: " + roomList.size());
	}
	
	public PDFGenerator (String path, String tableId) throws Exception {
		eventList = new ArrayList<Event>();
		roomList = new ArrayList<Room>();
		batchList = new ArrayList<Batch>();
		facultyList = new ArrayList<Faculty>();
		department = Methods.getDepartment(tableId);
		eventList = Data.getInstance().getEventList();
		roomList = Data.getInstance().getRoomList();
		batchList = Data.getInstance().getBatchList();
		facultyList = Data.getInstance().getFacultyList();
		System.out.println("-------------Path Constructor-----------------------");
		System.out.println("path: " + path + ", tableId: " + tableId + ", department: " + department);
		System.out.println("events: " + eventList.size());
		System.out.println("faculty: " + facultyList.size());
		System.out.println("batch: " + batchList.size());
		System.out.println("room: " + roomList.size());
		boolean created = checkDirectoryPath(path);
		publishAllBatchesTimetables(path);
		publishAllFacultyTimetables(path);
		publishAllRoomTimetables(path);
		publishAllBatchHTML(path, tableId);
		publishAllFacultyHTML(path, tableId);
		publishAllRoomHTML(path, tableId);
		if(created) {
/*			throw new TimetableException("Some directories were created during " +
					"generating Timetables.<br/>You might not have the necessary " +
					"javascript file for Drag and Drop in HTMLs."); */
		}
	}
	
	public void generateFromDB (String path, String tableId, ArrayList<Event> eventList) throws Exception {
		this.eventList = new ArrayList<Event>();
		roomList = new ArrayList<Room>();
		batchList = new ArrayList<Batch>();
		facultyList = new ArrayList<Faculty>();
		department = Methods.getDepartment(tableId);
		this.eventList = eventList;
		roomList = Methods.getRoomList(eventList);
		batchList = Methods.getBatchList(eventList);
		facultyList = Methods.getFacultyList(eventList);
		System.out.println("-------------generateFromDB-----------------------");
		System.out.println("events: " + eventList.size());
		System.out.println("faculty: " + facultyList.size());
		System.out.println("batch: " + batchList.size());
		System.out.println("room: " + roomList.size());
		boolean created = checkDirectoryPath(path);
		publishAllBatchesTimetables(path);
		publishAllFacultyTimetables(path);
		publishAllRoomTimetables(path);
		publishAllBatchHTML(path, tableId);
		publishAllFacultyHTML(path, tableId);
		publishAllRoomHTML(path, tableId);
		if(created) {
			throw new TimetableException("Some directories were created during " +
					"generating Timetables.<br/>You might not have the necessary " +
					"javascript file for Drag and Drop in HTMLs.");
		}
	}
	
	public void publishAllFacultyHTML(String dirName, String tableId) throws TimetableException,IOException {
		
		ArrayList<Faculty> facultyList = this.facultyList;

		for (int i = 0; i < facultyList.size(); i++) {
			String facultyId = facultyList.get(i).getId();
			String facultyName = facultyList.get(i).getName();
			@SuppressWarnings("unused")
			String facultyDepartment = facultyList.get(i).getDepartment();
			FileWriter outFile = new FileWriter(dirName + "/html/faculty/" + facultyId + " " + facultyName + ".html");
			PrintWriter out = new PrintWriter(outFile);

			String text = getHTMLInit(facultyName, facultyId, "faculty", tableId);
			
			text += "<div id=\"main_container\">" +
					"<div id=\"drag\">";
			
			//String table1 = getTableOne();
			//text += table1;
			
			String table2 = getTableTwo("faculty", facultyId);
			text += table2;
			
			text += "</div><!-- drag container -->" +
					"</div><!-- main container -->" +
					"</body>" +
					"</html>";
				
			out.println(text);
			out.close();
		}
	}
	
	public void publishAllRoomHTML(String dirName, String tableId) throws TimetableException,IOException {
		
		ArrayList<Room> roomList = this.roomList;

		for (int i = 0; i < roomList.size(); i++) {
			if(roomList.get(i) == null) {
				continue;
			}
			String roomCode = roomList.get(i).getCode();
			FileWriter outFile = new FileWriter(dirName + "/html/room/" + roomCode + " " + ".html");
			PrintWriter out = new PrintWriter(outFile);

			String text = getHTMLInit("", roomCode, "room", tableId);
			
			text += "<div id=\"main_container\">" +
					"<div id=\"drag\">";
			
			//String table1 = getTableOne();
			//text += table1;
			
			String table2 = getTableTwo("room", roomCode);
			text += table2;
			
			text += "</div><!-- drag container -->" +
					"</div><!-- main container -->" +
					"</body>" +
					"</html>";
			
			out.println(text);
			out.close();
		}
	}
	
	public void publishAllBatchHTML(String dirName, String tableId) throws TimetableException,IOException {
		
		ArrayList<Batch> batches = batchList;

		for (int i = 0; i < batches.size(); i++) {
			String batchCode = batches.get(i).getId();
			String batchName = batches.get(i).getName();
			FileWriter outFile = new FileWriter(dirName + "/html/batch/" + batchCode + ".html");
			PrintWriter out = new PrintWriter(outFile);

			String text = getHTMLInit(batchName, batchCode, "batch", tableId);
			
			text += "<div id=\"main_container\">" +
					"<div id=\"drag\">";
			
			//String table1 = getTableOne();
			//text += table1;
			
			String table2 = getTableTwo("batch", batchCode);
			text += table2;
			
			text += "</div><!-- drag container -->" +
					"</div><!-- main container -->" +
					"</body>" +
					"</html>";
			
			out.println(text);
			out.close();
		}
	}
	
	public void publishAllFacultyTimetables(String dirName) throws TimetableException, DocumentException {
		
		ArrayList<Faculty> facultyList = this.facultyList;

		for (int i = 0; i < facultyList.size(); i++) {
			String facultyId = facultyList.get(i).getId();
			String facultyName = facultyList.get(i).getName();
			String facultyDepartment = facultyList.get(i).getDepartment();
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream(dirName + "/pdf/faculty/" + facultyId + " " + facultyName + ".pdf"));
			} catch (Exception e) {
				e.printStackTrace();
				throw new TimetableException(e.getMessage());
			}

			document.open();
			try{
				iitrLogo = Image.getInstance(dirName + "/../images/IITR Logo.jpg");
				iitrLogo.setAbsolutePosition(70, 750);
				iitrLogo.scaleAbsolute(50,50);
				document.add(iitrLogo);
			}
			catch(Exception e){
			//	System.out.println("Error while loading Institute logo.");
			//	Methods.addErrorMsg("Error while loading Institute logo.");
				//System.out.println(e.getMessage());
			}
			Font f;
			Paragraph p;
			GregorianCalendar g = new GregorianCalendar();
			f = new Font(Font.UNDEFINED,10,Font.ITALIC);
			p = new Paragraph("Dated: "+months[g.get(Calendar.MONTH)]+" "+g.get(Calendar.DAY_OF_MONTH)+", "+g.get(Calendar.YEAR),f);
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);

			f = new Font(Font.TIMES_ROMAN,Font.DEFAULTSIZE,Font.BOLD);
			p= new Paragraph("Indian Institute of Technology, Roorkee",f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			p = new Paragraph(department,f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			f = new Font(Font.TIMES_ROMAN,Font.DEFAULTSIZE,Font.NORMAL);
			p = new Paragraph("Spring Semester 2010-2011",f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);

				/*f = new Font(Font.TIMES_ROMAN,10,Font.NORMAL);
			p=new Paragraph("               Batch: "+batchCode,f);
			p.setFirstLineIndent(10);
			p.setAlignment(Element.ALIGN_LEFT);
			document.add(p);*/

			PdfPTable table = new PdfPTable(colWidths);
			PdfPCell cell = new PdfPCell();

			table.setWidthPercentage(80);
			table.setSpacingBefore(25f);
			table.setSpacingAfter(10f);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setUseBorderPadding(true);

			f = new Font(Font.UNDEFINED,10,Font.BOLD | Font.UNDERLINE);
			p = new Paragraph(facultyName,f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);

			f = new Font(Font.UNDEFINED,9,Font.BOLD);
			p=new Paragraph("Time\\Day",f);
			cell = new PdfPCell(p);
			cell.setFixedHeight(20f);
			cell = getSpecialCell(cell);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			f = new Font(Font.UNDEFINED,9,Font.BOLD);
			for(int k = Constants.MONDAY ; k <= Constants.FRIDAY; k++) {
				Paragraph dayText = new Paragraph(days[k],f);
				cell = new PdfPCell(dayText);
				cell.setFixedHeight(20f);
				cell = getSpecialCell(cell);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}

			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			table.getDefaultCell().setFixedHeight(60f);
			table.getDefaultCell().setPadding(2);
			table.getDefaultCell().setPaddingBottom(2);
			table.getDefaultCell().setUseAscender(true);
			table.getDefaultCell().setUseDescender(true);

			for(int slot = 1; slot < noOfRows; slot++) {
				for(int day = 0; day < noOfCols ; day++){
					if(day == 0){
						table.addCell(getDayCell(slot));
					}
					else if(slot == breakSlot){
						table.addCell(getBreakCell());
						day = noOfCols;
					}
					else{
						int j = (day - 1) * 8 + slot - 1 - ((slot > breakSlot) ? 1 : 0);
						String cellText = null;
						ArrayList<Event> events = Methods.getEventListBySlot(j, eventList);
						for (Event event : events) {
							if(event.hasFixedSchedule()) {
								continue;
							}
							//for(Faculty fth : event.getFacultyList()){
								if (event.getFaculty().getId().equals(facultyId)) {
									String batches = "";
									for (Batch bth : event.getBatchList()){
										batches = batches + bth.getId() + ", ";
									}
									if(!batches.equals(""))
										batches = batches.substring(0, batches.length()-2);
									Room r = event.getRoom();
									String room = "null";
									if(r!=null) room = r.getCode();
									cellText = CLASS_CODES[event.getType()] 
									                       + event.getCourse().getCourseCode() + "\n" 
									                       + room + "\n"
									                       + batches;
									break;
								}
							//}
						}
						if(null == cellText) cellText = "  " + "\n" + " ";
						table.addCell(getNormalCell(cellText));
					}
				}
			}
			document.add(table);
			document.close();
		}
	}
	public void publishAllBatchesTimetables(String dirName) throws TimetableException, DocumentException {
		
		ArrayList<Batch> batches = batchList;
		
		for (int i = 0; i < batches.size(); i++) {
			String batchCode = batches.get(i).getId();
			String batchName = batches.get(i).getName();
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream(dirName + "/pdf/batch/" + batchCode + ".pdf"));
			} catch (Exception e) {
				e.printStackTrace();
				throw new TimetableException(e.getMessage());
			}

			document.open();
			try{
				iitrLogo = Image.getInstance(dirName + "/../images/IITR Logo.jpg");
				iitrLogo.setAbsolutePosition(70, 750);
				iitrLogo.scaleAbsolute(50,50);
				document.add(iitrLogo);
			}
			catch(Exception e){
			//	System.out.println("Error while loading Institute logo.");
			//	Methods.addErrorMsg("Error while loading Institute logo.");
				//System.out.println(e.getMessage());
			}
			Font f;
			Paragraph p;
			GregorianCalendar g = new GregorianCalendar();
			f = new Font(Font.UNDEFINED,10,Font.ITALIC);
			p = new Paragraph("Dated: "+months[g.get(Calendar.MONTH)]+" "+g.get(Calendar.DAY_OF_MONTH)+", "+g.get(Calendar.YEAR),f);
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);
			
			f = new Font(Font.TIMES_ROMAN,Font.DEFAULTSIZE,Font.BOLD);
			p= new Paragraph("Indian Institute of Technology, Roorkee",f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			p = new Paragraph(department,f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			f = new Font(Font.TIMES_ROMAN,Font.DEFAULTSIZE,Font.NORMAL);
			p = new Paragraph("Spring Semester 2010-2011",f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			f = new Font(Font.TIMES_ROMAN,10,Font.NORMAL);
			p=new Paragraph("               Branch: " + batchName,f);
			p.setFirstLineIndent(10);
			p.setAlignment(Element.ALIGN_LEFT);
			document.add(p);
			
			PdfPTable table = new PdfPTable(colWidths);
			PdfPCell cell = new PdfPCell();
			
			table.setWidthPercentage(80);
			table.setSpacingBefore(25f);
			table.setSpacingAfter(10f);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setUseBorderPadding(true);
			
			f = new Font(Font.UNDEFINED,10,Font.BOLD | Font.UNDERLINE);
			p = new Paragraph("Batch: " + batchCode,f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			f = new Font(Font.UNDEFINED,9,Font.BOLD);
			p=new Paragraph("Time\\Day",f);
			cell = new PdfPCell(p);
			cell.setFixedHeight(20f);
			cell = getSpecialCell(cell);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			f = new Font(Font.UNDEFINED,9,Font.BOLD);
			for(int k = Constants.MONDAY ; k <= Constants.FRIDAY; k++) {
				Paragraph dayText = new Paragraph(days[k],f);
				cell = new PdfPCell(dayText);
				cell.setFixedHeight(20f);
				cell = getSpecialCell(cell);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}
			
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			table.getDefaultCell().setFixedHeight(60f);
			table.getDefaultCell().setPadding(2);
			table.getDefaultCell().setPaddingBottom(2);
			table.getDefaultCell().setUseAscender(true);
			table.getDefaultCell().setUseDescender(true);
			
			for(int slot = 1; slot < noOfRows; slot++) {
				for(int day = 0; day < noOfCols ; day++){
					if(day == 0){
						table.addCell(getDayCell(slot));
					}
					else if(slot == breakSlot){
						table.addCell(getBreakCell());
						day = noOfCols;
					}
					else{
						int j = (day - 1) * 8 + slot - 1 - ((slot > breakSlot) ? 1 : 0);
						String cellText = null;
						ArrayList<Event> events = Methods.getEventListBySlot(j, eventList);
						for (Event event : events) {
							if(event.hasFixedSchedule()) {
								continue;
							}
							for (Batch bth : event.getBatchList()){
								if (bth.getId().equals(batchCode)) {
									Room r = event.getRoom();
									String room = "null";
									if(r!=null) room = r.getCode();
									cellText = CLASS_CODES[event.getType()] 
									                       + event.getCourse().getCourseCode() + "\n" 
									                       + room + "\n"
									                       + event.getProfessor().getId();
									break;
	
								}
							}
						}
						if(null == cellText) cellText = "  " + "\n" + " ";
						table.addCell(getNormalCell(cellText));
					}
				}
			}
			
			document.add(table);
			document.close();
		}
	}
	
	public void publishAllRoomTimetables(String dirName) throws TimetableException, DocumentException {
		
		ArrayList<Room> rooms = roomList;
		
		for (int i = 0; i < rooms.size(); i++) {
			if(rooms.get(i) == null) {
				continue;
			}
			String roomCode = rooms.get(i).getCode();
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream(dirName + "/pdf/room/" + roomCode + ".pdf"));
			} catch (Exception e) {
				e.printStackTrace();
				throw new TimetableException(e.getMessage());
			}

			document.open();
			try{
				iitrLogo = Image.getInstance(dirName + "/../images/IITR Logo.jpg");
				iitrLogo.setAbsolutePosition(70, 750);
				iitrLogo.scaleAbsolute(50,50);
				document.add(iitrLogo);
			}
			catch(Exception e){
				//System.out.println("Error while loading Institute logo.");
				//Methods.addErrorMsg("Error while loading Institute logo.");
				//System.out.println(e.getMessage());
			}
			Font f;
			Paragraph p;
			GregorianCalendar g = new GregorianCalendar();
			f = new Font(Font.UNDEFINED,10,Font.ITALIC);
			p = new Paragraph("Dated: "+months[g.get(Calendar.MONTH)]+" "+g.get(Calendar.DAY_OF_MONTH)+", "+g.get(Calendar.YEAR),f);
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);
			
			f = new Font(Font.TIMES_ROMAN,Font.DEFAULTSIZE,Font.BOLD);
			p= new Paragraph("Indian Institute of Technology, Roorkee",f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			p = new Paragraph(department,f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			f = new Font(Font.TIMES_ROMAN,Font.DEFAULTSIZE,Font.NORMAL);
			p = new Paragraph("Spring Semester 2010-2011",f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			PdfPTable table = new PdfPTable(colWidths);
			PdfPCell cell = new PdfPCell();
			
			table.setWidthPercentage(80);
			table.setSpacingBefore(25f);
			table.setSpacingAfter(10f);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setUseBorderPadding(true);
			
			f = new Font(Font.UNDEFINED,10,Font.BOLD | Font.UNDERLINE);
			p = new Paragraph("Room: "+roomCode,f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			f = new Font(Font.UNDEFINED,9,Font.BOLD);
			p=new Paragraph("Time\\Day",f);
			cell = new PdfPCell(p);
			cell.setFixedHeight(20f);
			cell = getSpecialCell(cell);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			f = new Font(Font.UNDEFINED,9,Font.BOLD);
			for(int k = Constants.MONDAY ; k <= Constants.FRIDAY; k++) {
				Paragraph dayText = new Paragraph(days[k],f);
				cell = new PdfPCell(dayText);
				cell.setFixedHeight(20f);
				cell = getSpecialCell(cell);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}
			
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			table.getDefaultCell().setFixedHeight(60f);
			table.getDefaultCell().setPadding(2);
			table.getDefaultCell().setPaddingBottom(2);
			table.getDefaultCell().setUseAscender(true);
			table.getDefaultCell().setUseDescender(true);
			
			for(int slot = 1; slot < noOfRows; slot++) {
				for(int day = 0; day < noOfCols ; day++){
					if(day == 0){
						table.addCell(getDayCell(slot));
					}
					else if(slot == breakSlot){
						table.addCell(getBreakCell());
						day = noOfCols;
					}
					else{
						int j = (day - 1) * 8 + slot - 1 - ((slot > breakSlot) ? 1 : 0);
						String cellText = null;
						ArrayList<Event> events = Methods.getEventListBySlot(j, eventList);
						for (Event event : events) {
							if(event.hasFixedSchedule()) {
								continue;
							}
							if (event.getRoom() == null) continue;
							if (event.getRoom().getCode().equals(roomCode)) {
								String batches = "";
								for (Batch bth : event.getBatchList()){
									batches=batches+bth.getId()+", ";
								}
								if(!batches.equals(""))
									batches=batches.substring(0, batches.length()-2);
								cellText = CLASS_CODES[event.getType()] 
								                       + event.getCourse().getCourseCode() + "\n" 
								                       + event.getProfessor().getId() + "\n"
								                       + batches;
								break;

							}
						}
						if(null == cellText) cellText = "  " + "\n" + " ";
						table.addCell(getNormalCell(cellText));
					}
				}
			}
			
			document.add(table);
			document.close();
		}
	}
	
	private PdfPCell getDayCell(int slot) {
		Font f = new Font(Font.UNDEFINED,9,Font.UNDEFINED);
		Paragraph p = new Paragraph(slots[slot],f);
		return getSpecialCell(new PdfPCell(p));
	}
	private PdfPCell getBreakCell() {
		Font f = new Font(Font.HELVETICA,12,Font.BOLDITALIC);
		Paragraph p = new Paragraph("Break",f);
		PdfPCell cell = new PdfPCell(p);
		cell.setColspan(5);
		cell = getSpecialCell(cell);
		//cell.setBorderWidth(1f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(30f);
		return cell;
	}
	private PdfPCell getSpecialCell(PdfPCell cell){
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(2);
		cell.setGrayFill(0.8f);
		cell.setBorderWidth(1.1f);
		cell.setUseAscender(true);
		cell.setUseDescender(true);
		cell.setUseBorderPadding(true);
		return cell;
	}
	private PdfPCell getNormalCell(String cellText){
		Font f = new Font(Font.UNDEFINED,10,Font.NORMAL);
		Paragraph p = new Paragraph(cellText,f);
		PdfPCell cell = new PdfPCell(p);
		cell.setLeading(1f, 1.2f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(2);
		cell.setBorderWidth(1f);
		cell.setFixedHeight(60f);
		cell.setUseAscender(true);
		cell.setUseDescender(true);
		cell.setUseBorderPadding(true);
		return cell;
	}
	
	private String getTableTwo(String tableFor, String code) throws TimetableException{
		String p;
		String text = "<div id=\"right\">" +
					"<table id=\"table2\">" +
					"	<colgroup>";
		for(int day = 0; day < noOfCols; day++) {
			text += "<col width=\"100\"/>";
		}
		text +=	"	</colgroup>" +
					"	<tbody>" +
						"<tr><td class=\"mark dark\">Time\\Day</td>";
	
		for(int k = Constants.MONDAY ; k <= Constants.FRIDAY; k++) {
			text += "<td class=\"mark dark\">" + days[k] + "</td>";
		}
		text += "</tr>";
		int tableSlot = 0;
		for(int slot = 1; slot < noOfRows; slot++) {
			text += "<tr>";
			for(int day = 0; day < noOfCols ; day++){
				text += "\n";
				if(0==day){
					text += "<td class=\"mark dark\">";
					p = slots[slot];
					text += p;
					text += "</td>";
				}
				else if(slot == breakSlot){
					int x = noOfCols-1;
					text += "<td class=\"mark lunch\" colspan=\"" + x + "\">";
					p = "Break";
					text += p;
					day = noOfCols;
					text += "</td>";
				}
				else{
					tableSlot++;
					int j = (day - 1) * 8 + slot - 1 - ((slot > breakSlot) ? 1 : 0);
					text += "<td id=\"tableSlot=" + j +"\">";
					String cellText = null;
					ArrayList<Event> events = Methods.getEventListBySlot(j, eventList);
					for (Event event : events) {
						if(event.hasFixedSchedule()) {
							continue;
						}
						if(tableFor == "faculty") {
							//for(Faculty fth : event.getFacultyList()) {
								if (event.getFaculty().getId().equals(code)) {
									String batches = "";
									for (Batch bth : event.getBatchList()) {
										batches=batches+bth.getId()+", ";
									}
									if(!batches.equals("")) {
										batches=batches.substring(0, batches.length()-2);
									}
									Room r = event.getRoom();
									String room = "null";
									if(r!=null) room = r.getCode();
									cellText = "<div id=\"eventId=" + event.getId()   
											+ "&type=" + event.getRequiredSlots() + "\" class=\"drag green\">"
											+ CLASS_CODES[event.getType()] 
											+ event.getCourse().getCourseCode() + "<br/>" 
					                        + room + "<br/>"
					                        + batches
					                        + "</div>";
									break;
								}
							//}
						}
						else if(tableFor == "batch") {
							for (Batch bth : event.getBatchList()) {
								if (bth.getId().equals(code)) {
									Room r = event.getRoom();
									String room = "null";
									if(r!=null) room = r.getCode();
									cellText = "<div id=\"eventId=" + event.getId()  
											+ "&type=" + event.getRequiredSlots() + "\" class=\"drag green\">"
										    + CLASS_CODES[event.getType()] 
					                        + event.getCourse().getCourseCode() + "<br/>" 
					                        + room + "<br/>"
					                        + event.getProfessor().getId()
					                        + "</div>";
									break;
								}
							}
						}
						else if(tableFor == "room") {
							if (event.getRoom() == null) continue;
							if (event.getRoom().getCode().equals(code)) {
								String batches = "";
								String faculty = event.getFaculty().getId();
								for (Batch bth : event.getBatchList()) {
									batches=batches+bth.getId()+", ";
								}
								if(!batches.equals("")) {
									batches=batches.substring(0, batches.length()-2);
								}
								cellText = "<div id=\"eventId=" + event.getId()   
										+ "&type=" + event.getRequiredSlots() + "\" class=\"drag green\">"
										+ CLASS_CODES[event.getType()] 
										+ event.getCourse().getCourseCode() + "<br/>" 
				                        + faculty + "<br/>"
				                        + batches
				                        + "</div>";
								break;
							}
							
						}
					}
					if(null == cellText) cellText = "  ";
					p = cellText;
					text += p;
					text += "</td>";
				}
			}
			text += "</tr>";
		}
		text += "</tbody>" +
		"</table>" +
		"</div><!-- right container -->";
		return text;
	}

	/*private String getTableOne(){
		String table1 = "<div id=\"left\">" +
		"<table id=\"table1\">" +
		"	<colgroup>" +
		"		<col width=\"100\"/>" +
		"	</colgroup>" +
		"	<tbody>"; 
		for(int i = 0; i < 6; i++) {
			table1 += "<tr><td class=\"dark\"><div id=\"\" class=\"drag green\">Hello" + i +"</div></td></tr>";
		}
		
		table1 +="</tbody>" +
		"</table>		" +		
		"</div><!-- left container -->";
		return table1;
	}*/
	private String getHTMLInit(String name, String code, String initFor, String tableId){
		String heading_1 = "";
		String heading_2 = "";
		if(initFor.equals("batch")) {
			heading_1 = "Branch: " + name;
			heading_2 = "Batch: " + code;
		}
		else if(initFor.equals("faculty")) {
			heading_1 = "Faculty: " + code;
			heading_2 = "Proff. " + name;
		}
		else if(initFor.equals("room")) {
			heading_1 = "&nbsp;";
			heading_2 = "Room: " + code;
		}
		String text = "<html><head><script type=\"text/javascript\" src=\"../../../../../../drag.js\"></script>" +
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"../../../../../../style.css\" /><br/>" +
				"<br/><!-- initialize drag and drop -->" +
				"<script type=\"text/javascript\">\n" +
				"<!--\n" +
				"window.onload = function () {" +
				"	REDIPS.drag.init();" +
				"	REDIPS.drag.clone_ctrlKey = false;" +
				"	REDIPS.drag.drop_option = 'switch';" + 
				"	REDIPS.drag.hover_color = '#9BB3DA';" +
				"	REDIPS.drag.trash_ask = false;" +
				"	REDIPS.drag.myhandler_dropped = function () {" +
				"		var obj         = REDIPS.drag.obj;				" +
				"		var obj_old     = REDIPS.drag.obj_old;			" +
				"		var target_cell = REDIPS.drag.target_cell;		" +
				"		var target_row  = REDIPS.drag.target_cell.parentNode;" +
				"		var marked_cell = REDIPS.drag.marked_cell;			" +
				"		var mark_cname  = REDIPS.drag.mark_cname;			" +
				"		var i, obj_new, mark_found, id;					" +
						"		if (document.getElementById('week').checked === true && obj_old.className.indexOf('clone') > -1) {" +
						"			for (i = 0; i < target_row.cells.length; i++) {" +
						"				if (target_cell === target_row.cells[i]){" +
						"					continue;" +
						"				}" +
						"				if (target_row.cells[i].childNodes.length > 0) {" +
						"					continue;" +
						"				}" +
						"				mark_found = target_row.cells[i].className.indexOf(mark_cname) > -1 ? true : false;" +
						"				if ((mark_found === true && marked_cell === 'deny') || (mark_found === false && marked_cell === 'allow')) {" +
						"					continue;" +
						"				}" +
						"				obj_new = obj.cloneNode(false);" +
						"				id = obj.id.substring(0, 2);" +
						"				obj_new.id = id + 'c' + REDIPS.drag.cloned_id[id];" +
						"				REDIPS.drag.cloned_id[id] += 1;" +
						"				obj_new.onmousedown = REDIPS.drag.handler_onmousedown;" +
						"				target_row.cells[i].appendChild(obj_new);" +
						"			}" +
						"		}" +
						"	}" +
				"	}\n" +
				"//-->\n" +
				"</script></head>";
		GregorianCalendar g = new GregorianCalendar();
		String p = "<div class=\"date\"><p align=\"right\"> <i>Dated: "+months[g.get(Calendar.MONTH)]+" "+g.get(Calendar.DAY_OF_MONTH)+", "+g.get(Calendar.YEAR)+"</i></p></div>";
		text += p;
		p = "<h2 align=\"center\">Indian Institute of Technology, Roorkee</h2>";
		text += p;
		p = "<h4 align=\"center\">" + department + "</h4>";
		text += p;
		p = "<p align=\"center\">Spring Semester 2010-2011</p>";
		text += p;
		text += "\n\n<form method=\"post\" action=\"/brihaspati/servlet/brihaspati/template/call%2C" +
					"Timetable%2Cbfr_html.vm/action/GenerateTimeTable\">" +
				"	<input type=\"hidden\" name=\"eventSlotMap\" value=\"\" />" +
				"	<input type=\"hidden\" name=\"tableId\" value=\"" + tableId + "\" />" +
				"	<span class=\"heading_1\">" + heading_1 + "</span>" +
				"	<input class=\"submit_button\" type=\"submit\"  name=\"eventSubmit_doVerification\" " +
				"		value=\"Check availability\" onClick=\"save(this.form)\" />" +
				"</form>\n\n";
		p = "<p class=\"heading_2\">" + heading_2 + "</p>";
		text += p;
		return text;
	}
	private boolean checkDirectoryPath(String path) throws TimetableException {
		String rootPath = path;
		boolean created = false;
		File root = new File(rootPath);
		File html = new File(rootPath + "/html/");
		File pdf = new File(rootPath + "/pdf/");
		File htmlB = new File(rootPath + "/html/batch/");
		File htmlF = new File(rootPath + "/html/faculty/");
		File htmlR = new File(rootPath + "/html/room/");
		File pdfB = new File(rootPath + "/pdf/batch/");
		File pdfF = new File(rootPath + "/pdf/faculty/");
		File pdfR = new File(rootPath + "/pdf/room/");
		created = createDirectory(root);
		created = createDirectory(htmlB);
		created = createDirectory(htmlF);
		created = createDirectory(htmlR);
		created = createDirectory(pdfB);
		created = createDirectory(pdfF);
		created = createDirectory(pdfR);
		return created;
	}
	private boolean createDirectory(File file) throws TimetableException {
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
