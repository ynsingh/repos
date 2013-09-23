package org.iitk.brihaspati.modules.utils;

/*
 * @(#)XMLWriter_StudentAttendance
 *
 *  Copyright (c) 2013 conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */

import java.io.File;
import java.io.FileOutputStream;

import java.util.List;
import java.util.Date;
import java.util.Vector;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.iitk.brihaspati.om.ParentInfo;
import org.iitk.brihaspati.om.ParentInfoPeer;

import org.apache.torque.util.Criteria;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @author <a href="tejdgurung20@gmail.com">Tej Bahadur</a>
 * @modifydate: 02-08-2013,22-09-2013(Tej)
 */

/**
 * class for hadling all the stuff of Student Attendance in xml file	
 * class file Create the Xml file, store and read the details of the file
 * and search the student attendance record and update the attendance record. 
 */

public class XMLWriter_StudentAttendance {
	
	/**
	 * method for define the structure of xml file and store the attendance record.
	 * @param filePath (String)
	 * @param studattendlist (Vector)
	 * @return String
	 */
	
	public static String StudentAttendanceListXml(String filePath, Vector studattendlist)
	{
		String message="UnSuccessfull";
                try
		{
			/**
			* Get instructor's full name for send information regarding absent students to their parent. 
			* file.getName()- This method returns the file name.
			* lastIndexOf()- This method returns the index within this string.
			* file.length()- This method returns the length of the file.
			* substring(0, index)-  This method returns a new string that is a substring of this string.
			* @see Util: UserUtil
			*/
			File fileName = new File(filePath);
			// This method returns the index within this string.
			int index = fileName.getName().lastIndexOf('.');
			String instFullName="";
			String groupName="";
      			if (index>0&& index <= fileName.getName().length() - 2 )
			{
				// Get file name
				groupName=fileName.getName().substring(0, index);
				// Get instructor Email if from GroupName(fileName)
				String instEmail= CourseUtil.getCourseInstrEmail(groupName);
				// Get instructor UserId from Email
				int instUid= UserUtil.getUID(instEmail);
				// Get Instructor FullName from UserID
				instFullName= UserUtil.getFullName(instUid);
      			}
			String courseName=CourseUtil.getCourseName(groupName);
			//Create instance of DocumentBuilderFactory
	               	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			//Get the DocumentBuilder	
        	        DocumentBuilder builder = factory.newDocumentBuilder();
			//Create blank DOM Document
                	Document doc = builder.parse(getFile(filePath));
                	// Getting all student details for store attendance in xml file	
			for(int i=0;i<studattendlist.size();i++)
			{
				CourseUserDetail cud=(CourseUserDetail)studattendlist.get(i);
                        	String userid=cud.getUserId().toString(); 	// Get User Id
                        	String email=cud.getEmail().toString();   	// Get Email Id
                        	String att_status=cud.getStatus().toString();	// Get Attendance Status
                        	String remark=cud.getRemarks().toString();   	// Get Remarks
                        	String date=cud.getCreateDate().toString();   	// Get Attendance Date
                        	String classtype=cud.getClassType().toString(); // Get Class Type like "Regular or Extra/Special"
                        	String attendkey=cud.getStudAttendKey(); 	// Get Student Attendace Key 

				// root element
                      		Element root = doc.getDocumentElement();
				//create the root element
                        	Element attendance = doc.createElement("Attendance");
			
				//creating tag or field for student attendance information
				Attr attr = doc.createAttribute("UserId");
				attr.setValue(userid);
				attendance.setAttributeNode(attr);
				
				// Set attribute Email Id
				attr = doc.createAttribute("Email");
                        	attr.setValue(email);
                        	attendance.setAttributeNode(attr);

				/**
				 * Set attendance attribute in xml file.
				 * Set Attendance Status Present as '1' and rest is '0' if it is equals to "p".
				 * here "p" means present.
				 **/
				if(att_status.equals("p"))
				{	
					attr = doc.createAttribute("Present");
        	                	attr.setValue("1");
                	        	attendance.setAttributeNode(attr); 
			
					attr = doc.createAttribute("Absent");
	                        	attr.setValue("0");
        	                	attendance.setAttributeNode(attr);

					attr = doc.createAttribute("Leave");
                       			attr.setValue("0");
          	              		attendance.setAttributeNode(attr);
				}

				/**
                                 * Set Attendance Status Absent as '1' and rest is '0' if it is equals to "a".
                                 * here "a" means absent.
                                 **/
				else if(att_status.equals("a"))
				{
					attr = doc.createAttribute("Present");
                        		attr.setValue("0");
                        		attendance.setAttributeNode(attr);

                       			attr = doc.createAttribute("Absent");
	                        	attr.setValue("1");
        	                	attendance.setAttributeNode(attr);

                	        	attr = doc.createAttribute("Leave");
                        		attr.setValue("0");
                        		attendance.setAttributeNode(attr);

					/**
					* This code is written for send absent students attendance to their parents.
					* Get parent information in vector array using student's UserId
					* @see util: CourseUserDetail
					* @see util: MailNotificationThread  
					*/
					Vector parentInfo=getParentInfo(userid);	// Get parent information
					if(parentInfo.size()>0)
					{
						try{
							// Get parent infromation
                                			CourseUserDetail cu=(CourseUserDetail)parentInfo.get(0);
                                			String parentEmail=cu.getEmail().toString();         	// Get Parent Email Id
                                			String parentFullName=cu.getUserName().toString();  	// Get Parent FullName
							String StudFullName= UserUtil.getFullName(Integer.parseInt(userid));// Get Student FullName
							// Set Message for sending mail to the parent
							String staus_msg= "Dear "+parentFullName +", <br><br>Your ward "+StudFullName +" ("+email+") is absent today. <br><br> With Regards,<br><br>"+instFullName+"<br>"+courseName;
							// This MailNotificationThread is used for send mail.
							String MailMsg =  MailNotificationThread.getController().set_Message(staus_msg, "", "", "", parentEmail, "Attendance status of your ward", "","");
					}
					catch(Exception e){
					ErrorDumpUtil.ErrorLog(" Exception in send mail to the parent---->>"+e);
					}
					}
				}
				 
				/**
                                 * Set Attendance Status Leave as '1' and rest is '0' if it is equals to "l".
                                 * here "l" means leave.
                                 **/
				else	
				{
					attr = doc.createAttribute("Present");
	                        	attr.setValue("0");
        	                	attendance.setAttributeNode(attr);
	
        	                	attr = doc.createAttribute("Absent");
                	        	attr.setValue("0");
        	                	attendance.setAttributeNode(attr);
	
                	        	attr = doc.createAttribute("Leave");
                        		attr.setValue("1");
                       			attendance.setAttributeNode(attr);
				}
				
				// Set attribute Remarks 
				attr = doc.createAttribute("Remark");
                        	attr.setValue(remark);
                        	attendance.setAttributeNode(attr);

				// Set attribute Attendance Date 
				attr = doc.createAttribute("Attendance_date");
                        	attr.setValue(date);
                        	attendance.setAttributeNode(attr);

				// Set attribute Class Type 
                                attr = doc.createAttribute("ClassType");
                                attr.setValue(classtype);
                                attendance.setAttributeNode(attr);
			
				// Set attribute Student Attendance Key
                                attr = doc.createAttribute("AttendanceKey");
                                attr.setValue(attendkey);
                                attendance.setAttributeNode(attr);

				//add in the root element and save attendance record.
               	        	root.appendChild(attendance);
                		message=saveXML(doc,filePath);
			}
                }
		catch(Exception ex)
		{
			ErrorDumpUtil.ErrorLog("Error in store student attendance record in xml file !! see XMLWriter_StudentAttendance.java util file--"+ex.getMessage());
                } 
		return message;
        }
	
	/**
	 * This method uses Xerces specific classes
	 * prints the XML document to file.
	 * @param doc (Document)
	 * @param filePath (String)
	 * @return String
         */ 
	
	private static  String saveXML(Document doc,String filePath) 
	{
        	try{
			//print
                        OutputFormat format = new OutputFormat(doc);
                        format.setIndenting(true);
			//to generate a file output use fileoutputstream
                        XMLSerializer output = new XMLSerializer(new FileOutputStream(filePath), format);
                        output.serialize(doc);
                        return "Successfull";
                }
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Exception in save xml file --"+e.getMessage());
		}
                return "UnSuccessfull";
        }
	
	/**
	 * method for creating blank xml file
	 * with root element
	 * @param filePath (String)
	 */
	
	private static File getFile(String filePath) {
                File file=new File(filePath);
                if(!file.exists()){
                        try {
				//Create instance of DocumentBuilderFactor
                                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				//Get the DocumentBuilder
                                DocumentBuilder builder = factory.newDocumentBuilder();
				//Create blank DOM Document
                                Document doc = builder.newDocument();
				//create the root element	
                                Element rootEle = doc.createElement("StudentAttendanceReport");
				//all it to the xml tre
                                doc.appendChild(rootEle);
                                saveXML(doc,filePath);
                        }
			catch (Exception ex) { 
				ErrorDumpUtil.ErrorLog("Exception in getFile Method in util (XMLWriter_StudentAttendance.java)"+ex.getMessage());
                	} 
		}	        
		return file;
	}
	
	/**
	 * method to get attendance date of student.
	 * @param filePath (String)
	 * @param attendancedate (String)
	 * @return boolean
	 */
	
	public static boolean getAttendanceDate(String filePath,String attendancedate )
	{
        	boolean AttenDateExist=false;
                Element element=null;
                Node node=null;
                try
		{
                	NodeList list = getNodeList(filePath);
			//check if file has entry
                        if(list!=null)
			{
                                for( int i=0; i<list.getLength(); i++ )
				{
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE )
					{
                                                element = ( Element )node;
						//get domain name from the file
						String attendance_date=element.getAttribute("Attendance_date");
						//check student attendance date  
                                                if(attendance_date.equals(attendancedate))
                                                        AttenDateExist=true;
                                        }
                                }
                        }
                }
		catch( Exception e )
		{
                         ErrorDumpUtil.ErrorLog("Exception in get attendance date of student in util (XMLWriter_StudentAttendance.java)"+e.getMessage());;
                }
                return AttenDateExist;
        }
	
	/**
	 * method for Create blank DOM Document
	 * @param filepath (String)
	 * @return document
	 */	
	
	private static Document getCreateDocument(String filePath )
	{
        	Document doc=null;
                try
		{
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        doc = builder.parse(getFile(filePath));

                }
		catch( Exception e )
		{
                        e.printStackTrace();
                }
                return doc;
        }
	
	/**
	 * method for get all elements with the name "Attendance"
	 * @param filepath (String)
	 * @return NodeList
	 */
	
	private static NodeList getNodeList(String filePath )
	{
        	NodeList list=null;
                try
		{
                        Document doc =getCreateDocument(filePath);
                        list = doc.getElementsByTagName("Attendance");

                }
		catch( Exception e )
		{
                        e.printStackTrace();
                }
                return list;
        }
	
	/**
	 * method for reading attendance details of student from xml file
         * @param filePath (String)
         * @param Userid (String)
         * @param Alldates (Vector)
         * @return Vector
         */
	
	public static Vector ReadStudAttendList(String filePath,String Userid,Vector Alldates)
	{
        	Vector v=new Vector();
                Element eElement=null;
                try
		{
                        //Create instance of DocumentBuilderFactory
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        //Get the DocumentBuilder
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        //Create blank DOM Document
                        Document doc = builder.parse(getFile(filePath));
                        //Normalize All of the Text in a Document
                        doc.getDocumentElement().normalize();
                        
			File f=new File(filePath);
                        if(f.exists())
			{
				if(Alldates.size()!=0){
                        	for(int j=0;j<Alldates.size();j++)
				{
					String date=Alldates.get(j).toString();
                                	int present=0;
                                	int absent=0;
                                	int leave=0;
                                	// Find all elements with the name "Attendance"
                                	NodeList nodeList = doc.getElementsByTagName("Attendance");
                                	for(int i=0; i<nodeList.getLength(); i++ )
					{
                                        	Node nNode = nodeList.item(i);
                                        	if (nNode.getNodeType() == Node.ELEMENT_NODE)
						{
                                               		eElement = (Element) nNode;
                                                	/** 
							* get the attribute value by passing element.
                                                 	* set the value in  CourseUserDetail object.
                                                 	* @see CourseUserDetail in utils
                                                 	*/
                                                	CourseUserDetail cu=new CourseUserDetail();
                                                	String attendance_date=eElement.getAttribute("Attendance_date");	//Get Attendance Date
                                                	String userid=eElement.getAttribute("UserId");				// Get User Id
							if(date.equals(attendance_date) && Userid.equals(userid))
							{
								String fullname=UserUtil.getFullName(Integer.parseInt(userid));
                                                		String email=eElement.getAttribute("Email"); 	// Get Email Id
                                                		String prst=eElement.getAttribute("Present");	// Get Present
								present = Integer.parseInt(prst);
								String abst=eElement.getAttribute("Absent");	// Get Absent
                                                		absent = Integer.parseInt(abst);
								String lve=eElement.getAttribute("Leave");	// Get Leave
                                                		leave = Integer.parseInt(lve);
                                                		String remark=eElement.getAttribute("Remark");	// Get Remarks
                                                		String classtype=eElement.getAttribute("ClassType");	// Get Class Type
                                                		String attendKey=eElement.getAttribute("AttendanceKey");	// Get Student Attendance Key                                                		
								// Set all values
								cu.setLoginName(fullname);	// Set LoginName 
                                                		cu.setUserId(userid);		// set UserId
                                                		cu.setEmail(email);		// Set Email Id
                                                		cu.setPresent(present);		// Set Present
                                                		cu.setAbsent(absent);		// Set Absent
                                                		cu.setLeave(leave);		// Set Leave
                                                		cu.setCreateDate(attendance_date);	// Set Attendance date
                                                		cu.setClassType(classtype);	// Set Class Type
                                                		cu.setStudAttendKey(attendKey);	// Set Student Attendance Key 
                                                		cu.setRemarks(remark);		// Set Student Attendance Remarks 
                                                		v.add(cu);
							}
                                                }
					}
				}
				}// end if
				else{
					int present=0;
                                        int absent=0;
                                        int leave=0;
                                        // Find all elements with the name "Attendance"
                                        NodeList nodeList = doc.getElementsByTagName("Attendance");
                                        for(int i=0; i<nodeList.getLength(); i++ )
                                        {
                                                Node nNode = nodeList.item(i);
                                                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                                                {
                                                        eElement = (Element) nNode;
                                                        /** 
                                                        * get the attribute value by passing element.
                                                        * set the value in  CourseUserDetail object.
                                                        * @see CourseUserDetail in utils
                                                        */
                                                        CourseUserDetail cu=new CourseUserDetail();
                                                        String userid=eElement.getAttribute("UserId");                          // Get User Id
                                                        String attendance_date=eElement.getAttribute("Attendance_date");        //Get Attendance Date
                                                        if((Userid.equals(userid)) && (!attendance_date.equals("")))
                                                        {
                                                                String fullname=UserUtil.getFullName(Integer.parseInt(userid));
                                                                String email=eElement.getAttribute("Email");    // Get Email Id
                                                                String prst=eElement.getAttribute("Present");   // Get Present
                                                                present = Integer.parseInt(prst);
                                                                String abst=eElement.getAttribute("Absent");    // Get Absent
                                                                absent = Integer.parseInt(abst);
                                                                String lve=eElement.getAttribute("Leave");      // Get Leave
                                                                leave = Integer.parseInt(lve);
                                                                String remark=eElement.getAttribute("Remark");  // Get Remarks
                                                		String classtype=eElement.getAttribute("ClassType");	// Get Class Type

                                                                // Set all values
                                                                cu.setLoginName(fullname);      // Set LoginName 
                                                                cu.setUserId(userid);           // set UserId
                                                                cu.setEmail(email);             // Set Email Id
                                                                cu.setPresent(present);         // Set Present
                                                                cu.setAbsent(absent);           // Set Absent
                                                                cu.setLeave(leave);             // Set Leave
                                                                cu.setCreateDate(attendance_date);// Set Attendance date
                                                		cu.setClassType(classtype);	// Set Class Type
                                                		cu.setRemarks(remark);		// Set Student Attendance Remarks 
                                                                v.add(cu);
                                                        }
                                                }
                                        }

				}// end else
			}
		}
               	catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Error in read student attendance details in util:(XMLWriter_StudentAttendance)"+e);
		}
                return v;
        }

	/**
         * method to get all attendance dates between two dates
         * @param startdate (String)
         * @param enddate (String)
         * @return Vector
         */

	public static Vector getAllDates(String startdate, String enddate)
	{
		Vector alldate=new Vector();
		SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
		Date st = null;
		Date ed = null;
		try
		{
			st = formatterDate.parse(startdate);
			ed = formatterDate.parse(enddate);
		}
		catch (ParseException e)
		{
			System.out.println("Parse Exception :"+e);
		}
		Calendar ss=Calendar.getInstance();
		Calendar ee=Calendar.getInstance();
		ss.setTime(st);
		ee.setTime(ed);
		ee.add(Calendar.DATE,1);//just incrementing end date by 1
		String day = "";
		while(!ss.equals(ee))
		{
			day = formatterDate.format(ss.getTime());
			alldate.add(day);
			ss.add(Calendar.DATE,1);
		}
		return alldate;
	}

	 /**
          * method for reading attendance details of student to their specific course from xml file
          * @param filePath (String)
          * @param courseId (String)
          * @param Userid (String)
          * @param Alldates (Vector)
          * @return Vector
          */

     	public static Vector getAttendReport(String filePath, String CourseId,String userId,Vector Alldates)
	{
		Vector v = new Vector();
                try
		{
			// Get groupId
			int g_id=GroupUtil.getGID(CourseId);
                        Vector userList=new Vector();
			
			// Get student list according to groupId(Course).
                        userList=UserGroupRoleUtil.getUDetail(g_id,3);
			String email="";
			String name="";
			String fullname="";
			String stRlNo="";
			
			/**
			 * Check userid values.
			 * Get student attendance details by Instructor and Institute Admin if userId is "NULL" or "blank".
			 */
			if((userId.equals(null)) ||(userId.equals("")))
			{
				for(int i=0;i<userList.size();i++)
				{
					// initialize attendance status as '0' for calculate total attendance of user.
					int present=0;
	                        	int absent=0;
        	                	int leave=0;
					String classType="";
					String attndKey="";
					String remarks="";
					CourseUserDetail cu=(CourseUserDetail)userList.get(i);
					String loginname=cu.getLoginName().toString();			// Get LoginName 
					String userid1=Integer.toString(UserUtil.getUID(loginname));	// Get UserId
					
					// Get student attendance details using their userid.
					Vector studattendlist1=ReadStudAttendList(filePath,userid1,Alldates);
					for(int j=0;j<studattendlist1.size();j++)
					{
						CourseUserDetail cud1=(CourseUserDetail)studattendlist1.get(j);
                                        	String userid2=cud1.getUserId().toString();
						
						// Calculate total attendance of student.
						present=present+cud1.getPresent();	// Total present
						absent=absent+cud1.getAbsent();		// Total absent
						leave=leave+cud1.getLeave();		// Total leave
						classType=cud1.getClassType();  	// Class Type
                                                attndKey=cud1.getStudAttendKey();	// Student Attendance Key 
                                                remarks=cud1.getRemarks(); 		// Student Attendance Remarks 
					}
					// Get Roll No.	
					stRlNo=CourseProgramUtil.getUserRollNo(loginname,CourseId);
					// Get email and fullName.	
					email=UserUtil.getEmail(Integer.parseInt(userid1));		// Email     
					fullname=UserUtil.getFullName(Integer.parseInt(userid1));   	// FullName
					// Set student attendance detail.
					CourseUserDetail cud=new CourseUserDetail();
                                	cud.setEmail(email);		// set Email
                                	cud.setLoginName(fullname);	// set LoginName
                                	cud.setUserId(userid1);		// set UserId
                                	cud.setPresent(present);	// set Present
                                	cud.setAbsent(absent);		// set Absent
                                	cud.setLeave(leave);		// set Leave
					cud.setRollNo(stRlNo);		// set Roll No.
					cud.setClassType(classType);    // Set Class Type
                                        cud.setStudAttendKey(attndKey);	// Set Student Attendance Key 
                                        cud.setRemarks(remarks);	// Set Student Attendance Remarks 
                                	v.add(cud);			// Store in Vector
				}
			}
			// Get attendance details by student.
			else
			{
				v= ReadStudAttendList(filePath,userId,Alldates);
			}
		}
                catch(Exception ex){
                        ErrorDumpUtil.ErrorLog("Exception in getting attendance report in util--------->>"+ex.getMessage());
                }
                return v; 
        }

	/**
	 * method for update the student attendance status in xml file
	 * @param filePath (String)
	 * @param userId (String)
	 * @param userList (Vector)
	 */
	
	public static String UpdateUserAttendance(String filePath,String userId,Vector userList) 
	{
		String message="Unsuccessfull";
		Node node=null;
		Element eElement=null;
                try 
		{
			// Get student attendance infromation from Vector 'userList'.
			for(int i=0;i<userList.size();i++) 
			{
                        	CourseUserDetail cud=(CourseUserDetail)userList.get(i);
                        	String att_status=cud.getStatus().toString();	// Attendance Status
                        	String date=cud.getCreateDate().toString();	// Attendance date
                        	String sakey=cud.getStudAttendKey().toString();	// Attendance Key
	
				/**
				 * Create blank DOM Document
        	                 * @see getCreateDocument
                	         */
				Document doc =getCreateDocument(filePath);

				/**Find all elements with the name "Attendance"*/
                        	NodeList list = doc.getElementsByTagName("Attendance");
                       		if(list!=null)
				{
                                	for( int j=0; j<list.getLength(); j++ )
					{
                                        	node = list.item(j);
                                        	if( node.getNodeType() == node.ELEMENT_NODE )
						{
                                                	eElement = ( Element )node;
					
							// get attribute value by passing Attribute name
							String xml_attendance_date=eElement.getAttribute("Attendance_date");	// Attendance Date
							String xml_userid=eElement.getAttribute("UserId");			// UserId
							String xml_sakey=eElement.getAttribute("AttendanceKey");		// Attendance Key
							if(date.equals(xml_attendance_date) && userId.equals(xml_userid) && (sakey.equals(xml_sakey) || sakey.equals("null")))	// Compare Attendance Date, UserId and Attendance Key
							{
								//  Set attendance status Present as '1' if att_status is "p". Here "p" means present.
								if(att_status.equals("p"))
								{
									Node present = eElement.getAttributes().getNamedItem("Present"); 
									present.setTextContent("1");					// Present ='1'
									Node absent = eElement.getAttributes().getNamedItem("Absent"); 
                                                			absent.setTextContent("0");					// Absent ='0' 
									Node leave = eElement.getAttributes().getNamedItem("Leave");
                                                			leave.setTextContent("0");					// Leave ='0'
								}
								
								// Set attendance status Absent as '1' if att_status is "a". Here "a" means absent.
								else if(att_status.equals("a"))
								{
									Node present = eElement.getAttributes().getNamedItem("Present");	 
                                                        		present.setTextContent("0");					// Present ='0'
                                                        		Node absent = eElement.getAttributes().getNamedItem("Absent");
                                                        		absent.setTextContent("1");					// Absent ='1'
                                                        		Node leave = eElement.getAttributes().getNamedItem("Leave");
                                                        		leave.setTextContent("0");					// Leave ='0'	
								}
								
								// Set attendance status Leave as '1' if att_status is "l". Here "l" means leave.
								else if(att_status.equals("l"))
								{
                                                        		Node present = eElement.getAttributes().getNamedItem("Present");
                                                        		present.setTextContent("0");					// Present ='0'
                                                        		Node absent = eElement.getAttributes().getNamedItem("Absent");
                                                        		absent.setTextContent("0");					// Absent ='0'
                                                        		Node leave = eElement.getAttributes().getNamedItem("Leave");
                                                        		leave.setTextContent("1");					// Leave ='1'
                                                        	}
									// Save all attendance updation in xml file
									saveXML(doc,filePath);
									message="Sucessfull";
							}
						}
					}
				}
			}
		}//try
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Error occured during updation in util 'XMLWriter_StudentAttendance' method name:(UpdateUserAttendance)"+e);
		}
		return message;
	}//method
	
	/**
	* This method is used for getting Parent Informatio.
        * @param: String StudentId
        * @return: Vector
	*/

	public static Vector getParentInfo(String studentId)
	{
		Vector parentInfo=new Vector();
		try{	
			 //Initialize criteria for database query and get list of parent information
			Criteria crit=new Criteria();
			crit.addGroupByColumn(ParentInfoPeer.ID);
                	List v=ParentInfoPeer.doSelect(crit);
			String parentId="";
			String parentFullName="";
			String email="";
			for(int i=0;i<v.size();i++) 
			{
				ParentInfo element = (ParentInfo)v.get(i);
                		String studId = element.getStudentId();		// Get Studnet UserId
				// check condtion if parent has more than one studnet id which is separate from "#" smbol.
				if(studId.equals(studentId) && studId.indexOf('#') == -1)
				{
					// get parentId if parent has single studnet Id
					parentId=element.getParentId();
				}
				else
				{
					// get all student Id which is separate from "#" symbol.
					StringTokenizer st=new StringTokenizer(studId,"#");
		        		for(int j=0;st.hasMoreTokens();j++)
           				{	
						// compare student id and get Parent Id.
      						if(studentId.equals(st.nextToken())){
							parentId=element.getParentId();	// Get Paret Id
						}
          				}
				}
			}
			// Get parent's EmailId
			email=UserUtil.getEmail(Integer.parseInt(parentId));
			// Get parent's FullName
			parentFullName=UserUtil.getFullName(Integer.parseInt(parentId));
			/**
			* Set parent's Email and fullName
			* @see Util: CourseUserDetail 
			*/
			CourseUserDetail cu=new CourseUserDetail();
			cu.setEmail(email);           	// set Parent Email
        		cu.setUserName(parentFullName); // set Parent FullName
			parentInfo.add(cu);		// add parent infromation into Vector
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Exception in getting parent email id --- "+e.getMessage());
		}
		return parentInfo;
	}
}
