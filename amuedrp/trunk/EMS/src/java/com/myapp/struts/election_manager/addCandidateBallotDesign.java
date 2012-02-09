/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.ajax.Position1DAO;
import com.myapp.struts.hbm.*;
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
public class addCandidateBallotDesign extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StringBuffer emails= new StringBuffer();
        PositionDAO emailDAO = new PositionDAO();
        String positionText = request.getParameter("setPosition");
        String noofChoiceText = request.getParameter("setCandidate");
        String candidateId = request.getParameter("setId");
        HttpSession session = request.getSession();
        String electionId = (String)session.getAttribute("electionid");
        String instituteId = (String)session.getAttribute("institute_id");
        Position1 position = emailDAO.getPositionByName(positionText,electionId,instituteId);

        if(position!=null)
        {
            Candidate1 checkcandi = emailDAO.checkCandidateByName(noofChoiceText,candidateId, position.getId().getPositionId(), electionId, instituteId);
            if(checkcandi==null)
            {
            Candidate1 candidate = emailDAO.getCandidateByName(candidateId,position.getId().getPositionId(), electionId, instituteId);
            if(candidate==null)
            {
            Candidate1 pos = new Candidate1();
            Candidate1Id pos1 = new Candidate1Id();
            
            pos1.setElectionId(electionId);
            pos1.setInstituteId(instituteId);
            pos1.setPositionId(position.getId().getPositionId());
            pos1.setCandidateId(Integer.parseInt(candidateId));
            pos.setCandidateName(noofChoiceText);
            System.out.println("Position Text = "+positionText);
            pos.setId(pos1);
            System.out.println("Choice = "+noofChoiceText);
                        
            emailDAO.insertcandidate(pos);
            response.setContentType("application/xml");
             emails.append("<email_ids>");
        emails.append("<message>Candidate Saved successfully</message>");
             emails.append("</email_ids>");
        response.getWriter().write(emails.toString());
        }
        else{
        
            candidate.setCandidateName(noofChoiceText);
            
            
            emailDAO.updateCandidate(candidate);
            response.setContentType("application/xml");
             emails.append("<email_ids>");
        emails.append("<message>Candidate updated successfully</message>");
        emails.append("</email_ids>");
        response.getWriter().write(emails.toString());

        
        }
            }
            response.setContentType("application/xml");
             emails.append("<email_ids>");
        emails.append("<message>Candidate Already Exist with this Name</message>");
        emails.append("</email_ids>");
        response.getWriter().write(emails.toString());
        }
        
        return null;
        //return mapping.findForward(SUCCESS);
    }
}
