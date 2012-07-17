package in.ac.dei.edrp.cms.daoimpl.resultprocessing;

import in.ac.dei.edrp.cms.domain.programmaster.ProgramMasterInfoGetter;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessForResultBean;
import in.ac.dei.edrp.cms.utility.PreviousSemesterDetail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ResultProcessingUtility {
	PreProcessForResult preProcessForResult=new PreProcessForResult();
	private SqlMapClient sqlMapClient;
	private TransactionTemplate transactionTemplate;
	
	public ResultProcessingUtility(SqlMapClient sqlMapClient,
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

	/**
	 * It returns true if current semester is Final Semester for current program.
	 * @param preProcessForResultBean
	 * @return {@link Boolean}
	 */
	public boolean isFinalSemester(
			PreProcessForResultBean preProcessForResultBean) {
		// TODO Auto-generated method stub
		boolean finalSemester=false;
		
		try{
			List<PreProcessForResultBean> list=sqlMapClient.queryForList("preprocess.getFinalSemester", preProcessForResultBean);
			
			for(PreProcessForResultBean marks:list){
				if(marks.getSemesterStatus().equalsIgnoreCase("F")){
					System.out.println("This is final Semester ");
					finalSemester=true;
				}
			}
		}catch(Exception e){
			System.out.println("Coming inside exception e for final Semester"+e.getMessage());
		}
		return finalSemester;
	}

	//To find Min Passing Marks, Grade, Internal weight, external weight
	//Main use of this function is to fetch value from SYSTEM_VALUES.
	public String getMinPassMarks(
			PreProcessForResultBean preProcessForResultBean, String code) {
		// TODO Auto-generated method stub
		
		String minMarks="0";
		preProcessForResultBean.setCode(code);
		
		try{
			List<PreProcessForResultBean> list=sqlMapClient.queryForList("preprocess.getMinPassMarks", preProcessForResultBean);
			
			for(PreProcessForResultBean marks:list){
				minMarks=marks.getMinPassMarks();
			}
			
		}catch(Exception e){
			System.out.println("Coming inside exception "+e.getMessage());
		}
		return minMarks;
	}
	
	//To change a number in double
	public double changeNumber(double theoryAggregate,
			double totalEvaluationForTheory) {
		// TODO Auto-generated method stub
		
		try{
			if(totalEvaluationForTheory==0.0){
				return 0.0;
			}
			else{
				double d=(double)theoryAggregate/(double)totalEvaluationForTheory*100.0;
				
				
			return d;
			}
		}catch(Exception e){
			return 0.0;			
		}
		
	}
	
	//To get Result System
	//If in a program, all courses follows GRADE system, it returns GR
	//IF in a program, all course follows PERCENTAGE system, it returns MK
	//If in a program, some courses follows GRADE and some percentage, or no course is there, it returns XX
	public String getResultSystem(PreProcessForResultBean preProcessForResultBean) {
		// TODO Auto-generated method stub
		try{
			int noOfGradeCourse=0;
			int noOfMarkCourse=0;
			preProcessForResultBean.setResultSystem("GR");
			List<PreProcessForResultBean> listGradeCourse=sqlMapClient.queryForList("preprocess.getResultSystemCount", preProcessForResultBean);
			for(PreProcessForResultBean grade:listGradeCourse){
				noOfGradeCourse=grade.getCount();
			}
			preProcessForResultBean.setResultSystem("MK");
			List<PreProcessForResultBean> listMarksCourse=sqlMapClient.queryForList("preprocess.getResultSystemCount", preProcessForResultBean);
			for(PreProcessForResultBean marks:listMarksCourse){
				noOfMarkCourse=marks.getCount();
			}
			
			if(noOfGradeCourse>0){
				if(noOfMarkCourse==0){
					return "GR";
				}
			}
			else{
				if(noOfMarkCourse>0){
					if(noOfGradeCourse==0){
						return "MK";
					}
				}
			}
			
			
		}catch(Exception e){
			System.out.println("Exception inside result system: "+e.getMessage());
		}
		return "XX";
	}
	
	
	public List<PreviousSemesterDetail> getPreviousSemesterDetailForMarksSystem(PreviousSemesterDetail previousSemesterDetail) {
		// TODO Auto-generated method stub
		List<PreviousSemesterDetail> detailList=new ArrayList<PreviousSemesterDetail>();
		try{
		
		detailList=sqlMapClient.queryForList("commonresultprocessquery.getPreviousSemesterDetailsForMarksSystem", previousSemesterDetail);
//		for(PreviousSemesterDetail detail:detailList){
//			
//			list.add(new PreviousSemesterDetail(previousSemesterDetail.getRollNumber(),detail.getProgramCourseKey(),detail.getNumberOfRemedials(),
//					detail.getCumulativeWeighted()));
//		}
		}catch(Exception e){
			System.out.println("Exception is e: "+e.getMessage());
		}
		return detailList;
	}

	//Get course classification
	public char isAuditCourse(String courseType) {
		// TODO Auto-generated method stub
		
		if(courseType.equalsIgnoreCase("REG")){
			return 'M';
		}
		else{
			return 'A';
		}
	}
	
	//To find previous semester details
	public List<PreviousSemesterDetail> getPreviousSemesterDetailForGradeSystem(PreviousSemesterDetail previousSemesterDetail) {
		// TODO Auto-generated method stub
		List<PreviousSemesterDetail> detailList=new ArrayList<PreviousSemesterDetail>();;
		try{
			detailList=sqlMapClient.queryForList("commonresultprocessquery.getPreviousSemesterDetailsForGRSystem", previousSemesterDetail);;
		//		for(PreviousSemesterDetail detail:detailList){
//		
//			list.add(new PreviousSemesterDetail(previousSemesterDetail.getRollNumber(),detail.getProgramCourseKey(),detail.getNumberOfRemedials(),
//					detail.getSgpa(),detail.getPointEarnedCgpa(),detail.getEarnedCreditCgpa()));
//		}
		}catch(Exception e){
			System.out.println("Exception is e: "+e.getMessage());
		}
		return detailList;
	}
	
	public char getClassification(String courseClassification) {
		// TODO Auto-generated method stub
		char classification=courseClassification.toCharArray()[0];
		
		switch (classification){
		case 'T':{
			return 'T';
		}
		case 'P':{
			return 'P';
		}
		default:{
			return 'X';
		}
		
		}
		
	}
	
	public ProgramMasterInfoGetter getMaxAttmptAllowed(
			ProgramMasterInfoGetter programMaster) {
		// TODO Auto-generated method stub
		ProgramMasterInfoGetter programMaxDetail=new ProgramMasterInfoGetter();
		try{
		List<ProgramMasterInfoGetter> detailList=sqlMapClient.queryForList("commonresultprocessquery.getProgramMaxDetail", programMaster);
		for(ProgramMasterInfoGetter program:detailList){
		programMaxDetail.setNumberOfAttemptAllowed(program.getNumberOfAttemptAllowed());
		programMaxDetail.setMaxNumberOfFailSubjects(program.getMaxNumberOfFailSubjects());
		programMaxDetail.setNumberOfTerms(program.getNumberOfTerms());
					
		}
		}catch(Exception e){
			System.out.println("Exception is e: "+e.getMessage());
		}
		return programMaxDetail;
	}
	
	
}
