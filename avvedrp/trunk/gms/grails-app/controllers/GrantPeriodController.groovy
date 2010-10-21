import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class GrantPeriodController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
    		
    	GrailsHttpSession gh=getSession()
        gh.removeValue("Help")
       		//putting help pages in session
       	gh.putValue("Help","Grant_Period_List.htm")	
        if(!params.max) params.max = 10
        
        def grantPeriodService = new GrantPeriodService()
        def grantPeriodInstanceList = grantPeriodService.getAllGrantPeriods(params)
        [ grantPeriodInstanceList: grantPeriodInstanceList ]
    }

    def show = {
		def grantPeriodService = new GrantPeriodService()
		def grantPeriodInstance = grantPeriodService.getGrantPeriodById(new Integer( params.id ))

        if(!grantPeriodInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ grantPeriodInstance : grantPeriodInstance ] }
    }

    def delete = {
		def grantPeriodService = new GrantPeriodService()
		Integer grantPeriodId = grantPeriodService.deleteGrantPeriod(new Integer(params.id))
		
		if(grantPeriodId != null){
			flash.message = "${message(code: 'default.deleted.label')}"
            redirect(action:list)
		}
		else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
    }

    def edit =
                {
		            def grantPeriodService = new GrantPeriodService()
		            def grantPeriodInstance = grantPeriodService.getGrantPeriodById(new Integer( params.id ))
                    if(!grantPeriodInstance)
                      {
                          flash.message = "${message(code: 'default.notfond.label')}"
                          redirect(action:list)
                      }
                    else
                      {
                          return [ grantPeriodInstance : grantPeriodInstance ]
                      }
                }

  def update =
                {
    		        def grantPeriodInstance = new GrantPeriod(params)
                    def grantPeriodService = new GrantPeriodService()
		            def chkDefaultGrantPeriodInstance=grantPeriodService.getDefaultGrantPeriod(params)
    	            println"chkDefaultGrantPeriodInstance in edit"+chkDefaultGrantPeriodInstance.size()
    	            if(chkDefaultGrantPeriodInstance)
    	             {
    		           if((chkDefaultGrantPeriodInstance[0].id != Long.parseLong(params.id)) && (chkDefaultGrantPeriodInstance.size()>=1) && (grantPeriodInstance.defaultYesNo == 'Y'))
        	             {
    	                   flash.message = "${message(code: 'default.Defaultgrantperiodmustunique.label')}"
    		               redirect(action:edit,id:params.id)
    	                 }
    		           else
    	                 {
		                   grantPeriodInstance = grantPeriodService.updateGrantPeriod(params)
		                   if(grantPeriodInstance)
		                    {
			                  if(grantPeriodInstance.saveMode != null)
			                    {
				                  if(grantPeriodInstance.saveMode.equals("Updated"))
				                    {
					                  flash.message = "${message(code: 'default.updated.label')}"
	                                  redirect(action:create,id:grantPeriodInstance.id)
				                    }
                                  else 
			                        {
                                      render(view:'edit',model:[grantPeriodInstance:grantPeriodInstance])
                                    }
		                        }
    	                      else
    	                        {
                                  flash.message = "${message(code: 'default.notfond.label')}"
                                  redirect(action:edit,id:params.id)
                                }
		                    }
    	                 }
    	             }
    	            else
    	               {
    		             if((chkDefaultGrantPeriodInstance.size()>=1) && (grantPeriodInstance.defaultYesNo == 'Y'))
           	               {
       	                     flash.message = "${message(code: 'default.Defaultgrantperiodmustunique.label')}"
       		                 redirect(action:edit,id:params.id)
       	                   }
       		             else
       	                   {
   		                     grantPeriodInstance = grantPeriodService.updateGrantPeriod(params)
   		                     if(grantPeriodInstance)
   		                       {
   			                     if(grantPeriodInstance.saveMode != null)
   			                       {
   				                    if(grantPeriodInstance.saveMode.equals("Updated"))
   				                      {
   					                    flash.message = "${message(code: 'default.updated.label')}"
   	                                    redirect(action:create,id:grantPeriodInstance.id)
   				                      }
   			                        else
   			                          {
                                        render(view:'edit',model:[grantPeriodInstance:grantPeriodInstance])
                                      }
   		                           }
       	                         else
       	                           {
                                     flash.message = "${message(code: 'default.notfond.label')}"
                                     redirect(action:edit,id:params.id)
                                   }
   		                       }
       	                   }  
    	               }
                }

    def create = {
    		GrailsHttpSession gh=getSession()
         def grantPeriodInstance = new GrantPeriod()
        
        
        	gh.removeValue("Help")
    		//putting help pages in session
    		gh.putValue("Help","Create_Grant_Period.htm")	
        grantPeriodInstance.properties = params
        def grantPeriodService = new GrantPeriodService()
        def grantPeriodInstanceList = grantPeriodService.getAllGrantPeriods(params)
       
        return ['grantPeriodInstance':grantPeriodInstance,
                'grantPeriodInstanceList': grantPeriodInstanceList]
    }

    def save = {
        def grantPeriodInstance = new GrantPeriod(params)
        if(!grantPeriodInstance.hasErrors() ) 
        {
        	grantPeriodInstance.createdBy="admin"
    		grantPeriodInstance.modifiedBy="admin"
    		
			def grantPeriodService = new GrantPeriodService()
        	println"grantPeriodInstance"+grantPeriodInstance.defaultYesNo
        	def chkDefaultGrantPeriodInstance=grantPeriodService.getDefaultGrantPeriod(params)
        	println"chkDefaultGrantPeriodInstance"+chkDefaultGrantPeriodInstance.size()
        	
        	def grantPeriodDuplicateInstance = grantPeriodService.getGrantPeriod(grantPeriodInstance)
        	
        	if(grantPeriodDuplicateInstance)
        	{
        	    flash.message = "${message(code: 'default.AlreadyExists.label')}"
	        	redirect(action:create,id:grantPeriodInstance.id)
        	}
        	else
        	{
        		if(grantPeriodInstance.defaultYesNo=='Y' && chkDefaultGrantPeriodInstance.size()>0)
	            {
	        	    flash.message = "${message(code: 'default.Defaultgrantperiodmustunique.label')}"
	        		redirect(action:create,id:grantPeriodInstance.id)
	        	}
	        	
	        	else
	        	{
	        		grantPeriodInstance = grantPeriodService.saveGrantPeriod(grantPeriodInstance)
	        		if(grantPeriodInstance.saveMode != null)
	        		{
	        			if(grantPeriodInstance.saveMode.equals("Saved"))
	        			{
	        				flash.message = "${message(code: 'default.created.label')}"
	        				redirect(action:create,id:grantPeriodInstance.id)
	        			}
	        		}
	        		else 
	        		{
	        			render(view:'create',model:[grantPeriodInstance:grantPeriodInstance])
	        		}
	        	}
        	}
        }
        
    }
}
