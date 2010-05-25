package in.ac.dei.edrp.pms.addorg_in_portal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 *Creation date: 19-03-2010
 * XDoclet definition:
 * @struts:action scope="session" validate="true"
 * @struts:action-forward name="addorgportal" path="/WEB-INF/JspFiles/addorg_in_portal/AddOrgInPortal.jsp"
 */
/**
 * This action class is used for creating portal.
 * Related with create portal link.
 */

public class ForwardAddOrgInPortalAction extends Action {
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	return mapping.findForward("addorgportal");
	}
}