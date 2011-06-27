import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import grails.converters.JSON
import java.util.Date
import grails.util.GrailsUtil
import java.text.SimpleDateFormat
import org.apache.commons.validator.EmailValidator
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import java.lang.Number;
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
    def evalItemService
    def userService
    def projectsService
    def grantAllocationService 
    def projectTypeService
    
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
        { 	
        	def notificationsAttachmentsInstance = NotificationsAttachments.findAll("from NotificationsAttachments NA where NA.proposal="+params.id)
        	gh.putValue("ProposalId",params.id)
        	
        	gh.putValue("Appform",proposalInstance.notification.applicationForm)
        	
        	
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
       
        if(proposalInstanceCheck){
        gh.putValue("ProposalId",proposalInstanceCheck.id)}
        
        
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
			        	
			        	redirect(controller:"proposalApplication",action:"applicationForm",
			        			params:[id:proposalInstance.notification.id,proposalId:proposalInstance.id])
			        }
			        else if(!proposalInstance.hasErrors() && proposalInstance.save())
			        {
			        	gh.putValue("ProposalId",proposalInstance.id)
			            flash.message = "${message(code: 'default.created.label')}"
			            
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
    			def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(proposalInstanceList[i].id)
    			def proposalAwardInstance = proposalService.getAwardByProposal(proposalInstanceList[i].id)
    			
    	    	if(proposalAwardInstance)
    			{
    				proposalApplicationInstance.award='Y'
    			}
    			else
    			{
    				proposalApplicationInstance.award='N'
    			}
    			proposalApplicationInstanceList.add(proposalApplicationInstance)
    			def evalScoreInstance = evalItemService.getEvalScoreByProposal(proposalInstanceList[i].id)
    			evalScoreInstanceList.add(evalScoreInstance)
    			def evalItemList = evalItemService.getevalItemList(proposalInstanceList[i].notification.id)
    			double nOfQuestions = evalItemList.size()
    			double maxScale = 0
    			for(int j=0;j<evalItemList.size();j++)
    			{
    				def scale = evalItemService.getMaxScoreEvalScale(evalItemList[j].evalScale)
    				if(scale)
    				{
    					if(scale[0])
    						maxScale = maxScale + scale[0].doubleValue()
    				}
    				/*Getting the eval scale options of particular eval item*/
    				def evalScaleOptionsService =new EvalScaleOptionsService()
    				def scaleOptionsList = evalScaleOptionsService.listEvalScaleOptionsByEvalScale(evalItemList[j].evalScale.id)
    	    		
    				/**Excluding Particular Eval Item from calculating the average scale
    	    		  *if the item doesn't have any Scale options */
    				if(!scaleOptionsList)
    	    			nOfQuestions = nOfQuestions - (1).doubleValue()
    			}
    			def avgScale = maxScale/nOfQuestions
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
    		
    		if (params.proposalId==null)
    		{
    			GrailsHttpSession gh = getSession()
    	        def proposalInstance = new Proposal()
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
	            /*methhod to get all submitted proposal of the notification*/
				def proposalByNotification=proposalService.getProposalByNotification(proposalInstance.notification.id)
				/*template used to send mail content*/
				String mailMessage=g.render(template:"mailMessage",model:['proposalApplicationInstance':proposalApplicationInstance,'proposalInstance':proposalInstance,'projectTitle':projectTitle,'urlPath':urlPath,'totalProposal':proposalByNotification.size()]); 
				
				for(aprovalAuthorityDetailValue in aprovalAuthorityDetailInstance)
	            {
	            	if(aprovalAuthorityDetailValue?.person?.email)
					{
						if (emailValidator.isValid(aprovalAuthorityDetailValue?.person?.email))
						{
							
					        /*send mail to each reviewer*/
					    	def emailId = notificationsEmailsService.sendMessage(aprovalAuthorityDetailValue?.person?.email,mailMessage,"text/html")
						}
					}
	            }
	            if(proposalInstanceSave) 
	            {
	            	def proposalApprovalAuthorityMapInstanceByNotificationId = proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapByProposalIdAndType(proposalInstance.notification.id,"Notification")
	            	if(proposalApprovalAuthorityMapInstanceByNotificationId)
	            	{
	            	
	            	def proposalApprovalAuthorityMapInstance=proposalApprovalAuthorityMapService.saveProposalApprovalAuthorityMapForProposalApplication(proposalApprovalAuthorityMapInstanceByNotificationId.approvalAuthority,proposalInstance.id,"Proposal")        	
	            	}
	            	else
	            	{
	            		def approvalAuthorityInstanceList = approvalAuthorityService.getDefaultActiveApprovalAuthority(proposalInstance.notification.party.id)
		            	for(approvalAuthorityInstance in approvalAuthorityInstanceList)
		            	{
		            		def proposalApprovalAuthorityMapInstance=proposalApprovalAuthorityMapService.saveProposalApprovalAuthorityMapForProposalApplication(approvalAuthorityInstance,proposalInstance.id,"Proposal")
		            	}
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
    		
        	GrailsHttpSession gh = getSession()
        	gh.removeValue("ProposalId")
    		gh.putValue("Grantor",params.party.id)
    		
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
    		/*Geting attachment type of cv*/
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
    			
    			/*Getting the total score of each proposal*/
    			def evalScoreInstance = evalItemService.getEvalScoreByProposal(proposalInstanceList[i].proposal.id)
    			/*Forming a list*/
    			evalScoreInstanceList.add(evalScoreInstance)
    			
    			def evalItemList = evalItemService.getevalItemList(proposalInstanceList[i].proposal.notification.id)
    			
    			/*Initializing the maximum score as zero*/
    			double maxScale = 0

	    		double nOfQuestions = evalItemList.size()
	    		for(int j=0;j<evalItemList.size();j++)
    			{
    				/*Getting the maximum score each eval Item*/
	    			def scale = evalItemService.getMaxScoreEvalScale(evalItemList[j].evalScale)
    				/*Getting the total of maximum score*/
	    			if(scale)
    				{
    					if(scale[0])
    						maxScale = maxScale + scale[0].doubleValue()
    				}
    				/*Getting the eval scale options of particular eval item*/
    				def evalScaleOptionsService =new EvalScaleOptionsService()
    				def scaleOptionsList = evalScaleOptionsService.listEvalScaleOptionsByEvalScale(evalItemList[j].evalScale.id)
    	    		
    				/**Excluding Particular Eval Item from calculating the average scale
    	    		  *if the item doesn't have any Scale options */
    				if(!scaleOptionsList)
    	    			nOfQuestions = nOfQuestions - (1).doubleValue()
    			}
	    		/**Taking the Average maximum score
	    		 * Calculation -- sum(max.score of each item)/total no of evalItems
	    		 */
	    		def avgScale = maxScale/nOfQuestions
	    		
	    		/*Round the average score*/
	    		def avgScore = Math.round(avgScale)
	    		
    			/*Adding the Score to a List*/
    			maxScaleList.add(avgScore.toString())
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
            SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd")
	        Date date = new Date()
	        
            String currentDate = sdfDestination.format(date)
            
		
	        def notificationInstanceList=notificationService.getAllPublicAndPublishedNotification(currentDate)	
			[notificationInstanceList:notificationInstanceList]
    }
    def notificationDetails = 
    {
    		def notificationInstance=notificationService.getNotificationById(params.id)	
    		def notificationsAttachmentsInstance=notificationsAttachmentsService.getNotificationAttachmentsByNotificationId('Notification',params.id)
    		ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    		[notificationsInstance:notificationInstance,'currencyFormat':currencyFormatter,notificationsAttachmentsInstance:notificationsAttachmentsInstance]
    }
    /*
     * method used to search proposal based on certain criteria
     */
    def proposalSearch =
    {
    		GrailsHttpSession gh=getSession()
    		/*method to get proposal application based on search criteria*/
    		def proposalInstanceList = proposalService.getProposalApplicationSearchListForReviewer(gh.getValue("UserId"),gh.getValue("Party"),params)
    		/*Geting attachment type of cv*/
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
    			
    			/*Getting the total score of each proposal*/
    			def evalScoreInstance = evalItemService.getEvalScoreByProposal(proposalInstanceList[i].proposal.id)
    			/*Forming a list*/
    			evalScoreInstanceList.add(evalScoreInstance)
    			
    			def evalItemList = evalItemService.getevalItemList(proposalInstanceList[i].proposal.notification.id)
    			
    			/*Initializing the maximum score as zero*/
    			double maxScale = 0

	    		double nOfQuestions = evalItemList.size()
	    		for(int j=0;j<evalItemList.size();j++)
    			{
    				/*Getting the maximum score each eval Item*/
	    			def scale = evalItemService.getMaxScoreEvalScale(evalItemList[j].evalScale)
    				/*Getting the total of maximum score*/
	    			if(scale)
    				{
    					if(scale[0])
    						maxScale = maxScale + scale[0].doubleValue()
    				}
    				/*Getting the eval scale options of particular eval item*/
    				def evalScaleOptionsService =new EvalScaleOptionsService()
    				def scaleOptionsList = evalScaleOptionsService.listEvalScaleOptionsByEvalScale(evalItemList[j].evalScale.id)
    	    		
    				/**Excluding Particular Eval Item from calculating the average scale
    	    		  *if the item doesn't have any Scale options */
    				if(!scaleOptionsList)
    	    			nOfQuestions = nOfQuestions - (1).doubleValue()
    			}
	    		/**Taking the Average maximum score
	    		 * Calculation -- sum(max.score of each item)/total no of evalItems
	    		 */
	    		def avgScale = maxScale/nOfQuestions
    			
	    		/*Round the average score*/
	    		def avgScore = Math.round(avgScale)
    			
	    		/*Adding the Score to a List*/
    			maxScaleList.add(avgScore.toString())
    		}
    		render (template:"proposalSearch", model : ['proposalInstanceList' : proposalInstanceList,
    		                                              'evalScoreInstanceList':evalScoreInstanceList,'maxScaleList':maxScaleList,'attachmentsInstanceCVList':attachmentsInstanceCVList])
    		
    
    }
    def mailMessage={}
    
    def proposalReviewDetails =
    {
    	
    	 def proposalInstance = Proposal.get(params.id)
    	 def evalAnswerInstance = proposalService.getEvalAnswerByProposal(proposalInstance.id)
    	 def evalInstance = evalItemService.getEvalAnswerForProposalId(proposalInstance.id)
    	 def evalItemInstance = evalItemService.getevalItemList(proposalInstance.notification.id)
    	 
    	 def evalScoreInstance
    	 def evalScoreInstanceList=[]
    	 def evalList=[]
    	 for(int i=0;i<evalAnswerInstance.size();i++)
    	 {
    		 
    	  evalScoreInstance = proposalService.getEvalScore(proposalInstance.id,evalAnswerInstance[i].person.id)
    	
    	  for(int j=0;j<evalScoreInstance.size();j++)
    	    	{
    			 evalScoreInstanceList = evalScoreInstance[j]
    			 evalList.add(evalScoreInstanceList)
    	    	}
    	 
    	 }
   
    	 ['evalAnswerInstance':evalAnswerInstance,'evalItemInstance':evalItemInstance,'evalScoreInstance':evalScoreInstance,'evalInstance':evalInstance,'evalList':evalList]
    }
    /*
     * method used to award a proposal 
     */
    def award=
    {
    		def projectsInstance
    		def grantAllocationInstance
    		def subProjectGrantAllocationInstance
    		def notificationAmountInstance
    		def data
    		def value
    		def grantAllocationForSubProjectInstanceList = []
    		def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(params.id)
    		def proposalApplicationExtProjectInstance = proposalService.getProposalApplicationExtByFieldAndProposalAppId('TitleOfTheResearchProject_2',proposalApplicationInstance?.id)
    		
            def projectTypeInstance = projectTypeService.getAllProjectType()
            def proposalInstance = proposalService.getProposalById(params.id)
            def notificationAward =proposalService.getAwardListByProposalNotificationId(proposalInstance.notification.id)
            def parentId = notificationAward[0]?.projects?.parentId	
            if(parentId)
            {
            projectsInstance =projectsService.getActiveProjectById(parentId)
	            grantAllocationInstance = GrantAllocation.executeQuery("select SUM(GA.amountAllocated) from GrantAllocation GA where GA.projects.id="+parentId+" group by GA.projects.id")
	            subProjectGrantAllocationInstance = GrantAllocation.executeQuery("select SUM(GA.amountAllocated) from GrantAllocation GA where GA.projects.parent.id="+parentId+" group by GA.projects.parent.id")
	            data = Double.valueOf(grantAllocationInstance[0]) - Double.valueOf(subProjectGrantAllocationInstance[0])
            }	
            else
            {
            	def proposalForNotificationInstance = Proposal.get(params.id)
            	notificationAmountInstance = Notification.executeQuery("select amount from Notification N where N.id = "+proposalForNotificationInstance.notification.id)
            	println"notificationAmountInstance------------------>"+notificationAmountInstance[0]
            	value = notificationAmountInstance[0]
            }
            def attachmentTypeList = attachmentsService.getattachmentTypesByDocType('Project')
    		
            [proposalTitle:proposalApplicationExtProjectInstance.value,proposalApplicationInstance:proposalApplicationInstance,projectTypeInstance:projectTypeInstance,attachmentTypeList:attachmentTypeList,projectInstance:projectsInstance, notificationAmount:value,balanceAmountValue:data]
    }
    /*
     * method used to save and award a project 
     * create a project for the proposal
     * create a project for the notification
     * create a party and site admin if party of the proposal is null
     * save award details
     */
    def saveAward=
    {
    	
    		GrailsHttpSession gh=getSession()
    		
    		def ctx = AH.application.mainContext
     		def springSecurityService=ctx.springSecurityService
     		def proposalInstance = proposalService.getProposalById(params.proposalId)
     		//method to get proposalApplication Instance by proposalId
     		def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(proposalInstance.id)
     		//method to get ProposalApplicationExt by field name and proposal application id 
     		def proposalApplicationExtProjectInstance =proposalService.getProposalApplicationExtByFieldAndProposalAppId('TitleOfTheResearchProject_2',proposalApplicationInstance?.id)
     		//method to get award list by proposal id
     		def proposalAward = proposalService.getAwardListByProposal(proposalInstance.id)
     		def partyInstance
     		def personInstance
     		def projectsInstance
     		def grantAllocationInstance
     		def userMapInstance
     		def awardInstance
     		def savedStatus=true
     		def personDuplicateInstance=[]
     		if(!proposalInstance.party)
     		{
     			personDuplicateInstance=userService.getPersonByUserName(proposalApplicationInstance.email)
     		}
     	    if(proposalAward.size() == 0)
     		{
     	    	projectsInstance = new Projects(params['Projects'])
     	    	if(((projectsService.checkDuplicateProject(projectsInstance)) == 0)&&(personDuplicateInstance.size() == 0))
     	    	{
     	    	//method to get award by notification id 
     			def notificationAward =proposalService.getAwardListByProposalNotificationId(proposalInstance.notification.id)
     			
     			if(notificationAward.size() == 0)
     			{
     				//create a new main project for notification
     				def projectsNotificationInstance = new Projects(params['Projects'])
     				
     				//generate random project code start
    	 			def codeVal =proposalService.generateCode()
    	     		String substring = (proposalInstance.notification.notificationCode).substring(0, 1);
    	 			String projectCodeValue=(substring+codeVal).toUpperCase();
    	 			//generate random code end
     				
     				projectsNotificationInstance.code=projectCodeValue
     				projectsNotificationInstance.name=proposalInstance.notification.notificationTitle
     				
     				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); //please notice the capital M
     				
     				
     				projectsNotificationInstance.projectStartDate=formatter.parse(params.projectStartDate_value)
     				projectsNotificationInstance.projectEndDate=formatter.parse(params.projectEndDate_value)
     				projectsNotificationInstance.activeYesNo='Y'
     				
     				//save new project
     				projectsNotificationInstance=projectsService.saveNewProjects(projectsNotificationInstance)
     				
     				
     				//create grantallocation for notification project
     				def grantAllocationNotificationInstance=new GrantAllocation()
     				grantAllocationNotificationInstance.projects=projectsNotificationInstance
             		
             		//grantAllocationNotificationInstance.granter=proposalInstance.notification.party
             		grantAllocationNotificationInstance.party=proposalInstance.notification.party
             		if(proposalInstance.notification.amount)
             		{
             			grantAllocationNotificationInstance.amountAllocated=proposalInstance.notification.amount
             		}
             		else
             		{
             			grantAllocationNotificationInstance.amountAllocated=0
             		}
     				grantAllocationNotificationInstance.DateOfAllocation = projectsNotificationInstance.projectStartDate
     				grantAllocationNotificationInstance.remarks = params.description
             		//grantAllocationInstance.amountAllocated=new Double(params.totalAmountAllocated)
             		
     				grantAllocationNotificationInstance=grantAllocationService.saveNewGrantAllocation(grantAllocationNotificationInstance)
     		     	 				 				
     				//find the person instance using person id
     				//def personParentInstance=userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
     				def userRoleInstance=userService.getUserRoleByAuthorityAndParty('ROLE_SITEADMIN',proposalInstance.notification.party.id)
     				def personParentInstance=userRoleInstance.user
     				//create project access permision for the site admin
     				projectsService.saveAccessPermissionForprojects(grantAllocationNotificationInstance,projectsNotificationInstance,personParentInstance.username)
     				//assign parent project
     				projectsInstance.parent=projectsNotificationInstance
     			}
     			else
     			{
     				
     					//assign parent project
         				  projectsInstance.parent=notificationAward[0].projects.parent	
                   	
     				
     			}
     		
     			  projectsInstance.activeYesNo='Y'
     		
     			if(!proposalInstance.party)
     			{
    	 			//intializing party instance
    	 			partyInstance=new Party()
    	 			//generate random party code start
    	 			def codeValue =proposalService.generateCode()
    	     		String substring = (proposalApplicationInstance.organisation).substring(0, 1);
    	 			String partyCodeValue=(substring+codeValue).toUpperCase();
    	 			//generate random code end
    	 			partyInstance.code=partyCodeValue
    	 			partyInstance.nameOfTheInstitution=proposalApplicationInstance.organisation
    	 			partyInstance.address=proposalApplicationInstance.organisation
    	 			partyInstance.phone=proposalApplicationInstance.phone	
    	 			partyInstance.email=""
    	 			partyInstance.activeYesNo="Y"
     			
    	 			//intializing person instance
    	 			personInstance= new Person()
    				personInstance.username=proposalApplicationInstance.email
    				personInstance.password = springSecurityService.encodePassword("a")
    	     		personInstance.email=proposalApplicationInstance.email
    				personInstance.userRealName=proposalApplicationInstance.name
    				personInstance.userSurName=proposalApplicationInstance.name
    				
    				personInstance.enabled=false
    				personInstance.accountExpired=false
    				personInstance.accountLocked=false
    				personInstance.passwordExpired=false
    				personInstance=userService.saveNewUser(personInstance,params['Projects'])
    				partyInstance=partyService.saveParty(partyInstance)
    			    if((personInstance?.id==null)||(partyInstance.saveMode != null) && (partyInstance.saveMode.equals("Duplicate")))
    				{
    						
    				}
    				else
    				{
    					//create a new user map
    					userMapInstance =new UserMap()
    					
    					userMapInstance.user=personInstance
    					userMapInstance.party=partyInstance
    					userMapInstance=userService.saveNewUserMapInstance(userMapInstance)
    					
    				}
     			
     			}
     			else
     			{
    	 			partyInstance=proposalInstance.party
    	 			//def userRole = UserRole.find("from UserRole UR where UR.role.authority='ROLE_SITEADMIN' and UR.user.id in (select UM.user.id from UserMap UM where UM.party.id="+proposalInstance.party.id+")")
    	 			//find the site admin of recipient party
    	 			def userRole=userService.getUserRoleByAuthorityAndParty('ROLE_SITEADMIN',proposalInstance?.party?.id)
     				personInstance=userRole.user
     			}
     		
    	 		projectsInstance=projectsService.saveNewProjects(projectsInstance)
    	 		grantAllocationInstance = new GrantAllocation(params['GrantAllocation'])
    	 		//check if project saved or not
    	 		if(projectsInstance.saveMode == "Saved")
    	 		{
    	 			
    	 			grantAllocationInstance.projects=projectsInstance
    	     		
    	     		grantAllocationInstance.granter=proposalInstance.notification.party
    	     		grantAllocationInstance.party=partyInstance
    	     		grantAllocationInstance.DateOfAllocation = projectsInstance.projectStartDate
    	     		grantAllocationInstance.remarks = params.description
    	     		//create a grant allocation
    	     		grantAllocationInstance=grantAllocationService.saveNewGrantAllocation(grantAllocationInstance)
    	     		    	     		
    	 			
    	 		}
    	 		else
    	 		{
    	 			
    	 		}
     		
     		
    	 		if(grantAllocationInstance.id!="null")
    	 		{
    	 			//create project access permision for the site admin
    	 			projectsService.saveAccessPermissionForprojects(grantAllocationInstance,projectsInstance,personInstance.username)
    	 			//save award 
    	 			awardInstance=proposalService.saveAward(proposalInstance,projectsInstance)
    	 			
    	 		}
    	 		else
    	 		{
    	 		
    	 		}
    	 		def downloadedfile = request.getFile("attachmentPath");
     	 		if(params.attachmentType)
    	 		{
    	 			//get attachmentInstance
     	 			def attachmentsTypeInstance=attachmentsService.getattachmentsTypeInstanceByAttachmentId(params.attachmentType)
	     	 		//upload attachments
	        		attachmentsService.uploadAttachments(downloadedfile,'Projects',projectsInstance?.id,attachmentsTypeInstance)
    	 		}
    	 		else
    	 		{
    	 			
    	 		}
        		//mail
        		def mailContent=gmsSettingsService.getGmsSettingsValue("MailContent")
    	    	def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
        		String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/user/userActivation/"
    	    	//mail content
    	    	String mailMessage="";
    	        mailMessage="Dear "+personInstance.userRealName+" "+personInstance.userSurName+", \n \n "+mailContent+".";
    	        mailMessage+="\n \n LoginName    : "+personInstance.username;
    	        mailMessage+="\n Password     : a";
    	        mailMessage+="\n \n \n To activate your account,click on the following link   \t:"+urlPath+personInstance.id+" \n";
    	        mailMessage=mailMessage+" \n\n" 
    	    	mailMessage+=mailFooter
    	    	if((personInstance.id!="null")&&(!proposalInstance.party))
    	    	{
    	    		//send confirmation mail
    	    		def emailId = notificationsEmailsService.sendMessage(personInstance.email,mailMessage,"text/plain")
    	    	}
    	        flash.message = "${message(code: 'default.created.label')}"
    	        redirect(action:'proposalList',id:proposalInstance.notification.id)
     		}
     	    	else
     	    	{
     	    		flash.message = "${message(code: 'default.AlreadyExists.label')}"
     	    		redirect(action:'award',id:proposalInstance.id)
     	    	}
     	    }
     		else
     		{
     			redirect(action:'proposalList',id:proposalInstance.notification.id)
     		}
    		
	    	
    }
    /*
     * method to view awarded proposal 
     */
    def awardedProposal=
    {
    		 //method to get award by proposal id
    		 def awardInstance = proposalService.getAwardByProposal(params.id)
    		 //method to find total amount allocated to the project
    		 def grantAllocationSum = grantAllocationService.getGrantAllocationsSumForProject(awardInstance.projects.id)
    		 //find grantallocation
    		 def grantAllocationInstance =grantAllocationService.getGrantAllocationByProjects(awardInstance.projects.id)
    		 ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    		 if(grantAllocationSum[0]==null)
    			 grantAllocationSum[0]=0
    			
    		 ['awardInstance':awardInstance,grantAllocationSum:grantAllocationSum,'currencyFormat':currencyFormatter,grantAllocationInstance:grantAllocationInstance]
    }
    /*
     * method to check a user with email address already exists or not
     */
    def userNameCheck={
    		 
    		 def proposalApplicationInstance
    		 def personInstance=[]
    		 //create a new person object
    		 def personNew = new Person()
    		 //check if proposal is new or update
    		 if(params.proposalApplication!='')
    		 {
    			 proposalApplicationInstance=proposalService.getProposalApplicationById(params.proposalApplication)
    		 }
    		 //check if email address edited or not
    		 if(proposalApplicationInstance?.email==params.email)
			 {
				 //add a person object with null value
    			 personInstance.add(personNew)
			 }
    		 else
    		 {
    			 //check if a user with email address already exists or not
    			 personInstance=userService.getPersonByUserName(params.email)
    			 if(personInstance.size() == 0)
	    		 {
    				 //add a person object with null value
    				 personInstance.add(personNew)
	    		 }
    		 }
    		 render personInstance as JSON
     }
    def redirectNotificationList = 
    {
    		 redirect(controller:"notification",action:"list")	
    }
    /*
     * Method to Create PreProposal
     */
    def preProposalCreate = 
           {
        	def proposalInstance = new Proposal()
    		def proposalApplicationInstance = new ProposalApplication()
    		def proposalApplicationForm = gmsSettingsService.getGmsSettingsValue("ProposalForm")
            def proposalApplicationPath = gmsSettingsService.getGmsSettingsValue("ProposalApplicationPath")
            proposalInstance.properties = params
        	proposalApplicationInstance.properties = params
                	
    		 def webRootDir
         		if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
         		{
         			webRootDir = servletContext.getRealPath("/")+"WEB-INF/grails-app/views/proposal/"
         		}
            	if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
            	{
            		webRootDir = "grails-app/views/proposal/"
            	}
            	def srcFile = new File(proposalApplicationPath+proposalApplicationForm)
            	def targetFile = new File(webRootDir+proposalApplicationForm)
            	try
            	{
            		org.apache.commons.io.FileUtils.copyFile(srcFile, targetFile)
            	}
            	catch(Exception e)
            	{
            		
            	}
            return [proposalInstance: proposalInstance,proposalApplicationInstance: proposalApplicationInstance,proposalApplicationForm:proposalApplicationForm]
        }
    /*
     * Method to Save PreProposal
     */
        def preProposalSave =
        {
           def proposalService = new ProposalService()
           def proposalInstance = new Proposal(params)
           def proposalApplicationInstance = new ProposalApplication(params)
           GrailsHttpSession gh=getSession()
           def chkPreProposalInstance = proposalService.checkDuplicatePreProposal(params)
    		if(chkPreProposalInstance)
    	    {
    	    	flash.message ="${message(code: 'default.AlreadyExists.label')}"
    	    		redirect(action: "preProposalCreate", id: proposalInstance.id)
    	    }
    		else
    	   {
        	 proposalInstance  = proposalService.savePreProposal(params,gh.getValue("UserId"),gh.getValue("Party"))
            if (proposalInstance)
             {
            	gh.putValue("Proposal",proposalInstance.id)
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'preProposal.label', default: 'PreProposal'), proposalInstance.id])}"
                redirect(action: "preProposalApplication", id: proposalInstance.id)
            }
            else 
            {
                render(view: "create", model: [proposalInstance: proposalInstance])
            }
    	   }
        }
    /*
     * Method to Get PreProposal Form
     */
        def preProposalApplication =
    	{
    		GrailsHttpSession gh=getSession()
    		gh.putValue("Proposal",params.id)
    		def proposalInstance=proposalService.getPrePropsalById(params.id)
    		[proposalApplicationForm:proposalInstance.proposalDocumentationPath]
    	}
    
    def getForm = 
	{
    	GrailsHttpSession gh = getSession()
		def proposalApplicationExtInstance = proposalService.getAllPreProposalAppExtByPreProposalId(gh.getValue("ProposalId"))
		if(proposalApplicationExtInstance)
		{
		   render proposalApplicationExtInstance as JSON
		}
		
	}
    /*
     * Method to Save PreProposal Application Form
     */
    def saveForm = 
	{
		GrailsHttpSession gh=getSession()
		def proposalDetailsSaveStatus=proposalService.saveformDetailsPreProposal(params,gh.getValue("Proposal"))
		redirect(action: "preProposalSubmit",id:gh.getValue("Proposal"))
	}
    /*
     * Method to Submit PreProposal Application Form
     */
	def preProposalSubmit = 
	{
		
	}
	def submitPreProposal =
	{
		GrailsHttpSession gh=getSession()
		def proposalInstance=proposalService.getPrePropsalById(gh.getValue("Proposal"))
		proposalInstance.proposalStatus="Submitted"
		def proposalInstanceSaved=proposalService.savePreProposalInstance(proposalInstance)
		if(proposalInstanceSaved)
		{
			redirect(action: "preProposalList")
		}
		else
		{
			redirect(action: "preProposalList")
		}
	}
    /*
     * Method to get PreProposal List
     */

    def preProposalList = {
        def proposalInstance = Proposal.get(params.id)
	    def proposalApplicationInstanceList = []
    	GrailsHttpSession gh=getSession()
    	def proposalInstanceList = proposalService.getUserInstance(gh.getValue("UserId"))
    	 for(int i=0;i<proposalInstanceList.size();i++)
    	 {
    		 /*method to get proposal title*/
    			def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(proposalInstanceList[i].id)
        		 proposalApplicationInstanceList.add(proposalApplicationInstance)
    	 }
    	
    	 [proposalInstanceList: proposalInstanceList,proposalApplicationInstanceList: proposalApplicationInstanceList]
    }

    /*
     * Method to Edit PreProposal 
     */

    def preProposalEdit = {
        	def proposalService = new ProposalService()
        	def proposalInstance = proposalService.getPreProposalById(new Integer( params.id ))
        	/*method to get proposal Title*/
        	def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(proposalInstance.id)
    		GrailsHttpSession gh=getSession()
          gh.putValue("ProposalId",proposalInstance.id);
            if (!proposalInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposal.label', default: 'PreProposal'), params.id])}"
                redirect(action: "preProposalList")
            }
            else {
            	if(proposalInstance.proposalStatus=="Saved" || proposalInstance.proposalStatus=="NeedMoreInfo" )
            	{
            		return [proposalInstance: proposalInstance,proposalApplicationInstance:proposalApplicationInstance]
            	}
            	else
            	{
            		render(view: "submittedPreProposal", model: [proposalInstance: proposalInstance,proposalApplicationInstance:proposalApplicationInstance,proposalApplicationForm:proposalInstance.proposalDocumentationPath])
            	}
              // render(view: "edit", model: ['preProposalInstance': preProposalInstance,'proposalApplicationForm':preProposalInstance.preProposalForm])
            }
        }
 
    def submittedPreProposal =
	{
		def proposalInstance = Proposal.get(params.id)
		GrailsHttpSession gh=getSession()
		gh.putValue("ProposalId",proposalInstance.id);
		/*method to get proposal Title*/
    	def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(proposalInstance.id)
		[proposalInstance:proposalInstance,proposalApplicationForm:proposalInstance.proposalDocumentationPath,proposalApplicationInstance:proposalApplicationInstance]
	}
    
    /*
     * Method to Update PreProposal 
     */
    def preProposalUpdate =
    {
    	
    	println"params------------->"+params
    	println"parseLong------------->"+Long.parseLong(params.id)
    	def proposalService = new ProposalService()
    	def proposalInstance = proposalService.getPreProposalById(new Integer( params.id ))
    	println"proposalInstance------------->"+proposalInstance		      
    	 if (proposalInstance) 
    	  {
   	   if (params.version)
    		 {
    		   def version = params.version.toLong()
    		    if (proposalInstance.version > version)
    			  {
    			    proposalInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'preProposal.label', default: 'PreProposal')] as Object[], "Another user has updated this PreProposal while you were editing")
    			    render(view: "preProposalEdit", model: [proposalInstance: proposalInstance])
    			        return
    			  }
    		}
           	 def chkPreProposalInstance = proposalService.checkDuplicatePreProposal(params)
	              println"chkPreProposalInstance"+chkPreProposalInstance.proposal.id
	           if(chkPreProposalInstance&&(chkPreProposalInstance.proposal.id!= Long.parseLong(params.id)))
	           {
			        flash.message ="${message(code: 'default.AlreadyExists.label')}"
			        redirect(action: "preProposalList", id: proposalInstance.id)
	            }
	           else
	  {
    			  
    			  proposalInstance = proposalService.updatePreProposal(params,proposalInstance)
    			 if( proposalInstance.saveMode.equals( "Updated")) 
    			  {
    		   		 def proposalApplicationInstance = proposalService.getProposalApplicationByProposalId(proposalInstance.id)
    	    		 println"-------proposalApplicationInstance-----"+proposalApplicationInstance
    				 proposalApplicationInstance.projectTitle=params.projectTitle
    				 proposalApplicationInstance.save()
    			    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'preProposal.label', default: 'PreProposal'), proposalInstance.id])}"
    			     redirect(action: "preProposalApplication", id: proposalInstance.id)
    		      }
    			   else 
    			     {
    			        render(view: "preProposalEdit", model: [proposalInstance: proposalInstance])
    			     }
    		 }
    			        }
    			        else 
    			        {
    			            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposal.label', default: 'PreProposal'), params.id])}"
    			            redirect(action: "preProposalList")
    			        }
    }

    /* 
     * Method to get PreProposal Status
     */
    
     def preProposalReviewalStatus =
     		
     	{
            def proposalInstance = Proposal.get(params.id)
     		GrailsHttpSession gh = getSession()
     		
     		def authorityInstance = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.proposalType = 'PreProposal' and PAM.proposalId ="+proposalInstance.id)
     		
     		def currentApprovalAuthority
     		def proposalApprovalStatus
     		def proposalApprovalDetailStatus
     		def proposalApprovalDetailStatusList=[]
     		def proposalDetailInstance = [];
     		def currentApprovalAuthorityMembers
     		if(authorityInstance)
     		{
     		for(int i=0;i<authorityInstance.size();i++)
     		{
     		  if(new Integer(authorityInstance[i].approveOrder) == (new Integer(proposalInstance.proposalLevel))+1)
     		  {
     			
     			currentApprovalAuthority = ApprovalAuthority.find("from ApprovalAuthority AA where AA.id="+authorityInstance[i].approvalAuthority.id)
     			currentApprovalAuthorityMembers = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AAD where AAD.activeYesNo='Y' and AAD.approvalAuthority.id="+currentApprovalAuthority.id)
     			proposalApprovalStatus = ProposalApproval.findAll("from ProposalApproval PA where PA.approvalAuthorityDetail.approvalAuthority="+currentApprovalAuthority.id+ "and PA.proposalApprovalAuthorityMap="+authorityInstance[i].id)
     			
     				for(int j=0;j<proposalApprovalStatus.size();j++)
     				 {
     					proposalApprovalDetailStatus = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.proposalApproval = "+proposalApprovalStatus[j].id)
     					proposalApprovalDetailStatusList.add(proposalApprovalDetailStatus)
     			     }
     		  }
     		}
     		for(int i=0;i<proposalApprovalDetailStatusList.size();i++)
     		 {
     			if(proposalApprovalDetailStatusList[i].size()!= 0)
     			 {
     				proposalDetailInstance.add(proposalApprovalDetailStatusList[i])
     			 }
     		 }
     		}
     		else
     		{
     		}
     		//def authorityInstance = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AD where AD.person ="+gh.getValue("UserId"))
     		def proposalApprovalDetailInstanceList = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+params.id+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='PreProposal' order by PD.proposalApproval.proposalApprovalAuthorityMap.approveOrder")
     		['currentApprovalAuthorityMembers':currentApprovalAuthorityMembers,'proposalInstance':proposalInstance,'authorityInstance':authorityInstance,'currentApprovalAuthority':currentApprovalAuthority,'proposalDetailInstance':proposalDetailInstance,'proposalApprovalStatus':proposalApprovalStatus,'proposalApprovalDetailInstanceList':proposalApprovalDetailInstanceList]
     	}
     
     
         /*
          * method to submit a fullproposal for review
          */
       
        def fullProposalCreate =
         {
         	 GrailsHttpSession gh=getSession()
     		 def fullProposalSavedList = []
     		 def chkFullProposalInstance
     		 def proposalInstance = proposalService.getProposalById(params.id)
     		 /*Method to get approved preproposals */
     		 def preProposalInstanceList = proposalService.getParentProposalId('PreProposal','Approved',gh.getValue("UserId"))
     		 /*Method to get fullproposals with status Submitted and Approved */
     		 def fullProposalStatus = proposalService.getfullProposalStatus(proposalInstance.id)
     		 /*Method to get fullproposals with status Saved and NeedMoreInfo */
     		 def fullProposalSavedStatus = proposalService.getfullProposalSavedStatus(proposalInstance.id)
     		 /*Method to get proposalApplicationInstance */
     		 def proposalTitleInstance = proposalService.getProposalApplicationByProposal(proposalInstance.id)
     
     		 /*method to check fullproposal already exist for this proposal*/
     	     chkFullProposalInstance = proposalService.getFullProposalByProposal(params.id)
     	        	
      		 ['proposalInstance':proposalInstance,'preProposalInstanceList':preProposalInstanceList,'fullProposalSavedStatus':fullProposalSavedStatus ,
     		  'proposalTitleInstance':proposalTitleInstance ,'fullProposalStatus':fullProposalStatus,'fullProposalList':fullProposalList,'chkFullProposalInstance':chkFullProposalInstance]
         }
        
         /*
          * method to list fullproposals  
          */
         def fullProposalList =
         {
         	
         	GrailsHttpSession gh=getSession()
         	def fullProposalProjectTitleInstanceList=[]
         	def submittedFullProposalInstanceList = []
          	def preProposalList =[]
             /*method to get preproposal of login user with status Approved*/
             def preProposalInstanceList = proposalService.getParentProposalId('PreProposal','Approved',gh.getValue("UserId"))
     	     for(int i=0;i<preProposalInstanceList.size();i++)
     	      {
         	   //getting title of the proposal
     	    	def fullProposalProjectTitileInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal.id="+preProposalInstanceList[i].id) 
         	    fullProposalProjectTitleInstanceList.add(fullProposalProjectTitileInstance)
            	  }
          	['preProposalList': preProposalInstanceList,'fullProposalProjectTitleInstanceList':fullProposalProjectTitleInstanceList]
     
         	      
         }
       
    
  
    /*
     * method to save a fullproposal  
     */
    
   def fullProposalSave =
   {   
    		 
        GrailsHttpSession gh=getSession()
        def dateValue = new Date()
        def proposalInstance = new Proposal(params)
        def personInstance = Person.get(gh.getValue("UserId"))
       
        String fileName
		def attachmentsName='FullProposalForm'
		def gmsSettingsService = new GmsSettingsService()
		def gmsSettingsInstance = gmsSettingsService.getGmsSettings(attachmentsName)
		def webRootDir
	    if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
	    {
	    	webRootDir = gmsSettingsInstance.value
	    }
       if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
       {
       	webRootDir = gmsSettingsInstance.value
       }
	      new File( webRootDir).mkdirs()
	      def downloadedfile = request.getFile("myFile");
	     
	       
	if(!downloadedfile.empty)
	{
		fileName=downloadedfile.getOriginalFilename()
		downloadedfile.transferTo( new File( webRootDir +fileName) )
		proposalInstance.proposalDocumentationPath =fileName
		def preProposalInstance = Proposal.get(params.id)
		def partyInstance = Party.get(gh.getValue("Party"))
		proposalInstance.proposalType = "FullProposal"
		proposalInstance.proposalLevel = new Integer(0)
		proposalInstance.code="PR-"+dateValue.getYear()+dateValue.getMonth()+1+dateValue.getDate()+dateValue.getSeconds()+dateValue.getMinutes()+dateValue.getHours()   
		proposalInstance.proposalSubmitteddate =  new Date();
		proposalInstance.parent = preProposalInstance
		proposalInstance.person = personInstance
		proposalInstance.party = partyInstance
    		 if(params.status=='Saved')
    		 {
  			   proposalInstance.proposalStatus = "Saved"
  			   
			
    		 }
    		 else
    		 {
    			 proposalInstance.proposalStatus = "Submitted" 
    		 }
				 proposalInstance.proposalLevel=new Integer(0)
		    		if(!proposalInstance.hasErrors() && proposalInstance.save()) 
		    		{
		    			def proposalApplicationInstance  = new ProposalApplication()
		    			proposalApplicationInstance.proposal=proposalInstance
		    			proposalApplicationInstance.applicationSubmitDate=new Date()
		    			proposalApplicationInstance.createdBy=fileName
		    			def fullProposalProjectTitleInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal.id = "+preProposalInstance.id)
		    			proposalApplicationInstance.projectTitle =fullProposalProjectTitleInstance.projectTitle
		    			proposalApplicationInstance.save()
		    			flash.message = "${message(code:'default.created.label')}"
		   				 redirect(action:fullProposalCreate , params:['id':params.id,'proposalSavedInstance':proposalInstance.id],)

		    		}
		}
		else
 		{
 			flash.message = "${message(code: 'default.SelectFile.label')}"
 			redirect(action:fullProposalCreate , params:['id':params.id])
  		}
		
	}
    
    /*
     * Method to Update Full Proposal
     */
    def fullProposalUpdate = {
        	
  		   println"params------------->"+params
            def fullProposalInstance = Proposal.get(params.id)
      	    
  		    String fileName
 		    def attachmentsName='FullProposalForm'
			def gmsSettingsService = new GmsSettingsService()
			def gmsSettingsInstance = gmsSettingsService.getGmsSettings(attachmentsName)
     		def webRootDir
     	       if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
     	        {
     	        	webRootDir = gmsSettingsInstance.value
     	       }
     	       if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
     	       {
     	        	webRootDir = gmsSettingsInstance.value
     	       }
     	       
     	      def downloadedfile = request.getFile("myFile");
     	     	       
     		if(!downloadedfile.empty)
     		{
    	    		fileName=downloadedfile.getOriginalFilename()
    	    		downloadedfile.transferTo( new File( webRootDir +fileName) )
    	    		
    	    		fullProposalInstance.proposalDocumentationPath=fileName
    	    	
     		}
     		fullProposalInstance.lockedYN=params.lockedYN
     		
     		if(params.status=="Submitted")
     		{
     			fullProposalInstance.proposalStatus="Submitted"
     		}
     		else
     		{
     			fullProposalInstance.proposalStatus="Saved"
     		}
     	   		if(!fullProposalInstance.hasErrors() && fullProposalInstance.save()) 
		    		{
		    			def proposalApplicationInstance  = ProposalApplication.find("from ProposalApplication PA where PA.proposal.id = "+fullProposalInstance.id)
		    			proposalApplicationInstance.proposal=fullProposalInstance
		    			proposalApplicationInstance.applicationSubmitDate=new Date()
		    			proposalApplicationInstance.createdBy=fullProposalInstance.lockedYN
		    			proposalApplicationInstance.save()
		    			flash.message = "${message(code: 'default.updated.label')}"
		   				redirect(action:fullProposalCreate , params:['id':fullProposalInstance.parent.id])
		    		}
		
     		else
     		{
         	  render(view:'create',model:['fullProposalInstance': fullProposalInstance,'preProposalInstance':preProposalInstance])
     		}
        }


	def download = 
    {
	    def proposalInstance = Proposal.get(params.id)
  		def gmsSettingsService = new GmsSettingsService()
		def attachmentsName='FullProposalForm'
 		def gmsSettingsInstance = gmsSettingsService.getGmsSettings(attachmentsName)
 		def webRootDir
 		
 		String fileName = proposalInstance.proposalDocumentationPath
 		if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
        {
 			webRootDir = gmsSettingsInstance.value
        }
        if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
        {
        	webRootDir = gmsSettingsInstance.value
        }
 		
 		def file = new File(webRootDir+fileName) 
 		def fname=file.getName()
 		
 		if (fname.indexOf(".gif")>-1) {
	         response.setContentType("image/gif");
	      } else if (fname.indexOf(".pdf")>-1) {
	         response.setContentType("application/pdf");
	      } else if (fname.indexOf(".doc")>-1) {
	         response.setContentType("application/msword");
	      }else if (fname.indexOf(".xls")>-1){
	    	 response.setContentType("application/vnd.ms-excel");
	      }else if (fname.indexOf(".xlsx")>-1){
	    	 response.setContentType("application/vnd.ms-excel");
	      }else if(fname.indexOf(".docx")>-1) {
	    	 response.setContentType("application/msword");
	      }else if(fname.indexOf(".ppt")>-1) {
	    	 response.setContentType("application/ppt");
	      }else if(fname.indexOf(".pptx")>-1) {
	    	 response.setContentType("application/ppt");
	      }
 		
 		response.setHeader("Content-disposition", "attachment;fileName=${file.getName()}") 
		response.outputStream << file.newInputStream() // Performing a binary stream copy 
  }

    def fullProposalEdit =
    {
	  def fullProposalInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal.id= "+params.id) 
	   ['fullProposalInstance': fullProposalInstance]
    }
    
   /*
    * Method to get FullProposal Status
    */
   def fullProposalReviewalStatus =
		
	{
    	GrailsHttpSession gh = getSession()
        def preProposalInstance = Proposal.get(params.id)
		def fullProposalInstance = Proposal.find("from Proposal P where P.parent.id ="+preProposalInstance.id)
		def authorityInstance = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.proposalType = 'FullProposal' and PAM.proposalId ="+fullProposalInstance.id)
		def currentApprovalAuthority
		def proposalApprovalStatus
		def currentApprovalAuthorityMembers
		def proposalApprovalDetailStatus
		def proposalApprovalDetailStatusList=[]
		def proposalDetailInstance = [];
		if(authorityInstance)
		{
		for(int i=0;i<authorityInstance.size();i++)
		{
		  if(new Integer(authorityInstance[i].approveOrder) == (new Integer(fullProposalInstance.proposalLevel))+1)
		  {
			currentApprovalAuthority = ApprovalAuthority.find("from ApprovalAuthority AA where AA.id="+authorityInstance[i].approvalAuthority.id)
			currentApprovalAuthorityMembers = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AAD where AAD.activeYesNo='Y' and AAD.approvalAuthority.id="+currentApprovalAuthority.id)
     		proposalApprovalStatus = ProposalApproval.findAll("from ProposalApproval PA where PA.approvalAuthorityDetail.approvalAuthority="+currentApprovalAuthority.id+ "and PA.proposalApprovalAuthorityMap="+authorityInstance[i].id)
			
			for(int j=0;j<proposalApprovalStatus.size();j++)
			{
				proposalApprovalDetailStatus = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.proposalApproval = "+proposalApprovalStatus[j].id)
				proposalApprovalDetailStatusList.add(proposalApprovalDetailStatus)
			}
		  }
		  
		}
		
		for(int i=0;i<proposalApprovalDetailStatusList.size();i++)
		{
			if(proposalApprovalDetailStatusList[i].size()!= 0)
			{
				proposalDetailInstance.add(proposalApprovalDetailStatusList[i])
			}
		}
		}
		else
		{
			
		}
		def proposalApprovalDetailInstanceList = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+fullProposalInstance.id+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='FullProposal' order by PD.proposalApproval.proposalApprovalAuthorityMap.approveOrder")
		['fullProposalInstance':fullProposalInstance,'authorityInstance':authorityInstance,'currentApprovalAuthority':currentApprovalAuthority,'proposalDetailInstance':proposalDetailInstance,'proposalApprovalStatus':proposalApprovalStatus,'proposalApprovalDetailInstanceList':proposalApprovalDetailInstanceList,'currentApprovalAuthorityMembers':currentApprovalAuthorityMembers]
	}
    
    
    
}
