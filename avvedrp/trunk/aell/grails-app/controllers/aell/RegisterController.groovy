
package aell

class RegisterController {
	def RegisterService
	def MymailService
	
    def index = {
		def hostname=request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()
		def roleList=RegisterService.getRoleList()
		def universityList=RegisterService.getUniversityList()
		render( view:"index" , model: [hostname:hostname,roleList:roleList,universityList:universityList])
		 }
	def registerUser={
		try{
		RegisterService.registerNewUser(params)
		flash.message="New User Created"
		if(grailsApplication.config.mail_send_flag != 0)
		{
		//println "here2"
		String host = grailsApplication.config.mail_host;
		String username = grailsApplication.config.mail_authusername // your authsmtp username
		String password = grailsApplication.config.mail_authpassword // your authsmtp password
		String from = grailsApplication.config.mail_from;
		String port = grailsApplication.config.mail_port
		def mail_tosend = grailsApplication.config.mail_tosend
		def mail_subject = grailsApplication.config.mail_subject
		def mail_content = grailsApplication.config.mail_content
		def mail_userSubject = grailsApplication.config.mail_userSubject
		def mail_userContent = grailsApplication.config.mail_userContent
		MymailService.sendMessage(mail_tosend,mail_subject,mail_content,host,username,password,from,port,mail_userSubject,mail_userContent,params)
		}
		redirect(action:index)

		}catch(AvlServiceException ase){
			flash.error = ase.message
			redirect(action:index)
		}
		
	}
}
