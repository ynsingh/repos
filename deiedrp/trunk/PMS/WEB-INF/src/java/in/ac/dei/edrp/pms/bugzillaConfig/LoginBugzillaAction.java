package in.ac.dei.edrp.pms.bugzillaConfig;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
/**
 * This class is used to login in the bugzilla screen if URL for bugzilla is given.
 * @author <a href="noopur.here@gmail.com">Nupur Dixit</a>
 *
 */

public class LoginBugzillaAction extends Action{
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	static final Logger logger = Logger.getLogger(LoginBugzillaAction.class);
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Map<String, String> urlData = ReadPropertiesFile.urlConfig(getServlet().getServletContext().getRealPath("/")+"WEB-INF/classes/");
		final String url = urlData.get("url").toString();	 
		ActionErrors errors = new ActionErrors();
		ActionMessage error=null;
		//if urlConfig.properties file is missing then url=not exist
		if(url.equalsIgnoreCase("not exist")){
			error = new ActionMessage("msg.urlFindFail");
			errors.add("urlFindFail",error);
			saveErrors(request,errors); 
			return mapping.findForward("invalid");			
		 }
		 else{			 
			 String name = (String)session.getAttribute("uid");
			 String pass = (String)session.getAttribute("pass");
			 String authority = (String)session.getAttribute("authority");
			 /*
			  * logInBugzilla attribute in session is set to uid to active other bugzilla links
			  * if user is already logged in bugzilla.
			  */
			 session.setAttribute("logInBugzilla", name);
			 System.out.println("user name is "+name);
			 if(authority.equalsIgnoreCase("Super Admin")){
				 response.sendRedirect(url+"/index.cgi?Bugzilla_login="+name+"&Bugzilla_password="+pass+"&GoAheadAndLogIn=Log+in");
			 }
			 else{
				 response.sendRedirect(url+"/index.cgi?Bugzilla_login="+name+"&Bugzilla_password=&GoAheadAndLogIn=Log+in");
			 }
		 }
		return null;
	}
}
