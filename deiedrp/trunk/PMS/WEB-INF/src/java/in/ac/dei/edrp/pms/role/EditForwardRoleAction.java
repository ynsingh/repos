package in.ac.dei.edrp.pms.role;
import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sun.rowset.CachedRowSetImpl;

/** 
 * MyEclipse Struts
 *Creation date: 24-02-2010
 * XDoclet definition:
 * @struts:action scope="session" validate="true"
 * @struts:action-forward name="editrole" path="edit.role"
 */

public class EditForwardRoleAction extends Action {
	
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
		//return mapping.findForward("editrole");
		Connection con=null;
		String forwardmsg="editrolefail";
		
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
		//request.setAttribute("message","The desired portal has been deleted.");
		con=MyDataSource.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from role r,default_authority d"+
	" where r.Role_ID=? and r.role_id=d.role_id");
	    ps.setString(1,request.getParameter("rolekey"));
	    ResultSet rs_role=ps.executeQuery();
	    CachedRowSet crs = new CachedRowSetImpl();
	    crs.populate(rs_role);
	    
		    if(crs.next())
		    {
		    	request.setAttribute("crs", crs);
		    	forwardmsg="editrole";
		    }
		}
		catch(Exception e){}
		finally
		{
			MyDataSource.freeConnection(con);
		}

		return mapping.findForward(forwardmsg);
	}

}


