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
        GrailsHttpSession gh = getSession()

        if(!proposalInstance) 
        {
            flash.message = "Proposal not found"
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
            flash.message = "Proposal deleted"
            redirect(action:create,id:proposalInstance.notification.id)
        }
        else 
        {
            flash.message = "Proposal not found"
            redirect(action:create)
        }
    }

    def edit = 
    {
        def proposalInstance = Proposal.get( params.id )

        if(!proposalInstance) 
        {
            flash.message = "Proposal not found"
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
                flash.message = "Proposal updated"
                redirect(action:create,id:proposalInstance.notification.id)
            }
            else 
            {
                render(view:'edit',model:[proposalInstance:proposalInstance])
            }
        }
        else {
            flash.message = "Proposal not found "
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
    		GrailsHttpSession gh = getSession()
    		def proposalNotificationInstance = Proposal.find("from Proposal P where P.notification="+params.id+" and P.party="+gh.getValue("Party")+" and P.lockedYN='N'")
    	if(!proposalNotificationInstance)
    	{
    	def proposalInstance = Proposal.find("from Proposal P where P.notification="+params.id+" and P.party="+gh.getValue("Party"))
        if(!proposalInstance)
        {
    	 proposalInstance = new Proposal()
        }
    	proposalInstance.notification=Notification.get(params.id)
    	println "p id"+proposalInstance.notification.id
        println "+++++++++++++++++++++params.notificationId++++++++++++" + params.notificationId
        //proposalInstance.notification=Notification.get(params.notificationId)
        proposalInstance.party=Party.get(gh.getValue("Party"))
        proposalInstance.code=proposalInstance.notification.notificationCode+"-PR-"+proposalInstance.party.code
        println "proposalInstance.code "+proposalInstance.code
        proposalInstance.proposalSubmitteddate=new Date()
        //def proposalInstanceCheck = Proposal.find("from Proposal P where P.notification="+params.id+"and P.party.id="+gh.getValue("Party") ) 
        gh.putValue("Appform",proposalInstance.notification.applicationForm)
       // if(proposalInstanceCheck){
        //gh.putValue("ProposalId",proposalInstanceCheck.id)}
        
    	println "partyid"+proposalInstance.party
        proposalInstance.lockedYN='Y'
        println "++++++++++documentType++++++"+ params.documentType
        
        println "proposalNotificationInstance"+proposalNotificationInstance
        if(proposalNotificationInstance)
        {
        	gh.putValue("ProposalId",proposalNotificationInstance.id)
        	flash.message = "Proposal allready created for this notification"
        	println "Proposal allready created for this notification"
        	redirect(controller:"proposalApplication",action:"applicationForm",params:[id:proposalInstance.notification.id,proposalId:proposalInstance.id])
        }
        else if(!proposalInstance.hasErrors() && proposalInstance.save())
        {
        	gh.putValue("ProposalId",proposalInstance.id)
            flash.message = "Proposal created Successfully"
            println "Proposal created Successfully"
            redirect(controller:"proposalApplication",action:"applicationForm",params:[id:proposalInstance.notification.id,proposalId:proposalInstance.id])
        }
        else 
        {
        	println "else----------"
            render(view:'create',model:[proposalInstance:proposalInstance])
        }
    	}
    	else
    	{
    		flash.message = "Proposal already submitted"
    		redirect(controller:"notificationsEmails",action:"partyNotificationsList")
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
    			def proposalInstance = Proposal.get( params.proposalId )
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
    	            flash.message = "Proposal not found"
    	            redirect(action:edit,id:params.id)
    	        }
    		}
    }
    def showProposal =
    {
    		def proposalInstance = Proposal.find("from Proposal P where P.notification="+params.id+" and P.lockedYN='N'" )

            if(!proposalInstance) 
            {
                flash.message = "Proposal not found"
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
