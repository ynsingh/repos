package in.ac.dei.edrp.cms.daoimpl.resultprocessing;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramMasterInfoGetter;
import in.ac.dei.edrp.cms.domain.registration.prestaging.TransferNORInPSTBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessCourseList;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessForResultBean;
import in.ac.dei.edrp.cms.domain.utility.ErrorLogs;
import in.ac.dei.edrp.cms.domain.utility.StudentTracking;
import in.ac.dei.edrp.cms.utility.PreviousSemesterDetail;
import in.ac.dei.edrp.cms.utility.RegistrationFunction;
import in.ac.dei.edrp.cms.utility.StudentTrackingFunction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProcessStudentGrade {

	private SqlMapClient sqlMapClient;
	private TransactionTemplate transactionTemplate;
	public void setSqlMapClient(SqlMapClient sqlMapClient) {	
		this.sqlMapClient = sqlMapClient;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public ProcessStudentGrade(SqlMapClient sqlMapClient,TransactionTemplate transactionTemplate){
		this.sqlMapClient=sqlMapClient;
		this.transactionTemplate=transactionTemplate;
	}

	public  void processStudentGrade(
			PreProcessForResultBean preProcessForResultBean,
			PreProcessForResultBean listOfStudent,PreProcessCourseList courseListData,StartActivityBean startActivityBean) throws Exception {
		// TODO Auto-generated method stub
		//Setting roll number and user id in preProcessResultBean object
		String semesterCode=preProcessForResultBean.getSemesterCode();
		ResultProcessingUtility resultProcessingUtility=new ResultProcessingUtility(sqlMapClient,transactionTemplate);
		UpdatePassFailForProgram updatePassFail=new UpdatePassFailForProgram(sqlMapClient,transactionTemplate);
		preProcessForResultBean.setRollNumber(listOfStudent.getRollNumber());
		preProcessForResultBean.setUserId(startActivityBean.getUserId());
		//Fail subject list
		List<String> failSubjectsList=new ArrayList<String>();

		//Pass subject list
		List<String> passSubjectsList=new ArrayList<String>();


		
			courseListData.setRollNumber(listOfStudent.getRollNumber());
			courseListData.setUserId(startActivityBean.getUserId());
			//Update Student's Marks: Total Marks = Internal Marks + External Marks: If internal_external_flag=true
			sqlMapClient.update("preprocess.updateStudentMarksSummary",courseListData);
			//Update Student's Marks: Total Marks = Internal Marks + External Marks: If internal_external_flag=False
			sqlMapClient.update("preprocess.updateStudentMarksSummaryForInternal",courseListData);

			sqlMapClient.update("preprocess.updateStudentMarksSummaryGradePoint",courseListData);
			//Update Student's Marks: Final Grade Point = Internal Grade + External Grade: If internal_external_flag=False
			sqlMapClient.update("preprocess.updateStudentMarksSummaryGradePointForInternal",courseListData);

			//Update if course type != Audit/S/Z grades, final grade will be compared with Min Passing grade
			sqlMapClient.update("preprocess.updateEarnedCredits",courseListData);
			//Update if course type = Audit/S/Z grades, Internal grade should be PASSING Grade
			sqlMapClient.update("preprocess.updateEarnedCreditsForAudit",courseListData);

			courseListData.setStudentStatus("REG");
			List<PreProcessCourseList> courseList=sqlMapClient.queryForList("preprocess.getListOfCourseForPRE", courseListData);
			System.out.println("Course List size for "+listOfStudent.getRollNumber()+" and: "+courseList.size());
			double theoryEarnedCredit=0.0;
			double practicalEarnedCredit=0.0;
			double sgpaTheoryPoint=0.0;
			double sgpaPracticalPoint=0.0;
			double cgpaTheoryPoint=0.0;
			double cgpaPracticalPoint=0.0;
			double cgpaEarnedTheroyCredit=0.0;
			double cgpaEarnedPracticalCredit=0.0;
			double earnedTheoryAudCredit=0.0;
			double earnedPracticalAudCredit=0.0;
			double earnedCreditsInSemester=0.0;
			double sgpa=0.0;
			int i=0;
			for(PreProcessCourseList course:courseList){
				i++;		
				char courseClassification=resultProcessingUtility.getClassification(course.getCourseClassification());
				char courseType=resultProcessingUtility.isAuditCourse(course.getCourseType());
				//Earned Credits in semester including audit courses
				earnedCreditsInSemester=earnedCreditsInSemester+course.getEarnedCredits();

				switch (courseType){
				//M: Regular courses
				case 'M':{

					switch (courseClassification){
					case 'T':{
						if(course.getEarnedCredits()==course.getCredits()){
							//CGPA Point secured in pass courses excluding audit course
							cgpaTheoryPoint=cgpaTheoryPoint+(course.getEarnedCredits()*course.getObtainedGrade());
							cgpaEarnedTheroyCredit=cgpaEarnedTheroyCredit+course.getEarnedCredits();
							theoryEarnedCredit=theoryEarnedCredit+course.getEarnedCredits();
							System.out.println("adding course in pass"+i);
							passSubjectsList.add(course.getCourseCode());
						}
						else{
							failSubjectsList.add(course.getCourseCode());
							System.out.println("adding course in fail"+i);
						}//earned grade point excluding Audit and S/Z grade course in Theory subjects
						sgpaTheoryPoint=sgpaTheoryPoint+(course.getObtainedGrade()*course.getCredits());
						break;
					}
					case 'P':{
						if(course.getEarnedCredits()==course.getCredits()){
							//CGPA Point secured in pass courses excluding audit course
							cgpaPracticalPoint=cgpaPracticalPoint+(course.getEarnedCredits()*course.getObtainedGrade());
							cgpaEarnedPracticalCredit=cgpaEarnedPracticalCredit+course.getCredits();
							practicalEarnedCredit=practicalEarnedCredit+course.getEarnedCredits();
							passSubjectsList.add(course.getCourseCode());
							System.out.println("adding course in pass"+i+"CGPA"+cgpaEarnedPracticalCredit);
						}
						else{
							failSubjectsList.add(course.getCourseCode());
							System.out.println("adding course in fail"+i);
						}//earned grade point excluding Audit and S/Z grade course in Theory subjects
						sgpaPracticalPoint=sgpaPracticalPoint+(course.getObtainedGrade()*course.getCredits());
						break;
					}
					}
					break;
				}

				//A: Means all course which are not REG like Audit,S/Z grade
				case 'A':{
					System.out.println("Audit Course Found");
					switch (courseClassification){
					case 'T':{
						if(course.getEarnedCredits()==course.getCredits()){
							//CGPA Point secured in pass courses excluding audit course
							earnedTheoryAudCredit=earnedTheoryAudCredit+course.getEarnedCredits();
							theoryEarnedCredit=theoryEarnedCredit+course.getEarnedCredits();
							passSubjectsList.add(course.getCourseCode());
							System.out.println("adding course in pass"+i);
						}
						else{
							failSubjectsList.add(course.getCourseCode());
							System.out.println("adding course in fail"+i);
						}//earned grade point excluding Audit and S/Z grade course in Theory subjects
						break;
					}
					case 'P':{
						//earned Practical Credits
						if(course.getEarnedCredits()==course.getCredits()){
							//CGPA Point secured in pass courses excluding audit course
							earnedPracticalAudCredit=earnedPracticalAudCredit+course.getEarnedCredits();
							practicalEarnedCredit=practicalEarnedCredit+course.getEarnedCredits();
							passSubjectsList.add(course.getCourseCode());
							System.out.println("adding course in pass"+i);
						}
						else{
							failSubjectsList.add(course.getCourseCode());
							System.out.println("adding course in fail"+i);
						}//earned grade point excluding Audit and S/Z grade course in Theory subjects
						break;
					}
					}
					break;
				}

				//If course niether M or nor A: Basically this block should never be executed
				case 'X':{
					//This block should never be executed
					break;
				}

				}


			}//Loop of course List ends

			sgpa=(sgpaTheoryPoint+sgpaPracticalPoint)/(listOfStudent.getRegisteredTheoryCreditExcludingAudit()+listOfStudent.getRegisteredPracticalCreditExcludingAudit());
			System.out.println("SGPA of a student"+sgpa);		

			/**********************************************************
			 * PASS/FAIL DECISION CODE
			 **********************************************************/
			preProcessForResultBean.setRollNumber(listOfStudent.getRollNumber());
			preProcessForResultBean.setAttemptNumber(listOfStudent.getAttemptNumber());

			System.out.println(listOfStudent.getAttemptNumber()+"rohit check of attempt no. in proces st grd list");
			System.out.println(preProcessForResultBean.getAttemptNumber()+"rohit check of attempt no. in proces st grd");

			int attemptNumber=listOfStudent.getAttemptNumber();
			ProgramMasterInfoGetter programMaster=new ProgramMasterInfoGetter();
			programMaster.setProgramId(startActivityBean.getProgramId());
			ProgramMasterInfoGetter maxDetails=resultProcessingUtility.getMaxAttmptAllowed(programMaster);
			int maxFailSubjects=Integer.parseInt(maxDetails.getMaxNumberOfFailSubjects());
			int maxAttemptAllowed=Integer.parseInt(maxDetails.getNumberOfAttemptAllowed());
			int numberOfTerms=Integer.parseInt(maxDetails.getNumberOfTerms());
			PreviousSemesterDetail previousSemesterDetail=new PreviousSemesterDetail(listOfStudent.getRollNumber(),startActivityBean.getEntityId(),
					startActivityBean.getProgramCourseKey());
			List<PreviousSemesterDetail> previousDetails=resultProcessingUtility.getPreviousSemesterDetailForMarksSystem(previousSemesterDetail);
			//		List<PreviousSemesterDetail> previousDetails=getPreviousSemesterDetailForGradeSystem(previousSemesterDetail);
			//count=failSubjectsList.size();

			//Set theory and practical percentage and credits according to roll number in STUDENT_AGGREGATE
			String programStatus=updatePassFail.updatePassFailForProgram(previousDetails,preProcessForResultBean,passSubjectsList,failSubjectsList,
					startActivityBean,maxFailSubjects,maxAttemptAllowed);

			preProcessForResultBean.setTheoryCredit(theoryEarnedCredit);
			preProcessForResultBean.setPracticalCredit(practicalEarnedCredit);

			preProcessForResultBean.setSgpa(sgpa);

			preProcessForResultBean.setCgpaTheoryPointSecured(cgpaTheoryPoint);
			preProcessForResultBean.setCgpaPracticalPointSecured(cgpaPracticalPoint);
			
			preProcessForResultBean.setSgpaTheoryPointSecured(sgpaTheoryPoint);
			preProcessForResultBean.setSgpaPracticalPointSecured(sgpaPracticalPoint);
			
			preProcessForResultBean.setCgpaTheoryPointSecured(cgpaTheoryPoint);
			
			preProcessForResultBean.setCgpaPracticalPointSecured(cgpaPracticalPoint);
			
			preProcessForResultBean.setEarnedTheorycgpaCredit(cgpaEarnedTheroyCredit);
			preProcessForResultBean.setEarnedPracticalcgpaCredit(cgpaEarnedPracticalCredit);
			
			
			preProcessForResultBean.setEarnedTheoryAudCredit(earnedTheoryAudCredit);
			preProcessForResultBean.setEarnedPracticalAudCredit(earnedPracticalAudCredit);
			preProcessForResultBean.setRegisteredTheoryCreditExcludingAudit(listOfStudent.getRegisteredTheoryCreditExcludingAudit());
			preProcessForResultBean.setRegisteredPracticalCreditExcludingAudit(listOfStudent.getRegisteredPracticalCreditExcludingAudit());

			preProcessForResultBean.setNumberOfRemedials(failSubjectsList.size());

			preProcessForResultBean.setRemarks("NIL");

			System.out.println("SGPA"+sgpaTheoryPoint+" and "+sgpaPracticalPoint+""+listOfStudent.getRegisteredTheoryCreditExcludingAudit()+
					""+listOfStudent.getRegisteredPracticalCreditExcludingAudit());
			System.out.println("Theory SGPA"+sgpaTheoryPoint/listOfStudent.getRegisteredTheoryCreditExcludingAudit());
			System.out.println("Practical SGPA"+sgpaPracticalPoint/listOfStudent.getRegisteredPracticalCreditExcludingAudit());

		//	int y=sqlMapClient.update("preprocess.insertStudentAggregate", preProcessForResultBean);

			double previousRemedials=0;
			boolean isFail=false;
			boolean finalSemester=resultProcessingUtility.isFinalSemester(preProcessForResultBean);
			double previousCGPAPoint=0.0;

			double previousEarnedCredit=0.0;


			TransferNORInPSTBean transferNORInPSTBean=new TransferNORInPSTBean(preProcessForResultBean.getEntityId(),preProcessForResultBean.getProgramId(),
					preProcessForResultBean.getBranchId(),preProcessForResultBean.getSpecializationId(),preProcessForResultBean.getSemesterCode(),
					preProcessForResultBean.getSemesterStartDate(),preProcessForResultBean.getSemesterEndDate());
			String minSemester=new RegistrationFunction(sqlMapClient,transactionTemplate).getMinimumSemester(transferNORInPSTBean);


			transferNORInPSTBean.setSemesterCode(minSemester);
			String previousProgramCourseKey=new RegistrationFunction(sqlMapClient,transactionTemplate).getProgramCourseKey(transferNORInPSTBean);

			//double previousSGPA=0;
			//double  previousCGPAtheoryPoint = 0 ;
			//double  previousCGPApracticalPoint = 0 ;
			//double  previousCGPAtheoryearnedCredit = 0 ;
			//double  previousCGPApracticalearnedCredit = 0 ;

			int totalSemester=0;
			PreviousSemesterDetail previousProgram=new PreviousSemesterDetail();
			if(previousDetails.size()>0){
				
				
				
				for(PreviousSemesterDetail previousSemester:previousDetails){
					previousRemedials=previousRemedials+previousSemester.getNumberOfRemedials();
					previousCGPAPoint=previousCGPAPoint+(previousSemester.getTheoryCGPAPoint()+previousSemester.getPracticalCGPAPoint());
					
			//		previousCGPAtheoryPoint =previousCGPAtheoryPoint + previousSemester.getTheoryCGPAPoint() ;
			//		previousCGPApracticalPoint = previousCGPApracticalPoint + previousSemester.getPracticalCGPAPoint() ;
					
			//		previousCGPAtheoryearnedCredit = previousCGPAtheoryearnedCredit + previousSemester.getTheoryEarnedCgpaCredit();
			//		previousCGPApracticalearnedCredit = previousCGPApracticalearnedCredit + previousSemester.getPracticalEarnedCgpaCredit();
					
			//		previousEarnedCredit=previousEarnedCredit+(previousSemester.getTheoryEarnedCgpaCredit()+previousSemester.getPracticalEarnedCgpaCredit());
			//		previousSGPA=previousSGPA+previousSemester.getSgpa();
			//		System.out.println("Previous CGPA Point"+previousCGPAPoint+"Previous Earned CGPA"+previousEarnedCredit);

					previousProgram.setProgramCourseKey(preProcessForResultBean.getProgramCourseKey());
					List<PreviousSemesterDetail> list =sqlMapClient.queryForList("commonresultprocessquery.previousProgram",previousProgram );
					String programId=(list.get(0)).getProgramId();
					if(programId.equalsIgnoreCase(preProcessForResultBean.getProgramId())){
						totalSemester++;
					}

					//For all previous semesters in that program for that session only
				}//For loop for previous details ends
			}
	//		double cgpa = 0.00 ;
			
			RemedialProcessingImpl rpi = new RemedialProcessingImpl(sqlMapClient ,transactionTemplate);
			PreProcessForResultBean wrkcgpa = new PreProcessForResultBean();
			
			wrkcgpa = rpi.calculatecgpa(preProcessForResultBean);
			
//			if ((preProcessForResultBean.getEarnedTheorycgpaCredit()+preProcessForResultBean.getEarnedPracticalcgpaCredit()+previousEarnedCredit) > 0)
//			{
//			cgpa=(preProcessForResultBean.getCgpaTheoryPointSecured()+preProcessForResultBean.getCgpaPracticalPointSecured()+previousCGPAPoint)
//			/(preProcessForResultBean.getEarnedTheorycgpaCredit()+preProcessForResultBean.getEarnedPracticalcgpaCredit()+previousEarnedCredit);
//			}
//			else {cgpa =0;
//				
//			}
//			
//			
//			double wrktheorycgpa = 0 ;
//			double wrkpracticalcgpa = 0 ;
//			
//			
//			// calculate theory cgpa
//			if ((preProcessForResultBean.getEarnedTheorycgpaCredit()+previousCGPAtheoryearnedCredit) > 0){
//				wrktheorycgpa = (preProcessForResultBean.getCgpaTheoryPointSecured()+previousCGPAtheoryPoint)
//				/(preProcessForResultBean.getEarnedTheorycgpaCredit()+previousCGPAtheoryearnedCredit) ;
//			}else{
//				wrktheorycgpa = 0;
//			}
//			
//			// calculate practical cgpa
//			if ((preProcessForResultBean.getEarnedPracticalcgpaCredit()+previousCGPApracticalearnedCredit) > 0){
//				
//				wrkpracticalcgpa = (preProcessForResultBean.getCgpaPracticalPointSecured()+previousCGPApracticalPoint)
//				/(preProcessForResultBean.getEarnedPracticalcgpaCredit()+previousCGPApracticalearnedCredit) ;
//			
//			}else{
//				wrkpracticalcgpa = 0;
//			}
			
			
			

			preProcessForResultBean.setCgpa(wrkcgpa.getCgpa());
			preProcessForResultBean.setTheorycgpa(wrkcgpa.getTheorycgpa());
			preProcessForResultBean.setPracticalcgpa(wrkcgpa.getPracticalcgpa());
			
			preProcessForResultBean
			.setCgpaTheoryPointSecured(wrkcgpa.getCgpaTheoryPointSecured());
			preProcessForResultBean
			.setCgpaPracticalPointSecured(wrkcgpa.getCgpaPracticalPointSecured());
			preProcessForResultBean
			.setEarnedTheorycgpaCredit(wrkcgpa.getEarnedTheorycgpaCredit());
			preProcessForResultBean
			.setEarnedPracticalcgpaCredit(wrkcgpa.getEarnedPracticalcgpaCredit());
			
			
			int y=sqlMapClient.update("preprocess.insertStudentAggregate", preProcessForResultBean);
			
				
			
			sqlMapClient.update("preprocess.updateStudentProgramCGPA", preProcessForResultBean);
			// update cgpa at Student aggregate
			

			
			
			
			
			
			
			
			String currentProgramKey=preProcessForResultBean.getProgramCourseKey();
			String currentSemesterStartDate=preProcessForResultBean.getSemesterStartDate();
			String currentSemesterEndDate=preProcessForResultBean.getSemesterEndDate();
			


			InsertYTR insertYTR=new InsertYTR(sqlMapClient,transactionTemplate);
			StudentTrackingFunction studentTrackingFunction=new StudentTrackingFunction(sqlMapClient,transactionTemplate);
			if(failSubjectsList.size()==0){
				preProcessForResultBean.setSemesterStatus("PAS");
				sqlMapClient.update("preprocess.updateSRSHResultStatus", preProcessForResultBean);
				System.out.println("rohit pre check for track"+CRConstant.REGISTRATION_STATUS+CRConstant.STATUS_PASS);

				studentTrackingFunction.insertStudentTracking(preProcessForResultBean.getEntityId(),
						preProcessForResultBean.getRollNumber(),
						preProcessForResultBean.getProgramCourseKey(),
						preProcessForResultBean.getSemesterStartDate(),
						preProcessForResultBean.getSemesterEndDate(),
						preProcessForResultBean.getSessionStartDate(),
						preProcessForResultBean.getSessionEndDate(),
						CRConstant.STATUS_PASS,
						CRConstant.ACTIVE_STATUS, preProcessForResultBean
						.getUserId(), preProcessForResultBean
						.getActivityId(), preProcessForResultBean
						.getProcessId());
				if(finalSemester){
					if(previousRemedials==0 && ((totalSemester+1)==numberOfTerms)){
						
						preProcessForResultBean.setProgramCompletionDate(preProcessForResultBean.getSemesterEndDate());
						
						preProcessForResultBean.setOutSemester(preProcessForResultBean.getSemesterCode());
						
						preProcessForResultBean.setProgramStatus(CRConstant.STATUS_PASS);
				//		preProcessForResultBean.setProgramCompletionDate(preProcessForResultBean.getSemesterEndDate());
					//	preProcessForResultBean.setOutSemester(preProcessForResultBean.getSemesterCode());
						sqlMapClient.update("preprocess.updateStudentProgramStatus", preProcessForResultBean);

						studentTrackingFunction.insertStudentTracking(preProcessForResultBean.getEntityId(),
								preProcessForResultBean.getRollNumber(),
								preProcessForResultBean.getProgramCourseKey(),
								preProcessForResultBean.getSemesterStartDate(),
								preProcessForResultBean.getSemesterEndDate(),
								preProcessForResultBean.getSessionStartDate(),
								preProcessForResultBean.getSessionEndDate(),
								CRConstant.STATUS_PASS,
								CRConstant.STATUS_PASS, preProcessForResultBean
								.getUserId(), preProcessForResultBean
								.getActivityId(), preProcessForResultBean
								.getProcessId());
					}//If Previous Remedial equals to zero
					else{
						String message="Student has atleast one remedial";
					}
				}//If UIP.semester is final semester if ends
				else{
					
					try{
					//Insert YTR & student Tracking both
					// Only insert ytr when no Remedial in previous semester.Arush
					
					if(previousRemedials>0 && IsNextSemesterInSameSession(preProcessForResultBean)){
						
					insertYTR.insertYTRForPASS(preProcessForResultBean);
					}
					else{ 
						if (previousRemedials==0){
							insertYTR.insertYTRForPASS(preProcessForResultBean);
							
						}
						
					}
					} catch(MyException ex){
					
						throw new MyException(ex.getMessage());
						
					}
					}//else INSERT YTR ends
			}//if failSubject List==0 ends
			else{
				if(failSubjectsList.size()>maxFailSubjects || failSubjectsList.size()+previousRemedials>maxFailSubjects){
					preProcessForResultBean.setSemesterStatus("FAL");
					sqlMapClient.update("preprocess.updateSRSHResultStatus", preProcessForResultBean);
					studentTrackingFunction.insertStudentTracking(preProcessForResultBean.getEntityId(),
							preProcessForResultBean.getRollNumber(),
							preProcessForResultBean.getProgramCourseKey(),
							preProcessForResultBean.getSemesterStartDate(),
							preProcessForResultBean.getSemesterEndDate(),
							preProcessForResultBean.getSessionStartDate(),
							preProcessForResultBean.getSessionEndDate(),
							CRConstant.STATUS_FAIL,
							CRConstant.ACTIVE_STATUS, preProcessForResultBean
							.getUserId(), preProcessForResultBean
							.getActivityId(), preProcessForResultBean
							.getProcessId());

					//update user semester with fail
					if(previousDetails.size()>0){
						for(PreviousSemesterDetail previousSemester:previousDetails){
							boolean checkGroup=false;
							previousProgram.setProgramCourseKey(previousSemester.getProgramCourseKey());

							List<PreviousSemesterDetail> list =sqlMapClient.queryForList("commonresultprocessquery.previousProgram",previousProgram );
							String programId=(list.get(0)).getProgramId();
							String branchId=(list.get(0)).getBranchId();
							String specializationId=(list.get(0)).getSpecializationId();
							String semesterId=(list.get(0)).getSemesterCode();

							//Previous Values will be set to find out the semester group
							//In case of same group that previous semester will be updated FAL
							TransferNORInPSTBean checkGroupInput = new TransferNORInPSTBean(preProcessForResultBean.getEntityId(),
									programId, branchId,
									specializationId, semesterId,
									previousSemester.getPreviousSemesterStartDate(), previousSemester.getPreviousSemesterEndDate());

							// pick up previous semester code and group
							List<TransferNORInPSTBean> checkGroupList = (List<TransferNORInPSTBean>) sqlMapClient
							.queryForList("TransferNORInPSTBean.getNextSemester",
									checkGroupInput);

							for (TransferNORInPSTBean checkGroupValue : checkGroupList) {
								checkGroup = checkGroupValue.getCheckSemesterGroup();
							}
							if(checkGroup){
								preProcessForResultBean.setProgramCourseKey(previousSemester.getProgramCourseKey());
								
								// Commented By Arush Changes start from here 
//								preProcessForResultBean.setSessionStartDate(previousSemester.getPreviousSemesterStartDate());
//								preProcessForResultBean.setSessionEndDate(previousSemester.getPreviousSemesterEndDate());
								
								
								
								preProcessForResultBean.setSemesterStartDate(previousSemester.getPreviousSemesterStartDate());
								
     							preProcessForResultBean.setSemesterEndDate(previousSemester.getPreviousSemesterEndDate());
     							
     							// Arush Changes end here
     							
     												
								

								//sqlMapClient.update("preprocess.updateSRSHResultStatus", preProcessForResultBean);
     							sqlMapClient.update("preprocess.updateSRSHStatus", preProcessForResultBean);  // only status flag need to be updated:Arush
								
								studentTrackingFunction.insertStudentTracking(preProcessForResultBean.getEntityId(),
										preProcessForResultBean.getRollNumber(),
										preProcessForResultBean.getProgramCourseKey(),
										preProcessForResultBean.getSemesterStartDate(),
										preProcessForResultBean.getSemesterEndDate(),
										preProcessForResultBean.getSessionStartDate(),
										preProcessForResultBean.getSessionEndDate(),
										CRConstant.STATUS_FAIL,
										CRConstant.ACTIVE_STATUS, preProcessForResultBean
										.getUserId(), preProcessForResultBean
										.getActivityId(), preProcessForResultBean
										.getProcessId());

								//Arush Changes  reset back the program course key to current 
								preProcessForResultBean.setProgramCourseKey(currentProgramKey);
								preProcessForResultBean.setSemesterStartDate((currentSemesterStartDate));
								preProcessForResultBean.setSemesterEndDate(currentSemesterEndDate);

							}//If previous detail loop ends
						}//Previous Details==0 ends
					}//If checkgroup ends

					if(preProcessForResultBean.getAttemptNumber()>=maxAttemptAllowed){
						preProcessForResultBean.setProgramStatus(CRConstant.STATUS_FAIL);
						isFail=true;
						preProcessForResultBean.setSemesterCode(semesterCode);
						preProcessForResultBean.setProgramCourseKey(currentProgramKey);
						
						// Commented out by Arush
						
//						preProcessForResultBean.setSessionStartDate(currentSemesterStartDate);
//						preProcessForResultBean.setSessionEndDate(currentSemesterEndDate);


					//	sqlMapClient.update("preprocess.updateStudentProgramStatus", preProcessForResultBean);  commented by arush
						
						sqlMapClient.update("preprocess.updateProgramStatusonly", preProcessForResultBean); // added by arush only need to update status

						studentTrackingFunction.insertStudentTracking(preProcessForResultBean.getEntityId(),
								preProcessForResultBean.getRollNumber(),
								preProcessForResultBean.getProgramCourseKey(),
								preProcessForResultBean.getSemesterStartDate(),
								preProcessForResultBean.getSemesterEndDate(),
								preProcessForResultBean.getSessionStartDate(),
								preProcessForResultBean.getSessionEndDate(),
								CRConstant.STATUS_FAIL,
								CRConstant.STATUS_FAIL, preProcessForResultBean
								.getUserId(), preProcessForResultBean
								.getActivityId(), preProcessForResultBean
								.getProcessId());
					}
					else{
						
						//Insert YTR
						try{
						insertYTR.insertYTRForFAIL(preProcessForResultBean);
					
						
	
						
						}catch(MyException ex){
							
							throw new MyException(ex.getMessage());
							
						}
						}

				}//failSubjectList==maxFail Subject ends		


				else{
					preProcessForResultBean.setSemesterStatus("REM");
					sqlMapClient.update("preprocess.updateSRSHResultStatus", preProcessForResultBean);
					studentTrackingFunction.insertStudentTracking(preProcessForResultBean.getEntityId(),
							preProcessForResultBean.getRollNumber(),
							preProcessForResultBean.getProgramCourseKey(),
							preProcessForResultBean.getSemesterStartDate(),
							preProcessForResultBean.getSemesterEndDate(),
							preProcessForResultBean.getSessionStartDate(),
							preProcessForResultBean.getSessionEndDate(),
							CRConstant.STATUS_REMEDIAL,
							CRConstant.ACTIVE_STATUS, preProcessForResultBean
							.getUserId(), preProcessForResultBean
							.getActivityId(), preProcessForResultBean
							.getProcessId());
					boolean checkGroup=false;

					TransferNORInPSTBean userData = new TransferNORInPSTBean(preProcessForResultBean.getEntityId(),
							preProcessForResultBean.getProgramId(), preProcessForResultBean.getBranchId(),
							preProcessForResultBean.getSpecializationId(), preProcessForResultBean.getSemesterCode(),
							preProcessForResultBean.getSemesterStartDate(), preProcessForResultBean.getSemesterEndDate());

					// pick up previous semester code and group
					List<TransferNORInPSTBean> registerSemesterList = (List<TransferNORInPSTBean>) sqlMapClient
					.queryForList("TransferNORInPSTBean.getNextSemester",
							userData);

					for (TransferNORInPSTBean registerSemesterCode : registerSemesterList) {
						checkGroup = registerSemesterCode.getCheckSemesterGroup();
					}
					if(checkGroup){System.out.println("ytr funct call at 462");
					//Insert YTR and student tracking
					insertYTR.insertYTRForPASS(preProcessForResultBean);
					}
				}//else  FAIL SUBJECT LIST and REMEDIAL LIST ends


			}
			System.out.println(passSubjectsList.size()+" and "+failSubjectsList.size());
			for(String courseName:passSubjectsList){
				System.out.println("updating course");
				courseListData.setCourseCode(courseName);
				courseListData.setStudentStatus(CRConstant.STATUS_PASS);
				sqlMapClient.update("preprocess.updateCourseStatus", courseListData);
			}
			for(String courseName:failSubjectsList){
				System.out.println("updating course in REM");
				courseListData.setCourseCode(courseName);
				if(isFail){
					courseListData.setStudentStatus(CRConstant.STATUS_FAIL);
				}
				else{
					courseListData.setStudentStatus(CRConstant.STATUS_REMEDIAL);
				}

				sqlMapClient.update("preprocess.updateCourseStatus", courseListData);
			}
	}
	
	
	public boolean IsNextSemesterInSameSession(PreProcessForResultBean preProcessForResultBean) throws SQLException{
		
			boolean checkGroup=false;

			TransferNORInPSTBean userData = new TransferNORInPSTBean(preProcessForResultBean.getEntityId(),
					preProcessForResultBean.getProgramId(), preProcessForResultBean.getBranchId(),
					preProcessForResultBean.getSpecializationId(), preProcessForResultBean.getSemesterCode(),
					preProcessForResultBean.getSemesterStartDate(), preProcessForResultBean.getSemesterEndDate());

			// pick up previous semester code and group
			List<TransferNORInPSTBean> registerSemesterList = (List<TransferNORInPSTBean>) sqlMapClient
			.queryForList("TransferNORInPSTBean.getNextSemester",
					userData);

			for (TransferNORInPSTBean registerSemesterCode : registerSemesterList) {
				checkGroup = registerSemesterCode.getCheckSemesterGroup();
			}
			if(checkGroup){
				return true;
						}
			else
			{
				return false;
			}
				
		}
	
	
	
		
	}



		
		

		

