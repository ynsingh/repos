/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import com.myapp.struts.AdminDAO.LoginDAO;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.utility.Email;
import com.myapp.struts.utility.PasswordEncruptionUtility;
import com.myapp.struts.utility.RandomPassword;
import com.myapp.struts.utility.UserLog;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author erp
 */
public class LoginForgetPassword extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private final ExecutorService executor=Executors.newFixedThreadPool(1);
    Email obj;
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        Login rst;


        LoginActionForm loginActionForm;
        loginActionForm = (LoginActionForm) form;
        String user_id = loginActionForm.getUsername();
        System.out.println("user id isss "+user_id);
    //HttpSession session = request.getSession();
      LoginDAO logindao = new LoginDAO();
      String admin_password1;
     String admin_password= RandomPassword.getRandomString(10);
                rst = logindao.getUser1(user_id);
                if(rst!=null)
                {
                         admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
                       rst.setPassword(admin_password1);

                        logindao.update(rst);
                         String mailbody=UserLog.readProperty("mail.properties", user_id+"em");
                         
                            mailbody="\n\n Your New Password is "+admin_password+"\n\nWith Regards\n\nElection Manager\n";
                            obj=new Email(user_id,"","Password change Successfully from EMS",mailbody);
                            executor.submit(new Runnable()
                            {
                                public void run()
                                {
                                    obj.send();
                                  }
                             });
                           request.setAttribute("msg", "New Password has been sent to your mail");
                           return mapping.findForward(SUCCESS);

                }

//      rst = dao.getUser1(user_id);
//      String staff_id;
//      String user_name;
//      String question;
//        System.out.println("i am out of iffff");
//        if(rst!=null){
//                  user_name=rst.getUserName();
//                  question=rst.getQuestion();
//                  session.setAttribute("user_id", user_id);
//                  session.setAttribute("username", user_name);
//                  session.setAttribute("question", question);
//
//                  System.out.println("i am in ifffff");
//                  return mapping.findForward(SUCCESS);
//        }
      return mapping.findForward(null);
    }
}
