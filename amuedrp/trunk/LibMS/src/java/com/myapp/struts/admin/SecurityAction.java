/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * Developed By Kedar Kumar
 * Use to Check the First Login Security Question Setup
 */
public class SecurityAction extends org.apache.struts.action.Action {


    
    String staff_id;
    String user_name;
    String question;
    String ans;

    String library_id;
    private boolean result;
LoginDAO logindao;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
     SecurityActionForm login=(SecurityActionForm)form;

logindao=new LoginDAO();
           staff_id=login.getStaff_id();
           user_name=login.getUser_id();
           question=login.getQuestion();
           ans=login.getAns();
           HttpSession session=request.getSession();
library_id=(String)session.getAttribute("library_id");


Login obj=logindao.searchStaffId(staff_id, library_id);
obj.setQuestion(question);
obj.setAns(ans);

result=logindao.update1(obj);
      if(result==true)
      {
          Login rs=logindao.searchStaffId(staff_id, library_id);

                            if(rs!=null)
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

