/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.MemberDAO;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class Member2Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String emptype_id;
    String button;
    String library_id;
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
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
         HttpSession session=request.getSession();

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
        Member2ActionForm maf=(Member2ActionForm)form;
        emptype_id=maf.getEmptype_id();
        button=maf.getButton();
        
        library_id=(String)session.getAttribute("library_id");

        if(button.equals("Add"))
        {
         EmployeeType employeetype=MemberDAO.getEployeeName(library_id, emptype_id);
         if(employeetype!=null)
         {
            // request.setAttribute("msg1", "Member Id : "+emptype_id+" already exists");
             request.setAttribute("msg1", resource.getString("circulation.cir_newmember.memberid") +emptype_id+resource.getString("systemsetup.manage_notice.alreadyexists"));
            return mapping.findForward("duplicate");
         }
         else
         {

            
             request.setAttribute("new_emptype_id",emptype_id);
             return mapping.findForward("success");


         }
        }


EmployeeType employeetype;
        if(button.equals("Update")||button.equals("View")||button.equals("Delete"))
        {



                         employeetype=MemberDAO.getEployeeName(library_id, emptype_id);
                        if(employeetype==null)
                        {

                           // request.setAttribute("msg1", "Member Id: "+emptype_id+" doesn't exists");
                            request.setAttribute("msg1", resource.getString("circulation.cir_newmember.memberid")+emptype_id+resource.getString("systemsetup.manage_notice.doesnotexist"));

                        return mapping.findForward("duplicate");
                        }






         if(employeetype!=null)
         {



          request.setAttribute("button",button);
           request.setAttribute("employeetype", employeetype);
             return mapping.findForward("update/view/delete");
         }



    }
        
        return mapping.findForward(SUCCESS);
    }
}
