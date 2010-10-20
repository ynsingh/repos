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
 *Creation date: 14-05-2010
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="edittask" path="edit.task"
 */

public class ForwardEditTaskAction extends Action {
	
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
		Connection con=null;
		String forwardmsg="updationfail";
		HttpSession session=request.getSession();
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
		con=MyDataSource.getConnection();
		session.setAttribute("taskkey",request.getParameter("taskkey"));
		session.setAttribute("projectkey",request.getParameter("projectkey"));
		PreparedStatement ps=con.prepareStatement("select p.project_name,t.task_id,t.task_name," +
				"t.no_of_days,t.schedule_start_date,t.schedule_end_date,t.actual_start_date," +
				"t.actual_end_date,t.gchart_color,t.per_completed,t.task_status,t.dependency," +
				"t.description,p.actual_start_date,p.actual_end_date,p.project_code from task t,project p " +
				"where t.vproject_code=p.project_code " +
				"and t.task_id=?");
	    ps.setString(1,request.getParameter("taskkey"));
	    ResultSet rs_role=ps.executeQuery();
	    CachedRowSet crs = new CachedRowSetImpl();
	    crs.populate(rs_role);
	    
	    if(crs.next())
	    {
	    	request.setAttribute("crs", crs);
	    	forwardmsg="edittask";
	    }
		//System.out.println("task id="+request.getParameter("taskkey"));
		}
		catch(Exception e){}
		finally
		{
			MyDataSource.freeConnection(con);
		}

		return mapping.findForward(forwardmsg);
	}
	
}
