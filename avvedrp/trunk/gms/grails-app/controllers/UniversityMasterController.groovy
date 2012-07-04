import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class UniversityMasterController {
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [universityMasterInstanceList: UniversityMaster.list(params), universityMasterInstanceTotal: UniversityMaster.count()]
    }

    def create = {
    	GrailsHttpSession gh=getSession()
	gh.removeValue("Help")
	gh.putValue("Help","University_Master.htm")//putting help pages in session
	def universityMasterInstance = new UniversityMaster()
	def partyService = new PartyService()
        universityMasterInstance.properties = params
        def universityMasterInstanceList = partyService.getAllUniversityMaster()
        return [universityMasterInstance: universityMasterInstance,universityMasterInstanceList:universityMasterInstanceList]
    }

    def save = {
    	GrailsHttpSession gh=getSession()
    	def partyService = new PartyService()
        def universityMasterInstance = new UniversityMaster(params)
        def chkUniversityInstance = partyService.checkDuplicateUniversity(params)
		if(chkUniversityInstance)
	    {
			flash.message ="${message(code: 'default.AlreadyExists.label')}"
	    	redirect(action: "create", id: universityMasterInstance.id)
	    }
		else
		{
			universityMasterInstance.createdBy="admin"
		    universityMasterInstance.createdDate=new Date()
		    universityMasterInstance.activeYesNo="Y" 
		    if (universityMasterInstance.save(flush: true)) {
	        	flash.message = "${message(code: 'default.created.label')}"
	            redirect(action: "create", id: universityMasterInstance.id)
	        }
	        else {
	            render(view: "create", model: [universityMasterInstance: universityMasterInstance])
	        }
	    }
    }

    def show = {
        def universityMasterInstance = UniversityMaster.get(params.id)
        if (!universityMasterInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'universityMaster.label', default: 'UniversityMaster'), params.id])}"
            redirect(action: "list")
        }
        else {
            [universityMasterInstance: universityMasterInstance]
        }
    }

    def edit = {
        def universityMasterInstance = UniversityMaster.get(params.id)
        if (!universityMasterInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'universityMaster.label', default: 'UniversityMaster'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [universityMasterInstance: universityMasterInstance]
        }
    }

    def update = {
        def universityMasterInstance = UniversityMaster.get(params.id)
        def partyService = new PartyService()
        if (universityMasterInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (universityMasterInstance.version > version) {
                    
                    universityMasterInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'universityMaster.label', default: 'UniversityMaster')] as Object[], "Another user has updated this UniversityMaster while you were editing")
                    render(view: "create", model: [universityMasterInstance: universityMasterInstance])
                    return
                }
            }
            def chkUniversityInstance = partyService.checkDuplicateUniversity(params)
            if(chkUniversityInstance && (chkUniversityInstance.id!= Long.parseLong(params.id)))
            {
            	flash.message ="${message(code: 'default.AlreadyExists.label')}"
            	redirect(action: "edit", id: universityMasterInstance.id)
            }
            else
            {  
	            universityMasterInstance.properties = params
	            if (!universityMasterInstance.hasErrors() && universityMasterInstance.save(flush: true)) {
	            	flash.message = "${message(code: 'default.updated.label')}"
	                redirect(action: "create", id: universityMasterInstance.id)
	            }
	            else {
	                render(view: "create", model: [universityMasterInstance: universityMasterInstance])
	            }
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'universityMaster.label', default: 'UniversityMaster'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
    	def universityMasterInstance = UniversityMaster.get(params.id)
        if (universityMasterInstance) {
            try {
            	universityMasterInstance.activeYesNo="N" 
                universityMasterInstance.save(flush: true)
                flash.message = "${message(code: 'default.deleted.label')}"
                redirect(action: "create")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'universityMaster.label', default: 'UniversityMaster'), params.id])}"
                redirect(action: "create", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'universityMaster.label', default: 'UniversityMaster'), params.id])}"
            redirect(action: "create")
        }
    }
    
    def institutionMap = 
    {
    	GrailsHttpSession gh=getSession()
	gh.removeValue("Help")
	gh.putValue("Help","Univerity_institutionMap.htm")//putting help pages in session
    	def universityInstitutionMapInstance = new UniversityInstitutionMap()
    	def partyService = new PartyService()
    	universityInstitutionMapInstance.properties = params
    	def universityMasterInstanceList = partyService.getAllUniversityMaster()
    	def partyInstanceList = partyService.getAllActiveParties()
    	def universityInstitutionInstanceList = partyService.getAllUniversityInstitutionMap()
    	return [universityInstitutionMapInstance:universityInstitutionMapInstance,universityMasterInstanceList:universityMasterInstanceList,partyInstanceList:partyInstanceList,universityInstitutionInstanceList:universityInstitutionInstanceList]
    }
    
    def saveInstitutionMap = 
    {
    	def partyInstance =Party.get(params.party.id)
    	def partyService = new PartyService()
    	def universityInstance = UniversityMaster.get(params.university.id)
    	def universityInstitutionMapInstance = new UniversityInstitutionMap()
    	def chkUniversityInstitutioMapInstance = partyService.checkDuplicateInstitutioMap(params)
    	if(chkUniversityInstitutioMapInstance)
	    {
			flash.error ="${message(code: 'default.AlreadyExists.label')}"
	    	redirect(action: "institutionMap",  id: universityInstitutionMapInstance.id)
	    }
		else
		{
	    	universityInstitutionMapInstance.universityMaster = universityInstance
	    	universityInstitutionMapInstance.party = partyInstance
	    	universityInstitutionMapInstance.activeYesNo ="Y"
	    	universityInstitutionMapInstance.createdBy ="admin"
	    	universityInstitutionMapInstance.modifiedBy ="admin"
	    	universityInstitutionMapInstance.createdDate =new Date()
	    	universityInstitutionMapInstance.modifiedDate =new Date()
	    	if (universityInstitutionMapInstance.save(flush: true)) 
			{
	        	  flash.message = "${message(code: 'default.created.label')}"
				  redirect(action: "institutionMap", id: universityInstitutionMapInstance.id)
			}
		}
    }
    
    def editInstitutionMap = 
    {
   		def universityInstitutionMapInstance = UniversityInstitutionMap.get(params.id)
    	def partyService = new PartyService()
    	def universityMasterInstanceList = partyService.getAllUniversityMaster()
    	def partyInstanceList = partyService.getAllActiveParties()
    	if (!UniversityInstitutionMap) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'universityMaster.label', default: 'UniversityMaster'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [universityInstitutionMapInstance: universityInstitutionMapInstance,universityMasterInstanceList:universityMasterInstanceList,partyInstanceList:partyInstanceList]
        }
    }
    
    def updateInstitutionMap = 
    {
        def universityInstitutionMapInstance = UniversityInstitutionMap.get(new Integer(params.id))
        def partyInstance =Party.get(params.party.id)
        def universityInstance = UniversityMaster.get(params.university.id)
    	def partyService = new PartyService()
        if (universityInstitutionMapInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (universityInstitutionMapInstance > version) {
                    
                    universityInstitutionMapInstance("version", "default.optimistic.locking.failure", [message(code: 'universityMaster.label', default: 'UniversityMaster')] as Object[], "Another user has updated this UniversityMaster while you were editing")
                    render(view: "institutionMap", model: [universityInstitutionMapInstance: universityInstitutionMapInstance])
                    return
                }
            }
            def chkUniversityInstitutioMapInstance = partyService.checkDuplicateInstitutioMap(params)
            if(chkUniversityInstitutioMapInstance && (chkUniversityInstitutioMapInstance.id!= Long.parseLong(params.id)))
            {
             	flash.error ="${message(code: 'default.AlreadyExists.label')}"
            	redirect(action: "editInstitutionMap", id: universityInstitutionMapInstance.id)
            }
            else
            {  
            	universityInstitutionMapInstance.universityMaster = universityInstance
	    		universityInstitutionMapInstance.party = partyInstance
	    		universityInstitutionMapInstance.activeYesNo ="Y"
	    		universityInstitutionMapInstance.createdBy ="admin"
	    		universityInstitutionMapInstance.modifiedBy ="admin"
	    		universityInstitutionMapInstance.createdDate =new Date()
	    		universityInstitutionMapInstance.modifiedDate =new Date()
	            if (!universityInstitutionMapInstance.hasErrors() && universityInstitutionMapInstance.save(flush: true)) 
	            {
	            	flash.message = "${message(code: 'default.updated.label')}"
	                redirect(action: "institutionMap", id: universityInstitutionMapInstance.id)
	            }
	            else {
	                render(view: "institutionMap", model: [universityInstitutionMapInstance: universityInstitutionMapInstance])
	            }
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'universityMaster.label', default: 'UniversityMaster'), params.id])}"
            redirect(action: "institutionMap")
        }
    }
    
    def deleteInstitutionMap = {
    	def universityInstitutionMapInstance = UniversityInstitutionMap.get(new Integer(params.id))
    	if (universityInstitutionMapInstance) {
            try {
            	universityInstitutionMapInstance.activeYesNo="N" 
                universityInstitutionMapInstance.save(flush: true)
                flash.message = "${message(code: 'default.deleted.label')}"
                redirect(action: "institutionMap")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'universityMaster.label', default: 'UniversityMaster'), params.id])}"
                redirect(action: "institutionMap", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'universityMaster.label', default: 'UniversityMaster'), params.id])}"
            redirect(action: "institutionMap")
        }
    }
}
