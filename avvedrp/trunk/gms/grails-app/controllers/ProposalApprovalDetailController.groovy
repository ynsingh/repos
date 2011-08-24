import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class ProposalApprovalDetailController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def proposalService
    def proposalApprovalDetailService
    def approvalAuthorityDetailService
    def proposalApprovalAuthorityMapService
    def fullProposalDetailService
    def fullProposalService
    def ProposalApprovalService
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [proposalApprovalDetailInstanceList: ProposalApprovalDetail.list(params), proposalApprovalDetailInstanceTotal: ProposalApprovalDetail.count()]
    }

    def create = {
        def proposalApprovalDetailInstance = new ProposalApprovalDetail()
        proposalApprovalDetailInstance.properties = params
        return [proposalApprovalDetailInstance: proposalApprovalDetailInstance]
    }

    def save = {
    	GrailsHttpSession gh=getSession()
    	def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(params.proposalId)
    	/*method to get preProposalInstance using id*/
    	def proposalInstance = proposalService.getPrePropsalById(params.proposalId)
    	/*method to get proposalApprovalAuthorityMap using id*/
    	def proposalApprovalAuthorityMapInstance=proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapById(params.proposalApprovalAuthorityMap.id)
        /*method to get approvalAuthorityDetail using id*/
    	def approvalAuthorityDetailInstance=approvalAuthorityDetailService.getApprovalAuthorityDetailById(new Integer(params.approvalAuthorityDetail.id))
    	/*method to get proposalApproval of current user*/
    	def proposalApprovalUserInstance = proposalApprovalService.getProposalApprovalByProposalApprovalAuthorityMapAndUser(proposalApprovalAuthorityMapInstance.id,approvalAuthorityDetailInstance.id)
        def proposalApprovalInstance
        /*check proposalApproval exist or not */
    	if(!proposalApprovalUserInstance)
    	{
    		proposalApprovalInstance = new ProposalApproval(params['ProposalApproval'])
    		proposalApprovalInstance=proposalApprovalService.saveProposalApproval(proposalApprovalInstance)
    	}
    	else
    	{
    		proposalApprovalInstance = proposalApprovalUserInstance
    	}
    		if(proposalApprovalInstance)
    		{
    			/*check if the user is already review the preProposal */
    			def proposalApprovalDetailUserInstance = proposalApprovalDetailService.getProposalApprovalDetailByProposalApproval(proposalApprovalInstance.id)
    			/*check if the user is already review the preProposal */
    			if(!proposalApprovalDetailUserInstance)
    			{
    				/*save ProposalApprovalDetail*/
    				def proposalApprovalDetailInstance = new ProposalApprovalDetail(params['ProposalApprovalDetail'])
    				proposalApprovalDetailInstance.proposalApproval=proposalApprovalInstance
    				proposalApprovalDetailInstance.activeYesNo='Y'
    				proposalApprovalDetailInstance.approvalDate=new Date()
    				def proposalApprovalDetailSaveInstance=proposalApprovalDetailService.saveProposalApprovalDetail(proposalApprovalDetailInstance)
    				if(proposalApprovalDetailSaveInstance)
    				{
    					/*validate proposal reviews*/
    					def proposalApprovalDetailValidateInstance = proposalApprovalDetailService.validateProposalApprovalDetail(proposalApprovalDetailInstance,"PreProposal",proposalInstance.proposalLevel,proposalInstance.proposalStatus)
	        			/*update proposal based on proposal reviews*/
    	    			if((proposalApprovalDetailValidateInstance.proposalStatus!=proposalInstance.proposalStatus)||(proposalApprovalDetailValidateInstance.proposalNewLevel!=proposalInstance.proposalLevel))
    	    			{
    	    				proposalInstance.proposalStatus=proposalApprovalDetailValidateInstance.proposalStatus
    	    				proposalInstance.proposalLevel=proposalApprovalDetailValidateInstance.proposalNewLevel
    	    				def preProposalSaveInstance=proposalService.updatePreProposalInstance(proposalInstance)
    	    				if(proposalApprovalDetailValidateInstance.proposalStatus=='NeedMoreInfo')
    	    				{
    	    					/*inactivate proposalApprovalDetails for review again*/
    	    					def proposalApprovalDetailInactivate = proposalApprovalDetailService.inactivateProposalApprovalDetailForNeedMoreInfo(proposalInstance.id,proposalInstance.proposalLevel,"PreProposal")
    	    				}
    	    			}
    	    			flash.message = "${message(code: 'default.ReviewedSuccessfully.label')}"
	            		redirect(controller:"proposalApproval",action: "list")
	    			}
    			
    			}
    			else
    			{
    				flash.message = "${message(code: 'default.Allreadyreviewed.label')}"
    				redirect(controller:"proposalApproval",action: "list")
    			}
    		}
    	
      
    }


		def saveFullProposal = 
		{
		    	
    	GrailsHttpSession gh=getSession()
    	//*method to get preProposalInstance using id*/
    	def fullProposalInstance = Proposal.get(params.proposalId)
    	/*method to get proposalApprovalAuthorityMap using id*/
    	def proposalApprovalAuthorityMapInstance=proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapById(params.proposalApprovalAuthorityMap.id)
        /*method to get approvalAuthorityDetail using id*/
    	def approvalAuthorityDetailInstance=approvalAuthorityDetailService.getApprovalAuthorityDetailById(new Integer(params.approvalAuthorityDetail.id))
        /*method to get proposalApproval of current user*/
    	def proposalApprovalUserInstance = proposalApprovalService.getProposalApprovalByProposalApprovalAuthorityMapAndUser(proposalApprovalAuthorityMapInstance.id,approvalAuthorityDetailInstance.id)
        def proposalApprovalInstance
        /*check proposalApproval exist or not */
    	if(!proposalApprovalUserInstance)
    	{
    		proposalApprovalInstance = new ProposalApproval(params['ProposalApproval'])
    		proposalApprovalInstance=proposalApprovalService.saveProposalApproval(proposalApprovalInstance)
    	}
    	else
    	{
    		proposalApprovalInstance = proposalApprovalUserInstance
    	   }
    		if(proposalApprovalInstance)
    		{
    			/*check if the user is already review the preProposal */
    		def proposalApprovalDetailUserInstance = proposalApprovalDetailService.getProposalApprovalDetailByProposalApproval(proposalApprovalInstance.id)
    			/*check if the user is already review the preProposal */
        	if(!proposalApprovalDetailUserInstance)
    			{
    				/*save ProposalApprovalDetail*/
    				def proposalApprovalDetailInstance = new ProposalApprovalDetail(params['ProposalApprovalDetail'])
    				proposalApprovalDetailInstance.proposalApproval=proposalApprovalInstance
    				proposalApprovalDetailInstance.activeYesNo='Y'
    				proposalApprovalDetailInstance.approvalDate=new Date()
    				def proposalApprovalDetailSaveInstance=proposalApprovalDetailService.saveProposalApprovalDetail(proposalApprovalDetailInstance)
    				if(proposalApprovalDetailSaveInstance)
    				{
    					/*validate proposal reviews*/
    					def proposalApprovalDetailValidateInstance = proposalApprovalDetailService.validateProposalApprovalDetail(proposalApprovalDetailInstance,"FullProposal",fullProposalInstance.proposalLevel,fullProposalInstance.proposalStatus)
	        			/*update proposal based on proposal reviews*/
    	    			if((proposalApprovalDetailValidateInstance.proposalStatus!=fullProposalInstance.proposalStatus)||(proposalApprovalDetailValidateInstance.proposalNewLevel!=fullProposalInstance.proposalLevel))
    	    			{
    	    				fullProposalInstance.proposalStatus=proposalApprovalDetailValidateInstance.proposalStatus
    	    				fullProposalInstance.proposalLevel=proposalApprovalDetailValidateInstance.proposalNewLevel
    	    				def fullProposalSaveInstance=proposalService.updateFullProposal(fullProposalInstance)
    	    				if(proposalApprovalDetailValidateInstance.proposalStatus=='NeedMoreInfo')
    	    				{
    	    					/*inactivate proposalApprovalDetails for review again*/
    	    					def proposalApprovalDetailInactivate = proposalApprovalDetailService.inactivateProposalApprovalDetailForNeedMoreInfo(fullProposalInstance.id,fullProposalInstance.proposalLevel,"FullProposal")
    	    				}
    	    			}
    	    			flash.message = "${message(code: 'default.ReviewedSuccessfully.label')}"
	            		redirect(controller:"proposalApproval",action: "fullProposalList")
	    			}
    			
    			}
    			else
    			{
    				flash.message = "${message(code: 'default.Allreadyreviewed.label')}"
    				redirect(controller:"proposalApproval",action: "fullProposalList")
    			}
    		}
    		
		  }

// new save end
    def show = {
        def proposalApprovalDetailInstance = ProposalApprovalDetail.get(params.id)
        if (!proposalApprovalDetailInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApprovalDetail.label', default: 'ProposalApprovalDetail'), params.id])}"
            redirect(action: "list")
        }
        else {
            [proposalApprovalDetailInstance: proposalApprovalDetailInstance]
        }
    }

    def edit = {
        def proposalApprovalDetailInstance = ProposalApprovalDetail.get(params.id)
        if (!proposalApprovalDetailInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApprovalDetail.label', default: 'ProposalApprovalDetail'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [proposalApprovalDetailInstance: proposalApprovalDetailInstance]
        }
    }

    def update = {
        def proposalApprovalDetailInstance = ProposalApprovalDetail.get(params.id)
        if (proposalApprovalDetailInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (proposalApprovalDetailInstance.version > version) {
                    
                    proposalApprovalDetailInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'proposalApprovalDetail.label', default: 'ProposalApprovalDetail')] as Object[], "Another user has updated this ProposalApprovalDetail while you were editing")
                    render(view: "edit", model: [proposalApprovalDetailInstance: proposalApprovalDetailInstance])
                    return
                }
            }
            proposalApprovalDetailInstance.properties = params
            if (!proposalApprovalDetailInstance.hasErrors() && proposalApprovalDetailInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'proposalApprovalDetail.label', default: 'ProposalApprovalDetail'), proposalApprovalDetailInstance.id])}"
                redirect(action: "show", id: proposalApprovalDetailInstance.id)
            }
            else {
                render(view: "edit", model: [proposalApprovalDetailInstance: proposalApprovalDetailInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApprovalDetail.label', default: 'ProposalApprovalDetail'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def proposalApprovalDetailInstance = ProposalApprovalDetail.get(params.id)
        if (proposalApprovalDetailInstance) {
            try {
                proposalApprovalDetailInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'proposalApprovalDetail.label', default: 'ProposalApprovalDetail'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'proposalApprovalDetail.label', default: 'ProposalApprovalDetail'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApprovalDetail.label', default: 'ProposalApprovalDetail'), params.id])}"
            redirect(action: "list")
        }
    }
    def preProposalReview = 
    {
    	GrailsHttpSession gh=getSession()
    	/*method to get pre proposal by id*/
        def proposalInstance = proposalService.getPreProposalById(new Integer(params.id))
    	/*method to get proposalApprovalAuthorityMap by id*/
        def proposalApprovalAuthorityMapInstance=proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapById(params.proposalApprovalAuthorityMap.id)
        /*method to get proposalApplication Instance by proposalId*/
        def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(proposalApprovalAuthorityMapInstance.proposalId)
        /*method to get ApprovalAuthorityDetail by approval authority and user id*/
        def approvalAuthorityDetailInstance=approvalAuthorityDetailService.getApprovalAuthorityDetailByApprovalAuthorityUser(proposalApprovalAuthorityMapInstance.approvalAuthority.id,gh.getValue("UserId"))
    	
      	gh.putValue("ProposalId",proposalInstance.id);
		[proposalInstance:proposalInstance,
    	 proposalApplicationForm:proposalInstance.proposalDocumentationPath,
    	 proposalApprovalAuthorityMapInstance:proposalApprovalAuthorityMapInstance,
    	 approvalAuthorityDetailInstance:approvalAuthorityDetailInstance,proposalApplicationInstance:proposalApplicationInstance]
    }
    def fullProposalReview =
    {
    	
    	GrailsHttpSession gh=getSession()   
    	def fullProposalDetailInstance = proposalService.getProposalApplicationByProposalId(params.id)
    	//ProposalApplication.find("from ProposalApplication PA where PA.proposal.id ="+params.id)
    	/*method to get proposalApprovalAuthorityMap by id*/
    	def proposalApprovalAuthorityMapInstance=proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapById(params.proposalApprovalAuthorityMap.id)
    	/*method to get ApprovalAuthorityDetail by approval authority and user id*/
    	def approvalAuthorityDetailInstance=approvalAuthorityDetailService.getApprovalAuthorityDetailByApprovalAuthorityUser(proposalApprovalAuthorityMapInstance.approvalAuthority.id,gh.getValue("UserId"))
    	/*method to get FullProposalDetail by fullproposal id*/
        //def fullProposalDetailInstance = fullProposalDetailService.getFullProposalDetailByFullProposalId(proposalApprovalAuthorityMapInstance.proposalId)
    	
    	[fullProposalDetailInstance:fullProposalDetailInstance,
    	 proposalApprovalAuthorityMapInstance:proposalApprovalAuthorityMapInstance,
    	 approvalAuthorityDetailInstance:approvalAuthorityDetailInstance]
    }
    def preProposalReviewStatus =
    {
    	def proposalApprovalDetailInstance = new ProposalApprovalDetail()
    }
}
