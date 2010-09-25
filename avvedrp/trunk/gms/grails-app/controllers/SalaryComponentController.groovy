class SalaryComponentController 
{
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = 
    {
        redirect(action: "list", params: params)
    }

    def list = 
    {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [salaryComponentInstanceList: SalaryComponent.list(params), 
         salaryComponentInstanceTotal: SalaryComponent.count()]
    }

    def create = 
    {
        def salaryComponentInstance = new SalaryComponent()
        salaryComponentInstance.properties = params
        def salaryComponentService = new SalaryComponentService()
        def salaryComponentInstanceList=salaryComponentService.getsalaryComponentList()
        
        return [salaryComponentInstance: salaryComponentInstance, 
                salaryComponentInstanceList:salaryComponentInstanceList]
    }

    def save = 
    {
        def salaryComponentInstance = new SalaryComponent(params)
        if (salaryComponentInstance.save(flush: true)) 
        {
            flash.message = "Created New Salary Component ${params.name}"
            redirect(action: "create", id: salaryComponentInstance.id)
        }
        else 
        {
            render(view: "create", model: [salaryComponentInstance: salaryComponentInstance])
        }
    }

    def show = 
    {
        def salaryComponentInstance = SalaryComponent.get(params.id)
        if (!salaryComponentInstance) 
        {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'salaryComponent.label', default: 'SalaryComponent'), params.id])}"
            redirect(action: "list")
        }
        else 
        {
            [salaryComponentInstance: salaryComponentInstance]
        }
    }

    def edit = 
    {
        def salaryComponentInstance = SalaryComponent.get(params.id)
        if (!salaryComponentInstance) 
        {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'salaryComponent.label', default: 'SalaryComponent'), params.id])}"
            redirect(action: "list")
        }
        else 
        {
            return [salaryComponentInstance: salaryComponentInstance]
        }
    }

    def update = 
    {
        def salaryComponentInstance = SalaryComponent.get(params.id)
        if (salaryComponentInstance) 
        {
            if (params.version) 
            {
                def version = params.version.toLong()
                if (salaryComponentInstance.version > version) 
                {
                    
                    salaryComponentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", 
                    		[message(code: 'salaryComponent.label', default: 'SalaryComponent')] as Object[], 
                    		"Another user has updated this SalaryComponent while you were editing")
                    render(view: "edit", model: [salaryComponentInstance: salaryComponentInstance])
                    return
                }
            }
            salaryComponentInstance.properties = params
            if (!salaryComponentInstance.hasErrors() && salaryComponentInstance.save(flush: true)) 
            {
                flash.message = "Updated Salary Component ${params.name}"
                redirect(action: "create", id: salaryComponentInstance.id)
            }
            else 
            {
                render(view: "edit", model: [salaryComponentInstance: salaryComponentInstance])
            }
        }
        else 
        {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'salaryComponent.label', default: 'SalaryComponent'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = 
    {
        def salaryComponentInstance = SalaryComponent.get(params.id)
        if (salaryComponentInstance) 
        {
            try 
            {
                salaryComponentInstance.delete(flush: true)
                flash.message = "Deleted Salary Component ${params.name}"
                redirect(action: "create")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) 
            {
                flash.message = "Can't delete the component ${params.name}"
                redirect(action: "create", id: params.id)
            }
        }
        else 
        {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'salaryComponent.label', default: 'SalaryComponent'), params.id])}"
            redirect(action: "create")
        }
    }
}
