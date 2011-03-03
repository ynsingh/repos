/**
 * @author ahis
 *
 */
public class EvalScore
{
		Proposal proposal;	
		Double totalScore;
		Integer noOfReviewers;
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
