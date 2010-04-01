import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class UtilizationController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
		GrailsHttpSession gh = getSession()
		println "partyid"+gh.getValue("Party")
		def utilizationInstanceList = Utilization.findAll("from Utilization U where U.projects IN (from GrantAllocation GA where GA.granter="+gh.getValue("Party")+")") 
	
      if(!params.max) params.max = 10
        [ utilizationInstanceList: utilizationInstanceList ]
    }

    def show = {
        def utilizationInstance = Utilization.get( params.id )

        if(!utilizationInstance) {
            flash.message = "Utilization not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ utilizationInstance : utilizationInstance ] }
    }

    def delete = {
        def utilizationInstance = Utilization.get( params.id )
        if(utilizationInstance) {
            utilizationInstance.delete()
            flash.message = "Utilization ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Utilization not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def utilizationInstance = Utilization.get( params.id )

        if(!utilizationInstance) {
            flash.message = "Utilization not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ utilizationInstance : utilizationInstance ]
        }
    }

    def update = {
        def utilizationInstance = Utilization.get( params.id )
        if(utilizationInstance) {
            utilizationInstance.properties = params
            if(!utilizationInstance.hasErrors() && utilizationInstance.save()) {
                flash.message = "Utilization ${params.id} updated"
                redirect(action:show,id:utilizationInstance.id)
            }
            else {
                render(view:'edit',model:[utilizationInstance:utilizationInstance])
            }
        }
        else {
            flash.message = "Utilization not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def utilizationInstance = new Utilization()
        println "projectid=="+params.id
        utilizationInstance.properties = params
        return ['utilizationInstance':utilizationInstance]
    }

    def save = {
		GrailsHttpSession gh=getSession()
        def utilizationInstance = new Utilization(params)
        println "fileeeeeeeee"+utilizationInstance.attachmentPath
        
        def downloadFile=request.getFile("attachmentName")
        String  fileName=downloadFile.getOriginalFilename()
        def projectInstance = Projects.get(params.projectsId)
		utilizationInstance.attachmentPath=fileName
       utilizationInstance.projects= Projects.get(params.projectsId)
        utilizationInstance.uploadedDate=new Date()
		utilizationInstance.grantee=Party.get(gh.getValue("Party"))
        new File("grails-app/views/appForm/").mkdirs()
        downloadFile.transferTo(new File("grails-app/views/appForm/"+File.separatorChar+fileName))
        if(!utilizationInstance.hasErrors() && utilizationInstance.save()) {
            flash.message = "Utilization certificate uploaded"
            redirect(action:'create',id:params.projectsId)
        }
        else {
            render(view:'create',model:[utilizationInstance:utilizationInstance])
        }
    }
    def download ={
    		def utilizationInstance=Utilization.get(params.id)
    		println "utilizationid="+utilizationInstance.attachmentPath
    		String fileName = utilizationInstance.attachmentPath
    		def file = new File("grails-app/views/appForm/"+fileName)
    		response.setContentType("application/octet-stream") 
    		response.setHeader("Content-disposition", "attachment;fileName=${file.getName()}") 
    		response.outputStream << file.newInputStream()
    }
}
