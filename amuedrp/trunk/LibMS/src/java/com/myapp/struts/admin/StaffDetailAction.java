/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.DuplicateEntry;
import com.myapp.struts.MyConnection;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class StaffDetailAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
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
    private String role;
    Connection con;
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
       // role=staff.getRole();
        con=MyConnection.getMyConnection();
        System.out.println("..........."+do_joining+do_releaving+date_of_birth);



        try
        {



         boolean test=DuplicateEntry.checkDuplicateEntry("staff_detail","emai_id" , email_id);
         if(test)
         {
            request.setAttribute("email_id", "You have entered duplicate Email Id :"+email_id);
            return mapping.findForward("duplicate");
         }






        String sql = ("INSERT INTO staff_detail VALUES ('" + employee_id + "','" + courtesy + "','" +
                first_name + "','" + last_name + "','" + contact_no + "','" + mobile_no + "','" +
                email_id + "','" + Date.valueOf(do_joining) + "','" +Date.valueOf( do_releaving) +"','"+ father_name +"','"+
                Date.valueOf(date_of_birth)+ "','"+ gender  +"','"+ address1 +"','"+ city1 +"','"+ state1 +"','"+
                country1 +"','"+ zip1 +"','"+ address1 +"','"+ city2 +"','"+ state2 +"','"+ country2 +
                "','"+ zip2 +"','"+library_id+"')");
        PreparedStatement stmt=con.prepareStatement(sql);
        i=stmt.executeUpdate();
        if(i!=0)
        {
        request.setAttribute("staff_id",employee_id );
        request.setAttribute("library_id",library_id );
        request.setAttribute("first_name",first_name );
        request.setAttribute("last_name",last_name );
        request.setAttribute("email_id",email_id );
        return mapping.findForward("success");

        }
        else
            return mapping.findForward("errorp");
        }catch(Exception e)
        {
        }

        return mapping.findForward("success");
    }
}


