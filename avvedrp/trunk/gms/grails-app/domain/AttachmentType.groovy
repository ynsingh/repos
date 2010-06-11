class AttachmentType {
	 String type;
	 String description;
	 String documentType;
	 String createdBy;
	 Date createdDate;
	 String modifiedBy;
	 Date modifiedDate;
	 static constraints={
	             type(nullable:false)
	             description(nullable:true)
	             createdBy(nullable:true)
	             createdDate(nullable:true)
	             modifiedBy(nullable:true)
	             modifiedDate(nullable:true)
	             
	 }

}
