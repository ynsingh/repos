class EmployeeDesignationService
{
	/**
	 * Get the list of all active Employee designations
	 */
	public List getemployeeDesignationList()
	{
		def employeeDesignationInstanceList=EmployeeDesignation.findAll("from EmployeeDesignation ED where ED.activeYesNo='Y'")			
		return employeeDesignationInstanceList
	}

	/*
	 * Save the employee designation
	 */
	public saveEmployeeDesignation(params)
	{
	    def employeeDesignationInstance = new EmployeeDesignation(params)
	    employeeDesignationInstance.activeYesNo="Y" 
	    employeeDesignationInstance.save(flush: true)
	    return employeeDesignationInstance
	}
	
	/*
	 * Get Employee designation by id
	 */
	public EmployeeDesignation getEmployeeDesignationById(String employeeDesignationId)
	{
		def employeeDesignationInstance = EmployeeDesignation.get(employeeDesignationId)
		return employeeDesignationInstance
	}
	
	/*
	 *  Update the employee designation details
	 */
	public EmployeeDesignation updateEmployeeDesignation(def params,def employeeDesignationInstance)
	{	
		if (employeeDesignationInstance) 
		{
			employeeDesignationInstance.properties = params
			 
			if (!employeeDesignationInstance.hasErrors() && employeeDesignationInstance.save(flush: true)) 
			{
				employeeDesignationInstance.saveMode = "Updated"
			}
			return employeeDesignationInstance
		}
		return employeeDesignationInstance
	}
	
	/*
	 * Delete the employee designation details
	 */
	public deleteEmployeeDesignation(def employeeDesignationInstance)
	{
		/*setting the record as inactive*/
		employeeDesignationInstance.activeYesNo="N"
		employeeDesignationInstance.save(flush: true) 
		return employeeDesignationInstance
	}
	/*
	 * Check Duplicate Designation
	 */
	 public checkDuplicateDesignation(def params)
	{
		 def chkEmployeeDesignationInstance = EmployeeDesignation.find("from EmployeeDesignation ED where ED.designation= '"+params.designation+"' and ED.activeYesNo='Y'")
		 return chkEmployeeDesignationInstance
	}
}

 