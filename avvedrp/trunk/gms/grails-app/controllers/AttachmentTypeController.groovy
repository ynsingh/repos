class AttachmentTypeController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ attachmentTypeInstanceList: AttachmentType.list( params ) ]
    }

    def show = {
        def attachmentTypeInstance = AttachmentType.get( params.id )

        if(!attachmentTypeInstance) {
            flash.message = "${message(code: 'default.AttachmentTypenotfound.label')}"
            redirect(action:create)
        }
        else { return [ attachmentTypeInstance : attachmentTypeInstance ] }
    }

    def delete = {
        def attachmentTypeInstance = AttachmentType.get( params.id )
        if(attachmentTypeInstance) {
            attachmentTypeInstance.delete()
            flash.message = "${message(code: 'default.deleted.label')}"
            redirect(action:create)
        }
        else {
            flash.message = "${message(code: 'default.AttachmentTypenotfound.label')}"
            redirect(action:create)
        }
    }

    def edit = {
        def attachmentTypeInstance = AttachmentType.get( params.id )

        if(!attachmentTypeInstance) {
            flash.message = "${message(code: 'default.AttachmentTypenotfound.label')}"
            redirect(action:create)
        }
        else {
            return [ attachmentTypeInstance : attachmentTypeInstance ]
        }
    }

    def update = {
        def attachmentTypeInstance = AttachmentType.get( params.id )
        if(attachmentTypeInstance) {
            attachmentTypeInstance.properties = params
            if(!attachmentTypeInstance.hasErrors() && attachmentTypeInstance.save()) {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action:create,id:attachmentTypeInstance.id)
            }
            else {
                render(view:'edit',model:[attachmentTypeInstance:attachmentTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.AttachmentTypenotfound.label')}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def attachmentTypeInstance = new AttachmentType()
        attachmentTypeInstance.properties = params
        def attachmentTypeInstanceList= AttachmentType.list( params )
        return ['attachmentTypeInstance':attachmentTypeInstance,
                'attachmentTypeInstanceList': attachmentTypeInstanceList]
    }

    def save = {
        def attachmentTypeInstance = new AttachmentType(params)
        if(!attachmentTypeInstance.hasErrors() && attachmentTypeInstance.save()) {
            flash.message = "${message(code: 'default.created.label')}"
            redirect(action:create,id:attachmentTypeInstance.id)
        }
        else {
            render(view:'create',model:[attachmentTypeInstance:attachmentTypeInstance])
        }
    }
}
