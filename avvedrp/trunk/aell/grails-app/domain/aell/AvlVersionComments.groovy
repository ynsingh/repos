package aell

class AvlVersionComments {
		String comments
		Integer versionId
		Integer id
	    static mapping = {
			id column: "id"
	   	   version false
	   	   }
	    static constraints = {
	    }
}
