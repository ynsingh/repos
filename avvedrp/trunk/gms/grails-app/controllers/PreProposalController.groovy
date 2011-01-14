import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.converters.JSON
import grails.util.GrailsUtil
class PreProposalController {
	def gmsSettingsService
	def preProposalDetailService
	def preProposalService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "create", params: params)
    }

    def list = {
        
        
	    	def preProposalInstance = PreProposal.get(params.id)
    	
    	GrailsHttpSession gh=getSession()
    	def preProposalInstanceList = PreProposal.findAll("from PreProposal P where P.person.id="+gh.getValue("UserId"))
       // params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [preProposalInstanceList: preProposalInstanceList]
    }

    def create = {
		def preProposalInstance = new PreProposal()
		//def proposalApplicationPath = proposalSettingsService.getProposalSettingsValue("ProposalApplicationPath")
    	//def proposalApplicationForm = proposalSettingsService.getProposalSettingsValue("ProposalForm")
        def proposalApplicationForm = gmsSettingsService.getGmsSettingsValue("ProposalForm")
        def proposalApplicationPath = gmsSettingsService.getGmsSettingsValue("ProposalApplicationPath")
    	preProposalInstance.properties = params
            	
		 def webRootDir
     		if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
     		{
     			webRootDir = servletContext.getRealPath("/")+"WEB-INF/grails-app/views/preProposal/"
     		}
        	if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
        	{
        		webRootDir = "grails-app/views/preProposal/"
        	}
        	def srcFile = new File(proposalApplicationPath+proposalApplicationForm)
        	def targetFile = new File(webRootDir+proposalApplicationForm)
        	try
        	{
        		org.apache.commons.io.FileUtils.copyFile(srcFile, targetFile)
        	}
        	catch(Exception e)
        	{
        		
        	}
        return [preProposalInstance: preProposalInstance,proposalApplicationForm:proposalApplicationForm]
    }

    def save = {
    	def preProposalService = new PreProposalService()
        def preProposalInstance = new PreProposal(params)
    	GrailsHttpSession gh=getSession()
    	 preProposalInstance  = preProposalService.savePreProposal(params,gh.getValue("UserId"),gh.getValue("Party"))
        if (preProposalInstance) {
        	
        	gh.putValue("PreProposal",preProposalInstance.id)
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'preProposal.label', default: 'PreProposal'), preProposalInstance.id])}"
            redirect(action: "preProposalApplication", id: preProposalInstance.id)
        }
        else {
            render(view: "create", model: [preProposalInstance: preProposalInstance])
        }
    }
	
	def preProposalApplication =
	{
		GrailsHttpSession gh=getSession()
		gh.putValue("PreProposal",params.id)
		def preProposalInstance=preProposalService.getPrePropsalById(params.id)
		[proposalApplicationForm:preProposalInstance.preProposalForm]
	}
	def saveForm = 
	{
		GrailsHttpSession gh=getSession()
		def proposalDetailsSaveStatus=preProposalService.saveformDetails(params,gh.getValue("PreProposal"))
		redirect(action: "preProposalSubmit",id:gh.getValue("PreProposal"))
	}
	def preProposalSubmit = 
	{
		
	}
	def submitProposal =
	{
		GrailsHttpSession gh=getSession()
		def preProposalInstance=preProposalService.getPrePropsalById(gh.getValue("PreProposal"))
		preProposalInstance.preProposalStatus="Submitted"
		def preProposalInstanceSaved=preProposalService.savePreProposalInstance(preProposalInstance)
		if(preProposalInstanceSaved)
		{
			redirect(action: "list")
		}
		else
		{
			redirect(action: "list")
		}
	}

    def show = {
        def preProposalInstance = PreProposal.get(params.id)
        if (!preProposalInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposal.label', default: 'PreProposal'), params.id])}"
            redirect(action: "list")
        }
        else {
            [preProposalInstance: preProposalInstance]
        }
    }

    def edit = {
    	def preProposalService = new PreProposalService()
    	def preProposalInstance = preProposalService.getPreProposalById(new Integer( params.id ))
      GrailsHttpSession gh=getSession()
      gh.putValue("ProposalId",preProposalInstance.id);
        if (!preProposalInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposal.label', default: 'PreProposal'), params.id])}"
            redirect(action: "list")
        }
        else {
        	if(preProposalInstance.preProposalStatus=="Saved" || preProposalInstance.preProposalStatus=="NeedMoreInfo" )
        	{
        		return [preProposalInstance: preProposalInstance]
        	}
        	else
        	{
        		render(view: "submittedPreProposal", model: [preProposalInstance: preProposalInstance,proposalApplicationForm:preProposalInstance.preProposalForm])
        	}
          // render(view: "edit", model: ['preProposalInstance': preProposalInstance,'proposalApplicationForm':preProposalInstance.preProposalForm])
        }
    }

    def update = {
    	def preProposalService = new PreProposalService()
    	def preProposalInstance = preProposalService.getPreProposalById(new Integer( params.id ))
      
        if (preProposalInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (preProposalInstance.version > version) {
                    
                    preProposalInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'preProposal.label', default: 'PreProposal')] as Object[], "Another user has updated this PreProposal while you were editing")
                    render(view: "edit", model: [preProposalInstance: preProposalInstance])
                    return
                }
            }
            preProposalInstance = preProposalService.updatePreProposal(params,preProposalInstance)
            if( preProposalInstance.saveMode.equals( "Updated")) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'preProposal.label', default: 'PreProposal'), preProposalInstance.id])}"
                redirect(action: "preProposalApplication", id: preProposalInstance.id)
            }
            else {
                render(view: "edit", model: [preProposalInstance: preProposalInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposal.label', default: 'PreProposal'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
    	def preProposalService = new PreProposalService()
    	def preProposalInstance = preProposalService.getPreProposalById(new Integer( params.id ))
      
        if (preProposalInstance) {
            try {
                preProposalInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'preProposal.label', default: 'PreProposal'), params.id])}"
                redirect(action: "create")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'preProposal.label', default: 'PreProposal'), params.id])}"
                redirect(action: "create", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposal.label', default: 'PreProposal'), params.id])}"
            redirect(action: "list")
        }
    }
    
    
    def preProposalApprovalDetails =
        {
        	println"params........"+params
        	def preProposalInstance = PreProposal.get(params.id)
        	def preProposalInstanceList =  PreProposal.list(params)
        	println"preProposalInstance............."+preProposalInstance
        	return['preProposalInstance':preProposalInstance,'preProposalInstanceList':preProposalInstanceList]
    }
    def uploadProposalForm =
    {
    	
    }
    def saveUploadedProposalForm =
    {
    	//def proposalApplicationPath = proposalSettingsService.getProposalSettingsValue("ProposalApplicationPath")
    	//def proposalApplicationForm = proposalSettingsService.getProposalSettings("ProposalForm")
    	 def proposalApplicationForm = gmsSettingsService.getGmsSettings("ProposalForm")
        def proposalApplicationPath = gmsSettingsService.getGmsSettingsValue("ProposalApplicationPath")
    	def proposalSettingsInstance = new GmsSettings()     	
         def webRootDir
         if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
         {
         	webRootDir = proposalApplicationPath
         }
         if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
         {
         	webRootDir = proposalApplicationPath
         }
         	def downloadedfile = request.getFile("attachmentPath");
         if(!downloadedfile.empty) {
         	//String fileName=downloadedfile.getOriginalFilename()
         	String fileName=downloadedfile.getOriginalFilename().toString().substring(0,downloadedfile.getOriginalFilename().toString().indexOf("."))
         	if((fileName.lastIndexOf(".EXE")==-1)&&(fileName.lastIndexOf(".exe")==-1))
 			{
         		new File( webRootDir ).mkdirs()
	        	downloadedfile.transferTo( new File( webRootDir + File.separatorChar + fileName+".gsp") )
         		
         		proposalSettingsInstance.name="ProposalForm"
         		proposalSettingsInstance.value=fileName+".gsp"
         		proposalSettingsInstance.activeYesNo='Y'
         		if (proposalSettingsInstance.save(flush: true)) {
         			if(proposalApplicationForm)
         			{
         			proposalApplicationForm.activeYesNo='N'
         			proposalApplicationForm.save()
         			}
                     flash.message = "File uploaded successfully"
                     
                 }
                 else {
                 	println "error ="
                 	//redirect(action: "create", id: attachmentsInstance.domainId)//, model: [attachmentsInstance: attachmentsInstance])
                 }
 			}
         	else 
             {
             	flash.message = "Cannot upload a .exe file"	
             	
             }
         }
         else {
             flash.message = "file cannot be empty"
             
          }
         redirect(action: "uploadProposalForm")
    }
	
	def getForm = 
	{
		GrailsHttpSession gh = getSession()
		
		def preProposalDetailInstance = preProposalDetailService.getAllPreProposalDetailByPreProposalId(gh.getValue("ProposalId"))
		
		if(preProposalDetailInstance)
		{
					render preProposalDetailInstance as JSON
		}
		
	}
	def submittedPreProposal =
	{
		println "submittedPreProposal "+params
		def preProposalInstance = PreProposal.get(params.id)
		GrailsHttpSession gh=getSession()
		gh.putValue("ProposalId",preProposalInstance.id);
		[preProposalInstance:preProposalInstance,proposalApplicationForm:preProposalInstance.preProposalForm]
	}
def preProposalReviewalStatus =
		
	{
		def preProposalInstance = PreProposal.get(params.id)
		GrailsHttpSession gh = getSession()
		
		def authorityInstance = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.proposalType = 'PreProposal' and PAM.proposalId ="+preProposalInstance.id)
		
		def currentApprovalAuthority
		def proposalApprovalStatus
		def proposalApprovalDetailStatus
		def proposalApprovalDetailStatusList=[]
		def proposalDetailInstance = [];
		
		if(authorityInstance)
		{
		for(int i=0;i<authorityInstance.size();i++)
		{
		  if(new Integer(authorityInstance[i].approveOrder) == (new Integer(preProposalInstance.preProposalLevel))+1)
		  {
			
			currentApprovalAuthority = ApprovalAuthority.find("from ApprovalAuthority AA where AA.id="+authorityInstance[i].approvalAuthority.id)
			
			proposalApprovalStatus = ProposalApproval.findAll("from ProposalApproval PA where PA.approvalAuthorityDetail.approvalAuthority="+currentApprovalAuthority.id+ "and PA.proposalApprovalAuthorityMap="+authorityInstance[i].id)
			
			
			for(int j=0;j<proposalApprovalStatus.size();j++)
			{
				proposalApprovalDetailStatus = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.proposalApproval = "+proposalApprovalStatus[j].id)
				
				proposalApprovalDetailStatusList.add(proposalApprovalDetailStatus)
			}

			
			
			
		  }
		  
		}
				
		
		
		for(int i=0;i<proposalApprovalDetailStatusList.size();i++)
		{
			if(proposalApprovalDetailStatusList[i].size()!= 0)
			{
				proposalDetailInstance.add(proposalApprovalDetailStatusList[i])
			}
		}
		
		}
		else
		{
			
		}
		
		
		
		//def authorityInstance = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AD where AD.person ="+gh.getValue("UserId"))
		def proposalApprovalDetailInstanceList = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+params.id+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='PreProposal' order by PD.proposalApproval.proposalApprovalAuthorityMap.approveOrder")
		['preProposalInstance':preProposalInstance,'authorityInstance':authorityInstance,'currentApprovalAuthority':currentApprovalAuthority,'proposalDetailInstance':proposalDetailInstance,'proposalApprovalStatus':proposalApprovalStatus,'proposalApprovalDetailInstanceList':proposalApprovalDetailInstanceList]
	}


}
