package org.dei.edrp.pms.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 *Creation date: 06-09-2009
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="projectlist" path="page.projectlist"
 */

public class ReviewTaskAction extends Action {
	
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
		//ShowTaskForm showtaskform = (ShowTaskForm) form;// TODO Auto-generated method stub
		//System.out.println("id anil="+request.getParameter("id"));
		HttpSession session=request.getSession();
		request.setAttribute("taskList", new TaskList((String)session.getAttribute("projectName")));
		return mapping.findForward("tasklist");
	}
}
