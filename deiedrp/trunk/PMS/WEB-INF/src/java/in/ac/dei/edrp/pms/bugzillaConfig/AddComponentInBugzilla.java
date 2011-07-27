package in.ac.dei.edrp.pms.bugzillaConfig;

import in.ac.dei.edrp.pms.dataBaseConnection.BugConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddComponentInBugzilla {

	public static int insertComponents(String componentName,int product_id,int initialowner, String description)
	  {
		 int x=0;
		 Connection con=BugConnection.getJNDIConnection();
		 try {
			 if(AddProjectInBugzilla.getDesiredId("name","components","product_id",product_id,"name",componentName)==null){
			PreparedStatement ps=con.prepareStatement("insert into components (" +
					"id,name,product_id,initialowner,description)" +
					"values(?,?,?,?,?)");
			ps.setInt(1,0);
			ps.setString(2,componentName);
			ps.setInt(3,product_id);
			ps.setInt(4,initialowner);
			ps.setString(5,description);
			
			x=ps.executeUpdate();
			 }
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally{
			 BugConnection.freeConnection(con);
		 }
		  return x;
	  }
	
	
	public static int insertSeries(int creator, String projectName, String componentName,String[] data)
	  {
		 int x=0;
		 int category = AddProjectInBugzilla.getDesiredId("id","series_categories","name",projectName);
		 int sub_category = AddProjectInBugzilla.getDesiredId("id","series_categories","name",componentName);
		 Connection con=BugConnection.getJNDIConnection();
		 try {
			 if(category > 0 && sub_category > 0){
			for(int i=0;i<data.length;i++){	
			 PreparedStatement ps=con.prepareStatement("insert into series (" +
					"series_id,creator,category,subcategory,name,frequency,query,is_public)" +
					"values(?,?,?,?,?,?,?,?)");
			ps.setInt(1,0);
			ps.setInt(2,creator);
			ps.setInt(3,category);
			ps.setInt(4,sub_category);
			ps.setString(5,data[i]);
			ps.setInt(6,1);
			ps.setString(7,"myquery");
			ps.setInt(8,1);
			
			ps.executeUpdate();
			}
			}
		
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally{
			 BugConnection.freeConnection(con);
		 }
		  return x;
	  }
	

}
