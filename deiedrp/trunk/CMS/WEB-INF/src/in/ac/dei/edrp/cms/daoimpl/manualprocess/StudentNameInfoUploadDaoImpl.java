package in.ac.dei.edrp.cms.daoimpl.manualprocess;

import java.util.List;

import in.ac.dei.edrp.cms.dao.manualprocess.StudentNameInfoUploadDao;
import in.ac.dei.edrp.cms.domain.manualprocess.StudentNameInfoUploadBean;
import in.ac.dei.edrp.cms.domain.manualprocess.StudentUploadBean;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class StudentNameInfoUploadDaoImpl extends SqlMapClientDaoSupport implements StudentNameInfoUploadDao {
	/** Creating object of TransactionTemplate for transaction Management */
	TransactionTemplate transactionTemplate = null;
	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(StudentNameInfoUploadDaoImpl.class);

	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	/**
	 * This method is called from the controller for the mid semester(neither first nor final semester) records entry
	 * @param list of objects of StudentUploadBean containing course details of a particular student
	 * @param list of objects of StudentUploadBean containing student records
	 * @return String containing info whether the records are inserted successfully
	 */
	public String uploadStudentNameInfo(final StudentNameInfoUploadBean upload) {
//			String s = (String) transactionTemplate.execute(new TransactionCallback() {
//				public String doInTransaction(TransactionStatus tStatus) {
//					System.out.println("before save point declaration");
//					int totalRecords = studentDetails.size();
//					int successUpload = 0;
//					int failUpload = 0;
//					Object savepoint = null;
					String status="false";
					int result=0;
//					try {						
						//*******************************************
//						for(StudentNameInfoUploadBean upload:studentDetails){
//							savepoint = tStatus.createSavepoint();
							System.out.println("enrollment number "+upload.getEnrollmentNumber());
							System.out.println("entity + father name _ mother name "+upload.getEntityId()+" : "+upload.getFatherNameEnglish()+" : "+upload.getMotherNameEnglish());
							try{
								result = updateStudentMaster(upload);
								if(result==1){
									if(updateEnrollmentDetail(upload)==1)
										return "true";						
								}								
							}catch(Exception ex){
								System.out.println("inside inner exception in upload student name info"+upload.getEnrollmentNumber());
								logObj.error("Exception in upload student name info", ex.getCause());
//								tStatus.rollbackToSavepoint(savepoint);
//								failUpload = failUpload +1;
								return status;
							}//end catch
//						}//end for
//						System.out.println("total recordes :"+totalRecords+" success records :"+successUpload);
//					} catch (Exception ex) {
//						System.out.println("inside inner exception in mid semester entry sql"+ex);
//						logObj.error("Exception in mid semester entry", ex.getCause());
////						tStatus.rollbackToSavepoint(savepoint);
//						status = "false";
//						return status;
//					}
							System.out.println("status is : "+status);
					return status;
				}
//			});
//			return s;
//	}

	/**
	 * This method add student Detail into student mark summary table
	 * @param list of objects of StudentUploadBean
	 * @return String containing info(true/false) whether a record is inserted or not
	 */
	public int updateStudentMaster(StudentNameInfoUploadBean studentRecord) {		
		int result;
		try{
			result = getSqlMapClientTemplate().update("studentUpload.updateStudentMaster", studentRecord);
			System.out.println("result after student_master "+result);	
			}catch(Exception ex) {
				logObj.info("Exception in updateStudentMaster : "+studentRecord.getEnrollmentNumber());
//				insertIntoErrorLog(upload,ex, path);				
				result=0;
			}							
		return result;
	}
	/**
	 * This method add student Detail into student mark summary table
	 * @param list of objects of StudentUploadBean
	 * @return String containing info(true/false) whether a record is inserted or not
	 */
	public int updateEnrollmentDetail(StudentNameInfoUploadBean studentRecord) {		
		int result;
		try{			
			result = getSqlMapClientTemplate().update("studentUpload.updateEnrollmentPersonalDetails", studentRecord);
			System.out.println("result after enrollment personal details "+result);	
			}catch(Exception ex) {
				logObj.info("Exception in updateEnrollmentDetail : "+studentRecord.getEnrollmentNumber());
//				insertIntoErrorLog(upload,ex, path);				
				result=0;
			}					
//		}
			return result;
	}
}
