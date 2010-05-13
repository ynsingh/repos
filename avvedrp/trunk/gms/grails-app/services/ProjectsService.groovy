
class ProjectsService{
	
	/**
	 * Function to get all main projects
	 */
	public List getActiveMainProjects(){
		def projectsInstanceList =Projects.findAll("from Projects P where P.parent=NULL and P.activeYesNo='Y' ")
		return projectsInstanceList
	}
	
	/**
	 * Function to get project by id.
	 */
	public Projects getProjectById(Integer projectId){
		def projectsInstance = Projects.get( projectId )
		return projectsInstance
	}
	
	/**
	 * Function to delete project.
	 */
	public Integer deleteProject(Integer projectId){
		Integer projectDeletedId = null
		def projectsInstance = getProjectById(projectId)
		// check for reference
		def grantAllocationInstance = GrantAllocation.find("from GrantAllocation GA where GA.projects = "+projectId)
        //if(projectsInstance) {
        	if(!grantAllocationInstance)
        	{
        	
            projectsInstance.delete()
            projectDeletedId = projectsInstance.id
        	}
        	else{
        		projectDeletedId = -1
        	}
        	//else
        	//{
        	//	projectDeletedId = null
        	//}
		return projectDeletedId
	}
	
	/**
	 * Function to update project.
	 */
	public Projects updateProject(def projectParams){
		def projectsInstance = getProjectById(new Integer(projectParams.id ))
        if(projectsInstance) {
        	projectsInstance.modifiedBy = "user";
        	projectsInstance.modifiedDate = new Date();
        	if(projectParams.parent!=null)	{
	        	if(projectParams.parent.id.equals("0"))	{
	        		projectsInstance.parent = null;
	        	  	System.out.println("Executed")
	        	}
        	}
          
        	/* Check whether Projects with same name already exists.*/
        	Integer projectId = checkDuplicateProject(projectParams)
        	if(projectId.intValue() == 0 || projectId.intValue() == projectsInstance.id.intValue()){
        	    projectsInstance.properties = projectParams 
        	    if(!projectsInstance.hasErrors() && projectsInstance.save()) {
                	projectsInstance.saveMode = "Updated"
                }
        	}
        	else
    		    projectsInstance.saveMode = "Duplicate"
        }
		return projectsInstance
        		   
	}
	
	/**
	 * Function to save project
	 */
	public Projects saveProjects(def projectsInstance,def useriD){
		/* Check whether Projects with same name already exists.*/
		if(checkDuplicateProject(projectsInstance) == 0){
			if(!projectsInstance.hasErrors() && projectsInstance.save()) {
				projectsInstance.saveMode = "Saved"
				def grantAllocationInstance = new GrantAllocation();
		    	grantAllocationInstance.projects=projectsInstance;
		    	
		    	//if the project is root, creating a default grant allocation for  thr root project
		    	if(projectsInstance.parent==null)
		    	{
		    	def userMap=UserMap.find("from UserMap UM where UM.user.id="+useriD);
		    	
		    	grantAllocationInstance.party=userMap.party
		    	grantAllocationInstance.amountAllocated=0;
		    	grantAllocationInstance.granter=null
		    	grantAllocationInstance.code = "default";
		    	grantAllocationInstance.DateOfAllocation= new Date();
		    	grantAllocationInstance.allocationType="";
		    	grantAllocationInstance.remarks="";
		    	grantAllocationInstance.createdBy=userMap.user.username;
		    	grantAllocationInstance.modifiedBy="";
		    	grantAllocationInstance.sanctionOrderNo="";
		    	grantAllocationInstance.save();	 
		    	}
			}
		}
		else
			projectsInstance.saveMode = "Duplicate"
				
	    	
	    	
		return projectsInstance
	}
	
	/**
	 * Function to check whether project already exists or not.
	 */
	public Integer checkDuplicateProject(projectsInstance){
    	def projectId = 0
    	System.out.println("DuplicateProjects__ "+projectsInstance.code)
    	def chkProjectInstance = Projects.find("from Projects P where P.code= '"+projectsInstance.code+"' and P.activeYesNo='Y'")
    	if(chkProjectInstance)
    		projectId = chkProjectInstance.id
    		
    	return projectId
    }
	
	/**
	 * Function to get active sub projects.
	 */
	public List getActiveSubProjects(Integer mainProjectId,String subQuery){
		def projectsInstanceList=Projects.findAll("from Projects P where P.parent.id="+mainProjectId+""+subQuery)
		return projectsInstanceList
	}
	
	/**
	 * Function to get all project tracking by params
	 */
	public List getAllProjecTracking(def params){
		def projectTrackingInstanceList = ProjectTracking.list( params )
		return projectTrackingInstanceList
	}
	
	/**
	 * Function to get project tracking by id.
	 */
	public ProjectTracking getProjectTrackingById(Integer projectTrackingId){
		def projectTrackingInstance = ProjectTracking.get( projectTrackingId )
		projectTrackingInstance
	}
	
	/**
	 * Function to delete project tracking.
	 */
	public Integer deleteProjectTracking(Integer projectTrackingId){
		Integer projectId = null
		def projectTrackingInstance = getProjectTrackingById( projectTrackingId )
        if(projectTrackingInstance) {
            projectTrackingInstance.delete()
            projectId = projectTrackingInstance.projects.id
        }
		return projectId
	}
	
	/**
	 * Function to get project tracking by project
	 */
	public List getProjectTrackingByProject(def projectsInstance){
		def projectTrackingInstanceList = ProjectTracking.findAllByProjects(projectsInstance)
		return projectTrackingInstanceList
	}
	
	/**
	 * Function to update project tracking.
	 */
	public ProjectTracking updateProjectTracking(def projectTrackingParams){
		def projectTrackingInstance = getProjectTrackingById( new Integer(projectTrackingParams.id ))
        if(projectTrackingInstance) {
        	projectTrackingParams.modifiedBy = "admin"
    		projectTrackingParams.modifiedDate = new Date()
            projectTrackingInstance.properties = projectTrackingParams
            if(!projectTrackingInstance.hasErrors() && projectTrackingInstance.save()) {
            	projectTrackingInstance.saveMode = "Updated"
            }
        }
		return projectTrackingInstance
	}
	
	/**
	 * Function to save project tracking.
	 */
	public ProjectTracking saveProjectTracking(def projectTrackingInstance){
		if(!projectTrackingInstance.hasErrors() && projectTrackingInstance.save()) {
			projectTrackingInstance.saveMode = "Saved"
		}
		return projectTrackingInstance
	}	
}