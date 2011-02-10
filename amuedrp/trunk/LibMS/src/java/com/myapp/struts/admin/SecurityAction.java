/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.admin.SecurityActionForm;
import java.sql.*;
import  com.myapp.struts.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author System Administrator
 */
public class SecurityAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String staff_id;
    String user_name;
    String question;
    String ans;
    ResultSet rs;
    String library_id;

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
     SecurityActionForm login=(SecurityActionForm)form;
    

           staff_id=login.getStaff_id();
           user_name=login.getUser_id();
           question=login.getQuestion();
           ans=login.getAns();
           HttpSession session=request.getSession();
library_id=(String)session.getAttribute("library_id");
          // System.out.println(staff_id+question+ans);
        int x=MyQueryResult.getMyExecuteUpdate("update  login set question='"+question+"',ans='"+ans+"' where staff_id='"+staff_id+"' and library_id='"+ library_id + "'");
      if(x!=0)
      {
           rs=MyQueryResult.getMyExecuteQuery("select *  from login where staff_id='"+staff_id+"' and library_id='"+library_id+"'" );

                            if(rs.next())
                             {
                                request.setAttribute("account_resultset", rs);
                                return mapping.findForward("success");
                             }
                             else
                             {
                              return mapping.findForward("failue");
                             }
      }
        return mapping.findForward("failue");
    }
}

