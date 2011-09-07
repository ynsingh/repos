/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Candidate;

import com.myapp.struts.hbm.Candidate1;
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
public class CandidateMenifestoUploadAction extends org.apache.struts.action.Action {
    
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
        
        CandidateMenifestoActionForm form1 = (CandidateMenifestoActionForm)form;
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("user_id");
        String staff_id = (String)session.getAttribute("staff_id");
        String institute_id = (String)session.getAttribute("institute_id");

        List<Candidate1> cand = (List<Candidate1>)CandidateRegistrationDAO.searchcandidate(institute_id,staff_id);
        if(cand!=null && !cand.isEmpty())
        {
            Candidate1 candidate = cand.get(0);
            candidate.setMenifesto(form1.getMenifesto().getFileData());
            CandidateRegistrationDAO.updateCandidate1(candidate);
            request.setAttribute("msg", "menifesto Successfully uploaded");
        }
        else{
             request.setAttribute("msg", "menifesto upload Failure!");
        }
        return mapping.findForward(SUCCESS);
    }
}
