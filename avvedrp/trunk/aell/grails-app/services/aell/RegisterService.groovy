
package aell
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH


class RegisterService {

    boolean transactional = true
	
    public List getRoleList(){
		
		def roleMasterInstanceList=AvlRoleMaster.findAll("from AvlRoleMaster r where r.status='A' and r.authority not in (:authority)", [authority:['Administrator','ROLE_OPENID']])
		return roleMasterInstanceList
	}
	
	public List getCompleteRoleList(){
        def roleMasterInstanceList=AvlRoleMaster.findAllByStatus('A')
		return roleMasterInstanceList
	}
	
	public List getUniversityList(){
		
		def roleMasterInstanceList=AvlUniversityMaster.getAll()
		return roleMasterInstanceList
	}
	
	void registerNewUser (params) throws AvlServiceException{
		def ctx = AH.application.mainContext
		def springSecurityService=ctx.springSecurityService
		List avlUserMasters = AvlUserMaster.findAllByUsername(params.emailId)
		if(avlUserMasters !=null && avlUserMasters.size()>0){
			AvlServiceException avlexc = new AvlServiceException()
			avlexc.message = "User With This Email-id Already Exist"
			throw avlexc
		}
		def avlUserMasterInstance=new AvlUserMaster()
		avlUserMasterInstance.username=params.emailId
		avlUserMasterInstance.password=springSecurityService.encodePassword(params.paswd)
		avlUserMasterInstance.universityId=params.universityList.id as int
		avlUserMasterInstance.emailId=params.emailId
		if(params.adminRegister=="1"){
		    avlUserMasterInstance.enabled=1
			avlUserMasterInstance.userStatus='A'
		}
		else{
		   avlUserMasterInstance.enabled=0
		   avlUserMasterInstance.userStatus='D'
		}
		if( !avlUserMasterInstance.save()) {
			avlUserMasterInstance.errors.each {
				 println it
			}
		 }
		else
		{
			def userRoleRelInstance=new AvlRoleUserRel()
			userRoleRelInstance.user=avlUserMasterInstance
			userRoleRelInstance.role=AvlRoleMaster.get(params.roleList.id as int)
			if( !userRoleRelInstance.save()) {
				userRoleRelInstance.errors.each {
					 println it
				}
			}
			else
			{
				def avlUserDetailsInstance=new AvlUserDetails(params)
				avlUserDetailsInstance.avlUserMaster=avlUserMasterInstance
				def avlUniversityMasterInstance=AvlUniversityMaster.get(params.universityList.id as int)
				avlUserDetailsInstance.university=avlUniversityMasterInstance.universityName
				if( !avlUserDetailsInstance.save()) {
					avlUserDetailsInstance.errors.each {
						 println it
					}
				 }
			}
		}
	}
}
