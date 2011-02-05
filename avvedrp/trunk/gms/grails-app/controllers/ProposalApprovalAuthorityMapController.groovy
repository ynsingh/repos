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
        	proposalApprovalAuthorityMapInstanceList = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAAM WHERE PAAM.approvalAuthority IN ("+approvalAuthorityInstance[i].id+") and PAAM.proposalType NOT IN('ExpenseRequest')")
        	for(int j=0;j<proposalApprovalAuthorityMapInstanceList.size();j++)
        		proposalApprovalAuthorityMapList.add(proposalApprovalAuthorityMapInstanceList[j])
        	
        }
        
        return ['proposalApprovalAuthorityMapInstance': proposalApprovalAuthorityMapInstance,'preProposalList':preProposalList,'proposalApprovalAuthorityMapInstanceList': proposalApprovalAuthorityMapList,'approvalAuthorityInstance':approvalAuthorityInstance]
    }

    def save = {
		def proposalApprovalAuthorityMapService = new ProposalApprovalAuthorityMapService()
        def proposalApprovalAuthorityMapInstance = new ProposalApprovalAuthorityMap(params)
		println"params"+params
        def proposalApprovalAuthorityMapData = proposalApprovalAuthorityMapService.checkDuplicateProposalApprovalAuthorityMap(params)
        println"proposalApprovalAuthorityMapData"+proposalApprovalAuthorityMapData
        if(proposalApprovalAuthorityMapData)
        {
        	flash.message ="${message(code: 'default.AlreadyExists.label')}"
	    		redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
        }
        else 
        {
        if (proposalApprovalAuthorityMapInstance.save(flush: true)) {
        	
        	
        	def approvalAuthorityDetailInstance = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AD where AD.activeYesNo='Y' and AD.approvalAuthority.id="+proposalApprovalAuthorityMapInstance.approvalAuthority.id)
          
        	for(approvalAuthorityDetails in approvalAuthorityDetailInstance)
           {
        	  
        	  def proposalApprovalInstance = new ProposalApproval()
        	  proposalApprovalInstance.proposalApprovalAuthorityMap=proposalApprovalAuthorityMapInstance
        	   proposalApprovalInstance.approvalAuthorityDetail=approvalAuthorityDetails
        	   proposalApprovalInstance.save()
           }
        	flash.message = "${message(code: 'default.ProposalApprovalAuthorityMapCreated.label')}"
            redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
        }
        else {
            render(view: "create", model: [proposalApprovalAuthorityMapInstance: proposalApprovalAuthorityMapInstance])
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
        println"params"+proposalApprovalAuthorityMapInstance
        
        def preProposalInstance = PreProposal.find("from PreProposal P where P.id ="+proposalApprovalAuthorityMapInstance.proposalId)
        println"preProposalInstance"+preProposalInstance
        
        if (!proposalApprovalAuthorityMapInstance) {
        	
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap'), params.id])}"
            redirect(action: "edit")
        }
        else {
            return ['proposalApprovalAuthorityMapInstance': proposalApprovalAuthorityMapInstance,'approvalAuthorityInstance':approvalAuthorityInstance,'preProposalInstance':preProposalInstance]
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
           
            def proposalApprovalAuthorityMapDetail = proposalApprovalAuthorityMapService.checkDuplicateEditProposalApprovalAuthorityMap(params)
            println"proposalApprovalAuthorityMapDetail"+proposalApprovalAuthorityMapDetail
            if(proposalApprovalAuthorityMapDetail)
            {
            	flash.message ="${message(code: 'default.AlreadyExists.label')}"
    	    		redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
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
    	println "params get "+params
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
    	println "preProposalList "+preProposalList
    	//render (template:"proposalSelect", model : ['preProposalList' : preProposalList])
    }
}
