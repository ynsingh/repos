package in.ac.dei.edrp.pms.role;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.checkRecord;
import java.util.ArrayList;
import java.sql.*;
/**
 * Class RoleList
 * This class is responsible for getting the values of all roles details.
 * 
 */

public class RoleList {

		ArrayList<RoleFields> list = new ArrayList<RoleFields>();
		//uid means email id of login person
		public RoleList(String roleOrAuthority,String orgportal,String roleid){

			String edit=checkRecord.AuthorityChecker("edit_remove_role", roleid);
//			System.out.println("edit authority="+edit);
				fillList(roleOrAuthority,orgportal,edit);
		}
		
		/*
		 * This constructor calls the fillList(roleid) method,
		 * which returns all authorities of the desired role for view.
		 */
		public RoleList(String roleid){
		
			fillList(roleid);
		}
				
		/*
		 * it is used for adding new role and editing the roles.
		 */
		public RoleList(String userid,int validorgportal,String role_in_org){
			
			fillRoleList(role_in_org);
		}
		/**
		 * fill the list for viewing the role list
		 */
		
		public void fillList(String roleOrAuthority,String orgportal,String edit){
			Connection con=null;
			try{
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				PreparedStatement ps=null;
				if(roleOrAuthority.equalsIgnoreCase("super admin"))//it can be user/super admin
				{
					ps=con.prepareStatement("select role_id,Role_Name,description,Created_By,DATE_FORMAT(Created_On,'%d-%b-%Y %r')," +
							"DATE_FORMAT(Updated_On,'%d-%b-%Y %r') from role where ValidOrgPortal IS NULL");// 'where ValidOrgPortal IS NULL' for showing only those roles which is created by super admin   
				}
				else if(roleOrAuthority.equalsIgnoreCase("user"))
				{
					ps=con.prepareStatement("select role_id,Role_Name,description,Created_By,DATE_FORMAT(Created_On,'%d-%b-%Y %r')," +
							"DATE_FORMAT(Updated_On,'%d-%b-%Y %r') from role where ValidOrgPortal=?");
					ps.setString(1,orgportal);
				}
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					list.add(new RoleFields(rs.getString(1),rs.getString(2),rs.getString(3),
							rs.getString(4),rs.getString(5),rs.getString(6),roleOrAuthority,edit));
				}
				
			}
			catch(Exception e){}
			finally
			{
				MyDataSource.freeConnection(con);
			}
		}
		
		public void fillRoleList(String roleid){
			Connection con=null;
			//String s[]=new String[14];
			try{
				
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				PreparedStatement ps=null;
				ps=con.prepareStatement("select dfa.authorities" +
						" from role r,default_authority dfa " +
				"where r.role_id=dfa.role_id and r.role_id=? order by dfa.authorities asc");
				ps.setString(1, roleid);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next())
				{
					list.add(new RoleFields(rs.getString(1)));
				}
				
				
			}
			catch(Exception e){System.out.println("error in role list anil="+e);}
			finally
			{
				MyDataSource.freeConnection(con);
			}
		}
		
		
		public void fillList(String roleid){
			Connection con=null;
			try{
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				PreparedStatement ps=null;
				ps=con.prepareStatement("select r.role_id,r.Role_Name,r.description,"+
				"dfa.authorities from role r,default_authority dfa "+
				"where dfa.role_id=r.Role_Id and r.Role_Id=?");
				ps.setString(1, roleid);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					list.add(new RoleFields(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
				}
			}
			catch(Exception e){}
			finally
			{
				MyDataSource.freeConnection(con);
			}
		}
		
		/**
		 *  getter and setter methods for list
		 */
		public ArrayList<RoleFields> getList() {
			return list;
		}
		
		public void setList(ArrayList<RoleFields> list) {
			this.list = list;
		}
		
	}

