import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
class AttachmentsController {
	def projectsService
	def grantAllocationService
	def attachmentsService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		
		GrailsHttpSession gh=getSession()
		def grantAllocationInstance = attachmentsService.getGrantAllocationForGrantor(gh.getValue("Party"))
		println "-----"+params.id
		def attachmentsInstanceList = attachmentsService.getProjectUploadedAttachments(params.id)
		println "attachmentsInstanceListdemo=="+attachmentsInstanceList.domainId
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [attachmentsInstanceList: attachmentsInstanceList, attachmentsInstanceTotal: Attachments.count()]
    }

    def create = {
        def attachmentsInstance = new Attachments()
        GrailsHttpSession gh=getSession()
        println "params id"+params
       // attachmentsInstance.properties = params
       	def grantAllocationInstance = attachmentsService.getGrantAllocationForParty(gh.getValue("Party"))
		def attachmentsInstanceList = attachmentsService.getProjectUploadedAttachments(params.id)
		println "attachmentsInstanceList "+attachmentsInstanceList
        List<GrantAllocation> grantAllocationInstanceList 	
        
    	    	
    	try{
          grantAllocationInstanceList = grantAllocationService.getAll()
	
    	}
    	catch(Exception e)
    	{
    		
    	}
        return [attachmentsInstance: attachmentsInstance,grantAllocationInstanceList:grantAllocationInstanceList,attachmentsInstanceList:attachmentsInstanceList,projects:params.id]
    }

    def save = {
        //def attachmentsInstance = new Attachments(params)
        def attachmentsInstance = new Attachments()
        println "-=-=-=params-=-=-=-"+params.projects
        def webRootDir
        if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
        {
        	webRootDir = servletContext.getRealPath("/")+"WEB-INF/grails-app/views/"
        }
        if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
        {
        	webRootDir = "grails-app/views/"
        }
        	def downloadedfile = request.getFile("attachmentPath");
        if(!downloadedfile.empty) {
        	String fileName=downloadedfile.getOriginalFilename()
        	downloadedfile.transferTo(new File(webRootDir+"appForm/" + File.separatorChar + fileName))
        	println "params.achmentType.id"+params.attachmentType.id
        	attachmentsInstance.domain="Projects"
        	attachmentsInstance.domainId=params.projects
        	attachmentsInstance.attachmentType=AttachmentType.get(params.attachmentType.id)
        	attachmentsInstance.attachmentPath=fileName
        }
        else {
            flash.message = 'file cannot be empty'
            redirect(action:'create')
         }

        if (attachmentsInstance.save(flush: true)) {
            flash.message = "File uploaded successfully"
            redirect(action: "create", id: attachmentsInstance.domainId)
        }
        else {
        	println "error ="
            render(view: "create")//, model: [attachmentsInstance: attachmentsInstance])
        }
    }

    def show = {
        def attachmentsInstance = Attachments.get(params.id)
        if (!attachmentsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'attachments.label', default: 'Attachments'), params.id])}"
            redirect(action: "list")
        }
        else {
            [attachmentsInstance: attachmentsInstance]
        }
    }

    def edit = {
        def attachmentsInstance = Attachments.get(params.id)
        if (!attachmentsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'attachments.label', default: 'Attachments'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [attachmentsInstance: attachmentsInstance]
        }
    }

    def update = {
        def attachmentsInstance = Attachments.get(params.id)
        if (attachmentsInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (attachmentsInstance.version > version) {
                    
                    attachmentsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'attachments.label', default: 'Attachments')] as Object[], "Another user has updated this Attachments while you were editing")
                    render(view: "edit", model: [attachmentsInstance: attachmentsInstance])
                    return
                }
            }
            attachmentsInstance.properties = params
            if (!attachmentsInstance.hasErrors() && attachmentsInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'attachments.label', default: 'Attachments'), attachmentsInstance.id])}"
                redirect(action: "show", id: attachmentsInstance.id)
            }
            else {
                render(view: "edit", model: [attachmentsInstance: attachmentsInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'attachments.label', default: 'Attachments'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def attachmentsInstance = Attachments.get(params.id)
        if (attachmentsInstance) {
            try {
                attachmentsInstance.delete(flush: true)
                flash.message = "Document deleted successfully"
                redirect(action: "create",id:params.domainId)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'attachments.label', default: 'Attachments'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'attachments.label', default: 'Attachments'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def downloadAttachments = {
		def attachmentsInstance = Attachments.get( params.id )
 		println"++++++++++++++id+++++++++++"+params
 		def webRootDir
 		String fileName = attachmentsInstance.attachmentPath
 		attachmentsInstance.openedYesNo='Y'
 		attachmentsInstance.save()
 		 if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
        {
        	webRootDir = servletContext.getRealPath("/")+"WEB-INF/grails-app/views/"
        }
        if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
        {
        	webRootDir = "grails-app/views/"
        }
 		println"++++++++++++++++++filename+++++++++"+fileName
 		def file = new File(webRootDir+"appForm/"+fileName)     
 		response.setContentType("application/octet-stream") 
 		response.setHeader("Content-disposition", "attachment;fileName=${file.getName()}") 
 		 
 		response.outputStream << file.newInputStream() // Performing a binary stream copy 
 		
	}
}
