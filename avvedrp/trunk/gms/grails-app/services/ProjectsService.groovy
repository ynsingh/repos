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
import java.text.SimpleDateFormat
import java.text.*;
import java.util.*;
import groovy.sql.Sql;
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder as SCH
class ProjectsService{

	static transactional = false
	private aclService
	private aclUtilService
	private objectIdentityRetrievalStrategy
	private sessionFactory
	private springSecurityService
	def investigatorService
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

/* ===================== 11-11-2010 NEW  FUNCTION TO DELELETE===================  */	
	public Integer deleteProject(def projectParams)
	{
		Integer projectDeletedId = null
		def projectsInstance = getProjectById(new Integer(projectParams.id ))
		Integer projectId = new Integer(projectParams.id)
		def GrantAllocationSplitInstance = GrantAllocationSplit.findAll("from GrantAllocationSplit GAS where GAS.projects = "+projectId)	
		if(!GrantAllocationSplitInstance)		 
		{
	        if(projectsInstance) 
	        {  			
	        	projectsInstance.modifiedBy = "user";
	        	projectsInstance.modifiedDate = new Date();
				projectsInstance.activeYesNo = "N"
				projectsInstance.properties = projectParams
				if(!projectsInstance.hasErrors() && projectsInstance.save()&& checkPIForUpdateProject(projectParams)) 
				{  
					projectDeletedId = projectsInstance.id 	
				}
    	    	else
    	    	{
				 	projectDeletedId = -1    	
				}
			}		   
	        else
	        {     	
	        	return projectDeletedId	        	
	        }
		}     
		else
		{   
			projectDeletedId = 0		
			return projectDeletedId			
		}
	}
		
	
/*==========================================================*/



	
	/**
	 * Function to update project.
	 */
	
	public Projects updateProject(def projectParams){
		def projectsInstance = getProjectById(new Integer(projectParams.id ))
		
        if(projectsInstance) {
        	projectsInstance.modifiedBy = "user";
        	projectsInstance.modifiedDate = new Date();
        	/* Check whether Projects with same name already exists.*/
        	Integer projectId = checkDuplicateProject(projectParams)
             if(projectId.intValue() == 0 || projectId.intValue() == projectsInstance.id.intValue()){
        	    projectsInstance.properties = projectParams 
        	    
        	    	if(!projectsInstance.hasErrors() && projectsInstance.save()&& checkPIForUpdateProject(projectParams)) {
        	    	
        	    		if(projectParams?.projectType.id)
        	    		{
        	    			def suProjectsList = getSubProjectsForClosingMainProject(projectsInstance.id);
        	    			if(suProjectsList)
        	    			{
        	    				for(int i=0;i<suProjectsList.size();i++)
        	    				{
        	    					suProjectsList[i].projectType = projectsInstance.projectType;
        	    					suProjectsList[i].save();
        	    				}
        	    			}
        	    		}
        	    		
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
		def projectsInstance = getProjectById(projectParams.id )
		
        if(projectsInstance) {
        	projectsInstance.modifiedBy = "user";
        	projectsInstance.modifiedDate = new Date();
        	         
        	/* Check whether Projects with same name already exists.*/
        	Integer projectId = checkDuplicateProject(projectParams)
             if(projectId.intValue() == 0 || projectId.intValue() == projectsInstance.id.intValue()){
        	    projectsInstance.properties = projectParams 
        	       
        	    	if(!projectsInstance.hasErrors() && projectsInstance.save() && checkPIsForUpdateProject(projectParams) && checkCOPIsForUpdateProject(projectParams)) {
            	    	
            	    	
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
			projectsInstance.activeYesNo = "Y" //=============11-11-2010
			if(!projectsInstance.hasErrors() && projectsInstance.save()) {
				projectsInstance.saveMode = "Saved"
				def projectsPIMapInstance = new ProjectsPIMap();
		    	projectsPIMapInstance.projects = projectsInstance
				projectsPIMapInstance.investigator = projectsInstance.investigator
				projectsPIMapInstance.role = "PI"
				projectsPIMapInstance.activeYesNo="Y"
				projectsPIMapInstance.save()
				if(projectsInstance.copi)
					{
						def projectsCOPIMapInstance = new ProjectsPIMap();
				    	projectsCOPIMapInstance.projects = projectsInstance
						projectsCOPIMapInstance.investigator = projectsInstance.copi
						projectsCOPIMapInstance.role = "CO-PI"
						projectsCOPIMapInstance.activeYesNo="Y"
						projectsCOPIMapInstance.save()
					}
				def grantAllocationInstance = new GrantAllocation();
		    	grantAllocationInstance.projects=projectsInstance;
		    	

		    	//if the project is root, creating a default grant allocation for  the root project
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
		    	/*save grant Allocation to acl*/
		    	saveGrantAllocationToAcl(grantAllocationInstance,projectsInstance,userInstanse)
		    	/*calling a method for save project access permission for pi*/
		    	saveAccessPermissionForprojects(grantAllocationInstance,projectsInstance,projectsPIMapInstance.investigator.email)
			    }
		    	
			}
		}
		else
			projectsInstance.saveMode = "Duplicate"
				
	    	
	    	
		return projectsInstance
	}
	
	/**
	 * Function to save project with amount allocated
	 */
	public Projects saveProjectsWithAmountAllocated(def projectsInstance,def useriD,params){
		def userInstanse = Person.get(useriD)
		/* Check whether Projects with same name already exists.*/
		if(checkDuplicateProject(projectsInstance) == 0){
			projectsInstance.activeYesNo = "Y" //=============11-11-2010
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
		    	grantAllocationInstance.amountAllocated=new Integer(params.amountAllocated);
		    	grantAllocationInstance.granter=null
		    	grantAllocationInstance.code = "default";
		    	grantAllocationInstance.DateOfAllocation= new Date();
		    	grantAllocationInstance.allocationType="";
		    	grantAllocationInstance.remarks="";
		    	grantAllocationInstance.createdBy=userMap.user.username;
		    	grantAllocationInstance.modifiedBy="";
		    	grantAllocationInstance.sanctionOrderNo="";
		    	grantAllocationInstance.save();	 
		    	/*save grant Allocation to acl*/
		    	saveGrantAllocationToAcl(grantAllocationInstance,projectsInstance,userInstanse)
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
	 * save grant Allocation to acl
	 * */
	public def saveGrantAllocationToAcl(def grantAllocationInstance,def projectsInstance,def userInstanse)
	{
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
		if(grantAllocationInstance)
		{
			saveAccessPermissionForprojects(grantAllocationInstance,projectsInstance,investigatorInstance.email)
		}
		else
		{
			saveAccessPermissionForprojects(grantAllocationInstanceList[0],projectsInstance,investigatorInstance.email)
		}
	}
	
	/**
	 * function to save project access permision 
	 * (save grantAlloctionInstance for sub allocate project)*/
	
	public def saveProjectAccessPermission(def grantAllocationInstance)
	{
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
	  * Function to get active sub projects for closing main project.
	  */
	  
	  public List getSubProjectsForClosingMainProject(def projectId)
	{
		  
		  def subProjectInstanceList = Projects.findAll("from Projects PS where PS.parent.id= "+projectId)  
		  return subProjectInstanceList 
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
	@PostFilter("hasPermission(filterObject, 'read') or hasPermission(filterObject, admin)")
	public List searchProjects(def projectsInstance,def Party,def params)
	{
		def grantAllocationInstanceList 
		String subqry ="";
		String query="";
		if(projectsInstance != null)		
		{
			
			if(projectsInstance.code != null && projectsInstance.code != "") 
			{
				
				subqry = "GA.projects.code like '%"+ projectsInstance.code+ "%'"
				
			}
			if(projectsInstance.name != null && projectsInstance.name != "")
			{
				
				if(!subqry.equals(""))
				{
					subqry = subqry + " AND "
				}
				
				subqry = subqry + "GA.projects.name like '%"+projectsInstance.name+ "%'"
				
			}
			if(projectsInstance.projectType != null)
			{
				
				if(!subqry.equals(""))
				{
					subqry = subqry + " AND "
				}
				
				subqry = subqry + "GA.projects.projectType.id = "+projectsInstance.projectType.id
			}
			if(projectsInstance.investigator?.id != null)
			{
				
				if(!subqry.equals(""))
				{
					subqry = subqry + " AND "
				}
				
				subqry = subqry + "GA.projects in (  select PM.projects from ProjectsPIMap PM where PM.investigator.id = "+ projectsInstance.investigator.id+" and PM.role='PI' and PM.activeYesNo='Y') and GA.projects.activeYesNo='Y'"
			}
			
			if(params.projectStartDate)
			{
				
				if(!subqry.equals(""))
				{
					subqry = subqry + " AND "
				}
				
				if(params.projectStartDateTo)
				{
					subqry = subqry + "DATE_FORMAT(GA.projects.projectStartDate, '%Y-%m-%d') between '"+formatDate(params.projectStartDate)+"' and '"+formatDate(params.projectStartDateTo)+"'"
				}
				else
				{
					subqry = subqry + "DATE_FORMAT(GA.projects.projectStartDate, '%Y-%m-%d') >='"+formatDate(params.projectStartDate)+"'"
				}
			}
			else if(params.projectStartDateTo)
			{
				if(!subqry.equals(""))
				{
					subqry = subqry + " AND "
				}
				subqry = subqry + "DATE_FORMAT(GA.projects.projectStartDate, '%Y-%m-%d') <='"+formatDate(params.projectStartDateTo)+"'"
			}
			if(params.projectEndDate)
			{
				
				
				if(!subqry.equals(""))
				{
					subqry = subqry + " AND "
				}
				
				if(params.projectEndDateTo)
				{
					subqry = subqry + "DATE_FORMAT(GA.projects.projectEndDate, '%Y-%m-%d') between '"+formatDate(params.projectEndDate)+"' and '"+formatDate(params.projectEndDateTo)+"'"
				}
				else
				{
					subqry = subqry + "DATE_FORMAT(GA.projects.projectEndDate, '%Y-%m-%d') >='"+formatDate(params.projectEndDate)+"'"
				}
			}
			else if(params.projectEndDateTo)
			{
				if(!subqry.equals(""))
				{
					subqry = subqry + " AND "
				}
				subqry = subqry + "DATE_FORMAT(GA.projects.projectEndDate, '%Y-%m-%d') <='"+formatDate(params.projectEndDateTo)+"'"
			}
			if(params.projectStatus != "null")
			{
				if(!subqry.equals(""))
				{
					subqry = subqry + " AND "
				}
				subqry = subqry + "GA.projects in (  select PT.projects from ProjectTracking PT where PT.projectStatus = '"+params.projectStatus+"')"
			}
			
			if(!subqry.equals(""))
			{
				grantAllocationInstanceList = GrantAllocation.findAll("from GrantAllocation GA where GA.party.id = "+ Party +" AND GA.projects.activeYesNo='Y' AND " + subqry+" GROUP BY GA.projects")
			}
			else
			{	
				if(params.selectAll)
				{
					grantAllocationInstanceList = GrantAllocation.findAll("from GrantAllocation GA where GA.party.id = "+ Party+" AND GA.projects.activeYesNo='Y' GROUP BY GA.projects")
				}
				else
				{
					grantAllocationInstanceList=[]					
				}
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
	* Function to check whether the Co-PI is added for a project.
	*/
	public ProjectsPIMap checkCOPIofProject(def projectId)
	{
		def projectsCOPIMapInstance=ProjectsPIMap.find("from ProjectsPIMap PM where PM.projects="+projectId+" and PM.role = 'CO-PI' and PM.activeYesNo='Y'")
		return projectsCOPIMapInstance
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
		def projectsPIMapInstance=ProjectsPIMap.find("from ProjectsPIMap PM where PM.projects.id="+project.id+" and PM.investigator.id="+project.investigator.id+" and PM.activeYesNo='Y' and PM.role = 'PI'")
		def projectInstance = Projects.get(project.id)
		def projectsPIMapInstancedelete=ProjectsPIMap.find("from ProjectsPIMap PM where PM.projects="+project.id+" and PM.role = 'PI' and PM.activeYesNo='Y'")
		def projectsPIMapCoPIInstancedelete = ProjectsPIMap.find("from ProjectsPIMap PM where PM.projects.id="+project.id+" and PM.investigator.id="+project.investigator.id+" and PM.activeYesNo='Y' and PM.role = 'CO-PI'")
		if(projectsPIMapInstance)
		{
			checkPiInstance=true
		}
		else
		{
			if(!projectsPIMapInstancedelete)
			{	
				if(!projectsPIMapCoPIInstancedelete)
				{
					//Assign to project to PI and giving project access permission
					savePIMapInstance(project,projectInstance)					
					checkPiInstance=true
				}
				else
				{
					projectsPIMapCoPIInstancedelete.activeYesNo="N"
						if(checkFordeleteProjectAccessPermissionOfPiMap(projectsPIMapCoPIInstancedelete.projects.id,projectsPIMapCoPIInstancedelete.investigator.id))
						{	
							projectsPIMapCoPIInstancedelete.save()
							//Assign to project to PI and giving project access permission
							savePIMapInstance(project,projectInstance)
							checkPiInstance=true
						}
						else
						{
							checkPiInstance=false	
						}
				}
			}
			else
			{
				if(!projectsPIMapCoPIInstancedelete)
				{
					projectsPIMapInstancedelete.activeYesNo="N"
						if(checkFordeleteProjectAccessPermissionOfPiMap(projectsPIMapInstancedelete.projects.id,projectsPIMapInstancedelete.investigator.id))
						{	
							projectsPIMapInstancedelete.save()
							//Assign to project to PI and giving project access permission
							savePIMapInstance(project,projectInstance)
							checkPiInstance=true
						}
						else
						{
							checkPiInstance=false
						}
				}
				else
				{
					projectsPIMapCoPIInstancedelete.activeYesNo="N"
					if(checkFordeleteProjectAccessPermissionOfPiMap(projectsPIMapCoPIInstancedelete.projects.id,projectsPIMapCoPIInstancedelete.investigator.id))
					{	
						projectsPIMapCoPIInstancedelete.save()
						
						projectsPIMapInstancedelete.activeYesNo="N"
							if(checkFordeleteProjectAccessPermissionOfPiMap(projectsPIMapInstancedelete.projects.id,projectsPIMapInstancedelete.investigator.id))
							{	
								projectsPIMapInstancedelete.save()
								//Assign to project to PI and giving project access permission
								savePIMapInstance(project,projectInstance)
								checkPiInstance=true
							}
							else
							{
								checkPiInstance=false	
							}
					}
					else
					{
						checkPiInstance=false	
					}
				}
				
			}	
		}
		return checkPiInstance
	}
	
	public def checkPIsForUpdateProject(def project)
	{
		def checkPiInstance
		def projectsPIMapInstance=ProjectsPIMap.find("from ProjectsPIMap PM where PM.projects.id="+project.id+" and PM.investigator.id="+project.investigator.id+" and PM.activeYesNo='Y' and PM.role = 'PI'")
		def projectInstance = Projects.get(project.id)
		def projectsPIMapInstancedelete=ProjectsPIMap.find("from ProjectsPIMap PM where PM.projects="+project.id+" and PM.role = 'PI' and PM.activeYesNo='Y'")
		if(projectsPIMapInstance)
		{
			checkPiInstance=true
		}
		else
		{
			projectsPIMapInstancedelete.activeYesNo="N"
			if(checkFordeleteProjectAccessPermissionOfPiMap(projectsPIMapInstancedelete.projects.id,projectsPIMapInstancedelete.investigator.id))
			{	
				projectsPIMapInstancedelete.save()
				//Assign to project to PI and giving project access permission
				savePIMapInstance(project,projectInstance)
				checkPiInstance=true
			}
			else
			{
				checkPiInstance=false
			}
		}
	}
	
	public def checkCOPIsForUpdateProject(def project)
	{
	
		def checkPiInstance
		def projectsCOPIMapInstance=ProjectsPIMap.find("from ProjectsPIMap PM where PM.projects.id="+project.id+" and PM.investigator.id="+project.copi.id+" and PM.activeYesNo='Y' and PM.role = 'CO-PI'")
		def projectInstance = Projects.get(project.id)
		def projectsCOPIMapInstancedelete=ProjectsPIMap.find("from ProjectsPIMap PM where PM.projects="+project.id+" and PM.role = 'CO-PI' and PM.activeYesNo='Y'")
		if(projectsCOPIMapInstance)
		{
			checkPiInstance=true
		}
		else
		{
			projectsCOPIMapInstancedelete.activeYesNo="N"
			if(checkFordeleteProjectAccessPermissionOfPiMap(projectsCOPIMapInstancedelete.projects.id,projectsCOPIMapInstancedelete.investigator.id))
			{	
				projectsCOPIMapInstancedelete.save()
				//Assign to project to PI and giving project access permission
				saveCoPIMapInstance(project,projectInstance)
				checkPiInstance=true
			}
			else
			{
				checkPiInstance=false
			}
		}
	}
	/**
	 * Assign to project to PI and giving project access permission
	 */
	public def savePIMapInstance(def project,def projectInstance)
	{
		def projectsPIMapInstance = new ProjectsPIMap();
		projectsPIMapInstance.projects = Projects.get(project.id)
		projectsPIMapInstance.investigator = Investigator.get(project.investigator.id)
		projectsPIMapInstance.role = "PI"
		projectsPIMapInstance.activeYesNo="Y"
		projectsPIMapInstance.save()
		saveProjectAccessPermissionForPiMap(projectInstance.id,projectsPIMapInstance.investigator.id)
	}
	
	/**
	 * Assign to project to PI and giving project access permission
	 */
	public def saveCoPIMapInstance(def project,def projectInstance)
	{
		def projectsCOPIMapInstance = new ProjectsPIMap();
		projectsCOPIMapInstance.projects = Projects.get(project.id)
		projectsCOPIMapInstance.investigator = Investigator.get(project.copi.id)
		projectsCOPIMapInstance.role = "CO-PI"
		projectsCOPIMapInstance.activeYesNo="Y"
		projectsCOPIMapInstance.save()
		saveProjectAccessPermissionForPiMap(projectInstance.id,projectsCOPIMapInstance.investigator.id)
	}
	
	
	public def formatDate(def dateValue)
	{
		println "dateValue "+dateValue
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		def formattedDate=formatter.parse(dateValue);
		println "formattedDate "+formattedDate
		DateFormat newFormatter = new SimpleDateFormat("yyyy-MM-dd");
		def formattedDateValue=newFormatter.format(formattedDate)
		
		return formattedDateValue
	}
	/**
	 * method to get grant search result
	 */
	
	public def getGrantSearchResult(def params,def Party)
	{
		println "grant search result"+params
		def grantAllocationInstanceList 
		String subqry ="";
		String query="";
		if(params.minimumAmount != "")
		{
			
			if(!subqry.equals(""))
			{
				subqry = subqry + " AND "
			}
			if(params.maximumAmount != "")
			{
				subqry = subqry + "GA.amountAllocated between "+params.minimumAmount+" and "+params.maximumAmount
			}
			else
			{
				subqry = subqry + "GA.amountAllocated >= "+params.minimumAmount
			}
		}
		else if(params.maximumAmount != "")
		{
			if(!subqry.equals(""))
			{
				subqry = subqry + " AND "
			}
			subqry = subqry + "GA.amountAllocated <= "+params.maximumAmount
		}
		if(params.granter.id != "null")
		{
			
			if(!subqry.equals(""))
			{
				subqry = subqry + " AND "
			}
			
			subqry = subqry + "GA.granter.id = "+params.granter.id
		}
		if(params.investigator.id != "null")
		{
			
			if(!subqry.equals(""))
			{
				subqry = subqry + " AND "
			}
			
			subqry = subqry + "GA.projects in (  select PM.projects from ProjectsPIMap PM where PM.investigator.id = "+ params.investigator.id+" and PM.role='PI' and PM.activeYesNo='Y') and GA.projects.activeYesNo='Y'"
		}
		if(params.partyDepartment != "null")
		{
			
			if(!subqry.equals(""))
			{
				subqry = subqry + " AND "
			}
			
			subqry = subqry + "GA.projects in (  select PM.projects from ProjectDepartmentMap PM where PM.partyDepartment.id = "+ params.partyDepartment+" and PM.activeYesNo='Y')"
		}
		if(params.awardedDateFrom)
		{
			
			if(!subqry.equals(""))
			{
				subqry = subqry + " AND "
			}
			
			if(params.awardedDateTo)
			{
				subqry = subqry + "DATE_FORMAT(GA.dateOfAllocation, '%Y-%m-%d') between '"+formatDate(params.awardedDateFrom)+"' and '"+formatDate(params.awardedDateTo)+"'"
			}
			else
			{
				subqry = subqry + "DATE_FORMAT(GA.dateOfAllocation, '%Y-%m-%d') >='"+formatDate(params.awardedDateFrom)+"'"
			}
		}
		else if(params.awardedDateTo)
		{
			if(!subqry.equals(""))
			{
				subqry = subqry + " AND "
			}
			subqry = subqry + "DATE_FORMAT(GA.dateOfAllocation, '%Y-%m-%d') <='"+formatDate(params.awardedDateTo)+"'"
		}
		
		if(!subqry.equals(""))
		{
			grantAllocationInstanceList = GrantAllocation.findAll("from GrantAllocation GA where GA.party.id = "+ Party +" AND GA.projects.activeYesNo='Y' AND GA.amountAllocated !='0' AND " + subqry)
		}
		else
		{	
			if(params.selectAll)
			{
				grantAllocationInstanceList = GrantAllocation.findAll("from GrantAllocation GA where GA.party.id = "+ Party+" AND GA.projects.activeYesNo='Y' AND GA.amountAllocated !='0'")
			}
			else
			{
				grantAllocationInstanceList=[]
			}
		}
		return grantAllocationInstanceList
	}
	
	/*Method to get project details with selected projectType */
	public List getProjectByProjectType(def params)
	{
		def ProjectsInstance = Projects.findAll("from Projects PR where PR.projectType="+params.id)
		return ProjectsInstance
	}
	
	/*Method to get closed projects by using grantAllocation details */
	
	public def getClosedProjects(def grantAllocProjectId)
	{
		
	 def closedProjectInstance = ProjectTracking.find("from ProjectTracking PT where PT.projectStatus = 'Closed' and PT.projects.id = "+grantAllocProjectId )
		return closedProjectInstance
	}
	
	/* To get active project list by project id*/
	
	public Projects getActiveProjectById(def projectId)
	{
		def projectInstance = Projects.find("from Projects P where P.activeYesNo='Y' and P.id ="+ projectId)
		return projectInstance
	}
	
	/* To get the upperlimit for pagination */
	public Integer findUpperIndex(int offset, int max, int total) {
	    max = offset + max - 1
	    if (max >= total) {
	      max -= max - total + 1
	    }
	    return max
	  }
	/*
	 * method to save new projects
	 */
	 public def saveNewProjects(def projectsInstance)
	{
		projectsInstance.createdBy=''
		projectsInstance.modifiedBy=''
		projectsInstance.createdDate=new Date()
		projectsInstance.modifiedDate=new Date()
		 /* Check whether Projects with same name already exists.*/
		if(checkDuplicateProject(projectsInstance) == 0)
		{
			if(!projectsInstance.hasErrors() && projectsInstance.save()) 
			{
				projectsInstance.saveMode = "Saved"
			}
		}
		else
		{
			projectsInstance.saveMode = "Duplicate"
		}
		 return projectsInstance
	}
	
	 public List getNotEndedProjects(def currentDate)
	 {
	    def projectInstanceList = Projects.findAll("from Projects P where P.activeYesNo='Y' and DATE_FORMAT(P.projectEndDate, '%Y-%m-%d')>='"+currentDate+"'")
	    return projectInstanceList
	 }
	 /*
	  * Get project Instance by code.
	  */
	 public getProjectInstanceByCode(def code)
	 {
		 def projectsInstance =Projects.find("from Projects P where P.code ='"+code+"'")
		 return projectsInstance
	 }
	 
}
