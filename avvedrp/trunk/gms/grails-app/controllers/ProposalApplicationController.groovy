import java.util.HashMap
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import grails.converters.JSON
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil

class ProposalApplicationController {
	def notificationsEmailsService
	def userService
	def proposalService
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
}
