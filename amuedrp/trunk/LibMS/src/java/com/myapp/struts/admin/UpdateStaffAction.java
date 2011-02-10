/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.admin.StaffDetailActionForm;
import  com.myapp.struts.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;

/**
 *
 * @author System Administrator
 */
public class UpdateStaffAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String library_id;
    private String employee_id;
    private String first_name;
    private String last_name;
    private String contact_no;
    private String mobile_no;
    private String email_id;
    private String do_joining;
    private String do_releaving;
    private String date_of_birth;
    private String address1;
    private String city1;
    private String state1;
    private String country1;
    private String zip1;
    private String address2;
    private String city2;
    private String state2;
    private String country2;
    private String zip2;
    private String father_name;
    private String gender;
    private String courtesy;
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
        StaffDetailActionForm staff=(StaffDetailActionForm)form;
        employee_id=staff.getEmployee_id();
        first_name=staff.getFirst_name();
        last_name=staff.getLast_name();
        contact_no=staff.getContact_no();
        mobile_no=staff.getMobile_no();
        email_id=staff.getEmail_id();
        do_joining=staff.getDo_joining();
        date_of_birth=staff.getDate_of_birth();
        do_releaving=staff.getDo_releaving();
        address1=staff.getAddress1();
        city1=staff.getCity1();
        state1=staff.getState1();
        country1=staff.getCountry1();
        zip1=staff.getZip1();
        address2=staff.getAddress2();
        city2=staff.getCity2();
        state2=staff.getState2();
        country2=staff.getCountry2();
        zip2=staff.getZip2();
        father_name=staff.getFather_name();
        gender=staff.getGender();
        courtesy=staff.getCourtesy();
        button=staff.getButton();
        String sql,sql1,sql2;
        ResultSet rst,rst1,rst2;
        System.out.println(button);
        if(button.equals("View"))
         {
                sql="select * from staff_detail where staff_id !='"+employee_id+"' and emai_id='"+email_id+"'and library_id='"+library_id+"'";
              
                rst=MyQueryResult.getMyExecuteQuery(sql);
               
                if(rst.next())
                {
                     request.setAttribute("email_id", "This Email Already Exists");
                     return mapping.findForward("duplicate");
                }
             else
            {
            request.setAttribute("staff_id",employee_id );
            
              request.setAttribute("staff_name",first_name+" "+last_name );
            request.setAttribute("msg", "Sorry Record Updation UnSuceessfully For ");
            return mapping.findForward("success");
              }
         }



         
         if(button.equals("Update"))
         {

        String  id1=(String)session.getAttribute("staff_id");



        String id="admin."+library_id;   //get Institute admin ID
        if(employee_id.equals(id) && employee_id.equals(id1)==false)
         {
              ResultSet rs1=MyQueryResult.getMyExecuteQuery("select * from login where staff_id='"+employee_id+"' and library_id='"+library_id+"'");

            if(rs1.next())
            {
         first_name=rs1.getString("user_name");

            }
               request.setAttribute("user_id", email_id);
              request.setAttribute("staff_name",first_name );
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", employee_id);

                request.setAttribute("msg", "You Cannot Update Institute Admin");
                  return mapping.findForward("success");
         }

       //cannot delete own account
      /*      if(employee_id.equals(id))
            {


                   request.setAttribute("user_id", email_id);

                  request.setAttribute("staff_name",first_name+" "+last_name );
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", employee_id);

                request.setAttribute("msg", "You Cannot Update Your Account for Staff Name :");
                  return mapping.findForward("success");

            }*/
      id=(String)session.getAttribute("staff_id");
         String login_role=(String)session.getAttribute("login_role");    //cannot create co-admin
         ResultSet rs1=MyQueryResult.getMyExecuteQuery("select * from login where staff_id='"+employee_id+"' and library_id='"+library_id+"'");

            if(rs1.next())
            {
                if(rs1.getString("role").equals(login_role) && (rs1.getString("staff_id").equals(id)==false))
                {
                   request.setAttribute("user_id", email_id);

                  request.setAttribute("staff_name",first_name+" "+last_name );
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", employee_id);
                
                request.setAttribute("msg", "You Cannot Update Admin Name :");
                  return mapping.findForward("success");
                }
            }



       

       

             sql = ("update  staff_detail set title='" + courtesy + "',first_name='" +
                first_name + "',last_name='" + last_name + "',contact_no='" + contact_no + "',mobile_no='" + mobile_no + "',emai_id='" +
                email_id + "',date_joining='" + Date.valueOf(do_joining) + "',date_releaving='" +Date.valueOf( do_releaving) +"',father_name='"+ father_name +"',date_of_birth='"+
                Date.valueOf(date_of_birth)+ "',gender='"+ gender  +"',address1='"+ address1 +"',city1='"+ city1 +"',state1='"+ state1 +"',country1='"+
                country1 +"',zip1='"+ zip1 +"',address1='"+ address1 +"',city2='"+ city2 +"',state2='"+ state2 +"',country2='"+ country2 +
                "',zip2='"+ zip2 +"' where library_id='"+library_id+"' and staff_id='"+employee_id+"'");
              i=MyQueryResult.getMyExecuteUpdate(sql);

                   if(i!=0)
                  {
                //admin table updated if staff is admin.library_id
                if(employee_id.equals("admin."+library_id))
                {
                    sql="update admin_registration set city='"+city1+"',state='"+state1+"',Country='"+country1+"',pin='"+zip1+"',land_line_no='"+contact_no+"',mobile_no='"+mobile_no+"',admin_fname='"+first_name+"',admin_lname='"+last_name+"',admin_email='"+email_id+"',courtesy='"+courtesy+"',gender='"+gender+"' where staff_id='"+employee_id+"' and library_id='"+library_id+"'";
                i=MyQueryResult.getMyExecuteUpdate(sql);


                if(i==0)
                {
                    request.setAttribute("staff_id",employee_id );
                    request.setAttribute("staff_name",first_name+" "+last_name );
                    request.setAttribute("msg", "Sorry Record Updation UnSuceessfully For ");
                    return mapping.findForward("success");
                }
                }
                   //login updated
                ResultSet rs=MyQueryResult.getMyExecuteQuery("select * from login where staff_id='"+employee_id+"' and library_id='"+library_id+"'");
                if(rs.next())
                  {


                  sql = ("update  login set user_id='" + email_id + "',user_name='" +
                                 first_name+" "+last_name + "' where staff_id='"+employee_id+"' and library_id='"+library_id+"'");

                  i=MyQueryResult.getMyExecuteUpdate(sql);
                 if(i!=0)
                 {
                    request.setAttribute("staff_id",employee_id );
                    request.setAttribute("staff_name",first_name+" "+last_name );
                    request.setAttribute("msg", "Record Updated Suceessfully For ");
                    return mapping.findForward("success");

                  }
                   }
                  else
                {
                    request.setAttribute("staff_id",employee_id );
                    request.setAttribute("staff_name",first_name+" "+last_name );
                    request.setAttribute("msg", "Record Updated Suceessfully For ");
                    return mapping.findForward("success");
                 }
                 }
             else
                {

                     request.setAttribute("staff_id",employee_id );
                  request.setAttribute("staff_name",first_name+" "+last_name );
                    request.setAttribute("msg", "Sorry Record Updation UnSuceessfully For ");
                 return mapping.findForward("success");
                 }

            
      
         }



               




         if(button.equals("Delete"))
         {
        
            String id="admin."+library_id;   //get Institute admin ID
   if(employee_id.equals(id))
         {
              request.setAttribute("user_id", email_id);
                  request.setAttribute("staff_name",first_name+" "+last_name );
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", employee_id);
                request.setAttribute("msg", "You Cannot Delete Institute Admin ");
                  return mapping.findForward("success");
         }

        id=(String)session.getAttribute("staff_id");  //cannot delete own account
            if(employee_id.equals(id))
            {


                     request.setAttribute("staff_name",first_name+" "+last_name );
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", employee_id);
                request.setAttribute("msg", "You Cannot Delete Your Own Account for Staff Name ");
                  return mapping.findForward("success");

            }
         String login_role=(String)session.getAttribute("login_role");    //cannot create co-admin
         ResultSet rs=MyQueryResult.getMyExecuteQuery("select * from login where staff_id='"+employee_id+"' and library_id='"+library_id+"'");

            if(rs.next())
            {
                if(rs.getString("role").equals(login_role))
                {

               request.setAttribute("staff_name",first_name+" "+last_name );
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", employee_id);

                request.setAttribute("msg", "You Cannot Delete Admin Name :");
                  return mapping.findForward("success");
                }
            }


                ////////////////////////////////
             sql1="Delete from login where user_id=(select emai_id from staff_detail where staff_id='"+employee_id+"' and library_id='"+library_id+"')";
              int j=MyQueryResult.getMyExecuteUpdate(sql1);

             sql="Delete from staff_detail where staff_id='"+employee_id+"' and library_id='"+library_id+"'";
              i=MyQueryResult.getMyExecuteUpdate(sql);
              if(i!=0)
              {
                    if(j!=0)
                    {
                       
                    request.setAttribute("staff_id",employee_id );
                    request.setAttribute("staff_name",first_name+" "+last_name );
                    request.setAttribute("msg", "Staff & Account Details Are Deleted Successfully for ");
                    return mapping.findForward("success");
                    }
                    else
                    {
                    request.setAttribute("staff_id",employee_id );
                    request.setAttribute("staff_name",first_name+" "+last_name );
                    request.setAttribute("msg", "Staff Record are Sucessfully Deleted for  ");
                    return mapping.findForward("success");
                    }
              }
              else
              {
               request.setAttribute("staff_id",employee_id );
               request.setAttribute("staff_name",first_name+" "+last_name );
               request.setAttribute("msg", "Sorry Record Deletion UnSuceessfully For ");
              return mapping.findForward("success");
              }
           
       //////////////////////////////////////////////////
                
          }
        return mapping.findForward("success");
    }
}
        

      
