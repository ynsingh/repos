class AuthorityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [authorityInstanceList: Authority.list(params), authorityInstanceTotal: Authority.count()]
    }

    def create = {
        def authorityInstance = new Authority()
        authorityInstance.properties = params
        def authorityInstanceList=Authority.findAll("from Authority AT where AT.activeYesNo='Y'")
        return [authorityInstance: authorityInstance,
                authorityInstanceList: authorityInstanceList, 
                authorityInstanceTotal: Authority.count()]
    }

    def save = {
        def authorityInstance = new Authority(params)
        if (authorityInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.label')}"
            redirect(action: "create", id: authorityInstance.id)
        }
        else {
            render(view: "create", model: [authorityInstance: authorityInstance])
        }
    }

    def show = {
        def authorityInstance = Authority.get(params.id)
        if (!authorityInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
        else {
            [authorityInstance: authorityInstance]
        }
    }

    def edit = {
        def authorityInstance = Authority.get(params.id)
        if (!authorityInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
        else {
            return [authorityInstance: authorityInstance]
        }
    }

    def update = {
        def authorityInstance = Authority.get(params.id)
        if (authorityInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (authorityInstance.version > version) {
                    
                    authorityInstance.errors.rejectValue("version", "default.optimistic.locking.failure", 
                    		[message(code: 'authority.label', default: 'Authority')] as Object[], 
                    		"Another user has updated this Authority while you were editing")
                    render(view: "edit", model: [authorityInstance: authorityInstance])
                    return
                }
            }
            authorityInstance.properties = params
            if (!authorityInstance.hasErrors() && authorityInstance.save(flush: true)) {
            	 flash.message = "${message(code: 'default.updated.label')}"
                redirect(action: "create", id: authorityInstance.id)
            }
            else {
                render(view: "edit", model: [authorityInstance: authorityInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create")
        }
    }

    def delete = {
        def authorityInstance = Authority.get(params.id)
        if (authorityInstance) {
            try {
                authorityInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.label')}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.inuse.label')}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
    }
}
