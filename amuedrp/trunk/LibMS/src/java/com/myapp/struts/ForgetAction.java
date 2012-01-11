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
import java.util.*;
import java.util.ResourceBundle;
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
 Locale locale=null;
 LoginDAO logindao=new LoginDAO();
   String locale1="en";
   String rtl="ltr";
   String align="left";
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SecurityActionForm login=(SecurityActionForm)form;

            HttpSession session=request.getSession(true);
            try{

        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
            library_id=(String)session.getAttribute("library_id");

           staff_id=login.getStaff_id();
           user_id=login.getUser_id();
           question=login.getQuestion();
           ans=login.getAns();

System.out.println(staff_id+""+ans+library_id);
Login logobj=logindao.searchAns(staff_id,library_id,ans);
        

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
          
          
            //request.setAttribute("error","Answer not correct");
            request.setAttribute("error", resource.getString("admin.forgetpassword.error"));

            return mapping.findForward("failure");
      }
          
    }
}
