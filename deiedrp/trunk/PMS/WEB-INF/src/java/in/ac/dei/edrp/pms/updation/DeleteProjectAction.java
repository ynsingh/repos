package in.ac.dei.edrp.pms.updation;
import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



import java.sql.*;

/** 
 * MyEclipse Struts
 *Creation date: 06-09-2009
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="deleteproject" path="viewdesiredproject.jsp"
 */

public class DeleteProjectAction extends Action {
	
/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
		Connection con=MyDataSource.getConnection();   
		HttpSession session=request.getSession();
		//String forwardMsg="deleteUserProjectList";
		//if(((String)session.getAttribute("authority")).equalsIgnoreCase("Super Admin"))
			//forwardMsg="deleteAdminProjectList";
		/*delete to the desired record of project table.*/
		try
		{
	    //PreparedStatement ps=con.prepareStatement("delete from project where Project_Id=?");
			PreparedStatement ps=con.prepareStatement("update project set Enable="+
					"(case when Enable=0 then 1 else 0 end) where Project_Code=?");
	    ps.setString(1,request.getParameter("project_code"));
	    ps.executeUpdate();
	    response.sendRedirect((String)session.getAttribute("myquery"));
		}
		catch(SQLException se){}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	    return mapping.findForward(null);
	}
}
