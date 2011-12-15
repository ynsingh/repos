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
import com.myapp.struts.utility.PasswordEncruptionUtility;


/**
 *
 * @author Dushyant
 */
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

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception

     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        
        if(session.isNew())
        {
            return mapping.findForward("out");
        }
        session_id = session.getId();
        System.out.println(session.getId());
        try {
            locale1 = (String) session.getAttribute("locale");





            if (session.getAttribute("locale") != null) {
                locale1 = (String) session.getAttribute("locale");
                // System.out.println("locale="+locale1);
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

        //if(loginActionForm.getButton1()!=null){
        user_id = loginActionForm.getUsername();
        password = loginActionForm.getPassword();
        button = loginActionForm.getButton1();
        //loginActionForm.setButton1(null);
            //session.setAttribute("LoginActionForm", loginActionForm);
        //}
        //else{
        /*if(session.getAttribute("staff_id")!=null){
        user_id = (String)session.getAttribute("staff_id");
        password = (String)session.getAttribute("password");
        button = "Log In";
        }
        else
        {
            user_id="";
            password="";
            button="Log In";
        }*/
            //loginActionForm = (LoginActionForm)session.getAttribute("LoginActionForm");
        //}
        
session.setAttribute("staff_id", user_id);



        System.out.println(user_id+"....."+password+"......"+button+"......");

//con=MyConnection.getMyConnection();
//            if(con==null)
//            {
//             request.setAttribute("msg1","Database Connectivity is Closed");
//             return mapping.findForward("failure");
//            }


        if (button.equals("Log In")) {
           if((user_id!=null && password!=null) && !(user_id.equals("") || password.equals("")))
        {

            String password1 = PasswordEncruptionUtility.password_encrupt(password);

            //loginActionForm.setButton("");
            LoginDAO dao = new LoginDAO();
           
            try{
                System.out.println("password="+password);
            rst = dao.getLoginDetails(user_id, password);
            System.out.println("password="+password);
            rst1 = (List)dao.getLoginDetails(user_id, password1);
            }catch(Exception e)
        {
             request.setAttribute("msg1", "Database Not Connected! Please Contact Web Admin");
             System.out.println("Error:"+e.getMessage());
             return mapping.findForward("failure");
        }

            Login login=new Login();
            if (!rst.isEmpty()||!rst1.isEmpty()) //record found
            {
            //System.out.println("ResultSet"+rst.get(0));
            
                if (rst.isEmpty()){
                login = (Login) rst1.get(0);}
            else{
                login = (Login) rst.get(0);
            }

                //session.setMaxInactiveInterval(60 * 1);

                /*  Cookie cookie = new Cookie ("library_id",rst.getString("library_id"));
                Cookie cookie1 = new Cookie ("staff_id",rst.getString("staff_id"));


                response.addCookie(cookie);
                response.addCookie(cookie1);
                 */
                String staffId = (String)session.getAttribute("staff_id");
               
          /*      if(login.getStatus().equalsIgnoreCase("login")){
                    if(login.getRole().equalsIgnoreCase("superadmin")){
                       return mapping.findForward("superadmin");
                    }
                }*/

             //   if(login.getStatus().equalsIgnoreCase("logout") && button!=null){
                InstituteDAO  obj=new InstituteDAO();
            Institute x= obj.getInstituteDetails(login.getStaffDetail().getId().getInstituteId());
if(x!=null)
{      session.setAttribute("institute_name", x.getInstituteName());
       
}



                session.setAttribute("institute_id", login.getStaffDetail().getId().getInstituteId());
                session.setAttribute("username", login.getUserName());
              
                session.setAttribute("staff_id", login.getStaffDetail().getId().getStaffId());
                session.setAttribute("user_id", login.getUserId());
                session.setAttribute("login_role", login.getRole());
                session.setAttribute("password", login.getPassword());
               // login.setStatus("login");
                //dao.update(login);
                //loginActionForm.setButton1(null);
               /* loginTempDAO logintempdao = new loginTempDAO();
                logintempdao.delete(user_id);
                logintempdao.insert(user_id);
*/
                staff_id = login.getStaffDetail().getId().getStaffId();
                institute_id =login.getStaffDetail().getId().getInstituteId();


       List<Election> election = ElectionDAO.Elections(institute_id);
        Iterator ite = election.iterator();
        //System.out.println("Election List Size="+election.size());
        ArrayList electionList=new ArrayList();
        ArrayList currentelectionList=new ArrayList();
       ArrayList ClosedelectionList=new ArrayList();
       InstituteDAO insti= new InstituteDAO();
        String status="OK";
        List Institute = insti.getInstituteNameByStatus(status);
        System.out.println( "InstituteList"+""+Institute.size());
        session.setAttribute("Institute",Institute);
        while(ite.hasNext())
        {

            Calendar cal1 = Calendar.getInstance();
           Date d = cal1.getTime();
            Election elec = (Election)ite.next();
            if(elec.getNstart().before(d) && elec.getNend().after(d))
            {
               currentelectionList.add(elec);

            }
            if(elec.getStartDate().before(d) && elec.getEndDate().after(d))
            {

                elec.setStatus("started");
                    ElectionDAO.update(elec);
                electionList.add(elec);
                
                    

            }
            else if(elec.getEndDate().before(d))
            {
                elec.setStatus("closed");
                 ElectionDAO.update(elec);
                  ClosedelectionList.add(elec);
            }
        session.setAttribute("electionList", electionList);
        session.setAttribute("currentelectionList", currentelectionList);
        session.setAttribute("ClosedelectionList", ClosedelectionList);
        }
                if(login.getRole()==null) login.setRole(" ");
                if (login.getRole().equalsIgnoreCase("superadmin")) //superadmin
                {
                    //pending
                    /*con = MyConnection.getMyConnection();
                    stmt = con.prepareStatement("select * from admin_registration where status = 'NotRegistered'");
                    */
                   
                    return mapping.findForward("superadmin");
                }
               
                InstituteDAO institutedao = new InstituteDAO();
                List block = institutedao.getInstituteDetailsByStatus(institute_id, "Blocked");
                Iterator it = block.iterator();

                if (it.hasNext()) {
                    request.setAttribute("msg1", "Institute is Blocked, Contact Admin.");
                    return mapping.findForward("failure");

                } else {

                    if (rst!= null && login.getQuestion()==null) {
                       Institute rs1 = institutedao.getInstituteDetails(institute_id);

                        if (rs1!=null) {
                            
                            session.setAttribute("institute_name", rs1.getInstituteName());
                        }


                        return mapping.findForward("firstlogin");


                    }



                   

                else if(login.getRole().equalsIgnoreCase("insti-admin"))
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
                     return mapping.findForward("electionmanager");
                 }

                    else if(login.getRole().equalsIgnoreCase("voter"))  {

                        Institute rs1 = institutedao.getInstituteDetails(institute_id);

                        if (rs1!=null) {

                            session.setAttribute("institute_name", rs1.getInstituteName());
                        }
                     return mapping.findForward("voter");
                 }

                    else if(login.getRole().equalsIgnoreCase("candidate"))  {

                        Institute rs1 = institutedao.getInstituteDetails(institute_id);

                        if (rs1!=null) {

                            session.setAttribute("institute_name", rs1.getInstituteName());
                            session.setAttribute("user_name", login.getUserName());
                        }
                     return mapping.findForward("candidate");
                 }

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