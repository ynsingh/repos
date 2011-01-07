/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.MyConnection;
import com.myapp.struts.admin.AdminRegistrationActionForm;
import com.myapp.struts.opac.MyQueryResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Dushyant
 */
public class AdminRegistrationAction extends org.apache.struts.action.Action {
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
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AdminRegistrationActionForm adminRegistrationActionForm=(AdminRegistrationActionForm)form;
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
        institute_name.trim();
        abbreviated_name.trim();
        institute_address.trim();
        pin.trim();
        city.trim();
        state.trim();
        country.trim();
        land_line_no.trim();
        mobile_no.trim();
        type_of_institute.trim();
        institute_domain.trim();
        institute_website.trim();
        admin_fname.trim();
        admin_lname.trim();
        admin_designation.trim();
        admin_email.trim();
        admin_password.trim();
        library_name.trim();
        courtesy.trim();
        gender.trim();
        HttpSession session=request.getSession();
System.out.println(library_name);
        try{
        con=MyConnection.getMyConnection();


       ResultSet rs=MyQueryResult.getMyExecuteQuery("select * from admin_registration where admin_email='"+admin_email+"'");
       ResultSet rs1=MyQueryResult.getMyExecuteQuery("select * from login where user_id='"+admin_email+"'");
      ResultSet rs2=MyQueryResult.getMyExecuteQuery("select * from staff_detail where emai_id='"+admin_email+"'");
       if(rs.next()|| rs1.next() || rs2.next())
       {

            request.setAttribute("msg", "Dublicate Email ID");
            return mapping.findForward("failure");
       }

       else
       {

                PreparedStatement stmt=con.prepareStatement("insert into admin_registration (institute_name,abbreviated_name,institute_address,city,state,Country,pin,land_line_no,mobile_no,domain,type_of_institute,website,admin_fname,admin_lname,admin_designation,admin_email,admin_password,library_name,courtesy,gender,status) values('"+institute_name+"','"+abbreviated_name+"','"+institute_address+"','"+
                city+"','"+state+"','"+country+"','" +pin+"','"+land_line_no+"','"+mobile_no+"','"+institute_domain+"','"+type_of_institute+"','"+institute_website+"','"+admin_fname+"','"+
                admin_lname+"','"+admin_designation+"','"+admin_email+"','"+admin_password+"','"+library_name+"','"+courtesy+"','"+gender+"','NotRegistered')");
                i=stmt.executeUpdate();
                stmt=con.prepareStatement("select max(registration_id) from admin_registration");
                ResultSet rst=stmt.executeQuery();
                rst.next();
                String msg="Registered successfully with registration id :"+rst.getInt(1);
                // System.out.println(msg);
                request.setAttribute("registration_msg", msg);
       }

        }
        catch(Exception e)
        {
        System.out.println(e);
         String msg="Registration failure due to some error";
        request.setAttribute("registration_msg", msg);
        session.invalidate();
         return mapping.findForward("failure");
        }



         return mapping.findForward("success1");



    }
}
