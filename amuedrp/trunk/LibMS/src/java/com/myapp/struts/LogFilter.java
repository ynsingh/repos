// Import required java libraries
package com.myapp.struts;
import javax.servlet.*;
import javax.servlet.http.*;
import com.myapp.struts.utility.DateCalculation;
//import org.apache.log4j.Logger;
import com.myapp.struts.utility.writeLog;


 
// Implements Filter class
public class LogFilter implements Filter  {
   public void  init(FilterConfig config) 
                         throws ServletException{
      // Get init parameter 
    //  String testParam = config.getInitParameter("test-param");
 
      //Print the init parameter 
    //  System.out.println("Test Param: " + testParam);
   }
   public void  doFilter(ServletRequest request, 
                 ServletResponse response,
                 FilterChain chain) 
                 throws java.io.IOException, ServletException {

      //  Logger systemlog = Logger.getLogger(LoginAction.class);


       // Get the IP address of client machine.
      String ipAddress = request.getRemoteHost();
      HttpServletResponse resp=(HttpServletResponse)response;
 
      // Log the IP address and current timestamp.
      HttpServletRequest req=(HttpServletRequest)request;
      HttpSession session=req.getSession();
//List arr=new ArrayList();
 //arr.add(req.getRequestURI());
 //session.setAttribute("arr", arr);


      if(req.getRequestURI().endsWith(".jsp")||req.getRequestURI().endsWith(".do"))
      {
           if(!req.getRequestURI().contains("OPAC"))
          if(((String)session.getAttribute("username")!=null) && ((String)session.getAttribute("library_id")!=null) && ((String)session.getAttribute("sublibrary_id")!=null) &&  ((String)session.getAttribute("login_id")!=null) && ((String)session.getAttribute("login_role")!=null))
          {
System.out.println("*************************    Log Information   ************************************");
            System.out.println("Request for URL="+ req.getRequestURI() +" By UserName="+((String)session.getAttribute("username"))+" From Libraary="+((String)session.getAttribute("library_id"))+ " SubLibrary="+((String)session.getAttribute("sublibrary_id"))+" LoginID="+((String)session.getAttribute("login_id"))+" Having Role="+ ((String)session.getAttribute("login_role")+" At "+DateCalculation.dateTime().toString()));
System.out.println("*************************************************************");


//Log Data in Log File on Apache Server
   // systemlog.info("Request for URL="+ req.getRequestURI() +" By UserName="+((String)session.getAttribute("username"))+" From Libraary="+((String)session.getAttribute("library_id"))+ " SubLibrary="+((String)session.getAttribute("sublibrary_id"))+" LoginID="+((String)session.getAttribute("login_id"))+" Having Role="+ ((String)session.getAttribute("login_role")+" At "+DateCalculation.dateTime().toString()));

// Logs obj=new Logs();
  //        obj.setUrl(req.getRequestURI());
    //      obj.setDate(DateCalculation.dateTime().toString());
      //    obj.setUserId(session.getAttribute("login_id").toString());
        //  obj.setLibraryId((String)session.getAttribute("library_id"));
        //  obj.setSublibraryId((String)session.getAttribute("sublibrary_id"));
        //  obj.setUsername((String)session.getAttribute("username"));
        //  obj.setRole((String)session.getAttribute("login_role"));
//System.out.println("Object"+obj);
          //LogsDAO.insertLog(obj);

          writeLog.writelog(req.getRealPath(req.getContextPath().toString()),DateCalculation.dateTime().toString(), ipAddress, req.getRequestURI(),((String)session.getAttribute("username")),((String)session.getAttribute("library_id")), ((String)session.getAttribute("sublibrary_id")),((String)session.getAttribute("login_id")), ((String)session.getAttribute("login_role")));
           }
         
        //  System.out.println("Log Info:"++ipAddress);
          

      //    code to log Activity of Login User
//System.out.println("Header Info111"+req.getHeader("X-Forwarded-For")+req.getLocalAddr()+req.getRemoteUser()+req.getRemoteAddr()+req.getRemoteHost());
         
        
 
          //RequestDispatcher view = req.getRequestDispatcher("/writelog");
 //view.forward(req, resp);

      }
      // Pass request back down the filter chain
      chain.doFilter(request,response);
   }
   public void destroy( ){
      /* Called before the Filter instance is removed 
      from service by the web container*/
   }
}

