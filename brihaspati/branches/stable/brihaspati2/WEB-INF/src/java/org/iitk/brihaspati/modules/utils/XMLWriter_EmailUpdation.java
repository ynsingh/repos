package org.iitk.brihaspati.modules.utils;

/**
 * @(#)XMLWriter_EmailUpdation.java  
 *  
 *  Copyright (c) 2012 ETRG,IIT Kanpur. 
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met:
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
 */


import java.io.File;
import java.util.Date;
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
import org.iitk.brihaspati.modules.utils.InstituteFileEntry;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Class for handling email verification
 * during profile updation, the class will create the Xml 
 * file and read the details of the file. 
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @modified date: 15-10-2012
 */

public class XMLWriter_EmailUpdation {
        private Document doc1 = null;

	/**
 	  * method for define the structure of xml file
 	  * @param filePath (String) Path of the xml file
          * @param user_name (String) User name of the user
          * @param email (String) Email_id of the user
          * @param hash_string (String) Hash sent in the mail
          * @param current_date (Date) Date on which profile was updated
          * @param photo (boolean) Has value 'true', if photo was updated
          *              'false', if photo was not attached.
          *              The parameter has been added to maintain
          *              the consistency of the existing system.
 	  * @return String
	  */
	public static String emailVerification(String filePath, String user_name, String email, String hash_string, Date current_date, boolean photo){
		String message="UnSuccessfull";
		String Photo;		

                try{
			//Create instance of DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			//Get the DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
                        //Create blank DOM Document
                        Document doc = builder.parse(getFile(filePath));

                        //creating tag or field for profile updation
                        // root element
                        Element root = doc.getDocumentElement();
                        //create the root element
                        Element profile = doc.createElement("Profile");

			/**
 			 * create child element and text node to the element
                         * and the text node is the content of that node
                         */
			Element userName = doc.createElement("UserName");
                        Text user_nameText = doc.createTextNode(user_name);
                        userName.appendChild(user_nameText);

                        Element eMail = doc.createElement("Email");
                        Text emailText = doc.createTextNode(email);
                        eMail.appendChild(emailText);

                        Element hash = doc.createElement("Hash");
                        Text hash_stringText = doc.createTextNode(hash_string);
                        hash.appendChild(hash_stringText);

			DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
  			String s_date = formatter.format(current_date);
			
			Element date = doc.createElement("Date");
                        Text current_dateText = doc.createTextNode(s_date);
                        date.appendChild(current_dateText);
 			
			if(photo)
				Photo = "exist";
			else
				Photo = "nexist";
			Element user_photo = doc.createElement("Photo");
                        Text photoText = doc.createTextNode(Photo);
                        user_photo.appendChild(photoText);

			//inserting values in the tag or field.
			profile.appendChild(userName);
                        profile.appendChild(eMail);
                        profile.appendChild(hash);
                        profile.appendChild(date);
			profile.appendChild(user_photo);	
			  
			//add in the root element
			root.appendChild(profile);
			
			message=saveXML(doc,filePath);

                 }//try
		 catch(Exception e){ 
			  ErrorDumpUtil.ErrorLog("Error while creating email updation xml "+e);
		 }//catch
		
		 return message;
	}//method

	/**
         * This method uses Xerces specific classes
         * prints the XML document to file.
         * @param doc (Document)
         * @param filePath (String)
         * return String
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
                }
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error while saving email updation xml "+e);
		}
                
		return "UnSuccessfull";
        }//method

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
                                Element rootEle = doc.createElement("EmailVerification");
                                //add it to the xml tree
                                doc.appendChild(rootEle);
                                saveXML(doc,filePath);
                        }
			catch (Exception ex) { 
				ErrorDumpUtil.ErrorLog("Error while creating blank xml file "+ex); 
			}
                }//if 
		return file;
        }//method

	/**
 	 * method for creating blank DOM Document
	 * @param filePath (String)
         * @param Domain (String)
         * @return boolean
         */

	private static Document getCreateDocument(String filePath ){
                Document doc=null;
                try{
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        doc = builder.parse(getFile(filePath));

                } catch( Exception e ){
                        ErrorDumpUtil.ErrorLog("Error while creating blank DOM document "+e);
                }
                return doc;
        }//method

	/** 
 	 * method for getting all  elements with the name "Profile"
         * @param filepath (String)
         * @return NodeList
         */

	private static NodeList getNodeList(String filePath ){
                NodeList list=null;
                try{
                        Document doc =getCreateDocument(filePath);
                        list = doc.getElementsByTagName("Profile");

                } catch( Exception e ){
                        ErrorDumpUtil.ErrorLog("Error while getting all the elements "+e);
                }
                return list;
        }//method

	/** 
	 * method for get node value by passing element(tag)
	 * @param tag (String)
         * @param element (Element)
         * return String
         */
        private static String getTagValue(String tag, Element element) {
                NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
                Node node = (Node) nodes.item(0);
                return node.getNodeValue().trim();
        }//method

	/**
	 * method for reading details of a profile from xml file
	 *@param filePath (String)
         *return Vector
         */
        public static Vector readProfileDetails(String filePath, String e_mail) {
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
                                // Find all elements with the name "Profile"
                                NodeList nodeList = doc.getElementsByTagName("Profile");
                                for( int i=0; i<nodeList.getLength(); i++ ) {
                                        Node nNode = nodeList.item(i);
                                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                               eElement = (Element) nNode;
						String email=getTagValue("Email",eElement);
						if(email.equals(e_mail))
						{
							String u_name=getTagValue("UserName",eElement);
                                                	v.addElement(u_name);
							v.addElement(email);
                                                	String hash=getTagValue("Hash",eElement);
							v.addElement(hash);
							String photo=getTagValue("Photo",eElement);
							v.addElement(photo);
						}
                                        }
                                }
                        }
                }catch( Exception e ){}
                return v;
        }//method

	/**
	 * Method for deleting profile from Xml
         *@param filePath (String)
         *@param email (String)
	 *return string 
         */

        public static String removeElement(String filePath,String email)
        {
                String message="UnSuccessfull";
                Element element=null;
                Node node=null;
                try{
                        /**
			 * Create blank DOM Document
                         *@see getCreateDocument
                         */
                        Document doc =getCreateDocument(filePath);

                        /**Find all elements with the name "Profile"*/

                        NodeList list = doc.getElementsByTagName("Profile");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                /**get tag value by passing tag(email)
                                                 *@see getTagValue method
                                                 *if email matches then delete the entry from xml file
                                                 */
                                                String value =getTagValue("Email",element);
                                                if(value.equals(email)){
                                                        doc.getDocumentElement().removeChild(element);
                                                        saveXML(doc,filePath);
                					message="Successfull"; 
		                               }
                                        }
                                }
                        }
                }
                catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in util XMLWriter_EmailUpdation method name:(removeElement)"+e);
		}
                return message;
        }//method

	 /**
	  * Method for checking, whether email
	  * already exists in xml file
          *@param filePath (String)
          *@param email (String)
          *@return boolean
          */

	public static boolean mailExist(String filePath,String email)
        {
		Element element=null;
                Node node=null;
                try{
                        /**Create blank DOM Document
                         *@see getCreateDocument
                         */
                        Document doc =getCreateDocument(filePath);
                        /**Find all elements with the name "Profile"*/
                        NodeList list = doc.getElementsByTagName("Profile");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                 /**
						  * get tag value by passing tag(email)
                                                  *@see getTagValue method
                                                  *if email matches then delete the entry from xml file
                                                  */
                                                String value =getTagValue("Email",element);
                                                if(value.equals(email)){
                                                        return true;
                                                }
                                        }//if
                                }//for
                        }//if
                }//try
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in util XMLWriter_EmailUpdation method name:(mailExist)"+e);
		}

		return false;
        }//method



	/**
 	* method for setting Hash element in xml file
 	*@param filePath (String)
 	*@param email (String)
 	*return boolean
 	*/

	public static boolean setHash(String filePath, String email)
        {
                ErrorDumpUtil.ErrorLog("email from mail "+email);
                boolean set=false;
                //READ the file
		Element eElement=null;
                try {
                        File f=new File(filePath);
                        if(f.exists()) {
                                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                DocumentBuilder builder = factory.newDocumentBuilder();
                                Document doc = builder.parse(getFile(filePath));
                                doc.getDocumentElement().normalize();
                                 NodeList nodeList = doc.getElementsByTagName("Profile");
                                 for( int i=0; i<nodeList.getLength(); i++ ) {
                                        Node nNode = nodeList.item(i);
                                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                                eElement = (Element) nNode;
						String e_mail=getTagValue("Email",eElement);
                                                ErrorDumpUtil.ErrorLog("email from xml "+e_mail);
                                                if(email.equals(e_mail))
                                                {
							NodeList exptag = doc.getElementsByTagName("Hash");
                                                        Node node1 = exptag.item(i);
                                                        node1.getFirstChild().setNodeValue("updated");
                                                        saveXML(doc,filePath);
							set = true;
						}
                                         }
                                 }
                         }
                }
                catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("Error in util XMLWriter_EmailUpdation method name:(setHash)"+e);
                }
		return set;
	}//method

}//class
