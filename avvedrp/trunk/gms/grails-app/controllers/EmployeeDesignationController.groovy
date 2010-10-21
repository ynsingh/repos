class EmployeeDesignationController 
{
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def index = 
	{
    	redirect(action: "list", params: params)
    }
    def list = 
    {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [employeeDesignationInstanceList: EmployeeDesignation.list(params), 
         employeeDesignationInstanceTotal: EmployeeDesignation.count()]
    }
	def create = 
	{
        def employeeDesignationInstance = new EmployeeDesignation()
        employeeDesignationInstance.properties = params
        def EmployeeDesignationService = new EmployeeDesignationService()
        
        def employeeDesignationInstanceList=EmployeeDesignationService.getemployeeDesignationList()
        return [employeeDesignationInstance: employeeDesignationInstance, 
                employeeDesignationInstanceList:employeeDesignationInstanceList]
    }
	def save = 
	{
        def employeeDesignationInstance = new EmployeeDesignation(params)
        if (employeeDesignationInstance.save(flush: true)) 
	        {
	            flash.message ="${message(code: 'default.created.label')}"
	            redirect(action: "create", id: employeeDesignationInstance.id)
	        }
	        else 
	        {
	            render(view: "create", model: [employeeDesignationInstance: employeeDesignationInstance])
	        }
	 }

    def show = 
	{
        def employeeDesignationInstance = EmployeeDesignation.get(params.id)
        if (!employeeDesignationInstance) 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
        else 
        {
            [employeeDesignationInstance: employeeDesignationInstance]
        }
    }

    def edit = 
    {
        def employeeDesignationInstance = EmployeeDesignation.get(params.id)
        if (!employeeDesignationInstance) 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create")
        }
        else 
        {
            return [employeeDesignationInstance: employeeDesignationInstance]
        }
    }

    def update = 
    {
	    def employeeDesignationInstance = EmployeeDesignation.get(params.id)
	    if (employeeDesignationInstance) 
	    {
	        if (params.version) 
	        {
	            def version = params.version.toLong()
	            if (employeeDesignationInstance.version > version) 
	            { 
	                employeeDesignationInstance.errors.rejectValue("version", "default.optimistic.locking.failure", 
            		[message(code: 'employeeDesignation.label', default: 'EmployeeDesignation')] as Object[], 
            		"Another user has updated this EmployeeDesignation while you were editing")
	                render(view: "edit", model: [employeeDesignationInstance: employeeDesignationInstance])
	                return
	            }
	        }
	        employeeDesignationInstance.properties = params
	        if (!employeeDesignationInstance.hasErrors() && employeeDesignationInstance.save(flush: true)) 
	        {
	            flash.message = "${message(code: 'default.updated.label')}"
	            redirect(action: "create", id: employeeDesignationInstance.id)
	        }
	        else 
	        {
	            render(view: "edit", model: [employeeDesignationInstance: employeeDesignationInstance])
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
        def employeeDesignationInstance = EmployeeDesignation.get(params.id)
        if (employeeDesignationInstance) 
        {
        	try 
        	{
                employeeDesignationInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.label')}"
                redirect(action: "create")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) 
            {
                flash.message ="${message(code: 'default.inuse.label')}"
                redirect(action: "show", id: params.id)
            }
        }
        else 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
    }
}
