/*
 * SEND VOTER REQUEST FROM LOGIN PAGE
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.Voter.VoterRegActionForm;
import com.myapp.struts.Voter.VoterRegistrationDAO;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.LoginDAO;
import com.myapp.struts.hbm.StaffDetail;
import com.myapp.struts.hbm.StaffDetailDAO;
import com.myapp.struts.hbm.StaffDetailId;
import com.myapp.struts.hbm.VoterRegistrationId;
import com.myapp.struts.hbm.VoterRegistration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import  com.myapp.struts.utility.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddVoterRegistrationAction extends org.apache.struts.action.Action {
    
     private VoterRegistration ob=new VoterRegistration();
     private LoginDAO logindao = new LoginDAO();
     private Login login =new Login();
     private StaffDetail staffd =new StaffDetail();
     private StaffDetailId staffid =new StaffDetailId();
     private final ExecutorService executor=Executors.newFixedThreadPool(1);
     Email obj;
     private String admin_password;
     private String admin_password1;
     String userid;
     VoterRegistrationId empid=new VoterRegistrationId ();
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {

        VoterRegActionForm lf=(VoterRegActionForm)form;
        HttpSession session = request.getSession();
        String button="Submit";
        String button1 = (String)request.getParameter("button");
        String st = (String)request.getParameter("status");
         if (button1!=null)
             button = button1;
         String id=lf.getEnrollment();
         FormFile v=(FormFile)session.getAttribute("filename");
           byte[] iii=null;
           if(v!=null)iii=v.getFileData();

        String instituteid=(String)session.getAttribute("institute_id");
        userid=lf.getEnrollment()+""+instituteid;
        String email=lf.getEmail();

        if(button.equals("Submit"))
        {
              VoterRegistration obj1=VoterRegistrationDAO.searchVoterRegistration(instituteid, id);
               if(obj1!=null)
               {
                   request.setAttribute("msgerr", "Sorry Enrollment No Already Exist, Use Another");
                    return mapping.findForward("success1");
               }
               List<VoterRegistration> obj3 =VoterRegistrationDAO.searchVoterMail1(instituteid,email);
              if(!obj3.isEmpty())
              {
                 request.setAttribute("msgerr1", "Sorry Mail Id Already Exist, Use Another");
                 return mapping.findForward("success1");
               }
               
               //code added on 4 Feb 2014
               LoginDAO lgdao=new LoginDAO();
               Login login1 =lgdao.getUserId(email);
              if(login1!=null)
              {
                 request.setAttribute("msgerr1", "Sorry Mail Id Already Exist, Use Another");
                 return mapping.findForward("success1");
               }
               //code added on 4 Feb 2014
               
              StaffDetail obj2=StaffDetailDAO.searchStaffId(id,instituteid);
               if(obj2!=null)
               {
               //Add CEO/EO as Voter
                admin_password= RandomPassword.getRandomString(10);
                login=logindao.getStaffDetails1(obj2.getEnrollment(), instituteid);
                if(login!=null)
                {
                    if(login.getRole().endsWith("voter")==false)
                    {
                        login.setRole(login.getRole()+",voter");
                        logindao.update(login);
                         String mailbody=UserLog.readProperty("mail.properties", userid+"em");
                         if(mailbody.isEmpty())
                            mailbody="\n\n You are Registered as a Voter \n\nWith Regards\n\nElection Manager\n";
                            obj=new Email(lf.getEmail(),"","Registration Accepted Successfully from EMS","+mailbody+"+session.getAttribute("institute_name"));
                            executor.submit(new Runnable()
                            {
                                public void run()
                                {
                                    obj.send();
                                  }
                             });
                           request.setAttribute("msg", "Voter Successfully Added");
                           return mapping.findForward("success1");

                    }
                    else
                    {
                        request.setAttribute("msgerr", "Sorry Entered User Already Registered As a Voter");
                        return mapping.findForward("success1");
                     }
                    }
                }

            empid.setEnrollment(id);
            empid.setInstituteId(instituteid);
            ob.setBirthdate(lf.getB_date());
            ob.setCAddress(lf.getC_add());
            ob.setCity(lf.getCity());
            ob.setCity1(lf.getCity1());
            ob.setCountry(lf.getCountry());
            ob.setCountry1(lf.getCountry1());
            ob.setCourse(lf.getCourse());
            ob.setCourseDuration(lf.getDuration());
            ob.setCurrentSession(lf.getSession());
            ob.setDepartment(lf.getDepartment());
            ob.setEmail(lf.getEmail());
            ob.setFName(lf.getF_name());
            ob.setGender(lf.getGender());
            ob.setJoiningDate(lf.getJ_date());
            ob.setMName(lf.getM_name());
             if(lf.getM_number().isEmpty()==true)
                  ob.setMobileNumber("0");
             else
             ob.setMobileNumber(lf.getM_number());
             ob.setPAddress(lf.getP_add());
             ob.setState(lf.getState());
             ob.setState1(lf.getState1());
             ob.setVoterName(lf.getV_name());
             ob.setYear(lf.getYear());
             ob.setZipCode(lf.getZipcode());
             ob.setZipCode1(lf.getZipcode1());
             ob.setStatus("REGISTERED");
             ob.setId(empid);
             ob.setAlternateMail(lf.getAlternateemail());
               if (iii!=null)
               {
                  String ext=UserLog.returnextension(v.getFileName());
                  UserLog.writeImage1(id+instituteid+"."+ext, iii);
                  ob.setImage(id+instituteid+"."+ext);
               }
                admin_password= RandomPassword.getRandomString(10);
                admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
                login.setUserId(lf.getEmail());
                login.setPassword(admin_password1);
                login.setRole("voter");
                login.setUserName(lf.getV_name());
                staffid.setInstituteId(instituteid);
                staffid.setStaffId(lf.getEnrollment());
                staffd.setId(staffid);
                login.setStaffDetail(staffd);
                VoterRegistrationDAO.insert(ob);
                logindao.insert(login);
                request.setAttribute("msg", "Voter Successfully Added");
                obj=new Email(lf.getEmail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+lf.getV_name()+"+mailbody+"+userid +" , Password for EMS Login "+admin_password+".\n\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
                executor.submit(new Runnable() {
                        public void run() {
                        obj.send();
                    }
                    });
        
       }
        else if(button.equalsIgnoreCase("Change Status") || button.equalsIgnoreCase("UnBlock"))
        {
            ob = VoterRegistrationDAO.searchVoterRegistration(instituteid, lf.getEnrollment());
            if(button.equalsIgnoreCase("UnBlock"))
                ob.setStatus("REGISTERED");
            else
                ob.setStatus(lf.getStatus());
            request.setAttribute("msg", "Voter With ID"+lf.getEnrollment() +" Successfully "+lf.getStatus());
            VoterRegistrationDAO.update(ob);
            obj=new Email(lf.getEmail(),admin_password,"Voter Account Status Changed with "+lf.getStatus(),"User Id "+userid);
             executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
                });
            }
          if(button.equalsIgnoreCase("UnBlock"))
              return mapping.findForward("success2");
           else
              return mapping.findForward("success");
    }
}
