package aell

class AellRoleplayAudioDetails {
	Integer  ContentTypeId
	String audioName
	AellRoleplayUserMaster aellRoleplayUserMaster 
	 static mapping = {
		 version false
    }
    static constraints = {
		audioName blank: false
    }
}
