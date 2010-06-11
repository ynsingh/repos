class Notification {
	
    Projects project;	
    String notificationCode;
    Date notificationDate;
    Date proposalSubmissionLastDate
    String eligibilitydocument;
    String applicationForm;
    String description;
    String createdBy;
    Date createdDate;
    String modifiedBy;
    Date modifiedDate;

static constraints={
                    
                    project(nullable:false)
                    notificationCode(blank:false,unique:true)
                    notificationDate(nullable:false)
                    proposalSubmissionLastDate(nullable:false)
                    eligibilitydocument(nullable:true)
                    applicationForm(nullable:true)
                    description(nullable:true)
                    createdBy(nullable:true)
                    createdDate(nullable:true)
                    modifiedBy(nullable:true)
                    modifiedDate(nullable:true)
             }

}
