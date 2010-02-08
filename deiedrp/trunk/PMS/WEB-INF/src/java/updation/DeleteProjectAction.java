package updation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dataBaseConnection.MyDataSource;

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
		
		//System.out.println("id="+request.getParameter("id"));
		Connection con=MyDataSource.getConnection();   
		String k=request.getParameter("id");
		String s="";
		/*delete to that record of project table which project_id is assigned in k variable.*/
		try
		{
//		PreparedStatement ps1=con.prepareStatement("delete from task where Project_Id=?");
//	    ps1.setString(1,k);
//	    int n=ps1.executeUpdate();
//		update project
//			set assigned = (
//			 case
//			   when assigned = 1
//			     then 0
//			   else 1
//			 end)
//			where project_id=21;
	    //PreparedStatement ps=con.prepareStatement("delete from project where Project_Id=?");

			PreparedStatement ps=con.prepareStatement("update project set Enable="+
					"(case when Enable=0 then 1 else 0 end) where Project_Id=?");
	    ps.setString(1,k);
	    int n=ps.executeUpdate();
	    if(n>0)
	    {
	    	HttpSession session=request.getSession();
			session.setAttribute("deleteoperation","yes");
	    	if(this.getServlet().getServletConfig().getServletContext().getAttribute("update").equals("yes"))
	    		s="updateoperation";
	    	else
	    		s="deleteproject";
	    }
		}
		catch(SQLException se){}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	    return mapping.findForward(s);
	}
}
