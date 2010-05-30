package in.ac.dei.edrp.pms.addorg_in_portal;

import in.ac.dei.edrp.pms.myMail.SendingMail;
import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.checkRecord;
import in.ac.dei.edrp.pms.viewer.CodeGenerator;
import in.ac.dei.edrp.pms.member.PasswordGenerator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/** 
 * MyEclipse Struts
 * Creation date: 03-16-2010
 * <br>
 * XDoclet definition:
 * @struts.action path="/addorgportal" name="orgportalform" input="/WEB-INF/JspFiles/orgportal/addorgportal.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/index.jsp"
 * @author Anil Kumar Tiwari <b>mailto:</b>aniltiwari08@gmail.com 
 */
public class AddOrgInPortalAction extends Action {
	/*
	 * Generated Methods
	 */
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AddOrgInPortalForm orgportalform = (AddOrgInPortalForm)form;
		//HttpSession session=request.getSession();
		
//		System.out.println("Email Id is "+orgportalform.getEmailid());
//		System.out.println("Role is "+orgportalform.getRole());
//		System.out.println("Portal name is "+orgportalform.getPortalname());
//		System.out.println("Organisation name is "+orgportalform.getOrganisation());
		
		Connection con=null;
		PreparedStatement ps=null;
		String forwardmsg="addorginportalfail";
		String valid_code="";
		String pass1="Please use your old password.";
		int x=0;
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
		request.setAttribute("message","The organisation has not been added in the desired Portal.");
		con=MyDataSource.getConnection();
		String role_id=checkRecord.twoFieldDuplicacyChecker("Role_ID","role","Role_Name",orgportalform.getRole().trim(),"ValidOrgPortal",null);
		String portal_id=checkRecord.duplicacyChecker("Portal_ID","portal","Portal_Name",orgportalform.getPortalname());
		String org_id=checkRecord.duplicacyChecker("Org_ID","organisation","Org_Name",orgportalform.getOrganisation());
		
		if(checkRecord.twoFieldDuplicacyChecker("valid_org_inportal","org_into_portal","org_id",org_id,"portal_id",portal_id)==null)
		{
			System.out.println("org into portal does not exist.");
		/*
		 * Inserting the record into org_into_portal table.
		 */
			ps=con.prepareStatement("insert into org_into_portal values(?,?,?)");
			ps.setInt(1,Integer.parseInt(portal_id));
			ps.setInt(2,Integer.parseInt(org_id));
			ps.setInt(3,0);
			ps.executeUpdate();
		}
		if(checkRecord.duplicacyChecker("User_ID","user_info","user_id",orgportalform.getEmailid().trim())==null)
		{
			System.out.println("user in user info does not exist.");
		/*
		 * Inserting the record into user_info table.
		 */
		ps=con.prepareStatement("insert into user_info (User_ID,Created_On,Updated_On) " +
				"values(?,NOW(),NOW())");
		ps.setString(1,orgportalform.getEmailid().trim());
		ps.executeUpdate();
		}
		String orginportal=checkRecord.twoFieldDuplicacyChecker("valid_org_inportal","org_into_portal","org_id",org_id,"portal_id",portal_id);
		request.setAttribute("message","This user already work in this portal and organisation on the same role.");
		if(checkRecord.twoFieldDuplicacyChecker("Valid_User_ID","user_in_org","valid_user_id",orgportalform.getEmailid().trim(),"Valid_OrgPortal",orginportal)==null)
		{
			System.out.println("user in user_in_org does not exist.");
		/*
		 * Inserting the record into user_in_org table.
		 */
		ps=con.prepareStatement("insert into user_in_org values(?,?,?)");
		ps.setString(1,orgportalform.getEmailid().trim());
		ps.setString(2,orginportal);
		PreparedStatement pst=con.prepareStatement("select max(substr(Valid_Key,5)) from user_in_org where substr(Valid_Key,1,4)=Date_Format(Now(),'%Y')");
		ResultSet rst=pst.executeQuery();
		rst.next();
		String maxvalue=rst.getString(1);
		if(maxvalue!=null)
			{
				valid_code=CodeGenerator.gettingCode(Long.parseLong(maxvalue)+1,6);
			}
			else
				valid_code=CodeGenerator.gettingCode(1,6);
		ps.setString(3,valid_code);
		ps.executeUpdate();
		}
	
		ps=con.prepareStatement("insert into user_role_in_org values(?,?,?,?)");
		ps.setString(1,checkRecord.twoFieldDuplicacyChecker("Valid_Key","user_in_org","valid_user_id",orgportalform.getEmailid().trim(),"Valid_OrgPortal",orginportal));
		ps.setInt(2,Integer.parseInt(role_id));
		ps.setString(3,"Default");//which authority default/member.
		ps.setString(4,"Active");//status active/inactive.
		x=ps.executeUpdate();
		
			if(x>0) /*if x is greater than zero it means insertion operation is successful.*/
			{
				if(checkRecord.duplicacyChecker("login_user_id","login","login_user_id",orgportalform.getEmailid().trim())==null)
				{
				PreparedStatement ps1=con.prepareStatement("insert into login values(?,?,SHA1(?))");
				ps1.setString(1,orgportalform.getEmailid().trim());
				ps1.setString(2,"User");
				pass1=PasswordGenerator.generatePassword(3,8).toLowerCase();
				ps1.setString(3,pass1);
				ps1.executeUpdate();
				}
				System.out.println("password="+pass1);
				//System.out.println("valid code"+valid_code);
				String url="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
				System.out.println("url="+url);
				String s4="Welcome to Project Management System," +
						"\n Your account has been created successfully.\n " +
						"click on the following link, " +url+
						" and proceed by your login.\n" +
						" User Id: "+orgportalform.getEmailid().trim()+
						"\n Password: "+pass1+"\n Thanks !";
				boolean bool=SendingMail.sendMail(s4,"pms.dei2010@gmail.com",orgportalform.getEmailid().trim(),"PMS Info");
				ActionErrors errors = new ActionErrors();
				ActionMessage error=null;
				if(bool)
				{
					error = new ActionMessage("msg.addorg_in_portal.added");
				}
				else
				{
					error = new ActionMessage("msg.addorg_in_portal.added1");
				}
					errors.add("addorgportal",error);
				saveErrors(request,errors);
				forwardmsg="addorginportalsuccess"; 
				System.out.println("body="+s4);

			}
		
		}
		catch(Exception e)
		{
			System.out.println("error in add org in portalaction ="+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}

		return mapping.findForward(forwardmsg);
	}

}

