class SecurityFilters
{
 def filters = {  
      /*
               loginCheck1(controller:'dashboard', action:'*'){
                       before = {
                                  if(session.env==null) {
                                  redirect(action:'auth',controller:'login')
                                  return false
                                   }
                                }
               }               
               loginCheck2(controller:'courseActivty', action:'*'){
                       before = {
                                  if(session.env==null) {
                                  redirect(action:'auth',controller:'login')
                                  return false
                                   }
                                }
               }
               loginCheck3(controller:'authority', action:'*'){
                       before = {
                                  if(session.env==null) {
                                  redirect(action:'auth',controller:'login')
                                  return false
                                   }
                                }
               }
               loginCheck4(controller:'person', action:'*'){
                       before = {
                                  if(session.env==null) {
                                  redirect(action:'auth',controller:'login')
                                  return false
                                   }
                                }
               }
                loginCheck5(controller:'siteHelp', action:'*'){
                       before = {
                                  if(session.env==null) {
                                  redirect(action:'auth',controller:'login')
                                  return false
                                   }
                                }
							
               }     
			   */   
   }//end of filter
}//end of class
