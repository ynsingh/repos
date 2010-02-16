package org.dei.edrp.pms.task;

import java.sql.*;
import org.dei.edrp.pms.dataBaseConnection.MyDataSource;

public class DynamicList{

	ResultSet rs=null;
	Connection	con=null;
	PreparedStatement st=null;
	int i=0;
	int count=0;
	int flag=0;
	public String[] taskList(String name,String tname,String rname,String oname) {
		try{
			con=MyDataSource.getConnection();
			PreparedStatement st=con.prepareStatement("select distinct t.Task_Name from task t,project p,organisation o,validatetab v "+
	"where (p.Project_Name=? and t.Task_Name!=? and o.Org_Name=? and o.Org_ID=v.Org_ID and p.Project_ID=v.Project_ID and v.Valid_ID=t.Valid_ID) order by t.Task_Name asc");
			 st.setString(1,name);
			 st.setString(2,tname);
			// st.setString(3,rname);
			 st.setString(3,oname);
			 rs=st.executeQuery();
			 while(rs.next())
			 {
				 flag=1;
				 count++;
			 }
		if(flag==0)
		{
			st=con.prepareStatement("select distinct t.Task_Name from task t,project p,organisation o,validatetab v "+
			"where (p.Project_Name=? and t.Task_Name!=? and o.Org_Name=? and o.Org_ID=v.Org_ID and v.Project_ID=p.Project_ID and v.Valid_ID=t.Valid_ID) order by t.Task_Name asc");
							 st.setString(1,name);
							 st.setString(2,tname);
							 //st.setString(3,rname);
							 st.setString(3,oname);
							 rs=st.executeQuery();
							 while(rs.next())
							 {
								 flag=2;
								 count++;
							 }
		}
			 
		}catch(Exception e){System.out.println("error is"+e);}
		
		String s[]=new String[count+1];
		s[i]="No";
		i++;
		try{
			if(flag==1)
			{
			PreparedStatement st=con.prepareStatement("select distinct t.Task_Name from task t,project p,organisation o,validatetab v "+
			"where (p.Project_Name=? and t.Task_Name!=? and o.Org_Name=? and o.Org_ID=v.Org_ID and v.Project_ID=p.Project_ID and v.Valid_ID=t.Valid_ID) order by t.Task_Name asc");
					 st.setString(1,name);
					 st.setString(2,tname);
					 //st.setString(3,rname);
					 st.setString(3,oname);
				 rs=st.executeQuery();
				 while(rs.next())
				{
					s[i]=rs.getString(1);
					i++;
				}
			}
			else if(flag==2)
					{
						st=con.prepareStatement("select distinct t.Task_Name from task t,project p,organisation o,validatetab v "+
						"where (p.Project_Name=? and t.Task_Name!=? and o.Org_Name=? and o.Org_ID=v.Org_ID and v.Project_ID=p.Project_ID and v.Valid_ID=t.Valid_ID) order by t.Task_Name asc");
										 st.setString(1,name);
										 st.setString(2,tname);
										 //st.setString(3,rname);
										 st.setString(3,oname);
										 rs=st.executeQuery();
										 while(rs.next())
											{
												s[i]=rs.getString(1);
												i++;
											}
					}		
				 
			}catch(Exception e){System.out.println("The  error in DynamicList class(taskList)"+e);}
			finally
			{
				MyDataSource.freeConnection(con);
			}
			return s;	
	}

	
	
	
	public String[] orgList(String rname,String uname) {
		try{
			con=MyDataSource.getConnection();
			if(rname.equals(uname))
			 {
				 st=con.prepareStatement("select distinct o.Org_Name from validatetab v,organisation o"+
					" where v.User_ID=? and o.Org_ID=v.Org_ID order by o.Org_Name asc");
				 st.setString(1,rname);
				 rs=st.executeQuery();
				 while(rs.next())
				 {
					 count++;
				 }
			 	}
			else
			{
				st=con.prepareStatement("select distinct o.Org_Name from validatetab v,organisation o"+
						" where v.User_ID=? and v.PermittedBy=? and o.Org_ID=v.Org_ID order by o.Org_Name asc");
			st.setString(1,rname);
			st.setString(2, uname); 
			rs=st.executeQuery();
			 while(rs.next())
			 {
				 count++;
			 }
			}
		}catch(Exception e){System.out.println("error is"+e);}
		
		String s[]=new String[count];
		try{
			if(rname.equals(uname))
			{
				st=con.prepareStatement("select distinct o.Org_Name from validatetab v,organisation o"+
				" where v.User_ID=? and o.Org_ID=v.Org_ID order by o.Org_Name asc");

					st.setString(1,rname);
					rs=st.executeQuery();
					while(rs.next())
					{
						s[i]=rs.getString(1);
						i++;
					}
			}

			else
			{
			st=con.prepareStatement("select distinct o.Org_Name from validatetab v,organisation o"+
			" where v.User_ID=? and v.PermittedBy=? and o.Org_ID=v.Org_ID order by o.Org_Name asc");

				st.setString(1,rname);
				st.setString(2, uname); 
				rs=st.executeQuery();
				while(rs.next())
				{
					s[i]=rs.getString(1);
					i++;
				}
			}
			}catch(Exception e){System.out.println("The  error in DynamicList class(orgList)"+e);}
			finally
			{
				MyDataSource.freeConnection(con);
			}
			return s;	
	}

	
	
	
	
	public String[] projectList(String orgname,String rname,String uname) {
		
		try{
			con=MyDataSource.getConnection();
			if(rname.equals(uname))
			 {
				 st=con.prepareStatement("select distinct p.Project_Name from validatetab v,project p,organisation o"+
				 " where o.Org_Name=? and v.User_ID=? and v.Project_ID=p.Project_ID and p.enable=0 and v.Org_ID=o.Org_ID order by p.Project_Name asc");
				 st.setString(1,orgname);
				 st.setString(2,rname);
				 rs=st.executeQuery();
				 while(rs.next())
				 {
					 count++;
				 }
			 }
			else
			{
				st=con.prepareStatement("select distinct p.Project_Name from validatetab v,project p,organisation o"+
					 " where o.Org_Name=? and v.User_ID=? and v.PermittedBy=? and v.Project_ID=p.Project_ID and p.enable=0 and v.Org_ID=o.Org_ID order by p.Project_Name asc");
			 st.setString(1,orgname);
			 st.setString(2,rname);
			 st.setString(3,uname);
			 rs=st.executeQuery();
			 while(rs.next())
			 {
				 count++;
			 }
			}
		}catch(Exception e)
		{
			System.out.println("error is"+e);
			}
		
		String s[]=new String[count];
		try{
			if(rname.equals(uname))
			 {
				 st=con.prepareStatement("select distinct p.Project_Name from validatetab v,project p,organisation o"+
				 " where o.Org_Name=? and v.User_ID=? and v.Project_ID=p.Project_ID and p.enable=0 and v.Org_ID=o.Org_ID order by p.Project_Name asc");
				 st.setString(1,orgname);
				 st.setString(2,rname);
				 rs=st.executeQuery();
				 while(rs.next())
				 {
					 s[i]=rs.getString(1);
					 i++;
				 }
			 }
			else
			{
			st=con.prepareStatement("select distinct p.Project_Name from validatetab v,project p,organisation o"+
			 " where o.Org_Name=? and v.User_ID=? and v.PermittedBy=? and v.Project_ID=p.Project_ID and p.enable=0 and v.Org_ID=o.Org_ID order by p.Project_Name asc");
				 st.setString(1,orgname);
				 st.setString(2,rname);
				 st.setString(3, uname);
				 rs=st.executeQuery();
				while(rs.next())
				{
					s[i]=rs.getString(1);
					i++;
				}
			}	
			}catch(Exception e){System.out.println("The  error in DynamicList class(projectList)"+e);}
			finally
			{
				MyDataSource.freeConnection(con);
			}
			return s;	
	}
	
	
public String[] orgProjectList(String name,String uname) {
		
		try{
			con=MyDataSource.getConnection();
	st=con.prepareStatement("select distinct p.Project_Name from validatetab v,project p,organisation o,login l"+
					 " where o.Org_Name=? and v.User_ID=? and v.User_ID=l.User_ID and v.Project_ID=p.Project_ID and v.Org_ID=o.Org_ID order by p.Project_Name asc");
			 st.setString(1,name);
			 st.setString(2,uname);
			 rs=st.executeQuery();
			 while(rs.next())
			 {
				 count++;
			 }
		}catch(Exception e){System.out.println("error is"+e);}
		
		String s[]=new String[count];
		try{
			st=con.prepareStatement("select distinct p.Project_Name from validatetab v,project p,organisation o,login l"+
			 " where o.Org_Name=? and v.User_ID=? and v.User_ID=l.User_ID and v.Project_ID=p.Project_ID and v.Org_ID=o.Org_ID order by p.Project_Name asc");
				 st.setString(1,name);
				 st.setString(2,uname);
				 rs=st.executeQuery();
				while(rs.next())
				{
					s[i]=rs.getString(1);
					i++;
				}
			}catch(Exception e){System.out.println("The  error is"+e);}
			finally
			{
				MyDataSource.freeConnection(con);
			}
			return s;	
	}


public String pSDate(String pname) {
	String s=null;
	try{
		con=MyDataSource.getConnection();
st=con.prepareStatement("select p.Start_Date from project p where p.Project_Name=?");
		 st.setString(1,pname);
		 rs=st.executeQuery();
		 rs.next();
		 s=rs.getString(1);
		 
		}catch(Exception e)
		{
			//System.out.println("The  error is (pSDate)"+e);
			}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	return s;	
}

public String pFDate(String pname) {
	String s=null;
	try{
		con=MyDataSource.getConnection();
st=con.prepareStatement("select p.Finish_Date from project p where p.Project_Name=?");
		 st.setString(1,pname);
		 rs=st.executeQuery();
		 rs.next();
		 s=rs.getString(1);
		 
		}catch(Exception e)
		{
			//System.out.println("The  error in DynamicList class (pFDate)"+e);
			}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	return s;	
}


public String seeExistence(String pname,String info) {
	String s=null;
	try{
		con=MyDataSource.getConnection();
		if(info.equals("project"))
		{	
		st=con.prepareStatement("select p.Project_Name from project p where p.Project_Name=?");
		}
		else
			st=con.prepareStatement("select o.Org_Name from organisation o where o.Org_Name=?");
		st.setString(1,pname.trim());
		 rs=st.executeQuery();
		 rs.next();
		 s=rs.getString(1);
		 
		}catch(Exception e)
		{
			//System.out.println("The  error is in dynamiclist.java"+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	return s;	
}

}



        

