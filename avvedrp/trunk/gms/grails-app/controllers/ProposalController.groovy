import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import grails.converters.JSON
class ProposalController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']
    def proposalService
    def list = 
    {
        if(!params.max) params.max = 10
        [proposalInstanceList: Proposal.list( params ) ]
    }

    def show = 
    {
        def proposalInstance = Proposal.get( params.id )
        GrailsHttpSession gh = getSession()

        if(!proposalInstance) 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else 
        { 	println "proposal="+params.id
        	def notificationsAttachmentsInstance = NotificationsAttachments.findAll("from NotificationsAttachments NA where NA.proposal="+params.id)
        	gh.putValue("ProposalId",params.id)
        	println "Session--------"+gh.getValue("ProposalId")
        	gh.putValue("Appform",proposalInstance.notification.applicationForm)
        	
        	println "Proposal=== id"+proposalInstance.id
        	println "notificationsAttachmentsInstance="+notificationsAttachmentsInstance.attachmentPath
        	return [ proposalInstance : proposalInstance,notificationsAttachmentsInstance:notificationsAttachmentsInstance ] 
        }
    }

    def delete = 
    {
        def proposalInstance = Proposal.get( params.id )
        if(proposalInstance) 
        {
            proposalInstance.delete()
            flash.message = "${message(code: 'default.deleted.label')}"
            redirect(action:create,id:proposalInstance.notification.id)
        }
        else 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:create)
        }
    }

    def edit = 
    {
        def proposalInstance = Proposal.get( params.id )

        if(!proposalInstance) 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:create)
        }
        else
        {
            return [ proposalInstance : proposalInstance ]
        }
    }

    def update = 
    {
        def proposalInstance = Proposal.get( params.id )
        if(proposalInstance) 
        {
            proposalInstance.properties = params
            if(!proposalInstance.hasErrors() && proposalInstance.save()) 
            {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action:create,id:proposalInstance.notification.id)
            }
            else 
            {
                render(view:'edit',model:[proposalInstance:proposalInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = 
    {
    	
        def proposalInstance = new Proposal()
        proposalInstance.properties = params
        GrailsHttpSession gh = getSession()
         
        gh.removeValue("Help")
    		//putting help pages in session
    	gh.putValue("Help","Create_Proposal.htm")       
        proposalInstance.notification=Notification.get(params.id)
        def proposalInstanceCheck = Proposal.find("from Proposal P where P.notification="+params.id+"and P.party.id="+gh.getValue("Party") ) 
        
        
        //put the application form and proposalid into session
        gh.putValue("Appform",proposalInstance.notification.applicationForm)
        
        def proposalService = new ProposalService();
        def proposalInstanceList=proposalService.getProposalList(proposalInstance,gh.getValue("Party"))
        def proposalId=proposalInstanceList.id
        println "proposalInstanceCheck-"+gh.getValue("Party")
        println "proposalInstanceList.id="+proposalInstanceList
        if(proposalInstanceCheck){
        gh.putValue("ProposalId",proposalInstanceCheck.id)}
        println "+++++++++++++++++++++proposalInstanceList++++++++++++" + proposalInstanceList
        
       // return ['proposalInstance':proposalInstance,'proposalInstanceList':proposalInstanceList]
        redirect(action:create,id:params.id)
        
    }

    def save =
    {
    		def applicationFormView='proposalView'
    		GrailsHttpSession gh = getSession()
    		def proposalNotificationInstance = Proposal.find("from Proposal P where P.notification="+params.id+" and P.party="+gh.getValue("Party")+" and P.lockedYN='N'")
    	if(!proposalNotificationInstance)
    	{
    		def proposalInstance = proposalService.getProposalByNotificationAndParty(params.id,gh.getValue("Party"))
    		if(!proposalInstance)
    		{
					proposalInstance = new Proposal()
					 proposalInstance.notification=Notification.get(params.id)
				 	 proposalInstance.party=Party.get(gh.getValue("Party"))
				     proposalInstance.code=proposalInstance.notification.notificationCode+"-PR-"+proposalInstance.party.code
				     proposalInstance.proposalSubmitteddate=new Date()
				     gh.putValue("Appform",proposalInstance.notification.applicationForm)
				  	 proposalInstance.lockedYN='Y'
        		    	
			        if(proposalNotificationInstance)
			        {
			        	gh.putValue("ProposalId",proposalNotificationInstance.id)
			        	flash.message = "${message(code: 'default.Proposalalreadycreated.label')}"
			        	println "Proposal allready created for this notification ---"
			        	redirect(controller:"proposalApplication",action:"applicationForm",
			        			params:[id:proposalInstance.notification.id,proposalId:proposalInstance.id])
			        }
			        else if(!proposalInstance.hasErrors() && proposalInstance.save())
			        {
			        	gh.putValue("ProposalId",proposalInstance.id)
			            flash.message = "${message(code: 'default.created.label')}"
			            println "Proposal created Successfully"
			            redirect(controller:"proposalApplication",action:"applicationForm",
			            		params:[id:proposalInstance.notification.id,proposalId:proposalInstance.id,
			            		        applicationFormView:applicationFormView])
			        }
			        else 
			        {
			        	render(view:'create',model:[proposalInstance:proposalInstance])
			        }
    		}
    		else
    		{
    			def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(proposalInstance.id)
    			if(proposalApplicationInstance?.controllerId)
    			{
    				render(view:"controllIdValidation",
		            		model:[id:proposalInstance.notification.id,proposalId:proposalInstance.id,
		            		        applicationFormView:applicationFormView])
    			}
    			else
    			{
    				gh.putValue("ProposalId",proposalInstance.id)
    				gh.putValue("Appform",proposalInstance.notification.applicationForm)
    				redirect(controller:"proposalApplication",action:"applicationForm",
		            		params:[id:proposalInstance.notification.id,proposalId:proposalInstance.id,
		            		        applicationFormView:applicationFormView])
    			}
    		}
    	}
    	else
    	{
    		flash.message = "${message(code: 'default.Proposalalreadysubmitted.label')}"
    		redirect(controller:"notificationsEmails",action:"partyNotificationsList")
    	}
    }
    def proposalList =
    {
    		println "Notification Id="+params.id
    		def notificationInstance = Notification.get(params.id)
    		def proposalInstanceList = proposalService.getProposalByNotification(params.id)
    		println "proposalInstanceList.size()"+proposalInstanceList.size()
    		if(proposalInstanceList)
    		{
    		return [ proposalInstanceList: proposalInstanceList ]
    		}
    		else
    		{
    			flash.message =" ${message(code: 'default.NoProposal.label')} "+notificationInstance.notificationCode
    			redirect(controller:'notification',action:'list')
    		}
    }
    
    def submitProposal = 
    {
    		println "Submit*******"
    		if (params.proposalId==null)
    		{
    			GrailsHttpSession gh = getSession()
    	        def proposalInstance = new Proposal()
    	    	println "p id"+params.id
    	        println "+++++++++++++++++++++params.notificationId++++++++++++" + params.id
    	        proposalInstance.notification=Notification.get(params.id)
    	        proposalInstance.lockedYN='N'
    	        proposalInstance.party=Party.get(gh.getValue("Party"))
    	        proposalInstance.code=proposalInstance.notification.notificationCode+"-PR-"+proposalInstance.party.code
    	        proposalInstance.proposalSubmitteddate=new Date()
    			gh.putValue("Appform",proposalInstance.notification.applicationForm)
    	        if(!proposalInstance.hasErrors() && proposalInstance.save())
    	        {
    	            flash.message = "${message(code: 'default.ProposalSibmitted.label')}" +proposalInstance.notification.project.name
    	            redirect(controller:'notificationsEmails',action:'partyNotificationsList',id:proposalInstance.notification.id)
    	        }
    	        else 
    	        {
    	            render(view:'create',model:[proposalInstance:proposalInstance])
    	        }
    		}
    		else
    		{
    			def proposalInstance = Proposal.get( params.proposalId )
    	        if(proposalInstance) 
    	        {
    	        	proposalInstance.properties = params
    	            proposalInstance.lockedYN='N'
    	            if(!proposalInstance.hasErrors() && proposalInstance.save()) 
    	            {
    	                flash.message = "${message(code: 'default.ProposalSibmitted.label')}" +proposalInstance.notification.project.name
    	                redirect(controller:'notificationsEmails',action:'partyNotificationsList',id:proposalInstance.notification.id)
    	            }
    	            else 
    	            {
    	                render(view:'edit',model:[proposalInstance:proposalInstance])
    	            }
    	        }
    	        else {
    	            flash.message = "${message(code: 'default.notfond.label')}"
    	            redirect(action:edit,id:params.id)
    	        }
    		}
    }
    def showProposal =
    {
    		def proposalInstance = Proposal.find("from Proposal P where P.notification="+params.id+" and P.lockedYN='N'" )

            if(!proposalInstance) 
            {
                flash.message = "${message(code: 'default.notfond.label')}"
                redirect(action:list)
            }
            else 
            { 
            	def notificationsAttachmentsInstance = NotificationsAttachments.findAll("from NotificationsAttachments NA where NA.proposal="+proposalInstance.id)
            	return [ proposalInstance : proposalInstance,notificationsAttachmentsInstance:notificationsAttachmentsInstance ] 
            }
    		
    }
    def validateControllId = 
    {
    	def applicationFormView='proposalView'
    	GrailsHttpSession gh = getSession()
    	def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(params.proposal.id) 
    	if(params.controllerId)
    	{
    		if(proposalApplicationInstance.controllerId == params.controllerId)
    		{
    			gh.putValue("ProposalId",proposalApplicationInstance.proposal.id)
    			gh.putValue("Appform",proposalApplicationInstance.proposal.notification.applicationForm)
				redirect(controller:"proposalApplication",action:"applicationForm",
	            		params:[id:proposalApplicationInstance.proposal.notification.id,proposalId:proposalApplicationInstance.proposal.id,
	            		        applicationFormView:applicationFormView])
    		}
    		else
    		{
    			flash.message = "${message(code: 'default.ProposalControllIdNot.label')}" 
    			render(view:"controllIdValidation",
	            		model:[id:proposalApplicationInstance.proposal.notification.id,proposalId:proposalApplicationInstance.proposal.id,
	            		        applicationFormView:applicationFormView])
    		}
    	}
    	else
    	{
    		flash.message = "${message(code: 'default.ProposalControllIdNot.label')}" 
    			render(view:"controllIdValidation",
	            		model:[id:proposalApplicationInstance.proposal.notification.id,proposalId:proposalApplicationInstance.proposal.id,
	            		        applicationFormView:applicationFormView])
    	}
    }
    
}
