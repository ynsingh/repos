/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.Voter.VoterRegActionForm;
import com.myapp.struts.Voter.VoterRegistrationDAO;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Edrp-04
 */
public class AddVoterRegistrationAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
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
         VoterRegActionForm lf=(VoterRegActionForm)form;
        HttpSession session = request.getSession();
         String button="Submit";
         String button1 = (String)request.getParameter("button");
         if (button1.equalsIgnoreCase("block") || button1.equalsIgnoreCase("Unblock")) button = button1;
         String id=lf.getEnrollment();
System.out.println(button+" "+id);
          FormFile v=(FormFile)session.getAttribute("filename");
       byte[] iii=null;
       if(v!=null)iii=v.getFileData();

       String instituteid=(String)session.getAttribute("institute_id");



        if(button.equals("Submit"))
       {
         empid.setEnrollment(id);

System.out.println(id+" "+instituteid);
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

             ob.setImage(lf.getUploadedFile());

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
             ob.setStatus("REGISTERED");
             ob.setId(empid);
            if (lf.getImg()!=null)
            ob.setImage(lf.getImg().getFileData());
         else
               if(iii!=null){ob.setImage(iii);}
               else{ob.setImage(null);}

 admin_password= RandomPassword.getRandomString(10);
                 System.out.println(admin_password);
                admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);

System.out.println(admin_password1);

 userid=lf.getEnrollment()+""+instituteid;
                login.setUserId(userid);
login.setPassword(admin_password1);
login.setRole("voter");
login.setUserName(lf.getV_name());
//login.getStaffDetail().getId().setInstituteId(ob.getId().getInstituteId());
staffid.setInstituteId(instituteid);
staffid.setStaffId(lf.getEnrollment());
staffd.setId(staffid);

login.setStaffDetail(staffd);


VoterRegistrationDAO.insert(ob);
logindao.insert(login, userid);
request.setAttribute("msg", "Voter Successfully Added");
String path = servlet.getServletContext().getRealPath("/");
        obj=new Email(path,lf.getEmail(),admin_password,"Registration Accepted Successfully from EMS","User Id="+userid +" Your Password for EMS Login is="+admin_password);
         executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });
         
// request.setAttribute("msg1", "Voter Successfully Added");
       }
        else if(button.equalsIgnoreCase("Block") || button1.equalsIgnoreCase("Unblock"))
        {
            ob = VoterRegistrationDAO.searchVoterRegistration(instituteid, lf.getEnrollment());
            if(button1.equalsIgnoreCase("block"))
                ob.setStatus("Block");
            else if(button1.equalsIgnoreCase("Unblock"))
                ob.setStatus("REGISTERED");
//            List<Login> login1 = logindao.getUser(lf.getEnrollment()+""+instituteid);
//            if(login1!=null)
//            {
//                login = login1.get(0);
//
//            }
            VoterRegistrationDAO.update(ob);
            String path = servlet.getServletContext().getRealPath("/");
        obj=new Email(path,lf.getEmail(),admin_password,"Voter Account Blocked from EMS","User Id="+userid);
         executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });
            }
        


        return mapping.findForward(SUCCESS);
    }
}
