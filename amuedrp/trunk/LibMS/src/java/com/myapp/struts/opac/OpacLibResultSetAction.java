/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Faraz
 */
public class OpacLibResultSetAction extends org.apache.struts.action.Action {
    
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
        
        HttpSession session = request.getSession();
        session.removeAttribute("libRs");
        String sqlQuery="";
        ResultSet rs = null;
        String formname="";
        String lib_id = (String)session.getAttribute("library_id");

        formname = request.getParameter("name");

        sqlQuery = "select distinct library_id from document where library_id!='"+ lib_id +"'";

        rs = MyQueryResult.getMyExecuteQuery(sqlQuery);

        session.setAttribute("libRs", rs);

        if (formname.equals("simple"))
            return mapping.findForward("simple");
        if (formname.equals("browse"))
            return mapping.findForward("browse");
        if (formname.equals("additional"))
            return mapping.findForward("additional");
        if (formname.equals("advance"))
            return mapping.findForward("advance");
        if (formname.equals("isbn"))
            return mapping.findForward("isbn");
        if (formname.equals("callno"))
            return mapping.findForward("callno");
        if (formname.equals("accno"))
            return mapping.findForward("accno");
        if (formname.equals("journal"))
            return mapping.findForward("journal");
        if (formname.equals("newarrival"))
            return mapping.findForward("newarrival");
return mapping.findForward(SUCCESS);

    }
}
