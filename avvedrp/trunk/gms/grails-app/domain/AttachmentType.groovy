class AttachmentType {
	 String type;
	 String description;
	 String documentType;
	 String createdBy;
	 Date createdDate;
	 String modifiedBy;
	 Date modifiedDate;
	 char activeYesNo; //15-11-2010
	 static constraints={
	             type(nullable:false)
	             description(nullable:true)
	             createdBy(nullable:true)
	             createdDate(nullable:true)
	             modifiedBy(nullable:true)
	             modifiedDate(nullable:true)
	             
	 }

}
