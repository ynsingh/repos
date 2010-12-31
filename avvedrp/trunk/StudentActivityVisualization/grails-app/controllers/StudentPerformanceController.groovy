class StudentPerformanceController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ studentPerformanceInstanceList: StudentPerformance.list( params ) ]
    }

    def show = {
        def studentPerformanceInstance = StudentPerformance.get( params.id )

        if(!studentPerformanceInstance) {
            flash.message = "StudentPerformance not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ studentPerformanceInstance : studentPerformanceInstance ] }
    }

    def delete = {
        def studentPerformanceInstance = StudentPerformance.get( params.id )
        if(studentPerformanceInstance) {
            studentPerformanceInstance.delete()
            flash.message = "StudentPerformance ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "StudentPerformance not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def studentPerformanceInstance = StudentPerformance.get( params.id )

        if(!studentPerformanceInstance) {
            flash.message = "StudentPerformance not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ studentPerformanceInstance : studentPerformanceInstance ]
        }
    }

    def update = {
        def studentPerformanceInstance = StudentPerformance.get( params.id )
        if(studentPerformanceInstance) {
            studentPerformanceInstance.properties = params
            if(!studentPerformanceInstance.hasErrors() && studentPerformanceInstance.save()) {
                flash.message = "StudentPerformance ${params.id} updated"
                redirect(action:show,id:studentPerformanceInstance.id)
            }
            else {
                render(view:'edit',model:[studentPerformanceInstance:studentPerformanceInstance])
            }
        }
        else {
            flash.message = "StudentPerformance not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def studentPerformanceInstance = new StudentPerformance()
        studentPerformanceInstance.properties = params
        return ['studentPerformanceInstance':studentPerformanceInstance]
    }

    def save = {
        def studentPerformanceInstance = new StudentPerformance(params)
        if(!studentPerformanceInstance.hasErrors() && studentPerformanceInstance.save()) {
            flash.message = "StudentPerformance ${studentPerformanceInstance.id} created"
            redirect(action:show,id:studentPerformanceInstance.id)
        }
        else {
            render(view:'create',model:[studentPerformanceInstance:studentPerformanceInstance])
        }
    }
}
