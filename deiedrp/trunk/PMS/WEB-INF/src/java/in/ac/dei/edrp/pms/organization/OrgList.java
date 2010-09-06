package in.ac.dei.edrp.pms.organization;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;

import java.util.ArrayList;
import java.sql.*;
/**
 * Class OrgList
 * This class is responsible for getting the values of all Organisation details.
 * This class has three parametric constructor.
 * It consist functions which returns data in list,by using this list data,first we set this value of list in a
 * attribute and then in required JSP,we populate data.It sets the pagination length,no,scrollable bar  etc.
 */

public class OrgList {

		//properties
		ArrayList<OrgFields> list = new ArrayList<OrgFields>();
		
		/**
		 * This Constructor has two parameters,both are String.
		 * One is searchOption and other is search value.
		 * This is specially for search organisation page.After Getting option and search value,we gets the data according to them
		 * by calling fillSearchList(String,String) method.
		 */
		
		public OrgList(String searchOption,String keysearch)
		{
			fillSearchList(searchOption,keysearch);	
		}
		/*
		 * This is another constructor which sets the scrollable bar and by calls the fillList() method,
		 * which returns all the organisation for view.
		 */
		public OrgList(){
			
			fillList();
		}
		/*
		 * It has only one parameter,only for getting all the Organisation.
		 * Here flag means authority,if we want the view authority wise.
		 */
		public OrgList(int flag)
		{
			if(flag==1)
			fillList();	
		}

		
		/**
		 * fill the list for viewing the organisation list
		 */
		public void fillList(){
			Connection con=null;
			try{
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select o.Org_Name,o.org_address,c.city_name," +
						"s.state_name,o.Org_Phone,o.Org_Fax,o.Org_URL," +
						"o.org_id from organisation o,state s,city c" +
						" where o.Org_State=s.state_id and o.org_city=c.city_id and s.state_id=c.state_id");
				while(rs.next())
				{
					list.add(new OrgFields(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
				}
			}
			catch(Exception e){}
			finally
			{
				MyDataSource.freeConnection(con);
			}
		}
		
		/**
		 * fill the list for searching the project according to user input i.e. search value and option
		 * @param searchOption: It Means how user is searching i.e according to City Name,Owner or Organisation name
		 * @param keysearch: This is the value which is entered by user for searching
		 */ 
		public void fillSearchList(String searchOption,String keysearch){
			PreparedStatement ps=null;
			Connection con=null;
			//System.out.println("searchOption="+searchOption+"and value"+keysearch);
			try{
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				if(searchOption.equalsIgnoreCase("Organisation Name"))
				{	
					ps=con.prepareStatement("select distinct o.Org_Name,o.Org_Address,c.city_name,"+
					  		"s.state_name,o.org_phone,o.Org_Fax,o.Org_Url,o.org_id  "+ 
					  		" from organisation o,state s,city c "+
					  		"where o.Org_State=s.state_id and o.org_city=c.city_id and s.state_id=c.state_id" +
					  		" and o.org_name like ? ");	
					
				}
				else if(searchOption.equalsIgnoreCase("Organisation City"))
				{
					ps=con.prepareStatement("select distinct o.Org_Name,o.Org_Address,c.city_name,"+
					  		"s.state_name,o.org_phone,o.Org_Fax,o.Org_Url,o.org_id "+ 
					  		" from organisation o,state s,city c "+
					  		"where o.Org_State=s.state_id and o.org_city=c.city_id and s.state_id=c.state_id and o.org_city like ? ");
					//System.out.println("Query running according to organisation City");
				}
				else
					ps=con.prepareStatement("select distinct o.Org_Name,o.Org_Address,c.city_name,"+
					  		"s.state_name,o.org_phone,o.Org_Fax,o.Org_Url,o.org_id "+ 
					  		" from organisation o,state s,city c "+
					  		"where o.Org_State=s.state_id and o.org_city=c.city_id and s.state_id=c.state_id and s.state_name like ? ");	
				ps.setString(1,keysearch.trim()+'%');
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					
			list.add(new OrgFields(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));

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
		public ArrayList<OrgFields> getList() {
			return list;
		}
		
		public void setList(ArrayList<OrgFields> list) {
			this.list = list;
		}
		
	}

