class RoleController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ roleInstanceList: Role.list( params ) ]
    }

    def show = {
        def roleInstance = Role.get( params.id )

        if(!roleInstance) {
            flash.message = "Role not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ roleInstance : roleInstance ] }
    }

    def delete = {
        def roleInstance = Role.get( params.id )
        if(roleInstance) {
            roleInstance.delete()
            flash.message = "Role ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Role not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def roleInstance = Role.get( params.id )

        if(!roleInstance) {
            flash.message = "Role not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ roleInstance : roleInstance ]
        }
    }

    def update = {
        def roleInstance = Role.get( params.id )
        if(roleInstance) {
            roleInstance.properties = params
            if(!roleInstance.hasErrors() && roleInstance.save()) {
                flash.message = "Role ${params.id} updated"
                redirect(action:show,id:roleInstance.id)
            }
            else {
                render(view:'edit',model:[roleInstance:roleInstance])
            }
        }
        else {
            flash.message = "Role not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def roleInstance = new Role()
        roleInstance.properties = params
        return ['roleInstance':roleInstance]
    }

    def save = {
        def roleInstance = new Role(params)
        if(!roleInstance.hasErrors() && roleInstance.save()) {
            flash.message = "Role ${roleInstance.id} created"
            redirect(action:show,id:roleInstance.id)
        }
        else {
            render(view:'create',model:[roleInstance:roleInstance])
        }
    }
}
