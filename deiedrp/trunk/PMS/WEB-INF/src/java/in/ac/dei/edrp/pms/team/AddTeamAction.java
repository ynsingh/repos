package in.ac.dei.edrp.pms.team;
import in.ac.dei.edrp.pms.viewer.DynamicList;
import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.checkRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/** 
 * Creation date: 02-Aug-2010
 * @struts:action scope="null" validate="false"
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a> 
 */

public class AddTeamAction extends Action{
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws SQLException 
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response){
		PmsTeamForm pmsteamform = (PmsTeamForm) form;
		String s[]=pmsteamform.getSelect2();
		String roleid=null;
		String forwardString="invalid";
		HttpSession session=request.getSession();
		if((String)session.getAttribute("mysession")!=null)
		{	
			forwardString="pmsTeamPage";
		
		if(Integer.parseInt(pmsteamform.getDefValueOfSelect2())==-1)
		{
			s=null;
		}
				
		String uid=(String)session.getAttribute("uid");
		String permittedBy=uid;
		try{
			
		if(s!=null)
		{
		String userrole=null;
		String userid=null;
		String orgportal=(String)session.getAttribute("validOrgInPortal");
		//System.out.println("project name="+pmsteamform.getProjectName());
		
		/*
		 * established database connection.
		 * */
		con=MyDataSource.getConnection();
		for(int i=0;i<pmsteamform.getSelect2().length;i++)
		{
			//System.out.println("select2="+s[i]);
			StringTokenizer stringTokenizer = new StringTokenizer(s[i],"()");
			userid=stringTokenizer.nextToken().trim();
			userrole=stringTokenizer.nextToken();
					//System.out.println(userid);
					//System.out.println(userrole);
				
		PreparedStatement check=con.prepareStatement("select v.valid_id from validatetab v," +
				"user_in_org u,project p where v.valid_user_key=u.valid_key" +
				" and u.valid_user_id=? and u.valid_orgportal=?" +
				" and p.project_code=v.valid_project_code and p.project_name=?");
		
		check.setString(1,userid);
		check.setString(2,orgportal);
		check.setString(3,pmsteamform.getProjectName());
		//check.setString(4,projassignform.getRoleName());
		rs=check.executeQuery();
		if(!rs.next())//It means the user does not working in the selected project on the selected role.
	    {
			//for adding member into validatetab
			AddMemberIntoProject.insertIntoValidatetab(userrole, userid,
				pmsteamform.getProjectName(), orgportal, permittedBy);	
			/*if n>0 it means insertion operation successful in 'validatetab' table.*/
	    }
		}
		//for removing
		ArrayList<String> firstList = new ArrayList<String>();
		ArrayList<String> secondList = new ArrayList<String>();
		for(int i=0;i<pmsteamform.getSelect2().length;i++)
		{
			//System.out.println("(select2)in first list="+s[i]);
			firstList.add(s[i]);
		}
		String s2[]=new DynamicList().generateProjectTeamList(pmsteamform.getProjectName(), orgportal,0);
		for(int i=0;i<s2.length;i++)
		{
		//System.out.println("in second list="+s2[i]);
			secondList.add(s2[i]);
		}
		//System.out.println("first List="+firstList);
		//System.out.println("second List="+secondList);
		// Remove all elements in firstList from secondList
		secondList.removeAll(firstList);

		// Show the "after" list
		//System.out.println("Result: " + secondList);
		for(int i=0;i<secondList.size();i++)
		{		
			//System.out.println(secondList.get(i));
			StringTokenizer stringTokenizer = new StringTokenizer(secondList.get(i),"()");
			userid=stringTokenizer.nextToken().trim();
			userrole=stringTokenizer.nextToken();
			roleid=checkRecord.twoFieldDuplicacyChecker("Role_Id","role","Role_Name",userrole,"ValidOrgPortal",orgportal);
			if(roleid==null)
			{
				PreparedStatement pst1=con.prepareStatement("select r.role_id from role r," +
						"user_in_org u,user_role_in_org uro where u.valid_user_id=?" +
						" and u.valid_orgportal=? and u.valid_key=uro.valid_key " +
						"and uro.permittedBy=(select l.login_user_id from login l " +
						"where l.authority='Super Admin') and uro.valid_role=r.role_id " +
						"and r.role_name=?");
				pst1.setString(1,userid);
				pst1.setString(2,orgportal);
				pst1.setString(3, userrole);
				ResultSet rst1=pst1.executeQuery();
				rst1.next();
				roleid=rst1.getString(1);
			}
			ps=con.prepareStatement("delete from validatetab where valid_user_key=" +
					"(select valid_key from user_in_org where valid_user_id=? " +
					"and valid_orgportal=?)" +
					"and valid_project_code=(select project_code from project where " +
					"project_name=? and Valid_Org_Inportal=?) and valid_role_id=?");
			ps.setString(1, userid);
			ps.setString(2, orgportal);
			ps.setString(3, pmsteamform.getProjectName());
			ps.setString(4, orgportal);
			ps.setString(5, roleid);
			ps.executeUpdate();
		}

		}//if closed
		else //if(s==null)
		{
			ps=con.prepareStatement("delete from validatetab where valid_project_code=" +
					"(select project_code from project where " +
					"project_name=? and Valid_Org_Inportal=?)");
			ps.setString(1, pmsteamform.getProjectName());
			ps.setString(2,(String)session.getAttribute("validOrgInPortal"));
			ps.executeUpdate();
			
		}
		}
		catch(Exception e)
		{
			System.out.println("error in AddTeamAction="+e);
			}
		
		}
		return mapping.findForward(forwardString);
		
	}
}
