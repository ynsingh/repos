class ProjectEmployeeExperienceController 
{
 def projectEmployeeExperienceService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = 
    {
        redirect(action: "list", params: params)
    }

    def list = 
    {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [projectEmployeeExperienceInstanceList: ProjectEmployeeExperience.list(params), 
         projectEmployeeExperienceInstanceTotal: ProjectEmployeeExperience.count()]
    }

    def create = 
    {
	 	def projectEmployeeExperienceInstance = new ProjectEmployeeExperience()
        // projectEmployeeExperienceInstance.properties = params
        def projectEmployeeInstance = ProjectEmployee.get(params.id)
        def employeeDesignationInstance = EmployeeDesignation.list()
        println "emp id"+params.id
        def projectEmployeeExperienceInstanceList=projectEmployeeExperienceService.getExperienceList(params.id)
        def dur=projectEmployeeExperienceInstanceList.relievingDate-projectEmployeeExperienceInstanceList.joiningDate
        println "duration"+dur
        return [projectEmployeeExperienceInstance: projectEmployeeExperienceInstance,
                projectEmployeeInstance:projectEmployeeInstance,employeeDesignationInstance:employeeDesignationInstance,
                projectEmployeeExperienceInstanceList: projectEmployeeExperienceInstanceList]
    }

    def save = 
    {
        def projectEmployeeExperienceInstance = new ProjectEmployeeExperience(params)
        def projectEmployeeInstance = ProjectEmployee.get(params.projectEmployee.id)
        //projectEmployeeExperienceInstance.Status='Y'
         if (projectEmployeeExperienceInstance.save(flush: true)) 
	     {
	       flash.message = "${message(code: 'default.created.label')}"
	       redirect(action: "create", id: projectEmployeeInstance.id)
	     }
        else 
        {
            render(view: "create", model: ['projectEmployeeExperienceInstance': projectEmployeeExperienceInstance,
                                           'projectEmployeeInstance':projectEmployeeInstance])
        }
    }

    def show = 
    {
        def projectEmployeeExperienceInstance = ProjectEmployeeExperience.get(params.id)
        if (!projectEmployeeExperienceInstance) 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
        else 
        {
            [projectEmployeeExperienceInstance: projectEmployeeExperienceInstance]
        }
    }

    def edit = 
    {
        def projectEmployeeExperienceInstance = ProjectEmployeeExperience.get(params.id)
        def projectEmployeeInstance = ProjectEmployee.get(params.projectEmployee.id)
        if (!projectEmployeeExperienceInstance) 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
        else 
        {
            return [projectEmployeeExperienceInstance: projectEmployeeExperienceInstance,
                    projectEmployeeInstance:projectEmployeeInstance]
        }
    }

    def update = 
    {
        def projectEmployeeExperienceInstance = ProjectEmployeeExperience.get(params.id)
        def projectEmployeeInstance = ProjectEmployee.get(params.projectEmployee.id)

        if (projectEmployeeExperienceInstance) 
        {
            if (params.version) 
            {
                def version = params.version.toLong()
                if (projectEmployeeExperienceInstance.version > version) 
                {
                    
                    projectEmployeeExperienceInstance.errors.rejectValue("version", "default.optimistic.locking.failure", 
                    		[message(code: 'projectEmployeeExperience.label', default: 'ProjectEmployeeExperience')] 
                    		 as Object[], "Another user has updated this ProjectEmployeeExperience while you were editing")
                    render(view: "edit", model: [projectEmployeeExperienceInstance: projectEmployeeExperienceInstance])
                    return
                }
            }
            projectEmployeeExperienceInstance.properties = params
            if (!projectEmployeeExperienceInstance.hasErrors() && projectEmployeeExperienceInstance.save(flush: true)) 
            {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action: "create", id: projectEmployeeInstance.id)
            }
            else 
            {
                render(view: "edit", model: [projectEmployeeExperienceInstance: projectEmployeeExperienceInstance])
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
        def projectEmployeeExperienceInstance = ProjectEmployeeExperience.get(params.id)
        def projectEmployeeInstance = ProjectEmployee.get(params.projectEmployee.id)

        if (projectEmployeeExperienceInstance) 
        {
            try 
            {
            	//projectEmployeeExperienceInstance.Status='D'
                projectEmployeeExperienceInstance.save(flush: true)
                flash.message = "${message(code: 'default.deleted.label')}"
                redirect(action: "create",id:projectEmployeeInstance.id)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) 
            {
                flash.message = "${message(code: 'default.inuse.label')}"
                redirect(action: "create", id: params.id)
            }
        }
        else 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create")
        }
    }
}
