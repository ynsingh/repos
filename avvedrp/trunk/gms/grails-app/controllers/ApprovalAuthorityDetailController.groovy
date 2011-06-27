import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.springframework.beans.BeanWrapper
import org.springframework.beans.PropertyAccessorFactory
import grails.util.GrailsUtil

import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class ApprovalAuthorityDetailController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "create", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [approvalAuthorityDetailInstanceList: ApprovalAuthorityDetail.list(params), approvalAuthorityDetailInstanceTotal: ApprovalAuthorityDetail.count()]
    }

    def create = {
    	GrailsHttpSession gh=getSession()
    	def userService = new UserService()
        def approvalAuthorityDetailInstance = new ApprovalAuthorityDetail()
        approvalAuthorityDetailInstance.properties = params

        def approvalAuthorityList = ApprovalAuthority.findAll("from ApprovalAuthority A where A.activeYesNo = 'Y' and A.party="+gh.getValue("Party"))
        def approvalAuthorityInstance =ApprovalAuthority.get(params.id)
        approvalAuthorityDetailInstance = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AA where AA.activeYesNo='Y' and AA.approvalAuthority="+params.id)
        
        println"approvalAuthorityDetailInstance++++"+approvalAuthorityDetailInstance
        //def userList = UserRole.findAll("from UserRole UR  where UR.role = "+(Authority.find("from Authority A where A.authority = 'ROLE_REVIEWER'"))?.id)
        def userList = userService.getAllUsersByPartyID(gh.getValue("PartyID"))
        
        
        println"userList"+userList
        
    	 
        println".........."+(Authority.find("from Authority A where A.authority = 'ROLE_REVIEWER'"))?.id
        println"userList"+userList
        def userInstanceList =[]
    	for(int i=0;i<userList.size();i++)
    	{
    		def userInstance = Person.get(userList[i].user.id)
    		println"userInstance"+userInstance
    		userInstanceList.add(userInstance)
    	}
    	println"userInstanceList"+userInstanceList
    	def approvalAuthorityDetailPersonList = []
      if(approvalAuthorityDetailInstance)
    	{
	    	for(int i=0;i<approvalAuthorityDetailInstance.person.size();i++)
	        {
	
	     	   println"approvalAuthorityDetailPersonList++++"
	    		approvalAuthorityDetailPersonList.add(approvalAuthorityDetailInstance.person[i])
	    		
	        }
    	}
    	if(approvalAuthorityDetailPersonList)
    	{
    		userInstanceList.removeAll(approvalAuthorityDetailPersonList) 
        println"userInstanceList"+userInstanceList

    	}
        
    	println"approvalAuthorityDetailInstance++++"+approvalAuthorityDetailInstance
        return [approvalAuthorityInstance: approvalAuthorityInstance,approvalAuthorityDetailInstance: approvalAuthorityDetailInstance,'userInstanceList':userInstanceList]
       
    
    

        def approvalAuthorityDetailInstanceList = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AD where AD.activeYesNo = 'Y'")
        return [approvalAuthorityDetailInstance: approvalAuthorityDetailInstance,'userInstanceList':userInstanceList,'approvalAuthorityList':approvalAuthorityList,'approvalAuthorityDetailInstanceList':approvalAuthorityDetailInstanceList]

    }

    def save = {
    	def approvalAuthorityDetailService = new ApprovalAuthorityDetailService()
        def approvalAuthorityDetailInstance = new ApprovalAuthorityDetail(params)
    	approvalAuthorityDetailInstance = approvalAuthorityDetailService.saveApprovalAuthorityDetail(params)
        if (approvalAuthorityDetailInstance) {
        	def approvalAuthorityInstance=ApprovalAuthority.get(approvalAuthorityDetailInstance.approvalAuthority.id)
        	def approvalAuthorityDetailInstanceList=approvalAuthorityDetailService.getApprovalAuthorityDetailByApprovalAuthority(approvalAuthorityDetailInstance.approvalAuthority.id)
        	approvalAuthorityInstance.totalMembers=approvalAuthorityDetailInstanceList.size()
        	approvalAuthorityInstance.save()
        	
            flash.message = "${message(code: 'default.ApprovalAuthorityDetailCreated.label')}"
            redirect(action: "create", id: approvalAuthorityDetailInstance.id)
        }
        else {
            render(view: "create",id: approvalAuthorityDetailInstance.id)
        }
    }

    def show = {
        def approvalAuthorityDetailInstance = ApprovalAuthorityDetail.get(params.id)
        if (!approvalAuthorityDetailInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'approvalAuthorityDetail.label', default: 'ApprovalAuthorityDetail'), params.id])}"
            redirect(action: "list")
        }
        else {
            [approvalAuthorityDetailInstance: approvalAuthorityDetailInstance]
        }
    }

    def edit = {
    	def approvalAuthorityDetailService = new ApprovalAuthorityDetailService()
    	def approvalAuthorityDetailInstance = approvalAuthorityDetailService.getApprovalAuthorityDetailById(new Integer( params.id ))
    	   def userList = UserRole.findAll("from UserRole UR  where UR.role = "+(Authority.find("from Authority A where A.authority = 'ROLE_REVIEWER'")).id)
        println".........."+(Authority.find("from Authority A where A.authority = 'ROLE_REVIEWER'")).id
        println"userList"+userList
        def userInstanceList =[]
       for(int i=0;i<userList.size();i++)
       {
        def userInstance = Person.get(userList[i].user.id)
        println"userInstance"+userInstance
        userInstanceList.add(userInstance)
       }
        println"userInstanceList"+userInstanceList
       
        if (!approvalAuthorityDetailInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'approvalAuthorityDetail.label', default: 'ApprovalAuthorityDetail'), params.id])}"
            redirect(action: "create")
        }
        else {
            return [approvalAuthorityDetailInstance: approvalAuthorityDetailInstance,'userInstanceList':userInstanceList]
        }
    }

    def update = {
    	def approvalAuthorityDetailService = new ApprovalAuthorityDetailService()
    	def approvalAuthorityDetailInstance = approvalAuthorityDetailService.getApprovalAuthorityDetailById(new Integer( params.id ))
       
        if (approvalAuthorityDetailInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (approvalAuthorityDetailInstance.version > version) {
                    
                    approvalAuthorityDetailInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'approvalAuthorityDetail.label', default: 'ApprovalAuthorityDetail')] as Object[], "Another user has updated this ApprovalAuthorityDetail while you were editing")
                    render(view: "edit", model: [approvalAuthorityDetailInstance: approvalAuthorityDetailInstance])
                    return
                }
            }
            approvalAuthorityDetailInstance = approvalAuthorityDetailService.updateApprovalAuthorityDetail(params,approvalAuthorityDetailInstance)
            if( approvalAuthorityDetailInstance.saveMode.equals( "Updated")) {
                flash.message = "${message(code: 'default.ApprovalAuthorityDetailUpdated.label')}"
                redirect(action: "create", id: approvalAuthorityDetailInstance.id)
            }
            else {
                render(view: "edit", model: [approvalAuthorityDetailInstance: approvalAuthorityDetailInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'approvalAuthorityDetail.label', default: 'ApprovalAuthorityDetail'), params.id])}"
            redirect(action: "list")
        }
    }
    
    def delete = 
    {
    	
    	def approvalAuthorityDetailService = new ApprovalAuthorityDetailService()
    	def approvalAuthorityDetailInstance = new ApprovalAuthorityDetail()
    	approvalAuthorityDetailInstance.properties =params
    	
    		
           def approvalAuthorityDetailId = null
            
            /* Delete the account head details */
    		approvalAuthorityDetailId = approvalAuthorityDetailService.deleteApprovalAuthorityDetail(params)
			if (approvalAuthorityDetailId == 0)
			{
				/* Shows the following message if the account head is already in use. */
				 flash.message = "${message(code: 'default.approvalauthorityusedinapprovalauthoritydeatil.label')}"
				 redirect(action:edit,id:params.id)
			}
			else
			{	
				if(approvalAuthorityDetailId != null)
				{				
					flash.message = "${message(code: 'default.deleted.label')}"			
							    		
						   redirect(action:create,id:approvalAuthorityDetailInstance.id)
						
				}    
				else 
				{
		            flash.message = "${message(code: 'default.notbedeletedAccountHead.label')}"
		            redirect(action:edit,id:params.id)
		        }
			}
    }

    
    def add =
    {
    	println"ADDITION"
    	println"params.id"+params
    	println "params ===="+params.person
    	def personInstance
        GrailsHttpSession gh=getSession()
        def approvalAuthorityInstance
        def authorityPersonInstanceList = params.person
        def authorityPersonList = params.person.toString()
        println "authorityPersonList +"+authorityPersonList
        def webRootDir = servletContext.getRealPath("/")
        def authorityPersonListSplit=authorityPersonList.split(',')
        println "authorityPersonListSplit "+authorityPersonListSplit
           if( authorityPersonListSplit.length == 1)
           {
        	println "list ======"
        	   def authorityPersonDet=[]
        	authorityPersonDet.add(params.person)
        	println "authorityPersonDet --"+authorityPersonDet
       		authorityPersonInstanceList=authorityPersonDet 
           }
    	println"authorityPersonInstanceList***********"+authorityPersonInstanceList
    	
    	for(int i=0;i<authorityPersonInstanceList.size();i++)
    	{
    		println"authorityPersonInstanceList###################"+authorityPersonInstanceList[i]
    		personInstance = Person.get(authorityPersonInstanceList[i])
        	println"personInstanceList&&&&&&&&&&&&&&&"+personInstance
        	approvalAuthorityInstance = ApprovalAuthority.get(params.approvalAuthority.id)
        	println"approvalAuthorityInstance"+approvalAuthorityInstance
        	def approvalAuthorityDetailInstance = new ApprovalAuthorityDetail()
           	approvalAuthorityDetailInstance.approvalAuthority = approvalAuthorityInstance
          	approvalAuthorityDetailInstance.person = personInstance
          	println"approvalAuthorityDetailInstance"+approvalAuthorityDetailInstance
        	approvalAuthorityDetailInstance.activeYesNo="Y"
             if(approvalAuthorityDetailInstance.save())
             {
            	 println "saved "+approvalAuthorityDetailInstance
             }
             
        	println"approvalAuthorityDetailInstance+++++++++++++++++++="+approvalAuthorityDetailInstance
     	}	
    	
    	redirect(action:getAssignedMembers,id:approvalAuthorityInstance.id)
 
       /* def personInstance   		
   		if(params.person)
   			personInstance = Person.find("from Person PE where PE.id="+params.person)
    	println"personInstanceList"+personInstance
    	def approvalAuthorityInstance = ApprovalAuthority.get(params.approvalAuthority.id)
    	println"approvalAuthorityInstance"+approvalAuthorityInstance
    	def approvalAuthorityDetailInstance = new ApprovalAuthorityDetail()
       	approvalAuthorityDetailInstance.approvalAuthority = approvalAuthorityInstance
      	approvalAuthorityDetailInstance.person = personInstance 
      	println"approvalAuthorityDetailInstance"+approvalAuthorityDetailInstance
    	approvalAuthorityDetailInstance.activeYesNo="Y"
         if(approvalAuthorityDetailInstance.save())
         {
        	 println "saved "+approvalAuthorityDetailInstance
         }
         
    	println"approvalAuthorityDetailInstance"+approvalAuthorityDetailInstance
    	redirect(action:getAssignedMembers,id:approvalAuthorityInstance.id)
        return approvalAuthorityDetailInstance */
 
 }
   def getAssignedMembers =   {
    	GrailsHttpSession gh=getSession()
    	def userService = new UserService()
    	def approvalAuthorityDetailInstance = new ApprovalAuthorityDetail()
        approvalAuthorityDetailInstance.properties = params
        approvalAuthorityDetailInstance = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AA where AA.activeYesNo='Y' and AA.approvalAuthority="+params.id)
        println"approvalAuthorityDetailInstance++++"+approvalAuthorityDetailInstance
        println"approvalAuthorityDetailInstance++++"+approvalAuthorityDetailInstance.person
        def approvalAuthorityDetailPersonList =[]
    	def userInstance
    	 def userList = userService.getAllUsersByPartyID(gh.getValue("PartyID"))
       // def userList = UserRole.findAll("from UserRole UR  where UR.role = "+(Authority.find("from Authority A where A.authority = 'ROLE_REVIEWER'")).id)
        println".........."+(Authority.find("from Authority A where A.authority = 'ROLE_REVIEWER'")).id
        println"userList"+userList
        def userInstanceList =[]
    	
        for(int i=0;i<userList.size();i++)
         {
           userInstance = Person.get(userList[i].user.id)
           println"userInstance"+userInstance
           userInstanceList.add(userInstance)
         }
    	println"userInstanceList++++"+userInstanceList
    	 
    	for(int i=0;i<approvalAuthorityDetailInstance.person.size();i++)
        {

     	   println"approvalAuthorityDetailPersonList++++"
    		approvalAuthorityDetailPersonList.add(approvalAuthorityDetailInstance.person[i])
    		
        }
    	println"approvalAuthorityDetailPersonList++++"+approvalAuthorityDetailPersonList
    	
    	userInstanceList.removeAll(approvalAuthorityDetailPersonList)
    	println "userInstanceList "+userInstanceList
      		
    	render (template:"facultyReviewer" , model: ['userInstanceList' : userInstanceList,'approvalAuthorityDetailInstance' : approvalAuthorityDetailInstance])
   
    }
    
    def remove =
    {
    	
    	def approvalAuthorityDetailInstance
    	def proposalApprovalInstance
    	def approvalAuthorityDetailService = new ApprovalAuthorityDetailService()
    	def proposalApprovalAuthorityMapService = new ProposalApprovalAuthorityMapService()
    	def proposalApprovalService = new ProposalApprovalService()
    	GrailsHttpSession gh=getSession()
		def authorityPersonInstanceList = params.approvalAuthority
		def authorityPersonList=params.approvalAuthority.toString();
		def authorityPersonListSplit=authorityPersonList.split(',')
		if( authorityPersonListSplit.length == 1)
        {
			def authorityPersonDet=[]
           	authorityPersonDet.add(params.approvalAuthority)
           	authorityPersonInstanceList=authorityPersonDet 
    	}
    	for(int i=0;i<authorityPersonInstanceList.size();i++)
		{
    		approvalAuthorityDetailInstance = ApprovalAuthorityDetail.find("from ApprovalAuthorityDetail AD where AD.id="+authorityPersonInstanceList[i])
    		def evalAnswerInstance = EvalAnswer.find("from EvalAnswer EA where EA.person.id="+approvalAuthorityDetailInstance.person.id)
    		
    		def proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
		.getProposalApprovalAuthorityMapByApprovalauthorityId(approvalAuthorityDetailInstance.approvalAuthority.id)
		println"proposalApprovalAuthorityMapInstance--------------->"+proposalApprovalAuthorityMapInstance
		if(proposalApprovalAuthorityMapInstance)
		{
			for(int j=0;j<proposalApprovalAuthorityMapInstance.size();j++)
        	{
				proposalApprovalInstance=proposalApprovalService.getProposalApprovalByAuthorityMapandauthorityId(proposalApprovalAuthorityMapInstance,approvalAuthorityDetailInstance.id)
		    }
		}
			if(proposalApprovalInstance || evalAnswerInstance)
			{
				flash.error = "${message(code: 'default.CannotRemove.message')}"
					
			}
			else
			{
    		approvalAuthorityDetailInstance.activeYesNo='N'
				if(approvalAuthorityDetailInstance.save())
	    	    {
	    	    println "saved"
	    	    }
			}
		
		}
    	redirect(action:getAssignedMembers,id:approvalAuthorityDetailInstance.approvalAuthority.id)
    	
    }
    
    
}

