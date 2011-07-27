package in.ac.dei.edrp.pms.addorg_in_portal;

import in.ac.dei.edrp.pms.myMail.SendingMail;
import in.ac.dei.edrp.pms.adminConfig.ReadPropertiesFile;
import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.PasswordGenerator;
import in.ac.dei.edrp.pms.viewer.checkRecord;
import in.ac.dei.edrp.pms.viewer.CodeGenerator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;

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
 * 
 * XDoclet definition:
 * @struts.action path="/addorgportal" name="orgportalform" input="/WEB-INF/JspFiles/orgportal/addorgportal.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/index.jsp"
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
	
		Connection con=null;
		PreparedStatement ps=null;
		String forwardmsg="addorginportalfail";
		String valid_code="";
		String pass1="Please use your old password.";
		int x=0;
		String permitted_By=null;
		ActionErrors errors = new ActionErrors();
		ActionMessage error=null;
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

		String orginportal=checkRecord.twoFieldDuplicacyChecker("valid_org_inportal","org_into_portal","org_id",org_id,"portal_id",portal_id);
		request.setAttribute("message","The insertion operation failed.");
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
		String Valid_Key = checkRecord.twoFieldDuplicacyChecker("Valid_Key","user_in_org","valid_user_id",orgportalform.getEmailid().trim(),"Valid_OrgPortal",orginportal);
		permitted_By=checkRecord.duplicacyChecker("login_user_id","login","authority","Super Admin");
		if(checkRecord.twoFieldDuplicacyChecker("Valid_Key","user_role_in_org","Valid_Key",Valid_Key,"Valid_Role",role_id)==null)
		{
			System.out.println("user role in user_role_in_org does not exist.");
		ps=con.prepareStatement("insert into user_role_in_org values(?,?,?,?,?)");
		ps.setString(1,Valid_Key);
		ps.setInt(2,Integer.parseInt(role_id));
		ps.setString(3,permitted_By);
		ps.setString(4,"Default");//which authority default/member.
		ps.setString(5,"Active");//status active/inactive.
		x=ps.executeUpdate();
		}
		if(x>0) /*if x is greater than zero it means insertion operation is successful.*/
		{
			//update validatetab on 21june 2010
			ps=con.prepareStatement("update validatetab set permitted_by=?," +
					"valid_role_id=? where valid_user_key=? and permitted_by!=?");
			ps.setString(1,permitted_By);
			ps.setInt(2,Integer.parseInt(role_id));
			ps.setString(3,checkRecord.twoFieldDuplicacyChecker("Valid_Key","user_in_org","valid_user_id",orgportalform.getEmailid().trim(),"Valid_OrgPortal",orginportal));
			ps.setString(4,permitted_By);
			ps.executeUpdate();
			
				if(checkRecord.duplicacyChecker("login_user_id","login","login_user_id",orgportalform.getEmailid().trim())==null)
				{
				PreparedStatement ps1=con.prepareStatement("insert into login values(?,?,SHA1(?))");
				ps1.setString(1,orgportalform.getEmailid().trim());
				ps1.setString(2,"User");
				pass1=PasswordGenerator.generatePassword(3,5).toLowerCase();
				ps1.setString(3,pass1);
				ps1.executeUpdate();
				}
				//System.out.println("password="+pass1);
				//System.out.println("valid code"+valid_code);
				String url="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
				//System.out.println("url="+url);
				Locale locale = new Locale("en", "US");
				ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//edrp//pms//propertiesFile//ApplicationResources",locale);
				String s4=message.getString("body.text.mail") + " "+url+
				"\n"+message.getString("label.user")+": "+orgportalform.getEmailid().trim()+
				"\n"+message.getString("label.password")+": "+pass1+
				"\n "+message.getString("body.text.mail.note")+
				"\n "+message.getString("body.text.mail.thanks");
				boolean bool=SendingMail.sendMail(s4,orgportalform.getEmailid().trim(),
						message.getString("mail.subject.newmember.addedby.superadmin"),
						ReadPropertiesFile.mailConfig(getServlet().getServletContext().getRealPath("/")+"WEB-INF/"));
				
				if(bool)
				{
					error = new ActionMessage("msg.addorg_in_portal.mailSuccess");
				}
				else
				{
					error = new ActionMessage("msg.addorg_in_portal.mailFail");
				}
					errors.add("addOrgIntoPortalMessage",error);
				saveErrors(request,errors);
				forwardmsg="addorginportalsuccess"; 
				//System.out.println("body="+s4);
			}
		else{
			error = new ActionMessage("msg.addorg_in_portal.notadded");
			errors.add("addOrgIntoPortalMessage",error);
			saveErrors(request,errors);
			forwardmsg="addorginportalsuccess"; 
		}
		//}//outer if
		
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

