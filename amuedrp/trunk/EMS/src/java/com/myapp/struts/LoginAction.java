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
import java.sql.*;
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

                if(login.getRole()==null) login.setRole(" ");
                if (login.getRole().equalsIgnoreCase("superadmin")) //superadmin
                {
                    //pending
                    /*con = MyConnection.getMyConnection();
                    stmt = con.prepareStatement("select * from admin_registration where status = 'NotRegistered'");
                    */
                   
                    return mapping.findForward("superadmin");
                }
                 }
                else{
                request.setAttribute("msg1","Invalid User Id or Password");
                session.setAttribute("staff_id", user_id);
                return mapping.findForward("failure");
                }
                //  System.out.println(user_id+""+password+staff_id);

                //check first login
                //ResultSet block = MyQueryResult.getMyExecuteQuery("select working_status from library where library_id='" + rst.getString("library_id") + "' and working_status='Blocked'");
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
/*
                        String sql1 = ("select * from privilege where staff_id='" + staff_id + "' and library_id='" + library_id + "'");
                        PreparedStatement st = con.prepareStatement(sql1);
                        rst1 = (List)st.executeQuery();
                        rst1.next();
                        session.setAttribute("privilege_resultset", rst1);

                        String sql2 = ("select * from acq_privilege where staff_id='" + staff_id + "' and library_id='" + library_id + "'");
                        st = con.prepareStatement(sql2);
                        rst2 = (List)st.executeQuery();
                        rst2.next();
                        session.setAttribute("acq_privilege_resultset", rst2);

                        String sql3 = ("select * from cat_privilege where staff_id='" + staff_id + "' and library_id='" + library_id + "'");
                        st = con.prepareStatement(sql3);
                        rst3 = (List)st.executeQuery();
                        rst3.next();
                        session.setAttribute("cat_privilege_resultset", rst3);

                        String sql4 = ("select * from cir_privilege where staff_id='" + staff_id + "' and library_id='" + library_id + "'");
                        st = con.prepareStatement(sql4);
                        rst4 = (List)st.executeQuery();
                        rst4.next();
                        session.setAttribute("cir_privilege_resultset", rst4);

                        String sql5 = ("select * from ser_privilege where staff_id='" + staff_id + "' and library_id='" + library_id + "'");
                        st = con.prepareStatement(sql5);
                        rst5 = (List)st.executeQuery();
                        rst5.next();
                        session.setAttribute("ser_privilege_resultset", rst5);
*/
                        return mapping.findForward("firstlogin");

                    }


/*
                    ResultSet rs1 = MyQueryResult.getMyExecuteQuery("select library_name from library where library_id='" + library_id + "'");
                    if (rs1.next()) {
                        session.setAttribute("library_name", rs1.getString(1));
                    }

                    String sql1 = ("select * from privilege where staff_id='" + staff_id + "' and library_id='" + library_id + "'");
                    PreparedStatement st = con.prepareStatement(sql1);
                    rst1 = (List)st.executeQuery();
                    rst1.next();
                    session.setAttribute("privilege_resultset", rst1);

                    String sql2 = ("select * from acq_privilege where staff_id='" + staff_id + "' and library_id='" + library_id + "'");
                    st = con.prepareStatement(sql2);
                    rst2 = (List)st.executeQuery();
                    rst2.next();
                    session.setAttribute("acq_privilege_resultset", rst2);

                    String sql3 = ("select * from cat_privilege where staff_id='" + staff_id + "' and library_id='" + library_id + "'");
                    st = con.prepareStatement(sql3);
                    rst3 = (List)st.executeQuery();
                    rst3.next();
                    session.setAttribute("cat_privilege_resultset", rst3);

                    String sql4 = ("select * from cir_privilege where staff_id='" + staff_id + "' and library_id='" + library_id + "'");
                    st = con.prepareStatement(sql4);
                    rst4 = (List)st.executeQuery();
                    rst4.next();
                    session.setAttribute("cir_privilege_resultset", rst4);

                    String sql5 = ("select * from ser_privilege where staff_id='" + staff_id + "' and library_id='" + library_id + "'");
                    st = con.prepareStatement(sql5);
                    rst5 = (List)st.executeQuery();
                    rst5.next();
                    session.setAttribute("ser_privilege_resultset", rst5);
*/
                    //System.out.println("ok1"+staff_id);
                    // System.out.println(user_id+""+password+"1");
                    request.setAttribute("msg", "You are Successfully Logged In! Further functionality is being developed...");
                    return mapping.findForward("failure");


                    //System.out.println("ok"+staff_id);
                }



        }
        else {
                request.setAttribute("msg1", "Please Enter UserId and Password");
                return mapping.findForward("failure");
            }

    }

        //login button
        else if (button.equals("Forget Password")) {
            //loginActionForm.setButton("");
           /* con = MyConnection.getMyConnection();
            stmt = con.prepareStatement("select * from login where user_id=? and question is not null");
            stmt.setString(1, user_id);
            rst = (List)stmt.executeQuery();
            if (rst.next()) {
                session.setAttribute("pass", "t");
                session.setAttribute("user_id", rst.getString("user_id"));
                session.setAttribute("username", rst.getString("user_name"));
                request.setAttribute("question", rst.getString("question"));
                request.setAttribute("staff_id", rst.getString("staff_id"));

                return mapping.findForward("forgetpassword");
            } else {

                request.setAttribute("msg", "Security question not assigned");
                return mapping.findForward("failure");
            }
*/
        }



        //  else//if(!locale.equals(locale1))
        //  {
        // if(locale.equals("English")) session.setAttribute("locale", "en");
        // else if(locale.equals("Hindi")) session.setAttribute("locale", "hi");
        //  else if(locale.equals("Urdu")) session.setAttribute("locale", "ur");
        // else if(locale.equals("Arabic")) session.setAttribute("locale", "ar");
        //return mapping.findForward("login");
        // }


        return mapping.findForward("failure");
        
    }
}
