package in.ac.dei.edrp.pms.projmanage;
import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.checkRecord;

import java.util.ArrayList;
import java.sql.*;
/**
 * The class ProjectList is used for maintaining the list of project .
 * It maintains the pagination and how the records will be displayed in per page.
 * This class contains those methods which is related with displaying the project list
 * as well as searching the project.
 */

public class ProjectList {

		//properties
		ArrayList<ProjectFields> list = new ArrayList<ProjectFields>();
				
		//constructors
		
		/**
		 * This constructor is used for initialising the offset and length 
		 * that is used for displaying the records of project.
		 * Inside this constructor we calls the {@link #fillList(int,String) fillList} method.
		 * @param offset It holds the position of record, from where the next records will be shown.
		 * @param length It holds maximum number of records for showing per page.
		 */
		public ProjectList(String portal,String orgname){
			//in case of superadmin
			fillProjectList(portal,orgname,"");
		}
		/**
		 * This constructor is used for maintaining the project list related with update operation.
		 * Inside this we call the {@link #fillList(int,String) fillList} method.
		 * @param flag It holds only 0 or 1 
		 * @since 1 for admin and 0 for normal user.
		 * @param uid It holds the user id of that person which is currently logged in.
		 */
		public ProjectList(String user_id,String org_portal,String role_in_org)
		{
			String editPermission=checkRecord.AuthorityChecker("edit_disable_project", user_id, org_portal,role_in_org);
			fillProjectList(org_portal,editPermission);	
		}

		
		/**
		 * This method is used for filling the project list in the ArrayList object.
		 * @param flag It holds only 0 or 1 
		 * @since 1 for admin and 0 for normal user.
		 * @param uid It holds the user id of that person which is currently logged in.
		 * @see projmanage.ProjectFields
		 */
		public void fillProjectList(String org_portal,String editPermission){
			Connection con=null;
			try{
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				PreparedStatement ps=null;
				ResultSet rs=null;
				
					//System.out.println("for user");
					ps=con.prepareStatement("select p.project_code,p.project_name," +
							"DATE_FORMAT(p.schedule_start_date,'%d-%b-%Y')," +
							"DATE_FORMAT(p.schedule_end_date,'%d-%b-%Y')," +
							"DATE_FORMAT(p.actual_start_date,'%d-%b-%Y')," +
							"DATE_FORMAT(p.actual_end_date,'%d-%b-%Y')," +
							"p.target_budget,p.priority,p.status,p.gchart_color," +
							"p.description,p.Enable from project p " +
							"where p.valid_org_inportal=? order by p.Project_Code asc");
					ps.setString(1,org_portal);
					rs=ps.executeQuery();
					while(rs.next())
					{
										
			list.add(new ProjectFields(rs.getString(1),rs.getString(2),rs.getString(3),
					rs.getString(4),rs.getString(5),rs.getString(6),
					Integer.parseInt(rs.getString(7)),rs.getString(8),rs.getString(9),
					rs.getString(10),rs.getString(11),rs.getString(12),editPermission));

					}
				}
			catch(Exception e)
			{
				//System.out.println("error="+e);
			}
			finally
			{
				MyDataSource.freeConnection(con);
			}
		}
		
		
		public void fillProjectList(String portal,String orgname,String s){
			Connection con=null;
			try{
				//System.out.println("for super admin");
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				PreparedStatement ps=null;
				ResultSet rs=null;
				ps=con.prepareStatement("select p.project_code,p.project_name," +
						"DATE_FORMAT(p.schedule_start_date,'%d-%b-%Y')," +
						"DATE_FORMAT(p.schedule_end_date,'%d-%b-%Y')," +
						"DATE_FORMAT(p.actual_start_date,'%d-%b-%Y')," +
						"DATE_FORMAT(p.actual_end_date,'%d-%b-%Y')," +
						"p.target_budget,p.priority,p.status,p.gchart_color," +
						"p.description,p.Enable" +
						" from project p,portal port,organisation o,org_into_portal oip" +
						" where p.valid_org_inportal=oip.valid_org_inportal " +
						"and oip.org_id=o.org_id and oip.portal_id=port.portal_id" +
						" and port.portal_name=? and o.org_name=? order by p.Project_Code asc");
				ps.setString(1,portal);
				ps.setString(2,orgname);
				rs=ps.executeQuery();
						while(rs.next())
						{
							list.add(new ProjectFields(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						Integer.parseInt(rs.getString(7)),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),""));
						}
					}
					catch(Exception e)
					{
						//System.out.println("error="+e);
					}
					finally
					{
						MyDataSource.freeConnection(con);
					}
				}
	
		
		/**
		 * It is used for getting the project list.
		 * @return List of project.
		 */
		public ArrayList<ProjectFields> getList() {
			return list;
		}
		
	    /**
	     * It is used for setting the list of project in ArrayList object. 
	     * @param list
	     */
		public void setList(ArrayList<ProjectFields> list) {
			this.list = list;
		}
		
	}

