package org.iitk.brihaspati.modules.screens.call.Timetable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.torque.util.BasePeer;
import org.iitk.brihaspati.om.Batch;
import org.iitk.brihaspati.om.BatchCourse;
import org.iitk.brihaspati.om.BatchCoursePeer;
import org.iitk.brihaspati.om.BatchPeer;
import org.iitk.brihaspati.om.CourseInfo;
import org.iitk.brihaspati.om.CourseInfoPeer;
import org.iitk.brihaspati.om.FacInfo;
import org.iitk.brihaspati.om.FacInfoPeer;
import org.iitk.brihaspati.om.FacultyCourse;
import org.iitk.brihaspati.om.FacultyCoursePeer;
import org.iitk.brihaspati.om.Venue;
import org.iitk.brihaspati.om.VenuePeer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseXml {

	public ParseXml(String xmlFile) throws TimetableException, SQLException {
		this.xmlFile=xmlFile;
		parseXmlFile(xmlFile);
		try {
			parseDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Document dom;
	String xmlFile;

	public void parseXmlFile(String xmlFile) throws TimetableException{
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			dom = db.parse(xmlFile);


		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
			throw new TimetableException("Error in xml configuration");
		}catch(SAXException se) {
			se.printStackTrace();
			throw new TimetableException("Error while parsing xml file");
		}catch(IOException ioe) {
			ioe.printStackTrace();
			throw new TimetableException("Input/Output Error");
		}
	}

	public void parseDocument() throws Exception {

		// get the root element -> workbook
		Element docEle=dom.getDocumentElement();

		// get a nodelist of <sheet> elements
		NodeList nl = docEle.getElementsByTagName("sheet");
		if( nl != null && nl.getLength() > 0 ) {

			deleteBatchInfo();
			deleteBatchCourseInfo();
			deleteCourseInfo();
			deleteFacultyInfo();
			deleteFacultyCourse();
			deleteVenue();

			for(int i=0;i<nl.getLength();i++) {

				// get the sheet element
				Element el=(Element)nl.item(i);

				//get the nodelist of <row> elements
				NodeList nlRow=el.getElementsByTagName("row");
				if(nlRow!=null && nlRow.getLength()>0) {
					for(int j=2;j<nlRow.getLength();j++) {

						//get the row element
						Element e=(Element)nlRow.item(j);

						//String number=e.getAttribute("number");

						//get the object corresponding to Batch/Course/Faculty
						if(i == 0) {
							InsertBatchInfo(e);
						} else if(i == 1) {
							InsertCourseInfo(e);
						} else if(i == 2) {
							InsertFacultyInfo(e);
						}
						else if(i == 3){
							InsertVenueInfo(e);
						}
					}

				}
			}

		}
	}

	private void InsertVenueInfo(Element e) throws Exception {
		NodeList nl = e.getElementsByTagName("col");
		if(null != nl && nl.getLength() > 0) {
			String venueCode = "";
			int capacity = 0;
			int numComputer = 0;
			int projector = 0;
			int type = 0;

			for(int k = 0; k < nl.getLength(); k++) {

				Element col = (Element)nl.item(k);
				int number = Integer.parseInt(col.getAttribute("number"));

				switch(number) {
				case 0:
					venueCode = getTextValue(col);
					break;
				case 1:
					capacity = Integer.parseInt(getTextValue(col));
					break;
				case 2:
					numComputer = Integer.parseInt(getTextValue(col));
					break;
				case 3:
					projector = Integer.parseInt(getTextValue(col));
					break;
				case 4:
					type = Integer.parseInt(getTextValue(col));
					break;
				}
			}
			Venue venue = new Venue();
			venue.setCapacity(capacity);
			venue.setCode(venueCode);
			venue.setNcomputers(numComputer);
			venue.setProjector(projector);
			venue.setType(type);
			venue.save();
		}
	}


	private void InsertFacultyInfo(Element e) throws Exception {
		NodeList nl = e.getElementsByTagName("col");
		if(null != nl && nl.getLength() > 0) {
			// name, department, institute, id
			String facultyId = "";
			String facultyName = "";
			String department = "";
			String institute = "";
			ArrayList<String> courseCodes = new ArrayList<String>();
			for(int k = 0; k < nl.getLength(); k++) {

				Element col = (Element)nl.item(k);

				int number = Integer.parseInt(col.getAttribute("number"));

				switch(number) {
				case 0:
					facultyName = getTextValue(col);
					break;
				case 1:
					facultyId = getTextValue(col);
					break;
				case 2:
					department = getTextValue(col);
					break;
				case 3:
					institute = getTextValue(col);
					break;
				default:
					courseCodes.add(getTextValue(col));
				}

			}

			FacInfo facInfo = new FacInfo();
			facInfo.setId(facultyId);
			facInfo.setName(facultyName);
			facInfo.setDepartment(department);
			facInfo.setInstitute(institute);
			facInfo.save();

			for(String course_code:courseCodes) {
				FacultyCourse fc = new FacultyCourse();
				fc.setCourseCode(course_code);
				fc.setFacultyId(facultyId);
				fc.save();
			}
		}

	}

	private void InsertCourseInfo(Element e) throws Exception {
		NodeList nl = e.getElementsByTagName("col");
		if(null != nl && nl.getLength() > 0) {
			// name, department, institute, id
			String courseCode = "";
			int numLectures = 0;
			int lecDuration = 0;
			int numTutorials = 0;
			int tutDuration = 0;
			int numPracticals = 0;
			int pracDuration = 0;
			int scheduled = 0;

			for(int k = 0; k < nl.getLength(); k++) {

				Element col = (Element)nl.item(k);

				int number = Integer.parseInt(col.getAttribute("number"));

				switch(number) {
				case 0:
					courseCode = getTextValue(col);
					break;
				case 2:
					numLectures = Integer.parseInt(getTextValue(col));
					break;
				case 3:
					lecDuration = Integer.parseInt(getTextValue(col));
					break;
				case 4:
					numTutorials = Integer.parseInt(getTextValue(col));
					break;
				case 5:
					tutDuration = Integer.parseInt(getTextValue(col));
					break;
				case 6:
					numPracticals = Integer.parseInt(getTextValue(col));
					break;
				case 7:
					pracDuration = Integer.parseInt(getTextValue(col));
					break;
				case 8:
					scheduled = Integer.parseInt(getTextValue(col));
					break;
				default:
					break;
				}

			}

			if(numLectures != 0) {
				String type = String.valueOf(Constants.CLASS_CODES[Constants.LECTURE]);

				CourseInfo c = new CourseInfo();
				c.setComputer(0);
				c.setProjector(0);
				c.setVenueCode("");
				c.setScheduled(scheduled);
				c.setCourseCode(courseCode);
				c.setCourseType(type);
				c.setEventsPerWeek(numLectures);
				c.setDuration(lecDuration);
				c.save();
			}

			if(numTutorials != 0) {
				String type = String.valueOf(Constants.CLASS_CODES[Constants.TUTORIAL]);

				CourseInfo c = new CourseInfo();
				c.setComputer(0);
				c.setProjector(0);
				c.setVenueCode("");
				c.setScheduled(scheduled);
				c.setCourseCode(courseCode);
				c.setCourseType(type);
				c.setEventsPerWeek(numTutorials);
				c.setDuration(tutDuration);
				c.save();
			}

			if(numPracticals != 0) {
				String type = String.valueOf(Constants.CLASS_CODES[Constants.LABORATORY]);

				CourseInfo c = new CourseInfo();
				c.setComputer(0);
				c.setProjector(0);
				c.setVenueCode("");
				c.setScheduled(scheduled);
				c.setCourseCode(courseCode);
				c.setCourseType(type);
				c.setEventsPerWeek(numPracticals);
				c.setDuration(pracDuration);
				c.save();
			}

		}


	}

	private void deleteVenue() throws Exception {
		BasePeer.executeStatement("delete from " + VenuePeer.TABLE_NAME);
	}

	private void deleteFacultyCourse() throws Exception {
		BasePeer.executeStatement("delete from " + FacultyCoursePeer.TABLE_NAME);
	}

	private void deleteFacultyInfo() throws Exception {
		BasePeer.executeStatement("delete from " + FacInfoPeer.TABLE_NAME);
	}

	private void deleteCourseInfo() throws Exception {
		BasePeer.executeStatement("delete from " + CourseInfoPeer.TABLE_NAME);
	}

	private void deleteBatchCourseInfo() throws Exception {
		BasePeer.executeStatement("delete from " + BatchCoursePeer.TABLE_NAME);
	}

	private void deleteBatchInfo() throws Exception {
		BasePeer.executeStatement("delete from " + BatchPeer.TABLE_NAME);
	}

	private void InsertBatchInfo(Element e) throws Exception {
		NodeList nl = e.getElementsByTagName("col");
		if(null != nl && nl.getLength() > 0) {
			String batchId = "";
			String batchName = "";
			int strength = 0;
			ArrayList<String> courseCodes = new ArrayList<String>();
			for(int k = 0; k < nl.getLength(); k++) {

				Element col = (Element)nl.item(k);

				int number = Integer.parseInt(col.getAttribute("number"));

				switch(number) {
				case 0:
					batchId = getTextValue(col);
					break;
				case 1:
					batchName = getTextValue(col);
					break;
				case 2:
					strength = Integer.parseInt(getTextValue(col));
					break;
				default:
					courseCodes.add(getTextValue(col));
				}

			}

			Batch b = new Batch();
			b.setBatchCode(batchId);
			b.setBatchName(batchName);
			b.setStrength(strength);
			b.save();

			for(String course_code:courseCodes) {
				BatchCourse bc = new BatchCourse();
				bc.setBatchCode(batchId);
				bc.setCourseCode(course_code);
				bc.save();
			}
		}
	}




	private String getTextValue(Element el) {            
		String textVal = new String();
		textVal = el.getFirstChild().getNodeValue();                         
		return textVal;                                               
	}               

	/*public static void main(String[] args) throws TimetableException, SQLException{
		//create an instance


		ParseXml px=new ParseXml();	
		px.parseXmlFile();
		px.parseDocument();


	}
	 */

}
