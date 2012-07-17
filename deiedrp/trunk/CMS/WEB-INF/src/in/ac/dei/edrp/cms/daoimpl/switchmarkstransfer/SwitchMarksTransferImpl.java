package in.ac.dei.edrp.cms.daoimpl.switchmarkstransfer;

import in.ac.dei.edrp.cms.daoimpl.resultprocessing.PreProcessForResult;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.TransferNORInPSTBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;
import in.ac.dei.edrp.cms.domain.switchmarkstransfer.SwitchMarksTransferInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class SwitchMarksTransferImpl {
	public static final Logger logger = Logger
			.getLogger(PreProcessForResult.class);

	private SqlMapClient sqlMapClient;
	private TransactionTemplate transactionTemplate;

	public SwitchMarksTransferImpl(SqlMapClient sqlMapClient,
			TransactionTemplate transactionTemplate) {
		this.sqlMapClient = sqlMapClient;
		this.transactionTemplate = transactionTemplate;
	}

	@SuppressWarnings("unchecked")
	public CountProcessRecorList transferSwitchedStudentMarks(
			final StartActivityBean startActivityBean) throws Exception {
		int total=0;
		int processed=0;
		Boolean status=true;
		final List<UnProcessedStduent> unprocessedStudent=new ArrayList<UnProcessedStduent>();
		SwitchMarksTransferInfo switchMarksTransferInfo = new SwitchMarksTransferInfo();

		switchMarksTransferInfo.setEntityId(startActivityBean.getEntityId());
		switchMarksTransferInfo.setProgramId(startActivityBean.getProgramId());
		switchMarksTransferInfo.setBranchId(startActivityBean.getBranchId());
		switchMarksTransferInfo.setSpecializationId(startActivityBean
				.getSpecializationId());
		switchMarksTransferInfo.setSemesterCode(startActivityBean
				.getSemesterCode());
		switchMarksTransferInfo.setAdmissionMode("SWT");

		final List<SwitchMarksTransferInfo> studentList = sqlMapClient
				.queryForList("switchMarksTransfer.getSwitchedStudent",
						switchMarksTransferInfo);
		total=studentList.size();

		for (int i = 0; i < studentList.size(); i++) {
			final SwitchMarksTransferInfo tempInput = studentList.get(i);
			Boolean value=(Boolean)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus tStatus) {
					Object savePoint = new Object();
					try {
						savePoint = tStatus.createSavepoint();
						SwitchMarksTransferInfo input = tempInput;
						input.setUniversityCode(startActivityBean
								.getUniversityId());
						input.setEntityId(startActivityBean.getEntityId());
						input.setProgramId(startActivityBean.getProgramId());
						input.setBranchId(startActivityBean.getBranchId());
						input.setSpecializationId(startActivityBean
								.getSpecializationId());
						input.setSemesterCode(startActivityBean
								.getSemesterCode());
						input.setAdmissionMode("SWT");
						input.setUserId(startActivityBean.getActivityId());

						List<SwitchMarksTransferInfo> ruleIdInfo = sqlMapClient
								.queryForList("switchMarksTransfer.getRuleId",
										input);
						if (ruleIdInfo.size() > 0) {
							input.setRuleId(ruleIdInfo.get(0).getRuleId());
							input.setSwitchType(ruleIdInfo.get(0)
									.getSwitchType());
						}

						List<SwitchMarksTransferInfo> ruleFormulaInfo = sqlMapClient
								.queryForList(
										"switchMarksTransfer.getRuleFormulaDetails",
										input);
						if (ruleFormulaInfo.size() > 0) {
							input.setRuleCodeOne(ruleFormulaInfo.get(0)
									.getRuleCodeOne());
							input.setRuleCodeTwo(ruleFormulaInfo.get(0)
									.getRuleCodeTwo());
							input.setRuleFormula(ruleFormulaInfo.get(0)
									.getRuleFormula());
						}

						if (input.getRuleCodeOne().equalsIgnoreCase("y")
								&& input.getRuleCodeTwo().equalsIgnoreCase("y")) {
							StringTokenizer rules = new StringTokenizer(input
									.getRuleFormula(), ",");
							while (rules.hasMoreTokens()) {
								StringTokenizer rule = new StringTokenizer(
										rules.nextToken(), ":");
								int r = 0;
								String[] inputSemList = null;
								String outputSem = "";
								while (rule.hasMoreTokens()) {
									if (r == 0) {
										inputSemList = rule.nextToken().split(
												"\\+");
									} else {
										outputSem = rule.nextToken();
									}
									r++;
								}

								String totalCreditEarned = "0";
								String registeredCredit = "0";
								String registeredTheoryCreditExcludingAudit = "0";
								String registeredPracticalCreditExcludingAudit = "0";
								String registrationCreditExcludingAudit = "0";
								String theoryWeightedPercentage = "0";
								String practicalWeightedPercentage = "0";
								String earnedPracticalCredit = "0";
								String earnedTheoryCredit = "0";
								String pointSecuredTheorySgpa = "0";
								String pointSecuredPracticalSgpa = "0";
								String pointSecuredTheoryCgpa = "0";
								String pointSecuredPracticalCgpa = "0";
								String earnedTheoryCreditCgpa = "0";
								String earnedPracticalCreditCgpa = "0";
								String earnedTheoryAudCredit = "0";
								String earnedPracticalAudCredit = "0";
								String theorySgpa = "0";
								String practicalSgpa = "0";
								String weightedPercentage = "0";
								String sgpa = "0";
								String registerDate = "";
								String registerDueDate = "";
								
								//Added By Dheeraj
								String cgpa = "0";
								String practicalCgpa = "0";
								String theoryCgpa = "0";

								for (int l = 0; l < inputSemList.length; l++) {
									SwitchMarksTransferInfo inputForCredit = new SwitchMarksTransferInfo();
									inputForCredit.setEntityId(input
											.getOldEntityId());
									inputForCredit.setProgramId(input
											.getOldProgramId());
									inputForCredit.setBranchId(input
											.getOldBranchId());
									inputForCredit.setSpecializationId(input
											.getOldSpecializationId());
									inputForCredit
											.setSemesterCode(inputSemList[l]);
									inputForCredit.setUniversityCode(input
											.getUniversityCode());
									inputForCredit.setRollNumber(input
											.getRollNumber());
									inputForCredit.setRegistrationNo(input
											.getRegistrationNo());

									String programCourseKey = ((TransferNORInPSTBean) sqlMapClient
											.queryForObject(
													"TransferNORInPSTBean.getProgramCourseKey",
													inputForCredit))
											.getProgramCourseKey();
									inputForCredit
											.setProgramCourseKey(programCourseKey);

									List<SwitchMarksTransferInfo> creditsInfo = sqlMapClient
											.queryForList(
													"switchMarksTransfer.getMarksCreditDetails",
													inputForCredit);
									if (creditsInfo.size() > 0) {
										totalCreditEarned = creditsInfo.get(0)
												.getTotalCreditEarned();
										registeredCredit = creditsInfo.get(0)
												.getRegisteredCredit();
										registeredTheoryCreditExcludingAudit = creditsInfo
												.get(0)
												.getRegisteredTheoryCreditExcludingAudit();
										registeredPracticalCreditExcludingAudit = creditsInfo
												.get(0)
												.getRegisteredPracticalCreditExcludingAudit();
										registrationCreditExcludingAudit = creditsInfo
												.get(0)
												.getRegistrationCreditExcludingAudit();
										theoryWeightedPercentage = creditsInfo
												.get(0)
												.getTheoryWeightedPercentage();
										practicalWeightedPercentage = creditsInfo
												.get(0)
												.getPracticalWeightedPercentage();
										earnedPracticalCredit = creditsInfo
												.get(0)
												.getEarnedPracticalCredit();
										earnedTheoryCredit = creditsInfo.get(0)
												.getEarnedTheoryCredit();
										pointSecuredTheorySgpa = creditsInfo
												.get(0)
												.getPointSecuredTheorySgpa();
										pointSecuredPracticalSgpa = creditsInfo
												.get(0)
												.getPointSecuredPracticalSgpa();
										pointSecuredTheoryCgpa = creditsInfo
												.get(0)
												.getPointSecuredTheoryCgpa();
										pointSecuredPracticalCgpa = creditsInfo
												.get(0)
												.getPointSecuredPracticalCgpa();
										earnedTheoryCreditCgpa = creditsInfo
												.get(0)
												.getEarnedTheoryCreditCgpa();
										earnedPracticalCreditCgpa = creditsInfo
												.get(0)
												.getEarnedPracticalCreditCgpa();
										earnedTheoryAudCredit = creditsInfo
												.get(0)
												.getEarnedTheoryAudCredit();
										earnedPracticalAudCredit = creditsInfo
												.get(0)
												.getEarnedPracticalAudCredit();
										theorySgpa = creditsInfo.get(0)
												.getTheorySgpa();
										practicalSgpa = creditsInfo.get(0)
												.getPracticalSgpa();
										weightedPercentage = creditsInfo.get(0)
												.getWeightedPercentage();
										sgpa = creditsInfo.get(0)
												.getSgpa();
										registerDate = creditsInfo.get(0)
												.getRegisterDate();
										registerDueDate = creditsInfo.get(0)
												.getRegisterDueDate();
										
										// Added By Dheeraj
										
										cgpa = creditsInfo.get(0)
												.getCgpa();
										practicalCgpa = creditsInfo.get(0)
												.getPracticalCgpa();
										theoryCgpa = creditsInfo.get(0)
												.getTheoryCgpa();
										
									}
								}

								input.setSemesterCode(outputSem);
								input.setSemesterStartDate(startActivityBean
										.getSessionStartDate());
								input.setSemesterEndDate(startActivityBean
										.getSessionEndDate());

								String programCourseKey = ((TransferNORInPSTBean) sqlMapClient
										.queryForObject(
												"TransferNORInPSTBean.getProgramCourseKey",
												input)).getProgramCourseKey();

								input.setProgramCourseKey(programCourseKey);

								SwitchMarksTransferInfo semesterDates = (SwitchMarksTransferInfo) sqlMapClient
										.queryForObject("switchMarksTransfer.getSemesterDateDetails",
												input);
								
								input.setSemesterStartDate(semesterDates.getSemesterStartDate());
								input.setSemesterEndDate(semesterDates.getSemesterEndDate());
								input.setTotalCreditEarned(totalCreditEarned);
								input.setRegisteredCredit(registeredCredit);
								input.setRegisteredTheoryCreditExcludingAudit(registeredTheoryCreditExcludingAudit);
								input.setRegisteredPracticalCreditExcludingAudit(registeredPracticalCreditExcludingAudit);
								input.setRegistrationCreditExcludingAudit(registrationCreditExcludingAudit);
								input.setTheoryWeightedPercentage(theoryWeightedPercentage);
								input.setPracticalWeightedPercentage(practicalWeightedPercentage);
								input.setEarnedPracticalCredit(earnedPracticalCredit);
								input.setEarnedTheoryCredit(earnedTheoryCredit);
								input.setPointSecuredTheorySgpa(pointSecuredTheorySgpa);
								input.setPointSecuredPracticalSgpa(pointSecuredPracticalSgpa);
								input.setPointSecuredTheoryCgpa(pointSecuredTheoryCgpa);
								input.setPointSecuredPracticalCgpa(pointSecuredPracticalCgpa);
								input.setEarnedTheoryCreditCgpa(earnedTheoryCreditCgpa);
								input.setEarnedPracticalCreditCgpa(earnedPracticalCreditCgpa);
								input.setEarnedTheoryAudCredit(earnedTheoryAudCredit);
								input.setEarnedPracticalAudCredit(earnedPracticalAudCredit);
								input.setTheorySgpa(theorySgpa);
								input.setPracticalSgpa(practicalSgpa);
								input.setWeightedPercentage(weightedPercentage);
								input.setSgpa(sgpa);
								input.setRegisterDate(registerDate);
								input.setRegisterDueDate(registerDueDate);
								input.setRemarks("Nil");
								
								//Added By Dheeraj
								input.setCgpa(cgpa);
								input.setPracticalCgpa(practicalCgpa);
								input.setTheoryCgpa(theoryCgpa);

								sqlMapClient.insert(
										"switchMarksTransfer.setSRSH", input);
								sqlMapClient
										.insert("switchMarksTransfer.setStudentAggregate",
												input);
							}
						}
						return true;
					} catch (Exception e) {
						unprocessedStudent.add(new UnProcessedStduent(tempInput.getRollNumber(),"Database Issue"+e.getMessage()));
						tStatus.rollbackToSavepoint(savePoint);
						System.out.println(e.getMessage());
						return false;
					}
				}
			});
			if(value){
				processed++;
			}
			status=(value && status);
		}

		return new CountProcessRecorList(total,processed, total-processed, status,
				unprocessedStudent);
	}
}