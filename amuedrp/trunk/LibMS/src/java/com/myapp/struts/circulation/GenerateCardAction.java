/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirculationDAO.CirculationDAO;
import com.myapp.struts.hbm.CirMemberDetail;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class GenerateCardAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String memid,library_id;
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GenerateCardActionForm myform=(GenerateCardActionForm)form;
        memid=myform.getTXTMEMID();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        CirMemberDetail memobj=CirculationDAO.searchCirMemDetails(library_id, memid);
        System.out.println("@@@@@@@@"+memobj.getFname()+" "+memobj.getEmail()+" "+memobj.getAddress1());
        request.setAttribute("memobj", memobj);
        return mapping.findForward(SUCCESS);
    }
}
