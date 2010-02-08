package projmanage;

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
 * @struts:action-forward name="viewproject" path="/viewproject"
 */
/**
 * This class is used when the user click on the View Project link.
 */
public class ProjectListAction1 extends Action {
	
/** 
	 * This method is used to forward the viewproject.jsp page for showing the project list. 
	 * @param mapping It holds the action mapping information used to invoke a next view.
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward that holds the "viewproject", which is defined in the struts-config.xml file
	 * For forwarding we use the {@link mapping#findForward(String) findForword} method.
	 * @see projmanage.ProjectListAction 
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		return mapping.findForward("viewproject");
	}
}
