

class ExternalFundAllocation {


GrantAllocation grantAllocation
char refundable
String status
String remarks
String createdBy
Date createdDate
String modifiedBy
Date modifiedDate

    static constraints = {
	    modifiedBy(nullable:true)
	    modifiedDate(nullable:true)
	    createdBy(nullable:true)
	    createdDate(nullable:true)
	    remarks(nullable:true)
	    status(nullable:true)
    }
}
