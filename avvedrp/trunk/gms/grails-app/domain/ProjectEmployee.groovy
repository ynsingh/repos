

class ProjectEmployee {
	Projects projects
	String empNo
	String EmpName
	Date dOB
	EmployeeDesignation employeeDesignation
	Date joiningDate
	Date relievingDate
	String Status
    static constraints = {
		EmpName(blank:false)
		empNo(blank:false,unique:true)
		dOB(nullable:true)
		relievingDate(nullable:true)
    }
}
