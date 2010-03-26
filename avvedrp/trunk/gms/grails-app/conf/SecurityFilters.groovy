class SecurityFilters {
   def filters = {
       loginCheck(controller:'*', action:'*')
       {
    	 try{
    	   before  = {
    			   println "party"+session.Party
    			   println "actionname"+actionName
    			   
        	  if(!session.Party && !actionName.equals('auth')&&!actionName.equals('mainDash')&&(actionName!=null))
        	  {
        		  println " herecf"
        	
        		 if(!params.toString().contains("login_error"))
        		 {
        		  
                	redirect(controller:'login',action:'auth')
                    return false
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