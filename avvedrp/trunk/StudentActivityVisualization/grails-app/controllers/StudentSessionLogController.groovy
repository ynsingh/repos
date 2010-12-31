class StudentSessionLogController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ studentSessionLogInstanceList: StudentSessionLog.list( params ) ]
    }

    def show = {
        def studentSessionLogInstance = StudentSessionLog.get( params.id )

        if(!studentSessionLogInstance) {
            flash.message = "StudentSessionLog not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ studentSessionLogInstance : studentSessionLogInstance ] }
    }

    def delete = {
        def studentSessionLogInstance = StudentSessionLog.get( params.id )
        if(studentSessionLogInstance) {
            studentSessionLogInstance.delete()
            flash.message = "StudentSessionLog ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "StudentSessionLog not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def studentSessionLogInstance = StudentSessionLog.get( params.id )

        if(!studentSessionLogInstance) {
            flash.message = "StudentSessionLog not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ studentSessionLogInstance : studentSessionLogInstance ]
        }
    }

    def update = {
        def studentSessionLogInstance = StudentSessionLog.get( params.id )
        if(studentSessionLogInstance) {
            studentSessionLogInstance.properties = params
            if(!studentSessionLogInstance.hasErrors() && studentSessionLogInstance.save()) {
                flash.message = "StudentSessionLog ${params.id} updated"
                redirect(action:show,id:studentSessionLogInstance.id)
            }
            else {
                render(view:'edit',model:[studentSessionLogInstance:studentSessionLogInstance])
            }
        }
        else {
            flash.message = "StudentSessionLog not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def studentSessionLogInstance = new StudentSessionLog()
        studentSessionLogInstance.properties = params
        return ['studentSessionLogInstance':studentSessionLogInstance]
    }

    def save = {
        def studentSessionLogInstance = new StudentSessionLog(params)
        if(!studentSessionLogInstance.hasErrors() && studentSessionLogInstance.save()) {
            flash.message = "StudentSessionLog ${studentSessionLogInstance.id} created"
            redirect(action:show,id:studentSessionLogInstance.id)
        }
        else {
            render(view:'create',model:[studentSessionLogInstance:studentSessionLogInstance])
        }
    }
}
