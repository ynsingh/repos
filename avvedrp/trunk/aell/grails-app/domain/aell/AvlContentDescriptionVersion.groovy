package aell

class AvlContentDescriptionVersion {
	    AvlContentDetails content
		String contentMode
		String contentDescription
		Integer userId
	    Date dateTime 
	    String versionStatus
		Integer id
	    static mapping = {
	   	   id column: "version_id"
	   	   version false
	   	   }
	    static constraints = {
		    content blank: false
			contentMode blank: false
			userId blank: false
			dateTime blank:false
	    }
}
