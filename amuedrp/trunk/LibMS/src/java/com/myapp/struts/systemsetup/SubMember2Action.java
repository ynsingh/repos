/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.*;
import java.util.List;
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
public class SubMember2Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String sub_emptype_id;
    String button;
    String library_id;
    String emptype_full_name;
    private String emptype_id;
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
   
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

        SubMember2ActionForm smaf=(SubMember2ActionForm)form;
        sub_emptype_id=smaf.getSub_emptype_id();
        button=smaf.getButton();
       
        emptype_id=smaf.getEmptype_id();
        
        library_id=(String)session.getAttribute("library_id");

        EmployeeType emp=MemberDAO.getEployeeByName(library_id,emptype_id);
        if(emp!=null)
            emptype_full_name=emp.getEmptypeFullName();

        System.out.println(emptype_id+"   "+sub_emptype_id+"  "+button);
        
        if(button.equals("Add"))
        {
         SubEmployeeType subemployeetype=SubMemberDAO.getSubEployeeName(library_id,emptype_id, sub_emptype_id);
         if(subemployeetype!=null)
         {
             //request.setAttribute("msg1", "SubMember Id : "+sub_emptype_id+" already exists");
             request.setAttribute("msg1", resource.getString("systemsetup.submember2action.submemid")+sub_emptype_id+resource.getString("systemsetup.manage_notice.alreadyexists"));
           

            return mapping.findForward("duplicate");
         }
         else
         {
             request.setAttribute("emptype_full_name",emptype_full_name);
             request.setAttribute("emptype_id",emptype_id);
             request.setAttribute("new_sub_emptype_id",sub_emptype_id);
             return mapping.findForward("success");


         }
        }


SubEmployeeType subemployeetype;
        if(button.equals("Update")||button.equals("View")||button.equals("Delete"))
        {



                         subemployeetype=SubMemberDAO.getSubEployeeName(library_id,emptype_id, sub_emptype_id);
                        if(subemployeetype==null)
                        {

                        request.setAttribute("msg1", resource.getString("systemsetup.submember2action.submemid")+sub_emptype_id+resource.getString("systemsetup.manage_notice.doesnotexist"));
                         System.out.println(emptype_id+"In Action Register");
                        return mapping.findForward("duplicate");
                        }






         if(subemployeetype!=null)
         {

          emptype_id=(String)subemployeetype.getId().getEmptypeId();
          EmployeeType emptype=MemberDAO.getEployeeName(library_id, emptype_id);
           request.setAttribute("emptype", emptype);
            request.setAttribute("emptype_id",emptype_id);
           request.setAttribute("button",button);
           request.setAttribute("subemployeetype", subemployeetype);
             return mapping.findForward("update/view/delete");
         }



    }

        
        return null;
    }
}
