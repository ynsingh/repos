import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class ProposalApprovalDetailController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def preProposalService
    def proposalApprovalDetailService
    def approvalAuthorityDetailService
    def proposalApprovalAuthorityMapService
    def fullProposalDetailService
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
    	
        def proposalApprovalDetailInstance = new ProposalApprovalDetail(params)
       println "params ******************** "+params
        proposalApprovalDetailInstance.approvalDate=new Date()
        proposalApprovalDetailInstance.activeYesNo="Y"
        	GrailsHttpSession gh=getSession()
        	
        	def preProposalInstance = preProposalService.getPrePropsalById(proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId)
        	def proposalApprovalDetailUserInstance = proposalApprovalDetailService.getPreProposalApprovalDetailsByUserAndProposalId(gh.getValue("UserId"),params.proposalId,proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id)
        	def preProposalApprovalRestartMinOrder=proposalApprovalAuthorityMapService.getPreProposalApprovalRestartMinOrder("PreProposal",preProposalInstance.id)
        	def preProposalApprovalMaxOrder=proposalApprovalAuthorityMapService.getPreProposalApprovalMaxOrder("PreProposal",preProposalInstance.id)
        if(proposalApprovalDetailUserInstance == null)
        {
        	if (proposalApprovalDetailInstance.save(flush: true)) 
        	{
        	def proposalApprovalDetailByApprovalAuthority=proposalApprovalDetailService.getPreProposalApprovalDetailsByAuthorityAndProposalId(proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id,proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId)
        	def approvalAuthorityInstance = approvalAuthorityDetailService.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id)
        	def proposalApprovalDetailByProposalStatus =proposalApprovalDetailService.getApprovedPreProposalApprovalDetailsByAuthorityAndProposalId(proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id,proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId)
        	def proposalApprovalDetailByProposalRejectedStatus=proposalApprovalDetailService.getPreProposalApprovalDetailByProposalRejectedStatus(proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id,proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId)
        	println "proposalApprovalDetailInstance "+proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
        				println "preProposalLevel "+preProposalInstance.preProposalLevel
        	if(proposalApprovalDetailByApprovalAuthority.size()==approvalAuthorityInstance.size())
        	{
        		if(approvalAuthorityInstance[0].approvalAuthority.approveAll=='Y')
        		{
        			if(proposalApprovalDetailByProposalStatus.size()==approvalAuthorityInstance.size())
        			{
        				println "proposalApprovalDetailInstance "+proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
        				println "preProposalLevel "+preProposalInstance.preProposalLevel
        				if(preProposalInstance.preProposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
        				{
        				//preProposalInstance.preProposalLevel=preProposalInstance.preProposalLevel+1
        					preProposalInstance.preProposalLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
        				}
                		preProposalInstance.save()
                		if(preProposalApprovalMaxOrder[0]==preProposalInstance.preProposalLevel)
        				{
        					preProposalInstance.preProposalStatus="Approved"
                    		preProposalInstance.save()
        				}
        			}
        			else
        			{
        				if(proposalApprovalDetailByProposalRejectedStatus.size()==approvalAuthorityInstance.size())
        				{
        					if(preProposalInstance.preProposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
            				{
            				//preProposalInstance.preProposalLevel=preProposalInstance.preProposalLevel+1
            					preProposalInstance.preProposalLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
            				}
                    		
            					preProposalInstance.preProposalStatus="Rejected"
                        		preProposalInstance.save()
            				
        				}
        				else
        				{
        					preProposalInstance.preProposalLevel=preProposalInstance.preProposalLevel+1
                    			preProposalInstance.preProposalStatus="NeedMoreInfo"
                        		preProposalInstance.preProposalLevel=new Integer(preProposalApprovalRestartMinOrder[0])-1
                        		preProposalInstance.save()
                        		def proposalApprovalDetailInactivate = proposalApprovalDetailService.inactivateProposalApprovalDetailForNeedMoreInfo(preProposalInstance)
            				
        				}
        			}
        		}
        		else
        		{
        			if(proposalApprovalDetailByProposalRejectedStatus.size()==approvalAuthorityInstance.size())
        				{
        				if(preProposalInstance.preProposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
        				{
        				//preProposalInstance.preProposalLevel=preProposalInstance.preProposalLevel+1
        					preProposalInstance.preProposalLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
        				}
                    			preProposalInstance.preProposalStatus="Rejected"
                        			preProposalInstance.save()
            			
        				}
        			else if(proposalApprovalDetailByProposalStatus)
        			{
        				if(preProposalInstance.preProposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
        				{
        				//preProposalInstance.preProposalLevel=preProposalInstance.preProposalLevel+1
        					preProposalInstance.preProposalLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
        				}
                		preProposalInstance.save()
                		if(preProposalApprovalMaxOrder[0]==preProposalInstance.preProposalLevel)
        				{
        					preProposalInstance.preProposalStatus="Approved"
                    		preProposalInstance.save()
        				}
        			}
        			else
        			{
        				if(preProposalInstance.preProposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
        				{
        				//preProposalInstance.preProposalLevel=preProposalInstance.preProposalLevel+1
        					preProposalInstance.preProposalLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
        				}
                			preProposalInstance.preProposalStatus="NeedMoreInfo"
                    		preProposalInstance.preProposalLevel=new Integer(preProposalApprovalRestartMinOrder[0])-1
                    		preProposalInstance.save()
                    		def proposalApprovalDetailInactivate = proposalApprovalDetailService.inactivateProposalApprovalDetailForNeedMoreInfo(preProposalInstance)
        			}
        		}
        		
        	}
        	flash.message = "${message(code: 'default.ReviewedSuccessfully.label')}"
            redirect(controller:"proposalApproval",action: "list")
        }
        else {
            render(view: "create", model: [proposalApprovalDetailInstance: proposalApprovalDetailInstance])
        }
        }
        else
        {
        	flash.message = "${message(code: 'default.Allreadyreviewed.label')}"
        	redirect(controller:"proposalApproval",action: "list")
        }
    }
// new save

		def saveFullProposal = {
		    	
		        def proposalApprovalDetailInstance = new ProposalApprovalDetail(params)
		        proposalApprovalDetailInstance.approvalDate=new Date()
		        proposalApprovalDetailInstance.activeYesNo="Y"
		        	GrailsHttpSession gh=getSession()
		        	
		        	def preProposalInstance = FullProposal.get(proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId)
		        	def proposalApprovalDetailUserInstance = proposalApprovalDetailService.getFullProposalApprovalDetailsByUserAndProposalId(gh.getValue("UserId"),params.proposalId,proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id)
		        	def preProposalApprovalRestartMinOrder=proposalApprovalAuthorityMapService.getPreProposalApprovalRestartMinOrder("FullProposal",preProposalInstance.id)
		        	def preProposalApprovalMaxOrder=proposalApprovalAuthorityMapService.getPreProposalApprovalMaxOrder("FullProposal",preProposalInstance.id)
		        if(proposalApprovalDetailUserInstance == null)
		        {
		        	if (proposalApprovalDetailInstance.save(flush: true)) {
		        	def proposalApprovalDetailByApprovalAuthority=proposalApprovalDetailService.getFullProposalApprovalDetailsByAuthorityAndProposalId(proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id,proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId)
		        	
		        	def approvalAuthorityInstance = approvalAuthorityDetailService.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id)
		        	def proposalApprovalDetailByProposalStatus =proposalApprovalDetailService.getApprovedFullProposalApprovalDetailsByAuthorityAndProposalId(proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id,proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId)
		        	def proposalApprovalDetailByProposalRejectedStatus=proposalApprovalDetailService.getFullProposalApprovalDetailByProposalRejectedStatus(proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id,proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId)
		        	if(proposalApprovalDetailByApprovalAuthority.size()==approvalAuthorityInstance.size())
		        	{
		        		if(approvalAuthorityInstance[0].approvalAuthority.approveAll=='Y')
		        		{
		        			if(proposalApprovalDetailByProposalStatus.size()==approvalAuthorityInstance.size())
		        			{
		        				if(preProposalInstance.preProposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
		        				{
		        				//preProposalInstance.preProposalLevel=preProposalInstance.preProposalLevel+1
		        					preProposalInstance.preProposalLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
		        				}
		                		preProposalInstance.save()
		                		if(preProposalApprovalMaxOrder[0]==preProposalInstance.preProposalLevel)
		        				{
		        					preProposalInstance.proposalStatus="Approved"
		                    		preProposalInstance.save()
		        				}
		        			}
		        			else
		        			{
		        				if(proposalApprovalDetailByProposalRejectedStatus.size()==approvalAuthorityInstance.size())
		        				{
		        					if(preProposalInstance.preProposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
		            				{
		            				//preProposalInstance.preProposalLevel=preProposalInstance.preProposalLevel+1
		            					preProposalInstance.preProposalLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
		            				}
		                    		preProposalInstance.proposalStatus="Rejected"
		                        		preProposalInstance.save()
		            				
		        				}
		        				else
		        				{
		        					if(preProposalInstance.preProposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
		            				{
		            				//preProposalInstance.preProposalLevel=preProposalInstance.preProposalLevel+1
		            					preProposalInstance.preProposalLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
		            				}
		                    			preProposalInstance.proposalStatus="NeedMoreInfo"
		                        		preProposalInstance.preProposalLevel=new Integer(preProposalApprovalRestartMinOrder[0])-1
		                        		preProposalInstance.save()
		                        		def proposalApprovalDetailInactivate = proposalApprovalDetailService.inactivateFullProposalApprovalDetailForNeedMoreInfo(preProposalInstance)
		        				}
		        			}
		        		}
		        		
		        		else
		        		{
		        			if(proposalApprovalDetailByProposalRejectedStatus.size()==approvalAuthorityInstance.size())
		        				{
		        				if(preProposalInstance.preProposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
		        				{
		        				//preProposalInstance.preProposalLevel=preProposalInstance.preProposalLevel+1
		        					preProposalInstance.preProposalLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
		        				}
		                    			preProposalInstance.proposalStatus="Rejected"
		                        			preProposalInstance.save()
		            			
		        				}
		        			else if(proposalApprovalDetailByProposalStatus)
		        			{
		        				if(preProposalInstance.preProposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
		        				{
		        				//preProposalInstance.preProposalLevel=preProposalInstance.preProposalLevel+1
		        					preProposalInstance.preProposalLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
		        				}
		                		preProposalInstance.save()
		                		if(preProposalApprovalMaxOrder[0]==preProposalInstance.preProposalLevel)
		        				{
		        					preProposalInstance.proposalStatus="Approved"
		                    		preProposalInstance.save()
		        				}
		        			}
		        			else
		        			{
		        				if(preProposalInstance.preProposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
		        				{
		        				//preProposalInstance.preProposalLevel=preProposalInstance.preProposalLevel+1
		        					preProposalInstance.preProposalLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
		        				}
		                		preProposalInstance.proposalStatus="NeedMoreInfo"
		                    		preProposalInstance.preProposalLevel=new Integer(preProposalApprovalRestartMinOrder[0])-1
		                    		preProposalInstance.save()
		                    		def proposalApprovalDetailInactivate = proposalApprovalDetailService.inactivateFullProposalApprovalDetailForNeedMoreInfo(preProposalInstance)
		        			}
		        		}
		        		
		        	}
		        	flash.message = "${message(code: 'default.ReviewedSuccessfully.label', args: [message(code: 'proposalApprovalDetail.label', default: 'Proposal'), proposalApprovalDetailInstance.proposalStatus])}"
		            redirect(controller:"proposalApproval",action: "fullProposalList")
		        }
		        else {
		            render(view: "create", model: [proposalApprovalDetailInstance: proposalApprovalDetailInstance])
		        }
		        }
		        else
		        {
		        	flash.message = "${message(code: 'default.Allreadyreviewed.label')}"
		        	redirect(controller:"proposalApproval",action: "fullProposalList")
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
    	def proposalApprovalInstance = ProposalApproval.get(params.id)
    	GrailsHttpSession gh=getSession()
      
    	def preProposalInstance = preProposalService.getPreProposalById(proposalApprovalInstance.proposalApprovalAuthorityMap.proposalId)
    	gh.putValue("ProposalId",preProposalInstance.id);
    	[proposalApprovalInstance:proposalApprovalInstance,preProposalInstance:preProposalInstance,proposalApplicationForm:preProposalInstance.preProposalForm]
    }
    def fullProposalReview =
    {
    	
    	def proposalApprovalInstance = ProposalApproval.get(params.id)
    	GrailsHttpSession gh=getSession()    	   	
    	def fullProposalDetailInstance = fullProposalDetailService.getFullProposalDetailByFullProposalId(proposalApprovalInstance.proposalApprovalAuthorityMap.proposalId)
    	//gh.putValue("ProposalId",fullProposalDetailInstance?.id);
    	[fullProposalDetailInstance:fullProposalDetailInstance,proposalApprovalInstance:proposalApprovalInstance]
    }
    def preProposalReviewStatus =
    {
    	def proposalApprovalDetailInstance = new ProposalApprovalDetail()
    }
}
