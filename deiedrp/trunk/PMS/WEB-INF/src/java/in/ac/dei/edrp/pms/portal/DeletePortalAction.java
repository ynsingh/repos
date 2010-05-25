package in.ac.dei.edrp.pms.portal;
import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 *Creation date: 26-02-2010
 * XDoclet definition:
 * @struts:action-forward name="deleteportal" path="/WEB-INF/JspFiles/portal/ViewPortal.jsp"
 */

public class DeletePortalAction extends Action {
	
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
		
		try
		{
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql
			PreparedStatement ps=con.prepareStatement("delete from portal where Portal_Id=?");
		    ps.setInt(1,Integer.parseInt(request.getParameter("portalkey")));
		    ps.executeUpdate();
		    
		}
		catch(Exception e){}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return mapping.findForward("deleteportal");
	}
}

