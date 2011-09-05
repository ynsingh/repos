/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.institute_admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 *
 * @author Edrp-04
 */
public class Update_ManagerAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
     private String first_name;
    private String last_name;
    private String address1;
    private String city1;
    private String state1;
    private String country1;
    private String gender;
    private String contact_no;
    private String mobile_no;
    private String department;
    private String staff_id;
    private String manager_id;
    private String institute_id;
    private String user_id;
    private String password;
    private String zip1;
    private String repassword;
    private String email_id;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";



    private static final String SUCCESS = "success";
    
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
        Election_Manager_RegistrationActionForm electionManagerForm=(Election_Manager_RegistrationActionForm)form;
        //System.out.println("hey there");
        try{
        
       //
 HttpSession session = request.getSession();

             try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);


        first_name=electionManagerForm.getFirst_name();
        //System.out.println("hey there first name");
        last_name=electionManagerForm.getLast_name();
       // System.out.println("hey there last name");
        address1=electionManagerForm.getAddress1();
       // System.out.println("hey there address");
        city1=electionManagerForm.getCity1();
       // System.out.println("hey there city");
        state1=electionManagerForm.getState1();
       // System.out.println("hey there state");
        zip1=electionManagerForm.getZip1();
       // System.out.println("hey there zip");
        country1=electionManagerForm.getCountry1();
       // System.out.println("hey there country");
        gender=electionManagerForm.getGender();
        //System.out.println("hey there gender");
        contact_no=electionManagerForm.getContact_no();
        //System.out.println("hey there contact no");
        mobile_no=electionManagerForm.getMobile_no();
       // System.out.println("hey there mobile no");
        department=electionManagerForm.getDepartment();
        staff_id=electionManagerForm.getStaff_id();
        manager_id=electionManagerForm.getManager_id();
        institute_id=electionManagerForm.getInstitute_id();
        user_id=electionManagerForm.getUser_id();
        email_id=electionManagerForm.getEmail_id();





        //LoginDAO logindao= new LoginDAO();
        StaffDetailDAO staffdao= new StaffDetailDAO();
        ElectionManagerDAO managerdao=new ElectionManagerDAO();
        StaffManagerDAO staffmanagerdao=new StaffManagerDAO();


  

                Election_Manager_StaffDetail staffmanager=new Election_Manager_StaffDetail();
               // staffmanager.getStaffDetail().
                List<Election_Manager_StaffDetail> lstManager= (List<Election_Manager_StaffDetail>)managerdao.GetManagerDetails(manager_id,institute_id);
                if(!lstManager.isEmpty())staffmanager = (Election_Manager_StaffDetail)lstManager.get(0);
                staffmanager.getStaffDetail().setFirstName(first_name);
                staffmanager.getStaffDetail().setLastName(last_name);
                staffmanager.getStaffDetail().setAddress1(address1);
                staffmanager.getStaffDetail().setCity1(city1);
                staffmanager.getStaffDetail().setState1(state1);
                staffmanager.getStaffDetail().setZip1(zip1);
                staffmanager.getStaffDetail().setCountry1(country1);
                staffmanager.getStaffDetail().setGender(gender);
                staffmanager.getStaffDetail().setContactNo(contact_no);
                staffmanager.getStaffDetail().setMobileNo(mobile_no);
                staffmanager.getStaffDetail().setEmailId(email_id);
                staffmanager.getElectionManager().setDepartment(department);
                //staffmanager.getElectionManager().setUserId(user_id);
               // staff_id=staffmanager.getStaffDetail().getId().getStaffId();
                //staffmanager.getLogin();
                //Login l=logindao.getbyStaffId(staff_id);
                
                
                //staffmanagerdao.update(staffmanager);
                    staffmanagerdao.update(staffmanager);

                    String msg=resource.getString("record_updated_successfully");
                    request.setAttribute("msg",msg);
        }
 catch(Exception e)
 {
     //e.getMessage();
     request.setAttribute("msg", e.getMessage());
 

       






//if(request.getAttribute("msg")==null)
//request.setAttribute("msg", "record not updated successfully");

        return mapping.findForward(SUCCESS);}
        catch(ExceptionInInitializerError e)
 {
     //e.getMessage();
     request.setAttribute("msg", e.getMessage());









//if(request.getAttribute("msg")==null)
//request.setAttribute("msg", "record not updated successfully");

        return mapping.findForward("failure");}
        return mapping.findForward("success");
    }
    
}
