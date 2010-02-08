package task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.Action;

import org.apache.struts.action.ActionForm;

import org.apache.struts.action.ActionForward;

import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 *Creation date: 06-09-2009
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="page.newproject" path="page.newproject"
 */

public class TaskAction1 extends Action {
	
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
		HttpSession session = request.getSession();
		String user=(String)session.getAttribute("uid");
		if((user.equalsIgnoreCase("pms123")) ||(user.equalsIgnoreCase("admin"))) 
		{
			return mapping.findForward("notshowtask");
		}
		else
		return mapping.findForward("task");
	}
}
