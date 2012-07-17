package in.ac.dei.edrp.cms.daoimpl.reportgeneration;

import in.ac.dei.edrp.cms.dao.reportgeneration.ProgressCardDao;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;
import in.ac.dei.edrp.cms.domain.university.UniversityMasterInfoGetter;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ProgressCardImpl extends SqlMapClientDaoSupport implements ProgressCardDao{
	private Logger loggerObject = Logger.getLogger(ProgressCardImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> progressCardDetails(ProgressCardInfo progressCardInfo) {
		List<ProgressCardInfo> progressCardName=null;
		try{
			loggerObject.info("inside progressCardDetails");
			progressCardName = getSqlMapClientTemplate().queryForList("progressCard.getProgressCardName",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside progressCardDetails: "+e);
		}
		return progressCardName;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> progressCardMarksDetails(ProgressCardInfo progressCardInfo)
	{
		System.out.println("in marks details "+progressCardInfo.getProgramCourseKey()+" "+progressCardInfo.getRollNoForDetail()+" "+progressCardInfo.getSemesterStartDate()+" "+progressCardInfo.getSemesterEndDate());
		List<ProgressCardInfo> progressCardDetails=null;
		try{
			loggerObject.info("progressCardMarksDetails");
			progressCardDetails = getSqlMapClientTemplate().queryForList("progressCard.getProgressCardMarks",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside progressCardMarksDetails: "+e);
		}
		return progressCardDetails;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> progressCardClass(ProgressCardInfo progressCardInfo) {				
		List<ProgressCardInfo> classInfo=null;
		try{
			loggerObject.info("inside progressCardClass");
			classInfo =  getSqlMapClientTemplate().queryForList("progressCard.getClassInfo",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside progressCardClass: "+e);
		}
		return classInfo;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> totalMarksDetails(ProgressCardInfo progressCardInfo) {
		System.out.println("in ttd "+progressCardInfo.getProgramCourseKey()+" "+progressCardInfo.getRollNoForDetail()+" "+progressCardInfo.getSemesterStartDate()+" "+progressCardInfo.getSemesterEndDate());
		List<ProgressCardInfo> getTotalMarks=null;
		try{
			getTotalMarks = getSqlMapClientTemplate().queryForList("progressCard.getTotalMarksDetails",progressCardInfo);
			System.out.println("ttdsiz "+getTotalMarks.size());
		}
		catch (Exception e) {
			loggerObject.error("inside totalMarksDetails: "+e);
		}
		return getTotalMarks;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> getProgramCourseKeys(ProgressCardInfo progressCardInfo) {
		List<ProgressCardInfo> programCourseKey=null;
		try{
			loggerObject.info("inside getProgramCourseKeys");
			programCourseKey = getSqlMapClientTemplate().queryForList("progressCard.getProgramCourseKey",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside getProgramCourseKeys: "+e);
		}
		return programCourseKey;
	}			
	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> studentSatisfyingPCK(ProgressCardInfo progressCardInfo)
	{	
		List<ProgressCardInfo> studentHavingPCK=null;
		try{
			loggerObject.info("inside studentSatisfyingPCK");
			System.out.println(progressCardInfo.getProgramId()+" "+progressCardInfo.getBranchId()+" "+progressCardInfo.getSpecializationId()+progressCardInfo.getSemesterStartDate()+" "+progressCardInfo.getSemesterEndDate()+" "+progressCardInfo.getProgramCourseKey());
			studentHavingPCK = getSqlMapClientTemplate().queryForList("progressCard.getStudentsSatisfyingPCK",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside studentSatisfyingPCK: "+e);
		}
		return studentHavingPCK;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> semestersBetTwoSemesters(ProgressCardInfo progressCardInfo) {
		List<ProgressCardInfo> semesters=null;
		try{
			loggerObject.info("inside semesterBetTwoSemesters");
			semesters = getSqlMapClientTemplate().queryForList("progressCard.getSemestersBetweenTwoSemester",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside semestersBetTwoSemesters "+e);
		}
		return semesters;
	}
	
	// it is unused method.
	public ProgressCardInfo semesterIsGreater(ProgressCardInfo progressCardInfo) {
		ProgressCardInfo pci=null;
		try{
			loggerObject.info("inside semesterIsGreater");
			System.out.println("in sem "+progressCardInfo.getInSemester()+"input sem "+progressCardInfo.getSemesterId()+progressCardInfo.getProgramId());
			pci = (ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.semesterIsGreater",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside semesterIsGreater: "+e);
		}
		return pci;
	}
	
	public ProgressCardInfo switchRules(ProgressCardInfo progressCardInfo) {
		ProgressCardInfo switchRule=null;
		try{
			loggerObject.info("inside switchRules");
			System.out.println(progressCardInfo.getSequenceNumber()+" sn "+progressCardInfo.getSwitchNumber()+" rollno "+progressCardInfo.getRollNoForDetail()+" program "+progressCardInfo.getProgramId()+" branch "+progressCardInfo.getBranchId()+" sp "+progressCardInfo.getSpecializationId());			
			switchRule = (ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.getSwitchRule",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("switchRules "+e);
		}
		return switchRule;
	}
	
	public ProgressCardInfo getPreviousCombination(ProgressCardInfo progressCardInfo) {
		ProgressCardInfo previousCombination=null;
		try{
			loggerObject.info("inside getPreviousCombination");
			System.out.println(progressCardInfo.getEnrollmentNumber() + " " +progressCardInfo.getRollNoForDetail()+progressCardInfo.getSwitchNumber()+progressCardInfo.getSequenceNumber());
			previousCombination = (ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.getCombinationBeforeSwitch",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside getPreviousCombination "+e);
		}
		return previousCombination;
	}
	
	public ProgressCardInfo getSinglePCK(ProgressCardInfo progressCardInfo) {
		ProgressCardInfo programCourseKey=null;
		try{
			System.out.println("in pck "+progressCardInfo.getProgramId()+ " "+progressCardInfo.getBranchId()+" "+progressCardInfo.getSemesterId()+" "+progressCardInfo.getSpecializationId());
			programCourseKey = (ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.programCourseKey",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside getSinglePCK"+e);
		}
		return programCourseKey;
	}
	
	public ProgressCardInfo semesterStartEndDate(ProgressCardInfo progressCardInfo) {
		ProgressCardInfo date=null;
		try{
			System.out.println("in date "+progressCardInfo.getProgramCourseKey()+" "+progressCardInfo.getEntityId()+" "+progressCardInfo.getRollNoForDetail());
			date = (ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.getSemesterStartEndDate",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside semesterStartEndDate "+e);
		}
		return date;
	}
	
	public ProgressCardInfo previousSemesterMarks(ProgressCardInfo progressCardInfo) {
		ProgressCardInfo marks=null;
		try{
			System.out.println(progressCardInfo.getRollNoForDetail()+"\n"+progressCardInfo.getProgramId()+"\n"+progressCardInfo.getSemesterId()+"\n"+progressCardInfo.getSpecializationId());		
			marks = (ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.getMarksOfSemesterByRollNo",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside previousSemesterMarks"+e);
		}		
		return marks;
	}
	
	public int insertIntoReportControlLog(ProgressCardInfo progressCardInfo) {		
		try{
			ProgressCardInfo wetherRecordExistInControl = (ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.verifyInsertInReportControlLog", progressCardInfo);
			System.out.println("record exists or not "+wetherRecordExistInControl);
			if(wetherRecordExistInControl == null){			
				getSqlMapClientTemplate().insert("progressCard.insertInReportControlLog",progressCardInfo);
				return 1;
			}
			else if(wetherRecordExistInControl.getPdfGenerated().equalsIgnoreCase("N")){
				getSqlMapClientTemplate().update("progressCard.updateReportLog", progressCardInfo);
				return 1;
			}
		}
		catch (Exception e) {
			loggerObject.error("inside insertIntoReportControlLog"+e);
		}
		return 0;		
	}
	
	public int insertIntoPrintControlLog(ProgressCardInfo progressCardInfo) {
		
		return 0;
	}
	
	public int insertIntoErrorLog(String rollNumber,String programCourseKey,String reportType,String errorCode,String semesterStartDate,String semesterEndDate,String session) {
		 
		System.out.println("in error log "+rollNumber+" "+programCourseKey+" "+reportType+" "+errorCode +" "+semesterEndDate+" "+semesterStartDate+" "+session);
		ProgressCardInfo progressCardInfo = new ProgressCardInfo();
		progressCardInfo.setRollNumber(rollNumber);
		progressCardInfo.setProgramCourseKey(programCourseKey);
		progressCardInfo.setReportType(reportType);
		progressCardInfo.setErrorCode(errorCode);
		progressCardInfo.setSemesterStartDate(semesterStartDate);
		progressCardInfo.setSemesterEndDate(semesterEndDate);
		progressCardInfo.setSession(session);
		ProgressCardInfo wetherRecordExistInErrorLog = (ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.verifyInsertInReportErrorLog",progressCardInfo);
		System.out.println(wetherRecordExistInErrorLog);
		if(wetherRecordExistInErrorLog == null)
		{
		getSqlMapClientTemplate().insert("progressCard.insertInReportErrorLog",progressCardInfo);
		return 1;
		}
		else return 0;		
	}
	
	public ProgressCardInfo cummulativeForFinalResultCard(ProgressCardInfo progressCardInfo) {
		ProgressCardInfo cummulativeForFC=null;
		try{
			System.out.println(progressCardInfo.getRollNoForDetail()+" "+progressCardInfo.getEnrollmentNumber()+" "+progressCardInfo.getProgramId()+" "+progressCardInfo.getEntityId()+" "+progressCardInfo.getBranchId()+" "+progressCardInfo.getSpecializationId());
			cummulativeForFC = (ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.getCummulativeForFC",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside cummulativeForFinalResultCard "+e);
		}
		return cummulativeForFC;
	}
	
	// Ankit section start	
	public ProgressCardInfo checkStatusOfAwradSheet(ProgressCardInfo progressCardInfo) {
		ProgressCardInfo awardBlankStatus=null;
		try{
			awardBlankStatus =(ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.checkABVStatus",progressCardInfo);			
		}
		catch (Exception e) {
			loggerObject.error("checkStatusOfAwradSheet "+e);
		}
		return awardBlankStatus;
	}

	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> getPreviousProgramCourseKey(
			ProgressCardInfo progressCardInfo) {
		List<ProgressCardInfo> preProgramCourseKeyList=null;
		try{
			preProgramCourseKeyList = getSqlMapClientTemplate().queryForList("progressCard.getPreviousProgramCourseKey",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("getPreviousProgramCourseKey "+e);
		}
		return preProgramCourseKeyList;
	}

	// Ankit section end
}
