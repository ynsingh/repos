class RequestmapController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ requestmapInstanceList: Requestmap.list( params ) ]
    }

    def show = {
        def requestmapInstance = Requestmap.get( params.id )

        if(!requestmapInstance) {
            flash.message = "Requestmap not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ requestmapInstance : requestmapInstance ] }
    }

    def delete = {
        def requestmapInstance = Requestmap.get( params.id )
        if(requestmapInstance) {
            requestmapInstance.delete()
            flash.message = "Requestmap ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Requestmap not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def requestmapInstance = Requestmap.get( params.id )

        if(!requestmapInstance) {
            flash.message = "Requestmap not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ requestmapInstance : requestmapInstance ]
        }
    }

    def update = {
        def requestmapInstance = Requestmap.get( params.id )
        if(requestmapInstance) {
            requestmapInstance.properties = params
            if(!requestmapInstance.hasErrors() && requestmapInstance.save()) {
                flash.message = "Requestmap ${params.id} updated"
                redirect(action:show,id:requestmapInstance.id)
            }
            else {
                render(view:'edit',model:[requestmapInstance:requestmapInstance])
            }
        }
        else {
            flash.message = "Requestmap not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def requestmapInstance = new Requestmap()
        requestmapInstance.properties = params
        return ['requestmapInstance':requestmapInstance]
    }

    def save = {
        def requestmapInstance = new Requestmap(params)
        if(!requestmapInstance.hasErrors() && requestmapInstance.save()) {
            flash.message = "Requestmap ${requestmapInstance.id} created"
            redirect(action:show,id:requestmapInstance.id)
        }
        else {
            render(view:'create',model:[requestmapInstance:requestmapInstance])
        }
    }
}
