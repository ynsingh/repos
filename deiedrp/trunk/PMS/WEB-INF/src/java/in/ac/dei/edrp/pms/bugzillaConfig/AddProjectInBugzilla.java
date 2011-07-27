package in.ac.dei.edrp.pms.bugzillaConfig;

import in.ac.dei.edrp.pms.dataBaseConnection.BugConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddProjectInBugzilla {

	public static int insertProduct(String pname,String description)
	  {
		 int x=0;
		 Connection con=BugConnection.getJNDIConnection();
		 try {
			 if(AddProjectInBugzilla.getDesiredId("id","products","name",pname)==0){
			PreparedStatement ps=con.prepareStatement("insert into products (" +
					"id,name,classification_id,description,isactive,votesperuser," +
					"maxvotesperbug,votestoconfirm,defaultmilestone,allows_unconfirmed)" +
					"values(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1,0);
			ps.setString(2,pname);
			ps.setInt(3,1);
			ps.setString(4,description);
			ps.setInt(5,1);
			ps.setInt(6,0);
			ps.setInt(7,10000);
			ps.setInt(8,0);
			ps.setString(9,"...");
			ps.setInt(10,0);
			x=ps.executeUpdate();
			if(x>0){
				insertMilestones(pname,"...",0);
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
	
	
	public static int insertVersions(String pname,String projectVersion)
	  {
		 int x=0;
		 Connection con=BugConnection.getJNDIConnection();
		 try {
			
			int product_id=getDesiredId("id","products","name",pname);
			String version_product = getDesiredId("value","versions","product_id",product_id,"value",projectVersion);
			System.out.println("exits version_product="+version_product);
			if(version_product==null){
			PreparedStatement ps=con.prepareStatement("insert into versions (" +
					"id,value,product_id)" +
					"values(?,?,?)");
			ps.setInt(1,0);
			ps.setString(2,projectVersion);
			ps.setInt(3,product_id);
			x=ps.executeUpdate();
//			
//			if(ps.executeUpdate()>0)
//				insertMilestones(product_id,"...",0);
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
	
	public static int insertMilestones(String pname,String value,int sortkey)
	  {
		 int x=0;
		 Connection con=BugConnection.getJNDIConnection();
		 try {
			 int product_id=getDesiredId("id","products","name",pname);
			 PreparedStatement ps=con.prepareStatement("insert into milestones (" +
					"id,product_id,value,sortkey)" +
					"values(?,?,?,?)");
			ps.setInt(1,0);
			ps.setInt(2,product_id);
			ps.setString(3,value);
			ps.setInt(4,sortkey);
			x=ps.executeUpdate();
			
		
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally{
			 BugConnection.freeConnection(con);
		 }
		  return x;
	  }
	
	public static int getDesiredId(String getData,String tableName,String colName,String colValue)
	  {
		int product_id=0;
		 Connection con=BugConnection.getJNDIConnection();
		 try {
			 PreparedStatement check=con.prepareStatement("select "+getData+" from "+tableName+
						" where "+colName+"=?");
				check.setString(1,colValue);
				ResultSet rs=check.executeQuery();
				
				if(rs.next())
				{
					product_id=rs.getInt(1);
					//System.out.println("value="+value);
				}
			 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally{
			 BugConnection.freeConnection(con);
		 }
		  return product_id;
	  }
	
	public static int insertSeries_Categories(String projectName)
	  {
		 int x=0;
		 
		 Connection con=BugConnection.getJNDIConnection();
		 try {
			 if(AddProjectInBugzilla.getDesiredId("id","series_categories","name",projectName)==0){
			 PreparedStatement ps=con.prepareStatement("insert into series_categories (" +
					"id,name)" +
					"values(?,?)");
			ps.setInt(1,0);
			ps.setString(2,projectName);
			
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
	
	public static String getDesiredId(String getData,String tableName,String colName1,int colValue1,String colName2,String colValue2)
	  {
		String product_id=null;
		 Connection con=BugConnection.getJNDIConnection();
		 try {
			 System.out.println(colName1+colValue1+colName2+colValue2);
			 PreparedStatement check=con.prepareStatement("select "+getData+" from "+tableName+
					 " where "+colName1+"=?"+" and "+colName2+"=?");
				check.setInt(1,colValue1);
				check.setString(2,colValue2);
				ResultSet rs=check.executeQuery();
				
				if(rs.next())
				{
					product_id=rs.getString(1);
					//System.out.println("value="+value);
				}
			 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally{
			 BugConnection.freeConnection(con);
		 }
		  return product_id;
	  }
}
