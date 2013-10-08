/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.AdminDAO.LoginDAO;
import com.myapp.struts.Candidate.CandidateRegistrationDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import  com.myapp.struts.utility.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.myapp.struts.Voter.VoterRegistrationDAO;
import com.myapp.struts.hbm.Candidate1;
import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.VoterRegistration;
//import java.util.List;
import java.util.*;

import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.SetVoter;
import com.myapp.struts.hbm.SetVoterId;
import com.myapp.struts.hbm.VotingDAO;
import com.myapp.struts.hbm.VotingProcess;
import java.io.*;

public class VotersetAction1 extends org.apache.struts.action.Action {


    private static final String SUCCESS = "success";

    private final ExecutorService executor=Executors.newFixedThreadPool(1);
    Email x;
    private String admin_password;
    private String admin_password1,onetimekey,onetimekey1;
	private String bodymessRP1="Dear Mr/Ms. ";
	private String bodymessRP2="\n\n There are two emails, one containing login and password and other containing onetime use key. If there are more than one of each type, please use latest ones.\nFor this check the time of the email.\n\n Your login is ";
	private String bodymessRP3=" and  Password is ";
	private String bodymessRP4;


/*
Dear Mr.XXXX,

There are two emails. If there are more than one of each type, please use latest ones. For
this check the time of the email.

Your login is XXX@XXX and password is YYYY

Use this to login into the system at http://202.141.40.218:8080/EMS or
https://202.141.40.218:8443/EMS .

Click on 'cast vote'. Now enter the key sent in the email with subject
"One time key.... ", now choose the candidates for various posts and
click on vote now.

YOU NEED NOT TO DO ANYTHING ELSE. OTHER FUNCTIONALITIES ARE BEING
DISABLED/USELESS AS OF NOW.

Sorry for inconvenience. Your feedback can be sent on ynsingh@iitk.ac.in,
brihspti@iitk.ac.in or alumni@iitk.ac.in

ynsingh
EO, IITKAA Elections 2012
*/
	private String bodymessOTK1="Dear Sir/Madam.";
	private String bodymessOTK2="\n\n Your one time use key to be used after clicking on 'cast vote' after login into EMS system is ";
	private String bodymessOTK3;
/*
Dear Sir/Madam,

Your one time use key to be used after clicking on 'cast vote' after login into EMS system

is ZZZZZ

If you have lost your login and password, email to ynsingh@iitk.ac.in or brihspti@iitk.ac.in

ynsingh
EO, IITKAA Elections 2012
*/



    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        HttpSession session = request.getSession();
	
        DepActionForm loginActionForm;
	loginActionForm = (DepActionForm) form;

	List log=new ArrayList();

	String election=loginActionForm.getElectionId();

	List obj= (List)session.getAttribute("resultset");

	String institute_id=(String)session.getAttribute("institute_id");
        String user_id=(String)session.getAttribute("user_id");
        String username=(String)session.getAttribute("username");
        Election e1=ElectionDAO.searchElection(election, institute_id);
        bodymessRP4=" \n\n Use this to login into the system at http://202.141.40.218:8080/EMS or https://202.141.40.218:8443/EMS .\n\n Click on 'cast vote'. Now enter the key sent in the email with subject \"One time key.... \", now choose the candidates for various posts and click on vote now.\n\n YOU NEED NOT TO DO ANYTHING ELSE. OTHER FUNCTIONALITIES ARE BEING DISABLED/USELESS AS OF NOW.\n\n Sorry for inconvenience. Your feedback can be sent on "+user_id+"\n\n"+username+" \nEO, "+e1.getElectionName()+"\n";
        bodymessOTK3="\n\n If you have lost your login and password, \nemail to "+user_id+"\n\n"+username+"\nEO, "+e1.getElectionName()+"\n";

	String action=loginActionForm.getAction();
if(action.equalsIgnoreCase("4"))//got Reset Password to ALL Voters
{
     VoterRegistrationDAO voter=new VoterRegistrationDAO();
 List<VoterRegistration> rst = (List<VoterRegistration>)voter.getVoterDetailsReg(institute_id);

        for(int i=0;i<rst.size();i++)
        {
            VoterRegistration obj1=(VoterRegistration)rst.get(i);
            if(obj1.getEmail()!=null){
            Login obj2=(Login)LoginDAO.searchLoginID(obj1.getEmail());
                /*Admin Password Generate*/
                  admin_password= RandomPassword.getRandomString(10);
                  admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
                //  System.out.println("aaaaaaaaaaaaaa"+obj1.getFName()+" "+obj1.getId().getEnrollment());
                  String role=obj2.getRole();                
                  String sid=obj2.getStaffDetail().getId().getStaffId();                  
                  List<Candidate1> ca=CandidateRegistrationDAO.getCandidateID(institute_id,sid,election);
                  if(role.equalsIgnoreCase("voter") && ca.isEmpty())
                  {
                      obj2.setPassword(admin_password1);
                      LoginDAO.update1(obj2);
                      //mailSend(path,obj1.getEmail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+obj1.getVoterName()+"\n You are Registered as a Voter with given User Id= "+obj1.getEmail() +" , Password for Election Management System (EMS) Login "+admin_password+"\n The URL of the EMS server is https://202.141.40.218:8443/EMS \nFor Voting you will receive separate one time password.\n\n\nWith Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                      mailSend(obj1.getEmail(),admin_password,"Registration Accepted Successfully from EMS",bodymessRP1+obj1.getVoterName()+bodymessRP2+obj1.getEmail() +bodymessRP3+admin_password+bodymessRP4+session.getAttribute("institute_name"));
                      log.add( "\nMail has been send successfully to= "+obj1.getEmail());
                      if(obj1.getAlternateMail()!=null){
                      mailSend1(obj1.getAlternateMail(),admin_password,"Registration Accepted Successfully from EMS",bodymessRP1+obj1.getVoterName()+bodymessRP2+obj1.getEmail() +bodymessRP3+admin_password+bodymessRP4+session.getAttribute("institute_name"));
                      //mailSend1(path,obj1.getAlternateMail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+obj1.getVoterName()+"\n You are Registered as a Voter with given User Id "+obj1.getEmail() +" , Password for Election Management System (EMS) Login "+admin_password+"\n The URL of the EMS server is https://202.141.40.218:8443/EMS \nFor Voting you will receive separate one time password.\n\n\nWith Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                      log.add( "\nMail has been send successfully to= "+obj1.getAlternateMail());
                  }}
        }
        }

}
else if(action.equalsIgnoreCase("1"))//got Reset Password to Current Page
{
    for(int i=0;i<obj.size();i++)
        {
            VoterRegistration obj1=(VoterRegistration)obj.get(i);
            if(obj1.getEmail()!=null){
            Login obj2=(Login)LoginDAO.searchLoginID(obj1.getEmail());
            /*Admin Password Generate*/
            admin_password= RandomPassword.getRandomString(10);
            admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
            if(admin_password1!= null)
            {
                obj2.setPassword(admin_password1);}
            String role=obj2.getRole();
            String sid=obj2.getStaffDetail().getId().getStaffId();
            List<Candidate1> ca=CandidateRegistrationDAO.getCandidateID(institute_id,sid,election);
            if(role.equalsIgnoreCase("voter") && ca.isEmpty())
            {
                  LoginDAO.update1(obj2);
                  mailSend(obj1.getEmail(),admin_password,"Registration Accepted Successfully from EMS",bodymessRP1+obj1.getVoterName()+bodymessRP2+obj1.getEmail() +bodymessRP3+admin_password+bodymessRP4+session.getAttribute("institute_name"));
                  //mailSend(path,obj1.getEmail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+obj1.getVoterName()+"\n You are Registered as a Voter with given User Id "+obj1.getEmail() +" , Password for Election Management System (EMS) Login  "+admin_password+"\n The URL of the EMS server is https://202.141.40.218:8443/EMS \nFor Voting you will receive separate one time password.\n\n\nWith Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                  log.add( "\nMail has been send successfully to= "+obj1.getEmail());
             if(obj1.getAlternateMail()!=null)
             {
	          //	mailSend1(path,obj1.getAlternateMail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+obj1.getVoterName()+"\n You are Registered as a Voter with given User Id "+obj1.getEmail() +" , Password for Election Management System (EMS) Login "+admin_password+"\n The URL of the EMS server is https://202.141.40.218:8443/EMS \nFor Voting you will receive separate one time password.\n\n\nWith Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                  mailSend1(obj1.getAlternateMail(),admin_password,"Registration Accepted Successfully from EMS",bodymessRP1+obj1.getVoterName()+bodymessRP2+obj1.getEmail() +bodymessRP3+admin_password+bodymessRP4+session.getAttribute("institute_name"));
                  log.add( "\nMail has been send successfully to Alternate Mail"+obj1.getAlternateMail());
             }}
            }}
}
else if(action.equalsIgnoreCase("5")) //Reset Password and one time key for All
{
VoterRegistrationDAO voter=new VoterRegistrationDAO();
 List<VoterRegistration> rst = (List<VoterRegistration>)voter.getVoterDetailsReg(institute_id);

        for(int i=0;i<rst.size();i++)
        {
            VoterRegistration obj1=(VoterRegistration)rst.get(i);
            if(obj1.getEmail()!=null){
            Login obj2=(Login)LoginDAO.searchLoginID(obj1.getEmail());
                /*Admin Password Generate*/
                  admin_password= RandomPassword.getRandomString(10);
                  admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
                  if(admin_password1!=null){
                  obj2.setPassword(admin_password1);}
                  String role=obj2.getRole();
                  String sid=obj2.getStaffDetail().getId().getStaffId();
                  List<Candidate1> ca=CandidateRegistrationDAO.getCandidateID(institute_id,sid,election);

                  if(role.equalsIgnoreCase("voter") && ca.isEmpty()){
                  LoginDAO.update1(obj2);
                  mailSend(obj1.getEmail(),admin_password,"Registration Accepted Successfully from EMS",bodymessRP1+obj1.getVoterName()+bodymessRP2+obj1.getEmail() +bodymessRP3+admin_password+bodymessRP4+session.getAttribute("institute_name"));
          //         mailSend(path,obj1.getEmail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+obj1.getVoterName()+"\n You are Registered as a Voter with given User Id "+obj1.getEmail() +" , Password for Election Management System (EMS) Login "+admin_password+"\n The URL of the EMS server is https://202.141.40.218:8443/EMS \nFor Voting you will receive separate one time password.\n\n\nWith Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                    log.add( "\nMail has been send successfully to= "+obj1.getEmail());
                 if(obj1.getAlternateMail()!=null)
                 { // mailSend1(path,obj1.getAlternateMail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+obj1.getVoterName()+"\n You are Registered as a Voter with given User Id "+obj1.getEmail() +" , Password for Election Management System (EMS) Login "+admin_password+"\n The URL of the EMS server is https://202.141.40.218:8443/EMS \nFor Voting you will receive separate one time password.\n\n\nWith Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                  mailSend1(obj1.getAlternateMail(),admin_password,"Registration Accepted Successfully from EMS",bodymessRP1+obj1.getVoterName()+bodymessRP2+obj1.getEmail() +bodymessRP3+admin_password+bodymessRP4+session.getAttribute("institute_name"));
                 log.add( "\nMail has been send successfully to Alternate Email"+obj1.getAlternateMail());
                 }
                   
                 
                    Election e=ElectionDAO.searchElection(election, institute_id);
                                VotingDAO v=new VotingDAO();
					SetVoter o=new SetVoter();
	    				SetVoterId oi=new SetVoterId();
	    				oi.setInstituteId(institute_id);
	    				oi.setElectionId(election);
	    				oi.setEnrollment(obj1.getId().getEnrollment());
	    				o.setId(oi);
					/*Admin Password Generate*/
                 			onetimekey= RandomPassword.getRandomString(10);
			//		log.add( "\nOne time key is  "+onetimekey);
                			onetimekey1=PasswordEncruptionUtility.password_encrupt(onetimekey);
            				o.setPassword(onetimekey1);
            				VoterRegistrationDAO.setVoter(o);
	         			
            				mailSend(obj1.getEmail(),onetimekey,"Login Password & One time key for voting for  : "+e.getElectionName()+" election",bodymessRP1+obj1.getVoterName()+bodymessOTK2+onetimekey+"\n Login password is"+""+admin_password+bodymessOTK3+session.getAttribute("institute_name"));
            			//	mailSend(path,obj1.getEmail(),onetimekey,"One time key for voting for  : "+e.getElectionName()+" election","Dear "+obj1.getVoterName()+"\n For Voting Your one time key for casting your ballot for "+e.getElectionName()+" Election Only is "+onetimekey+"\n"+"With Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                                        log.add( "\nOne time key has been send successfully to= "+obj1.getEmail());
                   			if(obj1.getAlternateMail()!=null)
                                        {
                                            mailSend1(obj1.getAlternateMail(),onetimekey,"Login Password and One time key for voting for  : "+e.getElectionName()+" election",bodymessRP1+obj1.getVoterName()+bodymessOTK2+onetimekey+"\n Login password is"+""+admin_password+bodymessOTK3+session.getAttribute("institute_name"));
                                            //mailSend1(path,obj1.getAlternateMail(),onetimekey,"One time key for voting for  : "+e.getElectionName()+" election","Dear "+obj1.getVoterName()+"\n For Voting Your one time key for casting your ballot for "+e.getElectionName()+" Election Only is "+onetimekey+"\n"+"With Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                                        log.add( "\nOne time key has been send successfully to Alternate Mail "+obj1.getAlternateMail()+"\n");
                                        }}
         }
        }
}
else if(action.equalsIgnoreCase("2")) //Reset Password and one time key for Current Page
{
        for(int i=0;i<obj.size();i++)
        {
            
            VoterRegistration obj1=(VoterRegistration)obj.get(i);
            if(obj1.getEmail()!=null){
            
            Login obj2=(Login)LoginDAO.searchLoginID(obj1.getEmail());
            
           // System.out.println(obj2+"Bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+obj1.getEmail());
            
                /*Admin Password Generate*/
                  admin_password= RandomPassword.getRandomString(10);
                  admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
                  if(admin_password1!=null){
                  obj2.setPassword(admin_password1);}
                  String role=obj2.getRole();
                  String sid=obj2.getStaffDetail().getId().getStaffId();
                 List<Candidate1> ca=CandidateRegistrationDAO.getCandidateID(institute_id,sid,election);

                  if(role.equalsIgnoreCase("voter") && ca.isEmpty()){
                  LoginDAO.update1(obj2);
                  mailSend(obj1.getEmail(),admin_password,"Registration Accepted Successfully from EMS",bodymessRP1+obj1.getVoterName()+bodymessRP2+obj1.getEmail() +bodymessRP3+admin_password+bodymessRP4+session.getAttribute("institute_name"));
  //                   mailSend(path,obj1.getEmail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+obj1.getVoterName()+"\n You are Registered as a Voter with given User Id "+obj1.getEmail() +" , Password for Election Management System (EMS) Login "+admin_password+"\n The URL of the EMS server is https://202.141.40.218:8443/EMS \nFor Voting you will receive separate one time password.\n\n\nWith Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                     log.add( "\nMail has been send successfully to= "+obj1.getEmail());
                    if(obj1.getAlternateMail()!=null)
                    { 
//mailSend1(path,obj1.getAlternateMail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+obj1.getVoterName()+"\n You are Registered as a Voter with given User Id "+obj1.getEmail() +" , Password for Election Management System (EMS) Login "+admin_password+"\n The URL of the EMS server is https://202.141.40.218:8443/EMS \nFor Voting you will receive separate one time password.\n\n\nWith Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                  mailSend1(obj1.getAlternateMail(),admin_password,"Registration Accepted Successfully from EMS",bodymessRP1+obj1.getVoterName()+bodymessRP2+obj1.getEmail() +bodymessRP3+admin_password+bodymessRP4+session.getAttribute("institute_name"));
                     log.add( "\nMail has been send successfully to Alternate Mail"+obj1.getAlternateMail());
                    }


                    Election e=ElectionDAO.searchElection(election, institute_id);
                                
                                
					SetVoter o=new SetVoter();
	    				SetVoterId oi=new SetVoterId();
	    				oi.setInstituteId(institute_id);
	    				oi.setElectionId(election);
	    				oi.setEnrollment(obj1.getId().getEnrollment());
	    				o.setId(oi);
					/*Admin Password Generate*/
                 			onetimekey= RandomPassword.getRandomString(10);
			//		log.add( "\nOne time key is  "+onetimekey);
                			onetimekey1=PasswordEncruptionUtility.password_encrupt(onetimekey);
            				o.setPassword(onetimekey1);
            				VoterRegistrationDAO.setVoter(o);

            				mailSend(obj1.getEmail(),onetimekey,"Reset Login Password & One time key for voting for  : "+e.getElectionName()+" election",bodymessRP1+obj1.getVoterName()+bodymessOTK2+onetimekey+"\n Login password is"+""+admin_password+bodymessOTK3+session.getAttribute("institute_name"));
  //          				mailSend(path,obj1.getEmail(),onetimekey,"One time key for voting for  : "+e.getElectionName()+" election","Dear "+obj1.getVoterName()+"\n For Voting Your one time key for casting your ballot for "+e.getElectionName()+" Election Only is "+onetimekey+"\n"+"With Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                                        log.add( "\nOne time key has been send successfully to= "+obj1.getEmail());
                                        if(obj1.getAlternateMail()!=null)
                                        {
//mailSend1(path,obj1.getAlternateMail(),onetimekey,"One time key for voting for  : "+e.getElectionName()+" election","Dear "+obj1.getVoterName()+"\n For Voting Your one time key for casting your ballot for "+e.getElectionName()+" Election Only is "+onetimekey+"\n"+"With Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                                            mailSend1(obj1.getAlternateMail(),onetimekey,"Login Password & One time key for voting for  : "+e.getElectionName()+" election",bodymessRP1+obj1.getVoterName()+bodymessOTK2+onetimekey+"\n Login password is"+""+admin_password+bodymessOTK3+session.getAttribute("institute_name"));
                                        log.add( "\nOne time key has been send successfully to Alternate Mail"+obj1.getAlternateMail()+"\n");
                                        }}
         }
        }
}
else if(action.equalsIgnoreCase("3")) //Reset Password and one time key for Current Page if Vote not cast
{


        for(int i=0;i<obj.size();i++)
        {
            VoterRegistration obj1=(VoterRegistration)obj.get(i);
            if(obj1.getEmail()!=null){
                    Election e=ElectionDAO.searchElection(election, institute_id);
                                VotingDAO v=new VotingDAO();
                                VotingProcess voting=(VotingProcess)v.GetVoteStatus(institute_id, election,obj1.getEmail());
				if(voting==null){


                                     Login obj2=(Login)LoginDAO.searchLoginID(obj1.getEmail());
                                    /*Admin Password Generate*/
                                    admin_password= RandomPassword.getRandomString(10);
                                    admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
                                    obj2.setPassword(admin_password1);
                                    String role=obj2.getRole();
                  String sid=obj2.getStaffDetail().getId().getStaffId();
                  List<Candidate1> ca=CandidateRegistrationDAO.getCandidateID(institute_id,sid,election);

                  if(role.equalsIgnoreCase("voter") && ca.isEmpty()){
                                    LoginDAO.update1(obj2);
                  mailSend(obj1.getEmail(),admin_password,"Registration Accepted Successfully from EMS",bodymessRP1+obj1.getVoterName()+bodymessRP2+obj1.getEmail() +bodymessRP3+admin_password+bodymessRP4+session.getAttribute("institute_name"));
  //                                  mailSend(path,obj1.getEmail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+obj1.getVoterName()+"\n You are Registered as a Voter with given User Id "+obj1.getEmail() +" , Password for Election Management System (EMS) Login "+admin_password+"\n The URL of the EMS server is https://202.141.40.218:8443/EMS \nFor Voting you will receive separate one time password.\n\n\nWith Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                                    log.add( "\nMail has been send successfully to= "+obj1.getEmail());
                                   if(obj1.getAlternateMail()!=null)
                                   {  
//mailSend1(path,obj1.getAlternateMail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+obj1.getVoterName()+"\n You are Registered as a Voter with given User Id "+obj1.getEmail() +" , Password for Election Management System (EMS) Login "+admin_password+"\n The URL of the EMS server is https://202.141.40.218:8443/EMS \nFor Voting you will receive separate one time password.\n\n\nWith Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                  mailSend1(obj1.getAlternateMail(),admin_password,"Registration Accepted Successfully from EMS",bodymessRP1+obj1.getVoterName()+bodymessRP2+obj1.getEmail() +bodymessRP3+admin_password+bodymessRP4+session.getAttribute("institute_name"));
                                  log.add("\n Mail  has been send succesfully to  Alternate Mail ="+obj1.getAlternateMail());
                                   }
					SetVoter o=new SetVoter();
	    				SetVoterId oi=new SetVoterId();
	    				oi.setInstituteId(institute_id);
	    				oi.setElectionId(election);
	    				oi.setEnrollment(obj1.getId().getEnrollment());
	    				o.setId(oi);
					/*Admin Password Generate*/
                 			onetimekey= RandomPassword.getRandomString(10);
			//		log.add( "\nOne time key is  "+onetimekey);
                			onetimekey1=PasswordEncruptionUtility.password_encrupt(onetimekey);
            				o.setPassword(onetimekey1);
            				VoterRegistrationDAO.setVoter(o);

            			//	mailSend(path,obj1.getEmail(),onetimekey,"One time key for voting for  : "+e.getElectionName()+" election","Dear "+obj1.getVoterName()+"\n For Voting Your one time key for casting your ballot for "+e.getElectionName()+" Election Only is "+onetimekey+"\n"+"With Regards\nElection Officer\n"+session.getAttribute("institute_name"));
            				mailSend(obj1.getEmail(),onetimekey,"Login Password & One time key for voting for  : "+e.getElectionName()+" election",bodymessRP1+obj1.getVoterName()+bodymessOTK2+onetimekey+"\n Login password is"+""+admin_password+bodymessOTK3+session.getAttribute("institute_name"));
                                        log.add( "\nOne time key has been send successfully to= "+obj1.getEmail());
                                        if(obj1.getAlternateMail()!=null)
                                        { 
						//  mailSend1(path,obj1.getAlternateMail(),onetimekey,"One time key for voting for  : "+e.getElectionName()+" election","Dear "+obj1.getVoterName()+"\n For Voting Your one time key for casting your ballot for "+e.getElectionName()+" Election Only is "+onetimekey+"\n"+"With Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                                            mailSend1(obj1.getAlternateMail(),onetimekey,"Login Password & One time key for voting for  : "+e.getElectionName()+" election",bodymessRP1+obj1.getVoterName()+bodymessOTK2+onetimekey+"\n Login password is"+""+admin_password+bodymessOTK3+session.getAttribute("institute_name"));
					log.add( "\nOne time key has been send successfully to Alternate Email"+obj1.getAlternateMail()+"\n");
                                        }
                                   }
                                else{
                                      log.add( "\nOne time key has not been send to = "+obj1.getEmail()+ " because Voter already cast their vote"+"\n");
                                }}
         }
        }
}
else if(action.equalsIgnoreCase("6")) //Reset Password and one time key for All Voter not cast there vote
{
VoterRegistrationDAO voter=new VoterRegistrationDAO();
 List<VoterRegistration> rst = (List<VoterRegistration>)voter.getVoterDetailsReg(institute_id);

        for(int i=0;i<rst.size();i++)
        {
            VoterRegistration obj1=(VoterRegistration)rst.get(i);
            if(obj1.getEmail()!=null){
                    Election e=ElectionDAO.searchElection(election, institute_id);
                                VotingDAO v=new VotingDAO();
                                   VotingProcess voting=(VotingProcess)v.GetVoteStatus(institute_id, election,obj1.getEmail());
				if(voting==null){

                                     Login obj2=(Login)LoginDAO.searchLoginID(obj1.getEmail());
                                     /*Admin Password Generate*/
                                     admin_password= RandomPassword.getRandomString(10);
                                     admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
                                     obj2.setPassword(admin_password1);
                                     String role=obj2.getRole();
                  String sid=obj2.getStaffDetail().getId().getStaffId();
                  List<Candidate1> ca=CandidateRegistrationDAO.getCandidateID(institute_id,sid,election);

                  if(role.equalsIgnoreCase("voter") && ca.isEmpty()){
                                     LoginDAO.update1(obj2);
                                    // mailSend(path,obj1.getEmail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+obj1.getVoterName()+"\n You are Registered as a Voter with given User Id "+obj1.getEmail() +" , Password for Election Management System (EMS) Login "+admin_password+"\n The URL of the EMS server is https://202.141.40.218:8443/EMS \nFor Voting you will receive separate one time password.\n\n\nWith Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                  mailSend(obj1.getEmail(),admin_password,"Registration Accepted Successfully from EMS",bodymessRP1+obj1.getVoterName()+bodymessRP2+obj1.getEmail() +bodymessRP3+admin_password+bodymessRP4+session.getAttribute("institute_name"));
                                     log.add( "\nMail has been send successfully to= "+obj1.getEmail());
                                    if(obj1.getAlternateMail()!=null)
                                    { 
				 // mailSend1(path,obj1.getAlternateMail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+obj1.getVoterName()+"\n You are Registered as a Voter with given User Id "+obj1.getEmail() +" , Password for Election Management System (EMS) Login "+admin_password+"\n The URL of the EMS server is https://202.141.40.218:8443/EMS \nFor Voting you will receive separate one time password.\n\n\nWith Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                  mailSend1(obj1.getAlternateMail(),admin_password,"Registration Accepted Successfully from EMS",bodymessRP1+obj1.getVoterName()+bodymessRP2+obj1.getEmail() +bodymessRP3+admin_password+bodymessRP4+session.getAttribute("institute_name"));
                                    log.add( "\nMail has been send successfully to Alternate Mail"+obj1.getAlternateMail());
                                    }

                			SetVoter o=new SetVoter();
	    				SetVoterId oi=new SetVoterId();
	    				oi.setInstituteId(institute_id);
	    				oi.setElectionId(election);
	    				oi.setEnrollment(obj1.getId().getEnrollment());
	    				o.setId(oi);
					/*Admin Password Generate*/
                 			onetimekey= RandomPassword.getRandomString(10);
			//		log.add( "\nOne time key is  "+onetimekey);
                			onetimekey1=PasswordEncruptionUtility.password_encrupt(onetimekey);
            				o.setPassword(onetimekey1);
            				VoterRegistrationDAO.setVoter(o);
//                                        mailSend(path,obj1.getEmail(),onetimekey,"One time key for voting for  : "+e.getElectionName()+" election","Dear "+obj1.getVoterName()+"\n For Voting Your one time key for casting your ballot for "+e.getElectionName()+" Election Only is "+onetimekey+"\n"+"With Regards\nElection Officer\n"+session.getAttribute("institute_name"));
            				mailSend(obj1.getEmail(),onetimekey,"Login Password & One time key for voting for  : "+e.getElectionName()+" election",bodymessRP1+obj1.getVoterName()+bodymessOTK2+onetimekey+"\n Login password is"+""+admin_password+bodymessOTK3+session.getAttribute("institute_name"));
                                        	log.add( "\nOne time key has been send successfully to= "+obj1.getEmail());
                                        if(obj1.getAlternateMail()!=null)
                                        { //  mailSend1(path,obj1.getAlternateMail(),onetimekey,"One time key for voting for  : "+e.getElectionName()+" election","Dear "+obj1.getVoterName()+"\n For Voting Your one time key for casting your ballot for "+e.getElectionName()+" Election Only is "+onetimekey+"\n"+"With Regards\nElection Officer\n"+session.getAttribute("institute_name"));
                                            mailSend1(obj1.getAlternateMail(),onetimekey,"Login Password & One time key for voting for  : "+e.getElectionName()+" election",bodymessRP1+obj1.getVoterName()+bodymessOTK2+onetimekey+"\n Login password is"+""+admin_password+bodymessOTK3+session.getAttribute("institute_name"));
                                            log.add( "\nOne time key has been send successfully to Alternate Mail= "+obj1.getAlternateMail());
                                        }
            				
                                     }
                                else{
                                	log.add( "\nOne time key has not been send to = "+obj1.getEmail()+ " because Voter already cast their vote"+"\n");
                                    }}


         }
        }
}
System.out.println("System is here11111111");
UserLog.ErrorListLog(log,"ViewVoterLog.txt");
session.setAttribute("log",log);


System.out.println("System is here");

request.setAttribute("msg", log);

return mapping.findForward("success");
    }
    public void mailSend(String to,String admin_password,String subject,String body){
            x=new Email(to,admin_password,subject,body);
            x.send();
           

    }
      public void mailSend1(String to,String admin_password,String subject,String body){
            x=new Email(to,admin_password,subject,body);
            x.sendAlternatemail();


    }

}
