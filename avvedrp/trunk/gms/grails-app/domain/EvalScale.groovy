/**
 * @author avvp
 *
 */
class EvalScale
{
	Party party;
	String scaleTitle;
	char activeYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	
	static constraints = 
	{
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		
	}

}
