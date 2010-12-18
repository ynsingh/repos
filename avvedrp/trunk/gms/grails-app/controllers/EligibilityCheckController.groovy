class EligibilityCheckController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [eligibilityCheckInstanceList: EligibilityCheck.list(params), eligibilityCheckInstanceTotal: EligibilityCheck.count()]
    }

    def create = {
    	def eligibilityStatusInstance = new EligibilityStatus()
        def eligibilityCheckInstance = new EligibilityCheck()
        eligibilityCheckInstance.properties = params
        def proposalInstance = Proposal.get(new Integer(params.id))
        eligibilityCheckInstance.proposal = proposalInstance
        def eligibilityCriteriaInstanceList=EligibilityCriteria.findAll("from EligibilityCriteria EC")
        def eligibilityCheckList = EligibilityCheck.findAll("from EligibilityCheck EG where EG.proposal="+proposalInstance.id)
        
        return [eligibilityCheckInstance: eligibilityCheckInstance,
                eligibilityCriteriaInstanceList:eligibilityCriteriaInstanceList,
                proposalInstance:proposalInstance,eligibilityStatusInstance:eligibilityStatusInstance,
                eligibilityCheckList:eligibilityCheckList,eligibilityStatus:params.eligibilityStatus]
    }

    def save = {
        def saved
        def eligibilityCriteriaInstanceList=EligibilityCriteria.findAll("from EligibilityCriteria EC")
        def eligibilityCheckInstance
        def proposalInstance
        for(int i=0;i<eligibilityCriteriaInstanceList.size();i++)
		 {
        	eligibilityCheckInstance = new EligibilityCheck()
        	
        	def eligibilityCriteriaInstance = EligibilityCriteria.get(new Integer(params."eligibilityCheck${i}.eligibilityCriteria"))
        	proposalInstance = Proposal.get(new Integer(params."eligibilityCheck${i}.proposal"))
        	eligibilityCheckInstance.eligibilityCriteria= eligibilityCriteriaInstance
        	eligibilityCheckInstance.proposal = proposalInstance
        	eligibilityCheckInstance.qualifiedYesNo = params."eligibilityCheck${i}.qualifiedYesNo"
        	eligibilityCheckInstance.description = params."eligibilityCheck${i}.description"
        	eligibilityCheckInstance.save()
        	saved=true
		 
		 }
        
        if (saved) {
        	flash.message ="${message(code: 'default.created.label')}"
        		render(view: "create", model: [eligibilityCheckInstance: eligibilityCheckInstance,
        		                               proposalInstance:proposalInstance,eligibilityCriteriaInstanceList:eligibilityCriteriaInstanceList,id:proposalInstance.id])
        }
        else {
            render(view: "create")
        }
    }

    def show = {
        def eligibilityCheckInstance = EligibilityCheck.get(params.id)
        if (!eligibilityCheckInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'eligibilityCheck.label', default: 'EligibilityCheck'), params.id])}"
            redirect(action: "list")
        }
        else {
            [eligibilityCheckInstance: eligibilityCheckInstance]
        }
    }

    def edit = {
        def eligibilityCheckInstance = EligibilityCheck.get(params.id)
        if (!eligibilityCheckInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'eligibilityCheck.label', default: 'EligibilityCheck'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [eligibilityCheckInstance: eligibilityCheckInstance]
        }
    }

    def update = {
        def eligibilityCheckInstance = EligibilityCheck.get(params.id)
        if (eligibilityCheckInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (eligibilityCheckInstance.version > version) {
                    
                    eligibilityCheckInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'eligibilityCheck.label', default: 'EligibilityCheck')] as Object[], "Another user has updated this EligibilityCheck while you were editing")
                    render(view: "edit", model: [eligibilityCheckInstance: eligibilityCheckInstance])
                    return
                }
            }
            eligibilityCheckInstance.properties = params
            if (!eligibilityCheckInstance.hasErrors() && eligibilityCheckInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'eligibilityCheck.label', default: 'EligibilityCheck'), eligibilityCheckInstance.id])}"
                redirect(action: "show", id: eligibilityCheckInstance.id)
            }
            else {
                render(view: "edit", model: [eligibilityCheckInstance: eligibilityCheckInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'eligibilityCheck.label', default: 'EligibilityCheck'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def eligibilityCheckInstance = EligibilityCheck.get(params.id)
        if (eligibilityCheckInstance) {
            try {
                eligibilityCheckInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'eligibilityCheck.label', default: 'EligibilityCheck'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'eligibilityCheck.label', default: 'EligibilityCheck'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'eligibilityCheck.label', default: 'EligibilityCheck'), params.id])}"
            redirect(action: "list")
        }
    }
    def submit = {
    	def saved
        def eligibilityCriteriaInstanceList=EligibilityCriteria.findAll("from EligibilityCriteria EC")
        def eligibilityCheckInstance
        def proposalInstance
        for(int i=0;i<eligibilityCriteriaInstanceList.size();i++)
	    {
	    	eligibilityCheckInstance = new EligibilityCheck()
	    	def eligibilityCriteriaInstance = EligibilityCriteria.find("from EligibilityCriteria EC where EC.id="+eligibilityCriteriaInstanceList[i].id)
	    	eligibilityCheckInstance.eligibilityCriteria= eligibilityCriteriaInstance
	    	eligibilityCheckInstance.qualifiedYesNo = params."eligibilityCheck${i}.qualifiedYesNo"
	    	eligibilityCheckInstance.description = params."eligibilityCheck${i}.description"
	    	proposalInstance = Proposal.get(new Integer(params.eligibilityCheck.proposal))
	    	eligibilityCheckInstance.proposal = proposalInstance
	    	eligibilityCheckInstance.save()
	    	saved=true
	    }
        if (saved) 
        {
		   	def proposalService = new ProposalService()
		   	def eligibilityStatusInstance = new EligibilityStatus(params)
		   	eligibilityStatusInstance.proposal = proposalInstance
		   	eligibilityStatusInstance.description = params. description
		   	eligibilityStatusInstance.eligibilitysStatus = params.status
		   	def notificationInstance = Notification.get(proposalInstance.notification.id)
			def proposalInstanceList = proposalService.getProposalByNotification(proposalInstance.notification.id)
			if (eligibilityStatusInstance.save(flush: true)) 
			{
		   		flash.message ="${message(code: 'default.submittedsuccessfully.message')}"
		   		redirect(controller:"proposal",action:"proposalList",
		        		params:[notificationId:notificationInstance.id,proposalId:eligibilityStatusInstance.proposal.id])
		   	}
        }
		else {
		           render(view: "create", model: [eligibilityStatusInstance: eligibilityStatusInstance])
		      }
		   	
		    	
    }
}
