/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;

import com.myapp.struts.Voting.VoterElectionActionForm;
import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.SetVoter;
import com.myapp.struts.instituteAdmin;
import com.myapp.struts.utility.PasswordEncruptionUtility;

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
public class CheckElection extends org.apache.struts.action.Action {



    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       

HttpSession session=request.getSession();
session.removeAttribute("election_id");
        String election = request.getParameter("election");

      String inst = (String)session.getAttribute("institute_id");
      ElectionDAO e=new ElectionDAO();
      

Election obj=(Election)e.Electionname(inst,election);

if(obj!=null && obj.getStatus().equalsIgnoreCase("started"))
{
    String staff_id=(String)session.getAttribute("staff_id");

    SetVoter o=VoterRegistrationDAO.searchVoterList(inst,election,staff_id);
  //  System.out.println(election+"..................."+inst+o.getPassword());

    if(o!=null){
        if(o.getStatus()!=null){
        request.setAttribute("msgerr", "Sorry You are not a valid Voter for this Election");

String role=(String)session.getAttribute("login_role");
if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
return mapping.findForward("success1");
}else if(role.equalsIgnoreCase("Election Manager")|| role.equalsIgnoreCase("Election Manager,voter"))
   {
return mapping.findForward("success2");
}else{
    request.setAttribute("msg1",  "Sorry In Selected Election Voting Activity is not in-process");
return mapping.findForward("success3");
}

        }else{
        session.setAttribute("election", obj.getId().getElectionId());
    return mapping.findForward("success");
        }
    }
    else{
     session.setAttribute("election", obj.getId().getElectionId());
    return mapping.findForward("success");

    }



}
else
{
    request.setAttribute("msgerr", "Sorry In Selected Election Voting Activity is not in-process");

String role=(String)session.getAttribute("login_role");
if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
return mapping.findForward("success1");
}else if(role.equalsIgnoreCase("Election Manager")|| role.equalsIgnoreCase("Election Manager,voter"))
   {
return mapping.findForward("success2");
}else{
    request.setAttribute("msg1",  "Sorry In Selected Election Voting Activity is not in-process");
return mapping.findForward("success3");
}




}

    }
}
