class CourseMasterController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ courseMasterInstanceList: CourseMaster.list( params ) ]
    }

    def show = {
        def courseMasterInstance = CourseMaster.get( params.id )

        if(!courseMasterInstance) {
            flash.message = "CourseMaster not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ courseMasterInstance : courseMasterInstance ] }
    }

    def delete = {
        def courseMasterInstance = CourseMaster.get( params.id )
        if(courseMasterInstance) {
            courseMasterInstance.delete()
            flash.message = "CourseMaster ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "CourseMaster not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def courseMasterInstance = CourseMaster.get( params.id )

        if(!courseMasterInstance) {
            flash.message = "CourseMaster not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ courseMasterInstance : courseMasterInstance ]
        }
    }

    def update = {
        def courseMasterInstance = CourseMaster.get( params.id )
        if(courseMasterInstance) {
            courseMasterInstance.properties = params
            if(!courseMasterInstance.hasErrors() && courseMasterInstance.save()) {
                flash.message = "CourseMaster ${params.id} updated"
                redirect(action:show,id:courseMasterInstance.id)
            }
            else {
                render(view:'edit',model:[courseMasterInstance:courseMasterInstance])
            }
        }
        else {
            flash.message = "CourseMaster not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def courseMasterInstance = new CourseMaster()
        courseMasterInstance.properties = params
        return ['courseMasterInstance':courseMasterInstance]
    }

    def save = {
        def courseMasterInstance = new CourseMaster(params)
        if(!courseMasterInstance.hasErrors() && courseMasterInstance.save()) {
            flash.message = "CourseMaster ${courseMasterInstance.id} created"
            redirect(action:show,id:courseMasterInstance.id)
        }
        else {
            render(view:'create',model:[courseMasterInstance:courseMasterInstance])
        }
    }
}
