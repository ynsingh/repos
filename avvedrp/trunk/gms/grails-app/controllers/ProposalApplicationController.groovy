import java.util.HashMap
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import grails.converters.JSON
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil

class ProposalApplicationController {
    
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
            flash.message = "ProposalApplication not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ proposalApplicationInstance : proposalApplicationInstance ] }
    }

    def delete = {
        def proposalApplicationInstance = ProposalApplication.get( params.id )
        if(proposalApplicationInstance) {
            proposalApplicationInstance.delete()
            flash.message = "ProposalApplication ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "ProposalApplication not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def proposalApplicationInstance = ProposalApplication.get( params.id )

        if(!proposalApplicationInstance) {
            flash.message = "ProposalApplication not found with id ${params.id}"
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
                flash.message = "ProposalApplication ${params.id} updated"
                redirect(action:show,id:proposalApplicationInstance.id)
            }
            else {
                render(view:'edit',model:[proposalApplicationInstance:proposalApplicationInstance])
            }
        }
        else {
            flash.message = "ProposalApplication not found with id ${params.id}"
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
            flash.message = "ProposalApplication ${proposalApplicationInstance.id} created"
            redirect(action:show,id:proposalApplicationInstance.id)
        }
        else {
            render(view:'create',model:[proposalApplicationInstance:proposalApplicationInstance])
        }
    }
    def applicationForm = {
    		println  "-------"+params 
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
 	        	org.apache.commons.io.FileUtils.copyFile(srcFile, targetFile)
 	        	
          
			//	def proposalInstance = Proposal.get(new Integer(params.id))
    	//	GrailsHttpSession gh=getSession()
    	//	gh.putValue("AppForm",proposalInstance.notification.applicationForm)
    		//[proposalInstance:proposalInstance]
    		[notificationInstance:notificationInstance,gmsSettingsInstance:gmsSettingsInstance.value]
    }
    def saveformDetails = {
    		
    		//saving the form details into the master and extention tables.
    		//extention table values are stored as fields ,values
    		def proposalApplicationInstance=new ProposalApplication()
    		Set keyList=params.keySet()
    		
    		GrailsHttpSession gh=getSession()
    		println "proposalApplicationInstance"+gh.getValue("ProposalId")
    		Iterator itr=keyList.iterator()
    		def proposalApplicationInstanceDb = ProposalApplication.find("from ProposalApplication PA where PA.proposal="+gh.getValue("ProposalId"))
    		if(proposalApplicationInstanceDb)
    		{
    			proposalApplicationInstance=proposalApplicationInstanceDb
    		}
    		proposalApplicationInstance.proposal=Proposal.get(getSession().ProposalId)
    		proposalApplicationInstance.applicationSubmitDate=new Date()
    		proposalApplicationInstance.save()
    		
    		while(itr.hasNext())
    		{
    			Object obj=itr.next()
    			if(obj.toString() !="action")
    			{
    			if(obj.toString()!="controller")
    			{
    			def proposalApplicationExtInstance=new ProposalApplicationExt()
    			def proposalApplicationExtInstanceDb = ProposalApplicationExt.find("from ProposalApplicationExt PE where PE.proposalApplication="+proposalApplicationInstance.id+" and PE.field='"+obj+"'") 
    			
    			if(proposalApplicationExtInstanceDb)
    			{
    				proposalApplicationExtInstance=proposalApplicationExtInstanceDb
    			}
    			proposalApplicationExtInstance.proposalApplication=proposalApplicationInstance
    			proposalApplicationExtInstance.field=obj.toString()
    			proposalApplicationExtInstance.value=params.get(obj).toString()
    			proposalApplicationExtInstance.save()
    			
    			println "Field="+obj.toString()
    			println "Value="+params.get(obj).toString()}}
    		}
    }
    
    def saveForm = {
    		println("submitForm")
    		println "params-"+params
    		saveformDetails()
    		GrailsHttpSession gh = getSession()
    		def proposalInstance = Proposal.find("from Proposal P where P.id="+gh.getValue("ProposalId"))
    		//redirect url: '..\confirm.gsp'
    		//redirect uri: '/proposalApplication/confirm.gsp'  
    		redirect(action:'confirm',params:[id:proposalInstance.notification.id,proposalId:proposalInstance.id])
    }
    def getForm = {
    		GrailsHttpSession gh = getSession()
    		println "Session="+gh.getValue("ProposalId")
    		def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal="+gh.getValue("ProposalId"))
    		println "proposalApplicationInstance get"+proposalApplicationInstance
    		if(proposalApplicationInstance)
    		{
    			def proposalApplicationExtResult = ProposalApplicationExt.findAll("from ProposalApplicationExt PE where PE.proposalApplication="+proposalApplicationInstance.id)
    			println "proposalApplicationExtResult"+proposalApplicationExtResult
    			render proposalApplicationExtResult as JSON
    		}
    		
    }
    def confirm = 
    {
    	//redirect(controller:'proposal',action:'create',id:params.id)	
    }
}
