

class PerStudentSummaryController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ perStudentSummaryInstanceList: PerStudentSummary.list( params ), perStudentSummaryInstanceTotal: PerStudentSummary.count() ]
    }
    
    def visit = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ perStudentSummaryInstanceList: PerStudentSummary.list( params ), perStudentSummaryInstanceTotal: PerStudentSummary.count() ]
    }

    
    def timeSpent = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ perStudentSummaryInstanceList: PerStudentSummary.list( params ), perStudentSummaryInstanceTotal: PerStudentSummary.count() ]
    }

    def show = {
        def perStudentSummaryInstance = PerStudentSummary.get( params.id )

        if(!perStudentSummaryInstance) {
            flash.message = "PerStudentSummary not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ perStudentSummaryInstance : perStudentSummaryInstance ] }
    }

    def delete = {
        def perStudentSummaryInstance = PerStudentSummary.get( params.id )
        if(perStudentSummaryInstance) {
            try {
                perStudentSummaryInstance.delete(flush:true)
                flash.message = "PerStudentSummary ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PerStudentSummary ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PerStudentSummary not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def perStudentSummaryInstance = PerStudentSummary.get( params.id )

        if(!perStudentSummaryInstance) {
            flash.message = "PerStudentSummary not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ perStudentSummaryInstance : perStudentSummaryInstance ]
        }
    }

    def update = {
        def perStudentSummaryInstance = PerStudentSummary.get( params.id )
        if(perStudentSummaryInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(perStudentSummaryInstance.version > version) {
                    
                    perStudentSummaryInstance.errors.rejectValue("version", "perStudentSummary.optimistic.locking.failure", "Another user has updated this PerStudentSummary while you were editing.")
                    render(view:'edit',model:[perStudentSummaryInstance:perStudentSummaryInstance])
                    return
                }
            }
            perStudentSummaryInstance.properties = params
            if(!perStudentSummaryInstance.hasErrors() && perStudentSummaryInstance.save()) {
                flash.message = "PerStudentSummary ${params.id} updated"
                redirect(action:show,id:perStudentSummaryInstance.id)
            }
            else {
                render(view:'edit',model:[perStudentSummaryInstance:perStudentSummaryInstance])
            }
        }
        else {
            flash.message = "PerStudentSummary not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def perStudentSummaryInstance = new PerStudentSummary()
        perStudentSummaryInstance.properties = params
        return ['perStudentSummaryInstance':perStudentSummaryInstance]
    }

    def save = {
        def perStudentSummaryInstance = new PerStudentSummary(params)
        if(!perStudentSummaryInstance.hasErrors() && perStudentSummaryInstance.save()) {
            flash.message = "PerStudentSummary ${perStudentSummaryInstance.id} created"
            redirect(action:show,id:perStudentSummaryInstance.id)
        }
        else {
            render(view:'create',model:[perStudentSummaryInstance:perStudentSummaryInstance])
        }
    }
}
