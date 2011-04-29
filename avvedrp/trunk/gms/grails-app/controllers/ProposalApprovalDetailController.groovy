import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class ProposalApprovalDetailController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def preProposalService
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
    	/*method to get preProposalInstance using id*/
    	def preProposalInstance = preProposalService.getPrePropsalById(params.proposalId)
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
    					def proposalApprovalDetailValidateInstance = proposalApprovalDetailService.validateProposalApprovalDetail(proposalApprovalDetailInstance,"PreProposal",preProposalInstance.preProposalLevel,preProposalInstance.preProposalStatus)
	        			/*update proposal based on proposal reviews*/
    	    			if((proposalApprovalDetailValidateInstance.proposalStatus!=preProposalInstance.preProposalStatus)||(proposalApprovalDetailValidateInstance.proposalNewLevel!=preProposalInstance.preProposalLevel))
    	    			{
    	    				preProposalInstance.preProposalStatus=proposalApprovalDetailValidateInstance.proposalStatus
    	    				preProposalInstance.preProposalLevel=proposalApprovalDetailValidateInstance.proposalNewLevel
    	    				def preProposalSaveInstance=preProposalService.updatePreProposalInstance(preProposalInstance)
    	    				if(proposalApprovalDetailValidateInstance.proposalStatus=='NeedMoreInfo')
    	    				{
    	    					/*inactivate proposalApprovalDetails for review again*/
    	    					def proposalApprovalDetailInactivate = proposalApprovalDetailService.inactivateProposalApprovalDetailForNeedMoreInfo(preProposalInstance.id,preProposalInstance.preProposalLevel,"PreProposal")
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
    	/*method to get preProposalInstance using id*/
    	def fullProposalInstance = fullProposalService.getFullProposalById(params.proposalId)
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
    					def proposalApprovalDetailValidateInstance = proposalApprovalDetailService.validateProposalApprovalDetail(proposalApprovalDetailInstance,"FullProposal",fullProposalInstance.preProposalLevel,fullProposalInstance.proposalStatus)
	        			/*update proposal based on proposal reviews*/
    	    			if((proposalApprovalDetailValidateInstance.proposalStatus!=fullProposalInstance.proposalStatus)||(proposalApprovalDetailValidateInstance.proposalNewLevel!=fullProposalInstance.preProposalLevel))
    	    			{
    	    				fullProposalInstance.proposalStatus=proposalApprovalDetailValidateInstance.proposalStatus
    	    				fullProposalInstance.preProposalLevel=proposalApprovalDetailValidateInstance.proposalNewLevel
    	    				def fullProposalSaveInstance=fullProposalService.updateFullProposal(fullProposalInstance)
    	    				if(proposalApprovalDetailValidateInstance.proposalStatus=='NeedMoreInfo')
    	    				{
    	    					/*inactivate proposalApprovalDetails for review again*/
    	    					def proposalApprovalDetailInactivate = proposalApprovalDetailService.inactivateProposalApprovalDetailForNeedMoreInfo(fullProposalInstance.id,fullProposalInstance.preProposalLevel,"FullProposal")
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
        def preProposalInstance = preProposalService.getPreProposalById(new Integer(params.id))
    	/*method to get proposalApprovalAuthorityMap by id*/
        def proposalApprovalAuthorityMapInstance=proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapById(params.proposalApprovalAuthorityMap.id)
    	/*method to get ApprovalAuthorityDetail by approval authority and user id*/
        def approvalAuthorityDetailInstance=approvalAuthorityDetailService.getApprovalAuthorityDetailByApprovalAuthorityUser(proposalApprovalAuthorityMapInstance.approvalAuthority.id,gh.getValue("UserId"))
    	
    	gh.putValue("ProposalId",preProposalInstance.id);
    	[preProposalInstance:preProposalInstance,
    	 proposalApplicationForm:preProposalInstance.preProposalForm,
    	 proposalApprovalAuthorityMapInstance:proposalApprovalAuthorityMapInstance,
    	 approvalAuthorityDetailInstance:approvalAuthorityDetailInstance]
    }
    def fullProposalReview =
    {
    	
    	GrailsHttpSession gh=getSession()   
    	/*method to get full proposal by id*/
    	def fullProposalInstance = fullProposalService.getFullProposalById(params.id)
    	/*method to get proposalApprovalAuthorityMap by id*/
    	def proposalApprovalAuthorityMapInstance=proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapById(params.proposalApprovalAuthorityMap.id)
    	/*method to get ApprovalAuthorityDetail by approval authority and user id*/
    	def approvalAuthorityDetailInstance=approvalAuthorityDetailService.getApprovalAuthorityDetailByApprovalAuthorityUser(proposalApprovalAuthorityMapInstance.approvalAuthority.id,gh.getValue("UserId"))
    	/*method to get FullProposalDetail by fullproposal id*/
    	def fullProposalDetailInstance = fullProposalDetailService.getFullProposalDetailByFullProposalId(proposalApprovalAuthorityMapInstance.proposalId)
    	
    	[fullProposalDetailInstance:fullProposalDetailInstance,
    	 proposalApprovalAuthorityMapInstance:proposalApprovalAuthorityMapInstance,
    	 approvalAuthorityDetailInstance:approvalAuthorityDetailInstance]
    }
    def preProposalReviewStatus =
    {
    	def proposalApprovalDetailInstance = new ProposalApprovalDetail()
    }
}
