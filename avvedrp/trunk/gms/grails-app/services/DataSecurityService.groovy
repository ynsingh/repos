import static org.springframework.security.acls.domain.BasePermission.ADMINISTRATION
import static org.springframework.security.acls.domain.BasePermission.DELETE
import static org.springframework.security.acls.domain.BasePermission.READ
import org.springframework.security.acls.domain.PrincipalSid
import org.springframework.security.acls.model.Permission
import org.springframework.security.acls.model.Sid
import org.springframework.dao.DataAccessException

import grails.plugins.springsecurity.Secured
import groovy.sql.Sql;


import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import static org.springframework.security.acls.domain.BasePermission.ADMINISTRATION
import static org.springframework.security.acls.domain.BasePermission.DELETE
import static org.springframework.security.acls.domain.BasePermission.READ
import static org.springframework.security.acls.domain.BasePermission.WRITE
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder as SCH
class DataSecurityService {
	private aclService
	private aclUtilService
	private objectIdentityRetrievalStrategy
	private sessionFactory
	private springSecurityService
	def dataSource 

	/**
	 * Function to get all grant allocations for the login user.
	 */
	public GrantAllocation[] getGrantAllocationsForLoginUser(String projectId,String partyId){
		 
		 def grantAllocationInstanceList = []
		 def grantAllocationInstanceInitialList=GrantAllocation.findAll("from GrantAllocation GA where GA.projects in "+projectId+" and GA.party in "+partyId);
		 
		 /* Check the grant allocation status in grant allocation tracking */
		 for(grantAllocationInstance in grantAllocationInstanceInitialList){
			 def grantAllocationTrackingList = GrantAllocationTracking.findAllByGrantAllocationAndGrantAllocationStatusNotEqual(grantAllocationInstance,"Open")
			 if(!grantAllocationTrackingList)
				 grantAllocationInstanceList.add(grantAllocationInstance)
		 }
		 
		 return grantAllocationInstanceList
	}
	
	/**
	 * Function to get all grant allocations for the login user by allocation type.
	 */
	 public GrantAllocation[] getGrantAllocationsForLoginUser(String allocationType,String projectId,String partyId){
		 def subQry = "";
		 if(allocationType != null){
			 subQry = " and GA.allocationType='"+allocationType+"' "
		 }
		 def grantAllocationInstanceList=GrantAllocation.findAll("from GrantAllocation  GA where  "+
				 " GA.projects in "+projectId+" and GA.party in"+partyId+subQry);
		 
		 return grantAllocationInstanceList
	}
	
	
	
	/**
	 * Function to get all projects assigned to login user.
	 */
	public GrantAllocation[] getProjectsFromGrantAllocationForLoginUser(String partyId){
		def grantAllocationList
		
		
		if(partyId!=null)
	     grantAllocationList=GrantAllocation.findAll("from GrantAllocation GA where GA.party in "+partyId+" and GA.projects in(select P.id from Projects P where P.activeYesNo='Y') group by GA.projects");
		return grantAllocationList
	}
	
	
	/**
	 * Function to check for Authrized User In ProjectDomain.
	 */
	public GrantAllocation[] getProjectsFromGrantAllocationForLoginUser(String partyId,String projectID){
		 def grantAllocationList
			if(partyId!=null)
		        grantAllocationList=GrantAllocation.findAll("from GrantAllocation GA where GA.party in "+partyId+" and  GA.projects in ('"+projectID+"')");
				
		return grantAllocationList
	}
	
	/**
	 * Function to get all projects assigned to login user.
	 */
	public Projects[] getProjectsForLoginUser(String partyId){
		
		def grantAllocationlist
		if(partyId!=null)
		  grantAllocationlist=GrantAllocation.findAll(" from GrantAllocation GA where GA.party in "+partyId+" and GA.projects.activeYesNo='Y' group by GA.projects  ");
		def projectsList=[]
		for(int i=0;i<grantAllocationlist.size();i++)
		{
			projectsList.add(grantAllocationlist[i].projects)
			println projectsList
		}
				
		println projectsList
		return projectsList
	}
	/**
	 * Function to get all parties assigned to login user.
	 */
	public Party[] getPartiesOfLoginUser(String partyId){
		def partyInstanceList = Party.findAll("from Party P where P.activeYesNo='Y' and P.id in " +partyId)
		return partyInstanceList
	}
	
	/**
	 * Function to get parties with party type null.
	 */
	public List getPartiesWithoutPartyTypeOfLoginUser(String partyId,String subQuery){
		def partyInstanceList=Projects.find("from Party P where P.partyType is NULL and P.activeYesNo='Y' and P.id in " +partyId+subQuery  )
		return partyInstanceList
	}
	
	/**
	 * Function to get user map by projects and parties of login user.
	 */
	public List getUserMapByProjectsAndPartiesOfLoginUser(String projectId,String partyId){
		def userMapInstanceList=UserMap.findAll("from UserMap UM where UM.projects in " +projectId +" and UM.party in " +partyId )
		return userMapInstanceList
	}
	
	/**
	 *  Function to get projects and subprojects of login user 
	 */
	 public List getProjectsAndSubProjectsOfLoginUser(String role,String projectId){
    	def projectsInstanceList
    	if(role.equals('ROLE_ADMIN'))
        {
    		projectsInstanceList = Projects.findAll("from Projects P where P.activeYesNo='Y' order by P.code" )
        }
    	else
    	{
    		projectsInstanceList=Projects.findAll("from Projects P where P.activeYesNo='Y' and P.id in " +projectId+" or P.parent.id in "+projectId+" order by P.code" )
    	}
    	return projectsInstanceList
    }
	
    /**
     *  Function to get parties and sub parties allocated to login user 
     */
     public List getPartiesAndSubPartiesOfLoginUser(String role,String partyId){
    	
    	def partyInstanceList
    	if(role.equals('ROLE_ADMIN'))
        {
    		partyInstanceList = Party.findAll("from Party P where P.activeYesNo='Y' order by P.code" )
        }
    	else
    	{ 
    		partyInstanceList=Party.findAll("from Party P where P.activeYesNo='Y' and P.id in " +partyId+" or P.parent.id in "+ partyId +" order by P.code")
    	}

    	return partyInstanceList
    }
    
     /**
      *  Function to get users assigned to projects 
      */
     public List getUsersAssignedToProjects(String role,String projectId){
     	String projectIds = "("
     		
     	/* Find all projects id(projects of login user and sub projects) */
     	def projectsInstanceList = getProjectsAndSubProjectsOfLoginUser(role,projectId)
     	for(int i=0;i<projectsInstanceList.size();i++){
     		def projectInstance = (Projects)projectsInstanceList.get(i)
     		projectIds = projectIds+projectInstance.id+","
     	}
     	projectIds = projectIds.substring(0,projectIds.length()-1)
     	projectIds = projectIds+")"
     	System.out.println("*******************Inside getUsersAssignedToProjects**")
     	System.out.println("projectIds  "+projectIds)
     	
     	/* Get all users assigned to project*/
     	def userMapInstanceList = UserMap.findAll("from UserMap UM where UM.projects in"+projectIds)
     		
     	return userMapInstanceList
     }
     
     /* Function to get users of assigned projects */
     public List getUsersOfAssignedProjects(String role,String projectId){
     	def userInstanceList = new ArrayList()
     	String projectIds = "("
     		
     	/* Find all projects id(projects of login user and sub projects) */
     	def projectsInstanceList = getProjectsAndSubProjectsOfLoginUser(role,projectId)
     	
     	if(role.equals('ROLE_ADMIN'))
             {
     		userInstanceList = User.findAllByEnabled(true)
             }
     	else
     	{
     	for(int i=0;i<projectsInstanceList.size();i++){
     		def projectInstance = (Projects)projectsInstanceList.get(i)
     		projectIds = projectIds+projectInstance.id+","
     	}
     	projectIds = projectIds.substring(0,projectIds.length()-1)
     	projectIds = projectIds+")"
     	System.out.println("******************************Inside userInstanceList*********************")
     	System.out.println("projectIds  "+projectIds)
     	
     	/* Get all enabled users */
     	def userInstanceInitialList=User.findAllByEnabled(true)
     	for(int i=0;i<userInstanceInitialList.size();i++){
     		def userInstance = (Person)userInstanceInitialList.get(i)
     		
     		/* Check whether user is assigned any project */
     		if(!UserMap.findByUser(userInstance)){
     			System.out.println("User not in usermap  "+userInstance.id)
     			userInstanceList.add(userInstance)
     		}
     		else{
     			/* If user is assigned projects, check whether the projects are same as of login user 
     			 * or its sub projects
     			 */
     			def userMapInstance = UserMap.find("from UserMap UM where UM.projects in"+projectIds+" and UM.user = "+userInstance.id)
     			if(userMapInstance){
     				userInstanceList.add(userInstance)
     				System.out.println("User in usermap  "+userInstance.id)
     			}
     		}
     	}
     	}
     	return userInstanceList
     }
     
     /**
      * Function to get user of login user.
      */
     public Person getUserOfLoginUser(def userId){
    	 def person = Person.get(userId)
    	 return person
     }
     
     
     
     /**
 	 * Function to check whether the user has access to the given projects
 	 */
 	public int checkForAuthorisedAcsessInProjects(def projectId,def partyId)
     {
    	
    	 //User will  have access to project only if that projects has  grant allocation  
 		 def grantAllocationInstance= GrantAllocation.findAll("from GrantAllocation GA where GA.party = "+partyId+" and  GA.projects ="+projectId);
    	 if(grantAllocationInstance)
    		 return 1
    	 else
    		 return 0 
 				
 	}
     
 	public GrantAllocation[] getProjectsWithGrantAllocationForLoginUser(String partyId,String subQuery){
		def grantAllocationList
		
		
		if(partyId!=null)
	     grantAllocationList=GrantAllocation.findAll("from GrantAllocation GA where GA.party in "+partyId+"  group by GA.projects"+subQuery);
				
		return grantAllocationList
	}
 	
 	public GrantAllocation[] getProjectsWithGrantAllocationForLoginPi(String piId){
		def grantAllocationList
		
		
		if(piId!=null)
		grantAllocationList=GrantAllocation.findAll("from GrantAllocation GA where GA.projects in (select PM.projects from ProjectsPIMap PM where PM.investigator.id="+piId+") and GA.projects.activeYesNo='Y' "+"group by GA.projects");
	
		return grantAllocationList
	}
     
 	public ProjectsPIMap[] getProjectsPIMapForLoginUser(def projectId)
 	{
 		def projectsPIMapInstanceList
 		if(projectId !=null)
 		{
 			projectsPIMapInstanceList = ProjectsPIMap.findAll("from ProjectsPIMap P where P.projects = '"+ projectId + "' and P.activeYesNo='Y'");
 		}
 		return projectsPIMapInstanceList
 	}
 	public ProjectsPIMap getProjectsPIMap(def projectId)
 	{
 		def projectsPIMapInstance
 		if(projectId !=null)
 		{
 			projectsPIMapInstance = ProjectsPIMap.find("from ProjectsPIMap P where P.projects = '"+ projectId + "' and P.role='PI'");
 		}
 		return projectsPIMapInstance
 	}
 	/**
	 * Function to get all active sub projects of a Project.
	 */
	public Projects[] getSubProjectsForProject(def projectInstance)
 	{
		def subProjectslist
		subProjectslist = Projects.findAll("from Projects P where P.activeYesNo='Y' and P.parent.id ="+ projectInstance.id);
		println subProjectslist
		return subProjectslist
	}
 	public def saveAccessPermisionDetails(def grantAllocationList,def userId)
 	{
 		//println "ser-0-90--0-0-0"+grantAllocationId+"----"+userId
 		//def userInstance = User.get(userId)
 		def userInstance = Person.get(userId)
 		def userName = userInstance.username
 		for (grantAllocationId in grantAllocationList)
 		{
 		println "grantAllocationId.id"+grantAllocationId
 		
 		def grantAllocationInstance = GrantAllocation.get(grantAllocationId)
 		def projectInstance = Projects.get(grantAllocationInstance.projects.id)
 		println "projectInstance="+projectInstance
 		println "grantAllocationInstance====serv==="+grantAllocationInstance
 		def ctx = AH.application.mainContext
		aclService = ctx.aclService
		objectIdentityRetrievalStrategy = ctx.objectIdentityRetrievalStrategy
		sessionFactory = ctx.sessionFactory
		springSecurityService = ctx.springSecurityService
		aclUtilService = ctx.aclUtilService
    	println "error+++++++++++++++++++++++++++++++++++++++++++++++++++"
    	aclUtilService.addPermission grantAllocationInstance,userName ,    ADMINISTRATION
    	aclUtilService.addPermission projectInstance,userName ,    ADMINISTRATION
 		}
    	println "after===================+++++++++"+dataSource
    	
    	def sql = new Sql(dataSource);
 		println "after===================+++++++++"+sql
        def query2 = "SELECT projects.id FROM projects,grant_allocation,acl_sid,acl_object_identity,acl_entry,acl_class "+
				" WHERE grant_allocation.projects_id=projects.id AND "+
				" grant_allocation.id=acl_object_identity.object_id_identity "+
				" AND acl_object_identity.object_id_class=acl_class.id "+
				" AND acl_entry.acl_object_identity=acl_object_identity.id "+
				" AND acl_entry.sid= acl_sid.id AND acl_sid.sid='Adi'"+
				" AND acl_class.class='GrantAllocation'";
        def result = sql.rows(query2);

    	
    	
    	def acl = null;
    	println "after===================+++++++++"+result
    	return acl
    	
 	}
 	public List getAccessPermissionProjects(def userId)
 	{println "after===================+++++++++"+dataSource
 		def userInstance = Person.get(userId) 
 		def sql = new Sql(dataSource);
 		println "after===================+++++++++"+sql
        def query2 = "SELECT projects.id,projects.name FROM projects,grant_allocation,acl_sid,acl_object_identity,acl_entry,acl_class "+
				" WHERE grant_allocation.projects_id=projects.id AND "+
				" grant_allocation.id=acl_object_identity.object_id_identity "+
				" AND acl_object_identity.object_id_class=acl_class.id "+
				" AND acl_entry.acl_object_identity=acl_object_identity.id "+
				" AND acl_entry.sid= acl_sid.id AND acl_sid.sid='"+userInstance.username+"'"+
				" AND acl_class.class='GrantAllocation'";
        def result = sql.rows(query2);
        return result
 	}
 	public List deleteAccessPermission(def projectList,def userId)
 	{
 		def userInstance = Person.get(userId)
 		for(projectId in projectList)
 		{
 		def grantAllocationInstance = GrantAllocation.find("from GrantAllocation GA where GA.projects.id="+projectId)
 		def projectInstance = Projects.get(grantAllocationInstance.projects.id)
 		def ctx = AH.application.mainContext
		aclService = ctx.aclService
		objectIdentityRetrievalStrategy = ctx.objectIdentityRetrievalStrategy
		sessionFactory = ctx.sessionFactory
		springSecurityService = ctx.springSecurityService
		aclUtilService = ctx.aclUtilService
    	println "error+++++++++++++++++++++++++++++++++++++++++++++++++++"
    	
    	//aclUtilService.addPermission grantAllocationInstance,userName ,    ADMINISTRATION
    	//deleting grantallocation instance from acl
    	aclUtilService.deletePermission grantAllocationInstance, userInstance.username, ADMINISTRATION
    	//deleting project instance from acl
    	aclUtilService.deletePermission projectInstance, userInstance.username, ADMINISTRATION
 		}
 		def acl = null
    	println "after delete================"
    	return acl
 	}
}