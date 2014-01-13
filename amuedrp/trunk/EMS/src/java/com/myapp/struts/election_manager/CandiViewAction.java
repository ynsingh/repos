/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.Candidate.CandidateRegActionForm;
import com.myapp.struts.Candidate.CandidateRegistrationDAO;
import com.myapp.struts.hbm.Candidate1;
import com.myapp.struts.hbm.CandidateRegistration;
import com.myapp.struts.hbm.CandidateRegistrationId;
import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionCriteria;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.InstituteDAO;
import com.myapp.struts.hbm.Position1;
import com.myapp.struts.hbm.PositionDAO;
import com.myapp.struts.hbm.VoterRegistration;
import com.myapp.struts.hbm.VoterRegistrationId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Edrp-04
 */
public class CandiViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     private VoterRegistration ob=new VoterRegistration();
     private CandidateRegistration ob1= new CandidateRegistration();
     private CandidateRegistrationId canid= new CandidateRegistrationId();
     PositionDAO p1=new PositionDAO();
     ElectionDAO ED= new ElectionDAO();

private VoterRegistrationId elid=new VoterRegistrationId();
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        InstituteDAO insti= new InstituteDAO();
        String status="OK";
        List Institute = insti.getInstituteNameByStatus(status);
        System.out.println( "InstituteList"+""+Institute.size());
        session.setAttribute("Institute",Institute);

         CandidateRegActionForm employeeform=(CandidateRegActionForm)form;
         String button="add";
         String stat = request.getParameter("status");
         String eleid = request.getParameter("eid");
          String  id="id";
          id=request.getParameter(id);
          String position = request.getParameter("pos");
           String eid=(String)session.getAttribute("institute_id");
           String va=request.getParameter("va");
           String institute_id=(String)session.getAttribute("institute_id");
           if(va!=null)
           {
               System.out.println("i am in ifff");
               Candidate1 c=CandidateRegistrationDAO.searchcandidateMenifesto(institute_id,id,eleid,position);
               CandidateRegistration can=CandidateRegistrationDAO.searchCandidateRegistration(eid,id,position);
               List <Election> el=ElectionDAO.ElectionID(eleid, institute_id);
               Calendar cal = Calendar.getInstance();
               Date d = cal.getTime();
               
               
               if( el.get(0).getWithdrawlEndDate().after(d))
            {
         
               
           

            can.setStatus("Withdraw");
            
            CandidateRegistrationDAO.update(can);
            CandidateRegistrationDAO cd=new CandidateRegistrationDAO();
               cd.delete(c);
               System.out.println("candidate name  "+c.getCandidateName());
            request.setAttribute("msg", "Candidate Withdrawal Successful");
            }
            else
            {
                   System.out.println("candidate name in else part "+c.getCandidateName());
                request.setAttribute("msg1", "You can not delete this candidate, withdraw time expire at  "+el.get(0).getWithdrawlEndDate());

            }


              
                
                return mapping.findForward("delete");
           }
           else{
          System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR  "+id+eid+position);

        VoterRegistration r=CandidateRegistrationDAO.searchVoterRegistration(eid,id);
        CandidateRegistration c=CandidateRegistrationDAO.searchCandidateRegistration(eid,id,position);
        List<ElectionCriteria> obj= (List<ElectionCriteria>)CandidateRegistrationDAO.GetRuleDetails(eid,id,eleid,Integer.parseInt(position));
       System.out.println("AAAAAAAAAAAAAAAAAAa"+obj.size());
       session.setAttribute("rule",obj);
        Candidate1 candi=new Candidate1();

        
        //Position1 p= p1.searchPosition(Integer.parseInt(c.getPosition()));


List<Candidate1> c1=p1.ElectionId(Integer.parseInt(c.getId().getPosition()), eid)   ;



 String electionid=c.getId().getElectionId();

Election e=ED.Electionname(eid,electionid);
String Elec="";
if(e!=null)
    Elec=e.getElectionName();



        Position1 p=p1.searchPosition1(Integer.parseInt(c.getId().getPosition()),electionid, eid);


        

        System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+c.getId().getPosition()+electionid+" "+eid);
        if(button.equals("add"))
        {
            /* if(l!=null){

            request.setAttribute("msg1", "your request for candidate registration for Enrollment: "+id+" has been already sent");
            return mapping.findForward("duplicate");
                       }
else{
                employeeform.setEnrollment(id);
                employeeform.setInstitute_id(eid);
            request.setAttribute("button", button);
            return mapping.findForward("add");
                }
        }*/
System.out.println("View Page");
          
            session.setAttribute("voter", r);
            

            if(r!=null){
              
            
                employeeform.setB_date(r.getBirthdate());
             employeeform.setC_add(r.getCAddress());
              employeeform.setCity(r.getCity());
               employeeform.setCity1(r.getCity1());
                employeeform.setCountry(r.getCountry());
                employeeform.setCountry1(r.getCountry1());
            employeeform.setCourse(r.getCourse());
                 employeeform.setDuration(r.getCourseDuration());
                 employeeform.setSession(r.getCurrentSession());


            employeeform.setDepartment(r.getDepartment());
             employeeform.setEmail(r.getEmail());

               employeeform.setF_name(r.getFName());
                employeeform.setGender(r.getGender());

            employeeform.setJ_date(r.getJoiningDate());
                 employeeform.setM_name(r.getMName());
                 employeeform.setM_number(r.getMobileNumber());
employeeform.setSecondedBy(c.getSecondedBy());
employeeform.setProposedBy(c.getProposedBy());
employeeform.setPositionAccepted(c.getPositionAccepted());
employeeform.setRequestDate(c.getRequestDate());
                employeeform.setP_add(r.getPAddress());
            employeeform.setState(r.getState());
                 employeeform.setState1(r.getState1());


                employeeform.setV_name(r.getVoterName());
           employeeform.setYear(r.getYear());
                 employeeform.setZipcode(r.getZipCode());
                 employeeform.setZipcode1(r.getZipCode1());

             employeeform.setEnrollment(r.getId().getEnrollment());
            employeeform.setInstitute_id(r.getId().getInstituteId());
            employeeform.setAlternateemail(r.getAlternateMail());
            employeeform.setPositionAccepted(c.getPositionAccepted());
            employeeform.setProposedBy(c.getProposedBy());
            employeeform.setSecondedBy(c.getSecondedBy());

//            employeeform.setEnrolled_in(c.getEnrolledIn());
//            employeeform.setP_marks(c.getPMarks());
//            employeeform.setP_attendence(c.getPAttendence());
//
//            employeeform.setBacklog(c.getBacklog());
//            employeeform.setCriminal(c.getCriminal());
//            employeeform.setIndisc(c.getIndisc());
            if(p!=null)
                employeeform.setPosition(p.getPositionName());
            
 //System.out.println(e.getElectionName());
            employeeform.setElections(Elec);

           
System.out.println(stat+"....................");
            request.setAttribute("button", button);
            request.setAttribute("status",c.getStatus());
           if(stat.equalsIgnoreCase("NR"))
               return mapping.findForward("accept");
           else if(stat.equalsIgnoreCase("R"))
               return mapping.findForward("success1");
           else if(stat.equalsIgnoreCase("A"))
             return mapping.findForward("success2");
           else if(stat.isEmpty()==true)
                return mapping.findForward("success3");
           else
                return mapping.findForward("success");
                       }
            else{
            request.setAttribute("msg1", "plz register yourself as a voter first ");
            return mapping.findForward("duplicate");
                }

        }
   return mapping.findForward("failure");
    }
    }

}
