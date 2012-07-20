package org.iitk.brihaspati.modules.utils;

/*
 * @(#)XMLWriter_GraphCalculation.java
 *
 *  Copyright (c) 2012 conditions are met:
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
import java.sql.Date;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import java.util.Vector;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.FileOutputStream;
import org.apache.torque.util.Criteria;
import javax.xml.parsers.DocumentBuilder;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import javax.xml.parsers.DocumentBuilderFactory;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

public class XMLWriter_GraphCalculation{
 private Document doc1 = null;
			public static String GraphCalculationXml(String filePath,String uid,String courseid,String day,String value)
			{	String message="UnSuccessfull";
	                	try{
                        //Create instance of DocumentBuilderFactory
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        //Get the DocumentBuilder       
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        //Create blank DOM Document
                        Document doc = builder.parse(getFile(filePath));
                        //creating tag or field for Graph information
                        // root element
                        Element root = doc.getDocumentElement();
                        //create the root element
                        Element graphvalue = doc.createElement("Value");
                        /**create child element
                         *and The text node is the content of that node
                         *Add a text node to the element        
                         */
                        Element userid = doc.createElement("USER_ID");
                        Text uidText = doc.createTextNode(uid);
                        userid.appendChild(uidText);
                        Element  cid= doc.createElement("COURSE_ID");
                        Text courseidText = doc.createTextNode(courseid);
                        cid.appendChild(courseidText);
                        Element  countday= doc.createElement("DAY");
                        Text dayText = doc.createTextNode(day);
                        countday.appendChild(dayText);
                        Element  strvalue= doc.createElement("VALUE_STRING");
                        Text valueText = doc.createTextNode(value);
                        strvalue.appendChild(valueText);
                        graphvalue.appendChild(userid);
                        graphvalue.appendChild(cid);
                        graphvalue.appendChild(countday);
                        graphvalue.appendChild(strvalue);
                        root.appendChild(graphvalue);
                        message=saveXML(doc,filePath);
			}catch(Exception ex){}
			return message;
        }
        //prints the XML document to file.
        private static  String saveXML(Document doc,String filePath) {
                try{
                        //print
                        OutputFormat format = new OutputFormat(doc);
                        format.setIndenting(true);
                        //to generate a file output use fileoutputstream
                        XMLSerializer output = new XMLSerializer(new FileOutputStream(filePath), format);
                        output.serialize(doc);
                        return "Successfull";
                }catch(Exception e){System.out.println(e.getMessage());}
                return "UnSuccessfull";
        }
        /**method for creating blank xml file
         *with root element
         *@param filePath (String)
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
                                Element rootEle = doc.createElement("GraphCalculation");
                                //all it to the xml tre
                                doc.appendChild(rootEle);
                                saveXML(doc,filePath);
                        }catch (Exception ex) { System.out.println("Error in ex "+ex.getMessage()); }
 } return file;
        }
        /**method for Create blank DOM Document
         *@param filepath (String)
         *return document
         */
        private static Document getCreateDocument(String filePath ){
                Document doc=null;
                try{
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        doc = builder.parse(getFile(filePath));

                } catch( Exception e ){
                        e.printStackTrace();
                }
                return doc;
        }
        /** method for get all  elements with the name "VALUE"
         *@param filepath (String)
         *return NodeList
         */
        private static NodeList getNodeList(String filePath ){
                NodeList list=null;
                try{
                        Document doc =getCreateDocument(filePath);
                        list = doc.getElementsByTagName("VALUE");

                } catch( Exception e ){
                        e.printStackTrace();
                }
                return list;
        }
	/** method for get node value by passing element(tag)
         *@param tag (String)
         *@param element (Element)
         *return String
         */
        private static String getTagValue(String tag, Element element) {
                NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
                Node node = (Node) nodes.item(0);
                return node.getNodeValue().trim();
        }

	/** method for Reading Values from xml
	  *
	  */
	public static  String getGraphDetails(String filepath,int userid,String courseid,int day)
	{
		String value="";
		Element eElement=null;
		try{
			File f=new File(filepath);
			if(f.exists()) {
				//Create instance of DocumentBuilderFactory
                                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                //Get the DocumentBuilder
                                DocumentBuilder builder = factory.newDocumentBuilder();
                                //Create blank DOM Document
                                Document doc = builder.parse(getFile(filepath));
                                //Normalize All of the Text in a Document
                                doc.getDocumentElement().normalize();
                                // Find all elements with the name "Institute"
                                NodeList nodeList = doc.getElementsByTagName("Value");
				for( int i=0; i<nodeList.getLength(); i++ ) {
                                        Node nNode = nodeList.item(i);
                                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                               eElement = (Element) nNode;
						int uid =Integer.parseInt(getTagValue("USER_ID",eElement));
						String cid=getTagValue("COURSE_ID",eElement);
						int da=Integer.parseInt(getTagValue("DAY",eElement));
						if((userid==uid) && (day==da) && (cid.equals(courseid)))
						value=getTagValue("VALUE_STRING",eElement);
					}
				}
			}
		}catch( Exception e ){}
		return value;
	}
	public static String ModuleGraphCalculationXml(String filePath,String uid,String courseid,String mname,String day,String value)
                        {       String message="UnSuccessfull";
                                try{
                        //Create instance of DocumentBuilderFactory
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        //Get the DocumentBuilder       
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        //Create blank DOM Document
                        Document doc = builder.parse(getFile(filePath));
                        //creating tag or field for Graph information
                        // root element
                        Element root = doc.getDocumentElement();
                        //create the root element
                        Element graphvalue = doc.createElement("Value");
                        /**create child element
                         *and The text node is the content of that node
                         *Add a text node to the element        
                         */
                        Element userid = doc.createElement("USER_ID");
                        Text uidText = doc.createTextNode(uid);
                        userid.appendChild(uidText);
                        Element  cid= doc.createElement("COURSE_ID");
                        Text courseidText = doc.createTextNode(courseid);
                        cid.appendChild(courseidText);
			Element  mid= doc.createElement("MNAME");
                        Text mnameText = doc.createTextNode(mname);
                         mid.appendChild(mnameText);
                        Element  countday= doc.createElement("DAY");
                        Text dayText = doc.createTextNode(day);
                        countday.appendChild(dayText);
                        Element  strvalue= doc.createElement("VALUE_STRING");
                        Text valueText = doc.createTextNode(value);
                        strvalue.appendChild(valueText);
                        graphvalue.appendChild(userid);
                        graphvalue.appendChild(cid);
			graphvalue.appendChild(mid);
                        graphvalue.appendChild(countday);
                        graphvalue.appendChild(strvalue);
			root.appendChild(graphvalue);
                        message=saveXML(doc,filePath);
                        }catch(Exception ex){}
                        return message;
        }

	
	public static  String getModuleGraphDetails(String filepath,int userid,String courseid,String mname,int day)
        {
                String value="";
                Element eElement=null;
                try{
                        File f=new File(filepath);
                        if(f.exists()) {
                                //Create instance of DocumentBuilderFactory
                                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                //Get the DocumentBuilder
                                DocumentBuilder builder = factory.newDocumentBuilder();
                                //Create blank DOM Document
                                Document doc = builder.parse(getFile(filepath));
                                //Normalize All of the Text in a Document
                                doc.getDocumentElement().normalize();
                                // Find all elements with the name "Institute"
                                NodeList nodeList = doc.getElementsByTagName("Value");
                                for( int i=0; i<nodeList.getLength(); i++ ) {
                                        Node nNode = nodeList.item(i);
                                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                               eElement = (Element) nNode;
                                                int uid =Integer.parseInt(getTagValue("USER_ID",eElement));
                                                String cid=getTagValue("COURSE_ID",eElement);
						String mid=getTagValue("MNAME",eElement);
                                                int da=Integer.parseInt(getTagValue("DAY",eElement));
                                                if((userid==uid) && (day==da) && (cid.equals(courseid)) && (mname.equals(mid)))
                                                value=getTagValue("VALUE_STRING",eElement);
                                        }
                                }
                        }
                }catch( Exception e ){}
		return value;
        }

}
