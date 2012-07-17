package in.ac.dei.edrp.cms.dao.studentregistration;

import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.util.List;

public interface TempCoursePdfDao {
	public List<StudentInfoGetter> getStudentInfo(StartActivityBean startActivityBean);
	public List<StudentInfoGetter> getStudentCourses(StudentInfoGetter studentInfoGetter);	
	public List<StudentInfoGetter> getPersonalInfoForExtendedList(StudentInfoGetter studentInfoGetter);	
	public List<StudentInfoGetter> getPersonalInfoForMainList(StudentInfoGetter studentInfoGetter);
}
