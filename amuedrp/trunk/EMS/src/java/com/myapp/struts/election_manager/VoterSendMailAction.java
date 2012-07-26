/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.Voter.VoterRegistrationDAO;
 import com.myapp.struts.hbm.VoterRegistration;
import com.myapp.struts.utility.Email;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import  com.myapp.struts.utility.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edrp-03
 */
public class VoterSendMailAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private final ExecutorService executor=Executors.newFixedThreadPool(1);
    	Email x;
    	private String admin_password;
    	private String admin_password1;
        String mailbody;
        String userid;
        private String institute_id;
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
HttpSession session = request.getSession();
        String email=(String)session.getAttribute("id");
        institute_id=(String)session.getAttribute("institute_id");
        userid=(String)session.getAttribute("user_id");
        List obj= (List)session.getAttribute("resultset");
		ArrayList log=new ArrayList();


if(email!=null){
        VoterRegistrationDAO vdao=new VoterRegistrationDAO();
        VoterRegistration obj1=new VoterRegistration();
        obj1=vdao.searchVoterRegistration(institute_id, email);
        
          
         mailbody=UserLog.readProperty("mail.properties", userid+"voter");

                               
	
				
               if(mailbody==null)
                {
                request.setAttribute("msg1","Please Add Candidate Mail Body");
                return mapping.findForward("success1");
                }
System.out.println("ok1");
                x=new Email(obj1.getEmail(),"","Mail From Election Manager from EMS","Dear "+obj1.getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
              //  x.send();
            log.add("Mail Send to"+obj1.getEmail());

                if(obj1.getAlternateMail()!=null){
                x=new Email(obj1.getAlternateMail(),"","Mail From Election Manager from EMS","Dear "+obj1.getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
               // x.sendAlternatemail();
                log.add("Mail Send to Alternate Mail"+obj1.getAlternateMail());
                }System.out.println("ok2");
session.removeAttribute("id");

request.setAttribute("msg", log);
//UserLog.ErrorListLog(log, path+"/web/Votermail.txt");


        return mapping.findForward(SUCCESS);

}


if(session.getAttribute("mail")!=null){

            VoterRegistrationDAO voter=new VoterRegistrationDAO();
                List<VoterRegistration> rst = (List<VoterRegistration>)voter.getVoterDetails(institute_id);
			for(int i=0;i<rst.size();i++){
                        VoterRegistration obj1=(VoterRegistration)rst.get(i);
                       
               mailbody=UserLog.readProperty("mail.properties",userid+"voter");
                                
               if(mailbody==null)
                {
                request.setAttribute("msg1","Please Add Candidate Mail Body");
                return mapping.findForward("success1");
                }

                x=new Email(obj1.getEmail(),"","Mail From Election Manager from EMS","Dear "+obj1.getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
               // x.send();
                log.add("Mail Send to"+obj1.getEmail());
                 if(obj1.getAlternateMail()!=null){
                x=new Email(obj1.getAlternateMail(),"","Mail From Election Manager from EMS","Dear "+obj1.getVoterName()+"\n"+mailbody+"\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));
                //x.sendAlternatemail();
                log.add("Mail Send to"+obj1.getEmail()+"  Along with Alternate Mail"+obj1.getAlternateMail());

                 }

       session.removeAttribute("mail");                 }
               // UserLog.ErrorListLog(log, session.getAttribute("apppath")+"/MailSendVoter.txt");
request.setAttribute("msg", log);
return mapping.findForward(SUCCESS);



}
                return null;
    }
}
