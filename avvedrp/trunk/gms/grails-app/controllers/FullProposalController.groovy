import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
class FullProposalController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
    	
    	println"params"+params
    	def fullProposalInstance = new FullProposal(params)
    	 def preProposalInstance = PreProposal.get(params.id)
    	 println"preProposalInstance"+preProposalInstance
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        GrailsHttpSession gh=getSession()
        println"params"+params
        def chkFullProposalInstance = []
    	def preProposalList =[]
        def preProposalInstanceList = PreProposal.findAll("from PreProposal where preProposalStatus='Approved' and person="+gh.getValue("UserId"))
        for(int i=0;i<preProposalInstanceList.size();i++)
        {
       chkFullProposalInstance = FullProposal.find("from FullProposal F where F.preProposal = "+preProposalInstanceList[i].id)
        println" chkFullProposalInstance"+ chkFullProposalInstance
        
        if(chkFullProposalInstance != null)
        {
        	println"preProposalInstanceList[i]"+preProposalInstanceList[i]
        	println"chkFullProposalInstance[i]...."+chkFullProposalInstance
        	preProposalInstanceList[i].proposalStatus = chkFullProposalInstance.proposalStatus
        	println"preProposalInstanceList[i].proposalStatus"+preProposalInstanceList[i].proposalStatus
        }
      // preProposalList.add(preProposalInstanceList[i])
        //def fullProposalList = FullProposal.findAll("from FullProposal F where F.preProposal.person="+gh.getValue("UserId")+ "and F.preProposal.preProposalStatus='Approved' group by F.preProposal")
       // println"fullProposalList"+fullProposalList
       
        }	
    	println" preProposalList"+ preProposalList
    	println"preProposalList.proposalStatus"+preProposalList?.proposalStatus
    	
        
        [fullProposalInstanceList: preProposalInstanceList, fullProposalInstanceTotal: FullProposal.count(),'fullProposalInstance':fullProposalInstance,'chkFullProposalInstance':chkFullProposalInstance, 'preProposalList': preProposalInstanceList]
    }

    def create = {
        def fullProposalInstance = new FullProposal()
        fullProposalInstance.properties = params
       
        GrailsHttpSession gh=getSession()
       println"params"+params
    
       def preProposalService = new PreProposalService()
       def preProposalInstance = PreProposal.get(params.id)
       println"preProposalInstance"+preProposalInstance
       def preProposalInstanceList = FullProposal.findAll("from FullProposal F where F.preProposal="+preProposalInstance.id+" and F.preProposal.person.id="+gh.getValue("UserId"))
       fullProposalInstance.preProposal = preProposalInstance
     
       println " preProposalInstanceList "+preProposalInstanceList
       def fullProposalStatus = FullProposal.find("from FullProposal F where F.preProposal="+preProposalInstance.id+" and F.proposalStatus in ('Submitted','Approved')")
       println"fullProposalStatus"+fullProposalStatus
       
       
       
       def fullProposalSavedStatus = FullProposal.find("from FullProposal F where F.preProposal="+preProposalInstance.id+" and F.proposalStatus in ('Saved','NeedMoreInfo')")
       println"fullProposalSavedStatus"+fullProposalSavedStatus
       ['fullProposalInstance': fullProposalInstance,'preProposalInstance':preProposalInstance,'preProposalInstanceList': preProposalInstanceList,'fullProposalStatus':fullProposalStatus,'fullProposalSavedStatus':fullProposalSavedStatus]
       }
     
  

    def save = {
    	
    	println"inside Save"
    	println"params////"+params
    	 def fullProposalInstance = new FullProposal(params)
    	def preProposalInstance = PreProposal.get(params.id)
    	
   println"preProposalInstance....."+preProposalInstance
   println"preProposalInstance.createdBy"+fullProposalInstance.createdBy
    def preProposalService = new PreProposalService()
    	 GrailsHttpSession gh=getSession()
    	def preProposalInstanceList = FullProposal.findAll("from FullProposal F where F.preProposal="+preProposalInstance.id+" and F.preProposal.person.id="+gh.getValue("UserId"))
   
    
    //println"notificationInstanceList.notificationCode"+notificationInstanceList.notificationCode
     
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
 	      println"downloadedfile"+downloadedfile
 	       
 		if(!downloadedfile.empty)
 		{
	    		fileName=downloadedfile.getOriginalFilename()
	    		//String fileName=downloadedfile.getOriginalFilename()
	    		
	    		
	    		
	    		downloadedfile.transferTo( new File( webRootDir +fileName) )
	    		
	    		fullProposalInstance.createdBy=fileName
	    	println"finish save"	
 		
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
     	println "not saved ==============="
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
     		println"++++++++++++++id+++++++++++"+params
     		def gmsSettingsService = new GmsSettingsService()
     		println"++++++++++++++id+++++++++++"+params
     		def attachmentsName='FullProposalForm'
     		def gmsSettingsInstance = gmsSettingsService.getGmsSettings(attachmentsName)
     		def webRootDir
     		
     		String fileName = fullProposalInstance.createdBy
     		println"++++++++++++++++++filename+++++++++"+fileName
     		
     		if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
            {
     			webRootDir = gmsSettingsInstance.value
            }
            if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
            {
            	webRootDir = gmsSettingsInstance.value
            }
     		println"++++++++++++++++++filename+++++++++"+fileName
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
     		//def file = new File('c:/somefolder/'+fileName)     
     		//response.setContentType("application/octet-stream") 
     		response.setHeader("Content-disposition", "attachment;fileName=${file.getName()}") 
     		 
     		response.outputStream << file.newInputStream() // Performing a binary stream copy 
    
     		
 }
    def show = 
        {
            def fullProposalInstance = FullProposal.get(params.id)
             GrailsHttpSession gh=getSession()
            println"params"+params
         
            def preProposalService = new PreProposalService()
            def preProposalInstance = PreProposal.get(params.id)
            println"preProposalInstance"+preProposalInstance
            def preProposalInstanceList = PreProposal.findAll("from PreProposal where preProposalStatus='Approved' and person="+gh.getValue("UserId"))
          
            
            ['fullProposalInstance': preProposalInstance,'preProposalInstance':preProposalInstance,'fullProposalInstanceList': preProposalInstanceList]
            }
      
        
        
    
     

    def edit = {
    	 
        
         GrailsHttpSession gh=getSession()
        println"params"+params
      
     
        def preProposalService = new PreProposalService()
        def fullProposalInstance = FullProposal.get(params.id)
        //println"preProposalInstance"+preProposalInstance
        println"fullProposalInstance"+fullProposalInstance
        
        //def preProposalInstanceList = FullProposal.findAll("from FullProposal F where F.preProposal="+preProposalInstance.id+" and F.preProposal.person.id="+gh.getValue("UserId"))
        //fullProposalInstance.preProposal = preProposalInstance
        
        ['fullProposalInstance': fullProposalInstance]
    }

    def update = {
    	
    	 //def fullProposalInstance = new FullProposal(params)
    	 println"params"+params
    	 def fullProposalInstance = FullProposal.get(params.id)
     	
        println"fullProposalInstance"+fullProposalInstance
        
           
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
 	      println"downloadedfile"+downloadedfile
 	       
 		if(!downloadedfile.empty)
 		{
	    		fileName=downloadedfile.getOriginalFilename()
	    		//String fileName=downloadedfile.getOriginalFilename()
	    		
	    		
	    		
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
 		println"fullProposalInstance.proposalStatus"+fullProposalInstance.proposalStatus
 		def preProposalInstance = PreProposal.get(fullProposalInstance.preProposal.id)
 		fullProposalInstance.preProposal = preProposalInstance
 		fullProposalInstance.preProposalLevel=new Integer(0)
 		
 		
 		if(!fullProposalInstance.hasErrors() && fullProposalInstance.save()) 
 		{
 			println "saved ==="+fullProposalInstance
 			def fullProposalDetailInstance = FullProposalDetail.find("from FullProposalDetail FD where FD.fullProposal = "+fullProposalInstance.id)
 			
 			//fullProposalDetailInstance.fullProposal=fullProposalInstance
 			fullProposalDetailInstance.fileName=fullProposalInstance?.createdBy
 			fullProposalDetailInstance.comments =params.modifiedBy
 			fullProposalDetailInstance.proposalSubmittedDate=new Date()
 			
 			
 			fullProposalDetailInstance.save()
 			
         flash.message = "${message(code: 'default.updated.label')}"
         	
         redirect(action:create,id:fullProposalInstance.preProposal.id)
 		}
 		else
 		{
     	println "not saved ==============="
         render(view:'create',model:['fullProposalInstance': fullProposalInstance,'preProposalInstance':preProposalInstance])
 		}
    }

    def delete = 
    {
        def fullProposalInstance = FullProposal.get(params.id)
        println"fullProposalInstance"+fullProposalInstance
        if (fullProposalInstance) 
        {
        	def fullProposalDetailInstance = FullProposalDetail.findAll("from FullProposalDetail FD where FD.fullProposal = "+fullProposalInstance.id)
        	println"fullProposalDetailInstance"+fullProposalDetailInstance
        	
        	
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
		println"fullProposalInstance"+fullProposalInstance
		println"fullProposalInstance.preProposal"+fullProposalInstance.preProposal.id
		println"fullProposalInstance.id"+fullProposalInstance.id
		def authorityInstance = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.proposalType = 'FullProposal' and PAM.proposalId ="+fullProposalInstance.id)
		println"authorityInstance[0].approvalAuthority.name"+authorityInstance
		def currentApprovalAuthority
		def proposalApprovalStatus
		def proposalApprovalDetailStatus
		def proposalApprovalDetailStatusList=[]
		def proposalDetailInstance = [];
		println"fullProposalInstance.preProposalLevel"+new Integer(fullProposalInstance.preProposalLevel)+1
		println "authorityInstance "+authorityInstance
		if(authorityInstance)
		{
		for(int i=0;i<authorityInstance.size();i++)
		{
		  if(new Integer(authorityInstance[i].approveOrder) == (new Integer(fullProposalInstance.preProposalLevel))+1)
		  {
			println"(authorityInstance[i].approveOrder)"+new Integer(authorityInstance[i].approveOrder)
			currentApprovalAuthority = ApprovalAuthority.find("from ApprovalAuthority AA where AA.id="+authorityInstance[i].approvalAuthority.id)
			println"currentApprovalAuthority"+currentApprovalAuthority.name
			proposalApprovalStatus = ProposalApproval.findAll("from ProposalApproval PA where PA.approvalAuthorityDetail.approvalAuthority="+currentApprovalAuthority.id+ "and PA.proposalApprovalAuthorityMap="+authorityInstance[i].id)
			println"proposalApprovalStatus"+proposalApprovalStatus.size()
			
			for(int j=0;j<proposalApprovalStatus.size();j++)
			{
				proposalApprovalDetailStatus = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.proposalApproval = "+proposalApprovalStatus[j].id)
				println"proposalApprovalDetailStatus"+proposalApprovalDetailStatus.size()
				proposalApprovalDetailStatusList.add(proposalApprovalDetailStatus)
			}

			
			
			
		  }
		  
		}
				
		println"proposalApprovalDetailStatusList"+proposalApprovalDetailStatusList
		
		for(int i=0;i<proposalApprovalDetailStatusList.size();i++)
		{
			if(proposalApprovalDetailStatusList[i].size()!= 0)
			{
				proposalDetailInstance.add(proposalApprovalDetailStatusList[i])
			}
		}
		println"proposalDetailInstance"+proposalDetailInstance.size()
		}
		else
		{
			
		}
		
		
		println "currentApprovalAuthority "+currentApprovalAuthority
		println"params.id"+fullProposalInstance.id
		println"authorityInstance.proposalId"+authorityInstance.proposalId
		//def authorityInstance = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AD where AD.person ="+gh.getValue("UserId"))
		def proposalApprovalDetailInstanceList = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+fullProposalInstance.id+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='FullProposal' order by PD.proposalApproval.proposalApprovalAuthorityMap.approveOrder")
		println"proposalApprovalDetailInstanceList"+proposalApprovalDetailInstanceList
		['fullProposalInstance':fullProposalInstance,'authorityInstance':authorityInstance,'currentApprovalAuthority':currentApprovalAuthority,'proposalDetailInstance':proposalDetailInstance,'proposalApprovalStatus':proposalApprovalStatus,'proposalApprovalDetailInstanceList':proposalApprovalDetailInstanceList]
	}

}
