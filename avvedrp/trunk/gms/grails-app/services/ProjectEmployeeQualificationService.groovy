

class ProjectEmployeeQualificationService {

	/**
	 * Function to get each employees qualification
	 */
	public List getQualificationList(def subQuery){
		def projectEmployeeQualificationInstanceList =  ProjectEmployeeQualification.findAll("from ProjectEmployeeQualification PQ where PQ.projectEmployee.id = '"+subQuery+"' and PQ.status='Y'")
		return projectEmployeeQualificationInstanceList
	}
	
}
