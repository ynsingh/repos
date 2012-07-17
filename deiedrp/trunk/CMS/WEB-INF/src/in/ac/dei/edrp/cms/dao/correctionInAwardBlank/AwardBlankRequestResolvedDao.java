/**
 * 
 */
package in.ac.dei.edrp.cms.dao.correctionInAwardBlank;

import java.util.List;
import java.util.StringTokenizer;
 
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramMasterInfoGetter;
import in.ac.dei.edrp.cms.domain.studentmarkssummary.StudentMarksSummaryBean;

/**
 * @author Ashish Mohan
 * @used to declare method to get and set data
 *
 */
public interface AwardBlankRequestResolvedDao {

	String solveRequestedIssue(StudentMarksSummaryBean input);
	
	List<StudentMarksSummaryBean> getStudentRequestData(StudentMarksSummaryBean input);
	
		//for collation of award blank
	List<AwardSheetInfoGetter> getCourseList(AwardSheetInfoGetter inputObj);

	String saveStudentMarks(AwardSheetInfoGetter inputObj, StringTokenizer data);

	List<AwardSheetInfoGetter> getStudentMarks(ProgramMasterInfoGetter inputObj);
}
