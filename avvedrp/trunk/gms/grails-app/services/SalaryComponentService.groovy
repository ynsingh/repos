class SalaryComponentService {

	/**
	 * Function to get item list
	 */
	public List getsalaryComponentList()
	{
		
		def salaryComponentInstanceList = SalaryComponent.list()
			
		return salaryComponentInstanceList
	}
}
