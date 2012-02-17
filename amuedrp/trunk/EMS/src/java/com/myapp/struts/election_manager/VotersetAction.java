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
		List log=new ArrayList();
		System.out.println("ddvsdfsfgs");

		String institute_id=(String)session.getAttribute("institute_id");
		Election e=ElectionDAO.searchElection(election, institute_id);

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
                		admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);
            			o.setPassword(admin_password1);
            			VoterRegistrationDAO.setVoter(o);
	
         			String path = servlet.getServletContext().getRealPath("/");

            			x=new Email(path,obj1.getEmail(),admin_password,"One time key for voting for  : "+e.getElectionName()+" election","Your one time key for Voting Process for "+e.getElectionName()+" Election Only is= "+admin_password);
                   		x.send();
		
				log.add( "\nOne time key has been send successfully to= "+obj1.getEmail());
			}else{
				log.add( "\nOne time key not Set for EMail = "+obj1.getEmail()+ " because Voter already cast their vote");

			}
		}
		StringBuffer str = new StringBuffer();
		//always give the path from root. This way it almost always works.
		String nameOfTextFile = "OneTimeKeylog.txt";
		String path1=(String)session.getAttribute("apppath");
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(path1+"/EMSLOG/"+nameOfTextFile,true));
			for(int ii=0;ii<log.size();ii++)
				str.append(log.get(ii)+"\n");
		      		pw.println(str+"\n");
                        //clean up
                		pw.close();
	        } catch(IOException ex) {
       			System.out.println(ex.getMessage());
        	}
	request.setAttribute("msg", log);
	return mapping.findForward("success");
    	}
}
