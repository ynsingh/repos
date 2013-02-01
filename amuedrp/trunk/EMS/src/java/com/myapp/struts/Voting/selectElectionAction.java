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
 * @author iqubal
 */
public class selectElectionAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {



        String election =(String) request.getParameter("election");
        System.out.println("election="+election);
        if(election==null || election.isEmpty())
            election = (String)request.getAttribute("election");
        System.out.println("election="+election);

        String compute =(String) request.getParameter("compute");
        String agm =(String) request.getParameter("agm");


if(agm!=null){



        if(election!=null && !election.isEmpty())
        {
            HttpSession session = request.getSession();
            String institute_id = (String)session.getAttribute("institute_id");
            Election elec = ElectionDAO.searchElection(election.trim(),institute_id);
            session.setAttribute("election_id", election);

               session.setAttribute("electionName", elec.getElectionName());
        }
         return mapping.findForward("success2");

        }






        if(compute!=null){

          

        if(election!=null && !election.isEmpty())
        {
            HttpSession session = request.getSession();
            String institute_id = (String)session.getAttribute("institute_id");
            Election elec = ElectionDAO.searchElection(election.trim(),institute_id);
            session.setAttribute("election_id", election);

               session.setAttribute("electionName", elec.getElectionName());
        }
         return mapping.findForward("success1");

        }

        if(election!=null && !election.isEmpty())
        {
            HttpSession session = request.getSession();
            String institute_id = (String)session.getAttribute("institute_id");
            Election elec = ElectionDAO.searchElection(election.trim(),institute_id);
            session.setAttribute("election_id", election);

               session.setAttribute("electionName", elec.getElectionName());
        }
        System.out.println("request URI="+request.getRequestURI());
        
         if(request.getRequestURI().endsWith("viewelection.do"))
            return mapping.findForward("view");

        if(request.getRequestURI().endsWith("election.do"))
            return mapping.findForward(SUCCESS);
        return mapping.findForward(SUCCESS);
    }
}
