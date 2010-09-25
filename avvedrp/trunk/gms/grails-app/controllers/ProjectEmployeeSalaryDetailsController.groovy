class ProjectEmployeeSalaryDetailsController 
{
	def projectEmployeeSalaryDetailsService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = 
    {
        redirect(action: "list", params: params)
    }

    def list = 
    {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [projectEmployeeSalaryDetailsInstanceList: ProjectEmployeeSalaryDetails.list(params), 
         projectEmployeeSalaryDetailsInstanceTotal: ProjectEmployeeSalaryDetails.count()]
    }

    def create = 
    {
        def projectEmployeeSalaryDetailsInstance = new ProjectEmployeeSalaryDetails(params) 
        	projectEmployeeSalaryDetailsInstance.properties = params
        def projectEmployeeInstance = ProjectEmployee.get(params.id)
        def salaryComponentInstance = SalaryComponent.list()
        def projectEmployeeSalaryDetailsInstanceList=projectEmployeeSalaryDetailsService.getSalaryList(params.id)
        // println "projectEmployeeInstance = " + projectEmployeeInstance
        return [projectEmployeeSalaryDetailsInstance: projectEmployeeSalaryDetailsInstance,
                projectEmployeeInstance:projectEmployeeInstance,salaryComponentInstance:salaryComponentInstance,
                projectEmployeeSalaryDetailsInstanceList:projectEmployeeSalaryDetailsInstanceList ]
    }

	def save = 
	{
		def projectEmployeeSalaryDetailsInstance = new ProjectEmployeeSalaryDetails(params)
        projectEmployeeSalaryDetailsInstance.properties = params
        def salaryComponentInstance = SalaryComponent.list()
        def projectEmployeeInstance = ProjectEmployee.get(params.projectEmployee.id)
        def projectEmployeeSalaryDetailsInstanceList=ProjectEmployeeSalaryDetails.list(params)
        // projectEmployeeSalaryDetailsInstance.Status='Y'
        def projectEmployeeSalaryInstanceCheck = 
        	ProjectEmployeeSalaryDetails.findAll("from ProjectEmployeeSalaryDetails PE where PE.projectEmployee.id='"
        			+projectEmployeeInstance.id+"' and PE.salaryComponent.id='"
        			+projectEmployeeSalaryDetailsInstance.salaryComponent.id+"'")
        if(projectEmployeeSalaryInstanceCheck)
        {
        	def projectEmployeeSalaryInstanceCheck1=
        		ProjectEmployeeSalaryDetails.find("from ProjectEmployeeSalaryDetails PE where PE.status='Y' and PE.projectEmployee.id='"
        				+projectEmployeeInstance.id+"' and PE.salaryComponent.id='"
        				+projectEmployeeSalaryDetailsInstance.salaryComponent.id+"'")
        	if(projectEmployeeSalaryInstanceCheck1 )
        	{
        		projectEmployeeSalaryInstanceCheck1.status='N'
        		projectEmployeeSalaryInstanceCheck1.endDate=projectEmployeeSalaryDetailsInstance.WithEffectFrom        			
        		
        	}     	
        	
        }       
        if (projectEmployeeSalaryDetailsInstance.save(flush: true)) 
        {
            flash.message = "New Salary Details created"
            redirect(action: "create", id: projectEmployeeInstance.id)
        }
        else 
        {
            render(view: "create", 
            		model: ['projectEmployeeSalaryDetailsInstance': projectEmployeeSalaryDetailsInstance,
            		        'projectEmployeeInstance':projectEmployeeInstance,
            		        salaryComponentInstance:salaryComponentInstance,
            		        projectEmployeeSalaryDetailsInstanceList: ProjectEmployeeSalaryDetails.list(params)])
        }
    }

    def show = 
    {
        def projectEmployeeSalaryDetailsInstance = ProjectEmployeeSalaryDetails.get(params.id)
        if (!projectEmployeeSalaryDetailsInstance) 
        {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectEmployeeSalaryDetails.label', default: 'ProjectEmployeeSalaryDetails'), params.id])}"
            redirect(action: "list")
        }
        else 
        {
            [projectEmployeeSalaryDetailsInstance: projectEmployeeSalaryDetailsInstance]
        }
    }

    def edit = 
    {
        def projectEmployeeSalaryDetailsInstance = ProjectEmployeeSalaryDetails.get(params.id)
        def projectEmployeeInstance = ProjectEmployee.get(params.projectEmployee.id)
        def salaryComponentInstance = SalaryComponent.list()
        if (!projectEmployeeSalaryDetailsInstance) 
        {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectEmployeeSalaryDetails.label', default: 'ProjectEmployeeSalaryDetails'), params.id])}"
            redirect(action: "list")
        }
        else 
        {
            return [projectEmployeeSalaryDetailsInstance: projectEmployeeSalaryDetailsInstance,projectEmployeeInstance:projectEmployeeInstance,salaryComponentInstance:salaryComponentInstance]
        }
    }

    def update = 
    {
        def projectEmployeeSalaryDetailsInstance = ProjectEmployeeSalaryDetails.get(params.id)
        def projectEmployeeInstance = ProjectEmployee.get(params.projectEmployee.id)
        if (projectEmployeeSalaryDetailsInstance) 
        {
            if (params.version) 
            {
                def version = params.version.toLong()
                if (projectEmployeeSalaryDetailsInstance.version > version) 
                {
                  projectEmployeeSalaryDetailsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'projectEmployeeSalaryDetails.label', default: 'ProjectEmployeeSalaryDetails')] as Object[], "Another user has updated this ProjectEmployeeSalaryDetails while you were editing")
                    render(view: "edit", model: [projectEmployeeSalaryDetailsInstance: projectEmployeeSalaryDetailsInstance])
                    return
                }
            }
            projectEmployeeSalaryDetailsInstance.properties = params
            if (!projectEmployeeSalaryDetailsInstance.hasErrors() && projectEmployeeSalaryDetailsInstance.save(flush: true)) {
                flash.message = "Salary details updated"
                redirect(action: "create", id: projectEmployeeInstance.id)
            }
            else 
            {
                render(view: "edit", model: [projectEmployeeSalaryDetailsInstance: projectEmployeeSalaryDetailsInstance])
            }
        }
        else 
        {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectEmployeeSalaryDetails.label', default: 'ProjectEmployeeSalaryDetails'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = 
    {
        def projectEmployeeSalaryDetailsInstance = ProjectEmployeeSalaryDetails.get(params.id)
        def projectEmployeeInstance = ProjectEmployee.get(params.projectEmployee.id)
        if (projectEmployeeSalaryDetailsInstance) 
        {
            try 
            {
            	//projectEmployeeSalaryDetailsInstance.Status='D'
                projectEmployeeSalaryDetailsInstance.save(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'projectEmployeeSalaryDetails.label', default: 'ProjectEmployeeSalaryDetails'), params.id])}"
                redirect(action: "create", id: projectEmployeeInstance.id)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) 
            {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'projectEmployeeSalaryDetails.label', default: 'ProjectEmployeeSalaryDetails'), params.id])}"
                redirect(action: "create", id: projectEmployeeInstance.id)
            }
        }
        else 
        {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectEmployeeSalaryDetails.label', default: 'ProjectEmployeeSalaryDetails'), params.id])}"
            redirect(action: "create", id: projectEmployeeInstance.id)
        }
    }
}
