
package aell

class TopicController {
	
	def ContentHomeService
	def SubjectMasterService
	def TopicMasterService

	def beforeInterceptor = [action:this.&init,except:[]]

	def init() {
			if(!session.university) {
				if(session.user.id)
				{
				//Store university info
				session.university = ContentHomeService.universityMasterInfo(session.user.universityId);
				} 
			}
	}
	
    def index = {
		//Get all the subjects
		def subjectList =  SubjectMasterService.getSubjectMasterByUnvId(session.university.id)
		if(subjectList.size() > 0)
		{
		
			//If the sectionId is passed in params, fired by onChange of the section list
			if(params.subjectId != null)
			{
				session.selectedSubjectId = params.subjectId;
				session.selectedTopicId = null;
				session.selectedExperimentId = null;
				session.selectedContentId = null;
				session.selectedVersionId = null;
			}

			//1. If the page is accessed by clicking the experiment link , session.selectedSubjectId will be present
			//2. if the page is accessed directly, session.selectedSubjectId will be null and set to the first element in the list
			//Get the subject id from params or the first one in the list (if the page is accessed directly)
			def subjectId = (session.selectedSubjectId != null && session.selectedSubjectId != "")? session.selectedSubjectId : subjectList[0].id

			//Store the subjectId in the session (this will be the first one in the list)
			if(session.selectedSubjectId == null)
				session.selectedSubjectId = subjectId;
			
			//Get the list of topics based on the subject
			def topicList =  TopicMasterService.getTopicsBySubjectId(session.selectedSubjectId as int)
			render(view:"/contentHome/topics", model:[topicList : topicList, subjectList : subjectList])
			
		}else
		{
			flash.message =  "${message(code: 'default.noTopics.message')}"
			render(view:"/contentHome/topics")
		}
		
	}
	

	def saveTopic = {
		if(params.topicId != null && params.topicId != ""){
			//EDIT TOPIC
			def avlTopicMaster = AvlTopicMaster.get( params.topicId )
			if(avlTopicMaster != null) {
				avlTopicMaster.properties = params
				avlTopicMaster.topicStatus ='A'
				if(avlTopicMaster.topicSequence == null){
					avlTopicMaster.topicSequence=1
				}
				if(!avlTopicMaster.hasErrors()) {
					TopicMasterService.updateTopic(avlTopicMaster, session.selectedSubjectId as int)
					flash.message = "Topic - ${avlTopicMaster.topicName} - successfully updated"
					redirect(action:index)					
				}
				else {
					flash.error = "Failed to update Topic  ${avlTopicMaster.topicName}"
					redirect(action:index)
				}
			}
		}else{
			//ADD NEW TOPIC
			def avlTopicMasterInstance = new AvlTopicMaster()
			avlTopicMasterInstance.properties = params
			try{
					if(!avlTopicMasterInstance.hasErrors()) {						
						TopicMasterService.createTopicMaster(avlTopicMasterInstance, session.selectedSubjectId as int)
						flash.message = "Topic - ${avlTopicMasterInstance.topicName} - successfully added"
						
					}else
						flash.error = "Failed to add Topic  ${avlTopicMasterInstance.topicName}"
						redirect(action:index)
			}catch(AvlServiceException ase){
				flash.error = ase.message
				redirect(action:index)
			}
			
		}
	}
	def deleteTopic = {
		flash.message = ""
		if(params.deleteId != null && params.deleteId != ""){
			def avlTopicMasterInstance = AvlTopicMaster.get( params.deleteId )
			if(avlTopicMasterInstance) {
				avlTopicMasterInstance.properties = params
				
				if(!avlTopicMasterInstance.hasErrors()) {
					avlTopicMasterInstance.topicStatus ='D'
					TopicMasterService.updateTopic(avlTopicMasterInstance, session.selectedSubjectId as int)
					flash.message = "Topic - ${avlTopicMasterInstance.topicName} - successfully Deleted"
					redirect(action:index)
					
					
				}
				else {
					flash.error = "Failed to delete the Topic  ${avlTopicMasterInstance.topicName}"
					redirect(action:index)
				}
			}
		}
	}
	
	def updateSequence =
	{
		TopicMasterService.updateSequence(params.sequenceOrder)
		redirect(action:index)
	}


}
