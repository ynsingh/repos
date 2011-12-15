/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Candidate;

import com.myapp.struts.AdminDAO.LoginDAO;
import com.myapp.struts.hbm.Candidate1;
import com.myapp.struts.hbm.Candidate1Id;
import com.myapp.struts.hbm.CandidateRegistration;
import com.myapp.struts.hbm.CandidateRegistrationId;
import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.ElectionManagerDAO;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.Position1;
import com.myapp.struts.hbm.PositionDAO;
import com.myapp.struts.hbm.StaffDetail;
import com.myapp.struts.hbm.StaffDetailId;
import com.myapp.struts.hbm.VoterRegistration;
import com.myapp.struts.utility.Email;
import com.myapp.struts.utility.PasswordEncruptionUtility;
import com.myapp.struts.utility.RandomPassword;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRExporterParameter;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.struts.upload.FormFile;


/**
 *
 * @author akhtar
 */
public class CandidateRegistrationAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
     private static final String SUCCESS = "success";
     private CandidateRegistration ob=new CandidateRegistration();
     VoterRegistration ab= new VoterRegistration();

     Candidate1 pos = new Candidate1();
            Candidate1Id pos1 = new Candidate1Id();
            PositionDAO positiondao = new PositionDAO();

            private String admin_password;
    private String admin_password1;
    String userid;
     private final ExecutorService executor=Executors.newFixedThreadPool(1);
    Email obj;

CandidateRegistrationId empid=new CandidateRegistrationId ();

private LoginDAO logindao = new LoginDAO();
     private Login login =new Login();
      private StaffDetail staffd =new StaffDetail();
      private StaffDetailId staffid =new StaffDetailId();

  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

      CandidateRegActionForm lf=(CandidateRegActionForm)form;
  
      HttpSession session = request.getSession();
         String button=lf.getButton();
         String id=lf.getEnrollment();
         String eid=lf.getInstitute_id();
         String position = lf.getPosition();
         String election = lf.getElections();

        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"+button+lf.getElections()+lf.getInstitute_id());

        String elec_id=null;

        Election eleobj=ElectionDAO.searchElectionByName(lf.getElections(), eid);
        if(eleobj!=null)
            elec_id=eleobj.getId().getElectionId();
System.out.println("...................."+elec_id);


         FormFile v=(FormFile)session.getAttribute("filename");
       byte[] iii=null;
       if(v!=null)iii=v.getFileData();








       if(button.equals("SUBMIT"))
       {
//System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"+lf.getCriminal());
           empid.setEnrollment(id);
         empid.setInstituteId(eid);
         empid.setElectionId(elec_id);
        
      

        ob.setBacklog(lf.getBacklog());
         ob.setCriminal(lf.getCriminal());
          ob.setEnrolledIn(lf.getEnrolled_in());
           ob.setIndisc(lf.getIndisc());
            ob.setPAttendence(lf.getP_attendence());
             ob.setPMarks(lf.getP_marks());

             ob.setPosition(lf.getPosition());
            ob.setStatus("not registered");
            ob.setId(empid);


         pos1.setElectionId(lf.getElections());
         pos1.setInstituteId(lf.getInstitute_id());
       pos1.setPositionId(Integer.parseInt(lf.getPosition()));
       // PROBLEM-------- pos1.setCandidateId(Integer.parseInt(lf.getEnrollment()));
         pos.setCandidateName(lf.getV_name());
         pos.setId(pos1);




        CandidateRegistrationDAO.insert(ob);
        positiondao.insertcandidate(pos);
         request.setAttribute("registration_msg", "request for candidature has been sent successfully");
        return mapping.findForward("add");
       }

if(button.equals("Reject"))
         {
String reason = lf.getReason();
             CandidateRegistrationDAO voterdao=new CandidateRegistrationDAO();
             Election e = ElectionDAO.searchElectionByName(election, eid);
          List vr=voterdao.getCandidateDetailsByStatus1(lf.getInstitute_id(),e.getId().getElectionId());
          List ve=voterdao.getEmail(lf.getEnrollment());
//
//          System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+vr+lf.getInstitute_id());
          if(!vr.isEmpty())

              ob=(CandidateRegistration)vr.get(0);
          ab=(VoterRegistration)ve.get(0);



//            admin_password= RandomPassword.getRandomString(10);
//                 System.out.println(admin_password);
//                admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
//
//System.out.println(admin_password1);
//
//String can="can";
//
//userid=ob.getId().getEnrollment()+""+ob.getId().getInstituteId()+""+ob.getId().getEnrollment();
//                login.setUserId(userid);
//login.setPassword(admin_password1);
//login.setRole("candidate");
//
////login.getStaffDetail().getId().setInstituteId(ob.getId().getInstituteId());
//staffid.setInstituteId(lf.getInstitute_id());
//staffid.setStaffId(lf.getEnrollment());
//staffd.setId(staffid);
//
//login.setStaffDetail(staffd);
//logindao.insert(login,userid);


 ob.setStatus("Rejected");
CandidateRegistrationDAO.update(ob);
String path = servlet.getServletContext().getRealPath("/");
          obj=new Email(path,ab.getEmail(),admin_password,"Candidature Request Rejected from EMS","Dear "+lf.getV_name()+"\nCandidature Request Rejected by Election Manager \n Reason:"+reason+".\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
         executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });
             return mapping.findForward(SUCCESS);
         }
          if(button.equals("Accept"))
         {
System.out.println("Accept is working");
             CandidateRegistrationDAO voterdao=new CandidateRegistrationDAO();
             
              System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+eid);
             Election e = ElectionDAO.searchElectionByName(election, eid);
               
          List vr=voterdao.getCandidateDetailsByStatus1(eid,e.getId().getElectionId(),lf.getEnrollment());
          List ve=voterdao.getEmail(lf.getEnrollment());

       
          if(!vr.isEmpty())

              ob=(CandidateRegistration)vr.get(0);
          ab=(VoterRegistration)ve.get(0);



Candidate1 c1 = new Candidate1();
Candidate1Id c1Id = new Candidate1Id();
c1Id.setElectionId(ob.getId().getElectionId());
c1Id.setInstituteId(ob.getId().getInstituteId());
c1Id.setPositionId(Integer.valueOf(ob.getPosition()));
c1.setId(c1Id);
c1.setCandidateName(ab.getVoterName());
c1.setEnrollment(ob.getId().getEnrollment());
            admin_password= RandomPassword.getRandomString(10);
                 System.out.println(admin_password);
                admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);

System.out.println(admin_password1);

String can="can";

userid=ob.getId().getEnrollment()+""+ob.getId().getInstituteId()+""+ob.getId().getEnrollment();

login.setUserId(userid);
login.setPassword(admin_password1);
login.setUserName(ab.getVoterName());
login.setRole("candidate");
System.out.println(userid);
//login.getStaffDetail().getId().setInstituteId(ob.getId().getInstituteId());
staffid.setInstituteId(lf.getInstitute_id());
staffid.setStaffId(lf.getEnrollment());
staffd.setId(staffid);

login.setStaffDetail(staffd);

Position1 pos = positiondao.searchPosition(Integer.parseInt(ob.getPosition()));
 ob.setStatus("REGISTERED");
 try{
CandidateRegistrationDAO.updateCandidature(login,ob,c1);
String path = servlet.getServletContext().getRealPath("/");
request.setAttribute("msg", "Registration Accepted Successfully");
obj=new Email(path,ab.getEmail(),admin_password,"Registration Accepted Successfully from EMS","Mr. "+ c1.getCandidateName() +"\n"+ (ab.getPAddress()!=null?ab.getPAddress():(ab.getCAddress()!=null?ab.getCAddress():"Address"))+" \n Your request of candidature for the post of "+ pos.getPositionName() +" has been accepted with User Id="+userid +" and your Password for EMS Login is="+admin_password+".\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
         executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });
            }catch(Exception e2){System.out.println("Exception"+e2);}
             return mapping.findForward(SUCCESS);
         }
         if(button.equals("Update"))
         {
            
             CandidateRegistrationDAO voterdao=new CandidateRegistrationDAO();
             Election e1 = ElectionDAO.searchElectionByName(lf.getElections(), eid);


          CandidateRegistration vr=voterdao.searchCandidateRegistration(eid, id,e1.getId().getElectionId());
          System.out.println(eid+"   "+id+"  ");
          VoterRegistration vr1=voterdao.searchVoterRegistration(eid, id);
          
          if(vr!=null)
          {
                if(vr.getBacklog().equalsIgnoreCase(lf.getBacklog())==false)
                    vr.setBacklog(lf.getBacklog());
                if(vr.getCriminal().equalsIgnoreCase(lf.getCriminal())==false)
                    vr.setCriminal(lf.getCriminal());
                if(vr.getEnrolledIn().equalsIgnoreCase(lf.getEnrolled_in())==false)
                    vr.setEnrolledIn(lf.getEnrolled_in());
                if(vr.getIndisc().equalsIgnoreCase(lf.getIndisc())==false)
                    vr.setIndisc(lf.getIndisc());
                if(vr.getPAttendence().equalsIgnoreCase(lf.getP_attendence())==false)
                    vr.setPAttendence(lf.getP_attendence());
                if(vr.getPMarks().equalsIgnoreCase(lf.getP_marks())==false)
                    vr.setPMarks(lf.getP_marks());

                Election e = ElectionDAO.searchElectionByName(lf.getElections(), eid);
                Position1 p=null;


                if(e!=null)
                    p = positiondao.getPositionByName(lf.getPosition(), e.getId().getElectionId(), eid);

                if(p!=null)
                {
                    
                    vr.setPosition(String.valueOf(p.getId().getPositionId()));}
                if(vr.getId().getEnrollment().equalsIgnoreCase(lf.getEnrollment())==false)
                    vr.getId().setEnrollment(lf.getEnrollment());
                
              
          }

           
          if(vr1!=null)
          {
              vr1.getId().setEnrollment(id);
              vr1.getId().setInstituteId(lf.getInstitute_id());
              vr1.setDepartment(lf.getDepartment());
              vr1.setCourse(lf.getCourse());
              vr1.setYear(lf.getYear());
              vr1.setCourseDuration(lf.getDuration());
              vr1.setCurrentSession(lf.getSession());
              vr1.setJoiningDate(lf.getJ_date());
              vr1.setVoterName(lf.getV_name());
              vr1.setGender(lf.getGender());
              vr1.setBirthdate(lf.getB_date());
              vr1.setFName(lf.getF_name());
              vr1.setMName(lf.getM_name());
              vr1.setMobileNumber(lf.getM_number());
              vr1.setEmail(lf.getEmail());
              vr1.setCAddress(lf.getC_add());
              vr1.setCity(lf.getCity());
              vr1.setState(lf.getState());
              vr1.setZipCode(lf.getZipcode());
              vr1.setCountry(lf.getCountry());
              vr1.setPAddress(lf.getP_add());
              vr1.setCity1(lf.getCity1());
              vr1.setState1(lf.getState1());
              vr1.setCountry1(lf.getCountry1());
              vr1.setZipCode1(lf.getZipcode1());
          }
             if (lf.getImg()!=null)
            vr1.setImage(lf.getImg().getFileData());
         else
               if(iii!=null){vr1.setImage(iii);}
               else{vr1.setImage(null);}


          CandidateRegistrationDAO.update(vr,vr1);

        
//String path = servlet.getServletContext().getRealPath("/");
//          obj=new Email(path,ab.getEmail(),admin_password,"Registration Accepted Successfully from EMS","User Id="+userid +" Your Password for EMS Login is="+admin_password);
//         executor.submit(new Runnable() {
//
//                public void run() {
//                    obj.send();
//                }
//            });
          session.setAttribute("msg","Candidate record Updated Successfully");
          System.out.println("ddd");

             return mapping.findForward(SUCCESS);
         }



         if(button.equals("Print"))
         {
              List list;
              ElectionManagerDAO dao= new ElectionManagerDAO();

        String path = servlet.getServletContext().getRealPath("/");
         JasperCompileManager.compileReportToFile(path+"/reports/CandidateReport.jrxml");
String enroll=lf.getEnrollment();
 System.out.println(enroll);
         list=dao.Report(enroll);
if(!list.isEmpty()){
         System.out.println(list.get(0)+""+enroll);
         JRBeanCollectionDataSource data=new  JRBeanCollectionDataSource(list);

          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");
         
         HashMap hash= new HashMap();
//         hash.put("image",list.get(11));


         JasperFillManager.fillReportToFile(path+"/reports/CandidateReport.jasper",hash,data);

         File file= new File(path+"/reports/CandidateReport.jrprint");

         JasperPrint print =(JasperPrint)JRLoader.loadObject(file);

         JRPdfExporter pdf=new JRPdfExporter();

         pdf.setParameter(JRExporterParameter.JASPER_PRINT, print);
         pdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path+"/reports/CandidateReport.pdf");

         pdf.exportReport();
JRExporter exporter = null;
                exporter = new JRHtmlExporter();
JasperExportManager.exportReportToPdfStream(print, ouputStream);


         


 // path=path+"/src/java/com/myapp/struts/circulation/JasperReport";
         }


 
         }
       return mapping.findForward("add");
    }
          }