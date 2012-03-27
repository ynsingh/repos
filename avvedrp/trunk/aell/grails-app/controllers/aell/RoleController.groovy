package aell

class RoleController {
def RoleService
    def index = { 
		def roleList=RoleService.getRoleList()
		[roleList:roleList]
		}
	def saveRole={
		if(params.addeditedId != null && params.addeditedId != ""){
			def avlRoleMaster = AvlRoleMaster.get( params.addeditedId )
			if(avlRoleMaster) {
				avlRoleMaster.properties = params
				avlRoleMaster.status ='A'
				
				if(!avlRoleMaster.hasErrors()) {
					RoleService.updateRoleMaster(avlRoleMaster)
					flash.message = "Role ${avlRoleMaster.authority} successfully updated"
					redirect(action:index)
					
					
				}
				else {
					flash.error = "Failed to Update Subject  ${avlRoleMaster.authority}"
					redirect(action:index)
				}
			}
		}else{
			def avlRoleMasterInstance = new AvlRoleMaster()
			avlRoleMasterInstance.properties = params
			avlRoleMasterInstance.status ='A'
			
			try{
					if(!avlRoleMasterInstance.hasErrors()) {
						RoleService.createRoleMaster(avlRoleMasterInstance)
						flash.message = "Subject ${avlRoleMasterInstance.authority} successfully added"
						
					}else
						flash.error = "Failed to Add Subject  ${avlRoleMasterInstance.authority}"
						redirect(action:index)
			}catch(AvlServiceException ase){
				flash.error = ase.message
				redirect(action:index)
			}
			
		}
	}
	def deleteRole={
		flash.message = ""
		if(params.deleteId != null && params.deleteId != ""){
			def avlRoleMaster = AvlRoleMaster.get( params.deleteId )
			if(avlRoleMaster) {
				avlRoleMaster.properties = params
				if(!avlRoleMaster.hasErrors()) {
					avlRoleMaster.status ='D'
					RoleService.updateRoleMaster(avlRoleMaster)
					flash.message = "Subject ${avlRoleMaster.authority} successfully Deleted"
					redirect(action:index)
					
					
				}
				else {
					flash.error = "Failed to Deleted Role  ${avlRoleMaster.authority}"
					redirect(action:index)
				}
			}
		}
	}
}




