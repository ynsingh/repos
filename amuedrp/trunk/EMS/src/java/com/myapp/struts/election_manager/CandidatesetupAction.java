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
public class CandidatesetupAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
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
String status="not registered";
       //  rst = admindao.getVoterDetailsByStatus(institute_id,"REGISTERED");
    List<VoterCandidate>     rst1=candidatedao.GetDetails(institute_id,status );
//System.out.println("VOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+rst1.get(0).getVoterRegistration().getStatus());
                   // session.setAttribute("resultset", rst);
                    session.setAttribute("resultset1", rst1);
        return mapping.findForward(SUCCESS);
    }
}
