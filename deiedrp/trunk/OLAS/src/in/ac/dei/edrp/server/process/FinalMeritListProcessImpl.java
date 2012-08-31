/**********************************************************************************
 * $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      .............
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

/*
   Author Name :Devendra Singhal
 */
package in.ac.dei.edrp.server.process;

import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.FinalMeritList;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("serial")
public class FinalMeritListProcessImpl extends RemoteServiceServlet implements FinalMeritList {
	
	//Make Object of SqlMapClient for communicating with database
	SqlMapClient sqlMap=SqlMapManager.getSqlMapClient();
	//Make Object of Log4JInitServlet class for maintaining log files
	Log4JInitServlet logger=new Log4JInitServlet();
	
	@SuppressWarnings("unchecked")
	public String generateFinalMeritList(String userId,String entityId,String programId){
		String message = "";
		ReportInfoGetter reportBeen=new ReportInfoGetter();
		reportBeen.setUniversity_id(userId.substring(1, 5));
		reportBeen.setEntity_id(entityId);
		reportBeen.setProgram_id(programId);
		reportBeen.setUser_id(userId);
		try {
			//Get Session Dates
			List<ReportInfoGetter>universityList=sqlMap.queryForList("getuniversitySessionDate", reportBeen);			
			reportBeen.setUniversity_start_date(universityList.get(0).getStart_date());
			reportBeen.setUniversity_end_date(universityList.get(0).getEnd_date());			
			//Get Registered Student from database
			List<ReportInfoGetter>regList=sqlMap.queryForList("getRegisteredStudent", reportBeen);	
			if(regList.size()>0){
				int totalStudents=regList.size();
				int recordProcessed=0;
					for(int i=0;i<regList.size();i++){
						reportBeen.setRegistration_number(regList.get(i).getRegistration_number());						
						//Get Component Detail From final_merit_components
						List<ReportInfoGetter>compList=sqlMap.queryForList("getComponentDetail", reportBeen);						
						double totalComponentMarks=0.0;
						double academicScore=0.0;
						boolean flag=false;
						if(compList.size()>0){									
							for(int j=0;j<compList.size();j++){
								//Check component is Academic score(AS) or not
								if(compList.get(j).getComponent_id().equalsIgnoreCase("AS")){
									academicScore=Double.parseDouble(compList.get(j).getMarks());
									flag=true;
								}	
								else{
									double compnentMarks=Double.parseDouble(compList.get(j).getMarks());
									double weightagePercentage=compList.get(j).getComponent_Weightage();
									double computedMarks=(compnentMarks*weightagePercentage)/100;
									totalComponentMarks=totalComponentMarks+computedMarks;	
								}
							}	
							reportBeen.setTest_number(regList.get(i).getTest_number());
							if(flag){
								reportBeen.setTotal_marks(totalComponentMarks+academicScore);
								reportBeen.setSum_actual_computed_marks(regList.get(i).getSum_actual_computed_marks());
								logger.logger.info("Academic score is added in total marks for generationg final merit list");
							}
							else{
								reportBeen.setTotal_marks(totalComponentMarks);	
								logger.logger.info("Academic score is not added in total marks for generationg final merit list");
							}									
							reportBeen.setCos_value(regList.get(i).getCos_value());							
							reportBeen.setComputed_Marks(totalComponentMarks);
							//Check for duplicate record
							String obj=(String)sqlMap.queryForObject("getDuplicateCount", reportBeen);
							if(Integer.parseInt(obj)>0){
								sqlMap.update("updateFinalMeritList", reportBeen);
								recordProcessed++;
							}
							else{
								sqlMap.insert("insertIntoFinalMeritList", reportBeen);
								recordProcessed++;
							}
						}
						else{							
							logger.logger.error("There is no Component Added in final_merit_component for program"+reportBeen.getProgram_id()+" and entity "+reportBeen.getEntity_id()+" amd registration number "+reportBeen.getRegistration_number());
						}
					
					}
					message="success-"+String.valueOf(totalStudents)+"-"+String.valueOf(recordProcessed)+"-"+String.valueOf((totalStudents-recordProcessed));

			}
			else{
				message="noRegisteredStudent";
				logger.logger.error("There is no Registered Student in student_test_number table for program_id "+reportBeen.getProgram_id()+" and entity_id "+reportBeen.getEntity_id()
						+" start_date "+reportBeen.getUniversity_start_date()+" end date "+reportBeen.getUniversity_end_date() +" and status  Eligible");
			}
		} catch (SQLException e) {
			message="error";
			logger.logger.error("Sql Exception in FinalMeritListProcessImpl inside method : generateFinalMeritList  :: "+e);
		}
		catch(Exception e){
			logger.logger.error("Exception in FinalMeritListProcessImpl inside method : generateFinalMeritList  :: "+e);
		}
		return message;
		
	}

	//Add by Devendra May 11 
	@SuppressWarnings("unchecked")
	@Override
	public String importOmrMarks(String userId,String programId, String entityId,
			String testId, String conductDate, String groupStatus) {
		String message="";
		try{
			int totalRecord=0;
			int processed=0;
			
			//Create Object of ReportInfoGetter been
			ReportInfoGetter reportBeen=new ReportInfoGetter();
			reportBeen.setUniversity_id(userId.substring(1, 5));
			reportBeen.setUser_id(userId);
			reportBeen.setProgram_id(programId);
			reportBeen.setEntity_id(entityId);
			reportBeen.setTest_number(testId);
			reportBeen.setStart_date(conductDate);
			reportBeen.setStatus(groupStatus);	
			//Check Entrance Test Component from database 
			String comp=(String)sqlMap.queryForObject("getComponent", reportBeen);
			if(comp==null || comp.equals("")){
				message="NoETComponent";
				logger.logger.error("There is no Enterence Test Component Added for program "+reportBeen.getProgram_id()
						+" ,Entity "+reportBeen.getEntity_id()+" ,component_id 'ET' ");
			}
			else{
				String maxMarks=comp;
				//Get University Session Dates from system_values
				List<ReportInfoGetter>sessionList=sqlMap.queryForList("getuniversitySessionDate", reportBeen);
				reportBeen.setUniversity_start_date(sessionList.get(0).getStart_date());
				reportBeen.setUniversity_end_date(sessionList.get(0).getEnd_date());
				//Get Student From student_test_number
				List<ReportInfoGetter>studentList=sqlMap.queryForList("getStudents", reportBeen);
				List<ReportInfoGetter>tempRegList=new ArrayList<ReportInfoGetter>();
				if(studentList.size()>0){
					totalRecord=studentList.size();
					List<String>li=new ArrayList<String>();
					for(int i=0;i<studentList.size();i++){
						li.add(studentList.get(i).getTest_number());
						ReportInfoGetter rpt=new ReportInfoGetter();
						rpt.setRegistration_number(studentList.get(i).getRegistration_number());
						rpt.setTest_number(studentList.get(i).getTest_number());
						tempRegList.add(rpt);
					}
					reportBeen.setList(li);					
					//Get Marks for Student From OMR
					List<ReportInfoGetter>marksList=sqlMap.queryForList("getOMRMarks", reportBeen);
					if(marksList.size()>0){
						List<String>tempList=new ArrayList<String>();					
						for(int i=0;i<marksList.size();i++){
							if(tempList.indexOf(marksList.get(i).getRegistration_number())<0){
								tempList.add(marksList.get(i).getRegistration_number());
								for(int j=0;j<tempRegList.size();j++){
									if(tempRegList.get(j).getTest_number().equals(marksList.get(i).getRegistration_number())){
										reportBeen.setRegistration_number(tempRegList.get(j).getRegistration_number());
									}
								}								
								reportBeen.setTotal_marks(marksList.get(i).getTotal_marks());
								reportBeen.setAttendance("P");
								if(marksList.get(i).getTotal_marks()<=Double.parseDouble(maxMarks)){								
									reportBeen.setComponent_id("ET");
									//Check Duplicate Count
									String count=(String)sqlMap.queryForObject("checkDuplicateInStudentMarks", reportBeen);
									if(Integer.parseInt(count)==0){
										//Insert Entrance Test Component Marks
										sqlMap.insert("insertStudentFinalMarksFromOMR", reportBeen);
									}
									else{
										//Update Entrance Test Component Marks
										sqlMap.update("updateStudentFinalMarksFromOMR", reportBeen);
									}								
									processed++;
								}
								else{
									logger.logger.error("Marks Obtain is greater than Maximum marks of student whose registration no is "+marksList.get(i).getRegistration_number());
								}
								for(int j=0;j<marksList.size();j++){
									if(marksList.get(i).getRegistration_number().equals(marksList.get(j).getRegistration_number())){
										reportBeen.setGroupCode(marksList.get(j).getGroupCode());
										reportBeen.setGroupMarks(marksList.get(j).getGroupMarks());
										//Check Duplicate for sub components
										Integer count=(Integer)sqlMap.queryForObject("checkDuplicateSubcomponents", reportBeen);
										if(count==0){
											//Insert SubComponent Marks
											sqlMap.insert("insertStudentSubcomponentMarks", reportBeen);
										}
										else{
											//Update SubComponent Marks 
											sqlMap.update("updateStudentSubcomponentMarks", reportBeen);
										}
									}
								}
							}
						}
						message="success-"+totalRecord+"-"+processed+"-"+(totalRecord-processed);
					}
					else{
						message="NoMarksInOMR";
						logger.logger.error("There is no Test conducted in omr for test_id "+reportBeen.getTest_number()+" conduct date "+reportBeen.getStart_date());								
					}
				}
				else{
					message="NoRecordFound";
					logger.logger.error("No Record found in student_test_number and student_result_info table for program "+reportBeen.getProgram_id()
							+" ,Entity "+reportBeen.getEntity_id()+" ,status Eligible ,called y , start_date "+reportBeen.getUniversity_start_date()
							+" ,end_date "+reportBeen.getUniversity_end_date() +" ,testId "+reportBeen.getTest_number());
				}
			}
			
		}
		catch(SQLException e){
			message="error";
			logger.logger.error("Exception in FinalMeritListProcessImpl inside method : importOmrMarks  :: "+e);
		}
		catch(Exception e){
			logger.logger.error("Exception in FinalMeritListProcessImpl inside method : importOmrMarks  :: "+e);
		}
		return message;
	}
}
