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
//import babylon.babylonUserTool;

	
/**
 * This class is responsible for Removing Secondary Admin and Changing their permission.
 * @author <a href="mailto:mail2sunil00@gmail.com">Sunil Yadav</a>
 */
	
	
	public class AdminList_Action extends VelocitySecureAction   {
        		
		private String LangFile=new String();
		protected boolean isAuthorized(RunData data) throws Exception
        	     {
			return true;
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
	                //String instadname=pp.getString("iadname");
	                int uid=UserUtil.getUID(instadname);
	
        	        List admindetail=null;
	                if(mode.equals("instadminlist"))
	                {
	                        context.put("instadminlist",instadminlist);
	                        try{
	  				String information="UPDATE INSTITUTE_ADMIN_USER SET ADMIN_PERMISSION_STATUS='"+permission+"' WHERE ADMIN_UNAME ='"+uname+"' and INSTITUTE_ID ='"+inst_id+"'";
	
	                                InstituteAdminUserPeer.executeStatement(information);
	                                if(permission == 2){
	                                data.setMessage("Primary Admin");

	                                }else{
	                                data.setMessage("Secondary Admin");
        	                        }
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
			String action = data.getParameters().getString("actionName","");
	                String mode=data.getParameters().getString("mode","");
	                context.put("mode",mode);
	                String uname=pp.getString("username");
	                String Institute_Idd=pp.getString("Institute_Id");
	                //int inst_id11=Integer.parseInt(Institute_Idd);

	                if(mode.equals("instadminlist")) {
		
                        	context.put("instadminlist",instadminlist);

                		try{
					 
				Criteria crit = new Criteria();
				crit.add(InstituteAdminUserPeer.ADMIN_UNAME,uname);
				crit.add(InstituteAdminUserPeer.INSTITUTE_ID,Institute_Idd);
				InstituteAdminUserPeer.doDelete(crit);
				data.addMessage(" "+MultilingualUtil.ConvertedString("delete_msgadm",LangFile));

	
		                }catch (Exception ex){ data.setMessage("Error in Removing Secondary Admin !!  " +ex); }

        		}
		}


		public void doPerform(RunData data, Context context) throws Exception    {
			
		        String action = data.getParameters().getString("actionName","");
	                if(action.equals("eventSubmit_doPermissionUser"))
        	          	   doPermissionUser(data,context);
	                else if(action.equals("eventSubmit_doRemove"))
	                        doRemove(data,context);
        	        else
	                        data.setMessage("Action not found");

        }
}
