import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class ProjectsController extends GmsController{
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']


    
     
    def list = {
		GrailsHttpSession gh=getSession()
		def grantAllocationWithprojectsInstanceList
		def grandAllocationList
		gh.removeValue("Help")
		//putting help pages in session
		gh.putValue("Help","Project_List.htm")
        if(!params.max) params.max = 10
        String subQuery="";
        if(params.sort != null && !params.sort.equals(""))
       	subQuery=" order by GA."+params.sort
       	println "+params.sort"+params.sort
       if(params.order != null && !params.order.equals(""))
       	subQuery =subQuery+" "+params.order
		
        
        def dataSecurityService = new DataSecurityService()
		def projectsService = new ProjectsService()
      //  grantAllocationOfprojectsInstanceList=dataSecurityService.getProjectsFromGrantAllocationForLoginUser(gh.getValue("PartyID"))
            if(gh.getValue("Role")=="ROLE_PI")
            {
            	println "PI"
            	grantAllocationWithprojectsInstanceList=dataSecurityService.getProjectsWithGrantAllocationForLoginPi(gh.getValue("Pi"))
            }
            else{
        	grantAllocationWithprojectsInstanceList=dataSecurityService.getProjectsWithGrantAllocationForLoginUser(gh.getValue("PartyID"),subQuery)
            }
       
        [ grantAllocationWithprojectsInstanceList: grantAllocationWithprojectsInstanceList ]
   }


    def show = {
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
        if(!projectsInstance) {
            flash.message = "Projects not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ projectsInstance : projectsInstance ] }
		}
    }

    def delete = {
		def projectsService = new ProjectsService()
		Integer projectId = projectsService.deleteProject(new Integer(params.id))
		 def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
	     def projectsInstance = new Projects()
        projectsInstance.properties = params
		if(projectId != null){
			if(projectId > 0){
				 flash.message = "Project ${params.name} deleted"
			}
			else{
				flash.message = "Grant allocation done for project ${params.name}. Not deleted"
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
           
			
           
        

        else {
            flash.message = "Project not found with id ${params.id}"
            redirect(action:list)
        }

    }
    
    

    def edit = {
		def projectsService = new ProjectsService()
		def dataSecurityService = new DataSecurityService()
		GrailsHttpSession gh=getSession()
		//putting help pages in session
		gh.putValue("Help","New_Projects.htm")
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
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectid,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
	

        if(!projectsInstance) {
            flash.message = "Project not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ projectsInstance : projectsInstance ]
        }
		}
    }
    
    
    def subedit = {
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
    		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.parent.id,new Integer(getUserPartyID()))==0)
    		{
    			
    					
    					 redirect uri:'/invalidAccess.gsp'

    		}
    		else
    		{
    	

            if(!projectsInstance) {
                flash.message = "Projects not found with id ${params.id}"
                redirect(action:list)
            }
            else {
                return [ projectsInstance : projectsInstance ]
            }
    		}
        }
    

    def update = {
		def projectsService = new ProjectsService()
		def projectsInstance = projectsService.updateProject(params)	
		if(projectsInstance){
			if(projectsInstance.saveMode != null){
				if(projectsInstance.saveMode.equals("Updated")){
					flash.message = "Project ${params.name} updated"
						if(projectsInstance.parent !=null)
						{
							redirect(action:showSubProjects,id:projectsInstance.parent.id)
						}
						else
						{
							redirect(action:list,id:projectsInstance.id)
						}
				}
				else if(projectsInstance.saveMode.equals("Duplicate")){
					flash.message = "Project Already Exists"
			    	render(view:'edit',model:[projectsInstance:projectsInstance])
				}
			}
			else {
                render(view:'edit',model:[projectsInstance:projectsInstance])
            }
		}
		else {
            flash.message = "Project not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
    	GrailsHttpSession gh=getSession()
    	//putting help pages in session
    	gh.putValue("Help","New_Projects.htm")
    	def grantAllocationWithprojectsInstanceList
        def projectsInstance = new Projects()
        projectsInstance.properties = params
        return ['projectsInstance':projectsInstance]
    }

    def save = {
		params.createdBy = "user";
		params.createdDate = new Date();
		params.modifiedBy = "user";
		params.modifiedDate = new Date();
    	def projectsInstance = new Projects(params)
    	println params
    	def projectsService = new ProjectsService()
    	
    	GrailsHttpSession gh=getSession()
    	
    	
    	projectsInstance = projectsService.saveProjects(projectsInstance,gh.getValue("UserId"))
    	
    	
    	if(projectsInstance.saveMode != null){
    		if(projectsInstance.saveMode.equals("Saved")){
    			flash.message = "Project ${projectsInstance.name} created"
	            
    			redirect(action:list,id:projectsInstance.id)
	                 
	            
    		}
    		else if(projectsInstance.saveMode.equals("Duplicate")){
    			flash.message = "Project Already Exists"
    			render(view:'create',model:[projectsInstance:projectsInstance])
    		}
    	}
    	else{
    		flash.message = "Project Already Exists"
    		render(view:'create',model:[projectsInstance:projectsInstance])
    	}
    }
    
    
    def saveSub = {
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
        	
        	
        	if(projectsInstance.saveMode != null){
        		if(projectsInstance.saveMode.equals("Saved")){
        			flash.message = "Project ${projectsInstance.name} created"
    	            
        			redirect(action:showSubProjects,id:projectsInstance.parent.id)
    	                 
    	            
        		}
        		else if(projectsInstance.saveMode.equals("Duplicate")){
        			flash.message = "Project Already Exists"
        			render(view:'create',model:[projectsInstance:projectsInstance])
        		}
        	}
        	else{
        		flash.message = "Project Already Exists"
        		render(view:'create',model:[projectsInstance:projectsInstance])
        	}
        }
        		 		
    def proList = {
		GrailsHttpSession gh=getSession()
		//putting help pages in session
		gh.putValue("Help","Project_List.htm")
        if(!params.max) params.max = 10
        
        def dataSecurityService = new DataSecurityService()
        def projectsInstanceList=dataSecurityService.getProjectsOfLoginUser(gh.getValue("ProjectID"))
        [ projectsInstanceList: projectsInstanceList ]
    }
    
    def showSubProjects = {
		println "params"+params.id
		 def dataSecurityService = new DataSecurityService()
//		checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(new Integer( params.id ),new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
    	GrailsHttpSession gh=getSession()
    	//putting help pages in session
    	gh.putValue("Help","sub_Projects.htm")
        def projectsInstance = new Projects()
		def projectsInstanceList= new Projects();
		
		def projectsService = new ProjectsService()
		def projectsList=projectsService.getProjectById(new Integer(params.id))
		projectsInstance.parent=projectsList
        projectsInstance.properties = params
        String subQuery ="";
        //GrailsHttpSession gh=getSession()
        if(params.sort != null && !params.sort.equals(""))
        	subQuery=" order by P."+params.sort
        if(params.order != null && !params.order.equals(""))
        	subQuery =subQuery+" "+params.order
        
        projectsInstanceList=projectsService.getActiveSubProjects(new Integer(params.id),subQuery)
        return ['projectsInstance':projectsInstance,'projectsInstanceList':projectsInstanceList]
		}
    }
    
    def checkforAuthrizedUserInProjectDomain = {
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
            	 flash.message = "You are not authorized to acsess this project"
            	 return -1;
             }
             else
             {
            	 return 1;
             }
    		}
           
       }
}
