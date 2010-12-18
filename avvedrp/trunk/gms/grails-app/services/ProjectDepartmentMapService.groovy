class ProjectDepartmentMapService {

    boolean transactional = true
/*
 * Function to get Party Department by Party Id
 */
    public PartyDepartment[] getPartyDepartmentForUser(String partyId)
    {
    	def partyDepartmentList
    	if(partyId)
    		partyDepartmentList = PartyDepartment.findAll("from PartyDepartment PD where PD.party.id="+partyId+"and PD.activeYesNo='Y'")
    	return partyDepartmentList;
    }
    /*
     * Function to get ProjectDepartmentMap by projects of the Party
     */
    public ProjectDepartmentMap[] getProjectDepartmentMapList(def partyId,def projectId)
    {
    	def projectDepartmentMapList = ProjectDepartmentMap.findAll("from ProjectDepartmentMap PM where PM.projects.id = "+projectId+ " and PM.activeYesNo='Y'")

    	return projectDepartmentMapList
    }
    /*
     * Function to get Project Department Map using Party Department
     */
    public getProjectDepartmentMapByPartyDepartment(def partyDepartment)
    {
    	def ProjectDepartmentMapInstance=ProjectDepartmentMap.findAll("from ProjectDepartmentMap PD where PD.partyDepartment="+partyDepartment + " AND PD.activeYesNo='Y'")
		return ProjectDepartmentMapInstance
    }
    /*
     * Function to get projectDepartmentMap Instance by projectDepartmentMap Id
     */
     public getProjectDepartmentMapById(def projectDepartmentId)
    {
    	 def projectDepartmentMapInstance = ProjectDepartmentMap.get(projectDepartmentId)
         return projectDepartmentMapInstance
    }
     /*
 	 * Function to delete department Map
 	 */
 	 public deleteDepartmentMap(def projectDepartmentMapParams)
 	{
 		
 	def projectDepartmentMapInstance = 	getProjectDepartmentMapById( new Integer(projectDepartmentMapParams.id ))
    if(projectDepartmentMapInstance)
 		{
 			projectDepartmentMapInstance.activeYesNo="N"
	        projectDepartmentMapInstance=updateDepartmentMap(projectDepartmentMapParams)
	        return projectDepartmentMapInstance
 		}
 	}
 	/*
  	 * Function to save department Map
  	 */
  	 public saveDepartmentMap(def projectDepartmentMapInstance)
  	{
  		if(projectDepartmentMapInstance)
  		{
  			projectDepartmentMapInstance.activeYesNo="Y"
  				if(projectDepartmentMapInstance.save())
  					{
  					projectDepartmentMapInstance.saveMode = "Saved"
  			    	}
  				    	
  			    	return projectDepartmentMapInstance
  				}
  			
  		}
  
  	/*
  	 * Function to update department Map
  	 */
  	 public updateDepartmentMap(def projectDepartmentMapParams)
  	{
  		def projectDepartmentMapInstance = getProjectDepartmentMapById( new Integer(projectDepartmentMapParams.id ))
        if(projectDepartmentMapInstance) 
        {
        	
        	projectDepartmentMapInstance.properties = projectDepartmentMapParams
        	if(!projectDepartmentMapInstance.hasErrors() && projectDepartmentMapInstance.save())
	    	{
        		projectDepartmentMapInstance.saveMode = "Updated"
            }
    	}
		return projectDepartmentMapInstance
	}
 
  	
  	public ProjectDepartmentMap[] chkDuplicatePDMap(def projectDepartmentMapInstance)
  	{
  		
  		def projectDepartmentMapDuplicateInstance = ProjectDepartmentMap.findAll("from ProjectDepartmentMap PM where PM.projects ="+projectDepartmentMapInstance.projects.id+"and PM.partyDepartment="+projectDepartmentMapInstance.partyDepartment.id+" and PM.activeYesNo='Y'")
  		return projectDepartmentMapDuplicateInstance
  	}
  	
  	
}
