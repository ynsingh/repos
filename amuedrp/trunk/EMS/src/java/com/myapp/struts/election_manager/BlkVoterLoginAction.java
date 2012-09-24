/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.VoterRegistration;
import com.myapp.struts.Voter.VoterRegistrationDAO;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edrp-03
 */
public class BlkVoterLoginAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session=request.getSession();
        String id=request.getParameter("id");

        String institute_id=(String)session.getAttribute("institute_id");

        VoterRegistration voter=new VoterRegistration();
        VoterRegistrationDAO voterdao=new VoterRegistrationDAO();
        voter=voterdao.searchVoterRegistration(institute_id,id);
        voter.setStatus("Blockedfromlogin");
        voterdao.update(voter);
        System.out.println("Blocke from Loginnnnnnnnnnnnnnn");


        request.setAttribute("msg", "Voter with Enrollment No:-"+id+" Blocked From Login Successfully");
        
        return mapping.findForward(SUCCESS);
    }
}
