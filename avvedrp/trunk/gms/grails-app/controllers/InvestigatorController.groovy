import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class InvestigatorController {
	def authenticateService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ investigatorInstanceList: Investigator.list( params ) ]
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
            redirect(action:list)
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
                redirect(action:list,id:investigatorInstance.id)
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
        def partyinstance=Party.get(gh.getValue("Party"))
       
        def department = PartyDepartment.find("from PartyDepartment P where P.party.id="+partyinstance.id)
        
       
        investigatorInstance.properties = params
        //investigatorInstance.department=department
        return ['investigatorInstance':investigatorInstance,'partyinstance':partyinstance]
    }

    def save = {
        def investigatorInstance = new Investigator(params)
        println "--------------------"+params.institution
        investigatorInstance.party = Party.get(params.institution)
        if(!investigatorInstance.hasErrors() && investigatorInstance.save()) {
            flash.message = "Investigator ${investigatorInstance.name} created"
            def userInstance = new User()
            userInstance.username = investigatorInstance.email
            def subName = investigatorInstance.email
            //Extracting username from emailId
            String userName = subName.substring(0,subName.indexOf('@'))
            println "userName"+userName
            userInstance.userRealName = investigatorInstance.name
            println "userInstance"+userInstance
            userInstance.passwd = authenticateService.encodePassword(userName)
            userInstance.email = investigatorInstance.email
            userInstance.enabled=true
           // userInstance.save()
            def userService = new UserService()
            def userPiInstance = userService.saveNewPi(userInstance)
            def userMapInstance = new UserMap()
            userMapInstance.user = userInstance
            println "userInstance"+userInstance
            println "userInstance"+userMapInstance.user
            userMapInstance.party = Party.get(params.institution)
            userMapInstance.createdBy="admin"
            userMapInstance.modifiedBy="admin"
            println "userMapInstance"+userMapInstance
            if(userMapInstance.save())
            {
            	println "userMap Pi saved"
            }
            redirect(action:list,id:investigatorInstance.id)
        }
        else {
            render(view:'create',model:[investigatorInstance:investigatorInstance])
        }
    }
    def updateSelect = 
    {
    	println "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"+params
    	println ("2fffff"+params.selectedValue)
    	//String val=(params.selectedValue).toString()
    	if(params.selectedValue)
    	{
	    	def institutionSelected = Party.find("from Party P where P.id="+params.selectedValue)
	    	println "lllllllllllww" +institutionSelected;
	    	render (template:"selectDepartment", model : ['party' : institutionSelected])
	    	println "after" +institutionSelected;  
    	}
    	else
    	{
    		render (template:"notSelected")
    	}
    }
}
