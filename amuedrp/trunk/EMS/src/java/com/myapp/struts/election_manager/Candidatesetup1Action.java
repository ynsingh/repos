/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.Candidate.CandidateRegistrationDAO;
import com.myapp.struts.Voter.VoterRegistrationDAO;
import com.myapp.struts.hbm.CandidateRegistration;
import com.myapp.struts.hbm.VoterCandidate;
import com.myapp.struts.hbm.VoterRegistration;
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
public class Candidatesetup1Action extends org.apache.struts.action.Action {
    
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
         List rst;
        HttpSession session = request.getSession();
        String institute_id=(String)session.getAttribute("institute_id");
        String manager_id=(String)session.getAttribute("user_id");

        VoterRegistration voter= new VoterRegistration();
        CandidateRegistration candidate= new CandidateRegistration();
        VoterRegistrationDAO admindao=new VoterRegistrationDAO();
        CandidateRegistrationDAO candidatedao= new CandidateRegistrationDAO();


       // ElectionDAO electiondao=new ElectionDAO();
String status= (String)request.getParameter("status");
if(status!=null)
{if(status.equalsIgnoreCase("A")) status="REGISTERED";
else if(status.equalsIgnoreCase("B")) status="BLOCK";
else if(status.equalsIgnoreCase("R")) status="REJECTED";
else if(status.equalsIgnoreCase("U")) status="REGISTERED";
 else if(status.equalsIgnoreCase("NR")) status="not registered";
}
else status=null;
       //  rst = admindao.getVoterDetailsByStatus(institute_id,"REGISTERED");
    List<VoterCandidate>     rst1=candidatedao.GetDetails1(institute_id,status );
//System.out.println("VOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+rst1.get(0).getVoterRegistration().getStatus());
                   // session.setAttribute("resultset", rst);
                    session.setAttribute("resultset1", rst1);
            
                return mapping.findForward(SUCCESS);
           
        
    }
}
