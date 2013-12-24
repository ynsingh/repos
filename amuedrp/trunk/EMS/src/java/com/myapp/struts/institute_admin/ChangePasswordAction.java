/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.institute_admin;

import com.myapp.struts.admin.AdminViewActionForm;
import com.myapp.struts.hbm.AdminRegistration;
import com.myapp.struts.hbm.AdminRegistrationDAO;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.LoginDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.utility.PasswordEncruptionUtility;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 *
 * @author Edrp-04
 */



public class ChangePasswordAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String SUCCESS1 = "success1";
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
private String institute_id;
private String staff_id;
private String user_id;
private String working_status;
private String password;
private String button;

 Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";

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
        institute_id=adminRegistrationActionForm.getInstitute_id();
        user_id=adminRegistrationActionForm.getUser_id();
        password=adminRegistrationActionForm.getPassword();
        button=adminRegistrationActionForm.getButton();
        institute_website=adminRegistrationActionForm.getInstitute_website();
        System.out.println("helloooooooooooooooooooo "+institute_name);
        HttpSession session = request.getSession();


        try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
    System.out.println("Button is "+button);
        if(button!=null && button.equals("Update"))
        {
            AdminRegistrationDAO admindao = new AdminRegistrationDAO();
            AdminRegistration admf=new AdminRegistration();
            
            System.out.println("Institute idddddddddddd is"+institute_id);
            List<AdminRegistration> adm =admindao.getAdminDeatilsByInstituteId(institute_id);
            if(adm!=null && !adm.isEmpty())
            {
                admf = (AdminRegistration)adm.get(0);
                admf.setInstituteName(institute_name);
                admf.setAbbreviatedName(abbreviated_name);
                admf.setInstituteAddress(institute_address);
                admf.setCourtesy(courtesy);
                admf.setAdminFname(admin_fname);
                admf.setAdminLname(admin_lname);
                admf.setAdminDesignation(admin_designation);
                admf.setCity(city);
                admf.setState(state);
                admf.setCountry(country);
                admf.setMobileNo(mobile_no);
                admf.setPin(pin);
                admf.setLandLineNo(land_line_no);
                admf.setGender(gender);
                admf.setTypeOfInstitute(type_of_institute);
                admf.setWebsite(institute_website);
                        admindao.update(admf);
                System.out.println("in ifffffffffffffff");
                String msg=resource.getString("record_updated_successfully");
                    request.setAttribute("msg",msg);
                 return mapping.findForward(SUCCESS1);
            }
            else{
            System.out.println("in elseeeeeeeeeee");
            return mapping.findForward(SUCCESS1);
            }

        }
        else{
        LoginDAO logindao= new LoginDAO();
        List<Login> login1 =logindao.getUser(user_id);
        if(login1!=null && !login1.isEmpty())
        {
        Login login = login1.get(0);
        String usr=login.getUserId();

        
        System.out.println("pass"+"usr");
        password = PasswordEncruptionUtility.password_encrupt(password);
        login.setPassword(password);

        logindao.update(login);
        session.invalidate();
        String msg=resource.getString("password_change_loginagain");
            System.out.println("message isssssssssaaaaaaaaaaaa ");
        request.setAttribute("msg", msg);
        System.out.println("message isssssssss ");
        return mapping.findForward(SUCCESS);
        }
        else
        {
              System.out.println("message isssssssssbbbbbbbbbbbbbbbbb ");
            request.setAttribute("msg", "Invalid User name  or Password!");
        return mapping.findForward(SUCCESS);
        }
    }
//    return null;
    }
}
