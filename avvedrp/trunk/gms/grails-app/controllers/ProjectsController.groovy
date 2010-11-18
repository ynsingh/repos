import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.SimpleDateFormat
import java.text.*;
import java.util.*;
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.commons.ControllerArtefactHandler
import org.springframework.beans.BeanWrapper
import org.springframework.beans.PropertyAccessorFactory

class ProjectsController extends GmsController
{
	def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def grantAllocationService
    def projectsService
    def investigatorService    
    def list = 
    {
		GrailsHttpSession gh=getSession()
		
		def grantAllocationWithprojectsInstanceList
		def grandAllocationList
		
		gh.removeValue("Help")
		gh.putValue("Help","Project_List.htm")//putting help pages in session
		
		if(!params.max) params.max = 10
			String subQuery="";
		if(params.sort != null && !params.sort.equals(""))
			subQuery=" order by GA."+params.sort
		println "+params.sort"+params.sort
		if(params.order != null && !params.order.equals(""))
			subQuery =subQuery+" "+params.order
		
		grantAllocationWithprojectsInstanceList = grantAllocationService
													.getGrantAllocationGroupByProjects(gh.getValue("Party"))
													
		def  pIMapList =[]
		def pIMapInstance

		for(int i=0;i<grantAllocationWithprojectsInstanceList.size();i++)
		{
			pIMapInstance=projectsService.checkPIofProject(grantAllocationWithprojectsInstanceList[i].projects.id)
			
			pIMapList.add(pIMapInstance)
			
		}
		
		[ 'grantAllocationWithprojectsInstanceList': grantAllocationWithprojectsInstanceList,'pIMapList':pIMapList ]
    }
	
	def inactiveProjectsList = 
	{
		GrailsHttpSession gh=getSession()
		
		def grantAllocationWithprojectsInstanceList
		def grandAllocationList
		
		gh.removeValue("Help")   		
		gh.putValue("Help","Project_List.htm")//putting help pages in session
		
		if(!params.max) params.max = 10
			String subQuery="";
		if(params.sort != null && !params.sort.equals(""))
			subQuery=" order by GA."+params.sort
		println "+params.sort"+params.sort
		if(params.order != null && !params.order.equals(""))
			subQuery =subQuery+" "+params.order
		
		List<GrantAllocation> grantAllocationInstanceList = grantAllocationService.getAll()
		[ grantAllocationWithprojectsInstanceList: grantAllocationInstanceList ]
	}
    def show = 
    {
		def projectsService = new ProjectsService()
		def projectsInstance = projectsService.getProjectById(new Integer( params.id ))
		def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(new Integer( params.id ),new Integer(getUserPartyID()))==0)
		{
			redirect uri:'/invalidAccess.gsp'
		}
		else
		{
			if(!projectsInstance) 
			{
				flash.message = "${message(code: 'default.notfond.label')}"
				redirect(action:list)
			}
			else 
			{ 
				return [ projectsInstance : projectsInstance ] 
			}
		}
    }

	def delete =
	{
		def projectsService = new ProjectsService()
		def dataSecurityService = new DataSecurityService()
		Integer projectId = projectsService.deleteProject(new Integer(params.id))
		
		//checking  whether the user has access to the given projects
		def projectsInstance = new Projects() 
		projectsInstance.properties = params
		if(projectId != null)
		{
			if(projectId > 0)
			{
				 flash.message = "${message(code: 'default.deleted.label')}"
			}
			else
			{
				flash.message = "${message(code: 'default.cannotDeleteProject.label')}"
			}
			if(projectsInstance.parent !=null)
			{
				redirect(action:showSubProjects,id:projectsInstance.parent.id)
			}
			else
			{
				redirect(action:list,id:projectsInstance.id)
			}
		}
		else 
		{
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
		}
    }
    def edit = 
    {
		def projectsService = new ProjectsService()
		def dataSecurityService = new DataSecurityService()
		
		GrailsHttpSession gh=getSession()
		gh.putValue("Help","New_Projects.htm")//putting help pages in session
		def projectsInstance = projectsService.getProjectById(new Integer( params.id ))
		if(projectsInstance)
		{
			def projectsPIMapInstance = projectsService.checkPIofProject(projectsInstance.id)
			if(projectsPIMapInstance)
				projectsInstance.investigator = projectsPIMapInstance.investigator
		}
		def projectid=projectsInstance.id
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectid,new Integer(getUserPartyID()))==0)
		{
			redirect uri:'/invalidAccess.gsp'
		}
		else
		{
			if(!projectsInstance) 
			{
				flash.message = "${message(code: 'default.notfond.label')}"
				redirect(action:list)
			}
			else 
			{
				gh.putValue("ProjectId",projectsInstance.id)
				return [ projectsInstance : projectsInstance ]
			}
		}
    }
	def editsub =
	{
		def projectsService = new ProjectsService()
		def dataSecurityService = new DataSecurityService()
		
		GrailsHttpSession gh=getSession()
		gh.putValue("Help","New_Projects.htm")//putting help pages in session
		
		def projectsInstance = projectsService.getProjectById(new Integer( params.id ))
		def projectid=projectsInstance.id
		println"params"+params
		println"projectid"+projectid
		if(!projectsInstance)
		{
			flash.message = "${message(code: 'default.notfond.label')}"
        	redirect(action:showSubProjects,id:params.parent.id)
		}
		else 
		{
			return [ projectsInstance : projectsInstance ]
		}
    }

	def subedit = 
	{
		def projectsService = new ProjectsService()
		def dataSecurityService = new DataSecurityService()
		GrailsHttpSession gh=getSession()
		def projectsInstance = projectsService.getProjectById(new Integer( params.id ))
		
		def projectid
       
		if(params.flag=='Y')
		{
			projectid=projectsInstance.parent.id
		}
		else
		{
			projectid=projectsInstance.id
		}
		    		
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.parent.id,
																	new Integer(getUserPartyID()))==0)
		{
			 redirect uri:'/invalidAccess.gsp'
		}
		else
		{
			if(!projectsInstance)
			{
                flash.message = "${message(code: 'default.notfond.label')}"
                redirect(action:list)
			}
			else 
			{
				return [ projectsInstance : projectsInstance ]
			}
		}
	}
    

    def update = 
    {
		
		println".......params........."+params
		def projectsInstance = projectsService.updateProject(params)	
		def investigatorInstance=investigatorService.getInvestigatorById(params.investigator.id)
		if(projectsInstance)
		{
			if(projectsInstance.saveMode != null)
			{
				if(projectsInstance.saveMode.equals("Updated"))
				{
					flash.message ="" +projectsInstance.name+ "&nbsp;"+"${message(code: 'default.updated.label')}" 
					redirect(action:list,id:projectsInstance.id)
				}
				else if(projectsInstance.saveMode.equals("Duplicate"))
				{
					flash.message = "${message(code: 'default.AlreadyExists.label')}"
					render(view:'edit',model:[projectsInstance:projectsInstance])
					}
				else if(projectsInstance.saveMode.equals("NotUpdated"))
				{
					flash.message = "${message(code: 'default.cannotupdate.label')}"+investigatorInstance.email+ "${message(code: 'default.alreadyassigned.label')}"
						
						render(view:'edit',model:[projectsInstance:projectsInstance])
				}
			}
			else 
			{
				render(view:'edit',model:[projectsInstance:projectsInstance])
			}
		}
		else 
		{
			flash.message = "${message(code: 'default.notfond.label')}"
			redirect(action:edit,id:params.id)
		}
	}
    
	def subupdate =
	{
		def projectsService = new ProjectsService()
		def projectsInstance = projectsService.updateSubProject(params)	
		println"projectsInstance"+projectsInstance.saveMode
		println"params.parent.id"+params.parent.id
		if(projectsInstance)
		{
			if(projectsInstance.saveMode != null)
			{
				if(projectsInstance.saveMode.equals("Updated"))
				{
					flash.message = "${message(code: 'default.updated.label')}"
					println"projectsInstance after"+projectsInstance
					redirect(action:showSubProjects,id:projectsInstance.parent.id)
				}
				else if(projectsInstance.saveMode.equals("Duplicate"))
				{
					flash.message = "${message(code: 'default.AlreadyExists.label')}"
					render(view:'editsub',model:[projectsInstance:projectsInstance])
				}
			}
			else 
			{
		        render(view:'editsub',model:[projectsInstance:projectsInstance])
		    }
		}
		else 
		{
		    flash.message = "${message(code: 'default.notfond.label')}"
		    redirect(action:editsub,id:params.parent.id)
		}
	}

    def create = 
    {
		def projectsService = new ProjectsService()
		GrailsHttpSession gh=getSession()
		gh.putValue("Help","New_Projects.htm")//putting help pages in session
		
		def grantAllocationWithprojectsInstanceList
		def projectsInstance = new Projects()
		projectsInstance.properties = params
		println "params" +params
		println "projectsInstance.id" +projectsInstance.id
		if(params.id)
		{
	    	projectsInstance = projectsService.getProjectById(new Integer( params.id ))
		}
		return ['projectsInstance':projectsInstance]
    }
	
	/*
	 * @@ Save project details @@ 
	 **** 1. Save the project details
	 **** 2. Grant will be allocated to the project with amount 0.00
	 **** 3. PI will be mapped to the project
	 */
    def save = 
    {
		params.createdBy = "user";
		params.createdDate = new Date();
		params.modifiedBy = "user";
		params.modifiedDate = new Date();
    	def projectsInstance = new Projects(params)
    	println params
    	def projectsService = new ProjectsService()
    	
    	GrailsHttpSession gh=getSession()
    	   	
    	projectsInstance = projectsService.saveProjects(projectsInstance,gh.getValue("UserId"))

    	
    	if(projectsInstance.saveMode != null)
    	{
    		if(projectsInstance.saveMode.equals("Saved"))
    		{
    			flash.message = "${message(code: 'default.created.label')}"
    			gh.putValue("ProjectId",projectsInstance.id)
    			redirect(action:create,id:projectsInstance.id)
    		}
    		else if(projectsInstance.saveMode.equals("Duplicate"))
    		{
    			flash.message = "${message(code: 'default.AlreadyExists.label')}"
    			render(view:'create',model:[projectsInstance:projectsInstance])
    		}
    	}
    	else
    	{
    		render(view:'create',model:[projectsInstance:projectsInstance])
    	}
    }
    def saveSub = 
    {
			println "save sub "+params.parent.id
		params.createdBy = "user";
		params.createdDate = new Date();
		params.modifiedBy = "user";
		params.modifiedDate = new Date();
    	def projectsInstance = new Projects(params)
    	def projectsService = new ProjectsService()
    	println params.parent
    	def parentProjectsInstance = projectsService.getProjectById(new Integer( params.parent.id ))
    	
    	projectsInstance.projectType=parentProjectsInstance.projectType
    	GrailsHttpSession gh=getSession()
    	
    	projectsInstance = projectsService.saveProjects(projectsInstance,gh.getValue("UserId"))
    	
    	if(projectsInstance.saveMode != null)
    	{
    		if(projectsInstance.saveMode.equals("Saved"))
    		{
    			flash.message = "${message(code: 'default.created.label')}"
    			redirect(action:showSubProjects,id:projectsInstance.parent.id)
    		}
    		else if(projectsInstance.saveMode.equals("Duplicate"))
    		{
    			flash.message = "${message(code: 'default.AlreadyExists.label')}"
    			render(view:'showSubProjects',model:[projectsInstance:projectsInstance])
    		}
    	}
    	else
    	{
    		flash.message = "${message(code: 'default.AlreadyExists.label')}"
    		render(view:'showSubProjects',model:[projectsInstance:projectsInstance])
    	}
    }
        		 		
    def proList = 
    {
		GrailsHttpSession gh=getSession()
		gh.putValue("Help","Project_List.htm")//putting help pages in session
        
		if(!params.max) params.max = 10
        
        def dataSecurityService = new DataSecurityService()
        def projectsInstanceList=dataSecurityService.getProjectsOfLoginUser(gh.getValue("ProjectID"))
        
        [ projectsInstanceList: projectsInstanceList ]
    }
    
    def showSubProjects =
    {
		println "params"+params.id
		def dataSecurityService = new DataSecurityService()
		
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(new Integer( params.id ),new Integer(getUserPartyID()))==0)
		{
			 redirect uri:'/invalidAccess.gsp'
		}
		else
		{
	    	GrailsHttpSession gh=getSession()
	    	gh.putValue("Help","sub_Projects.htm")//putting help pages in session
	       
	    	def projectsInstance = new Projects()
			def projectsInstanceList= new Projects();
			
			def projectsService = new ProjectsService()
			def projectsList=projectsService.getProjectById(new Integer(params.id))
			projectsInstance.parent=projectsList
	        projectsInstance.properties = params
	        String subQuery ="";
	        
	        if(params.sort != null && !params.sort.equals(""))
	        	subQuery=" order by P."+params.sort
	        if(params.order != null && !params.order.equals(""))
	        	subQuery =subQuery+" "+params.order
	        
	        projectsInstanceList=projectsService.getActiveSubProjects(new Integer(params.id),subQuery)
	        def grantAllocationInstance = GrantAllocation.find("from GrantAllocation  GA where  GA.projects="+params.id);
	        return ['projectsInstance':projectsInstance,
	                'projectsInstanceList':projectsInstanceList,
	                'grantAllocationInstance':grantAllocationInstance]
		}
    }
    
    def checkforAuthrizedUserInProjectDomain =
    {
		println "checkforAuthrizedUserInProjectDomain :"+params.id
		GrailsHttpSession gh=getSession()
		def grantAllocationWithprojectsInstanceList
		def dataSecurityService = new DataSecurityService()
		def projectsService = new ProjectsService()
		if(params.id!=null)
		{           
			grantAllocationWithprojectsInstanceList=dataSecurityService.getProjectsFromGrantAllocationForLoginUser(gh.getValue("PartyID"),params.id)
			println "grantAllocationWithprojectsInstanceList :"+grantAllocationWithprojectsInstanceList
			if(grantAllocationWithprojectsInstanceList.size()==0)
			{
				flash.message = "${message(code: 'default.notAuthorizedAccess.label')}"
				return -1;
			}
			else
			{
				return 1;
			}
		}           
    }
    def search = 
    {
        def projectsInstance = new Projects()
        projectsInstance.properties = params
        return ['projectsInstance':projectsInstance]        	       	
    }
    def searchProjects = 
    {
		def projectsInstance = new Projects(params)
    	
    	   	
    	
		GrailsHttpSession gh=getSession()
    	def grantAllocationInstanceList = projectsService.searchProjects(projectsInstance,gh.getValue("Party"),params);
		if (grantAllocationInstanceList.size()==0 )
		{
			 flash.message = "${message(code: 'default.notfond.label')}"
		}
		render(view:'search',model:['grantAllocationInstanceList':grantAllocationInstanceList])  
    }
	def advancedSearchProjects = 
    {
			
			if(params.id == "Advance")
			{
				render (template:"advancedSearch")
			}
			else if(params.id == "1")
			{
				render (template:"simpleSearch")
			}
			
    }
    
}
