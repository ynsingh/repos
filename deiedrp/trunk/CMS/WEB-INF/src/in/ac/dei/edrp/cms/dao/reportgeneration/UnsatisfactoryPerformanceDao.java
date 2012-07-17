package in.ac.dei.edrp.cms.dao.reportgeneration;

import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;
import java.util.List;
import in.ac.dei.edrp.cms.domain.university.UniversityMasterInfoGetter;


public interface UnsatisfactoryPerformanceDao {
	//to get information of student who are fail or has remedial in courses
	public List<StudentInfoGetter> getInfoForStudent(StudentInfoGetter studentInfoGetter);
	
	public List<StudentInfoGetter> getInfoOfStudentForMeritListCP(StudentInfoGetter studentInfoGetter);

	public List<StudentInfoGetter> getInfoOfStudentForMeritListGroup(StudentInfoGetter studentInfoGetter);
	
	public List<StudentInfoGetter> getListOfUnapprovedCourses(StudentInfoGetter studentInfoGetter);

	public List<UniversityMasterInfoGetter> getCourseGroup(StudentInfoGetter studentInfoGetter);
	
	//to get information of collation differences of award blank
	public List<StudentInfoGetter> getCollationDifferences(StudentInfoGetter input);
}
