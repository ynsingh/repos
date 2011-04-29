import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class PartyDepartmentController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']
/*============================= NEW =================================*/
	def delete = {
    		def projectDepartmentMapService= new ProjectDepartmentMapService()
    		def investigatorService = new InvestigatorService()
    		def partyDepartmentService = new PartyDepartmentService()
    		def ProjectDepartmentMapInstance=projectDepartmentMapService.getProjectDepartmentMapByPartyDepartment(params.id)
    		if (!ProjectDepartmentMapInstance)
    		{	
    			def investigatorInstance= investigatorService.getinvestigatorByDepartment(params.id)
	    		if (!investigatorInstance)
			    {	
	    			def partyDepartmentInstance = partyDepartmentService.getPartyDepartmentById(params.id )
			        if(partyDepartmentInstance) 
			        {
			        	//partyDepartmentInstance.properties = params
			        	partyDepartmentInstance = partyDepartmentService.deleteDepartment(partyDepartmentInstance)
			        	flash.message = "${message(code: 'default.deleted.label')}"
				        redirect(action:create)
		        	}	
			        else 
			        {
			            flash.message = "${message(code: 'default.notfond.label')}"
			            redirect(action:create) //15-11-2010
			        }
		        }
		        else
		        {
		            flash.message = "${message(code: 'default.usedinPI.label')}"		            
			        redirect(action:edit, id:params.id) //15-11-2010	
		        }
	    	}
	    	else    		
			   {
			      flash.message = "${message(code: 'default.usedinProjects.label')}"		            
			      redirect(action:edit, id:params.id) //15-11-2010	
			    }    		
		  }
/*========================== END ===================================*/
    def edit = {
    		def partyDepartmentService = new PartyDepartmentService()
    		def partyDepartmentInstance = partyDepartmentService.getPartyDepartmentById(params.id )
    		GrailsHttpSession gh=getSession()
    		def dataSecurityService = new DataSecurityService()
    		def partyList = dataSecurityService.getPartiesOfLoginUser(gh.getValue("PartyID"));
    		if(!partyDepartmentInstance) 
    		{
	            flash.message = "${message(code: 'default.notfond.label')}"
	            redirect(action:create)
    		}
    		else {
    			return [ 'partyDepartmentInstance' : partyDepartmentInstance,'partyList':partyList ]
    		}
    	}

    def update = {
    		println"params"+params
    		println"params"+params.party.code
    		//def partyService = new PartyService()
    		def partyDepartmentService = new PartyDepartmentService()
    		
    		//def partyInstance = Party.find("from Party P where P.code= '"+params.party.code+"' and P.activeYesNo='Y' ")
    		//println"partyInstance-------->"+partyInstance
    		//println"partyInstance.id-------->"+partyInstance.id
    		  
    		def partyDepartmentInstance = partyDepartmentService.getPartyDepartmentById(params.id)
       		println"partyDepartmentInstance........"+partyDepartmentInstance
       		println"params.id....."+params.id
    			if((params.departmentCode == partyDepartmentInstance.departmentCode )&& 
       			    (new Integer(params.id) == partyDepartmentInstance.id ))
       	  {
       		     println"ENTRY------>1"
				 partyDepartmentInstance.properties =params
				 savepartyDepartment(partyDepartmentInstance)  
       	  }
    		
       	  else
       	  {
       		 def partyDepartmentsInstance
               partyDepartmentsInstance = PartyDepartment.find("from PartyDepartment PD where PD.departmentCode = '"+params.departmentCode+"' and PD.activeYesNo ='Y' and PD.party="+partyDepartmentInstance.party.id )
                if(partyDepartmentsInstance)
                {
                
                flash.message = "${message(code: 'default.AlreadyExists.label')}"
   				redirect(action:create,id:params.id)
                
                }
                
                else
                {
                   println"Success"
                   partyDepartmentInstance.properties =params
                   partyDepartmentInstance.save()  
                   println"partyDepartmentInstance"+partyDepartmentInstance
                   flash.message = "${message(code: 'default.updated.label')}"
                   redirect(action:create , id:partyDepartmentInstance.id)
                
                }
       	  } 
    }      		
    		
    
    public savepartyDepartment(def partyDepartmentInstance)
    {
    	 if(!partyDepartmentInstance.hasErrors() && partyDepartmentInstance.save()) {
             flash.message = "${message(code: 'default.updated.label')}"
             redirect(action:create,id:partyDepartmentInstance.id)
         }
         else {
             render(view:'edit',model:[partyDepartmentInstance:partyDepartmentInstance])
         }
    }

    def create = {
        def partyDepartmentInstance = new PartyDepartment()
        partyDepartmentInstance.properties = params
        GrailsHttpSession gh=getSession()
        def dataSecurityService = new DataSecurityService()
        def partyInstance = Party.get(gh.getValue("Party"));
        //getting Department List
        def partyService = new PartyService()
        def partyDepartmentInstanceList = partyService.getPartyDepartment(gh.getValue("PartyID"))
        return ['partyDepartmentInstance':partyDepartmentInstance,
                'partyInstance':partyInstance,
                'partyDepartmentInstanceList': partyDepartmentInstanceList ]
    }

    def save = {
        def partyDepartmentInstance = new PartyDepartment(params)
        
        if(!partyDepartmentInstance.hasErrors())
        	{
	        	partyDepartmentInstance.createdBy="admin";        
	            partyDepartmentInstance.activeYesNo="Y" 
	            def partyDepartmentService = new PartyDepartmentService()
	            partyDepartmentInstance = partyDepartmentService.saveDepartment(partyDepartmentInstance)
	            if(partyDepartmentInstance.saveMode != null)
	            {
	           		if(partyDepartmentInstance.saveMode.equals("Saved"))
	           		{
	           			flash.message = "${message(code: 'default.created.label')}"
	           	        redirect(action:create,id:partyDepartmentInstance.id)
	           		}
	           		else if(partyDepartmentInstance.saveMode.equals("Duplicate"))
	           		{
	           			flash.message =  "${message(code: 'default.AlreadyExists.label')}"
	           			redirect(action:create,id:partyDepartmentInstance.id)
	           		}
	           	}
	           	else {
	                render(view:'create',model:[partyDepartmentInstance:partyDepartmentInstance])
	            }
	        }
        	else {
        		render(view:'create',model:[partyDepartmentInstance:partyDepartmentInstance])
        	}
    }
}