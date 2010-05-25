package in.ac.dei.edrp.pms.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 *Creation date: 04-12-2010
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="page.welcome" path="page.welcome"
 */

public class WelcomeAction extends Action{
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
		HttpServletResponse response){
		HttpSession session=request.getSession();
		String retString="invalid";
		if((String)session.getAttribute("mysession")!=null)
		{
			retString="showWelcome";
			//System.out.println("session is not null");
			if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))//the authority is user or super admin
			{
				request.setAttribute("portal", new PortalOrgBeanList((String)session.getAttribute("uid")));
				retString="showUrl";
			}
		}
		return mapping.findForward(retString);
	}
}
