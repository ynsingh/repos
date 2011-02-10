/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;

/**
 *
 * @author Faraz
 */
public class MyResultSetAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private ResultSet rst=null;
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
        HttpSession session = request.getSession();
        String id=request.getParameter("id");
        String page=(String)session.getAttribute("page_name");
        session.removeAttribute("page_name");
        System.out.println(id);
    //String query="select * from document where accessionno='"+id+"'";
    System.out.println("URI=................"+request.getRequestURI());
    rst = MyQueryResult.getMyExecuteQuery(id);
    request.setAttribute("MyResultSet", rst);
    System.out.println("page="+page);
    if (page!=null)
        if(page.equals("newarrivals")) return mapping.findForward("newarrival");
    return mapping.findForward("success");
    }

    }

