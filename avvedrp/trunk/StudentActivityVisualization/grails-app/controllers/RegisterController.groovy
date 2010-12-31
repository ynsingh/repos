


import org.springframework.security.providers.UsernamePasswordAuthenticationToken as AuthToken
import org.springframework.security.context.SecurityContextHolder as SCH

/**
 * Registration controller.
 */
class RegisterController {

	def authenticateService
	def daoAuthenticationProvider
	def emailerService
       // def personService

	static Map allowedMethods = [save: 'POST', update: 'POST']

	/**
	 * User Registration Top page.
	 */
	def index = {
           
             def instituteInstance=Institute.findAll("from Institute")             
		[instituteInstance:instituteInstance]
		// skip if already logged in
		if (authenticateService.isLoggedIn()) {
			redirect action: show
			return
		}
                else{
                   
                 return   ['instituteInstance':instituteInstance]}

		if (session.id) {
			def person = new Person()
			person.properties = params
			return [person: person]
		}

               
        redirect uri: '/'
	}

	/**
	 * User Information page for current user.
	 */
	def show = {

		// get user id from session's domain class.
		def user = authenticateService.userDomain()
		if (user) {
			render view: 'show', model: [person: Person.get(user.id)]
		}
		else {
			redirect action: index
		}
	}

	/**
	 * Edit page for current user.
	 */
	def edit = {

		def person
		def user = authenticateService.userDomain()
		if (user) {
			person = Person.get(user.id)
		}

		if (!person) {
			flash.message = "[Illegal Access] User not found with id ${params.id}"
			redirect action: index
			return
		}

		[person: person]
	}

	/**
	 * update action for current user's edit page
	 */
	def update = {

		def person
		def user = authenticateService.userDomain()
		if (user) {
			person = Person.get(user.id)
		}
		else {
			redirect action: index
			return
		}

		if (!person) {
			flash.message = "[Illegal Access] User not found with id ${params.id}"
			redirect action: index, id: params.id
			return
		}

		// if user want to change password. leave passwd field blank, passwd will not change.
		if (params.passwd && params.passwd.length() > 0
				&& params.repasswd && params.repasswd.length() > 0) {
			if (params.passwd == params.repasswd) {
				person.passwd = authenticateService.encodePassword(params.passwd)
			}
			else {
				person.passwd = ''
				flash.message = 'The passwords you entered do not match.'
				render view: 'edit', model: [person: person]
				return
			}
		}

		person.userRealName = params.userRealName
		person.email = params.email
		if (params.emailShow) {
			person.emailShow = true
		}
		else {
			person.emailShow = false
		}

		if (person.save()) {

                       
			redirect action: show, id: person.id
		}
		else {
			render view: 'edit', model: [person: person]
		}
	 }

		/**
	 * Person save action.
	 */
	def save = {

		// skip if already logged in
		if (authenticateService.isLoggedIn()) {
			redirect action: show
			return
		}

		def person = new Person()
		person.properties = params
                String pswd=params.passwd
		def config = authenticateService.securityConfig
		def defaultRole = config.security.defaultRole

		def role = Authority.findByAuthority(defaultRole)

		if (!role) {
			person.passwd = ''
			flash.message = "Default Role '$defaultRole' not found."
			render view: 'index', model: [person: person]
			return
		}


		if (params.captcha.toUpperCase() != session.captcha) {
			person.passwd = ''
			flash.message = 'Captcha code did not match.'
			render view: 'index', model: [person: person]
			return
		}


		if (params.passwd != params.repasswd) {
			person.passwd = ''
			flash.message = 'The passwords you entered do not match.'
			render view: 'index', model: [person: person]
			return
		}




        	def pass = authenticateService.encodePassword(params.passwd)
		bindData(person, [
				username: params.username,
                                userRealName : '',
				passwd: pass,
                                usercode: params.ucode,
				enabled: false,
				emailShow: true,
				description: '' ])



         def personService =new PersonService()
         def chkDuplicateUser = personService.checkDuplicateUser(params.username)
         def instituteInfo = personService.getInstituteInfo(params.institute)

	 if(chkDuplicateUser)
	       {
                     flash.message ="${message(code: 'UserName Already Exists')}"
                    //redirect(action: "index", id: salaryComponentInstance.id)
                    render view: 'index', model: [person: person]
	       }
           else
             {
              	   if (person.save())
                          {
                                     //role.addToPeople(person)
                                     //Setting Default Roles
                                     //if(chkValidUser.userType=='staff')
                                     //Authority.findByAuthority('ROLE_STAFF').addToPeople(person)
                                    // else if(chkValidUser.userType=='student')
                                    // Authority.findByAuthority('ROLE_STUDENT').addToPeople(person)
                                     //Setting Default Roles
                                     Authority.findByAuthority('ROLE_USER').addToPeople(person)

                                     def mailService =new MailService()
                                     String emailMessage = """
                                    Hi,
                                    A member from your institute has been registered with our site:-
                                    ${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}

                                    Here are the Account details of the New Member:
                                    -----------------------------------------------

                                    Institute Name: ${instituteInfo.name}
                                    Email-ID : ${person.username}
                                    Member Code: ${person.usercode}

                                    If the Member belongs to your institute,you can activate the member's account by clicking on the following link :-
                                     ${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}/register/userActivation/${person.id}
                                    """
	    	                    def mailstatus = mailService.sendMessage(instituteInfo.email,emailMessage)

                                            person.save(flush: true)

                                           /*
                                            def auth = new AuthToken(person.username, params.passwd)
                                            def authtoken = daoAuthenticationProvider.authenticate(auth)
                                            SCH.context.authentication = authtoken
                                            redirect uri: '/'
                                            */
                                             flash.message ="<font color='#399C0B'><strong>${message(code: 'Thanks For Registering.Waiting for Approval... </strong></font>')}"
                                             render view: 'index'
                            }
                            else
                            {
                                    person.passwd = ''
                                    render view: 'index', model: [person: person]
                            }
                        }

	}

    

    //New User Activation
        def userActivation = {
			
                        def personService =new PersonService()
                        def userInstance = personService.getUserById(Integer.parseInt(params.id))
                        if(userInstance.enabled==false)
			{
				userInstance.enabled=true
				userInstance.save()
				redirect uri: '/register/confirmActivation'
			}
			else
			{
				redirect(controller:'login',action:'auth')
			}
		}

      def confirmActivation = {}

}
