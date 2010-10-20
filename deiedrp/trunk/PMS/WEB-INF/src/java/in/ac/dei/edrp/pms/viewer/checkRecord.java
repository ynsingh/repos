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

	public static String AuthorityChecker(String getData,String roleid)
	{
		PreparedStatement ps_check=null;
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
		con=MyDataSource.getConnection();
		
		ps_check=con.prepareStatement("select d.authorities from " +
				"default_authority d where d.role_id="+"?"+" and d.authorities"+"=?");
		
		ps_check.setString(1,roleid);
		ps_check.setString(2,getData);
		ResultSet rs=ps_check.executeQuery();
		value="Not Allow";
		while(rs.next())
		{
			value="Allow";
			if(value.equalsIgnoreCase("Allow"))
			break;
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
