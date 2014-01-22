/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.AdminDAO.LoginDAO;
import com.myapp.struts.Candidate.CandidateRegActionForm;
import com.myapp.struts.Candidate.CandidateRegistrationDAO;
import com.myapp.struts.Voter.VoterRegistrationDAO;
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
import com.myapp.struts.hbm.VoterCandidate;
import com.myapp.struts.hbm.VoterRegistration;
import com.myapp.struts.utility.Email;
import com.myapp.struts.utility.UserLog;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

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
public class SendMailToAllAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
     private static final String SUCCESS = "success";
     private static final String SUCCESS1 = "success1";
     private CandidateRegistration ob=new CandidateRegistration();
     VoterRegistration ab= new VoterRegistration();

     Candidate1 pos = new Candidate1();
            Candidate1Id pos1 = new Candidate1Id();
            PositionDAO positiondao = new PositionDAO();

            private String admin_password;
    
    String userid;
     private final ExecutorService executor=Executors.newFixedThreadPool(1);
    Email obj;

CandidateRegistrationId empid=new CandidateRegistrationId ();

private LoginDAO logindao = new LoginDAO();
     private Login login =new Login();
      private StaffDetail staffd =new StaffDetail();
      private StaffDetailId staffid =new StaffDetailId();
String mailbody;
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

      CandidateRegActionForm lf=(CandidateRegActionForm)form;
  
      HttpSession session = request.getSession();
      String button=(String)request.getParameter("button");
       String institute_id=(String)session.getAttribute("institute_id");
       String id=(String)session.getAttribute("id");
       System.out.println("button!!!!!!!!!!!!!!!!"+button);
       String enrollment=(String)session.getAttribute("id");
       String textarea=lf.getCandidateid();
       CandidateRegistrationDAO candidatedao= new CandidateRegistrationDAO();
       ArrayList log=new ArrayList();
//   if(button.equalsIgnoreCase("1")){
//        System.out.println("button11"+button);
//      List<VoterCandidate> rs =candidatedao.GetDetails(institute_id,"REGISTERED");
//      userid=(String)session.getAttribute("user_id");
//
//            for(int i=0;i<rs.size();i++)
//            {
//
//                VoterCandidate temp=new VoterCandidate();
//                temp=(VoterCandidate)rs.get(i);
//                String path =(String) session.getAttribute("apppath");
//                FileInputStream in = new FileInputStream(path+"/web/mail.properties");
//  		Properties	pro = new Properties();
//                pro.load(in);
//        	Enumeration keys = pro.keys();
//				while (keys.hasMoreElements())
//				{
//                                       String key=(String)keys.nextElement();
//                                       if(key.equalsIgnoreCase(userid+"candi")){
//                                       mailbody=(String)pro.get(key);
//                                       }
//				}
//				in.close();
//                                if(mailbody==null){
//                                     System.out.println("mailbodyfor1111"+mailbody);
//                                    request.setAttribute("msg1","Please Add Candidate Mail Body");
//                                    return mapping.findForward(SUCCESS);
//
//                                }
//
//                if(mailbody.isEmpty())
//                {
//                request.setAttribute("msg1","Please Add Candidate Mail Body");
//                return mapping.findForward(SUCCESS);
//                }
//
//                obj=new Email(path,temp.getVoterRegistration().getEmail(),"","Mail From Election Manager from EMS","Dear "+temp.getVoterRegistration().getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
//                obj.send();
//                 log.add("Mail Send to Primary Mail Id"+temp.getVoterRegistration().getEmail());
//               if(temp.getVoterRegistration().getAlternateMail()!=null){
//                obj=new Email(path,temp.getVoterRegistration().getAlternateMail(),"","Mail From Election Manager from EMS","Dear "+temp.getVoterRegistration().getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
//                obj.sendAlternatemail();
//                 log.add("Mail Send to Alternate Mail Id"+temp.getVoterRegistration().getAlternateMail());
//               }
//
//
//
//            }
//
//
//   }
       
    if(button.equalsIgnoreCase("sendmailtoall")){

     System.out.println("button22222"+button);
      List<VoterCandidate> rs =(List<VoterCandidate>)session.getAttribute("resultset1");
      userid=(String)session.getAttribute("user_id");
   System.out.println("RSSizeqqqqqqqq"+rs.size());
            for(int i=0;i<rs.size();i++)
            {

                VoterCandidate temp=new VoterCandidate();
                temp=(VoterCandidate)rs.get(i);
                                mailbody=UserLog.readProperty("mail.properties", userid+"candi");
                                if(mailbody==null){
                                    System.out.println("Mailbody@@@@@@@@@@");
                                    request.setAttribute("msg1","Please Add Candidate Mail Body");
                                    return mapping.findForward(SUCCESS1);

                                }

                if(mailbody.isEmpty())
                {
                   request.setAttribute("msg1","Please Add Candidate Mail Body");
                   return mapping.findForward(SUCCESS1);
                }

                obj=new Email(temp.getVoterRegistration().getEmail(),"","Mail From Election Manager from EMS","Dear "+temp.getVoterRegistration().getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
                obj.send();
                 log.add("Mail Send to Primary Mail Id"+temp.getVoterRegistration().getEmail());
               if(temp.getVoterRegistration().getAlternateMail()!=null){
                obj=new Email(temp.getVoterRegistration().getAlternateMail(),"","Mail From Election Manager from EMS","Dear "+temp.getVoterRegistration().getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
                obj.sendAlternatemail();
                 log.add("Mail Send to Alternate Mail Id"+temp.getVoterRegistration().getAlternateMail());
               }



            }
   session.removeAttribute("mail");
request.setAttribute("msg2", log);
return mapping.findForward(SUCCESS1);

   }

 if(button.equalsIgnoreCase("sendmail"))
 {
      List<VoterCandidate> rs =candidatedao.GetDetails1(institute_id,"REGISTERED",enrollment);
      userid=(String)session.getAttribute("user_id");
System.out.println(rs.size()+" aaaaaaaaaaa...................");
           

                VoterCandidate temp=new VoterCandidate();
                temp=(VoterCandidate)rs.get(0);
               
                     mailbody=UserLog.readProperty("mail.properties", userid+"candi");
                                if(mailbody==null){
                                    System.out.println("Mailbody@@@@@@@@@@");
                                    request.setAttribute("msg1","Please Add Candidate Mail Body");
                                    return mapping.findForward(SUCCESS1);

                                }

                if(mailbody.isEmpty())
                {
                   request.setAttribute("msg1","Please Add Candidate Mail Body");
                    System.out.println("I am in last ssssddddd");
                   return mapping.findForward(SUCCESS1);
                }
                
                obj=new Email(temp.getVoterRegistration().getEmail(),"","Mail From Election Manager from EMS","Dear "+temp.getVoterRegistration().getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
                obj.send();
                 log.add("Mail Send to Primary Mail Id "+temp.getVoterRegistration().getEmail());
                
               if(temp.getVoterRegistration().getAlternateMail()!=null){
                obj=new Email(temp.getVoterRegistration().getAlternateMail(),"","Mail From Election Manager from EMS","Dear "+temp.getVoterRegistration().getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
                obj.sendAlternatemail();
                 log.add("Mail Send to Alternate Mail Id "+temp.getVoterRegistration().getAlternateMail());
               }


        request.setAttribute("msg2", log);
        
        return mapping.findForward(SUCCESS1);

   }
 if(button.equalsIgnoreCase("sendmailtoallv")){

      List<VoterRegistration> rs =(List<VoterRegistration>)session.getAttribute("resultset");
      userid=(String)session.getAttribute("user_id");
 
            for(int i=0;i<rs.size();i++)
            {

                VoterRegistration temp=new VoterRegistration();
                temp=(VoterRegistration)rs.get(i);
                                mailbody=UserLog.readProperty("mail.properties", userid+"vm");
                                if(mailbody==null){
                                    System.out.println("Mailbody@@@@@@@@@@");
                                    request.setAttribute("msg1","Please Add Candidate Mail Body");
                                    return mapping.findForward("success3");

                                }

                if(mailbody.isEmpty())
                {
                   request.setAttribute("msg1","Please Add Candidate Mail Body");
                  return mapping.findForward("success3");
                }

                obj=new Email(temp.getEmail(),"","Mail From Election Manager from EMS","Dear "+temp.getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
                obj.send();
                 log.add("Mail Send to Primary Mail Id "+temp.getEmail());
               if(temp.getAlternateMail()!=null){
                obj=new Email(temp.getAlternateMail(),"","Mail From Election Manager from EMS","Dear "+temp.getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
                obj.sendAlternatemail();
                 log.add("Mail Send to Alternate Mail Id "+temp.getAlternateMail());
               }



            }
   session.removeAttribute("mail");
request.setAttribute("msg2", log);
return mapping.findForward("success3");

   }


   if(button.equalsIgnoreCase("sendmailvv"))
 {
      VoterRegistration rs =(VoterRegistration)VoterRegistrationDAO.searchVoterRegistration(institute_id, enrollment);
      userid=(String)session.getAttribute("user_id");

        

                     mailbody=UserLog.readProperty("mail.properties", userid+"vm");
                                if(mailbody==null){
                                    System.out.println("Mailbody@@@@@@@@@@");
                                    request.setAttribute("msg1","Please Add Voter Mail Body");
                                  return mapping.findForward("success4");

                                }

                if(mailbody.isEmpty())
                {
                   request.setAttribute("msg1","Please Add Voter Mail Body");

                 return mapping.findForward("success4");
                }

                obj=new Email(rs.getEmail(),"","Mail From Election Manager from EMS","Dear "+rs.getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
                obj.send();
                 log.add("Mail Send to Primary Mail Id "+rs.getEmail());
               if(rs.getAlternateMail()!=null){
                obj=new Email(rs.getAlternateMail(),"","Mail From Election Manager from EMS","Dear "+rs.getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
                obj.sendAlternatemail();
                 log.add("Mail Send to Alternate Mail Id "+rs.getAlternateMail());
               }


        request.setAttribute("msg2", log);
        System.out.println("i am in lastttttttttttttttttvvvvvvvvvvvv");
//        session.removeAttribute("stat");
        return mapping.findForward("success4");

   }


 if(button.equalsIgnoreCase("sendmailv"))
 {
      VoterRegistration rs =(VoterRegistration)VoterRegistrationDAO.searchVoterRegistration(institute_id, enrollment);
      userid=(String)session.getAttribute("user_id");



                     mailbody=UserLog.readProperty("mail.properties", userid+"vm");
                                if(mailbody==null){
                                    System.out.println("Mailbody@@@@@@@@@@");
                                    request.setAttribute("msg1","Please Add Voter Mail Body");
                                  return mapping.findForward("success3");

                                }

                if(mailbody.isEmpty())
                {
                   request.setAttribute("msg1","Please Add Voter Mail Body");
                 return mapping.findForward("success3");
                }

                obj=new Email(rs.getEmail(),"","Mail From Election Manager from EMS","Dear "+rs.getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
                obj.send();
                 log.add("Mail Send to Primary Mail Id "+rs.getEmail());
               if(rs.getAlternateMail()!=null){
                obj=new Email(rs.getAlternateMail(),"","Mail From Election Manager from EMS","Dear "+rs.getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
                obj.sendAlternatemail();
                 log.add("Mail Send to Alternate Mail Id "+rs.getAlternateMail());
               }


        request.setAttribute("msg2", log);
        System.out.println("i am in lasttttttttttttttttt");
        return mapping.findForward("success3");

   }
 if(button.equalsIgnoreCase("update"))
 {

                                    userid=(String)session.getAttribute("user_id");
                                    String key=userid+"candi";
                                    HashMap obj=new HashMap();
                                    obj.put(key,textarea);
                                    UserLog.updateProperty("mail.properties", obj);
                                    request.setAttribute("msg","you have successfully updated your mailbody now you can send mail");
                                    return mapping.findForward(SUCCESS);


 }
   if(button.equalsIgnoreCase("updatev"))
 {

                                    userid=(String)session.getAttribute("user_id");
                                    String key=userid+"vm";
                                    HashMap obj=new HashMap();
                                    obj.put(key,textarea);
                                    System.out.println("callllllllllllllllllllll");
                                    UserLog.updateProperty("mail.properties", obj);
                                    request.setAttribute("msg","you have successfully updated your mailbody now you can send mail");
                                    return mapping.findForward("success2");


   }
UserLog.ErrorListLog(log, "MailSendCandidate.txt");
request.setAttribute("msg", log);
return mapping.findForward(SUCCESS1);
    }
          }