/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Candidate;

import com.myapp.struts.hbm.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

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
        //ArrayList<CandidateRegLoginDetails> lstcandi1 = new ArrayList<CandidateRegLoginDetails>();
        List<CandidateRegLoginDetails> lstcandi = (List<CandidateRegLoginDetails>)CandidateRegistrationDAO.searchCandidate(staff_id, institute_id);
        if(lstcandi!=null && !lstcandi.isEmpty())
        {
            Election e = lstcandi.get(0).getElection();
            Calendar cal = Calendar.getInstance();
            Date d = cal.getTime();
            if(e.getWithdrawlDate().after(d) && e.getWithdrawlEndDate().before(d))
            {
            CandidateRegistration cand = lstcandi.get(0).getCandidateRegistration();
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
