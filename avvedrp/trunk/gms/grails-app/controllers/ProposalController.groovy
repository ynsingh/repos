import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import grails.converters.JSON
import org.apache.commons.validator.EmailValidator
class ProposalController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']
    def proposalService
    def notificationService
    def approvalAuthorityDetailService
    def partyService
    def gmsSettingsService
    def notificationsEmailsService
    def proposalApprovalAuthorityMapService
    def approvalAuthorityService
    def notificationsAttachmentsService
    def attachmentsService
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
    		def notificationInstance
    		def proposalInstanceList
        	
    		if(params.id)
    		{
    			notificationInstance = Notification.get(params.id)
    			proposalInstanceList = proposalService.getProposalByNotification(params.id)
    		}
    		else
    		{
    			notificationInstance = Notification.get(params.notificationId)	
    			proposalInstanceList = proposalService.getProposalByNotification(params.notificationId)
    		}
    		def notificationInstanceList
    		def evalItemService = new EvalItemService()  	   	
    		def evalScoreInstanceList = []
    		def maxScaleList = []
    		def proposalApplicationInstanceList = []
    		for(int i=0;i<proposalInstanceList.size();i++)
    		{
    			def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal.id ="+proposalInstanceList[i].id) 
    	    	proposalApplicationInstanceList.add(proposalApplicationInstance)
    			def evalScoreInstance = evalItemService.getEvalScoreByProposal(proposalInstanceList[i].id)
    			evalScoreInstanceList.add(evalScoreInstance)
    			def evalItemList = evalItemService.getevalItemList(proposalInstanceList[i].notification.id)
    			double maxScale = 0
    			for(int j=0;j<evalItemList.size();j++)
    			{
    				def scale = evalItemService.getMaxScoreEvalScale(evalItemList[j].evalScale)
    				if(scale)
    				{
    					if(scale[0])
    						maxScale = maxScale + scale[0].doubleValue()
    				}
    			}
    			def avgScale = maxScale/evalItemList.size()
    			def val = Math.round(avgScale)
    			if(maxScale)
    					maxScaleList.add(val.toString())
    		}
    		if(proposalInstanceList)
    		{
    		return [proposalInstanceList: proposalInstanceList,notificationInstance:notificationInstance,
    		        evalScoreInstanceList:evalScoreInstanceList,maxScaleList:maxScaleList,proposalApplicationInstanceList:proposalApplicationInstanceList]
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
    def uploadProposalApplication ={
    		println "params "+params
    		GrailsHttpSession gh = getSession()
    		gh.removeValue("ProposalId")
    		gh.putValue("NotificationId",params.id)
			
	}
    def validateProposalControllId = 
    {
    		GrailsHttpSession gh = getSession()
    		if(params.controllerId)
        	{
    			//def proposalApplicationInstance = proposalService.getProposalApplicationByControllerId(params.controllerId) 
    			def proposalApplicationInstance = proposalService.getProposalApplicationByControllerIdAndNotification(params.controllerId,gh.getValue("NotificationId"))
    			if(proposalApplicationInstance)
        		{
        			if(proposalApplicationInstance.proposal.lockedYN=='Y')
        			{
    				gh.putValue("ProposalId",proposalApplicationInstance.proposal.id)
        				if(proposalApplicationInstance.proposal.proposalVersion>0)
        				{
        					redirect(controller:"proposalApplication",action:'proposalAppPreview',params:['proposalApplication.id':proposalApplicationInstance?.id])
        				}
        				else
        				{
        					redirect(controller:"proposalApplication",action:"proposalAppPart1PersonalDetails",
        							params:['proposalId':proposalApplicationInstance.proposal.id])
        				}
        			}
        			else if(proposalApplicationInstance.proposal.lockedYN=='N')
        			{
        				flash.message = "${message(code: 'default.FormSuccessfullySubmited.label')}" 
        				gh.putValue("ProposalId",proposalApplicationInstance.proposal.id)
                		redirect(action:"updateProposal",
            	            		params:['id':proposalApplicationInstance.id])
        				//render(view:"uploadProposal",)
        			}
        		}
        		else
        		{
        			flash.message = "${message(code: 'default.ProposalControllIdNot.label')}" 
        			render(view:"uploadProposalApplication")
        		}
        	}
        	else
        	{
        		flash.message = "${message(code: 'default.ProposalControllIdNot.label')}" 
        		render(view:"uploadProposalApplication")
        	}	
    }
    def proposalSubmission={
    		def proposalInstance = proposalService.getProposalById( params.proposalId )
    		def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(proposalInstance?.id)
    		def proposalApplicationExtInstance = proposalService.getProposalApplicationExtByProposalApplication( proposalApplicationInstance?.id )
    		def projectTitle
    		def proposalCategory
    		def dateofSubmission
    		def submitedBy
    		def firstName
    		def lastName
    		def designation
    		def organisation
    		def officeAddress
    		def city
    		def state
    		def mobile
    		def phoneNo
    		def eMail
    		for(proposalApplicationExtValue in proposalApplicationExtInstance)
    		{
    			if(proposalApplicationExtValue.field == 'TitleOfTheResearchProject_2')
    			{
    				projectTitle=proposalApplicationExtValue.value
    			}
    			    			
    		}
    		EmailValidator emailValidator = EmailValidator.getInstance()
    		def mailContent=gmsSettingsService.getGmsSettingsValue("MailContent")
			def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
			String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/login/auth"
	        if(proposalInstance) 
	        {
	        	proposalInstance.properties = params
	            proposalInstance.lockedYN='N'
	            def aprovalAuthorityDetailInstance = approvalAuthorityDetailService.getDefaultApprovalAuthorityDetailsByParty(proposalInstance.notification.party.id)
	            def proposalInstanceSave =proposalService.updateProposal(proposalInstance)
	            for(aprovalAuthorityDetailValue in aprovalAuthorityDetailInstance)
	            {
	            	if(aprovalAuthorityDetailValue?.person?.email)
					{
						if (emailValidator.isValid(aprovalAuthorityDetailValue?.person?.email))
						{
							String mailMessage="";
					        mailMessage="Dear Reviewer"+".";
					        mailMessage+="\none Project proposal has been uploaded on the site for reviewing.The details are as follows";
					        mailMessage+="\n\n New Proposal's Brief";
					        mailMessage+="\n------------------------";
					        mailMessage+="\n\n\tProject Title:- "+projectTitle+""
					        mailMessage+="\n\tProposal Category :- "+proposalApplicationInstance?.proposalCategory?.name
					        mailMessage+="\n\tDate of Submission :- "+proposalInstance?.proposalSubmitteddate
					        mailMessage+="\n\nSubmited By :-"
					        mailMessage+="\n\n\tName :- "+proposalApplicationInstance?.name
					        mailMessage+="\n\tDesignation :- "+proposalApplicationInstance?.designation
					        mailMessage+="\n\tOrganisation Name :- "+proposalApplicationInstance?.organisation
					        mailMessage+="\n\tOffice Address :- "+proposalApplicationInstance?.postalAddress
					        mailMessage+="\n\t\t\tCity :- "+proposalApplicationInstance?.city
					        mailMessage+="\n\t\t\tState :- "+proposalApplicationInstance?.state
					        mailMessage+="\n\t\t\tMobile :- "+proposalApplicationInstance?.mobile
					        mailMessage+="\n\t\t\tPhone No :- "+proposalApplicationInstance?.phone
					        mailMessage+="\n\t\t\tE_Mail :- "+proposalApplicationInstance?.email
					        mailMessage+="\n\nTo review the proposal please logon to :- "+urlPath
					        mailMessage=mailMessage+"\n\n\n" 
					    	mailMessage+=mailFooter
					        def emailId = notificationsEmailsService.sendMessage(aprovalAuthorityDetailValue?.person?.email,mailMessage)
						}
					}
	            }
	            if(proposalInstanceSave) 
	            {
	            	def approvalAuthorityInstanceList = approvalAuthorityService.getDefaultActiveApprovalAuthority(proposalInstance.notification.party.id)
	            	for(approvalAuthorityInstance in approvalAuthorityInstanceList)
	            	{
	            		def proposalApprovalAuthorityMapInstance=proposalApprovalAuthorityMapService.saveProposalApprovalAuthorityMapForProposalApplication(approvalAuthorityInstance,proposalInstance.id)
	            	}
	            	flash.message = "${message(code: 'default.ProposalSibmitted.label')}"
	                redirect(action:'notificationList')
	            }
	            else 
	            {
	                render(view:'uploadProposalApplication',model:[proposalInstance:proposalInstance])
	            }
	        }
	        else {
	            flash.message = "${message(code: 'default.notfond.label')}"
	            redirect(action:uploadProposalApplication)
	        }
    }
    def selectGrantorForProposal={
    		def partyInstance=partyService.getAllActivePartiesAndGrantAgencies()
    		//render (template:"selectGrantor", model :['partyInstance':partyInstance])
    		[partyInstance:partyInstance]
    		
    }
    def saveSelectGrantorForProposal={
    		println params
        	GrailsHttpSession gh = getSession()
        	gh.removeValue("ProposalId")
    		gh.putValue("Grantor",params.party.id)
    		println "Grantor "+gh.getValue("Grantor")
    		redirect(controller:"proposalApplication",action:"proposalAppPart1PersonalDetails")
    	            		    		
    }
    /*
     * method to list proposal for reviewer
     */
    def proposalApplicationList = 
    {
    		GrailsHttpSession gh=getSession()
    		/*method to get proposal application of each reviewer using user id and party id*/
    		def proposalInstanceList = proposalService.getProposalApplicationListForReviewer(gh.getValue("UserId"),gh.getValue("Party"))
    		/*method to get attachment type instance of cv*/
    		def attachmentsTypeInstanceCV=attachmentsService.getAttachmentTypesByDocumentTypeAndType("CV","Proposal")
    		def attachmentsInstanceCVList=[]
    		def evalItemService = new EvalItemService()  	   	
    		def evalScoreInstanceList = []
    		def maxScaleList = []
    		for(int i=0;i<proposalInstanceList.size();i++)
    		{
    			/*method to get attachment cv of each proposal*/
    			def attachmentsInstanceGetCV=attachmentsService.getAttachmentsByDomainAndType("Proposal",attachmentsTypeInstanceCV?.type,proposalInstanceList[i]?.proposal?.id)
    			/*adding each attachments to list*/
    			attachmentsInstanceCVList.add(attachmentsInstanceGetCV)
    			def evalScoreInstance = evalItemService.getEvalScoreByProposal(proposalInstanceList[i].proposal.id)
    			evalScoreInstanceList.add(evalScoreInstance)
    			def evalItemList = evalItemService.getevalItemList(proposalInstanceList[i].proposal.notification.id)
    			double maxScale = 0
    			for(int j=0;j<evalItemList.size();j++)
    			{
    				def scale = evalItemService.getMaxScoreEvalScale(evalItemList[j].evalScale)
    				if(scale)
    				{
    					if(scale[0])
    						maxScale = maxScale + scale[0].doubleValue()
    				}
    			}
    			def avgScale = maxScale/evalItemList.size()
    			def val = Math.round(avgScale)
    			//if(maxScale) (changeLog(28-02-2011) Ainsha)
    			maxScaleList.add(val.toString())
    		}
    		[proposalInstanceList:proposalInstanceList,evalScoreInstanceList:evalScoreInstanceList,
    		 maxScaleList:maxScaleList,attachmentsInstanceCVList:attachmentsInstanceCVList]
    }
    def updateProposal = 
    {
    		def proposalApplicationInstance = proposalService.getProposalApplicationById(params.id)
    		[proposalApplicationInstance:proposalApplicationInstance]
    }
    def notificationList = 
    {
    		def notificationInstanceList=notificationService.getAllPublicAndPublishedNotification()	
    		[notificationInstanceList:notificationInstanceList]
    }
    def notificationDetails = 
    {
    		def notificationInstance=notificationService.getNotificationById(params.id)	
    		def notificationsAttachmentsInstance=notificationsAttachmentsService.getNotificationAttachmentsByNotificationId('Notification',params.id)
    		[notificationsInstance:notificationInstance,notificationsAttachmentsInstance:notificationsAttachmentsInstance]
    }
    /*
     * method used to search proposal based on certain criteria
     */
    def proposalSearch =
    {
    		GrailsHttpSession gh=getSession()
    		/*method to get proposal application based on search criteria*/
    		def proposalInstanceList = proposalService.getProposalApplicationSearchListForReviewer(gh.getValue("UserId"),gh.getValue("Party"),params)
    		/*method to get attachment type instance of cv*/
    		def attachmentsTypeInstanceCV=attachmentsService.getAttachmentTypesByDocumentTypeAndType("CV","Proposal")
    		def attachmentsInstanceCVList=[]
    		def evalItemService = new EvalItemService()  	   	
    		def evalScoreInstanceList = []
    		def maxScaleList = []
    		for(int i=0;i<proposalInstanceList.size();i++)
    		{
    			/*method to get attachment cv of each proposal*/
    			def attachmentsInstanceGetCV=attachmentsService.getAttachmentsByDomainAndType("Proposal",attachmentsTypeInstanceCV?.type,proposalInstanceList[i]?.proposal?.id)
    			/*adding each attachments to list*/
    			attachmentsInstanceCVList.add(attachmentsInstanceGetCV)
    			def evalScoreInstance = evalItemService.getEvalScoreByProposal(proposalInstanceList[i].proposal.id)
    			evalScoreInstanceList.add(evalScoreInstance)
    			def evalItemList = evalItemService.getevalItemList(proposalInstanceList[i].proposal.notification.id)
    			double maxScale = 0
    			for(int j=0;j<evalItemList.size();j++)
    			{
    				def scale = evalItemService.getMaxScoreEvalScale(evalItemList[j].evalScale)
    				if(scale)
    				{
    					if(scale[0])
    						maxScale = maxScale + scale[0].doubleValue()
    				}
    			}
    			def avgScale = maxScale/evalItemList.size()
    			def val = Math.round(avgScale)
    			if(maxScale)
    					maxScaleList.add(val.toString())
    		}
    		render (template:"proposalSearch", model : ['proposalInstanceList' : proposalInstanceList,
    		                                              'evalScoreInstanceList':evalScoreInstanceList,'maxScaleList':maxScaleList,'attachmentsInstanceCVList':attachmentsInstanceCVList])
    		
    
    }
    
}
