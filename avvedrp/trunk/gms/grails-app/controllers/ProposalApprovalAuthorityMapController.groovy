import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class ProposalApprovalAuthorityMapController {
	def preProposalService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "create", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [proposalApprovalAuthorityMapInstanceList: ProposalApprovalAuthorityMap.list(params), proposalApprovalAuthorityMapInstanceTotal: ProposalApprovalAuthorityMap.count()]
    }

    def create = {
		GrailsHttpSession gh=getSession()
        def proposalApprovalAuthorityMapInstance = new ProposalApprovalAuthorityMap()
        def preProposalService = new PreProposalService()
		def approvalAuthorityService = new ApprovalAuthorityService()
		def proposalApprovalAuthorityMapInstanceList = []
        proposalApprovalAuthorityMapInstance.properties = params
        def preProposalList = preProposalService.getSubmittedPreProposal(gh.getValue("Party"))
        def approvalAuthorityInstance = approvalAuthorityService.getActiveApprovalAuthority(gh.getValue("PartyID"))
        def proposalApprovalAuthorityMapList = []
		
	
		
        for(int i=0;i<approvalAuthorityInstance.size();i++)
        {
        	proposalApprovalAuthorityMapInstanceList = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAAM WHERE PAAM.approvalAuthority IN ("+approvalAuthorityInstance[i].id+") and PAAM.proposalType NOT IN('ExpenseRequest','Proposal')")
        	for(int j=0;j<proposalApprovalAuthorityMapInstanceList.size();j++)
        		proposalApprovalAuthorityMapList.add(proposalApprovalAuthorityMapInstanceList[j])
        	
        }
        
        return ['proposalApprovalAuthorityMapInstance': proposalApprovalAuthorityMapInstance,'preProposalList':preProposalList,'proposalApprovalAuthorityMapInstanceList': proposalApprovalAuthorityMapList,'approvalAuthorityInstance':approvalAuthorityInstance]
    }

    def save = 
    {
		def proposalApprovalAuthorityMapService = new ProposalApprovalAuthorityMapService()
        def proposalApprovalAuthorityMapInstance = new ProposalApprovalAuthorityMap(params)
        def proposalApprovalAuthorityMapData = proposalApprovalAuthorityMapService.checkDuplicateProposalApprovalAuthorityMap(proposalApprovalAuthorityMapInstance,params)
        if(proposalApprovalAuthorityMapData)
        {
        	flash.error ="${message(code: 'default.AlreadyExistsAuthorityApproveOrder.label')}"
        	redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
        }
        else 
        {
         	def proposalApprovalAuthorityMapList = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PM where PM.activeYesNo='Y' and PM.proposalId="+params.proposalId+" and PM.proposalType='"+params.proposalType+"' and PM.approveOrder= "+params.approveOrder)
        	if(proposalApprovalAuthorityMapList.size())
        	{
        		flash.error ="${message(code: 'default.AlreadyExistsApproveOrder.label')}"
        		redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
        	}
        	else
        	{
        		proposalApprovalAuthorityMapInstance.activeYesNo = 'Y'
        		if (proposalApprovalAuthorityMapInstance.save(flush: true))
	        	{
	        		
	        		flash.message = "${message(code: 'default.ProposalApprovalAuthorityMapCreated.label')}"
	        		redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
	        	}
	        	else
	        	{
	        		render(view: "create", model: [proposalApprovalAuthorityMapInstance: proposalApprovalAuthorityMapInstance])
	        	}
        	}
        }
	}

    def show = {
        def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.get(params.id)
        if (!proposalApprovalAuthorityMapInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap'), params.id])}"
            redirect(action: "list")
        }
        else {
            [proposalApprovalAuthorityMapInstance: proposalApprovalAuthorityMapInstance]
        }
    }

    def edit = {
		GrailsHttpSession gh=getSession()
		def approvalAuthorityService = new ApprovalAuthorityService()
        def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.get(params.id)
        def approvalAuthorityInstance = approvalAuthorityService.getActiveApprovalAuthority(gh.getValue("PartyID"))
        def preProposalList = preProposalService.getSubmittedPreProposal(gh.getValue("Party"))
        def preProposalInstance = PreProposal.find("from PreProposal P where P.id ="+proposalApprovalAuthorityMapInstance.proposalId)
        if (!proposalApprovalAuthorityMapInstance) {
        	
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap'), params.id])}"
            redirect(action: "edit")
        }
        else {
            return ['proposalApprovalAuthorityMapInstance': proposalApprovalAuthorityMapInstance,'approvalAuthorityInstance':approvalAuthorityInstance,'preProposalInstance':preProposalInstance,'preProposalList':preProposalList]
        }
    }

    def update = {
       def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.get(params.id)
       
       
        if (proposalApprovalAuthorityMapInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (proposalApprovalAuthorityMapInstance.version > version) {
                    
                    proposalApprovalAuthorityMapInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap')] as Object[], "Another user has updated this ProposalApprovalAuthorityMap while you were editing")
                    render(view: "edit", model: [proposalApprovalAuthorityMapInstance: proposalApprovalAuthorityMapInstance])
                    return
                }
            }
            
            def proposalApprovalAuthorityMapService = new ProposalApprovalAuthorityMapService()
            def proposalApprovalAuthorityMapDetail = proposalApprovalAuthorityMapService.checkDuplicateProposalApprovalAuthorityMap(proposalApprovalAuthorityMapInstance,params)
            	def proposalList = ProposalApprovalDetail.executeQuery(" from ProposalApprovalDetail PA where PA.proposalApproval.proposalApprovalAuthorityMap.proposalId ="+ proposalApprovalAuthorityMapInstance.proposalId +" and  PA.proposalApproval.proposalApprovalAuthorityMap.proposalType='"+proposalApprovalAuthorityMapInstance.proposalType+"'") 
            	println "proposalList"+proposalList
            	if(proposalList)
            	{
            		flash.message ="${message(code: 'default.ReviewStarts.message')}"
            		redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
            	}
            	else  if((proposalApprovalAuthorityMapDetail) && (proposalApprovalAuthorityMapDetail[0].id != Long.parseLong(params.id)))
              {
            	flash.message ="${message(code: 'default.AlreadyExists.label')}"
    	    	redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
              }
           
            else 
             {
            	
            	def proposalApprovalAuthorityMapList = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PM where PM.activeYesNo='Y' and PM.proposalId="+params.proposalId+" and PM.proposalType='"+params.proposalType+"' and PM.approveOrder= "+params.approveOrder)
            	
	            	if(proposalApprovalAuthorityMapList.size())
	            	{
	            		
	            		if(new Integer(params.id)==proposalApprovalAuthorityMapList[0].id)
	            		{
	            			proposalApprovalAuthorityMapInstance.properties = params
		                		if (!proposalApprovalAuthorityMapInstance.hasErrors() && proposalApprovalAuthorityMapInstance.save(flush: true)) {
			    	           			flash.message = "${message(code: 'default.ProposalApprovalAuthorityMapUpdated.label')}"
			    		                redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
		    		            	}
		    		            else {
		    		                	render(view: "edit", model: [proposalApprovalAuthorityMapInstance: proposalApprovalAuthorityMapInstance])
		    		                }
	            		}
	            		else
	            		{
		            		flash.error ="${message(code: 'default.AlreadyExistsApproveOrder.label')}"
		            		redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
		            	}
	            	}
	            	else
	            	{
		            	proposalApprovalAuthorityMapInstance.properties = params
	            		if (!proposalApprovalAuthorityMapInstance.hasErrors() && proposalApprovalAuthorityMapInstance.save(flush: true)) {
		           			flash.message = "${message(code: 'default.ProposalApprovalAuthorityMapUpdated.label')}"
			                redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
			            	}
			            else {
			                render(view: "edit", model: [proposalApprovalAuthorityMapInstance: proposalApprovalAuthorityMapInstance])
			            }
	            	}
            	
            }
        
        }
     else {
	        flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap'), params.id])}"
	        redirect(action: "list")
    }
    }

    def delete = {
        def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.get(params.id)
        if (proposalApprovalAuthorityMapInstance) {
            try {
                proposalApprovalAuthorityMapInstance.delete(flush: true)
                flash.message = "${message(code: 'default.ProposalApprovalAuthorityMapDeleted.label')}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap'), params.id])}"
            redirect(action: "list")
        }
    }
    def getProposals =
    {
    	def preProposalList 
    	GrailsHttpSession gh=getSession()
    	if(params.proposal=="PreProposal")
    	{
    		preProposalList = preProposalService.getSubmittedPreProposal(gh.getValue("Party"))
    		render (template:"preProposalSelect", model : ['preProposalList' : preProposalList])
    	}
    	else
    	{
    		preProposalList = FullProposal.findAll("from FullProposal PP where PP.proposalStatus = 'Submitted' and PP.preProposal.party="+gh.getValue("Party"))
    		for(preProposalValue in preProposalList)
    		{
    			def preProposalId = PreProposal.get(preProposalValue.preProposal.id)
    			preProposalValue.projectTitle=preProposalId.projectTitle
    		}
    		render (template:"proposalSelect", model : ['preProposalList' : preProposalList])
    	}
    	//render (template:"proposalSelect", model : ['preProposalList' : preProposalList])
    }
}
