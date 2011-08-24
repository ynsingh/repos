class SecurityFilters 
{
   def filters = 
   {
      
		   
     loginCheck(controller:'*', action:'*')
       {
    	   try
    	   {
    		   before  = 
    		   {
    				   println "party"+session.Party
    				   println "actionname"+actionName
    				   println "Role"+session.Role
    				   println "party"+session.Party
    				   println "controllerName"+controllerName
    			      
    				   if(!session.Party)
    				   {
    					   if(!"login".equals(controllerName)&& !"newUserCreate".equals(actionName)&& !"user".equals(controllerName)&& !"saveNewUser".equals(actionName)&& !"proposal".equals(controllerName)&& !"proposalApplication".equals(controllerName)&& !"attachments".equals(controllerName)&& !"notificationsAttachments".equals(controllerName)&& !"download".equals(actionName))
    					   {
    						   redirect uri:'/login'
    						   return false;
    					   }
	        		  
    				   }
    				   else if("logout".equals(controllerName))
    				   {
    				   }
    				   else
    				   {    
    					    def userService = new UserService()
     					    def userRoleList = userService.getUserRoleByUserId(session.UserId)
							def roleLst= new ArrayList()
							for(int i=0;i<userRoleList.size();i++)
									roleLst << userRoleList[i]
							
    					    String roleIds="(";
							for(int i=0;i<roleLst.size();i++)
								roleIds=roleIds+roleLst[i].id+","
								
								roleIds=roleIds.substring(0,roleIds.length()-1)+")"
    					   if(userRoleList[0].authority != 'ROLE_SITEADMIN' && userRoleList[0].authority != 'ROLE_SUPERADMIN' )
    					   {
    						   
    						   if( !"gmsFrame".equals(actionName) && !"menu".equals(actionName)&& !"top".equals(actionName))
    						   {
     							   def rolePrivilegesInstance = RolePrivileges.findAll("from RolePrivileges RP where RP.role.id in "+roleIds+" and RP.party.id="+session.Party+" and RP.controllerName='"+controllerName+"' and RP.actionName ='"+actionName+"'")  
    							   println "rolePrivilegesInstance"+rolePrivilegesInstance
    							   if(!rolePrivilegesInstance)
    							   {
    								   println "++++++++++invalid access page++++++++tytfgdsf****"
    								   redirect uri:'/invalidAccess.gsp'
    								   return false;
    							   }
						
		    						   }
    						  
    					   }
    				   }
	    	   	}
    	   } 
    	   catch(Exception e)
    	   {}
       }
   }
}