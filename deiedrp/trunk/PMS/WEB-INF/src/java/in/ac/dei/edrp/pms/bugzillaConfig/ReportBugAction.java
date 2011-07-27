package in.ac.dei.edrp.pms.bugzillaConfig;

import java.util.Map;

import in.ac.dei.edrp.pms.bugzillaConfig.ReadPropertiesFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

import javax.servlet.http.HttpSession;
/**
 * This class is used to login in the report bug screen if bugzilla is already logged in.
 * @author <a href="noopur.here@gmail.com">Nupur Dixit</a>
 */

public class ReportBugAction extends Action{
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	static final Logger logger = Logger.getLogger(ReportBugAction.class);
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MessageResources messageResources = getResources(request);		 
		HttpSession session=request.getSession();
		Map<String, String> mailData = ReadPropertiesFile.urlConfig(getServlet().getServletContext().getRealPath("/")+"WEB-INF/classes/");
		final String url = mailData.get("url").toString();		 
		ActionErrors errors = new ActionErrors();
		ActionMessage error=null;
		String uid = (String)session.getAttribute("uid");
		//url == not exist, if urlConfig.properties file is missing
		if(url.equalsIgnoreCase("not exist")){
			error = new ActionMessage("msg.urlFindFail");
			errors.add("urlFindFail",error);
			saveErrors(request,errors); 
			return mapping.findForward("invalid");			
		 }
		 else{	
			 if((String)session.getAttribute("logInBugzilla")!=null && ((String)session.getAttribute("logInBugzilla")).equalsIgnoreCase(uid)){
				 response.sendRedirect(url+"/enter_bug.cgi");
			 }
			 else{				
				 error = new ActionMessage("msg.bugzillaLoginFailure",messageResources.getMessage("reportBug"));
				 errors.add("urlFindFail",error);
				 saveErrors(request,errors); 
				 return mapping.findForward("invalid");				
			 }			 		
		 }
		return null;
	}
}
