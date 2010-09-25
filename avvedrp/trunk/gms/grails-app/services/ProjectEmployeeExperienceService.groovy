

class ProjectEmployeeExperienceService {

	/**
	 * Function to get each employees experience
	 */
	public List getExperienceList(def subQuery){
		def projectEmployeeExperienceInstanceList = ProjectEmployeeExperience.findAll("from ProjectEmployeeExperience PQ where PQ.projectEmployee.id= '"+subQuery+"' and PQ.status='Y'")
		return projectEmployeeExperienceInstanceList
	}
}
