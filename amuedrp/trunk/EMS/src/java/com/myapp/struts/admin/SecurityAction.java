/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.LoginActionForm;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.LoginDAO;
import java.util.List;
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
    List rs;
    String institute_id;
    String role;

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
            institute_id=(String)session.getAttribute("institute_id");
            role=(String)session.getAttribute("role");
          // System.out.println(staff_id+question+ans);
LoginDAO logindao = new LoginDAO();
Login loginDetails = new Login();
String userid = (String)session.getAttribute("user_id");
System.out.println(userid);
List loginList = logindao.getUser(userid);
if(!loginList.isEmpty()){
loginDetails = (Login)loginList.get(0);

        //loginDetails.setUserId(staff_id);
        //loginDetails.setUserName(user_name);
        loginDetails.setQuestion(question);
        loginDetails.setAns(ans);


logindao.update(loginDetails);

   
      if(loginDetails.getRole().equalsIgnoreCase("insti-admin"))
        
                                return mapping.findForward("success");

if(loginDetails.getRole().equalsIgnoreCase("Election Manager"))
{               //request.setAttribute("msg","Requested Page is being developed");
                                return mapping.findForward("electionmanager");}
                             
      
        
    }

return mapping.findForward("failure");
    }
}

