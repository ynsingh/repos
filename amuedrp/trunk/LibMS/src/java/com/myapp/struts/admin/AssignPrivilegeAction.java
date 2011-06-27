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
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
/**
 *
 * @author System Administrator
 */
public class AssignPrivilegeAction extends org.apache.struts.action.Action {

     /* forward name="success" path="" */
    private String staff_id;
    private String button;
    private String library_id;
    private String staff_name;
    private String staff_sublibrary_id;
private String sql1,sql2,sql3,sql4,sql5;
//private ResultSet privilege_resultset,acq_privilege_resultset,cat_privilege_resultset,cir_privilege_resultset,ser_privilege_resultset;

    String sql;
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqRegisterActionForm acqRegisterActionForm =(AcqRegisterActionForm)form;
        staff_id=acqRegisterActionForm.getStaff_id();

        button=acqRegisterActionForm.getButton();
        request.setAttribute("button", button);
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");





        if(button.equals("Assign Privilege")||button.equals("Change Privilege"))
        {
         Login loginobj=LoginDAO.searchStaffId(staff_id, library_id);
        if(loginobj==null)
         {
            request.setAttribute("msg1", "Staff Id: "+staff_id+" Account not exists");
               
            return mapping.findForward("notfound");
         }
         else
         {
         staff_name=loginobj.getUserName();

         String id="admin."+library_id;   //get Institute admin ID
            if(staff_id.equals(id))
           {

                request.setAttribute("staff_name", staff_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("msg1", "You Cannot Modify Privilege of Institute Admin");
             return mapping.findForward("notfound");
           }

        id=(String)session.getAttribute("staff_id");  //cannot Modify own account
            if(staff_id.equals(id))
            {


               request.setAttribute("staff_name", staff_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("msg1", "You Cannot Modify Privilege of Own Account");
                  return mapping.findForward("notfound");

            }




               String login_role=(String)session.getAttribute("login_role");    //cannot Modify Same Level Person Privilege
        
             if(loginobj!=null)
            {
                if(loginobj.getRole().equalsIgnoreCase(login_role))
                {

                request.setAttribute("staff_name", staff_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("msg1", "You Cannot Update Priviliege of Admin");
                return mapping.findForward("notfound");
                }
            }





          
            request.setAttribute("button", button);
            request.setAttribute("new_staff_name", staff_name);
            request.setAttribute("new_staff_id", staff_id);
            staff_sublibrary_id=loginobj.getSublibraryId();
            request.setAttribute("staff_sub_library",staff_sublibrary_id);

        Privilege privobj=PrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
       AcqPrivilege acqprivobj=AcqPrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
       CatPrivilege catprivobj=CatPrivilegeDAO.getPrivilege(library_id,staff_sublibrary_id, staff_id);
        CirPrivilege cirprivobj=CirPrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
        SerPrivilege serprivobj=SerPrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
        Login loginprivobj=LoginDAO.searchStaffLogin(staff_id, library_id, staff_sublibrary_id);

        session.setAttribute("privilege", privobj);
        session.setAttribute("acq_privilege", acqprivobj);
        session.setAttribute("cat_privilege", catprivobj);
        session.setAttribute("cir_privilege", cirprivobj);
        session.setAttribute("ser_privilege", serprivobj);
        session.setAttribute("login_privilege", loginprivobj);
             return mapping.findForward("assign");
         }
        }

        if(button.equals("View Privilege"))
        {


          Login loginobj=LoginDAO.searchStaffId(staff_id, library_id);
        if(loginobj!=null){
                    staff_name=loginobj.getUserName();
                    request.setAttribute("staff_name", staff_name);
                    return mapping.findForward("view");

        }
        else{
         request.setAttribute("msg1", "Record not found corresponding to staff Id:"+staff_id);
                request.setAttribute("staff_name", staff_name);
            return mapping.findForward("notfound");

        }

   
        }

        return null;
    }
}
