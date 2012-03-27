package aell

class AvlSubTopicMaster {
	String subTopicName
	String subTopicDescription
	AvlTopicMaster avlTopicMaster
    String subTopicStatus 
	Integer subTopicSequence
    static mapping = {
  	   id column: "sub_topic_id"
  		 avlTopicMaster column:"topic_id"
  	   version false
  	   }
    static constraints = {
		subTopicName blank: false
		avlTopicMaster blank:false
		subTopicStatus blank:false
		subTopicSequence nullable: true
    }
}
