/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.AdminDAO.LoginDAO;

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

public class VotersetAction3 extends org.apache.struts.action.Action {


    private static final String SUCCESS = "success";

    private final ExecutorService executor=Executors.newFixedThreadPool(1);
    Email x;
    private String admin_password;
    private String admin_password1,onetimekey,onetimekey1;
    private String bodymessRPOTKM1;
    private String bodymessRPOTKM11;

//	private String bodymessRPOTKM1="Dear Sir/Madam,\n\nYou have still not casted your ballot in IIT Kanpur AA BoD election. Voting over web portal will close on 16 March 2012 2359hrs IST.\n\n For details of candidates please visit\n\nhttp://202.141.40.215/~brihaspati/final_list.shtml\n\nFor other details regarding election, you can visit election website at http://202.141.40.215/~brihaspati\n\n For casting your ballot on web portal, you can click on the following link\n\n"+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/voterlogin.do?email=";
//	private String ur="you can click on the following link"+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/voterlogin.do?email="; 

	private String bodymessRPOTKM2;
	private String bodymessRPOTKM3;
	private String subRPOTKM;
/*
Dear Sir/Madam,

You have still not casted your ballot in IIT Kanpur AA BoD election. For casting your ballot over web portal, kindly click the following link

http://202.141.40.218:8080/EMS/voterlogin.do?email="+obj2.getUserId()+"&hash="+admin_password+"&eid="+election+"&key="+onetimekey

You can also cut and past the above link in a browser to cast your ballot.

For login into election management system, your login is "+obj2.getUserId()+" and password is  "+obj2.getPassword()+" 

You are requested to use the latest mail for this casting your ballot.

YNSingh
EO, IITKAA 2012
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
        String username=(String)session.getAttribute("username");

         Election e1=ElectionDAO.searchElection(election, institute_id);

        subRPOTKM="Credentials for voting in "+e1.getElectionName();
	bodymessRPOTKM1="Dear Sir/Madam,\n\nYou have still not voted in "+e1.getElectionName()+". Please accept any security certificate presented by the browser. Voting over web portal will close on"+e1.getEndDate()+" IST.\n\n For casting your ballot on web portal, you can click on the following link\n\n"+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/voterlogin.do?email=";
	bodymessRPOTKM11="\n\n or \n\n http://172.26.82.23:8080"+request.getContextPath()+"/voterlogin.do?email=";

        bodymessRPOTKM2="\n\nYou can also cut and past the above link in a browser to cast your ballot.\n\nAlternatively, you can login into election management system at\nhttp://172.26.82.23:8080/EMS\nhttp://educontent.iitk.ernet.in:8080/EMS\nhttps://172.26.82.23:8080/EMS\nhttps://educontent.iitk.ernet.in:8443/EMS\nfor voting. Your login is ";
        bodymessRPOTKM3="\n\nYou are requested to use the latest mail for casting your ballot.\n\n\n\n\n"+username+"\n\nEO,"+e1.getElectionName();





	String action=loginActionForm.getAction();

if(action.equalsIgnoreCase("1"))//got Reset Password & One Time Key with Voting Link from  Current Page to Email ID
{
     for(int i=0;i<obj.size();i++)
        {
            VoterRegistration obj1=(VoterRegistration)obj.get(i);
              if(obj1.getEmail()!=null){
          

                    Election e=ElectionDAO.searchElection(election, institute_id);
                    VotingDAO v=new VotingDAO();
                    VotingProcess voting=(VotingProcess)v.GetVoteStatus(institute_id, election,obj1.getEmail());
		if(voting==null){
			 //check if the voter already set and is blocked
			  SetVoter s=VoterRegistrationDAO.searchVoterList(institute_id, election, obj1.getId().getEnrollment());
                          if(s!=null && s.getStatus()!=null)
                             {
                                        log.add( "\nReset Password & One time key link not generated as voter is already Blocked\n");

                             }
                             else
                             {

                                        Login obj2=(Login)LoginDAO.searchLoginID(obj1.getEmail());
                                        /*Admin Password Generate*/
                                        admin_password= RandomPassword.getRandomString(10);
                                        admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
                                        obj2.setPassword(admin_password1);
                                        LoginDAO.update1(obj2);

					SetVoter o=new SetVoter();
	    				SetVoterId oi=new SetVoterId();
	    				oi.setInstituteId(institute_id);
	    				oi.setElectionId(election);
	    				oi.setEnrollment(obj1.getId().getEnrollment());
	    				o.setId(oi);
					/*One time key Generate*/
                 			onetimekey= RandomPassword.getRandomString(10);

                			onetimekey1=PasswordEncruptionUtility.password_encrupt(onetimekey);
            				o.setPassword(onetimekey1);
            				VoterRegistrationDAO.setVoter(o);

            				mailSend(obj1.getEmail(),admin_password,subRPOTKM,bodymessRPOTKM1+obj2.getUserId()+"&hash="+admin_password+"&eid="+election+"&key="+onetimekey+bodymessRPOTKM11+obj2.getUserId()+"&hash="+admin_password+"&eid="+election+"&key="+onetimekey+bodymessRPOTKM2+obj2.getUserId()+" and password is  "+admin_password+" and key is "+onetimekey+bodymessRPOTKM3);
  
                                        log.add( "\nReset Password & One time key link has been send successfully to= "+obj1.getEmail());
                                        if(obj1.getAlternateMail()!=null)
                                        {

                                          //mailSend1(path,obj1.getAlternateMail(),admin_password,bodymessRPOTKM1"For Casting Vote  for "+e.getElectionName()+" election","\n Your Login Id for EMS is"+obj2.getUserId()+" Password "+obj2.getPassword()+"\nClick  http://202.141.40.218:8080/EMS/voterlogin.do?email="+obj2.getUserId()+"&hash="+admin_password+"&eid="+election+"&key="+onetimekey+"\n");
                                          mailSend1(obj1.getAlternateMail(),admin_password,subRPOTKM,bodymessRPOTKM1+obj2.getUserId()+"&hash="+admin_password+"&eid="+election+"&key="+onetimekey+bodymessRPOTKM11+obj2.getUserId()+"&hash="+admin_password+"&eid="+election+"&key="+onetimekey+bodymessRPOTKM2+obj2.getUserId()+" and password is  "+admin_password+" and key is "+onetimekey+bodymessRPOTKM3);

                                        log.add( "\nReset Password & One time key link has been send successfully to Alternate Mail= "+obj1.getAlternateMail()+"\n");
                                        }
				}
                                }else{
                                        log.add( "\nReset Password & One time key link not generated as Voter already cast there vote\n");
                                }
         }}
}
else if(action.equalsIgnoreCase("2")) //Reset Password and one time key for All Voter not cast there vote
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
				if(voting==null)
                                {
//check if the voter already set and is blocked
					 SetVoter s=VoterRegistrationDAO.searchVoterList(institute_id, election, obj1.getId().getEnrollment());
                                       if(s!=null && s.getStatus()!=null)
                                       {
                                        log.add( "\nReset Password & One time key link not generated as voter is already Blocked\n");

                                       }
                                       else
                                       {
                                     Login obj2=(Login)LoginDAO.searchLoginID(obj1.getEmail());
                                     /*Admin Password Generate*/
                                     admin_password= RandomPassword.getRandomString(10);
                                     admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
                                     obj2.setPassword(admin_password1);
                                     LoginDAO.update1(obj2);


                                        SetVoter o=new SetVoter();
	    				SetVoterId oi=new SetVoterId();
	    				oi.setInstituteId(institute_id);
	    				oi.setElectionId(election);
	    				oi.setEnrollment(obj1.getId().getEnrollment());
	    				o.setId(oi);
					/*One time key Generate*/
                 			onetimekey= RandomPassword.getRandomString(10);
			
                			onetimekey1=PasswordEncruptionUtility.password_encrupt(onetimekey);
            				o.setPassword(onetimekey1);
            				VoterRegistrationDAO.setVoter(o);

                                       mailSend(obj1.getEmail(),admin_password,subRPOTKM,bodymessRPOTKM1+obj2.getUserId()+"&hash="+admin_password+"&eid="+election+"&key="+onetimekey+bodymessRPOTKM11+obj2.getUserId()+"&hash="+admin_password+"&eid="+election+"&key="+onetimekey+bodymessRPOTKM2+obj2.getUserId()+" and password is  "+admin_password+" and key is "+onetimekey+bodymessRPOTKM3);
                                       //mailSend(path,obj1.getEmail(),admin_password,bodymessRPOTKM1"For Casting Vote  for "+e.getElectionName()+" election","\n Your Login Id for EMS is"+obj2.getUserId()+" Password "+obj2.getPassword()+"\nClick  http://202.141.40.218:8080/EMS/voterlogin.do?email="+obj2.getUserId()+"&hash="+admin_password+"&eid="+election+"&key="+onetimekey+"\n");

                                        log.add( "\nReset Password & One time key link has been send successfully to= "+obj1.getEmail());
                                        if(obj1.getAlternateMail()!=null)
                                        {

                                          mailSend1(obj1.getAlternateMail(),admin_password,subRPOTKM,bodymessRPOTKM1+obj2.getUserId()+"&hash="+admin_password+"&eid="+election+"&key="+onetimekey+bodymessRPOTKM11+obj2.getUserId()+"&hash="+admin_password+"&eid="+election+"&key="+onetimekey+bodymessRPOTKM2+obj2.getUserId()+" and password is  "+admin_password+" and key is "+onetimekey+bodymessRPOTKM3);
                                         // mailSend1(path,obj1.getAlternateMail(),admin_password,bodymessRPOTKM1"For Casting Vote  for "+e.getElectionName()+" election","\n Your Login Id for EMS is"+obj2.getUserId()+" Password "+obj2.getPassword()+"\nClick  http://202.141.40.218:8080/EMS/voterlogin.do?email="+obj2.getUserId()+"&hash="+admin_password+"&eid="+election+"&key="+onetimekey+"\n");
                                          log.add( "\nReset Password & One time key link has been send successfully to Alternate Mail= "+obj1.getAlternateMail()+"\n");
                                        }
					}
                                     }
                                else{
                                	log.add( "\nReset Password & One time key link has not been send to = "+obj1.getEmail()+ " because Voter already cast their vote."+"\n");
                                    }


         }}

}

UserLog.ErrorListLog(log,"ResetPasswordOneTimeLink.txt");
session.setAttribute("log",log);




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
