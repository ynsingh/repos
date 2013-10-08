package in.ac.dei.edrp.cms.daoimpl.reportgeneration;

import in.ac.dei.edrp.cms.dao.reportgeneration.ProgressCardDao;
import in.ac.dei.edrp.cms.daoimpl.resultprocessing.RemedialProcessingImpl;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;

public class ProgressCardImpl extends SqlMapClientDaoSupport implements ProgressCardDao{
	private Logger loggerObject = Logger.getLogger(ProgressCardImpl.class);
	
	private TransactionTemplate transactionTemplate;
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
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
	
	//Add By Devendra to check student is switched student or not
	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> checkSwitch(ProgressCardInfo progressCardInfo) {
		List<ProgressCardInfo> list=null;
		try{
			list = getSqlMapClientTemplate().queryForList("progressCard.checkStudentSwitch",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("Error in ProgressCardImple during Check Student Is Switched or not "+e);
		}
		return list;
	}

	//Add By Devendra to check switched semester and in semester in student pogram are same or not
	public ProgressCardInfo checkInSemester(ProgressCardInfo progressCardInfo) {
		ProgressCardInfo bean=null;
		try{
			 bean =(ProgressCardInfo)getSqlMapClientTemplate().queryForObject("progressCard.checkInSemester",progressCardInfo);
			if(bean!=null){						
				bean.setInSemester("YES");
			}
			else{
				bean=new ProgressCardInfo();
				bean.setInSemester("NO");
			}
		}
		catch (Exception e) {
			loggerObject.error("Error in ProgressCardImple during Check In semester in student program "+e);
		}
		return bean;
	}
	
	//Add by devendra to get previous semester marks in case of any Switch
	public List<ProgressCardInfo> getPreviousProgramCourseKeySwitch(
			ProgressCardInfo currentSemesterDetail,
			ProgressCardInfo switchedSemesterDetail) {	
		List<ProgressCardInfo> preProgramCourseKeyList=null;
		try{			
			//Get Rule Formula from switch rule for rule id
			ProgressCardInfo been=(ProgressCardInfo)getSqlMapClientTemplate().queryForObject("progressCard.getRuleFormula",switchedSemesterDetail);
			if(been==null){				
				preProgramCourseKeyList=new ArrayList<ProgressCardInfo>();
				been=new ProgressCardInfo();
				been.setErrorCode("NULL");
				preProgramCourseKeyList.add(been);
			}
			else if(been.getRuleCodeTwo().equalsIgnoreCase("Y")){	
				ProgressCardInfo input=new ProgressCardInfo();
				input.setProgramCourseKey(switchedSemesterDetail.getProgramCourseKey());
				RemedialProcessingImpl remedialProcessingImpl=new RemedialProcessingImpl(getSqlMapClient(), transactionTemplate);
				boolean flag=false;
				String previousSemester[]=new String[Integer.parseInt(switchedSemesterDetail.getSemesterSequence())-1];
				for(int i=Integer.parseInt(switchedSemesterDetail.getSemesterSequence())-1;i>=1;i--){
					input.setSemesterSequence(String.valueOf(i));
					ProgressCardInfo sequenceBeen=(ProgressCardInfo)getSqlMapClientTemplate().queryForObject("progressCard.getSemesterSequence",input);
					String str=remedialProcessingImpl.getMappedSemesterFromSwitchFormula(sequenceBeen.getSemesterId(), been.getSwitchRuleFormula());
					previousSemester[i-1]=str;					
					if(str.equalsIgnoreCase("Not Found")){
						flag=true;
						break;
					}					
				}
				if(!flag){							
					preProgramCourseKeyList=new ArrayList<ProgressCardInfo>();
					for(int j=0;j<previousSemester.length;j++){						
						input.setRollNumber(currentSemesterDetail.getRollNumber());
						input.setSemesterId(previousSemester[j]);
						input.setSemesterSequence("1");//Semester sequence for switched program
						//Get program detail for switched semester from student program
						ProgressCardInfo switchedProgramCourseKeyBeen=(ProgressCardInfo)getSqlMapClientTemplate().queryForObject("progressCard.getPreviousSwitchedProgramCourseKey",input);
						if(switchedProgramCourseKeyBeen==null){								
							been.setErrorCode("E003");
							preProgramCourseKeyList.add(been);
							break;
						}
						else{							
							switchedProgramCourseKeyBeen.setRollNoForDetail(currentSemesterDetail.getRollNumber());
							switchedProgramCourseKeyBeen.setErrorCode("AllISWELL");							
							preProgramCourseKeyList.add(switchedProgramCourseKeyBeen);							
						}						
					}
					//Case if Switched semester is not equal to current semester for which we are generating progress card it is greater than switched semester
					if(Integer.parseInt(currentSemesterDetail.getSemesterSequence())>Integer.parseInt(switchedSemesterDetail.getSemesterSequence())){						
						input.setRollNumber(currentSemesterDetail.getRollNumber());
						input.setEntityId(currentSemesterDetail.getEntityId());
						input.setProgramCourseKey(currentSemesterDetail.getProgramCourseKey());						
						for(int j=Integer.parseInt(switchedSemesterDetail.getSemesterSequence());j<Integer.parseInt(currentSemesterDetail.getSemesterSequence());j++){													
							input.setSemesterSequence(String.valueOf(j));
							//Get previous program course key for semesters lie b/w current semester and switched semester
							ProgressCardInfo prvProgramCourseKey=(ProgressCardInfo)getSqlMapClientTemplate().queryForObject("progressCard.getPreviousProgramCourseKeyAfterSwitch",input);							
							prvProgramCourseKey.setRollNoForDetail(currentSemesterDetail.getRollNumber());
							prvProgramCourseKey.setErrorCode("AllISWELL");							
							preProgramCourseKeyList.add(prvProgramCourseKey);
						}
					}					
				}
				else{					
					preProgramCourseKeyList=new ArrayList<ProgressCardInfo>();
					been.setErrorCode("E002");
					preProgramCourseKeyList.add(been);
										
				}
			}
			else if(been.getRuleCodeTwo().equalsIgnoreCase("N")){				
				preProgramCourseKeyList=new ArrayList<ProgressCardInfo>();
				been.setErrorCode("E001");
				preProgramCourseKeyList.add(been);
				List<ProgressCardInfo>list=new ArrayList<ProgressCardInfo>();
				if(Integer.parseInt(currentSemesterDetail.getSemesterSequence())>Integer.parseInt(switchedSemesterDetail.getSemesterSequence())){
					ProgressCardInfo input=new ProgressCardInfo();
					input.setRollNumber(currentSemesterDetail.getRollNumber());
					input.setEntityId(currentSemesterDetail.getEntityId());
					input.setProgramCourseKey(currentSemesterDetail.getProgramCourseKey());						
					for(int j=Integer.parseInt(switchedSemesterDetail.getSemesterSequence());j<Integer.parseInt(currentSemesterDetail.getSemesterSequence());j++){							
						input.setSemesterSequence(String.valueOf(j));
						//Get previous program course key for semesters lie b/w current semester and switched semester including switched semester
						ProgressCardInfo prvProgramCourseKey=(ProgressCardInfo)getSqlMapClientTemplate().queryForObject("progressCard.getPreviousProgramCourseKeyAfterSwitch",input);							
						prvProgramCourseKey.setRollNoForDetail(currentSemesterDetail.getRollNumber());												
						list.add(prvProgramCourseKey);
					}					
				}
				been.setList(list);
			}
		}
		catch (Exception e) {
			loggerObject.error("Exception in Progress Card Impl During ger previous program Course Key "+e);
		}
		return preProgramCourseKeyList;
	}

}
