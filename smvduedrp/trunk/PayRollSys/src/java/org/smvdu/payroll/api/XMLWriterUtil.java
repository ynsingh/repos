/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api;

/**
*  Copyright (c) 2010, 2011, 2014 SMVDU Katra.
*  Copyright (c) 2015, 2016 ETRG, IITK.
*  All Rights Reserved.
** Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
** Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
*
* @author  Manorama Pal (palseema30@gmail.com)
*/

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**class for handling all the stuff of Salary Heads and Ledger mapping in xml file        
 *class file Create the Xml file and read the details of the file. 
 */

public class XMLWriterUtil {
    
   /**method for creating blank xml file
    * with root element
    * @param filePath (String)
    * @return File
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
                Element rootEle = doc.createElement("SalaryHeadMap");
                //all it to the xml tre
                doc.appendChild(rootEle);
                saveXML(doc,filePath);
            }catch (Exception ex) { System.out.println("Error in ex "+ex.getMessage()); }
            
        } return file;
    }
    
    /** This method uses Xerces specific classes
    *prints the XML document to file.
    *@param doc (Document)
    *@param filePath (String)
    *@return String
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
    
    /**method for define the structure of xml file
    *@param filePath (String)
    *@param prshead (String)
    *@param prsheadId (String)
    *@param bgashead (String)
    *@param bgasheadCode (String)
    *@return String
    */

    public static String SalaryHeadmapXml(String filePath, String prshead,String prsheadId,String bgashead,String bgasheadCode){ 
        String message="Successfull" ;
        try{
            //Create instance of DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Get the DocumentBuilder       
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Create blank DOM Document
            Document doc = builder.parse(getFile(filePath));
            //creating tag or field for information
           // root element
            Element root = doc.getDocumentElement();
           //create the root element
            Element salaryhead = doc.createElement("Head");
            salaryhead.setAttribute("PRSHead", prshead);
            salaryhead.setAttribute("PRHeadId", prsheadId);
            salaryhead.setAttribute("BGASHead", bgashead);
            salaryhead.setAttribute("BGASHeadCode", bgasheadCode);
            root.appendChild(salaryhead);
            message=saveXML(doc,filePath);
         
        }
        catch(Exception ex) {};
        return message;
    } 
    
    /**Method to get Ledger
     * @param filePath (String)
     * @param attributeName (String)
     * @param value (String)
     * @return boolean
     */
    public static boolean getAttributeValueExist(String filePath, String attributeName,String value){
        String node="";
        boolean headExist=false;
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(getFile(filePath));
            Element root = doc.getDocumentElement();
            NodeList nodeList = doc.getElementsByTagName("Head");
            for(int x=0,size= nodeList.getLength(); x<size; x++) {
                node = nodeList.item(x ).getAttributes().getNamedItem(attributeName).getNodeValue();
                if(node.equals(value)){
                    headExist=true;
                }
             
            }
           
        }
        catch(Exception ex) {};
        return headExist;
    
    }
    
    /** Method for set attribute 
     * @param node (Node)
     * @param attName (String)
     * @param val (String)
     */
    
    public static void setAttribute(Node node, String attName, String val) {
        try{    
            NamedNodeMap attributes = node.getAttributes();
            Node attNode = node.getOwnerDocument().createAttribute(attName);
            attNode.setNodeValue(val);
            attributes.setNamedItem(attNode);
        }
        catch(Exception ex) {};
        
    }
    
    /** Method to get attribute value by passing Attribute Name
     * @param element (Node)
     * @param attName (string)
     * @return String
     */
    
    public static String getAttribute(Node element, String attName) {
        try{
            
            NamedNodeMap attrs = element.getAttributes();
            if (attrs == null) {
                return null;
            }
            Node attN = attrs.getNamedItem(attName);
            if (attN == null) {
                return null;
            }
            return attN.getNodeValue();
        } 
        catch(Exception ex) {};
        return "";
    }
    
    /** Method to get LedgerCode and Name 
     * @param filePath (String)
     * @param headcode (String)
     * @return String
     */
    
    public static String getLedgerNameCode(String filePath, String headcode)
    {
        String ledgername_code=null;
        try{
            File f=new File(filePath);
            if(f.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(getFile(filePath));
                doc.getDocumentElement().normalize();
                NodeList nodeList = doc.getElementsByTagName("Head");
                for( int i=0; i<nodeList.getLength(); i++ ) {
                    Node nNode = nodeList.item(i);
                    String nodeHead= getAttribute(nNode,"PRHeadId");
                    if(nodeHead.equals(headcode)){
                        String ledername=getAttribute(nNode,"BGASHead");
                        String ledercode=getAttribute(nNode,"BGASHeadCode");
                        ledgername_code=ledername+"-"+ledercode;
                        
                    }                                            
                }            
            }//if 1
        }//try
        catch(Exception e){}//catch                        
        return ledgername_code;
    }//method
    
    /** Method for edit the mapped ledger with salary head
    * @param filePath
     * @param AttValue
     * @param ledgername
     * @param ledgercode 
     */
    public static void UpdateEntry(String filePath, String AttValue,String ledgername,String ledgercode)
    {
        String message="successfull" ;
        try{
            File f=new File(filePath);
            if(f.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(getFile(filePath));
                doc.getDocumentElement().normalize();
                // Get the root element
                NodeList nodeList = doc.getElementsByTagName("Head");
                for( int i=0; i<nodeList.getLength(); i++ ) {
                    Node nNode = nodeList.item(i);
                    String nodeHead= getAttribute(nNode,"PRSHead");
                   // System.out.println("nodeHead=inxml=="+nodeHead+"AttValue==="+AttValue);
                    if(nodeHead.equals(AttValue)){
                        setAttribute(nNode,"BGASHead", ledgername);
                        setAttribute(nNode,"BGASHeadCode", ledgercode);
                        // write the content into xml file
                        message=saveXML(doc,filePath); 
                        
                        
                    }                                            
                }  
                
	    }//if 1
        }//try
        catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	   } catch (IOException ioe) {
		ioe.printStackTrace();
	   } catch (SAXException sae) {
		sae.printStackTrace();
	   }
        
    }//method
    
}        