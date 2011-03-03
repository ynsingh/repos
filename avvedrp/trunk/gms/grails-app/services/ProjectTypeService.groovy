class ProjectTypeService
{
	/**
	 * Get all active project types.
	 */
	public List getAllProjectType()
	{
		def projectTypeInstanceList = ProjectType.findAll("from ProjectType PT where PT.activeYesNo ='Y'") 
		return projectTypeInstanceList
	}
	
	/**
	 * Get project type details by id.
	 */
	public ProjectType getProjectTypeById(Integer projectTypeId)
	{
		def projectTypeInstance = ProjectType.get(projectTypeId)
		return projectTypeInstance
	}
	
	/**
	 * Delete project type details. 
	 */
	public Integer deleteProjectType(Integer projectTypeId)
	{
		def projectTypeDeletedId = null
		def projectTypeInstance = getProjectTypeById( projectTypeId )
        if(projectTypeInstance) 
        {
        	/* setting the project type as inactive */
        	projectTypeInstance.activeYesNo="N"
        	projectTypeInstance.save()
            projectTypeDeletedId = projectTypeInstance.id
        }
		return projectTypeDeletedId
	}
	
	/**
	 * Update project type details.
	 */
	public ProjectType updateProjectType(def projectTypeParams)
	{
		/*Getting project type details by id*/
		def projectTypeInstance = getProjectTypeById( new Integer(projectTypeParams.id ))
        if(projectTypeInstance) 
        {
        	projectTypeInstance.modifiedBy="admin"
        	projectTypeInstance.modifiedDate=new Date()
        	projectTypeInstance.properties = projectTypeParams
	    	if(!projectTypeInstance.hasErrors() && projectTypeInstance.save()) 
	    	{
            	projectTypeInstance.saveMode = "Updated"
            }
    	}
		return projectTypeInstance
	}
	
	/**
	 * Save project type details.
	 */
	public ProjectType saveProjectType(def projectTypeInstance)
	{
		projectTypeInstance.createdBy="admin"
        projectTypeInstance.createdDate = new Date();
        projectTypeInstance.modifiedBy="admin"
        
        /*Setting the project type as active*/
        projectTypeInstance.activeYesNo="Y" 
        
		if(projectTypeInstance.save())
    	{
    		projectTypeInstance.saveMode = "Saved"
    	}
	    	return projectTypeInstance
	}
	
	/*
	 * Check Duplicate Type
	 */
	 public checkDuplicateType(def params)
	{
		 def chkProjectTypeInstance = ProjectType.find("from ProjectType PT where PT.type= '"+params.type+"' and PT.activeYesNo='Y'")
		 return chkProjectTypeInstance
	}
	
}