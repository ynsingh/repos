/**
 * @author avvp
 *
 */
class EvalScaleOptions
{
	EvalScale evalScale;
	String scaleOption;
	Integer scaleOptionIndex;
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
