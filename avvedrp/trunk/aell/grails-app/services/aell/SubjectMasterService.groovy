package aell

class SubjectMasterService {
	ContentHomeService contentHomeService
    boolean transactional = true
	
    List getSubjectMasterByUnvId(Integer universityId) {
		def results = AvlSubjectMaster.findAll("from AvlSubjectMaster as b where b.universityId = ? and  b.subjectStatus='A' ",[universityId])
		return results
    }
	List getSubjectMasterByUnvIdAndUserId(Integer universityId,Integer userId){
		def results=[]
		def userSubjectRel=AvlUserSubjectAccessRel.findAll("from AvlUserSubjectAccessRel where userId=?",[userId])
		for(i in userSubjectRel){
			def subjectMasterInstance=AvlSubjectMaster.find("from AvlSubjectMaster where subjectStatus='A' and id=?",[i.subjectId])
			if(subjectMasterInstance)
			 results.add(subjectMasterInstance)
		}
		return results
	}
	List getSubjectMasterByUnvIdAndUserPrivilege(Integer universityId,Integer userId){
		def results=[]
		def subjectMasterList = AvlSubjectMaster.findAll("from AvlSubjectMaster as b where b.universityId = ? and  b.subjectStatus='A' ",[universityId])
		def subjectMap=[]
		for(i in subjectMasterList){
			def userSubjectRel=AvlUserSubjectAccessRel.find("from AvlUserSubjectAccessRel where userId=? and subjectId=?",[userId,i.id])
			if(userSubjectRel)
			   subjectMap=[id:i.id,subjectName:i.subjectName,rel:"true"]
			else
			subjectMap=[id:i.id,subjectName:i.subjectName,rel:""]
			results.add(subjectMap)
		}
		return results
	}
	
	void updateSubjectMaster(AvlSubjectMaster avlSubjectMasterInstance){
		
		//def avlSubjectMasterInstance = AvlSubjectMaster.get(editSubjectMasterInstance.id)
		//avlSubjectMasterInstance.subjectName = avlSubjectMasterInstance.subjectName
		//avlSubjectMasterInstance.subjectDescription = avlSubjectMasterInstance.subjectDescription
		avlSubjectMasterInstance.save()
	}
	
	void createSubjectMaster(AvlSubjectMaster newSubjectMasterInstance) throws AvlServiceException {		
			List avlSubMasters = AvlSubjectMaster.findAllBySubjectNameAndSubjectStatus(newSubjectMasterInstance.subjectName, 'A')
			if(avlSubMasters !=null && avlSubMasters.size()>0){
				AvlServiceException avlexc = new AvlServiceException()
				avlexc.message = "Subject Already Exist"
				throw avlexc
			}
			def  results = getSubjectMasterByUnvId(newSubjectMasterInstance.universityId);
			def lastSubjectMaster
			if(results.size>0){
				lastSubjectMaster = results.last()
			}
			
			newSubjectMasterInstance.subjectStatus = 'A'
			if(lastSubjectMaster != null){
				newSubjectMasterInstance.subjectSequence = lastSubjectMaster.subjectSequence + 1;
			}else {
				newSubjectMasterInstance.subjectSequence = 1
			} 
			newSubjectMasterInstance.save()
			//createUserSubjectAccessRel(new Integer(108), newSubjectMasterInstance.id)
			
			
	}
	
	void createUserSubjectAccessRel(Integer userid, Integer subjectMasterId){
		//TODO add userid
		AvlUserSubjectAccessRel avluser = new AvlUserSubjectAccessRel()
		avluser.userId = userid
		avluser.subjectId = subjectMasterId
		avluser.save()

	}
	
	void updateSequence(String sequenceOrder)
	{
		String[] ids = sequenceOrder.split(",");
		for(int i=1;i<ids.length;i++)
		{
			AvlSubjectMaster subjectMaster = AvlSubjectMaster.get(ids[i-1] as int);
			subjectMaster.subjectSequence = i;
			subjectMaster.save();
		}
	}
	
}
