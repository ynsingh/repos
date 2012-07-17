package in.ac.dei.edrp.cms.daoimpl.reportgeneration;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import in.ac.dei.edrp.cms.dao.reportgeneration.UnsatisfactoryPerformanceDao;


import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;
import in.ac.dei.edrp.cms.domain.university.UniversityMasterInfoGetter;
/**
 * This file consist of the methods used for getting 
 * the student info
 * @author Ashish Mohan
 * @date 13 Dec 2011
 * @version 1.0
 */

public class UnsatisfactoryPerformanceImpl extends SqlMapClientDaoSupport implements UnsatisfactoryPerformanceDao {

	public List<StudentInfoGetter> getInfoForStudent(StudentInfoGetter studentInfoGetter) {
		List<StudentInfoGetter> studentInfo=null;
		List<StudentInfoGetter> remedialList;
		List<StudentInfoGetter> checkList = new ArrayList<StudentInfoGetter>();	

			studentInfo = getSqlMapClientTemplate().queryForList("unsatisfactory.getPersonalInfoForPerformance",studentInfoGetter);	
			System.out.println("no of student"+studentInfo.size());
			
			for(StudentInfoGetter student : studentInfo){
				remedialList = new ArrayList<StudentInfoGetter>();
				//for the remedial courses
				remedialList = getSqlMapClientTemplate().queryForList("unsatisfactory.getRemedialCourses",student);
				checkList.add(new StudentInfoGetter(student.getProgramName(),
							student.getProgramCode(),student.getBranchId(),student.getRollNumber(),student.getStudentName(),student.getEnrollmentNumber(),String.valueOf(student.getAttemptNumber()),student.getSpecializationId(),student.getStatus(),student.getSemesterSequence(),remedialList));
				}
				
			return checkList;
	}
	
	public List<StudentInfoGetter> getInfoOfStudentForMeritListCP(StudentInfoGetter studentInfoGetter) {
		List<StudentInfoGetter> studentInfo=null;
		
			studentInfo = getSqlMapClientTemplate().queryForList("unsatisfactory.getPersonalInfoForMeritCP",studentInfoGetter);	
			System.out.println("no of student"+studentInfo.size());
		return studentInfo;
	}
	
	public List<StudentInfoGetter> getInfoOfStudentForMeritListGroup(StudentInfoGetter studentInfoGetter) {
		List<StudentInfoGetter> studentInfo=null;
		
			studentInfo = getSqlMapClientTemplate().queryForList("unsatisfactory.getPersonalInfoForMeritGroup",studentInfoGetter);	
			System.out.println("no of student"+studentInfo.size());
			
		return studentInfo;
	}
	
	public List<UniversityMasterInfoGetter> getCourseGroup(StudentInfoGetter studentInfoGetter) {
		List<UniversityMasterInfoGetter> studentInfo= getSqlMapClientTemplate().queryForList("unsatisfactory.getGroup",studentInfoGetter);	
		return studentInfo;
	}
	
	public List<StudentInfoGetter> getListOfUnapprovedCourses(StudentInfoGetter studentInfoGetter) {
		
		List<StudentInfoGetter> studentInfo=null;
		try{
			if(studentInfoGetter.getFalg()=="data"){
				studentInfo = getSqlMapClientTemplate().queryForList("unsatisfactory.getDataForInsert",studentInfoGetter);	
			}
			if(studentInfoGetter.getFalg()=="delete"){
				getSqlMapClientTemplate().delete("unsatisfactory.deleteFromTable",studentInfoGetter);
			}
			if(studentInfoGetter.getFalg()=="insert"){
				getSqlMapClientTemplate().insert("unsatisfactory.insertInTable",studentInfoGetter);	
			}
			else if(studentInfoGetter.getFalg()=="entity"){
				studentInfo = getSqlMapClientTemplate().queryForList("unsatisfactory.getUniqueEntity",studentInfoGetter);
			}
			else if(studentInfoGetter.getFalg()=="program"){
				studentInfo=getSqlMapClientTemplate().queryForList("unsatisfactory.getUniqueProgram",studentInfoGetter);
			}
			else if(studentInfoGetter.getFalg()=="course"){
				studentInfo = getSqlMapClientTemplate().queryForList("unsatisfactory.getDataForReportEntityProgWise",studentInfoGetter);
			}
		}
		catch(Exception e){
			
		}
			
		return studentInfo;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<StudentInfoGetter> getCollationDifferences(
			StudentInfoGetter input) {
		List<StudentInfoGetter> studentInfo = getSqlMapClientTemplate().queryForList("unsatisfactory.getCollationDifferences",input);
		return studentInfo;
	}
}
