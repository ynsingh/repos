/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.institute_admin;


import com.myapp.struts.utility.PasswordEncruptionUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import  com.myapp.struts.utility.*;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.*;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Edrp-04
 */
public class Election_Manager_RegistrationAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
     private final ExecutorService executor=Executors.newFixedThreadPool(1);
    Email obj;
  
    private static final String SUCCESS = "success";
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
    private String admin_password;
    private String admin_password1;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Election_Manager_RegistrationActionForm ManagerRegistrationForm=(Election_Manager_RegistrationActionForm)form;
        first_name=ManagerRegistrationForm.getFirst_name();
        last_name=ManagerRegistrationForm.getLast_name();
        address1=ManagerRegistrationForm.getAddress1();
        city1=ManagerRegistrationForm.getCity1();
        state1=ManagerRegistrationForm.getState1();
        country1=ManagerRegistrationForm.getCountry1();
        gender=ManagerRegistrationForm.getGender();
        contact_no=ManagerRegistrationForm.getContact_no();
        mobile_no=ManagerRegistrationForm.getMobile_no();
        department=ManagerRegistrationForm.getDepartment();
        staff_id=ManagerRegistrationForm.getStaff_id();
        manager_id=ManagerRegistrationForm.getManager_id();
        institute_id=ManagerRegistrationForm.getInstitute_id();
        user_id=ManagerRegistrationForm.getUser_id();
       // password=ManagerRegistrationForm.getPassword();
        zip1=ManagerRegistrationForm.getZip1();
        //repassword=ManagerRegistrationForm.getRepassword();
        email_id=ManagerRegistrationForm.getEmail_id();
        
        try{
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
    



         AdminRegistrationDAO admindao = new AdminRegistrationDAO();
        ElectionManagerDAO electionmanagerdao=new ElectionManagerDAO();
        StaffDetailDAO staffdetaildao=new StaffDetailDAO();
        LoginDAO logindao= new LoginDAO();
            //StaffManagerDAO staffmanagerdao=new StaffManagerDAO();
        
        
       StaffDetail staffdetail=new StaffDetail();
        ElectionManager electionmanager=new ElectionManager();
        ElectionManagerId electionprimary=new ElectionManagerId();
        StaffDetailId staffdetailprimary=new StaffDetailId();
        Login login =new Login();

      
      // List rs1=logindao.getStaffDetails(staff_id, institute_id);
      // List rs2=electionmanagerdao.getStaffDetails(staff_id, institute_id);



      
      //Iterator i1= rs1.iterator();
      //Iterator i2=rs2.iterator();

       List rs=admindao.getAdminDeatilsByUserId(user_id);
       List rs1=logindao.getUser(user_id);
       List rs2=electionmanagerdao.getUserId(user_id);
       List rs3=staffdetaildao.getStaffDetails(staff_id, institute_id);
       List rs4=logindao.getStaffDetails(staff_id, institute_id);
       List rs5=electionmanagerdao.getStaffDetails(staff_id, institute_id);
       List rs6=electionmanagerdao.ManagerDeatils(manager_id, institute_id);
      // List rs2=MyQueryResult.getMyExecuteQuery("select * from staff_detail where emai_id='"+admin_email+"'");
       Iterator it = rs.iterator();
       Iterator it1 = rs1.iterator();
       Iterator it2 = rs2.iterator();
       Iterator it3 = rs3.iterator();
       Iterator it4 = rs4.iterator();
       Iterator it5=rs5.iterator();
       Iterator it6= rs6.iterator();
       //while()
       if(it.hasNext() || it1.hasNext() || it2.hasNext() )
       {
String msg1=resource.getString("duplicate_user_id");
            request.setAttribute("msg1", msg1);
            return mapping.findForward("failure");
       }

       else if(it3.hasNext() || it4.hasNext() || it5.hasNext())
       {
           String msg2=resource.getString("duplicate_staff_id");
         request.setAttribute("msg2", msg2);
         return mapping.findForward("failure");
       }
       else if(it6.hasNext())
       {
           String msg3=resource.getString("duplicate_manager_id");
           request.setAttribute("msg3", msg3);
           return mapping.findForward("failure");
       }

        else{



        //Election_Manager_StaffDetail ems = new Election_Manager_StaffDetail();

        /*ems.getElectionManager().getId().setInstituteId(institute_id);
        ems.getElectionManager().getId().setManagerId(manager_id);
        ems.getElectionManager().setDepartment(department);
        ems.getElectionManager().setStaffId(staff_id);
        ems.getElectionManager().setStatus("ok");
        ems.getElectionManager().setUserId(user_id);
        ems.getStaffDetail().getId().setInstituteId(institute_id);
        ems.getStaffDetail().getId().setStaffId(staff_id);
        ems.getStaffDetail().setFirstName(first_name);
        ems.getStaffDetail().setLastName(last_name);
        ems.getStaffDetail().setAddress1(address1);
        ems.getStaffDetail().setCity1(city1);
        ems.getStaffDetail().setState1(state1);
        ems.getStaffDetail().setCountry1(country1);
        ems.getStaffDetail().setContactNo(contact_no);
        ems.getStaffDetail().setMobileNo(mobile_no);
        ems.getStaffDetail().setGender(gender);
        ems.getStaffDetail().setZip1(zip1);
        ems.getStaffDetail().setEmailId(email_id);
        ems.getLogin().setUserId(user_id);
        ems.getLogin().setPassword(password);
        ems.getLogin().setRole("Election Manager");
        ems.getLogin().setStaffDetail(staffdetail);*/

        electionprimary.setInstituteId(institute_id);
        electionprimary.setManagerId(manager_id);

        electionmanager.setDepartment(department);
        electionmanager.setStaffId(staff_id);
        electionmanager.setStatus("OK");
        electionmanager.setUserId(user_id);
        electionmanager.setId(electionprimary);

        staffdetailprimary.setInstituteId(institute_id);
        staffdetailprimary.setStaffId(staff_id);

        staffdetail.setFirstName(first_name);
        staffdetail.setLastName(last_name);
        staffdetail.setAddress1(address1);
        staffdetail.setCity1(city1);
        staffdetail.setCountry1(country1);
        staffdetail.setState1(state1);
        staffdetail.setZip1(zip1);
        staffdetail.setGender(gender);
        staffdetail.setContactNo(contact_no);
        staffdetail.setMobileNo(mobile_no);
        staffdetail.setEmailId(email_id);
        staffdetail.setEnrollment(staff_id);

        staffdetail.setId(staffdetailprimary);


        login.setUserId(user_id);

         /*Admin Password Generate*/
                 admin_password= RandomPassword.getRandomString(10);
                 System.out.println(admin_password);
                admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);



         login.setPassword(admin_password1);



        login.setRole("Election Manager");
        login.setUserName(staffdetail.getFirstName()+" "+staffdetail.getLastName());
        login.setStaffDetail(staffdetail);




        System.out.println("Institute_id="+electionmanager.getId().getInstituteId()+" manager_id="+electionmanager.getId().getManagerId());


        
        //staffmanagerdao.insert(ems);

        staffdetaildao.insert(staffdetail);
        logindao.insert(login, user_id);
         electionmanagerdao.insert(electionmanager);
         String msg=resource.getString("record_inserted_successfully");
         request.setAttribute("msg",msg);
       String path = servlet.getServletContext().getRealPath("/");

       FileInputStream in = new FileInputStream(path+"/mail.properties");
  			Properties	pro = new Properties();
                                 pro.load(in);


				Enumeration keys = pro.keys();
                                int i=0;
                                String mailbody="";
				while (keys.hasMoreElements())
				{
                                       String key=(String)keys.nextElement();

                                       if(key.equalsIgnoreCase(user_id+"em")){
                                       mailbody=(String)pro.get(key);
                                       }




                                   i++;
				}
				in.close();



       if(mailbody=="")
       mailbody="\n You are Registered as a Election Manager with given following details\n";

         obj=new Email(path,email_id,admin_password,"Registration Accepted Successfully from EMS"," Dear "+login.getUserName()+mailbody+"user_id="+user_id +" , Password for EMS Login ="+admin_password+".\nWith Regards\nInstitute Admin\n"+session.getAttribute("institute_name")+"\nElectionMS");
         System.out.println(email_id+institute_id+admin_password);
         executor.submit(new Runnable() {

                public void run() {

                    obj.send();
                }
            });
        return mapping.findForward(SUCCESS);
        }
        }
        catch(HibernateException e)
        {
throw e;
    }
}
}
