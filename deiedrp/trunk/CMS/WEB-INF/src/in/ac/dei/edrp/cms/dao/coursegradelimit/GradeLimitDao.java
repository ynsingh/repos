package in.ac.dei.edrp.cms.dao.coursegradelimit;
import in.ac.dei.edrp.cms.domain.coursegradelimit.*;

import java.util.List;
/**
 * This interface consist the list of methods used
 * in GradeLimitDaoImpl File.
 *
 * @author Ashish Mohan
 * @date 27 feb 2012
 * @version 1.0
 */
public interface GradeLimitDao {
    /**
     * Method retrieves list of courses for the concerned university
     * @param input Object of the referenced bean class
     * @return List
     */
	List<GradeLimitDomain> getCourseDetails(GradeLimitDomain input);
	
    /**
     * Method retrieves list of grade of specific course for the concerned university
     * @param input Object of the referenced bean class
     * @return List
     */
	List<GradeLimitDomain> getCourseGradeDetails(GradeLimitDomain input);
	
	 /**
     * Method retrieves list of grade and exam type for the concerned university
     * @param input Object of the referenced bean class
     * @return List
     */
	List<GradeLimitDomain> getExamDetails(GradeLimitDomain input);
	
	
	 /**
     * Method to insert course grade to database and map result to a jsp
     * @param input Object of the referenced bean class
     * @return String
     */
	String setCourseGradeDetails(GradeLimitDomain input);
	
	 /**
     * Method to delete course grade from database and map result to a jsp
     * @param input Object of the referenced bean class
     * @return String
     */
	String deleteCourseGradeDetails(GradeLimitDomain input);
 /**
     * Method retrieves list of approved Courses
     * @param input Object of the referenced bean class
     * @return List
     */
	List<GradeLimitDomain> getApprovedCourseCodes(GradeLimitDomain input);
	
	 /**
     * Method to update Course Code status to WDW
     * @param input Object of the referenced bean class
     * @author Mandeep
     * @return String
     */
	String CancelApprovedCourse(GradeLimitDomain input);
//method added for delay in Component Marks
	 /**
     * Method retrieves list of component detail
     * @param input Object of the referenced bean class
     * @return List
     */
	
	List<GradeLimitDomain> getAllComponentDetails(GradeLimitDomain input);
	 /**
     * Method retrieves list of Courses
     * @param input Object of the referenced bean class
     * @return List
     */
	List<GradeLimitDomain> getAllCourseCode(GradeLimitDomain input);
	 /**
     * Method retrieves list of Details of Courses
     * @param input Object of the referenced bean class
     * @return List
     */
	List<GradeLimitDomain> getAllCourseDetails(GradeLimitDomain input);
	
}
