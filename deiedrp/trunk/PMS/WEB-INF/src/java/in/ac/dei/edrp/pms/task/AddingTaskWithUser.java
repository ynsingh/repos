package in.ac.dei.edrp.pms.task;
import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.checkRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.StringTokenizer;

public class AddingTaskWithUser {
	static Connection con=null;
	static PreparedStatement ps=null;
	static int value=0;
	public static int insertTaskWithUser(String assignedTo,String taskid,String project_code,String orgportal)
	{
		value=0;
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
		con=MyDataSource.getConnection();
		StringTokenizer stringTokenizer = new StringTokenizer(assignedTo,"()");
		String userid=stringTokenizer.nextToken().trim();
		String userrole=stringTokenizer.nextToken();
		
		String roleid=checkRecord.twoFieldDuplicacyChecker("Role_Id","role","Role_Name",userrole,"ValidOrgPortal",orgportal);
		if(roleid==null)
		{
			ps=con.prepareStatement("select r.role_id from role r," +
					"user_in_org u,user_role_in_org uro where u.valid_user_id=?" +
					" and u.valid_orgportal=? and u.valid_key=uro.valid_key " +
					"and uro.permittedBy=(select l.login_user_id from login l " +
					"where l.authority='Super Admin') and uro.valid_role=r.role_id " +
					"and r.role_name=?");
			ps.setString(1,userid);
			ps.setString(2,orgportal);
			ps.setString(3, userrole);
			ResultSet rst=ps.executeQuery();
			rst.next();
			roleid=rst.getString(1);
		}
			
		ps=con.prepareStatement("insert into task_with_user values(?,?)");
			ps.setString(1,taskid);
			
			PreparedStatement ps_validid=con.prepareStatement("select distinct v.valid_id from" +
					" validatetab v where v.valid_user_key=(select u.valid_key from " +
					"user_in_org u where u.valid_user_id=? and u.valid_orgportal=?)" +
					" and v.valid_project_code=? and v.valid_role_id=?");
			ps_validid.setString(1,userid);
			ps_validid.setString(2,orgportal);
			ps_validid.setString(3,project_code);
			ps_validid.setString(4,roleid);
			ResultSet rs=ps_validid.executeQuery();
			if(rs.next())
			{
				ps.setString(2,rs.getString(1));
			}
			value=ps.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.println("error in AddingTaskWithUser.java file in insertTaskWithUser ="+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return value;
	}
	
}
