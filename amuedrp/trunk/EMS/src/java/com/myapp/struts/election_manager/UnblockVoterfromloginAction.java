/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
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

/**
 *
 * @author edrp01
 */
public class UnblockVoterfromloginAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        HttpSession session = request.getSession();

        	DepActionForm loginActionForm;
		loginActionForm = (DepActionForm) form;


//String election_id=loginActionForm.getElectionId();
		//String election=loginActionForm.getElection();
                String election=request.getParameter("eid");
		String institute_id=(String)session.getAttribute("institute_id");
		Election e=ElectionDAO.searchElection(election, institute_id);
                String enrollment=request.getParameter("id");
		List log=new ArrayList();

		//List obj= (List)session.getAttribute("resultset");

		//for(int i=0;i<obj.size();i++){
       			VoterRegistration obj1=(VoterRegistration)VoterRegistrationDAO.searchVoterRegistration(institute_id, enrollment);
    			SetVoter o=(SetVoter)VoterRegistrationDAO.searchVoterList(institute_id, election,enrollment);

			if(obj1!=null){

			

				obj1.setStatus("REGISTERED");
				VoterRegistrationDAO.update(obj1);
                                request.setAttribute("msg1","Voter with Enrollment No:-" +enrollment+ "  Successfully Unblock");
                                System.out.println("Unblock$$$$$$$$$$$$$$$$$$$$$$");
//         			String path = servlet.getServletContext().getRealPath("/");
//            			x=new Email(path,obj1.getEmail(),admin_password,"You Are Blocked from Election : "+election,"Sorry, You are blocked from Voting Process for The Election ="+admin_password);
//         			executor.submit(new Runnable() {
//
//                			public void run() {
//                   				x.send();
//                			}
//            			});
	            		//log.add( "Voter "+obj1.getEmail()+" has been UnBlocked for the Election "+e.getElectionName());
			//}
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
