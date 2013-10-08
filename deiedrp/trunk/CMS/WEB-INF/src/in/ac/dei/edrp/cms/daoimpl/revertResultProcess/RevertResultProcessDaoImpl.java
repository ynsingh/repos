package in.ac.dei.edrp.cms.daoimpl.revertResultProcess;

import java.util.List;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.revertResultProcess.RevertResultProcessDao;
import in.ac.dei.edrp.cms.domain.revertResultProcess.RevertResultProcessBean;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


public class RevertResultProcessDaoImpl extends SqlMapClientDaoSupport
	implements RevertResultProcessDao {
	
	private static Logger logObj = Logger.getLogger(RevertResultProcessDaoImpl.class);

	TransactionTemplate transactionTemplate = null;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public List<RevertResultProcessBean> getEntity(RevertResultProcessBean input) {
		List<RevertResultProcessBean> entityList = null;
		try{
			entityList = getSqlMapClientTemplate()
								.queryForList("revertResultProcess.getEntities", input);
		}catch(Exception ex){
			logObj.info("Exception in getting Entities");
			logObj.error(ex);
		}
		return entityList;
	}

	public List<RevertResultProcessBean> getProgram(RevertResultProcessBean input) {
		List<RevertResultProcessBean> programList = null;
		try{
			programList = getSqlMapClientTemplate()
								.queryForList("revertResultProcess.getPrograms", input);
		}catch(Exception ex){
			logObj.info("Exception in getting Programs");
			logObj.error(ex);
		}
		return programList;
	}

	public List<RevertResultProcessBean> getBranch(RevertResultProcessBean input) {
		List<RevertResultProcessBean> branchList = null;
		try{
			branchList = getSqlMapClientTemplate()
								.queryForList("revertResultProcess.getBranches", input);
		}catch(Exception ex){
			logObj.info("Exception in getting Branches");
			logObj.error(ex);
		}
		return branchList;
	}

	public List<RevertResultProcessBean> getSpecialization(RevertResultProcessBean input) {
		List<RevertResultProcessBean> specialization = null;
		try{
			specialization = getSqlMapClientTemplate()
								.queryForList("revertResultProcess.getSpecializations", input);
		}catch(Exception ex){
			logObj.info("Exception in getting Specializations");
			logObj.error(ex);
		}
		return specialization;
	}

	public List<RevertResultProcessBean> getSemester(RevertResultProcessBean input) {
		List<RevertResultProcessBean> semsterList = null;
		try{
			semsterList = getSqlMapClientTemplate()
								.queryForList("revertResultProcess.getSemesters", input);
		}catch(Exception ex){
			logObj.info("Exception in getting Semesters");
			logObj.error(ex);
		}
		return semsterList;
	}

	public String revertProcess(final RevertResultProcessBean input) {
		String message = "failure";
		message=(String)transactionTemplate.execute(new TransactionCallback() {
		Object savepoint = null;
		String result="failure";
		
				public String doInTransaction(TransactionStatus status) {
					savepoint = status.createSavepoint();
					
						try{
							String finalSemester = "";
							RevertResultProcessBean inputObj = new RevertResultProcessBean();
							inputObj.setUniversityId(input.getUniversityId());
							inputObj.setSessionStartDate(input.getSessionStartDate());
							inputObj.setSessionEndDate(input.getSessionEndDate());
							inputObj.setEntityId(input.getEntityId());
							inputObj.setProgramId(input.getProgramId());
							inputObj.setBranchId(input.getBranchId());
							inputObj.setSpecializationId(input.getSpecializationId());
							inputObj.setSemesterId(input.getSemesterId());
							
							RevertResultProcessBean programCourseKeyObj = (RevertResultProcessBean) getSqlMapClientTemplate()
											.queryForObject("revertResultProcess.getProgramCourseKey", input);
							
							input.setProgramId(inputObj.getProgramId());
							input.setSemesterId(inputObj.getSemesterId());
							
							RevertResultProcessBean finalSemesterObj = (RevertResultProcessBean) getSqlMapClientTemplate()
											.queryForObject("revertResultProcess.getFinalSemesterFlag", input);
							
							finalSemester = finalSemesterObj.getFinalSemester();
							
							inputObj.setProgramCourseKey(programCourseKeyObj.getProgramCourseKey());
							inputObj.setSemesterStartDate(programCourseKeyObj.getSemesterStartDate());
							inputObj.setSemesterEndDate(programCourseKeyObj.getSemesterEndDate());
							
							input.setProgramCourseKey(inputObj.getProgramCourseKey());
							input.setEntityId(inputObj.getEntityId());
							input.setUniversityId(inputObj.getUniversityId());
							input.setSemesterStartDate(inputObj.getSemesterStartDate());
							input.setSemesterEndDate(inputObj.getSemesterEndDate());
							
							RevertResultProcessBean studentAggregateObj = (RevertResultProcessBean) getSqlMapClientTemplate()
											.queryForObject("revertResultProcess.getStudentAggegateCount", input);
							
							if(studentAggregateObj.getCount() > 0){
								
								getSqlMapClientTemplate().delete("revertResultProcess.deleteStudentAggregate", inputObj);
								
								input.setProgramId(inputObj.getProgramId());
								input.setSemesterId(inputObj.getSemesterId());
								
								RevertResultProcessBean groupLastSemesterObj = (RevertResultProcessBean) getSqlMapClientTemplate()
												.queryForObject("revertResultProcess.getGroupLastSemester", input);
								
								//Check Group's Last Semester
								if(inputObj.getSemesterId().equalsIgnoreCase(groupLastSemesterObj.getGroupLastSemester())){
									input.setSessionStartDate(inputObj.getSessionStartDate());
									input.setSessionEndDate(inputObj.getSessionEndDate());
									
									RevertResultProcessBean nextSessionObj = (RevertResultProcessBean)getSqlMapClientTemplate()
												.queryForObject("revertResultProcess.getNextSession", input);
									
									inputObj.setNextSessionStartDate(nextSessionObj.getNextSessionStartDate());
									inputObj.setNextSessionEndDate(nextSessionObj.getNextSessionEndDate());

									if(finalSemester.equalsIgnoreCase("F")){
										System.out.println("Processing Final Semester");
									}else{
										input.setProgramId(inputObj.getProgramId());
										input.setSemesterId(inputObj.getSemesterId());
										
										RevertResultProcessBean nextSemesterObj = (RevertResultProcessBean) getSqlMapClientTemplate()
													.queryForObject("revertResultProcess.getNextSemester", input);
										
										inputObj.setNextSemesterId(nextSemesterObj.getNextSemesterId());
										
										input.setUniversityId(inputObj.getUniversityId());
										input.setEntityId(inputObj.getEntityId());
										input.setProgramId(inputObj.getProgramId());
										input.setBranchId(inputObj.getBranchId());
										input.setSpecializationId(inputObj.getSpecializationId());
										input.setSemesterId(inputObj.getNextSemesterId());
										input.setSessionStartDate(inputObj.getNextSessionStartDate());
										input.setSessionEndDate(inputObj.getNextSessionEndDate());
										
										RevertResultProcessBean nextprogramCourseKeyObj = (RevertResultProcessBean) getSqlMapClientTemplate()
													.queryForObject("revertResultProcess.getProgramCourseKey", input);
										
										inputObj.setNextProgramCourseKey(nextprogramCourseKeyObj.getNextProgramCourseKey());
										inputObj.setNextSemesterStartDate(nextprogramCourseKeyObj.getNextSemesterStartDate());
										inputObj.setNextSemesterEndDate(nextprogramCourseKeyObj.getNextSemesterEndDate());
										
										getSqlMapClientTemplate().delete("revertResultProcess.deleteYTRFromNextSemester", inputObj);
									}
								}else{
									RevertResultProcessBean nextSemesterObj = (RevertResultProcessBean) getSqlMapClientTemplate()
												.queryForObject("revertResultProcess.getNextSemester", input);
						
									inputObj.setNextSemesterId(nextSemesterObj.getNextSemesterId());
									
									input.setUniversityId(inputObj.getUniversityId());
									input.setEntityId(inputObj.getEntityId());
									input.setProgramId(inputObj.getProgramId());
									input.setBranchId(inputObj.getBranchId());
									input.setSpecializationId(inputObj.getSpecializationId());
									input.setSemesterId(inputObj.getNextSemesterId());
									input.setSessionStartDate(inputObj.getSessionStartDate());
									input.setSessionEndDate(inputObj.getSessionEndDate());
									
									RevertResultProcessBean nextprogramCourseKeyObj = (RevertResultProcessBean) getSqlMapClientTemplate()
												.queryForObject("revertResultProcess.getProgramCourseKey", input);
						
									inputObj.setNextProgramCourseKey(nextprogramCourseKeyObj.getProgramCourseKey());
									inputObj.setNextSemesterStartDate(nextprogramCourseKeyObj.getSemesterStartDate());
									inputObj.setNextSemesterEndDate(nextprogramCourseKeyObj.getSemesterEndDate());
				
									getSqlMapClientTemplate().delete("revertResultProcess.deleteYTRFromNextSemester", inputObj);
								}
								getSqlMapClientTemplate().delete("revertResultProcess.deleteYTRFromSameSemester", inputObj);
								getSqlMapClientTemplate().update("revertResultProcess.updateSRSH", inputObj);
								getSqlMapClientTemplate().update("revertResultProcess.updateStudentCourse", inputObj);
								getSqlMapClientTemplate().update("revertResultProcess.updateActivityMaster", inputObj);
								
								if(finalSemester.equalsIgnoreCase("F")){
									getSqlMapClientTemplate().update("revertResultProcess.updateStudentProgram", inputObj);
								}
								result = "Reverted";
							}else{
								result = "Already Reverted";
							}
						}catch(Exception ex){
							logObj.info("Exception In Revert Method");
							logObj.error(ex);
							status.rollbackToSavepoint(savepoint);
							throw new MyException("Exception");
						}
						return result;
				}
		});
		return message;
	}

}