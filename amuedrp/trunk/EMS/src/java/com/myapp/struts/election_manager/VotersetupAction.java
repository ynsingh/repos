/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.Voter.VoterRegistrationDAO;
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
 * @author Edrp-04
 */
public class VotersetupAction extends org.apache.struts.action.Action {
    
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
         List rst,rst1;
        HttpSession session = request.getSession();
        String institute_id=(String)session.getAttribute("institute_id");
        String manager_id=(String)session.getAttribute("user_id");
        String status = (String)request.getParameter("status");
        VoterRegistrationDAO admindao=new VoterRegistrationDAO();
       // ElectionDAO electiondao=new ElectionDAO();
       String status1=null;
       if(status==null) status1 = null;
       else if(status.equalsIgnoreCase("A")) status1 = "REGISTERED";
       else if(status.equalsIgnoreCase("B")) status1 = "Block";
       else if(status.equalsIgnoreCase("AB")) status1 = "REGISTERED";
         rst = admindao.getVoterDetailsByStatus(institute_id,status1);

                    session.setAttribute("resultset", rst);
        return mapping.findForward(SUCCESS);
    }
}
