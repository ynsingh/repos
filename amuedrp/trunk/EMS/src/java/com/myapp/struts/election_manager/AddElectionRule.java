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
public class AddElectionRule extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StringBuffer emails=new StringBuffer();
        PositionDAO emailDAO = new PositionDAO();
        String positionText = request.getParameter("setRule");
      
        String electionid = request.getParameter("setElectionId");
        String posid = request.getParameter("setposId");
      //  String instruct = request.getParameter("setposInstruction");
        HttpSession session = request.getSession();
       // String electionId = (String)session.getAttribute("electionid");
        System.out.println("Ajaxxxxxxxxxxxxxxxxxxxxxx"+positionText+electionid);
        String instituteId = (String)session.getAttribute("institute_id");
        Electionrule position = new Electionrule();
       // if(posid.equals(""))
        position = emailDAO.getRuleByName(positionText,electionid,instituteId);
      //  else
        //    position = emailDAO.searchPosition1(Integer.parseInt(posid),electionid,instituteId);

        if(position==null)
        {
            Electionrule pos = new Electionrule();
            ElectionruleId posId = new ElectionruleId();
            System.out.println("Election Id = "+electionid);
            posId.setElectionId(electionid);
            System.out.println("Institute Id = "+instituteId);
            posId.setInstituteId(instituteId);
            posId.setRuleId((String)ElectionDAO.returnMaxElectionRuleId(instituteId,electionid));
           // pos.setBallotId(electionid);

            System.out.println("Position Text = "+positionText);
            pos.setCriteria(positionText);
            //System.out.println("Choice = "+noofChoiceText);
           // pos.setNumberOfChoice(noofChoiceText);
            pos.setId(posId);
           // pos.setInstruction(instruct);
            emailDAO.insert1(pos);
            response.setContentType("application/xml");
            emails.append("<email_ids>");
            emails.append("<message>Criteria Saved successfully</message></email_ids>");
            System.out.println("Ajaxxxxxxxxxxxxxxxxxxxxxx");
        response.getWriter().write(emails.toString());
        }
        else{

           // position.setPositionName(positionText);
          //  position.setNumberOfChoice(noofChoiceText);
          //  position.setInstruction(instruct);
          //  emailDAO.updatePosition(position);

        response.setContentType("application/xml");
            emails.append("<email_ids>");
            emails.append("<message>Position updated successfully</message></email_ids>");
            response.getWriter().write(emails.toString());


        }
        return null;
        //return mapping.findForward(SUCCESS);
    }
}
