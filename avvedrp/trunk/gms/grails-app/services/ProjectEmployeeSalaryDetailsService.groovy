

class ProjectEmployeeSalaryDetailsService {
	
	/**
	 * Function to get each employees salary details
	 */
	public List getSalaryList(def subQuery){
		def projectEmployeeSalaryDetailsInstanceList = ProjectEmployeeSalaryDetails.findAll("from ProjectEmployeeSalaryDetails PQ where PQ.projectEmployee.id='"+subQuery+"' and PQ.status='Y'")
		return projectEmployeeSalaryDetailsInstanceList
	}
	
	/**
	 * Function to update Project Employee Salary Details
	 */
	public ProjectEmployeeSalaryDetails updateProjectEmployeeSalaryDetails(def projectEmployeeInstance,def projectEmployeeSalaryDetailsInstanceList,def projectEmployeeSalaryDetailsInstance){
		println "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~salary component!!!~~~~~~"+projectEmployeeInstance
		println "-----previous entries of salarycomppnent"	+projectEmployeeSalaryDetailsInstanceList.salaryComponent.id
		println "-----current entry of salarycomppnent"+projectEmployeeSalaryDetailsInstance.salaryComponent.id
		def editEntryInstance= ProjectEmployeeSalaryDetails.findAll("from ProjectEmployeeSalaryDetails  UM  where UM.salaryComponent.id ='"+projectEmployeeSalaryDetailsInstance.salaryComponent.id+"' and UM.projectEmployee.id='"+projectEmployeeInstance.id+"'")
		if(editEntryInstance)
		{
	println "exist"
	def getprevious = ProjectEmployeeSalaryDetails.find("from ProjectEmployeeSalaryDetails AH where AH.projectEmployee.id= '"+projectEmployeeInstance.id+"' and AH.status='Y' and AH.projectEmployee.id='"+projectEmployeeInstance.id+"'")
	//projectEmployeeSalaryDetailsInstance.endDate=projectEmployeeSalaryDetailsInstance.WithEffectFrom
	//projectEmployeeSalaryDetailsInstance.save()
	if(getprevious)
	{		println "yes"
		println "________"+getprevious.WithEffectFrom
		getprevious.endDate=projectEmployeeSalaryDetailsInstance.WithEffectFrom
		getprevious.status='N'	
				
	}
	}	
	}
}

	

