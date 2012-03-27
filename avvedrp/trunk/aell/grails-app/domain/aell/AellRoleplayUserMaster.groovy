package aell

class AellRoleplayUserMaster {
	Integer  ContentTypeId
	String audioUserType
	 static mapping = {
		 version false
    }
	  static constraints = {
		audioUserType blank: false
    }
}
