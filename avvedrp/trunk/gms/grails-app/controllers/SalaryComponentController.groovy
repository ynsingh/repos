class SalaryComponentController 
{
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index ={}

    /**
     * Method to perform create action
     */
    def create = 
    {
    	 def salaryComponentInstance = new SalaryComponent()
         def salaryComponentService = new SalaryComponentService()
    	 salaryComponentInstance.properties = params
    	 
    	 /*Getting the list of all active salary components*/
         def salaryComponentInstanceList=salaryComponentService.getsalaryComponentList()
         
         return [salaryComponentInstance: salaryComponentInstance, 
                 salaryComponentInstanceList:salaryComponentInstanceList]
    }

    /**
     * Method to perform Save action
     */
	def save = 
    {
    	def salaryComponentService = new SalaryComponentService()
    	def salaryComponentInstance = new SalaryComponent(params)
    	def chkSalaryComponentInstance = salaryComponentService.checkDuplicateSalaryComponent(params)
		if(chkSalaryComponentInstance)
	    {
	    	flash.message ="${message(code: 'default.AlreadyExists.label')}"
	    	redirect(action: "create", id: salaryComponentInstance.id)
	    }
	    else
	    {
	    	/*save salary component details*/ 
	    	salaryComponentInstance = salaryComponentService.saveSalaryComponent(params)
	    	if (salaryComponentInstance) 
	    	{
	            flash.message ="${message(code: 'default.created.label')}"
	            redirect(action: "create", id: salaryComponentInstance.id)
	    	}
	    	else 
	    	{
	    		render(view: "create", model: [salaryComponentInstance: salaryComponentInstance])
	    	}
	    }
	}

    /**
     * Method to perform the edit action
     */
	def edit = 
    {
    	def salaryComponentService = new SalaryComponentService()
    	/*Getting Salary Component based on id*/
    	def salaryComponentInstance =salaryComponentService.getSalaryComponentById(new Integer( params.id ))
        /**Check whether the salary component exists or not*/
    	if (salaryComponentInstance) 
        { 
        	return [salaryComponentInstance: salaryComponentInstance]
        }
        else 
        {
        	 flash.message = "${message(code: 'default.notfond.label')}"
             redirect(action: "create")
        }
    }

    /**
     * Method to perform the update action
     */
	def update = 
	{	
    	Integer salaryComponentId = null
    	def salaryComponentService = new SalaryComponentService()
    	/*Getting Salary Component based on id*/
		def salaryComponentInstance =salaryComponentService.getSalaryComponentById(new Integer( params.id ))
		def chkSalaryComponentInstance = salaryComponentService.checkDuplicateSalaryComponent(params)
		if(chkSalaryComponentInstance)
		{
			if((chkSalaryComponentInstance.id).equals(salaryComponentInstance.id))
			{
				salaryComponentId=salaryComponentService.updatesalaryComponent(params)
			    if(salaryComponentId>0)/*Check if the Salary component updated successfully*/
		        {
		            flash.message = "${message(code: 'default.updated.label')}"
		            redirect(action: "create", id: salaryComponentInstance.id)
		        }
				
			}
			else if(chkSalaryComponentInstance)
		    {
		    	flash.message ="${message(code: 'default.AlreadyExists.label')}"
		    	redirect(action: "edit", id: params.id)  
		    }
		}
	    else
	    {
		    if (salaryComponentInstance) 
		    {
		    	salaryComponentInstance.properties = params
		        /*Updating the salary component details*/
		        salaryComponentId=salaryComponentService.updatesalaryComponent(params)
		       
		        if(salaryComponentId>0)/*Check if the Salary component updated successfully*/
		        {
		            flash.message = "${message(code: 'default.updated.label')}"
		            redirect(action: "create", id: salaryComponentInstance.id)
		        }
		        else if(salaryComponentId == 0)/*Check if the salary component is assigned to employee*/
		        {
		        	flash.message = "${message(code: 'default.usedinProjectEmployee.label')}"
		        	redirect(action: "edit", id: params.id)  
		        }
		        else 
		        {
		            render(view: "edit", model: [salaryComponentInstance: salaryComponentInstance])
		        }
		    }
		    else 
		    {
		        flash.message = "${message(code: 'default.notfond.label')}"
		        redirect(action: "create")
		    }
	    }
	}
    
    /*
     * Method to perform the delete action
     */
    def delete = 
    {
    	Integer salaryComponentId = null	
    	def salaryComponentService = new SalaryComponentService()
		def salaryComponentInstance = new SalaryComponent()
		salaryComponentInstance.properties = params 
		/*Deleting the salary Component Details*/
		salaryComponentId =salaryComponentService.deleteSalaryComponent(params)
		
		if (salaryComponentId==0)/*Check if the salary component is assigned to employee*/
		{
			flash.message = "${message(code: 'default.usedinProjectEmployee.label')}"
    		redirect(action: "edit", id: params.id)  
		}
		else if (salaryComponentId>0)/*Check if the Salary component deleted successfully*/
		{	
			flash.message = "${message(code: 'default.deleted.label')}"
			redirect(action: "create")	          
		}	           
        else 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create")
        }    
    }

}