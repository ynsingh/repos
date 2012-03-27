package aell

import groovy.sql.Sql;
import java.io.File;
import javax.imageio.ImageIO

import java.util.Date
import java.util.logging.*
class EditExperimentController {
	
	def ContentHomeService
	def SubjectMasterService
	def TopicMasterService
	def ExperimentMasterService
	def QuizMasterService
	def contentTypes = ["T":"Text","L":"Link","Q":"Quiz"];
	def grailsApplication
	
	def beforeInterceptor = [action:this.&init,except:[]]
	def questionContentList
	
	
	def init() {
			if(!session.university) {
				if(session.user.id)
				{
				//Store university info
				session.university = ContentHomeService.universityMasterInfo(session.user.universityId);
				}  
			}
			if(session.fileIcons == null)
			{
				File directory = grailsApplication.parentContext.getResource("images/tab_icon_image").file
				if(directory != null){
					session.fileIcons = directory.list({d, f-> f ==~ /.*.png/ } as FilenameFilter)
				}
			}
			if(session.user.id)
			{
			session.userId = session.user.id //TODO: Once the page is secure, we will have the login id
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
				session.selectedContentTypeMode=null
			}
				
			//If the topicId is passed in params, fired by onChange of the topic list
			if(params.topicId != null)
			{
				session.selectedTopicId = params.topicId;
				session.selectedExperimentId = null;
				session.selectedContentId = null;
				session.selectedVersionId = null;
				session.selectedContentTypeMode=null
			}
	
			//If the experimentId is passed in params, fired by onChange of the experiment list
			if(params.experimentId != null)
			{
				session.selectedExperimentId = params.experimentId;
				session.selectedContentId = null;
				session.selectedVersionId = null;
				session.selectedContentTypeMode=null
			}
	
			//If the contentId is passed in params, fired by clicking the tab
			if(params.contentId != null)
			{
				session.selectedContentId = params.contentId as int;
				session.selectedContentTypeMode=ExperimentMasterService.getContentTypeModeUsingSelectedContentId(params.contentId as int)
				session.selectedVersionId = null;
			}
			else if(params.contentType!=null){
				session.selectedContentTypeMode=params.contentType
			}
			//If the versionId is passed in params, fired by clicking the version combo list
			if(params.versionId != null)
			{
				session.selectedVersionId = params.versionId as int;
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

			//1. If the page is accessed by clicking the experiment link , params.experimentId will be present
			//2. if the page is accessed directly, params.experimentId will be null and session will be set to the first element in the list
			//Get the experiment id from params or the first one in the list
			def experimentId
			if(experimentList.size>0)
			  experimentId = (session.selectedExperimentId != null && session.selectedExperimentId != "")? session.selectedExperimentId : experimentList[0].id

			//Store the experiment in the session
			if(session.selectedExperimentId == null) 
				session.selectedExperimentId = experimentId;
			//when subject is accessed directly session.selectedContentTypeMode must be based on the 
		    // session.selectedContentId
		   if(session.selectedContentTypeMode==null){
			 if(session.selectedContentId!=null)
			   session.selectedContentTypeMode=ExperimentMasterService.getContentTypeModeUsingSelectedContentId(session.selectedContentId)
			 if(session.selectedExperimentId!=null)
			   session.selectedContentTypeMode=ExperimentMasterService.getContentTypeModeUsingSelectedExperimentId(session.selectedExperimentId)
		   }
			//Get the tab details
			def contentTabDetails = ExperimentMasterService.getContentTabDetails(session.selectedExperimentId as int);

			//1. For the first page load, params.contentId will not be present
			//2. Subsequently, params.contentId is present for tab clicks 
			def contentId
			if(contentTabDetails.size>0)
			 contentId = (session.selectedcontentId != null && session.selectedcontentId != "")? session.selectedcontentId : contentTabDetails[0].id

			//Set the selectedContentId in the session
			//Store the experiment in the session
			if(session.selectedContentId == null) 
				session.selectedContentId = contentId;

			//Get the version details for the selected content id
			def versionDetails = ExperimentMasterService.getVersionDetails(session.selectedContentId as int);

			def contentDetails = null;
			
			if(session.selectedVersionId != null)
			{
				contentDetails = ExperimentMasterService.getContentDetailsForContentAndVersion(session.selectedContentId as int, session.selectedVersionId as int);
			}else
			{
				if(versionDetails != null && versionDetails.size() > 0)
					session.selectedVersionId = versionDetails[0].id as int;
					
				//Get the content details for the selected content id
				contentDetails = ExperimentMasterService.getContentDetailsForContent(session.selectedContentId as int);
			}
			if(contentDetails&&params.contentId!=null)
			 session.selectedContentTypeMode=contentDetails[0].contentMode
			def fbqn,qaList,ssHead,dndqn
			if (session.selectedContentTypeMode == 'Q') {
				session.quizStartTime=new Date()
				questionContentList = QuizMasterService.getAllQuizQuestion(session)
				fbqn = QuizMasterService.rFillBlanksQn(session,'FB')
				dndqn = QuizMasterService.rFillBlanksQn(session,'DND')
				qaList = QuizMasterService.rMatchQnAns(session)
				ssHead = QuizMasterService.rSubSectionHead(session)
			}
			render(view:"/contentHome/editExperiments", model:[contentTypes: contentTypes, contentDetails: contentDetails,
										contentTabDetails: contentTabDetails,versionDetails:versionDetails, 
										experimentList: experimentList , topicList : topicList, 
										subjectList : subjectList, questionContentList : questionContentList, fbqn:fbqn, qaList:qaList, ssHead:ssHead, dndqn:dndqn ]) 
		}else{
			flash.message =  "${message(code: 'default.noSubtopics.message')}"
			render(view:"/contentHome/editExperiments")
		}
		
	}
	
	def saveTextData = {
		//params.versionstatus = 'S','SR','P', session.selectedContentId is the content id to be used
		//save and preview S
		//Save & Send for Review SR
		//Publishing P		
		AvlContentDescriptionVersion contentDescriptionVersion = new AvlContentDescriptionVersion()
		contentDescriptionVersion.contentMode = 'T'
		contentDescriptionVersion.contentDescription = params.editor.encodeAsHTML()
		contentDescriptionVersion.versionStatus = params.versionstatus
		contentDescriptionVersion.userId = session.userId		
		contentDescriptionVersion.dateTime = new Date()
		def contentDetails = ExperimentMasterService.saveContent(contentDescriptionVersion, session.selectedContentId as int,params.versionComments2)
		session.selectedVersionId = null
		redirect(action:index)
	}
	
	def saveLinkData = {
		//params.versionstatus = 'S','SR','P', session.selectedContentId is the content id to be used
		//save and preview S
		//Save & Send for Review SR
		//Publishing P		
		AvlContentDescriptionVersion contentDescriptionVersion = new AvlContentDescriptionVersion()
		contentDescriptionVersion.contentMode = 'L'
		contentDescriptionVersion.contentDescription = params.extLinkText
		contentDescriptionVersion.versionStatus = params.versionstatus
		contentDescriptionVersion.userId = session.userId		
		contentDescriptionVersion.dateTime = new Date()
		def contentDetails = ExperimentMasterService.saveContent(contentDescriptionVersion, session.selectedContentId as int,params.versionComments3)
		session.selectedVersionId = null
		redirect(action:index)
	}

	def editTabIcon = {
		//params.editedimage is the icon name, session.selectedContentId is the content id to be used (get the content type for this content id)
		ExperimentMasterService.editTabIcon(session.selectedContentId as int, params.editedimage)
		flash.message = "Tab Icon changed"
		redirect(action:index)
	}
	
	def deleteTab = {
		//session.selectedContentId is the content id to be used 
		//check whether there is more than one tab for the given subtopic.
		def contentDetailsList=ExperimentMasterService.getTabCount(session.selectedContentId as int)
		if(contentDetailsList!=null && contentDetailsList.size>1)
		{
		   ExperimentMasterService.deleteContentDetails(session.selectedContentId as int)
		   //Remove the contentId from session
		   session.selectedContentId = null;
		   flash.message = "Tab deleted"
		}
		else
		  flash.error = "Can not delete, minimum one tab should be there for this subtopic"
		redirect(action:index)
	}
	
	def editTabName = {
		try
		{
			//params.editTabName is the new name,session.selectedContentId is the content id to be used (get the content type for this content id)
			ExperimentMasterService.editTabName(session.selectedExperimentId as int, 
												session.selectedContentId as int, params.editTabName)
			flash.message = "Tab name changed to ${params.editTabName}"
			redirect(action:index)
		}catch(AvlServiceException ase){
			flash.error = ase.message
			redirect(action:index)
		}
	}
	
	def addTab = {
		try
		{
			ExperimentMasterService.addTab(session.selectedExperimentId as int, params.addTabName, params.selectedImage);
			flash.message = "Tab ${params.addTabName} successfully added"
			redirect(action:index)
		}catch(AvlServiceException ase){
			flash.error = ase.message
			redirect(action:index)
		}

	}
	def publishTab = {
		try{
			ExperimentMasterService.publishTab(session.selectedContentId as int)
			flash.message = "Tab ${params.addTabName} published....it should now appear for the students"
			redirect(action:index)
		} catch (Exception e){
			e.printStackTrace()
		}
	}
	
	def unPublishTab = {
		try{
			ExperimentMasterService.unPublishTab(session.selectedContentId as int)
			flash.message = "Tab ${params.addTabName} un-published. You will not be able to see it in the student's view"
			redirect(action:index)
		} catch (Exception e){
			e.printStackTrace()
		}
	}

	def updateSequence =
	{
		ExperimentMasterService.updateTabSequence(params.sequenceOrder)
		redirect(action:index)
	}

	def addQuestion = {
			if(params.addNewQn.equals("Edit")){
				QuizMasterService.delQn(params.mTextQnId as int)
			}

			session.realPath =  servletContext.getRealPath("/")
			session.contextPath = servletContext.getContextPath()
			if(params.ansType.equals("Section head")){
				def sh = QuizMasterService.setNewSectionHead(session, params)
			}else{
				QuizMasterService.setNewQuizQuestion(session, params)
				flash.message = "Succesfully added question"
			}
			redirect(action:index, params:[contentType:'Q', choiceType: "select"])
    }
	
	def deleteQuestion = {
		def qId = Integer.parseInt(params.qnId)
		if (qId) {
			QuizMasterService.delQn(qId as int)
		}
		session.selectedVersionId = null
		redirect(action:index, params:[contentType:'Q', choiceType: "select"])
	}
	
	def editQuestion = {
		// update the question in all the tables
		def updatedInstance=QuizMasterService.updateQuizQuestion(params,session.userId)
		flash.message="successfully updated question"
     	session.selectedVersionId = null
		redirect(action:index, params:[contentType:'Q', choiceType: "select"])
	}
	
	
	def editQuestionDetails={		
		def editQuestionDetailsList
		if(params.id)
			 editQuestionDetailsList=QuizMasterService.getEditQuestionDetails(params.id as int)
		render (template:"/contentHome/editQuizFormDisplay", model : [editQuestionDetailsList : editQuestionDetailsList,'id':params.id])
	}
	
	def cropImage={
		def number=params.number
		render(view:"/contentHome/cropImage",model:[number:number,"width":300])
	}
	
	def uploadImage={
		def webRootDir = servletContext.getRealPath("/")
		def hostname=request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()
		def serverUploadPath=grailsApplication.config.upload_path
		def quizPath=grailsApplication.config.QuizFolder
		def number=params.number
		def userDir = new File(webRootDir, "/"+serverUploadPath+"/"+quizPath)
		def fileName,filePath
		userDir.mkdirs()
		def uploadedFile = request.getFile('imgName')
		if(!uploadedFile.empty){
		   def path=new File(userDir, uploadedFile.originalFilename)
		   fileName=QuizMasterService.getFileName(path,uploadedFile.originalFilename,uploadedFile,userDir)
		   //def img = ImageIO.read(path);
		   render(view:"/contentHome/cropImage",model:[fileName:fileName,number:number])
		   }
          else
		  {
			   filePath=QuizMasterService.getFilePathAfterCrop(webRootDir,params.x1 as Integer,params.y1 as Integer,params.x2 as Integer,params.y2 as Integer,hostname,serverUploadPath,params.fileName,quizPath)		  
			   render(view:"/contentHome/cropImage",model:[fileName:params.fileName,cropImage:"crop",filePath:filePath,number:number,"width":500])
		  }
	  
	}
	
	def updateSequenceQuiz ={
		QuizMasterService.updateSequence(params.sequenceOrderQuiz)
		redirect(action:index, params:[contentType:'Q', choiceType: "select"])
		
	}
	def updateSequenceFIB={
		QuizMasterService.updateSequenceQuiz(params.sequenceOrderFIB)
		redirect(action:index, params:[contentType:'Q', choiceType: "select"])
	}
	def updateSequenceMF={
		QuizMasterService.updateSequenceQuiz(params.sequenceOrderMF)
		redirect(action:index, params:[contentType:'Q', choiceType: "select"])
	}
	def updateSequenceDNDT={
		QuizMasterService.updateSequenceQuiz(params.sequenceOrderDNDT)
		redirect(action:index, params:[contentType:'Q', choiceType: "select"])
	}
}
