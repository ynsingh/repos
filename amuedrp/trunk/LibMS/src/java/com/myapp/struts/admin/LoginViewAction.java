/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.MyConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
/**
 *
 * @author System Administrator
 */
public class LoginViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String staff_id;
    private String library_id;
    Connection con;
    ResultSet rst;
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
        
        staff_id=request.getParameter("id");
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
      //  System.out.println("Registration Id="+registration_id);
        try{
        con=MyConnection.getMyConnection();
        PreparedStatement stmt=con.prepareStatement("select * from login where  staff_id='"+staff_id+"' and library_id='"+library_id+"'");
        rst=stmt.executeQuery();
        request.setAttribute("simple_resultset", rst);
        return mapping.findForward("success");
        }
        catch(Exception e)
        {
        e.getMessage();
        }
           return mapping.findForward("success");
    }
    }

