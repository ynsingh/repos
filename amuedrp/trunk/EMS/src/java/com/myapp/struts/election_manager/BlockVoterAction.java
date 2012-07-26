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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BlockVoterAction extends org.apache.struts.action.Action {
    
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


//String election_id=loginActionForm.getElectionId();
		String election=loginActionForm.getElection();
		String institute_id=(String)session.getAttribute("institute_id");
		Election e=ElectionDAO.searchElection(election, institute_id);

		List log=new ArrayList();

		List obj= (List)session.getAttribute("resultset");
               
		for(int i=0;i<obj.size();i++){
       			VoterRegistration obj1=(VoterRegistration)obj.get(i);
    			SetVoter o=(SetVoter)VoterRegistrationDAO.searchVoterList(institute_id, election,obj1.getId().getEnrollment());

			if(o==null){
			        log.add( "Sorry Voter "+ obj1.getEmail() +" is not a Voter for the Election"+e.getElectionName());
				request.setAttribute("msg", log);
				return mapping.findForward("success");
			}
//			if(o.getStatus()!=null)
//			{
//				if(o.getStatus().equalsIgnoreCase("Blocked")){
//					log.add( "Sorry Voter "+obj1.getEmail()+" is already  Blocked for the Election "+e.getElectionName());
//				}
//			}

                        else{
                           
       				o.setStatus("blocked");
            			VoterRegistrationDAO.setVoter(o);
				obj1.setStatus("block");
				VoterRegistrationDAO.update(obj1);

//         			String path = servlet.getServletContext().getRealPath("/");
//            			x=new Email(path,obj1.getEmail(),admin_password,"You Are Blocked from Election : "+election,"Sorry, You are blocked from Voting Process for The Election ="+admin_password);
//         			executor.submit(new Runnable() {
//
//                			public void run() {
//                   				x.send();
//                			}
//            			});
	            		log.add( "Voter "+obj1.getEmail()+" has been Blocked for the Election "+e.getElectionName());
			}
		}

		StringBuffer str = new StringBuffer();
                String nameOfTextFile = "BlockVoterlog.txt";
                UserLog.ErrorListLog(log, nameOfTextFile);
//
//		String path1=(String)session.getAttribute("apppath");
//			//path1=path1.substring(0,path1.lastIndexOf("/"));
//			//path1=path1.substring(0,path1.lastIndexOf("/"));
//			//path1=path1.substring(0,path1.lastIndexOf("/"));
//		try {
//	    		PrintWriter pw = new PrintWriter(new FileOutputStream(path1+"/EMSLOG/"+nameOfTextFile,true));
//			for(int ii=0;ii<log.size();ii++)
//		                str.append(log.get(ii)+"\n");
//      			        pw.println(str+"\n");
//                        //clean up
//                            	pw.close();
//                 } catch(IOException ex) {
//                        System.out.println(ex.getMessage());
//                 }

		request.setAttribute("msg", log);
		return mapping.findForward("success");
    	}
}
