/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;

import com.myapp.struts.election_manager.*;
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
public class getViewPositionsBallotAction extends org.apache.struts.action.Action {
    
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
            System.out.println("Position count="+position.size());
            Iterator itpos = position.listIterator();

            while(itpos.hasNext())
            {
            Position1 pos = (Position1)itpos.next();
            positions+="<position>";
            positions+="<positionId>"+pos.getId().getPositionId()+"</positionId>";
            positions+="<positionname>"+pos.getPositionName()+"</positionname>";
            positions+="<noofchoice>"+pos.getNumberOfChoice()+"</noofchoice>";
            positions+="<instruction>"+pos.getInstruction()+"</instruction>";
            List<Candidate1> candi = emailDAO.getCandidate(pos.getId().getPositionId(), electionid, instituteId);
            if(candi!=null)
            {
                System.out.println("candi count="+candi.size());
                
                Iterator it = candi.listIterator();

                while(it.hasNext())
                {
                    positions+="<candidate>";
                    Candidate1 can = (Candidate1)it.next();
                    positions+="<candidatename>"+can.getCandidateName()+"</candidatename>";
                    positions+="<enrollment>"+can.getEnrollment()+"</enrollment>";
                    String meni = can.getMenifesto()!=null?"true":"false";
                    positions+="<menifesto>"+meni+"</menifesto>";
                positions+="</candidate>";
                }
                
            }
  
                positions+="</position>";
        }
        
        
    }
        positions+="</positions>";
        System.out.println("XML ="+positions);
        response.setContentType("application/xml");
        response.getWriter().write(positions);
        return null;
}
}