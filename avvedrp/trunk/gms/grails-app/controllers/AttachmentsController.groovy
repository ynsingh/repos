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
        return [attachmentsInstance: attachmentsInstance,
                grantAllocationInstanceList:grantAllocationInstanceList,
                attachmentsInstanceList:attachmentsInstanceList,projects:params.id]
    }

    def save = {
        //def attachmentsInstance = new Attachments(params)
        def attachmentsInstance = new Attachments()
        attachmentsInstance.domainId=params.projects
        println "-=-=-=params-=-=-=-"+params.projects
        def attachmentsName='Attachments'
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
        	def downloadedfile = request.getFile("attachmentPath");
        if(!downloadedfile.empty) {
        	String fileName=downloadedfile.getOriginalFilename()
        	if((fileName.lastIndexOf(".EXE")==-1)&&(fileName.lastIndexOf(".exe")==-1))
			{
        		downloadedfile.transferTo(new File(webRootDir+fileName))
        		println "params.achmentType.id"+params.attachmentType.id
        		attachmentsInstance.domain="Projects"
        		attachmentsInstance.domainId=params.projects
        		attachmentsInstance.attachmentType=AttachmentType.get(params.attachmentType.id)
        		attachmentsInstance.attachmentPath=fileName
        		if (attachmentsInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.Fileuploaded.label')}"
                    
                }
                else {
                	println "error ="
                	//redirect(action: "create", id: attachmentsInstance.domainId)//, model: [attachmentsInstance: attachmentsInstance])
                }
			}
        	else 
            {
            	flash.message = "${message(code: 'default.ExeFile.label')}"	
            	
            }
        }
        else {
            flash.message = "${message(code: 'default.fileEmpty.label')}"
            
         }
        redirect(action: "create", id: attachmentsInstance.domainId)
        
    }

    def show = {
        def attachmentsInstance = Attachments.get(params.id)
        if (!attachmentsInstance) {
            flash.message = "${message(code: 'default.FilenotFound.label')}"
            redirect(action: "list")
        }
        else {
            [attachmentsInstance: attachmentsInstance]
        }
    }

    def edit = {
        def attachmentsInstance = Attachments.get(params.id)
        if (!attachmentsInstance) {
            flash.message = "${message(code: 'default.FilenotFound.label')}"
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
                    
                    attachmentsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", 
                    		[message(code: 'attachments.label', default: 'Attachments')] as Object[], 
                    		"Another user has updated this Attachments while you were editing")
                    render(view: "edit", model: [attachmentsInstance: attachmentsInstance])
                    return
                }
            }
            attachmentsInstance.properties = params
            if (!attachmentsInstance.hasErrors() && attachmentsInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action: "show", id: attachmentsInstance.id)
            }
            else {
                render(view: "edit", model: [attachmentsInstance: attachmentsInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.FilenotFound.label')}"
            redirect(action: "list")
        }
    }

    def delete = {
        def attachmentsInstance = Attachments.get(params.id)
        if (attachmentsInstance) {
            try {
                attachmentsInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.label')}"
                redirect(action: "create",id:params.domainId)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.Fileinuse.label')}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.FilenotFound.label')}"
            redirect(action: "list")
        }
    }
	
	def downloadAttachments = {
		def attachmentsInstance = Attachments.get( params.id )
		def gmsSettingsService = new GmsSettingsService()
 		println"++++++++++++++id+++++++++++"+params
 		def attachmentsName='Attachments'
 		def gmsSettingsInstance = gmsSettingsService.getGmsSettings(attachmentsName)
 		def webRootDir
 		String fileName = attachmentsInstance.attachmentPath
 		attachmentsInstance.openedYesNo='Y'
 		attachmentsInstance.save()
 		 if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
        {
 			webRootDir = gmsSettingsInstance.value
        }
        if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
        {
        	webRootDir = gmsSettingsInstance.value
        }
 		println"++++++++++++++++++filename+++++++++"+fileName
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
}
