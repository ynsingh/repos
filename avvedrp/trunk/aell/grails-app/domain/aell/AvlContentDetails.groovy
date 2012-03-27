package aell

class AvlContentDetails {
	AvlContentTypeMaster content_type
	AvlExperimentMaster experiment
	Integer contentTypeSequence 
    Integer versionId
    String authenticationNeeded
    String contentStatus
	Integer id
    static mapping = {
  	   id column: "content_id"
  	   version false
  	   }
    static constraints = {
		content_type blank: false
		experiment blank: false
		contentTypeSequence blank: false
		authenticationNeeded blank: false
		versionId nullable: true
    }
}
