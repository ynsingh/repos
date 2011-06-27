/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
//import  com.myapp.struts.hbm.*;

import  com.myapp.struts.hbm.Privilege;
import  com.myapp.struts.hbm.PrivilegeId;
import  com.myapp.struts.hbm.Library;
import  com.myapp.struts.hbm.SerPrivilege;
import  com.myapp.struts.hbm.CirPrivilegeId;
import  com.myapp.struts.hbm.CirPrivilege;
import  com.myapp.struts.hbm.CatPrivilege;
import  com.myapp.struts.hbm.CatPrivilegeId;
import  com.myapp.struts.hbm.AcqPrivilege;
import  com.myapp.struts.hbm.AcqPrivilegeId;
import  com.myapp.struts.hbm.Login;
import  com.myapp.struts.hbm.LoginId;
import  com.myapp.struts.hbm.StaffDetail;
import  com.myapp.struts.hbm.StaffDetailId;
import  com.myapp.struts.hbm.SubLibrary;
import  com.myapp.struts.hbm.SubLibraryId;
import  com.myapp.struts.hbm.Library;
import  com.myapp.struts.hbm.AdminRegistration;
import  com.myapp.struts.hbm.SerPrivilegeId;
import  com.myapp.struts.AdminDAO.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class PrivilegeAction1 extends org.apache.struts.action.Action {
    String privilege_list;
    String staff_id,library_id,staff_name;
    String sql1,sql2,sql3,sql4,sql5;
    String button;
    boolean result;
   
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
           HttpSession session=request.getSession();
         library_id=(String)session.getAttribute("library_id");
        PrivilegeActionForm privilege=(PrivilegeActionForm)form;
        privilege_list=privilege.getPrivilege_list();
        staff_id=privilege.getStaff_id();
        staff_name=privilege.getStaff_name();
        button=privilege.getButton();
        System.out.println(button+".....................................");
if(button.equals("Restore Previous Privilege"))
{
       Privilege backupprivobj=(Privilege)session.getAttribute("privilege_priv_backup");
       AcqPrivilege backupacqprivobj=(AcqPrivilege)session.getAttribute("privilege_acq_backup");
       CatPrivilege backupcatprivobj=(CatPrivilege)session.getAttribute("privilege_cat_backup");
        CirPrivilege backupcirprivobj=(CirPrivilege)session.getAttribute("privilege_cir_backup");
        SerPrivilege backupserprivobj=(SerPrivilege)session.getAttribute("privilege_ser_backup");
        Login backuploginprivobj = (Login)session.getAttribute("login_privilege");


        result = LoginDAO.updatePriv(backuploginprivobj,backupprivobj,backupacqprivobj,backupcatprivobj,backupcirprivobj,backupserprivobj);
   if(result==true)
                            {
                            request.setAttribute("res","Privileage Successfully Restored");
                            request.setAttribute("staff_id", staff_id);
                            request.setAttribute("staff_name", staff_name);
                            request.setAttribute("privilege_list", privilege_list);
                            System.out.println("staff name="+staff_name);
                            return mapping.findForward("success");
                            }
                            else{
                               
                               request.setAttribute("res","Privileage Successfully not removed");
                            request.setAttribute("staff_id", staff_id);
                            request.setAttribute("staff_name", staff_name);
                            request.setAttribute("privilege_list", privilege_list);
                            System.out.println("staff name="+staff_name);
                            return mapping.findForward("success");


                            }


}
                   
                
            

return null;
    
    }
}
