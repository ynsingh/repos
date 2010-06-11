class NotificationsAttachments 
{
	 Notification notification;
	 AttachmentType attachmentType;
	 Proposal proposal;
	 String attachmentPath;
	 String createdBy;
	 Date createdDate;
	 String modifiedBy;
	 Date modifiedDate;
	
	static constraints=
	{
		         
		         attachmentType(nullable:false)
		         proposal(nullable:true)
		         attachmentPath(nullable:true)
		         createdBy(nullable:true)
	             createdDate(nullable:true)
	             modifiedBy(nullable:true)
	             modifiedDate(nullable:true)
	}
}
