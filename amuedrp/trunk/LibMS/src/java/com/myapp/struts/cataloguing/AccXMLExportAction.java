/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.utility.AppPath;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import org.w3c.dom.*;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp-03
 */
public class AccXMLExportAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        HttpSession session= request.getSession();
BibliographicEntryDAO dao=new BibliographicEntryDAO();
         String login_role=(String)session.getAttribute("login_role");
        String sub_library_id=(String)session.getAttribute("sublibrary_id");
        String library_id=(String)session.getAttribute("library_id");
	

         String path=AppPath.getPropertiesFilePath();

         //List <BibliographicDetails>rst =(ArrayList<BibliographicDetails>)session.getAttribute("opaclist");
BibliographicDetailEntryActionForm1 institute = (BibliographicDetailEntryActionForm1) form;
        String search_by = institute.getSearch_by();
        String sort_by = institute.getSort_by();
        String search_keyword = institute.getSearch_keyword();
	String doc_type=institute.getDocument_type();
           int pageno=Integer.parseInt((String)(request.getParameter("page")==null || request.getParameter("page").isEmpty() ?"0":request.getParameter("page")));
       System.out.println(pageno);
          List<DocumentDetails> rst = dao.getAccession(library_id, sub_library_id,search_by,search_keyword, sort_by,pageno,doc_type);

System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");


        Iterator it1 = rst.iterator();
 int tcount1=0;

DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Accession_details");
		doc.appendChild(rootElement);
                DOMSource source=null;
                // write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		StreamResult result = new StreamResult(new File(path+"Accession_details.xml"));

 while(it1.hasNext())
     {
System.out.println("checkAction2");
//System.out.println("Staff first Name"+path+ staffobj.get(tcount1).getStaffDetail().getId().getLibraryId()+staffobj.get(tcount1).getStaffDetail().getSublibraryId()+staffobj.get(tcount1).getStaffDetail().getId().getStaffId()+staffobj.get(tcount1).getStaffDetail().getTitle()+staffobj.get(tcount1).getStaffDetail().getFirstName()+ staffobj.get(tcount1).getStaffDetail().getLastName()+"admin"+staffobj.get(tcount1).getStaffDetail().getContactNo()+staffobj.get(tcount1).getStaffDetail().getMobileNo() +staffobj.get(tcount1).getStaffDetail().getEmailId()+staffobj.get(tcount1).getStaffDetail().getDateJoining()+staffobj.get(tcount1).getStaffDetail().getDateReleaving()+staffobj.get(tcount1).getStaffDetail().getFatherName()+staffobj.get(tcount1).getStaffDetail().getDateOfBirth()+staffobj.get(tcount1).getStaffDetail().getGender()+staffobj.get(tcount1).getStaffDetail().getAddress1()+staffobj.get(tcount1).getStaffDetail().getCity1()+staffobj.get(tcount1).getStaffDetail().getState1()+staffobj.get(tcount1).getStaffDetail().getCountry1()+staffobj.get(tcount1).getStaffDetail().getZip1()+staffobj.get(tcount1).getStaffDetail().getAddress2()+staffobj.get(tcount1).getStaffDetail().getCity2()+staffobj.get(tcount1).getStaffDetail().getState2()+staffobj.get(tcount1).getStaffDetail().getCountry2()+staffobj.get(tcount1).getStaffDetail().getZip2());

//Exportxml.writexml(path, staffobj.get(tcount1).getStaffDetail().getId().getLibraryId(), staffobj.get(tcount1).getStaffDetail().getSublibraryId(),staffobj.get(tcount1).getStaffDetail().getId().getStaffId(),staffobj.get(tcount1).getStaffDetail().getTitle(),staffobj.get(tcount1).getStaffDetail().getFirstName(), staffobj.get(tcount1).getStaffDetail().getLastName(),"admin",staffobj.get(tcount1).getStaffDetail().getContactNo(),staffobj.get(tcount1).getStaffDetail().getMobileNo() ,staffobj.get(tcount1).getStaffDetail().getEmailId(),staffobj.get(tcount1).getStaffDetail().getDateJoining(),staffobj.get(tcount1).getStaffDetail().getDateReleaving(),staffobj.get(tcount1).getStaffDetail().getFatherName(),staffobj.get(tcount1).getStaffDetail().getDateOfBirth(),staffobj.get(tcount1).getStaffDetail().getGender(),staffobj.get(tcount1).getStaffDetail().getAddress1(),staffobj.get(tcount1).getStaffDetail().getCity1(),staffobj.get(tcount1).getStaffDetail().getState1(),staffobj.get(tcount1).getStaffDetail().getCountry1(),staffobj.get(tcount1).getStaffDetail().getZip1(),staffobj.get(tcount1).getStaffDetail().getAddress2(),staffobj.get(tcount1).getStaffDetail().getCity2(),staffobj.get(tcount1).getStaffDetail().getState2(),staffobj.get(tcount1).getStaffDetail().getCountry2(),staffobj.get(tcount1).getStaffDetail().getZip2());
try{


		// staff elements
		Element staff = doc.createElement("Book");
		rootElement.appendChild(staff);

//		// set attribute to staff element
//		Attr attr = doc.createAttribute("id");
//		attr.setValue("1");
//		staff.setAttributeNode(attr);

                // id elements

                Element acc = doc.createElement("AccessionNumber");
		acc.appendChild(doc.createTextNode(rst.get(tcount1).getAccessionNo()));
		staff.appendChild(acc);


                Element id = doc.createElement("Title");
		id.appendChild(doc.createTextNode(rst.get(tcount1).getTitle()));
		staff.appendChild(id);


		// shorten way
		// staff.setAttribute("id", "1");

		// firstname elements
		Element firstname = doc.createElement("Author");
		firstname.appendChild(doc.createTextNode(rst.get(tcount1).getMainEntry()));
		staff.appendChild(firstname);

		// lastname elements
		Element lastname = doc.createElement("CallNo");
		lastname.appendChild(doc.createTextNode(rst.get(tcount1).getCallNo()));
		staff.appendChild(lastname);

		// nickname elements
		Element nickname = doc.createElement("PublisherName");
		nickname.appendChild(doc.createTextNode(rst.get(tcount1).getPublisherName()));
		staff.appendChild(nickname);

		// salary elements
		Element salary = doc.createElement("PublishingPlace");
		salary.appendChild(doc.createTextNode(rst.get(tcount1).getPublicationPlace()));
		staff.appendChild(salary);

//                // salary elements
//		Element sublib = doc.createElement("SubLibrary");
//		sublib.appendChild(doc.createTextNode("rr"));
//		staff.appendChild(sublib);
//
//                  // salary elements
//		Element role = doc.createElement("Role");
//		role.appendChild(doc.createTextNode("tt"));
//		staff.appendChild(sublib);

                source = new DOMSource(doc);
                transformer.transform(source, result);

	  }  catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }



tcount1++;

it1.next();

 }


   String sucess="<email_ids><email_id>Record Successfully Exported to XML, Click here to view<a href="+request.getContextPath()+"/viewxml.jsp"+">View</a></email_id></email_ids>";

        if (!sucess.equals(""))
        {
        response.setContentType("application/xml");
        response.getWriter().write(sucess);

        }
        return null;
    }
}
