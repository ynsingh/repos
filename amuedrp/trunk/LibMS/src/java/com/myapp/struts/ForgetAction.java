/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;
import com.myapp.struts.admin.SecurityActionForm;
import com.myapp.struts.admin.SecurityActionForm;
import java.sql.*;
import  com.myapp.struts.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;

/**
 *
 * @author System Administrator
 */
public class ForgetAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     String staff_id;
    String user_id;
    String question;
    String ans;
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
        SecurityActionForm login=(SecurityActionForm)form;

            HttpSession session=request.getSession(true);
           staff_id=login.getStaff_id();
           user_id=login.getUser_id();
           question=login.getQuestion();
           ans=login.getAns();

System.out.println(staff_id+""+ans);

        ResultSet rst=MyQueryResult.getMyExecuteQuery("select *  from login where staff_id='"+staff_id+"' and ans='"+ans+"'");

      if(rst.next())
      {

            //String user_id=(String)session.getAttribute("user_id");
            //  String username=(String)session.getAttribute("username");
            request.setAttribute("question",question);
            request.setAttribute("staff_id",staff_id);
            request.setAttribute("ans",ans);
            request.setAttribute("password",rst.getString("password"));
            request.setAttribute("error","");
                return mapping.findForward("success");
      }
      else
      {
            request.setAttribute("question",question);
            request.setAttribute("staff_id",staff_id);
            request.setAttribute("ans",ans);
           // request.setAttribute("password","");
            request.setAttribute("error","Answer not correct");
            return mapping.findForward("failure");
      }
          
    }
}
