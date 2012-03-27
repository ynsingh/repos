package aell

class AvlContentTypeMaster {
	String contentTypeName
	String contentTypeIcon
    String typeMandatory 
	Integer id
    static mapping = {
 	   id column: "content_type_id"
 	   version false
 	   }
    static constraints = {
		contentTypeName blank: false
		contentTypeIcon blank: false
		typeMandatory blank:false
    }
}
