package in.ac.dei.edrp.pms.member;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.myMail.SendingMail;
import in.ac.dei.edrp.pms.viewer.CodeGenerator;
import in.ac.dei.edrp.pms.viewer.checkRecord;

/** 
 * MyEclipse Struts
 * Creation date: 04-15-2010
 * 
 * XDoclet definition:
 * @struts.action path="/addnewmember" name="newmemberform" scope="request" validate="true"
 * @struts.action-forward name="addmembersuccess" path="page.addnewmember"
 * @struts.action-forward name="addmemberfail" path="page.orginfo"
 */
public class AddNewMemberAction extends Action {
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
		NewMemberForm newmemberform = (NewMemberForm) form;// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String valid_code="";
		String pass1="Please use your old password.";
		boolean bool=false;
		int recInUser_Info=0,recInUser_In_Org=0;
		Connection con=null;
		PreparedStatement ps=null;
		String retstring="addmemberfail";
		request.setAttribute("message", "User could not be added ,because this user already exist!!");
		try{
			con=MyDataSource.getConnection();
			if(checkRecord.duplicacyChecker("User_ID","user_info","User_ID",newmemberform.getEmailid().trim())==null)
			{
				System.out.println("does not exist.");
				
				ps=con.prepareStatement("insert into user_info " +
					"(User_ID,First_Name,Last_Name,Phone_No,Skills,Experince,Created_On,Updated_On) " +
					"values(?,?,?,?,?,?,CURDATE(),CURDATE())");
				ps.setString(1, newmemberform.getEmailid().trim());
				ps.setString(2, newmemberform.getFirstname());
				ps.setString(3, newmemberform.getLastname());
				ps.setString(4, newmemberform.getPhoneno());
				ps.setString(5, newmemberform.getSkill());
				ps.setInt(6, Integer.parseInt(newmemberform.getExperience()));
//				ps.setString(7, newmemberform.getSecurequestion());
//				ps.setString(8, newmemberform.getSecureanswer());
				recInUser_Info=ps.executeUpdate();
			}
			ActionErrors errors = new ActionErrors();
			ActionMessage error=null;
			if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
			{
				request.setAttribute("message","This user already work in this portal and organisation.");
				if(checkRecord.twoFieldDuplicacyChecker("Valid_User_ID","user_in_org","valid_user_id",newmemberform.getEmailid().trim(),"Valid_OrgPortal",(String)session.getAttribute("validOrgInPortal"))==null)
				{
					System.out.println("user in user_in_org does not exist.");
				/*
				 * Inserting the record into user_in_org table.
				 */
				ps=con.prepareStatement("insert into user_in_org values(?,?,?)");
				ps.setString(1,newmemberform.getEmailid().trim());
				ps.setString(2,(String)session.getAttribute("validOrgInPortal"));
				PreparedStatement pst=con.prepareStatement("select max(substr(Valid_Key,5)) from user_in_org where substr(Valid_Key,1,4)=Date_Format(Now(),'%Y')");
				ResultSet rst=pst.executeQuery();
				rst.next();
				String maxvalue=rst.getString(1);
				if(maxvalue!=null)
					{
						valid_code=CodeGenerator.gettingCode(Integer.parseInt(maxvalue)+1,6);
					}
					else
						valid_code=CodeGenerator.gettingCode(1,6);
				ps.setString(3,valid_code);
				recInUser_In_Org=ps.executeUpdate();
				
				if(recInUser_In_Org>0) /*if recInUser_In_Org is greater than zero it means insertion operation is successful.*/
				{
						if(checkRecord.duplicacyChecker("login_user_id","login","login_user_id",newmemberform.getEmailid().trim())==null)
						{
						PreparedStatement ps1=con.prepareStatement("insert into login values(?,?,SHA1(?))");
						ps1.setString(1,newmemberform.getEmailid().trim());
						ps1.setString(2,"User");
						pass1=PasswordGenerator.generatePassword(3,8).toLowerCase();
						ps1.setString(3,pass1);
						ps1.executeUpdate();
						}
						System.out.println("password="+pass1);
						//System.out.println("valid code"+valid_code);
						String url="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
						String s4="Welcome to Project Management System," +
							"\n Your account has been created successfully.\n " +
							"click on the following link," +url+
							" and proceed by your login.\n" +
							" User Id: "+newmemberform.getEmailid().trim()+
							"\n Password: "+pass1+"\n Thanks !";
						bool=SendingMail.sendMail(s4,"pms.dei2010@gmail.com",newmemberform.getEmailid().trim(),"PMS Info");
						System.out.println("body="+s4);
					}
				}
			}
			
			if(bool)
			{
				error = new ActionMessage("msg.addmember_in_orgportal.added");
				errors.add("membermsg",error);
				saveErrors(request,errors);
				retstring="addmembersuccess";
			}
			else if(recInUser_In_Org>0)
			{
				error = new ActionMessage("msg.addmember_in_orgportal.added1");
				errors.add("membermsg",error);
				saveErrors(request,errors);
				retstring="addmembersuccess";
			}
			
			else if(recInUser_Info>0)
			{
				error = new ActionMessage("msg.member.added");
				errors.add("membermsg",error);
				saveErrors(request,errors);
				retstring="addmembersuccess";
			}
			
			
		}catch(Exception e)
		{
			
			System.out.println("Exception is e="+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return mapping.findForward(retstring);
	}
}