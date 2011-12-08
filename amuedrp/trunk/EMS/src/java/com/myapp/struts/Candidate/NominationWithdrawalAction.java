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
        //NominationListActionForm frm1 = (NominationListActionForm)form;
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("user_id");
        String institute_id = (String)session.getAttribute("institute_id");
        String staff_id = (String)session.getAttribute("staff_id");
        System.out.println("staff_id="+staff_id);
        //ArrayList<CandidateRegLoginDetails> lstcandi1 = new ArrayList<CandidateRegLoginDetails>();
        List<CandidateRegLoginDetails> lstcandi = (List<CandidateRegLoginDetails>)CandidateRegistrationDAO.searchCandidate(staff_id, institute_id);
        List <CandidateRegistration>stat=CandidateRegistrationDAO.getCandidateDetails(institute_id, staff_id);

        if(lstcandi!=null && !lstcandi.isEmpty())
        {
            Election e = lstcandi.get(0).getElection();
            Calendar cal = Calendar.getInstance();
            Date d = cal.getTime();
            if(e.getWithdrawlDate().before(d) && e.getWithdrawlEndDate().after(d))
            {
           //CandidateRegistration cand = lstcandi.get(0).getCandidateRegistration();
                CandidateRegistration cand =stat.get(0);
           System.out.println("Check"+stat.get(0));

            cand.setStatus("Withdraw");
            
            CandidateRegistrationDAO.update(cand);
           
            request.setAttribute("msg", "Candidate Withdrawal Successful");
            }
            else
            {
                request.setAttribute("msg", "You can only withdraw ur candidature between "+ e.getWithdrawlDate() +" to "+e.getWithdrawlEndDate());
            }
        }
        return mapping.findForward("success");
    }
}
