
class PartyService{
	
	/**
	 * Get Active Institution details based on id
	 */
	public List getPartyBasedOnId(Integer partyId)
	{ 
		def partyInstanceList =Party.findAll( "from Party P where  P.partyType is NULL and P.activeYesNo='Y' and P.id = "+partyId)
		return partyInstanceList
	}
	
	/**
	 * Get All Active institution details 
	 */
	public List getAllActiveParties()
	{ 
		def partyInstanceList =Party.findAll( "from Party P where  P.partyType is NULL and P.activeYesNo='Y'")
		return partyInstanceList
	}
	
	/**
	 * Get party based on id
	 */
	public Party getPartyById(def partyId)
	{
		def partyInstance = Party.get(partyId)
		return partyInstance
	}
	
	/**
	 * Delete party details based on Id
	 */
	public Integer deleteParty(Integer partyId)
	{
		def partyDeletedId = null
		def partyInstance = getPartyById( partyId )
		//check fund allocation by party and grantagency
		def partyFromGrantAllocationInstance = GrantAllocation.findAll("from GrantAllocation GA where GA.party="+partyInstance.id)
		def partyFromGrantAllocationGrantorInstance = GrantAllocation.findAll("from GrantAllocation GA where GA.granter="+partyInstance.id)
		println "partyFromGrantAllocationInstance"+partyFromGrantAllocationInstance
        if((!partyFromGrantAllocationInstance)&& (!partyFromGrantAllocationGrantorInstance))
        {
			if(partyInstance)
			{
				partyInstance.activeYesNo = "N"
				if(partyInstance.save())
	        		partyDeletedId = partyInstance.id
	        	else
	        		partyDeletedId = 0
	        }
		}
        else
        {
        	partyDeletedId = null
        }
		return partyDeletedId
	}
	
	/**
	 * Update party details
	 */
	public Party updateParty(def partyParams){
		def partyInstance = getPartyById( new Integer(partyParams.id ))
        if(partyInstance) {
        	partyInstance.modifiedBy="admin"
            partyInstance.modifiedDate=new Date()
        	
        	/* Check whether Institution with same name already exists.*/
            Integer partyId = checkDuplicateParty(partyParams)
    	    if(partyId.intValue() == 0 || partyId.intValue() == partyInstance.id.intValue())
    	    {
    	    	partyInstance.properties = partyParams
    	    	if(!partyInstance.hasErrors() && partyInstance.save())
	            	partyInstance.saveMode = "Updated"
    	    }
    	    else
    	    	partyInstance.saveMode = "Duplicate"
        }
		return partyInstance
	}
	
	/**
	 * Save party details
	 */
	public Party saveParty(def partyInstance)
	{
		partyInstance.createdBy="admin"
       	partyInstance.createdDate = new Date();
       	partyInstance.modifiedBy="admin"
		
       	/* Check whether Institution with same name already exists.*/
	    if(checkDuplicateParty(partyInstance) == 0)
	    {
           	if(partyInstance.save())
           		partyInstance.saveMode = "Saved"
	    }
	    else
	    {
	    	partyInstance.saveMode = "Duplicate"
	    	partyInstance.id=checkDuplicateParty(partyInstance)
	    }
	    	
    	return partyInstance
	}
	
	/**
	 * Checking whether party exists or not.
	 */
	public Integer checkDuplicateParty(def partyInstance){
    	def partyId = 0
    	System.out.println("DuplicateInstitution__ "+partyInstance.code)
    	def chkPartyInstance = Party.find("from Party P where P.code= '"+partyInstance.code+"' and P.activeYesNo='Y' and P.partyType is NULL ")
    	if(chkPartyInstance)
    		partyId = chkPartyInstance.id
    		
    	return partyId
    }
	
	/**
	 * Getting the list of all active grant agencies.
	 */
	public List getActiveGrantAgency(def params)
	{
		params.partyType = "GA"
		String subQuery ="";
		if(params.sort != null && !params.sort.equals(""))
			subQuery=" order by P."+params.sort
		if(params.order != null && !params.order.equals(""))
			subQuery =subQuery+" "+params.order
		def partyInstanceList =Party.findAll( "from Party P where P.partyType='GA' and P.activeYesNo='Y'"+subQuery ) 
		return partyInstanceList
	}
	
	/**
	 *Checking whether grant agency exists or not.
	 */
	private Integer checkDuplicateGrantAgency(def partyInstance)
	{
    	def grantAgencyId = 0
    	System.out.println("DuplicateGrantAgency__ "+partyInstance.code)
    	def chkPartyInstance = Party.find("from Party P where P.code= '"+partyInstance.code+"' and P.activeYesNo='Y' and P.partyType='GA'")
    	if(chkPartyInstance)
    		grantAgencyId = chkPartyInstance.id
    		
    	return grantAgencyId
    }
	
	/**
	 * Function to update grant agency.
	 */
	public Party updateGrantAgency(def partyParams){
		def partyInstance = getPartyById( new Integer(partyParams.id ))
        if(partyInstance)
        {
        	partyInstance.modifiedBy="admin"
            partyInstance.modifiedDate=new Date()
        	
        	/* Check whether Institution with same name already exists.*/
            Integer partyId = checkDuplicateGrantAgency(partyParams)
    	    if(partyId.intValue() == 0 || partyId.intValue() == partyInstance.id.intValue())
    	    {
    	    	partyInstance.properties = partyParams
	            if(!partyInstance.hasErrors() && partyInstance.save()) 
	            	partyInstance.saveMode = "Updated"
	            
    	    }
    	    else
    	    	partyInstance.saveMode = "Duplicate"
        }
		return partyInstance
	}
	
	/**
	 * Function to save grant agency.
	 */
	public Party saveGrantAgency(def partyInstance)
	{
		partyInstance.partyType="GA" 
		partyInstance.createdBy="admin"
		partyInstance.createdDate = new Date();
		partyInstance.modifiedBy="admin"
		partyInstance.activeYesNo = "Y"
		/* Check whether Institution with same name already exists.*/
	    if(checkDuplicateGrantAgency(partyInstance) == 0)
	    {
           	if(partyInstance.save())
           		partyInstance.saveMode = "Saved"
	    }
	    else
	    	partyInstance.saveMode = "Duplicate"
	    	
    	return partyInstance
	}
	/**
	 * get departments of an institution
	 */
	public PartyDepartment[] getPartyDepartment(def partyId)
	{
		
		def partyDepartmentInstance =  PartyDepartment.findAll("from PartyDepartment P where P.party.id = " +partyId+ "and P.activeYesNo='Y'")
		println "=====partyDepartmentInstance====== " + partyDepartmentInstance 
		return partyDepartmentInstance
	}
	/**
	 * Get All Active institution details 
	 */
	public List getAllActivePartiesAndGrantAgencies()
	{ 
		def partyInstanceList =Party.findAll( "from Party P where P.activeYesNo='Y'")
		return partyInstanceList
	}

	/**
	 * Get All Active menu details 
	 */
	public getAllMenu()
	{
		def menuInstanceList = Menu.findAll("from Menu M where M.activeYesNo='Y' and M.lockedYN='N'")
		return menuInstanceList
	}
	
	/**
	 * Get All Active menu Role Map 
	 */
	public getMapByPartyAndRole(def roleId)
	{
		def menuRoleMapInstanceList = MenuRoleMap.findAll("from MenuRoleMap MRM where MRM.role.id="+roleId+" and MRM.activeYesNo='Y' order by MRM.menu.parentId asc")
		return menuRoleMapInstanceList
	}
	/**
	 * Get All Menu from menu Role Map 
	 */
	public getMenuFromMap(def menuId)
	{
		def menuList =Menu.findAll("from Menu M where M.id="+menuId)
		return menuList
	}
	/**
	 * Get All Active menu Role Map By Menu Order
	 */
	public getMapByPartyAndRoleByOrder(def roleId)
	{
		def menuRoleMapInstanceList = MenuRoleMap.findAll("from MenuRoleMap MRM where MRM.role.id="+roleId+" and MRM.activeYesNo='Y' order by MRM.menu.parentId asc")
		return menuRoleMapInstanceList
	}
	
	/**
	 * Get Parent menu Role Map By Menu Order
	 */
	public getParentListByMenuOrder(def roleId)
	{
		def parentList = MenuRoleMap.findAll("from MenuRoleMap MRM where MRM.role.id="+roleId+" and MRM.menu.parentId="+-1+" and MRM.activeYesNo='Y' order by MRM.menu.menuOrder asc")
		return parentList
	}
	/**
	 * Get Child menu Role Map By Menu Order
	 */
	public getChildListByMenuOrder(def roleId,def parentId)
	{
		def childList = MenuRoleMap.findAll("from MenuRoleMap MRM where MRM.role.id="+roleId+" and MRM.menu.parentId="+parentId+" and MRM.activeYesNo='Y' order by MRM.menu.menuOrder asc")
		return childList
	}
	/**
	 * Get Child menu Role Map 
	 */
	public getChildList(def roleId)
	{
		def childList = MenuRoleMap.findAll("from MenuRoleMap MRM where MRM.role.id="+roleId+" and MRM.menu.parentId!="+-1+" and MRM.activeYesNo='Y' order by MRM.menu.menuOrder asc")
		return childList
	}
	/**
	 * Save menu Role Map 
	 */
	public saveMapInstance(def parentList,def roleId)
	{
		def roleInstance = Authority.get(roleId)
		for(int i=0;i<parentList.size();i++)
		{
			def menuRoleMapInstance = new MenuRoleMap()
	        menuRoleMapInstance.role=roleInstance
			menuRoleMapInstance.createdBy="SuperAdmin"
		    menuRoleMapInstance.createdDate=new Date()
	        menuRoleMapInstance.modifiedBy="SuperAdmin"
	        menuRoleMapInstance.modifiedDate=new Date()
	        menuRoleMapInstance.activeYesNo='Y'
	        menuRoleMapInstance.menu = parentList[i]
			menuRoleMapInstance.save()
		}
	}
	
	/**
	 * Get Main menu List Group by menu Id
	 */
	public getMainMenuGroupById(def roleIds)
	{
		def mainmenuList = MenuRoleMap.findAll("from MenuRoleMap MRM where MRM.role.id in "+roleIds+ "and MRM.menu.parentId=-1 and MRM.activeYesNo='Y' group by MRM.menu.id order by MRM.menu.menuOrder asc")
		return mainmenuList
	}
}