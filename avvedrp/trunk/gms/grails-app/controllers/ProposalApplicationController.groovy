import java.util.HashMap
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import grails.converters.JSON
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import org.apache.commons.validator.EmailValidator
class ProposalApplicationController {
	def notificationsEmailsService
	def userService
	def proposalService
	def gmsSettingsService
	def attachmentsService
	def proposalCategoryService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ proposalApplicationInstanceList: ProposalApplication.list( params ) ]
    }

    def show = {
        def proposalApplicationInstance = ProposalApplication.get( params.id )

        if(!proposalApplicationInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ proposalApplicationInstance : proposalApplicationInstance ] }
    }

    def delete = {
        def proposalApplicationInstance = ProposalApplication.get( params.id )
        if(proposalApplicationInstance) {
            proposalApplicationInstance.delete()
            flash.message = "${message(code: 'default.deleted.label')}"
            redirect(action:list)
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
    }

    def edit = {
        def proposalApplicationInstance = ProposalApplication.get( params.id )

        if(!proposalApplicationInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else {
            return [ proposalApplicationInstance : proposalApplicationInstance ]
        }
    }

    def update = {
        def proposalApplicationInstance = ProposalApplication.get( params.id )
        if(proposalApplicationInstance) {
            proposalApplicationInstance.properties = params
            if(!proposalApplicationInstance.hasErrors() && proposalApplicationInstance.save()) {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action:show,id:proposalApplicationInstance.id)
            }
            else {
                render(view:'edit',model:[proposalApplicationInstance:proposalApplicationInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def proposalApplicationInstance = new ProposalApplication()
        proposalApplicationInstance.properties = params
        return ['proposalApplicationInstance':proposalApplicationInstance]
    }

    def save = {
    		
    		saveformDetails()
        def proposalApplicationInstance = new ProposalApplication(params)
        if(!proposalApplicationInstance.hasErrors() && proposalApplicationInstance.save()) {
            flash.message = "${message(code: 'default.created.label')}"
            redirect(action:show,id:proposalApplicationInstance.id)
        }
        else {
            render(view:'create',model:[proposalApplicationInstance:proposalApplicationInstance])
        }
    }
    def applicationForm = {
    		def notificationInstance = Notification.get(params.id)
    		def attachmentsName='ApplicationForm'
    		def gmsSettingsService = new GmsSettingsService()
			def gmsSettingsInstance = gmsSettingsService.getGmsSettings(attachmentsName)
    	
			 def webRootDir
          		if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
          		{
          			webRootDir = servletContext.getRealPath("/")+"WEB-INF/grails-app/views/proposalApplication/"
          		}
 	        	if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
 	        	{
 	        		webRootDir = "grails-app/views/proposalApplication/"
 	        	}
 	        	def srcFile = new File(gmsSettingsInstance.value+notificationInstance.applicationForm)
 	        	def targetFile = new File(webRootDir+notificationInstance.applicationForm)
 	        	try
 	        	{
 	        		org.apache.commons.io.FileUtils.copyFile(srcFile, targetFile)
 	        	}
 	        	catch(Exception e)
 	        	{
 	        		
 	        	}
 	        	
        
    		[notificationInstance:notificationInstance,gmsSettingsInstance:gmsSettingsInstance.value]
    }
    
    
    def saveForm = {
    		
    		GrailsHttpSession gh = getSession()
    		String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/login/auth"
    		def saveProposalApplication = proposalService.saveformDetails(params,gh.getValue("UserId"),gh.getValue("ProposalId"),urlPath)
    		def proposalInstance = Proposal.find("from Proposal P where P.id="+gh.getValue("ProposalId"))
    		if(saveProposalApplication.saveFormStatus == true)
    		{
    		  	if(saveProposalApplication.controllerIdMail == true)
    		{
    			redirect(action:'mailConfirmation',params:[id:proposalInstance.notification.id,proposalId:proposalInstance.id])
    		}
    		else
    		{
    			
    			redirect(action:'confirm',params:[id:proposalInstance.notification.id,proposalId:proposalInstance.id])
    		}
    		}
    		else
    		{
    			String actionName='partyNotificationsList'
    			render(view:'serverNotResponding',model:['id':proposalInstance.notification.id,'proposalId':proposalInstance.id,'actionName':actionName])
    		}
    }
    def getForm = {
    		GrailsHttpSession gh = getSession()
    		def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(gh.getValue("ProposalId"))
    		if(proposalApplicationInstance)
    		{
    			def proposalApplicationExtResult =proposalService.getProposalApplicationExtByProposalApplication(proposalApplicationInstance.id) 
    			render proposalApplicationExtResult as JSON
    		}
    		
    }
    def confirm = 
    {
    	
    }
	def mailConfirmation = 
    {
    	
    }
	def saveProposal ={
			
			redirect(action:'saveProposalAppPart',params:params)
	}
	/*
	 * method used to save proposal and proposalApplication,
	 * save each page details of proposal application
	 * and send mail to PI while first time creating the proposal 
	 */
	def saveProposalAppPart={
			
			def proposalInstance
			def proposalApplicationValueInstance
			def proposalApplicationId
			/*method used to get MailContent and MailFooter*/
			def mailContent=gmsSettingsService.getGmsSettingsValue("MailContent")
			def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
			/*Perform email validations*/
			EmailValidator emailValidator = EmailValidator.getInstance()
			GrailsHttpSession gh = getSession()
			Set keyList=params.keySet()
			Iterator itr=keyList.iterator()
			/*check if the proposal is creating or updating*/
			if(params.proposalApplication.id=="")
			{
				/*method used to save proposal*/
				proposalInstance = proposalService.saveProposal(params,gh.getValue("NotificationId"))
				if(proposalInstance)
				{
					/*method used to save proposalApplication*/
					proposalApplicationValueInstance=proposalService.saveProposalAppliction(proposalInstance,params)
				}
				else
				{
					
				}
				//proposalApplicationValueInstance=proposalService.getProposalApplicationByProposal(proposalInstance.id)
				proposalApplicationId=proposalApplicationValueInstance.id
				gh.putValue("ProposalId",proposalInstance.id)
				String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/login/auth"
				
				if(params.Email_12!="")
					{
					/*Perform email validations*/
						if (emailValidator.isValid(params.Email_12))
						{
							String mailMessage="";
							mailMessage="Dear "+params.FirstName_1+", \n \n ";
							mailMessage+="Thank You for your interest in submitting a proposal"
						    mailMessage+="\n \n Proposal Control Id has been generated for your future reference. \n\n Proposal Control Id : "+proposalApplicationValueInstance.controllerId+"\n";
						    mailMessage+="\n \n Please use the control Id to edit the proposal."
						    mailMessage+=" We look forward to receiving your completed proposal"
						    mailMessage+="\n \n \n To Login please click here:\t "+urlPath;
					        mailMessage=mailMessage+" \n\n" 
					    	mailMessage+=mailFooter
					    	/*Send email to PI*/
					        def emailId = notificationsEmailsService.sendMessage(params.Email_12,mailMessage)
						}
					}
				
			}
			else
			{
				proposalApplicationId=params.proposalApplication.id
				/*check if the page is one then save the details to proposal application*/
				if(params.Page=='1')
				{
					proposalInstance=proposalService.getProposalById(gh.getValue("ProposalId"))
					proposalApplicationValueInstance=proposalService.saveProposalAppliction(proposalInstance,params)
				}
			}
			def proposalApplicationInstance=ProposalApplication.get(proposalApplicationId)
			/*save the page details to ProposalApplicationExt*/
			for ( e in params ) {
				if((e.key !="action")&&(e.key!="controller")&&(e.key!="proposalApplication.id")&&(e.key!="proposalApplication")&&(e.key!="actionName")&&(e.key!="Page")&&(e.key!="_action_"+params.action)&&(e.key!="status"))
				{
					
								
								def proposalApplicationExtInstance = new ProposalApplicationExt()
								def proposalApplicationExtInstanceDb = ProposalApplicationExt.find("from ProposalApplicationExt PE where PE.proposalApplication="+proposalApplicationInstance.id+" and PE.field='"+e.key+"'")
								def order
								def labelName
								if(((e.key).lastIndexOf('_'))>0)
								{
									order=(e.key).substring(((e.key).lastIndexOf('_'))+1)
									labelName=(e.key).substring(0,((e.key).lastIndexOf('_')))
								}
								else
								{
									order=0
								}
								
								if(proposalApplicationExtInstanceDb)
								{
									proposalApplicationExtInstance=proposalApplicationExtInstanceDb
								}
								proposalApplicationExtInstance.proposalApplication=proposalApplicationInstance
								//proposalApplicationExtInstance.label=g.message(code: 'default.'+e.key+'.label')
								proposalApplicationExtInstance.label= 'default.'+labelName+'.label'
								
								proposalApplicationExtInstance.field=e.key
								proposalApplicationExtInstance.value=e.value
								proposalApplicationExtInstance.orderNo=new Integer(order)
								proposalApplicationExtInstance.page=new Integer(params.Page)
								proposalApplicationExtInstance.activeYesNo='Y'
								proposalApplicationExtInstance.save()
							
						
				}
			}
			if(params.status=='update')
			{
				redirect(action:'proposalAppPreview',params:['actionName':params.actionName,'proposalApplication.id':proposalApplicationInstance.id])
			}
			else
			{
				redirect(action:'proposalApplicationPages',params:['actionName':params.actionName,'proposalApplication.id':proposalApplicationInstance.id])
			}
	}
	def proposalApplicationPages={
			
			GrailsHttpSession gh = getSession()
			
			if(params.actionName=='proposalAppPart1PersonalDetails')
			{
				def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
		    	def proposalApplicationInstanceSave=proposalService.updateProposalApplication(proposalApplicationInstance)
				redirect(action:'proposalAppPartInformationOfDepartment',params:['proposalApplication.id':params.proposalApplication.id])
			}
			else if(params.actionName=='proposalAppPartInformationOfDepartment')
			{
				redirect(action:'proposalAppPartThreeInformationRelatingDepartment',params:['proposalApplication.id':params.proposalApplication.id])
			}
			else if(params.actionName=='proposalAppPartThreeInformationRelatingDepartment')
			{
				redirect(action:'proposalAppPartFourAboutResearchProject',params:['proposalApplication.id':params.proposalApplication.id])
			}
			else if(params.actionName=='proposalAppPartFourAboutResearchProject')
			{
				redirect(action:'proposalAppPartFiveDetailProjectReport',params:['proposalApplication.id':params.proposalApplication.id])
			}
			else if(params.actionName=='proposalAppPartFiveDetailProjectReport')
			{
				redirect(action:'proposalAppPartSixUploadDocuments',params:['proposalApplication.id':params.proposalApplication.id])
			}
			else if(params.actionName=='proposalAppPartSixUploadDocuments')
			{
				redirect(action:'proposalAppSummary',params:['proposalApplication.id':params.proposalApplication.id])
			}
			else if(params.actionName=='proposalAppSummary')
			{
				def proposalApplicationInstance=ProposalApplication.get(params.proposalApplication.id)
				
				redirect(action:'proposalAppPreview',params:['proposalApplication.id':params.proposalApplication.id])
			}
	}
	def proposalAppPart1PersonalDetails = 
    {
    	
    	GrailsHttpSession gh = getSession()
			
    	def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(gh.getValue("ProposalId"))
    	def total=2
    	def page=1
    	def proposalCategoryList=proposalCategoryService.getProposalCategoryList()
    	def proposalApplicationExtInstance = proposalService.getProposalApplicationExtByProposalApplicationId(proposalApplicationInstance?.id)
    	 [proposalApplicationInstance:proposalApplicationInstance,
    	 proposalCategoryList:proposalCategoryList,proposalApplicationExtInstance:proposalApplicationExtInstance,total:total,page:page]
    }
	def proposalAppPartInformationOfDepartment = 
    {
    	
    	def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
    	[proposalApplicationInstance:proposalApplicationInstance]
    }
	def proposalAppPartThreeInformationRelatingDepartment =
	{
			
	    	def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
	    	[proposalApplicationInstance:proposalApplicationInstance]
	}
	def proposalAppPartFourAboutResearchProject=
	{
			
	    	def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
	    	[proposalApplicationInstance:proposalApplicationInstance]
	}
	def proposalAppPartFiveDetailProjectReport=
	{
			
			def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
	    	[proposalApplicationInstance:proposalApplicationInstance]
	}
	def proposalAppPartSixUploadDocuments =
	{
			
			
	    	def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
	    	def attachmentsInstanceGetCV=attachmentsService.getAttachmentsByDomainAndType("Proposal","CV",proposalApplicationInstance.proposal.id)
        	def attachmentsInstanceGetDPR=attachmentsService.getAttachmentsByDomainAndType("Proposal","DPR",proposalApplicationInstance.proposal.id)
	    	[proposalApplicationInstance:proposalApplicationInstance,attachmentsInstanceGetCV:attachmentsInstanceGetCV,attachmentsInstanceGetDPR:attachmentsInstanceGetDPR]
	}
	def proposalAppSummary=
	{
			
	    	//def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(params.proposalId)
	    	//println "proposalApplicationInstance "+proposalApplicationInstance
	    	//def proposalApplicationExtInstance = ProposalApplicationExt.findAll("from ProposalApplicationExt PE where PE.proposalApplication="+proposalApplicationInstance?.id)
	    	def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
	    	def proposalApplicationExtInstance = proposalService.getProposalApplicationExtByProposalApplication(proposalApplicationInstance?.id)
	    	def firstName
    		def lastName
    		def organisation
    		def projectTitle
	    	if(proposalApplicationExtInstance)
	    	{
	    		for(proposalApplicationExtValue in proposalApplicationExtInstance)
	    		{
	    			if(proposalApplicationExtValue.field == 'TitleOfTheResearchProject_2')
	    			{
	    				projectTitle=proposalApplicationExtValue.value
	    			}
	    			
	    		}
	    	}
	    	
	    	[proposalApplicationInstance:proposalApplicationInstance,projectTitle:projectTitle]
	}
	def proposalAppPreview=
	{
			
	    	//def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(params.proposalId)
	    	//println "proposalApplicationInstance "+proposalApplicationInstance
	    	//def proposalApplicationExtInstance = ProposalApplicationExt.findAll("from ProposalApplicationExt PE where PE.proposalApplication="+proposalApplicationInstance?.id)
	    	def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
	    	def proposalApplicationExtInstance =proposalService.getProposalApplicationExtByProposalApplication(params.proposalApplication.id)
	    	def attachmentsInstanceGetCV=attachmentsService.getAttachmentsByDomainAndType("Proposal","CV",proposalApplicationInstance.proposal.id)
        	def attachmentsInstanceGetDPR=attachmentsService.getAttachmentsByDomainAndType("Proposal","DPR",proposalApplicationInstance.proposal.id)
	    	
	    	[proposalApplicationInstance:proposalApplicationInstance,proposalApplicationExtInstance:proposalApplicationExtInstance,attachmentsInstanceGetCV:attachmentsInstanceGetCV,attachmentsInstanceGetDPR:attachmentsInstanceGetDPR]
	}
	def pagination ={
			redirect(action:'proposalAppPartThreeInformationRelatingDepartment',params:['proposalApplication.id':params.proposalApplication.id])
	}
	/*
	 * method used to upload CV and DPR documents for proposal
	 */
	def uploadProposalDocuments = {
			def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
		
        def attachmentsInstance = new Attachments()
        
        def attachmentsName='Attachments'
    	def gmsSettingsService = new GmsSettingsService()
    	def gmsSettingsInstance = gmsSettingsService.getGmsSettings(attachmentsName)
    	def attachmentsTypeInstanceCV=attachmentsService.getAttachmentTypesByDocumentTypeAndType("CV","Proposal")
        def attachmentsTypeInstanceDPR=attachmentsService.getAttachmentTypesByDocumentTypeAndType("DPR","Proposal")
       
        def attachmentsInstanceSaveCV
        def attachmentsInstanceSaveDPR
        def webRootDir
        if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
        {
        	webRootDir = gmsSettingsInstance.value
        }
        if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
        {
        	webRootDir = gmsSettingsInstance.value
        }
        	def downloadedCV = request.getFile("UploadCV");
        	def downloadeDPR = request.getFile("UploadDPR");
        	def attachmentsInstanceGetCV=attachmentsService.getAttachmentsByDomainAndType("Proposal",attachmentsTypeInstanceCV?.type,proposalApplicationInstance?.proposal?.id)
    		def attachmentsInstanceGetDPR=attachmentsService.getAttachmentsByDomainAndType("Proposal",attachmentsTypeInstanceDPR?.type,proposalApplicationInstance?.proposal?.id)
        if(!downloadedCV.empty && !downloadeDPR.empty) 
        {
        	String fileNameCV=downloadedCV.getOriginalFilename()
        	String fileNameDPR=downloadeDPR.getOriginalFilename()
        	if((fileNameCV.lastIndexOf(".EXE")==-1)&&(fileNameDPR.lastIndexOf(".EXE")==-1)&&(fileNameCV.lastIndexOf(".exe")==-1)&&(fileNameDPR.lastIndexOf(".exe")==-1))
			{
        		if((fileNameCV.lastIndexOf(".doc")>0)||(fileNameCV.lastIndexOf(".DOC")>0)||
        				(fileNameCV.lastIndexOf(".pdf")>0)||(fileNameCV.lastIndexOf(".PDF")>0))
        		{
        			if((fileNameDPR.lastIndexOf(".doc")>0)||(fileNameDPR.lastIndexOf(".DOC")>0)||
            				(fileNameDPR.lastIndexOf(".pdf")>0)||(fileNameDPR.lastIndexOf(".PDF")>0)||
            				(fileNameDPR.lastIndexOf(".xls")>0)||(fileNameDPR.lastIndexOf(".XLS")>0))
        			{
		        		downloadedCV.transferTo(new File(webRootDir+fileNameCV))
		        		downloadeDPR.transferTo(new File(webRootDir+fileNameDPR))
		        	
		        		attachmentsInstance.domain="Proposal"
		        		attachmentsInstance.domainId=proposalApplicationInstance.proposal.id
		        		
		        		
		        		if(!attachmentsInstanceGetCV)
		        		{
		        			attachmentsInstanceSaveCV=attachmentsService.saveAttachments("Proposal",proposalApplicationInstance.proposal.id,fileNameCV,attachmentsTypeInstanceCV)
		        		}
		        		else
		        		{
		        			attachmentsInstanceGetCV.openedYesNo='N'
		        			attachmentsInstanceGetCV.attachmentPath=fileNameCV
		        			attachmentsInstanceSaveCV=attachmentsService.updateAttachments(attachmentsInstanceGetCV)
		        		}
		        		if(!attachmentsInstanceGetDPR)
		        		{
		        			attachmentsInstanceSaveDPR=attachmentsService.saveAttachments("Proposal",proposalApplicationInstance.proposal.id,fileNameDPR,attachmentsTypeInstanceDPR)
		        		}
		        		else
		        		{
		        			attachmentsInstanceGetDPR.openedYesNo='N'
		        			attachmentsInstanceGetDPR.attachmentPath=fileNameDPR
		        			attachmentsInstanceSaveDPR=attachmentsService.updateAttachments(attachmentsInstanceGetDPR)
		        		}
		        		
		        		if (attachmentsInstanceSaveCV && attachmentsInstanceSaveDPR) {
		                  
		        			flash.message = "${message(code: 'default.Fileuploaded.label')}"
		        				if(params.status=='update')
		        				{
		        					redirect(action:'proposalAppPreview',params:['actionName':params.actionName,'proposalApplication.id':proposalApplicationInstance.id])
		        				}
		        				else
		        				{
		        					redirect(action:'proposalApplicationPages',params:['proposalApplication.id':params.proposalApplication.id,'actionName':'proposalAppPartSixUploadDocuments'])
		        				}
		                }
		                else {
		                	
		                	redirect(action:'proposalAppPartSixUploadDocuments',params:['proposalApplication.id':params.proposalApplication.id])
		                }
        			}
        			else 
                    {
                		
                		flash.message = "${message(code: 'default.InvalidFileFormatForDPR.label')}"	
                    		redirect(action:'proposalAppPartSixUploadDocuments',params:['proposalApplication.id':params.proposalApplication.id])
                    }
				}
        		else 
                {
            		
            		flash.message = "${message(code: 'default.InvalidFileFormatForCV.label')}"		
                		redirect(action:'proposalAppPartSixUploadDocuments',params:['proposalApplication.id':params.proposalApplication.id])
                }
			}
        	else 
            {
        		
        		flash.message = "${message(code: 'default.ExeFile.label')}"	
            		redirect(action:'proposalAppPartSixUploadDocuments',params:['proposalApplication.id':params.proposalApplication.id])
            }
        }
        else if(attachmentsInstanceGetCV && attachmentsInstanceGetDPR)
        {
        	if(params.status=='update')
			{
				redirect(action:'proposalAppPreview',params:['actionName':params.actionName,'proposalApplication.id':proposalApplicationInstance.id])
			}
			else
			{
				redirect(action:'proposalApplicationPages',params:['proposalApplication.id':params.proposalApplication.id,'actionName':'proposalAppPartSixUploadDocuments'])
			}
        }
        else {
        	flash.message = "${message(code: 'default.fileEmpty.label')}"
            redirect(action:'proposalAppPartSixUploadDocuments',params:['proposalApplication.id':params.proposalApplication.id])
         }
	}
	/*
	 * method used to view the uploaded proposal application details(Admin)
	 */
	def proposalApplicationReview =
	{
			def proposalApplicationExtInstance = proposalService.getProposalApplicationExtByProposalApplication(params.id)
			
			def attachmentsInstanceGetCV=attachmentsService.getAttachmentsByDomainAndType("Proposal","CV",proposalApplicationExtInstance[0].proposalApplication.proposal.id)
        	def attachmentsInstanceGetDPR=attachmentsService.getAttachmentsByDomainAndType("Proposal","DPR",proposalApplicationExtInstance[0].proposalApplication.proposal.id)
	    	
			[proposalApplicationExtInstance:proposalApplicationExtInstance,proposalApplicationInstance:proposalApplicationExtInstance.proposalApplication,attachmentsInstanceGetCV:attachmentsInstanceGetCV,attachmentsInstanceGetDPR:attachmentsInstanceGetDPR]
	}
	/*
	 * method used to view the uploaded proposal application details
	 */
	def proposalApplicationDetailsView =
	{
			
			def proposalApplicationExtInstance = proposalService.getProposalApplicationExtByProposalApplication(params.id)
			/*method used to get attachment instance of document CV for the proposal*/
			def attachmentsInstanceGetCV=attachmentsService.getAttachmentsByDomainAndType("Proposal","CV",proposalApplicationExtInstance[0].proposalApplication.proposal.id)
        	/*method used to get attachment instance of document DPR for the proposal*/
			def attachmentsInstanceGetDPR=attachmentsService.getAttachmentsByDomainAndType("Proposal","DPR",proposalApplicationExtInstance[0].proposalApplication.proposal.id)
	    	[proposalApplicationExtInstance:proposalApplicationExtInstance,proposalApplicationInstance:proposalApplicationExtInstance[0].proposalApplication,attachmentsInstanceGetCV:attachmentsInstanceGetCV,attachmentsInstanceGetDPR:attachmentsInstanceGetDPR]
	}
	def revisionOfProposal = 
	{
		
		def proposalApplicationInstance = proposalService.getProposalApplicationById(params.id)
		[proposalApplicationInstance:proposalApplicationInstance]
	}
	def revisionStatus = 
	{
		def proposalApplicationInstance = proposalService.getProposalApplicationById(params.id)
		def proposalInstanceStatus
		if(params.revisionStatus=='Y')
		{
			if(proposalApplicationInstance?.proposal?.proposalVersion==null)
			{
				proposalApplicationInstance.proposal.proposalVersion=new Integer(0)
			}
			proposalApplicationInstance.proposal.lockedYN='Y'
			proposalApplicationInstance.proposal.proposalVersion=(proposalApplicationInstance?.proposal?.proposalVersion)+1
			proposalInstanceStatus=proposalService.updateProposal(proposalApplicationInstance.proposal)
			if(proposalInstanceStatus)
			{
				redirect(action:'proposalAppPreview',params:['proposalApplication.id':proposalApplicationInstance?.id])
			}
			else
			{
				render(view:'revisionStatus',model:[proposalApplicationInstance:proposalApplicationInstance])
			}
		}
		else if(params.revisionStatus=='N')
		{
			redirect(action:'updateProposal',controller:'proposal',params:[id:proposalApplicationInstance.id])
		}
		
		
		[proposalApplicationInstance:proposalApplicationInstance]
	}
	
}
