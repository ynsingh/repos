import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
class FullProposalController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def preProposalService
    def fullProposalService
    def index = {
        redirect(action: "list", params: params)
    }

    def list = 
    {
    	
    	def fullProposalInstance = new FullProposal(params)
    	def preProposalInstance = PreProposal.get(params.id)
    	params.max = Math.min(params.max ? params.int('max') : 10, 100)
        GrailsHttpSession gh=getSession()
       
        def chkFullProposalInstance = []
    	def preProposalList =[]
        /*method to get preproposal of login user which status Approved*/
       def preProposalInstanceList = preProposalService.getPreProposalByStatus('Approved',gh.getValue("UserId"))
       for(int i=0;i<preProposalInstanceList.size();i++)
        {
        	/*method to check fullproposal already exist for this proposal*/
        	chkFullProposalInstance=fullProposalService.getFullProposalByProposal(preProposalInstanceList[i].id)
        	if(chkFullProposalInstance != null)
        	{
        		preProposalInstanceList[i].proposalStatus = chkFullProposalInstance.proposalStatus
        	}
    
        }	
    	
        [fullProposalInstanceList: preProposalInstanceList, fullProposalInstanceTotal: FullProposal.count(),'fullProposalInstance':fullProposalInstance,'chkFullProposalInstance':chkFullProposalInstance, 'preProposalList': preProposalInstanceList]
    }

    def create = {
        def fullProposalInstance = new FullProposal()
        fullProposalInstance.properties = params
       
        GrailsHttpSession gh=getSession()
        def preProposalService = new PreProposalService()
       def preProposalInstance = PreProposal.get(params.id)
       def preProposalInstanceList = FullProposal.findAll("from FullProposal F where F.preProposal="+preProposalInstance.id+" and F.preProposal.person.id="+gh.getValue("UserId"))
       fullProposalInstance.preProposal = preProposalInstance
       def fullProposalStatus = FullProposal.find("from FullProposal F where F.preProposal="+preProposalInstance.id+" and F.proposalStatus in ('Submitted','Approved')")
       def fullProposalSavedStatus = FullProposal.find("from FullProposal F where F.preProposal="+preProposalInstance.id+" and F.proposalStatus in ('Saved','NeedMoreInfo')")
       ['fullProposalInstance': fullProposalInstance,'preProposalInstance':preProposalInstance,'preProposalInstanceList': preProposalInstanceList,'fullProposalStatus':fullProposalStatus,'fullProposalSavedStatus':fullProposalSavedStatus]
       }
     
  

    def save = {
    	
    	
    	def fullProposalInstance = new FullProposal(params)
    	def preProposalInstance = PreProposal.get(params.id)
    	
  
    	def preProposalService = new PreProposalService()
    	GrailsHttpSession gh=getSession()
    	def preProposalInstanceList = FullProposal.findAll("from FullProposal F where F.preProposal="+preProposalInstance.id+" and F.preProposal.person.id="+gh.getValue("UserId"))
   
    
    	String fileName
 		
 		def attachmentsName='FullProposalForm'
		def gmsSettingsService = new GmsSettingsService()
		def gmsSettingsInstance = gmsSettingsService.getGmsSettings(attachmentsName)
 		def webRootDir
 	    if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
 	    {
        	webRootDir = gmsSettingsInstance.value
 	    }
        if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
        {
        	webRootDir = gmsSettingsInstance.value
        }
 	      new File( webRootDir).mkdirs()
 	      def downloadedfile = request.getFile("myFile");
 	     
 	       
 		if(!downloadedfile.empty)
 		{
	    		fileName=downloadedfile.getOriginalFilename()
	    		
	    		downloadedfile.transferTo( new File( webRootDir +fileName) )
	    		
	    		fullProposalInstance.createdBy=fileName
	    	
 		
	    		fullProposalInstance.preProposal=preProposalInstance
	    		if(params.status=="Submitted")
	    		{
	    			fullProposalInstance.proposalStatus="Submitted"
	    		}
	    		else
	    		{
	    			fullProposalInstance.proposalStatus="Saved"
	    		}
	    		fullProposalInstance.preProposalLevel=new Integer(0)
	    		if(!fullProposalInstance.hasErrors() && fullProposalInstance.save()) 
	    		{
		 			def fullProposalDetailInstance = new FullProposalDetail()
		 			fullProposalDetailInstance.fullProposal=fullProposalInstance
		 			fullProposalDetailInstance.fileName=fileName
		 			fullProposalDetailInstance.proposalSubmittedDate=new Date()
		 			fullProposalDetailInstance.comments =fullProposalInstance.modifiedBy
		 			fullProposalDetailInstance.save()
		 			flash.message = "${message(code: 'default.created.label')}"
         	
		 			redirect(action:create,id:params.id)
	    		}
	    		else
	    		{
     	
	    			render(view:'create',model:['fullProposalInstance': fullProposalInstance,'preProposalInstance':preProposalInstance])
	    		}
 		}else
 		{
 			flash.message = "${message(code: 'default.SelectFile.label')}"
 			render(view:'create',model:['fullProposalInstance': fullProposalInstance,'preProposalInstance':preProposalInstance,'preProposalInstanceList':preProposalInstanceList])
 		}
    }



    	def download = 
        {
        	 def fullProposalInstance = FullProposal.get(params.id)
        	def preProposalInstance = PreProposal.get(params.id)
     		
     		def gmsSettingsService = new GmsSettingsService()
     		
     		def attachmentsName='FullProposalForm'
     		def gmsSettingsInstance = gmsSettingsService.getGmsSettings(attachmentsName)
     		def webRootDir
     		
     		String fileName = fullProposalInstance.createdBy
     		
     		if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
            {
     			webRootDir = gmsSettingsInstance.value
            }
            if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
            {
            	webRootDir = gmsSettingsInstance.value
            }
     		
     		def file = new File(webRootDir+fileName) 
     		def fname=file.getName()
     		
     		if (fname.indexOf(".gif")>-1) {
    	         response.setContentType("image/gif");
    	      } else if (fname.indexOf(".pdf")>-1) {
    	         response.setContentType("application/pdf");
    	      } else if (fname.indexOf(".doc")>-1) {
    	         response.setContentType("application/msword");
    	      }else if (fname.indexOf(".xls")>-1){
    	    	 response.setContentType("application/vnd.ms-excel");
    	      }else if (fname.indexOf(".xlsx")>-1){
    	    	 response.setContentType("application/vnd.ms-excel");
    	      }else if(fname.indexOf(".docx")>-1) {
    	    	 response.setContentType("application/msword");
    	      }else if(fname.indexOf(".ppt")>-1) {
    	    	 response.setContentType("application/ppt");
    	      }else if(fname.indexOf(".pptx")>-1) {
    	    	 response.setContentType("application/ppt");
    	      }
     		
     		response.setHeader("Content-disposition", "attachment;fileName=${file.getName()}") 
     		 
     		response.outputStream << file.newInputStream() // Performing a binary stream copy 
    
     		
 }
    def show = 
        {
            def fullProposalInstance = FullProposal.get(params.id)
            GrailsHttpSession gh=getSession()
            def preProposalService = new PreProposalService()
            def preProposalInstance = PreProposal.get(params.id)
           
            def preProposalInstanceList = PreProposal.findAll("from PreProposal where preProposalStatus='Approved' and person="+gh.getValue("UserId"))
          
            
            ['fullProposalInstance': preProposalInstance,'preProposalInstance':preProposalInstance,'fullProposalInstanceList': preProposalInstanceList]
            }
      
        
        
    
     

    def edit = 
    {
    	 
        GrailsHttpSession gh=getSession()
        def preProposalService = new PreProposalService()
        def fullProposalInstance = FullProposal.get(params.id)
        
        ['fullProposalInstance': fullProposalInstance]
    }

    def update = {
    	
    	def fullProposalInstance = FullProposal.get(params.id)
     	String fileName
 		
 		def attachmentsName='FullProposalForm'
			def gmsSettingsService = new GmsSettingsService()
			def gmsSettingsInstance = gmsSettingsService.getGmsSettings(attachmentsName)
 		 def webRootDir
 	       if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
 	        {
 	        	webRootDir = gmsSettingsInstance.value
 	       }
 	       if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
 	       {
 	        	webRootDir = gmsSettingsInstance.value
 	       }
 	       
 	      def downloadedfile = request.getFile("myFile");
 	     	       
 		if(!downloadedfile.empty)
 		{
	    		fileName=downloadedfile.getOriginalFilename()
	    		downloadedfile.transferTo( new File( webRootDir +fileName) )
	    		
	    		fullProposalInstance.createdBy=fileName
	    	
 		}
 	      
 		
 		if(params.status=="Submitted")
 		{
 			fullProposalInstance.proposalStatus="Submitted"
 		}
 		else
 		{
 			fullProposalInstance.proposalStatus="Saved"
 		}
 		def preProposalInstance = PreProposal.get(fullProposalInstance.preProposal.id)
 		fullProposalInstance.preProposal = preProposalInstance
 		//fullProposalInstance.preProposalLevel=new Integer(0)
 		
 		
 		if(!fullProposalInstance.hasErrors() && fullProposalInstance.save()) 
 		{
 			def fullProposalDetailInstance = FullProposalDetail.find("from FullProposalDetail FD where FD.fullProposal = "+fullProposalInstance.id)
 			
 			fullProposalDetailInstance.fileName=fullProposalInstance?.createdBy
 			fullProposalDetailInstance.comments =params.modifiedBy
 			fullProposalDetailInstance.proposalSubmittedDate=new Date()
 			
 			
 			fullProposalDetailInstance.save()
 			
         flash.message = "${message(code: 'default.updated.label')}"
         	
         redirect(action:create,id:fullProposalInstance.preProposal.id)
 		}
 		else
 		{
     	  render(view:'create',model:['fullProposalInstance': fullProposalInstance,'preProposalInstance':preProposalInstance])
 		}
    }

    def delete = 
    {
        def fullProposalInstance = FullProposal.get(params.id)
        if (fullProposalInstance) 
        {
        	def fullProposalDetailInstance = FullProposalDetail.findAll("from FullProposalDetail FD where FD.fullProposal = "+fullProposalInstance.id)
        	if(fullProposalDetailInstance[0].delete())
        	{
        	fullProposalInstance.delete()
           
                
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'fullProposal.label', default: 'FullProposal'), params.id])}"
                redirect(action: "edit",id:fullProposalInstance.id)
          
        	}
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fullProposal.label', default: 'FullProposal'), params.id])}"
            redirect(action: "list")
        }
    }
    
def fullProposalReviewalStatus =
		
	{
    	def preProposalInstance = PreProposal.get(params.id)
		def fullProposalInstance = FullProposal.find("from FullProposal FP where FP.preProposal="+preProposalInstance.id)
		GrailsHttpSession gh = getSession()
		def authorityInstance = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.proposalType = 'FullProposal' and PAM.proposalId ="+fullProposalInstance.id)
		def currentApprovalAuthority
		def proposalApprovalStatus
		def proposalApprovalDetailStatus
		def proposalApprovalDetailStatusList=[]
		def proposalDetailInstance = [];
		if(authorityInstance)
		{
		for(int i=0;i<authorityInstance.size();i++)
		{
		  if(new Integer(authorityInstance[i].approveOrder) == (new Integer(fullProposalInstance.preProposalLevel))+1)
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
		
		
		def proposalApprovalDetailInstanceList = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+fullProposalInstance.id+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='FullProposal' order by PD.proposalApproval.proposalApprovalAuthorityMap.approveOrder")
		['fullProposalInstance':fullProposalInstance,'authorityInstance':authorityInstance,'currentApprovalAuthority':currentApprovalAuthority,'proposalDetailInstance':proposalDetailInstance,'proposalApprovalStatus':proposalApprovalStatus,'proposalApprovalDetailInstanceList':proposalApprovalDetailInstanceList]
	}

}
