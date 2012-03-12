/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voting;

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
public class selectElectionAction1 extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

 HttpSession session = request.getSession();


        String election =(String) request.getParameter("election");
        
        if(election==null || election.isEmpty())
            election = (String)session.getAttribute("election");
         
        

        if(election!=null && !election.isEmpty())
        {
          
            String institute_id = (String)session.getAttribute("institute_id");
            Election elec = ElectionDAO.searchElection(election.trim(),institute_id);
            session.setAttribute("election_id", election);

               session.setAttribute("electionName", elec.getElectionName());
        }
        
        
        if(((String)session.getAttribute("key")).equalsIgnoreCase("DirectLogin"))
            return mapping.findForward("success");
        else
            return mapping.findForward("fail");
    }
}
