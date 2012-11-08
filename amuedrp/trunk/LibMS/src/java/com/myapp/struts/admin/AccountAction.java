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
import java.util.ResourceBundle;
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
    LoginDAO logindao;
    PreparedStatement stmt;
    String sql;
    ResultSet rst,rst1;
    String main_sublib;
 Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
    private String sublibrary_id;
    StaffDetailDAO staffdao=new StaffDetailDAO();
    SubLibraryDAO sublibdao=new SubLibraryDAO();
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logindao=new LoginDAO();
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
 
   AccountActionForm acqRegisterActionForm =(AccountActionForm)form;
        staff_id=acqRegisterActionForm.getStaff_id();
        button=acqRegisterActionForm.getButton();


       
        
        
         loginstaff_id=(String)session.getAttribute("staff_id");
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");

main_sublib=(String)session.getAttribute("mainsublibrary");

System.out.print(staff_id+"........"+library_id+"////"+sublibrary_id);



Login loginobj;

        if(button.equals("Create Account"))
        {
            StaffDetail staff=staffdao.searchStaffId(staff_id, library_id);
            if(staff!=null)
            {
             
                         loginobj=(Login)logindao.searchStaffLogin(staff_id, library_id);



             

             if(loginobj==null)
                {

           
                           //create account code
                          //  StaffDetail staffobj=(StaffDetail)StaffDetailDAO.searchStaffId(staff_id, library_id);


                          //  if(staffobj!=null)
                           //  {
                                List<SubLibrary>  sublib;
                                
                                    sublib=sublibdao.searchSubLib(library_id);
                                
                                if(!sublib.isEmpty())
                                {
                                session.setAttribute("sublib",sublib);
                                session.setAttribute("button", button);

                                session.setAttribute("account_resultset", staff);
                                System.out.println("I am here");

                                return mapping.findForward("register");
                                }
                                else{
                                // request.setAttribute("msg1", "Some Error Encounterd");
                            request.setAttribute("msg1",resource.getString("admin.error.error"));
                              return mapping.findForward("not_found");
                                }


                           // }
           
                    }else
                    {

                             // request.setAttribute("msg1", "Account Already Registered for Staff_id :"+staff_id);
                              request.setAttribute("msg1", resource.getString("admin.AccountAction.error1")+staff_id);
                              return mapping.findForward("not_found");
                        }
                       
            }else{
             //request.setAttribute("msg1", "Staff_id :"+staff_id+" Not Registered");
                request.setAttribute("msg1",resource.getString("admin.acq_register.staffId") +" :"+staff_id+ resource.getString("admin.AccountAction.error2"));
                              return mapping.findForward("not_found");


            }

           




         


       

         




        }
           
            
        
        

         if(button.equals("Update Account")||button.equals("View Account")||button.equals("Delete Account"))
         {

              if(sublibrary_id.equalsIgnoreCase(main_sublib))
             {


                        loginobj=(Login)logindao.searchStaffLogin(staff_id, library_id);

             }
             else
             {
                         loginobj=(Login)logindao.searchStaffLogin(staff_id, library_id,sublibrary_id);



             }



          

         if(loginobj!=null)
         {
            if(loginobj.getId().getStaffId().equalsIgnoreCase(loginstaff_id))
             {
             // request.setAttribute("msg1", "Cannot Modify Your Own Account Staff_id :"+staff_id);
                 request.setAttribute("msg1", resource.getString("admin.AccountAction.error3")+staff_id);
                              return mapping.findForward("not_found");

          }
             if(loginobj.getId().getStaffId().equalsIgnoreCase("admin."+library_id))
             {
              //request.setAttribute("msg1", "Cannot Modify Account of Institute Admin Staff_id :"+staff_id);
                 request.setAttribute("msg1", resource.getString("admin.AccountAction.error4")+staff_id);
                              return mapping.findForward("not_found");

             }
          




              
                           List<SubLibrary>  sublib;
                                
                                    sublib=sublibdao.searchSubLib(library_id);
                                
                                if(!sublib.isEmpty())
                                {
                                session.setAttribute("sublib",sublib);
                                session.setAttribute("button", button);

                                session.setAttribute("update_account", loginobj);

                                return mapping.findForward("view_account/delete_account/change_password");
                                }
                                else{
                                     //request.setAttribute("msg1", "Some Error Encounterd");
                                        request.setAttribute("msg1",resource.getString("admin.error.error"));
                                     return mapping.findForward("not_found");
                               
                                }
         }
            else
               {
                             //request.setAttribute("msg1", "Account not registered for Staff_id :"+staff_id);
                            request.setAttribute("msg1", resource.getString("admin.AccountAction.error5")+staff_id);
                              return mapping.findForward("not_found");
               }
           
         }

        return null;
    }
}










