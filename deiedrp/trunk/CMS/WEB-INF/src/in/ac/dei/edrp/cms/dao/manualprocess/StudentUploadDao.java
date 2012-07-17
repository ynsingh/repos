package in.ac.dei.edrp.cms.dao.manualprocess;

import in.ac.dei.edrp.cms.domain.manualprocess.StudentUploadBean;

import java.util.List;

public interface StudentUploadDao {

	public String insertIntoStudentProgram(List<StudentUploadBean> studentRecordList, String path);
	public String insertIntoSRSH(List<StudentUploadBean> studentRecordList, String path);
	public String insertIntoStudentMarksSummary(List<StudentUploadBean> studentRecordList, String path);
	public String insertIntoStudentAggregate(List<StudentUploadBean> studentRecordList, String path);
	public String insertIntoStudentCourse(List<StudentUploadBean> studentCourseList, String path);
	public StudentUploadBean getPrgCourseKey(StudentUploadBean sub);
	public StudentUploadBean getSemesterDates(StudentUploadBean sub);
	public StudentUploadBean getFinalSemester(StudentUploadBean sub);
	public StudentUploadBean getDivisionFromCGPA(StudentUploadBean sub);
	public String finalSemesterEntry(List<StudentUploadBean> studentRecords, List<StudentUploadBean> studentRecords2,String path);
	public String firstSemesterEntry(List<StudentUploadBean> studentRecords, List<StudentUploadBean> studentRecords2, String path);
	public String midSemesterEntry(List<StudentUploadBean> studentRecords, List<StudentUploadBean> studentRecords2,String path);
	public String validateRecords(List<StudentUploadBean> studentRecords);
}
