class Notification {
	
    Party party;
	String notificationTitle;	
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
    char publicYesNo;
    char publishYesNo;

static constraints={
                    
                   
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
