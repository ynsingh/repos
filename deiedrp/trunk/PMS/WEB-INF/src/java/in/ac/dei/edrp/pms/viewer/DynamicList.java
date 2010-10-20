package in.ac.dei.edrp.pms.viewer;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import java.util.*;
import java.text.*;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * This class is used for populating dynamic data and used for DWR
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>  
 * 
 */
public class DynamicList{

	ResultSet rs=null;
	Connection	con=null;
	PreparedStatement st=null;

	public String getProjectName(String pcode,String orgportal)
	{
		String s=null;
		try{
			con=MyDataSource.getConnection();
	st=con.prepareStatement("select p.project_name from project p " +
			"where p.Project_Code=? and p.valid_org_inportal=?");
			 st.setString(1,pcode);
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
	public ArrayList<String> generateOrgEmployeeList(String projectName,String orgportal) {
		ArrayList<String> orgEmployeeList = new ArrayList<String>();
		try{
			con=MyDataSource.getConnection();
			PreparedStatement st=con.prepareStatement("select u.valid_user_id,r.role_name from " +
					"user_in_org u, user_role_in_org uro,role r " +
					"where u.valid_orgportal=? and u.valid_key=uro.valid_key " +
					"and uro.valid_role=r.role_id and u.valid_user_id " +
					"not in(select u.valid_user_id from validatetab v,user_in_org u," +
					"project p where v.valid_user_key=u.valid_key and" +
					" p.project_code=v.valid_project_code and p.project_name=?" +
					" and p.valid_org_inportal=?)" +
					" order by u.valid_user_id asc");
			st.setString(1,orgportal); 
			st.setString(2,projectName);
			st.setString(3,orgportal);
				 rs=st.executeQuery();
				 while(rs.next())
				{
					 orgEmployeeList.add(rs.getString(1)+" ("+rs.getString(2)+")");
				}
			}catch(Exception e){System.out.println("The  error in DynamicList class(generateOrgEmployeeList)"+e);}
			finally
			{
				MyDataSource.freeConnection(con);
			}
			return orgEmployeeList;	
	}
	
	
	public ArrayList<String> generateProjectTeamList(String projectName,String orgportal,int selectvalue) {
		ArrayList<String> projectTeamList=new ArrayList<String>();
		try{
			con=MyDataSource.getConnection();
				if(selectvalue!=0)
					projectTeamList.add("--Select--");
			PreparedStatement st=con.prepareStatement("select u.valid_user_id,r.role_name " +
					"from validatetab v,user_in_org u,project p,role r" +
					" where v.valid_user_key=u.valid_key and" +
					" p.project_code=v.valid_project_code and " +
					"p.project_name=? and p.valid_org_inportal=? and v.valid_role_id=r.role_id" +
					" order by u.valid_user_id asc");
			 st.setString(1,projectName);
			 st.setString(2,orgportal);
				 rs=st.executeQuery();
				 while(rs.next())
				{
					 projectTeamList.add(rs.getString(1)+" ("+rs.getString(2)+")");
				}
			}catch(Exception e){System.out.println("The  error in DynamicList class(generateProjectTeamList)"+e);}
			finally
			{
				MyDataSource.freeConnection(con);
			}
			
			return projectTeamList;	
	}
	
	
	/**
	 * It is used for getting resource list recursively in case of create task, assign task
	 * @param uname
	 * @param valid_key
	 * @param projectName
	 * @param orgportal
	 * @return
	 */
	/*
	 * It is working fine but now it is commented on 17 august 2010
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
	*/
	
	/**
	 * It is used in case of creating,editing the task it generates list of dependent task
	 * @param taskName
	 * @param projectName
	 * @param orgportal
	 * @return task list
	 */
	public ArrayList<String> taskList(String taskName,String projectName,String orgportal) {
		ArrayList<String> taskList = new ArrayList<String>();
		try{
			//System.out.println("task name="+taskName);
			con=MyDataSource.getConnection();
			taskList.add("No");
			PreparedStatement st=con.prepareStatement("select t.task_name from task t," +
					"project p where (t.task_name!=? and p.project_name=? and " +
			"t.vproject_code=p.project_code and p.valid_org_inportal=?) order by t.Task_Name asc");
				st.setString(1,taskName);
				st.setString(2,projectName);
				st.setString(3,orgportal);
				 rs=st.executeQuery();
				 while(rs.next())
				{
					 taskList.add(rs.getString(1));
				}
			}catch(Exception e){System.out.println("The  error in DynamicList class(taskList)"+e);}
			finally
			{
				MyDataSource.freeConnection(con);
			}
			return taskList;	
	}

	/**
	 * It is used for getting task list of the selected project in case of assign task
	 * @param projectName
	 * @param orgportal
	 * @return task list
	 */
	public ArrayList<String> generateTaskList(String projectName,String orgportal) {
		ArrayList<String> taskList = new ArrayList<String>();
		try{
			con=MyDataSource.getConnection();
			taskList.add("--Select--");
			PreparedStatement st=con.prepareStatement("select t.task_name from task t,project p" +
					" where (p.project_name=? and t.vproject_code=p.project_code " +
					"and p.valid_org_inportal=?) and t.Task_Status='Not Completed'" +
					" and t.task_id not in(select task_id from task_with_user) order by t.Task_Name");
				st.setString(1,projectName);
				st.setString(2,orgportal);
				 rs=st.executeQuery();
				 while(rs.next())
				{
					 taskList.add(rs.getString(1));
				}
			}catch(Exception e){System.out.println("The  error in DynamicList class(taskList)"+e);}
			finally
			{
				MyDataSource.freeConnection(con);
			}
			return taskList;	
	}

		
/**
 * for getting organization list according portal and login user
 * @param portalname
 * @param username
 * @return organization list
 */
public ArrayList<String> organisationPortalList(String portalname,String username) {
	ArrayList<String> orgPortalList = new ArrayList<String>();
	try{
		con=MyDataSource.getConnection();
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
				orgPortalList.add(rs.getString(1));
			}
		}catch(Exception e){
			//System.out.println("The  error is"+e);
			}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return orgPortalList;	
}

/**
 * for getting role list according portal,organization and login user
 * @param portalname
 * @param username
 * @return organization list
 */
public ArrayList<String> userRoleList(String portalname,String orgname,String username) {
	ArrayList<String> roleList = new ArrayList<String>();
	try{
		con=MyDataSource.getConnection();
		st=con.prepareStatement("select r.role_name from portal p,organisation o," +
				"org_into_portal oinp,user_in_org uino,user_role_in_org uro,role r " +
				"where oinp.org_id=o.org_id and p.portal_id=oinp.portal_id " +
				"and p.portal_name=? and uino.valid_orgportal=oinp.valid_org_inportal " +
				"and o.org_name=? and uino.valid_user_id=? and uro.valid_key=uino.valid_key " +
				"and uro.valid_role=r.role_id");
		st.setString(1,portalname);
		st.setString(2,orgname);
		st.setString(3,username);
				 rs=st.executeQuery();
			while(rs.next())
			{
				roleList.add(rs.getString(1));
			}
		}catch(Exception e){
			//System.out.println("The  error is"+e);
			}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return roleList;	
}

/**
 * For getting organization list of an portal
 * @author Anil Kumar Tiwari
 * @param portalname name of the desired portal
 * @return organization list
 */
public ArrayList<String> orgPortalList(String portalname) {
	ArrayList<String> orgPortalList = new ArrayList<String>();
	try{
		con=MyDataSource.getConnection();
		st=con.prepareStatement("select o.org_name from organisation o,portal p," +
				"org_into_portal oip where oip.portal_id=p.portal_id and " +
				"oip.org_id=o.org_id and p.portal_name=?");
		st.setString(1,portalname);
				 rs=st.executeQuery();
			while(rs.next())
			{
				orgPortalList.add(rs.getString(1));
			}
		}catch(Exception e){
			//System.out.println("The  error is"+e);
			}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return orgPortalList;	
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
		catch(Exception e){
			//System.out.println("error in task end date="+e);
			}
		return retvalue;
	}

/**
 * This is used for getting existing portal or organization name 
 * @author Anil Kumar Tiwari
 * @param pname
 * @param info
 * @return String
 */
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

/**
 * getting name of task of same project on the same portal and organization.
 * @param projectName
 * @param taskname
 * @param orgportal
 * @return task name
 */
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

/**
 * This method is used for getting all the roles of an organization.
 * @author Anil Kumar Tiwari
 * @param userId email-id of the selected user
 * @param orgportal id of organization with portal
 * @return roles list
 */
public ArrayList<String> roleList(String userId,String orgportal) {
	ArrayList<String> rolelist = new ArrayList<String>();
	try{
		con=MyDataSource.getConnection();
		rolelist.add("--Select--");
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
			 rolelist.add(rs.getString(1));
		 }while(rs.next());
			
		}catch(Exception e){//System.out.println("error in dyna list and in roleList="+e);
							}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return rolelist;	
     }


public ArrayList<String> cityList(String stateName) {
	ArrayList<String> cityListofState = new ArrayList<String>();
	try{
		con=MyDataSource.getConnection();
		cityListofState.add("--Select--");
		PreparedStatement st=con.prepareStatement("select c.city_name from city c," +
				"state s where s.state_name=? and s.state_id=c.state_id" +
				" order by c.city_name");
		
		 st.setString(1,stateName);
			 rs=st.executeQuery();
			 while(rs.next())
			{
				 cityListofState.add(rs.getString(1));
			}
		}catch(Exception e){System.out.println("The  error in DynamicList class(taskList)"+e);}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return cityListofState;	
}


}



        

