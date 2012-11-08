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
public class StaffAccountAction extends org.apache.struts.action.Action {
    /* forward name="success" path="" */
    private String staff_id;
    private String button;
    private String library_id;
    
    

    LoginDAO logindao;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
      logindao=new LoginDAO();
   
        staff_id=request.getParameter("staff_id");
        
       StaffDetailDAO staffdao=new StaffDetailDAO();
       SubLibraryDAO sublibdao=new SubLibraryDAO();
        
         HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        








      

             Login loginobj=(Login)logindao.searchStaffLogin(staff_id, library_id);


         if(loginobj!=null)
         {
            request.setAttribute("msg1", "Staff Id : "+staff_id+" already exists");
            return mapping.findForward("not_found");
         }
         else
         {

            //create account code
                 StaffDetail staffobj=(StaffDetail)staffdao.searchStaffId(staff_id, library_id);
                        

                            if(staffobj!=null)
                             {
                                 List<SubLibrary>  sublib=sublibdao.searchSubLib(library_id);
                                if(!sublib.isEmpty())
                                {
                                session.setAttribute("sublib",sublib);
                                session.setAttribute("button", button);

                                session.setAttribute("account_resultset", staffobj);

                                return mapping.findForward("register");
                                }
                                else{
                                 request.setAttribute("msg1", "Some Error Encounterd");
                              return mapping.findForward("error");
                                }

                                
                            }
                            else{
                             request.setAttribute("msg1", "Some Error Encounterd");
                              return mapping.findForward("error");

                            }
           }





      
     
    }
}










