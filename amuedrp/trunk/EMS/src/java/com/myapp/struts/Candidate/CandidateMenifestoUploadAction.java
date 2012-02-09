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
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        CandidateMenifestoActionForm form1 = (CandidateMenifestoActionForm)form;
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("user_id");
        String staff_id = (String)session.getAttribute("staff_id");
        String institute_id = (String)session.getAttribute("institute_id");
String election_id=(String)session.getAttribute("election_id");
String position_id=(String)session.getAttribute("position_id");
System.out.println(election_id+position_id);
        Candidate1 cand = (Candidate1)CandidateRegistrationDAO.searchcandidateMenifesto(institute_id,staff_id,election_id,position_id);
       System.out.println(cand);
        if(cand!=null )
        {
            Candidate1 candidate = cand;
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
