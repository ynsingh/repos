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
        String instituteId = (String)session.getAttribute("institute_id");
        if(electionid==null)
            electionid = (String)session.getAttribute("election_id");
        List<Electionrule> position = (List<Electionrule>)emailDAO.getRules(instituteId, electionid);
        positions+="<positions>";
        if(position!=null)
        {
            System.out.println("Position count="+position.size());
            Iterator itpos = position.listIterator();

            while(itpos.hasNext())
            {
     Electionrule pos = (Electionrule)itpos.next();
            positions+="<position>";
            positions+="<positionId>"+pos.getId().getRuleId()+"</positionId>";
            positions+="<positionname>"+pos.getCriteria()+"</positionname>";
            positions+="</position>";
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
