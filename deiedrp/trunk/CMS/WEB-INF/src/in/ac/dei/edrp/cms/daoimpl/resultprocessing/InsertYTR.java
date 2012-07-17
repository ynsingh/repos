package in.ac.dei.edrp.cms.daoimpl.resultprocessing;

import java.sql.SQLException;
import java.util.List;

import javax.imageio.spi.RegisterableService;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.domain.registration.prestaging.MasterTransferBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.TransferNORInPSTBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessForResultBean;
import in.ac.dei.edrp.cms.domain.utility.StudentTracking;
import in.ac.dei.edrp.cms.utility.RegistrationFunction;
import in.ac.dei.edrp.cms.utility.StudentTrackingFunction;

import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class InsertYTR {

	private SqlMapClient sqlMapClient;
	private TransactionTemplate transactionTemplate;
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public InsertYTR(SqlMapClient sqlMapClient,TransactionTemplate transactionTemplate){
		this.sqlMapClient=sqlMapClient;
		this.transactionTemplate=transactionTemplate;
	}
	
	public void  insertYTRForPASS(PreProcessForResultBean preProcessForResultBean)throws MyException {
		
		
		//try {
		System.out.println("line 37 ytr in pass values as "+preProcessForResultBean.getProgramStatus()+preProcessForResultBean.getSemesterStatus());
		
		RegistrationFunction registrationFunction=new RegistrationFunction(sqlMapClient,transactionTemplate);
		StudentTrackingFunction studentTrackingFunction=new StudentTrackingFunction(sqlMapClient,transactionTemplate);
		
		System.out.println("amir khan checkpost1 for pass"+preProcessForResultBean.getAttemptNumber());
		int attemptNumber=preProcessForResultBean.getAttemptNumber();
		
			String entityId=preProcessForResultBean.getEntityId();
			String programId=preProcessForResultBean.getProgramId();
			String branchId=preProcessForResultBean.getBranchId();
			String specializationId=preProcessForResultBean.getSpecializationId();
			String semesterCode=preProcessForResultBean.getSemesterCode();
			String semesterStartDate=preProcessForResultBean.getSemesterStartDate();
			String semesterEndDate=preProcessForResultBean.getSemesterEndDate();
			
			String registerSemester="NA";
			boolean checkGroup=false;
			String registerStartDay="";
			String registerEndDay="";
			
			String registerSemesterStartDate="";
			String registerSemesterEndDate="";
			
			TransferNORInPSTBean userData = new TransferNORInPSTBean(entityId,
					programId, branchId, specializationId, semesterCode,
					semesterStartDate, semesterEndDate);

			// pick up previous semester code and group
			List<TransferNORInPSTBean> registerSemesterList;
			
				try {
					registerSemesterList = (List<TransferNORInPSTBean>) sqlMapClient
							.queryForList("TransferNORInPSTBean.getNextSemester",
									userData);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					throw 	new MyException(e1.getMessage());
					//e1.printStackTrace();
				}
				
				
			
			
			for (TransferNORInPSTBean registerSemesterCode : registerSemesterList) {
				registerSemester = registerSemesterCode.getNextSemesterCode();
				checkGroup = registerSemesterCode.getCheckSemesterGroup();
				registerStartDay = registerSemesterCode.getSemesterStartDate();
				registerEndDay = registerSemesterCode.getSemesterEndDate();
				// System.out.println(checkGroup+"="+registerStartDay+"="+registerEndDay);
			}
			
			TransferNORInPSTBean registerKeyData = new TransferNORInPSTBean(
					programId, branchId, specializationId, registerSemester);
			String registerProgramCourseKey = registrationFunction
					.getProgramCourseKey(registerKeyData);
			
			String sessionStartDate=preProcessForResultBean.getSessionStartDate();
			String sessionEndDate=preProcessForResultBean.getSessionEndDate();
			if (!checkGroup) {
				attemptNumber = 1;
				sessionStartDate=registrationFunction.getNextYear(preProcessForResultBean.getSessionStartDate());
				sessionEndDate=registrationFunction.getNextYear(preProcessForResultBean.getSessionEndDate());
			}
			TransferNORInPSTBean datesData = new TransferNORInPSTBean(
					registerProgramCourseKey, sessionStartDate,sessionEndDate);
			List<TransferNORInPSTBean> startEndDate;
		
				try {
					startEndDate = sqlMapClient.queryForList("TransferNORInPSTBean.getYTRDate", datesData);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					throw 	new MyException(e1.getSQLState());
					//e1.printStackTrace();
				}
			
			for (TransferNORInPSTBean registerdates : startEndDate) {
				registerSemesterStartDate = registerdates.getSemesterStartDate();
				registerSemesterEndDate = registerdates.getSemesterEndDate();
				
			}

			// Register due date for registration semester
			TransferNORInPSTBean registerDueDateData = new TransferNORInPSTBean(
					registerProgramCourseKey, registerSemesterStartDate,
					registerSemesterEndDate);
			String registerDueDate = registrationFunction
					.getRegisterDueDate(registerDueDateData);
			
			TransferNORInPSTBean insertIntoSRSH=new TransferNORInPSTBean();
			insertIntoSRSH.setEntityId(entityId);
			insertIntoSRSH.setRollNumber(preProcessForResultBean.getRollNumber());
			insertIntoSRSH.setNextProgramCourseKey(registerProgramCourseKey);
			insertIntoSRSH.setNextSemesterStartDate(registerSemesterStartDate);
			insertIntoSRSH.setNextSemesterEndDate(registerSemesterEndDate);
			insertIntoSRSH.setNextStatus(CRConstant.YET_TO_REGISTER);
			insertIntoSRSH.setRegisterDueDate(registerDueDate);
			insertIntoSRSH.setAttemptNumber(attemptNumber);
			insertIntoSRSH.setCreatorId(preProcessForResultBean.getUserId());
			System.out.println("amir khan checkpost2 for pass"+insertIntoSRSH.getAttemptNumber());
			
				try {
					sqlMapClient.insert(
							"TransferNORInPSTBean.insertSRSHWithYTR",
							insertIntoSRSH);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("message1"+ e.getSQLState());
					System.out.println("message2"+ e.getErrorCode());
					System.out.println("message3"+ e.getLocalizedMessage());
					System.out.println("message4"+ e.getMessage());
					
					throw 	new MyException(e.getMessage());
					//e.printStackTrace();
				}
			
			
			System.out.println("rohit check1"+CRConstant.YET_TO_REGISTER);
			studentTrackingFunction.insertStudentTracking(preProcessForResultBean.getEntityId(),
					preProcessForResultBean.getRollNumber(),
					registerProgramCourseKey,
					registerSemesterStartDate,
					registerSemesterEndDate,
					sessionStartDate,
					sessionEndDate,
					CRConstant.YET_TO_REGISTER,
					CRConstant.ACTIVE_STATUS, preProcessForResultBean
							.getUserId(), preProcessForResultBean
							.getActivityId(), preProcessForResultBean
							.getProcessId());
	
//	}catch (Exception e) {
//		// TODO Auto-generated catch block
//		throw 	new MyException("Error while writing YTR entry  in method insertYTRForPASS in  program InsertYTR.java ");
//	}
	}
	
	//YTR entry in case of FAIL
	public void insertYTRForFAIL(PreProcessForResultBean preProcessForResultBean)throws MyException {
		
		
		RegistrationFunction registrationFunction=new RegistrationFunction(sqlMapClient,transactionTemplate);
		StudentTrackingFunction studentTrackingFunction=new StudentTrackingFunction(sqlMapClient,transactionTemplate);
		System.out.println("amir khan checkpost1 for fail"+preProcessForResultBean.getAttemptNumber());
		int attemptNumber=preProcessForResultBean.getAttemptNumber();
		//try{
			String entityId=preProcessForResultBean.getEntityId();
			String programId=preProcessForResultBean.getProgramId();
			String branchId=preProcessForResultBean.getBranchId();
			String specializationId=preProcessForResultBean.getSpecializationId();
			String semesterCode=preProcessForResultBean.getSemesterCode();
			String semesterStartDate=preProcessForResultBean.getSemesterStartDate();
			String semesterEndDate=preProcessForResultBean.getSemesterEndDate();
			
			String registerSemester="NA";
			boolean checkGroup=false;
			String registerStartDay="";
			String registerEndDay="";
			
			String registerSemesterStartDate="";
			String registerSemesterEndDate="";
			
			TransferNORInPSTBean userData = new TransferNORInPSTBean(entityId,
					programId, branchId, specializationId, semesterCode,
					semesterStartDate, semesterEndDate);

			// pick up previous semester code and group
			// Get semester code for minimum semester sequence of user semester group
			TransferNORInPSTBean minSemesterData = new TransferNORInPSTBean(
					programId, semesterCode);
			String minRegisterSemesterCode = registrationFunction
					.getMinimumSemester(minSemesterData);
			
			// pick up program course key for minimum semester
			TransferNORInPSTBean minimumCourseKeyData = new TransferNORInPSTBean(
					programId, branchId, specializationId,
					minRegisterSemesterCode);
			String minProgramCourseKey = registrationFunction
					.getProgramCourseKey(minimumCourseKeyData);
			
			String sessionStartDate=registrationFunction.getNextYear(preProcessForResultBean.getSessionStartDate());
			String sessionEndDate=registrationFunction.getNextYear(preProcessForResultBean.getSessionEndDate());
			
			TransferNORInPSTBean datesData = new TransferNORInPSTBean(
					minProgramCourseKey,sessionStartDate,sessionEndDate);
			List<TransferNORInPSTBean> startEndDate;
			try {
				startEndDate = sqlMapClient.queryForList("TransferNORInPSTBean.getYTRDate", datesData);
			} catch (SQLException e1) {
				
				System.out.println("message1"+ e1.getSQLState());
				System.out.println("message2"+ e1.getErrorCode());
				System.out.println("message3"+ e1.getLocalizedMessage());
				System.out.println("message4"+ e1.getMessage());
				
				throw 	new MyException(e1.getMessage());
				// TODO Auto-generated catch block
				
			}
			for (TransferNORInPSTBean registerdates : startEndDate) {
				registerSemesterStartDate = registerdates.getSemesterStartDate();
				registerSemesterEndDate = registerdates.getSemesterEndDate();
				
			}

			// Register due date for registration semester
			TransferNORInPSTBean registerDueDateData = new TransferNORInPSTBean(
					minProgramCourseKey, registerSemesterStartDate,
					registerSemesterEndDate);
			String registerDueDate = registrationFunction
					.getRegisterDueDate(registerDueDateData);
			
			TransferNORInPSTBean insertIntoSRSH=new TransferNORInPSTBean();
			insertIntoSRSH.setEntityId(entityId);
			insertIntoSRSH.setRollNumber(preProcessForResultBean.getRollNumber());
			insertIntoSRSH.setNextProgramCourseKey(minProgramCourseKey);
			insertIntoSRSH.setNextSemesterStartDate(registerSemesterStartDate);
			insertIntoSRSH.setNextSemesterEndDate(registerSemesterEndDate);
			insertIntoSRSH.setNextStatus(CRConstant.YET_TO_REGISTER);
			insertIntoSRSH.setRegisterDueDate(registerDueDate);
			insertIntoSRSH.setAttemptNumber(attemptNumber+1);
			System.out.println("amir khan checkpost2 for fail"+insertIntoSRSH.getAttemptNumber());
			insertIntoSRSH.setCreatorId(preProcessForResultBean.getUserId());
			try {
				sqlMapClient.insert(
						"TransferNORInPSTBean.insertSRSHWithYTR",
						insertIntoSRSH);
			} catch (SQLException e) {
				System.out.println("message1"+ e.getSQLState());
				System.out.println("message2"+ e.getErrorCode());
				System.out.println("message3"+ e.getLocalizedMessage());
				System.out.println("message4"+ e.getMessage());
				
				throw 	new MyException(e.getMessage());
				// TODO Auto-generated catch block
				
			}
			
			studentTrackingFunction.insertStudentTracking(preProcessForResultBean.getEntityId(),
					preProcessForResultBean.getRollNumber(),
					minProgramCourseKey,
					registerSemesterStartDate,
					registerSemesterEndDate,
					sessionStartDate,
					sessionEndDate,
					CRConstant.YET_TO_REGISTER,
					CRConstant.ACTIVE_STATUS  , preProcessForResultBean
							.getUserId(), preProcessForResultBean
							.getActivityId(), preProcessForResultBean
							.getProcessId());

			
	
	}
}
	

