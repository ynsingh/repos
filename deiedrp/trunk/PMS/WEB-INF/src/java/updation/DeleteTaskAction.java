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
 * @struts:action-forward name="deletetask" path="viewtask.jsp"
 */

public class DeleteTaskAction extends Action {
	
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
		
		String s="";
		System.out.println(" task id in delete task action file="+request.getParameter("id"));
		Connection con=null;
		try
		{
		con=MyDataSource.getConnection();   
		HttpSession session=request.getSession();
		//System.out.println("Task Name in EditTaskAction.java="+request.getParameter("id"));
		//Getting project_Name which is used for setting in session scope.
		PreparedStatement psProjName=con.prepareStatement("select p.project_Name from project p,task t,validatetab v where "+
		"t.Task_ID=? and t.Valid_ID=v.Valid_ID and v.Project_ID=p.Project_ID");
		psProjName.setString(1,request.getParameter("id"));
		ResultSet rsProjName=psProjName.executeQuery();
		rsProjName.next();
		session.setAttribute("projectName",rsProjName.getString(1));
		
		PreparedStatement ps=con.prepareStatement("update task set Dependency='' where Dependency=?");
		ps.setString(1,request.getParameter("id"));
		ps.executeUpdate();
		
	    /*delete to that record of task table which task_id will be matches.*/
		
		ps=con.prepareStatement("delete from task where Task_ID=?");
	    ps.setInt(1,Integer.parseInt(request.getParameter("id")));
	    //ps.setInt(1,rs1.getInt(1));
	    int n=ps.executeUpdate();
	    if(n>0)
	    {
	    	//session.setAttribute("taskDeletion", "yes");
	    	s="taskDeletionSuccess";
	    }
		}
		catch(Exception e)
		{
			s="taskDeletionFail";
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	    return mapping.findForward(s);
	}
}
