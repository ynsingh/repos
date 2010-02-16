package org.dei.edrp.pms.projmanage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.dei.edrp.pms.dataBaseConnection.MyDataSource;

/** 
 * MyEclipse Struts
 *Creation date: 06-09-2009
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="projectlist" path="page.projectlist"
 */
/**
 * This action class is responsible for showing the project list.
 * It contains only one method called execute.Inside this method we writes the business logic
 * related with pagination.
 * 
 */

public class ProjectListAction extends Action {
	
/** 
	 * Method execute is used for Pagination.
	 * It means how many number of records to be shown per page.
	 * It is also used for setting the 'projectList' as an attribute in the request scope,
	 * which is used in ProjectList.jsp page.
	 * @param mapping It holds the action mapping information used to invoke a Struts action.
	 * @param form This holds the object of the bean class
	 * @param request The HTTP servlet request we are processing
	 * @param response The HTTP servlet response we are creating
	 * @param maxEntriesPerPage It holds maximum number of records for showing per page.
	 * by default value is 5.
	 * @param page It holds the position of desired page for showing ,which will be requested from user.
	 * by default is 1.
	 * @param offset It holds the position of record, from where the next records will be shown. 
	 * @return ActionForward holds the "projectlist", which is defined in the struts-config.xml file
	 * @see "projectlist" is used for calling the ProjectList.jsp page for showing the project list"
	 * @see projmanage.ProjectList 
	 * 
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		int flag=0;
	  	Connection con=null;
	  	HttpSession session = request.getSession();
		String uid=(String)session.getAttribute("uid");
	    try
	    {
	    	con=MyDataSource.getConnection();
	        		 //out.println("uid="+uid);
	    		 PreparedStatement	s=con.prepareStatement("select Authority from login where User_ID=?");
	    			s.setString(1,uid);
	    		ResultSet rs=s.executeQuery();
	    				rs.next();
	    				//out.println("authority="+rs.getString(1));
	    			if(rs.getString(1).equals("Admin"))
	    			{
	    				flag=1;
	    				uid="admin";
	    			}
	    			else
	    			{
	    				flag=0;
	    				uid="user";
	    			}
	     }
	    catch(Exception e){}
	    finally
		{
	    	MyDataSource.freeConnection(con);
		}
		request.setAttribute("projectList", new ProjectList(flag,uid));
		return mapping.findForward("projectlist");
	}
}
