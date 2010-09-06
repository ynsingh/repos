package in.ac.dei.edrp.pms.task;

import in.ac.dei.edrp.pms.viewer.checkRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/** 
 * MyEclipse Struts
 *Creation date: 11-05-2010
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="page.assigntask" path="assigntask"
 */

public class ForwardAssignTaskAction extends Action{
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
		String forwardString="assigntask";
		HttpSession session=request.getSession();
		if((String)session.getAttribute("mysession")!=null)
		{	
		if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
		{
			String add_role_permission=checkRecord.AuthorityChecker("assign_task", 
				(String)session.getAttribute("roleid"));
			if(add_role_permission==null || add_role_permission.equalsIgnoreCase("Not Allow"))
			{
				request.setAttribute("message", "Sorry!!! You are not an authorized person for this operation.");
				forwardString="notAssigning";
			}
		}
		}
		return mapping.findForward(forwardString);
		
	}
}
