/**
 * @author avvp
 *
 */
class EvalAnswer
{
	EvalItem evalItem;
	EvalScaleOptions evalScaleOptions;
	Person person;
	Proposal proposal;
	String Remarks;
	char activeYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	static constraints = 
	{
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		evalScaleOptions(nullable: true)
	}
}
