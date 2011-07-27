package in.ac.dei.edrp.pms.bugzillaConfig;

import in.ac.dei.edrp.pms.adminConfig.ReadPropertiesFile;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;
/** 
 * Creation date: 27-Nov-2010
 * @struts:action scope="null" validate="true"
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a> 
 */

public class ForwardNextView extends DispatchAction{
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward accountCreationPage(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		MessageResources messageResources = getResources(request);
		 ActionErrors errors = new ActionErrors();
			ActionMessage error=null;
		String forwardString="invalid";
		HttpSession session=request.getSession();
		String uid = (String)session.getAttribute("uid");
		System.out.println(""+session.getAttribute("logInBugzilla"));
			if((String)session.getAttribute("logInBugzilla")!=null && ((String)session.getAttribute("logInBugzilla")).equalsIgnoreCase(uid)){
				if((String)session.getAttribute("mysession")!=null)
				{	
				forwardString="accountCreationPage";
				}			
			}
			else{
				error = new ActionMessage("msg.bugzillaLoginFailure",messageResources.getMessage("accountCreation"));
				errors.add("urlFindFail",error);
				saveErrors(request,errors); 
				forwardString="notLoggedInBuzilla";
			}
		return mapping.findForward(forwardString);
	}
	
	/** 
	 * Method urlCreationPage
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @author nupur Dixit
	 */
	public ActionForward urlCreationPage(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			String forwardString="invalid";
			HttpSession session=request.getSession();
		
			if((String)session.getAttribute("mysession")!=null)
			{	
					forwardString="urlCreationPage";
			}
			return mapping.findForward(forwardString);
		}
	
	public ActionForward loginPage(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			String forwardString="invalid";
			HttpSession session=request.getSession();
		
			if((String)session.getAttribute("mysession")!=null)
			{	
				Map<String, String> mailData = ReadPropertiesFile.mailConfig(getServlet().getServletContext().getRealPath("/")+"WEB-INF/");
				 final String url = mailData.get("url").toString();
				 System.out.println("url is ="+url);
				 forwardString="loginPage";
			}
			return mapping.findForward(forwardString);
		}
	
	 //setting for reporting a bug
	 public ActionForward setupReportBug(
				ActionMapping mapping,
				ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response) {
				String forwardString="invalid";
				HttpSession session=request.getSession();
					if((String)session.getAttribute("mysession")!=null)
					{	
					forwardString="setupReportBug";
					}			
				return mapping.findForward(forwardString);
			}
	 
	 //add project to report bugs
	 public ActionForward addProjectToReportBug(
				ActionMapping mapping,
				ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response) {
				String forwardString="invalid";
				HttpSession session=request.getSession();
			
				if((String)session.getAttribute("mysession")!=null)
				{	
					forwardString="addProjectToReportBug";
				}
				return mapping.findForward(forwardString);
			}
	 //add component to report bugs
	 public ActionForward addComponentToReportBug(
				ActionMapping mapping,
				ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response) {
				String forwardString="invalid";
				HttpSession session=request.getSession();
			
				if((String)session.getAttribute("mysession")!=null)
				{	
					forwardString="addComponentToReportBug";
				}
				return mapping.findForward(forwardString);
			}


}
