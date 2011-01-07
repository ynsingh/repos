/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Faraz
 */
public class CallNoSearchAction extends org.apache.struts.action.Action {
    
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
        ResultSet rs = null;
        CallNoSearchActionForm myform = (CallNoSearchActionForm)form;
        String lib_id=myform.getCMBLib();
        String callno = myform.getTXTKEY();
        if (session.getAttribute("Result")!=null) session.removeAttribute("Result");
        String query = "select * from document where callNo='"+ callno +"'";
        if(!lib_id.equals("all"))
             query +=" and library_id='" + lib_id + "'";
        rs = MyQueryResult.getMyExecuteQuery(query);
       session.setAttribute("Result", rs);//resultset
        return mapping.findForward(SUCCESS);
    }
}
