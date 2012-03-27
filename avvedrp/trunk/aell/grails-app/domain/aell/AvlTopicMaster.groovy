package aell

class AvlTopicMaster {
	String topicName
	String topicDescription
	AvlSubjectMaster avlSubjectMaster
    String topicStatus 
	Integer topicSequence
	Integer id
    static mapping = {
 	   id column: "topic_id"
 	   avlSubjectMaster column:"subject_id"
 	   version false
 	   }
    static constraints = {
		topicName blank: false
		avlSubjectMaster blank:false
		topicStatus blank:false		
    }	
}
