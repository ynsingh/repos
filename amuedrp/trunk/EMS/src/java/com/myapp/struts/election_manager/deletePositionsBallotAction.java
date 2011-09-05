/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.hbm.Position1;
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
public class deletePositionsBallotAction extends org.apache.struts.action.Action {
    
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
         String positions="";
        PositionDAO emailDAO = new PositionDAO();
        HttpSession session = request.getSession();
        String electionid = (String)request.getParameter("getElectionId");
        String instituteId = (String)session.getAttribute("institute_id");
        String positionId = (String)request.getParameter("positionId");

        Position1 position = emailDAO.searchPosition1(Integer.parseInt(positionId), electionid, instituteId);
        if(position!=null)
        {
            emailDAO.deletePosition(position);
            positions+="<email_ids><message>Position Deleted Successfully</message></email_ids>";
            response.setContentType("application/xml");
            response.getWriter().write(positions);
            return null;
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
