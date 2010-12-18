import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class ProjectTypeController {
    
    def index = {}

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']
    
    
    /**
	 * Method to Perform the create action
	 */

    def create = 
        {
    		def projectTypeInstance = new ProjectType()
        	def projectTypeService = new ProjectTypeService()
        	GrailsHttpSession gh=getSession()
        	
        	//Puting help page into session
        	gh.putValue("Help","Project_Type.htm")
            projectTypeInstance.properties = params
            
            /* Getting all active project types */
            def projectTypeInstanceList = projectTypeService.getAllProjectType()
            return ['projectTypeInstance':projectTypeInstance,
                    'projectTypeInstanceList': projectTypeInstanceList]
        }
    
    /**
	 * Method to perform the save action
	 */

    def save = 
        {
            def projectTypeInstance = new ProjectType(params)
            def projectTypeService = new ProjectTypeService()
            if(!projectTypeInstance.hasErrors())
            {
              def chkProjectTypeInstance = projectTypeService.checkDuplicateType(params)
              if(chkProjectTypeInstance)
	          {
	    	      flash.message ="${message(code: 'default.AlreadyExists.label')}"
	    		  redirect(action: "create", id: projectTypeInstance.id)
	          }
              else
              {
             
            	/* Save ProjectType details */
            	projectTypeInstance = projectTypeService.saveProjectType(projectTypeInstance)
            	if(projectTypeInstance.saveMode != null)
            	{
            		
            		
            		/*Checking whether the project type details are saved*/
            		if(projectTypeInstance.saveMode.equals("Saved"))
            		{
    	                  flash.message = "${message(code: 'default.created.label')}"
    	                  redirect(action:"create",id:projectTypeInstance.id)
            		}
            		else
            		{
            			  render(view:'create',model:[projectTypeInstance:projectTypeInstance])
            		}
            	}	
            	else 
            	{
            			render(view:'create',model:[projectTypeInstance:projectTypeInstance])
            	}
            }
           
        }
    }
    
    /**
	 * Method for performing the edit action
	 */
	 
    def edit = 
        {
        	def projectTypeService = new ProjectTypeService()
        	
            /* Getting Project Type details based on id */
            def projectTypeInstance = projectTypeService.getProjectTypeById(new Integer(params.id ))
            if(!projectTypeInstance) 
            {
                flash.message = "${message(code: 'default.notfond.label')}"
                redirect(action: "create")
            }
            else 
            {
                return [ projectTypeInstance : projectTypeInstance ]
            }
        }
    
    /**
	 * Method for performing the update action
	 */

    def update = 
    	{
       
            def projectTypeService = new ProjectTypeService()
        	def projectTypeInstance = projectTypeService.getProjectTypeById(new Integer(params.id ))
        	def chkProjectTypeInstance = projectTypeService.checkDuplicateType(params)
    		if(chkProjectTypeInstance)
    	    {
    			if((chkProjectTypeInstance.id).equals(projectTypeInstance.id))
    			{
    				  projectTypeInstance = projectTypeService.updateProjectType(params)
        				if(projectTypeInstance.saveMode.equals( "Updated"))
    			           	{
    	        				/*Shows a message if the details are updated*/
    	        				flash.message = "${message(code: 'default.updated.label')}"
    			        		redirect(action: "create", id: projectTypeInstance.id)
    			        	}
    	        
    			}
    			else if(chkProjectTypeInstance)
    			{
    				flash.message ="${message(code: 'default.AlreadyExists.label')}"
    				redirect(action: "edit", id:params.id)
    			}
    	    }
    	    else
    	    {
    	    	if(projectTypeInstance) 
    	    	{
    	    		projectTypeInstance.properties = params
        	
        		
        	        /* Update Project Type details */
                     projectTypeInstance = projectTypeService.updateProjectType(params)
             
                    /* Check whether any project type exists */
               if(projectTypeInstance)
               {
                if(projectTypeInstance.saveMode != null)
                  {
                	/* Check whether the project type details are updated */
    	            if(projectTypeInstance.saveMode.equals("Updated"))
    	              {
    	                 /* Shows a message if the details are updated */
                         flash.message = "${message(code: 'default.updated.label')}"
                         redirect(action:"create",id:projectTypeInstance.id)
                      }
                      else if (projectTypeInstance.saveMode == null)/*Check if the project type is assigned to project list*/
                      {
                    	  flash.message = "${message(code: 'default.usedinProjects.label')}"
                  	      redirect(action: "edit", id: params.id) 
                      }
                      else
                      {
                         render(view:'edit',model:[projectTypeInstance:projectTypeInstance])
                      }
                  }
              }
                  else 
                  {
                	 flash.message = "${message(code: 'default.notfond.label')}"
                	 redirect(action:"edit",id:params.id)
                  }
            
    	    	}
    	    }
    		} 
  
    /**
	 * Method for performing the delete action
	 */
	 
	def delete = 
 		 {
		    def projectTypeService = new ProjectTypeService() 
		    def projectsService =new ProjectsService()
		  
		    /* Getting project details with selected projectType */
    	    def projectsInstance =projectsService.getProjectByProjectType(params)
    	    
    	    /* check whether the project type is type of any project */
    	    if (!projectsInstance)
    	    {    		
	          def projectTypeInstance = ProjectType.get( params.id )
	          if(projectTypeInstance) 
	          {
	             projectTypeInstance.properties = params
	             
	             /* Delete project type details*/
	             Integer projectTypeId = projectTypeService.deleteProjectType(new Integer(params.id))
		         if(projectTypeId != null)
		         {
		        	flash.message = "${message(code: 'default.deleted.label')}"
		        	redirect(action:create)
		         }           
		         else 
		         {
		        	flash.message = "${message(code: 'default.notfond.label')}"
		        	redirect(action:create)
		         }
	          }
    	    }
    	    else
    	    {
    	    	/* Shows the following message if the project type is already in use. */
    	    	flash.message = "${message(code: 'default.usedinProjects.label')}"
    	    	redirect(action: "edit", id: params.id)   
    	    }    	   
 		 }
  }