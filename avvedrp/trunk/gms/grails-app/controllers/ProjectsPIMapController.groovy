import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class ProjectsPIMapController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ projectsPIMapInstanceList: ProjectsPIMap.list( params ) ]
    }

    def show = {
        def projectsPIMapInstance = ProjectsPIMap.get( params.id )

        if(!projectsPIMapInstance) {
            flash.message = "ProjectsPIMap not found with id ${params.id}"
            redirect(action:create)
        }
        else { return [ projectsPIMapInstance : projectsPIMapInstance ] }
    }

    def delete = {
        def projectsPIMapInstance = ProjectsPIMap.get( params.id )
        if(projectsPIMapInstance) {
            projectsPIMapInstance.delete()
            flash.message = "Deleted Successfully"
            redirect(action:create)
        }
        else {
            flash.message = "ProjectsPIMap not found with id ${params.id}"
            redirect(action:create)
        }
    }

    def edit = {
        def projectsPIMapInstance = ProjectsPIMap.get( params.id )

        if(!projectsPIMapInstance) {
            flash.message = "ProjectsPIMap not found with id ${params.id}"
            redirect(action:create)
        }
        else {
            return [ projectsPIMapInstance : projectsPIMapInstance ]
        }
    }

    def update = {
        def projectsPIMapInstance = ProjectsPIMap.get( params.id )
        if(projectsPIMapInstance) {
            projectsPIMapInstance.properties = params
            if(!projectsPIMapInstance.hasErrors() && projectsPIMapInstance.save()) {
                flash.message = "Updated Successfully"
                redirect(action:create,id:projectsPIMapInstance.id)
            }
            else {
                render(view:'edit',model:[projectsPIMapInstance:projectsPIMapInstance])
            }
        }
        else {
            flash.message = "ProjectsPIMap not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def projectsPIMapInstance = new ProjectsPIMap()
        projectsPIMapInstance.properties = params
        GrailsHttpSession gh=getSession()
        def dataSecurityService = new DataSecurityService()
        //get all projects for the logined institution
        def projectsList = dataSecurityService.getProjectsForLoginUser(gh.getValue("PartyID"));
        def projectsPIMapInstanceList=[]
        for(int i=0;i<projectsList.size();i++)
        {
        	println "projectsList[i].id"+ projectsList[i].id
        	def projectsPIMap = dataSecurityService.getProjectsPIMapForLoginUser(projectsList[i].id);
	        println "PIMAp"+ projectsPIMap
			for(int j=0;j<projectsPIMap.size();j++)
			{
				projectsPIMapInstanceList.add(projectsPIMap[j])
				println projectsPIMapInstanceList
			}
        }
        return ['projectsPIMapInstance':projectsPIMapInstance,
                'projectsPIMapInstanceList': projectsPIMapInstanceList,
                'projectsList':projectsList]
    }

    def save = {
        def projectsPIMapInstance = new ProjectsPIMap(params)
        if(!projectsPIMapInstance.hasErrors() && projectsPIMapInstance.save()) {
            flash.message = "Created successfully"
            redirect(action:create,id:projectsPIMapInstance.id)
        }
        else {
            render(view:'create',model:[projectsPIMapInstance:projectsPIMapInstance])
        }
    }
}