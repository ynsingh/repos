/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import com.myapp.struts.MyExecuteQueryAction;
import java.sql.ResultSet;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Faraz
 */
public class LogoutAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

      HttpSession session=request.getSession();
      session.removeAttribute("rs");

                session.removeAttribute("rs1");

                  session.removeAttribute("finedetails_resultset");
        session.removeAttribute("reservationdetails_resultset");
        session.removeAttribute("card_id");
        session.removeAttribute("mem_id");

        return mapping.findForward("success");
    }
}
