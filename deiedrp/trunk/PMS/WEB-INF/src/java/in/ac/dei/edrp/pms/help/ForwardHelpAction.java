package in.ac.dei.edrp.pms.help;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/** 
 * Creation date: 15-july-2010
 * @struts:action scope="null" validate="false"
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a> 
 */

public class ForwardHelpAction extends Action{
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
		String forwardString="invalid";
		HttpSession session=request.getSession();
		if((String)session.getAttribute("mysession")!=null)
		{	
			forwardString="pmsHelpPage";
		}
		return mapping.findForward(forwardString);
		
	}
}
