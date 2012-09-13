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
 * @author Iqubal
 */
public class getElectionRule extends org.apache.struts.action.Action {
    
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
        int posid = Integer.parseInt(request.getParameter("setpos"));
        int data = Integer.parseInt(request.getParameter("div"));
        String instituteId = (String)session.getAttribute("institute_id");

        System.out.println("DIV  #########################"+data);
        if(electionid==null)
        electionid = (String)session.getAttribute("election_id");
        List<Electionrule> position = (List<Electionrule>)emailDAO.getRules1(instituteId, electionid,posid);
        positions+="<positions1>";
        if(position!=null)
        {
            System.out.println("Rule count="+position.size());
            Iterator itpos = position.listIterator();

            while(itpos.hasNext())
            {
                Electionrule pos = (Electionrule)itpos.next();
                positions+="<position1>";
                positions+="<positionId1>"+pos.getId().getRuleId()+"</positionId1>";
                positions+="<positionname1>"+pos.getCriteria()+"</positionname1>";
                positions+="<pos>"+String.valueOf(data)+"</pos>";
                positions+="</position1>";
        }
        
        
    }
        positions+="</positions1>";
       session.setAttribute("position", position);
//        System.out.println("XML ="+positions);
        response.setContentType("application/xml");
        response.getWriter().write(positions);
        return null;
}
}
