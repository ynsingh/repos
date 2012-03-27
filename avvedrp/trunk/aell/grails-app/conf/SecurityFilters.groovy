
class SecurityFilters {
   def filters = {
	   
		   loginCheck(controller:'*', action:'*')
	       {
	    	try{
    	       before  = {
				   def ell_integrate_flag = grailsApplication.config.ell_integrate_flag
		           def referal_url = grailsApplication.config.referal_url;
				   if(!session.user)
			     {
				 if(ell_integrate_flag==0){
					 if(controllerName=="home"||controllerName=="logout"||controllerName=="contentHome"||controllerName=="topic"||controllerName=="experiment"||controllerName=="editExperiment"||controllerName=="editExperiment"||controllerName=="users"||controllerName=="role"){
					if(actionName=="cropImage"||actionName=="editUserProfile"){
					  flash.error= "Your session is out, Please log in again."
					  render(view:"/error")
					}
 				 else
					redirect uri:'/login'
        			    return false;
				 }
			  }
				 else if(ell_integrate_flag==1){
					 if(controllerName=="logout"||controllerName=="contentHome"||controllerName=="topic"||controllerName=="experiment"||controllerName=="editExperiment"||controllerName=="editExperiment"||controllerName=="users"||controllerName=="role")
					 {
						 if(actionName=="cropImage"||actionName=="editUserProfile"){
							 flash.error= "Your session is out, Please log in again."
							 render(view:"/error")
						   }
						 else
					         redirect url: referal_url
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