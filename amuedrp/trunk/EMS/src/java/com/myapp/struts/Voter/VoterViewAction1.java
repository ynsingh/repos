/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;
import com.myapp.struts.AdminDAO.LoginDAO;
import com.myapp.struts.hbm.InstituteDAO;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.SetVoter;
import com.myapp.struts.hbm.VoterRegistrationId;
import com.myapp.struts.hbm.VoterRegistration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 *
 * @author akhtar
 */
public class VoterViewAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
   private VoterRegistration ob=new VoterRegistration();

private VoterRegistrationId elid=new VoterRegistrationId();
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception

    {
         HttpSession session=request.getSession();
         
         String btt= request.getParameter("bt");
         System.out.println("button is "+btt);
         if(btt!=null)
         {
            System.out.println("voter delete is in processsssssssssss");
           String vid=(String) request.getParameter("id");
           String institute_id=(String)session.getAttribute("institute_id");
           VoterRegistration v=VoterRegistrationDAO.searchVoterRegistration(institute_id,vid);
           List <SetVoter> sv=VoterRegistrationDAO.ListofSetVoter1(institute_id,vid);
           
           if(!sv.isEmpty())
           {
               session.setAttribute("msg1", "You cannot delete this Voter");
               
           return mapping.findForward("delete");
           }
           else
           {
               try{
                  LoginDAO lg=new LoginDAO();
                  lg.delete(v.getEmail());
                  
                  VoterRegistrationDAO vr= new VoterRegistrationDAO();
                  
                  vr.delete(v);
              
              session.setAttribute("msg", "Voter Deleted Successfully");
               return mapping.findForward("delete");
               }
               catch(Exception er)
               {
                   return mapping.findForward("delete");
               }
           }
           
            
         }
         //else{
         
        VoterRegActionForm employeeform=(VoterRegActionForm)form;
         String button="View";

        String id="id";
         id=request.getParameter(id);
	if(id==null)
		id=(String)session.getAttribute("voter_id");
          String institute_id=(String)session.getAttribute("institute_id");
        
        VoterRegistration l=VoterRegistrationDAO.searchVoterRegistration(institute_id,id);
        
   System.out.println("voter delete is not process");
       if(button.equals("View"))
        {
            System.out.println("View Page");
           
            session.setAttribute("voter", l);

          if(l!=null){


        InstituteDAO insti= new InstituteDAO();
        String status="OK";
        List Institute = insti.getInstituteNameByStatus(status);
        System.out.println( "instituteList"+""+Institute.size());
        session.setAttribute("instituteList",Institute);
        session.setAttribute("Institute",Institute);


        Login login=LoginDAO.searchRole(l.getId().getEnrollment(), institute_id);

        if(login!=null)
            request.setAttribute("UserID", login.getUserId());

             employeeform.setB_date(l.getBirthdate());
             employeeform.setC_add(l.getCAddress());
              employeeform.setCity(l.getCity());
               employeeform.setCity1(l.getCity1());
                employeeform.setCountry(l.getCountry());
                employeeform.setCountry1(l.getCountry1());
            employeeform.setCourse(l.getCourse());
                 employeeform.setDuration(l.getCourseDuration());
                 employeeform.setSession(l.getCurrentSession());


            employeeform.setDepartment(l.getDepartment());
            System.out.println("emailId="+l.getEmail());
            employeeform.setEmail(l.getEmail());
             // employeeform.setFilename;
               employeeform.setF_name(l.getFName());
                employeeform.setGender(l.getGender());
            //   employeeform.setImage(l.getImage());
            employeeform.setJ_date(l.getJoiningDate());
                 employeeform.setM_name(l.getMName());
                 employeeform.setM_number(l.getMobileNumber());

                employeeform.setP_add(l.getPAddress());
            employeeform.setState(l.getState());
                 employeeform.setState1(l.getState1());
               //employeeform.setUploadedFile(l.getImage());

                employeeform.setV_name(l.getVoterName());
                 employeeform.setYear(l.getYear());
                 employeeform.setZipcode(l.getZipCode());
                 employeeform.setZipcode1(l.getZipCode1());
                 

             employeeform.setEnrollment(l.getId().getEnrollment());
            employeeform.setInstitute_id(l.getId().getInstituteId());
            employeeform.setAlternateemail(l.getAlternateMail());

            if(request.getParameter("status")!=null && request.getParameter("status").equalsIgnoreCase("AB"))
            {button = "AB";}
            if(request.getParameter("status")!=null && request.getParameter("status").equalsIgnoreCase("B"))
            {button = "Block1";}


            request.setAttribute("button", button);

//             if(session.getAttribute("voter_id")!=null)
//            {
//                  return mapping.findForward("add1");
//
//            }
//                if(btt!=null)
//                {
//
//                }

            return mapping.findForward("add");
                       }
           




          return mapping.findForward("add");
       }
        // }
        return mapping.findForward("duplicate");
    }

}

