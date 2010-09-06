package in.ac.dei.edrp.pms.member;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.checkRecord;

import java.util.ArrayList;
import java.sql.*;
/**
 * Class MemberList
 */
public class OrgMemberList{
		
	//properties
	ArrayList<MemberBean> list = new ArrayList<MemberBean>();;

	//constructors
	public OrgMemberList(){}
	//for displaying the project team
	public OrgMemberList(String project_code){
	fillProjectTeam(project_code);
	}
	
	public OrgMemberList(String type,String orgportal,String user_id,String role_in_org){
		String editPermission=checkRecord.AuthorityChecker("edit_remove_member", role_in_org);
		if(type.equalsIgnoreCase("Active"))
		fillList(orgportal,editPermission);
		else if(type.equalsIgnoreCase("Inactive"))
		fillList(type,orgportal,editPermission);
		
	}
	

	//fill the list of active users of an organization
	public void fillList(String orgportal,String editPermission){
		Connection con=null;
		try{
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql
			PreparedStatement st=con.prepareStatement("select uio.valid_user_id,v.permitted_by," +
					"p.project_name,r.role_name,v.valid_id from user_in_org uio," +
					"validatetab v,project p,role r where v.valid_user_key=uio.valid_key" +
					" and uio.valid_orgportal=? and v.valid_project_code=p.project_code and " +
					"r.role_id=v.valid_role_id and v.Status='Active'");
			st.setString(1,orgportal);
			ResultSet rs=st.executeQuery();
			while(rs.next())
			{
								
	list.add(new MemberBean(rs.getString(1),rs.getString(2),rs.getString(3),
			rs.getString(4),rs.getString(5),editPermission));

			}
			
		}
		catch(Exception e){}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	}

	//for showing the list of inactive users of an organization
	public void fillList(String uid,String orgportal,String editPermission){
		Connection con=null;
		try{
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql
			PreparedStatement st=con.prepareStatement("select u.user_id,u.first_name,u.last_name," +
					"u.skills,u.experince from user_info u,user_in_org uio " +
					"where u.user_id=uio.valid_user_id and uio.valid_orgportal=?" +
					" and uio.valid_key NOT IN(select v.valid_user_key from validatetab v)");
			st.setString(1,orgportal);
			ResultSet rs=st.executeQuery();
			while(rs.next())
			{
	list.add(new MemberBean(rs.getString(1),rs.getString(2),rs.getString(3),
			rs.getString(4),rs.getString(5),editPermission));
			}
			
		}
		catch(Exception e){System.out.println(e);}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	}
	
	public void fillProjectTeam(String project_code){
		Connection con=null;
		try{
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql
			PreparedStatement st=con.prepareStatement("select u.valid_user_id,v.Permitted_by," +
					"p.project_name,r.role_name,r.role_id from validatetab v,user_in_org u,role r,project p" +
					" where v.valid_user_key=u.valid_key and v.valid_role_id=r.role_id and" +
					" p.project_code=v.valid_project_code and v.valid_project_code=?");
			st.setString(1,project_code);
			ResultSet rs=st.executeQuery();
			while(rs.next())
			{
	list.add(new MemberBean(rs.getString(1),rs.getString(2),rs.getString(3),
			rs.getString(4),rs.getString(5),""));
			}
			
		}
		catch(Exception e){System.out.println(e);}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	}
	
		// getter and setter methods
	public ArrayList<MemberBean> getList() {
		return list;
	}
	public void setList(ArrayList<MemberBean> list) {
		this.list = list;
	}

	//used in showOrgMemberList for inactive members in case of delete
	public boolean checkUserId(String orgportal,String userid){
		Connection con=null;
		boolean bool=false;
		try{
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql
			PreparedStatement st=con.prepareStatement("select u.user_id from user_info u,user_in_org uio," +
					"user_role_in_org uro where u.user_id=uio.valid_user_id and uio.valid_orgportal=?" +
					" and u.user_id=? and uio.valid_key=uro.valid_key and uio.valid_key " +
					"NOT IN(select v.valid_user_key from validatetab v)");
			st.setString(1,orgportal);
			st.setString(2,userid);
			ResultSet rs=st.executeQuery();
			if(rs.next())
			bool=true;
			
		}
		catch(Exception e){System.out.println(e);}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return bool;
	}
}