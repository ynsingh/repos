package org.iitk.brihaspati.modules.utils;

/*
 * @(#)XMLWriter_Quotation.java
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
import java.util.Vector;
import java.util.Random;
import java.sql.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

/**
 * Class for handling addition, modification,
 * deletion of quotations stored in Quotation.xml, 
 * the class creates the Xml file and reads the details 
 * to be displayed on the Quotation Management module. 
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @date 25-04-2013
 */

public class XMLWriter_Quotation{

	private Document doc1 = null;
	private long longCreationDate;
        private long longCurrentDate;
	 private long noOfdays;

	/**
	 * This method adds quotation to xml
	 * @param filePath Path of Quotation.xml
	 * @param Quotation_id Unique id for the quotation
	 * @param Quotation_value Quotation
	 * @param date Current date  	 
	 */
	public static String QuotationXml(String filePath, String Quotation_id, String Quotation_value, String date)
	{	
		String dispMessage="UnSuccessfull";
	
	      	try{
                        //Create instance of DocumentBuilderFactory
               	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                       	//Get the DocumentBuilder       
               		DocumentBuilder builder = factory.newDocumentBuilder();
                       	//Create blank DOM Document
               		Document doc = builder.parse(getFile(filePath));
                       	//creating tag or field for root element
                       	Element root = doc.getDocumentElement();
                       	//create the root element
                       	Element QuotationValue = doc.createElement("Quotation");
                       
			/**
 			 * create child element
                       	 * and add a text node to the element        
                       	 */

                       	Element  quotId= doc.createElement("QUOTATION_ID");
                       	Text quotIdText = doc.createTextNode(Quotation_id);
                       	quotId.appendChild(quotIdText);

                       	Element  quotValue= doc.createElement("QUOTATION_VALUE");
                       	Text quotValText = doc.createTextNode(Quotation_value);
                       	quotValue.appendChild(quotValText);

                       	Element  addDate= doc.createElement("DATE");
			Text addDateText = doc.createTextNode(date);
                       	addDate.appendChild(addDateText);

                       	QuotationValue.appendChild(quotId);
                       	QuotationValue.appendChild(quotValue);
                       	QuotationValue.appendChild(addDate);
                       	root.appendChild(QuotationValue);
                       	dispMessage=saveXML(doc,filePath);
			
		}
		catch(Exception ex)
		{
			ErrorDumpUtil.ErrorLog("\nThe exception comes in method (QuotationXml): XMLWriter_Quotation "+ex);
		}
		
		return dispMessage;
        }

        //prints the XML document to file.
        private static  String saveXML(Document doc,String filePath) {
                try{
			FileOutputStream fos=new FileOutputStream(filePath);
                        //print
                        OutputFormat format = new OutputFormat(doc);
                        format.setIndenting(true);
                        //to generate a file output use fileoutputstream
                        XMLSerializer output = new XMLSerializer(fos, format);
                        output.serialize(doc);
                        return "Successfull";
                }
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("\nThe exception comes in method (saveXml): XMLWriter_Quotation "+e);
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
                                Element rootEle = doc.createElement("quotation");
                                //all it to the xml tre
                                doc.appendChild(rootEle);
                                saveXML(doc,filePath);
                        }catch (Exception ex) { System.out.println("Error in getFile() of XMLWriter_Quotation class "+ex.getMessage()); }
		} 
		return file;
        }
        
	/**
 	 * method to create blank DOM Document
         * @param filepath (String)
         * return document
         */
        private static Document getCreateDocument(String filePath ){
                Document doc=null;
                try{
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        doc = builder.parse(getFile(filePath));

                } catch( Exception e ){
			ErrorDumpUtil.ErrorLog("\nThe exception comes in method (getCreateDocument): XMLWriter_Quotation "+e);
                }
                return doc;
        }

        /** 
 	 * method to get all  elements with the name "Quotation"
         * @param filepath (String)
         * return NodeList
         *
        private static NodeList getNodeList(String filePath ){
                NodeList list=null;
                try{
                        Document doc =getCreateDocument(filePath);
                        list = doc.getElementsByTagName("Quotation");

                } catch( Exception e ){
                       ErrorDumpUtil.ErrorLog("\nThe exception comes in method (getNodeList): XMLWriter_Quotation "+e); 
                }
                return list;
        }*/

	/** 
 	 * method to get node value by passing element(tag)
         * @param tag (String)
         * @param element (Element)
         * return String
         */
        private static String getTagValue(String tag, Element element) {
                NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
                Node node = (Node) nodes.item(0);
                return node.getNodeValue().trim();
        }

	/** 
 	 * Method for reading values from xml
 	 * to display in the list
 	 * @param filePath (String)
	 */
	public static Vector getQuotationDetails( String filepath)
	{
		Element eElement=null;
		Vector v = new Vector();
			
		try{
			File f=new File(filepath);
			if(f.exists())
			{ 
				//Create instance of DocumentBuilderFactory
                                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                //Get the DocumentBuilder
                                DocumentBuilder builder = factory.newDocumentBuilder();
                                //Create blank DOM Document
                                Document doc = builder.parse(getFile(filepath));
                                //Normalize All of the Text in a Document
                                doc.getDocumentElement().normalize();
                                // Find all elements with the name "Quotation"
                                NodeList nodeList = doc.getElementsByTagName("Quotation");
				for( int i=0; i<nodeList.getLength(); i++ ) {
					Node nNode = nodeList.item(i);
					InstituteFileEntry InstfileEntry=new InstituteFileEntry();
                                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                        	eElement = (Element) nNode;
                                                String quote_id=getTagValue("QUOTATION_ID",eElement);
                                                String quote_value=getTagValue("QUOTATION_VALUE",eElement);
						InstfileEntry.setQuotationId(quote_id);
						InstfileEntry.setQuotationValue(quote_value);
                                                v.add(InstfileEntry);
                                        }
				}
			}
		}
		catch( Exception e )
		{
			ErrorDumpUtil.ErrorLog("The exception comes in method (getQuotaionDetails): XMLWriter_Quotation "+e);
		}
		return v;
	}

	/**
 	 * method for update the xml file
         * @param filePath (String)
         * @param quoteId Quotation Id(String)
         * @param quoteValue Quotation (String)
	 */
         public static String UpdateQuotationXml(String filePath, String quoteId, String quoteValue) {
                Node node=null;
                Element element=null;
		String msg = "Unsuccessful";
                try {
                        /**Create blank DOM Document
                         *@see getCreateDocument
                         */
                        Document doc =getCreateDocument(filePath);

                        /**Find all elements with the name "Institute"*/
                        NodeList list = doc.getElementsByTagName("Quotation");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i);
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;

                                                /**
 						 * get tag value by passing quotation id
                                                 * @see getTagValue method
                                                 */
                                                String xmlquote_id=getTagValue("QUOTATION_ID",element);

                                                if( xmlquote_id.equals(quoteId))
                                                {
                                                        NodeList exptag = doc.getElementsByTagName("QUOTATION_VALUE");
                                                        Node node1 = exptag.item(i);
                                                        node1.getFirstChild().setNodeValue(quoteValue);
                                                        saveXML(doc,filePath);
							msg="Successful";
                                                }
                                        }
                                }
                        }

                }//try
                catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception comes in method (UpdateQuotationXml): XMLWriter_Quotation "+e);
		}
		return msg;
        }//method

	/**
 	 * Method to delete the quotations
 	 * @param QuotationId Id of quotation (String)
 	 * @param filePath path of file (String)
	 */
	public static String deleteQuotation(String quoteId, String filePath)
	{
		String msg="Unsuccessful";
		Element element=null;
                Node node=null;
                try{
                        /**
                         * Create blank DOM Document
                         * @see getCreateDocument
                         */
			Document doc =getCreateDocument(filePath);

                        NodeList list = doc.getElementsByTagName("Quotation");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;

                                                /**
 						 * get tag value by passing tag(quoteId)
                                                 * @see getTagValue method
                                                 */
						String value =getTagValue("QUOTATION_ID",element);
                                                if(value.equals(quoteId)){
                                                        doc.getDocumentElement().removeChild(element);
                                                        String message=saveXML(doc,filePath);
							msg="Successful";
						}
					}
				}
			}
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("Error in util XMLWriter_EmailUpdation method name:(removeEmailElement)"+e);
                }
                return msg;

	}

	/**
 	 * Deletes the quotations which have been 
 	 * stored in the xml for more than two months 
 	 * @param filePath (String)
	 */
	public int expiredQuotes(String filePath)
	{
		Element element=null;
	        Node node=null;
        	Date Creation_date;
       		Vector remxml=new Vector();
        	String quote="";
		int cnt =0;

        	try{
                	Document doc =getCreateDocument(filePath);
                	NodeList list = doc.getElementsByTagName("Quotation");
             		if(!list.equals(null)){
                        	for( int i=0; i<list.getLength(); i++ ){
                                	node = list.item(i);
                                        	if( node.getNodeType() == node.ELEMENT_NODE ){
                                                	element = (Element)node;
                                               		String cdate=getTagValue("DATE",element);
                                                	String creation_date = cdate.substring(0,4)+"-"+cdate.substring(4,6)+"-"+cdate.substring(6,8);
                                                	java.util.Date currentDate= new java.util.Date();
                                                	Creation_date=Date.valueOf(creation_date);
                                                	longCreationDate= Creation_date.getTime();
                                                	longCurrentDate= currentDate.getTime();
                                                	noOfdays=(longCurrentDate-longCreationDate)/(24*3600*1000)+1;

                                                	if(noOfdays > 61 && (longCurrentDate-longCreationDate)!=0)
                                                	{
                                                       		quote=getTagValue("QUOTATION_ID",element);
                                                        	remxml.addElement(quote);
                                                       	}//if
                                        	}//if
                        	}//for

                        	int j=remxml.size();
//                        	int cnt=0;
                        	for(;j>0;j--)
                        	{
                                	quote =(String) remxml.get(cnt);
                                	deleteQuotation(quote, filePath);
                                	cnt++;
                        	}
                	}//if
         }//try
        catch(Exception e)
        {
                ErrorDumpUtil.ErrorLog("Error in util XMLWriter_Quotation method name:(expiredQuotes)"+e);
        }
		return cnt;
	}

	/**
         * Method for checking, whether quotations
         * already exists in xml file
         * @param filePath (String)
         * @param quotation (String)
         * @return boolean
         */
	public static boolean quoteExists(String filePath,String quotation)
        {
                Element element=null;
                Node node=null;
                try{
                        /**Create blank DOM Document
                         *@see getCreateDocument
                         */
			Document doc =getCreateDocument(filePath);
                        /**Find all elements with the name "Profile"*/
			NodeList list = doc.getElementsByTagName("Quotation");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i);
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                 /**
                                                  * get tag value by passing tag(quotation)
                                                  * @see getTagValue method
                                                  */
						String value =getTagValue("QUOTATION_VALUE",element);
                                                if(value.equals(quotation)){
                                                        return true;
                                                }
                                        }//if
                                }//for
                        }//if
                }//try
                catch(Exception e){
                        ErrorDumpUtil.ErrorLog("Error in util XMLWriter_Quotation method name:(quoteExists)"+e);
                }

                return false;
        }//method

	/**
 	 * Randomly selects quotation from the xml
 	 * @return String
	 */
	public static String randomSelect(String filepath)
	{
		Element eElement=null;
                String v = null;
		int len, i;
		Random rand = new Random();

                try{
                        File f=new File(filepath);
                        if(f.exists())
                        {
                		//Create instance of DocumentBuilderFactory
                                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                //Get the DocumentBuilder
                                DocumentBuilder builder = factory.newDocumentBuilder();
                                //Create blank DOM Document
                                Document doc = builder.parse(getFile(filepath));
				//Normalize All of the Text in a Document
                                doc.getDocumentElement().normalize();
                                // Find all elements with the name "Quotation"
                                NodeList nodeList = doc.getElementsByTagName("Quotation");
                                len = nodeList.getLength();
                                i = rand.nextInt(len);
			        Node nNode = nodeList.item(i);
                		InstituteFileEntry InstfileEntry=new InstituteFileEntry();
                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                	eElement = (Element) nNode;
                                        //String quote_id=getTagValue("QUOTATION_ID",eElement);
                                        v=getTagValue("QUOTATION_VALUE",eElement);
                                        //InstfileEntry.setQuotationId(quote_id);
                                        //InstfileEntry.setQuotationValue(quote_value);
                                        //v.add(InstfileEntry);
				}
                        }
                }
		catch(Exception e)	
		{
			ErrorDumpUtil.ErrorLog(" Error in randomSelect : XMLWriter_Quotation "+e);
		}
		return v;
	}//method

}//class
