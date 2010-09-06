package in.ac.dei.edrp.pms.organization;
/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.checkRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;

/** 
 * MyEclipse Struts
 * Creation date: 11-02-2009
 * XDoclet definition:
 * @struts.action path="/editorg" name="editorgform" input="editorg.jsp" scope="request" validate="true"
 */
/**
 * This class is responsile for updating the Organisation details according to Org_id.
  */
public class EditOrgAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * It updates the values of Organisation in database.
	 * And then sets the value of Offset and length.
	 * After successfull/failure on updation it returns the mapping forward string for next view.
	  * @param mapping It holds the action mapping information  used to invoke a struts action
	 * @param form It holds the object of bean class,named EditOrgForm
	 * @param request The HTTP servlet request,which is going to be processed
	 * @param response The HTTP servlet response,which is going to be created.
	 * @return ActionForward It returns successorg on successful updation and fail in updation failure for next view.
	 */ 
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgForm editorgform = (OrgForm) form;// TODO Auto-generated method stub
		String forwardmsg="editorgfail";
		Connection con=null;
		try{
			request.setAttribute("message","Organisation updation failed,because this organisation name is already exist. !!");
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql.
			if(!editorgform.getIname().trim().equalsIgnoreCase(editorgform.getOldorgname().trim()))
			{
			if(checkRecord.duplicacyChecker("Org_Id","organisation","Org_Name",editorgform.getIname().trim())!=null)
			{
				return mapping.findForward("editorgfail");
			}
			}
		/**
		 * update the 'organisation' table with the desired values.
		 * */
			String state_id=checkRecord.duplicacyChecker("state_id","state","state_name",editorgform.getIstate());
		PreparedStatement ps=con.prepareStatement("update organisation set Org_Name=?," +
				"Org_Address=?,Org_City=?,Org_State=?,Org_Phone=?,Org_Fax=?,Org_URL=? where Org_Id=?");
		ps.setString(1,editorgform.getIname());
		ps.setString(2,editorgform.getIaddress());
		ps.setString(3,checkRecord.twoFieldDuplicacyChecker("city_id","city","state_id",state_id,"city_name",editorgform.getIcity()));
		ps.setString(4,state_id);
		ps.setString(5,editorgform.getIphoneno());
		ps.setString(6,editorgform.getIfax());
		ps.setString(7,editorgform.getIurl());
		
		ps.setInt(8,Integer.parseInt(editorgform.getOrgid()));//converting String into Integer.
		int n=ps.executeUpdate();
		if(n>0)/*if n is greater than zero it means update operation is successful.*/
		{	
			forwardmsg="viewOrgList";
		}
		}
		catch(Exception e){	}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return mapping.findForward(forwardmsg);//calling to that jsp page which is assigned in variable forwardmsg.
		
	}
}