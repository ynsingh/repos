package in.ac.dei.edrp.pms.control;

import in.ac.dei.edrp.pms.viewer.checkRecord;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Savepoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
/** 
 * Creation date: 27-Nov-2010
 * @struts:action scope="null" validate="true"
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a> 
 */

public class ForwardNextView extends DispatchAction{
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward mailConfigPage(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		String forwardString="invalid";
		HttpSession session=request.getSession();
		if((String)session.getAttribute("mysession")!=null)
		{	
			if(((String)session.getAttribute("authority")).equalsIgnoreCase("Super Admin"))
			{
					forwardString="mailConfigPage";
			}
		}
		return mapping.findForward(forwardString);
		
	}
	//add new portal
	public ActionForward newPortal(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("portal");
		}
	
	/** 
	 * Method execute
	 * @param mapping It holds the action mapping information  used to invoke a struts action
	 * @param form It holds the object of bean class
	 * @param request The HTTP servlet request,which is going to be processed
	 * @param response The HTTP servlet response,which is going to be created.
	 * @return ActionForward returns neworg for next view
	 */
	public ActionForward addOrganization(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("neworg");
	}
	/** 
	 * Method execute
	 * This method returns the forward name for /viewOrganisation Action
	  * @param mapping It holds the action mapping information  used to invoke a struts action
	 * @param form It holds the object of bean class
	 * @param request The HTTP servlet request,which is going to be processed
	 * @param response The HTTP servlet response,which is going to be created.
	 * @return ActionForward returns vieworg for next view
	 */
	public ActionForward viewOrganization(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return mapping.findForward("vieworg");
		
	}
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward newRole(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		String forwardString="role";
		HttpSession session=request.getSession();
		if((String)session.getAttribute("mysession")!=null)
		{	
			if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
			{
				String add_role_permission=checkRecord.AuthorityChecker("add_role", 
					(String)session.getAttribute("roleid"));
				if(add_role_permission==null || add_role_permission.equalsIgnoreCase("Not Allow"))
				{
					request.setAttribute("message", "Sorry!!! You are not an authorized person for this operation.");
					forwardString="notAssigning";
				}
			}
		}
		return mapping.findForward(forwardString);
		
	}
	/** 
	 * Method execute
	 * This method returns the forward name for /viewRoleList Action
	  * @param mapping It holds the action mapping information  used to invoke a struts action
	 * @param form It holds the object of bean class
	 * @param request The HTTP servlet request,which is going to be processed
	 * @param response The HTTP servlet response,which is going to be created.
	 * @return ActionForward returns viewRoleList for next view
	 */
	public ActionForward viewRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return mapping.findForward("viewRoleList");
	}
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addMember(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String forwardString="member";
		HttpSession session=request.getSession();
		if((String)session.getAttribute("mysession")!=null)
		{	
		if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
		{
			String add_member_permission=checkRecord.AuthorityChecker("add_member", 
						(String)session.getAttribute("roleid"));
			if(add_member_permission==null || add_member_permission.equalsIgnoreCase("Not Allow"))
			{
				request.setAttribute("message", "Sorry!!! You are not an authorized person for this operation.");
				forwardString="notAssigning";
			}
		}
		}
		return mapping.findForward(forwardString);
		
	}
	/** 
	* Method execute
	* @param mapping
	* @param form
	* @param request
	* @param response
	* @return ActionForward
 */

	public ActionForward viewMember(ActionMapping mapping,ActionForm form,	HttpServletRequest request,HttpServletResponse response)
		 {
			HttpSession session=request.getSession();
			String retString="invalid";
			if((String)session.getAttribute("mysession")!=null)
			{		//list comes as active/inactive on date 7 april 2010
				if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))//the authority is user or super admin
					retString="viewOrgMember";
				else
					retString="viewmember";
			}
			return mapping.findForward(retString);
		 }
	
	/** 
	* Method execute
	* @param mapping
	* @param form
	* @param request
	* @param response
	* @return ActionForward
 */

	public ActionForward viewProjTeam(ActionMapping mapping,ActionForm form,	HttpServletRequest request,HttpServletResponse response)
		 {
			HttpSession session=request.getSession();
			String retString="invalid";
			if((String)session.getAttribute("mysession")!=null)
			{		retString="viewprojteam";
			}
			return mapping.findForward(retString);
		 }
	
	//add org into portal
	public ActionForward addOrgPortal(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
				
		return mapping.findForward("addorgportal");
		}
	/** 
	 * The execute method of this class is used for authenticates the user.
	 *  If the user is admin this means user can add the new project
	 *  If the user is not a admin this means he cant add the new project.
	 * @param mapping It holds the action mapping information used to invoke a Struts action.
	 * @param form 
	 * @param request The HTTP servlet request we are processing
	 * @param response The HTTP servlet response we are creating
	 * @param user It holds the user_id of that person which is currently logged in.
	 * @return ActionForward called "project or notshow", which is defined in the struts-config.xml file
 	 * For forwarding we use the {@link mapping#findForward(String) findForword} method.
	 * @see "project for calling the newproject.jsp page for input"
	 * @see "notshow for calling the login.jsp page."
	 * @see projmanage.ProjectAction 
	 * 
	 */
	public ActionForward addProject(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	
		String forwardString="project";
		HttpSession session=request.getSession();
		if((String)session.getAttribute("mysession")!=null)
		{		
		if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
		{
			String add_project_permission=checkRecord.AuthorityChecker("add_project", 
				(String)session.getAttribute("roleid"));
			if(add_project_permission==null || add_project_permission.equalsIgnoreCase("Not Allow"))
			{
				request.setAttribute("message", "Sorry!!! You are not an authorized person for this operation anil tiwari.");
				forwardString="notAssigning";
			}
		}
		}
		return mapping.findForward(forwardString);
		
	}
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward newTask(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		String forwardString="task";
		HttpSession session=request.getSession();
		if((String)session.getAttribute("mysession")!=null)
		{	
		if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
		{
			String add_role_permission=checkRecord.AuthorityChecker("assign_task", 
				(String)session.getAttribute("roleid"));
			if(add_role_permission==null || add_role_permission.equalsIgnoreCase("Not Allow"))
			{
				request.setAttribute("message", "Sorry!!! You are not an authorized person for this operation.");
				forwardString="notAssigning";
			}
		}
		}
		return mapping.findForward(forwardString);
		
	}
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward pmsHelpPage(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
			String forwardString="invalid";
			HttpSession session=request.getSession();
			Connection con=null;String query1,query2;
			PreparedStatement pst1=null,pst2=null;
			Savepoint svpt=null;
			if((String)session.getAttribute("mysession")!=null)
			{	System.out.println("forward anil");
				forwardString="pmsHelpPage";
//				try {
//					
//					con=MyDataSource.getConnection();
//					con.setAutoCommit(false);
//					
//					PreparedStatement pst=con.prepareStatement("select * from student_info");
//					ResultSet rst=pst.executeQuery();
//					query1 = "insert into student_marks values(?, ?)";
//					pst1 = con.prepareStatement(query1);
//					query2 = "insert into student_address values(?, ?)";
//					pst2 = con.prepareStatement(query2);
//					
//					int i=0;
//					while(rst.next()){
//						System.out.println(rst.getString(2));
//						i++;
//						pst1.setInt(1, rst.getInt(1));
//						pst1.setInt(2, rst.getInt(4));
//						pst1.addBatch();
//						
//						svpt = con.setSavepoint("MySavepoint");
//						
//						pst2.setInt(1, rst.getInt(1));
//						pst2.setString(2, rst.getString(5));
//						pst2.addBatch();
//						
//						pst1.executeBatch();
//						pst2.executeBatch();
//					     
//					    	 if(i==5)
//					    	 {
//					    		// con.rollback(svpt);
//					    		 i=i/0;
//					    	 }
//						}		
//				     // System.out.println("Successfully executed; totalInsertion=" + updateCounts.length);
//					     con.commit();//this commits only the statements before set save point method
//									
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					try {
//						con.rollback(svpt);
//						con.commit();
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					e.printStackTrace();
//				}catch(Exception e1){
//					try {
//					 con.rollback(svpt);
//					 con.commit();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}}
				
//				response.sendRedirect("http://localhost/index.cgi?Bugzilla_login="+session.getAttribute("uid")+"&Bugzilla_password=&GoAheadAndLogIn=Log+in");
			}
			return mapping.findForward(forwardString);
			
		}
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward loginIntoBugzilla(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
			HttpSession session=request.getSession();
			
			if((String)session.getAttribute("mysession")!=null)
			{	
				response.sendRedirect("http://localhost/index.cgi?Bugzilla_login="+session.getAttribute("uid")+"&Bugzilla_password=&GoAheadAndLogIn=Log+in");
			}
			return mapping.findForward(null);
			
		}
}
