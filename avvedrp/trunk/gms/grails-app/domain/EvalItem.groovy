/**
 * @author avvp
 *
 */
class EvalItem
{
	EvalScale evalScale;
	Notification notification;	
	String item;
	char activeYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	
	static constraints = {
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		
    }
	
}
