package aell

class ExperimentController {
	
	def ContentHomeService
	def SubjectMasterService
	def TopicMasterService
	def ExperimentMasterService
	def grailsApplication
	
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
				
			//If the topicId is passed in params, fired by onChange of the topic list
			if(params.topicId != null)
			{
				session.selectedTopicId = params.topicId;
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
			
			//1. If the page is accessed by clicking the experiment link , session.selectedtopicId will be present
			//2. if the page is accessed directly, session.selectedTopicId will be null and session will be set to the first element in the list
			//Get the topic id from params or the first one in the list
			def topicId
			if(topicList.size>0)
			  topicId = (session.selectedTopicId != null && session.selectedTopicId != "")? session.selectedTopicId : topicList[0].id

			//Store the topic in the session (this will be the first one in the list)
			if(session.selectedTopicId == null)
				session.selectedTopicId = topicId;

			//Get experiments based on topic
			def experimentList = ExperimentMasterService.getExperimentsByTopicId(session.selectedTopicId as int)

			def experimentTypeList =  ExperimentMasterService.getAllExperimentType()
			
			render(view:"/contentHome/experiments", model:[experimentList: experimentList , topicList : topicList, subjectList : subjectList, experimentTypeList:experimentTypeList]) 
		}else
		{
			flash.message =  "${message(code: 'default.noSubtopics.message')}"
			render(view:"/contentHome/experiments")
		}
		
	}
	def saveExperiment = {
		if(params.experimentId != null && params.experimentId != ""){
			//EDIT EXPERIMENT
			def avlExperimentMaster = AvlExperimentMaster.get( params.experimentId )
			if(avlExperimentMaster != null) {
				avlExperimentMaster.properties = params
				avlExperimentMaster.experimentStatus ='A'				
				if(!avlExperimentMaster.hasErrors()) {
					ExperimentMasterService.updateExperiment(avlExperimentMaster, avlExperimentMaster.avlExperimentTypeMaster.id)
					flash.message = "Experiment ${avlExperimentMaster.experimentName} successfully updated"
					redirect(action:index)
				}
				else {
					flash.error = "Failed to update Experiment  ${avlExperimentMaster.experimentName}"
					redirect(action:index)
				}
			}
		}else{
			//ADD NEW EXPERIMENT
			def avlExperimentMaster = new AvlExperimentMaster()
			println "params="+params
			avlExperimentMaster.properties = params
			try{
					if(!avlExperimentMaster.hasErrors()) {
						ExperimentMasterService.createExperimentMaster(avlExperimentMaster, params.experimentTypeId as int, session.selectedTopicId as int)
						flash.message = "Experiment ${avlExperimentMaster.experimentName} successfully Added"
						
					}else
						flash.error = "Failed to add Experiment  ${avlExperimentMaster.experimentName}"
						redirect(action:index)
			}catch(AvlServiceException ase){
				flash.error = ase.message
				redirect(action:index)
			}
			
		}
	}
	def deleteExperiment = {
		flash.message = ""
		if(params.deleteId != null && params.deleteId != ""){
			def avlExperimentMaster = AvlExperimentMaster.get( params.deleteId )
			if(avlExperimentMaster) {
				avlExperimentMaster.properties = params
				
				if(!avlExperimentMaster.hasErrors()) {
					avlExperimentMaster.experimentStatus ='D'
					ExperimentMasterService.updateExperiment(avlExperimentMaster, avlExperimentMaster.avlExperimentTypeMaster.id)
					flash.message = "Experiment ${avlExperimentMaster.experimentName} successfully Deleted"
					redirect(action:index)
					
					
				}
				else {
					flash.error = "Failed to delete the Experiment  ${avlExperimentMaster.experimentName}"
					redirect(action:index)
				}
			}
		}
	}
	
	def updateSequence =
	{
		ExperimentMasterService.updateSequence(params.sequenceOrder)
		redirect(action:index)
	}


}
