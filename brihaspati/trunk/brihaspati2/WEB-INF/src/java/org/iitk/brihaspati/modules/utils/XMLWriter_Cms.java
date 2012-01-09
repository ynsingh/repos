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
						
						NodeList node1 = doc.getElementsByTagName("sch4");
                                                node = node1.item(i);
                                                String sch4=(String)(node.getFirstChild()).getNodeValue();
						if(sch4.equals("null"))
							v.add("");
						else
							v.add(sch4);

						node1 = doc.getElementsByTagName("sch5");
                                                node = node1.item(i);
                                                String sch5=(String)(node.getFirstChild()).getNodeValue();
                                               if(sch5.equals("null"))
                                                        v.add("");
                                                else
						 v.add(sch5);

                                      		node1 = doc.getElementsByTagName("Mid_Sem");
						node = node1.item(i);
						String Mid_Sem=(String)(node.getFirstChild()).getNodeValue();	
						if(Mid_Sem.equals("null"))
                                                        v.add("");
                                                else
						v.add(Mid_Sem);
						
						node1 = doc.getElementsByTagName("Quiz");
                                                node = node1.item(i);
                                                String Quiz=(String)(node.getFirstChild()).getNodeValue();     
						if(Quiz.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(Quiz);
				
						node1 = doc.getElementsByTagName("LabWork");
                                                node = node1.item(i);
                                                String LabWork=(String)(node.getFirstChild()).getNodeValue();    
						if(LabWork.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(LabWork);
		
						node1 = doc.getElementsByTagName("End_Sem");
                                                node = node1.item(i);
                                                String End_Sem=(String)(node.getFirstChild()).getNodeValue();    
						if(End_Sem.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(End_Sem);
						
						
						node1 = doc.getElementsByTagName("Instrction");
                                                node = node1.item(i);
                                                String Instrction=(String)(node.getFirstChild()).getNodeValue();    
						if(Instrction.equals("null"))
                                                        v.add("");
                                                else   
                                                v.add(Instrction);

						node1 = doc.getElementsByTagName("FileName");
                                                node = node1.item(i);
                                                String FileName=(String)(node.getFirstChild()).getNodeValue();
                                                v.add(FileName);

                                                node1 = doc.getElementsByTagName("LabInstructor");
                                                node = node1.item(i);
                                                String LabInstructor=(String)(node.getFirstChild()).getNodeValue();
						if(LabInstructor.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(LabInstructor);

                                                node1 = doc.getElementsByTagName("LabInstructor1");
                                                node = node1.item(i);
                                                String LabInstructor1=(String)(node.getFirstChild()).getNodeValue();
						if(LabInstructor1.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(LabInstructor1);

                                                node1 = doc.getElementsByTagName("Tutor");
                                                node = node1.item(i);
                                                String Tutor=(String)(node.getFirstChild()).getNodeValue();
						if(Tutor.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(Tutor);

                                                node1 = doc.getElementsByTagName("Tutor1");
                                                node = node1.item(i);
                                                String Tutor1=(String)(node.getFirstChild()).getNodeValue();
						if(Tutor1.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(Tutor1);
						
						node1 = doc.getElementsByTagName("T");
                                                node = node1.item(i);
                                                String T=(String)(node.getFirstChild()).getNodeValue();
						if(T.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(T);
				
						node1 = doc.getElementsByTagName("T1");
                                                node = node1.item(i);
                                                String T1=(String)(node.getFirstChild()).getNodeValue();
						if(T1.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(T1);
				
						node1 = doc.getElementsByTagName("T2");
                                                node = node1.item(i);
                                                String T2=(String)(node.getFirstChild()).getNodeValue();
						if(T2.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(T2);

						node1 = doc.getElementsByTagName("T3");
                                                node = node1.item(i);
                                                String T3=(String)(node.getFirstChild()).getNodeValue();
						if(T3.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(T3);
	
						node1 = doc.getElementsByTagName("T4");
                                                node = node1.item(i);
                                                String T4=(String)(node.getFirstChild()).getNodeValue();
						if(T4.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(T4);
					
						node1 = doc.getElementsByTagName("T5");
                                                node = node1.item(i);
                                                String T5=(String)(node.getFirstChild()).getNodeValue();
						if(T5.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(T5);
						
						node1 = doc.getElementsByTagName("Sch");
                                                node = node1.item(i);
                                                String Sch=(String)(node.getFirstChild()).getNodeValue();
						if(Sch.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(Sch);
						
						node1 = doc.getElementsByTagName("Sch1");
                                                node = node1.item(i);
                                                String Sch1=(String)(node.getFirstChild()).getNodeValue();
						if(Sch1.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(Sch1);
		
						node1 = doc.getElementsByTagName("Sch2");
                                                node = node1.item(i);
                                                String Sch2=(String)(node.getFirstChild()).getNodeValue();
						if(Sch2.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(Sch2);
				
						node1 = doc.getElementsByTagName("Sch3");
                                                node = node1.item(i);
                                                String Sch3=(String)(node.getFirstChild()).getNodeValue();
						if(Sch3.equals("null"))
                                                        v.add("");
                                                else
                                                v.add(Sch3);



						
						return v;
                                        }
                                }
                        }
                }catch(Exception e){}
                return v;
        }	
	
	public static String  updateCourseManageMentSystem(String filePath,String couse_id,String sch4,String sch5,String mid_sem,String quiz,String labwork,String end_sem,String instrction,String filename,String labinst,String labinst1,String tute,String tute1,String t,String t1,String t2,String t3,String t4,String t5,String sch,String sch1,String sch2,String sch3){	
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile(filePath));	
			NodeList nodeList = doc.getElementsByTagName("Couse_ID");
                      	for( int i=0; i<nodeList.getLength(); i++ ) {
                        	Node node = nodeList.item(i);
                                String course=(String)(node.getFirstChild()).getNodeValue();
				if(course.equals(couse_id)) {
					NodeList Sch4 = doc.getElementsByTagName("sch4");
                                        Node node1 = Sch4.item(i);
                                        node1.getFirstChild().setNodeValue(sch4);
                                        saveXML(doc,filePath);
					NodeList Sch5 = doc.getElementsByTagName("sch5");
                                        Node node2 = Sch5.item(i);
                                        node2.getFirstChild().setNodeValue(sch5);
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
					saveXML(doc,filePath);
                                        NodeList LabInstructor = doc.getElementsByTagName("LabInstructor");
                                        Node node9 = LabInstructor.item(i);
                                        node9.getFirstChild().setNodeValue(labinst);
					saveXML(doc,filePath);
                                        NodeList LabInstructor1 = doc.getElementsByTagName("LabInstructor1");
                                        Node node10 = LabInstructor1.item(i);
                                        node10.getFirstChild().setNodeValue(labinst1);
					saveXML(doc,filePath);
                                        NodeList Tutor = doc.getElementsByTagName("Tutor");
                                        Node node11 = Tutor.item(i);
                                        node11.getFirstChild().setNodeValue(tute);
					saveXML(doc,filePath);
                                        NodeList Tutor1 = doc.getElementsByTagName("Tutor1");
                                        Node node12 = Tutor1.item(i);
                                        node12.getFirstChild().setNodeValue(tute1);
					saveXML(doc,filePath);
					
					NodeList T = doc.getElementsByTagName("T");
                                        Node node13 = T.item(i);
                                        node13.getFirstChild().setNodeValue(t);
                                        saveXML(doc,filePath);

					NodeList T1 = doc.getElementsByTagName("T1");
                                        Node node14 = T1.item(i);
                                        node14.getFirstChild().setNodeValue(t1);
                                        saveXML(doc,filePath);
					NodeList T2 = doc.getElementsByTagName("T2");
                                        Node node15 = T2.item(i);
                                        node15.getFirstChild().setNodeValue(t2);
                                        saveXML(doc,filePath);	
					NodeList T3 = doc.getElementsByTagName("T3");
                                        Node node16 = T3.item(i);
                                        node16.getFirstChild().setNodeValue(t3);
                                        saveXML(doc,filePath);
					NodeList T4 = doc.getElementsByTagName("T4");
                                        Node node17 = T4.item(i);
                                        node17.getFirstChild().setNodeValue(t4);
                                        saveXML(doc,filePath);
					NodeList T5 = doc.getElementsByTagName("T5");
                                        Node node18 = T5.item(i);
                                        node18.getFirstChild().setNodeValue(t5);
                                        saveXML(doc,filePath);
									
					NodeList Sch = doc.getElementsByTagName("Sch");
                                         Node node19 = Sch.item(i);
                                         node19.getFirstChild().setNodeValue(sch);
                                        saveXML(doc,filePath);
					NodeList Sch1 = doc.getElementsByTagName("Sch1");
                                         Node node20 = Sch1.item(i);
                                         node20.getFirstChild().setNodeValue(sch1);
                                        saveXML(doc,filePath);
					NodeList Sch2 = doc.getElementsByTagName("Sch2");
                                         Node node21 = Sch2.item(i);
                                         node21.getFirstChild().setNodeValue(sch2);
                                        saveXML(doc,filePath);
					NodeList Sch3 = doc.getElementsByTagName("Sch3");
                                         Node node22 = Sch3.item(i);
                                         node22.getFirstChild().setNodeValue(sch3);
                                        saveXML(doc,filePath);

					return saveXML(doc,filePath);
					
                                }
                      	}		
		}catch(Exception e){ErrorDumpUtil.ErrorLog("Error in updateCourseManageMentSystem "+e.getMessage());}
		return "";
	}
	public static String  CourseManageMentSystem(String filePath,String couse_id,String sch4,String sch5,String mid_sem,String quiz,String labwork,String end_sem,String instrction,String filename,String labinst,String labinst1,String tute,String tute1,String t,String t1,String t2,String t3,String t4,String t5,String sch,String sch1,String sch2,String sch3){
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

			Element sch4_e = doc.createElement("sch4");
                        Text sch4Text = doc.createTextNode(sch4);
                        sch4_e.appendChild(sch4Text);

			Element sch5_e = doc.createElement("sch5");
                        Text sch5Text = doc.createTextNode(sch5);
                        sch5_e.appendChild(sch5Text);

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
			
                        Element labinst_e = doc.createElement("LabInstructor");
                        Text labinstText = doc.createTextNode(labinst);
                        labinst_e.appendChild(labinstText);

			 Element labinst1_e = doc.createElement("LabInstructor1");
                        Text labinst1Text = doc.createTextNode(labinst1);
                        labinst1_e.appendChild(labinst1Text);

                        Element tute_e = doc.createElement("Tutor");
                        Text tuteText = doc.createTextNode(tute);
                        tute_e.appendChild(tuteText);
			
			Element tute1_e = doc.createElement("Tutor1");
                        Text tute1Text = doc.createTextNode(tute1);
                        tute1_e.appendChild(tute1Text);
		
		
                        Element t_e = doc.createElement("T");
                        Text tText = doc.createTextNode(t1);
                        t_e.appendChild(tText);
			
			Element t1_e = doc.createElement("T1");
                        Text t1Text = doc.createTextNode(t1);
                        t1_e.appendChild(t1Text);
	
			Element t2_e = doc.createElement("T2");
                        Text t2Text = doc.createTextNode(t2);
                        t2_e.appendChild(t2Text);

			Element t3_e = doc.createElement("T3");
                        Text t3Text = doc.createTextNode(t3);
                        t3_e.appendChild(t3Text);
			
			Element t4_e = doc.createElement("T4");
                        Text t4Text = doc.createTextNode(t4);
                        t4_e.appendChild(t4Text);	
			Element t5_e = doc.createElement("T5");
                        Text t5Text = doc.createTextNode(t5);
                        t5_e.appendChild(t5Text);
		
			Element sch_e = doc.createElement("Sch");
                        Text schText = doc.createTextNode(sch);
                        sch_e.appendChild(schText);
			
			Element sch1_e = doc.createElement("Sch1");
                        Text sch1Text = doc.createTextNode(sch1);
                        sch1_e.appendChild(sch1Text);

			Element sch2_e = doc.createElement("Sch2");
                        Text sch2Text = doc.createTextNode(sch2);
                        sch2_e.appendChild(sch2Text);

			Element sch3_e = doc.createElement("Sch3");
                        Text sch3Text = doc.createTextNode(sch3);
                        sch3_e.appendChild(sch3Text);

			
       	                Cms.appendChild(ip); 
			Cms.appendChild(sch4_e);
			Cms.appendChild(sch5_e);
               	        Cms.appendChild(courses);
                        Cms.appendChild(load);
       	                Cms.appendChild(labwork_e);
       	                Cms.appendChild(end_sem_e);
       	                Cms.appendChild(instrction_e);
       	                Cms.appendChild(filename_e);
			Cms.appendChild(labinst_e);
			Cms.appendChild(labinst1_e);
			Cms.appendChild(tute_e);
			Cms.appendChild(tute1_e);
			Cms.appendChild(t_e);
			Cms.appendChild(t1_e);
			Cms.appendChild(t2_e);
			Cms.appendChild(t3_e);
			Cms.appendChild(t4_e);
			Cms.appendChild(t5_e);
			Cms.appendChild(sch_e);
			Cms.appendChild(sch1_e);
			Cms.appendChild(sch2_e);
			Cms.appendChild(sch3_e);
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

