import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class PartyDepartmentController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
       
        GrailsHttpSession gh=getSession()
        def dataSecurityService = new DataSecurityService()
        def partyService = new PartyService()
        def partyDepartmentInstanceList = partyService.getPartyDepartment(gh.getValue("PartyID"))
        [ partyDepartmentInstanceList: partyDepartmentInstanceList ]
    }

    def show = {
        def partyDepartmentInstance = PartyDepartment.get( params.id )

        if(!partyDepartmentInstance) {
            flash.message = "Department not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ partyDepartmentInstance : partyDepartmentInstance ] }
    }

    def delete = {
        def partyDepartmentInstance = PartyDepartment.get( params.id )
        if(partyDepartmentInstance) {
            partyDepartmentInstance.delete()
            flash.message = "Department deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Department not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def partyDepartmentInstance = PartyDepartment.get( params.id )
         GrailsHttpSession gh=getSession()
        def dataSecurityService = new DataSecurityService()
        def partyList = dataSecurityService.getPartiesOfLoginUser(gh.getValue("PartyID"));
        if(!partyDepartmentInstance) {
            flash.message = "Department not found with id ${params.id}"
            redirect(action:create)
        }
        else {
            return [ 'partyDepartmentInstance' : partyDepartmentInstance,'partyList':partyList ]
        }
    }

    def update = {
        def partyDepartmentInstance = PartyDepartment.get( params.id )
        if(partyDepartmentInstance) {
            partyDepartmentInstance.properties = params
            if(!partyDepartmentInstance.hasErrors() && partyDepartmentInstance.save()) {
                flash.message = "Department updated Successfully"
                redirect(action:create,id:partyDepartmentInstance.id)
            }
            else {
                render(view:'edit',model:[partyDepartmentInstance:partyDepartmentInstance])
            }
        }
        else {
            flash.message = "Department not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def partyDepartmentInstance = new PartyDepartment()
        partyDepartmentInstance.properties = params
        GrailsHttpSession gh=getSession()
        def dataSecurityService = new DataSecurityService()
       	println "===== PartyID====== " +gh.getValue("PartyID")
        def partyList = dataSecurityService.getPartiesOfLoginUser(gh.getValue("PartyID"));
        
        //getting Department List
        def partyService = new PartyService()
        def partyDepartmentInstanceList = partyService.getPartyDepartment(gh.getValue("PartyID"))

        return ['partyDepartmentInstance':partyDepartmentInstance,
                'partyList':partyList,
                'partyDepartmentInstanceList': partyDepartmentInstanceList ]
    }

    def save = {
        def partyDepartmentInstance = new PartyDepartment(params)
        partyDepartmentInstance.createdBy="admin";
        if(!partyDepartmentInstance.hasErrors() && partyDepartmentInstance.save()) {
            flash.message = "Department created Successfully"
            redirect(action:create,id:partyDepartmentInstance.id)
        }
        else {
            render(view:'create',model:[partyDepartmentInstance:partyDepartmentInstance])
        }
    }
}
