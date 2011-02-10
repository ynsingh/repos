/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;



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
public class AdminUpdateAction extends org.apache.struts.action.Action {
    
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
private String domain;
private String website;
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
     /*   AdminRegistrationActionForm adminRegistrationActionForm=(AdminRegistrationActionForm)form;
        registration_id=adminRegistrationActionForm.getRegistration_id();
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
        domain=adminRegistrationActionForm.getInstitute_domain();
        website=adminRegistrationActionForm.getInstitute_website();
        HttpSession session=request.getSession();

        try
        {
        con=MyConnection.getMyConnection();


       
                PreparedStatement stmt=con.prepareStatement("update admin_registration set institute_name='"+institute_name+"',abbreviated_name='"+abbreviated_name+"',institute_address='"+institute_address+"',city'"+city+"',state='"+state+"',Country='"+country+"',pin='"+pin+"',land_line_no='"+land_line_no+"',mobile_no='"+mobile_no+"',domain='"+domain+"',type_of_institute='"+type_of_institute+"',website='"+website+"',admin_fname='"+admin_fname+"',admin_lname='"+admin_lname+"',admin_designation='"+admin_designation+"',admin_email='"+admin_email+"',admin_password='"+admin_password+"',library_name='"+library_name+"',courtesy='"+courtesy+"',gender='"+gender+"' where registration_id="+registration_id) ;
                i=stmt.executeUpdate();
              //  stmt=con.prepareStatement("select max(registration_id) from admin_registration");
//                ResultSet rst=stmt.executeQuery();
  //              rst.next();
                String msg="Update  successfully with registration id :"+registration_id;
                // System.out.println(msg);
                request.setAttribute("msg", msg);
       

        }
        catch(Exception e)
        {
        System.out.println(e);
         String msg="failure due to some error";
        request.setAttribute("registration_msg", msg);
        session.invalidate();
         return mapping.findForward("failure");
        }



         return mapping.findForward("success");

*/


         registration_id=(Integer.parseInt(request.getParameter("id")));
      //  System.out.println("Registration Id="+registration_id);
        try{
        con=MyConnection.getMyConnection();
        PreparedStatement stmt=con.prepareStatement("select * from admin_registration where  registration_id="+registration_id);
      ResultSet  rst=stmt.executeQuery();
        request.setAttribute("resultset", rst);
        return mapping.findForward("success");
        }
        catch(Exception e)
        {
        e.getMessage();
        }
           return mapping.findForward("success");


    }
}
