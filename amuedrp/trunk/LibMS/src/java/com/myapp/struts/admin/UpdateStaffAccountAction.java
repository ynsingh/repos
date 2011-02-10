/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.admin.CreateAccountActionForm;
import  com.myapp.struts.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;

/**
 *
 * @author Dushyant
 */
public class UpdateStaffAccountAction extends org.apache.struts.action.Action {
    
   /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String library_id;
    private String staff_id;
     private String user_name;

    private String email_id;
     private String password;

    private String button;
    int i=0;

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
    
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        CreateAccountActionForm staff=(CreateAccountActionForm)form;
       
        email_id=staff.getEmail_id();
        staff_id=staff.getStaff_id();
        user_name=staff.getUser_name();
        password=staff.getPassword();
       button=staff.getButton();
       String sql;
   ResultSet rst;
       if(button.equals("Create Account"))
         {

        sql = "insert into  login values('"+email_id+"','"+user_name+"','" +password+
               "','"+library_id+"')";
        i=MyQueryResult.getMyExecuteUpdate(sql);

        if(i!=0)
        {
        request.setAttribute("staff_id",staff_id );
         request.setAttribute("msg", "Record Inserted Suceessfully for Staff :"+user_name);
        return mapping.findForward("success");

        }
        else
        {
            request.setAttribute("staff_id",staff_id );
            request.setAttribute("msg", "Sorry Record Inserted UnSuceessfully for Staff :"+user_name);
            return mapping.findForward("success");
        }

       }
        if(button.equals("View Account"))
        {
             rst=MyQueryResult.getMyExecuteQuery("select a.staff_id,b.user_id,b.user_name,b.password from login b inner join staff_detail a on a.emai_id=b.user_id and a.library_id=b.library_id" );
                if(rst.next())
                {

            request.setAttribute("button", button);

                request.setAttribute("account_resultset1", rst);

                return mapping.findForward("register");


        }
   if(button.equals("Change Password"))
         {
               

         sql = ("update  login set password='" + password + "',user_name='" +
                user_name + "' where library_id='"+library_id+"' and user_id='"+email_id+"'");
        i=MyQueryResult.getMyExecuteUpdate(sql);

        if(i!=0)
        {
        request.setAttribute("staff_id",staff_id );
         request.setAttribute("msg", "Record Updated Suceessfully for Staff : "+user_name);
        return mapping.findForward("success");

        }
        else
        {
            request.setAttribute("staff_id",staff_id );
            request.setAttribute("msg", "Sorry Record Updation UnSuceessfully for Staff : "+user_name);
            return mapping.findForward("success");
        }
         }
         if(button.equals("Delete Account"))
         {

             sql="Delete from login where user_id='"+email_id+"' and library_id='"+library_id+"'";
              i=MyQueryResult.getMyExecuteUpdate(sql);
              if(i!=0)
              {

              request.setAttribute("staff_id",staff_id );
              request.setAttribute("msg", "Record  Deleted Successfully for Staff : "+user_name);
              return mapping.findForward("success");
              }
              else
              {
               request.setAttribute("staff_id",staff_id );
               request.setAttribute("msg", "Sorry Record Deletion UnSuceessfully for Staff : "+user_name);
              return mapping.findForward("success");
              }
         
              
         

         }
  
    }
 return mapping.findForward("success");
    }
    
}


