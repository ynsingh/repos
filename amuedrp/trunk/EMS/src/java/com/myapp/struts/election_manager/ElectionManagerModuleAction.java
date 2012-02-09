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
public class ElectionManagerModuleAction extends org.apache.struts.action.Action {
    
    
    private static final String SUCCESS = "success";

    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List rst,rst1;
        HttpSession session = request.getSession();
        String institute_id=(String)session.getAttribute("institute_id");
        String manager_id=(String)session.getAttribute("user_id");

System.out.println("dsfsdfsdfsdf");
        VoterRegistrationDAO admindao=new VoterRegistrationDAO();
        ElectionDAO electiondao=new ElectionDAO();
                    session.removeAttribute("resultset");
                    rst = admindao.getVoterDetailsByStatus(institute_id,"NOT REGISTERED");

                    session.setAttribute("resultset", rst);

                    int count = admindao.getVoterRequestCount(institute_id,"NOT REGISTERED");

                    session.setAttribute("count", count);


                    rst1= electiondao.GetElectionDetails1(institute_id,manager_id);
                     session.setAttribute("resultset1", rst1);

        return mapping.findForward(SUCCESS);
    }
}
