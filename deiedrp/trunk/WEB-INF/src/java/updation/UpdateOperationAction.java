package updation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import dataBaseConnection.MyDataSource;
import projmanage.ProjectList;
import java.sql.*;

public class UpdateOperationAction extends Action{
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
		int flag=0;
	  	Connection con=null;
	  	HttpSession session=request.getSession();
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
	    				}
	    				else
	    				flag=0;
	    				this.getServlet().getServletConfig().getServletContext().setAttribute("update","yes");
	    }
	    catch(Exception e){}
	    finally
		{
	    	MyDataSource.freeConnection(con);
		}
		request.setAttribute("updatedList", new ProjectList(flag,uid));
		return mapping.findForward("updateList");
	}
}


