package aell
import grails.converters.JSON;


class BvDictListController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }
    
    def dictSearch={
    	def meaning
    	def dictListInstance
    	def dictImageInstance
    	def dictVoiceInstance
    	def image
    	def voice
    	def dictTypeMasterInstance
    	def array=[]
    	if(params.t){
    		 dictListInstance=BvDictList.findAll("from BvDictList DL where DL.word='"+params.t+"'")
    		if(dictListInstance)
    		{
    			for(i in dictListInstance.bvDictTypeMaster.id){
    		    	dictTypeMasterInstance=BvDictTypeMaster.find("from BvDictTypeMaster where id='"+i+"'")
    		    	array.add(dictTypeMasterInstance.type)
    		    	}
    		meaning=dictListInstance.definition;
    		dictImageInstance=BvDictImage.find("from BvDictImage where bvDictList='"+dictListInstance.id[0]+"'")
        	if(dictImageInstance){
        		image="click to view image";
        		
        		}
        		else
        		{
        			image=""
        		}
    		dictVoiceInstance=BvDictImage.find("from BvDictVoice where bvDictList='"+dictListInstance.id[0]+"'")
        	if(dictVoiceInstance){
        		voice="click to view voice";
        		}
        		else
        		{
        			voice=""
        		}
    		}
    		else
    		{  
    			meaning="No definition found.";
    			image=""
    			voice=""
    			array=""
    					
    		}
    	}  else
    	{
    		meaning="select any text";
    		image=""
    			voice=""
    				array=""
    	}	
    	def result = ["textMeaning" : meaning,"image":image,"voice":voice,"type":array]
    	render result as JSON
    }
    def dictImageSearch={
    	def dictListInstance=BvDictList.find("from BvDictList where word='"+params.t+"' ")
		def dictImageInstance=BvDictImage.find("from BvDictImage where bvDictList='"+dictListInstance.id+"'")
		if(dictImageInstance){
		def result = ["ImageLink" : dictImageInstance.imageLink]
    	render result as JSON
		}
    }
    def dictVoiceSearch={
    	def dictListInstance=BvDictList.find("from BvDictList where word='"+params.t+"' ")
		def dictVoiceInstance=BvDictVoice.find("from BvDictVoice where bvDictList='"+dictListInstance.id+"'")
		if(dictVoiceInstance){
		def result = ["VoiceLink" : dictVoiceInstance.voiceLink]
    	render result as JSON
		}

    }
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [bvDictListInstanceList: BvDictList.list(params), bvDictListInstanceTotal: BvDictList.count()]
    }

    def create = {    	
        def bvDictListInstance = new BvDictList()
        bvDictListInstance.properties = params
        return [bvDictListInstance: bvDictListInstance]
    }

    def save = {
        def bvDictListInstance = new BvDictList(params)
        if (bvDictListInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'bvDictList.label', default: 'BvDictList'), bvDictListInstance.id])}"
            redirect(action: "show", id: bvDictListInstance.id)
        }
        else {
            render(view: "create", model: [bvDictListInstance: bvDictListInstance])
        }
    }

    def show = {
        def bvDictListInstance = BvDictList.get(params.id)
        if (!bvDictListInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'bvDictList.label', default: 'BvDictList'), params.id])}"
            redirect(action: "list")
        }
        else {
            [bvDictListInstance: bvDictListInstance]
        }
    }

    def edit = {
        def bvDictListInstance = BvDictList.get(params.id)
        if (!bvDictListInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'bvDictList.label', default: 'BvDictList'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [bvDictListInstance: bvDictListInstance]
        }
    }

    def update = {
        def bvDictListInstance = BvDictList.get(params.id)
        if (bvDictListInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (bvDictListInstance.version > version) {
                    
                    bvDictListInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'bvDictList.label', default: 'BvDictList')] as Object[], "Another user has updated this BvDictList while you were editing")
                    render(view: "edit", model: [bvDictListInstance: bvDictListInstance])
                    return
                }
            }
            bvDictListInstance.properties = params
            if (!bvDictListInstance.hasErrors() && bvDictListInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'bvDictList.label', default: 'BvDictList'), bvDictListInstance.id])}"
                redirect(action: "show", id: bvDictListInstance.id)
            }
            else {
                render(view: "edit", model: [bvDictListInstance: bvDictListInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'bvDictList.label', default: 'BvDictList'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def bvDictListInstance = BvDictList.get(params.id)
        if (bvDictListInstance) {
            try {
                bvDictListInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'bvDictList.label', default: 'BvDictList'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'bvDictList.label', default: 'BvDictList'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'bvDictList.label', default: 'BvDictList'), params.id])}"
            redirect(action: "list")
        }
    }
}
