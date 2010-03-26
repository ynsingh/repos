import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class ProjectDepartmentMapController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ projectDepartmentMapInstanceList: ProjectDepartmentMap.list( params ) ]
    }

    def show = {
        def projectDepartmentMapInstance = ProjectDepartmentMap.get( params.id )

        if(!projectDepartmentMapInstance) {
            flash.message = "ProjectDepartmentMap not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ projectDepartmentMapInstance : projectDepartmentMapInstance ] }
    }

    def delete = {
        def projectDepartmentMapInstance = ProjectDepartmentMap.get( params.id )
        if(projectDepartmentMapInstance) {
            projectDepartmentMapInstance.delete()
            flash.message = "ProjectDepartmentMap ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "ProjectDepartmentMap not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def projectDepartmentMapInstance = ProjectDepartmentMap.get( params.id )

        if(!projectDepartmentMapInstance) {
            flash.message = "ProjectDepartmentMap not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ projectDepartmentMapInstance : projectDepartmentMapInstance ]
        }
    }

    def update = {
        def projectDepartmentMapInstance = ProjectDepartmentMap.get( params.id )
        if(projectDepartmentMapInstance) {
            projectDepartmentMapInstance.properties = params
            if(!projectDepartmentMapInstance.hasErrors() && projectDepartmentMapInstance.save()) {
                flash.message = "ProjectDepartmentMap ${params.id} updated"
                redirect(action:show,id:projectDepartmentMapInstance.id)
            }
            else {
                render(view:'edit',model:[projectDepartmentMapInstance:projectDepartmentMapInstance])
            }
        }
        else {
            flash.message = "ProjectDepartmentMap not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def projectDepartmentMapInstance = new ProjectDepartmentMap()
        projectDepartmentMapInstance.properties = params
       	GrailsHttpSession gh=getSession()
        def dataSecurityService = new DataSecurityService()
        def projectDepartmentMapService = new ProjectDepartmentMapService()
    	println "===== PartyID====== " +gh.getValue("PartyID")
        def projectsList = dataSecurityService.getProjectsForLoginUser(gh.getValue("PartyID"));
        def partyDepartmentList = projectDepartmentMapService.getPartyDepartmentForUser(gh.getValue("PartyID"));
        return ['projectDepartmentMapInstance':projectDepartmentMapInstance,'projectsList':projectsList,'partyDepartmentList':partyDepartmentList]
    }

    def save = {
        def projectDepartmentMapInstance = new ProjectDepartmentMap(params)
        if(!projectDepartmentMapInstance.hasErrors() && projectDepartmentMapInstance.save()) {
            flash.message = "ProjectDepartmentMap ${projectDepartmentMapInstance.id} created"
            redirect(action:show,id:projectDepartmentMapInstance.id)
        }
        else {
            render(view:'create',model:[projectDepartmentMapInstance:projectDepartmentMapInstance])
        }
    }
}
