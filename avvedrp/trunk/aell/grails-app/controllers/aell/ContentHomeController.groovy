package aell
import org.apache.commons.logging.LogFactory
class ContentHomeController {
	
	def ContentHomeService
	def SubjectMasterService
	def IndexService
	
	private static final log = LogFactory.getLog(this)
	
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
		if(session.user.id!=null)
		   session.roleName=IndexService.getRoleName(session.user.id)
		def universityInstance=ContentHomeService.universityMasterInfo(session.university.id)
		if(universityInstance)
		{
			//If the sectionId is passed in params, fired by onChange of the section list
			if(params.universityId != null)
			{
				session.selectedUniversityId = params.universityId;
				session.selectedSubjectId = null;
				session.selectedTopicId = null;
				session.selectedExperimentId = null;
				session.selectedContentId = null;
				session.selectedVersionId = null;
			}
			// if the page is accessed directly, session.selectedUniversityId will be null and set to the first element in the list
			//Get the university id from params or the first one in the list (if the page is accessed directly)
			def universityId = (session.selectedUniversityId != null && session.selectedUniversityId != "")? session.selectedUniversityId :universityInstance.id
			//Store the universityId in the session (this will be the first one in the list)
			if(session.selectedUniversityId == null)
				session.selectedUniversityId = universityId
			//get the subject master info based on universityId
			def avlSubjectMasterInstanceList =  SubjectMasterService.getSubjectMasterByUnvId(session.selectedUniversityId as int)
			if(avlSubjectMasterInstanceList.size>0)
			   render(view:"/contentHome/index", model:[avlSubjectMasterInstanceList : avlSubjectMasterInstanceList, universityInstance : universityInstance])
			else
			 {
			   render(view:"/contentHome/index", model:[avlSubjectMasterInstanceList : avlSubjectMasterInstanceList, universityInstance : universityInstance])
			  flash.message = "${message(code: 'default.noModule.message')}"
			 }
		}
		else
		{
			render(view:"/contentHome/index")
		}
		
	}
	
	def saveSubject = {
		//flash.message = ""
		if(params.addeditedId != null && params.addeditedId != ""){
			def avlSubjectMaster = AvlSubjectMaster.get( params.addeditedId )
			if(avlSubjectMaster) {
				avlSubjectMaster.properties = params
				avlSubjectMaster.subjectStatus ='A'
				if(!avlSubjectMaster.hasErrors()) {
					SubjectMasterService.updateSubjectMaster(avlSubjectMaster)
					flash.message = "Module -  ${avlSubjectMaster.subjectName}  - successfully updated"
					redirect(action:index)
					
					
				}
				else {
					flash.error = "Failed to Update Subject  ${avlSubjectMaster.subjectName}"
					redirect(action:index)
				}
			}
		}else{
			def avlSubjectMasterInstance = new AvlSubjectMaster()
			avlSubjectMasterInstance.properties = params
			try{
					if(!avlSubjectMasterInstance.hasErrors()) {
						avlSubjectMasterInstance.universityId =  session.university.id
						SubjectMasterService.createSubjectMaster(avlSubjectMasterInstance)
						flash.message = "Module ${avlSubjectMasterInstance.subjectName} - successfully added"
						
					}else
						flash.error = "Failed to Add Subject  ${avlSubjectMasterInstance.subjectName}"
						redirect(action:index)
			}catch(AvlServiceException ase){
				flash.error = ase.message
				redirect(action:index)
			}
			
		}	
	}
	
	def deleteSubject = {
		flash.message = ""
		if(params.deleteId != null && params.deleteId != ""){
			def avlSubjectMaster = AvlSubjectMaster.get( params.deleteId )
			if(avlSubjectMaster) {
				avlSubjectMaster.properties = params
				
				if(!avlSubjectMaster.hasErrors()) {
					avlSubjectMaster.subjectStatus ='D'
					SubjectMasterService.updateSubjectMaster(avlSubjectMaster)
					flash.message = "Module -  ${avlSubjectMaster.subjectName} - successfully Deleted"
					redirect(action:index)
					
					
				}
				else {
					flash.error = "Failed to Deleted Subject  ${avlSubjectMaster.subjectName}"
					redirect(action:index)
				}
			}
		}
	}
	
	def updateSequence = 
	{
		SubjectMasterService.updateSequence(params.sequenceOrder)
		redirect(action:index)
		
	}
	
}
