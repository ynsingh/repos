//Created by MyEclipse Struts
package in.ac.dei.edrp.pms.login;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.Action;

import org.apache.struts.action.ActionForm;

import org.apache.struts.action.ActionForward;

import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 27-09-2009
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 */
/**
 * This action class is related with Logout the user.
 */
public class LogoutAction extends Action {

/** 
 * This method is used for invalidate the session of user.
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return ActionForward contains Logout for calling the login.jsp page.
 */
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	HttpSession session = request.getSession(false);
//	System.out.println("session="+session.isNew());
		if(session!=null)
		{
			session.invalidate();
			//return mapping.findForward("Logout"); 
		}
		//else
		//{
			return mapping.findForward("invalid");
		//}
	}
}