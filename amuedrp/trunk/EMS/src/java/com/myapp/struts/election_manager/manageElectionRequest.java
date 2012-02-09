/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
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
public class manageElectionRequest extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        ManageElectionActionForm form1 = (ManageElectionActionForm)form;
        String election_id = form1.getElection_id();
        String button = form1.getButton();
        String institute_id = (String)session.getAttribute("institute_id");
        Election elect = ElectionDAO.searchElection(election_id, institute_id);
        if(button.equalsIgnoreCase("add"))
        {
            if(elect!=null)
            {
                request.setAttribute("msg1", "Election ID Already Exist");
                mapping.findForward("error");
            }
            mapping.findForward("add");
        }


        return mapping.findForward(SUCCESS);
    }
}
