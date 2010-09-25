class EmployeeDesignationService {

	/**
	 * Function to get item list
	 */
	public List getemployeeDesignationList()
	{
		
		def employeeDesignationInstanceList = EmployeeDesignation.list()
			
		return employeeDesignationInstanceList
	}
}
