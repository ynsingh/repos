package aell

class TopicMasterService {

    boolean transactional = true
	
    List getTopicsBySubjectId(Integer subjectId) {
		def results = AvlTopicMaster.findAll("from AvlTopicMaster as b where avlSubjectMaster.id=? and topic_status='A' ORDER BY topic_sequence, topic_id ASC",[subjectId])
		return results
    }
	
	void updateTopic(AvlTopicMaster topic, Integer subjectId){
		def avlSubjectMasterIns =  AvlSubjectMaster.findById(subjectId)
		topic.avlSubjectMaster = avlSubjectMasterIns
		topic.save()
	}
	
	
	void createTopicMaster(AvlTopicMaster newTopicMasterInstance, Integer subjectId) throws AvlServiceException {
		List avlTopicMasters = AvlTopicMaster.findAllByTopicNameAndTopicStatus(newTopicMasterInstance.topicName, 'A')
		if(avlTopicMasters !=null && avlTopicMasters.size()>0){
			AvlServiceException avlexc = new AvlServiceException()
			avlexc.message = "Topic Already Exist"
			throw avlexc
		}
		def  results = getTopicsBySubjectId(subjectId);
		def lastTopicMaster
		if(results != null && results.size() > 0){
			lastTopicMaster = results.last()
		}
		
		newTopicMasterInstance.topicStatus = 'A'
		if(lastTopicMaster != null && lastTopicMaster.topicSequence != null){
			newTopicMasterInstance.topicSequence = lastTopicMaster.topicSequence + 1;
		}else {
			newTopicMasterInstance.topicSequence = 1
		}
		def avlSubjectMasterIns =  AvlSubjectMaster.findById(subjectId)
		newTopicMasterInstance.avlSubjectMaster = avlSubjectMasterIns		
		newTopicMasterInstance.save()
		createSubTopicMaster(newTopicMasterInstance)
}
	
	void createSubTopicMaster(AvlTopicMaster newTopicMasterInstance){
		def avlSubTopicMasterIns = new AvlSubTopicMaster()
		avlSubTopicMasterIns.subTopicName = newTopicMasterInstance.topicName
		avlSubTopicMasterIns.subTopicDescription = newTopicMasterInstance.topicDescription
		avlSubTopicMasterIns.avlTopicMaster = newTopicMasterInstance
		avlSubTopicMasterIns.subTopicStatus = 'A'
		//avlSubTopicMasterIns.save()
		if(!avlSubTopicMasterIns.save(flush:true)){
			println("errors: ${avlSubTopicMasterIns.errors}");
		}
		
	}
	
	void updateSequence(String sequenceOrder)
	{
		String[] ids = sequenceOrder.split(",");
		for(int i=0;i<ids.length;i++)
		{
			AvlTopicMaster topicMaster = AvlTopicMaster.get(ids[i] as int);
			topicMaster.topicSequence = i+1;
			topicMaster.save();
		}
	}

}
