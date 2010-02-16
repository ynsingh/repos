package org.dei.edrp.pms.member;
import org.dei.edrp.pms.dataBaseConnection.MyDataSource;

import java.util.ArrayList;
import java.sql.*;
/**
 * Class TestList
 */
public class MemberList{
		
	//properties
	ArrayList<MemberBean> list = new ArrayList<MemberBean>();;

	//constructors
	public MemberList(){
		fillList();
	}
	
	public MemberList(String keySearch,String searchOption) {
        fillList(keySearch,searchOption);
    }

	//fill the list with dummy data
	public void fillList(){
		Connection con=null;
		try{
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select v.User_ID,v.PermittedBy,p.Project_Name,o.Org_Name "+
					"from validatetab v,project p,organisation o "+
					"where v.Project_ID=p.Project_ID and v.Org_ID=o.Org_ID");
			while(rs.next())
			{
								
	list.add(new MemberBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));

			}
			
		}
		catch(Exception e){}
		finally
		{
			MyDataSource.freeConnection(con);
		}
	}

	public void fillList(String keySearch,String searchOption) {
		Connection con=null;
		try {
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql
            PreparedStatement st = con.prepareStatement("select v.User_ID,v.PermittedBy,p.Project_Name,o.Org_Name " +
                    "from validatetab v,project p,organisation o " +
            "where v.Project_ID=p.Project_ID and v.Org_ID=o.Org_ID and v.user_id like ?");
            st.setString(1, keySearch+"%");
            ResultSet rs = st.executeQuery();
                    
            while (rs.next()) {
                list.add(new MemberBean(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)));
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
