/*
 * @(#) AwardSheetDaoImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.awardsheet;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.awardsheet.AwardSheetDao;
import in.ac.dei.edrp.cms.daoimpl.employee.sendmail;
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramMasterInfoGetter;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramTermDetailsInfoGetter;

import org.apache.log4j.Logger;

import org.apache.poi.poifs.filesystem.OfficeXmlFileException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;
import org.xml.sax.SAXException;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.xml.bind.JAXBException;

import jxl.Sheet;
import jxl.Workbook;


/**
 * Implementation class of AwardSheetDao interface
 * @author Manpreet Kaur
 * @date 20-03-2011
 * @version 1.0
 */
public class AwardSheetDaoImpl extends SqlMapClientDaoSupport
    implements AwardSheetDao {
    private static Logger logObj = Logger.getLogger(AwardSheetDaoImpl.class);
    TransactionTemplate transactionTemplate = null;
    String sep=System.getProperty("file.separator");
    ResourceBundle resourceBundle = ResourceBundle.getBundle("in"+sep+"ac"+sep+"dei"+sep+"edrp"+sep+"cms"+sep+"databasesetting"+sep+"MessageProperties",
			new Locale("en", "US"));
    
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * Method for getting course list of logged in user
     * @param inputObj
     * @return List of courses
     */
    @SuppressWarnings("unchecked")
    public List<AwardSheetInfoGetter> getCourseList(AwardSheetInfoGetter inputObj) {
    	List<AwardSheetInfoGetter> courseList=null;
    	try{
    		logObj.info("getCourseList");
    		//Change Done By Dheeraj For Allowing Access To Examination Dept. For Entering External And Remedial Marks
    		if(inputObj.getDisplayType().equalsIgnoreCase("I")){
    			courseList = getSqlMapClientTemplate().queryForList("AwardSheet.getCourseList", inputObj);
    		}else{
    			courseList = getSqlMapClientTemplate().queryForList("AwardSheet.getCourseListForRemedialAndExternal", inputObj);
    		}
    	}
    	catch (Exception e) {
			logObj.error("getCourseList");
		}
        return courseList;
    }

    /**
     * Method for getting evaluation components
     * @param inputObj
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<AwardSheetInfoGetter> getEvaluationComponents(
        AwardSheetInfoGetter inputObj) {
    	List<AwardSheetInfoGetter> compList=null;
    	try{
    		logObj.info("getEvaluationComponents");
    		compList = getSqlMapClientTemplate().queryForList("AwardSheet.getEvaluationComponent", inputObj);
    	}
        catch (Exception e) {
        	logObj.error("getEvaluationComponents  "+e);
		}
        return compList;
    }

    /**
     * Method for getting list of students whose marks are to be entered for given course
     * @param inputObj
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<AwardSheetInfoGetter> getStudentList(
        ProgramMasterInfoGetter inputObj) {
    	List<AwardSheetInfoGetter> studentList=null;
		try{
			logObj.info("getStudentList");
			// add code to get attempt number also			
	    	if(inputObj.getDisplayType().equalsIgnoreCase("R")){
	    		inputObj.setSystemValue("REM");
	    		studentList= getSqlMapClientTemplate().queryForList("AwardSheet.getStudentList", inputObj);
	    	}
	    	else{
	    		studentList= getSqlMapClientTemplate().queryForList("AwardSheet.getStudentList1", inputObj);
	    	}
		}
		catch (Exception e) {
			logObj.error("getStudentList  "+e);
		} 

        return studentList;
    }

    /**
     * Method for getting marks of students
     * @param inputObj
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<AwardSheetInfoGetter> getStudentMarks(ProgramMasterInfoGetter inputObj) {
    	List<AwardSheetInfoGetter> progList = null;

        try {
        	logObj.info("getStudentMarks");
  	// Changed by Dheeraj for getting marks and grades based on display types
        	
        	if (inputObj.getDisplayType().equalsIgnoreCase("I")||inputObj.getDisplayType().equalsIgnoreCase("E")){
        		progList = getSqlMapClientTemplate().queryForList("AwardSheet.getStudentMarks", inputObj);
        	
        		if (progList.size()==0){  // If student Marks table is empty then get grades only
//        			progList = getSqlMapClientTemplate().queryForList("AwardSheet.getStudentgrade", inputObj);
        			return null;
        		}
        	}else{
        		progList = getSqlMapClientTemplate().queryForList("AwardSheet.getStudentMarks", inputObj);
        			if(progList.size()==0){
//        				progList = getSqlMapClientTemplate().queryForList("AwardSheet.getRemedialStudentgrade", inputObj);
        				return null;
        			}
        	}
            return progList;
        } catch (Exception e) {
        	logObj.error("getStudentMarks " + e);
        }

        return progList;
    }

    /**
     * Method for getting rules for groups
     * @param inputObj
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<ProgramTermDetailsInfoGetter> getRule(
        ProgramMasterInfoGetter inputObj) {
        try {
        	logObj.info("getRule");
            List<ProgramTermDetailsInfoGetter> progList = getSqlMapClientTemplate()
                                                              .queryForList("AwardSheet.getRule",
                    inputObj);

            return progList;
        } catch (Exception e) {
        	logObj.error("getrule " + e);
        }

        return null;
    }

    /**
     * Method for checking status of existing entry regarded given course
     * @param inputObj
     * @return String
     */
    @SuppressWarnings("unchecked")
	public String checkStatus(ProgramMasterInfoGetter inputObj) {
        try {
        	logObj.info("checkStatus");
        	 List<ProgramTermDetailsInfoGetter> empLevel = getSqlMapClientTemplate().queryForList("AwardSheet.getEmployeeLevel",inputObj);
             if (empLevel.size()>0) {
                 inputObj.setApprovalOrder(empLevel.get(0).getName());
             } else {
                 inputObj.setApprovalOrder("0");
             }

             List<ProgramTermDetailsInfoGetter> levelObject = getSqlMapClientTemplate().queryForList("AwardSheet.getNextGetter",inputObj);
             if (levelObject.size() > 0) {
                 inputObj.setApprovalOrder(levelObject.get(0).getName());
                 inputObj.setRequestGetter(levelObject.get(0).getCode());
                 inputObj.setRequestSender(inputObj.getEmployeeCode());
             }
        	
            ProgramTermDetailsInfoGetter statusList = (ProgramTermDetailsInfoGetter) getSqlMapClientTemplate().queryForObject("AwardSheet.checkStatus", inputObj);
            if(statusList==null){
            	// it means no entry exist in course_marks_approval_status table
            }
            else{
            	return statusList.getName(); // return status for Ex. SUB, APR, WDW
            }
        } catch (Exception e) {
        	logObj.error("checkStatus  "+e);
        }

        return null;
    }

    /**
     * Method for getting employee code
     * @param inputObj
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<AwardSheetInfoGetter> getEmployeeCode(String employeeId) {
        try {
        	logObj.info("getEmployeeCode");
            List<AwardSheetInfoGetter> employeeCode = getSqlMapClientTemplate().queryForList("AwardSheet.getEmployeeCode", employeeId);
            return employeeCode;
        } catch (Exception e) {
        	logObj.error("getEmployeeCode " + e);
        }

        return null;
    }

    /**
     * Method for getting Program Course Key
     * @param inputObj
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<ProgramTermDetailsInfoGetter> getProgramCourseKey(ProgramMasterInfoGetter inputObj) {
        try {
        	logObj.info("getProgramCourseKey");
            List<ProgramTermDetailsInfoGetter> programCourseKey = getSqlMapClientTemplate().queryForList("AwardSheet.getProgramCourseKey",inputObj);
            return programCourseKey;
        } catch (Exception e) {
        	logObj.error("getProgramCourseKey " + e);
        }

        return null;
    }

    /**
     * Method for getting List of pending request for given employee
     * @param inputObj
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<ProgramTermDetailsInfoGetter> getPendingList(
        ProgramMasterInfoGetter inputObj) {
        try {
        	inputObj.setSystemCode("SUB");
            List<ProgramTermDetailsInfoGetter> PendingList = getSqlMapClientTemplate().queryForList("AwardSheet.getPendingList", inputObj);

            return PendingList;
        } catch (Exception e) {
        	logObj.error("pending list " + e);
        }

        return null;
    }
    
    /**
     * Method for getting List of pending request for given employee
     * @param inputObj
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<ProgramTermDetailsInfoGetter> getApprovedList(
        ProgramMasterInfoGetter inputObj) {
        try {
        	logObj.info("getApprovedList");
        	inputObj.setSystemCode("APR");
            List<ProgramTermDetailsInfoGetter> approvedList = getSqlMapClientTemplate().queryForList("AwardSheet.getPendingList", inputObj);

            return approvedList;
        } catch (Exception e) {
            logObj.error("getApprovedList " + e);
        }

        return null;
    }

    /**
     * Method for insert/update student marks
     * @param input
     * @return String
     */
    public String saveStudentMarks(AwardSheetInfoGetter input, StringTokenizer data){
    	
    	try {
    		logObj.info("saveStudentMarks");
            ProgramTermDetailsInfoGetter datesList = (ProgramTermDetailsInfoGetter) getSqlMapClientTemplate().queryForObject("AwardSheet.getSemesterDates",input);
            input.setStartDate(datesList.getCode());
            input.setEndDate(datesList.getName());
            
            while (data.hasMoreTokens()) {
                StringTokenizer rowData = new StringTokenizer(data.nextToken(),"|");
                
                input.setRollNumber(rowData.nextToken());
                input.setEvaluationId(rowData.nextToken());

                String idType = rowData.nextToken();
 //======================================================================     
 //               commented out by Dheeraj
//                if (idType.equalsIgnoreCase("MK")) {
//                    input.setMarks(rowData.nextToken());
//                    input.setGrades(null);
//                    input.setPassfail(null);
//                } 
                
//=========================================================================      
      
                
              ///    Changes start by  Dheeraj  
              String marks = rowData.nextToken();
              System.out.println("Marks : " + marks);
              if (isInteger(marks)){
            	input.setAttendence("P");
              }else{
            	  marks = "0" ;
            	  input.setAttendence("ABS");
              }
              String total = "0";
              if(rowData.hasMoreTokens()){
              	total=rowData.nextToken();
//              	if(input.getDisplayType().equalsIgnoreCase("E")||input.getDisplayType().equalsIgnoreCase("R")){
//              	 if (isInteger(total)){
//                 	input.setAttendence("P");
//                   }else{
//                	   total = "0" ;
//                 	  input.setAttendence("ABS");
//                   }
//              	}
              }
              
              String grade = "";
              if(rowData.hasMoreTokens()){
              	grade=rowData.nextToken();
              }
                             
              if (total.equalsIgnoreCase("undefined")) {
                  total = "0";
              }

              if (grade.equalsIgnoreCase("undefined")) {
                  grade = "";
              }
              
              if (idType.equalsIgnoreCase("MK")) {
                  input.setMarks(marks);
                  if(input.getDisplayType().equalsIgnoreCase("I")||input.getDisplayType().equalsIgnoreCase("E")){
                  	input.setGrades(null);
                  }else{
                	  System.out.println("Dheeraj : " + grade);
                	  if(grade.equalsIgnoreCase("null")){
                		  grade="";
                	  }
                  	input.setGrades(grade);
                  }
                  input.setPassfail(null);
              } 
              
              
              
              ///    Changes end  by Dheeraj
                else if (idType.equalsIgnoreCase("GR")) {
                    input.setGrades(rowData.nextToken());
                    input.setPassfail(null);
                    input.setMarks(null);
                } 
                else if (idType.equalsIgnoreCase("PF")) {
                    input.setPassfail(rowData.nextToken());
                    input.setGrades(null);
                    input.setMarks(null);
                }
    
              if(rowData.hasMoreTokens()){
            	  String oldmark=rowData.nextToken();
            	  
            	  if(oldmark.equalsIgnoreCase("Z")){
            		  oldmark = null ;
            	  }else if (oldmark.equalsIgnoreCase("A")){
            		  oldmark = "-1";  // mark for absent. 
            	  }
            		  input.setOldmarks(oldmark);	  
            	  
                 
              }
              String mrkchg="";
             
              if(rowData.hasMoreTokens()){
            	   mrkchg = rowData.nextToken();
              }
             

              
              if (mrkchg.equalsIgnoreCase("C")){
            	  
              
                if ((idType.equalsIgnoreCase("MK"))||(idType.equalsIgnoreCase("GR"))||(idType.equalsIgnoreCase("PF"))){
                	
                            
                int rowsUpdated = getSqlMapClientTemplate().update("AwardSheet.updateStudentMarks",input);
                
                if (rowsUpdated < 1) {
                    getSqlMapClientTemplate().insert("AwardSheet.insertIntoStudentMarks", input);
                }
                }
              }
                
                // coomented out by Dheeraj
//                String total = "0";
      
//                
//                String grade = "";
//                if(rowData.hasMoreTokens()){
//                	grade=rowData.nextToken();
//                }
//                               
//                if (total.equalsIgnoreCase("undefined")) {
//                    total = "0";
//                }
//
//                if (grade.equalsIgnoreCase("undefined")) {
//                    grade = "";
//                }

                if (input.getDisplayType().equals("I")) {
                    input.setTotalInternal(total);
                    input.setInternalGrade(grade);
                    int rowChanged = getSqlMapClientTemplate().update("AwardSheet.updateStudentMarksSummaryInternal",input);

                    if (rowChanged < 1) {
                        getSqlMapClientTemplate().insert("AwardSheet.insertIntoStudentMarksSummaryInternal",input);
                    }
                } 
                else if (input.getDisplayType().equals("E")) {
                    input.setTotalExternal(total);
                    input.setExternalGrade(grade);
                    int rowChanged = getSqlMapClientTemplate().update("AwardSheet.updateStudentMarksSummaryExternal",input);

                    if (rowChanged < 1) {
                        getSqlMapClientTemplate().insert("AwardSheet.insertIntoStudentMarksSummaryExternal",input);
                    }
                } 
                else if (input.getDisplayType().equals("R")) {
                	input.setTotal(total);
                    input.setFinalGrade(grade);
                    int rowChanged = getSqlMapClientTemplate().update("AwardSheet.updateStudentMarksSummaryRemedial",input);

                    if (rowChanged < 1) {
                        getSqlMapClientTemplate().insert("AwardSheet.insertIntoStudentMarksSummaryRemedial",input);
                    }
                }
            }
        } catch (Exception e) {
        	logObj.error("saveStudentMarks: "+e);
            throw new MyException("E");
        }

        return null;
    }

    /**
     * Method for submitting award sheet for approval
     * @param inputObj
     * @return String
     */
    @SuppressWarnings({ "unchecked" })
    public String submitForApproval(AwardSheetInfoGetter inputObj,
        String function) {
    	 String toDay = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try {
        	logObj.info("submitForApproval");
            inputObj.setUniversityId(inputObj.getEntityId().substring(0, 4));
            List<ProgramTermDetailsInfoGetter> empLevel = getSqlMapClientTemplate().queryForList("AwardSheet.getEmployeeLevel",inputObj);

            if (empLevel.size() > 0) {
                inputObj.setApprovalOrder(empLevel.get(0).getName());
            } 
            else{
                inputObj.setApprovalOrder("0");
            }

            List<ProgramTermDetailsInfoGetter> levelObject = getSqlMapClientTemplate().queryForList("AwardSheet.getNextGetter",inputObj);
            
            //Code Added By Dheeraj For Allowing Multiple Senders And Approvers on Same Course_Code
            
            AwardSheetInfoGetter getter = new AwardSheetInfoGetter();
            getter.setApprovalOrder(inputObj.getApprovalOrder());
            getter.setEntityId(inputObj.getEntityId());
            getter.setProgramCourseKey(inputObj.getProgramCourseKey());
            getter.setStartDate(inputObj.getStartDate());
            getter.setEndDate(inputObj.getEndDate());
            getter.setCourseCode(inputObj.getCourseCode());
            getter.setDisplayType(inputObj.getDisplayType());
            List<AwardSheetInfoGetter> recordExists = getSqlMapClientTemplate().queryForList("AwardSheet.recordExists",getter);
            
            if(levelObject.size() > 0 && function.equalsIgnoreCase("S")){
            	for(int i = 0; i < levelObject.size(); i++){
            		inputObj.setApprovalOrder(levelObject.get(i).getName());
            		inputObj.setRequestGetter(levelObject.get(i).getCode());
            		inputObj.setRequestSender(inputObj.getEmployeeCode());
            		inputObj.setRequestdate(toDay);
            		inputObj.setSubmitdates(toDay);
            		int j = getSqlMapClientTemplate().update("AwardSheet.submitForApprove", inputObj);
            	
            		if(j < 1 && recordExists.size()==0){
            			getSqlMapClientTemplate().insert("AwardSheet.insertIntoCourseMarksApprovalStatus", inputObj);
            		}
            	}
            }
            if(function.equalsIgnoreCase("A")){
            	if(levelObject.size() > 0){
            		inputObj.setRequestSender(empLevel.get(0).getCode());
            		inputObj.setRequestGetter(levelObject.get(0).getCode());
            		inputObj.setStatus("SUB");
            		int approvalOrder = Integer.parseInt(inputObj.getApprovalOrder());
            		approvalOrder=approvalOrder+1;
            		inputObj.setApprovalOrder(String.valueOf(approvalOrder));
            		getSqlMapClientTemplate().insert("AwardSheet.insertIntoCourseMarksApprovalStatus",inputObj); 
            	}else{
              	  	//          	  this else mean that highest approval_order's employee has approved the award blank  
            		//          	  send mail of approval 
            		
            		inputObj.setEmployeeId(inputObj.getPreviousRequestSender());
            		AwardSheetInfoGetter empCodeObject = (AwardSheetInfoGetter)getSqlMapClientTemplate().queryForObject("AwardSheet.getEmployeeCode",inputObj);
            		inputObj.setEmployeeCode(empCodeObject.getCode());
            		empLevel = getSqlMapClientTemplate().queryForList("AwardSheet.getEmployeeLevel",inputObj);

            		AwardSheetInfoGetter employeeEmailIDObject = (AwardSheetInfoGetter)getSqlMapClientTemplate().queryForObject("AwardSheet.getEmployeeEmailId",inputObj);
            		sendMails(employeeEmailIDObject,resourceBundle.getString("requestApproved"),resourceBundle.getString("awardBlankApprovalMessage"));
          	
            		if(empLevel.size()!=0){
            				for(int i=0;i<Integer.parseInt(empLevel.get(0).getName());i++){
            						AwardSheetInfoGetter lastSendedRequest = (AwardSheetInfoGetter)getSqlMapClientTemplate().queryForObject("AwardSheet.getLastSendedRequest",inputObj);
            						if(lastSendedRequest!=null){
            								inputObj.setEmployeeCode(lastSendedRequest.getRequestSender());
            								employeeEmailIDObject = (AwardSheetInfoGetter)getSqlMapClientTemplate().queryForObject("AwardSheet.getEmployeeEmailId",inputObj);
            								sendMails(employeeEmailIDObject,resourceBundle.getString("requestApproved"), resourceBundle.getString("awardBlankApprovalMessage"));
            						}
            				}
            		}
            		
            	}
            }
            // Code Commented By Dheeraj For Allowing Multiple Senders And Approvers on Same Course_Code
            // The Commented Code is Only For Single Approver And Sender
      /**      
            if (levelObject.size() > 0) {
                inputObj.setApprovalOrder(levelObject.get(0).getName());
                inputObj.setRequestGetter(levelObject.get(0).getCode());
                inputObj.setRequestSender(inputObj.getEmployeeCode());
            }

            if (function.equalsIgnoreCase("S")) {
                inputObj.setPreviousStatus("WDW"); 
                inputObj.setRequestdate(toDay);
            	int i = getSqlMapClientTemplate().update("AwardSheet.updateStatus", inputObj);
                if (i < 1) {
                    getSqlMapClientTemplate().insert("AwardSheet.insertIntoCourseMarksApprovalStatus", inputObj);
                }
            } 
            else if (function.equalsIgnoreCase("A")) { 
                if (levelObject.size() > 0) {
                    inputObj.setStatus("SUB");
                    getSqlMapClientTemplate().insert("AwardSheet.insertIntoCourseMarksApprovalStatus",inputObj);                   
                }
                else{
//                	  this else mean that highest approval_order's employee has approved the award blank  
//                	  send mail of approval 
                	inputObj.setEmployeeId(inputObj.getPreviousRequestSender());
                	AwardSheetInfoGetter empCodeObject = (AwardSheetInfoGetter)getSqlMapClientTemplate().queryForObject("AwardSheet.getEmployeeCode",inputObj);
                	inputObj.setEmployeeCode(empCodeObject.getCode());
                	empLevel = getSqlMapClientTemplate().queryForList("AwardSheet.getEmployeeLevel",inputObj);
    
                	AwardSheetInfoGetter employeeEmailIDObject = (AwardSheetInfoGetter)getSqlMapClientTemplate().queryForObject("AwardSheet.getEmployeeEmailId",inputObj);
                	sendMails(employeeEmailIDObject,resourceBundle.getString("requestApproved"),resourceBundle.getString("awardBlankApprovalMessage"));
                	
                	if(empLevel.size()!=0){
                		for(int i=0;i<Integer.parseInt(empLevel.get(0).getName());i++){
	            			AwardSheetInfoGetter lastSendedRequest = (AwardSheetInfoGetter)getSqlMapClientTemplate().queryForObject("AwardSheet.getLastSendedRequest",inputObj);
	                    	if(lastSendedRequest!=null){
	                    		inputObj.setEmployeeCode(lastSendedRequest.getRequestSender());
	                    		employeeEmailIDObject = (AwardSheetInfoGetter)getSqlMapClientTemplate().queryForObject("AwardSheet.getEmployeeEmailId",inputObj);
	                    		sendMails(employeeEmailIDObject,resourceBundle.getString("requestApproved"), resourceBundle.getString("awardBlankApprovalMessage"));
	                    	}
                		}
                	}
                }
            }
            **/
        } catch (Exception e) {
            logObj.error("submitForApproval "+e);
            throw new MyException("E");
        }

        return null;
    }
    
    @SuppressWarnings("static-access")
	private void sendMails(AwardSheetInfoGetter employeeEmailIDObject, String subject, String text){
    	try{
    		logObj.info("sendMail");
//    		String subject=resourceBundle.getString("requestApproved");
        	String to=employeeEmailIDObject.getEmailId();
//        	String text=resourceBundle.getString("awardBlankMailmessage");
        	sendmail mailObject= new sendmail();
        	mailObject.main(text, to, resourceBundle.getString("emailId"), subject);
    	}
    	catch (Exception e) {
			logObj.error("sendMail "+e);
		}    	
    }
    

    /**
     * Method for withdrawing award blank sheet previously submitted
     * @param inputObj
     * @return
     */
    public String withdrawRequest(AwardSheetInfoGetter inputObj) {
        int rowsUpdated = 0;

        try {
        	logObj.info("withdrawRequest");
            rowsUpdated = getSqlMapClientTemplate().update("AwardSheet.withdrawSheet", inputObj);
        } catch (Exception e) {
        	logObj.error("withdrawRequest"+e);
            throw new MyException("E");
        }

        return rowsUpdated + "";
    }

    /**
     * Method for approving award blank sheet
     * @param inputObj
     * @return
     */
    @SuppressWarnings("unchecked")
    public String approveRequest(AwardSheetInfoGetter inputObj) {
        try {
        	 String toDay = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        	logObj.info("approveRequest");
        	List<ProgramTermDetailsInfoGetter> levelObject = getSqlMapClientTemplate().queryForList("AwardSheet.getEmployeeLevel", inputObj);

            if (levelObject.size() > 0) {
                if (levelObject.get(0).getName().equalsIgnoreCase(null)) {
                    throw new Exception("E");
                } else {
                    inputObj.setApprovalOrder(levelObject.get(0).getName());
                }
            } else {
                throw new Exception("E");
            }
            inputObj.setCompletiondate(toDay);
           // getSqlMapClientTemplate().update("AwardSheet.updateStatus", inputObj);
            getSqlMapClientTemplate().update("AwardSheet.updateStatusapproval", inputObj);
            
            // below method is called to insert a same record for the next level employee. 
            submitForApproval(inputObj, "A");
        } catch (Exception e) {
        	logObj.error("approveRequest "+ e);
            throw new MyException("E");
        }

        return "success"; 
    }

    /**
     * Method for rejecting award blank sheet
     * @param inputObj
     * @return
     */
    @SuppressWarnings("unchecked")
    public String rejectRequest(AwardSheetInfoGetter inputObj) {
        try {
        	logObj.info("rejectRequest");
            List<ProgramTermDetailsInfoGetter> levelObject = getSqlMapClientTemplate().queryForList("AwardSheet.getEmployeeLevel", inputObj);

            if (levelObject.size() > 0) {
                if (levelObject.get(0).getName().equalsIgnoreCase(null)) {
                    throw new Exception("");
                } else {
                    inputObj.setApprovalOrder(levelObject.get(0).getName());
                    inputObj.setEmployeeCode(levelObject.get(0).getCode());
                }
            } else {
                throw new Exception("");
            }

            int approvalOrder = Integer.parseInt(inputObj.getApprovalOrder());
            String toDay = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            for (int i = approvalOrder; i > 0; i--) {
                if (Integer.parseInt(inputObj.getApprovalOrder()) == approvalOrder) {
                    inputObj.setPreviousStatus("SUB");
                    inputObj.setCompletiondate(null);
                    inputObj.setRequestdate(toDay);
                    getSqlMapClientTemplate().update("AwardSheet.updateStatus", inputObj);
                } else {
                    inputObj.setPreviousStatus("APR");
                    inputObj.setCompletiondate(null);
                    inputObj.setRequestdate(toDay);
                    getSqlMapClientTemplate().update("AwardSheet.updateStatus", inputObj);
                    
                }

                
                if(i!=approvalOrder){
                	AwardSheetInfoGetter level = (AwardSheetInfoGetter)getSqlMapClientTemplate().queryForObject("AwardSheet.getLastEmployee", inputObj);
                    inputObj.setEmployeeCode(level.getEmployeeCode());
                    AwardSheetInfoGetter employeeEmailIDObject = (AwardSheetInfoGetter)getSqlMapClientTemplate().queryForObject("AwardSheet.getEmployeeEmailId",inputObj);
                	sendMails(employeeEmailIDObject,resourceBundle.getString("requestRejected"),resourceBundle.getString("awardBlankRejectionMessage"));
                }                
                
                inputObj.setApprovalOrder(Integer.parseInt(inputObj.getApprovalOrder()) - 1 + "");
            }
        } catch (Exception e) {
        	logObj.error("rejectRequest "+e);
            throw new MyException("E");
        }

        return null;
    }

	@SuppressWarnings("unchecked")
	public List<AwardSheetInfoGetter> getGrade(AwardSheetInfoGetter awardSheetInfoGetter) {
		List<AwardSheetInfoGetter> gradeObject = null;
		try{
			logObj.info("getGrade");
			gradeObject = getSqlMapClientTemplate().queryForList("AwardSheet.getGrade",awardSheetInfoGetter);
		}
		catch (Exception e) {
			logObj.error("getGrade");
		}
		return gradeObject;
	}
	
	  /**
     * Method for getting List of rejected request for given employee
     * @param inputObj
     * @return List
     */
    @SuppressWarnings({ "unchecked" })
    public List<AwardSheetInfoGetter> getRejectedList(AwardSheetInfoGetter inputObj) {
    	List<AwardSheetInfoGetter> rejectedList =null;
    	List<AwardSheetInfoGetter> rejectedListOfHigherApprovalOrder =new ArrayList<AwardSheetInfoGetter>();
    	List<AwardSheetInfoGetter> temp=null;
        try {
        	 logObj.info("rejected list");
            rejectedList = getSqlMapClientTemplate().queryForList("AwardSheet.getRejectedList", inputObj);
            if(rejectedList.size()==1){
            	return rejectedList;
            }
            else{
            	for(int i=0;i<rejectedList.size();i++){
            		
            		temp= getHigherOrderRecord(rejectedList.get(i));
            		if(temp!=null){
            			rejectedListOfHigherApprovalOrder.addAll(temp);
            		}
                }
            }            
            
        } catch (Exception e) {
            logObj.error("getHigherOrderRecord" + e);
        }
        return rejectedListOfHigherApprovalOrder;
    }
    
    @SuppressWarnings("unchecked")
	public List<AwardSheetInfoGetter> getHigherOrderRecord(AwardSheetInfoGetter rejectedList){
    	List<AwardSheetInfoGetter> temp=null;
    	try {
    		logObj.info("getHigherOrderRecord");
        	temp = getSqlMapClientTemplate().queryForList("AwardSheet.searchHigherApprovalRejected", rejectedList);        	
        } 
        catch (Exception e) {
        	 logObj.error("getHigherOrderRecord" + e);
        }
    	return temp;
    }

	public AwardSheetInfoGetter getStarterEmployee(
			AwardSheetInfoGetter inputObj) {
		AwardSheetInfoGetter data=null;
		try{
			logObj.info("getStarterEmployee");
			data=(AwardSheetInfoGetter)getSqlMapClientTemplate().queryForObject("AwardSheet.getStarterEmployee", inputObj);
		}
		catch (Exception e) {
			logObj.error("getStarterEmployee" + e);
		}
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public String isNextApprovalExist(
			AwardSheetInfoGetter inputObj) {
		List<AwardSheetInfoGetter> data=null;
		String message="exist";
		try{
			logObj.info("isNextApprovalExist");
			data=getSqlMapClientTemplate().queryForList("AwardSheet.isNextApprovalExist", inputObj);
			if(data.size()<2){
				message ="approvalOrderNotExist";
			}
		}
		catch (Exception e) {
			logObj.error("isNextApprovalExist" + e);
		}
		return message;
	}
	
	public String IsExternalAwardAllowed(
			AwardSheetInfoGetter inputObj) {
		AwardSheetInfoGetter data=null;
		String message="notAllowed";
		try{
			logObj.info("IsExternalAwardAllowed");
			data=(AwardSheetInfoGetter) getSqlMapClientTemplate().queryForObject("AwardSheet.IsExternalAwardAllowed", inputObj);
			if(data.getCode().equalsIgnoreCase("1")){
				message ="allowed";
			}
		}
		catch (Exception e) {
			logObj.error("IsExternalAwardAllowed" + e);
		}
		return message;
	}
	
	/** Method to Upload Excel Template
     * @param String Path of excel file
     * @param String data to be set into student_marks table
     * @param inputObj object of AwardSheetInfoGetter
     * @param String Column
     * @param String display type(Internal/External/Remadial)
     * @param String path1
     * @param String fileName1
     * @return String message
    */
	@SuppressWarnings({ "unchecked" })
	public String uploadTemplate(String path, String data,
			final AwardSheetInfoGetter inputObj,String colList,final String display,String path1,String fileName1,List<AwardSheetInfoGetter> oldmarks) {
		String message="";
		List sheetList=new ArrayList();
		
		FileInputStream fis;
		String sep=System.getProperty("file.separator");
		String fileName=path.split(sep+sep)[path.split(sep+sep).length-1];
		String splData[]=data.split(",");
			inputObj.setEntityId(splData[0]);
			inputObj.setProgramCourseKey(splData[1]);
			inputObj.setCourseCode(splData[2]);
			inputObj.setStartDate(splData[3]);
			inputObj.setEndDate(splData[4]);
			inputObj.setProgramId(splData[5]);
		try {
			String CList[]=colList.split(",");
			System.out.println("inside controller "+fileName);
			int charIndex=fileName.lastIndexOf("(");
			String fileNameServer;
			if(charIndex<0){
				fileNameServer=fileName;
				fileName1="Excel"+fileName1+".xls";
			}
			else{
				fileNameServer=fileName.substring(0,charIndex);
				fileName1="Excel"+fileName1;
			}
			
			System.out.println("inside controller "+charIndex+" server file name : "+fileNameServer+" : file name : "+fileName1);
			if(fileNameServer.equals(fileName1)){
					fis=new FileInputStream(path);
					Workbook workBook=Workbook.getWorkbook(new File(path));
					Sheet sheet=workBook.getSheet(0);
					for(int i=0;i<sheet.getRows();i++){
						List cellList=new ArrayList();
						for(int j=0;j<sheet.getColumns();j++){
							cellList.add(sheet.getCell(j,i).getContents());
						}
						sheetList.add(cellList);
					}
					System.out.println("sheet list size is "+sheetList.size());
				if(sheetList.size()>2 || sheetList.size()>3){
					List CTCountlist=(List) sheetList.get(1);
					int CTIndex=0;
				 //	for(int ii=2;ii<CTCountlist.size()-1;ii++){
						for(int ii=2;ii<CTCountlist.size();ii++){  //  Arush Changes
						for(int jj=0;jj<CList.length;jj++){				
							if(CTCountlist.get(ii).toString().split(" ")[0].equals(CList[jj].split("\n")[0])){							
								CTIndex++;
							}
						}
					}
					if(CTIndex==CList.length){
						for(int i=2;i<sheetList.size();i++){
							List list=(List) sheetList.get(i);
							String cel;
							if(list.size()>0){
								cel= (String) list.get(0);
								inputObj.setRollNumber(cel);
							}
							if(list.size()>1){
								cel=(String) list.get(1);
								inputObj.setStudentName(cel);
							}
							if(colList!=null || colList!=""){
								inputObj.setTotalInternal(null);
								inputObj.setTotalExternal(null);
								// Commented out by ARush
//								if(display.toUpperCase().equals("I") || display.toUpperCase().equals("R")){
//									if(list.size()>CList.length+2){
//										cel=(String) list.get(list.size()-1);
//										inputObj.setInternalGrade(cel);
//										inputObj.setTotalInternal(null);
//									}
//								}
//								else if(display.toUpperCase().equals("E")){
//									if(list.size()>CList.length+2){
//										cel=(String) list.get(list.size()-1);
//										inputObj.setExternalGrade(cel);
//										inputObj.setTotalExternal(null);
//									}
//								}
							}
							else{
								fis.close();
								workBook.close();
								new File(path).delete();
								new File(path1).delete();
								message="NoCT";
								return message;
							}
													
							if(colList!=null || colList!=""){
								for(int j=0;j<CList.length;j++){
									
									inputObj.setEvaluationId(CList[CList.length-(j+1)].substring(0, 3));									
									inputObj.setPassfail(null);
									
									try{
									
										boolean updatemarks=false; ;
									if (j!=CList.length-1){ // if it is not a last column   Arush
										
									
										if(list.size()>(j+2)){
											cel=(String) list.get(j+2);
											inputObj.setMarks(cel);
											if (markschanged(inputObj,oldmarks)){
												 updatemarks = true ;
												 if (isInteger(cel)){
													
														inputObj.setAttendence("P");
													}else if(cel.equalsIgnoreCase("A")){
														inputObj.setMarks("0");
														inputObj.setAttendence("ABS");
													}else{
														inputObj.setMarks(null);
														inputObj.setAttendence(null);
													}
												 
														
											}else{
												updatemarks = false ;
											}
											
										
												
												
											
											
											
											
											
											if(list.size()>0){
												//For Check Existing record
												
												final List<AwardSheetInfoGetter>checkList=getSqlMapClientTemplate().queryForList("AwardSheet.checkExistingForUpload", inputObj);
												
												// Condition For Checking remedial type was added By Dheeraj
												
												if(display.equalsIgnoreCase("I")||display.equalsIgnoreCase("E")){
												if(checkList.size()>0 ){
													if (updatemarks){
																				
													logObj.info("Record already exists in student_marks so updated : inside Method : uploadTemplate");
													getSqlMapClientTemplate().update("AwardSheet.updateStudentMarksForUpload",inputObj);
													}
												}
												else{
													if (updatemarks){
													logObj.info("Record not exists in student_marks so Insert : inside Method : uploadTemplate");
													getSqlMapClientTemplate().insert("AwardSheet.insertIntoStudentMarks",inputObj);
													}
												}
											}else{
												cel=(String) list.get(j+3);
												System.out.println("Cell Value : " + cel);
												inputObj.setGrades(cel);
												if(checkList.size()>0){
													if (updatemarks){
													logObj.info("Record already exists in student_marks so updated : inside Method : uploadTemplate");
													getSqlMapClientTemplate().update("AwardSheet.updateStudentMarksForUpload",inputObj);
													}
												}
												else{
													logObj.info("Record not exists in student_marks so Insert : inside Method : uploadTemplate");
													getSqlMapClientTemplate().insert("AwardSheet.insertIntoStudentMarks",inputObj);
												}
											}
											
										}
										
									}
								}//else {                                  /// Last column  then Grade is coming from excel Arush
//										
//										cel=(String) list.get(j+2);
//										
//										final List<AwardSheetInfoGetter>chekSummaryList=getSqlMapClientTemplate().queryForList("AwardSheet.checkExistingSummaryForUpload", inputObj);
//										if(chekSummaryList.size()>0){
//											if(display.toUpperCase().equals("I") || display.toUpperCase().equals("R")){
//												inputObj.setInternalGrade(cel); // Arush
//												
//												logObj.info("Record already exists in student_marks_summary so updated for Internal/Remadial : inside Method : uploadTemplate");
//												 getSqlMapClientTemplate().update("AwardSheet.updateStudentMarksSummaryInternalForUpload",inputObj);
//											}
//											else if(display.toUpperCase().equals("E")){
//												inputObj.setExternalGrade(cel); // Arush
//												logObj.info("Record already exists in student_marks_summary so updated for External: inside Method : uploadTemplate");
//												getSqlMapClientTemplate().update("AwardSheet.updateStudentMarksSummaryExternalForUpload",inputObj);
//											}
//										}
//										else{
//											if(display.toUpperCase().equals("I") || display.toUpperCase().equals("R")){
//												inputObj.setInternalGrade(cel); // Arush
//												logObj.info("Record not exists in student_marks_summary so Insert for Internal/Remadial: inside Method : uploadTemplate");
//												getSqlMapClientTemplate().insert("AwardSheet.insertIntoStudentMarksSummaryInternal",inputObj);
//											}
//											else if(display.toUpperCase().equals("E")){
//												inputObj.setExternalGrade(cel); // Arush
//												logObj.info("Record not exists in student_marks_summary so Insert for External: inside Method : uploadTemplate");
//												getSqlMapClientTemplate().insert("AwardSheet.insertIntoStudentMarksSummaryExternal",inputObj);
//											}
//										}
//									}
										
									
													
									
										
									
									
											
											
											message= "success";
									}
										catch(DataIntegrityViolationException e){
											fis.close();
											workBook.close();
											new File(path).delete();
											new File(path1).delete();
											logObj.error("DataIntegrityViolationException inside method uploadTemplate in AwardSheetDaoImpl" + e);
											return "ConstraintFail";
										}
										catch(Exception e){
											fis.close();
											workBook.close();
											new File(path).delete();
											new File(path1).delete();
											logObj.error("Exception inside method uploadTemplate in AwardSheetDaoImpl" + e);
											return "SqlError";
											
										}
								 
								}
										inputObj.setMarks(null);
										inputObj.setEvaluationId(null);
									}	
								
							
					
							else{
								fis.close();
								workBook.close();
								new File(path).delete();
								new File(path1).delete();
								message="NoCT";
								return message;
							}
							inputObj.setRollNumber(null);
							inputObj.setGrades(null);
						}
						fis.close();
						workBook.close();
						new File(path).delete();
						new File(path1).delete();
						return message;
					}
					else{
						fis.close();
						message= "CTCountDiff";
					}
				}
				else{
					message="NoRecord";
				}
			}
			else{
				message="fileNameDiff";
			}
		} 
		catch (FileNotFoundException e) {
			message="FileError";
			logObj.error("FileNotFoundException inside method uploadTemplate in AwardSheetDaoImpl" + e);
		} catch (IOException e) {
			message="IOError";
			logObj.error("IOException inside method uploadTemplate in AwardSheetDaoImpl" + e);
		}
		catch (OfficeXmlFileException e) {
			message="NotReadFile";
			logObj.error("OfficeXmlFileException inside method uploadTemplate in AwardSheetDaoImpl" + e);
		}
		catch(Exception e){
			message="error";
			logObj.error("Exception inside method uploadTemplate in AwardSheetDaoImpl" + e);
		}
		new File(path).delete();
		new File(path1).delete();
		return message;
	}


	@SuppressWarnings("unchecked")
	public List<AwardSheetInfoGetter> getAprStatus(AwardSheetInfoGetter inputObj) {
		List<AwardSheetInfoGetter> list=null;
		//String message="notAllowed";
		try{
			int approvalOrder = Integer.parseInt(inputObj.getApprovalOrder());
			approvalOrder = approvalOrder+1;
			inputObj.setApprovalOrder(String.valueOf(approvalOrder));
			System.out.println("Approval Order : " + approvalOrder);
			
			list= getSqlMapClientTemplate().queryForList("AwardSheet.getAprStatus", inputObj);
			
		}
		catch (Exception e) {
			logObj.error("IsExternalAwardAllowed" + e);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<AwardSheetInfoGetter> getgradelimit(
			AwardSheetInfoGetter inputObj) {
		List<AwardSheetInfoGetter> list=null;
		//String message="notAllowed";
		try{
			list= getSqlMapClientTemplate().queryForList("AwardSheet.getgradelimit1", inputObj);
			
		}
		catch (Exception e) {
			logObj.error("getgradelimit" + e);
		}
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<AwardSheetInfoGetter> getcourseAprStatus(
			AwardSheetInfoGetter inputObj) {
		List<AwardSheetInfoGetter> list=null;
		//String message="notAllowed";
		try{
			list= getSqlMapClientTemplate().queryForList("AwardSheet.getcourseAprStatus", inputObj);
			
		}
		catch (Exception e) {
			logObj.error("getgradelimit" + e);
		}
		return list;
	}
	
	public boolean isInteger( String s1 )   
	{   
	   try  
	   {   
	     // Integer.parseInt( s1 ); 
	      Double.parseDouble(s1);
	      return true;   
	   }   
	   catch( Exception e)   
	   {   
	      return false;   
	   }   
	}  
  public boolean markschanged(AwardSheetInfoGetter inputobj ,List<AwardSheetInfoGetter> oldmarks){
	  String roll =inputobj.getRollNumber() ;
	  java.util.ListIterator<AwardSheetInfoGetter> itr  = oldmarks.listIterator();
	  AwardSheetInfoGetter studentdetail = new AwardSheetInfoGetter();
	  while (itr.hasNext()){
		 
		studentdetail = itr.next();
		  String ev = studentdetail.getEvaluationId();
		  String  mk = studentdetail.getMarks();  
		   String rol = studentdetail.getRollNumber();
		 
            if (rol=="notfound"){
            	if (inputobj.getMarks()!=""){
            		
            		return true;
            }else {
            	return false;
            }
            	
            
            }
	
			if(inputobj.getRollNumber().equalsIgnoreCase(rol)   && inputobj.getEvaluationId().equalsIgnoreCase(ev)){
				String attendence  = studentdetail.getAttendence();
				if(attendence==null){
					attendence = "";
				}
				if (isInteger(inputobj.getMarks())&& isInteger(mk))  {
					if(!(Double.parseDouble(inputobj.getMarks())==Double.parseDouble(mk))){
						inputobj.setOldmarks(mk);
						if(attendence.equalsIgnoreCase("ABS")){
							inputobj.setOldmarks("ABS");
						}
						return true;
					}else{
						return false;
					}
				}else if(inputobj.getMarks().equalsIgnoreCase("A")) {
					 if(attendence.equalsIgnoreCase("ABS")){
						 return false ;
					 }else{
						 inputobj.setOldmarks(mk);
						 return true;
					 }
					
				}else if (inputobj.getMarks()=="" && mk != null){
					inputobj.setOldmarks(mk);
					return true;
					
			
				
				} else if (inputobj.getMarks()=="" && mk == null){
					return false;
				} else if (studentdetail.getMarks()==null &&  isInteger(inputobj.getMarks()) ) {
				
					 inputobj.setOldmarks(mk);
					 return true;
				
				} else if(studentdetail.getMarks()!=null && inputobj.getMarks()!=""   ){
					return false;
				}
				
				
				
				
				
			}
			
			  
	  }
	  
	  
	  if (inputobj.getMarks()!=""){
  		
  		return true;
  }else {
  	return false;
  }
	
  }

public List<AwardSheetInfoGetter> getApprovalOrder(AwardSheetInfoGetter inputObj) {
	List<AwardSheetInfoGetter> list=null;
	try{
		list = getSqlMapClientTemplate().queryForList("AwardSheet.getApprovalOrder", inputObj);
	}catch(Exception ex){
		logObj.error("getApprovalOrder" + ex);
	}
	return list;
}
	
	
}
