package aell

class AellRoleplayVideoDetails {

	Integer  ContentTypeId
	String videoName
	 static mapping = {
		 version false
   }
   static constraints = {
		videoName blank: false

   }
}
