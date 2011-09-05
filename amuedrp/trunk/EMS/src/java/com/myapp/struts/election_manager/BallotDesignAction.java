/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.hbm.ElectionDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edrp-04
 */
public class BallotDesignAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String election_id;
    private String institute_id;
    
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

        HttpSession session=request.getSession();
        election_id=(String)session.getAttribute("election_id");
        institute_id=(String)session.getAttribute("institute_id");
         String manager_id=(String)session.getAttribute("user_id");
         ElectionDAO electiondao=new ElectionDAO();
        session.removeAttribute("election_id");
         
             List rst1= electiondao.Elections(institute_id);
                     session.setAttribute("electionList", rst1);
 
        
        return mapping.findForward(SUCCESS);
    }
}
