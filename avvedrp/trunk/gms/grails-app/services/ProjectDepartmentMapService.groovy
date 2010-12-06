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
    public ProjectDepartmentMap[] getProjectDepartmentMapList(def partyId)
    {
    	def projectDepartmentMapList = ProjectDepartmentMap.findAll("from ProjectDepartmentMap PM where PM.projects.id in (select GA.projects.id from GrantAllocation GA where GA.party.id='"+partyId+"') and PM.activeYesNo='Y'")

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
 	 public deleteDepartmentMap(def projectDepartmentMapInstance)
 	{
 		if(projectDepartmentMapInstance)
 		{
 			projectDepartmentMapInstance.activeYesNo="N"
	        projectDepartmentMapInstance=updateDepartmentMap(projectDepartmentMapInstance)
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
  			def projectDepartmentMapSaveInstance =updateDepartmentMap(projectDepartmentMapInstance)
 	        return projectDepartmentMapSaveInstance
  		}
  	}
  	/*
  	 * Function to update department Map
  	 */
  	 public updateDepartmentMap(def projectDepartmentMapInstance)
  	{
  		if(projectDepartmentMapInstance)
  		{
  			projectDepartmentMapInstance.save()
 	        return projectDepartmentMapInstance
  		}
  	}
  	
  	public ProjectDepartmentMap[] chkDuplicatePDMap(def projectDepartmentMapInstance)
  	{
  		def projectDepartmentMapDuplicateInstance = ProjectDepartmentMap.findAll("from ProjectDepartmentMap PM where PM.projects ="+projectDepartmentMapInstance.projects.id+"and PM.partyDepartment="+projectDepartmentMapInstance.partyDepartment.id+" and PM.activeYesNo='Y'")
  		return projectDepartmentMapDuplicateInstance
  	}
     
}
