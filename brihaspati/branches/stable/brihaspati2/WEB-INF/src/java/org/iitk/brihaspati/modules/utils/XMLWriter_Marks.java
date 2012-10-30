package org.iitk.brihaspati.modules.utils;

/*
 * @(#)XMLWriter_Marks
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
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.modules.utils.MarksFileEntry;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;

/**
 * @author <a href="vipulk@iitk.ac.in">vipul kumar pal</a>
 */

/**class for hadling all the stuff of marks upload in xml file	
 * class file Create the Xml file and read the details of the file. 
 */
public class XMLWriter_Marks {
	private Document doc1 = null;

/** Method to define the structure of xml file. This xml file is used to
 * store multiple text files containing marks of different section of students.
 * The instructor can add multiple marks files. Whenever a marks file is
 * deleted/added, the entry in this main marksxml is updated. Each file also
 * assigned as alias. This alias is visible to all students. This helps in
 * identifying the files by alaises like section A, Section B, and so on.
 * @param filePath (String): This is file path given by calling function and it
 * gives location where the marksxml file will be stored. The calling code
 * decides based on the convetion used for location to store marks in a course
 * area.
 * @param fileName (String): This fileName is for the text file containing the
 * marks.
 * return String
 */
	public static String  MarksXml(String filePath,String filename,String alias,String check){
		String message="UnSuccessfull";
                try{
			//Create instance of DocumentBuilderFactory
	               	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			//Get the DocumentBuilder	
        	        DocumentBuilder builder = factory.newDocumentBuilder();
			//Create blank DOM Document
                	Document doc = builder.parse(getFile(filePath));
			
			//creating tag or field for Marks information
			// root element
                      	Element root = doc.getDocumentElement();
			//create the root element
                        Element marks = doc.createElement("Marks");
			
			/**create child element
			 *and The text node is the content of that node
			 *Add a text node to the element	
			 */
       	                Element coursealias = doc.createElement("Alias");
               	        Text aliasText = doc.createTextNode(alias);
                       	coursealias.appendChild(aliasText);

			Element coursefilename = doc.createElement("FileName");
                        Text filenameText = doc.createTextNode(filename);
                        coursefilename.appendChild(filenameText);

			Element coursecheck = doc.createElement("Check");
                        Text checkText = doc.createTextNode(check);
                        coursecheck.appendChild(checkText);

			//inserting values in the tag or field.
       	                marks.appendChild(coursealias); 
       	                marks.appendChild(coursefilename); 
       	                marks.appendChild(coursecheck); 
		 
			//add in the root element
               	        root.appendChild(marks);
			
                	message=saveXML(doc,filePath);
                }catch(Exception ex){
                        ErrorDumpUtil.ErrorLog("Error on creating xml file : "+ex.getMessage());
                } 
		return message;
        }
	/**
	 *This method uses Access specific classes
	 *prints the XML document to file.
	 *@param doc (Document)
	 *@param filePath (String)
	 *return String
         */ 
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
                                Element rootEle = doc.createElement("MarksUpdate");
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
	/** method for get all  elements with the name "Marks"
	 *@param filepath (String)
	 *return NodeList
	 */
	private static NodeList getNodeList(String filePath ){
                NodeList list=null;
                try{
                        Document doc =getCreateDocument(filePath);
                        list = doc.getElementsByTagName("Marks");

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

	/**method for reading details of a Marks from xml file
	 *@param filePath (String)
	 *return Vector
	 */
	public static Vector ReadMarksDeatils(String filePath) {
                Vector v=new Vector();
                Element eElement=null;
                try {
                        File f=new File(filePath);
                        if(f.exists()) {
				//Create instance of DocumentBuilderFactory
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				//Get the DocumentBuilder
                                DocumentBuilder builder = factory.newDocumentBuilder();
				//Create blank DOM Document
                                Document doc = builder.parse(getFile(filePath));
				//Normalize All of the Text in a Document
                                doc.getDocumentElement().normalize();
				// Find all elements with the name "Marks"
                                NodeList nodeList = doc.getElementsByTagName("Marks");
                                for( int i=0; i<nodeList.getLength(); i++ ) {
                                        Node nNode = nodeList.item(i);
                                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                               eElement = (Element) nNode;
				
						/** get the node value by passing element
						 *set the value in InstituteFileEntry object
						 *@see MarksFileEntry in utils
						 */
                                                MarksFileEntry MarksfileEntry=new MarksFileEntry();
                                                String alias=getTagValue("Alias",eElement);
                                                String filename=getTagValue("FileName",eElement);
                                                MarksfileEntry.setAlias(alias);
                                                MarksfileEntry.setFileName(filename);
						/*store all values in the vector*/
                                                v.add(MarksfileEntry);
                                        }
                                }
                        }
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_InstituteRegistration method name:(ReadInstDomainDeatils)"+e);}
                return v;
        }

	/**Method for Delete Marks from Xml
	 *@param filePath (String)
	 *@param domain (String)
	 *return string 
	 */
	public static String RemoveElement(String filePath,String domain)
        {
                String message="UnSuccessfull";
                Element element=null;
                Node node=null;
                try{
			/**Create blank DOM Document
			 *@see getCreateDocument
			 */
                        Document doc =getCreateDocument(filePath);

			/**Find all elements with the name "Institute"*/

                        NodeList list = doc.getElementsByTagName("Marks");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
						/**get tag value by passing tag(domain)
						 *@see getTagValue method
						 *if match domain then delete the entry from xml file
						 */
                                                String value =getTagValue("Alias",element);
                                                if(value.equals(domain)){
                                                        doc.getDocumentElement().removeChild(element);
                                                        saveXML(doc,filePath);
                                                }
                                        }
                                }
                        }
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_InstituteRegistration method name:(RemoveElement)"+e);}
                return message;
        }

	/**Method checks if domain exist in xml file
         *@param filePath (String)
         *@param domain (String)
         *return string 
         */
	public static String CheckElement(String filePath,String domain)
        {
                String message="NotExist";
                Element element=null;
                Node node=null;
                try{
                        /**Create blank DOM Document
                         *@see getCreateDocument
                         */
                        Document doc =getCreateDocument(filePath);

                        /**Find all elements with the name "Marks"*/

                        NodeList list = doc.getElementsByTagName("Marks");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                /**get tag value by passing tag(domain)
                                                 *@see getTagValue method
                                                 */
                                                String value =getTagValue("Alias",element);
                                                if(value.equals(domain)){
							message = "Exist";
                                                }
                                        }
                                }
                        }
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_InstituteRegistration method name:(CheckElement)"+e);}
                return message;
        }
	/**Method checks paticular value exist or not
         *@param filePath (String)
         *@param domain (String)
         *return string 
         */
	public static String Check(String filePath,String domain)
        {
                String message="NotExist";
                Element element=null;
                Node node=null;
                try{
                        /**Create blank DOM Document
                         *@see getCreateDocument
                         */
                        Document doc =getCreateDocument(filePath);

                        /**Find all elements with the name "Marks"*/

                        NodeList list = doc.getElementsByTagName("Marks");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                /**get tag value by passing tag(domain)
                                                 *@see getTagValue method
                                                 *if match domain then return Exist Message
                                                 */
                                                String value =getTagValue("Check",element);
                                                if(value.equals(domain)){
                                                        message = "Exist";
                                                }
                                        }
                                }
                        }
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_InstituteRegistration method name:(CheckElement)"+e);}
                return message;
        }

	/**Method for reading file name corrosponding alias name
         *@param filePath (String)
         *@param domain (String)
         *return string 
         */
	public static String ReadFileNameElement(String filePath,String domain)
        {
                String message="NotExist";
                Element element=null;
                Node node=null;
		String FileName="";
                try{
                        /**Create blank DOM Document
                         *@see getCreateDocument
                         */
                        Document doc =getCreateDocument(filePath);

                        /**Find all elements with the name "Marks"*/

                        NodeList list = doc.getElementsByTagName("Marks");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                /**get tag value by passing tag(domain)
                                                 *@see getTagValue method
                                                 *if match domain then get the corrosponding File Name
                                                 */
                                                String value =getTagValue("Alias",element);
                                                if(value.equals(domain)){
                                                        FileName =getTagValue("FileName",element);
                                                }
                                        }
                                }
                        }
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_InstituteRegistration method name:(CheckElement)"+e);}
                return FileName;
        }


}
