import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import org.apache.commons.validator.EmailValidator
class InvestigatorController {
	def authenticateService
	def notificationsEmailsService
	def gmsSettingsService
	def userService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
    
        def investigatorInstanceList
        def investigatorService=new InvestigatorService()
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
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ investigatorInstance : investigatorInstance ] }
    }

	/* ==================================== NEW =======================================*/
    def delete = {	
	    	def projectsPIMapInstance =ProjectsPIMap.findAll("from ProjectsPIMap PPM where PPM.investigator="+params.id + " and PPM.activeYesNo='Y'")
	    	def projectsInstance=null
	    	if (projectsPIMapInstance)
	    		{projectsInstance =Projects.findAll("from Projects P where P.id="+ projectsPIMapInstance[0].projects.id + " and P.activeYesNo='Y'"	    				)
	    		}	    			    	
	     	if (!projectsInstance)
	    	{			
	            def investigatorInstance = Investigator.get( params.id )
	            if(investigatorInstance) 
	            {
		    		investigatorInstance.activeYesNo="N"
		        	if(!investigatorInstance.hasErrors() && investigatorInstance.save()) 
		        	{ 
		            flash.message = "${message(code: 'default.deleted.label')}"	                
		            redirect(action:create)
		        	}            	
		        	else
		        	{
		    		flash.message = "${message(code: 'default.investigatorinuse.label')}"            			
		    		redirect(action:create)
		        	}
	            }
	            else 
	            {
	                flash.message = "${message(code: 'default.notfond.label')}"                
	                redirect(action:create)
	            }	            
	    	}    
	     	else
	     	{
	    		flash.message = "${message(code: 'default.usedinProjects.label')}"
		        redirect(action: "edit", id: params.id)  	     		
	     	}	     	
        }    
    /*==================================== END =======================================*/
    def edit = {
       	
    	GrailsHttpSession gh=getSession()
    	def investigatorInstance = Investigator.get( params.id )
    	def partyinstance=Party.get(gh.getValue("Party"))
    	def partyDepartmentService=new PartyDepartmentService()
        def departmentList=partyDepartmentService.getActiveDepartment(gh.getValue("PartyID"))
        if(!investigatorInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:create)
        }
        else {
            return [ investigatorInstance : investigatorInstance ,'departmentList':departmentList,'partyinstance':partyinstance]
        }
    }

    def update = 
       	{
    		def investigatorInstance = Investigator.get( params.id )
    		def investigatorService = new InvestigatorService()
    		def ctx = AH.application.mainContext
    		def springSecurityService=ctx.springSecurityService
    		def investigatorInstanceList
    		GrailsHttpSession gh=getSession()
    		def partyinstance=Party.get(gh.getValue("Party"))
    		
    		if(investigatorInstance) 
    		{
    			investigatorInstance.properties = params
    			def chkUniqueNameInstance = investigatorService.getUniqueName(params)
    			def chkUniqueEmailInstance = investigatorService.getUniqueEmail(params)
    			
    			
    				if(chkUniqueEmailInstance && chkUniqueEmailInstance[0].id != Long.parseLong(params.id))
    				{
    					flash.message ="${message(code: 'default.UserNamealreadyexists.label')}"
    					redirect(action:edit,id:investigatorInstance.id)
    				}
    				else
    				{
    					EmailValidator emailValidator = EmailValidator.getInstance()
    		        	if (emailValidator.isValid(params.email))
    		        	{
    		        		investigatorInstance.activeYesNo="Y" //15-11-2010	
    		        		if(!investigatorInstance.hasErrors() && investigatorInstance.save()) 
    		        		{
    		        			flash.message = "${message(code: 'default.updated.label')}"
    		        				redirect(action:create,id:investigatorInstance.id)
    		        		}
    		        		else 
    		        		{
    		        			render(view:'edit',model:[investigatorInstance:investigatorInstance,partyinstance:partyinstance])
    		        		}
    		        	}
    		            
    		        	
    					else
    					{
    						
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
    						def partyDepartmentService = new PartyDepartmentService()
    						def departmentList=partyDepartmentService.getActiveDepartment(gh.getValue("PartyID"))
    						flash.message = "${message(code: 'default.EntervalidEmailAddress.label')}"
    						render(view:'edit',model:[investigatorInstance:investigatorInstance,partyinstance:partyinstance,departmentList:departmentList,investigatorInstanceList:investigatorInstanceList])
    					}
    		         
    				
    					
    					
    				}
    			}
    		
    		else 
    		{
    			flash.message = "${message(code: 'default.notfond.label')}"
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
        def partyDepartmentService=new PartyDepartmentService()
        String subQuery ="";
      
        if(params.sort != null && !params.sort.equals(""))
        	subQuery=" order by I."+params.sort
        if(params.order != null && !params.order.equals(""))
        	subQuery =subQuery+" "+params.order
        def departmentList=partyDepartmentService.getActiveDepartment(gh.getValue("PartyID"))
        if(gh.getValue("Role")=="ROLE_ADMIN")
        {
        	investigatorInstanceList = investigatorService.getAllInvestigators(subQuery)
        }
        else
        {
        	
        	investigatorInstanceList =investigatorService.getInvestigatorsWithParty(gh.getValue("PartyID"),subQuery)
        }
        
        return ['investigatorInstance':investigatorInstance,'partyinstance':partyinstance,
                'investigatorInstanceList':investigatorInstanceList,'departmentList':departmentList]
    }

    def save =
    {
		def ctx = AH.application.mainContext
		def springSecurityService=ctx.springSecurityService
        def investigatorInstance = new Investigator(params)
		def investigatorInstanceList
		GrailsHttpSession gh=getSession()
		def investigatorService=new InvestigatorService()
        def partyinstance = Party.get(params.institution)
        investigatorInstance.party = partyinstance
        def chkUniqueNameInstance = investigatorService.getUniqueName(params)
        def chkUniqueEmailInstance = investigatorService.getUniqueEmail(params)
        Integer userId  = userService.getUserByUserName(params.email)
        
         /*if(chkUniqueNameInstance || userId != null)
        {
        	println"chkUniqueNameInstance "+chkUniqueNameInstance 
        	flash.message = "${message(code: 'default.investigatorexistswithsamename.label')}"
        	redirect(action:create,id:investigatorInstance.id)
        }
        else
        {
        */
          if(chkUniqueEmailInstance || userId != null)
         {
          flash.message ="${message(code: 'default.UserNamealreadyexists.label')}"
    	   redirect(action:create,id:investigatorInstance.id)
         }
         else
         {
        	EmailValidator emailValidator = EmailValidator.getInstance()
        	if (emailValidator.isValid(params.email))
        	{
        		investigatorInstance.activeYesNo="Y" //15-11-2010	
        		if(!investigatorInstance.hasErrors() && investigatorInstance.save()) 
        		{
        			flash.message = "${message(code: 'default.InvestigatorCreated.label')}"
        			def userInstance = new Person()
        			userInstance.username = investigatorInstance.email
        			userInstance.userSurName = investigatorInstance.userSurName
        			def subName = investigatorInstance.email
        			//Extracting username from emailId
        			String userName = subName.substring(0,subName.indexOf('@'))
            
        			userInstance.userRealName = investigatorInstance.name
        			userInstance.userSurName = investigatorInstance.userSurName
        			userInstance.password = springSecurityService.encodePassword(userName)
        			userInstance.email = investigatorInstance.email
        			userInstance.activeYesNo = "Y"
        			userInstance.enabled=false
        			//userInstance.save()
        			def userService = new UserService()
        			def userPiInstance = userService.saveNewPi(userInstance)
        			def userMapInstance = new UserMap()
        			userMapInstance.user = userInstance
        			println(userInstance)
        			userMapInstance.party = Party.get(params.institution)
        			userMapInstance.createdBy="admin"
        			userMapInstance.modifiedBy="admin"
        			
        			if(userMapInstance.save())
        			{
        				String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/user/userActivation/"
        				def mailContent=gmsSettingsService.getGmsSettingsValue("MailContent")
        				def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
        				//mail content
        				String mailMessage="";
        		        mailMessage="Dear "+investigatorInstance.name+", \n \n "+mailContent+".";
        		        mailMessage+="\n \n LoginName    : "+investigatorInstance.email;
        		        mailMessage+="\n Password     : "+userName;
        		        mailMessage+="\n \n \n To activate your account,click on the following link   \t:"+urlPath+userInstance.id;
        				mailMessage+=mailFooter;
        		        def emailId = notificationsEmailsService.sendMessage(investigatorInstance.email,mailMessage,"text/plain")
            	
        			}
        			redirect(action:create,id:investigatorInstance.id)
        		}
        		else 
        		{
        			render(view:'create',model:[investigatorInstance:investigatorInstance,partyinstance:partyinstance])
        		}
	        }
			else
			{
				
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
				def partyDepartmentService = new PartyDepartmentService()
				def departmentList=partyDepartmentService.getActiveDepartment(gh.getValue("PartyID"))
				flash.message = "${message(code: 'default.EntervalidEmailAddress.label')}"
				render(view:'create',model:[investigatorInstance:investigatorInstance,partyinstance:partyinstance,departmentList:departmentList,investigatorInstanceList:investigatorInstanceList])
			}
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
