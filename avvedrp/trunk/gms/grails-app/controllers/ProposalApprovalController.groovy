import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class ProposalApprovalController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def proposalApprovalService
    def proposalApprovalDetailService
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
    	GrailsHttpSession gh=getSession()
    	def proposalApprovalInstanceNewList = proposalApprovalService.getProposalApprovalList(gh.getValue("UserId"))
    	params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [proposalApprovalInstanceList: proposalApprovalInstanceNewList, proposalApprovalInstanceTotal: ProposalApproval.count()]
    }
    def fullProposalList = 
    {
    	GrailsHttpSession gh=getSession()
    	def proposalApprovalInstanceNewList = proposalApprovalService.getFullProposalApprovalList(gh.getValue("UserId"))
    	params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [proposalApprovalInstanceList: proposalApprovalInstanceNewList, proposalApprovalInstanceTotal: ProposalApproval.count()]
    
    }

    def create = {
        def proposalApprovalInstance = new ProposalApproval()
        proposalApprovalInstance.properties = params
        return [proposalApprovalInstance: proposalApprovalInstance]
    }

    def save = {
        def proposalApprovalInstance = new ProposalApproval(params)
        if (proposalApprovalInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'proposalApproval.label', default: 'ProposalApproval'), proposalApprovalInstance.id])}"
            redirect(action: "show", id: proposalApprovalInstance.id)
        }
        else {
            render(view: "create", model: [proposalApprovalInstance: proposalApprovalInstance])
        }
    }

    def show = {
        def proposalApprovalInstance = ProposalApproval.get(params.id)
        if (!proposalApprovalInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApproval.label', default: 'ProposalApproval'), params.id])}"
            redirect(action: "list")
        }
        else {
            [proposalApprovalInstance: proposalApprovalInstance]
        }
    }

    def edit = {
        def proposalApprovalInstance = ProposalApproval.get(params.id)
        if (!proposalApprovalInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApproval.label', default: 'ProposalApproval'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [proposalApprovalInstance: proposalApprovalInstance]
        }
    }

    def update = {
        def proposalApprovalInstance = ProposalApproval.get(params.id)
        if (proposalApprovalInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (proposalApprovalInstance.version > version) {
                    
                    proposalApprovalInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'proposalApproval.label', default: 'ProposalApproval')] as Object[], "Another user has updated this ProposalApproval while you were editing")
                    render(view: "edit", model: [proposalApprovalInstance: proposalApprovalInstance])
                    return
                }
            }
            proposalApprovalInstance.properties = params
            if (!proposalApprovalInstance.hasErrors() && proposalApprovalInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'proposalApproval.label', default: 'ProposalApproval'), proposalApprovalInstance.id])}"
                redirect(action: "show", id: proposalApprovalInstance.id)
            }
            else {
                render(view: "edit", model: [proposalApprovalInstance: proposalApprovalInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApproval.label', default: 'ProposalApproval'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def proposalApprovalInstance = ProposalApproval.get(params.id)
        if (proposalApprovalInstance) {
            try {
                proposalApprovalInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'proposalApproval.label', default: 'ProposalApproval'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'proposalApproval.label', default: 'ProposalApproval'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApproval.label', default: 'ProposalApproval'), params.id])}"
            redirect(action: "list")
        }
    }
    def reviewerStatus =
    {     
    	def proposalApprovalInstance = ProposalApproval.get(params.id)
    	GrailsHttpSession gh=getSession()
    	
    	def ProposalApprovalValueInstance=proposalApprovalDetailService.getProposalApprovalDetailsByUserAndProposalType(gh.getValue("UserId"),'PreProposal')
    	
    	 [proposalApprovalInstance:proposalApprovalInstance,ProposalApprovalValueInstance:ProposalApprovalValueInstance]
    	 
    }
    def fullProposalReview = {
    	def proposalApprovalInstance = ProposalApproval.get(params.id)
    	GrailsHttpSession gh=getSession()
    	def ProposalApprovalValueInstance=proposalApprovalDetailService.getProposalApprovalDetailsByUserAndProposalType(gh.getValue("UserId"),'FullProposal')
    	[proposalApprovalInstance:proposalApprovalInstance,ProposalApprovalValueInstance:ProposalApprovalValueInstance]
    	
    }
}
