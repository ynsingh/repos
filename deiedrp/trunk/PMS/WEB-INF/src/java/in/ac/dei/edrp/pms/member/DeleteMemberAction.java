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
 *Creation date: 08-04-2010
 * XDoclet definition:
 * @struts:action-forward name="deletemember" path="page.memberList"
 */

public class DeleteMemberAction extends Action {
	
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
		try
		{
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql
			if(((String)session.getAttribute("authority")).equalsIgnoreCase("Super Admin"))
			{
			ps=con.prepareStatement("delete from user_info where User_ID=?");
		    ps.setString(1,request.getParameter("userid"));
		    ps.executeUpdate();
			}
			else//in case of user
			{
				ps=con.prepareStatement("delete from user_in_org where valid_user_id=?" +
		    			" and valid_orgportal=?");
		    	ps.setString(1,request.getParameter("userid"));
		    	ps.setString(2,(String)session.getAttribute("validOrgInPortal"));
		       if(ps.executeUpdate()>0)
		       {
		    	  ps=con.prepareStatement("delete from user_info where user_id=?" +
		    		" and user_id not in(select uo.valid_user_id from user_in_org uo)");
		    	  ps.setString(1,request.getParameter("userid"));
		    	  ps.executeUpdate();
		       }
			    
			    forwardMsg="deleteOrgmember";
			}
		   
		    
		}
		catch(Exception e){System.out.println("error in delete member action file="+e);	}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return mapping.findForward(forwardMsg);
	}
}

