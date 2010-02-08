package task;
import dataBaseConnection.MyDataSource;

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
		
		public TaskList(String pselect)
		{
			//if(flag==1)
			fillList(pselect);	
		}

		
		//fill the list for viewing the project list
		 
		public void fillList(String pselect){
			PreparedStatement ps=null;
			Connection con=null;
			//System.out.println("soption="+soption);
			try{
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				ps=con.prepareStatement("select t.Task_Name,v.User_ID,o.Org_Name,p.Project_Name,"+
						"t.Start_Date,t.Finished_Date,t.Per_Completed,t.Gchart_Color,t.Dependency,t.Description,t.Task_ID"+
						" from task t,organisation o,validatetab v,project p where o.Org_ID=v.Org_ID and t.Valid_ID=v.Valid_ID and p.Project_ID=v.Project_Id and "+"v.Project_ID=(select Project_ID from project where Enable=0 and Project_Name like ?)");	
				ps.setString(1,pselect.trim()+'%');
				
				ResultSet rs=ps.executeQuery();
				
				while(rs.next())
				{
			list.add(new TaskFields(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)));

				}
			System.out.println("The list is"+list);	
			}
			catch(Exception e){System.out.println("The error is"+e);}
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

