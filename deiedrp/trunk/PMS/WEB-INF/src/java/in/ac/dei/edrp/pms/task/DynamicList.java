package in.ac.dei.edrp.pms.task;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import java.util.*;
import java.text.*;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

public class DynamicList{

	ResultSet rs=null;
	Connection	con=null;
	PreparedStatement st=null;
	int i=0;
	int count=0;
	ArrayList<String> al=new ArrayList<String>();
	public String[] resourceList(String uname,String valid_key,String projectName,String orgportal)
	{
		al.add(uname);
		if(valid_key!=null)
		{
			valid_key=runQuery(valid_key,projectName,orgportal);
		}
	
		Iterator<String> i=al.iterator();
		String s[]=new String[al.size()+1];
		int j=0;
		s[j]="--Select--";
		j++;
		while(i.hasNext())
		{
			s[j]=i.next();
			j++;
		}
	return s;	
	}
	
	String runQuery(String uid,String pname,String orgportal)
	 {
	 try{
		 con=MyDataSource.getConnection();
	PreparedStatement ps=con.prepareStatement("select v.valid_user_key,u.valid_user_id from validatetab v,"+
	 "user_in_org u,project p where v.permitted_by=(select u.valid_user_id from user_in_org u "+
	 "where u.valid_key=? and u.valid_orgportal=?) and "+
	 "(v.valid_project_code=p.project_code and p.project_name=?) "+
	 "and u.valid_key=v.valid_user_key");
		ps.setString(1,uid);
		ps.setString(2,orgportal);
		ps.setString(3,pname);
		rs=ps.executeQuery();
		 CachedRowSet crs = new CachedRowSetImpl();
		 crs.populate(rs);

		while(crs.next())
		{
			uid=crs.getString(1);
			al.add(crs.getString(2));
			//System.out.println("anil my uid in runquery ="+crs.getString(2));
			uid=runQuery(uid,pname,orgportal);
		}
				
	 }
	 catch(Exception e){System.out.println("error in runQuery="+e);
	 return null;	
	 }
		finally
		{
			MyDataSource.freeConnection(con);
		}
	 	return(uid);
	 }
	
	
	//it is used in case of creating the task,it generates dependent task
	public String[] taskList(String taskName,String projectName,String orgportal) {
		try{
			//System.out.println("task name="+taskName);
			con=MyDataSource.getConnection();
			PreparedStatement st=con.prepareStatement("select count(t.task_name) from task t," +
					"project p where (t.task_name!=? and p.project_name=? and " +
			"t.vproject_code=p.project_code and p.valid_org_inportal=?) order by t.Task_Name asc");
			 st.setString(1,taskName);
			 st.setString(2,projectName);
			 st.setString(3,orgportal);
			 rs=st.executeQuery();
			 if(rs.next())
			 {
				 count=rs.getInt(1);
			 }
		}catch(Exception e){System.out.println("error is"+e);}
		
		String s[]=new String[count+1];
		s[i]="No";
		i++;
		try{
			PreparedStatement st=con.prepareStatement("select t.task_name from task t," +
					"project p where (t.task_name!=? and p.project_name=? and " +
			"t.vproject_code=p.project_code and p.valid_org_inportal=?) order by t.Task_Name asc");
				st.setString(1,taskName);
				st.setString(2,projectName);
				st.setString(3,orgportal);
				 rs=st.executeQuery();
				 while(rs.next())
				{
					s[i]=rs.getString(1);
					i++;
				}
			}catch(Exception e){System.out.println("The  error in DynamicList class(taskList)"+e);}
			finally
			{
				MyDataSource.freeConnection(con);
			}
			return s;	
	}

	//it is used in case of assign task
	public String[] generateTaskList(String projectName,String orgportal) {
		try{
			con=MyDataSource.getConnection();
			PreparedStatement st=con.prepareStatement("select count(t.task_name) from task t,project p" +
					" where (p.project_name=? and t.vproject_code=p.project_code " +
			"and p.valid_org_inportal=?) and t.Task_Status='Not Completed'" +
			" and t.task_id not in(select task_id from task_with_user) order by t.Task_Name");
			
			 st.setString(1,projectName);
			 st.setString(2,orgportal);
			 rs=st.executeQuery();
			 if(rs.next())
			 {
				 count=rs.getInt(1);
			 }
			 
		}catch(Exception e){System.out.println("error is"+e);}
		
		String s[]=new String[count+1];
		s[i]="--Select--";
		i++;
		try{
			PreparedStatement st=con.prepareStatement("select t.task_name from task t,project p" +
					" where (p.project_name=? and t.vproject_code=p.project_code " +
					"and p.valid_org_inportal=?) and t.Task_Status='Not Completed'" +
					" and t.task_id not in(select task_id from task_with_user) order by t.Task_Name");
				
				st.setString(1,projectName);
				st.setString(2,orgportal);
				 rs=st.executeQuery();
				 while(rs.next())
				{
					s[i]=rs.getString(1);
					i++;
				}
			}catch(Exception e){System.out.println("The  error in DynamicList class(taskList)"+e);}
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

//for getting organization list according portal and login user
public String[] organisationPortalList(String portalname,String username) {
	
	try{
		con=MyDataSource.getConnection();
		st=con.prepareStatement("select count(distinct o.org_name) from portal p,organisation o," +
		"org_into_portal oinp,user_in_org uino where oinp.org_id=o.org_id " +
		"and p.portal_id=oinp.portal_id and p.portal_name=?" +
		"and uino.valid_orgportal=oinp.valid_org_inportal " +
		"and uino.valid_user_id=?");
		st.setString(1,portalname);
		st.setString(2,username);
		rs=st.executeQuery();
		if(rs.next())
		 {
			 count=rs.getInt(1);
		 }
	}catch(Exception e){System.out.println("error is"+e);}
	
	String s[]=new String[count];
	try{
		st=con.prepareStatement("select distinct o.org_name from portal p,organisation o," +
				"org_into_portal oinp,user_in_org uino where oinp.org_id=o.org_id " +
				"and p.portal_id=oinp.portal_id and p.portal_name=?" +
				"and uino.valid_orgportal=oinp.valid_org_inportal " +
				"and uino.valid_user_id=?");
				st.setString(1,portalname);
				 st.setString(2,username);
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

//for super admin when view the project
public String[] orgPortalList(String portalname) {
	
	try{
		//System.out.println("in orgPortalList java file");
		con=MyDataSource.getConnection();
		st=con.prepareStatement("select count(o.org_name) from organisation o,portal p," +
				"org_into_portal oip where oip.portal_id=p.portal_id and " +
				"oip.org_id=o.org_id and p.portal_name=?");
		st.setString(1,portalname);
		 rs=st.executeQuery();
		 if(rs.next())
		 {
			 count=rs.getInt(1);
		 }
	}catch(Exception e){System.out.println("error is"+e);}
	
	String s[]=new String[count];
	try{
		st=con.prepareStatement("select o.org_name from organisation o,portal p," +
				"org_into_portal oip where oip.portal_id=p.portal_id and " +
				"oip.org_id=o.org_id and p.portal_name=?");
		st.setString(1,portalname);
				 rs=st.executeQuery();
			while(rs.next())
			{
				s[i]=rs.getString(1);
				i++;
			}
		}catch(Exception e){
			//System.out.println("The  error is"+e);
			}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return s;	
}



public String pSDate(String pname,String orgportal) {
	String s=null;
	try{
		con=MyDataSource.getConnection();
st=con.prepareStatement("select p.Schedule_Start_Date from project p " +
		"where p.Project_Name=? and p.valid_org_inportal=?");
		 st.setString(1,pname);
		 st.setString(2,orgportal);
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

public String pFDate(String pname,String orgportal) {
	String s=null;
	try{
		con=MyDataSource.getConnection();
		st=con.prepareStatement("select p.Schedule_End_Date from project p " +
				"where p.Project_Name=? and p.valid_org_inportal=?");
		 st.setString(1,pname);
		 st.setString(2,orgportal);
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
	public String genTaskEndDate(int no_of_days,String taskStartDate)
	{
		String retvalue=null;
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date d = sdf.parse(taskStartDate);
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(d);
	    Calendar cal1 = Calendar.getInstance();
		cal1.setTime(d);
		//add days to current date using Calendar.add method
		cal1.add(Calendar.DATE,no_of_days);
//		System.out.println("Start Date="+sdf.format(cal.getTime()));
//		System.out.println("No_of_days="+no_of_days);
//		System.out.println("End Date="+sdf.format(cal1.getTime()));
		int no_of_sunday=0;
		while(!sdf.format(cal1.getTime()).equals(sdf.format(cal.getTime())))
		{
			int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
			if (dayofweek == Calendar.SUNDAY)
				no_of_sunday++;
			if(no_of_days<0)
				cal.add(Calendar.DATE,-1);
			else
				cal.add(Calendar.DATE,1);
		}
		cal1.add(Calendar.DATE,no_of_sunday);
		//System.out.println("no_of_sunday="+no_of_sunday);
		//System.out.println("Actual End Date="+sdf.format(cal1.getTime()));
		retvalue=sdf.format(cal1.getTime());
		}
		catch(Exception e){System.out.println("error in task end date="+e);}
		return retvalue;
	}


public String seeExistence(String pname,String info) {
	String s=null;
	try{
		con=MyDataSource.getConnection();
		if(info.equals("portal"))
		{	
			st=con.prepareStatement("select p.Portal_Name from portal p where p.Portal_Name=?");
		}
		else
			st=con.prepareStatement("select o.Org_Name from organisation o where o.Org_Name=?");
		st.setString(1,pname.trim());
		rs=st.executeQuery();
		 rs.next();
		 s=rs.getString(1);
		}catch(Exception e)
		{
			//System.out.println("The  error is in dynamiclist.java in seeExistence method"+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	return s;	
}

public String seeProjectExistence(String pname,String orgportal) {
	String s=null;
	try{
		con=MyDataSource.getConnection();
		st=con.prepareStatement("select p.Project_Name from project p where p.Project_Name=? and valid_org_inportal=?");
		st.setString(1,pname.trim());
		st.setString(2,orgportal);
		rs=st.executeQuery();
		 rs.next();
		 s=rs.getString(1);
		}catch(Exception e)
		{
			//System.out.println("The  error is in dynamiclist.java in seeProjectExistence method"+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	return s;	
}

public String seeRoleExistence(String pname,String orgportal) {
	String s=null;
	try{
		con=MyDataSource.getConnection();
			if(orgportal.equals("null"))
			{
				st=con.prepareStatement("select r.Role_Name from role r where r.Role_Name=? and ValidOrgPortal IS NULL");
			}
			else
			{
				st=con.prepareStatement("select r.Role_Name from role r where r.Role_Name=? and ValidOrgPortal=?");
			}
		st.setString(1,pname.trim());
		if(!orgportal.equals("null"))
		st.setString(2,orgportal.trim());
		 rs=st.executeQuery();
		 rs.next();
		 s=rs.getString(1);
		}
		catch(Exception e)
		{
			//System.out.println("The  error is in dynamiclist.java in seeRoleExistence method"+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	return s;	
}

public String seeUserExistence(String emailid,String user,String orgportal) {
	String s=null;
	try{
		con=MyDataSource.getConnection();
		if(user.equalsIgnoreCase("Super Admin"))
		{	
			st=con.prepareStatement("select u.User_ID from user_info u where u.User_ID=?");
			st.setString(1,emailid.trim());
		}
		else
		{
			st=con.prepareStatement("select u.valid_user_id from user_in_org u where u.valid_user_id=? and u.valid_orgportal=?");
			st.setString(1,emailid.trim());
			st.setString(2,orgportal);
		}
		rs=st.executeQuery();
		 rs.next();
		 s=rs.getString(1);
		}
		catch(Exception e)
		{
			//System.out.println("The  error is in dynamiclist.java in seeUserExistence method"+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	return s;	
}

//check duplicacy of task in the same project on the same portal and organization.
public String seeTaskExistence(String projectName,String taskname,String orgportal) {
	String s=null;
	try{
		con=MyDataSource.getConnection();
		st=con.prepareStatement("select t.task_name from task t where t.task_name=?" +
			" and t.vproject_code=(select p.project_code from project p " +
			"where p.project_name=? and p.valid_org_inportal=?)");
		st.setString(1,taskname);
		st.setString(2,projectName);
		st.setString(3,orgportal);
		rs=st.executeQuery();
		 rs.next();
		 s=rs.getString(1);
		}catch(Exception e)
		{
			//System.out.println("The  error is in dynamiclist.java in seeTaskExistence method"+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	return s;	
}


public String[] roleList(String userId,String orgportal) {
	int count=1;
	try{
		con=MyDataSource.getConnection();
		st=con.prepareStatement("select r.role_name from role r,user_role_in_org uro,"+
				"user_in_org uio where uio.valid_user_id=? and uio.valid_orgportal=?"+
				" and uio.valid_key=uro.valid_key and uro.valid_role=r.role_id");
				st.setString(1,userId);
				st.setString(2,orgportal);
				rs=st.executeQuery();
				if(!rs.next())
				{
				st=con.prepareStatement("select role_name from role where "+
				"validorgportal=? order by role_Name asc");
				st.setString(1,orgportal);
				rs=st.executeQuery();
				rs.next();
				}
		 do
		 {
			 count++;
		 }while(rs.next());
	}catch(Exception e){//System.out.println("error in dyna list and in roleList="+e);
						}
	String s[]=new String[count];
	s[0]="--Select--";
	try{
		st=con.prepareStatement("select r.role_name from role r,user_role_in_org uro,"+
				"user_in_org uio where uio.valid_user_id=? and uio.valid_orgportal=?"+
				" and uio.valid_key=uro.valid_key and uro.valid_role=r.role_id");
				st.setString(1,userId);
				st.setString(2,orgportal);
				rs=st.executeQuery();
				if(!rs.next())
				{
				st=con.prepareStatement("select role_name from role where "+
				"validorgportal=? order by role_Name asc");
				st.setString(1,orgportal);
				rs=st.executeQuery();
				rs.next();
				}
		 do
		 {
			 s[i+1]=rs.getString(1);
				i++;
		 }while(rs.next());
			
		}catch(Exception e){//System.out.println("error in dyna list and in roleList="+e);
							}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return s;	
     }
}



        

