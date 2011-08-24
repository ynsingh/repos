import org.springframework.security.context.SecurityContextHolder as SCH
import java.util.*;

class ForgotPasswordController {
   
    def emailerService
    def authenticateService	
    def charset = "!0123456789abcdefghijklmnopqrstuvwxyz";
    def index = { }
	
	def sendNewPassword ={ 
	
	    def personService = new PersonService()
		def newPassword=""
		if(params.email)
			 {
			        def userId = personService.getUserByUserName(params.email)			
					if(userId)
					{
					  def userInstance = personService.getUserById(userId)
					  newPassword=getRandomString(10);	
					  def encodedPassword=authenticateService.encodePassword(newPassword)
					  userInstance.passwd=encodedPassword					
					  userInstance.enabled=true
					  userInstance.save()
					  render encodedPassword;			
					  

						String emailMessage = """
						Hi,
						Below is your new account details:-
						--------------------------------------------------------
						Username :${params.email}
						Password :${newPassword}
						"""
						def email = [
										to: [params.email],	
										subject: "New Password for DIVE",
										text: emailMessage // 'text' is the email body
									]
							emailerService.sendEmails([email])
					  flash.message ="<font color='#54692E'><strong>New Password send to your emailID</strong></font>'"
					  redirect uri: '/forgotPassword/index'
					//Sending Mail ends here

			        render "success"
				    }
					else
					{
					  flash.message ="<font color='#F70305'><strong>Username does not exist!</strong></font>"
					  redirect uri: '/forgotPassword/index'
					}
			 }	
	  }//end of function
	  
	  
	   public String getRandomString(int length) {
	        Random rand = new Random(System.currentTimeMillis());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < length; i++) {
	            int pos = rand.nextInt(charset.length());
	            sb.append(charset.charAt(pos));
	        }
	        return sb.toString();
	    }
}
