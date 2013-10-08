/**
 * @(#) StudentUpload.java
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
package in.ac.dei.edrp.cms.controller.manualprocess;
import in.ac.dei.edrp.cms.dao.manualprocess.StudentUploadDao;
import in.ac.dei.edrp.cms.domain.manualprocess.ErrorLogBean;
import in.ac.dei.edrp.cms.domain.manualprocess.StudentUploadBean;
import in.ac.dei.edrp.cms.utility.GeneralUtility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for Result file upload process
 * 
 * @version 1.0 15 Oct 2011
 * @author Nupur Dixit
 */
public class StudentUpload extends MultiActionController{

	/** creating object of StudentUploadDao interface */
	private StudentUploadDao studentUploadDao;

	/** defining setter method for object of StudentUploadDao interface */
	public void setStudentUploadDao(StudentUploadDao studentUploadDao) {
		this.studentUploadDao = studentUploadDao;
	}
	
	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(StudentUpload.class);

	/**
	 * This method upload the result file records into seven tables of database and map the result info to a jsp
	 * student_master,enrollment_personal_details,student_program,SRSH,student_marks_summary,student_course,student_aggregate are table's name
	 * @param request
	 * @param response
	 * @return ModelAndView containing success/failure Info
	 */
	@SuppressWarnings({ "unused", "hiding" })
	public ModelAndView uploadStudent(HttpServletRequest request,HttpServletResponse response) {
		String sep = System.getProperty("file.separator");
		
		String fileName = request.getParameter("fileName");
		String newFileName = getServletContext().getRealPath(File.separator)+"WEB-INF"+sep+"input"+sep+fileName;
//		System.out.println("new file name "+newFileName);
		String processedFolderName = getServletContext().getRealPath(File.separator)+"WEB-INF"+sep+"processed"+sep;
//		System.out.println("new folder name "+processedFolderName);
		File inputFile=null;
		File outputFolder = null;
		String processFileName = "";
		String entityId = request.getParameter("entityId");
		String programId = request.getParameter("programId");
		String programCode = request.getParameter("programCode");
		String branchId = request.getParameter("branchId");
		String specializationId = request.getParameter("specializationId");
		String semesterCode = request.getParameter("semesterCode");
		String session = request.getParameter("session");
//		String semesterId = semesterCode.substring(1).trim();
		String sessionStartDate = session.substring(0,4).concat("-07-01");
		String sessionEndDate = "20".concat(session.substring(5)).concat("-06-30");
		processFileName = processFileName.concat(programCode).concat("_"+branchId).concat("_"+semesterCode).concat("_"+session);
		String rollNo = request.getParameter("rollNo").trim();
		//=================define local variables ==========================
		String line = null;
		String programCodeFile = null;
		String branchIdFile = null;
		String semesterSequenceFile;
		String semesterCodeFile;
		String specializationIdFile;
		String rollNumber;
		String enrollmentNumber;
		String studentName;
		String gender;
		String attemptNumber = null;		
		String CGPA = null;
		String resultCode;
		String finalSemesterCodeFile;
		String yearOfRegistration;
		String category;		
		String courseGroup = null;
		String programCourseKey = null;
		String semesterStartDate = null;		
		String semesterEndDate = null;
		String courseCode;
		String originalCourseCode = null;
		String courseStatus;
		String status = "false";
		
		List<String> previousPercentage = new ArrayList<String>();
		List<StudentUploadBean> studentRecords = new ArrayList<StudentUploadBean>();
		List<StudentUploadBean> courseList = new ArrayList<StudentUploadBean>();
		List<StudentUploadBean> remedialCourseList = new ArrayList<StudentUploadBean>();
		List<StudentUploadBean> studentCourseList = new ArrayList<StudentUploadBean>();
		
		int i=0,j=0;
		
		String studentStatus = null ;
		String statusSRSH = null;
		String statusStudentProgram = null;
		int noOfRemedials = 0;
		String internalMarks  = null;
		String externalMarks = null;
		String cummulativeMarks = null;
		String internalGrades  = null;
		String externalGrades = null;
		String totalGrades = null;
		String finalGradePoint = null;
		String registrationDate = null;
		/////Hard Coded
		String creatorId = "manualProcess";
		String universityId = "0001";
		String errorMessage = "";
		//================set variables in bean================
		StudentUploadBean sub = new StudentUploadBean();
		sub.setEntityId(entityId);
		sub.setProgramCode(programCodeFile);
		sub.setProgramId(programId);
		System.out.println(sub.getProgramId());
		sub.setBranchId(branchId);
		sub.setSpecializationId(specializationId);
		sub.setSemesterCode(semesterCode);
		sub.setSessionStartDate(sessionStartDate);
		sub.setSessionEndDate(sessionEndDate);
		//========check for the result file name availability====================================
		try{
			BufferedReader br = null;
			inputFile = new File(newFileName);
			 br = new BufferedReader(new FileReader(newFileName));
		}catch(FileNotFoundException ex){
			errorMessage = "file with the specified name is not available (file: "+fileName+" not found)";
			return new ModelAndView("ManualProcess/MyException","exception",errorMessage);
		}
		//========calculate program course key===================================================
		try{
		sub = studentUploadDao.getPrgCourseKey(sub);
		if(sub.getProgramCourseKey()!=null){
			programCourseKey = sub.getProgramCourseKey();
		}
		else{
			errorMessage = "program course key not found for the given combination of program, branch and specialization";
			return new ModelAndView("ManualProcess/MyException","exception",errorMessage);
		}
//		System.out.println("program course key is "+sub.getProgramCourseKey());	
		}catch(Exception e){
			logObj.error("inside program course key computation"+e.getMessage());
			System.out.println("inside program course key computation"+e.getMessage());
		}
		
		//========================================================================================
		//======calculate semester start date and end date =======================================
		try{
			sub = studentUploadDao.getSemesterDates(sub);
			if(sub.getSemesterStartDate()!=null){
				semesterStartDate = sub.getSemesterStartDate();
				semesterEndDate = sub.getSemesterEndDate();
			}
			else{
				errorMessage = "Semester start date and end date is not specified in program_registration table for the given session";
				return new ModelAndView("ManualProcess/MyException","exception",errorMessage);
			}
			System.out.println("semester start date end date is "+sub.getSemesterStartDate() +" : "+sub.getSemesterEndDate());	
			}catch(Exception e){
				logObj.error("inside semester date computation"+e.getMessage());
				System.out.println("inside semester date computation"+e.getMessage());
			}
		//=========================================================================================
		//====================final semester code from program_term_detail==========================
			String finalSemesterCode = null;
			try{
				sub = studentUploadDao.getFinalSemester(sub);
				if(sub.getFinalSemesterCode()!=null){
					finalSemesterCode = sub.getFinalSemesterCode();
				}
				else{
					errorMessage = "final semester code is not specified in program_term_details ";
					return new ModelAndView("ManualProcess/MyException","exception",errorMessage);
				}
				}catch(Exception e){
					logObj.error("inside final semester code computation"+e.getMessage());
					System.out.println("inside final semester code computation"+e.getMessage());
				}	
		//==========================================================================================		
		BufferedReader br = null;
		try {
			try{
				inputFile = new File(newFileName);
				outputFolder = new File(processedFolderName);				
				File filenames[] = outputFolder.listFiles(); //make array of filenames.
				String inputFileName = inputFile.getName().concat("_"+programCode).concat("_"+branchId).concat("_"+semesterCode).concat("_"+session);
				 for (int k = 0; k < filenames.length; k++) {
			            if (filenames[k].isFile()) {			            	
//			                System.out.println(filenames[k].getName());
			                if(filenames[k].getName().equalsIgnoreCase(inputFileName)){
			                	errorMessage = "You already have processed this file with the specified settings)";
			    				return new ModelAndView("ManualProcess/MyException","exception",errorMessage);
			                }			               			 
			            }
			        }
			 br = new BufferedReader(new FileReader(newFileName));
			}catch(FileNotFoundException ex){
				errorMessage = "file with the specified name is not available (file: "+fileName+" not found)";
				return new ModelAndView("ManualProcess/MyException","exception",errorMessage);
			} catch (IOException ex) {
				errorMessage = "file: "+fileName+" have some IO error: Kindly Check all the conditions";
				return new ModelAndView("ManualProcess/MyException","exception",errorMessage);
	        }

		outer:	while((line=br.readLine())!=null){
				String division = null;
				programCodeFile = line.substring(1,3);
				branchIdFile = line.substring(3, 5);
				semesterSequenceFile = line.substring(5, 6);
				semesterCodeFile = "SM".concat(semesterSequenceFile);
//				System.out.println("semester code file "+semesterCodeFile);
				specializationIdFile = line.substring(49, 51);
				if(specializationIdFile.equalsIgnoreCase("  ")){
					specializationIdFile = "00";
				}
				yearOfRegistration = line.substring(83, 85).trim();
           	 	int initialregis = (Integer.valueOf(yearOfRegistration)+2000);
           	 	String lastregis = "-"+String.format("%02d",(initialregis+1)-2000);
           	 	yearOfRegistration = String.valueOf(initialregis).concat(lastregis);           	 	          	 
           	 	rollNumber = line.substring(6, 12);        	 	
        	 	boolean  checkConditionForRollNo ;          	 
        	 	if(rollNo.length()!=0){           	 		
        	 		 checkConditionForRollNo = rollNumber.equalsIgnoreCase(rollNo);
        	 		 System.out.println("roll no thru input "+rollNo +" boolean check "+checkConditionForRollNo+" for rollNo "+rollNumber);
        	 	}
        	 	else{
        	 	checkConditionForRollNo = true;
        	 	}
           	 	if(yearOfRegistration.trim().equalsIgnoreCase(session.trim()) && programCode.trim().equalsIgnoreCase(programCodeFile.trim())
           	 			&& branchId.trim().equalsIgnoreCase(branchIdFile.trim())&& specializationId.trim().equalsIgnoreCase(specializationIdFile)
           	 			&& semesterCode.equalsIgnoreCase(semesterCodeFile)&& checkConditionForRollNo){
	           	 	rollNumber = line.substring(6, 12);
	           	 	registrationDate = "20".concat(rollNumber.substring(0,2).concat("-07-01"));
//	           	 	System.out.println("registration date with roll no "+registrationDate +" : "+rollNumber );
					studentName = line.substring(12, 46);
					gender = line.substring(47, 48);
//					System.out.println("inside student upload gender "+gender);
					attemptNumber = line.substring(48, 49);
					enrollmentNumber = line.substring(93, 99);
//					System.out.print("inside true condition of enrollment no "+enrollmentNumber);
					for(i=51;i<74;i=i+3){
						if(!(line.substring(i, i+3).trim().equalsIgnoreCase("")) && GeneralUtility.isValidNumber(line.substring(i, i+3))){
							previousPercentage.add(String.valueOf(Float.parseFloat(line.substring(i, i+3))/100));
						}
						else{
							previousPercentage.add(line.substring(i, i+3));
						}
					}
					String currentSemSGPA = previousPercentage.get(Integer.parseInt(semesterSequenceFile)-1);
					if(currentSemSGPA.trim().length()==0){
						System.out.println("student fail so 0.0 sgpa");
						currentSemSGPA = "0.0";
					}
					System.out.println("current sem SGPA will go in SRSH and student_aggregate "+currentSemSGPA);
					CGPA = line.substring(75, 78);
					finalSemesterCodeFile = line.substring(82, 83);
           	 		if(finalSemesterCodeFile.equalsIgnoreCase("F")){
						if(CGPA.trim().length()!=0){
							CGPA = String.valueOf(Float.parseFloat(CGPA)/100);
						}
						else{							
							CGPA=null;
						}	           	 		
	           	 		sub.setCGPA(CGPA);
	           	 		try{
	           	 			if(CGPA!=null && CGPA!=""){
	           	 				sub = studentUploadDao.getDivisionFromCGPA(sub);
	           	 				if(sub.getDivision()!=null){
	           	 					division = sub.getDivision();
	           	 				}
	           	 				else{
	           	 					errorMessage = "division for the corresponding CGPA is not in CGPA_division table ";
	           	 					logObj.error("Student with roll no:"+rollNumber+" & enrollment no :"+enrollmentNumber);
	           	 					logObj.error(errorMessage);	           	 					
	           	 					continue;
	           	 				}
	           	 			}
	           	 			else{
	           	 				division = null;
	           	 			}
//	           	 			System.out.println("division for roll number :"+rollNumber+" is "+division+" with cgpa "+CGPA);
	           	 		}catch(Exception e){
	           	 			logObj.error("inside CGPA division computation"+e.getMessage()+"for roll no :"+rollNumber);	           	 			
	           	 			continue;
	           	 		}	
           	 		}
           	 		else{
		           	 	if(CGPA.trim().length()==0){
		           	 		CGPA=null;
		           	 	}
           	 		}           	 		
	           	 	resultCode = line.substring(79, 80);
					category = line.substring(91, 93);				
					i=j=0;
					noOfRemedials = 0;
			inner:		for(i=112;i<368;i+=16){
						courseCode = line.substring(i, i+6);
						courseGroup = courseCode.substring(0, 3);
						j=i+6;
						internalGrades = line.substring(j, j+3).trim();
						if(internalGrades.length()==0){
							internalGrades = null;
						}
						else if(internalGrades.equalsIgnoreCase("abs")){
							internalGrades = "ABS";
						}
//						System.out.println("internal grades "+internalGrades);
						j=j+3;	
						externalGrades = line.substring(j, j+3).trim();
						if(externalGrades.length()==0){
							externalGrades = null;
						}
						else if(externalGrades.equalsIgnoreCase("abs")){
							externalGrades = "ABS";
						}
//						System.out.println("external grades "+externalGrades);
						j = j+3;
						totalGrades = line.substring(j, j+3).trim();
						if(totalGrades.length()==0){
							finalGradePoint = null;
						}
						else if(GeneralUtility.isValidNumber(totalGrades)){
							double totGrades = Double.parseDouble(totalGrades);								
							if(totGrades==0){
								finalGradePoint = "0.00";
							}
							else{
								double computedGrade = totGrades/100;								
								DecimalFormat df = new DecimalFormat("0.00");
								finalGradePoint = df.format(computedGrade);
							}
						}
						else{
							finalGradePoint = "ABS";
						}
//						System.out.println("final grade point "+finalGradePoint);						
						String remStar = line.substring(i+15, i+16).trim();						
						if(Integer.parseInt(semesterSequenceFile)%2!=0){
							if(resultCode.equalsIgnoreCase("R")){
								System.out.println("remedial case "+resultCode);
								if(remStar.equalsIgnoreCase("*")){
									System.out.println("inside * case ");
									studentStatus = "REM";
									noOfRemedials +=1;
									System.out.println("no of remedials "+noOfRemedials);
								}
								else{
									studentStatus = "PAS";
								}								
								statusSRSH = "REM";
								if(finalSemesterCodeFile.equalsIgnoreCase("f")){
									statusStudentProgram = "REM";
								}
								else{
									statusStudentProgram = "ACT";
								}				
							}
							else if(resultCode.equalsIgnoreCase("F")){
								System.out.println("fail case "+resultCode);
								if(remStar.equalsIgnoreCase("*")){
									System.out.println("inside * case ");
									studentStatus = "FAL";
								}
								else{
									studentStatus = "PAS";
								}
								if(finalSemesterCodeFile.equalsIgnoreCase("f") && Integer.parseInt(attemptNumber)==2){
									statusStudentProgram = "FAL";
								}
								else if(Integer.parseInt(attemptNumber)==2){
									// entry in student_program table
									statusStudentProgram = "FAL";
								}
								else{					
									statusStudentProgram = "ACT";
								}								
								statusSRSH = "FAL";
//								statusStudentProgram = "FAL";
							}
							else if(resultCode.equalsIgnoreCase("P")){
								studentStatus = "PAS";
								statusSRSH = "PAS";
								if(finalSemesterCodeFile.equalsIgnoreCase("f")){
									statusStudentProgram = "PAS";
								}
								else{
									statusStudentProgram = "ACT";
								}												
							}
							else{
								errorMessage = "is in odd semester and has result code other than P,F & R ";
	        					logObj.error("Student with roll no:"+rollNumber+" & enrollment no :"+enrollmentNumber);
	        					logObj.error(errorMessage);
	        					continue outer;
							}
						}
						else if(Integer.parseInt(semesterSequenceFile)%2==0){
//							System.out.println("inside even : result code is "+resultCode);
//							System.out.println("i is "+i + "attempt number is "+attemptNumber);
							if(resultCode.equalsIgnoreCase("F")){
								if(remStar.equalsIgnoreCase("*")){
									studentStatus = "FAL";									
								}
								else{
									studentStatus = "PAS";
								}
								if(finalSemesterCodeFile.equalsIgnoreCase("f") && Integer.parseInt(attemptNumber)==2 ){
									statusStudentProgram = "FAL";
								}
								else if(Integer.parseInt(attemptNumber)==2){
									// entry in student_program table
									statusStudentProgram = "FAL";
								}
								else{					
									statusStudentProgram = "ACT";
								}									
								statusSRSH = "FAL";
//								statusStudentProgram = "FAL";
							}
							else if(resultCode.equalsIgnoreCase("P")){
								studentStatus = "PAS";
								statusSRSH = "PAS";
								if(semesterCode.equalsIgnoreCase(finalSemesterCode)){
									statusStudentProgram = "PAS";
								}
								statusSRSH = "PAS";
								if(finalSemesterCodeFile.equalsIgnoreCase("f")){
									statusStudentProgram = "PAS";
								}
								else{
									statusStudentProgram = "ACT";
								}							
							}
							else{
								errorMessage = "is in even semester and has result code other than P & F  ";
	        					logObj.error("Student with roll no:"+rollNumber+" & enrollment no :"+enrollmentNumber);
	        					logObj.error(errorMessage);
	        					continue outer;
							}
						}
						
						courseStatus = "REG";
						courseList.add(new StudentUploadBean( courseCode, originalCourseCode, courseStatus, internalMarks, externalMarks, cummulativeMarks,
								studentStatus,courseGroup,internalGrades,externalGrades,finalGradePoint));
						//	System.out.print(courseCode+ " "+internalMarks+" "+externalMarks+" "+cummulativeMarks+" "+line.substring(i+15, i+16)+" "+studentStatus);

			}//end for :inner

					if(Integer.parseInt(semesterSequenceFile)%2==0){
//						System.out.println("inside even : result code is "+resultCode);
//						System.out.println("i is "+i + "attempt number is "+attemptNumber);
						if((resultCode.equalsIgnoreCase("P"))|| (resultCode.equalsIgnoreCase("F"))){
							int k,l=0;
							for(k=368;k<398;k+=10){
								courseCode = line.substring(k, k+6).trim();
								if(courseCode.length()==0){
									continue;
								}
								courseGroup = courseCode.substring(0, 3);
								l=k+6;
								totalGrades = line.substring(l, l+3).trim();
								if(totalGrades.length()==0){
									finalGradePoint = null;
								}
								else if(GeneralUtility.isValidNumber(totalGrades)){
									double totGrades = Double.parseDouble(totalGrades);	
//									System.out.println("total grades  "+totGrades);
									if(totGrades==0){
										finalGradePoint = "0.00";
									}
									else{
										double computedGrade = totGrades/100;
//										System.out.println("computed grade point  "+computedGrade);
										DecimalFormat df = new DecimalFormat("0.00");
										finalGradePoint = df.format(computedGrade);
									}
								}
								else{
									finalGradePoint = "ABS";
								}
								remedialCourseList.add(new StudentUploadBean( courseCode,courseGroup,internalGrades,externalGrades,finalGradePoint));
//								System.out.println("final grade point in remedial "+finalGradePoint);
							}//end for
						}
					}
//					System.out.println("remedial course list length "+remedialCourseList.size());
					studentCourseList.add(new StudentUploadBean(universityId, rollNumber, programCode, branchId,specializationId, semesterSequenceFile, semesterStartDate, semesterEndDate,
							courseList,remedialCourseList, studentStatus, creatorId, attemptNumber));
					ErrorLogBean errorLogBean = new ErrorLogBean(programId,branchId,semesterCode,specializationId,session,rollNumber,
							fileName,creatorId,enrollmentNumber);
					studentRecords.add(new StudentUploadBean(universityId,entityId,programId,branchId,semesterCode,specializationId,programCode,
							programCourseKey,rollNumber,enrollmentNumber,gender,studentName,attemptNumber,currentSemSGPA, CGPA,resultCode,noOfRemedials,
							statusSRSH, statusStudentProgram, finalSemesterCode, yearOfRegistration, division,
							category,semesterStartDate,semesterEndDate,registrationDate,courseList,remedialCourseList,creatorId,
							sessionStartDate,sessionEndDate,errorLogBean));
					courseList = new ArrayList<StudentUploadBean>();
					remedialCourseList = new ArrayList<StudentUploadBean>();		
					previousPercentage = new ArrayList<String>();			
				}				
           	 	else{
           	 		continue;
           	 	}
			}//end while
           	 br.close();
				String result ="";

/*	
 * commented code of lines are used when marks are present in the result file rather than grades			
				else{
					for(i=112;i<368;i+=16){
						courseCode = line.substring(i, i+6);
						courseGroup = courseCode.substring(0, 3);
						j=i+6;
						internalMarks = line.substring(j, j+3).trim();
						if(internalMarks.length()==0){
							result = "0";
						}
						else{
							result  = GeneralUtility.isValidNumberMarks(internalMarks);
						}
						if(result.equalsIgnoreCase("-1")){
							internalGrades = "-1";
							internalMarks  = "-1";
						}
						else if(result.equalsIgnoreCase("0") ){
							internalGrades = null;
							internalMarks  = null;
						}
						else{
							internalGrades = null;
						}
						j=j+3;	
						externalMarks = line.substring(j, j+3).trim();
						if(externalMarks.length()==0){
							result = "0";
						}
						else{
							result  = GeneralUtility.isValidNumberMarks(externalMarks);
						}
						if(result.equalsIgnoreCase("-1")){
							externalGrades = "-1";
							externalMarks  = "-1";
						}
						else if(result.equalsIgnoreCase("0") ){
							externalGrades = null;
							externalMarks  = null;
						}
						else{
							externalGrades = null;
						}
						j = j+3;
						cummulativeMarks = line.substring(j, j+3).trim();
						if(!GeneralUtility.isValidNumber(cummulativeMarks))
						{
							totalGrades = cummulativeMarks;	
							cummulativeMarks = null;
						}

						if(line.substring(i+15, i+16).equalsIgnoreCase("*"))
						{
							studentStatus = "REM";
						}
						courseStatus = "ACT";
						courseList.add(new StudentUploadBean( courseCode, originalCourseCode, courseStatus, internalMarks, externalMarks, cummulativeMarks,
								studentStatus,courseGroup,internalGrades,externalGrades,totalGrades));
						//	System.out.print(courseCode+ " "+internalMarks+" "+externalMarks+" "+cummulativeMarks+" "+line.substring(i+15, i+16)+" "+studentStatus);

					}
				}
				System.out.println("semester code and final semester code "+semesterCode+" : "+finalSemesterCode);
 */
			String resultValidation = "true";
			if(studentRecords.size()>0){
				logObj.error("inside processing checking for log4j implementation");
				if(!semesterCode.equalsIgnoreCase("SM1")){
					resultValidation = studentUploadDao.validateRecords(studentRecords);					
				}				
				if(resultValidation.equalsIgnoreCase("false")){
					status="Some validations are not working, kindly view result_file_upload_error_log table for more information";			
				}
				else{
					if(!semesterCode.equalsIgnoreCase(finalSemesterCode)){
						if(semesterCode.equalsIgnoreCase("SM1")){
								 status = studentUploadDao.firstSemesterEntry(studentCourseList, studentRecords,getServletContext().getRealPath(File.separator));						
						}
						else{
							 status = studentUploadDao.midSemesterEntry(studentCourseList,studentRecords,getServletContext().getRealPath(File.separator));
						}
					}
					else{
						 status = studentUploadDao.finalSemesterEntry(studentCourseList, studentRecords,getServletContext().getRealPath(File.separator));
					}
				}
		
		System.out.println("status "+status);
		logObj.info("\n after processing status "+status);
		}
		else{
			status="No record in the result file is available with the specified data";
		}
		} catch (Exception e) {
			logObj.error("\n Exception Inside controller :"+e.getCause());
		}
		if(status.equalsIgnoreCase("true")){
			// Move file to new directory
			boolean success = inputFile.renameTo(new File(outputFolder, inputFile.getName().concat("_").concat(processFileName)));
			if (!success) {
				System.out.println("file was not succesfully moved ");
			    // File was not successfully moved
			}
			errorMessage = status;
		}
		else if(status.equalsIgnoreCase("false")){
			errorMessage = "There is some error kindly view the error log table for more clarification";
		}
		else{
			errorMessage = status;
		}
		return new ModelAndView("ManualProcess/MyException","exception",errorMessage);
	}

	
}
