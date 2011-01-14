import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class ApprovalAuthorityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "create", params: params)
    }

    def list = {
    	GrailsHttpSession gh=getSession()
    	def partyInstance = Party.get(gh.getValue("Party"))
    	println"partyInstance"+partyInstance
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        //def approvalAuthorityList = ApprovalAuthority.findAll("from ApprovalAuthority A where A.activeYesNo = 'Y'")
        [approvalAuthorityInstanceList: ApprovalAuthority.list(params), approvalAuthorityInstanceTotal: ApprovalAuthority.count(),'approvalAuthorityList':approvalAuthorityList]
    }

    def create = {
    	
    	println"params***************"+params.id
        def approvalAuthorityInstance = new ApprovalAuthority(params)
    	approvalAuthorityInstance.save()
    	def approvalAuthorityService = new ApprovalAuthorityService()
       // approvalAuthorityInstance.properties = params
        
        GrailsHttpSession gh=getSession()
    	def partyInstance = Party.get(gh.getValue("Party"))
    	println"partyInstance"+partyInstance
    	 def approvalAuthorityList = approvalAuthorityService.getActiveApprovalAuthority(gh.getValue("PartyID"))
    		 
        return [approvalAuthorityInstance: approvalAuthorityInstance,'partyInstance':partyInstance,'approvalAuthorityList':approvalAuthorityList]

    }

    def save = {
    	def approvalAuthorityService = new ApprovalAuthorityService()
        def approvalAuthorityInstance = new ApprovalAuthority(params)
    	println"params"+params
    	approvalAuthorityInstance = approvalAuthorityService.saveApprovalAuthority(params)
    	
    	
        if (approvalAuthorityInstance) 
        {
            flash.message = "${message(code: 'default.created.label')}"
            redirect(action: "create", id: approvalAuthorityInstance.id)
        }
        else {
            render(view: "create", id: approvalAuthorityInstance.id)
        }
    }

    def show = {
    	def approvalAuthorityService = new ApprovalAuthorityService()
    	def approvalAuthorityInstance = approvalAuthorityService.getApprovalAuthorityById(new Integer(params.id ))
       
        if (!approvalAuthorityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'approvalAuthority.label', default: 'ApprovalAuthority'), params.id])}"
            redirect(action: "list")
        }
        else {
            [approvalAuthorityInstance: approvalAuthorityInstance]
        }
    }

    def edit = {
    	def approvalAuthorityService = new ApprovalAuthorityService()
    	def approvalAuthorityInstance = approvalAuthorityService.getApprovalAuthorityById(new Integer( params.id ))
       
        if (!approvalAuthorityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'approvalAuthority.label', default: 'ApprovalAuthority'), params.id])}"
            redirect(action: "create")
        }
        else {
            return [approvalAuthorityInstance: approvalAuthorityInstance]
        }
    }

    def update = {
    	def approvalAuthorityService = new ApprovalAuthorityService()
    	def approvalAuthorityInstance = approvalAuthorityService.getApprovalAuthorityById(new Integer( params.id ))
       
        if (approvalAuthorityInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (approvalAuthorityInstance.version > version) {
                    
                    approvalAuthorityInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'approvalAuthority.label', default: 'ApprovalAuthority')] as Object[], "Another user has updated this ApprovalAuthority while you were editing")
                    render(view: "edit", model: [approvalAuthorityInstance: approvalAuthorityInstance])
                    return
                }
            }
           
             approvalAuthorityInstance = approvalAuthorityService.updateApprovalAuthority(params,approvalAuthorityInstance)
            if( approvalAuthorityInstance.saveMode.equals( "Updated"))
            {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action: "create", id: approvalAuthorityInstance.id)
            }
            else {
                render(view: "edit", model: [approvalAuthorityInstance: approvalAuthorityInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'approvalAuthority.label', default: 'ApprovalAuthority'), params.id])}"
            redirect(action: "list")
        }
    }
    
    def delete = 
    {
    	
    	def approvalAuthorityService = new ApprovalAuthorityService()
    	def approvalAuthorityInstance = new ApprovalAuthority()
    	approvalAuthorityInstance.properties =params
    	
    		
            Integer approvalAuthorityId = null
            
            /* Delete the account head details */
    		approvalAuthorityId = approvalAuthorityService.deleteApprovalAuthority(params)
			if (approvalAuthorityId == 0)
			{
				/* Shows the following message if the account head is already in use. */
				 flash.message = "${message(code: 'default.approvalauthorityusedinapprovalauthoritydeatil.label')}"
				 redirect(action:edit,id:params.id)
			}
			else
			{	
				if(approvalAuthorityId != null)
				{				
					flash.message = "${message(code: 'default.deleted.label')}"			
							    		
						   redirect(action:create,id:approvalAuthorityInstance.id)
						
				}    
				else 
				{
		            flash.message = "${message(code: 'default.notbedeletedApprovalAuthority.label')}"
		            redirect(action:edit,id:params.id)
		        }
			}
    }

   
}
