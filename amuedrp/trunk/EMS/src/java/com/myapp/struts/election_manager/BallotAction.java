/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Edrp-04
 */
public class BallotAction extends org.apache.struts.action.Action {
    
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
        String go = (String)request.getParameter("go");
        System.out.println("Request value="+go);
        if(go==null)
        {
        BallotActionForm ballot= (BallotActionForm)form;
        BallotPositionCandi bpc=new BallotPositionCandi();
        BallotPositionCandiDAO bpcdao= new BallotPositionCandiDAO();
        PositionDAO positiondao=new PositionDAO();
       Candidate1 candidate=new Candidate1();
       Candidate1Id candiadteid=new Candidate1Id();
       HttpSession session=request.getSession();
       String election_id=(String)session.getAttribute("election_id");
       String institute_id=(String)session.getAttribute("institute_id");
       int positionid=ballot.getPosition_id();
       System.out.println(election_id+institute_id);

       List rs=bpcdao.getPositionID(positionid, institute_id);
 Iterator it = rs.iterator();
       if(!it.hasNext()){
       bpc.getPosition1().setBallotId(election_id);
       bpc.getPosition1().setPositionName(ballot.getPosition());
       bpc.getPosition1().getId().setPositionId(ballot.getPosition_id());
       bpc.getPosition1().getId().setElectionId(election_id);
       bpc.getPosition1().getId().setInstituteId(institute_id);
       bpc.getPosition1().setNumberOfChoice(ballot.getNumberofchoice());

      // bpc.getCandidate1().getId().setCandidateId(ballot.getCandidate());
       bpc.getCandidate1().getId().setElectionId(election_id);
       bpc.getCandidate1().getId().setInstituteId(institute_id);
       bpc.getCandidate1().getId().setPositionId(ballot.getPosition_id());

       bpcdao.insert(bpc);

       System.out.println("success");
         return mapping.findForward(SUCCESS);
       }
       else

           //candiadteid.setCandidateId(ballot.getCandidate());
           candiadteid.setElectionId(election_id);
           candiadteid.setInstituteId(institute_id);
           candiadteid.setPositionId(ballot.getPosition_id());
           candidate.setId(candiadteid);

           positiondao.insertcandidate(candidate);
 System.out.println("success");
       
 
    }else
    {
        String election =(String) request.getParameter("election");
        if(election!=null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("election_id", election);
        }
    }
        return mapping.findForward(SUCCESS);
    }
}
