class ProjectEmployeeQualificationController 
{
	def projectEmployeeQualificationService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = 
    {
        redirect(action: "list", params: params)
    }

    def list = 
    {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [projectEmployeeQualificationInstanceList: ProjectEmployeeQualification.list(params), 
         projectEmployeeQualificationInstanceTotal: ProjectEmployeeQualification.count()]
    }

    def create = 
    {
		println "____________create"
        def projectEmployeeQualificationInstance = new ProjectEmployeeQualification()
		//projectEmployeeQualificationInstance.properties = params
        def projectEmployeeInstance = ProjectEmployee.get(params.id)
    	println"--------------employeeid"+projectEmployeeInstance.id
        def employeeDesignationInstance = EmployeeDesignation.list()
    	// def projectEmployeeQualificationService = new ProjectEmployeeQualificationService()
        def projectEmployeeQualificationInstanceList =projectEmployeeQualificationService.getQualificationList(params.id)
		//def projectEmployeeInstance = ProjectEmployee.list()
		println "emp id"+params.id
    	println "desig id"+employeeDesignationInstance.id
        return [projectEmployeeQualificationInstance: projectEmployeeQualificationInstance,
                projectEmployeeInstance:projectEmployeeInstance,
                projectEmployeeQualificationInstanceList:projectEmployeeQualificationInstanceList]
    }

    def save = 
    {
		println "save"+params
        def projectEmployeeQualificationInstance = new ProjectEmployeeQualification(params)
		//projectEmployeeQualificationInstance.Status='Y'
        def projectEmployeeInstance = ProjectEmployee.get(params.projectEmployee.id)
        def employeeDesignationInstance = EmployeeDesignation.get(params.id)
        if (projectEmployeeQualificationInstance.save(flush: true)) 
        {
        	flash.message ="New Qualification ${params.examname} is Created" 
            redirect(action: "create", id: projectEmployeeInstance.id)
        }
        else 
        {
        	println "not created"
            render(view: "create", model: ['projectEmployeeQualificationInstance': projectEmployeeQualificationInstance,'projectEmployeeInstance':projectEmployeeInstance,'employeeDesignationInstance':employeeDesignationInstance])
        }
    }

    def show = 
    {
        def projectEmployeeQualificationInstance = ProjectEmployeeQualification.get(params.id)
        if (!projectEmployeeQualificationInstance) 
        {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectEmployeeQualification.label', default: 'ProjectEmployeeQualification'), params.id])}"
            redirect(action: "list")
        }
        else 
        {
            [projectEmployeeQualificationInstance: projectEmployeeQualificationInstance]
        }
    }

    def edit = 
    {
		println"++++++++++++++++++++++++++"+params
		def projectEmployeeInstance = ProjectEmployee.get(params.projectEmployee.id)
		def projectEmployeeQualificationInstance = ProjectEmployeeQualification.get(params.id)
		println"-----params.id----"+params.id
		println "________________projectEmployeeQualificationInstance"+projectEmployeeQualificationInstance
	    if (!projectEmployeeQualificationInstance) 
	    {
	        flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectEmployeeQualification.label', default: 'ProjectEmployeeQualification'), params.id])}"
	        redirect(action: "list")
	    }
        else 
        {
            return [projectEmployeeQualificationInstance: projectEmployeeQualificationInstance,
                    projectEmployeeInstance:projectEmployeeInstance]
        }
    }

    def update = 
    {
     def projectEmployeeQualificationInstance = ProjectEmployeeQualification.get(params.id)
     def projectEmployeeInstance = ProjectEmployee.get(params.projectEmployee.id)
     if (projectEmployeeQualificationInstance) 
     	{
            if (params.version) 
            {
                def version = params.version.toLong()
                if (projectEmployeeQualificationInstance.version > version) 
                {
                    projectEmployeeQualificationInstance.errors.rejectValue("version", "default.optimistic.locking.failure", 
                    		[message(code: 'projectEmployeeQualification.label', default: 'ProjectEmployeeQualification')] 
                    as Object[], "Another user has updated this ProjectEmployeeQualification while you were editing")
                    render(view: "edit", model: [projectEmployeeQualificationInstance: projectEmployeeQualificationInstance])
                    return
                }
            }
            projectEmployeeQualificationInstance.properties = params
            if (!projectEmployeeQualificationInstance.hasErrors() && projectEmployeeQualificationInstance.save(flush: true)) 
            {
                flash.message = "Qualification ${params.examname} Updated" 
                redirect(action: "create", id: projectEmployeeInstance.id)
            }
            else 
            {
                render(view: "edit", model: [projectEmployeeQualificationInstance: projectEmployeeQualificationInstance])
            }
        }
        else 
        {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectEmployeeQualification.label', default: 'ProjectEmployeeQualification'), projectEmployeeQualificationInstance.Examname])}"
            redirect(action: "create")
        }
    }

    def delete = 
    {
		println "--------------------------inside delete"
        def projectEmployeeQualificationInstance = ProjectEmployeeQualification.get(params.id)
        def projectEmployeeInstance =  ProjectEmployee.get(params.projectEmployee.id)
        println"____________"+projectEmployeeQualificationInstance.id
        println"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+projectEmployeeInstance.id
        if (projectEmployeeQualificationInstance) 
        {
            try 
            {
            	//projectEmployeeQualificationInstance.Status='D'
                projectEmployeeQualificationInstance.save(flush: true)
                flash.message = "Qualification ${params.examname} deleted" 
                redirect(action: "create",id:projectEmployeeInstance.id)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) 
            {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'projectEmployeeQualification.label', default: 'ProjectEmployeeQualification'), projectEmployeeQualificationInstance.Examname])}"
                redirect(action: "create", id:projectEmployeeInstance.id)
            }
        }
        else 
        {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectEmployeeQualification.label', default: 'ProjectEmployeeQualification'), projectEmployeeQualificationInstance.Examname])}"
            redirect(action: "create", id:projectEmployeeInstance.id)
        }
    }
}
