
package aell

class UsersService {

    boolean transactional = true

    def serviceMethod() {

    }
	List getUserInformationByUnvId(Integer universityId) {
		def userInformationList=[]
		def userInstanceList = AvlUserMaster.findAllByUniversityId(universityId)
		def usermap=[]
		if(userInstanceList.size() > 0 ) {
			for (i in userInstanceList) {
			  def userid= i.id
			  def userRoleRelInstnace=AvlRoleUserRel.find("from AvlRoleUserRel where user='"+userid+"'")
			 if(userRoleRelInstnace){
			    def roleInstance=AvlRoleMaster.get(userRoleRelInstnace.role.id as int)
				usermap=[id:i.id,username:i.username,email:i.emailId,roleId:roleInstance.id,role:roleInstance.authority,status:i.userStatus]
			 }
			 else
			 usermap=[id:i.id,username:i.username,email:i.emailId,status:i.userStatus]
			 userInformationList.add(usermap)
			 
			}
		}
		return userInformationList
	}
	public def updateUser(params)
	{
		def userMasterInstance=AvlUserMaster.get(params.userId as int)
		if(params.userStatus=='Active'){
		   userMasterInstance.enabled=1
		   userMasterInstance.userStatus='A'
		}
		else if(params.userStatus=='Deactive'){
		   userMasterInstance.enabled=0
		   userMasterInstance.userStatus='D'
		}
		userMasterInstance.universityId=params.universityList.id as int
		if( !userMasterInstance.save()) {
			userMasterInstance.errors.each {
				 println it
			}
		}
		else{
		def  avlRoleUserRelInstance=AvlRoleUserRel.find("from AvlRoleUserRel where user="+params.userId)
		//removing avlRoleUserRelInstance
		if(avlRoleUserRelInstance)
		  avlRoleUserRelInstance.delete(flush:true)
		def userRoleRelInstance=new AvlRoleUserRel()
		userRoleRelInstance.user=AvlUserMaster.get(params.userId as int)
		userRoleRelInstance.role=AvlRoleMaster.get(params.roleList.id as int)
		if( !userRoleRelInstance.save()) {
			userRoleRelInstance.errors.each {
				 println it
			}
		}
		else{
			def avlUserDetailsInstance=AvlUserDetails.find("from AvlUserDetails where avlUserMaster.id=?",[params.userId as long])
			if(avlUserDetailsInstance!=null)
			   avlUserDetailsInstance.properties = params
			else
			   avlUserDetailsInstance=new AvlUserDetails(params) 
			avlUserDetailsInstance.avlUserMaster=userMasterInstance
			def avlUniversityMasterInstance=AvlUniversityMaster.get(params.universityList.id as int)
			avlUserDetailsInstance.university=avlUniversityMasterInstance.universityName
			if( !avlUserDetailsInstance.save()) {
				avlUserDetailsInstance.errors.each {
					 println it
				}
			 }
			else{
				return avlUserDetailsInstance
			}
		}
	 }   
	}
	List getUsersByRoleIdAndUniversityId(selectedRoleId,selectedUtyId){
		def usersList=[]
		def avlRoleUserRelInstanceList=AvlRoleUserRel.findAll("from AvlRoleUserRel as ur where ur.role.id=?",[selectedRoleId])
		def uList=[]
		for(i in avlRoleUserRelInstanceList){
			def userMasterInstance=AvlUserMaster.find("from AvlUserMaster  where id='"+i.user.id+"' and userStatus='A' and universityId='"+selectedUtyId+"'")
			if(userMasterInstance){
			  uList=[uid:i.user.id,uname:userMasterInstance.username]
			  usersList.add(uList)
			}
			}
		return usersList
	}
	
	public void assignPrivilege(Integer utyId,Integer roleId,Integer userId,checkboxList){
		if(checkboxList!=null){
			
			if(checkboxList.toString().lastIndexOf(',')==-1){
			   deleteAccessPrivilege(checkboxList,userId,roleId)
			   saveNewUserSubjectRelInstance(checkboxList as int,userId,roleId)
			}
			else{
				for(int i=0;i<checkboxList.size();i++){
					deleteAccessPrivilege(checkboxList[i],userId,roleId)
				}
			  for(int i=0;i<checkboxList.size();i++){
				saveNewUserSubjectRelInstance(checkboxList[i] as int,userId,roleId)
			  }
			}
		}
		else
		{
			def userSubjectRelInstanceList=AvlUserSubjectAccessRel.findAll("from AvlUserSubjectAccessRel where userId=? and roleId=?",[userId,roleId])
			for(i in userSubjectRelInstanceList){
				def avlUserSubjectAccessRelInstnce= AvlUserSubjectAccessRel.get(i.id)
				avlUserSubjectAccessRelInstnce.delete(flush:true)
			}
		} 
		      
	}
	public void saveNewUserSubjectRelInstance(subjectId,userId,roleId){
		def userSubjectRelInstanceCheck=AvlUserSubjectAccessRel.findBySubjectIdAndUserId(subjectId,userId)
		if(!userSubjectRelInstanceCheck){
		def avlUserSubjectAccessRelInstance=new AvlUserSubjectAccessRel()
		avlUserSubjectAccessRelInstance.userId=userId
		avlUserSubjectAccessRelInstance.subjectId=subjectId
		avlUserSubjectAccessRelInstance.roleId=roleId
		avlUserSubjectAccessRelInstance.save()
		}
	}
	public void deleteAccessPrivilege(subjectId,userId,roleId){
		def userSubjectRelInstanceList=AvlUserSubjectAccessRel.findAll("from AvlUserSubjectAccessRel where userId=? and roleId=?",[userId,roleId])
		for(i in userSubjectRelInstanceList){
			if(i.id!=subjectId){
			   def avlUserSubjectAccessRelInstnce= AvlUserSubjectAccessRel.get(i.id)
			   avlUserSubjectAccessRelInstnce.delete(flush:true)
			}
		}
	}
	def getRoleIdByUserId(userId){
		def roleId
		def  avlRoleUserRelInstance=AvlRoleUserRel.find("from AvlRoleUserRel where user="+userId)
		if(avlRoleUserRelInstance)
		   roleId=avlRoleUserRelInstance.role.id
		return roleId
	}
	def getUserDetailsByUserId(userId){
		def avlUserDetailsInstance=AvlUserDetails.find("from AvlUserDetails where avlUserMaster.id=?",[userId])
		if(avlUserDetailsInstance==null)
		    avlUserDetailsInstance=new AvlUserDetails()
		return avlUserDetailsInstance
	}
	
	// Function to get avlUserMaster table instance using user id
	
	public def getUserInformationById(Integer userId){
		def avlUserMasterInstance=AvlUserMaster.get(userId);
		return avlUserMasterInstance
	}
	
	// Function to update password.
	
	 public AvlUserMaster updatePassword(def newpassword_encoded,def avllUserMasterInstance){
	   avllUserMasterInstance.password=newpassword_encoded
	   if (avllUserMasterInstance.save()) {
		   return avllUserMasterInstance
		}
	}


}
