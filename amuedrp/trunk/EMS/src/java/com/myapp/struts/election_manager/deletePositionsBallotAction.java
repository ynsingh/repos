/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.hbm.Candidate1;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.Position1;
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
 * @author Iqubal Ahmad
 */
public class deletePositionsBallotAction extends org.apache.struts.action.Action {
    
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
        String positionId = (String)request.getParameter("positionId");
        System.out.println("11111111electionid"+electionid+"positionid="+positionId);
        Position1 position = emailDAO.searchPosition1(Integer.parseInt(positionId), electionid, instituteId);
        
        if(position!=null)
        {
            PositionDAO pos=new PositionDAO();
            List<Candidate1> obj=(List<Candidate1>)pos.getCandidate(position.getId().getPositionId(),position.getId().getElectionId(),instituteId);
          
            if(obj.isEmpty()){
            
            emailDAO.deletePosition(position);
            positions+="<email_ids><message>Position Deleted Successfully</message></email_ids>";
            response.setContentType("application/xml");
            response.getWriter().write(positions);
            return null;
            }else{
            positions+="<email_ids><message>Sorry Position Cannot be Deleted because Candidature request is sent against this position </message></email_ids>";
            response.setContentType("application/xml");
            response.getWriter().write(positions);
            return null;

            }
        }
        else
        {
            positions+="<email_ids><message>Position Not Exist!</message></email_ids>";
            response.setContentType("application/xml");
            response.getWriter().write(positions);
            return null;
        }
        //return mapping.findForward(SUCCESS);
    }
}
