package member;

import javax.servlet.http.HttpServletRequest;

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
 * @struts:action-forward name="notassign" path="login.jsp"
 * @struts:action-forward name="assign" path="page.assignproject"
 */

public class AssignProjectAction1 extends Action {
	
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
		//String user=(String)this.getServlet().getServletConfig().getServletContext().getAttribute("uid");
		/*if(!user.equalsIgnoreCase("pms123"))
		{
			return mapping.findForward("notassign");
		}
		else*/
		return mapping.findForward("assigning");
	}
}
