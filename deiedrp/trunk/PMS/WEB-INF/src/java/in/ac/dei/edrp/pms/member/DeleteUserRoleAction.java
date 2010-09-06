package in.ac.dei.edrp.pms.member;
import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 *Creation date: 09-04-2010
 * XDoclet definition:
 * @struts:action-forward name="deleteuserrole" path="page.memberList"
 */

public class DeleteUserRoleAction extends Action {
	
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
		PreparedStatement ps=null;
		HttpSession session=request.getSession();
		String forwardMsg="deletemember";
		System.out.println("valid_key="+request.getParameter("valid_key")+" role name"+request.getParameter("roleName"));
		try
		{
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql
			//in case of super admin for active members
			if(((String)session.getAttribute("authority")).equalsIgnoreCase("Super Admin"))
			{
				ps=con.prepareStatement("delete from user_role_in_org where valid_key=? and valid_role=" +
					"(select role_id from role where role_name=? and validorgportal is null)");
				ps.setString(1,request.getParameter("valid_key"));
				ps.setString(2,request.getParameter("roleName"));
				if( ps.executeUpdate()>0)
				{
					ps=con.prepareStatement("delete from user_in_org where valid_user_id=?" +
		    			" and valid_key NOT IN" +
		    		"(select uro.valid_key FROM user_role_in_org uro)");
					ps.setString(1,request.getParameter("userid"));
					if( ps.executeUpdate()>0)
					{
						ps=con.prepareStatement("delete from login where authority!='Super Admin'" +
		    		" and login_user_id not in(select uo.valid_user_id from user_in_org uo)");
						ps.executeUpdate();
					}
				}
			}
			else//in case of user for active members
			{
				ps=con.prepareStatement("delete from validatetab where valid_id=?");
					ps.setString(1,request.getParameter("valid_key"));
					System.out.println(request.getParameter("valid_key"));
					ps.executeUpdate();
					forwardMsg="deleteOrgmember";
			}
		}
		catch(Exception e){System.out.println("error in deleteuser role action file ="+e);	}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return mapping.findForward(forwardMsg);
	}
}

