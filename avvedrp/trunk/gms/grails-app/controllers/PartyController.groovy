import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession


class PartyController  extends GmsController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        
        String subQuery ="";
        GrailsHttpSession gh=getSession()
        //adding help page into session
        gh.putValue("Help","Institution_List.htm")
        if(params.sort != null && !params.sort.equals(""))
        	subQuery=" order by P."+params.sort
        if(params.order != null && !params.order.equals(""))
        	subQuery =subQuery+" "+params.order
        
    	def partyInstanceList
    	def dataSecurityService = new DataSecurityService()
        def partyService = new PartyService()
    	
        if(getUserRole().equals('ROLE_ADMIN')){
        	partyInstanceList = partyService.getPartiesWithoutPartyType(subQuery)
        }
        else
        {
        	subQuery=" and P.id="+getUserPartyID()
        	partyInstanceList = partyService.getPartiesWithoutPartyType(subQuery)
        }
        
   
      
        [ partyInstanceList: partyInstanceList ]
    }

    def show = {
		def partyService = new PartyService()
		def partyInstance = partyService.getPartyById(new Integer(params.id ))

        if(!partyInstance) {
            flash.message = "Institution not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ partyInstance : partyInstance ] }
    }

    def delete = {
		def partyService = new PartyService()
		Integer partyId = partyService.deleteParty(new Integer(params.id))
		
		if(partyId != null){
			flash.message = "Institution ${params.nameOfTheInstitution} deleted"
			redirect(action:list)
		}
		else {
            flash.message = "Institution ${params.code} involved in grant Allocation,so could not delete"
            redirect(action:list)
        }
    }

    def edit = {
		def partyService = new PartyService()
		def partyInstance = partyService.getPartyById(new Integer(params.id ))
        if(!getUserRole().equals('ROLE_ADMIN')){
		if(!params.id.toString().equals(getUserPartyID().toString()))
			 redirect uri:'/invalidAccess.gsp'
        }
        if(!partyInstance) {
            flash.message = "Institution not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ partyInstance : partyInstance ]
        }
    }

    def update = {
		def partyService = new PartyService()
		def partyInstance = partyService.updateParty(params)
		
		if(partyInstance){
			if(partyInstance.saveMode != null){
				if(partyInstance.saveMode.equals("Updated")){
					flash.message = "Institution ${params.nameOfTheInstitution} updated"
	                redirect(action:list,id:partyInstance.id)
				}
				else if(partyInstance.saveMode.equals("Duplicate")){
					flash.message = "Institution Already Exists"
	    	    	render(view:'edit',model:[partyInstance:partyInstance])
				}
			}
			else {
                render(view:'edit',model:[partyInstance:partyInstance])
            }
		}
		else {
            flash.message = "Institution not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    		
    }

    def create = {
        def partyInstance = new Party()
        GrailsHttpSession gh=getSession()
        //Adding Help page into session
        gh.putValue("Help","Institution.htm")
        return ['partyInstance':partyInstance]
    }

    def save = {
        def partyInstance = new Party(params)
        if(!partyInstance.hasErrors() ) {
        	partyInstance.createdBy="admin"
           	partyInstance.createdDate = new Date();
           	partyInstance.modifiedBy="admin"
           	
       		def partyService = new PartyService()
           	partyInstance = partyService.saveParty(partyInstance)
           	
           	if(partyInstance.saveMode != null){
           		if(partyInstance.saveMode.equals("Saved")){
           			flash.message = "Institution ${partyInstance.nameOfTheInstitution} created"
		            redirect(action:list,id:partyInstance.id)
           		}
           		else if(partyInstance.saveMode.equals("Duplicate")){
           			flash.message = "Institution Already Exists"
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
    
   
    def showSubInstitutions = {
        def partyInstance = new Party()
		def partyInstanceList= new Party();
        
        def partyService = new PartyService()
		def partyList=partyService.getPartyById(new Integer(params.id))
		partyInstance.parent=partyList
		
        partyInstance.properties = params
        
        partyInstanceList=partyService.getSubParties(new Integer(params.id))
        return ['partyInstance':partyInstance,'partyInstanceList':partyInstanceList]
    }
   
    
}
