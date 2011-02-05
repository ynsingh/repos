class SecurityFilters {
   def filters = {
       loginCheck(controller:'*', action:'*')
       {
    	 try{
    	   before  = {
    			   println "party"+session.Party
    			   println "actionname"+actionName
    			   println "Role"+session.Role
    			   println "party"+session.Party
    			   println "controllerName"+controllerName
    			   
        	  if(!session.Party)
        	  {
        		  
        		  if(!"login".equals(controllerName)&& !"newUserCreate".equals(actionName)&& !"user".equals(controllerName)&& !"saveNewUser".equals(actionName)&& !"proposal".equals(controllerName)&& !"proposalApplication".equals(controllerName)&& !"attachments".equals(controllerName))
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
        		  
        		  
             if(session.Role != 'ROLE_SITEADMIN' )
            {
    		def rolePrivilegesInstance = RolePrivileges.findAll("from RolePrivileges RP where RP.role.authority='"+session.Role+"' and RP.party.id="+session.Party+" and RP.controllerName='"+controllerName+"' and RP.actionName ='"+actionName+"'")  
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
       
       catch(Exception e)
       {
    	   
       }
}
}
}