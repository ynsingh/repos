class FullProposalDetailController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [fullProposalDetailInstanceList: FullProposalDetail.list(params), fullProposalDetailInstanceTotal: FullProposalDetail.count()]
    }

    def create = {
        def fullProposalDetailInstance = new FullProposalDetail()
        fullProposalDetailInstance.properties = params
        return [fullProposalDetailInstance: fullProposalDetailInstance]
    }

    def save = {
        def fullProposalDetailInstance = new FullProposalDetail(params)
        if (fullProposalDetailInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'fullProposalDetail.label', default: 'FullProposalDetail'), fullProposalDetailInstance.id])}"
            redirect(action: "show", id: fullProposalDetailInstance.id)
        }
        else {
            render(view: "create", model: [fullProposalDetailInstance: fullProposalDetailInstance])
        }
    }

    def show = {
        def fullProposalDetailInstance = FullProposalDetail.get(params.id)
        if (!fullProposalDetailInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fullProposalDetail.label', default: 'FullProposalDetail'), params.id])}"
            redirect(action: "list")
        }
        else {
            [fullProposalDetailInstance: fullProposalDetailInstance]
        }
    }

    def edit = {
        def fullProposalDetailInstance = FullProposalDetail.get(params.id)
        if (!fullProposalDetailInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fullProposalDetail.label', default: 'FullProposalDetail'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [fullProposalDetailInstance: fullProposalDetailInstance]
        }
    }

    def update = {
        def fullProposalDetailInstance = FullProposalDetail.get(params.id)
        if (fullProposalDetailInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (fullProposalDetailInstance.version > version) {
                    
                    fullProposalDetailInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'fullProposalDetail.label', default: 'FullProposalDetail')] as Object[], "Another user has updated this FullProposalDetail while you were editing")
                    render(view: "edit", model: [fullProposalDetailInstance: fullProposalDetailInstance])
                    return
                }
            }
            fullProposalDetailInstance.properties = params
            if (!fullProposalDetailInstance.hasErrors() && fullProposalDetailInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'fullProposalDetail.label', default: 'FullProposalDetail'), fullProposalDetailInstance.id])}"
                redirect(action: "show", id: fullProposalDetailInstance.id)
            }
            else {
                render(view: "edit", model: [fullProposalDetailInstance: fullProposalDetailInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fullProposalDetail.label', default: 'FullProposalDetail'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def fullProposalDetailInstance = FullProposalDetail.get(params.id)
        if (fullProposalDetailInstance) {
            try {
                fullProposalDetailInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'fullProposalDetail.label', default: 'FullProposalDetail'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'fullProposalDetail.label', default: 'FullProposalDetail'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fullProposalDetail.label', default: 'FullProposalDetail'), params.id])}"
            redirect(action: "list")
        }
    }
}
