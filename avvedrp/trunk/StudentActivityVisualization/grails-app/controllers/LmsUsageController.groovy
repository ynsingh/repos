

class LmsUsageController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ lmsUsageInstanceList: LmsUsage.list( params ), lmsUsageInstanceTotal: LmsUsage.count() ]
    }

    def show = {
        def lmsUsageInstance = LmsUsage.get( params.id )

        if(!lmsUsageInstance) {
            flash.message = "LmsUsage not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ lmsUsageInstance : lmsUsageInstance ] }
    }

    def delete = {
        def lmsUsageInstance = LmsUsage.get( params.id )
        if(lmsUsageInstance) {
            try {
                lmsUsageInstance.delete(flush:true)
                flash.message = "LmsUsage ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "LmsUsage ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "LmsUsage not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def lmsUsageInstance = LmsUsage.get( params.id )

        if(!lmsUsageInstance) {
            flash.message = "LmsUsage not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ lmsUsageInstance : lmsUsageInstance ]
        }
    }

    def update = {
        def lmsUsageInstance = LmsUsage.get( params.id )
        if(lmsUsageInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(lmsUsageInstance.version > version) {
                    
                    lmsUsageInstance.errors.rejectValue("version", "lmsUsage.optimistic.locking.failure", "Another user has updated this LmsUsage while you were editing.")
                    render(view:'edit',model:[lmsUsageInstance:lmsUsageInstance])
                    return
                }
            }
            lmsUsageInstance.properties = params
            if(!lmsUsageInstance.hasErrors() && lmsUsageInstance.save()) {
                flash.message = "LmsUsage ${params.id} updated"
                redirect(action:show,id:lmsUsageInstance.id)
            }
            else {
                render(view:'edit',model:[lmsUsageInstance:lmsUsageInstance])
            }
        }
        else {
            flash.message = "LmsUsage not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def lmsUsageInstance = new LmsUsage()
        lmsUsageInstance.properties = params
        return ['lmsUsageInstance':lmsUsageInstance]
    }

    def save = {
        def lmsUsageInstance = new LmsUsage(params)
        if(!lmsUsageInstance.hasErrors() && lmsUsageInstance.save()) {
            flash.message = "LmsUsage ${lmsUsageInstance.id} created"
            redirect(action:show,id:lmsUsageInstance.id)
        }
        else {
            render(view:'create',model:[lmsUsageInstance:lmsUsageInstance])
        }
    }
}
