/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.hbm.ElectionManagerDAO;
import com.myapp.struts.hbm.VotingProcess;
import com.myapp.struts.utility.AppPath;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author edrp01
 */
public class XMLExportAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("checkAction2");
        HttpSession session= request.getSession();
       // StaffDetailDAO staffdao=new StaffDetailDAO();
        String login=(String)session.getAttribute("user_id");
        String login_role=(String)session.getAttribute("login_role");
        String institute_id=(String)session.getAttribute("institute_id");
        String election_id=request.getParameter("election");
        ElectionManagerDAO dao=new ElectionManagerDAO();
        List<CandidateReg1> staffobj=dao.VotedVoterListXML(institute_id,election_id);
         //  List<staffsubLib1>  staffobj=null;
        String path=AppPath.getProjectExportPath();
        if(login.indexOf(".")>0)
            login=login.substring(0,login.indexOf("."))+election_id;
        System.out.println("checkAction1"+staffobj.size()+"..................."+login);

        Iterator it1 = staffobj.iterator();
        int tcount1=0;

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Voted_Voter_details");
		doc.appendChild(rootElement);
                DOMSource source=null;
                // write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

                

		StreamResult result = new StreamResult(new File(path+login+".xml"));
                request.setAttribute("exportpath",login+".xml");
               

            while(it1.hasNext())
            {
              //  Exportxml.writexml(path, staffobj.get(tcount1).getStaffDetail().getId().getLibraryId(), staffobj.get(tcount1).getStaffDetail().getSublibraryId(),staffobj.get(tcount1).getStaffDetail().getId().getStaffId(),staffobj.get(tcount1).getStaffDetail().getTitle(),staffobj.get(tcount1).getStaffDetail().getFirstName(), staffobj.get(tcount1).getStaffDetail().getLastName(),"admin",staffobj.get(tcount1).getStaffDetail().getContactNo(),staffobj.get(tcount1).getStaffDetail().getMobileNo() ,staffobj.get(tcount1).getStaffDetail().getEmailId(),staffobj.get(tcount1).getStaffDetail().getDateJoining(),staffobj.get(tcount1).getStaffDetail().getDateReleaving(),staffobj.get(tcount1).getStaffDetail().getFatherName(),staffobj.get(tcount1).getStaffDetail().getDateOfBirth(),staffobj.get(tcount1).getStaffDetail().getGender(),staffobj.get(tcount1).getStaffDetail().getAddress1(),staffobj.get(tcount1).getStaffDetail().getCity1(),staffobj.get(tcount1).getStaffDetail().getState1(),staffobj.get(tcount1).getStaffDetail().getCountry1(),staffobj.get(tcount1).getStaffDetail().getZip1(),staffobj.get(tcount1).getStaffDetail().getAddress2(),staffobj.get(tcount1).getStaffDetail().getCity2(),staffobj.get(tcount1).getStaffDetail().getState2(),staffobj.get(tcount1).getStaffDetail().getCountry2(),staffobj.get(tcount1).getStaffDetail().getZip2());
		// staff elements
		Element staff = doc.createElement("Voter_Personal_Details");
		rootElement.appendChild(staff);
		Element id = doc.createElement("Institute_Name");
                if(staffobj.get(tcount1).getI_institute_name()!=null && staffobj.get(tcount1).getI_institute_name().isEmpty()==false)
		id.appendChild(doc.createTextNode(staffobj.get(tcount1).getI_institute_name()));
                else
                id.appendChild(doc.createTextNode("NA"));
		staff.appendChild(id);
		Element electionname = doc.createElement("Election_Name");
                if(staffobj.get(tcount1).getE_election_name()!=null && staffobj.get(tcount1).getE_election_name().isEmpty()==false)
		 electionname.appendChild(doc.createTextNode(staffobj.get(tcount1).getE_election_name()));
                else
                electionname.appendChild(doc.createTextNode("NA"));
		staff.appendChild(electionname);
 		// firstname elements
		Element firstname = doc.createElement("Voter_Name");
                if(staffobj.get(tcount1).getV_voter_name()!=null && staffobj.get(tcount1).getV_voter_name().isEmpty()==false)
		firstname.appendChild(doc.createTextNode(staffobj.get(tcount1).getV_voter_name()));
                else
                firstname.appendChild(doc.createTextNode("NA"));
		staff.appendChild(firstname);
		Element nickname = doc.createElement("Voter_Email_Id");
                if(staffobj.get(tcount1).getV_email()!=null && staffobj.get(tcount1).getV_email().isEmpty()==false)
		nickname.appendChild(doc.createTextNode(staffobj.get(tcount1).getV_email()));
                else
                nickname.appendChild(doc.createTextNode("NA"));
		staff.appendChild(nickname);
		Element role = doc.createElement("VotedStatus");
                if(staffobj.get(tcount1).getStatus()!=null && staffobj.get(tcount1).getStatus().isEmpty()==false)
		role.appendChild(doc.createTextNode(staffobj.get(tcount1).getStatus()));
                else
                    role.appendChild(doc.createTextNode("NA"));
		staff.appendChild(role);
      tcount1++;
     it1.next();
 }
                try{
                    source = new DOMSource(doc);
                    transformer.transform(source, result);
                 }  catch (TransformerException tfe)
                 {
		   tfe.printStackTrace();
	         }

   String msg="Record Successfully Exported to XML and Saved ";
   session.setAttribute("exportmsg", msg);
   return mapping.findForward(SUCCESS);
    }
}