class StudentLearningController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ studentLearningInstanceList: StudentLearning.list( params ) ]
    }

    def show = {
        def studentLearningInstance = StudentLearning.get( params.id )

        if(!studentLearningInstance) {
            flash.message = "StudentLearning not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ studentLearningInstance : studentLearningInstance ] }
    }

    def delete = {
        def studentLearningInstance = StudentLearning.get( params.id )
        if(studentLearningInstance) {
            studentLearningInstance.delete()
            flash.message = "StudentLearning ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "StudentLearning not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def studentLearningInstance = StudentLearning.get( params.id )

        if(!studentLearningInstance) {
            flash.message = "StudentLearning not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ studentLearningInstance : studentLearningInstance ]
        }
    }

    def update = {
        def studentLearningInstance = StudentLearning.get( params.id )
        if(studentLearningInstance) {
            studentLearningInstance.properties = params
            if(!studentLearningInstance.hasErrors() && studentLearningInstance.save()) {
                flash.message = "StudentLearning ${params.id} updated"
                redirect(action:show,id:studentLearningInstance.id)
            }
            else {
                render(view:'edit',model:[studentLearningInstance:studentLearningInstance])
            }
        }
        else {
            flash.message = "StudentLearning not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def studentLearningInstance = new StudentLearning()
        studentLearningInstance.properties = params
        return ['studentLearningInstance':studentLearningInstance]
    }

    def save = {
        def studentLearningInstance = new StudentLearning(params)
        if(!studentLearningInstance.hasErrors() && studentLearningInstance.save()) {
            flash.message = "StudentLearning ${studentLearningInstance.id} created"
            redirect(action:show,id:studentLearningInstance.id)
        }
        else {
            render(view:'create',model:[studentLearningInstance:studentLearningInstance])
        }
    }
}
