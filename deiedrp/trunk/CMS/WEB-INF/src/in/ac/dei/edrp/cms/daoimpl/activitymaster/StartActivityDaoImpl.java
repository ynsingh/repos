package in.ac.dei.edrp.cms.daoimpl.activitymaster;

/**
 * @(#) StartActivityDaoImpl.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
import java.awt.Color;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.controller.activitymaster.StartActivityController;
import in.ac.dei.edrp.cms.dao.activitymaster.StartActivityDao;
import in.ac.dei.edrp.cms.dao.registration.prestagingdao.TransferNORInPSTDAO;
import in.ac.dei.edrp.cms.daoimpl.registration.mastertransferdaoimpl.TransferTempIntoMaster;
import in.ac.dei.edrp.cms.daoimpl.registration.prestagingdaoimpl.TransfertNORInPSTFunction;
import in.ac.dei.edrp.cms.daoimpl.resultprocessing.PreProcessForResult;
//import in.ac.dei.edrp.cms.daoimpl.resultprocessing.RemedialProcessing;
import in.ac.dei.edrp.cms.daoimpl.resultprocessing.RemedialProcessingImpl;
import in.ac.dei.edrp.cms.daoimpl.studentregistration.ProbableToActualDaoImpl;
import in.ac.dei.edrp.cms.daoimpl.studentregistration.StudentMasterImpl;
import in.ac.dei.edrp.cms.daoimpl.studentregistration.preProcessImpl;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ActivityMasterBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.MasterTransferBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessCourseList;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;
import in.ac.dei.edrp.cms.domain.utility.ErrorLogs;
import in.ac.dei.edrp.cms.utility.CRException;
import in.ac.dei.edrp.cms.utility.RegistrationFunction;
import in.ac.dei.edrp.cms.utility.StudentTrackingFunction;
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import in.ac.dei.edrp.cms.daoimpl.studentregistration.FindDuplicateStudent;
import in.ac.dei.edrp.cms.daoimpl.switchmarkstransfer.SwitchMarksTransferImpl;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.SavepointManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPrintPage;

public class StartActivityDaoImpl implements StartActivityDao {

	public static final Logger logger = Logger
			.getLogger(StartActivityDaoImpl.class);

	protected SqlMapClient sqlMapClient = null;



	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	private TransactionTemplate transactionTemplate = null;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * Returns the list of process id and semester code and name using
	 * SYSTEM_TABLE_TWO according to group code in input object i.e
	 * startActivityBean if group code in object is PRCMST, it returns
	 * process_id and values and if group code in object is SEMCOD, it returns
	 * semester code and name
	 */
	public List<StartActivityBean> getProcessList(
			StartActivityBean startActivityBean) {
		// TODO Auto-generated method stub

		List<StartActivityBean> processList = new ArrayList<StartActivityBean>();
		try {

			processList = sqlMapClient.queryForList(
					"startActivity.getProcessList", startActivityBean);

			System.out.println("processList" + processList);
		} catch (Exception e) {
			System.out.println("Exception is: " + e.getMessage());
			logger.info("Exception in getting process list" + e.getMessage());
		}
		return processList;
	}

	/**
	 * Return data for processGridList
	 */
	public List<StartActivityBean> getProcessGridList(
			StartActivityBean startActivityBean) {
		// TODO Auto-generated method stub

		List<StartActivityBean> processGridList = new ArrayList<StartActivityBean>();
		try {

			processGridList = sqlMapClient.queryForList(
					"startActivity.getProcessGridList", startActivityBean);

			System.out.println("processList" + processGridList);
		} catch (Exception e) {
			System.out.println("Exception is: " + e.getMessage());
			logger.info("Exception in getting process grid list"
					+ e.getMessage());
		}
		return processGridList;
	}

	/**
	 * Returns list for activityProcessGridList
	 */
	public List<StartActivityBean> getProcessActivityGridList(
			StartActivityBean startActivityBean) {
		// TODO Auto-generated method stub

		List<StartActivityBean> processGridList = new ArrayList<StartActivityBean>();
		try {
			System.out.println(startActivityBean.getEntityId()
					+ startActivityBean.getProgramCourseKey()
					+ startActivityBean.getSemesterStartDate()
					+ startActivityBean.getSemesterEndDate()
					+ startActivityBean.getProcessId());
			processGridList = sqlMapClient.queryForList(
					"startActivity.getActivityList", startActivityBean);

			System.out.println("process activity List" + processGridList);
		} catch (Exception e) {
			System.out.println("Exception is: " + e.getMessage());
			logger.info("Exception in getting process activity grid list"
					+ e.getMessage());
		}
		return processGridList;
	}

	/**
	 * get Program course key for given input
	 *
	 */
	public List<StartActivityBean> getProgramCourseKey(
			StartActivityBean startActivityBean) {
		// TODO Auto-generated method stub

		List<StartActivityBean> programCourseKey = new ArrayList<StartActivityBean>();
		try {
			// if branch is not selected, then all branch and specialization for
			// this program and semesterwill be in list
			// if specialization is not selected, then all specialization for
			// this program ,branch and semester and will be in list
			// If all are selected, then a program course key according to
			// program, branch and specialization and semester
			System.out.println(startActivityBean.getBranchId()
					+ startActivityBean.getSpecializationId());
			if (startActivityBean.getBranchId().equalsIgnoreCase("")) {
				// List according to program and semester
				System.out.println("Coming inside program and semester");
				if (startActivityBean.getSpecializationId()
						.equalsIgnoreCase("")) {
					// List according to program,branch and semester
					System.out
							.println("Coming inside program and branch with semester");
					programCourseKey = sqlMapClient
							.queryForList("startActivity.getProgCoursekey",
									startActivityBean);
					System.out
							.println("Coming inside  program and branch with semester"
									+ programCourseKey.size());
				}

			} else {
				if (startActivityBean.getSpecializationId()
						.equalsIgnoreCase("")) {
					// List according to program,branch and semester
					System.out
							.println("Coming inside program and branch with semester");
					programCourseKey = sqlMapClient.queryForList(
							"startActivity.getProgCoursekeyBranch",
							startActivityBean);
					System.out
							.println("Coming inside program and branch and semester"
									+ programCourseKey.size());
				} else {
					// //List according to program,branch,specialzation and
					// semester
					System.out.println("Coming inside program and semester");
					programCourseKey = sqlMapClient.queryForList(
							"startActivity.getProgCoursekeySpecialiazation",
							startActivityBean);
					System.out
							.println("Coming inside program branch and specialization"
									+ programCourseKey.size());

				}
			}

		} catch (Exception e) {
			System.out.println("Exception is: " + e.getMessage());
			logger.info("Exception in program course key" + e.getMessage());
		}
		return programCourseKey;
	}

	// This method is responsible to run different activities.
	// Accordint to their process id, responsible method will be called
	public CountProcessRecorList startActivity(
			StartActivityBean startActivityBean, String filePath, String universityName) {
		// TODO Auto-generated method stub
		CountProcessRecorList countList = new CountProcessRecorList(
				0, 0, 0, false,new ArrayList<UnProcessedStduent>());
		;

		System.out.println(startActivityBean.getProcessId()
				+ startActivityBean.getActivityId()
				+ startActivityBean.getEntityId()
				+ startActivityBean.getProgramId()
				+ startActivityBean.getBranchId()
				+ startActivityBean.getSpecializationId()
				+ startActivityBean.getSemesterCode()
				+ startActivityBean.getSemesterStartDate()
				+ startActivityBean.getSemesterEndDate()
				+ startActivityBean.getActivityStatus()
				+ startActivityBean.getActivitySequence()
				+ startActivityBean.getProgramCourseKey()
				+ startActivityBean.getProgramName()
				+ startActivityBean.getBranchName()
				+ startActivityBean.getSpecializationName()
				+ startActivityBean.getSemesterName()
				+ startActivityBean.getSessionStartDate()
				+ startActivityBean.getSessionEndDate());

		ErrorLogs errorLogs = new ErrorLogs(startActivityBean
				.getEntityId(), startActivityBean.getProgramId(),
				startActivityBean.getBranchId(), startActivityBean
						.getSpecializationId(), startActivityBean
						.getSemesterCode(), startActivityBean
						.getSemesterStartDate(), startActivityBean
						.getSemesterEndDate(),
				startActivityBean.getProcessId(), startActivityBean
						.getActivityId());

		try {

			String activityCode = startActivityBean.getActivityId();
			String processCode = startActivityBean.getProcessId();




				// Object save=null;

						int processCounter = new StudentTrackingFunction(
								sqlMapClient, transactionTemplate)
								.batchProcessExist(errorLogs) + 1;

						errorLogs.setProcessCounter(processCounter);

						sqlMapClient.queryForList(
								"startActivity.getLockForActivity",
								startActivityBean);

						if (startActivityBean.getActivityStatus()
								.equalsIgnoreCase(CRConstant.READY_STATUS)
								|| startActivityBean.getActivityStatus()
										.equalsIgnoreCase(
												CRConstant.ERROR_STATUS)) {

							if (startActivityBean.getActivitySequence() == 1) {
								sqlMapClient.update(
										"startActivity.updateProcessStartDate",
										startActivityBean);
								sqlMapClient
										.update(
												"startActivity.updateActivityStartDate",
												startActivityBean);
							} else {
								if (!startActivityBean.getActivityStatus()
										.equalsIgnoreCase(
												CRConstant.ERROR_STATUS)) {
									sqlMapClient
											.update(
													"startActivity.updateActivityStartDate",
													startActivityBean);
								}
							}
						}

						// If process is registration
						if (processCode.equalsIgnoreCase("REG")) {


							// ENR= Enrollment number generation will be called
							if (activityCode.equalsIgnoreCase("ENR")) {
								ActivityMasterBean activityMasterBean = new ActivityMasterBean(
										startActivityBean.getUniversityId(),
										startActivityBean.getProcessId(),
										startActivityBean.getActivityId(),
										startActivityBean.getEntityId(),
										startActivityBean.getProgramId(),
										startActivityBean.getBranchId(),
										startActivityBean.getSpecializationId(),
										startActivityBean.getSemesterCode(),
										startActivityBean
												.getSemesterStartDate(),
										startActivityBean.getSemesterEndDate(),
										CRConstant.ACTIVE_STATUS,
										startActivityBean.getUserId(),
										startActivityBean.getSessionStartDate(),
										startActivityBean.getSessionEndDate(),
										processCounter);

								countList = new StudentMasterImpl(sqlMapClient,
										transactionTemplate)
										.generateEnrollmentNumbers(activityMasterBean);

								logger.info("Activity : "
										+ startActivityBean.getActivityId()
										+ "for "
										+ startActivityBean
												.getProgramCourseKey()
										+ " executed successfully");

							}

							// ROL=Roll number generation will be called
							if (activityCode.equalsIgnoreCase("ROL")) {
								ActivityMasterBean activityMasterBean = new ActivityMasterBean(
										startActivityBean.getUniversityId(),
										startActivityBean.getProcessId(),
										startActivityBean.getActivityId(),
										startActivityBean.getEntityId(),
										startActivityBean.getProgramId(),
										startActivityBean.getBranchId(),
										startActivityBean.getSpecializationId(),
										startActivityBean.getSemesterCode(),
										startActivityBean
												.getSemesterStartDate(),
										startActivityBean.getSemesterEndDate(),
										CRConstant.ACTIVE_STATUS,
										startActivityBean.getUserId(),
										startActivityBean.getSessionStartDate(),
										startActivityBean.getSessionEndDate(),
										processCounter);

								countList = new StudentMasterImpl(sqlMapClient,
										transactionTemplate)
										.generateRollNumbers(activityMasterBean);

								logger.info("Activity : "
										+ startActivityBean.getActivityId()
										+ "for "
										+ startActivityBean
												.getProgramCourseKey()
										+ " executed successfully");

							}

							// PR7 = pre check validation for new & switch
							// students
							if (activityCode.equalsIgnoreCase("PR7")) {

								ActivityMasterBean activityMasterBean = new ActivityMasterBean(
										startActivityBean.getUniversityId(),
										startActivityBean.getProcessId(),
										startActivityBean.getActivityId(),
										startActivityBean.getEntityId(),
										startActivityBean.getProgramId(),
										startActivityBean.getBranchId(),
										startActivityBean.getSpecializationId(),
										startActivityBean.getSemesterCode(),
										startActivityBean
												.getSemesterStartDate(),
										startActivityBean.getSemesterEndDate(),
										CRConstant.ACTIVE_STATUS,
										startActivityBean.getUserId(),
										startActivityBean.getSessionStartDate(),
										startActivityBean.getSessionEndDate(),
										processCounter);

								countList = new preProcessImpl(sqlMapClient,
										transactionTemplate)
										.preProcessCheck(activityMasterBean);

							}

							// P7N = pre check validation for normal mode
							// students
							if (activityCode.equalsIgnoreCase("P7N")) {

								ActivityMasterBean activityMasterBean = new ActivityMasterBean(
										startActivityBean.getUniversityId(),
										startActivityBean.getProcessId(),
										startActivityBean.getActivityId(),
										startActivityBean.getEntityId(),
										startActivityBean.getProgramId(),
										startActivityBean.getBranchId(),
										startActivityBean.getSpecializationId(),
										startActivityBean.getSemesterCode(),
										startActivityBean
												.getSemesterStartDate(),
										startActivityBean.getSemesterEndDate(),
										CRConstant.ACTIVE_STATUS,
										startActivityBean.getUserId(),
										startActivityBean.getSessionStartDate(),
										startActivityBean.getSessionEndDate(),
										processCounter);

								countList = new preProcessImpl(sqlMapClient,
										transactionTemplate)
										.preCheckforMaster(activityMasterBean);

							}

							// MST=Master Transfer will be called
							if (activityCode.equalsIgnoreCase("MST")) {
								System.out.println("User Id inside MST"
										+ startActivityBean.getUserId());
								MasterTransferBean masterTransferBean = new MasterTransferBean(
										startActivityBean.getUniversityId(),
										startActivityBean.getProcessId(),
										startActivityBean.getActivityId(),
										startActivityBean.getEntityId(),
										startActivityBean.getProgramId(),
										startActivityBean.getBranchId(),
										startActivityBean.getSpecializationId(),
										startActivityBean.getSemesterCode(),
										startActivityBean
												.getSemesterStartDate(),
										startActivityBean.getSemesterEndDate(),
										startActivityBean.getUserId(),
										startActivityBean.getSessionStartDate(),
										startActivityBean.getSessionEndDate(),
										startActivityBean.getProgramCourseKey(),
										processCounter);
								countList = new TransferTempIntoMaster(
										sqlMapClient, transactionTemplate)
										.transferTempToMaster(masterTransferBean);
								System.out
										.println("Master Transfer coding ends"
												+ countList.getTotalRecords());
							}

							if (activityCode.equalsIgnoreCase("SMT")) {
								countList=new SwitchMarksTransferImpl(sqlMapClient, transactionTemplate).transferSwitchedStudentMarks(startActivityBean);
							}

							// PR9=Probable to actual transfer
							if (activityCode.equalsIgnoreCase("PR9")) {
								StudentInfoGetter progDetails = new StudentInfoGetter();
								progDetails.setProgramId(startActivityBean
										.getProgramId());
								progDetails.setBranchCode(startActivityBean
										.getBranchId());
								progDetails
										.setNewSpecialization(startActivityBean
												.getSpecializationId());
								progDetails.setEntityId(startActivityBean
										.getEntityId());
								progDetails.setSemesterCode(startActivityBean
										.getSemesterCode());
								progDetails
										.setSessionStartDate(startActivityBean
												.getSemesterStartDate());
								progDetails.setSessionEndDate(startActivityBean
										.getSemesterEndDate());
								progDetails.setModifierId("PTA");
								countList = new ProbableToActualDaoImpl(
										sqlMapClient, transactionTemplate)
										.transferOfProbables(progDetails);

							}

						}

						// Add if condition for Process: ResultProcessing
						if (processCode.equalsIgnoreCase("RSP")) {

						}

						// FDS=Find Duplicate Student will be called. by Ankit
						if (activityCode.equalsIgnoreCase("FDS")) {
							System.out.println("User Id inside FDS"	+ startActivityBean.getUserId());
							ActivityMasterBean activityMasterBean = new ActivityMasterBean(
									startActivityBean.getUniversityId(),
									startActivityBean.getProcessId(),
									startActivityBean.getActivityId(),
									startActivityBean.getEntityId(),
									startActivityBean.getProgramId(),
									startActivityBean.getBranchId(),
									startActivityBean.getSpecializationId(),
									startActivityBean.getSemesterCode(),
									startActivityBean.getSemesterStartDate(),
									startActivityBean.getSemesterEndDate(),
									startActivityBean.getUserId(),
									startActivityBean.getSessionStartDate(),
									startActivityBean.getSessionEndDate(),
									startActivityBean.getProgramCourseKey(),
									processCounter);
							countList = new FindDuplicateStudent(sqlMapClient, transactionTemplate)
									.getDuplicateStudents(startActivityBean);
							System.out.println("Master Transfer coding ends"+ countList.getTotalRecords());

							StudentInfoGetter studentInfoGetter = new StudentInfoGetter();
							studentInfoGetter.setUniversityId(startActivityBean.getUniversityId());
							List<StudentInfoGetter> duplicateStudent =  sqlMapClient.queryForList("checkDuplicateStudent.getDuplicateStudentList",studentInfoGetter);

							buildPdfDocument(filePath, duplicateStudent,startActivityBean.getSessionStartDate(),
									startActivityBean.getSessionEndDate(), universityName);
						}

						// Add if condition for Process: ResultProcessing
						if (processCode.equalsIgnoreCase("SEP")) {
							// PR1= Process1 Method TransferNORInPST method will
							// be called.
							if (activityCode.equalsIgnoreCase("PR1")) {
								System.out.println("program id in PR1:"
										+ startActivityBean.getProgramId());
								ActivityMasterBean activityMasterBean = new ActivityMasterBean(
										startActivityBean.getUniversityId(),
										startActivityBean.getProcessId(),
										startActivityBean.getActivityId(),
										startActivityBean.getEntityId(),
										startActivityBean.getProgramId(),
										startActivityBean.getBranchId(),
										startActivityBean.getSpecializationId(),
										startActivityBean.getSemesterCode(),
										startActivityBean
												.getSemesterStartDate(),
										startActivityBean.getSemesterEndDate(),
										CRConstant.ACTIVE_STATUS,
										startActivityBean.getUserId(),
										startActivityBean.getSessionStartDate(),
										startActivityBean.getSessionEndDate(),
										processCounter);

								countList = new TransfertNORInPSTFunction(
										sqlMapClient, transactionTemplate)
										.transferNorinPST(activityMasterBean);
								logger.info("Activity : "
										+ startActivityBean.getActivityId()
										+ "for "
										+ startActivityBean
												.getProgramCourseKey()
										+ " executed successfully");
							}

							// PRE: Preprocess 1
							System.out.println("coming inside result process");
							if (activityCode.equalsIgnoreCase("PRE")) {
								System.out
										.println("coming inside result processing:MAIN");
								startActivityBean.setProcessCounter(processCounter);
								countList = new PreProcessForResult(
										sqlMapClient, transactionTemplate)
										.startPreProcess(startActivityBean);
							}

							// SMR: Preprocess 1 or remedial process
							if (activityCode.equalsIgnoreCase("SMR")) {
								countList = new RemedialProcessingImpl(
								sqlMapClient, transactionTemplate)
								.startRemedialProcess(startActivityBean);
							}


				// Award Sheet Verification for Internal And External
				if (activityCode.equalsIgnoreCase("ABV")) {
												System.out.println("here in award blank verifiction");
												AwardSheetInfoGetter input = new AwardSheetInfoGetter();
												input.setEntityId(startActivityBean.getEntityId());
												input.setProgramId(startActivityBean.getProgramId());
												input.setBranchId(startActivityBean.getBranchId());
												input.setSpecializationId(startActivityBean
														.getSpecializationId());
												input.setSemesterCode(startActivityBean.getSemesterCode());
												input.setPreviousStatus("%");
												input.setStatus("%");


												@SuppressWarnings("unchecked")
												List<AwardSheetInfoGetter> courseList = sqlMapClient
														.queryForList(
																"awardSheetVerification.getCourseList",
																input);

												System.out.println("total course record"
														+ courseList.size());

												List<UnProcessedStduent> errorList = new ArrayList<UnProcessedStduent>();
												
												Boolean bool = true;

												for (int i = 0; i < courseList.size(); i++) {
													input.setCourseCode(courseList.get(i).getCourseCode());
													input.setDisplayType("E");
													input.setIdType("I");
													AwardSheetInfoGetter countAPR = (AwardSheetInfoGetter) sqlMapClient
															.queryForObject(
																	"awardSheetVerification.getAPRCount",
																	input);

													if (Integer.parseInt(countAPR.getTotal()) <= Integer
															.parseInt(courseList.get(i).getStatus())) {
														UnProcessedStduent errorBean = new UnProcessedStduent();
														errorBean.setRollNumber(courseList.get(i).getCourseCode());
														errorBean.setProcessed("Award Blank Not Approved By Higher Approval Order Authorities");
														errorList.add(errorBean);
														bool = false;
													}
												}

												System.out.println("total course record "
														+ courseList.size());
												System.out.println("total rejected record "
														+ errorList.size());
												System.out.println("is completed " + bool);
												countList = new CountProcessRecorList(courseList.size(),
														courseList.size() - errorList.size(),
														errorList.size(), bool, errorList);



											}

											// Award Sheet Verification for Remedial
											if (activityCode.equalsIgnoreCase("ABR")) {
					System.out.println("here in award Blank Verification for remedial");
					List<PreProcessCourseList> semesterList = sqlMapClient
							.queryForList("remedialProcess.getSemesterList",
									startActivityBean);
					List<UnProcessedStduent> errorList = new ArrayList<UnProcessedStduent>();
					Integer total=0;
					Boolean bool = true;

					for(int i=0;i<semesterList.size();i++){
						System.out.println("here in award blank verifiction semester loop");
						System.out.println((i+1)+"th Semester is "+semesterList.get(i).getSemesterCode());
						AwardSheetInfoGetter input = new AwardSheetInfoGetter();
						input.setEntityId(startActivityBean.getEntityId());
						input.setProgramId(startActivityBean.getProgramId());
						input.setBranchId(startActivityBean.getBranchId());
						input.setSpecializationId(startActivityBean
								.getSpecializationId());
						input.setSemesterCode(semesterList.get(i).getSemesterCode());
						input.setPreviousStatus("REM");
						input.setStatus("REM");

						@SuppressWarnings("unchecked")
						List<AwardSheetInfoGetter> courseList = sqlMapClient
								.queryForList(
										"awardSheetVerification.getCourseList",
										input);

						total+=courseList.size();

						System.out.println("total course record in "+semesterList.get(i).getSemesterCode()+"="
								+ courseList.size());

						for (int j = 0; j < courseList.size(); j++) {
							input.setCourseCode(courseList.get(j).getCourseCode());
							input.setDisplayType("R");
							input.setIdType("R");
							AwardSheetInfoGetter countAPR = (AwardSheetInfoGetter) sqlMapClient
									.queryForObject(
											"awardSheetVerification.getAPRCount",
											input);

							if (Integer.parseInt(countAPR.getTotal()) <= 0) {
								UnProcessedStduent errorBean = new UnProcessedStduent();
								errorBean.setRollNumber(courseList.get(i).getCourseCode());
								errorBean.setProcessed("Award Blank Not Approved By Higher Approval Order Authorities");
								errorList.add(errorBean);
								bool = false;
							}
						}
					}

					System.out.println("total course record "
							+ total);
					System.out.println("total rejected record "
							+ errorList.size());
					System.out.println("is completed " + bool);
					countList = new CountProcessRecorList(total,
							total - errorList.size(),
							errorList.size(), bool, errorList);
			}
						}

						// countList contains
						// 1-totalRecord, 2-correctProcessed, 3-rejectedRecord,
						// 4-inErrorRecord and 5-activityCompletedflag

						// If return flag is false for running activity, it will
						// be considered in ERROR
						// If at least one record is in exception in activity,
						// false will be returned
						if (countList.isActivityCompleted()) {
							startActivityBean
									.setProcessStatus(CRConstant.COMPLETE_STATUS);
							startActivityBean
									.setActivityStatus(CRConstant.COMPLETE_STATUS);
						} else {
							startActivityBean
									.setProcessStatus(CRConstant.ERROR_STATUS);
							startActivityBean
									.setActivityStatus(CRConstant.ERROR_STATUS);
						}

						// status of activity and process will be updated
						new UpdateActivityAndProcessMaster(sqlMapClient,
								transactionTemplate)
								.updateActivityStatus(startActivityBean);


		}// Main try closed
		catch (Exception e) {
			logger.info("Process Can't be started" + e.getMessage());
			// return countList;
		}
		finally {

				String processFlag = CRConstant.INACTIVE_STATUS;
				boolean processedFlag = false;

				if (countList.getTotalRecords() == countList
						.getCorrectProcessed()
						&& countList.getTotalRecords() != 0) {
					System.out.println("Coming here inside final ");
					processFlag = CRConstant.COMPLETE_STATUS;
					processedFlag = true;
				}

				errorLogs.setActualRecord(countList.getTotalRecords());
				errorLogs.setProcessRecord(countList
						.getCorrectProcessed());
				errorLogs.setFailrecord(countList.getInError()
						+ countList.getRejectedProcessed());
				errorLogs.setRejectedValue(countList
						.getRejectedProcessed());
				errorLogs.setProcessFlag(processFlag);


			try {
				sqlMapClient.insert("studentTrackingAndLogs.insertBatchLogs",
						errorLogs);
			} catch (Exception e1) {
				System.out.println("Exception is" + e1.getMessage());
			}
		}

		return countList;
	}// method ends

	/**
	 * It returns ACTIVE session start date and end date according to university
	 *
	 * @param universityId
	 * @return
	 */
	public List<StartActivityBean> getSessionDate(String universityId) {
		// TODO Auto-generated method stub
		List<StartActivityBean> sessionDateList = new ArrayList<StartActivityBean>();
		StartActivityBean startActivityBean = new StartActivityBean();
		startActivityBean.setUniversityId(universityId);

		try {
			sessionDateList = sqlMapClient.queryForList(
					"startActivity.getSessionDate", startActivityBean);
			System.out.println(sessionDateList);
		} catch (Exception e) {
			System.out.println("Exception e" + e.getMessage());
		}

		return sessionDateList;
	}

	/**
	 * It retruns semester start and end date according to active session
	 *
	 * @param startActivityKey
	 * @return
	 */
	public List<StartActivityBean> getSemesterDate(
			StartActivityBean startActivityKey) {

		// TODO Auto-generated method stub
		List<StartActivityBean> semesterDateList = new ArrayList<StartActivityBean>();

		try {
			semesterDateList = sqlMapClient.queryForList(
					"startActivity.getSemesterDate", startActivityKey);
			System.out.println(semesterDateList);
		} catch (Exception e) {
			System.out.println("Exception e" + e.getMessage());
		}

		return semesterDateList;
	}

	//added by ankit.
	// it will build Duplicate Student PDF
	protected void buildPdfDocument(String filePath, List<StudentInfoGetter> duplicateStudentList,
			String sessionStartDate, String sessionEndDate, String universityName) throws Exception
	{
		Document document = new Document();
		PdfWriter writer=null;

		String sep = System.getProperty("file.separator");

		writer = PdfWriter.getInstance(document,new FileOutputStream(filePath+sep+"Duplicate_Student_List"+".pdf"));
		document.setPageSize(PageSize.A4.rotate());


		Phrase header1= new Phrase(universityName.toUpperCase(),FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,
				new Color(0, 0, 0)));
		Phrase header2= new Phrase("\nDUPLICATE STUDENTS OF SESSION : "+ sessionStartDate.substring(0, 4)+"-"+
				sessionEndDate.substring(0, 4),FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,new Color(0, 0, 0)));

		Phrase headers=new Phrase();
        headers.add(header1);
        headers.add(header2);

		HeaderFooter header = new HeaderFooter(headers,false);
		header.setAlignment(Element.ALIGN_CENTER);
		header.setBorderWidth(0);
        document.setHeader(header);

        Date printingDate = new Date();
        String toDay = DateFormat.getDateTimeInstance().format(printingDate);

        HeaderFooter footer = new HeaderFooter(new Phrase("Date : "+toDay),false);
        footer.setBorderWidth(0);
        document.setFooter(footer);

        document.open();

//		duplicateStudentList = (List <StudentInfoGetter>) model.get("duplicateStudentList");

		StudentInfoGetter finalSemesterResultStatistics=new StudentInfoGetter();
		finalSemesterResultStatistics.setProgramCourseKey(duplicateStudentList.get(0).getProgramCourseKey());
		finalSemesterResultStatistics.setProgramId(duplicateStudentList.get(0).getProgramId());
		finalSemesterResultStatistics.setBranchId(duplicateStudentList.get(0).getBranchId());
		finalSemesterResultStatistics.setSpecializationId(duplicateStudentList.get(0).getSpecializationId());
		finalSemesterResultStatistics.setSemesterCode(duplicateStudentList.get(0).getSemesterCode());

		Font cellFont = new Font(Font.HELVETICA, 8);
		PdfPTable studentTable = new PdfPTable(new float[] {4,5,5,5,3,3,2,4,5,4,3});
		studentTable.setWidthPercentage(100f);
		PdfPCell c1 = new PdfPCell(new Phrase("", cellFont));
			addCell(c1, cellFont, studentTable,"Registration Number");
			addCell(c1, cellFont, studentTable,"Student Name");
			addCell(c1, cellFont, studentTable,"Father's Name");
			addCell(c1, cellFont, studentTable,"Mother's Name");
			addCell(c1, cellFont, studentTable,"Date of Birth");
			addCell(c1, cellFont, studentTable,"Category");
			addCell(c1, cellFont, studentTable,"Gender");
			addCell(c1, cellFont, studentTable,"Program");
			addCell(c1, cellFont, studentTable,"Branch");
			addCell(c1, cellFont, studentTable,"Specialization");
			addCell(c1, cellFont, studentTable,"Semester");

			for(int i=0;i<duplicateStudentList.size();i++){
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getRegistrationNumber());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getStudentName());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getFatherName());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getMotherName());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getDateOfBirth());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getCategory());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getGender());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getProgramName());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getBranchName());
				addCell(c1, cellFont, studentTable, duplicateStudentList.get(i).getNewSpecialization());
				addCell(c1, cellFont, studentTable, duplicateStudentList.get(i).getSemester());
			}

			document.add(studentTable);
			document.close();

			// Create a PDFFile from a File reference
			File f = new File(filePath+sep+"Duplicate_Student_List"+".pdf");

			FileInputStream fis =	new FileInputStream(f);
			byte[] pdfContent = new byte[fis.available()];
			fis.read(pdfContent,0, fis.available());
			ByteBuffer bb = ByteBuffer.wrap(pdfContent);
			PDFFile pdfFile =
			new PDFFile(bb); // Create PDF Print Page

			PDFPrintPage pages =new PDFPrintPage(pdfFile);
			PrinterJob pjob = PrinterJob.getPrinterJob();
			PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
			pjob.setJobName(f.getName());
			Book book =	new Book();
			book.append(pages, pf, pdfFile.getNumPages());
			pjob.setPageable(book);
			pjob.print();
	}


	//added by ankit
	public static final void addCell(PdfPCell c1, Font cellFont, PdfPTable chartTable, String s) {
		try {
			c1 = new PdfPCell(new Phrase(s, cellFont));
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setBorderWidth(1);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setVerticalAlignment(Element.ALIGN_TOP);
		chartTable.addCell(c1);
	}

	public List<StartActivityBean> getMailUsers() {
		List<StartActivityBean> mailUsers = null;
		try{
			mailUsers = sqlMapClient.queryForList("startActivity.getMailUsers");
		}catch(Exception ex){
			logger.info("Exception in getting mail users");
			logger.error(ex);
		}
		return mailUsers;
	}
}
