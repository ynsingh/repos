package org.iitk.brihaspati.modules.utils;

/*
 * @(#)XMLWriter_Cms.java
 *
 *  Copyright (c) 2011 conditions are met:
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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedOutputStream;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.Text;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kuamr Singh</a>
 * @author <a href="mailto:parasharirajeev@gmail.com">Rajeev Parashari</a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal</a>
 */


public class XMLWriter_Cms {

	public static String searchElement(String filePath,String courseid) {
                String message="UnSuccessfull";
                try {
			File f=new File(filePath); 
                	if(f.exists()) {
                        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	                        DocumentBuilder builder = factory.newDocumentBuilder();
        	                Document doc = builder.parse(getFile(filePath));
                	        NodeList nodeList = doc.getElementsByTagName("Couse_ID");
                        	for( int i=0; i<nodeList.getLength(); i++ ) {
	                                Node node = nodeList.item(i);
        	                        String course=(String)(node.getFirstChild()).getNodeValue();
                        	        if(course.equals(courseid)) {
						message="Successfull";	
					}
				}
			}
		}catch(Exception e){}
		return message;
	}	
	
	public static Vector getSearchElement(String filePath,String courseid) {
                String message="UnSuccessfull";
		Vector v=null;
                try {
                        File f=new File(filePath);
                        if(f.exists()) {
                                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                DocumentBuilder builder = factory.newDocumentBuilder();
                                Document doc = builder.parse(getFile(filePath));
                                NodeList nodeList = doc.getElementsByTagName("Couse_ID");
                                for( int i=0; i<nodeList.getLength(); i++ ) {
                                        Node node = nodeList.item(i);
                                        String course=(String)(node.getFirstChild()).getNodeValue();
					if(course.equals(courseid)) {
						v=new Vector();
						
						NodeList node1 = doc.getElementsByTagName("Schedule");
                                                node = node1.item(i);
                                                String Schedule=(String)(node.getFirstChild()).getNodeValue();
                                                v.add(Schedule);

						node1 = doc.getElementsByTagName("Venue");
                                                node = node1.item(i);
                                                String Venue=(String)(node.getFirstChild()).getNodeValue();
                                                v.add(Venue);

                                      		 node1 = doc.getElementsByTagName("Mid_Sem");
						node = node1.item(i);
						String Mid_Sem=(String)(node.getFirstChild()).getNodeValue();	
						v.add(Mid_Sem);
						
						node1 = doc.getElementsByTagName("Quiz");
                                                node = node1.item(i);
                                                String Quiz=(String)(node.getFirstChild()).getNodeValue();      
                                                v.add(Quiz);
				
						node1 = doc.getElementsByTagName("LabWork");
                                                node = node1.item(i);
                                                String LabWork=(String)(node.getFirstChild()).getNodeValue();       
                                                v.add(LabWork);
		
						node1 = doc.getElementsByTagName("End_Sem");
                                                node = node1.item(i);
                                                String End_Sem=(String)(node.getFirstChild()).getNodeValue();    
                                                v.add(End_Sem);
						
						
						node1 = doc.getElementsByTagName("Instrction");
                                                node = node1.item(i);
                                                String Instrction=(String)(node.getFirstChild()).getNodeValue();       
                                                v.add(Instrction);

						node1 = doc.getElementsByTagName("FileName");
                                                node = node1.item(i);
                                                String FileName=(String)(node.getFirstChild()).getNodeValue();
                                                v.add(FileName);
						
						return v;
                                        }
                                }
                        }
                }catch(Exception e){}
                return v;
        }	
	
	public static String  updateCourseManageMentSystem(String filePath,String couse_id,String Schedule,String Venue,String mid_sem,String quiz,String labwork,String end_sem,String instrction,String filename){	
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile(filePath));	
			NodeList nodeList = doc.getElementsByTagName("Couse_ID");
                      	for( int i=0; i<nodeList.getLength(); i++ ) {
                        	Node node = nodeList.item(i);
                                String course=(String)(node.getFirstChild()).getNodeValue();
				if(course.equals(couse_id)) {
					 NodeList schedule = doc.getElementsByTagName("Schedule");
                                         Node node1 = schedule.item(i);
                                         node1.getFirstChild().setNodeValue(Schedule);
                                        saveXML(doc,filePath);
					 NodeList venue = doc.getElementsByTagName("Venue");
                                        Node node2 = venue.item(i);
                                        node2.getFirstChild().setNodeValue(Venue);
                                        saveXML(doc,filePath);
					NodeList Mid_Sem = doc.getElementsByTagName("Mid_Sem");
                                        Node node3 = Mid_Sem.item(i);
                                        node3.getFirstChild().setNodeValue(mid_sem);
					saveXML(doc,filePath);		
					NodeList Quiz = doc.getElementsByTagName("Quiz");
                                        Node node4 = Quiz.item(i);
                                        node4.getFirstChild().setNodeValue(quiz);
					saveXML(doc,filePath);
					NodeList LabWork = doc.getElementsByTagName("LabWork");
                                        Node node5 = LabWork.item(i);
                                        node5.getFirstChild().setNodeValue(labwork);
					saveXML(doc,filePath);
					NodeList End_Sem = doc.getElementsByTagName("End_Sem");
                                        Node node6 = End_Sem.item(i);
                                        node6.getFirstChild().setNodeValue(end_sem);
					saveXML(doc,filePath);	
					NodeList Instrction = doc.getElementsByTagName("Instrction");
                                        Node node7 = Instrction.item(i);
                                        node7.getFirstChild().setNodeValue(instrction);
					saveXML(doc,filePath);
					NodeList FileName = doc.getElementsByTagName("FileName");
                                        Node node8 = FileName.item(i);
                                        node8.getFirstChild().setNodeValue(filename);
					return saveXML(doc,filePath);
					
                                }
                      	}		
		}catch(Exception e){ErrorDumpUtil.ErrorLog("Error in updateCourseManageMentSystem "+e.getMessage());}
		return "";
	}
	public static String  CourseManageMentSystem(String filePath,String couse_id,String Schedule,String Venue,String mid_sem,String quiz,String labwork,String end_sem,String instrction,String filename){
		String message="UnSuccessfull";
                try{
	               	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	        DocumentBuilder builder = factory.newDocumentBuilder();
                	Document doc = builder.parse(getFile(filePath));

                      	Element root = doc.getDocumentElement();
                        Element Cms = doc.createElement("CouseManagement");

       	                Element ip = doc.createElement("Couse_ID");
               	        Text ipText = doc.createTextNode(couse_id);
                       	ip.appendChild(ipText);

			Element Schedule_e = doc.createElement("Schedule");
                        Text ScheduleText = doc.createTextNode(Schedule);
                        Schedule_e.appendChild(ScheduleText);

			Element Venue_e = doc.createElement("Venue");
                        Text VenueText = doc.createTextNode(Venue);
                        Venue_e.appendChild(VenueText);

                        Element courses = doc.createElement("Mid_Sem");
       	                Text coursesText = doc.createTextNode(mid_sem);
               	        courses.appendChild(coursesText);

                       	Element load = doc.createElement("Quiz");
                        Text loadText = doc.createTextNode(quiz);
       	                load.appendChild(loadText);
	
       	                Element labwork_e = doc.createElement("LabWork");
               	        Text labworkText = doc.createTextNode(labwork);
                       	labwork_e.appendChild(labworkText);
	
			Element end_sem_e = doc.createElement("End_Sem");
               	        Text endsemText = doc.createTextNode(end_sem);
                       	end_sem_e.appendChild(endsemText);
		
			Element instrction_e = doc.createElement("Instrction");
                        Text instrctionText = doc.createTextNode(instrction);
       	                instrction_e.appendChild(instrctionText);
	
			Element filename_e = doc.createElement("FileName");
               	        Text filenameText = doc.createTextNode(filename);
                       	filename_e.appendChild(filenameText);
			
       	                Cms.appendChild(ip); 
			Cms.appendChild(Schedule_e);
			Cms.appendChild(Venue_e);
               	        Cms.appendChild(courses);
                        Cms.appendChild(load);
       	                Cms.appendChild(labwork_e);
       	                Cms.appendChild(end_sem_e);
       	                Cms.appendChild(instrction_e);
       	                Cms.appendChild(filename_e);
               	        root.appendChild(Cms);
                	message=saveXML(doc,filePath);
                }catch(Exception ex){
                        System.out.println("Error on creating xml file : "+ex.getMessage());
                } 
		return message;
        }	

	
	private static  String saveXML(Document doc,String filePath) {
                try{

                        OutputFormat format = new OutputFormat(doc);
                        format.setIndenting(true);
                        XMLSerializer output = new XMLSerializer(new FileOutputStream(filePath), format);
                        output.serialize(doc);
                        return "Successfull";
                }catch(Exception e){System.out.println(e.getMessage());}
                return "UnSuccessfull";
        }
	
	private static File getFile(String filePath) {
                File file=new File(filePath);
                if(!file.exists()){
                        try {
                                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                DocumentBuilder builder = factory.newDocumentBuilder();
                                Document doc = builder.newDocument();
                                Element rootEle = doc.createElement("CourseManagement_Peers");
                                doc.appendChild(rootEle);
                                saveXML(doc,filePath);
                        }catch (Exception ex) { System.out.println("Error in ex "+ex.getMessage()); }
                } return file;
        }
	
}

