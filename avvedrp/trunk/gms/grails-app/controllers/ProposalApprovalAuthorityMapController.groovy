import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class ProposalApprovalAuthorityMapController {
	def proposalService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "create", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [proposalApprovalAuthorityMapInstanceList: ProposalApprovalAuthorityMap.list(params), proposalApprovalAuthorityMapInstanceTotal: ProposalApprovalAuthorityMap.count()]
    }

    def create = {
		GrailsHttpSession gh=getSession()
        def proposalApprovalAuthorityMapInstance = new ProposalApprovalAuthorityMap()
        def proposalService = new ProposalService()
		def proposalApplicationList = []
		def proposalApplicationInstanceDataList = []
		def approvalAuthorityService = new ApprovalAuthorityService()
		def proposalApprovalAuthorityMapInstanceList = []
        proposalApprovalAuthorityMapInstance.properties = params
    	def preProposalList = proposalService.getSubmittedPreProposal(gh.getValue("Party"))
         for(int i=0;i<preProposalList.size();i++)
         {
        	 def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(preProposalList[i].id)
     		proposalApplicationList.add(proposalApplicationInstance)
          	  
         }
        def approvalAuthorityInstance = approvalAuthorityService.getActiveApprovalAuthority(gh.getValue("PartyID"))
        def proposalApprovalAuthorityMapList = []
		
	
		
        for(int i=0;i<approvalAuthorityInstance.size();i++)
        {
        	proposalApprovalAuthorityMapInstanceList = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAAM WHERE PAAM.approvalAuthority IN ("+approvalAuthorityInstance[i].id+") and PAAM.proposalType NOT IN('ExpenseRequest','Proposal','Notification')")
        	for(int j=0;j<proposalApprovalAuthorityMapInstanceList.size();j++)
        		proposalApprovalAuthorityMapList.add(proposalApprovalAuthorityMapInstanceList[j])
        	
        }
        for(int k=0;k<proposalApprovalAuthorityMapList.size();k++)
        {
       	 def proposalApplicationInstanceData = proposalService.getProposalApplicationByProposal(proposalApprovalAuthorityMapList[k].proposalId)
  		    proposalApplicationInstanceDataList.add(proposalApplicationInstanceData)
        	
        }
        
        return ['proposalApprovalAuthorityMapInstance': proposalApprovalAuthorityMapInstance,'preProposalList':preProposalList,'proposalApprovalAuthorityMapInstanceList': proposalApprovalAuthorityMapList,'approvalAuthorityInstance':approvalAuthorityInstance,'proposalApplicationList':proposalApplicationList,'proposalApplicationInstanceDataList':proposalApplicationInstanceDataList]
    }

    def save = 
    {
		def proposalApprovalAuthorityMapService = new ProposalApprovalAuthorityMapService()
        def proposalApprovalAuthorityMapInstance = new ProposalApprovalAuthorityMap(params)
		def proposalApplicationInstance = ProposalApplication.get(params.proposalId)
        
		def proposalApprovalAuthorityMapData = proposalApprovalAuthorityMapService.getDuplicateProposalAuthorityMapDetails(proposalApplicationInstance,params)
			//ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.proposalId="+proposalApplicationInstance.proposal.id+" and PAM.proposalType='"+params.proposalType+"' and PAM.approvalAuthority="+params.approvalAuthority.id)
        if(proposalApprovalAuthorityMapData)
        {
        	flash.error ="${message(code: 'default.AlreadyExistsAuthorityApproveOrder.label')}"
        	redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
        }
        else 
        {
         	def proposalApprovalAuthorityMapList = proposalApprovalAuthorityMapService.getProposalAuthorityMapDetails(proposalApplicationInstance,params)
         		//ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PM where PM.activeYesNo='Y' and PM.proposalId="+proposalApplicationInstance.proposal.id+" and PM.proposalType='"+params.proposalType+"' and PM.approveOrder= "+params.approveOrder)
        	if(proposalApprovalAuthorityMapList.size())
        	{
        		flash.error ="${message(code: 'default.AlreadyExistsApproveOrder.label')}"
        		redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
        	}
        	else
        	{
        		proposalApprovalAuthorityMapInstance.activeYesNo = 'Y'
        		//def proposalIdInstance = ProposalApplication.get(proposalApprovalAuthorityMapInstance.proposalId)
        		//println"----------------proposalApplicationInstance--------------"+proposalApplicationInstance  
         		proposalApprovalAuthorityMapInstance.proposalId = proposalApplicationInstance.proposal.id
           		if (proposalApprovalAuthorityMapInstance.save(flush: true))
	        	{
	        		
	        		flash.message = "${message(code: 'default.ProposalApprovalAuthorityMapCreated.label')}"
	        		redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
	        	}
	        	else
	        	{
	        		render(view: "create", model: [proposalApprovalAuthorityMapInstance: proposalApprovalAuthorityMapInstance])
	        	}
        	}
        }
	}

    def show = {
        def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.get(params.id)
        if (!proposalApprovalAuthorityMapInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap'), params.id])}"
            redirect(action: "list")
        }
        else {
            [proposalApprovalAuthorityMapInstance: proposalApprovalAuthorityMapInstance]
        }
    }

    def edit = {
		GrailsHttpSession gh=getSession()
		def approvalAuthorityService = new ApprovalAuthorityService()
		def proposalApplicationList = []
        def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.get(params.id)
        def projectTitleInstance = proposalService.getProposalApplicationByProposal(proposalApprovalAuthorityMapInstance.proposalId)
        def approvalAuthorityInstance = approvalAuthorityService.getActiveApprovalAuthority(gh.getValue("PartyID"))
        def preProposalList = proposalService.getSubmittedPreProposal(gh.getValue("Party"))
         for(int i=0;i<preProposalList.size();i++)
         {
        	 def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(preProposalList[i].id)
      		  proposalApplicationList.add(proposalApplicationInstance)
          	  
         }
        def proposalInstance = proposalService.getProposalApprovalAuthorityByProposalId(proposalApprovalAuthorityMapInstance.proposalId)
        if (!proposalApprovalAuthorityMapInstance) {
        	
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap'), params.id])}"
            redirect(action: "edit")
        }
        else {
            return ['proposalApprovalAuthorityMapInstance': proposalApprovalAuthorityMapInstance,'approvalAuthorityInstance':approvalAuthorityInstance,'proposalInstance':proposalInstance,'preProposalList':preProposalList,'proposalApplicationList':proposalApplicationList,'projectTitleInstance':projectTitleInstance]
        }
    }

    def update = {
	     def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.get(params.id)
         if (proposalApprovalAuthorityMapInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (proposalApprovalAuthorityMapInstance.version > version) {
                    
                    proposalApprovalAuthorityMapInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap')] as Object[], "Another user has updated this ProposalApprovalAuthorityMap while you were editing")
                    render(view: "edit", model: [proposalApprovalAuthorityMapInstance: proposalApprovalAuthorityMapInstance])
                    return
                }
            }
            
            def proposalApprovalAuthorityMapService = new ProposalApprovalAuthorityMapService()
            def proposalApprovalDetailService = new ProposalApprovalDetailService()
            def proposalApplicationInstance = proposalService.getProposalApplicationByProposalId(proposalApprovalAuthorityMapInstance.proposalId)
  		    def proposalApprovalAuthorityMapDetail = proposalApprovalAuthorityMapService.checkDuplicateProposalApprovalAuthorityMap(proposalApplicationInstance,params)
            	def proposalList = proposalApprovalDetailService.proposalApprovalDetailByProposalApprovalAuthorityMap(proposalApprovalAuthorityMapInstance) 
            	if(proposalList)
            	{
            		flash.message ="${message(code: 'default.ReviewStarts.message')}"
            		redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
            	}
            	else  if((proposalApprovalAuthorityMapDetail) && (proposalApprovalAuthorityMapDetail[0].id != Long.parseLong(params.id)))
              {
            	flash.message ="${message(code: 'default.AlreadyExists.label')}"
    	    	redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
              }
           
            else 
             {
            	
            	def proposalApprovalAuthorityMapList = proposalApprovalAuthorityMapService.getProposalAuthorityMapDetails(proposalApplicationInstance,params)            	
            	
	            	if(proposalApprovalAuthorityMapList.size())
	            	{
	            		
	            		if(new Integer(params.id)==proposalApprovalAuthorityMapList[0].id)
	            		{
	            			proposalApprovalAuthorityMapInstance.properties = params
		                		if (!proposalApprovalAuthorityMapInstance.hasErrors() && proposalApprovalAuthorityMapInstance.save(flush: true)) {
			    	           			flash.message = "${message(code: 'default.ProposalApprovalAuthorityMapUpdated.label')}"
			    		                redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
		    		            	}
		    		            else {
		    		                	render(view: "edit", model: [proposalApprovalAuthorityMapInstance: proposalApprovalAuthorityMapInstance])
		    		                }
	            		}
	            		else
	            		{
		            		flash.error ="${message(code: 'default.AlreadyExistsApproveOrder.label')}"
		            		redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
		            	}
	            	}
	            	else
	            	{
		            	proposalApprovalAuthorityMapInstance.properties = params
	            		if (!proposalApprovalAuthorityMapInstance.hasErrors() && proposalApprovalAuthorityMapInstance.save(flush: true)) {
		           			flash.message = "${message(code: 'default.ProposalApprovalAuthorityMapUpdated.label')}"
			                redirect(action: "create", id: proposalApprovalAuthorityMapInstance.id)
			            	}
			            else {
			                render(view: "edit", model: [proposalApprovalAuthorityMapInstance: proposalApprovalAuthorityMapInstance])
			            }
	            	}
            	
            }
        
        }
     else {
	        flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap'), params.id])}"
	        redirect(action: "list")
    }
    }

    def delete = {
        def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.get(params.id)
        if (proposalApprovalAuthorityMapInstance) {
            try {
                proposalApprovalAuthorityMapInstance.delete(flush: true)
                flash.message = "${message(code: 'default.ProposalApprovalAuthorityMapDeleted.label')}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap'), params.id])}"
            redirect(action: "list")
        }
    }
    def getProposals =
    {
    	def preProposalList 
    	def proposalApplicationList=[]
    	GrailsHttpSession gh=getSession()
    	if(params.proposal=="PreProposal")
    	{
    		/*Method to get submitted PreProposal by type*/
    		preProposalList = proposalService.getSubmittedPreProposalByType(gh.getValue("Party"))
    		  for(int i=0;i<preProposalList.size();i++)
    	         {	
    			     /*method to get proposalApplication Instance by proposalId*/
    	    	     def proposalApplicationInstance = proposalService.getProposalApplicationByProposal(preProposalList[i].id)
    	    		 proposalApplicationList.add(proposalApplicationInstance)
    	        	
    	         }
    		render (template:"preProposalSelect", model : ['proposalApplicationList' : proposalApplicationList])
    	}
    	else
    	{
    		preProposalList = proposalService.getSubmittedFullProposalByType(gh.getValue("Party"))
    		for(int i=0;i<preProposalList.size();i++)
    		{
    			def proposalTitleInstance = proposalService.getProposalApplicationByProposal(preProposalList[i].id)
    			//def proposalTitleInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal= "+preProposalList[i].id)
                         proposalApplicationList.add(proposalTitleInstance)
    		}
    		render (template:"proposalSelect", model : ['proposalApplicationList' : proposalApplicationList])
    	}
    	//render (template:"proposalSelect", model : ['preProposalList' : preProposalList])
    }
}
