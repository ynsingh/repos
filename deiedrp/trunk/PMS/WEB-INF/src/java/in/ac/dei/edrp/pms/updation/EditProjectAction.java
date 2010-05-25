package in.ac.dei.edrp.pms.updation;
import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sun.rowset.CachedRowSetImpl;

/** 
 * MyEclipse Struts
 *Creation date: 06-09-2009
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="editproject" path="edit.project"
 */

public class EditProjectAction extends Action {
	
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
		HttpSession session=request.getSession();
		Connection con=null;
		String forwardmsg="projectupdationfail";
		
		try{
			request.setAttribute("message","Project updation failed!!");
			/*
			 * This method Established the connection from the database MySql
			 */
		//request.setAttribute("message","The desired portal has been deleted.");
		con=MyDataSource.getConnection();
		session.setAttribute("projectkey",request.getParameter("project_code"));
		PreparedStatement ps=con.prepareStatement("select p.project_code,p.project_name," +
				"p.schedule_start_date,p.schedule_end_date,p.actual_start_date," +
				"p.actual_end_date,p.target_budget,p.priority,p.status,p.gchart_color," +
				"p.description,max(t.actual_end_date) from project p,task t"+
	" where p.project_code=? and p.project_code=t.vproject_code");
	    ps.setString(1,request.getParameter("project_code"));
	    ResultSet rs_project=ps.executeQuery();
	    CachedRowSet crs = new CachedRowSetImpl();
	    crs.populate(rs_project);
	    
	    if(crs.next())
	    {
	    	request.setAttribute("crs", crs);
	    	forwardmsg="editproject";
	    }
		//System.out.println("role id="+request.getParameter("rolekey"));
		}
		catch(Exception e){}
		finally
		{
			MyDataSource.freeConnection(con);
		}

		return mapping.findForward(forwardmsg);

	}
}
