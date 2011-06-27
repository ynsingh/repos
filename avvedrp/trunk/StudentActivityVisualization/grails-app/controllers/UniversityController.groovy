class UniversityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [universityInstanceList: University.list(params), universityInstanceTotal: University.count()]
    }

    def create = {
        def universityInstance = new University()
        universityInstance.properties = params
        return [universityInstance: universityInstance]
    }

    def save = {
        def universityInstance = new University(params)
        if (universityInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'university.label', default: 'University'), universityInstance.id])}"
            redirect(action: "show", id: universityInstance.id)
        }
        else {
            render(view: "create", model: [universityInstance: universityInstance])
        }
    }

    def show = {
        def universityInstance = University.get(params.id)
        if (!universityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'university.label', default: 'University'), params.id])}"
            redirect(action: "list")
        }
        else {
            [universityInstance: universityInstance]
        }
    }

    def edit = {
        def universityInstance = University.get(params.id)
        if (!universityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'university.label', default: 'University'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [universityInstance: universityInstance]
        }
    }

    def update = {
        def universityInstance = University.get(params.id)
        if (universityInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (universityInstance.version > version) {
                    
                    universityInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'university.label', default: 'University')] as Object[], "Another user has updated this University while you were editing")
                    render(view: "edit", model: [universityInstance: universityInstance])
                    return
                }
            }
            universityInstance.properties = params
            if (!universityInstance.hasErrors() && universityInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'university.label', default: 'University'), universityInstance.id])}"
                redirect(action: "show", id: universityInstance.id)
            }
            else {
                render(view: "edit", model: [universityInstance: universityInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'university.label', default: 'University'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def universityInstance = University.get(params.id)
        if (universityInstance) {
            try {
                universityInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'university.label', default: 'University'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'university.label', default: 'University'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'university.label', default: 'University'), params.id])}"
            redirect(action: "list")
        }
    }
}
