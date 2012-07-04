import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class EmployeeDesignationController 
{
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def index = 
	{
    	
    }
	/**
	 * Method to Perform the create action
	 */
	def create = 
	{
		GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","Employee_Designation.htm")//putting help pages in session
        def employeeDesignationInstance = new EmployeeDesignation()        
        def employeeDesignationService = new EmployeeDesignationService()  
        
        employeeDesignationInstance.properties = params
        /*Get all active Employee designation*/
        def employeeDesignationInstanceList=employeeDesignationService.getemployeeDesignationList()        
        
        return [employeeDesignationInstance: employeeDesignationInstance, 
                employeeDesignationInstanceList:employeeDesignationInstanceList]
    }
	
	/**
	 * Method to perform the save action
	 */
	def save = 
	{
		def employeeDesignationService = new  EmployeeDesignationService()
		def employeeDesignationInstance = new EmployeeDesignation(params)
		def chkEmployeeDesignationInstance = employeeDesignationService.checkDuplicateDesignation(params)
		if(chkEmployeeDesignationInstance)
	    {
	    	flash.message ="${message(code: 'default.AlreadyExists.label')}"
	    		redirect(action: "create", id: employeeDesignationInstance.id)
	    }
	    else
	    {
	    	/*Save employee designation details*/
			employeeDesignationInstance = employeeDesignationService.saveEmployeeDesignation(params)
			
		    if (employeeDesignationInstance) 
	        {
	            flash.message ="${message(code: 'default.created.label')}"
	            redirect(action: "create", id: employeeDesignationInstance.id)
	        }
	        else 
	        {
	            render(view: "create", model: [employeeDesignationInstance: employeeDesignationInstance])
	        }	
	    }
		
	 }

	/**
	 * Method for performing the edit action
	 */
    def edit = 
    {
	     def employeeDesignationService = new EmployeeDesignationService()
         /*Get Employee designation details by id*/
	     def employeeDesignationInstance = employeeDesignationService.getEmployeeDesignationById(new String(params.id ))
         /*Check whether the record exits or not*/
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

	/**
	 * Method for performing the update action
	 */
    def update = 
    {
        def employeeDesignationService = new EmployeeDesignationService()
        def projectEmployeeService = new ProjectEmployeeService()
        
        def employeeDesignationInstance = employeeDesignationService.getEmployeeDesignationById(new String(params.id ))	    
        def chkEmployeeDesignationInstance = employeeDesignationService.checkDuplicateDesignation(params)
		if(chkEmployeeDesignationInstance)
	    {
			if((chkEmployeeDesignationInstance.id).equals(employeeDesignationInstance.id))
			{
					employeeDesignationInstance = employeeDesignationService.updateEmployeeDesignation(params,employeeDesignationInstance)
    				if(employeeDesignationInstance.saveMode.equals( "Updated"))
			           	{
	        				/*Shows a message if the details are updated*/
	        				flash.message = "${message(code: 'default.updated.label')}"
			        		redirect(action: "create", id: employeeDesignationInstance.id)
			        	}
	        
			}
			else
			{
				flash.message ="${message(code: 'default.AlreadyExists.label')}"
				redirect(action: "edit", id: employeeDesignationInstance.id)
			}
	    }
	    else
	    {
	        if (employeeDesignationInstance) 
		    {
	        	/*Get the employee details based on the designation*/
	        	def projectEmployeeInstance = projectEmployeeService.getProjectEmployeeBasedOnDesignation(employeeDesignationInstance)
	        	
	        	/* Check whether any employee exists of this particular designation */
	        	if (!projectEmployeeInstance)
	        	{
		        	/* Update the employee designation details*/
	        		employeeDesignationInstance = employeeDesignationService.updateEmployeeDesignation(params,employeeDesignationInstance)
			        
	        		if(employeeDesignationInstance.saveMode !=null)
			        {
			        	/* Check whether the employee designation details are updated */
	        			if(employeeDesignationInstance.saveMode.equals( "Updated"))
			           	{
	        				/*Shows a message if the details are updated*/
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
	        	
		    }
	    }
    }

	/**
	 * Method for performing the delete action
	 */
    def delete = 
    {
        def employeeDesignationService = new EmployeeDesignationService()
        def projectEmployeeService = new ProjectEmployeeService()
        
        /*Get employee designation details by id*/
        def employeeDesignationInstance = employeeDesignationService.getEmployeeDesignationById(new String (params.id ))
        if (employeeDesignationInstance)        	
        {	
        	/*Get the employee details based on the designation*/
        	def projectEmployeeInstance = projectEmployeeService.getProjectEmployeeBasedOnDesignation(employeeDesignationInstance)
        	
        	/* Check whether any employee exists of this particular designation */
        	if (!projectEmployeeInstance)
        	{
        		try 
        		{  
        			/* Delete the employee designation*/
        			employeeDesignationService.deleteEmployeeDesignation(employeeDesignationInstance)
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
        		/* Shows the following message if any employee exists of this particular designation. */
        		flash.message = "${message(code: 'default.usedinProjectEmployee.label')}"
        		redirect(action: "edit", id: params.id)    		   
        	}
        }
        else 
        {
        	flash.message = "${message(code: 'default.notfond.label')}"
        	redirect(action: "list")
        }
    }
}