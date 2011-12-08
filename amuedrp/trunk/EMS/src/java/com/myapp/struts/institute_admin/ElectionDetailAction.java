/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.institute_admin;

import com.myapp.struts.Candidate.CandidateRegistrationDAO;
import com.myapp.struts.hbm.CandidateRegistration;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.ElectionDAO;
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
public class ElectionDetailAction extends org.apache.struts.action.Action {
    
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
        HttpSession session = request.getSession();
        ShowElectionActionForm sef=(ShowElectionActionForm)form;
        String manager_id=request.getParameter("id");
        String election_id=request.getParameter("electionId");
        String institute_id = (String)session.getAttribute("institute_id");

        Election e = ElectionDAO.searchElection(election_id, institute_id);
        if(e!=null)
        {
            List<VoterRegistration> voterreg=ElectionDAO.searchVoter(institute_id);
            List<Candidate1> candireg=ElectionDAO.searchCandidate(institute_id,election_id);
            sef.setElectionName(e.getElectionName());
            sef.setNstart(e.getNstart().toString());
            sef.setNend(e.getNend().toString());
            sef.setScrutnyDate(e.getScrutnyDate().toString());
            sef.setScrutnyEndDate(e.getScrutnyEndDate().toString());
            sef.setWithdrawlDate(e.getWithdrawlDate().toString());
            sef.setWithdrawlEndDate(e.getWithdrawlEndDate().toString());
            sef.setStartDate(e.getStartDate().toString());
            sef.setEndDate(e.getEndDate().toString());
            sef.setNo_of_voters(String.valueOf(voterreg.size()));
            sef.setNoofcandi(String.valueOf(candireg.size()));

            request.setAttribute("voterreg", voterreg);
           // List<CandidateRegistration> cr = (List<CandidateRegistration>)CandidateRegistrationDAO.
        }

        return mapping.findForward(SUCCESS);
    }
}
