package aell
import org.apache.commons.lang.builder.HashCodeBuilder
class AvlExperimentSubtopicRel implements Serializable{
	AvlExperimentMaster experiment
	AvlSubTopicMaster sub_topic
	Integer experimentSequence
	static mapping = {
		id composite: ['sub_topic', 'experiment']
		   version false
		   }
    static constraints = {
		experiment blank: false ,nullable:true
		sub_topic blank: false ,nullable:true
		experimentSequence nullable:true
    }
}
