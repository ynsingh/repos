import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class NotificationsEmailsController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
    		
    		//def notificationsEmailsInstanceList
    		def partyInstanceList
    		GrailsHttpSession gh = getSession()
    		
    	    
    		
            gh.removeValue("Help")
           		//putting help pages in session
           	gh.putValue("Help","Notification_Emails.htm")
        if(!params.max) params.max = 10
        println "iddd="+params.id
        
        def notificationsEmailsInstanceList = NotificationsEmails.findAll("from NotificationsEmails NE")
    	println "notificationsEmailsInstanceList="+notificationsEmailsInstanceList
       def notificationsEmailsService = new NotificationsEmailsService()
    		println "what????"
    		notificationsEmailsService.savePartyStatus(params.id)
    		println "list*****"
    	partyInstanceList = notificationsEmailsService.getAllParty()
    	//println "check--"+notificationsEmailsInstanceList.status
    	
    	
    	[ partyInstanceList : partyInstanceList ]
    }

    def show = {
        def notificationsEmailsInstance = NotificationsEmails.get( params.id )

        if(!notificationsEmailsInstance) {
            flash.message = "NotificationsEmails not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ notificationsEmailsInstance : notificationsEmailsInstance ] }
    }

    def delete = {
        def notificationsEmailsInstance = NotificationsEmails.get( params.id )
        if(notificationsEmailsInstance) {
            notificationsEmailsInstance.delete()
            flash.message = "NotificationsEmails ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "NotificationsEmails not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def notificationsEmailsInstance = NotificationsEmails.get( params.id )

        if(!notificationsEmailsInstance) {
            flash.message = "NotificationsEmails not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ notificationsEmailsInstance : notificationsEmailsInstance ]
        }
    }

    def update = {
        def notificationsEmailsInstance = NotificationsEmails.get( params.id )
        if(notificationsEmailsInstance) {
            notificationsEmailsInstance.properties = params
            if(!notificationsEmailsInstance.hasErrors() && notificationsEmailsInstance.save()) {
                flash.message = "NotificationsEmails ${params.id} updated"
                redirect(action:show,id:notificationsEmailsInstance.id)
            }
            else {
                render(view:'edit',model:[notificationsEmailsInstance:notificationsEmailsInstance])
            }
        }
        else {
            flash.message = "NotificationsEmails not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def notificationsEmailsInstance = new NotificationsEmails()
        notificationsEmailsInstance.properties = params
        return ['notificationsEmailsInstance':notificationsEmailsInstance]
    }

    def save = {
        def notificationsEmailsInstance = new NotificationsEmails(params)
        if(!notificationsEmailsInstance.hasErrors() && notificationsEmailsInstance.save()) {
            flash.message = "NotificationsEmails ${notificationsEmailsInstance.id} created"
            redirect(action:show,id:notificationsEmailsInstance.id)
        }
        else {
            render(view:'create',model:[notificationsEmailsInstance:notificationsEmailsInstance])
        }
    }
    def createMail = {
    		//def notificationsEmailsInstance = new NotificationsEmails(params)
    		//notificationsEmailsInstance.properties = params
    		//redirect(action:send)
    		println "check--"+params.choices
    		def notificationsEmailsInstance = new NotificationsEmails()
        notificationsEmailsInstance.properties = params
        return ['params':params]
    }
    def send = {
    		def notificationsEmailsService = new NotificationsEmailsService()
    		//println "name--"+params.userName
    		def userName="hi"
    		def emailInstance = params.choices
    		println "name--"+emailInstance
    		println "name--"+params.choices
    		GrailsHttpSession gh = getSession()
        	def partyNotificationsInstance = Party.find("from Party P where P.id="+"'"+gh.getValue("Party")+"'")
        	println "partyemails"+partyNotificationsInstance.email
    		if(params.choices)
    		{
    		def emailsList=params.choices.toString();
    		println "emailsList"+emailsList
    		def emailsSplit=emailsList.split(',')
    		println "emailsList"+emailsSplit.length
    		println "remarks-"+params.remarks
    		println "Notification in send="+params.notificationId
    		
    		
    		if (emailsSplit.length ==1)
    		{
    		def email=[]
    		email.add(params.choices)
    		emailInstance=email
    		println "orvalue--"+emailInstance
    		}
    		//def notificationInstance = 
    		def notificationPartyList = notificationsEmailsService.contactUser(params.remarks,params.notificationId,emailInstance,partyNotificationsInstance.email)
    		println "Note=="+notificationPartyList
    		render (view:'sendList',model:[notificationPartyList:notificationPartyList])
    		//redirect(action:show)
    		}
    		else
    		{
    			flash.message = "Institution not selected"
    			redirect(action:list,id:params.notificationId)
    		}
    	  }
    def partyNotificationsList = {
    		println "hii party"
    		GrailsHttpSession gh = getSession()
    		
    
		
        gh.removeValue("Help")
       		//putting help pages in session
       	gh.putValue("Help","Party_Notifications_List.htm")
            if(!params.max) params.max = 10
            def notificationsEmailsService = new NotificationsEmailsService()
    		println "party=="+gh.getValue("Party")
            def partyNotificationsInstance = notificationsEmailsService.getNotificationsByParty(gh.getValue("Party"))
            println "instance=="+partyNotificationsInstance
            [ partyNotificationsInstance : partyNotificationsInstance ]
    }
    def showPartyNotifications = {
    		
    		println "comonnnnnnnnnnn=="+params.id
    		println "show party"
    		GrailsHttpSession gh = getSession()
    		gh.removeValue("Help")
       		//putting help pages in session
       	gh.putValue("Help","Show_Party_Notifications.htm")
    		 def notificationsInstance = Notification.get( params.id )
    		 println "hii id="+notificationsInstance
    	        if(!notificationsInstance) {
    	            flash.message = "NotificationsEmails not found with id ${params.id}"
    	            redirect(action:partyNotificationsList)
    	        }
    	        else {
    	        	def notificationsAttachmentsInstance = NotificationsAttachments.findAll("from NotificationsAttachments NA where NA.attachmentType.documentType='Notification' and NA.notification="+params.id)
    	        	def attachmentTypesInstance = AttachmentType.findAll("from AttachmentType AT where AT.documentType='Notification'")
    	        	def proposalInstance = Proposal.find("from Proposal P where P.notification="+params.id+"and P.party.id='"+gh.getValue("Party")+"'and P.lockedYN='N'")

    	        	if(proposalInstance){flash.message = "Proposal Submited for this Notification"}
    	        	return [ notificationsInstance : notificationsInstance,attachmentTypesInstance:attachmentTypesInstance,notificationsAttachmentsInstance:notificationsAttachmentsInstance,proposalInstance:proposalInstance ] }
    		
    }
}
