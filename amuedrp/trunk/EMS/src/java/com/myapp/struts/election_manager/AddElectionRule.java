/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.ajax.Position1DAO;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.PositionDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Iqubal
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
        String positionText = (String)request.getParameter("setRule");
      
        String electionid =(String) request.getParameter("setElectionId");
        int pos_id =Integer.parseInt((String) request.getParameter("setpos_id"));
   
        HttpSession session = request.getSession();
       
        System.out.println("Ajaxxxxxxxxxxxxxxxxxxxxxx"+positionText+electionid+pos_id);
        String instituteId = (String)session.getAttribute("institute_id");
        Electionrule position = new Electionrule();
       // if(posid.equals(""))
        position = emailDAO.getRuleByName(positionText,electionid,instituteId);

         List<Position1> pos1 = (List<Position1>)emailDAO.getPosition(electionid,instituteId);
        System.out.println(pos_id+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+pos1.get(0).getId().getPositionId());
         pos_id=pos1.get(0).getId().getPositionId()+pos_id;

         System.out.println(pos_id+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        if(position==null)
        {
            Electionrule pos = new Electionrule();
            ElectionruleId posId = new ElectionruleId();
            System.out.println("Election Id = "+electionid);
            posId.setElectionId(electionid);
            System.out.println("Institute Id = "+instituteId);
            posId.setInstituteId(instituteId);
            posId.setRuleId((String)ElectionDAO.returnMaxElectionRuleId(instituteId,electionid,pos_id));
            posId.setPositionId(pos_id);

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
