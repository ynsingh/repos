package org.iitk.brihaspati.modules.utils;

/*
 * @(#)XMLWriter_InstituteRegistration
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
import org.iitk.brihaspati.modules.utils.InstituteFileEntry;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;

/**
 * This class file Create the Xml file and read the details of the file. 
 * @author <a href="jaivirpal@gmail.com">Jaivir Singh</a>
 * @author <a href="palseema30@gmail.com">Manorama Pal</a>
 */

public class XMLWriter_InstituteRegistration {
	private Document doc1 = null;
	public static String  InstituteRegistrationListXml(String filePath,String name,String address,String city,String pincode,String state,String landlineno,String domain,String type,String affiliation,String website,String regDate,String expDate,String fname,String lname,String email,String designation,String username,String password ){
		String message="UnSuccessfull";
                try{
	               	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	        DocumentBuilder builder = factory.newDocumentBuilder();
                	Document doc = builder.parse(getFile(filePath));

                      	Element root = doc.getDocumentElement();
                        Element institute = doc.createElement("Institute");

       	                Element instname = doc.createElement("Name");
               	        Text nameText = doc.createTextNode(name);
                       	instname.appendChild(nameText);

			Element instaddress = doc.createElement("Address");
                        Text addressText = doc.createTextNode(address);
                        instaddress.appendChild(addressText);

			Element instcity = doc.createElement("City");
                        Text cityText = doc.createTextNode(city);
                        instcity.appendChild(cityText);

			Element instpincode = doc.createElement("Pincode");
                        Text pincodeText = doc.createTextNode(pincode);
                        instpincode.appendChild(pincodeText);

			Element inststate = doc.createElement("State");
                        Text stateText = doc.createTextNode(state);
                        inststate.appendChild(stateText);

			Element instlandline = doc.createElement("LandLineNo");
                        Text landlineText = doc.createTextNode(landlineno);
                        instlandline.appendChild(landlineText);

			Element instdomain = doc.createElement("Domain");
                        Text domainText = doc.createTextNode(domain);
                        instdomain.appendChild(domainText);

			Element insttype = doc.createElement("Type");
                        Text typeText = doc.createTextNode(type);
                        insttype.appendChild(typeText);

			Element instaffiliation = doc.createElement("Affiliation");
                        Text affiliationText = doc.createTextNode(affiliation);
                        instaffiliation.appendChild(affiliationText);

			Element instwebsite = doc.createElement("Website");
                        Text websiteText = doc.createTextNode(website);
                        instwebsite.appendChild(websiteText);

			Element instregdate = doc.createElement("RegDate");
                        Text regdateText = doc.createTextNode(regDate);
                        instregdate.appendChild(regdateText);

			Element instexpdate = doc.createElement("ExpDate");
                        Text expdateText = doc.createTextNode(expDate);
                        instexpdate.appendChild(expdateText);

			Element instfname = doc.createElement("FName");
                        Text fnameText = doc.createTextNode(fname);
                        instfname.appendChild(fnameText);

			Element instlname = doc.createElement("LName");
                        Text lnameText = doc.createTextNode(lname);
                        instlname.appendChild(lnameText);

			Element instemail = doc.createElement("Email");
                        Text emailText = doc.createTextNode(email);
                        instemail.appendChild(emailText);

			Element instdesignation = doc.createElement("Designation");
                        Text designationText = doc.createTextNode(designation);
                        instdesignation.appendChild(designationText);

			Element instusername = doc.createElement("UserName");
                        Text usernameText = doc.createTextNode(username);
                        instusername.appendChild(usernameText);

			Element instpassword = doc.createElement("Password");
                        Text passwordText = doc.createTextNode(password);
                        instpassword.appendChild(passwordText);

       	                institute.appendChild(instname); 
       	                institute.appendChild(instaddress); 
       	                institute.appendChild(instcity); 
       	                institute.appendChild(instpincode); 
       	                institute.appendChild(inststate); 
       	                institute.appendChild(instlandline); 
       	                institute.appendChild(instdomain); 
       	                institute.appendChild(insttype); 
       	                institute.appendChild(instaffiliation); 
       	                institute.appendChild(instwebsite); 
       	                institute.appendChild(instregdate); 
       	                institute.appendChild(instexpdate); 
       	                institute.appendChild(instfname); 
       	                institute.appendChild(instlname); 
       	                institute.appendChild(instemail); 
       	                institute.appendChild(instdesignation); 
       	                institute.appendChild(instusername); 
       	                institute.appendChild(instpassword); 
               	        root.appendChild(institute);
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
                                Element rootEle = doc.createElement("InstituteRegistrationList");
                                doc.appendChild(rootEle);
                                saveXML(doc,filePath);
                        }catch (Exception ex) { System.out.println("Error in ex "+ex.getMessage()); }
                } return file;
        }
	public static boolean DomainExist(String filePath,String Domain ){
                boolean DomainExist=false;
                Element element=null;
                Node node=null;
                try{
                        NodeList list = getNodeList(filePath);
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                String domainvalue=getTagValue("Domain",element);
                                                if(domainvalue.equals(Domain))
                                                        DomainExist=true;
                                        }
                                }
                        }
			Criteria criteria = new Criteria();
                        criteria.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_DOMAIN);
                        List institutedetail = InstituteAdminRegistrationPeer.doSelect(criteria);
			if(institutedetail.size() !=0){
                        	for(int k=0;k<institutedetail.size();k++){
                                	InstituteAdminRegistration instituteregister=(InstituteAdminRegistration)(institutedetail.get(k));
                                	String domainname=instituteregister.getInstituteDomain().toString();
                               		// check institute with domain name exist
                               		if(domainname.equals(Domain))
					DomainExist=true;
                                }
                        }
                }catch( Exception e ){
                        e.printStackTrace();
                }
                return DomainExist;
        }
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
	private static NodeList getNodeList(String filePath ){
                NodeList list=null;
                try{
                        Document doc =getCreateDocument(filePath);
                        list = doc.getElementsByTagName("Institute");

                } catch( Exception e ){
                        e.printStackTrace();
                }
                return list;
        }
	private static String getTagValue(String tag, Element element) {
                NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
                Node node = (Node) nodes.item(0);
                return node.getNodeValue().trim();
        }
	public static Vector ReadInstituteDeatils(String filePath) {
                Vector v=new Vector();
                Element eElement=null;
                try {
                        File f=new File(filePath);
                        if(f.exists()) {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                DocumentBuilder builder = factory.newDocumentBuilder();
                                Document doc = builder.parse(getFile(filePath));
                                doc.getDocumentElement().normalize();
                                NodeList nodeList = doc.getElementsByTagName("Institute");
                                for( int i=0; i<nodeList.getLength(); i++ ) {
                                        Node nNode = nodeList.item(i);
                                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                               eElement = (Element) nNode;
                                                InstituteFileEntry InstfileEntry=new InstituteFileEntry();
                                                String name=getTagValue("Name",eElement);
                                                String addr=getTagValue("Address",eElement);
                                                String city=getTagValue("City",eElement);
                                                String pcode=getTagValue("Pincode",eElement);
                                                String state=getTagValue("State",eElement);
                                                String llno=getTagValue("LandLineNo",eElement);
                                                String domain=getTagValue("Domain",eElement);
                                                String type=getTagValue("Type",eElement);
                                                String affil=getTagValue("Affiliation",eElement);
                                                String website=getTagValue("Website",eElement);
                                                String regdate=getTagValue("RegDate",eElement);
                                                String expdate=getTagValue("ExpDate",eElement);
                                                String fname=getTagValue("FName",eElement);
                                                String lname=getTagValue("LName",eElement);
                                                String email=getTagValue("Email",eElement);
                                                String degs=getTagValue("Designation",eElement);
                                                String Uname=getTagValue("UserName",eElement);
                                                String passwd=getTagValue("Password",eElement);
                                                InstfileEntry.setInstituteName(name);
                                                InstfileEntry.setInstituteAddress(addr);
                                                InstfileEntry.setInstituteCity(city);
                                                InstfileEntry.setInstitutePincode(pcode);
                                                InstfileEntry.setInstituteState(state);
                                                InstfileEntry.setInstituteLandLineNo(llno);
                                                InstfileEntry.setInstituteDomain(domain);
                                                InstfileEntry.setInstituteType(type);
                                                InstfileEntry.setInstituteAffiliation(affil);
                                                InstfileEntry.setInstituteWebsite(website);
                                                InstfileEntry.setInstituteRegDate(regdate);
                                                InstfileEntry.setInstituteExpDate(expdate);
                                                InstfileEntry.setInstituteFName(fname);
                                                InstfileEntry.setInstituteLName(lname);
                                                InstfileEntry.setInstituteEmail(email);
                                                InstfileEntry.setInstituteDesignation(degs);
                                                InstfileEntry.setInstituteUserName(Uname);
                                                InstfileEntry.setInstitutePassword(passwd);
                                                v.add(InstfileEntry);
                                        }
                                }
                        }
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_InstituteRegistration method name:(ReadInstDomainDeatils)"+e);}
                return v;
        }
	public static Vector ReadInstDomainDeatils(String filePath,String instdomain) {
                Vector v=new Vector();
                Element eElement=null;
                try {
                        File f=new File(filePath);
                        if(f.exists()) {
				NodeList nodeList = getNodeList(filePath);
                                for( int i=0; i<nodeList.getLength(); i++ ) {
                                        Node nNode = nodeList.item(i);
                                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                               eElement = (Element) nNode;
                                                String domain=getTagValue("Domain",eElement);
						if(instdomain.equals(domain))
						{
                                                	InstituteFileEntry InstfileEntry=new InstituteFileEntry();
                                                	String name=getTagValue("Name",eElement);
                                                	String addr=getTagValue("Address",eElement);
                                                	String city=getTagValue("City",eElement);
                                                	String pcode=getTagValue("Pincode",eElement);
                                                	String state=getTagValue("State",eElement);
                                                	String llno=getTagValue("LandLineNo",eElement);
                                                	String type=getTagValue("Type",eElement);
                                                	String affil=getTagValue("Affiliation",eElement);
                                                	String website=getTagValue("Website",eElement);
                                                	String regdate=getTagValue("RegDate",eElement);
                                                	String expdate=getTagValue("ExpDate",eElement);
                                                	String fname=getTagValue("FName",eElement);
                                                	String lname=getTagValue("LName",eElement);
                                                	String email=getTagValue("Email",eElement);
                                                	String degs=getTagValue("Designation",eElement);
                                                	String Uname=getTagValue("UserName",eElement);
                                                	String passwd=getTagValue("Password",eElement);
                                                	InstfileEntry.setInstituteName(name);
                                                	InstfileEntry.setInstituteAddress(addr);
                                                	InstfileEntry.setInstituteCity(city);
                                                	InstfileEntry.setInstitutePincode(pcode);
                                                	InstfileEntry.setInstituteState(state);
                                                	InstfileEntry.setInstituteLandLineNo(llno);
                                                	InstfileEntry.setInstituteDomain(domain);
                                                	InstfileEntry.setInstituteType(type);
                                                	InstfileEntry.setInstituteAffiliation(affil);
                                                	InstfileEntry.setInstituteWebsite(website);
                                                	InstfileEntry.setInstituteRegDate(regdate);
                                                	InstfileEntry.setInstituteExpDate(expdate);
                                                	InstfileEntry.setInstituteFName(fname);
                                                	InstfileEntry.setInstituteLName(lname);
                                                	InstfileEntry.setInstituteEmail(email);
                                                	InstfileEntry.setInstituteDesignation(degs);
                                                	InstfileEntry.setInstituteUserName(Uname);
                                                	InstfileEntry.setInstitutePassword(passwd);
                                                	v.add(InstfileEntry);
						}
                                        }
                                }
                        }
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_InstituteRegistration method name:(ReadInstDomainDeatils)"+e);}
                return v;
	}

	//Delete Institute from Xml

	public static String RemoveElement(String filePath,String domain)
        {
                String message="UnSuccessfull";
                Element element=null;
                Node node=null;
                try{
                        Document doc =getCreateDocument(filePath);
                        NodeList list = doc.getElementsByTagName("Institute");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                String value =getTagValue("Domain",element);
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
	public static String MoveElement(String filePathsrc,String domain,String filepathdest)
        {
                String message="Unsuccessfull";
                Element element=null;
                Node node=null;
                String id="";
                try{
                        Document doc =getCreateDocument(filePathsrc);
                        Document docdest =getCreateDocument(filepathdest);
                        NodeList list = doc.getElementsByTagName("Institute");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                String value=getTagValue("Domain",element);
                                                if(value.equals(domain)){
                                                        doc.getDocumentElement().removeChild(element);
                                                                saveXML(doc,filePathsrc);
                                                        Node dup = docdest.importNode(element, true);
                                                        docdest.getDocumentElement().appendChild(dup);
                                                                saveXML(docdest,filepathdest);
                                                }
                                        }
                                }
                        }

                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_InstituteRegistration method name:(MoveElement)"+e);}
                return message;
        }
	 public static void UpdateRejectxml(String filePath,String domain,String expdate) {
		Node node=null;
		Element element=null;
                try {
			Document doc =getCreateDocument(filePath);
                        NodeList list = doc.getElementsByTagName("Institute");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                String value=getTagValue("Domain",element);
						if(value.equals(domain))
                                                {
                                                        NodeList exptag = doc.getElementsByTagName("ExpDate");
                                                        Node node1 = exptag.item(i);
                                                        node1.getFirstChild().setNodeValue(expdate);
                                                        saveXML(doc,filePath);
						}
					}
				}
			}

		}//try
		catch(Exception e){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_InstituteRegistration method name:(UpdateRejectxml)"+e);}
	}//method
	public static void RemoveElementRejectxml(String filePath,String expdate)
        {
                Element element=null;
                Node node=null;
                try{
                        Document doc =getCreateDocument(filePath);
                        NodeList list = doc.getElementsByTagName("Institute");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                String value =getTagValue("ExpDate",element);
                                                if(value.equals(expdate)){
                                                        doc.getDocumentElement().removeChild(element);
                                                        saveXML(doc,filePath);
                                                }
                                        }
                                }
                        }
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in util XMLWriter_InstituteRegistration method name:(RemoveElementRejectxml)"+e);}
        }
}
