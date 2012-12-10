package org.iitk.brihaspati.modules.actions;



import java.io.File;
import java.util.Properties;
import java.util.List;
import java.util.Vector;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.actions.SecureAction_Institute_Admin;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.DeleteInstituteUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import babylon.babylonUserTool;

	
/**
 * This class is responsible for Removing Secondary Admin and Changing their permission.
 * @author <a href="mailto:mail2sunil00@gmail.com">Sunil Yadav</a>
 */
	
	
	public class AdminList_Action extends SecureAction_Institute_Admin   {
        		
		private String LangFile=new String();
		private Log log = LogFactory.getLog(this.getClass());
		  
			
	public void AddAdmin(RunData data, Context context)
        {
                        StringUtil str=new StringUtil();
                        ParameterParser pp = data.getParameters();
                        String usermgmt = "";
                        String mail_msg="";
                        LangFile = (String)data.getUser().getTemp("LangFile");
                        MultilingualUtil mu = new MultilingualUtil();
                        /**
                        *   Get parameter passed from templates.
                        */
                        String adminfname = pp.getString("IADMINFNAME");
                        String adminlname = pp.getString("IADMINLNAME");
                        String admindesig = pp.getString("IADMINDESIGNATION");
                        String adminemail = pp.getString("IADMINEMAIL");
                        String adminusername = adminemail;
                        String adminpass = adminemail;
                        String mode = pp.getString("mode");
                        context.put("mode",mode);
                        String instituteid = pp.getString("Institute_Id");
                        context.put("Institute_Id",instituteid);
                        String instName=InstituteIdUtil.getIstName(Integer.parseInt(instituteid));
                        context.put("Institute_Name",instName);
			   /**
                        *   Create password string by spliting email with "@" . 
                        */

                        String adminpassword []= adminpass.split("@");
                        String password = adminpassword[0];
                        //String encrPassword;
                        Criteria crit = new Criteria();
                        try{

                                /**
                                *   Check for user to already exist in same institute as an admin.
                                */

                                crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteid);
                                crit.add(InstituteAdminUserPeer.ADMIN_UNAME,adminemail);
                                List userexistininstitute=InstituteAdminUserPeer.doSelect(crit);
                                if(userexistininstitute.size()==0)
                                {
                                        int inststat=0;
                                        /**
                                        *   Check for special character.
                                        */
                                        if(str.checkString(adminusername)==-1 && str.checkString(adminfname)==-1 && str.checkString(adminlname)==-1)
                                        {
                                                crit=new Criteria();
                                                crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
                                                List instdetail = InstituteAdminRegistrationPeer.doSelect(crit);
                                                inststat = ((InstituteAdminRegistration)instdetail.get(0)).getInstituteStatus();
                                                crit = new Criteria();
                                                /**
                                                *  Check for institute_status to addning institute admin.
                                                */
                                                if(inststat == 1)
                                                {
                                                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteid);
                                                        crit.add(InstituteAdminUserPeer.ADMIN_DESIGNATION,admindesig);
                                                        crit.add(InstituteAdminUserPeer.ADMIN_UNAME,adminemail);
                                                        crit.add(InstituteAdminUserPeer.ADMIN_PERMISSION_STATUS,0);
                                                        InstituteAdminUserPeer.doInsert(crit);
							  String serverName=data.getServerName();
                                                        int srvrPort=data.getServerPort();
                                                        String serverPort=Integer.toString(srvrPort);
                                                        /**
                                                        *   Create User Profile to call UserManagement util Method         
                                                        *   CreateUserProfile. 
                                                        */
                                                        UserManagement usermanagement = new UserManagement();
                                                        usermgmt = usermanagement.CreateUserProfile(adminusername,password,adminfname,adminlname,instName,adminemail,"institute_admin","institute_admin",serverName,serverPort,LangFile,"","","act");// Last parameter added by Priyanka
                                                        data.setMessage(usermgmt +" "+ mail_msg);
							// maintain log
							log.info("Secondary Admin Added with mailid "+adminemail+" By "+data.getUser().getName()+" | IP Address : "+data.getRemoteAddr());
                                                }//if
                                        }//charif
                                        else{
                                                data.setMessage(mu.ConvertedString("brih_specialSymbol&char", LangFile)+" "+mu.ConvertedString("Notallow", LangFile)+" "+(mu.ConvertedString("brih_exceptAtTheRate&Dot",LangFile) +"!!"));
                                                //special character are not allowed in email except @ and .
                                        }
                                }////if userexists
                                else
                                {
                                        data.setMessage(mu.ConvertedString("brih_user", LangFile) +" "+mu.ConvertedString("Wikiaction6", LangFile) +" "+mu.ConvertedString("brih_asAn", LangFile)+" "+mu.ConvertedString("brih_institute", LangFile)+" "+mu.ConvertedString("brih_admin", LangFile));
                                        //"User is already exist as an Institute Admin ");
                                }
                                //context.put("mode","viewadmin");
                        }
                        catch(Exception ex){}
        }


		
		 /**
         	  * Method for Update Permission of an Institute Admin.
                  * @param data RunData instance
                  * @param context Context instance
                  */
		
		public void doPermissionUser(RunData data, Context context)	{
			
			User user=data.getUser();
			ParameterParser pp=data.getParameters();
			String LangFile = data.getUser().getTemp("LangFile").toString();
	                String instadminlist=data.getParameters().getString("instadminlist","");
	                String mode=data.getParameters().getString("mode","");
	                String inst_id=(data.getUser().getTemp("Institute_id")).toString();
	                context.put("mode",mode);
	                String uname=pp.getString("username");
	                int permission=Integer.parseInt(pp.getString("permission"));
	
        	        List admindetail=null;
	                if(mode.equals("instadminlist"))
	                {
	                        context.put("instadminlist",instadminlist);
	                        try{
	  				String information="UPDATE INSTITUTE_ADMIN_USER SET ADMIN_PERMISSION_STATUS='"+permission+"' WHERE ADMIN_UNAME ='"+uname+"' and INSTITUTE_ID ='"+inst_id+"'";
	
	                                InstituteAdminUserPeer.executeStatement(information);
                	                data.addMessage(" "+MultilingualUtil.ConvertedString("update_msgadm",LangFile));


                        	} catch(Exception ex){data.setMessage("Permission updataion failed! Error occured "+ex);}
        		}	
		}	
		
	
		/**
                  * Method for Removing Admin from an Institute.
                  * @param data RunData instance
                  * @param context Context instance
                  */
	
        	public void doRemove(RunData data, Context context)  {
			
			ParameterParser pp=data.getParameters();
	                String LangFile = data.getUser().getTemp("LangFile").toString();
        	        String instadminlist=data.getParameters().getString("instadminlist","");
	                String mode=data.getParameters().getString("mode","");
	                context.put("mode",mode);
	                String uname=pp.getString("username");
	                String Institute_Id=pp.getString("Institute_Id");

	                if(mode.equals("instadminlist")) {
		
                        	context.put("instadminlist",instadminlist);

                		try{
					
				String file=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
        		        /** Delete InstituteAdmin from the selected institute
		                *By calling method DeleteInstAdmin
		                *@see DeleteInstituteUtil in Utils
		                */
                		String DelInstAdmin=DeleteInstituteUtil.DeleteInstAdmin(uname,Institute_Id,LangFile,file);
		                data.setMessage(DelInstAdmin);
				log.info("Secondary Admin deleted with username "+uname+" By "+data.getUser().getName()+" | IP Address : "+data.getRemoteAddr());
					
		                }catch (Exception ex){ data.setMessage("Error in Removing Secondary Admin !!  " +ex); }

        		}
		}


		public void doPerform(RunData data, Context context) throws Exception    {
			
		        String action = data.getParameters().getString("actionName","");
			if(action.equals("eventSubmit_AddAdmin"))
                        	   AddAdmin(data,context);
	                else if(action.equals("eventSubmit_doPermissionUser"))
        	          	   doPermissionUser(data,context);
	                else if(action.equals("eventSubmit_doRemove"))
	                        doRemove(data,context);
        	        else
	                        data.setMessage("Action not found");

        }
}
