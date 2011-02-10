/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.admin.AdminViewActionForm;

import  com.myapp.struts.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author System Administrator
 */
public class AdminUpdateAction1 extends org.apache.struts.action.Action {
    
   private int registration_id ;
private String institute_name ;
private String abbreviated_name ;
private String institute_address ;
private String city ;
private String state ;
private String country ;
private String pin ;
private String land_line_no ;
private String mobile_no ;
private String institute_domain ;
private String institute_website ;
private String admin_fname ;
private String admin_lname ;
private String admin_designation ;
private String type_of_institute;
private String admin_email;
private String admin_password;
private String library_name;
private String courtesy;
private String gender;
private String sql;
private String status;
private String library_id;
private String staff_id;
Connection con=null;
int i=0;
    /* forward name="success" path="" */


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
        AdminViewActionForm adminRegistrationActionForm=(AdminViewActionForm)form;
        registration_id=adminRegistrationActionForm.getRegistration_request_id();
        institute_name=adminRegistrationActionForm.getInstitute_name();
        abbreviated_name=adminRegistrationActionForm.getAbbreviated_name();
        institute_address=adminRegistrationActionForm.getInstitute_address();
        pin=adminRegistrationActionForm.getPin();
        city=adminRegistrationActionForm.getCity();
        state=adminRegistrationActionForm.getState();
        country=adminRegistrationActionForm.getCountry();
        land_line_no=adminRegistrationActionForm.getLand_line_no();
        mobile_no=adminRegistrationActionForm.getMobile_no();
        type_of_institute=adminRegistrationActionForm.getType_of_institute();
        institute_domain=adminRegistrationActionForm.getInstitute_domain();
        institute_website=adminRegistrationActionForm.getInstitute_website();
        admin_fname=adminRegistrationActionForm.getAdmin_fname();
        admin_lname=adminRegistrationActionForm.getAdmin_lname();
        admin_designation=adminRegistrationActionForm.getAdmin_designation();
        admin_email=adminRegistrationActionForm.getAdmin_email();
        admin_password=adminRegistrationActionForm.getAdmin_password();
        library_name=adminRegistrationActionForm.getLibrary_name();
        courtesy=adminRegistrationActionForm.getCourtesy();
        gender=adminRegistrationActionForm.getGender();
        library_id=adminRegistrationActionForm.getLibrary_id();

      
        HttpSession session=request.getSession();

        try
        {
       con=MyConnection.getMyConnection();
                 PreparedStatement pstmt2=con.prepareStatement("select * from library where registration_id!="+registration_id+" and library_id='"+library_id+"'");
                ResultSet rs2=pstmt2.executeQuery();


                if(rs2.next())
                {
                System.out.println("This Libaray ID already exists");

                //   System.out.println(query_id);
                request.setAttribute("reg", "Library Already Registered With Libaray ID:"+library_id);
                return mapping.findForward("failure");
                }

            
            PreparedStatement pstmt=con.prepareStatement("select * from admin_registration where registration_id!="+  registration_id+ " and admin_email='"+admin_email+"'");
            ResultSet rs=pstmt.executeQuery();
        
            PreparedStatement pstmt1=con.prepareStatement("select * from staff_detail where staff_id!='"+"admin."+library_id+"' and emai_id='"+admin_email+"'");

            ResultSet rs1=pstmt1.executeQuery();

       if(rs.next() || rs1.next() )
       {
          //  System.out.println("Duplicate Email ID");
            request.setAttribute("reg1", "Email: "+admin_email+" already exists");
            return mapping.findForward("failure");
       }


            ResultSet rs3=MyQueryResult.getMyExecuteQuery("select status from admin_registration where registration_id="+registration_id);
            if(rs3.next())
            {
            status=rs3.getString("status");
            }
        //if admin is registered
      if(status.equals("Registered"))
       {
            //update admin
                PreparedStatement stmt=con.prepareStatement("update admin_registration set institute_name='"+institute_name+"',abbreviated_name='"+abbreviated_name+"',institute_address='"+institute_address+"',city='"+city+"',state='"+state+"',Country='"+country+"',pin='"+pin+"',land_line_no='"+land_line_no+"',mobile_no='"+mobile_no+"',domain='"+institute_domain+"',type_of_institute='"+type_of_institute+"',website='"+institute_website+"',admin_fname='"+admin_fname+"',admin_lname='"+admin_lname+"',admin_designation='"+admin_designation+"',admin_email='"+admin_email+"',admin_password='"+admin_password+"',library_name='"+library_name+"',courtesy='"+courtesy+"',gender='"+gender+"' where registration_id="+registration_id) ;

                i=stmt.executeUpdate();
             
                if(i!=0)
                {

                    ResultSet rs4=MyQueryResult.getMyExecuteQuery("select staff_id from admin_registration where registration_id="+registration_id) ;
                    if(rs4.next())
                        staff_id=rs4.getString("staff_id");
            ///update staff
                          sql = ("update  staff_detail set title='" + courtesy + "',first_name='" +
                                 admin_fname + "',last_name='"+admin_lname+"',contact_no='" + land_line_no + "',mobile_no='" + mobile_no + "',emai_id='" +
                  admin_email+ "',gender='"+ gender  +"',city1='"+ city +"',state1='"+ state +"',country1='"+
                country +"',zip1='"+ pin +"' where staff_id='"+staff_id+"' and library_id='"+ library_id + "'");
                 i=MyQueryResult.getMyExecuteUpdate(sql);
     

                    if(i!=0)
                    {
            //update login
                     sql = ("update  login set user_id='" + admin_email + "',user_name='" +
                                 admin_fname+" "+admin_lname + "',password='"+admin_password+"' where staff_id='"+staff_id+"' and library_id='"+ library_id + "'");

                     i=MyQueryResult.getMyExecuteUpdate(sql);
                   if(i!=0)
                   {
                    String msg="Record Updated Successfully for Library :"+library_name;
                    request.setAttribute("msg", msg);
                     request.setAttribute("registration_id",registration_id);
                        return mapping.findForward("success");
                }
                    }
                }
                else
                {
                        String msg="Failure due to some Internal Errors";
                        request.setAttribute("msg", msg);
                        request.setAttribute("registration_id",registration_id);
                        return mapping.findForward("failure");
                    
                }
     }
     else
     {
     //if admin is unregistered
              //update admin
                PreparedStatement stmt=con.prepareStatement("update admin_registration set institute_name='"+institute_name+"',abbreviated_name='"+abbreviated_name+"',institute_address='"+institute_address+"',city='"+city+"',state='"+state+"',Country='"+country+"',pin='"+pin+"',land_line_no='"+land_line_no+"',mobile_no='"+mobile_no+"',domain='"+institute_domain+"',type_of_institute='"+type_of_institute+"',website='"+institute_website+"',admin_fname='"+admin_fname+"',admin_lname='"+admin_lname+"',admin_designation='"+admin_designation+"',admin_email='"+admin_email+"',admin_password='"+admin_password+"',library_name='"+library_name+"',courtesy='"+courtesy+"',gender='"+gender+"' where registration_id="+registration_id) ;

                i=stmt.executeUpdate();

                if(i!=0)
                {

                    String msg="Record Updated Successfully for Library :"+library_name;
                    request.setAttribute("msg", msg);
                     request.setAttribute("registration_id",registration_id);
                        return mapping.findForward("success");

                    }
              
                else
                {
                     String msg="Failure due to some Internal Errors";
                     request.setAttribute("msg", msg);
                     request.setAttribute("registration_id",registration_id);
                     return mapping.findForward("failure");
                }



     }
      

                 //System.out.println(msg);
                

        }
        catch(Exception e)
        {
        e.printStackTrace();
         String msg="Failure due to some Internal Errors";
        request.setAttribute("msg", msg);
       request.setAttribute("registration_id",registration_id);
         return mapping.findForward("failure");
        }



         return mapping.findForward("failure");




       

    }
}
