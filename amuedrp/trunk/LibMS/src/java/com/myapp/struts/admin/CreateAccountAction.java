/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.MyConnection;
import com.myapp.struts.admin.CreateAccountActionForm;
import java.io.PrintWriter;
import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;


import java.sql.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class CreateAccountAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
    
     */

    private String user_name;
    private String staff_id;
    private String password;
    private String library_id;
    private String email_id;
    private String role;
    int i;
    Connection con;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
      CreateAccountActionForm caaction=(CreateAccountActionForm)form;
      email_id=caaction.getEmail_id();
      user_name=caaction.getUser_name();
      password=caaction.getPassword();
      staff_id=caaction.getStaff_id();
      role=caaction.getRole();
      HttpSession session=request.getSession();
      library_id=(String)session.getAttribute("library_id");

      System.out.println(staff_id);

      request.setAttribute("user_id", email_id);
      request.setAttribute("user_name", user_name);
      request.setAttribute("library_id", library_id);
      request.setAttribute("staff_id", staff_id);

        request.setAttribute("staff_name", user_name);
        request.setAttribute("role", role);

       con=MyConnection.getMyConnection();

       String sql = ("INSERT INTO login(user_id,user_name,password,library_id,staff_id,role) VALUES ('" + email_id + "','" + user_name + "','" +password + "','" + library_id +"','"+staff_id+"','"+role+"')");


       
        PreparedStatement stmt=con.prepareStatement(sql);
        i=stmt.executeUpdate();
        if(i!=0)
           
      {
            if(role.equals("staff"))
            Privilege.assignStaffPrivilege(staff_id, library_id);
            else if(role.equals("admin"))
                Privilege.assignAdminPrivilege(staff_id, library_id);


         return mapping.findForward(SUCCESS);

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
        else
           return mapping.findForward(ERROR);
      }
      

        

    }

