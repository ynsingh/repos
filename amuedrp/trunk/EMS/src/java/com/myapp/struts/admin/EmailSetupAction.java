/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.utility.PasswordEncruptionUtility;
import com.myapp.struts.hbm.*;
import org.hibernate.HibernateException;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Edrp-04
 */
public class EmailSetupAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String emailid;
    private String password;
    private String institute_id;
   // private String pass;
    
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
        EmailSetupActionForm emailsetupform=(EmailSetupActionForm)form;

          HttpSession session=request.getSession();
        emailid=emailsetupform.getEmailid();
        password=emailsetupform.getPassword();
        institute_id=(String)session.getAttribute(institute_id);
        if(session.getAttribute("institute_id")!=null)
    {
        institute_id = (String)session.getAttribute("institute_id");
       // System.out.println("locale="+locale1);
    }
        System.out.println(institute_id);
//pass=PasswordEncruptionUtility.password_encrupt(password);

         EmailSetupDAO emailsetupdao=new EmailSetupDAO();
         EmailSetup emailsetup= new EmailSetup();

         emailsetup.setEmailId(emailid);
         emailsetup.setInstituteId(institute_id);
         emailsetup.setPassword(password);
         emailsetupdao.insert(emailsetup);

System.out.println("hiiiiii");

        return mapping.findForward(SUCCESS);
    }
}
