/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;

import com.myapp.struts.Candidate.CandidateRegistrationDAO;
import com.myapp.struts.ajax.Position1DAO;
import com.myapp.struts.hbm.Candidate1;
import com.myapp.struts.hbm.Candidate1Id;
import com.myapp.struts.hbm.CandidateRegistration;
import com.myapp.struts.hbm.CandidateRegistrationId;
import com.myapp.struts.hbm.PositionDAO;
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
public class ApplyCandidatureAction extends org.apache.struts.action.Action {
    
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
        HttpSession session = request.getSession();
        String position = request.getParameter("position");
        String election = request.getParameter("election");
        String institute_id =(String)session.getAttribute("institute_id");
        String enrollment =(String)session.getAttribute("staff_id");
        

        CandidateRegistration candi1 = CandidateRegistrationDAO.searchCandidature(election, enrollment, institute_id);
        if(candi1==null)
        {
        CandidateRegistrationId crId = new CandidateRegistrationId(enrollment, institute_id,election);
        CandidateRegistration ob = new CandidateRegistration(crId);

            ob.setPosition(position);
            ob.setStatus("not registered");
//            Candidate1Id pos1 = new Candidate1Id();
//            Candidate1 pos = new Candidate1();
//         pos1.setElectionId(election);
//         pos1.setInstituteId(institute_id);
//       pos1.setPositionId(Integer.parseInt(position));
//       //pos1.setCandidateId(Integer.parseInt());
//         pos.setCandidateName(username);
//         pos.setEnrollment(enrollment);
//         pos.setId(pos1);

        CandidateRegistrationDAO.insert(ob);
//        PositionDAO positiondao = new PositionDAO();
//        positiondao.insertcandidate(pos);
        response.setContentType("application/xml");
        response.getWriter().write( "<messages><message>request for candidature has been sent succsessfully</message></messages>");
        }
        else
        {
            response.setContentType("application/xml");
        response.getWriter().write( "<messages><message>You Already Send Candidature Request for this Election</message></messages>");
        }
     return null;
    }
}
