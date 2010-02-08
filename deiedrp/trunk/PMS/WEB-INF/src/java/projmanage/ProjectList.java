package projmanage;
import dataBaseConnection.MyDataSource;
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
		//ArrayList<String> heading = new ArrayList<String>();
	
		
		//constructors
		
		/**
		 * This constructor is used for maintaining the searching result.
		 * Inside this constructor we calls the {@link #fillSearchList(String,String) fillSearchList} method.
		 * @param soption Holds the selected option according this the searching will be performed.
		 * @param svalue Holds the search value which you are searching.
		 *    
		 */
		public ProjectList(String soption,String svalue)
		{
			fillSearchList(soption,svalue);	
		}
		/**
		 * This constructor is used for initialising the offset and length 
		 * that is used for displaying the records of project.
		 * Inside this constructor we calls the {@link #fillList(int,String) fillList} method.
		 * @param offset It holds the position of record, from where the next records will be shown.
		 * @param length It holds maximum number of records for showing per page.
		 */
//		public ProjectList(){
//			
//			fillList(1,"");
//		}
		/**
		 * This constructor is used for maintaining the project list related with update operation.
		 * Inside this we call the {@link #fillList(int,String) fillList} method.
		 * @param flag It holds only 0 or 1 
		 * @since 1 for admin and 0 for normal user.
		 * @param uid It holds the user id of that person which is currently logged in.
		 */
		public ProjectList(int flag,String uid)
		{
			fillList(flag,uid);	
		}

		
		/**
		 * This method is used for filling the project list in the ArrayList object.
		 * @param flag It holds only 0 or 1 
		 * @since 1 for admin and 0 for normal user.
		 * @param uid It holds the user id of that person which is currently logged in.
		 * @see projmanage.ProjectFields
		 */
		public void fillList(int flag,String uid){
			Connection con=null;
			try{
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				PreparedStatement ps=null;
				ResultSet rs=null;
				if(flag==1 && uid.equals("admin"))
				{
					ps=con.prepareStatement("select * from project order by Project_ID asc");
				}
				else if(flag==0 && uid.equals("user"))
				{
					ps=con.prepareStatement("select * from project where enable=0 order by Project_ID asc");
				}
				else if(flag==1)
				{
					ps=con.prepareStatement("select * from project order by Project_ID asc");
				}
				else
				{
				ps=con.prepareStatement("select p.Project_ID,p.Project_Name,p.Start_Date,p.Finish_Date,p.Target_Budget,"+
				"p.Priority,p.Status,p.View_Permission,p.GChart_Color,p.Description,p.Enable from project p,validatetab v,login l "+
				"where v.User_ID=? and v.PermittedBy=l.User_ID and l.Authority='Admin' and v.Project_ID=p.Project_ID order by p.Project_ID asc");
				ps.setString(1,uid);
				}
				rs=ps.executeQuery();
				
				/*ResultSetMetaData rsmd=rs.getMetaData();
				int col=rsmd.getColumnCount();	
				for(int i=1;i<=col;i++)
				{
				heading.add(rsmd.getColumnLabel(i));
				}*/
				
				while(rs.next())
				{
									
		list.add(new ProjectFields(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),Integer.parseInt(rs.getString(5)),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString("Enable")));

				}
				
			}
			catch(Exception e){}
			finally
			{
				MyDataSource.freeConnection(con);
			}
		}
		
		/**
		 * This method is used for adding the searching result in ArrayList object.
		 * @param soption Holds the selected option according this the searching will be performed.
		 * @param svalue Holds the search value which you are searching.
		 * @see projmanage.ProjectFields 
		 */
		public void fillSearchList(String soption,String svalue){
			PreparedStatement ps=null;
			Connection con=null;
			//System.out.println("soption="+soption);
			try{
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				if(soption.equalsIgnoreCase("Organisation Name"))
				{
					ps=con.prepareStatement("select p.Project_ID,p.Project_Name,p.Start_Date,p.Finish_Date,"+
					  		"p.Target_Budget,v.User_ID,p.status,l.Authority,v.PermittedBy,o.Org_Name,p.Enable "+
					  		"from project p,validatetab v,login l,organisation o where o.Org_Name like ? and "+
					  		"v.Project_ID=p.Project_ID and l.User_ID=v.User_ID and v.Org_ID=o.Org_ID");
				}
				else if(soption.equalsIgnoreCase("Project Owner"))
				{
					ps=con.prepareStatement("select p.Project_ID,p.Project_Name,p.Start_Date,p.Finish_Date,"+
							"p.Target_Budget,v.User_ID,p.status,l.Authority,v.PermittedBy,o.Org_Name,p.Enable"+ 
					  		" from project p,validatetab v,login l,organisation o "+
					  		"where v.User_ID like ? and v.Project_ID=p.Project_ID "+
					  		"and v.User_ID=l.User_ID and o.Org_ID=v.Org_ID");
					
				}
				else
					ps=con.prepareStatement("select * from project where Project_Name like ?");	
				ps.setString(1,svalue.trim()+'%');
				
				ResultSet rs=ps.executeQuery();
				
				while(rs.next())
				{
			list.add(new ProjectFields(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),Integer.parseInt(rs.getString(5)),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString("Enable")));

				}
				
			}
			catch(Exception e){}
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

