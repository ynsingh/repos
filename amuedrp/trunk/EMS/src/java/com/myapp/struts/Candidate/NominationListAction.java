/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Candidate;

import com.myapp.struts.hbm.*;
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
public class NominationListAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        NominationListActionForm frm1 = (NominationListActionForm)form;
        HttpSession session = request.getSession();
        
        String institute_id = (String)session.getAttribute("institute_id");
        String staff_id = (String)session.getAttribute("user_id");

        List<CandidateRegLoginDetails> lstcandi = (List<CandidateRegLoginDetails>)CandidateRegistrationDAO.searchCandidate1(staff_id, institute_id);
      System.out.println(lstcandi.size()+"..............."+staff_id);
        if(lstcandi!=null)
        {


            request.setAttribute("nominationList", lstcandi);
            session.setAttribute("nominationList", lstcandi);
        }
        return mapping.findForward("show");
    }
}
