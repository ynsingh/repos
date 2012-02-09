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
public class addPositionBallotDesign extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StringBuffer emails=new StringBuffer();
        PositionDAO emailDAO = new PositionDAO();
        String positionText = request.getParameter("setPosition");
        String noofChoiceText = request.getParameter("setChoice");
        String electionid = request.getParameter("setElectionId");
        String posid = request.getParameter("setposId");
        String instruct = request.getParameter("setposInstruction");
        HttpSession session = request.getSession();
       // String electionId = (String)session.getAttribute("electionid");
        String instituteId = (String)session.getAttribute("institute_id");
        Position1 position = new Position1();
        if(posid.equals(""))
        position = emailDAO.getPositionByName(positionText,electionid,instituteId);
        else
            position = emailDAO.searchPosition1(Integer.parseInt(posid),electionid,instituteId);

        if(position==null)
        {
            Position1 pos = new Position1();
            Position1Id posId = new Position1Id();
            System.out.println("Election Id = "+electionid);
            posId.setElectionId(electionid);
            System.out.println("Institute Id = "+instituteId);
            posId.setInstituteId(instituteId);
            pos.setBallotId(electionid);
            System.out.println("Position Text = "+positionText);
            pos.setPositionName(positionText);
            System.out.println("Choice = "+noofChoiceText);
            pos.setNumberOfChoice(noofChoiceText);
            pos.setId(posId);
            pos.setInstruction(instruct);
            emailDAO.insert(pos);
            response.setContentType("application/xml");
            emails.append("<email_ids>");
            emails.append("<message>Position Saved successfully</message></email_ids>");
        response.getWriter().write(emails.toString());
        }
        else{
        
            position.setPositionName(positionText);
            position.setNumberOfChoice(noofChoiceText);
            position.setInstruction(instruct);
            emailDAO.updatePosition(position);
            
        response.setContentType("application/xml");
            emails.append("<email_ids>");
            emails.append("<message>Position updated successfully</message></email_ids>");
            response.getWriter().write(emails.toString());

        
        }
        return null;
        //return mapping.findForward(SUCCESS);
    }
}
