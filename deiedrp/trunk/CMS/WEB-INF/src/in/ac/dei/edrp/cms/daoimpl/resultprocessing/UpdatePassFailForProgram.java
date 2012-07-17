package in.ac.dei.edrp.cms.daoimpl.resultprocessing;

import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessCourseList;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessForResultBean;
import in.ac.dei.edrp.cms.utility.PreviousSemesterDetail;

import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class UpdatePassFailForProgram {

	
	private SqlMapClient sqlMapClient;
	private TransactionTemplate transactionTemplate;
	private ResultProcessingUtility resultProcessingUtility=new ResultProcessingUtility(sqlMapClient,transactionTemplate);
	public UpdatePassFailForProgram(SqlMapClient sqlMapClient,
			TransactionTemplate transactionTemplate) {
		// TODO Auto-generated constructor stub
		this.sqlMapClient=sqlMapClient;
		this.transactionTemplate=transactionTemplate;
	}
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	//To take decision of PASS/FAIL
	public String updatePassFailForProgram(
			List<PreviousSemesterDetail> previousDetails,PreProcessForResultBean preProcessForResultBean,List<String> passSubjectList,
			List<String> failSubjectsList,StartActivityBean startActivityBean,int maxFailSubjects,int maxAttemptAllowed) {
		// TODO Auto-generated method stub
		String programStatus="XXXX";
		try{
//			int x=0;
//			int y=0;
//			int z=0;
//			int p=0;
//			double previousCGPAPoint=0.0;
//			double previousEanredCredit=0.0;
//			
//		PreProcessCourseList preProcessCourseList=new PreProcessCourseList(startActivityBean.getUniversityId(),startActivityBean.getProgramCourseKey(),
//				startActivityBean.getSemesterStartDate(),startActivityBean.getSemesterEndDate(),preProcessForResultBean.getRollNumber()
//				,startActivityBean.getUserId());
//		int previousRemedials=0;
//		
//			if(previousDetails.size()>0){
//				for(PreviousSemesterDetail previousSemesterDetail:previousDetails){
//					previousRemedials=previousRemedials+previousSemesterDetail.getNumberOfRemedials();
//					previousCGPAPoint=previousCGPAPoint+previousSemesterDetail.getPointEarnedCgpa();
//					previousEanredCredit=previousEanredCredit+previousSemesterDetail.getEarnedCreditCgpa();
//				}
//			}
//			
//			double cgpa=(preProcessForResultBean.getCgpaTheoryPointSecured()+preProcessForResultBean.getCgpaPracticalPointSecured()+previousCGPAPoint)
//			/(preProcessForResultBean.getEarnedTheorycgpaCredit()+preProcessForResultBean.getEarnedPracticalcgpaCredit()+previousEanredCredit);
//			
//			
//			if(failSubjectsList.size() +previousRemedials >0){
//				if( preProcessForResultBean.getAttemptNumber()== maxAttemptAllowed){
//					preProcessForResultBean.setProgramStatus(CRConstant.STATUS_FAIL);
//					x=sqlMapClient.update("preprocess.updateStudentProgramStatus", preProcessForResultBean);
//					
//				}//maxAttempt allowed if ends
//				else{
//					for(String courseCode:failSubjectsList){
//						preProcessCourseList.setCourseCode(courseCode);
//						preProcessCourseList.setStudentStatus(CRConstant.STATUS_FAIL);
//						z=sqlMapClient.update("preprocess.updateCourseStatus", preProcessCourseList);
//					}//Loop ends for fail courses
//					
//				//sqlMapClient.update("preprocess.updateSRSHStatus", preProcessForResultBean);
//				}//maximum attempt allowed else ends	
//				preProcessForResultBean.setSemesterStatus(CRConstant.STATUS_FAIL);
//			}else{
//			if( failSubjectsList.size()==0){
//				if(resultProcessingUtility.isFinalSemester(preProcessForResultBean)&&(failSubjectsList.size() +previousRemedials)==0){
//					preProcessForResultBean.setProgramStatus(CRConstant.STATUS_PASS);
//					y=sqlMapClient.update("preprocess.updateStudentProgramStatus", preProcessForResultBean);
//				}
//				else{
//					preProcessForResultBean.setCgpa(cgpa);
//					y=sqlMapClient.update("preprocess.updateStudentProgramStatus", preProcessForResultBean);
//				}
//					
//			}//count=0
//				
//			if(failSubjectsList.size()>0){
//				preProcessForResultBean.setSemesterStatus(CRConstant.STATUS_REMEDIAL);
//				//sqlMapClient.update("preprocess.updateSRSHStatus", preProcessForResultBean);
//					for(String courseCode:failSubjectsList){
//						preProcessCourseList.setCourseCode(courseCode);
//						preProcessCourseList.setStudentStatus(CRConstant.STATUS_REMEDIAL);
//						z=sqlMapClient.update("preprocess.updateCourseStatus", preProcessCourseList);
//					}//Loop ends for Remedial courses
//			}// count!=0 else ends
//			}
//			for(String courseCode:passSubjectList){
//				preProcessCourseList.setCourseCode(courseCode);
//				preProcessCourseList.setStudentStatus(CRConstant.STATUS_PASS);
//				x=sqlMapClient.update("preprocess.updateCourseStatus", preProcessCourseList);
//			}
//			if( z==failSubjectsList.size()||y==passSubjectList.size()){
//				if(x>0){
//				programStatus="PASS";
//				}
//			}
//			else{
//				return "XX";
//			}
			
		}catch(Exception e){
			System.out.println("Coming inside update program exception :"+e);
			
		}
		return null;
	}
}
