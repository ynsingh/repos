/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.hbm.SetVoter;

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
import com.myapp.struts.hbm.SetVoterId;
import com.myapp.struts.hbm.VoterRegistration;
import java.util.*;
import com.myapp.struts.hbm.VotingDAO;
import com.myapp.struts.hbm.VotingProcess;
import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import java.io.*;

public class VotersetAction extends org.apache.struts.action.Action {

	private static final String SUCCESS = "success";
    	private final ExecutorService executor=Executors.newFixedThreadPool(1);
    	Email x;
    	private String admin_password;
    	private String admin_password1;
    	@Override
    	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        	HttpSession session = request.getSession();

        	DepActionForm loginActionForm;
		loginActionForm = (DepActionForm) form;

		String election_id=loginActionForm.getElectionId();
		String election=loginActionForm.getElection();

		List obj= (List)session.getAttribute("resultset");
		ArrayList log=new ArrayList();
	//	System.out.println("ddvsdfsfgs");

		String institute_id=(String)session.getAttribute("institute_id");
		Election e=ElectionDAO.searchElection(election, institute_id);
		String action=loginActionForm.getAction();
                if(action==null)
                    action="Current";
	 	if(action.equalsIgnoreCase("All")){
     			institute_id=(String)session.getAttribute("institute_id");
    			VoterRegistrationDAO voter=new VoterRegistrationDAO();
     			List<VoterRegistration> rst = (List<VoterRegistration>)voter.getVoterDetailsReg(institute_id);
			for(int i=0;i<rst.size();i++){
		    		VoterRegistration obj1=(VoterRegistration)rst.get(i);

 				//check the Voter already cast vote for this election or not.
 				VotingDAO v=new VotingDAO();
    				VotingProcess voting=(VotingProcess)v.GetVoteStatus(institute_id, election_id,obj1.getEmail());
				if(voting==null){
    					SetVoter o=new SetVoter();
    					SetVoterId oi=new SetVoterId();
    					oi.setInstituteId(institute_id);
    					oi.setElectionId(election_id);
    					oi.setEnrollment(obj1.getId().getEnrollment());
    					o.setId(oi);
					 /*Admin Password Generate*/
                 			admin_password= RandomPassword.getRandomString(10);
				//	log.add( "\nOne time key is  "+admin_password);
              				//       System.out.println(admin_password);
                              		admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
 					o.setPassword(admin_password1);

            				VoterRegistrationDAO.setVoter(o);
         				
		//			System.out.println(path+obj1.getEmail()+admin_password+"One time key for voting for  : "+e.getElectionName()+" election"+"Your one time key for Voting Process for "+e.getElectionName()+" Election Only is= "+admin_password);
            				x=new Email(obj1.getEmail(),admin_password,"One time key for voting for  : "+e.getElectionName()+" election","Your one time key for casting your ballot for "+e.getElectionName()+" Election is "+admin_password);
                   			x.send();
                			log.add( "\nOne time key has been send successfully to= "+obj1.getEmail()+"\n");
                                        if(obj1.getAlternateMail()!=null)
                                        mailSend1(obj1.getAlternateMail(),admin_password,"One time key for voting for  : "+e.getElectionName()+" election","Your one time key for casting your ballot for "+e.getElectionName()+" Election Only is "+admin_password+"\n");

                                        log.add( "\nOne time key has been send successfully to Alternate Mail= "+obj1.getAlternateMail());
        
				}else{
					log.add( "\nOne time key has not been send to = "+obj1.getEmail()+ " because Voter already cast their vote"+"\n");
				}
			}
		}
		else{
			for(int i=0;i<obj.size();i++){
	     			VoterRegistration obj1=(VoterRegistration)obj.get(i);
				VotingDAO v=new VotingDAO();
				VotingProcess voting=(VotingProcess)v.GetVoteStatus(institute_id, election_id,obj1.getEmail());
				if(voting==null){
					SetVoter o=new SetVoter();
	    				SetVoterId oi=new SetVoterId();
	    				oi.setInstituteId(institute_id);
	    				oi.setElectionId(election_id);
	    				oi.setEnrollment(obj1.getId().getEnrollment());
	    				o.setId(oi);
					/*Admin Password Generate*/
                 			admin_password= RandomPassword.getRandomString(10);
                                       // admin_password="1";
					//log.add( "\nOne time key is  "+admin_password);
                			admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
					//log.add( "\nOne time key MD5  "+admin_password1);//
					//String admin_password2=PasswordEncruptionUtility.password_encrupt(admin_password1);
					//log.add( "\nOne time key MD5 MD5  "+admin_password2);//
            				o.setPassword(admin_password1);
            				VoterRegistrationDAO.setVoter(o);
	         			
            				mailSend(obj1.getEmail(),admin_password,"One time key for voting for  : "+e.getElectionName()+" election","Your one time key for casting your ballot for "+e.getElectionName()+" Election Only is "+admin_password+"\n");
                   			if(obj1.getAlternateMail()!=null)
                                        mailSend1(obj1.getAlternateMail(),admin_password,"One time key for voting for  : "+e.getElectionName()+" election","Your one time key for casting your ballot for "+e.getElectionName()+" Election Only is "+admin_password+"\n");

					log.add( "\nOne time key has been send successfully to= "+obj1.getEmail());
                                        log.add( "\nOne time key has been send successfully to Alternate Mail= "+obj1.getAlternateMail());

				}else{
					log.add( "\nOne time key has not been send to = "+obj1.getEmail()+ " because Voter already cast their vote");
				}
			}
		}
		StringBuffer str = new StringBuffer();
		//always give the path from root. This way it almost always works.
		String nameOfTextFile = "OneTimeKeylog.txt";
                UserLog.ErrorListLog(log, nameOfTextFile);
//		String path1=(String)session.getAttribute("apppath");
//		try {
//			PrintWriter pw = new PrintWriter(new FileOutputStream(path1+"/EMSLOG/"+nameOfTextFile,true));
//			for(int ii=0;ii<log.size();ii++)
//				str.append(log.get(ii)+"\n");
//		      	pw.println(str+"\n");
//                        //clean up
//                	pw.close();
//	        } catch(IOException ex) {
//       			System.out.println(ex.getMessage());
//        	}
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
