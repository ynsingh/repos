/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package organization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 10-30-2009
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="searchorg" path="page.searchorganization"
 */
/**
 * It is responsible for next view.It returns the ActionForward object
 */
public class SearchOrganisationAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping It holds the action mapping information  used to invoke a struts action
	 * @param form It holds the object of bean class
	 * @param request The HTTP servlet request,which is going to be processed
	 * @param response The HTTP servlet response,which is going to be created.
	 * @return ActionForward returns searchorg for next view
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("searchorg");
	}
}