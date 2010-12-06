class ProjectEmployeeSalaryDetailsService
{
	
	/**
	 * Get Employee salary details
	 */
	public List getSalaryList(def subQuery){
		def projectEmployeeSalaryDetailsInstanceList = ProjectEmployeeSalaryDetails.findAll("from ProjectEmployeeSalaryDetails PQ where PQ.projectEmployee.id='"+subQuery+"' and PQ.status='Y'")
		return projectEmployeeSalaryDetailsInstanceList
	}
	
	/**
	 * Update Employee salary details
	 */
	public ProjectEmployeeSalaryDetails updateProjectEmployeeSalaryDetails(def projectEmployeeInstance,
										def projectEmployeeSalaryDetailsInstanceList,
										def projectEmployeeSalaryDetailsInstance)
	{
		def editEntryInstance= ProjectEmployeeSalaryDetails.findAll("from ProjectEmployeeSalaryDetails  UM  where UM.salaryComponent.id ='"+projectEmployeeSalaryDetailsInstance.salaryComponent.id+"' and UM.projectEmployee.id='"+projectEmployeeInstance.id+"'")
		if(editEntryInstance)
		{
			def getprevious = ProjectEmployeeSalaryDetails.find("from ProjectEmployeeSalaryDetails AH where AH.projectEmployee.id= '"+projectEmployeeInstance.id+"' and AH.status='Y' and AH.projectEmployee.id='"+projectEmployeeInstance.id+"'")
			if(getprevious)
			{	
				getprevious.endDate=projectEmployeeSalaryDetailsInstance.WithEffectFrom
				getprevious.status='N'	
				
			}
		}	
	}
	
	/**
	 * Get Employee salary details based on salary component
	 */
	 public List getEmployeeSalaryDetailsBasedOnComponent(def salaryComponentInstance)
	{	 
		 def ProjectEmployeeSalaryDetailsInstance = ProjectEmployeeSalaryDetails.findAll("from ProjectEmployeeSalaryDetails PE where PE.salaryComponent="
				 																			+salaryComponentInstance.id + " and PE.status='Y'")
	     return ProjectEmployeeSalaryDetailsInstance
	}
}

	

