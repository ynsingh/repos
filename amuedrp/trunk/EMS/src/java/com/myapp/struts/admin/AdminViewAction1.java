/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;


import  com.myapp.struts.*;
import com.myapp.struts.hbm.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import  com.myapp.struts.utility.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dushyant
 */
public class AdminViewAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    
    private  String institute_id;
    private int registration_request_id;
    private String staff_id;
  private final ExecutorService executor=Executors.newFixedThreadPool(1);
    Email obj;
    private String admin_password;
    private String admin_password1;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
    private String button;
    
    
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
            throws Exception
    {
        AdminViewActionForm admin=(AdminViewActionForm)form;
        registration_request_id=admin.getRegistration_request_id();
        institute_id=admin.getInstitute_id();
        button=admin.getButton();
        System.out.println("Registration_Id="+ registration_request_id +" Institute ID="+institute_id+button);
        staff_id="admin."+institute_id;
System.out.println("button"+button);
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


        //Institute Table Data Access Objects and POJO's
            InstituteDAO institutedao = new InstituteDAO();
            Institute institute = new Institute();
            //Staff Detail Table Data Access Objects and POJO's
            StaffDetailDAO staffDetaildao = new StaffDetailDAO();
            StaffDetail staffDetail = new StaffDetail();
            StaffDetailId staffDetailId = new StaffDetailId();
            //Login Table Data Access Objects and POJO's
            LoginDAO logindao = new LoginDAO();
            Login login = new Login();
            //Admin Registration Table Data Access Objects and POJO's
            AdminRegistrationDAO admindao = new AdminRegistrationDAO();
            AdminRegistration adminReg = new AdminRegistration();

if (institute_id!=null)
{
          //  System.out.println("..................."+institute_name);
      // System.out.println("user_id="+institute_id+library_name);
  try
   {

      
      if(button.equalsIgnoreCase("accept")){
        System.out.println("Institute Id="+institute_id);
        boolean b=DuplicateEntry.checkDuplicateEntry("institute","institute_id",institute_id);
        boolean b1=DuplicateEntry.checkDuplicateEntry("login","staff_id",staff_id,institute_id);
        if(b==false)
        {
            
         //request.setAttribute("query_id",query_id);
      //   System.out.println(query_id);
            String msg=resource.getString("institute_registeredwith_id");
             request.setAttribute("msg", msg +institute_id);
             return mapping.findForward("failure");
        }
        if(b1==false)
        {
            request.setAttribute("msg", "Admin Already Registered with Staff_ID: "+staff_id);
           
              return mapping.findForward("failure");
        }
       //insert into library
           List adminList = (List)admindao.getAdminDeatilsById(registration_request_id);
            if (adminList.isEmpty())
            {
                request.setAttribute("ErrorMsg", "DataBase Disconnected! Please Contact Web Admin");
                return mapping.findForward("error");
            }
            adminReg = (AdminRegistration)adminList.get(0);

            institute.setInstituteId(institute_id);
            institute.setRegistrationId(registration_request_id);
            institute.setInstituteName(adminReg.getInstituteName());
            institute.setWorkingStatus("OK");

            //institutedao.insert(institute);

            staffDetailId.setStaffId(staff_id);
            staffDetailId.setInstituteId(institute_id);

            staffDetail.setId(staffDetailId);
            staffDetail.setInstitute(institute);
            staffDetail.setEmailId(adminReg.getAdminEmail());
            staffDetail.setFirstName(adminReg.getAdminFname());
            staffDetail.setLastName(adminReg.getAdminLname());
            staffDetail.setEnrollment(adminReg.getEnrollment());
            String user_id = adminReg.getUserId();
            System.out.println("user_id = "+user_id);
            login.setUserId(user_id);
            login.setUserName(adminReg.getAdminFname()+" "+ adminReg.getAdminLname());
            
             /*Admin Password Generate*/
                 admin_password= RandomPassword.getRandomString(10);
                 System.out.println(admin_password);
                admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);

            
            login.setPassword(admin_password1);

            //login.setQuestion("@");

            login.setStaffDetail(staffDetail);
            login.setRole("insti-admin");

            institutedao.insert(institute);
            staffDetaildao.insert(staffDetail);
            logindao.insert(login,(String)admin.getUser_id());
            
            adminReg.setStatus("Registered");
            admindao.update(adminReg);
            request.setAttribute("accept_msg1", institute_id);
            request.setAttribute("accept_msg3",adminReg.getInstituteName() );
            String msg=resource.getString("request_accepted_instituteid");
            request.setAttribute("msg", msg +institute_id);
           
List<AdminRegistration> admin1=(List<AdminRegistration>)admindao.getAdminDeatilsByUserId(user_id);
if(admin1.isEmpty()==false && admin1!=null)
{
    AdminRegistration x=admin1.get(0);
     x.setInstituteId(institute_id);
     admindao.update(x);

}
                      
         String path = servlet.getServletContext().getRealPath("/");
            obj=new Email(path,adminReg.getAdminEmail(),admin_password,"Registration Accepted Successfully from EMS","User Id="+adminReg.getUserId()+" Your Password for EMS Login is="+admin_password);
         executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });


      
return mapping.findForward("success");}
      else{
      if(button.equalsIgnoreCase("Reject")){
           List adminList = (List)admindao.getAdminDeatilsById(registration_request_id);
           adminReg = (AdminRegistration)adminList.get(0);
System.out.println("button"+button);
//adminReg.setRegistrationId(registration_request_id);
//adminReg.setAbbreviatedName(admin.getAbbreviated_name());
//adminReg.setAdminDesignation(admin.getAdmin_designation());
//adminReg.setAdminEmail(admin.get);

     adminReg.setStatus("Rejected");
     // adminReg.setWorkingStatus("OK");
      admindao.update(adminReg);
request.setAttribute("reject","Request for Institute Registration is rejected");
request.setAttribute("msg","Request for Institute Registration is rejected");

String path = servlet.getServletContext().getRealPath("/");
      obj=new Email(path,admin.getAdmin_email(),admin_password,"Approval of Institute Registration Request","Dear "+admin.getAdmin_fname()+" "+admin.getAdmin_lname()+"\n Sorry, Your request for Institute registration had not been Approved."+" ,\nWith Regards\nWebAdmin\nEMS");
//obj=new Email(path,adminReg.getAdminEmail(),"admin_password"," request for Institute Registration","Sorry, Your request for Institute registration had not been Approved .\n" ,"\n\nDear "+adminReg.getAdminFname()+" "+adminReg.getAdminLname()+",\n","With Regards\nWebAdmin\nEMS");
         executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });
             return mapping.findForward("success");
      }

      }


    }
        catch(Exception e2)
        {
       System.out.println(e2);
       
        }
 // return mapping.findForward("success");
}
        return mapping.findForward("failure");
    }


}
