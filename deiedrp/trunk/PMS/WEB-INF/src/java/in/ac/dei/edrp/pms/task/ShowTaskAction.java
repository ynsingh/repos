package in.ac.dei.edrp.pms.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ShowTaskAction extends Action {
	
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
		HttpSession session=request.getSession();
		String retString="invalid";
		String query=request.getQueryString();
		if((String)session.getAttribute("mysession")!=null)
		{	
			if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))//the authority is user or super admin
				retString="TaskList_ofUser";
			else
				retString="TaskList_ofAdmin";
			session.setAttribute("mytaskquery",request.getRequestURL()+"?"+query);
		}
		return mapping.findForward(retString);
	 }
	
}
