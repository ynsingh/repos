/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.admin.AccountActionForm;
import com.myapp.struts.opac.MyQueryResult;
import java.sql.*;
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
    Connection con;
    PreparedStatement stmt;
    String sql;
    ResultSet rst,rst1;
    /**
    /* forward name="success" path="" */
   
    
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
        
      
   AccountActionForm acqRegisterActionForm =(AccountActionForm)form;
        staff_id=acqRegisterActionForm.getStaff_id();
        button=acqRegisterActionForm.getButton();
        //library_id=acqRegisterActionForm.getLibrary_id();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        
        if(button.equals("Create Account"))
        {
            System.out.println("create account....................."+library_id);
            //staff_id search
            rst1=MyQueryResult.getMyExecuteQuery("select emai_id from staff_detail where staff_id='"+staff_id+"' and library_id='"+library_id+"'" );
            //if found
            if(rst1.next())
            {

               //account search
                     rst1=MyQueryResult.getMyExecuteQuery("select user_id from login where user_id=(select emai_id from staff_detail where staff_id='"+staff_id+"' and library_id='"+library_id+"')" );
                     if(rst1.next())
                    {
                     request.setAttribute("msg1", "Staff ID: "+staff_id+" Account Already Exists");
                    return mapping.findForward("not_found");
                     }
                    else
                    {
                    //create account code
                        rst1=MyQueryResult.getMyExecuteQuery("select staff_id,first_name,last_name,emai_id from staff_detail where staff_id='"+staff_id+"' and library_id='"+library_id+"'" );

                            if(rst1.next())
                             {

                                request.setAttribute("button", button);

                                request.setAttribute("account_resultset", rst1);

                                return mapping.findForward("register");
                            }
                    }
            }
            else
            {
                request.setAttribute("msg1", "Staff_ID : "+staff_id+" not exists");
                return mapping.findForward("not_found");
           
             }
            
        }
        

         if(button.equals("Change Password")||button.equals("View Account")||button.equals("Delete Account"))
         {
             

            //staff_id search
            rst1=MyQueryResult.getMyExecuteQuery("select emai_id from staff_detail where staff_id='"+staff_id+"' and library_id='"+library_id+"'" );
            //if found
            if(rst1.next())
            {

               //account search
                     rst1=MyQueryResult.getMyExecuteQuery("select user_id from login where user_id=(select emai_id from staff_detail where staff_id='"+staff_id+"' and library_id='"+library_id+"')" );
                     if(rst1.next())
                    {
                         //update/view/delete account code
                        rst=MyQueryResult.getMyExecuteQuery("select b.staff_id,a.user_id,a.user_name,a.password from login a inner join staff_detail b on a.user_id=b.emai_id and a.library_id=b.library_id where b.staff_id='"+staff_id+"'");


                            if(rst.next())
                             {

                                request.setAttribute("button", button);

                                request.setAttribute("account_resultset1", rst);

                                return mapping.findForward("view_account/delete_account/change_password");
                            }
                     }
                    else
                    {
                     request.setAttribute("msg1", "Account not registered for Staff_id :"+staff_id);
                return mapping.findForward("not_found");
                    }
            }
            else
            {
                request.setAttribute("msg1", "Staff_id not registered :"+staff_id);
                return mapping.findForward("not_found");

             }
         }

        return mapping.findForward("not_found");
    }
}










