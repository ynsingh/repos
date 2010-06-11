import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class ProposalController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = 
    {
        if(!params.max) params.max = 10
        [proposalInstanceList: Proposal.list( params ) ]
    }

    def show = 
    {
        def proposalInstance = Proposal.get( params.id )

        if(!proposalInstance) 
        {
            flash.message = "Proposal not found with id ${params.id}"
            redirect(action:list)
        }
        else 
        { 	println "proposal="+params.id
        	def notificationsAttachmentsInstance = NotificationsAttachments.findAll("from NotificationsAttachments NA where NA.proposal="+params.id)
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
            flash.message = "Proposal ${params.id} deleted"
            redirect(action:create,id:proposalInstance.notification.id)
        }
        else 
        {
            flash.message = "Proposal not found with id ${params.id}"
            redirect(action:create)
        }
    }

    def edit = 
    {
        def proposalInstance = Proposal.get( params.id )

        if(!proposalInstance) 
        {
            flash.message = "Proposal not found with id ${params.id}"
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
                flash.message = "Proposal ${params.id} updated"
                redirect(action:create,id:proposalInstance.notification.id)
            }
            else 
            {
                render(view:'edit',model:[proposalInstance:proposalInstance])
            }
        }
        else {
            flash.message = "Proposal not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = 
    {
    	
        def proposalInstance = new Proposal()
        proposalInstance.properties = params
        
        println "+++++++++++++++++++++paramscreate++++++++++++" + params
        proposalInstance.notification=Notification.get(params.id)
        def proposalInstanceCheck = Proposal.findAll("from Proposal P where P.notification="+params.id+" and P.lockedYN='N'") 
        println "proposalInstanceCheck "+proposalInstanceCheck
        
        println "+++++++++++++++++++++proposalInstance++++++++++++" + proposalInstance.notification
        def proposalService = new ProposalService();
        def proposalInstanceList=proposalService.getProposalList(proposalInstance)
        println "+++++++++++++++++++++proposalInstanceList++++++++++++" + proposalInstanceList
        
        return ['proposalInstance':proposalInstance,'proposalInstanceList':proposalInstanceList]
        
        
    }

    def save =
    {
    	GrailsHttpSession gh = getSession()
        def proposalInstance = new Proposal(params)
    	println "p id"+params.id
        println "+++++++++++++++++++++params.notificationId++++++++++++" + params.notificationId
        proposalInstance.notification=Notification.get(params.notificationId)
        proposalInstance.party=Party.get(gh.getValue("Party"))
        proposalInstance.lockedYN='Y'
        println "++++++++++documentType++++++"+ params.documentType
        if(!proposalInstance.hasErrors() && proposalInstance.save())
        {
            flash.message = "Proposal created Successfully"
            redirect(action:create,id:proposalInstance.notification.id)
        }
        else 
        {
            render(view:'create',model:[proposalInstance:proposalInstance])
        }
    }
    def proposalList =
    {
    		println "Notification Id="+params.id
    		def notificationInstance = Notification.get(params.id)
    		def proposalInstanceList = Proposal.findAll("from Proposal P where P.notification.id="+params.id+" and P.lockedYN='N'")
    		if(proposalInstanceList)
    		{
    		return [ proposalInstanceList: proposalInstanceList ]
    		}
    		else
    		{
    			flash.message = "No Proposal for Notification "+notificationInstance.notificationCode
    			redirect(controller:'notification',action:'list')
    		}
    }
    def submitProposal = 
    {
    		println "Submit*******"
    		if (params.id==null)
    		{
    			GrailsHttpSession gh = getSession()
    	        def proposalInstance = new Proposal(params)
    	    	println "p id"+params.id
    	        println "+++++++++++++++++++++params.notificationId++++++++++++" + params.notificationId
    	        proposalInstance.notification=Notification.get(params.notificationId)
    	        proposalInstance.lockedYN='N'
    	        proposalInstance.party=Party.get(gh.getValue("Party"))
    	        println "++++++++++documentType++++++"+ params.documentType
    	        if(!proposalInstance.hasErrors() && proposalInstance.save())
    	        {
    	            flash.message = "Proposal created and Submited Successfully for project ${proposalInstance.notification.project.name}"
    	            redirect(controller:'notificationsEmails',action:'partyNotificationsList',id:proposalInstance.notification.id)
    	        }
    	        else 
    	        {
    	            render(view:'create',model:[proposalInstance:proposalInstance])
    	        }
    		}
    		else
    		{
    			def proposalInstance = Proposal.get( params.id )
    	        if(proposalInstance) 
    	        {
    	        	println "proposal="+proposalInstance
    	            proposalInstance.properties = params
    	            proposalInstance.lockedYN='N'
    	            if(!proposalInstance.hasErrors() && proposalInstance.save()) 
    	            {
    	                flash.message = "Proposal Submited for Notification of Project ${proposalInstance.notification.project.name}"
    	                redirect(controller:'notificationsEmails',action:'partyNotificationsList',id:proposalInstance.notification.id)
    	            }
    	            else 
    	            {
    	                render(view:'edit',model:[proposalInstance:proposalInstance])
    	            }
    	        }
    	        else {
    	            flash.message = "Proposal not found with id ${params.id}"
    	            redirect(action:edit,id:params.id)
    	        }
    		}
    }
    def showProposal =
    {
    		def proposalInstance = Proposal.find("from Proposal P where P.notification="+params.id+" and P.lockedYN='N'" )

            if(!proposalInstance) 
            {
                flash.message = "Proposal not found with id ${params.id}"
                redirect(action:list)
            }
            else 
            { 	println "proposal="+params.id
            	def notificationsAttachmentsInstance = NotificationsAttachments.findAll("from NotificationsAttachments NA where NA.proposal="+proposalInstance.id)
            	println "notificationsAttachmentsInstance="+notificationsAttachmentsInstance.attachmentPath
            	return [ proposalInstance : proposalInstance,notificationsAttachmentsInstance:notificationsAttachmentsInstance ] 
            }
    		
    }
    
}
