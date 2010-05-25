package in.ac.dei.edrp.pms.portal;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;

import java.util.ArrayList;
import java.sql.*;
/**
 * Class PortalList
 * This class is responsible for getting the values of all portal details.
 * 
 */

public class PortalList {

		//properties
		ArrayList<PortalFields> list = new ArrayList<PortalFields>();
		
		/*
		 * This constructor calls the fillList() method,
		 * which returns all the roles for view.
		 */
		public PortalList(){
		
			fillList();
		}
				
		/**
		 * fill the list for viewing the role list
		 */
		public void fillList(){
			Connection con=null;
			try{
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				PreparedStatement ps=null;
		ps=con.prepareStatement("select Portal_Id,Portal_Name,Portal_Description,Created_By,DATE_FORMAT(Created_On,'%d-%b-%Y %r') from portal");
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					list.add(new PortalFields(rs.getString(1),rs.getString(2),rs.getString(3),
							rs.getString(4),rs.getString(5)));
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
		public ArrayList<PortalFields> getList() {
			return list;
		}
		
		public void setList(ArrayList<PortalFields> list) {
			this.list = list;
		}
		
	}

