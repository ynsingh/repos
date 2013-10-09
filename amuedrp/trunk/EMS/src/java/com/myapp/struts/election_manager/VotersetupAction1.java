/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.Candidate.CandidateRegistrationDAO;
import com.myapp.struts.Voter.VoterRegistrationDAO;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.VoterList;
import com.myapp.struts.hbm.VoterRegistration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Edrp-04
 */
public class VotersetupAction1 extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
         List rst,rst1;

        HttpSession session = request.getSession();

          String id=(String)request.getParameter("id");

          int pageno=Integer.parseInt((String)(request.getParameter("page")==null || request.getParameter("page")=="" ?"0":request.getParameter("page")));
       System.out.println("Page No"+pageno);
          if(id!=null)
        {

CandidateRegistrationDAO candi=new CandidateRegistrationDAO();
 List<VoterRegistration>     v=null;
   String institute_id=(String)session.getAttribute("institute_id");
      v=candi.GetVoterList(institute_id);

session.setAttribute("resultset", v);
 return mapping.findForward("success1");

        }

        String institute_id=(String)session.getAttribute("institute_id");
        String manager_id=(String)session.getAttribute("user_id");
        String status = "A";
       String searchby = request.getParameter("search_by");
    
      String searchkeyword = request.getParameter("search_keyword");
 
      String sortby = request.getParameter("sort_by");
   
       VoterRegistrationDAO admindao=new VoterRegistrationDAO();
       // ElectionDAO electiondao=new ElectionDAO();
       String status1=null;
       if(status==null) status1 = null;
       else if(status.equalsIgnoreCase("A")) status1 = "REGISTERED";
       else if(status.equalsIgnoreCase("B")) status1 = "Block";
       else if(status.equalsIgnoreCase("AB")) status1 = "REGISTERED";
       
       
       if(sortby==null)
           sortby="id.enrollment";


       if(searchkeyword!=null && searchkeyword.isEmpty()==false)
       {
        rst = admindao.getVoterDetailsByStatus(institute_id,status1,searchby,searchkeyword,sortby,pageno);
           }
        else
           rst = admindao.getVoterDetailsByStatus(institute_id,status1,null,null,sortby,pageno);

        session.setAttribute("resultset", rst);
        return mapping.findForward(SUCCESS);
    }
}
