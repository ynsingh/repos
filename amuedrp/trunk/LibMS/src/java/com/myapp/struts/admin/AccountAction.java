/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class AccountAction extends org.apache.struts.action.Action {
    /* forward name="success" path="" */
    private String staff_id;
    private String button;
    private String library_id;
    private String loginstaff_id;
    Connection con;
    PreparedStatement stmt;
    String sql;
    ResultSet rst,rst1;
    String main_sublib;

    private String sublibrary_id;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
      
   AccountActionForm acqRegisterActionForm =(AccountActionForm)form;
        staff_id=acqRegisterActionForm.getStaff_id();
        button=acqRegisterActionForm.getButton();


       
        
         HttpSession session=request.getSession();
         loginstaff_id=(String)session.getAttribute("staff_id");
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");

main_sublib=(String)session.getAttribute("mainsublibrary");

System.out.print(staff_id+"........"+library_id+"////"+sublibrary_id);



Login loginobj;

        if(button.equals("Create Account"))
        {
            StaffDetail staff=StaffDetailDAO.searchStaffId(staff_id, library_id);
            if(staff!=null)
            {
             
                         loginobj=(Login)LoginDAO.searchStaffLogin(staff_id, library_id);



             

             if(loginobj==null)
                {

           
                           //create account code
                            StaffDetail staffobj=(StaffDetail)StaffDetailDAO.searchStaffId(staff_id, library_id);


                            if(staffobj!=null)
                             {
                                List<SubLibrary>  sublib;
                                
                                    sublib=SubLibraryDAO.searchSubLib(library_id);
                                
                                if(!sublib.isEmpty())
                                {
                                session.setAttribute("sublib",sublib);
                                session.setAttribute("button", button);

                                session.setAttribute("account_resultset", staffobj);
                                System.out.println("I am here");

                                return mapping.findForward("register");
                                }
                                else{
                                 request.setAttribute("msg1", "Some Error Encounterd");
                              return mapping.findForward("not_found");
                                }


                            }
           
                    }else
                    {

                              request.setAttribute("msg1", "Account Already Registered for Staff_id :"+staff_id);
                              return mapping.findForward("not_found");
                        }
                       
            }else{
             request.setAttribute("msg1", "Staff_id :"+staff_id+" Not Registered");
                              return mapping.findForward("not_found");


            }

           




         


       

         




        }
           
            
        
        

         if(button.equals("Update Account")||button.equals("View Account")||button.equals("Delete Account"))
         {

              if(sublibrary_id.equalsIgnoreCase(main_sublib))
             {


                        loginobj=(Login)LoginDAO.searchStaffLogin(staff_id, library_id);

             }
             else
             {
                         loginobj=(Login)LoginDAO.searchStaffLogin(staff_id, library_id,sublibrary_id);



             }



          

         if(loginobj!=null)
         {
            if(loginobj.getId().getStaffId().equalsIgnoreCase(loginstaff_id))
             {
              request.setAttribute("msg1", "Cannot Modify Your Own Account Staff_id :"+staff_id);
                              return mapping.findForward("not_found");

          }
             if(loginobj.getId().getStaffId().equalsIgnoreCase("admin."+library_id))
             {
              request.setAttribute("msg1", "Cannot Modify Account of Institute Admin Staff_id :"+staff_id);
                              return mapping.findForward("not_found");

             }
          




              
                           List<SubLibrary>  sublib;
                                
                                    sublib=SubLibraryDAO.searchSubLib(library_id);
                                
                                if(!sublib.isEmpty())
                                {
                                session.setAttribute("sublib",sublib);
                                session.setAttribute("button", button);

                                session.setAttribute("update_account", loginobj);

                                return mapping.findForward("view_account/delete_account/change_password");
                                }
                                else{
                                     request.setAttribute("msg1", "Some Error Encounterd");
                                     return mapping.findForward("not_found");
                               
                                }
         }
            else
               {
                             request.setAttribute("msg1", "Account not registered for Staff_id :"+staff_id);
                              return mapping.findForward("not_found");
               }
           
         }

        return null;
    }
}










