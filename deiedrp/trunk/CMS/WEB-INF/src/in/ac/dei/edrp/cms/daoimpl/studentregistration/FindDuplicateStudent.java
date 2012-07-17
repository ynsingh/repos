package in.ac.dei.edrp.cms.daoimpl.studentregistration;

import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class FindDuplicateStudent {

	private TransactionTemplate transactionTemplate = null;
	private SqlMapClient sqlMapClient = null;
	static final Logger logger = Logger.getLogger(FindDuplicateStudent.class);

	List<StudentInfoGetter> totalStudentList = null;
	List<StudentInfoGetter> duplicateStudent = null;
	
	// actualRecords: It will contain total number of records which will be processed
	int actualRecords = 0;
	// processedStudent: Correctly processed student
	int processedStudent = 0;
	// recordsFailed: number of records which could not be processed
	int recordsFailed = 0;
	// rejectedValue: number of records which has some value missing at some
	// field/column in database
	// which will be used for further calculation.
	int rejectedValue = 0;
	// processFlag: If actualRecords==processedStudent, this flag will be true
	// and process is considered as completed.
	boolean processedFlag = false;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public FindDuplicateStudent() {
	}

	public FindDuplicateStudent(SqlMapClient sqlMapClient,
			TransactionTemplate transactionTemplate) {
		this.sqlMapClient = sqlMapClient;
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * @param activityMasterBeanObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public CountProcessRecorList getDuplicateStudents(
			final StartActivityBean startActivityBean) {

		CountProcessRecorList countList = new CountProcessRecorList();
		
		// i.e. it is being processed successfully or unsuccessfully
		final List<UnProcessedStduent> studentList = new ArrayList<UnProcessedStduent>();
		
		transactionTemplate.execute(new TransactionCallback() {
			Object savepoint = null;

			public String doInTransaction(TransactionStatus status) {

				try {
					//clean record for the current program course
					sqlMapClient.delete("checkDuplicateStudent.cleanDupliStuTable",  startActivityBean);
					
					// get the all new student
					totalStudentList = sqlMapClient.queryForList(
							"checkDuplicateStudent.getStudents",
							startActivityBean);

					// get the list of duplicate student.
					duplicateStudent = sqlMapClient.queryForList(
							"checkDuplicateStudent.duplicateStudentList",
							startActivityBean);

					for (StudentInfoGetter studentInfoGetter : duplicateStudent) {
//						studentInfoGetter.setProgramCourseKey(startActivityBean.getProgramCourseKey());
						studentInfoGetter.setCreatorId(startActivityBean.getUserId());
						StudentInfoGetter pckOfDuplicateStudent = (StudentInfoGetter) sqlMapClient.queryForObject("checkDuplicateStudent.getPCKOfDuplicateStudent",studentInfoGetter);
						studentInfoGetter.setProgramCourseKey(pckOfDuplicateStudent.getProgramCourseKey());

						// insert the duplicate student list into the
						// temp_duplicate_student table.
						sqlMapClient
								.insert(
										"checkDuplicateStudent.InsertDuplicateStudentList",
										studentInfoGetter);
						
						studentList.add(new UnProcessedStduent(studentInfoGetter.getRegistrationNumber(),"This student is registered on more than one program."));
						
					}

					HashSet<String> tmp = new HashSet<String>();
					String student = "";

					Iterator iter = duplicateStudent.iterator();
					while (iter.hasNext()) {
						StudentInfoGetter element = (StudentInfoGetter) iter
								.next();
						element.setProgramId(startActivityBean.getProgramId());
						element.setBranchId(startActivityBean.getBranchId());
						element.setSpecializationId(startActivityBean
								.getSpecializationId());
						element.setToSemester(startActivityBean
								.getSemesterCode());

						StudentInfoGetter duplicateStuOfProgram = (StudentInfoGetter) sqlMapClient
								.queryForObject("checkDuplicateStudent.duplicateStudentOnProgram",element);

						student = duplicateStuOfProgram.getFirstName()
								+ duplicateStuOfProgram.getMiddleName()
								+ duplicateStuOfProgram.getLastName()
								+ duplicateStuOfProgram.getFatherFirstName()
								+ duplicateStuOfProgram.getFatherMiddleName()
								+ duplicateStuOfProgram.getFatherLastName()
								+ duplicateStuOfProgram.getMotherFirstName()
								+ duplicateStuOfProgram.getMotherMiddleName()
								+ duplicateStuOfProgram.getMotherLastName()
								+ duplicateStuOfProgram.getCategory()
								+ duplicateStuOfProgram.getGender()
								+ duplicateStuOfProgram.getDateOfBirth();

						if (!tmp.contains(student)) {
							tmp.add(student);
							duplicateStuOfProgram.setCreatorId(startActivityBean.getUserId());
							StudentInfoGetter pckOfDuplicateStudent = (StudentInfoGetter) sqlMapClient.queryForObject("checkDuplicateStudent.getPCKOfDuplicateStudent",duplicateStuOfProgram);
							duplicateStuOfProgram.setProgramCourseKey(pckOfDuplicateStudent.getProgramCourseKey());

							// insert the duplicate student list into the
							// temp_duplicate_student table.
							sqlMapClient
									.insert(
											"checkDuplicateStudent.InsertDuplicateStudentList",
											duplicateStuOfProgram);
							rejectedValue++;
							studentList.add(new UnProcessedStduent(duplicateStuOfProgram.getRegistrationNumber(),"This student is registered on more than one program."));
						}
					}
				} catch (Exception e) {
					logger.info("Exception Check Duplicate Student " + e);
					status.rollbackToSavepoint(savepoint);
				} finally {
					actualRecords = totalStudentList.size();
					processedStudent = totalStudentList.size()-(duplicateStudent.size() + rejectedValue);
					rejectedValue = rejectedValue+duplicateStudent.size();					
					
					if (rejectedValue > 0) {
						processedFlag = false;
					} else {
						processedFlag = true;
					}
				}
				return null;
			}
		});

		countList = new CountProcessRecorList(actualRecords, processedStudent, rejectedValue, processedFlag, studentList);

		return countList;
	}
}
