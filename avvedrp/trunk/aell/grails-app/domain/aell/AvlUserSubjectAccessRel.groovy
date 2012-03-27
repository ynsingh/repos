package aell

class AvlUserSubjectAccessRel {
	Integer userId
	Integer subjectId
	Integer roleId
    static constraints = {
		subjectId blank: false
		userId blank:false	
		roleId blank: false
    }
	
}


