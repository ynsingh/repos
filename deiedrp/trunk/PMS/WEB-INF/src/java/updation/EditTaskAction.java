package updation;
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

import dataBaseConnection.MyDataSource;

/** 
 * MyEclipse Struts
 *Creation date: 06-09-2009
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="edittask" path="edit.task"
 */

public class EditTaskAction extends Action {
	
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
		String s="fail";
		System.out.println("task id="+request.getParameter("id"));
		session.setAttribute("taskId",request.getParameter("id"));
			//session.setAttribute("projectId",request.getParameter("pName"));
			//session.setAttribute("resourceName",request.getParameter("rName"));
//			else
//				session.setAttribute("resourceName",recInfo.nextToken());
		Connection con=null;
		try{
		con=MyDataSource.getConnection();
		PreparedStatement psProjName=con.prepareStatement("select p.project_Name from project p,task t,validatetab v where "+
		"t.Task_ID=? and t.Valid_ID=v.Valid_ID and v.Project_ID=p.Project_ID and p.enable=0");
		psProjName.setString(1,request.getParameter("id"));
		ResultSet rsProjName=psProjName.executeQuery();
		if(rsProjName.next())
		{
			s="edittask";
		}
		}
		catch(Exception e){}
		finally
		{
			MyDataSource.freeConnection(con);
		}
				return mapping.findForward(s);
	}
}
