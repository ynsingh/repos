class GrantAllocationSplitController extends GmsController  {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
    		def grantAllocationSplitService = new GrantAllocationSplitService()
    		def grantAllocationService = new GrantAllocationService()
            def grantAllocationSplitInstance = new GrantAllocationSplit()
    		
    		
    		
            def projectsInstance = Projects.get(new Integer(params.id))
            
              def dataSecurityService = new DataSecurityService()
    		//checking  whether the user has access to the given projects
    		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.id,new Integer(getUserPartyID()))==0)
    		{
    			
    					
    					 redirect uri:'/invalidAccess.gsp'

    		}
    		else
    		{
    			
    			def grantAllocationSplitDetailsList=[]
             projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
    		def grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(params.id)
            for(int i=0;i<grantAllocationInstanceList.size();i++)
            {
            	grantAllocationSplitDetailsList.add(grantAllocationSplitService.getGrantAllocationSplitDetailsByGrantAllocation(grantAllocationInstanceList[i].id))
            }
    			 println "grantAllocationSplitDetailsList"+grantAllocationSplitDetailsList
            def grantAllocationSplitList=grantAllocationSplitService.getGrantAllocationSplitDetailsByProject(params.id)
            println "grantAllocationSplitList"+grantAllocationSplitList
            grantAllocationSplitInstance.properties = params
            grantAllocationSplitInstance.projects=projectsInstance;
            return ['grantAllocationSplitInstance':grantAllocationSplitInstance,'grantAllocationInstanceList':grantAllocationInstanceList,'grantAllocationSplitDetailsList':grantAllocationSplitDetailsList]
    		}
    }

    def show = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
        def grantAllocationSplitInstance = grantAllocationSplitService.getGrantAllocationSplitById(new Integer(params.id))

        if(!grantAllocationSplitInstance) {
            flash.message = "GrantAllocationSplit not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ grantAllocationSplitInstance : grantAllocationSplitInstance ] }
    }

    def delete = {
        def grantAllocationSplitService = new GrantAllocationSplitService()
        Integer projectId = grantAllocationSplitService.deleteGrantAllocationSplit(new Integer(params.id))
        
        if(projectId != null){
        	if(projectId > 0){
        		flash.message = "Grant Head Deleted"
        			  redirect(action:list,id:projectId)
        	}
        }
        else{
        	flash.message = "GrantAllocationSplit not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
        def grantAllocationSplitInstance = grantAllocationSplitService.getGrantAllocationSplitById(new Integer(params.id))
           def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(grantAllocationSplitInstance.projects.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
        if(!grantAllocationSplitInstance) {
            flash.message = "GrantAllocationSplit not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ grantAllocationSplitInstance : grantAllocationSplitInstance ]
        }
    }
    }

    def update = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
		def grantAllocationSplitInstance = grantAllocationSplitService.updateGrantAllocationSplit(params)
		if(grantAllocationSplitInstance) {
			if(grantAllocationSplitInstance.isSaved){
				flash.message = "Grant Heads Updated"
				
				  redirect(action:list,id:grantAllocationSplitInstance.projects.id)
			}
			else{
				render(view:'edit',model:[grantAllocationSplitInstance:grantAllocationSplitInstance])
			}
		}
		else {
            flash.message = "GrantAllocationSplit not found with id ${params.id}"
            redirect(action:edit,id:params.id)
		}
    }

    def create = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
		def grantAllocationService = new GrantAllocationService()
        def grantAllocationSplitInstance = new GrantAllocationSplit()
		println "params"+params
		println "params.grantAllotId"+params.grantAllotId
		println "params.UnAll "+params.UnAll
		
        def projectsInstance = Projects.get(new Integer(params.id))
        def grantAllocationInstance = GrantAllocation.get(new Integer(params.grantAllotId))
        
          def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
         projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
		def grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(params.id)
    
        def grantAllocationSplitList=grantAllocationSplitService.getGrantAllocationSplitByProjects(params.id)
        println "grantAllocationSplitList"+grantAllocationSplitList
        grantAllocationSplitInstance.properties = params
        grantAllocationSplitInstance.unAllocatedAmt = new Double(params.UnAll)
        grantAllocationSplitInstance.projects=projectsInstance;
        grantAllocationSplitInstance.grantAllocation=grantAllocationInstance;
        return ['grantAllocationSplitInstance':grantAllocationSplitInstance,'grantAllocationInstance':grantAllocationInstance,'grantAllocationInstanceList':grantAllocationInstanceList]
		}
    }

    def save = {
    		println params
        def grantAllocationSplitInstance = new GrantAllocationSplit(params)
        if(!grantAllocationSplitInstance.hasErrors() ) {
            def grantAllocationInstance = GrantAllocation.get(new Integer(params.grantAllotId))
        	grantAllocationSplitInstance.createdBy="admin"
        	grantAllocationSplitInstance.modifiedBy="admin"
        	grantAllocationSplitInstance.modifiedBy="admin"
        	grantAllocationSplitInstance.createdDate=new Date()
            grantAllocationSplitInstance.grantAllocation=grantAllocationInstance;
            println "grantAllocationSplitInstance.grantAllocation"+grantAllocationSplitInstance.grantAllocation
        	def grantAllocationSplitService = new GrantAllocationSplitService()
        	
        	grantAllocationSplitInstance =grantAllocationSplitService.saveGrantAllocationSplit(grantAllocationSplitInstance,new Integer(params.projectId)) 
        	
            flash.message = "Grant is Allocated to Heads"
            	  redirect(action:list,id:params.projectId)
        }
        else {
            render(view:'create',model:[grantAllocationSplitInstance:grantAllocationSplitInstance])
        }
    }
}
