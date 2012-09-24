/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.hbm.*;
import java.util.Iterator;
import java.util.List;
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
public class getPositionsBallotAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         String positions="";
        PositionDAO emailDAO = new PositionDAO();
        HttpSession session = request.getSession();
        String electionid = (String)request.getParameter("getElectionId");
        String instituteId = (String)session.getAttribute("institute_id");
        if(electionid==null)
            electionid = (String)session.getAttribute("election_id");
        List<Position1> position = (List<Position1>)emailDAO.getPosition(electionid,instituteId);
        positions+="<positions>";
        if(position!=null)
        {
            
            Iterator itpos = position.listIterator();

            while(itpos.hasNext())
            {
              Position1 pos = (Position1)itpos.next();

               List<Candidate1> candi = emailDAO.getCandidate(pos.getId().getPositionId(), electionid, instituteId);

                if(candi!=null && candi.size()>Integer.parseInt(pos.getNumberOfChoice()))
                  {

                       positions+="<position>";
                       positions+="<positionId>"+pos.getId().getPositionId()+"</positionId>";
                       positions+="<positionname>"+pos.getPositionName()+"</positionname>";
                       positions+="<noofchoice>"+pos.getNumberOfChoice()+"</noofchoice>";
                       positions+="<instruction>"+pos.getInstruction()+"</instruction>";
                
                          Iterator it = candi.listIterator();
                             while(it.hasNext())
                              {
                                    positions+="<candidate>";
                                   Candidate1 can = (Candidate1)it.next();
                                  positions+="<candidateenroll>"+can.getEnrollment()+"</candidateenroll>";
                                  positions+="<candidatename>"+can.getCandidateName()+"</candidatename>";
                                  positions+="<candidateid>"+can.getId().getCandidateId()+"</candidateid>";
                                if(can.getMenifesto()==null)
                                    positions+="<candidatemn>0</candidatemn>";
                                else
                                    positions+="<candidatemn>0</candidatemn>"
;
                         positions+="</candidate>";
                }
                         positions+="</position>";
            }
  
               
        }
        
        
    }
        positions+="</positions>";
       session.setAttribute("position", position);
//        System.out.println("XML ="+positions);
        response.setContentType("application/xml");
        response.getWriter().write(positions);
        return null;
}
}
