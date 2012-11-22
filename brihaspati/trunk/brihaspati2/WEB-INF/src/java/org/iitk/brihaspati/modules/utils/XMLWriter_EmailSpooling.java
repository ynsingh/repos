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
import org.apache.commons.lang.StringUtils;
import javax.xml.parsers.DocumentBuilderFactory;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

/*
* @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
*/
public class XMLWriter_EmailSpooling{
 private Document doc1 = null;
			//public static String EmailSpoolingXml(String filePath, String emailId, String subject, String msg, String date, String sendStatus, String langFile, String instId)
			public static String EmailSpoolingXml(String filePath, String emailId, String subject, String msg, String attachedFile, String date, String sendStatus, String langFile)
			{	
				String dispMessage="UnSuccessfull";
				 //ErrorDumpUtil.ErrorLog("emailId===in XML="+emailId);
			
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
                        Element emailSpoolValue = doc.createElement("Email");
                        /**create child element
                         *and The text node is the content of that node
                         *Add a text node to the element        
                         */
                        Element email = doc.createElement("EMAIL_ID");
                        Text emailIdText = doc.createTextNode(emailId);
                        email.appendChild(emailIdText);

                        Element  sub= doc.createElement("SUBJECT");
                        Text subjectText = doc.createTextNode(subject);
                        sub.appendChild(subjectText);

                        Element  message= doc.createElement("MESSAGE");
                        Text messageText = doc.createTextNode(msg);
                        message.appendChild(messageText);

                        Element  attachFile= doc.createElement("ATTACH_FILE");
			Text attachFileText;
			if(StringUtils.isBlank(attachedFile))
                        	attachFileText = doc.createTextNode("tmp");
			else
	                        attachFileText = doc.createTextNode(attachedFile);
                        attachFile.appendChild(attachFileText);

                        Element countDate= doc.createElement("DATE");
                        Text dateText = doc.createTextNode(date);
                        countDate.appendChild(dateText);

                        Element countAttempt = doc.createElement("ATTEMPT");
                        Text ateText = doc.createTextNode(sendStatus);
                        countAttempt.appendChild(ateText);
			
			Element lang = doc.createElement("LANG_FILE");
                        Text langFileText = doc.createTextNode(langFile);
                        lang.appendChild(langFileText);
		/*
			Element instituteId = doc.createElement("INST_ID");
                        Text instIdText = doc.createTextNode(instId);
                        instituteId.appendChild(instIdText);
		*/
                        emailSpoolValue.appendChild(email);
                        emailSpoolValue.appendChild(sub);
                        emailSpoolValue.appendChild(message);
                        emailSpoolValue.appendChild(attachFile);
                        emailSpoolValue.appendChild(countDate);
                        emailSpoolValue.appendChild(countAttempt);
                        emailSpoolValue.appendChild(lang);
                        //emailSpoolValue.appendChild(instituteId);
                        root.appendChild(emailSpoolValue);
                        dispMessage=saveXML(doc,filePath);
			}catch(Exception ex){}
			return dispMessage;
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
                }catch(Exception e){ErrorDumpUtil.ErrorLog(e.getMessage());}
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
                                Element rootEle = doc.createElement("EmailSpooling");
                                //all it to the xml tre
                                doc.appendChild(rootEle);
                                saveXML(doc,filePath);
                        }catch (Exception ex) { System.out.println("Error in getFile() of XMLWriter_EmailSpooling class "+ex.getMessage()); }
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
        /** method for get all  elements with the name "Email"
         *@param filepath (String)
         *return NodeList
         */
        private static NodeList getNodeList(String filePath ){
                NodeList list=null;
                try{
                        Document doc =getCreateDocument(filePath);
//////////////////////// need to check
                        list = doc.getElementsByTagName("Email");

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

	/** method for Reading Values from xmln
	  *
	  */
	public static Vector getEmailSpoolDetails( String filepath)
	{
		String value="";
		Element eElement=null;
		Vector v = new Vector();
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
                                // Find all elements with the name "Email"
                                NodeList nodeList = doc.getElementsByTagName("Email");
				for( int i=0; i<nodeList.getLength(); i++ ) {

					 /** get the node value by passing element
                                          *set the value in InstituteFileEntry object
                                          *@see InstituteFileEntry in utils
                                         */

                                        Node nNode = nodeList.item(i);
					InstituteFileEntry InstfileEntry=new InstituteFileEntry();
                                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                                eElement = (Element) nNode;
					
						String emailId = getTagValue("EMAIL_ID",eElement);
						String subject = getTagValue("SUBJECT", eElement);
						String message = getTagValue("MESSAGE", eElement);
						String attachFile = getTagValue("ATTACH_FILE", eElement);
						String date = getTagValue("DATE", eElement);
						String sendMailStatus = getTagValue("ATTEMPT",eElement);
						String langFile = getTagValue("LANG_FILE", eElement);
						InstfileEntry.setInstituteEmail(emailId);
						InstfileEntry.setSubject(subject);
						InstfileEntry.setMessage(message);
						InstfileEntry.setAttachFile(attachFile);
						InstfileEntry.setDate(date);
						InstfileEntry.setAttempt(sendMailStatus);
						InstfileEntry.setLangFile(langFile);
                                                /*store all values in the vector*/
                                                v.add(InstfileEntry);
						
					}
				}
			}
		}catch( Exception e ){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_EmailSpooling "+e);}
		return v;
	}
 /**Method for Delete Institute from Xml
         *@param filePath (String)
         *@param domain (String)
         *return string 
         */
        public static String RemoveElement(String filePath, String emailId, String msg)
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

                        NodeList list = doc.getElementsByTagName("Email");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                /**get tag value by passing tag(domain)
                                                 *@see getTagValue method
                                                 *if match domain then delete the entry from xml file
                                                 */
                                                String valueEmail =getTagValue("EMAIL_ID",element).trim();
                                                String valueMsg =getTagValue("MESSAGE",element).trim();
	//					ErrorDumpUtil.ErrorLog("\n\nXML REMOVE=============valueEmail="+valueEmail+"\nvalueMsg ="+valueMsg+"\t msg="+msg);
                                                if(valueEmail.equals(emailId) && valueMsg.equals((msg))){
                                                        doc.getDocumentElement().removeChild(element);
                                                        saveXML(doc,filePath);
                                                }
                                        }
                                }
                        }
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_EmailSpooling method name:(RemoveElement)"+e);}
                return message;
        }

        //public static int RemoveElement(String filePath, String emailId, int num)
        public static String RemoveElement(String filePath, String emailId)
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

                        NodeList list = doc.getElementsByTagName("Email");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
	                                        if( node.getNodeType() == node.ELEMENT_NODE ){
        	                                        element = ( Element )node;
                	                                /**get tag value by passing tag(domain)
                        	                         *@see getTagValue method
                                	                 *if match domain then delete the entry from xml file
                                        	         */
                                                	String valueEmail =getTagValue("EMAIL_ID",element).trim();
							//ErrorDumpUtil.ErrorLog("\n\nXML REMOVE=============Email="+valueEmail);
        	                                        if(valueEmail.equals(emailId) ){
                	                                        doc.getDocumentElement().removeChild(element);
                        	                                saveXML(doc,filePath);
                                	                }
                                        	}
                                }
                        }
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_EmailSpooling method name:(RemoveElement)"+e);}
                return message;
        }
/**method for update the xml file
         *@param filePath (String)
         *@param domain (String)
         *@param expdate (String)
         */
         public static void UpdateEmailSpoolxml(String filePath, String emailId, String msg, String mailAttempt, String date) {
                Node node=null;
                Element element=null;
                try {
                        /**Create blank DOM Document
                         *@see getCreateDocument
                         */
                        Document doc =getCreateDocument(filePath);

                        /**Find all elements with the name "Institute"*/
                        NodeList list = doc.getElementsByTagName("Email");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                /**get tag value by passing tag(domain)
                                                 *@see getTagValue method
                                                 */
                                                String emailValue=getTagValue("EMAIL_ID",element);
                                                String msgValue=getTagValue("MESSAGE",element);
                                                if( emailValue.equals(emailId) && msgValue.equals(msg))
                                                {
                                                        /**Find element with the name "ExpDate"
                                                         * set node value (update xml)
                                                         */
                                                        NodeList exptag = doc.getElementsByTagName("ATTEMPT");
                                                        Node node1 = exptag.item(i);
                                                        node1.getFirstChild().setNodeValue(mailAttempt);
                                                        NodeList exptag1 = doc.getElementsByTagName("DATE");
                                                        Node node2 = exptag1.item(i);
                                                        node2.getFirstChild().setNodeValue(date);
                                                        saveXML(doc,filePath);
                                                }
                                        }
                                }
                        }

                }//try
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_EmailSpooling method name:(UpdateEmailSpoolxml)"+e);}
        }//method

}
