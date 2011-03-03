import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class AttachmentsService {

    boolean transactional = true

    def serviceMethod() {

    }
    // method for listing project attachments
    public def getProjectAttachments(def grantAllocationInstance)
    {
    	List attachmentsInstanceList = []
    	List attachmentsList = []
    	//println "party ="+partyId
    	//def grantAllocationInstance = GrantAllocation.findAll("from GrantAllocation GA where GA.granter="+partyId+" group by GA.projects.id")
    	for(projectId in grantAllocationInstance.projects)
    	{
    	def attachmentInstance = Attachments.findAll("from Attachments A where A.domain='Projects' and A.domainId="+projectId.id)
    	
    		attachmentsList << attachmentInstance
    	
    	}
    	
    	
    	Iterator<List> iter = attachmentsList.iterator();
    	while(iter.hasNext()){
    	    Iterator<List> siter = iter.next().iterator();
    	    while(siter.hasNext()){
    	         def s = siter.next();
    	         attachmentsInstanceList << s;
    	         
    	     }
    	}
    	
    	
    	return attachmentsInstanceList
    }
    
   //method to find grantAllocation for party
    public def getGrantAllocationForParty(def partyId)
    {
    	def grantAllocationInstance = GrantAllocation.findAll("from GrantAllocation GA where GA.party="+partyId+" group by GA.projects.id")
    	return grantAllocationInstance
    }
   
    //method to find grantAllocation for grantor
    public def getGrantAllocationForGrantor(def partyId)
    {
    	def grantAllocationInstance = GrantAllocation.findAll("from GrantAllocation GA where GA.granter="+partyId+" group by GA.projects.id")
    	return grantAllocationInstance
    }
    
    /*Function to find attachments for a project*/
    
    public def getProjectUploadedAttachments(def projectId)
    {
    	def attachmentInstance = Attachments.findAll("from Attachments A where A.domain='Projects' and A.domainId="+projectId)
    	return attachmentInstance
    }
    /*Function to find notification attachments by attachment type id*/
     public List getnotificationById(def attachmentTypeId)
   {
	   def notificationInstance = NotificationsAttachments.findAll("from NotificationsAttachments NA where NA.attachmentType="+attachmentTypeId)
	   return notificationInstance
   }
     /*Function to get all attachment type*/ 
    public List getattachmentTypes()
   {
	   def attachmentInstance = AttachmentType.findAll("from AttachmentType AT where AT.activeYesNo='Y'")
	   return attachmentInstance
   } 
    /*Function to get all attachment type by id*/ 
    public def getattachmentTypes(def attachmentTypeId)
    {
 	   def attachmentInstance = AttachmentType.get( attachmentTypeId )
 	   return attachmentInstance
    }
    /*Function to get all attachment type*/ 
    public List getattachmentTypesByDocTypeAndType(def type,def docType)
   {
	   def attachmentInstanceList = AttachmentType.findAll("from AttachmentType AT where AT.activeYesNo='Y' and AT.type = '"+type+"' and AT.documentType='"+ docType +"'")
	   return attachmentInstanceList
   } 
    /* Getting Attachment using ExpenseRequestEntry Id*/
    public getAttachmentsbyExpenseRequestEntryId(def domainId,def domain)
    {
    	def attachmentsInstance = Attachments.find("from Attachments AT where AT.domain = '"+domain+"' and AT.domainId='"+domainId+"'")
    	return attachmentsInstance
    }
    
    /*Function to get all attachment type by domain*/ 
    public List getattachmentTypesByDocType(def docType)
   {
	   def attachmentTypeInstance = AttachmentType.findAll("from AttachmentType AT where AT.activeYesNo='Y' and AT.documentType ='"+docType+"'")
	   return attachmentTypeInstance
   } 
    
    /* Getting AttachmentList using ExpenseRequestEntry Id*/
    public getAttachmentListbyDomainIdAndDomain(def domainId,def domain)
    {
    	def attachmentsInstanceList = Attachments.findAll("from Attachments AT where AT.domain='"+domain+"' and AT.domainId='"+domainId+"'")
    	return attachmentsInstanceList
    }
    /*Function to get a attachment type*/ 
    public def getAttachmentTypesByDocumentTypeAndType(def type,def docType)
   {
	   def attachmentInstanceList = AttachmentType.find("from AttachmentType AT where AT.activeYesNo='Y' and AT.type = '"+type+"' and AT.documentType='"+ docType +"'")
	   return attachmentInstanceList
   } 
    /*Function to save a attachments */ 
    public def saveAttachments(def domain,def domainId,def fileName,def attachmentsTypeInstance)
   {
	   println "save attachments---"
    	def attachmentInstanceStatus = false
    	def attachmentsInstance=new Attachments()
	   attachmentsInstance.domain=domain
	   attachmentsInstance.domainId=domainId
	   attachmentsInstance.openedYesNo='N'
	   attachmentsInstance.attachmentPath=fileName
	   attachmentsInstance.attachmentType=attachmentsTypeInstance
	   if(attachmentsInstance.save())
	   {
		   println "save attachments--- saved"
		   attachmentInstanceStatus=true
	   }
	   return attachmentInstanceStatus
   } 
/*Function to find attachments for a domain */
    
    public def getAttachmentsByDomainAndType(def domain,def type,def domainId)
    {
    	def attachmentInstance = Attachments.find("from Attachments A where A.domain='"+domain+"' and A.attachmentType.type='"+type+"' and A.domainId='"+domainId+"'")
    	return attachmentInstance
    }
/*
 * function to update attachments
 */
    public def updateAttachments(def attachmentsInstance)
    {
 	   println "save attachments---"
 	  def attachmentInstanceStatus = false
 	   if(attachmentsInstance.save())
 	   {
 		   println "save attachments--- saved"
 		  attachmentInstanceStatus=true
 	   }
 	   return attachmentInstanceStatus
    }

}
