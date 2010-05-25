package in.ac.dei.edrp.pms.role;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.checkRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class AddNewRoleAction extends Action {
	
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
		NewRoleForm newroleform = (NewRoleForm) form;
		HttpSession session=request.getSession();
		//System.out.println("role name="+newroleform.getRolename()+"member="+newroleform.getAddmember()+", org="+newroleform.getAddorg());
		Connection con=null;
		String forwardmsg="rolefail";
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
		request.setAttribute("message","New Role has not been added.");
		con=MyDataSource.getConnection();
		if(checkRecord.twoFieldDuplicacyChecker("Role_ID","role","Role_Name",newroleform.getRolename().trim(),"ValidOrgPortal",(String)session.getAttribute("validOrgInPortal"))!=null)
		{
			System.out.println("already exist.");
			return mapping.findForward("rolesuccess");
		}
		/*
		 * Inserting the record into role table.
		 */
		PreparedStatement ps=con.prepareStatement("insert into role values(?,?,?,?,NOW(),NOW(),?)");
		ps.setInt(1,0);
		ps.setString(2,newroleform.getRolename().trim());
		ps.setString(3,newroleform.getRoledescription().trim());
		ps.setString(4,(String)session.getAttribute("uid"));
		if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
		{
		ps.setString(5,(String)session.getAttribute("validOrgInPortal"));
		}
		else
			ps.setString(5,null);//in case of super admin
		int x=ps.executeUpdate();
		if(x>0) /*if x is greater than zero it means insertion operation is successful.*/
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("msg.role.added");
			errors.add("rolemsg",error);
			saveErrors(request,errors);
			/*
			 * Inserting the record into default_authority table.
			 */
			PreparedStatement authority=con.prepareStatement("insert into default_authority values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			authority.setInt(1,0);
			authority.setString(2,newroleform.getAddorg());
			authority.setString(3,newroleform.getEditorg());
			authority.setString(4,newroleform.getAddproject());
			authority.setString(5,newroleform.getEditproject());
			authority.setString(6,newroleform.getAddmember());
			authority.setString(7,newroleform.getEditmember());
			authority.setString(8,newroleform.getAssignproject());
			authority.setString(9,newroleform.getEditauthority());
			authority.setString(10,newroleform.getAssigntask());
			authority.setString(11,newroleform.getEdittask());
			authority.setString(12,newroleform.getUploaddoc());
			authority.setString(13,newroleform.getDownloaddoc());
			authority.setInt(14,Integer.parseInt(checkRecord.twoFieldDuplicacyChecker("Role_ID","role","Role_Name",newroleform.getRolename().trim(),"ValidOrgPortal",(String)session.getAttribute("validOrgInPortal"))));
			authority.setString(15,newroleform.getAddrole());
			authority.setString(16,newroleform.getEditrole());
			authority.executeUpdate();
			forwardmsg="rolesuccess"; 
		}
			
		}
		catch(Exception e)
		{
			System.out.println("error in add new role action="+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}

		return mapping.findForward(forwardmsg);
	}
}
