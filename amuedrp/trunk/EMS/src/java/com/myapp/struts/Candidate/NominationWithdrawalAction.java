/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Candidate;

import com.myapp.struts.hbm.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author faraz
 */
public class NominationWithdrawalAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("user_id");
        String institute_id = (String)session.getAttribute("institute_id");

        String staff_id = (String)session.getAttribute("staff_id");
        System.out.println("staff_id="+staff_id);
       String election_id=(String)request.getParameter("id");
       String position_id=(String)request.getParameter("pos_id");
       CandidateRegLoginDetails lstcandi = (CandidateRegLoginDetails)CandidateRegistrationDAO.searchCandidateElection(user_id, institute_id,election_id,position_id);
        CandidateRegistration stat=CandidateRegistrationDAO.getCandidateDetails1(institute_id, staff_id,election_id,position_id);
System.out.println(lstcandi+""+stat);
        if(lstcandi!=null && stat!=null)
        {
           
            Election e = lstcandi.getElection();
            Calendar cal = Calendar.getInstance();
            Date d = cal.getTime();
            if(e.getWithdrawlDate().before(d) && e.getWithdrawlEndDate().after(d))
            {
         
                CandidateRegistration cand =stat;
           

            cand.setStatus("Withdraw");
            
            CandidateRegistrationDAO.update(cand);
           
            request.setAttribute("msg", "Candidate Withdrawal Successful");
            }
            else
            {
                request.setAttribute("msg1", "You can only withdraw ur candidature between "+ e.getWithdrawlDate() +" to "+e.getWithdrawlEndDate());
                
            }
        }
        return mapping.findForward("success");
    }
}
