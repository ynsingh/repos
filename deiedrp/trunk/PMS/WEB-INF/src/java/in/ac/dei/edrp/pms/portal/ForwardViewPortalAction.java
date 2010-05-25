package in.ac.dei.edrp.pms.portal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 17-03-2010
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="viewPortalList" path="/WEB-INF/JspFiles/portal/ViewPortal.jsp"
 */
/**
 * This Action class is only for forwarding  the request to next view of portal list.
 */
public class ForwardViewPortalAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * This method returns the forward name for /viewPortalList Action
	  * @param mapping It holds the action mapping information  used to invoke a struts action
	 * @param form It holds the object of bean class
	 * @param request The HTTP servlet request,which is going to be processed
	 * @param response The HTTP servlet response,which is going to be created.
	 * @return ActionForward returns viewRoleList for next view
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return mapping.findForward("viewPortalList");
	}
}