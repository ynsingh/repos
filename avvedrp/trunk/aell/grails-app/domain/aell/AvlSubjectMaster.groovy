package aell

class AvlSubjectMaster {
	String subjectName
	String subjectDescription
	Integer universityId
    String subjectStatus 
	Integer subjectSequence
	Integer id
    static mapping = {
	   id column: "subject_id"
	   version false
	   }
    static constraints = {
		subjectName blank: false
		subjectStatus blank:false
		universityId blank:false
    }
}
