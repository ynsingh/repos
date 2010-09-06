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
 */
/**
 * This class is responsible for setting the list of portal with their details
 */
public class PortalListAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping It holds the action mapping information  used to invoke a struts action
	 * @param form It holds the object of bean class
	 * @param request The HTTP servlet request,which is going to be processed
	 * @param response The HTTP servlet response,which is going to be created.
	 * @return ActionForward returns portallist for next view
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			request.setAttribute("portalList", new PortalList());
			return mapping.findForward("portallist");
		}
	}
