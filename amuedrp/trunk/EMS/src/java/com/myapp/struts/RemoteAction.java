

package com.myapp.struts;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.http.*;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;

public class RemoteAction extends HttpServlet {
     

  public void service(HttpServletRequest request,
	 HttpServletResponse response)
        throws ServletException, IOException
  {
HttpSession session=request.getSession();

            
//Check Authetication Part
session.setAttribute("remoteauth", "OK");


System.out.println("In Success Page");
String hdir=System.getProperty("user.home");
String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
String line=ReadNWriteInTxt.readLin(path,"amu_ems");
String skey=StringUtils.substringBetween(line, ";",";");

String rand=request.getParameter("rand");
String hash=request.getParameter("hash");
String encd=request.getParameter("encd");
String context=(String)session.getAttribute("context");
String returnurl=context+"/login.do";
String returnurl1=context+"/login.jsp";
                        String enUrl1=EncrptDecrpt.decrypt(encd,"amu_ems");
                        System.out.println("message in Success screen aa =="+enUrl1);
                        String email1=StringUtils.substringBetween(enUrl1,"email=","&sess=");
                        System.out.println("message in Success screen bb ==  "+email1);
                        String sessno=StringUtils.substringAfter(enUrl1,"&sess=");
                        System.out.println("message in Success screen 3 =="+enUrl1+" "+email1+" "+sessno);

                        String hashcode=EncrptDecrpt.keyedHash(email1, rand, skey);
boolean matched=hashcode.equals(hash);

String filepath=hdir+"/remote_auth/remote-user.txt";
session.setAttribute("email_id", email1);
  System.out.println("USER................."+matched);
if(matched)
    {
            //boolean verified=ReadNWriteInTxt.readF(filepath, "email;session");

            //System.out.println(verified);

            //if(verified)
        //{
           response.sendRedirect(returnurl);
        //}
        //else
        //{
              
          //      response.sendRedirect(returnurl1+"?m=Invalid User or Password");
        //}
}
else
{
    
    response.sendRedirect(returnurl1+"?m=Invalid User or Password");
}



    }
}
