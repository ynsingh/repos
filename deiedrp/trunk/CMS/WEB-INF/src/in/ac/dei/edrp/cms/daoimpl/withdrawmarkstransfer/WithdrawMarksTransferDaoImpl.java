package in.ac.dei.edrp.cms.daoimpl.withdrawmarkstransfer;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.withdrawmarkstransfer.WithdrawMarksTransferDao;
import in.ac.dei.edrp.cms.daoimpl.cancelfinalregistration.CancelDaoimpl;
import in.ac.dei.edrp.cms.domain.withdrawmarkstransfer.WithdrawMarksTransferGetter;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class WithdrawMarksTransferDaoImpl extends SqlMapClientDaoSupport
implements WithdrawMarksTransferDao {

	private static Logger logObj = Logger.getLogger(WithdrawMarksTransferDaoImpl.class);
	
	TransactionTemplate transactionTemplate = null;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	
	public List<WithdrawMarksTransferGetter> getDetails(
			WithdrawMarksTransferGetter input) {
		
		List<WithdrawMarksTransferGetter> detailsList=null;
		detailsList = getSqlMapClientTemplate().queryForList("withdrawMarksTransfer.getStudentDetails", input);
		return detailsList;
		}

	public List<WithdrawMarksTransferGetter> getSession(
			WithdrawMarksTransferGetter input) {
		
		List<WithdrawMarksTransferGetter> sessionList=null;
		sessionList = getSqlMapClientTemplate().queryForList("withdrawMarksTransfer.getRegisteringSession", input);
		return sessionList;
	}

	public String enableRegistration(final WithdrawMarksTransferGetter input) {
		
		String message = "failure";
		message=(String)transactionTemplate.execute(new TransactionCallback() {
		Object savepoint = null;
		String result="failure";
			
			public String doInTransaction(TransactionStatus status) {
				savepoint = status.createSavepoint();
			
					try{
							String CurrentSemester = input.getSemesterCode();
							WithdrawMarksTransferGetter inputObj = new WithdrawMarksTransferGetter();
							inputObj.setRollNumber(input.getRollNumber());
							inputObj.setEntityId(input.getEntityId());
							inputObj.setProgramId(input.getProgramId());
							inputObj.setBranchId(input.getBranchId());
							inputObj.setSpecializationId(input.getSpecializationId());
							inputObj.setSemesterCode(CurrentSemester);
							inputObj.setProgramCourseKey(input.getProgramCourseKey());
							inputObj.setSemesterStartDate(input.getSemesterStartDate());
							inputObj.setSemesterEndDate(input.getSemesterEndDate());
							inputObj.setSessionStartDate(input.getSessionStartDate());
							inputObj.setSessionEndDate(input.getSessionEndDate());
							inputObj.setAttemptNumber(input.getAttemptNumber());
							inputObj.setCreatorId(input.getCreatorId());
							List<WithdrawMarksTransferGetter> firstList = getSqlMapClientTemplate()
												.queryForList("withdrawMarksTransfer.getFirstSemester", input);
							System.out.println("First Semester of Program : " + firstList.get(0).getSemesterCode());
							input.setProgramId(inputObj.getProgramId());
							input.setSemesterCode(inputObj.getSemesterCode());
							List<WithdrawMarksTransferGetter> groupedSemestersList = getSqlMapClientTemplate()
												.queryForList("withdrawMarksTransfer.getGroupedSemesters", input);
							
							if(!CurrentSemester.equalsIgnoreCase(firstList.get(0).getSemesterCode())){
								input.setEntityId(inputObj.getEntityId());
								input.setProgramId(inputObj.getProgramId());
								input.setSessionStartDate(inputObj.getSessionStartDate());
								input.setSessionEndDate(inputObj.getSessionEndDate());
								input.setBranchId(inputObj.getBranchId());
								input.setSpecializationId(inputObj.getSpecializationId());
								input.setSemesterCode(groupedSemestersList.get(0).getSemesterCode());
								
								List<WithdrawMarksTransferGetter> probableList = getSqlMapClientTemplate()
												.queryForList("withdrawMarksTransfer.getProbableDates", input);
								
								inputObj.setProbableStartDate(probableList.get(0).getProbableStartDate());
								inputObj.setProbableEndDate(probableList.get(0).getProbableEndDate());
								inputObj.setProbableRegisterDate(probableList.get(0).getProbableStartDate());
								inputObj.setProbableSemester(groupedSemestersList.get(0).getSemesterCode());
							}
							
							input.setEntityId(inputObj.getEntityId());
							input.setProgramId(inputObj.getProgramId());
							input.setBranchId(inputObj.getBranchId());
							input.setSpecializationId(inputObj.getSpecializationId());
							input.setSemesterCode(CurrentSemester);
							input.setRollNumber(inputObj.getRollNumber());
							
							List<WithdrawMarksTransferGetter> personalInfo = getSqlMapClientTemplate()
												.queryForList("withdrawMarksTransfer.getPersonalInfo", input);
							
							inputObj.setStudentFirstName(personalInfo.get(0).getStudentFirstName());
							inputObj.setStudentMiddleName(personalInfo.get(0).getStudentMiddleName());
							inputObj.setStudentLastName(personalInfo.get(0).getStudentLastName());
							inputObj.setFatherFirstName(personalInfo.get(0).getFatherFirstName());
							inputObj.setFatherMiddleName(personalInfo.get(0).getFatherMiddleName());
							inputObj.setFatherLastName(personalInfo.get(0).getFatherLastName());
							inputObj.setMotherFirstName(personalInfo.get(0).getMotherFirstName());
							inputObj.setMotherMiddleName(personalInfo.get(0).getMotherMiddleName());
							inputObj.setMotherLastName(personalInfo.get(0).getMotherLastName());
							inputObj.setDateOfBirth(personalInfo.get(0).getDateOfBirth());
							inputObj.setCategory(personalInfo.get(0).getCategory());
							inputObj.setStudentId(personalInfo.get(0).getStudentId());
							inputObj.setEnrollmentNumber(personalInfo.get(0).getEnrollmentNumber());
							inputObj.setPrimaryMail(personalInfo.get(0).getPrimaryMail());
							inputObj.setGender(personalInfo.get(0).getGender());
							
							if(CurrentSemester.equalsIgnoreCase(groupedSemestersList.get(0).getSemesterCode())){
								if(CurrentSemester.equalsIgnoreCase(firstList.get(0).getSemesterCode())){
									getSqlMapClientTemplate().insert("withdrawMarksTransfer.setPrestaging", inputObj);
								}else{
									getSqlMapClientTemplate().insert("withdrawMarksTransfer.setPrestagingWithProbable", inputObj);
								}
							}else{
								
								input.setEntityId(inputObj.getEntityId());
								input.setProgramId(inputObj.getProgramId());
								input.setSessionStartDate(inputObj.getSessionStartDate());
								input.setSessionEndDate(inputObj.getSessionEndDate());
								input.setBranchId(inputObj.getBranchId());
								input.setSpecializationId(inputObj.getSpecializationId());
								input.setSemesterCode(groupedSemestersList.get(0).getSemesterCode());
								List<WithdrawMarksTransferGetter> regDates = getSqlMapClientTemplate()
													.queryForList("withdrawMarksTransfer.getDateForMarksTransfer", input);
								
								input.setProgramId(inputObj.getProgramId());
								input.setBranchId(inputObj.getBranchId());
								input.setSpecializationId(inputObj.getSpecializationId());
								input.setSemesterCode(groupedSemestersList.get(0).getSemesterCode());
								List<WithdrawMarksTransferGetter> previousKeyList = getSqlMapClientTemplate()
													.queryForList("withdrawMarksTransfer.getPreviousKey", input);
								String previousKey = previousKeyList.get(0).getPreviousKey();
								
								inputObj.setPreviousKey(previousKey);
								inputObj.setStartDate(regDates.get(0).getStartDate());
								inputObj.setEndDate(regDates.get(0).getEndDate());
								
								getSqlMapClientTemplate().insert("withdrawMarksTransfer.setSRSH", inputObj);
								getSqlMapClientTemplate().insert("withdrawMarksTransfer.setStudentCourse", inputObj);
								getSqlMapClientTemplate().insert("withdrawMarksTransfer.setStudentMarksSummary", inputObj);
								getSqlMapClientTemplate().insert("withdrawMarksTransfer.setStudentAggregate", inputObj);
								getSqlMapClientTemplate().insert("withdrawMarksTransfer.setPrestagingWithProbable", inputObj);
								getSqlMapClientTemplate().update("withdrawMarksTransfer.updateStatusInStudentCourse", inputObj);
							}
							
							getSqlMapClientTemplate().insert("withdrawMarksTransfer.insertYTR", inputObj);
							getSqlMapClientTemplate().update("withdrawMarksTransfer.updateSRSH", inputObj);
							result = "success";
							
					}catch(Exception ex){
						logObj.error(ex);
						ex.printStackTrace();
						status.rollbackToSavepoint(savepoint);
						throw new MyException("E");
					}
					return result;
				}
			});
			return message;
	}


	public List<WithdrawMarksTransferGetter> getRegistrationDates(
			WithdrawMarksTransferGetter input) {
		
		List<WithdrawMarksTransferGetter> dateList=null;
		dateList = getSqlMapClientTemplate().queryForList("withdrawMarksTransfer.getRegistrationDates", input);
		return dateList;
	}


}
