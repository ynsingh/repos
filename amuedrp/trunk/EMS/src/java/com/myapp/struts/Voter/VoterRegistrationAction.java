/*
 * VOTER REGISTRATION FROM OUTSIDE LINK
 */

package com.myapp.struts.Voter;

import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.LoginDAO;
import com.myapp.struts.hbm.StaffDetail;
import com.myapp.struts.hbm.StaffDetailId;
import com.myapp.struts.hbm.VoterRegistrationId;
import com.myapp.struts.hbm.VoterRegistration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import  com.myapp.struts.utility.*;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class VoterRegistrationAction extends org.apache.struts.action.Action {
    
  
         private static final String SUCCESS = "success";
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
            throws Exception {

      VoterRegActionForm lf=(VoterRegActionForm)form;
        HttpSession session = request.getSession();
         String button=lf.getButton();
         String id=lf.getEnrollment();
         String eid=lf.getInstitute_id();
         System.out.println("InstituteId="+"---"+eid);

          FormFile v=(FormFile)session.getAttribute("filename");
          byte[] iii=null;
          if(v!=null)iii=v.getFileData();
            String email=lf.getEmail();
       if(button.equals("Submit")||button.equals("Add"))
       {

             VoterRegistration obj=VoterRegistrationDAO.searchVoterRegistration(eid, id);
             if(obj!=null){

                   request.setAttribute("msg", "Sorry Enrollment No Already Exist, Use Another");
                   return mapping.findForward("add");

       }

                 List<VoterRegistration> obj3 =VoterRegistrationDAO.searchVoterMail1(eid,email);
                if(!obj3.isEmpty()){
               request.setAttribute("msg2", "Sorry Mail Id Already Exist, Use Another");
              System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAa");
             return mapping.findForward("back");

       }

             empid.setEnrollment(id);
             empid.setInstituteId(eid);
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
             if (iii!=null)
             {
                 String ext=UserLog.returnextension(v.getFileName());
                 UserLog.writeImage(id+eid+"."+ext, iii);
                 ob.setImage(id+eid+"."+ext);
             }

             ob.setJoiningDate(lf.getJ_date());
             ob.setMName(lf.getM_name());
             ob.setMobileNumber(lf.getM_number());
             ob.setPAddress(lf.getP_add());
             ob.setState(lf.getState());
             ob.setState1(lf.getState1());
             ob.setVoterName(lf.getV_name());
             ob.setYear(lf.getYear());
             ob.setZipCode(lf.getZipcode());
             ob.setZipCode1(lf.getZipcode1());
             ob.setStatus("NOT REGISTERED");
             ob.setAlternateMail(lf.getAlternateemail());
             ob.setId(empid);
             VoterRegistrationDAO.insert(ob);
             request.setAttribute("msg", "request for registration has sent succsessfully");
           return mapping.findForward("add");
       }

        if(button.equals("Update"))

     {
              empid.setEnrollment(id);

     ob=VoterRegistrationDAO.searchVoterRegistration(eid,id);
        empid.setInstituteId(lf.getInstitute_id());

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

            // ob.setImage(lf.getUploadedFile());

             ob.setJoiningDate(lf.getJ_date());
             ob.setMName(lf.getM_name());
             ob.setMobileNumber(lf.getM_number());
             ob.setPAddress(lf.getP_add());
             ob.setState(lf.getState());
             ob.setState1(lf.getState1());
             ob.setVoterName(lf.getV_name());
             ob.setYear(lf.getYear());
             ob.setZipCode(lf.getZipcode());
             ob.setZipCode1(lf.getZipcode1());
             //ob.setStatus(lf.getStatus());

             ob.setId(empid);

          //  if (lf.getImg()!=null)
           // ob.setImage(lf.getImg().getFileData());
           //  else
             //{ if(iii!=null){ob.setImage(iii);}}


        VoterRegistrationDAO.update(ob);
         request.setAttribute("msg", "Record updated  succsessfully");
        return mapping.findForward("addd");
       }
            if(button.equals("Delete"))
            {
                 VoterRegistrationDAO.delete(id,lf.getInstitute_id());
                 request.setAttribute("msg", "record has deleted  successfully");
                 return mapping.findForward("add");
            }

         if(button.equals("Accept"))
         {

             VoterRegistrationDAO voterdao=new VoterRegistrationDAO();
             List vr=voterdao.getVoterDetailsByStatus1(lf.getInstitute_id());
             if(!vr.isEmpty())
          {
                 for(int g=0;g<vr.size();g++)
                {
                          ob=(VoterRegistration)vr.get(g);
                           if(!ob.getId().getEnrollment().equalsIgnoreCase(lf.getEnrollment()))
                             continue;
                           admin_password= RandomPassword.getRandomString(10);
                           admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
                           userid=ob.getId().getEnrollment()+""+ob.getId().getInstituteId();
                           login.setUserId(ob.getEmail());
                           login.setPassword(admin_password1);
                           login.setRole("voter");
                           login.setUserName(ob.getVoterName());
                           staffid.setInstituteId(lf.getInstitute_id());
                           staffid.setStaffId(lf.getEnrollment());
                           staffd.setId(staffid);
                           login.setStaffDetail(staffd);
                           logindao.insert(login);
                           ob.setStatus("REGISTERED");
                           VoterRegistrationDAO.update(ob);
                           break;
               }
     }
         
            obj=new Email(ob.getEmail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+lf.getV_name()+"\n You are Registered as a Voter with given User Id="+userid +" , Password for EMS Login ="+admin_password+".\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
            executor.submit(new Runnable() {

                    public void run() {
                    obj.send();
                }
            });
              request.setAttribute("msg", "Voter Request of Enrollment No "+ob.getId().getEnrollment()+" Successfully Accepted");
              return mapping.findForward("accept");
         }

              return mapping.findForward("add");
    }
          }
         
    
    
