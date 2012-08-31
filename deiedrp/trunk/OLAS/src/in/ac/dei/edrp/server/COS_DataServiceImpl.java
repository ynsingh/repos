 package in.ac.dei.edrp.server;

import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.SubjectCode;
import in.ac.dei.edrp.client.SystemTableTwo;
import in.ac.dei.edrp.client.RPCFiles.COS_DataService;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

public class COS_DataServiceImpl extends RemoteServiceServlet implements COS_DataService{

	 SqlMapClient client = SqlMapManager.getSqlMapClient();
	    Log4JInitServlet logObj = new Log4JInitServlet();
	
	    public CM_ProgramInfoGetter[] getEntityNames(String userID,String entityType) 
	    {
	    	List<CM_ProgramInfoGetter> entityNameList=new ArrayList<CM_ProgramInfoGetter>();
			String university_id=userID.substring(1,5);
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setUniversity_id(university_id);
			cmpig.setEntity_type(entityType);
			try
			{System.out.println(entityType);
				entityNameList=client.queryForList("getEntityNames_COS",cmpig);
				return (CM_ProgramInfoGetter[]) entityNameList.toArray(new CM_ProgramInfoGetter[entityNameList.size()]);
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getEntityNames" + e);
			}
			return (CM_ProgramInfoGetter[]) entityNameList.toArray(new CM_ProgramInfoGetter[entityNameList.size()]);
	    }

		
		public CM_ProgramInfoGetter[] getEntityTypes(String userID) {
			List<CM_ProgramInfoGetter> entityTypeList=new ArrayList<CM_ProgramInfoGetter>();
			String university_id=userID.substring(1,5);
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setUniversity_id(university_id);
			try
			{
				entityTypeList=client.queryForList("getEntityType_COS",cmpig);
				return (CM_ProgramInfoGetter[]) entityTypeList.toArray(new CM_ProgramInfoGetter[entityTypeList.size()]);
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getEntityTypes" + e);
			}
			return (CM_ProgramInfoGetter[]) entityTypeList.toArray(new CM_ProgramInfoGetter[entityTypeList.size()]);
		}


		
		public CM_ProgramInfoGetter[] getPrograms(String entityID,
				String settings, String userID) {
			List<CM_ProgramInfoGetter> programList=new ArrayList<CM_ProgramInfoGetter>();
			String university_id=userID.substring(1,5);
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setUniversity_id(university_id);
			cmpig.setSettings(settings);
			cmpig.setEntity_id(entityID);
			try
			{
				programList=client.queryForList("getProgram_COS",cmpig);
				return (CM_ProgramInfoGetter[])programList.toArray(new CM_ProgramInfoGetter[programList.size()]);
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getPrograms" + e);
			}
			return (CM_ProgramInfoGetter[])programList.toArray(new CM_ProgramInfoGetter[programList.size()]);
		}

		 public Integer getTotalSeats(String programID, String entityID) {
	           
		    	Integer total_seats;
		    	Integer cos_seats;
		    	Integer result;
		        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

		           
		           
		            cmp.setProgram_id(programID);
		            cmp.setEntity_id(entityID);
		            try {
		            	total_seats = (Integer) client.queryForObject("getTotalSeats", cmp);
		            	cos_seats=(Integer)client.queryForObject("getCOS_Seats",cmp);
		            	if(cos_seats==null)
		            	{
		            		result=total_seats;
		            	}
		            	else
		            		{
		            		result=total_seats-cos_seats;
		            		}
		                
		                return result;
		            } catch (Exception e) {
		                logObj.logger.error("exception in get Total Seats" + e);
		            }

		            return null;
		        }


		
		public CM_ProgramInfoGetter[] getSessionDates(String userID) {
			List<CM_ProgramInfoGetter> sessionList=new ArrayList<CM_ProgramInfoGetter>();
			 CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
			 String university_id=userID.substring(1,5);
			 cmp.setUniversity_id(university_id);
			 try {
				 sessionList=client.queryForList("getSession_Dates",cmp);
				
				 return (CM_ProgramInfoGetter[]) sessionList.toArray(new CM_ProgramInfoGetter[sessionList.size()]);  
	            } catch (Exception e) {
	                logObj.logger.error("exception in getSessionDates" + e);
	            }
			 return (CM_ProgramInfoGetter[]) sessionList.toArray(new CM_ProgramInfoGetter[sessionList.size()]);
		}


		
		public CM_ProgramInfoGetter[] getCOSRecords(String program_id,
				String entityID, String cos_value) {
			List<CM_ProgramInfoGetter> recordsList=new ArrayList<CM_ProgramInfoGetter>();
			 CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
			cmp.setProgram_id(program_id);
			cmp.setEntity_id(entityID);
			cmp.setCos_value(cos_value);
			
			 try {
				 recordsList=client.queryForList("getCOS_Records",cmp);
				
				 return (CM_ProgramInfoGetter[]) recordsList.toArray(new CM_ProgramInfoGetter[recordsList.size()]);  
	            } catch (Exception e) {
	                logObj.logger.error("exception in getCOSRecords" + e);
	            }
			 return (CM_ProgramInfoGetter[]) recordsList.toArray(new CM_ProgramInfoGetter[recordsList.size()]);
		}
		 public SystemTableTwo[] getCategories(String university_id)
		   {
			  
			   SystemTableTwo stt=new SystemTableTwo();

		       List l1;
		       String uni_id;

		       uni_id = university_id.substring(1, 5);

		       try {
		           
		          
		           stt.setUniversity_id(uni_id);
		           
		           l1 = (List) client.queryForList("getCategories", stt);
		           System.out.println(l1.size()+"Category(ies) has/have been fetched");
		           return (SystemTableTwo[]) l1.toArray(new SystemTableTwo[l1.size()]);
		       } catch (Exception e) {
		           logObj.logger.error(" Exception in getCategories in callcutoff.xml id=getCategories" + e);
		           System.out.println(" Exception in getCategories in callcutoff.xml id=getCategories" + e);
		       }

			return null;
			   
		   }
		 public SystemTableTwo[] getGender(String university_id)
		   {
			   
			   SystemTableTwo stt=new SystemTableTwo();

		       List l1;
		       String uni_id;

		       uni_id = university_id.substring(1, 5);

		       try {
		           
		          
		           stt.setUniversity_id(uni_id);
		           
		           l1 = (List) client.queryForList("getGender", stt);
		           System.out.println(l1.size()+"Gender(s) has/have been fetched");
		           return (SystemTableTwo[]) l1.toArray(new SystemTableTwo[l1.size()]);
		       } catch (Exception e) {
		           logObj.logger.error(" Exception in getGender in callcutoff.xml id=getCategories" + e);
		           System.out.println(" Exception in getGender in callcutoff.xml id=getCategories" + e);
		       }

			return null;
			   
		   }
		
		 public String insertCOS_Details(String cosvalue, String factor,
			        String seats, String programid, String offered,
			        Date dateSelected, Date dateSelected1, String uniid) {
			        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
			System.out.println("System is going to insert COS records");
			        try {
			            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
			                           .substring(0, 19);
			           Date startDate=new java.sql.Date(dateSelected.getYear(),dateSelected.getMonth(),dateSelected.getDate());
			           Date endDate=new java.sql.Date(dateSelected1.getYear(),dateSelected1.getMonth(),dateSelected1.getDate());
			           System.out.println("Start Date is"+startDate.toString()+"End Date is"+endDate.toString());
			            cmp.setCos_value(cosvalue);
			            cmp.setNo_of_times(factor);
			            cmp.setNo_of_times_active("I");
			            cmp.setNo_of_seats(seats);
			            cmp.setInsert_time(date);
			            cmp.setProgram_id(programid);
			            cmp.setEntity_id(offered);
			         //Commented by Arjun   cmp.setBranch_code(branch_code);
			            cmp.setCut_off_number(0);
			            cmp.setCut_off_number_active("A");
			            cmp.setCut_off_percentage(0);
			            cmp.setCut_off_percentage_active("I");
			            cmp.setSettings("I");
			          //Commented by Arjun cmp.setSession_start_date(dateSelected);
			          //Commented by Arjun cmp.setSession_end_date(dateSelected1);
			            cmp.setSession_start_date(startDate.toString());
			            cmp.setSession_end_date(endDate.toString());
			            cmp.setCreator_id(uniid);
			            //Commented by Arjun cmp.setSpecialization_id(specialization_id);

			            client.insert("insertCOS_Details", cmp);
			            System.out.println("COS Records are inserted");
			        } catch (SQLException e) {
			            e.printStackTrace();
			            logObj.logger.error("exception in methodinsertcutoffdetails = " +
			                e);
			        }

			        return null;
			    }

		 public SubjectCode[] getSubjectCodes(String university_id, String program_id)
		   {
			   System.out.println("Control comes in CM_connectRImpl to get Subject Codes"+program_id+university_id);
			   SubjectCode sc=new SubjectCode();

		       List l1;
		       String uni_id;

		       uni_id = university_id.substring(1, 5);

		       try {
		           
		          
		           sc.setUniversity_id(uni_id);
		           sc.setProgramID(program_id);
		           l1 = (List) client.queryForList("getSubjectCodes", sc);
		           System.out.println(l1.size()+"SubjectCode(s) has/have been fetched");
		           return (SubjectCode[]) l1.toArray(new SubjectCode[l1.size()]);
		       } catch (Exception e) {
		           logObj.logger.error(" Exception in getSubjectCode in callcutoff.xml id=getSubjectCodes" + e);
		           System.out.println(" Exception in getSubjectCode in callcutoff.xml id=getSubjectCodes" + e);
		       }

			return null;
			   
		   }


		
		public CM_ProgramInfoGetter[] getProgramNames(String userID,String entityID) {
			List<CM_ProgramInfoGetter> programList=new ArrayList<CM_ProgramInfoGetter>();
			
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setUniversity_id(userID.substring(1,5));
			cmpig.setEntity_id(entityID);
			try
			{
				programList=client.queryForList("getSimpleProgramNames",cmpig);
				return (CM_ProgramInfoGetter[])programList.toArray(new CM_ProgramInfoGetter[programList.size()]);
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getPrograms" + e);
			}
			return (CM_ProgramInfoGetter[])programList.toArray(new CM_ProgramInfoGetter[programList.size()]);
		}


		
		public SystemTableTwo[] getEntranceCenter(String userID) {
			 System.out.println("Control comes in CM_connectRImpl to get Categories");
			   SystemTableTwo stt=new SystemTableTwo();
			   stt.setUniversity_id(userID.substring(1, 5));
		       List l1;
		       

		      

		       try {
		           
		          
		           
		           
		           l1 = (List) client.queryForList("getEntranceCenter", stt);
		           System.out.println(l1.size()+"Category(ies) has/have been fetched");
		           return (SystemTableTwo[]) l1.toArray(new SystemTableTwo[l1.size()]);
		       } catch (Exception e) {
		           logObj.logger.error(" Exception in getCategories in callcutoff.xml id=getCategories" + e);
		           System.out.println(" Exception in getCategories in callcutoff.xml id=getCategories" + e);
		       }

			return null;
			
		}


		@Override
		public void insertEntranceCenter(String userID,String entityID, String programID,
				String center_code) {
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setEntity_id(entityID);
			cmpig.setProgram_id(programID);
			cmpig.setComponent_id(center_code);
			cmpig.setCreator_id(userID);
			try
			{
				client.insert("insert_entrance",cmpig);
			}
			catch(Exception ex)
			{
				logObj.logger.error(" Exception in getCategories in callcutoff.xml id=getCategories" + ex);
			}
			
			
		}


		
		public CM_ProgramInfoGetter[] getEntranceDetails(String userID,String entityID) {
			List<CM_ProgramInfoGetter> programList=new ArrayList<CM_ProgramInfoGetter>();
			
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setUniversity_id(userID.substring(1, 5));
			cmpig.setEntity_id(entityID);
			try
			{
				programList=client.queryForList("getEntranceDetails",cmpig);
				return (CM_ProgramInfoGetter[])programList.toArray(new CM_ProgramInfoGetter[programList.size()]);
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getPrograms" + e);
			}
			return (CM_ProgramInfoGetter[])programList.toArray(new CM_ProgramInfoGetter[programList.size()]);
		}


		
		public Integer deleteEntranceCenter(String[] param,String entityID,String userID) {
			Integer i=null;
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setUniversity_id(userID.substring(1,5));
			cmpig.setProgram_id(param[0]);
			cmpig.setComponent_id(param[1]);
			cmpig.setEntity_id(entityID);
			
			try
			{
				i=client.delete("deleteCenter", cmpig);
			}
			catch(Exception ex)
			{
				logObj.logger.error(" Exception in getCategories in callcutoff.xml id=getCategories" + ex);
			}
			return i;
		}


		
		public CM_ProgramInfoGetter[] getExaminationCenter(String entityID,
				String programID,String examinationCenter) {
			List<CM_ProgramInfoGetter> programList=new ArrayList<CM_ProgramInfoGetter>();
			
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setEntity_id(entityID);
			cmpig.setProgram_id(programID);
			cmpig.setComponent_id(examinationCenter);
			try
			{
				programList=client.queryForList("getExaminationDetails",cmpig);
				return (CM_ProgramInfoGetter[])programList.toArray(new CM_ProgramInfoGetter[programList.size()]);
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getPrograms" + e);
			}
			return (CM_ProgramInfoGetter[])programList.toArray(new CM_ProgramInfoGetter[programList.size()]);
		}


		
		public CM_ProgramInfoGetter[] getCOS_Details(String userID) {
			List<CM_ProgramInfoGetter> COSList=new ArrayList<CM_ProgramInfoGetter>();
			
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setUniversity_id(userID.substring(1,5));
			try
			{
				COSList=client.queryForList("getCOS_Manage_Details",cmpig);
				return (CM_ProgramInfoGetter[])COSList.toArray(new CM_ProgramInfoGetter[COSList.size()]);
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getCOS_Manage_Details" + e);
			}
			return (CM_ProgramInfoGetter[])COSList.toArray(new CM_ProgramInfoGetter[COSList.size()]);
		}


		
		public Integer deleteCOSDetails(String[] param) {
			Integer i=null;
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			
			cmpig.setEntity_id(param[0]);
			cmpig.setProgram_id(param[1]);
			cmpig.setCos_value(param[2]);
			System.out.println(param[0]+param[1]+param[2]);
			try
			{
				i=client.delete("deleteCOS", cmpig);
			}
			catch(Exception ex)
			{
				logObj.logger.error("" + ex);
			}
			return i;
			
		}


		
		public CM_ProgramInfoGetter[] getCOS_DetailsWithET(String userID,
				String entityType) {
List<CM_ProgramInfoGetter> COSList=new ArrayList<CM_ProgramInfoGetter>();
			
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setUniversity_id(userID.substring(1,5));
			cmpig.setEntity_type(entityType);
			try
			{
				COSList=client.queryForList("getCOS_Manage_Details_ET",cmpig);
				return  (CM_ProgramInfoGetter[])COSList.toArray(new CM_ProgramInfoGetter[COSList.size()]);
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getCOS_Manage_Details" + e);
			}
			return (CM_ProgramInfoGetter[])COSList.toArray(new CM_ProgramInfoGetter[COSList.size()]);
		}


		
		public CM_ProgramInfoGetter[] getCOS_DetailsWithEN(String userID,String entityID)
		{
List<CM_ProgramInfoGetter> COSList=new ArrayList<CM_ProgramInfoGetter>();
			
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setUniversity_id(userID.substring(1,5));
			cmpig.setEntity_id(entityID);
			try
			{
				COSList=client.queryForList("getCOS_Manage_Details_EN",cmpig);
				return (CM_ProgramInfoGetter[])COSList.toArray(new CM_ProgramInfoGetter[COSList.size()]);
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getCOS_Manage_Details" + e);
			}
			return null;
		}

		public String getEntityName(String userID, String entityID) {
			String entityName=null;
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setUniversity_id(userID.substring(1, 5));
			cmpig.setEntity_id(entityID);
			try
			{
			entityName=client.queryForObject("getEName", cmpig).toString();
			return entityName;
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getting Entity Name" + e);
			}
			return entityName;
		}


		
		public String getProgramName(String programID) {
			String programName=null;
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setProgram_id(programID);
			try
			{
				programName=client.queryForObject("getPName", cmpig).toString();
			return programName;
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getting Program Name" + e);
			}
			return programName;
		}


		
		public String getSubjectName(String userID, String subjectCode) {
			String subjectName=null;
			CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
			cmpig.setUniversity_id(userID.substring(1, 5));
			cmpig.setComponent_id(subjectCode);
			try
			{
				subjectName=client.queryForObject("getSName", cmpig).toString();
			return subjectName;
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getting Subject Name" + e);
			}
			return subjectName;
		}


		
		public void setCOS(String[] category, String[] gender,
				String subjectCode, String[] seats, String[] xFactor,
				String entityID, String programID, String userID,
				Date startDate, Date endDate,int counter) {
			CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
			System.out.println("System is going to insert COS records");
			        try {
			            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
			                           .substring(0, 19);
			           Date sDate=new java.sql.Date(startDate.getYear(),startDate.getMonth(),startDate.getDate());
			           Date eDate=new java.sql.Date(endDate.getYear(),endDate.getMonth(),endDate.getDate());
			           System.out.println("Start Date is"+startDate.toString()+"End Date is"+endDate.toString());
			            
			           
			            cmp.setNo_of_times_active("I");
			           
			            cmp.setInsert_time(date);
			            cmp.setProgram_id(programID);
			            cmp.setEntity_id(entityID);
			         //Commented by Arjun   cmp.setBranch_code(branch_code);
			            cmp.setCut_off_number(0);
			            cmp.setCut_off_number_active("A");
			            cmp.setCut_off_percentage(0);
			            cmp.setCut_off_percentage_active("I");
			            cmp.setSettings("I");
			          //Commented by Arjun cmp.setSession_start_date(dateSelected);
			          //Commented by Arjun cmp.setSession_end_date(dateSelected1);
			            cmp.setSession_start_date(sDate.toString());
			            cmp.setSession_end_date(eDate.toString());
			            cmp.setCreator_id(userID);
			            //Commented by Arjun cmp.setSpecialization_id(specialization_id);
			           if(gender.length==0)
			           {	for(int i=0;i<counter;i++)
			          		{
			        	   String cosvalue=category[i]+"X"+subjectCode;
		            		cmp.setNo_of_times(xFactor[i]);
				            cmp.setNo_of_seats(seats[i]);
				            cmp.setCos_value(cosvalue);
				            client.insert("insertCOS_Details", cmp);  
			          		}
			           }
			           else
			        	   {
			        	   for(int i=0;i<counter;i++)
			        	   {	            	
			        		   String cosvalue=category[i]+gender[i]+subjectCode;
			            		cmp.setNo_of_times(xFactor[i]);
					            cmp.setNo_of_seats(seats[i]);
					            cmp.setCos_value(cosvalue);
					            client.insert("insertCOS_Details", cmp);
			        	   }
			            
			            
			           
			            }
			            System.out.println("COS Records are inserted");
			        } catch (SQLException e) {
			            e.printStackTrace();
			            logObj.logger.error("exception in methodinsertcutoffdetails = " +
			                e);
			        }

			       
			
			
		}

		public void setCOS_WithoutGender(String[] category, 
				String subjectCode, String[] seats, String[] xFactor,
				String entityID, String programID, String userID,
				Date startDate, Date endDate,int counter) {
			CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
			System.out.println("System is going to insert COS records");
			        try {
			            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
			                           .substring(0, 19);
			           Date sDate=new java.sql.Date(startDate.getYear(),startDate.getMonth(),startDate.getDate());
			           Date eDate=new java.sql.Date(endDate.getYear(),endDate.getMonth(),endDate.getDate());
			           System.out.println("Start Date is"+startDate.toString()+"End Date is"+endDate.toString());
			            
			           
			            cmp.setNo_of_times_active("I");
			           
			            cmp.setInsert_time(date);
			            cmp.setProgram_id(programID);
			            cmp.setEntity_id(entityID);
			         //Commented by Arjun   cmp.setBranch_code(branch_code);
			            cmp.setCut_off_number(0);
			            cmp.setCut_off_number_active("A");
			            cmp.setCut_off_percentage(0);
			            cmp.setCut_off_percentage_active("I");
			            cmp.setSettings("I");
			          //Commented by Arjun cmp.setSession_start_date(dateSelected);
			          //Commented by Arjun cmp.setSession_end_date(dateSelected1);
			            cmp.setSession_start_date(sDate.toString());
			            cmp.setSession_end_date(eDate.toString());
			            cmp.setCreator_id(userID);
			            //Commented by Arjun cmp.setSpecialization_id(specialization_id);
			           
			        	   for(int i=0;i<counter;i++)
			        	   {	 
			        		   String cosvalue=category[i]+"X"+subjectCode;
			            		cmp.setNo_of_times(xFactor[i]);
					            cmp.setNo_of_seats(seats[i]);
					            cmp.setCos_value(cosvalue);
					            client.insert("insertCOS_Details", cmp);
			        	   		
			        	   }
			            
			            
			           
			            
			            System.out.println("COS Records are inserted");
			        } catch (SQLException e) {
			            e.printStackTrace();
			            logObj.logger.error("exception in methodinsertcutoffdetails = " +
			                e);
			        }

			       
			
			
		}


		
		public CM_ProgramInfoGetter[] getCAT_GEN(String userID,
				String programID, String entityID, String subjectCode) {
			List<CM_ProgramInfoGetter> cat_genList=new ArrayList<CM_ProgramInfoGetter>();
			CM_ProgramInfoGetter cmp=new CM_ProgramInfoGetter();
			cmp.setUniversity_id(userID.substring(1, 5));
			cmp.setProgram_id(programID);
			cmp.setEntity_id(entityID);
			cmp.setSubject_code(subjectCode);
			try
			{
				cat_genList=client.queryForList("getCAT_GEN",cmp);
				return (CM_ProgramInfoGetter[])cat_genList.toArray(new CM_ProgramInfoGetter[cat_genList.size()]);
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getting Category and Gender" + e);
			}
			return null;
		}


		
		public CM_ProgramInfoGetter[] getCAT(String userID, String programID,
				String entityID, String subjectCode) {
			List<CM_ProgramInfoGetter> catList=new ArrayList<CM_ProgramInfoGetter>();
			CM_ProgramInfoGetter cmp=new CM_ProgramInfoGetter();
			cmp.setUniversity_id(userID.substring(1, 5));
			cmp.setProgram_id(programID);
			cmp.setEntity_id(entityID);
			cmp.setSubject_code(subjectCode);
			try
			{
				catList=client.queryForList("getCAT",cmp);
				return (CM_ProgramInfoGetter[])catList.toArray(new CM_ProgramInfoGetter[catList.size()]);
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in getting Category and Gender" + e);
			}
			return null;
		}
		
		public Integer checkCOSWithoutGender(String entityID, String programID,String subjectCode) {
			Integer count=null;
			CM_ProgramInfoGetter cmp=new CM_ProgramInfoGetter();
			cmp.setProgram_id(programID); 
			cmp.setEntity_id(entityID);
			cmp.setSubject_code(subjectCode);
			try
			{
				count=(Integer)client.queryForObject("COS_WithoutCG",cmp);
				return count;
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in Getting COS without Gender Impact" + e);
			}
			return null;
		}
		
		public Integer checkCOSWithGender(String entityID, String programID,String subjectCode) {
			Integer count=null;
			CM_ProgramInfoGetter cmp=new CM_ProgramInfoGetter();
			cmp.setProgram_id(programID); 
			cmp.setEntity_id(entityID);
			cmp.setSubject_code(subjectCode);
			try
			{
				count=(Integer)client.queryForObject("COS_WithCG",cmp);
				return count;
			}
			catch(Exception e)
			{
				logObj.logger.error("Exception in Getting COS without Gender Impact" + e);
			}
			return null;
		}
		
		
		
}
