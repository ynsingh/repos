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
            flash.message = "AttachmentType not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ attachmentTypeInstance : attachmentTypeInstance ] }
    }

    def delete = {
        def attachmentTypeInstance = AttachmentType.get( params.id )
        if(attachmentTypeInstance) {
            attachmentTypeInstance.delete()
            flash.message = "AttachmentType ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "AttachmentType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def attachmentTypeInstance = AttachmentType.get( params.id )

        if(!attachmentTypeInstance) {
            flash.message = "AttachmentType not found with id ${params.id}"
            redirect(action:list)
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
                flash.message = "AttachmentType ${params.id} updated"
                redirect(action:list,id:attachmentTypeInstance.id)
            }
            else {
                render(view:'edit',model:[attachmentTypeInstance:attachmentTypeInstance])
            }
        }
        else {
            flash.message = "AttachmentType not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def attachmentTypeInstance = new AttachmentType()
        attachmentTypeInstance.properties = params
        return ['attachmentTypeInstance':attachmentTypeInstance]
    }

    def save = {
        def attachmentTypeInstance = new AttachmentType(params)
        if(!attachmentTypeInstance.hasErrors() && attachmentTypeInstance.save()) {
            flash.message = "AttachmentType ${attachmentTypeInstance.id} created"
            redirect(action:list,id:attachmentTypeInstance.id)
        }
        else {
            render(view:'create',model:[attachmentTypeInstance:attachmentTypeInstance])
        }
    }
}
