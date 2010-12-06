import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class ProjectEmployeeController 
{
	def projectEmployeeService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index = 
    {
        redirect(action: "list", params: params)
    }

    def list = 
    {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [projectEmployeeInstanceList: ProjectEmployee.list(params),
        projectEmployeeInstanceTotal: ProjectEmployee.count()]
    }
    
	def create = 
    {
	    println "params"+params
        def projectEmployeeInstance = new ProjectEmployee()
        projectEmployeeInstance.properties = params
        def projectInstance
        if(params.id)
        {
        	projectInstance = Projects.get(params.id)
        }
        //def employeeDesignationInstance = EmployeeDesignation.list()
        def employeeDesignationService = new EmployeeDesignationService()
        def employeeDesignationInstanceList =employeeDesignationService.getemployeeDesignationList()
        def projectEmployeeInstanceList=projectEmployeeService.getProjectEmployeeList(projectInstance)
        return [projectEmployeeInstance: projectEmployeeInstance,
                employeeDesignationInstanceList: employeeDesignationInstanceList,
                projectEmployeeInstanceList:projectEmployeeInstanceList,
                projectInstance:projectInstance]
    }
	/**
	 * Method to save the Project Employee Details
	 */
	 def save = 
	    {
	        def projectEmployeeInstance = new ProjectEmployee(params)
	        def projectInstance = Projects.get(params.projectId)
	        projectEmployeeInstance.projects=projectInstance
	        def projectEmployeeUniqueCheckstatus=projectEmployeeService.projectEmployeeUniqueCheck(projectEmployeeInstance)
	        if(projectEmployeeUniqueCheckstatus)
	        {
	        	flash.message = "${message(code: 'default.AlreadyExists.label')}"
	        }
	        projectEmployeeInstance.Status='Y'
	        //def employeeDesignationInstance = EmployeeDesignation.list()
	        println"params"+params
	        def employeeDesignationService  = new EmployeeDesignationService()
	        def employeeDesignationInstanceList = employeeDesignationService.getemployeeDesignationList()
	        println"params"+params
	        def projectEmployeeInstanceList=projectEmployeeService.getProjectEmployeeList(projectEmployeeInstance)
	        println"projectEmployeeInstance"+projectEmployeeInstance
	        //projectEmployeeInstance.Status="Y"
	        if (projectEmployeeInstance.save(flush: true))
	        {
	            flash.message ="${message(code: 'default.created.label')}" 
	            redirect(action: "create", id:projectInstance.id)
	            	/*model: [projectEmployeeInstance: projectEmployeeInstance,
	                                               employeeDesignationInstance:employeeDesignationInstance,
	                                               projectEmployeeInstanceList:projectEmployeeInstanceList,
	                                               projectInstance:projectInstance])*/
	        }
	        else
	        {
	        	redirect(action: "create", id:projectInstance.id)
	        }
	    }
    def show = 
    {
        def projectEmployeeInstance = ProjectEmployee.get(params.id)
        if (!projectEmployeeInstance) 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
        else 
        {
            [projectEmployeeInstance: projectEmployeeInstance]
        }
    }

	 def edit = 
	    {
	        def projectEmployeeInstance = ProjectEmployee.get(params.id)
	        println"params"+params
	        //def employeeDesignationInstance = EmployeeDesignation.list()
	        def employeeDesignationService = new EmployeeDesignationService()
	        def employeeDesignationInstanceList = employeeDesignationService.getemployeeDesignationList()
	        println"params"+params
	        if (!projectEmployeeInstance) 
	        {
	            flash.message = "${message(code: 'default.notfond.label')}"
	            redirect(action: "list")
	        }
	        else 
	        {
	            return [projectEmployeeInstance: projectEmployeeInstance,
	                    employeeDesignationInstanceList: employeeDesignationInstanceList]
	        }
	    }
    def update = 
    {
        def projectEmployeeInstance = ProjectEmployee.get(params.id)
        if (projectEmployeeInstance) 
        {
            if (params.version)
            {
                def version = params.version.toLong()
                if (projectEmployeeInstance.version > version) 
                {
                    projectEmployeeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", 
                    [message(code: 'projectEmployee.label',default: 'ProjectEmployee')] as Object[], 
                	"Another user has updated this ProjectEmployee while you were editing")
                    render(view: "edit", model: [projectEmployeeInstance: projectEmployeeInstance])
                    return
                }
            }
            projectEmployeeInstance.properties = params
            if (!projectEmployeeInstance.hasErrors() && projectEmployeeInstance.save(flush: true))
            {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action: "create", id: projectEmployeeInstance.id)
            }
            else 
            {
                render(view: "edit", model: [projectEmployeeInstance: projectEmployeeInstance])
            }
        }
        else
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
    }

    def delete = 
    {		
		def projectEmployeeInstance = ProjectEmployee.get(params.id)		
		def projectEmployeeQualificationInstance = ProjectEmployeeQualification.list()
		def projectEmployeeExperienceInstance=ProjectEmployeeExperience.list()
		def projectEmployeeSalaryDetailsInstance=ProjectEmployeeSalaryDetails.list()
		//println projectEmployeeQualificationInstance.projectEmployee.id
        if (projectEmployeeInstance) 
        {         	
        	try 
        	{   
        		projectEmployeeInstance.Status="N" //15-11-2010
                projectEmployeeInstance.save(flush: true)                
                flash.message = "${message(code: 'default.deleted.label')}"
                //redirect(action: "create")
                redirect(action: "create", id: projectEmployeeInstance.projectsId) //15-11-2010
                //redirect(action: "create", id: projectEmployeeInstance.id)
            }
        	catch(org.springframework.dao.DataIntegrityViolationException e)
            {
                flash.message ="${message(code: 'default.inuse.label')}"
                //redirect(action: "create", id: params.id)
                redirect(action: "create", id: projectEmployeeInstance.projectsId) //15-11-2010
            }
        }
        else 
        {
            flash.message = "${message(code: 'default.notfond.label')}"            
            //redirect(action: "create", id: projectEmployeeInstance.id)
            redirect(action: "create", id: projectEmployeeInstance.projectsId) //15-11-2010
        }
    }
	
	def addemp = 
	{
		def grantAllocationService = new GrantAllocationService()
		GrailsHttpSession gh=getSession()
		def dataSecurityService = new DataSecurityService()
		def projectsList = dataSecurityService.getProjectsForLoginUser(gh.getValue("PartyID"))
		def grandAllocationList = []
		def grantAllocationInstance
		for(int i=0;i<projectsList.size();i++)
		{
			grantAllocationInstance = grantAllocationService.getGrantAllocationByProjects(projectsList[i].id)
			grandAllocationList.add(grantAllocationInstance)
		}
		
		[ grandAllocationList: grandAllocationList]
	}
	
}
