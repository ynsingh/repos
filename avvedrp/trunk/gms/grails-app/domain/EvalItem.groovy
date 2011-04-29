/**
 * @author avvp
 *
 */
class EvalItem
{
	EvalScale evalScale;
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
