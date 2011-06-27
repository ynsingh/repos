/**
 * @author avvp
 *
 */
class EvalItemNotificationMap 
{
	Notification notification;	
	EvalItem evalItem;
	char activeYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
    static constraints = 
    {
    	createdBy(nullable: true)
		modifiedBy(nullable: true)
    	createdDate(nullable: true)
		modifiedDate(nullable: true)
    }
}
