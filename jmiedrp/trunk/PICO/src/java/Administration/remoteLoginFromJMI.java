/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author sknaqvi
 */

package Administration;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class remoteLoginFromJMI extends HttpServlet {

    private String message;

    public void setMesssge(String message) {
            this.message = message;
    }

     public String getMessage() {
        return message;
    }

     protected void doGet(  HttpServletRequest request,
                            HttpServletResponse response)
                throws ServletException, IOException {

      doPost(request, response);
    }


  public void doPost(   HttpServletRequest request,
                        HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session=request.getSession();
        String email=request.getParameter("erpmuser.getErpmuName()");

        String context=request.getParameter("context");
        System.out.println(context+"#######################################"+email);

        session.setAttribute("context",context);

        String returnurl=context+"/admin/remote1";
        //String resp=RemoteAuth.AuthR(email, returnurl,"jmi_pico");

        //response.sendRedirect(resp);
        message = "here" + returnurl + "==" + email;
  }
}