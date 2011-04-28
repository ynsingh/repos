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
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class PrivilegeAction extends org.apache.struts.action.Action {
    String privilege_list;
    String staff_sublibrary_id;
    String staff_id,library_id,button;
    String sql1,sql2,sql3,sql4,sql5;
    //ResultSet privilege_backup_resultset[];
    String staff_name;
    boolean result;
    //ResultSet privilege_resultset,acq_privilege_resultset,cat_privilege_resultset,cir_privilege_resultset,ser_privilege_resultset;
 
   


    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        PrivilegeActionForm privilege=(PrivilegeActionForm)form;


        library_id=(String)session.getAttribute("library_id");

        staff_id=privilege.getStaff_id();
        staff_name=privilege.getStaff_name();
        button=privilege.getButton();


        System.out.println("....button="+button);

    StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id, library_id);
        if(staffobj!=null)
        staff_sublibrary_id=staffobj.getSublibraryId();


        if(button.equals("Assign Privilege")||button.equals("Change Privilege"))
        {
        privilege_list=privilege.getPrivilege_list();


        System.out.println("privilege="+privilege_list);
        
        
        if (privilege_list.equals("")==false)
        {


        //CreatePrivilege priv=new CreatePrivilege();

        //privilege_backup_resultset=priv.backupPrivilege(staff_id,library_id);

        /*BackUp the Privilege of Mention User*/
    
       Privilege backupprivobj=PrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
       AcqPrivilege backupacqprivobj=AcqPrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
       CatPrivilege backupcatprivobj=CatPrivilegeDAO.getPrivilege(library_id,staff_sublibrary_id, staff_id);
        CirPrivilege backupcirprivobj=CirPrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
        SerPrivilege backupserprivobj=SerPrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
        Login login=LoginDAO.searchStaffLogin(staff_id, library_id, staff_sublibrary_id);


        session.setAttribute("privilege_priv_backup",backupprivobj);
        session.setAttribute("privilege_acq_backup",backupacqprivobj);
        session.setAttribute("privilege_cat_backup",backupcatprivobj);
        session.setAttribute("privilege_cir_backup",backupcirprivobj);
        session.setAttribute("privilege_ser_backup",backupserprivobj);
        session.setAttribute("login_privilege",login);





      result=CreatePrivilege.AssignPrivilege(privilege_list,staff_id, library_id,staff_sublibrary_id);


     // privilege_backup_resultset[0].next();
       // System.out.println("****************************"+privilege_backup_resultset[0].getString(4)+staff_id+library_id);
     //   privilege_backup_resultset[0].beforeFirst();
        request.setAttribute("staff_id", staff_id);
        request.setAttribute("staff_name", staff_name);
        request.setAttribute("privilege_list", privilege_list);


   
        Privilege privobj=PrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
       AcqPrivilege acqprivobj=AcqPrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
       CatPrivilege catprivobj=CatPrivilegeDAO.getPrivilege(library_id,staff_sublibrary_id, staff_id);
        CirPrivilege cirprivobj=CirPrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
        SerPrivilege serprivobj=SerPrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);





      
        request.setAttribute("privilege_resultset", privobj);

        request.setAttribute("acq_privilege_resultset", acqprivobj);
        request.setAttribute("cat_privilege_resultset", catprivobj);
        request.setAttribute("cir_privilege_resultset", cirprivobj);
        request.setAttribute("ser_privilege_resultset", serprivobj);

         //check the privilege of admin role user and degrade it to staff role if administrator privielege
         //not selected
        Privilege tempprivobj=PrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);

         if(tempprivobj!=null)
         {
             System.out.println(tempprivobj.getAdministrator()+"...................");
         if(tempprivobj.getAdministrator().equalsIgnoreCase("true"))
         {
             System.out.println("Admin");
           Login logobj=LoginDAO.searchStaffLogin(staff_id, library_id, staff_sublibrary_id);


           if(logobj.getRole().equalsIgnoreCase("dept-admin"))
             logobj.setRole("dept-staff");
           
           else if(logobj.getRole().equalsIgnoreCase("admin"))
               logobj.setRole("staff");
         

             result=LoginDAO.update1(logobj);


             if(result==false)
             {
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("staff_name", staff_name);
                request.setAttribute("msg", "Exception occured");
                System.out.println(tempprivobj.getAdministrator());
                return mapping.findForward("failure");
             }
            

         }

         }
 Login logobj1=LoginDAO.searchStaffLogin(staff_id, library_id, staff_sublibrary_id);

         request.setAttribute("staff_role", logobj1.getRole());

        return mapping.findForward("assign_privilege");

        }
       
        }

        else      //if(button.equals("View Privilge"))
        {
        System.out.println(staff_sublibrary_id);
          Login staff=LoginDAO.searchStaffLogin(staff_id, library_id, staff_sublibrary_id);

        String staff_role=staff.getRole();

               Privilege privobj=PrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
       AcqPrivilege acqprivobj=AcqPrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
       CatPrivilege catprivobj=CatPrivilegeDAO.getPrivilege(library_id,staff_sublibrary_id, staff_id);
        CirPrivilege cirprivobj=CirPrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);
        SerPrivilege serprivobj=SerPrivilegeDAO.getPrivilege(library_id, staff_sublibrary_id, staff_id);






        request.setAttribute("privilege_resultset", privobj);

        request.setAttribute("acq_privilege_resultset", acqprivobj);
        request.setAttribute("cat_privilege_resultset", catprivobj);
        request.setAttribute("cir_privilege_resultset", cirprivobj);
        request.setAttribute("ser_privilege_resultset", serprivobj);

          request.setAttribute("staff_id", staff_id);
          request.setAttribute("staff_role", staff_role);
        request.setAttribute("staff_name", request.getAttribute("staff_name"));
            return mapping.findForward("view_privilege");
        }
 return null;
    }
}
