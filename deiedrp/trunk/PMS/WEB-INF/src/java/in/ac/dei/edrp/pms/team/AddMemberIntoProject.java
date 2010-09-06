package in.ac.dei.edrp.pms.team;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.CodeGenerator;
import in.ac.dei.edrp.pms.viewer.checkRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddMemberIntoProject {
	static Connection con=null;
	static PreparedStatement ps=null;
	static int value=0;
	public static int insertIntoValidatetab(String userrole,String userid,
			String pname,String orgportal,String permittedBy)
	{
		value=0;
		String valid_code=null;
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
		con=MyDataSource.getConnection();
		PreparedStatement ps=con.prepareStatement("insert into validatetab values(?,?,?,?,?,?,?)");
		PreparedStatement pst=con.prepareStatement("select max(substr(Valid_Id,8)) from validatetab where substr(Valid_Id,4,4)=Date_Format(Now(),'%Y')");
		ResultSet rst=pst.executeQuery();
		rst.next();
		String maxvalue=rst.getString(1);
		if(maxvalue!=null)
		{
			valid_code=CodeGenerator.gettingCombineCode("VID",Long.parseLong(maxvalue)+1,5);
		}
		else
			valid_code=CodeGenerator.gettingCombineCode("VID",1,5);
		//System.out.println("valid code="+valid_code);
		String roleid=checkRecord.twoFieldDuplicacyChecker("Role_Id","role","Role_Name",userrole,"ValidOrgPortal",orgportal);
		if(roleid==null)
		{
			PreparedStatement pst1=con.prepareStatement("select r.role_id from role r," +
					"user_in_org u,user_role_in_org uro where u.valid_user_id=?" +
					" and u.valid_orgportal=? and u.valid_key=uro.valid_key " +
					"and uro.permittedBy=(select l.login_user_id from login l " +
					"where l.authority='Super Admin') and uro.valid_role=r.role_id " +
					"and r.role_name=?");
			pst1.setString(1,userid);
			pst1.setString(2,orgportal);
			pst1.setString(3, userrole);
			ResultSet rst1=pst1.executeQuery();
			rst1.next();
			roleid=rst1.getString(1);
			permittedBy=checkRecord.duplicacyChecker("login_user_id","login","authority","Super Admin");
		}
		ps.setString(1,valid_code);
		ps.setString(2,checkRecord.twoFieldDuplicacyChecker("Valid_Key","user_in_org","valid_user_id",userid,"Valid_OrgPortal",orgportal));
		ps.setString(3,permittedBy);//permitted by
		ps.setString(4,checkRecord.twoFieldDuplicacyChecker("Project_Code","project","Project_Name",pname,"Valid_Org_Inportal",orgportal));
		
		ps.setString(5,roleid);
		ps.setString(6,"Default");
		ps.setString(7,"Active");
		value=ps.executeUpdate();/*if v>0 it means insertion operation successful in 'validatetab' table.*/
	
		}
		catch(Exception e)
		{
			System.out.println("error in AddMemberIntoProject.java file in insertTaskWithUser ="+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return value;
	}

}
