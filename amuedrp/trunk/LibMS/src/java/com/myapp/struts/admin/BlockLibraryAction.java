/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.admin.AdminViewActionForm;

import  com.myapp.struts.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dushyant
 */
public class BlockLibraryAction extends org.apache.struts.action.Action {
    
int i;
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
      i=Integer.parseInt(request.getParameter("id"));
      HttpSession session=request.getSession();
 
        ResultSet rst=MyQueryResult.getMyExecuteQuery("select * from admin_registration where registration_id="+i);
        if(rst.next())
        {
            session.setAttribute("blocked_resultset", rst);

            return mapping.findForward("success");
        }else
        {
           return mapping.findForward("error");
        }
        

        
    }
}
