/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import com.myapp.struts.hbm.*;
import java.sql.Connection;
import org.hibernate.HibernateException;
import java.util.*;


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
private String user_id;
private String admin_password;
private String enrollment;
private String courtesy;
private String gender;
Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
Connection con1;
int i=0;
  
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
        user_id=admin_email;
      
        enrollment=adminRegistrationActionForm.getEnrollment();
        courtesy=adminRegistrationActionForm.getCourtesy();
        gender=adminRegistrationActionForm.getGender();

         HttpSession session=request.getSession();
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


           if(adminRegistrationActionForm.getInstitute_name()!=null)
        {
        
System.out.println("institute_name="+institute_name);
        try{


        AdminRegistrationDAO admindao = new AdminRegistrationDAO();
        LoginDAO logindao = new LoginDAO();

  List ins=admindao.getAdminDeatilsByInstituteName(institute_name);
       List rs=admindao.getAdminDeatilsByUserId(user_id);
       List rs1=logindao.getUser(user_id);
      // List rs2=MyQueryResult.getMyExecuteQuery("select * from staff_detail where emai_id='"+admin_email+"'");
       Iterator it = rs.iterator();
       Iterator it1 = rs1.iterator();

        Iterator it2=ins.iterator();
       //while()
       if(it2.hasNext())
       {
           String msg1=resource.getString("duplicate_institute_name");
            request.setAttribute("msg1", msg1);
            return mapping.findForward("failure");
       }



       if(it.hasNext() || it1.hasNext() )
       {

            String msg1=resource.getString("duplicate_user_id");
            request.setAttribute("msg1", msg1);
            return mapping.findForward("failure");
       }

       else
       {
            AdminRegistration adminReg=new AdminRegistration();
                adminReg.setInstituteName(institute_name);
                adminReg.setAbbreviatedName(abbreviated_name);
                adminReg.setInstituteAddress(institute_address);
                adminReg.setCity(city);
                adminReg.setState(state);
                adminReg.setCountry(country);
                adminReg.setPin(pin);
                adminReg.setLandLineNo(land_line_no);
                adminReg.setMobileNo(mobile_no);
                adminReg.setDomain(institute_domain);
                adminReg.setTypeOfInstitute(type_of_institute);
                adminReg.setWebsite(institute_website);
                adminReg.setAdminFname(admin_fname);
                adminReg.setAdminLname(admin_lname);
                adminReg.setAdminDesignation(admin_designation);
                adminReg.setAdminEmail(admin_email);
               
                adminReg.setEnrollment(enrollment);
                adminReg.setCourtesy(courtesy);
                System.out.println("Courtesy");
                adminReg.setGender(gender);
                System.out.println("Gender");
                adminReg.setStatus("NotRegistered");
                adminReg.setWorkingStatus("OK");
                adminReg.setUserId(admin_email);
                System.out.println("UserId");
                
                registration_id = admindao.insert(adminReg);
                String msg=resource.getString("requestforregistration_accept_successfuly")+String.valueOf(user_id) ;
               
                request.setAttribute("registration_msg", msg);
                return mapping.findForward("success");
       }

        }
        catch(HibernateException e)
        {
        System.out.println("Hibernate Exception: "+e.getMessage());
         String msg1="Database Not Connected! Please contact web-Administrator";
        request.setAttribute("msg1", msg1);
        session.invalidate();
         return mapping.findForward("success");
        }
        catch(Exception e)
        {
        System.out.println(e);
         String msg1="Request for registration failure due to some error";
        request.setAttribute("msg1", msg1);
        session.invalidate();
         return mapping.findForward("success");
        }
//return mapping.findForward("failure");
        }
        return mapping.findForward("failure");
            }
}
