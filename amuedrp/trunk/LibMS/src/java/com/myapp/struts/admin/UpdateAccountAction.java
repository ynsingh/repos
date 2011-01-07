/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.MyConnection;
import com.myapp.struts.admin.CreateAccountActionForm;
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
public class UpdateAccountAction extends org.apache.struts.action.Action {
    
   /* forward name="success" path="" */
    
    private String user_name;
    private String staff_id;
    private String password;
    private String library_id;
    private String email_id;
    private String button;
    int i;
    Connection con;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
      CreateAccountActionForm caaction=(CreateAccountActionForm)form;
      email_id=caaction.getEmail_id();
      user_name=caaction.getUser_name();
      password=caaction.getPassword();
      staff_id=caaction.getStaff_id();
      button=caaction.getButton();
      HttpSession session=request.getSession();
      library_id=(String)session.getAttribute("library_id");

        if(button.equals("View Account"))
    {

                con=MyConnection.getMyConnection();
                String sql ="select b.staff_id,a.user_id,a.user_name,a.password from login a inner join staff_detail b on a.user_id=b.emai_id and a.library_id=b.library_id where b.staff_id='"+staff_id+"' and b.library_id='"+library_id+"'";

                Statement stmt=con.createStatement();
               ResultSet rs=stmt.executeQuery(sql);
                if(rs.next())

                {
                request.setAttribute("user_id", email_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);

                return mapping.findForward("success");





                }
    }

    if(button.equals("Change Password"))
    {
               
                con=MyConnection.getMyConnection();

                //code to change password



               // password="123";









                String sql = ("update login set password='"+password+"' where user_id='" + email_id +"'");

                PreparedStatement stmt=con.prepareStatement(sql);
                i=stmt.executeUpdate();
                if(i!=0)

                {
                request.setAttribute("user_id", email_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("msg", "Record Updated Successfully for Staff Name :");
                return mapping.findForward("success");

                /*code for Java Mailing*/
                /*mail to user for updatation*/





                }
    }
    if(button.equals("Confirm"))
    {
      



       con=MyConnection.getMyConnection();
       String sql = ("delete  from login where user_id=(select emai_id from staff_detail where staff_id='"+staff_id+"' and library_id='"+library_id+"')");


        PreparedStatement stmt=con.prepareStatement(sql);
        i=stmt.executeUpdate();
        if(i!=0)

      {
            request.setAttribute("user_id", email_id);
      request.setAttribute("user_name", user_name);
      request.setAttribute("library_id", library_id);
      request.setAttribute("staff_id", staff_id);


            request.setAttribute("msg", "Record Deleted Successfully for Staff Name : ");
         return mapping.findForward("success");

/*code for Java Mailing*/
/*mail to user for deletion of account message*/





      }
    }
      return mapping.findForward("success");
       
      }
    }




    