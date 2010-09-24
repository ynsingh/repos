
class RolePrivilegesService {

    boolean transactional = true

    def serviceMethod() {

    }
    public def saveRolePrivileges(def params,def actionInstanceList,def partyId)
    {
    	def rolePrivilegesStatus = null
    	    	
    	actionInstanceList.each
    	{
    		def rolePrivilegesInstance = new RolePrivileges()
    		
    		rolePrivilegesInstance.controllerName=params.controllerName
    		
    		rolePrivilegesInstance.role=Authority.get(params.role.id)
    		
    		rolePrivilegesInstance.actionName=it
    		
    		rolePrivilegesInstance.party=Party.get(partyId)
    		rolePrivilegesInstance.save()
    		if(rolePrivilegesInstance.save())
    		{
    			
    			rolePrivilegesStatus =rolePrivilegesInstance
    			return rolePrivilegesStatus
    		}
    		else
    		{
    			rolePrivilegesStatus
    		}
    		
    		
    	}
    	return rolePrivilegesStatus
    	
    }
    public def getActionNameFromController(def params,def webRootDir)
    {
    	def rolePrivilegesInstance = RolePrivileges.findAll("from RolePrivileges RP where RP.controllerName='"+params.filename+"'"+" and RP.role="+params.role)
		
    	List actionFileName = new ArrayList()
    	File folder = new File(webRootDir+"WEB-INF/grails-app/views"+params.filename)
    	//File folder = new File("grails-app/views/"+params.filename)
		File[] listofFiles = folder.listFiles()
		for (int i=0;i<listofFiles.length;i++)
		{
			def tempAction=listofFiles[i].getName()
			
			if(tempAction.indexOf('.')>=0)
			{
				if(tempAction.substring(tempAction.indexOf('.'))=='.gsp')
				{
						
						actionFileName.add(tempAction.substring(0,tempAction.lastIndexOf('.')))
				
				}
			}
		}

    	return actionFileName
    }
    public def saveRolePrivilegesForParty(def party,def webRootDir,def roleId)
    {
    	println "saveRolePrivileges For Party ="+roleId
    	def roleInstance
    	if(roleId == null)
    	{	
    		println "roleId null"
    		roleInstance = Authority.findAll("from Authority A")
    	}
    	else 
    	{
    		roleInstance = Authority.get(roleId)
    	}
    	def folder = new File(webRootDir)
    	File[] listofFolder = folder.listFiles()
		for (int i=0;i<listofFolder.length;i++){
			if(listofFolder[i].isFile())
			{
				println "name :- "+listofFolder[i].getName()
			}
			else 
				{
					if(listofFolder[i].getName().indexOf('.')!=0)
					{
						def fileName = new File(webRootDir+listofFolder[i].getName())
		    			File[] listofFiles = fileName.listFiles()
		    			for (int j=0;j<listofFiles.length;j++)
		    			{
		    				def tempAction=listofFiles[j].getName()
		    				
		    				if(tempAction.indexOf('.')>=0)
		    				{
		    					if(tempAction.substring(tempAction.indexOf('.'))=='.gsp')
		    					{
		    							for(authorityInstance in roleInstance)
		    							{
		    							
		    							def rolePrivilegesInstance=new RolePrivileges()
		    							rolePrivilegesInstance.controllerName=listofFolder[i].getName()
		    							rolePrivilegesInstance.actionName=tempAction.substring(0,tempAction.lastIndexOf('.'))
		    							rolePrivilegesInstance.role=authorityInstance
		    							rolePrivilegesInstance.party=party
		    							rolePrivilegesInstance.save()
		    							
		    							}
		    					}
		    				}
		    			}
					}
				}
		}
    	return party
		
    }
    public getRolePrivileges(def controllerName,def actionName,def roleId,def partyId)
    {
    	def rolePrivilegesInstance = RolePrivileges.find("from RolePrivileges RP where RP.controllerName='"+controllerName+"' and RP.actionName='"+actionName+"' and RP.role="+roleId+" and RP.party="+partyId)
    	return rolePrivilegesInstance
    }
}
