package in.ac.dei.edrp.pms.portal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 *Creation date: 16-03-2010
 * XDoclet definition:
 * @struts:action scope="session" validate="true"
 * @struts:action-forward name="portal" path="newportal"
 */
/**
 * This action class is used for creating portal.
 * Related with create portal link.
 */

public class ForwardPortalAction extends Action {
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	return mapping.findForward("portal");
	}
}
