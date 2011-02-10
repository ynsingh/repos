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
public class SuperAdminAction extends org.apache.struts.action.Action {
    String user_id1;
    String user_id2;
    String password1;
    String password2;
    ResultSet rst1,rst2;
    
  
    
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
        SuperAdminActionForm admin=(SuperAdminActionForm)form;

        user_id1=admin.getUser_id1();
        user_id2=admin.getUser_id2();
        password1=admin.getPassword1();
        password2=admin.getPassword2();

        rst1=MyQueryResult.getMyExecuteQuery("select * from login where user_id='"+user_id1+"' and password='"+password1+"' and staff_id='"+"admin.libms"+"'");
        if(rst1.next())
        {
            int no=MyQueryResult.getMyExecuteUpdate("update staff_detail set emai_id='"+user_id2+"' where staff_id='"+"admin.libms"+"'");
            if(no>0)
            {
              int no1=MyQueryResult.getMyExecuteUpdate("update login set user_id='"+user_id2+"', password='"+password2+"' where staff_id='"+"admin.libms"+"'");
              if(no1>0)
              {
                  request.setAttribute("msg","Record Successfully Updated");
                return    mapping.findForward("success");
              }
            }

        }
        else
        {request.setAttribute("msg","Invalid user Name or Password");
           return mapping.findForward("fail");
        }


  return mapping.findForward("fail");
 
    }
}
