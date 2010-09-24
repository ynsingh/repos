class Attachments {
	String domain;
	String domainId;
	AttachmentType attachmentType;
	String attachmentPath;
	String openedYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
   
	static constraints = {
    	 
    	attachmentType(nullable:false)
         attachmentPath(nullable:true)
         openedYesNo(nullable:true)
         createdBy(nullable:true)
         createdDate(nullable:true)
         modifiedBy(nullable:true)
         modifiedDate(nullable:true)
    
    }
}
