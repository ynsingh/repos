
class ExternalFundRefund {

ExternalFundAllocation externalFundAllocation
Date dateOfRefund 
double refundAmount
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
    }
}
