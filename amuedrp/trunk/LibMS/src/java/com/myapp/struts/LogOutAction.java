/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import  com.myapp.struts.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dushyant
 */
public class LogOutAction extends org.apache.struts.action.Action {
    String user_id;
    String username;
    String password;
    Connection con;
    ResultSet rst,rst1,rst2,rst3,rst4,rst5,rst6;
    PreparedStatement stmt;
    String staff_id;
    String library_id;
    String button;

    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    
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
            throws Exception
    {
HttpSession session=request.getSession();
session.invalidate();

           
      return mapping.findForward("success");
    }
}
