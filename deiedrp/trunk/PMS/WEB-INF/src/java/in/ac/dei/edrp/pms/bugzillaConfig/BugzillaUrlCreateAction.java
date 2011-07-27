package in.ac.dei.edrp.pms.bugzillaConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class BugzillaUrlCreateAction extends Action{
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		BugzillaUrlCreateForm bugForm=(BugzillaUrlCreateForm)form;
		String mapString = "";
		ActionErrors errors = new ActionErrors();
		ActionMessage error=null;
		try {			
			boolean success = WritePropertiesFile.urlConfig(getServlet().getServletContext().getRealPath("/")+"WEB-INF/classes/",
					bugForm.getUrl());
			if(success){
				error = new ActionMessage("msg.bugzillaAddUrlSuccess");
				mapString = "urlconfigsuccess";	
			}
			else{
				error = new ActionMessage("msg.bugzillaAddUrlFailure");
				mapString = "urlconfigfailure";	
			}			
			errors.add("urlConfigMsg",error);
			saveErrors(request,errors);					
		} catch (Exception e) {
			error = new ActionMessage("msg.bugzillaAddUrlFailure");
			errors.add("urlConfigMsg",error);
			saveErrors(request,errors);	
			mapString = "urlconfigfailure";			
		}
		System.out.println(bugForm.getUrl());
		return mapping.findForward(mapString);				
	}
}


