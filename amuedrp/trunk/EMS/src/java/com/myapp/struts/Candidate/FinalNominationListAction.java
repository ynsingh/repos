/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Candidate;

import com.myapp.struts.hbm.*;
import java.util.ArrayList;
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
public class FinalNominationListAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        NominationListActionForm frm1 = (NominationListActionForm)form;
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("user_id");
        String institute_id = (String)session.getAttribute("institute_id");
        String staff_id = (String)session.getAttribute("staff_id");
        //ArrayList<CandidateRegLoginDetails> lstcandi1 = new ArrayList<CandidateRegLoginDetails>();
        List<CandidateRegLoginDetails> lstcandi = (List<CandidateRegLoginDetails>)CandidateRegistrationDAO.searchfinalCandidate(staff_id, institute_id);
        if(lstcandi!=null)
        {
            System.out.println(lstcandi.size());
//            Iterator it = lstcandi.iterator();
//            int i=0;
//            while(it.hasNext())
//            {
//            CandidateQuery cand = (CandidateQuery)it.next();
//            CandidateRegLoginDetails candi = cand.getRow();
//            lstcandi1.add(candi);
//            }
            request.setAttribute("nominationList", lstcandi);
            session.setAttribute("nominationList", lstcandi);
        }
        return mapping.findForward("show");
    }
}
