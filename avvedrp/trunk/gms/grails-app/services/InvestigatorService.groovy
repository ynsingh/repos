class InvestigatorService {

	public List getAllInvestigators(String subQuery)
	{  
		def investigatorInstanceList=Investigator.findAll("from Investigator I where I.activeYesNo='Y'"+subQuery)
		return investigatorInstanceList
	}
	   
	public List getInvestigatorsWithParty(def partyId,String subQuery)
	{	   
		def investigatorInstanceList=Investigator.findAll("from Investigator I where I.party.id="+partyId+"and I.activeYesNo='Y'"+subQuery)
		return investigatorInstanceList
	}
	
	public List getInvestigatorsWithParty(def partyId)
	{	   
		def investigatorList=Investigator.findAll("from Investigator I where I.party.id="+partyId+"and I.activeYesNo='Y'")
		return investigatorList
	}
   
   /**
	 * Function to get investigator by id.
	 */
   public getInvestigatorById(def investigatorId)
   {
	   def investigatorInstance = Investigator.get(investigatorId)
	   return investigatorInstance
   }
   
   public List getUniqueEmail(def params)
   {
	   def chkUniqueEmailInstance = Investigator.findAll("from Investigator I where I.email= '" + params.email + "'")
	   return chkUniqueEmailInstance
   }
   
   public List getUniqueName(def params)
   {
	   def chkUniqueNameInstance = Investigator.findAll("from Investigator I where I.name= '" + params.name + "'")
	   return chkUniqueNameInstance
   }
   /**
    * Function to get investigator by department
    */
    public getinvestigatorByDepartment(def department)
   {
    	def investigatorInstance=Investigator.findAll("from Investigator IT where IT.department="+department + " AND IT.activeYesNo='Y'")
    	return investigatorInstance
   }
    /**
	 * Function to get Project Pi Map by id.
	 */
   public getProjectsPIMapById(def investigatorMapId)
   {
	   def projectsPIMapInstance = ProjectsPIMap.get( investigatorMapId )
	   return projectsPIMapInstance
   }
   /*
 	 * Function to save PI Map
 	 */
 	 public savePIMap(def projectsPIMapInstance)
 	{
 		if(projectsPIMapInstance)
 		{
 			projectsPIMapInstance.activeYesNo="Y"
 			def projectPIMapSaveInstance = updatePIMap(projectsPIMapInstance)
	        return projectPIMapSaveInstance
 		}
 	}
 	/*
 	 * Function to update PI Map
 	 */
 	 public updatePIMap(def projectsPIMapInstance)
 	{
 		if(projectsPIMapInstance)
 		{
 			projectsPIMapInstance.save()
	        return projectsPIMapInstance
 		}
 	}
 	/*
  	 * Function to delete PI Map
  	 */
  	 public deletePIMap(def projectsPIMapInstance)
  	{
  		if(projectsPIMapInstance)
  		{
  			projectsPIMapInstance.activeYesNo="N"
  			projectsPIMapInstance=updatePIMap(projectsPIMapInstance)
 	        return projectsPIMapInstance
  		}
  	}
  	/**
 	 * Function to get Project Pi Map by id.
 	 */
    public ProjectsPIMap getProjectsPIMapByProjectsId(def projectsId)
    {
 	   def projectsPIMapInstance = ProjectsPIMap.find("from ProjectsPIMap PM where PM.role='PI' and PM.projects="+projectsId)
 	   return projectsPIMapInstance
    }
}
