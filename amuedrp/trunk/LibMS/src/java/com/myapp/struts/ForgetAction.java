/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;
import com.myapp.struts.admin.SecurityActionForm;
import java.sql.*;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
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
    String library_id;
     String staff_id;
    String user_id;
    String question;
    String ans;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SecurityActionForm login=(SecurityActionForm)form;

            HttpSession session=request.getSession(true);
            library_id=(String)session.getAttribute("library_id");

           staff_id=login.getStaff_id();
           user_id=login.getUser_id();
           question=login.getQuestion();
           ans=login.getAns();

System.out.println(staff_id+""+ans+library_id);
Login logobj=LoginDAO.searchAns(staff_id,library_id,ans);
        

      if(logobj!=null)
      {

            
            request.setAttribute("question",question);
            request.setAttribute("staff_id",staff_id);
            request.setAttribute("ans",ans);
           
            request.setAttribute("error","");
                return mapping.findForward("success");
      }
      else
      {
          
          
            request.setAttribute("error","Answer not correct");
            return mapping.findForward("failure");
      }
          
    }
}
