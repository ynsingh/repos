import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.SimpleDateFormat;
import java.text.DateFormat;
class UtilizationController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
		GrailsHttpSession gh = getSession()
		println "partyid"+gh.getValue("Party")
		def utilizationInstanceListbyparty
		utilizationInstanceListbyparty = Utilization.findAll("from Utilization U where U.projects.id in (select GA.projects.id from GrantAllocation GA where GA.granter='"+gh.getValue("Party")+"')")
		println"...params....."+params	
        [ utilizationInstanceList: utilizationInstanceListbyparty ]
    }

    def show = {
        def utilizationInstance = Utilization.get( params.id )

        if(!utilizationInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ utilizationInstance : utilizationInstance ] }
    }

    def delete = {
    		println";;;;;;params;;;;"+params
        def utilizationInstance = Utilization.get( params.id )
        if(utilizationInstance) {
            utilizationInstance.delete()
            flash.message = "${message(code: 'default.deleted.label')}"
            redirect(action:list)
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
    }

    def edit = {
        def utilizationInstance = Utilization.get( params.id )

        if(!utilizationInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
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
                flash.message ="${message(code: 'default.updated.label')}"
                redirect(action:show,id:utilizationInstance.id)
            }
            else {
                render(view:'edit',model:[utilizationInstance:utilizationInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def utilizationInstance = new Utilization()
        println "projectid=="+params.id
        def projectInstance = Projects.get(params.id)
        def utilizationInstanceCheck = Utilization.findAll("from Utilization U where U.projects.id="+projectInstance.id)
        utilizationInstance.properties = params
        
        [projectInstance:projectInstance,utilizationInstance:utilizationInstance]
      }

    def save = {
		GrailsHttpSession gh=getSession()
		println "uti"+params
		def projectInstance = Projects.get(params.id)
        def utilizationInstance = new Utilization(params)
        utilizationInstance.projects= Projects.get(params.id)
        utilizationInstance.submittedDate=new Date()
		utilizationInstance.grantee=Party.get(gh.getValue("Party"))
		// utilizationInstance.grantPeriod=GrantPeriod.get(params.grantPeriod.id)
		println"....stesting..."
		
		println"utilizationInstance"+utilizationInstance
			if(!utilizationInstance.hasErrors() && utilizationInstance.save()) 
			{
				
				flash.message = "${message(code: 'default.Utilizationcertificatesubmitted.label')}"
				redirect(action:'create',id:params.id)
			}
			else {
				
            render(view:'create',model:[projectInstance:projectInstance])
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
