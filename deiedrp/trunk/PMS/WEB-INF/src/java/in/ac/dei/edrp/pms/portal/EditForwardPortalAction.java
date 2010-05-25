package in.ac.dei.edrp.pms.portal;
import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.rowset.CachedRowSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 *Creation date: 17-03-2010
 * XDoclet definition:
 * @struts:action scope="session" validate="true"
 * @struts:action-forward name="editportal" path="edit.portal"
 */

public class EditForwardPortalAction extends Action {
	
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
		String forwardmsg="editportalfail";
		
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
		//request.setAttribute("message","The desired portal has been deleted.");
		con=MyDataSource.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from portal p"+
		" where p.Portal_Id=?");
	    ps.setString(1,request.getParameter("portalkey"));
	    ResultSet rs_portal=ps.executeQuery();
	    CachedRowSet crs = new CachedRowSetImpl();
	    crs.populate(rs_portal);
	    
	    if(crs.next())
	    {
	    	request.setAttribute("crs", crs);
	    	forwardmsg="editportal";
	    }
		//System.out.println("portal id="+request.getParameter("portalkey"));
		}
		catch(Exception e){}
		finally
		{
			MyDataSource.freeConnection(con);
		}

		return mapping.findForward(forwardmsg);
	}

}
