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
			println "saveProposal "+params
			redirect(action:'saveProposalAppPart',params:params)
	}
	def saveProposalAppPart={
			println "params "+params
			def proposalInstance
			def proposalApplicationValueInstance
			def proposalApplicationId
			def mailContent=gmsSettingsService.getGmsSettingsValue("MailContent")
			def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
			EmailValidator emailValidator = EmailValidator.getInstance()
			GrailsHttpSession gh = getSession()
			Set keyList=params.keySet()
			Iterator itr=keyList.iterator()
			if(params.proposalApplication.id=="")
			{
				proposalInstance = proposalService.saveProposal(params,gh.getValue("Grantor"))
				proposalApplicationValueInstance=proposalService.getProposalApplicationByProposal(proposalInstance.id)
				proposalApplicationId=proposalApplicationValueInstance.id
				gh.putValue("ProposalId",proposalInstance.id)
				String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/login/auth"
				
				if(params.Email_12!="")
					{
						if (emailValidator.isValid(params.Email_12))
						{
							String mailMessage="";
							mailMessage="Dear "+params.FirstName_1+", \n \n ";
						    mailMessage+="\n \n Proposal Controll Id has generated \nProposal Controll Id    : "+proposalApplicationValueInstance.controllerId+"\n";
						    mailMessage+="\n \n Please use the controller Id to edit the proposal"
						    mailMessage+="\n \n \n To Login   \t "+urlPath;
					        mailMessage=mailMessage+" \n\n" 
					    	mailMessage+=mailFooter
					        def emailId = notificationsEmailsService.sendMessage(params.Email_12,mailMessage)
						}
					}
				
			}
			else
			{
				proposalApplicationId=params.proposalApplication.id
			}
			def proposalApplicationInstance=ProposalApplication.get(proposalApplicationId)
			
			for ( e in params ) {
				if((e.key !="action")&&(e.key!="controller")&&(e.key!="proposalApplication.id")&&(e.key!="proposalApplication")&&(e.key!="actionName")&&(e.key!="Page")&&(e.key!="_action_"+params.action)&&(e.key!="status"))
				{
					
								println "e -"+e.key+" - "+e.value
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
								println "order "+order
								println "labelName "+labelName
								if(proposalApplicationExtInstanceDb)
								{
									proposalApplicationExtInstance=proposalApplicationExtInstanceDb
								}
								proposalApplicationExtInstance.proposalApplication=proposalApplicationInstance
								//proposalApplicationExtInstance.label=g.message(code: 'default.'+e.key+'.label')
								proposalApplicationExtInstance.label= 'default.'+labelName+'.label'
								println "proposalApplicationExtInstance.label "+proposalApplicationExtInstance.label
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
			println "params pages "+params
			GrailsHttpSession gh = getSession()
			println "gh.getValue"+gh.getValue("ProposalId")
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
				proposalApplicationInstance.saveAll=true
				if(proposalApplicationInstance.save())
				{
					println "saved******************************"+proposalApplicationInstance.saveAll
				}
				redirect(action:'proposalAppPreview',params:['proposalApplication.id':params.proposalApplication.id])
			}
	}
	def proposalAppPart1PersonalDetails = 
    {
    	println params
    	GrailsHttpSession gh = getSession()
			
    	def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(gh.getValue("ProposalId"))
    	def total=2
    	def page=1
    	def proposalCategoryList=proposalCategoryService.getProposalCategoryList()
    	def proposalApplicationExtInstance = ProposalApplicationExt.findAll("from ProposalApplicationExt PE where PE.proposalApplication="+proposalApplicationInstance?.id)
    	[proposalApplicationInstance:proposalApplicationInstance,
    	 proposalCategoryList:proposalCategoryList,proposalApplicationExtInstance:proposalApplicationExtInstance,total:total,page:page]
    }
	def proposalAppPartInformationOfDepartment = 
    {
    	println params
    	def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
    	[proposalApplicationInstance:proposalApplicationInstance]
    }
	def proposalAppPartThreeInformationRelatingDepartment =
	{
			println params
	    	def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
	    	[proposalApplicationInstance:proposalApplicationInstance]
	}
	def proposalAppPartFourAboutResearchProject=
	{
			println params
	    	def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
	    	[proposalApplicationInstance:proposalApplicationInstance]
	}
	def proposalAppPartFiveDetailProjectReport=
	{
			println params
			def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
	    	[proposalApplicationInstance:proposalApplicationInstance]
	}
	def proposalAppPartSixUploadDocuments =
	{
			println params
			
	    	def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
	    	def attachmentsInstanceGetCV=attachmentsService.getAttachmentsByDomainAndType("Proposal","CV",proposalApplicationInstance.proposal.id)
        	def attachmentsInstanceGetDPR=attachmentsService.getAttachmentsByDomainAndType("Proposal","DPR",proposalApplicationInstance.proposal.id)
	    	[proposalApplicationInstance:proposalApplicationInstance,attachmentsInstanceGetCV:attachmentsInstanceGetCV,attachmentsInstanceGetDPR:attachmentsInstanceGetDPR]
	}
	def proposalAppSummary=
	{
			println params
	    	//def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(params.proposalId)
	    	//println "proposalApplicationInstance "+proposalApplicationInstance
	    	//def proposalApplicationExtInstance = ProposalApplicationExt.findAll("from ProposalApplicationExt PE where PE.proposalApplication="+proposalApplicationInstance?.id)
	    	def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
	    	[proposalApplicationInstance:proposalApplicationInstance]
	}
	def proposalAppPreview=
	{
			println params
	    	//def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(params.proposalId)
	    	//println "proposalApplicationInstance "+proposalApplicationInstance
	    	//def proposalApplicationExtInstance = ProposalApplicationExt.findAll("from ProposalApplicationExt PE where PE.proposalApplication="+proposalApplicationInstance?.id)
	    	def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
	    	def proposalApplicationExtInstance = ProposalApplicationExt.findAll("from ProposalApplicationExt P where P.proposalApplication="+params.proposalApplication.id+" order by page,orderNo")
	    	def attachmentsInstanceGetCV=attachmentsService.getAttachmentsByDomainAndType("Proposal","CV",proposalApplicationInstance.proposal.id)
        	def attachmentsInstanceGetDPR=attachmentsService.getAttachmentsByDomainAndType("Proposal","DPR",proposalApplicationInstance.proposal.id)
	    	println "proposalApplicationExtInstance "+proposalApplicationExtInstance.orderNo+" - "+proposalApplicationExtInstance.page
	    	[proposalApplicationInstance:proposalApplicationInstance,proposalApplicationExtInstance:proposalApplicationExtInstance,attachmentsInstanceGetCV:attachmentsInstanceGetCV,attachmentsInstanceGetDPR:attachmentsInstanceGetDPR]
	}
	def pagination ={
			redirect(action:'proposalAppPartThreeInformationRelatingDepartment',params:['proposalApplication.id':params.proposalApplication.id])
	}
	def uploadProposalDocuments = {
			def proposalApplicationInstance = ProposalApplication.get(params.proposalApplication.id)
		
        def attachmentsInstance = new Attachments()
        
        def attachmentsName='Attachments'
    	def gmsSettingsService = new GmsSettingsService()
    	def gmsSettingsInstance = gmsSettingsService.getGmsSettings(attachmentsName)
    	def attachmentsTypeInstanceCV=attachmentsService.getAttachmentTypesByDocumentTypeAndType("CV","Proposal")
        def attachmentsTypeInstanceDPR=attachmentsService.getAttachmentTypesByDocumentTypeAndType("DPR","Proposal")
        println "attachmentsTypeInstanceCV "+attachmentsTypeInstanceCV+" attachmentsTypeInstanceDPR "+attachmentsTypeInstanceDPR
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
        			attachmentsInstanceSaveDPR=attachmentsService.updateAttachments(attachmentsInstanceGetCV)
        		}
        		
        		if (attachmentsInstanceSaveCV && attachmentsInstanceSaveDPR) {
                   println "file saved"
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
                	println "error ="
                	println "file not saved"
                	redirect(action:'proposalAppPartSixUploadDocuments',params:['proposalApplication.id':params.proposalApplication.id])
                }
			}
        	else 
            {
        		println "file not saved"
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
        	println "file not saved"
        	flash.message = "${message(code: 'default.fileEmpty.label')}"
            redirect(action:'proposalAppPartSixUploadDocuments',params:['proposalApplication.id':params.proposalApplication.id])
         }
	}
	def proposalApplicationReview =
	{
			println params
			def proposalApplicationExtInstance = proposalService.getProposalApplicationExtByProposalApplication(params.id)
			def attachmentsInstanceGetCV=attachmentsService.getAttachmentsByDomainAndType("Proposal","CV",proposalApplicationExtInstance.proposalApplication.proposal.id)
        	def attachmentsInstanceGetDPR=attachmentsService.getAttachmentsByDomainAndType("Proposal","DPR",proposalApplicationExtInstance.proposalApplication.proposal.id)
	    	println "proposalApplicationExtInstance "+proposalApplicationExtInstance
			[proposalApplicationExtInstance:proposalApplicationExtInstance,proposalApplicationInstance:proposalApplicationExtInstance.proposalApplication,attachmentsInstanceGetCV:attachmentsInstanceGetCV,attachmentsInstanceGetDPR:attachmentsInstanceGetDPR]
	}
	
}
