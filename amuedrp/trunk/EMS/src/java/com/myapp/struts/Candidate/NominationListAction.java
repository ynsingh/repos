/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Candidate;

import com.myapp.struts.hbm.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

/**
 *
 * @author faraz
 */
public class NominationListAction extends org.apache.struts.action.Action {
    
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
        NominationListActionForm frm1 = (NominationListActionForm)form;

        String user_id = (String)request.getAttribute("user_id");
        String institute_id = (String)request.getAttribute("institute_id");
        String staff_id = (String)request.getAttribute("staff_id");

        List<CandidateRegLoginDetails> lstcandi = (List<CandidateRegLoginDetails>)CandidateRegistrationDAO.searchCandidate(staff_id, institute_id);
        if(lstcandi!=null)
        {
            request.setAttribute("nominationList", lstcandi);
        }
        return mapping.findForward(SUCCESS);
    }
}
