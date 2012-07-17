package in.ac.dei.edrp.cms.daoimpl.resultprocessing;

import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramMasterInfoGetter;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessCourseList;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessForResultBean;
import in.ac.dei.edrp.cms.utility.PreviousSemesterDetail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProcessStudentMarks {
	
	PreProcessForResult preProcessForResult=new PreProcessForResult();
	private SqlMapClient sqlMapClient;
	private TransactionTemplate transactionTemplate;
	
	public ProcessStudentMarks(SqlMapClient sqlMapClient,
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
	
	/******************************************************
	 * Computation according to Marks system
	 *****************************************************/
	
	public boolean processStudentMarks(
			PreProcessForResultBean preProcessForResultBean,
			PreProcessForResultBean listOfStudent,PreProcessCourseList courseListData,StartActivityBean startActivityBean) {
		// TODO Auto-generated method stub
		ResultProcessingUtility resultProcessingUtility=new ResultProcessingUtility(sqlMapClient,transactionTemplate);
		UpdatePassFailForProgram updatePassFail=new UpdatePassFailForProgram(sqlMapClient,transactionTemplate);
		List<String> failSubjectsList=new ArrayList<String>();
		int x=0,y=0,z=0;
		try{
			System.out.println("Roll number of student is: "+listOfStudent.getRollNumber());
			
			preProcessForResultBean.setRollNumber(listOfStudent.getRollNumber());
			preProcessForResultBean.setUserId(startActivityBean.getUserId());
			//Fail subject list
			
			
			//Pass subject list
			List<String> passSubjectsList=new ArrayList<String>();
			
			//setting roll number of a student with theory data 				
			courseListData.setRollNumber(listOfStudent.getRollNumber());
			courseListData.setUserId(startActivityBean.getUserId());
			//courseListTheoryData.setMinPassMarks(70);

			//Update Student's Marks: Total Marks = Internal Marks + External Marks: If internal_external_flag=True
			x=sqlMapClient.update("preprocess.updateStudentMarksSummary",courseListData);
			//Update Student's Marks: Total Marks = Internal Marks + External Marks: If internal_external_flag=False
			y=sqlMapClient.update("preprocess.updateStudentMarksSummaryForInternal",courseListData);
			//Update Eanred Credits for Marks System
			z=sqlMapClient.update("preprocess.updateEarnedCreditsForMarksSystem",courseListData);
			
			courseListData.setStudentStatus("ACT");
			
			List<PreProcessCourseList> courseList=sqlMapClient.queryForList("preprocess.getListOfCourseForPRE", courseListData);
		
			System.out.println("List of courses: "+courseList.size()+ "for " +courseListData.getRollNumber());
			double theoryCredits=0.0;
			double practicalCredits=0.0;
			
			double theoryAggregate=0;
			
			double practicalAggregate=0.0;
			
			double totalCredits=0.0;
			
			double sgpa=0.0;
			double sgpaTheory=0.0;
			double sgpaPractical=0.0;
			
			double cgpa=0.0;
			double cgpaPoint=0.0;
			
			//Total evaluation for theoratical marks and theory
			double totalEvaluationForTheory=0.0;
			double totalEvaluationForPractical=0.0;
			
			double courseWeightedPercentage=0.0;
			
						
			double earnedCreditInSemester=0.0;
			double earnedCreditForCGPA=0.0;
			double earnedCreditsExcludingAudit=0.0;
			
			for(PreProcessCourseList course:courseList){
				
				courseListData.setCourseCode(course.getCourseCode());
				
				double totalEvaluationMarks=course.getTotalMarks();
				
				System.out.println("Roll number:"+listOfStudent.getRollNumber()+"Theory Course Code"+course.getCourseCode()+"Obtained Marks="+course.getObtainedMarks()+"TM="+course.getTotalMarks()+"Result System="+course.getResultSystem()+"CR="+course.getCredits());					
				
				char courseClassification=resultProcessingUtility.getClassification(course.getCourseClassification());
				
				/******************************************************
				 * Computation according to marks system
				 *****************************************************/
//				if(resultSystem.equalsIgnoreCase("MK")){
					courseWeightedPercentage= (course.getObtainedMarks()/ course.getTotalMarks())*course.getEarnedCredits();
					switch (courseClassification){
					
					case 'T':{
						if(course.getObtainedMarks()<courseListData.getMinPassMarks()){
							//count++;
							System.out.println("Coming imside fail marks: "+course.getCourseCode());
							failSubjectsList.add(course.getCourseCode());
							
						}
						else{
							theoryCredits=theoryCredits+course.getCredits();
							passSubjectsList.add(course.getCourseCode());
							courseListData.setCourseStatus(CRConstant.STATUS_PASS);
							//update status by PASS here
							sqlMapClient.update("preprocess.updateCourseStatus",courseListData);
							theoryAggregate=theoryAggregate+(courseWeightedPercentage);
							earnedCreditInSemester=earnedCreditInSemester+course.getCredits();
						}
				totalEvaluationForTheory=totalEvaluationForTheory+course.getCredits();	
				break;
					}//Case for Theory and practical ends.
					
					
					case 'P':{
						if(course.getObtainedMarks()<courseListData.getMinPassMarks()){
							//count++;
							System.out.println("Coming inside fail marks: "+course.getCourseCode());
							failSubjectsList.add(course.getCourseCode());
							
						}
						else{
							practicalCredits=practicalCredits+course.getCredits();
							passSubjectsList.add(course.getCourseCode());
							courseListData.setCourseStatus(CRConstant.STATUS_PASS);
							//update status by PASS here
							sqlMapClient.update("preprocess.updateCourseStatus",courseListData);
							theoryAggregate=theoryAggregate+(courseWeightedPercentage);
							earnedCreditInSemester=earnedCreditInSemester+course.getCredits();
						}
				totalEvaluationForPractical=totalEvaluationForPractical+course.getCredits();	
				
				break;
					}//Case for Practical ends
					
					case 'X':{
						//This block should never be executed
						//Invalid course, make entry into Student_Error_Logs
						//	break;
						}
						//Make entry into Student_Error_Log
						//break;
					
					}//switch ends
					
				}//Loop ends
			/**********************************************************
			 * PASS/FAIL DECISION CODE
			 **********************************************************/
			preProcessForResultBean.setRollNumber(listOfStudent.getRollNumber());
			preProcessForResultBean.setAttemptNumber(listOfStudent.getAttemptNumber());
			ProgramMasterInfoGetter programMaster=new ProgramMasterInfoGetter();
			programMaster.setProgramId(startActivityBean.getProgramId());
			ProgramMasterInfoGetter maxDetails=resultProcessingUtility.getMaxAttmptAllowed(programMaster);
			int maxFailSubjects=Integer.parseInt(maxDetails.getMaxNumberOfFailSubjects());
			int maxAttemptAllowed=Integer.parseInt(maxDetails.getNumberOfAttemptAllowed());
			
			PreviousSemesterDetail previousSemesterDetail=new PreviousSemesterDetail(listOfStudent.getRollNumber(),startActivityBean.getEntityId(),
					startActivityBean.getProgramCourseKey());
			List<PreviousSemesterDetail> previousDetails=resultProcessingUtility.getPreviousSemesterDetailForMarksSystem(previousSemesterDetail);
//			List<PreviousSemesterDetail> previousDetails=getPreviousSemesterDetailForGradeSystem(previousSemesterDetail);
			//count=failSubjectsList.size();
			
				//Set theory and practical percentage and credits according to roll number in STUDENT_AGGREGATE
//			updatePassFail.updatePassFailForProgram(previousDetails,preProcessForResultBean,passSubjectsList,failSubjectsList,startActivityBean,
//					maxFailSubjects,maxAttemptAllowed,(theoryAggregate+practicalAggregate)/(totalCredits)*(previousDetails.size()));
		
							
			preProcessForResultBean.setAggregateTheory(resultProcessingUtility.changeNumber(theoryAggregate,totalEvaluationForTheory));
			preProcessForResultBean.setAggregatePractical(resultProcessingUtility.changeNumber(practicalAggregate,totalEvaluationForPractical));
			preProcessForResultBean.setWeightedPercentage(resultProcessingUtility.changeNumber((theoryAggregate+practicalAggregate),(totalEvaluationForTheory+totalEvaluationForPractical)));
			preProcessForResultBean.setTheoryCredit(theoryCredits);
			preProcessForResultBean.setPracticalCredit(practicalCredits);
			
			preProcessForResultBean.setNumberOfRemedials(failSubjectsList.size());
			
			preProcessForResultBean.setRemarks("Computed Successfully");
			
				
			sqlMapClient.update("preprocess.updateSRSHResultStatus", preProcessForResultBean);
			
			sqlMapClient.update("preprocess.insertStudentAggregate", preProcessForResultBean);
			return true;
		}catch(Exception e){
			System.out.println("Coming inside exception "+e.getMessage());
			return false;
		}
		
	}

}
