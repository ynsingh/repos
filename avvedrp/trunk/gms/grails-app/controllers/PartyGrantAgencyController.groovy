class PartyGrantAgencyController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        params.partyType = "GA"
        String subQuery ="";
        if(params.sort != null && !params.sort.equals(""))
        	subQuery=" order by P."+params.sort
        if(params.order != null && !params.order.equals(""))
        	subQuery =subQuery+" "+params.order
        	
    	def partyService = new PartyService()
        def partyInstanceList = partyService.getActiveGrantAgency(subQuery)
        [ partyInstanceList: partyInstanceList ]
    }

    def show = {
		def partyService = new PartyService()
		def partyInstance = partyService.getPartyById(new Integer(params.id ))

        if(!partyInstance) {
            flash.message = "Grant Agency not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ partyInstance : partyInstance ] }
    }

    def delete = {
		def partyService = new PartyService()
		Integer partyId = partyService.deleteParty(new Integer(params.id))
		
		if(partyId != null){
			flash.message = "Grant Agency ${params.code} deleted"
			redirect(action:list)
		}
		else {
            flash.message = "Grant Allocation done by Grant Agency ${params.code},so could not delete"
            redirect(action:list)
        }
    }

    def edit = {
        def partyInstance = Party.get( params.id )

        if(!partyInstance) {
            flash.message = "Grant Agency not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ partyInstance : partyInstance ]
        }
    }

    def update = {
		def partyService = new PartyService()
		def partyInstance = partyService.updateGrantAgency(params)
		
		if(partyInstance){
			if(partyInstance.saveMode != null){
				if(partyInstance.saveMode.equals("Updated")){
					flash.message = "Grant Agency ${params.code} updated"
	                redirect(action:list,id:partyInstance.id)
				}
				else if(partyInstance.saveMode.equals("Duplicate")){
					flash.message = "Grant Agency Already Exists"
	    	    	render(view:'edit',model:[partyInstance:partyInstance])
				}
			}
			else {
                render(view:'edit',model:[partyInstance:partyInstance])
            }
		}
		else {
            flash.message = "Grant Agency not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def partyInstance = new Party()
        return ['partyInstance':partyInstance]
    }

    def save = {
        def partyInstance = new Party(params)
        System.out.println("****************save****************** "+params.parent)
        if(!partyInstance.hasErrors() ) {
        	partyInstance.partyType="GA" 
            partyInstance.createdBy="admin"
           	partyInstance.createdDate = new Date();
           	partyInstance.modifiedBy="admin"
           	
       		def partyService = new PartyService()
           	partyInstance = partyService.saveGrantAgency(partyInstance)
           	
           	if(partyInstance.saveMode != null){
           		if(partyInstance.saveMode.equals("Saved")){
           			flash.message = "Grant Agency ${partyInstance.code} created"
		            redirect(action:list,id:partyInstance.id)
           		}
           		else if(partyInstance.saveMode.equals("Duplicate")){
           			flash.message = "Grant Agency Already Exists"
                    render(view:'create',model:[partyInstance:partyInstance])
           		}
           	}
           	else {
                render(view:'create',model:[partyInstance:partyInstance])
            }
        }
        else {
            render(view:'create',model:[partyInstance:partyInstance])
        }
    }
    
    
}
