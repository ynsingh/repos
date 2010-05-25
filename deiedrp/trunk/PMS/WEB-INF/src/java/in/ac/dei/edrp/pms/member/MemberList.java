package in.ac.dei.edrp.pms.member;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;

import java.util.ArrayList;
import java.sql.*;
/**
 * Class MemberList
 */
public class MemberList{
		
	//properties
	ArrayList<MemberBean> list = new ArrayList<MemberBean>();;

	//constructors
	public MemberList(){
		fillList();
	}
	public MemberList(String uid){
		if(uid.equalsIgnoreCase("Active"))
		fillList();
		else if(uid.equalsIgnoreCase("Inactive"))
		fillList(uid);
		
	}
	public MemberList(String keySearch,String searchOption) {
        fillList(keySearch,searchOption);
    }

	//fill the list of active users
	public void fillList(){
		Connection con=null;
		try{
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select uio.valid_user_id,p.portal_name,o.org_name," +
					"r.role_name,uio.valid_key,r.role_id from user_in_org uio,role r,user_role_in_org uro," +
					"org_into_portal oip,portal p,organisation o " +
					"where uio.valid_orgportal=oip.valid_org_inportal and " +
					"uro.valid_key=uio.valid_key and uro.valid_role=r.role_id " +
					"and oip.portal_id=p.portal_id and o.org_id=oip.org_id");
			while(rs.next())
			{
								
	list.add(new MemberBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));

			}
			
		}
		catch(Exception e){}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	}

	//for showing the list of inactive users
	public void fillList(String uid){
		Connection con=null;
		try{
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql
			PreparedStatement st=con.prepareStatement("select distinct u.user_id,u.first_name,u.last_name,u.skills,u.experince" +
					" from user_info u where u.user_id NOT IN(SELECT valid_user_id " +
					"FROM user_in_org) and u.user_id!=(select l.login_user_id from login l where l.authority='Super Admin')");
			
			ResultSet rs=st.executeQuery();
			while(rs.next())
			{
	list.add(new MemberBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),""));
			}
			
		}
		catch(Exception e){System.out.println(e);}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	}
	//above method is end here
	public void fillList(String keySearch,String searchOption) {
		Connection con=null;
		try {
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql
            PreparedStatement st = con.prepareStatement("select u.valid_user_id,p.portal_name,o.org_name," +
					"r.role_name,u.valid_key from user_in_org u,role r,user_role_in_org ur," +
					"org_into_portal op,portal p,organisation o " +
					"where u.valid_orgportal=op.valid_org_inportal and " +
					"ur.valid_key=u.valid_key and ur.valid_role=r.role_id " +
					"and op.portal_id=p.portal_id and o.org_id=op.org_id and u.valid_user_id like ?");
            st.setString(1, keySearch+"%");
            ResultSet rs = st.executeQuery();
                    
            while (rs.next()) {
                list.add(new MemberBean(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),rs.getString(5),""));
            }
        }
		catch(Exception e){}
		finally
		{
			MyDataSource.freeConnection(con);
		}
    }
	
		// getter and setter methods
	public ArrayList<MemberBean> getList() {
		return list;
	}
	public void setList(ArrayList<MemberBean> list) {
		this.list = list;
	}

}
