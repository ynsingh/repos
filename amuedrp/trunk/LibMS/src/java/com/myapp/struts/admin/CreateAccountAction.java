/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.AdminDAO.*;
import com.myapp.struts.hbm.*;
//import javax.mail.*;
//import javax.mail.internet.*;


import java.sql.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.myapp.struts.utility.*;

/**
 *
 * @author Dushyant
 */
public class CreateAccountAction extends org.apache.struts.action.Action {
    
   

    private String user_name;
    private String staff_id;
    private String password;
    private String library_id;
    private String login_id;
    private String sublibrary_id;
    private String role;
    private boolean result;
    int i;
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
      CreateAccountActionForm accountobj=(CreateAccountActionForm)form;
      login_id=accountobj.getLogin_id();
     
      user_name=accountobj.getUser_name();
      password=accountobj.getPassword();
      staff_id=accountobj.getStaff_id();
      role=accountobj.getRole();
      sublibrary_id=accountobj.getSublibrary_id();

      
      HttpSession session=request.getSession();
      library_id=(String)session.getAttribute("library_id");
     

     

      request.setAttribute("user_id", login_id);
      request.setAttribute("user_name", user_name);
      request.setAttribute("staff_id", staff_id);
      request.setAttribute("role", role);
    
      request.setAttribute("library_id",library_id);

                        Login log=LoginDAO.searchLoginID(login_id);
                        if(log!=null)
                        {
                            String msg="Duplicate Login Id ";
                            request.setAttribute("msg", msg);
                             return mapping.findForward("duplicate");

                        }

 



  /* Use to Update Staff Entry related to Library Table & SubLibrary Table if sublibrary is changed */
            StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id,library_id);


                    staffobj.setSublibraryId(sublibrary_id);

             result=StaffDetailDAO.update1(staffobj);
                if(result==false)
                {
                    String msg="Request for registration failure due to some error";
                               request.setAttribute("msg", msg);
                               return mapping.findForward("error");

                }


              staffobj=StaffDetailDAO.searchStaffId(staff_id,library_id,sublibrary_id);
              LoginId loginIdobj=new LoginId(staff_id, library_id);
              Login logobj=new Login(loginIdobj,staffobj,login_id) ;
              password=PasswordEncruptionUtility.password_encrupt(password);
                logobj.setPassword( password);
                logobj.setRole(role);
                logobj.setSublibraryId(sublibrary_id);
                logobj.setUserName(user_name);
                logobj.setQuestion("@");
                  result=LoginDAO.insert1(logobj);
                if(result==false)
                {



                    String msg="Request for registration failure due to some error";
                    request.setAttribute("msg", msg);
                    return mapping.findForward("error");

                }
                else{
                           if(role.equals("staff"))
                                    result=CreatePrivilege.assignStaffPrivilege(staff_id, library_id,sublibrary_id);
                            else if(role.equals("admin"))
                                    result=CreatePrivilege.assignAdminPrivilege(staff_id, library_id,sublibrary_id);
                            else if(role.equals("dept-admin"))
                                   result=CreatePrivilege.assignDepartmentalAdminPrivilege(staff_id, library_id,sublibrary_id);
                            else if(role.equals("dept-staff"))
                                   result=CreatePrivilege.assignDepartmentalStaffPrivilege(staff_id, library_id,sublibrary_id);


                           if(result==false)
                           {
                               String msg="Request for registration failure due to some error";
                               request.setAttribute("msg", msg);
                               return mapping.findForward("error");
                          }
                          

                           return mapping.findForward("success");
                }

       
       

         

/*code for Java Mailing

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.println("<html><head><title>Mail Example</title></head>");
        writer.println("<body bgcolor=\"white\">");
        writer.println("<h1>Mail Example</h1>");

        ///////// set this variable to be your SMTP host

        String mailHost = "your.smtp.server";

        ///////// set this variable to be your desired email recipient

        String to = "recipient@recip.recip";

        // these variables come from the mail form

        String from = request.getParameter("from");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");

        if ((from != null) && (to != null) && (subject != null)  && (body != null)) // we have mail to send
        {

        try {


            //Get system properties
            Properties props = System.getProperties();

            //Specify the desired SMTP server
            props.put("mail.smtp.host", mailHost);

            // create a new Session object
      Session   session1 = Session.getInstance(props,null);

            // create a new MimeMessage object (using the Session created above)
            Message message = new MimeMessage(session1);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[] { new InternetAddress(to) });
            message.setSubject(subject);
            message.setContent(body, "text/plain");

            Transport.send(message);

            // it worked!
            writer.println("<b>Thank you.  Your message to " + to + " was successfully sent.</b>");

        } catch (Throwable t) {

            writer.println("<b>Unable to send message: <br><pre>");
            t.printStackTrace(writer);
            writer.println("</pre></b>");
        }


        }
        else
        {
            // no mail to send. print a blank mail form
            writer.println("<form action=\"/mine/mail\" method=\"POST\">");
            writer.println("<table border=\"0\">");
            writer.println("<tr><td>Message From: </td><td><input type=\"text\" name=\"from\"></td></tr>");
            writer.println("<tr><td>Subject: </td><td><input type=\"text\" name=\"subject\"></td></tr>");
            writer.println("<tr><td valign=\"top\">Message: </td><td><textarea name=\"body\" rows=\"10\" cols\"40\"></textarea></td></tr>");
            writer.println("<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"Send\"></td></tr>");
            writer.println("</table>");
            writer.println("</form>");
        }


        writer.println("</body>");
        writer.println("</html>");



----------------------------------*/
    }
    }
    

