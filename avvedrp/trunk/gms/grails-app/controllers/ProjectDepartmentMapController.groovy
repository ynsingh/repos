import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class ProjectDepartmentMapController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
    	GrailsHttpSession gh = getSession()
    	
    	def projectDepartmentMapService=new ProjectDepartmentMapService()
    	def projectDepartmentMapInstanceList = projectDepartmentMapService.getProjectDepartmentMapList(gh.getValue("Party"))
        
        [ projectDepartmentMapInstanceList: projectDepartmentMapInstanceList ]
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
            flash.message = "ProjectDepartmentMap  deleted"
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
                flash.message = "ProjectDepartmentMap is updated"
                redirect(action:list,id:projectDepartmentMapInstance.id)
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
       	//Adding help page into session
       	gh.putValue("Help","Project_Dept_Map.htm")
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
            flash.message = "ProjectDepartmentMap created for ${projectDepartmentMapInstance.projects.code}"
            redirect(action:list,id:projectDepartmentMapInstance.id)
        }
        else {
            render(view:'create',model:[projectDepartmentMapInstance:projectDepartmentMapInstance])
        }
    }
}
