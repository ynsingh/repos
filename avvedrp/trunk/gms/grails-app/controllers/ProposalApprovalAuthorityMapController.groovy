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
        proposalApprovalAuthorityMapInstance.properties = params
        def preProposalList = preProposalService.getSubmittedPreProposal(gh.getValue("Party"))
        def approvalAuthorityInstance = ApprovalAuthority.findAll("from ApprovalAuthority A where A.party="+gh.getValue("Party"))
        println"preProposalList"+preProposalList
        return ['proposalApprovalAuthorityMapInstance': proposalApprovalAuthorityMapInstance,'preProposalList':preProposalList,'proposalApprovalAuthorityMapInstanceList': ProposalApprovalAuthorityMap.list(params),'approvalAuthorityInstance':approvalAuthorityInstance]
    }

    def save = {
        def proposalApprovalAuthorityMapInstance = new ProposalApprovalAuthorityMap(params)
       
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
        def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.get(params.id)
        def approvalAuthorityInstance = ApprovalAuthority.findAll("from ApprovalAuthority A where A.party="+gh.getValue("Party"))
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
            proposalApprovalAuthorityMapInstance.properties = params
            if (!proposalApprovalAuthorityMapInstance.hasErrors() && proposalApprovalAuthorityMapInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.ProposalApprovalAuthorityMapUpdated.label')}"
                redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
            }
            else {
                render(view: "edit", model: [proposalApprovalAuthorityMapInstance: proposalApprovalAuthorityMapInstance])
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
