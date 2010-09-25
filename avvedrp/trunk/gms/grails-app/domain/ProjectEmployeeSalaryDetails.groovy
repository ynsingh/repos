

class ProjectEmployeeSalaryDetails {
	ProjectEmployee projectEmployee
    SalaryComponent salaryComponent
	Double SalaryAmount
	Date WithEffectFrom
	Date endDate
	String Status
    static constraints = {
		endDate(nullable:true)
		   }
}
