import java.util.Date;


class MessageAttachments {
	Projects projects;
	String attachmentPath;
	String viewedYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
   
	static constraints = {
		 
		 createdBy(nullable:true)
		 createdDate(nullable:true)
		 modifiedBy(nullable:true)
		 modifiedDate(nullable:true)
	}
}
