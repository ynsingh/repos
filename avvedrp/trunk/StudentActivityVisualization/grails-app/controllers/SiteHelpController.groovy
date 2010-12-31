

class SiteHelpController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ siteHelpInstanceList: SiteHelp.list( params ), siteHelpInstanceTotal: SiteHelp.count() ]
    }

    def show = {
        def siteHelpInstance = SiteHelp.get( params.id )

        if(!siteHelpInstance) {
            flash.message = "SiteHelp not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ siteHelpInstance : siteHelpInstance ] }
    }

    def delete = {
        def siteHelpInstance = SiteHelp.get( params.id )
        if(siteHelpInstance) {
            try {
                siteHelpInstance.delete(flush:true)
                flash.message = "SiteHelp ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "SiteHelp ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "SiteHelp not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def siteHelpInstance = SiteHelp.get( params.id )

        if(!siteHelpInstance) {
            flash.message = "SiteHelp not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ siteHelpInstance : siteHelpInstance ]
        }
    }

    def update = {
        def siteHelpInstance = SiteHelp.get( params.id )
        if(siteHelpInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(siteHelpInstance.version > version) {
                    
                    siteHelpInstance.errors.rejectValue("version", "siteHelp.optimistic.locking.failure", "Another user has updated this SiteHelp while you were editing.")
                    render(view:'edit',model:[siteHelpInstance:siteHelpInstance])
                    return
                }
            }
            siteHelpInstance.properties = params
            if(!siteHelpInstance.hasErrors() && siteHelpInstance.save()) {
                flash.message = "SiteHelp ${params.id} updated"
                redirect(action:show,id:siteHelpInstance.id)
            }
            else {
                render(view:'edit',model:[siteHelpInstance:siteHelpInstance])
            }
        }
        else {
            flash.message = "SiteHelp not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def siteHelpInstance = new SiteHelp()
        siteHelpInstance.properties = params
        return ['siteHelpInstance':siteHelpInstance]
    }

    def save = {
        def siteHelpInstance = new SiteHelp(params)
        if(!siteHelpInstance.hasErrors() && siteHelpInstance.save()) {
            flash.message = "SiteHelp ${siteHelpInstance.id} created"
            redirect(action:show,id:siteHelpInstance.id)
        }
        else {
            render(view:'create',model:[siteHelpInstance:siteHelpInstance])
        }
    }


    def help={
        render view: 'help'
    }
   
}
