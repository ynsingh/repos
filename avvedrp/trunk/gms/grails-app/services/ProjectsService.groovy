import static org.springframework.security.acls.domain.BasePermission.ADMINISTRATION
import static org.springframework.security.acls.domain.BasePermission.DELETE
import static org.springframework.security.acls.domain.BasePermission.READ
import static org.springframework.security.acls.domain.BasePermission.WRITE
import org.springframework.security.acls.domain.PrincipalSid
import org.springframework.security.acls.model.Permission
import org.springframework.security.acls.model.Sid
import org.springframework.dao.DataAccessException
import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.security.acls.model.Permission
import org.springframework.transaction.annotation.Transactional
import grails.plugins.springsecurity.Secured

import groovy.sql.Sql;
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder as SCH
class ProjectsService{


	private aclService
	private aclUtilService
	private objectIdentityRetrievalStrategy
	private sessionFactory
	private springSecurityService
	
	def dataSource 
	
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
     @PreAuthorize("hasPermission(#id, 'Projects', read) or hasPermission(#id, 'Projects', admin)")
	  Projects getProjectById(def id){
		Projects.get id 
		
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
		// updateProjectsPiMap(projectParams)
		def projectsInstance = getProjectById(new Integer(projectParams.id ))
		
        if(projectsInstance) {
        	projectsInstance.modifiedBy = "user";
        	projectsInstance.modifiedDate = new Date();
        	//if(projectParams.parent!=null)	{
	        	//if(projectParams.parent.id.equals("0"))	{
	        		//projectsInstance.parent = null;
	        	  //	System.out.println("Executed")
	        	//}
        	//}
          
        	/* Check whether Projects with same name already exists.*/
        	Integer projectId = checkDuplicateProject(projectParams)
             if(projectId.intValue() == 0 || projectId.intValue() == projectsInstance.id.intValue()){
        	    projectsInstance.properties = projectParams 
        	    
        	    	if(!projectsInstance.hasErrors() && projectsInstance.save()&& checkPIForUpdateProject(projectParams)) {
        	    	
        	    	
        	    		projectsInstance.saveMode = "Updated"
        	    	}
        	    	else
        	    	{
        	    		projectsInstance.saveMode = "NotUpdated"
        	    	}
        	   
        	    
             }
        	else
    		    projectsInstance.saveMode = "Duplicate"
        }
		return projectsInstance
        		   
	}
	
	/**
	 * Function to update sub project.
	 */
	
	public Projects updateSubProject(def projectParams){
		// updateProjectsPiMap(projectParams)
		def projectsInstance = getProjectById(new Integer(projectParams.id ))
		
        if(projectsInstance) {
        	projectsInstance.modifiedBy = "user";
        	projectsInstance.modifiedDate = new Date();
        	         
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
		def userInstanse = Person.get(useriD)
		/* Check whether Projects with same name already exists.*/
		if(checkDuplicateProject(projectsInstance) == 0){
			if(!projectsInstance.hasErrors() && projectsInstance.save()) {
				projectsInstance.saveMode = "Saved"

		    	def projectsPIMapInstance = new ProjectsPIMap();
		    	projectsPIMapInstance.projects = projectsInstance
				projectsPIMapInstance.investigator = projectsInstance.investigator
				projectsPIMapInstance.role = "PI"
				projectsPIMapInstance.activeYesNo="Y"
				projectsPIMapInstance.save()
				
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

		    	
		    	
		    	
		    	def ctx = AH.application.mainContext
				aclService = ctx.aclService
				objectIdentityRetrievalStrategy = ctx.objectIdentityRetrievalStrategy
				sessionFactory = ctx.sessionFactory
				springSecurityService = ctx.springSecurityService
				aclUtilService = ctx.aclUtilService
		    	try{
		    	aclService.createAcl objectIdentityRetrievalStrategy.createObjectIdentity(new Long(1), GrantAllocation.name)
		    	}
		    	catch(Exception e)
		    	{
		    	}
		    	aclUtilService.addPermission grantAllocationInstance, userInstanse.username,    ADMINISTRATION
		    	aclUtilService.addPermission projectsInstance, userInstanse.username,    ADMINISTRATION
		    	
		    	/*calling a method for save project access permission for pi*/
		    	saveAccessPermissionForprojects(grantAllocationInstance,projectsInstance,projectsPIMapInstance.investigator.email)
		    	}
		    	
		    	
		    	
			}
		}
		else
			projectsInstance.saveMode = "Duplicate"
				
	    	
	    	
		return projectsInstance
	}
	/*
	 * save access permission for each user(PI)
	 * */
	public def saveAccessPermissionForprojects(def grantAllocationInstance,def projectsInstance,def userName)
	{
		def ctx = AH.application.mainContext
		aclService = ctx.aclService
		objectIdentityRetrievalStrategy = ctx.objectIdentityRetrievalStrategy
		sessionFactory = ctx.sessionFactory
		springSecurityService = ctx.springSecurityService
		aclUtilService = ctx.aclUtilService
    	try{
    	
    		//aclService.createAcl objectIdentityRetrievalStrategy.createObjectIdentity(new Long(1), GrantAllocation.name)
    		aclUtilService.addPermission grantAllocationInstance, userName,    ADMINISTRATION
        	aclUtilService.addPermission projectsInstance, userName,    ADMINISTRATION
    	}
    	catch(Exception e)
    	{
    	}
    	
    
	}
	/*save access permission for each user(PI) 
	 * during project Pi mapping*/
	public def saveProjectAccessPermissionForPiMap(def projectsId,def investigatorId)
	{
		def grantAllocationService=new GrantAllocationService()
		
		
		def grantAllocationInstanceList = GrantAllocation.findAll("from GrantAllocation GA where GA.projects.id="+projectsId)
		def investigatorInstance=Investigator.get(investigatorId)
		def grant = getGrantAllocationFromAcl(grantAllocationInstanceList)
		
		def projectsInstance = Projects.get(projectsId)
		def grantAllocationInstance=GrantAllocation.get(grant)
		
		def userName = investigatorInstance.email
		saveAccessPermissionForprojects(grantAllocationInstance,projectsInstance,investigatorInstance.email)
	}
	
	/**
	 * function to save project access permision 
	 * (save grantAlloctionInstance for sub allocate project)*/
	
	public def saveProjectAccessPermission(def grantAllocationInstance)
	{
		println "=========saveProjectAccessPermission================"+grantAllocationInstance.party.id
		def projectsInstance = Projects.get(grantAllocationInstance.projects.id)
		def userMapInstance = UserMap.find("from UserMap UM where UM.party.id="+grantAllocationInstance.party.id)
		def userInstance
		def userRoleInstance = UserRole.findAll("from UserRole UR where UR.role.authority='ROLE_SITEADMIN' and UR.user.id in (select UM.user.id from UserMap UM where UM.party.id='"+grantAllocationInstance.party.id +"')")
		if((userRoleInstance.user).size() == 1)
		{
			
			userInstance = Person.get((userRoleInstance.user.id).get(0))
						    	
    	saveAccessPermissionForprojects(grantAllocationInstance,projectsInstance,userInstance.username)
		}
    	return projectsInstance
	}
	
		
	/**
	 * method for (check)deleting project access permission of pi
	 */
	public def checkFordeleteProjectAccessPermissionOfPiMap(def projectsId,def investigatorId)
	{
		def checkFordelete
		 def projectInstance = Projects.get(projectsId)
		def grantAllocationInstance = GrantAllocation.findAll("from GrantAllocation GA where GA.projects.id="+projectsId)
		def investigatorInstance=Investigator.get(investigatorId)
						
		def grantAllocationId = getGrantAllocationFromAcl(grantAllocationInstance,investigatorInstance)
		if(grantAllocationId)
		{
		def grantallocationInstanceValue=GrantAllocation.get(grantAllocationId)
		
		
		deleteProjectAccessPermissionForAll(grantallocationInstanceValue,projectInstance,investigatorInstance.email)
		checkFordelete = true
		}
		else
		{
			checkFordelete = false
		}
	}
	
	/**
	 * Function to get grant allocation from acl using user name and grant allocation id
	 */
	 public def getGrantAllocationIdfromAcl(def projectsId)
	{
		 def ctx = AH.application.mainContext
			aclService = ctx.aclService
			objectIdentityRetrievalStrategy = ctx.objectIdentityRetrievalStrategy
			sessionFactory = ctx.sessionFactory
			springSecurityService = ctx.springSecurityService
			aclUtilService = ctx.aclUtilService
			def value
			
			def grantAllocationInstanceList = GrantAllocation.findAll("from GrantAllocation GA where GA.projects.id="+projectsId)
			
			def aclDomain = org.codehaus.groovy.grails.plugins.springsecurity.acl.AclEntry
			for(grantAllocationInstanceId in grantAllocationInstanceList)
			{
				def aclClassInstance
				try
				{
					aclClassInstance=aclUtilService.readAcl(GrantAllocation,grantAllocationInstanceId.id)
					
				}
				catch(Exception e)
				{}
			if(aclClassInstance)
				{
					value = grantAllocationInstanceId
				}
			
			}
		 println "value  "+value
		 return value
	}
	/**
	 * Function to get grant allocation from acl using user name and grant allocation id
	 */
	public def getGrantAllocationFromAcl(def grantAllocationInstance,def investigatorInstance)
	{
		
		def sql = new Sql(dataSource);
		def result
		def value
 		
 		for(grantId in grantAllocationInstance.id)
 		{
        def query2 = "SELECT AO.object_id_identity,GA.id FROM acl_entry AE,acl_object_identity AO,acl_class AC,acl_sid AD,grant_allocation GA  where"+
				" AE.acl_object_identity=AO.id  "+
				" AND AO.object_id_class=AC.id "+
				" AND AC.class = 'GrantAllocation' "+
				" AND AO.object_id_identity=GA.id"+
				" AND GA.id="+grantId+
				" AND AE.sid=AD.id "+
				"AND AD.sid='"+investigatorInstance.email+"'"+
				"GROUP BY AO.object_id_identity";
        result = sql.rows(query2);
       
        if(result.id)
        {
        	value=result
        	
        }
 		}
		
		def grantAllocationId
		if(value)
		{
		for(id in value.id)
		{
			grantAllocationId=id
			
		}
		}
		else
		{
			grantAllocationId=null
		}
		
		return grantAllocationId
	}
	
	/**
	 * Function to get grant allocation from acl using grant allocation id
	 */
	public def getGrantAllocationFromAcl(def grantAllocationInstance)
	{
		
		def sql = new Sql(dataSource);
		def result
		def value
 		
 		for(grantId in grantAllocationInstance.id)
 		{
        def query2 = "SELECT AO.object_id_identity,GA.id FROM"+ 
        				" acl_object_identity AO,"+
        				" acl_sid AIS,"+
        				" acl_entry AE,"+
        				" acl_class AC,grant_allocation GA WHERE"+ 
        				" AE.acl_object_identity=AO.id"+
        				" AND AO.object_id_class=AC.id"+
        				" AND AC.class='GrantAllocation'"+
        				" AND AO.object_id_identity=GA.id"+
        				" AND GA.id="+grantId+
        				" GROUP BY AO.object_id_identity";
        result = sql.rows(query2);
       
        if(result.id)
        {
        	value=result
        	
        }
 		}
		
		def grantAllocationId
		if(value)
		{
		for(id in value.id)
		{
			grantAllocationId=id
			
		}
		}
		else
		{
			grantAllocationId=null
		}
		
		return grantAllocationId
	}
	/**
	 * Function to deleting project access permission of pi
	 */
	public def deleteProjectAccessPermissionForAll(def grantAllocationInstance,def projectInstance,def userName)
	{
		def ctx = AH.application.mainContext
		aclService = ctx.aclService
		objectIdentityRetrievalStrategy = ctx.objectIdentityRetrievalStrategy
		sessionFactory = ctx.sessionFactory
		springSecurityService = ctx.springSecurityService
		aclUtilService = ctx.aclUtilService
		
		aclUtilService.deletePermission grantAllocationInstance, userName, ADMINISTRATION
    	//deleting project instance from acl
    	aclUtilService.deletePermission projectInstance, userName, ADMINISTRATION 
	}
	
	/**
	 * Function to call method of delete projet and grantallocation (sub project) from acl 
	 */
	public def deleteProjectAccessPermission(def grantAllocationId)
	{
		def accessInstance = null
		def userInstance
		def grantAllocationInstance = GrantAllocation.get(grantAllocationId)
		
		def projectInstance = Projects.get(grantAllocationInstance.projects.id)
		def userRoleInstance = UserRole.findAll("from UserRole UR where UR.role.authority='ROLE_SITEADMIN' and UR.user.id in (select UM.user.id from UserMap UM where UM.party.id='"+grantAllocationInstance.party.id +"')")
		if((userRoleInstance.user).size() == 1)
		{
			userInstance = Person.get((userRoleInstance.user.id).get(0))
		
		
    	deleteProjectAccessPermissionForAll(grantAllocationInstance,projectInstance,userInstance.username)
    	accessInstance=1
		}
		return accessInstance
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
		def projectsInstanceList=Projects.findAll("from Projects P where P.parent.id="+mainProjectId+"and P.activeYesNo='Y'"+subQuery)
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
	/**
	 * Function to Search projects based on criteria.
	 */
	public List searchProjects(def projectsInstance,def Party)
	{
		def grantAllocationInstanceList 
		String subqry ="";
		if(projectsInstance != null)		
		{
			println "projectsInstance = "+projectsInstance
			if(projectsInstance.code != null && projectsInstance.code != "") 
			{
				println "projectsInstance.code  = "+projectsInstance.code 
				subqry = "GA.projects.code like '%"+ projectsInstance.code+ "%'"
				println "subqry code = "+subqry 
			}
			if(projectsInstance.name != null && projectsInstance.name != "")
			{
				println "projectsInstance.name  = "+projectsInstance.name 
				if(!subqry.equals(""))
				{
					subqry = subqry + " AND "
				}
				
				subqry = subqry + "GA.projects.name like '%"+projectsInstance.name+ "%'"
				println "subqry  name= "+subqry 
			}
			if(projectsInstance.projectType != null)
			{
				println "projectsInstance.projectType  = "+projectsInstance.projectType 
				if(!subqry.equals(""))
				{
					subqry = subqry + " AND "
				}
				println "subqry  = "+subqry 
				subqry = subqry + "GA.projects.projectType.id = "+projectsInstance.projectType.id
			}
			/*if(projectsInstance.investigator != null)
			{
				println "projectsInstance.investigator  = "+projectsInstance.investigator 
				if(!subqry.equals(""))
				{
					subqry = subqry + " AND "
				}
				println "subqry  = "+subqry 
				subqry = subqry + "GA.projects.investigator.id = "+ projectsInstance.investigator.id
			}*/
			println "subqry  = "+subqry 
			if(!subqry.equals(""))
			{
				grantAllocationInstanceList = GrantAllocation.findAll("from GrantAllocation GA where GA.party.id = "+ Party +" AND " + subqry)
			}
			else
			{
				
				grantAllocationInstanceList = GrantAllocation.findAll("from GrantAllocation GA where GA.party.id = "+ Party)
			}
	
		}
		
		return grantAllocationInstanceList
	}	
	/**
	* Function to check whether the PI is added for a project.
	*/
	public ProjectsPIMap checkPIofProject(def projectId)
	{		
		def projectsPIMapInstance=ProjectsPIMap.find("from ProjectsPIMap PM where PM.projects="+projectId+" and PM.role = 'PI' and PM.activeYesNo='Y'")
		return projectsPIMapInstance
	}
	
	/**
	* Function to check duplicate entry in projectPiMap.
	*/
	public ProjectsPIMap checkDuplicatePIofProject(def projectsId,def investigatorId)
	{		
		def projectsPIMapInstance=ProjectsPIMap.find("from ProjectsPIMap PM where PM.projects.id="+projectsId+" and PM.investigator.id ="+investigatorId+" and PM.activeYesNo='Y'")
		return projectsPIMapInstance
	}
	
	/**
	* Function to check whether the PI is updated for a project.
	*/
	public def checkPIForUpdateProject(def project)
	{		
		def checkPiInstance
		def projectsPIMapInstanceList=ProjectsPIMap.find("from ProjectsPIMap PM where PM.projects.id="+project.id+" and PM.investigator.id="+project.investigator.id+" and PM.activeYesNo='Y'")
		println "jjjjjjjj"+ projectsPIMapInstanceList
		def projectInstance = Projects.get(project.id)
		def projectsPIMapInstancedelete=ProjectsPIMap.find("from ProjectsPIMap PM where PM.projects="+project.id+" and PM.role = 'PI' and PM.activeYesNo='Y'")
		
		if(!projectsPIMapInstancedelete)
		{
			if(!projectsPIMapInstanceList)
			{
				def projectsPIMapInstance = new ProjectsPIMap();
				projectsPIMapInstance.projects = Projects.get(project.id)
				projectsPIMapInstance.investigator = Investigator.get(project.investigator.id)
				projectsPIMapInstance.role = "PI"
				projectsPIMapInstance.activeYesNo="Y"
				projectsPIMapInstance.save()
				saveProjectAccessPermissionForPiMap(projectInstance.id,projectsPIMapInstance.investigator.id)
				checkPiInstance=true
			}
			else
			{
				println "duplicate entry"
				checkPiInstance=false
			}
		}
		else if(projectsPIMapInstancedelete.investigator.id == Long.parseLong(project.investigator.id))
		{
			println "checkPiInstance true"
			checkPiInstance=true
		}
		else
		{
			if(!projectsPIMapInstanceList)
			{
			
				println projectsPIMapInstancedelete
				projectsPIMapInstancedelete.activeYesNo="N"
				if(checkFordeleteProjectAccessPermissionOfPiMap(projectsPIMapInstancedelete.projects.id,projectsPIMapInstancedelete.investigator.id))
				{	
					projectsPIMapInstancedelete.save()
					def projectsPIMapInstance = new ProjectsPIMap();
					projectsPIMapInstance.projects = Projects.get(project.id)
					projectsPIMapInstance.investigator = Investigator.get(project.investigator.id)
					projectsPIMapInstance.role = "PI"
					projectsPIMapInstance.activeYesNo="Y"
					projectsPIMapInstance.save()
					saveProjectAccessPermissionForPiMap(projectInstance.id,projectsPIMapInstance.investigator.id)
					checkPiInstance=true
				}
				else
				{
					checkPiInstance=false
				}
				
			}
			else
			{
				println "duplicate entry"
				checkPiInstance=false
			}
		}
		return checkPiInstance
	}
}