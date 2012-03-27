package aell

class RoleService {

    boolean transactional = true

    def serviceMethod() {

    }
	List getRoleList()
	{
		def avlRoleMasterList=AvlRoleMaster.findAll("from AvlRoleMaster where status='A'")
		return avlRoleMasterList
	}
	
	void updateRoleMaster(AvlRoleMaster avlRoleMasterInstance){
		avlRoleMasterInstance.save()
	}
	void createRoleMaster(AvlRoleMaster newRoleMasterInstance) throws AvlServiceException {
		List avlRoleMasters = AvlRoleMaster.findAllByAuthorityAndStatus(newRoleMasterInstance.authority,'A')
		if(avlRoleMasters !=null && avlRoleMasters.size()>0){
			AvlServiceException avlexc = new AvlServiceException()
			avlexc.message = "Role Already Exist"
			throw avlexc
		}
		newRoleMasterInstance.save()
     }
	
}


