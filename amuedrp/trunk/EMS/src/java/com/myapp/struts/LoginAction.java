/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;
import com.myapp.struts.hbm.*;
import javax.servlet.http.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import javax.servlet.http.HttpSession;
import com.myapp.struts.utility.*;
import com.myapp.struts.Voter.VoterRegistrationDAO;



public class LoginAction extends org.apache.struts.action.Action {

    String user_id;
    String username;
    String password;
    Connection con;
    List rst, rst1, rst2, rst3, rst4, rst5, rst6;
    PreparedStatement stmt;
    String staff_id;
    String institute_id;
    String button;
    String session_id;
    Locale locale = null;
    String locale1 = "en";
    String rtl = "ltr";
    boolean page = true;
    Hashtable hashtable;

   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
	System.gc();
        HttpSession session = request.getSession();
        
        if(session.isNew())
        {
            return mapping.findForward("out");
        }
        session_id = session.getId();
     
        try {
//            locale1 = (String) session.getAttribute("locale");

            if (session.getAttribute("locale") != null) {
                locale1 = (String) session.getAttribute("locale");
               
            } else {
                locale1 = "en";
            }
        } catch (Exception e) {
            locale1 = "en";
        }
        locale = new Locale(locale1);
        if (!(locale1.equals("ur") || locale1.equals("ar"))) {
            rtl = "LTR";
            page = true;
        } else {
            rtl = "RTL";
            page = false;
        }
        ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);


        LoginActionForm loginActionForm;
        loginActionForm = (LoginActionForm) form;

   
        user_id = loginActionForm.getUsername();
        password = loginActionForm.getPassword();
        button = loginActionForm.getButton1();


        String remote=(String)session.getAttribute("remoteauth");
        System.out.println(user_id+"Reached-LoginAction.java line 89"+remote);
        if(remote!=null){
           user_id=(String)session.getAttribute("email_id");
        }

        if(button==null) button="Log In";

        if (button.equals("Log In")) {
           if((user_id!=null ) && !(user_id.equals(""))) {

              String password1=null;
              if(password!=null)
                 password1 = PasswordEncruptionUtility.password_encrupt(password);
          
              LoginDAO dao = new LoginDAO();
           
              try {
                 System.out.println(user_id+" Reached loginAction.java line no.102, "+remote);
                 if(remote!=null) {
                    user_id=(String)session.getAttribute("email_id");
                    rst = dao.getUser(user_id);
                    rst1 = (List)dao.getUser(user_id);
                    if(rst.size()==0 || rst1.size()==0) {
                       session.removeAttribute("remoteauth");
                       request.setAttribute("msg", "Invalid User or Password");
                       return mapping.findForward("failure");
                    }
                 } else{
                    rst = dao.getLoginDetails(user_id, password);
                    rst1 = (List)dao.getLoginDetails(user_id, password1);
                 }
              } catch(Exception e){
                 request.setAttribute("msg1", "Database Not Connected! Please Contact Web Admin");
                 System.out.println("Error:"+e.getMessage());
                 return mapping.findForward("failure");
              }

              Login login=new Login();

              if (!rst.isEmpty()||!rst1.isEmpty()) { //record found
                 if (rst.isEmpty()){
                    login = (Login) rst1.get(0);}
                 else {
                    login = (Login) rst.get(0);
                 }
                 InstituteDAO  obj=new InstituteDAO();
                 Institute x= obj.getInstituteDetails(login.getStaffDetail().getId().getInstituteId());
                 if(x!=null) {
                    session.setAttribute("institute_name", x.getInstituteName());
                 }

                 session.setAttribute("institute_id", login.getStaffDetail().getId().getInstituteId());
                 session.setAttribute("username", login.getUserName());
              
                 session.setAttribute("staff_id", login.getStaffDetail().getId().getStaffId());
                 session.setAttribute("user_id", login.getUserId());
                 session.setAttribute("login_role", login.getRole());
                 session.setAttribute("password", login.getPassword());
                 session.setAttribute("loginname", login.getUserName());

                 staff_id = login.getStaffDetail().getId().getStaffId();
                 institute_id =login.getStaffDetail().getId().getInstituteId();

                 InstituteDAO insti= new InstituteDAO();
                 Institute ado=(Institute)insti.getInstituteDetails(institute_id);
                 session.setAttribute("insti",ado.getInstituteName());
                 List<Election> election = ElectionDAO.Elections(institute_id);
                 Iterator ite = election.iterator();
        
                 ArrayList electionList=new ArrayList();
                 ArrayList currentelectionList=new ArrayList();
                 ArrayList ClosedelectionList=new ArrayList();
                 ArrayList underprocessList=new ArrayList();
                 ArrayList setVoter=new ArrayList();
       
                 String status="OK";

                 List Institute = insti.getInstituteNameByStatus(status);
      
        session.setAttribute("Institute",Institute);
        while(ite.hasNext())
        {

            Calendar cal1 = Calendar.getInstance();
           Date d = cal1.getTime();
            Election elec = (Election)ite.next();
            if(elec.getNstart().before(d) && elec.getNend().after(d))
            {
               currentelectionList.add(elec);
		//setVoter.add(elec);
            }
             if(elec.getNstart().before(d) && elec.getWithdrawlEndDate().after(d))
            {
               underprocessList.add(elec);
		//setVoter.add(elec);
            }
            if(elec.getStartDate().before(d) && elec.getEndDate().after(d))
            {

                elec.setStatus("started");
                    ElectionDAO.update(elec);
                electionList.add(elec);
                //setVoter.add(elec);
                    

            }
            else if(elec.getEndDate().before(d)&& elec.getResultDeclarationDate().after(d)){
                elec.setStatus("result-wait");
                    ElectionDAO.update(elec);
            electionList.add(elec);
            }
            else if(elec.getResultDeclarationDate().before(d))
            {
                elec.setStatus("closed");
                 ElectionDAO.update(elec);
                  ClosedelectionList.add(elec);
            }
            //code on 29 Jan 2014 and commented the line setVoter.add(elec) in above conditions
            if(elec.getNstart().before(d) && elec.getEndDate().after(d))
            {
               setVoter.add(elec);
            }                      
            //end of code on 29 Jan 2014

            
        session.setAttribute("electionList", electionList);
        session.setAttribute("currentelectionList", currentelectionList);
        session.setAttribute("underprocessList", underprocessList);
        session.setAttribute("ClosedelectionList", ClosedelectionList);
       
        }
         session.setAttribute("SetVoterList", setVoter);
                if(login.getRole()==null) login.setRole(" ");
                if (login.getRole().equalsIgnoreCase("superadmin")) //superadmin
                {
                   
                    return mapping.findForward("superadmin");
                }
               
                InstituteDAO institutedao = new InstituteDAO();
                List block = institutedao.getInstituteDetailsByStatus(institute_id, "Blocked");
                Iterator it = block.iterator();

                if (it.hasNext()) {
                    request.setAttribute("msg1", "Institute is Blocked, Contact Admin.");
                    System.out.println("blocked instituteeeeeeeeeeeeeeeeeeeeeeee");
                    return mapping.findForward("failure");

                } else {
                         System.out.println("not blocked instituteeeeeeeeeeeeeeeeeeeeeeee");
//                    if (rst!= null && login.getQuestion()==null) {
//                       Institute rs1 = institutedao.getInstituteDetails(institute_id);
//
//                        if (rs1!=null) {
//
//                            session.setAttribute("institute_name", rs1.getInstituteName());
//                        }
//
//
//                        return mapping.findForward("firstlogin");
//
//
//                    }



                   

                 if(login.getRole().equalsIgnoreCase("insti-admin"))
                {
                    Institute rs1 = institutedao.getInstituteDetails(institute_id);

                        if (rs1!=null) {

                            session.setAttribute("institute_name", rs1.getInstituteName());
                        }
                    return mapping.findForward("instituteadmin");
                }
                      else if(login.getRole().equalsIgnoreCase("insti-admin,voter"))
                {
                    Institute rs1 = institutedao.getInstituteDetails(institute_id);

                        if (rs1!=null) {

                            session.setAttribute("institute_name", rs1.getInstituteName());
                        }
                    return mapping.findForward("instituteadmin");
                }

                 else if(login.getRole().equalsIgnoreCase("Election Manager"))  {

                     Institute rs1 = institutedao.getInstituteDetails(institute_id);

                        if (rs1!=null) {

                            session.setAttribute("institute_name", rs1.getInstituteName());
                        }

                       ElectionManager electionmanager=   institutedao.getElectionManagerDetailsWithStatus(login.getUserId(),institute_id,"OK");

                            if(electionmanager==null){
                                 request.setAttribute("msg1", "Election Manager is Blocked, Contact Admin.");
                                return mapping.findForward("failure");
                            }
                          if(electionmanager!=null)
                          {

                             session.setAttribute("manager_id",electionmanager.getId().getManagerId().toString());

                          }


                        rs1 = institutedao.getInstituteDetails(institute_id);

                        if (rs1!=null) {



                          session.setAttribute("voter_id",login.getStaffDetail().getId().getStaffId());
                        session.setAttribute("user_id", login.getUserId());

                        session.setAttribute("login", login);

                            session.setAttribute("institute_name", rs1.getInstituteName());
                        }






                     return mapping.findForward("electionmanager");
                 }
                     else if(login.getRole().equalsIgnoreCase("Election Manager,voter"))  {

                     Institute rs1 = institutedao.getInstituteDetails(institute_id);

                        if (rs1!=null) {

                            session.setAttribute("institute_name", rs1.getInstituteName());
                        }

                       ElectionManager electionmanager=   institutedao.getElectionManagerDetails(login.getUserId(),institute_id);


                          if(electionmanager!=null)
                          {

                             session.setAttribute("manager_id",electionmanager.getId().getManagerId().toString());

                          }






                     return mapping.findForward("electionmanager");
                 }

                    else if(login.getRole().equalsIgnoreCase("voter"))  {

                        Institute rs1 = institutedao.getInstituteDetails(institute_id);
                        VoterRegistration voter=VoterRegistrationDAO.searchVoterRegistration(institute_id,login.getStaffDetail().getId().getStaffId());
                        if(voter.getStatus().equalsIgnoreCase("Blockedfromlogin")){

                            session.setAttribute("msg5", "Sorry You are Blocked. Contact Election Manager");
                            return mapping.findForward("failure");
                        }

                        if (rs1!=null) {


                           
                          session.setAttribute("voter_id",login.getStaffDetail().getId().getStaffId());
                        session.setAttribute("user_id", login.getUserId());
      
                        session.setAttribute("login", login);

                            session.setAttribute("institute_name", rs1.getInstituteName());
                        }

                       
//
//
                           session.setAttribute("candidate_id",login.getStaffDetail().getId().getStaffId());
//
                         List<Candidate1> obj1=(List<Candidate1>) institutedao.getCandidatePosition(login.getStaffDetail().getId().getInstituteId(),login.getStaffDetail().getId().getStaffId());
                        
                        
                         if(obj1.isEmpty()==false)
                            {
                           session.setAttribute("position_id",obj1.get(0).getId().getPositionId());
                           
                            session.setAttribute("institute_name", rs1.getInstituteName());
                          session.setAttribute("user_name", login.getUserName());
//
                              Position1 pos=institutedao.getCandidatePositionName(login.getStaffDetail().getId().getInstituteId(),obj1.get(0).getId().getElectionId(),String.valueOf(obj1.get(0).getId().getPositionId()));
                           session.setAttribute("positionname", pos.getPositionName());
//
                          Election elec=ElectionDAO.searchElection(pos.getId().getElectionId(), login.getStaffDetail().getId().getInstituteId());
//
                           session.setAttribute("electionname", elec.getElectionName());
//
//
                            }
                    

                     return mapping.findForward("voter");
                 }

//                    else if(login.getRole().equalsIgnoreCase("votercandidate"))  {
//
//                        Institute rs1 = institutedao.getInstituteDetails(institute_id);
//
//                        if (rs1!=null) {
//
//
//                            session.setAttribute("candidate_id",login.getStaffDetail().getId().getStaffId());
//
//                      //      List<Candidate1> obj1=(List<Candidate1>) institutedao.getCandidatePosition(login.getStaffDetail().getId().getInstituteId(),login.getStaffDetail().getId().getStaffId());
//
//                          //  session.setAttribute("position_id",obj1.getId().getPositionId());
//
//                          //  session.setAttribute("institute_name", rs1.getInstituteName());
//                         //   session.setAttribute("user_name", login.getUserName());
//
//                          //     Position1 pos=institutedao.getCandidatePositionName(login.getStaffDetail().getId().getInstituteId(),obj1.getId().getElectionId(),String.valueOf(obj1.getId().getPositionId()));
//                         //   session.setAttribute("positionname", pos.getPositionName());
//
//                         //   Election elec=ElectionDAO.searchElection(pos.getId().getElectionId(), login.getStaffDetail().getId().getInstituteId());
//
//                          //   session.setAttribute("electionname", elec.getElectionName());
//
//
//
//                        }
//                     return mapping.findForward("candidate");
//                 }

                }



        }
        else {
                request.setAttribute("msg1", "Please Enter UserId and Password");
                return mapping.findForward("failure");
            }

    }

        } //login button
        else if (button.equals("Forget Password")) {
           /* Check Weather the Question is Assigned for the User or Not */
              
               Login obj=LoginDAO.searchForgetPassword(user_id);
                if(obj!=null)
                {
                session.setAttribute("pass","t");
                session.setAttribute("user_id", obj.getUserId());
                session.setAttribute("institute_id", obj.getStaffDetail().getId().getInstituteId());
                session.setAttribute("login_id",obj.getUserId());
                session.setAttribute("username", obj.getUserName());
                session.setAttribute("question",obj.getQuestion());
                session.setAttribute("staff_id",obj.getStaffDetail().getId().getStaffId());


                return mapping.findForward("forgetpassword");
                }
                else
                {

                    request.setAttribute("msg","Security question not assigned");
                    return mapping.findForward("failure");
                }
        }

     

        return mapping.findForward("failure");
        
    }

}
