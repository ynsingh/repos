package in.ac.dei.edrp.pms.task;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.checkRecord;

import java.util.ArrayList;
import java.sql.*;
/**
 * Class TaskList
 */

public class TaskList {

		//properties
		ArrayList<TaskFields> list = new ArrayList<TaskFields>();
		//ArrayList<String> heading = new ArrayList<String>();
			
		//constructors
		//for super admin
		public TaskList(String projectname,String type,String validOrgInPortal)
		{
			fillList(projectname,type,validOrgInPortal,"");	
		}
		//for user
		public TaskList(String projectname,String type,String validOrgInPortal,String uid,String role_in_org)
		{
			String edit=checkRecord.AuthorityChecker("edit_remove_task", uid, validOrgInPortal,role_in_org);
			//System.out.println("edit authority of task="+edit);
			//if(flag==1)
			fillList(projectname,type,validOrgInPortal,edit);	
		}

		
		//fill the list for viewing the project list
		 
		public void fillList(String projectname,String type,String validOrgInPortal,String edit){
			PreparedStatement ps=null;
			Connection con=null;
			//System.out.println("soption="+soption);
			try{
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				if(type.equalsIgnoreCase("Not Assigned"))
				{
					ps=con.prepareStatement("select t.task_name,t.description,t.no_of_days," +
							"t.schedule_start_date,t.schedule_end_date,t.actual_start_date," +
							"t.actual_end_date,t.gchart_color,t.per_completed,t.task_status," +
							"t.dependency,t.task_id,t.vproject_code from task t " +
							"where t.vproject_code=(select p.project_code from project p where " +
						"p.project_name=? and p.valid_org_inportal=?) " +
						"and t.task_id not in(select task_id from task_with_user)");
				}
				else if(type.equalsIgnoreCase("Completed"))
				{
					ps=con.prepareStatement("select t.task_name,u.valid_user_id,t.no_of_days," +
							"t.schedule_start_date,t.schedule_end_date,t.actual_start_date," +
							"t.actual_end_date,t.gchart_color,t.per_completed,t.task_status," +
							"t.dependency,t.task_id,t.vproject_code from task t,task_with_user twu,user_in_org u,validatetab v " +
							"where t.vproject_code=(select p.project_code from project p where " +
						"p.project_name=? and p.valid_org_inportal=?) and u.valid_key=v.valid_user_key " +
						"and v.valid_id=twu.valid_id and twu.task_id=t.task_id and t.per_completed=100");
				} 
				else
				{
					ps=con.prepareStatement("select t.task_name,u.valid_user_id,t.no_of_days," +
							"t.schedule_start_date,t.schedule_end_date,t.actual_start_date," +
							"t.actual_end_date,t.gchart_color,t.per_completed,t.task_status," +
							"t.dependency,t.task_id,t.vproject_code from task t,task_with_user twu,user_in_org u,validatetab v " +
							"where t.vproject_code=(select p.project_code from project p where " +
						"p.project_name=? and p.valid_org_inportal=?) and u.valid_key=v.valid_user_key " +
						"and v.valid_id=twu.valid_id and twu.task_id=t.task_id");
				} 
				ps.setString(1,projectname);
				ps.setString(2,validOrgInPortal);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next())
				{
			list.add(new TaskFields(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
					rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
					rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),edit));

				}
				
			}
			catch(Exception e){System.out.println("in task list="+e);}
			finally
			{
				MyDataSource.freeConnection(con);
			}
		}
		
		// getter and setter methods
		public ArrayList<TaskFields> getList() {
			return list;
		}
		
		public void setList(ArrayList<TaskFields> list) {
			this.list = list;
		}
	
	}

