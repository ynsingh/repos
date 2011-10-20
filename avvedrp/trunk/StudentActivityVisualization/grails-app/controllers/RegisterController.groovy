import org.springframework.security.providers.UsernamePasswordAuthenticationToken as AuthToken
import org.springframework.security.context.SecurityContextHolder as SCH
import groovy.sql.Sql;


/**
 * Registration controller.
 */
class RegisterController {


	def authenticateService
	def daoAuthenticationProvider
    def emailerService
    def dataSource
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
                                println(instituteInfo.email)
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
                                    A member from your institute has been registered in our site:-
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



/*---------------------------------------  UNIVERSITY REGISTRATION STARTS HERE -------------------------------------*/

    def registerUniversity = {
    		
	
		 
		// skip if already logged in
		if (authenticateService.isLoggedIn()) {
			redirect action: index
			return
		}

		def person = new Person()
		person.properties = params
        def university = new University()
		university.properties = params
                
        String pswd=params.univ_paswd
		def config = authenticateService.securityConfig
		def defaultRole = config.security.defaultRole

		def role = Authority.findByAuthority(defaultRole)

		if (!role) {
			person.passwd = ''
			flash.message = "Default Role '$defaultRole' not found."
			render view: 'index', model: [person: person]
			return
		}


		if (params.univ_captcha.toUpperCase() != session.captcha) {
			person.passwd = ''
			flash.message = '<font color=red><strong>Captcha code did not match.</strong></font>'
			render view: 'index', model: [person: person]
			return
		}


		if (params.univ_paswd != params.univ_conpaswd) {
			person.passwd = ''
			flash.message = '<font color=red><strong>The passwords you entered do not match.</strong></font>'
			render view: 'index', model: [person: person]
			return
		}

       	def pass = authenticateService.encodePassword(params.univ_paswd)
		def real_name=[]
		real_name=params.univ_username.split('@')
		bindData(person, [
				username: params.univ_username,
                userRealName : real_name[0],
				passwd: pass,
                usercode: 'nil',
				enabled: 'on',
				emailShow: true,
				description: '' ])
                 bindData(university, [univ_name: params.univ_name,
                                       univ_address: params.univ_address,
				       univ_email: params.univ_username])



         def personService =new PersonService()
         def chkDuplicateUser = personService.checkDuplicateUser(params.univ_username)
	 if(chkDuplicateUser)
	       {
                     flash.message ="<font color=red><strong>Username Already Exists.</strong></font>"
                    //redirect(action: "index", id: salaryComponentInstance.id)
                    render view: 'index', model: [person: person]
	       }
           else
             {
              	   if ( person.save() && university.save())
                          {
                                       Authority.findByAuthority('ROLE_UNIVERSITY').addToPeople(person)								   
								
	//Sending Mail starts here
	if(params.univ_username!='') {
	def sql=new Sql(dataSource);
	def host = ""; def port = ""; def username = ""; def password = "";				
				sql.eachRow("SELECT smtp_host as HOST,smtp_port as PORT,smtp_username as USERNAME,smtp_password as PASSWORD  FROM email_settings")
				{ 
				    row ->				
	    		                host = row.HOST
	    		                port = row.PORT
	    		                username = row.USERNAME
								password = row.PASSWORD
				}
					
	String emailMessage = """
	Hi,
	Thanks for Registering in our site:-
	${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}
	
	Your Account Details:
	---------------------
	Username: ${params.univ_username}
	Password : ${params.univ_paswd}
	"""
	
    def mailService =new MailService()
	def status=mailService.sendMessage(host,port,username,password,params.univ_username,emailMessage)
						   
						}
						//Sending Mail ends here

										
									   person.save(flush: true)
                                       flash.message ="<font color='#399C0B'><strong>Thanks For Registering.An email has been send to $params.univ_username</strong></font>"
                                       render view: 'index'
                            }
                            else
                            {
                                    person.passwd = ''
                                    render view: 'index', model: [person: person]
                            }
                        }
	}

/*---------------------------------------  UNIVERSITY REGISTRATION ENDS HERE -------------------------------------*/



/*---------------------------------------  INSTITUTE REGISTRATION STARTS HERE -------------------------------------*/
 def registerInstitute = {
 
         

	   //Setting the LMS Name Array for insertion
        def sql=new Sql(dataSource);
		def lms_array=[];
		def index=0;
		if (params.lmsname.class.isArray())
		{		  
		   for ( lmsname in params.lmsname ) {
				  lms_array[index]=lmsname;
				  index++;
				 }
		}
		else {			
		    lms_array[index]=params.lmsname;
		}
       

 

        // skip if already logged in
		if (authenticateService.isLoggedIn()) {
			redirect action: show
			return
		}

		def person = new Person()
		person.properties = params
        def institute = new Institute()
		institute.properties = params
		
        String pswd=params.inst_paswd
		def config = authenticateService.securityConfig
		def defaultRole = config.security.defaultRole

		def role = Authority.findByAuthority(defaultRole)

		if (!role) {
			person.passwd = ''
			flash.message = "Default Role '$defaultRole' not found."
			render view: 'index', model: [person: person]
			return
		}


		if (params.inst_captcha.toUpperCase() != session.captcha) {
			person.passwd = ''
			flash.message = '<font color=red><strong>Captcha code did not match.</strong></font>'
			render view: 'index', model: [person: person]
			return
		}


		if (params.inst_paswd != params.inst_conpaswd) {
			person.passwd = ''
			flash.message = '<font color=red><strong>The passwords you entered do not match.</strong></font>'
			render view: 'index', model: [person: person]
			return
		}

        	def pass = authenticateService.encodePassword(params.inst_paswd)
			def real_name=[]
            real_name=params.inst_username.split('@')
                
		bindData(person, [
				username: params.inst_username,
                userRealName : real_name[0],
				passwd: pass,
                usercode: 'nil',
				enabled: false,
				emailShow: true,
				description: '' ])
             



         def personService =new PersonService()
         def chkDuplicateUser = personService.checkDuplicateUser(params.inst_username)
	 if(chkDuplicateUser)
	       {
                     flash.message ="<font color=red><strong>Username Already Exists.</strong></font>"
                    //redirect(action: "index", id: salaryComponentInstance.id)
                    render view: 'index', model: [person: person]
	       }
           else
             {
              	   if (person.save())
                          {								
								bindData(institute, [
                                univ_id: params.inst_univ_id,
								user_id: person.id,
				                inst_name: params.inst_name,
                                inst_address: params.inst_address,
                                inst_email: params.inst_username])
								
								if(institute.save()) {
								for (lmsval in lms_array){                                  
								sql.execute("insert into lms (inst_id, lms_name) values (${institute.id},'"+lmsval+"')")                                   }
								}
                                Authority.findByAuthority('ROLE_USER').addToPeople(person)
									   
 //Sending Mail starts here
    def univ = sql.firstRow("select univ_email as email,univ_name as university from university where id='"+params.inst_univ_id+"'")
    if(univ.email!='') {

				def host = ""; def port = ""; def username = ""; def password = "";				
				sql.eachRow("SELECT smtp_host as HOST,smtp_port as PORT,smtp_username as USERNAME,smtp_password as PASSWORD  FROM email_settings")
				{ 
				    row ->				
	    		                host = row.HOST
	    		                port = row.PORT
	    		                username = row.USERNAME
								password = row.PASSWORD
				}
	
	
	
    String emailMessage = """
    Hi,
    An Institute under your university has been registered in our site:-
    ${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}

    Here are the Account details of the New Institute Member:
    --------------------------------------------------------

    University Name: ${univ.university}
    Institute Name: ${params.inst_name}
    Email-ID : ${params.inst_username}
    Address : ${params.inst_address}
    """   
 
    def mailService =new MailService()
	def status=mailService.sendMessage(host,port,username,password,univ.email,emailMessage)

    }
//Sending Mail ends here		
								 
								 
				       person.save(flush: true)
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
/*---------------------------------------  INSTITUTE REGISTRATION ENDS HERE -------------------------------------*/


/*---------------------------------------  AJAX FUNCTIONS START HERE -------------------------------------*/
def ajaxGetInstitutes={
     def sel_id=params.id;  
     def response="<select style='width:223px;' id='usr_inst_id' name='usr_inst_id' onchange='getLms(this.value)'><option value=''>-------------- select -------------</option>"
     def sql=new Sql(dataSource);
     def list1=sql.rows("select id,inst_name from institute where univ_id='"+sel_id+"'");
     for(a in list1)
        {
            response+="<option value="+a.id+">"+a.inst_name+"</option>";
        }
      response+="</select>";
      render response;
}

  def ajaxGetLms={
     def sel_id=params.id;
     def response="<select style='width:223px;' id='lmsname' name='lmsname' multiple size=4 onchange='call()'>"
     def sql=new Sql(dataSource);
     def lmslist=sql.rows("select id,lms_name from lms where inst_id='"+sel_id+"'");
     for(lmsval in lmslist)
        {
            response+="<option value="+lmsval.id+">"+lmsval.lms_name+"</option>";
        }
      response+="</select>";
      render response;
}
/*---------------------------------------  AJAX FUNCTIONS ENDS HERE -------------------------------------*/


/*---------------------------------------  STAFF/STUDENT REGISTRATION STARTS HERE -------------------------------------*/
    def registerUser={
	
	   

        //Getting the LMS Username's   
        def split_usrdet=params.usernames.split(",");
        def array_len=(split_usrdet.length-1);
   

         def sql=new Sql(dataSource);
        // skip if already logged in
		if (authenticateService.isLoggedIn()) {
			redirect action: show
			return
		}

		def person = new Person()
		person.properties = params


        String pswd=params.usr_paswd
		def config = authenticateService.securityConfig
		def defaultRole = config.security.defaultRole

		def role = Authority.findByAuthority(defaultRole)

		if (!role) {
			person.passwd = ''
			flash.message = "Default Role '$defaultRole' not found."
			render view: 'index', model: [person: person]
			return
		}


		if (params.usr_captcha.toUpperCase() != session.captcha) {
			person.passwd = ''
			flash.message = '<font color=red><strong>Captcha code did not match.</strong></font>'
			render view: 'index', model: [person: person]
			return
		}


		if (params.usr_paswd != params.usr_conpaswd) {
			person.passwd = ''
			flash.message = '<font color=red><strong>The passwords you entered do not match.</strong></font>'
			render view: 'index', model: [person: person]
			return
		}

        	def pass = authenticateService.encodePassword(params.usr_paswd)
            def real_name=[]
            real_name=params.usr_username.split('@')
			
		bindData(person, [
				username: params.usr_username,
                userRealName : real_name[0],
				passwd: pass,
                usercode: 'nil',
				enabled: false,
				emailShow: true,
				description: '' ])



         def personService =new PersonService()
         def chkDuplicateUser = personService.checkDuplicateUser(params.usr_username)
	 if(chkDuplicateUser)
	       {
                     flash.message ="<font color=red><strong>Username Already Exists.</strong></font>"
                    //redirect(action: "index", id: salaryComponentInstance.id)
                    render view: 'index', model: [person: person]
	       }
           else
             {
              	   if ( person.save())
                          {
                                    //Inserting the usernames for LMS
                                    
                                        for ( i in 0..array_len ) {
                                                def split_val=split_usrdet[i].split("@");
                                                 sql.execute("insert into lms_mapping (lms_id,user_id,lms_username) values ("+split_val[0]+",${person.id},'"+split_val[1]+"')")
                                        
                                     }
                                     //Inserting the usernames for LMS

                                       Authority.findByAuthority('ROLE_USER').addToPeople(person)
									   
 //Sending Mail starts here
    def univ = sql.firstRow("select univ_name,univ_email from university where id='"+params.usr_univ_id+"'")
    def inst = sql.firstRow("select inst_name,inst_email from institute where id='"+params.usr_inst_id+"'")
    if(inst.inst_email!='') {
	def host = ""; def port = ""; def username = ""; def password = "";				
				sql.eachRow("SELECT smtp_host as HOST,smtp_port as PORT,smtp_username as USERNAME,smtp_password as PASSWORD  FROM email_settings")
				{ 
				    row ->				
	    		                host = row.HOST
	    		                port = row.PORT
	    		                username = row.USERNAME
					password = row.PASSWORD
				}
	
  
    String emailMessage = """
    Hi,
    A member of your institute has registered in our site:-
    ${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}

    Here are the Account details of the New  Member:
    --------------------------------------------------------
    Username :${params.usr_username}
    University : ${univ.univ_name}
    Institute : ${inst.inst_name}
    """
	
	def mailService =new MailService()
	def status=mailService.sendMessage(host,port,username,password,inst.inst_email,emailMessage)

    }
//Sending Mail ends here
								 
								 
									   person.save(flush: true)
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
/*---------------------------------------  STAFF/STUDENT REGISTRATION ENDS HERE -------------------------------------*/
    

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
	  
	  def gmsFrame = {}
} // End of Class
