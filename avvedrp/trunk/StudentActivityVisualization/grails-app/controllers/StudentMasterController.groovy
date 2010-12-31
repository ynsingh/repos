class StudentMasterController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ studentMasterInstanceList: StudentMaster.list( params ) ]
    }

    def show = {
        def studentMasterInstance = StudentMaster.get( params.id )

        if(!studentMasterInstance) {
            flash.message = "StudentMaster not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ studentMasterInstance : studentMasterInstance ] }
    }

    def delete = {
        def studentMasterInstance = StudentMaster.get( params.id )
        if(studentMasterInstance) {
            studentMasterInstance.delete()
            flash.message = "StudentMaster ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "StudentMaster not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def studentMasterInstance = StudentMaster.get( params.id )

        if(!studentMasterInstance) {
            flash.message = "StudentMaster not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ studentMasterInstance : studentMasterInstance ]
        }
    }

    def update = {
        def studentMasterInstance = StudentMaster.get( params.id )
        if(studentMasterInstance) {
            studentMasterInstance.properties = params
            if(!studentMasterInstance.hasErrors() && studentMasterInstance.save()) {
                flash.message = "StudentMaster ${params.id} updated"
                redirect(action:show,id:studentMasterInstance.id)
            }
            else {
                render(view:'edit',model:[studentMasterInstance:studentMasterInstance])
            }
        }
        else {
            flash.message = "StudentMaster not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def studentMasterInstance = new StudentMaster()
        studentMasterInstance.properties = params
        return ['studentMasterInstance':studentMasterInstance]
    }

    def save = {
        def studentMasterInstance = new StudentMaster(params)
        if(!studentMasterInstance.hasErrors() && studentMasterInstance.save()) {
            flash.message = "StudentMaster ${studentMasterInstance.id} created"
            redirect(action:show,id:studentMasterInstance.id)
        }
        else {
            render(view:'create',model:[studentMasterInstance:studentMasterInstance])
        }
    }
}
