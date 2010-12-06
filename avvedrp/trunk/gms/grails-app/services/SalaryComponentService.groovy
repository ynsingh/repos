class SalaryComponentService 
{
	/**
	* Get the list of all active salary components
	*/
	public List getsalaryComponentList()
	{
		def salaryComponentInstanceList = SalaryComponent.findAll("from SalaryComponent SC where SC.activeYesNo='Y'")
		return salaryComponentInstanceList
	}
	
	/**
	 * Save Salary Component Details
	*/
	public saveSalaryComponent(params)
	{
		def salaryComponentInstance = new SalaryComponent(params)
		/*Setting the component as active*/
		salaryComponentInstance.activeYesNo="Y" 
		salaryComponentInstance.save(flush: true)
		return salaryComponentInstance
	}
	
	/**
	 * Delete Salary Compoment
	*/
	public Integer deleteSalaryComponent(params)
	{	
		def salaryComponentId = null
		def projectEmployeeSalaryDetailsService=new ProjectEmployeeSalaryDetailsService()
		
		/*Get Salary Component based on Id*/
		def salaryComponentInstance = getSalaryComponentById( new Integer(params.id ))
		/*Check whether the Salary Component details exists or not*/
		if (salaryComponentInstance)
		{   	
			/*Get Employee Salary details based on the salary component*/
			def projectEmployeeSalaryDetailsInstance=projectEmployeeSalaryDetailsService
																.getEmployeeSalaryDetailsBasedOnComponent(salaryComponentInstance)			
			/*Check whether any employee having this salary componet*/
			if (!projectEmployeeSalaryDetailsInstance)
			{	    
				/*Setting the salary Component as in-active*/
				salaryComponentInstance.activeYesNo="N" 
				salaryComponentInstance.save(flush: true)
				salaryComponentId =salaryComponentInstance.id 
			}  
			else
			{
				salaryComponentId=0			    
			}	
		}
		/*1.Returns the salary component instance id if the component deleted successfully
		 *2.Returns zero if the salary component is assigned to employees
		 * */
	    return salaryComponentId
	}

	/**
	 *Get Salary Component based on id
	*/
	public SalaryComponent getSalaryComponentById(Integer salaryComponentId)
	{
		def salaryComponentInstance = SalaryComponent.get(salaryComponentId)
		return salaryComponentInstance
	}
	
	/**
	 * Update Salary Component
	 */
	public Integer updatesalaryComponent(def salaryComponentParams)
	{	
		def salaryComponentId = null
		def projectEmployeeSalaryDetailsService=new ProjectEmployeeSalaryDetailsService()
		/*Get Salary Component by Id*/
		def salaryComponentInstance = getSalaryComponentById( new Integer(salaryComponentParams.id ))
		if (salaryComponentInstance)
		{
			/*Get Employee Salary details based on the salary component*/
			def projectEmployeeSalaryDetailsInstance=projectEmployeeSalaryDetailsService
																.getEmployeeSalaryDetailsBasedOnComponent(salaryComponentInstance)			
			/*Check whether any employee having this salary componet*/
			if (!projectEmployeeSalaryDetailsInstance)
			{
				salaryComponentInstance.properties = salaryComponentParams
				if(salaryComponentInstance.save())
	            {
	            	salaryComponentId =salaryComponentInstance.id
	            }  
			}
			else
			{
				salaryComponentId=0			    
			}	
		}
		/*1.Returns the salary component instance id if the component updated successfully
		 *2.Returns zero if the salary component is assigned to employees
		 * */
		return salaryComponentId		
	}	
	/*
	 * Check Duplicate Salary Component
	 */
	 public checkDuplicateSalaryComponent(def params)
	{
		 def chkSalaryComponentInstance = SalaryComponent.find("from SalaryComponent SC where SC.name= '"+params.name+"' and SC.activeYesNo='Y'")
		 return chkSalaryComponentInstance
	}
}
	
