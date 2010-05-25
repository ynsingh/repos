package in.ac.dei.edrp.pms.home;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class PortalOrgBeanList {

	private List<PortalOrgBean> list=new ArrayList<PortalOrgBean>();
	
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;

	public PortalOrgBeanList(String userid)
	{
		fillPortal(userid);
	}
	
	public List<PortalOrgBean> fillPortal(String User)
	{
		try
		{
			con=MyDataSource.getConnection();
			ps=con.prepareStatement("select distinct p.portal_name from" +
				" portal p,org_into_portal oip,user_in_org uio " +
				"where p.portal_id=oip.portal_id and " +
				"uio.Valid_OrgPortal=oip.valid_org_inportal and uio.valid_user_id=?");
			ps.setString(1,User);
			rs=ps.executeQuery();
			while(rs.next())
			{
				list.add(new PortalOrgBean(rs.getString(1)));
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in PortalOrgBeanList anil="+e.getMessage());	
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return list;
	}
	
	public List<PortalOrgBean> getList() {
		return list;
	}

	public void setList(List<PortalOrgBean> list) {
		this.list = list;
	}

}
