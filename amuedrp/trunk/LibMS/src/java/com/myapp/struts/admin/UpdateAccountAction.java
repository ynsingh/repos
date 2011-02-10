/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.*;
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
    private String role;
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
      role=caaction.getRole();
      HttpSession session=request.getSession();
      library_id=(String)session.getAttribute("library_id");
      request.setAttribute("btn",button);
      System.out.println(role);

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
                request.setAttribute("role", role);
                return mapping.findForward("success");





                }
    }

    if(button.equals("Update Account"))
    {



         String id="admin."+library_id;   //get Institute admin ID
   if(staff_id.equals(id))
         {
              request.setAttribute("user_id", email_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                 request.setAttribute("role", role);
                request.setAttribute("msg1", "You Cannot Update Institute Admin");
                  return mapping.findForward("success");
         }

        id=(String)session.getAttribute("staff_id");  //cannot delete own account
            if(staff_id.equals(id))
            {


                   request.setAttribute("user_id", email_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                 request.setAttribute("role", role);
                request.setAttribute("msg1", "You Cannot Update Your Own Account");
                  return mapping.findForward("success");

            }
         String login_role=(String)session.getAttribute("login_role");    //cannot create co-admin
         ResultSet rs=MyQueryResult.getMyExecuteQuery("select * from login where staff_id='"+staff_id+"' and library_id='"+library_id+"'");

            if(rs.next())
            {
                if(rs.getString("role").equals(login_role))
                {
                   request.setAttribute("user_id", email_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                 request.setAttribute("role",rs.getString("role"));
                request.setAttribute("msg1", "You Cannot Update Admin ");
                  return mapping.findForward("success");
                }
            }


                if(role.equals("admin" +
                "") && login_role.equals("admin"))
                {
                   request.setAttribute("user_id", email_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                 request.setAttribute("role",rs.getString("role"));
                request.setAttribute("msg1", "You Cannot Create Admin ");
                  return mapping.findForward("success");
                }


                con=MyConnection.getMyConnection();

                //code to change password



               // password="123";


   //if existing role is updated to new role of any user like staff role updated  with staff...
//in this case no privilege are to be reassigned but password can be modified.
//normally it can be user by mistake case..................

System.out.println(role+staff_id);
String user_role="";
ResultSet temp=MyQueryResult.getMyExecuteQuery("select * from login where staff_id='"+staff_id+"' and library_id='"+library_id+"'");
if(temp.next())
    user_role=temp.getString("role");

if(user_role.equals(role))
{

                String sql = ("update login set password='"+password+"' where user_id='" + email_id +"'");
   PreparedStatement stmt=con.prepareStatement(sql);
                i=stmt.executeUpdate();
                if(i!=0)

                {
                 request.setAttribute("user_id", email_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                 request.setAttribute("role", role);
                 request.setAttribute("msg", "Record Updated Successfully");
                return mapping.findForward("success");

                }


}


//if admin role decrease to staff then previous privilege will have to restored and only admin/systemsetup and utitlty should
//be removed
user_role="";
temp=MyQueryResult.getMyExecuteQuery("select * from login where staff_id='"+staff_id+"' and library_id='"+library_id+"'");
if(temp.next())
   user_role=temp.getString("role");
System.out.println("user_role="+user_role+ "  role="+role);
if(user_role.equals("admin") && role.equals("staff"))
{

                String sql = ("update login set password='"+password+"',role='"+role+"' where user_id='" + email_id +"'");
   PreparedStatement stmt=con.prepareStatement(sql);
                i=stmt.executeUpdate();
                System.out.println("i="+i);
                if(i>0)

                {

                sql = ("update privilege set administrator='"+true+"',system_setup='"+true+"',utilities='"+true+"' where staff_id='" + staff_id +"' and library_id='"+library_id+"'");
                stmt=con.prepareStatement(sql);
               int j=stmt.executeUpdate();
               System.out.println("j="+j);
                  if(j>0)
                  {
                      System.out.println("..............kkkkkkkk");
                    request.setAttribute("user_id", email_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);
                    request.setAttribute("msg", "Record Updated Successfully");
                    return mapping.findForward("success");
                  }
                }



}











                String sql = ("update login set password='"+password+"',role='"+role+"' where user_id='" + email_id +"'");






                PreparedStatement stmt=con.prepareStatement(sql);
                i=stmt.executeUpdate();
                if(i!=0)

                {
                    int x=MyQueryResult.getMyExecuteUpdate("delete from privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'");
                    if(x>0)
                    {if(role.equals("staff"))
                            Privilege.assignStaffPrivilege(staff_id, library_id);
                    else if(role.equals("admin"))
                            Privilege.assignAdminPrivilege(staff_id, library_id);
                    }
                request.setAttribute("user_id", email_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                 request.setAttribute("role", role);
                request.setAttribute("msg", "Record Updated Successfully");
                return mapping.findForward("success");

                /*code for Java Mailing*/
                /*mail to user for updatation*/





                }else
                {

                }
    }
    if(button.equals("Confirm"))
    {
        String id="admin."+library_id;   //get Institute admin ID
   if(staff_id.equals(id))
         {
              request.setAttribute("user_id", email_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                 request.setAttribute("role", role);
                request.setAttribute("msg1", "You Cannot Delete Institute Admin");
                  return mapping.findForward("success");
         }

        id=(String)session.getAttribute("staff_id");  //cannot delete own account
            if(staff_id.equals(id))
            {


                   request.setAttribute("user_id", email_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                 request.setAttribute("role", role);
                request.setAttribute("msg1", "You Cannot Delete Your Own Account");
                  return mapping.findForward("success");

            }
         String login_role=(String)session.getAttribute("login_role");    //cannot create co-admin
         ResultSet rs=MyQueryResult.getMyExecuteQuery("select * from login where staff_id='"+staff_id+"' and library_id='"+library_id+"'");

            if(rs.next())
            {
                if(rs.getString("role").equals(login_role))
                {
                   request.setAttribute("user_id", email_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                 request.setAttribute("role", role);
                request.setAttribute("msg1", "You Cannot Delete Admin");
                  return mapping.findForward("success");
                }
            }

    
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
     request.setAttribute("role", role);


            request.setAttribute("msg", "Record Deleted Successfully  ");
         return mapping.findForward("success");

/*code for Java Mailing*/
/*mail to user for deletion of account message*/





      }
    }
      return mapping.findForward("success");
       
      }
    }




    