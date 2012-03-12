/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;

import com.myapp.struts.AdminDAO.LoginDAO;
import com.myapp.struts.Voting.VoterElectionActionForm;
import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.SetVoter;
import com.myapp.struts.hbm.VoterRegistration;
import com.myapp.struts.hbm.VotingProcess;
import com.myapp.struts.hbm.voterDAO;
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
public class CheckVoter extends org.apache.struts.action.Action {



    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       

HttpSession session=request.getSession();
String user_id = request.getParameter("email");
String password = request.getParameter("hash");
String election = request.getParameter("eid");
String onetimekey = request.getParameter("key");
 String password1 = PasswordEncruptionUtility.password_encrupt(password);
      Login l=LoginDAO.searchUser(user_id,password1);

      if(l!=null)
      {
System.out.println("Login"+election);
session.setAttribute("election", election);
session.setAttribute("key", "DirectLogin");
      VoterRegistration v=VoterRegistrationDAO.searchVoterEmail(l.getStaffDetail().getId().getInstituteId(), user_id);
        ElectionDAO e=new ElectionDAO();

        Election obj=(Election)e.Electionname(l.getStaffDetail().getId().getInstituteId(),election);
            if(obj!=null && obj.getStatus().equalsIgnoreCase("started"))
            {
                System.out.println("Login Election");
                String staff_id=v.getId().getEnrollment();
                password1 = PasswordEncruptionUtility.password_encrupt(onetimekey);
                SetVoter o=VoterRegistrationDAO.searchVoterList(l.getStaffDetail().getId().getInstituteId(),election,staff_id,password1);
  

            if(o!=null)
            {
             voterDAO voterdao = new voterDAO();
             System.out.println("ok");
                VotingProcess vp = voterdao.getVoter(l.getStaffDetail().getId().getInstituteId(), election, user_id);
if(vp!=null){
    session.invalidate();
  request.setAttribute("msg", "Sorry You Already Voted for this Election");
      return mapping.findForward("fail");

}
                System.out.println("Login Vote");
                 String path = servlet.getServletContext().getRealPath("/");
        session.setAttribute("apppath", path);
        session.setAttribute("election_id", obj.getId().getElectionId());
        session.setAttribute("user_id",l.getUserId());
                session.setAttribute("institute_id",l.getStaffDetail().getId().getInstituteId() );
                session.setAttribute("electionName",obj.getElectionName() );
              return mapping.findForward("success");

            }
            }
      }
      session.invalidate();
      request.setAttribute("msg", "You are Successfully Logout");
      return mapping.findForward("fail");

    }
}
