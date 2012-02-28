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
import com.myapp.struts.hbm.SetVoterId;
import com.myapp.struts.hbm.VoterRegistration;
import java.util.*;
import com.myapp.struts.hbm.VotingDAO;
import com.myapp.struts.hbm.VotingProcess;
import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import java.io.*;

public class VotersetAction2 extends org.apache.struts.action.Action {

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
		//Election e=ElectionDAO.searchElection(election, institute_id);
		String action=loginActionForm.getAction();
                if(action==null){
                    action="Current";
                }
                System.out.println(action+"....................");
	 	if(action.equalsIgnoreCase("All")){

		}
		else{
			for(int i=0;i<obj.size();i++){
	     			VoterRegistration obj1=(VoterRegistration)obj.get(i);
                                obj1.setAlternateMail(loginActionForm.getCreatedby());
                              //  VotingDAO v=new VotingDAO();
				//VotingProcess voting=(VotingProcess)v.GetVoteStatus(institute_id, election_id,obj1.getEmail());
				//if(voting==null){

                                VoterRegistrationDAO.update(obj1);
                                log.add( "\nAlternate Email Set  to= "+obj1.getEmail());
                              //  }
                              //  else
                               // {
                              //   log.add( "\nSorry Alternate Email not set to= "+obj1.getEmail()+" because voter already cast there vote");
                              //  }
					
			}
                }

		StringBuffer str = new StringBuffer();
		//always give the path from root. This way it almost always works.
		String nameOfTextFile = "AlternateMail.txt";
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
