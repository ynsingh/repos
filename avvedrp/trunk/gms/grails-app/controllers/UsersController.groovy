class UsersController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ usersInstanceList: Users.list( params ) ]
    }

    def show = {
        def usersInstance = Users.get( params.id )

        if(!usersInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ usersInstance : usersInstance ] }
    }

    def delete = {
        def usersInstance = Users.get( params.id )
        if(usersInstance) {
            usersInstance.delete()
            flash.message = "${message(code: 'default.deleted.label')}"
            redirect(action:list)
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
    }

    def edit = {
        def usersInstance = Users.get( params.id )

        if(!usersInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else {
            return [ usersInstance : usersInstance ]
        }
    }

    def update = {
        def usersInstance = Users.get( params.id )
        if(usersInstance) {
            usersInstance.properties = params
            if(!usersInstance.hasErrors() && usersInstance.save()) {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action:show,id:usersInstance.id)
            }
            else {
                render(view:'edit',model:[usersInstance:usersInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def usersInstance = new Users()
        usersInstance.properties = params
        return ['usersInstance':usersInstance]
    }

    def save = {
        def usersInstance = new Users(params)
        if(!usersInstance.hasErrors() && usersInstance.save()) {
            flash.message = "${message(code: 'default.created.label')}"
            redirect(action:show,id:usersInstance.id)
        }
        else {
            render(view:'create',model:[usersInstance:usersInstance])
        }
    }
}
