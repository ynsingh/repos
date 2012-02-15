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
import com.myapp.struts.hbm.SetVoterId;
import com.myapp.struts.hbm.VoterRegistration;
import java.util.List;
import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;

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

System.out.println("ddvsdfsfgs");

String institute_id=(String)session.getAttribute("institute_id");
for(int i=0;i<obj.size();i++){

    
    VoterRegistration obj1=(VoterRegistration)obj.get(i);
    SetVoter o=new SetVoter();
    SetVoterId oi=new SetVoterId();

    oi.setInstituteId(institute_id);
    oi.setElectionId(election_id);
    oi.setEnrollment(obj1.getId().getEnrollment());
    o.setId(oi);
    



        
     /*Admin Password Generate*/
                 admin_password= RandomPassword.getRandomString(10);
   // admin_password="1";
          //       System.out.println(admin_password);
                admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);


            o.setPassword(admin_password1);

         

            
            VoterRegistrationDAO.setVoter(o);
            

	 Election e=ElectionDAO.searchElection(election, institute_id);
         String path = servlet.getServletContext().getRealPath("/");
            x=new Email(path,obj1.getEmail(),admin_password,"One time key for voting for  : "+e.getElectionName()+" election","Your one time key for Voting Process for "+e.getElectionName()+" Election Only is= "+admin_password);
//         executor.submit(new Runnable() {

  //              public void run() {
                   x.send();
    //            }
      //      });



}
request.setAttribute("msg", "Voter Successfully Added");
return mapping.findForward("success");
    }
}
