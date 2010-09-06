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
		 * This constructor calls the fillList() method,
		 * which returns all the roles for view.
		 */
		public RoleList(String roleid){
		
			fillList(roleid);
		}
				
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
			String s[]=new String[14];
			try{
				for(int i=0;i<14;i++)
				{
					s[i]="Not Allow";
				}
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				PreparedStatement ps=null;
				ps=con.prepareStatement("select dfa.add_org,dfa.edit_remove_org,dfa.add_project," +
						"dfa.edit_disable_project,dfa.add_member,dfa.edit_remove_member," +
						"dfa.assign_project,dfa.edit_member_authority,dfa.assign_task,"+
				"dfa.edit_remove_task,dfa.upload_documents,dfa.dwnld_remove_doc," +
				"dfa.add_role,dfa.edit_remove_role from role r,default_authority dfa " +
				"where r.role_id=dfa.role_id and r.role_id=?");
				ps.setString(1, roleid);
				ResultSet rs=ps.executeQuery();
				int i=0;
				while(rs.next())
				{	i=0;
					while(i<14)
					{
						i++;
						if(rs.getString(i).equalsIgnoreCase("allow"))
						{							
							s[i-1]=rs.getString(i);
						}
					}
				}
				list.add(new RoleFields(s));
				
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
				"dfa.add_org,dfa.edit_remove_org,dfa.add_project,dfa.edit_disable_project,dfa.add_member,"+
				"dfa.edit_remove_member,dfa.assign_project,dfa.edit_member_authority,dfa.assign_task,"+
				"dfa.edit_remove_task,dfa.upload_documents,dfa.dwnld_remove_doc," +
				"dfa.add_role,dfa.edit_remove_role from role r,default_authority dfa "+
				"where dfa.role_id=r.Role_ID and r.Role_ID=?");
				ps.setString(1, roleid);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					list.add(new RoleFields(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
							rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),
							rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),rs.getString(17)));
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

