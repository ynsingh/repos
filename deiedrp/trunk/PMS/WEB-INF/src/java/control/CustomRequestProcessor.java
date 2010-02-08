package control;

import org.apache.struts.action.RequestProcessor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CustomRequestProcessor extends RequestProcessor{

	public boolean processPreprocess(HttpServletRequest request,
			HttpServletResponse response)
			{
			 boolean continueProcessing = true; // assume success
			
			 try
			 {
				 HttpSession session = request.getSession(false);
				 if(session.getAttribute("mysession")==null)
				 {
					 continueProcessing = false;
					 
					 response.sendRedirect("login.jsp");
				 }
			 }
			 catch(Exception ioe)
			 {}
			 return continueProcessing;
			}
	public void processNoCache(HttpServletRequest request,
			HttpServletResponse response)
	{
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
		response.setDateHeader("Expires", 1);

	}
}
