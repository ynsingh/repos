

class ProjectEmployeeQualification {
	ProjectEmployee projectEmployee
	String Examname
	Double PercMark
	String University
	String PassoutYear
	String Status
    static constraints = {
		PercMark(size:1..2)
    }
}
