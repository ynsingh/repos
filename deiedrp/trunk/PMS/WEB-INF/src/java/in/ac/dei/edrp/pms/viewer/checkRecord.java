package in.ac.dei.edrp.pms.viewer;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class checkRecord {
	static Connection con=null;
	static String value=null;
	public static String duplicacyChecker(String getData,String tableName,String colName,String colValue)
	{
		value=null;
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
			//System.out.println("in duplicacy checker field name="+"select "+getData+" from "+tableName+
					//" where "+colName+"="+colValue);
		con=MyDataSource.getConnection();
		PreparedStatement check=con.prepareStatement("select "+getData+" from "+tableName+
				" where "+colName+"=?");
		check.setString(1,colValue);
		ResultSet rs=check.executeQuery();
		
		if(rs.next())
		{
			value=rs.getString(1);
			//System.out.println("value="+value);
		}
		}
		catch(Exception e)
		{
			System.out.println("error in checkRecord.java file in duplicacyChecker ="+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return value;
	}
	
	public static String twoFieldDuplicacyChecker(String getData,String tableName,String colName1,String colValue1,String colName2,String colValue2)
	{
		PreparedStatement check=null;
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
			con=MyDataSource.getConnection();
		if(colValue2==null)
		{
			check=con.prepareStatement("select "+getData+" from "+tableName+
				" where "+colName1+"=?"+" and "+colName2+" IS NULL");
		}
		else
		{
		check=con.prepareStatement("select "+getData+" from "+tableName+
				" where "+colName1+"=?"+" and "+colName2+"=?");
		}
		check.setString(1,colValue1);
		if(colValue2!=null)
		check.setString(2,colValue2);
		ResultSet rs=check.executeQuery();
		value=null;
		if(rs.next())
		{
			value=rs.getString(1);
			//System.out.println("value="+value);
		}
		}
		catch(Exception e)
		{
			System.out.println("error in checkRecord.java file in twoFieldDuplicacyChecker ="+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return value;
	}
	
	public static String threeFieldDuplicacyChecker(String getData,String tableName,String colName1,String colValue1,String colName2,String colValue2,String colName3,String colValue3)
	{
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
			//System.out.println("in duplicacy checker field name="+getData);
		con=MyDataSource.getConnection();
		PreparedStatement check=con.prepareStatement("select "+getData+" from "+tableName+
				" where "+colName1+"=?"+" and "+colName2+"=?"+" and "+colName3+"=?");
		check.setString(1,colValue1);
		check.setString(2,colValue2);
		check.setString(3,colValue3);
		ResultSet rs=check.executeQuery();
		value=null;
		if(rs.next())
		{
			value=rs.getString(1);
			//System.out.println("value="+value);
		}
		}
		catch(Exception e)
		{
			System.out.println("error in checkRecord.java file in threeFieldDuplicacyChecker ="+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return value;
	}

	public static String AuthorityChecker(String getData,String valid_user_id,String valid_orgportal,String role_in_org)
	{
		PreparedStatement check=null;
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
		con=MyDataSource.getConnection();
		if(role_in_org.equals("exist"))
		{
		check=con.prepareStatement("select d."+getData+" from user_in_org uio," +
				"default_authority d,role r,user_role_in_org uro"+
				" where (uio.valid_user_id="+"?"+" and uio.valid_orgportal="+"?)"+
				" and (uio.valid_key=uro.valid_key and " +
				"uro.valid_role=r.role_id and d.role_id=r.role_id)");
		}
		else
		{
			check=con.prepareStatement("select d."+getData+" from user_in_org uio," +
					"default_authority d,role r,validatetab v"+
					" where (uio.valid_user_id="+"?"+" and uio.valid_orgportal="+"?)"+
					" and (uio.valid_key=v.valid_user_key and " +
					"v.valid_role_id=r.role_id and d.role_id=r.role_id)");
		}
		check.setString(1,valid_user_id);
		check.setString(2,valid_orgportal);
		ResultSet rs=check.executeQuery();
		value=null;
		while(rs.next())
		{
			value=rs.getString(1);
			if(value.equalsIgnoreCase("Allow"))
			break;
			//System.out.println("value in authority checker="+value);
		}
		}
		catch(Exception e)
		{
			System.out.println("error in checkRecord.java file in AuthorityChecker ="+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return value;
	}
}
