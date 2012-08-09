package com.myapp.struts;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;
import org.iitk.brihaspati.modules.utils.security.RemoteAuth;
import org.iitk.brihaspati.modules.utils.security.RemoteAuth;



public class RemoteAMU extends HttpServlet {

     protected void doGet( HttpServletRequest request,
                        HttpServletResponse response)
        throws ServletException, IOException {

      doPost(request, response);
  }


  public void doPost(HttpServletRequest request, 
	 HttpServletResponse response)
        throws ServletException, IOException
  {
      HttpSession session=request.getSession();
String     email=request.getParameter("email");

String context=request.getParameter("context");
System.out.println(context+"#######################################"+email);

session.setAttribute("context",context);

String returnurl=context+"/admin/remote1";
String resp=RemoteAuth.AuthR(email, returnurl,"amu_libms");


response.sendRedirect(resp);

  }
}