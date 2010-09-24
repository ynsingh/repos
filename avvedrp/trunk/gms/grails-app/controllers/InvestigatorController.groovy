import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import org.apache.commons.validator.EmailValidator
class InvestigatorController {
	def authenticateService
	def notificationsEmailsService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
    
        def investigatorInstanceList
        def investigatorService=new InvestigatorService()
        println"@@@@@params@@@@@@"+params
        String subQuery ="";
       GrailsHttpSession gh=getSession()
        if(params.sort != null && !params.sort.equals(""))
        	subQuery=" order by I."+params.sort
        if(params.order != null && !params.order.equals(""))
        	subQuery =subQuery+" "+params.order
      
       if(gh.getValue("Role")=="ROLE_ADMIN")
       {
        	investigatorInstanceList = investigatorService.getAllInvestigators(subQuery)
        }
        else
        {
        	
        	investigatorInstanceList =investigatorService.getInvestigatorsWithParty(gh.getValue("PartyID"),subQuery)
        }
        
        [ investigatorInstanceList: investigatorInstanceList ]
    }

    def show = {
        def investigatorInstance = Investigator.get( params.id )

        if(!investigatorInstance) {
            flash.message = "Investigator not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ investigatorInstance : investigatorInstance ] }
    }

    def delete = {
        def investigatorInstance = Investigator.get( params.id )
        if(investigatorInstance) {
        	def chkPrjctInstance=Projects.findAll("from Projects P where P.principalInvestigatorName.id="+investigatorInstance.id)
        	if(chkPrjctInstance[0]==null)
        	{
            investigatorInstance.delete()
            flash.message = "Investigator ${investigatorInstance.name} deleted"
            redirect(action:list)
        	}
        	else
        	{
        		flash.message = "Investigator investigated a project.So could not be deleted"
        			redirect(action:list)
        	}
        }
        else {
            flash.message = "Investigator not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
       	
    	println "------------------params------"+ params
    	println "------------------params------"+params
    	def investigatorInstance = Investigator.get( params.id )

        if(!investigatorInstance) {
            flash.message = "Investigator not found with id ${params.id}"
            redirect(action:create)
        }
        else {
            return [ investigatorInstance : investigatorInstance ]
        }
    }

    def update = {
    		println "iiiiiiiiiiiiiiiiiiiiitest......................."
        def investigatorInstance = Investigator.get( params.id )
        if(investigatorInstance) {
            investigatorInstance.properties = params
            if(!investigatorInstance.hasErrors() && investigatorInstance.save()) {
                flash.message = "Investigator ${investigatorInstance.name} updated"
                redirect(action:create,id:investigatorInstance.id)
            }
            else {
                render(view:'edit',model:[investigatorInstance:investigatorInstance])
            }
        }
        else {
            flash.message = "Investigator not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
    		
        def investigatorInstance = new Investigator()
        GrailsHttpSession gh=getSession()
        //Puting help page into session
    	gh.putValue("Help","Investigator.htm")
        def partyinstance=Party.get(gh.getValue("Party"))
       
        def department = PartyDepartment.find("from PartyDepartment P where P.party.id="+partyinstance.id)
        
       
        investigatorInstance.properties = params
        //investigatorInstance.department=department
       
        def investigatorInstanceList
        def investigatorService=new InvestigatorService()
        println"@@@@@params@@@@@@"+params
        String subQuery ="";
      
        if(params.sort != null && !params.sort.equals(""))
        	subQuery=" order by I."+params.sort
        if(params.order != null && !params.order.equals(""))
        	subQuery =subQuery+" "+params.order
      
        if(gh.getValue("Role")=="ROLE_ADMIN")
        {
        	investigatorInstanceList = investigatorService.getAllInvestigators(subQuery)
        }
        else
        {
        	
        	investigatorInstanceList =investigatorService.getInvestigatorsWithParty(gh.getValue("PartyID"),subQuery)
        }
        
        return ['investigatorInstance':investigatorInstance,'partyinstance':partyinstance,
                'investigatorInstanceList':investigatorInstanceList]
    }

    def save = {
			def ctx = AH.application.mainContext
			def springSecurityService=ctx.springSecurityService
        def investigatorInstance = new Investigator(params)
        def partyinstance = Party.get(params.institution)
        investigatorInstance.party = partyinstance
        EmailValidator emailValidator = EmailValidator.getInstance()
		if (emailValidator.isValid(params.email))
		{
        if(!investigatorInstance.hasErrors() && investigatorInstance.save()) {
            flash.message = "Investigator ${investigatorInstance.name} created"
            def userInstance = new Person()
            userInstance.username = investigatorInstance.email
            def subName = investigatorInstance.email
            //Extracting username from emailId
            String userName = subName.substring(0,subName.indexOf('@'))
            
            userInstance.userRealName = investigatorInstance.name
            
            userInstance.password = springSecurityService.encodePassword(userName)
            userInstance.email = investigatorInstance.email
            userInstance.enabled=false
            //userInstance.save()
            def userService = new UserService()
            def userPiInstance = userService.saveNewPi(userInstance)
            def userMapInstance = new UserMap()
            userMapInstance.user = userInstance
            userMapInstance.party = Party.get(params.institution)
            userMapInstance.createdBy="admin"
            userMapInstance.modifiedBy="admin"
            
            if(userMapInstance.save())
            {
            	String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/user/userActivation/"
            	def emailId = notificationsEmailsService.sendMessage(investigatorInstance.email,userName,investigatorInstance.name,userInstance.id,urlPath)
            	
            }
            redirect(action:create,id:investigatorInstance.id)
        }
        else {
            render(view:'create',model:[investigatorInstance:investigatorInstance,partyinstance:partyinstance])
        }
		}
		else
		{
			flash.message = "Please provide a valid email address"
			render(view:'create',model:[investigatorInstance:investigatorInstance,partyinstance:partyinstance])
		}
    }
    def updateSelect = 
    {
    	//String val=(params.selectedValue).toString()
    	if(params.selectedValue)
    	{
	    	def institutionSelected = Party.find("from Party P where P.id="+params.selectedValue)
	    	
	    	render (template:"selectDepartment", model : ['party' : institutionSelected])
	    	  
    	}
    	else
    	{
    		render (template:"notSelected")
    	}
    }
}
