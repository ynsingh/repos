/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.Candidate.CandidateRegistrationDAO;
import com.myapp.struts.Voter.VoterRegistrationDAO;
import com.myapp.struts.hbm.AdminRegistrationDAO;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.ElectionDetails;
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
public class VotersetupAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         List rst,rst1;

        HttpSession session = request.getSession();
        session.removeAttribute("resultset");
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
        String status = (String)request.getParameter("status");
        String searchby = request.getParameter("search_by");
      String searchkeyword = request.getParameter("search_keyword");
      String sortby = request.getParameter("sort_by");
      System.out.println(sortby+"......................hhdfh.........")   ;
        VoterRegistrationDAO admindao=new VoterRegistrationDAO();
       // ElectionDAO electiondao=new ElectionDAO();
       String status1=null;
       session.removeAttribute("stat");
       if(status==null){
           
           session.removeAttribute("stat");
           status1 = null;
       }
       else if(status.equalsIgnoreCase("A"))
       {  session.removeAttribute("stat");
           status1 = "REGISTERED";}
       else if(status.equalsIgnoreCase("D"))
       {
            
           session.removeAttribute("stat");
           status1 = "REGISTERED";
           if(sortby==null)
           sortby="id.enrollment";
          
       if(searchkeyword!=null && searchkeyword.isEmpty()==false)
       rst = admindao.getVoterDetailsByStatus1(institute_id,status1,searchby,searchkeyword,sortby,pageno);

       else
           rst = admindao.getVoterDetailsByStatus1(institute_id,status1,null,null,sortby,pageno);
            session.removeAttribute("resultset");
        session.setAttribute("resultset", rst);
        return mapping.findForward("success4");
       }
       else if(status.equalsIgnoreCase("B"))
       {
           session.setAttribute("stat", "Blk");
           status="block";
           AdminRegistrationDAO candi=new AdminRegistrationDAO();
           List  v=null;
  // String institute_id=(String)session.getAttribute("institute_id");
           System.out.println("institute id "+institute_id);
      v=(List<ElectionDetails>)candi.getElectionDetails1(searchkeyword,searchby,sortby,institute_id);


           status1 = "Block";
           System.out.println("Block status #################################"+status1+v.size());
          
           session.setAttribute("resultset", v);
           return mapping.findForward("success2");
       }
       else if(status.equalsIgnoreCase("AB"))
       {session.removeAttribute("stat");
           status1 = "REGISTERED";}
        else if(status.equalsIgnoreCase("BL")){
            session.removeAttribute("stat");
           status1 = "Block";
        CandidateRegistrationDAO candi=new CandidateRegistrationDAO();
 List<VoterRegistration>     v=null;
   //String institute_id=(String)session.getAttribute("institute_id");
      v=candi.GetblockfromloginVoterList(institute_id);

 session.setAttribute("resultset", v);
 return mapping.findForward("success3");
        
        }
       if(sortby==null)
           sortby="id.enrollment";

       if(searchkeyword!=null && searchkeyword.isEmpty()==false)
       rst = admindao.getVoterDetailsByStatus(institute_id,status1,searchby,searchkeyword,sortby,pageno);
       else
           rst = admindao.getVoterDetailsByStatus(institute_id,status1,null,null,sortby,pageno);

        session.setAttribute("resultset", rst);
        return mapping.findForward(SUCCESS);
    }
}
