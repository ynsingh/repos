

class ProjectEmployeeService {
	/**
	 * Function to get  employees list
	 */
	public List getProjectEmployeeList(def projectInstance){
		
		def projectEmployeeInstanceList = ProjectEmployee.findAll("from ProjectEmployee PQ where PQ.projects.id =" +projectInstance.id+ " and PQ.status='Y'")
		return projectEmployeeInstanceList
	
	}
	/**
	 * Function to check unique property 
	 */
	public  projectEmployeeUniqueCheck(def projectEmployeeInstance){
		def projectEmployeeUniqueCheckstatus=ProjectEmployee.findAll("from ProjectEmployee PE where PE.empNo='"+projectEmployeeInstance.empNo+"'")
		return projectEmployeeUniqueCheckstatus
	}
	/**
	 * Function to update status
	 */
	public  projectEmployeeupadateStatus(def projectEmployeeInstance,def projectEmployeeQualificationInstance,def projectEmployeeExperienceInstance,def projectEmployeeSalaryDetailsInstance){
		def projectEmployeeupdateQualificationStatuscheck=ProjectEmployeeQualification.findAll("from ProjectEmployeeQualification PQ where PQ.projectEmployee.id='"+projectEmployeeInstance.id+"'")
		def projectEmployeeupdateExperienceStatuscheck=ProjectEmployeeExperience.findAll("from ProjectEmployeeExperience PE where PE.projectEmployee.id='"+projectEmployeeInstance.id+"'")
		def projectEmployeeupdateSalaryDetailsStatuscheck=ProjectEmployeeSalaryDetails.findAll("from ProjectEmployeeSalaryDetails PS where PS.projectEmployee.id='"+projectEmployeeInstance.id+"'")
		if(projectEmployeeupdateQualificationStatuscheck)
		{
			for(int i=0;i<projectEmployeeupdateQualificationStatuscheck.size();i++){
				projectEmployeeupdateQualificationStatuscheck[i].status='D'
		}			
		}
		if(projectEmployeeupdateExperienceStatuscheck)
		{
			for(int i=0;i<projectEmployeeupdateExperienceStatuscheck.size();i++){
				projectEmployeeupdateExperienceStatuscheck[i].status='D'
		}			
		}
		if(projectEmployeeupdateSalaryDetailsStatuscheck)
		{
			for(int i=0;i<projectEmployeeupdateSalaryDetailsStatuscheck.size();i++){
				projectEmployeeupdateSalaryDetailsStatuscheck[i].status='D'
		}			
		}
		//println "projectEmployeeupadateStatuscheck.status"+projectEmployeeupadateStatuscheck.status
		
	}
	
}
