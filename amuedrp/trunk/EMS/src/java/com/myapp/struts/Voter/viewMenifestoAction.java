/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;

import com.myapp.struts.Candidate.CandidateRegistrationDAO;
import com.myapp.struts.hbm.Candidate1;
import javax.servlet.ServletOutputStream;
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
public class viewMenifestoAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        String enrol = request.getParameter("en");
        String eid = request.getParameter("eid");
        String instituteId = (String)session.getAttribute("institute_id");
        Candidate1 cand = CandidateRegistrationDAO.getCandidate(instituteId, enrol, eid);
        byte[] meni = cand.getMenifesto();
        response.setContentType("application/pdf");
        String data = meni.toString();
        System.out.println("PDF Data="+data);
        ServletOutputStream stream = null;
        stream = response.getOutputStream();
        stream.write(meni);
        return null;
    }
}
