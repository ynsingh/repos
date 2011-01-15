package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.CM_CourseInfoGetter;
import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_StudentInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_instituteInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("serial")
public class CM_connectTempImpl extends RemoteServiceServlet implements CM_connectTemp {
	 
	    SqlMapClient client = SqlMapManager.getSqlMapClient();
	    Log4JInitServlet logObj = new Log4JInitServlet();
	    
	    public String Entity_Course(String univ,String courseID, String course,boolean status) {
			 @SuppressWarnings("unused")
				Object li;
	
			        try {
			        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
			        	 

			        	    CMDG.setuniversity_code(univ);
			        	    CMDG.setcourseCode(courseID);
			                CMDG.setcourse_name(course);
			                CMDG.setStatus(status);
			             
			                li = client.insert("insertCourse", CMDG);
			               
			          
			        } catch (SQLException sqle) {
			            System.out.println(sqle);
			        } catch (Exception ex) {
			            System.out.println(ex);
			        }

			        return null;
		}

	    @SuppressWarnings("unchecked")
		public CM_CourseInfoGetter[] getCourseCode(String univ,boolean status) {
	   	 List li;
	
	        try {
	        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
	        	 

	        	    CMDG.setuniversity_code(univ);
	        	    CMDG.setStatus(status);
	        	  
	                li = client.queryForList("Getcoursecode", CMDG);
	                return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
	          
	        } catch (SQLException sqle) {
	            System.out.println(sqle);
	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
	        return null;
		}


		@SuppressWarnings("unchecked")
		public CM_CourseInfoGetter[] getCourseName(String univ, String courseID) {
		 List li;
	
        try {
        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
        	 

        	    CMDG.setuniversity_code(univ);
        	    CMDG.setcourseCode(courseID);
        	    CMDG.setStatus(true);
             
                li = client.queryForList("courseName", CMDG);
                return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
          
        } catch (SQLException sqle) {
            System.out.println(sqle);
        } catch (Exception ex) {
            System.out.println(ex);
        }
	
        return null;
		}
		
	
		public String deleteCourse(String univ, String courseID) {
			 @SuppressWarnings("unused")
			Object li;
	
		        try {
		        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
		        	 

		        	    CMDG.setuniversity_code(univ);
		        	    CMDG.setcourseCode(courseID);
		        	    CMDG.setStatus(false);
		             
		                li = client.delete("deleteCourse", CMDG);
		               
		          
		        } catch (SQLException sqle) {
		            System.out.println(sqle);
		        } catch (Exception ex) {
		            System.out.println(ex);
		        }

		        return null;
		}
		

		public String CourseType(String univ, String coursetypeId, String course_type_name) {
			 @SuppressWarnings("unused")
				Object li;

			        //Begin The Try-Catch Loop		
			        try {
			        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
			        	 

			        	    CMDG.setuniversity_code(univ);
			        	    CMDG.setcourse_Type(coursetypeId);
			                CMDG.setcourse_type_name(course_type_name);
			                CMDG.setStatus(true);
			             
			                li = client.insert("insertCourseType", CMDG);
			               
			          
			        } catch (SQLException sqle) {
			            System.out.println(sqle);
			        } catch (Exception ex) {
			            System.out.println(ex);
			        }

			        return null;
		}

		
		
		@SuppressWarnings("unchecked")
		public CM_CourseInfoGetter[] getCourseType(String univ) {
			 List li;

		        try {
		        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
		        	 

		        	    CMDG.setuniversity_code(univ);
		        	    CMDG.setStatus(true);
		        	  
		                li = client.queryForList("GetcourseType", CMDG);
		                return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
		          
		        } catch (SQLException sqle) {
		            System.out.println(sqle);
		        } catch (Exception ex) {
		            System.out.println(ex);
		        }

		        return null;

		}

		
		@SuppressWarnings("unchecked")
		public CM_CourseInfoGetter[] getCourseTypeName(String univ, String coursetypeID) {
			 List li;

		        try {
		        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
		        	 

		        	    CMDG.setuniversity_code(univ);
		        	    CMDG.setcourse_Type(coursetypeID);
		        	    CMDG.setStatus(true);
		             
		                li = client.queryForList("courseTypeName", CMDG);
		                return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
		          
		        } catch (SQLException sqle) {
		            System.out.println(sqle);
		        } catch (Exception ex) {
		            System.out.println(ex);
		        }
		        
		        return null;
		}
		
		
	
		public String deleteCourseType(String univ, String coursetypeID) {
			 @SuppressWarnings("unused")
				Object li;
	
			        try {
			        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
			        	 

			        	    CMDG.setuniversity_code(univ);
			        	    CMDG.setcourse_Type(coursetypeID);
			        	    CMDG.setStatus(false);
			        
			             
			                li = client.delete("deleteCourseType", CMDG);
			               
			          
			        } catch (SQLException sqle) {
			            System.out.println(sqle);
			        } catch (Exception ex) {
			            System.out.println(ex);
			        }

			        return null;

		}

		@SuppressWarnings("unchecked")
		public CM_CourseInfoGetter[] CourseTypeName(String univ) {
			 List li;
	
		        try {
		        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
		        	 

		        	    CMDG.setuniversity_code(univ);
		        	    CMDG.setStatus(true);
		        	   
		        
		             
		                li = client.queryForList("GetcourseTypeName", CMDG);
		                return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
		          
		        } catch (SQLException sqle) {
		            System.out.println(sqle);
		        } catch (Exception ex) {
		            System.out.println(ex);
		        }

		        return null;
		}

	
		@SuppressWarnings("unchecked")
		public CM_ProgramInfoGetter[] getProgramme() {
			 List li;
	
		        try {
		        	
		        	CM_ProgramInfoGetter CMDG = new  CM_ProgramInfoGetter();
		        
		             
		                li = client.queryForList("Getprogram_name", CMDG);
		                return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
		          
		        } catch (SQLException sqle) {
		            System.out.println(sqle);
		        } catch (Exception ex) {
		            System.out.println(ex);
		        }

		        return null;
		}
		
		@SuppressWarnings("unchecked")
		public CM_ProgramInfoGetter[] getProgrammeOff(String entity) throws Exception {
			 List li;

		        try {
		        	  logObj.logger.info("in prog off");
		        	CM_ProgramInfoGetter CMDG = new  CM_ProgramInfoGetter();
		        	
		        	CMDG.setentity_name(entity);
		      
		                li = client.queryForList("GetprogramOff", CMDG);
		                return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
		          
		        }  catch (Exception ex) {
		        	  logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}

		
		@SuppressWarnings("unchecked")
		public CM_CourseInfoGetter[] CourseName(String univ) {
			 List li;
	
		        try {
		        	  logObj.logger.info("in coursename");
		        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
		        	 

		        	    CMDG.setuniversity_code(univ);
		        	    CMDG.setStatus(true);
		        
		             
		                li = client.queryForList("GetNameofCourse", CMDG);
		                return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
		          
		        }catch (Exception ex) {
		        	  logObj.logger.error(ex);
		            System.out.println(ex);
		        }

		        //Return the Value of s in Function	
		        return null;
		}

		@SuppressWarnings("unchecked")
		public  CM_CourseInfoGetter[] getCourseCode(String univ, String courseName) {
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	  logObj.logger.info("in course code");
		        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
		        	 

		        	    CMDG.setuniversity_code(univ);
		        	    CMDG.setcourse_name(courseName);
		        	   
		        
		             
		                li = client.queryForList("GetcourseName", CMDG);
		                return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
		          
		        }  catch (Exception ex) {
		        	  logObj.logger.error(ex);
		            System.out.println(ex);
		        }

		        //Return the Value of s in Function	
		        return null;
		}
		

		@SuppressWarnings("unchecked")
	
		public boolean checkCourseCode(String univ, String CourseId) {
			boolean flag = false;
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	  logObj.logger.info("in course code");
		        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
		        	 

		        	    CMDG.setuniversity_code(univ);
		        	    CMDG.setStatus(true);
		        	   
		             
		                li = client.queryForList("checkcourse", CMDG);
		                for(int i=0;i<li.size();i++){
		              
		               CMDG= (CM_CourseInfoGetter)li.get(i);
		               
		               if(CMDG.getcourseCode().equals(CourseId))
		               
		            	   flag=true;
		                }
		            	return flag;
		          
		        } catch (SQLException sqle) {
		            System.out.println(sqle);
		        } catch (Exception ex) {
		        	  logObj.logger.error(ex);
		            System.out.println(ex);
		        }

		       
			return flag;
		}
	

		@SuppressWarnings("unchecked")
		public boolean checkCourseCodeType(String univ, String CourseTypeId) {
			boolean flag = false;
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	
		        	  logObj.logger.info("in coursecode type");
		        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
		        	 

		        	    CMDG.setuniversity_code(univ);
		        	    CMDG.setStatus(true);
		        	   
		             
		                li = client.queryForList("checkcourseType", CMDG);
		                for(int i=0;i<li.size();i++){
		              
		               CMDG= (CM_CourseInfoGetter)li.get(i);
		            
		               
		               if(CMDG.getCourse_Type().equals(CourseTypeId))
		               
		            	   flag=true;
		                }
		            	return flag;
		          
		        } catch (SQLException sqle) {
		            System.out.println(sqle);
		        } catch (Exception ex) {
		        	  logObj.logger.error(ex);
		            System.out.println(ex);
		        }

		       
			return flag;
		}

		@SuppressWarnings("unchecked")
	
		public CM_ProgramInfoGetter[] getProgramId(String progName) throws Exception {
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	  logObj.logger.info("in get programid");
		        	CM_ProgramInfoGetter CMDG = new  CM_ProgramInfoGetter();
		        	 

		        	    CMDG.setprogram_name(progName);
		        	  
		                li = client.queryForList("Getprogram_id", CMDG);
		               
		                return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
		          
		        }catch (Exception ex) {
		        	  logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}
		
		
		@SuppressWarnings("unchecked")
		public CM_ProgramInfoGetter[] getProgramOffId() throws Exception {
			 List li;

		        try {
		        	  logObj.logger.info("in prog offrd id");
		        	
		        	    CM_ProgramInfoGetter CMDG = new  CM_ProgramInfoGetter();
		        	 
		                li = client.queryForList("Getprogram_offid", CMDG);
		              
		                return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
		          
		        }  catch (Exception ex) {
		        	  logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}
		
		
		public String programCourse(String[] prog) {
			@SuppressWarnings("unused")
			Object li;	
	        try {

				 CM_ProgramInfoGetter CMDG = new  CM_ProgramInfoGetter();
	        	 

	        	    CMDG.setuniversity_code("DEI");
	        	    CMDG.setprogram_id(prog[0]);
	        	    CMDG.setEntity_program_TermId(prog[1]);
	                CMDG.setcourseCode(prog[2]);
	        	    CMDG.setFlag(prog[3]);
	        	    CMDG.setAvailable(prog[4]);
	        	    CMDG.setcourse_Type(prog[6]);
	        	    CMDG.setCredits(prog[7]);
	        	    CMDG.setlectures(prog[8]);
	        	    CMDG.settutorials(prog[9]);
	        	    CMDG.setprac(prog[10]);
	        	    
	        	    CMDG.setunits(prog[11]);
	        	    
	        	    CMDG.setstatus(true);
	                li = client.insert("insertProg", CMDG);
	               
	          
	        } catch (SQLException sqle) {
	            System.out.println(sqle);
	        } catch (Exception ex) {
	            System.out.println(ex);
	        }

	        return null;
			
			
		}
	

		@SuppressWarnings("unchecked")
	
		public CM_CourseInfoGetter[] TypeID(String univ, String coursetypeName) {
			 List li;

		        try {
		        	 CM_CourseInfoGetter CMDG = new  CM_CourseInfoGetter();
		        	 

		        	    CMDG.setuniversity_code(univ);
		        	    CMDG.setcourse_type_name(coursetypeName);
		        	    CMDG.setStatus(true);
		             
		                li = client.queryForList("GetTypeId", CMDG);
		                return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
		          
		        } catch (SQLException sqle) {
		            System.out.println(sqle);
		        } catch (Exception ex) {
		            System.out.println(ex);
		        }

		        return null;
		}

		@SuppressWarnings("unchecked")
	
		public CM_ProgramInfoGetter[] programDetails(String[] progdetails) {
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		     
		        	 CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();
		        	 

		        	    CMDG.setprogram_id(progdetails[0]);
		        	    CMDG.setEntity_program_TermId(progdetails[1]);
			            CMDG.setstatus(true);
		             
		                li = client.queryForList("selectProgDetails", CMDG);
		                return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
		          
		        } catch (SQLException sqle) {
		            System.out.println(sqle);
		        } catch (Exception ex) {
		            System.out.println(ex);
		        }

		        return null;
		}

		

		public String editProg(String[] editProg) {
			 try {
				 String Update = "";
			        
					@SuppressWarnings("unused")
					Object li;  

			        //Begin The Try-Catch Loop
			        try {
			          
			        	CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();
			        	
			        	CMDG.setprogram_id(editProg[0]);
			        	CMDG.setcourseCode(editProg[1]);
			            CMDG.setCredits(editProg[2]);
			            CMDG.setlectures(editProg[3]);
			            CMDG.settutorials(editProg[4]);
			            CMDG.setprac(editProg[5]);
			            CMDG.setunits(editProg[6]);
			            CMDG.setAvailable(editProg[7]);
			           
			      
			            li = client.update("editProg",CMDG);
			         
			        } catch (SQLException sqle) {
			            System.out.println(sqle);
			        } catch (Exception ex) {
			            System.out.println(ex);
			        }
				
					return Update;

		        } catch (Exception ex) {
		            System.out.println(ex);
		        }

		        return null;
		}

		public String disableProg(String courseCode) {
			 String Update = "";
		        
				@SuppressWarnings("unused")
				Object li;  

		        try {
		          
		        	 CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();
		           
		        	 CMDG.setcourseCode(courseCode);
		             CMDG.setstatus(false);
		           
		      
		            li = client.update("deleteProgAgeLimit",CMDG);
		         
		        } catch (SQLException sqle) {
		            System.out.println(sqle);
		        } catch (Exception ex) {
		            System.out.println(ex);
		        }

		      
		        return Update;
		}
		
		@SuppressWarnings("unchecked")
		public CM_instituteInfoGetter[] methodgetDesignation() {
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	CM_instituteInfoGetter CSIG = new CM_instituteInfoGetter();
		     
		                li = client.queryForList("getDesignation", CSIG);
		                return ( CM_instituteInfoGetter[]) li.toArray(new  CM_instituteInfoGetter[li.size()]);
		          
		        } catch (SQLException sqle) {
		            System.out.println(sqle);
		        } catch (Exception ex) {
		            System.out.println(ex);
		        }

		        return null;
		}
	    
		
		@SuppressWarnings("unchecked")
		@Override
		public CM_entityInfoGetter[] Entity_Name(String univID,String Type) throws Exception {
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	
		        	  logObj.logger.info("in EntityName");
		        	CM_entityInfoGetter CSIG = new CM_entityInfoGetter();
		        	CSIG.setUniversity_id(univID.substring(1,5));
		        	CSIG.setEntity_type(Type);
		        	
		     
		                li = client.queryForList("getEntityName", CSIG);
		                return (CM_entityInfoGetter[]) li.toArray(new  CM_entityInfoGetter[li.size()]);
		          
		        }  catch (Exception ex) {
		        	  logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}

		@SuppressWarnings("unchecked")
		public boolean checkProgrammeCourse(String univ, String ProgrmId,
				String CourseId, String sem) {
			boolean flag = false;
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	 CM_ProgramInfoGetter CMDG = new  CM_ProgramInfoGetter();
		        	
		        	    CMDG.setuniversity_code(univ);
		        	    CMDG.setprogram_id(ProgrmId);
		        	    CMDG.setEntity_program_TermId(sem);
		        	    CMDG.setstatus(true);
		        	   
		             
		                li = client.queryForList("checkProgram", CMDG);
		                for(int i=0;i<li.size();i++){
		              
		               CMDG= (CM_ProgramInfoGetter)li.get(i);
		               
		               if(CMDG.getcourseCode().equals(CourseId))
		               
		            	   flag=true;
		                }
		            	return flag;
		          
		        }catch (Exception ex) {
		            System.out.println(ex);
		        }

		       
			return flag;
		}

		@SuppressWarnings("unchecked")
		public CM_entityInfoGetter[] Entity_Description(String univID) throws Exception {
			 try {
			List li;
			int count=0;
			  logObj.logger.info("in EntityDesc");
		         
		     	CM_entityInfoGetter CSIG = new CM_entityInfoGetter();
	        	CSIG.setUniversity_id(univID);
	        
	     
	              li = client.queryForList("getEntityDesc", CSIG);
		        
		         count = li.size();
		        
		         CM_entityInfoGetter[] CMDG = new CM_entityInfoGetter[count];
		    
		             for(int i=0;i<count;i++){
		            	 
		            	 CMDG[i] = (CM_entityInfoGetter)li.get(i);
		            	  
		             }
		         	
		             return CMDG;
		     }  catch (Exception ex) {
		    	  logObj.logger.error(ex);
		    	 throw new Exception(ex);
		     }
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public CM_ProgramInfoGetter[] papercodeGroupCount(String progID) throws Exception {
			 List li;
			
		        //Begin The Try-Catch Loop		
		        try {
		        	  logObj.logger.info("in papercodeGroupCount");
		        	 CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();
		        	 
		        	 CMDG.setprogram_id(progID);
		        	 
		             li = client.queryForList("GetGrouping", CMDG);
		            
		             return (CM_ProgramInfoGetter[]) li.toArray(new  CM_ProgramInfoGetter[li.size()]);
		             
		         
		          
		        } catch (Exception ex) {
		        	  logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
			
		}
		@SuppressWarnings("unchecked")
		@Override
		public CM_ProgramInfoGetter[] DistinctPaperGroupingCount(String progId, String group) throws Exception{
			 List li;
		
		        //Begin The Try-Catch Loop		
		        try {
		        	  logObj.logger.info("in DistinctPaperGroupingCount");     
		        	 CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();
		  
		        	 CMDG.setprogram_id(progId);
		        	 CMDG.setGrouping(group);
		
		        	 li = client.queryForList("getPaperCode", CMDG);
		        	   
		        	 return (CM_ProgramInfoGetter[]) li.toArray(new  CM_ProgramInfoGetter[li.size()]);
		         
		          
		        } catch (Exception ex) {
		        	logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
				
		}


		@SuppressWarnings("unchecked")
		public String[][] getProgramComponents( String programID,String branchCode) throws Exception {
			List li;
			int count = 0;
			
			String[][]  coa = null;
			try {
				  logObj.logger.info("in getProgramComponents");
	                
	                CM_ProgramInfoGetter CMDG2 = new  CM_ProgramInfoGetter();
	                CMDG2.setProgram_id(programID);
	                CMDG2.setBranch_code(branchCode);
	                
	                li = client.queryForList("GetPCcount", CMDG2);
	                count=li.size();
	            
	                CM_ProgramInfoGetter CMDG3 = new  CM_ProgramInfoGetter();
	                CMDG3.setProgram_id(programID);
	                CMDG3.setBranch_code(branchCode);
	                
	                List   liPC = client.queryForList("GetProgComponents", CMDG3);
	         
				 
				    coa = new String[count][7];
				
				    for(int i=0;i<liPC.size();i++){
					   
					CMDG3=(CM_ProgramInfoGetter)liPC.get(i);
				
					String component_id=CMDG3.getComponent_id();
				
					
					CMDG2.setComponent_id(component_id);
		            
		            li = client.queryForList("GetComponent_name", CMDG2);
		                
		            CMDG2=(CM_ProgramInfoGetter)li.get(0);
					
			
					coa[i][0] = CMDG2.getComponent(); 
					
					coa[i][1] = CMDG3.getType();		
					
					coa[i][2] = CMDG3.getSequence();		
					
					coa[i][3] = CMDG3.getBoard_flag();	
					
					coa[i][4] = CMDG3.getUGorPG();	
					
					coa[i][5] = component_id;
					
					coa[i][6] = CMDG3.getWeightage_flag();	
				
				}
				
			

				return coa;
			} catch (Exception e) {
				  logObj.logger.error(e);
				throw new Exception(e);
			}
		}


		@SuppressWarnings("unchecked")
		@Override
		public CM_ProgramInfoGetter[] FirstDegreeCode(String progID,String Type) throws Exception {
			 List li;
			 

		        //Begin The Try-Catch Loop		
		        try {
		        	  logObj.logger.info("in firstdegree");
		        	  CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();
		        	
		        	   CMDG.setprogram_id(progID);
		        	   CMDG.setUGorPG(Type);
		        	 
		                li = client.queryForList("getFirstDegreeCode", CMDG);
		            
		                return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
		          
		        } catch (Exception ex) {
		        	  logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}

		public String PersonalInfo(String[] student,String creatorId) throws Exception {
			 @SuppressWarnings("unused")
				Object li;

			        //Begin The Try-Catch Loop		
			        try {
			        	  logObj.logger.info("in PersonalInfo");
			        	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
	                     .substring(0, 19);
			        	CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
			        	
			        	    
			    		   CMDG.setfirst_name(student[0]);
			    		   CMDG.setmiddle_name(student[1]);
			    		   CMDG.setlast_name(student[2]);
			    		   CMDG.setgender(student[3]);
			    		   CMDG.setcategory(student[4]);
			    		   CMDG.setdate_of_birth(student[5]);
			    		   CMDG.setfather_Fname(student[6]);
			    		   CMDG.setfather_Mname(student[7]);
			    		   CMDG.setfather_Lname(student[8]);
			    		   CMDG.setmother_Fname(student[9]);
			    		   CMDG.setmother_Mname(student[10]);
			    		   CMDG.setmother_Lname(student[11]);
			    		   CMDG.setInsert_time(date);
			    		   CMDG.setUser_id(student[14]);
			    		   CMDG.setCreator_id(creatorId);
			    		   
			             
			                li = client.insert("insertPersonalInfo", CMDG);
			               
			          
			        }catch (Exception ex) {
			        	  logObj.logger.error(ex);
			        	throw new Exception(ex);
			        }

			        return null;
		}
		
		
		@SuppressWarnings("unchecked")
		public CM_StudentInfoGetter getEnrolledStudentPersonalInfo(String enrollNumber) throws Exception {
			 
				List li;

			        //Begin The Try-Catch Loop		
			        try {
			        	  logObj.logger.info("in getEnrolledStudentPersonalInfo");
			        	 
			        	CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
			        	CMDG.setEnrollmentNumber(enrollNumber);
			        	   li = client.queryForList("getEnrolledStudentPersonalInfo", CMDG);
			               
			               if(li.size()==0){
			            	   CMDG=null;
			               }else{
			                CMDG= (CM_StudentInfoGetter)li.get(0);
			               }
			                return CMDG; 
			        }catch (Exception ex) {
			        	  logObj.logger.error(ex);
			        	throw new Exception(ex);
			        }

			        
		}


		@Override
		public String addressInfo(String[] address,String creatorId) throws Exception {
			 @SuppressWarnings("unused")
				Object li;

			        //Begin The Try-Catch Loop		
			        try {
			        	  logObj.logger.info("in address info");
			        	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
	                     .substring(0, 19);
			        	CM_entityInfoGetter CMDG = new  CM_entityInfoGetter();
			        	
			        	CMDG.setEntity_id(address[0]);
			        	CMDG.setAddress_line1(address[1]);
			        	CMDG.setAddress_line2(address[5]);
			        	CMDG.setcity(address[2]);
			        	CMDG.setstate(address[3]);
			        	CMDG.setpincode(Integer.parseInt(address[4]));
			        	CMDG.setCreator_id(creatorId);
			        	CMDG.setInsert_time(date);
			             
			                li = client.insert("insertaddressInfo", CMDG);
			               
			          
			        } catch (Exception ex) {
			        	  logObj.logger.error(ex);
			        	throw new Exception(ex);
			        }

			        
			        return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public String paperCodeInfo(String ProgId, String RegNo, String FormNo,
				String PaperCode, String entity_id,String creatorId, String grouping) throws Exception {
			 @SuppressWarnings("unused")
				Object li;
			 	List li3;

			        //Begin The Try-Catch Loop		
			        try {
			        	  logObj.logger.info("in papercodeInfo");
			        	  
			       	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                     .substring(0, 19);
			       	 
			        	CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
			        	CM_entityInfoGetter CMEG = new  CM_entityInfoGetter();
			        	
			        	   CMDG.setProgramId(ProgId);
			    		   CMDG.setForm_number(FormNo);
			    		   CMDG.setRegistrationNumber(RegNo);
			    		   CMDG.setPapercode(PaperCode);
			    	
			    		   CMEG.setEntity_id(entity_id);
			    		   
			    		   li3=client.queryForList("Getsessiondate",CMEG);
			        	
			        	   CMEG= (CM_entityInfoGetter)li3.get(0);
			        	   
			        	   String Start_date=CMEG.getStartdate();
			        	   String end_date=CMEG.getEnddate();
			        	   
			        	   CMDG.setStartdate(Start_date);
			        	   CMDG.setEnddate(end_date);
			        	   CMDG.setInsert_time(date);
			        	   CMDG.setCreator_id(creatorId);
			        	   CMDG.setGrouping(grouping);
				        	
			                li = client.insert("insertPaperCodeInfo", CMDG);
			               
			          
			        }  catch (Exception ex) {
			        	  logObj.logger.error(ex);
			        	throw new Exception(ex);
			        }

			        return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public String StudentcallListInfo(String entity_id, String ProgId,String branchID,
				String RegNo, String[] data, String creatorId) throws Exception {
			 @SuppressWarnings("unused")
				Object li;
			    List li3;

			        //Begin The Try-Catch Loop		
			        try {
			        	 logObj.logger.info("in studentcalllist");
			        	  
			       	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                     .substring(0, 19);
			        	
			        	CM_entityInfoGetter CMEG = new  CM_entityInfoGetter();
			        	
			        	CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
			        	
			        	
							for(int j=1;j<5;j++){
					
								if(Float.parseFloat(data[j])>0==false)
									data[j]=null;
							}
			        	   
			        
			        	   CMDG.setentity_id(entity_id);
			        	   CMDG.setProgramId(ProgId);
			        	   CMDG.setBranch(branchID);
			    		   CMDG.setRegistrationNumber(RegNo);
			    		   CMDG.setComponentID(data[0]);
			    		   CMDG.setMarksPercentage(data[1]);
			    		   CMDG.setMarksObtained(data[2]);
			    		   CMDG.setTotalMarks(data[3]);
			    		   CMDG.setScore(data[4]);
			    		   CMDG.setboard_id(data[5]);
			    		   CMEG.setEntity_id(entity_id);
			    		   
			    		   li3=client.queryForList("Getsessiondate",CMEG);
			        	
			        	   CMEG= (CM_entityInfoGetter)li3.get(0);
			        	   
			        	   String Start_date=CMEG.getStartdate();
			        	   String end_date=CMEG.getEnddate();
			        	   
			        	   CMDG.setStartdate(Start_date);
			        	   CMDG.setEnddate(end_date);
			        	   CMDG.setInsert_time(date);
			        	   CMDG.setCreator_id(creatorId);
				        	
			             
			               li = client.insert("insertstudentCallListInfo", CMDG);
			               
			          
			        }  catch (Exception ex) {
			        	  logObj.logger.error(ex);
			        	throw new Exception(ex);
			        }

			        return null;
		}

		@SuppressWarnings({ "unchecked" })
		@Override
		public String studentSpWt(String progID, String RegNo,
				String WtId, String entity_id,String creatorid_modifierid) throws Exception {
			 @SuppressWarnings("unused")
				Object li,li2;
			 	List li3 ,li4;
			    CM_entityInfoGetter CMEG= new CM_entityInfoGetter();
			 	
			        //Begin The Try-Catch Loop		
			        try {
			        	  logObj.logger.info("in studentspwt");
			        	String insert_time=null;
			        	String modification_time=null;
			        	String creator=null;
			        	String modifier=null;
			        	
			       	  String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                      .substring(0, 19);
			        	
			       	  CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
			       	 
			       	  CM_StudentInfoGetter CMSG = new  CM_StudentInfoGetter();
			   
			        	   CMDG.setuniversity_code(creatorid_modifierid.substring(1,5));
			        	   CMDG.setProgramId(progID);
			    		   CMDG.setRegistrationNumber(RegNo);
			    		   CMDG.setWeightageID(WtId);
			    		  
			    		   CMEG.setEntity_id(entity_id);
			    		   
			    		   li3=client.queryForList("Getsessiondate",CMEG);
			        	   CMEG= (CM_entityInfoGetter)li3.get(0);
			        	   
			        	   String Start_date=CMEG.getStartdate();
			        	   String end_date=CMEG.getEnddate();
			        	
			        	   CMDG.setStartdate(Start_date);
			        	   CMDG.setEnddate(end_date);
			        	
			        	   
			        	   CMSG.setRegistrationNumber(RegNo);
			        	   CMSG.setWeightageID(WtId);
			        	   li4=client.queryForList("GetInsertCreator",CMSG);
			        	   
			        	   if(li4.size()>0){
			        		   
			        	   CM_StudentInfoGetter CMSG1 = new  CM_StudentInfoGetter();
			        	   CMSG1= (CM_StudentInfoGetter)li4.get(0);
			        	
			        	   insert_time=CMSG1.getInsert_time();
			        	   creator=CMSG1.getCreator_id();
			        	   modification_time=date;
			        	   modifier=creatorid_modifierid;
			        	   
			        	   CMSG.setRegistrationNumber(RegNo);
			        	   CMSG.setWeightageID(WtId);
			        	   li2=client.delete("deleteStudentSpWt",CMSG);
			        	
			        		   
			        	   }
			        	   
			        	   else{
			        		   
			        		   insert_time=date;
			        		   creator=creatorid_modifierid;
			        		   modification_time=null;
				        	   modifier=null;
			        		   
			        		  
			        	   }
			        	   
			        	   CMDG.setInsert_time(insert_time);
			        	   CMDG.setCreator_id(creator);
			        	   CMDG.setModification_time(modification_time);
			        	   CMDG.setModifier_id(modifier);
			        	   
			                li = client.insert("insertstudentSpWtInfo", CMDG);
			               
			          
			        }  catch (Exception ex) {
			        	  logObj.logger.error(ex);
			        	throw new Exception(ex);
			        }

			        return null;
		}

		@SuppressWarnings("unchecked")
		public CM_ProgramInfoGetter[] getBranch(String progId, String entity_id) throws Exception {
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	  logObj.logger.info("in getBranch");
		        	 CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();
		        	 
		        	    String univ_id= progId.substring(0, 4);
		        	   
		        	    CMDG.setprogram_id(progId);
		        	    CMDG.setEntity_id(entity_id);
		        	    CMDG.setUniversity_id(univ_id);
		        	    
		        	 
		                li = client.queryForList("GetBranch", CMDG);
		                return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
		          
		        }  catch (Exception ex) {
		        	  logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}


		@SuppressWarnings("unchecked")
		public CM_ProgramInfoGetter[] getBranchID(String progId,String branchName) throws Exception {
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	  logObj.logger.info("in getBranchID");
		        	 CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();
		        	 

		        	    CMDG.setprogram_id(progId);
		        	    CMDG.setBranchName(branchName);
		        	 
		                li = client.queryForList("GetBranchID", CMDG);
		                return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
		          
		        }  catch (Exception ex) {
		        	  logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
			}


		@SuppressWarnings("unchecked")
		@Override
		public CM_StudentInfoGetter[] getStudentPersonalInfo(String RegNo) throws Exception {
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	  logObj.logger.info("in getStudentPersonalInfo");
		        	 CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
		        	 

		        	    CMDG.setRegistrationNumber(RegNo);
		        	 
		                li = client.queryForList("GetStudentPersonalInfo", CMDG);
		                return ( CM_StudentInfoGetter[]) li.toArray(new  CM_StudentInfoGetter[li.size()]);
		          
		        }  catch (Exception ex) {
		        	  logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}

		@SuppressWarnings("unchecked")
		@Override
		public CM_entityInfoGetter[] getStudentAddressInfo(String StudId) throws Exception {
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	  logObj.logger.info("in getStudentAddressInfo");
		        	CM_entityInfoGetter CMDG = new  CM_entityInfoGetter();
		        	 

		        	    CMDG.setUser_id(StudId);
		        	 
		                li = client.queryForList("GetStudentAddressInfo", CMDG);
		                return ( CM_entityInfoGetter[]) li.toArray(new  CM_entityInfoGetter[li.size()]);
		          
		        } catch (Exception ex) {
		        	  logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}
		
		@Override
		public CM_entityInfoGetter[] getEnrolledStudentAddressInfo(String studId) throws Exception {
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	  logObj.logger.info("in getStudentAddressInfo");
		        	CM_entityInfoGetter CMDG = new  CM_entityInfoGetter();
		        	 

		        	    CMDG.setUser_id(studId);
		        	 
		                li = client.queryForList("GetEnrolledStudentAddressInfo", CMDG);
		                return ( CM_entityInfoGetter[]) li.toArray(new  CM_entityInfoGetter[li.size()]);
		          
		        } catch (Exception ex) {
		        	  logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}


		@SuppressWarnings("unchecked")
		@Override
		public CM_StudentInfoGetter[] getStudentPaperInfo(String RegNo,String ProgId) throws Exception {
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	  logObj.logger.info("in getStudentPaperInfo");
		     
		        	 CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
		        	 

		        	    CMDG.setRegistrationNumber(RegNo);
		        	 
		                li = client.queryForList("GetStudentPaperInfo", CMDG);
		             
		                return ( CM_StudentInfoGetter[]) li.toArray(new  CM_StudentInfoGetter[li.size()]);
		          
		        } catch (Exception ex) {
		        	  logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }

		}

		@SuppressWarnings("unchecked")
		@Override
		public CM_StudentInfoGetter[] getStudentSpWt(String RegNo,String ProgId) throws Exception {
		
			 List li;

		        try {
		        	  logObj.logger.info("getStudentSpWt");
		        	 CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
		        	 

		        	    CMDG.setRegistrationNumber(RegNo);
		        	    CMDG.setProgramId(ProgId);
		        	 
		                li = client.queryForList("GetStudentSpWt", CMDG);
		                return ( CM_StudentInfoGetter[]) li.toArray(new  CM_StudentInfoGetter[li.size()]);
		          
		        }  catch (Exception ex) {
		        	  logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		
		}

		@SuppressWarnings("unchecked")
		@Override
		public CM_StudentInfoGetter[] getStudentCallListInfo(String RegNo) throws Exception {
			 List li;

		      
		        try {
		        	 logObj.logger.info("getStudentCallListInfo");
		        	 
		        	  CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
		        	 
		        	   CMDG.setRegistrationNumber(RegNo);
		        	
		                li = client.queryForList("GetStudentCallListInfo", CMDG);
		                
		           	   CM_StudentInfoGetter[] cs = new  CM_StudentInfoGetter[li.size()];
		           	 
		                for(int i=0;i<li.size();i++){
		                cs[i]= new CM_StudentInfoGetter();
		                cs[i]= (CM_StudentInfoGetter)li.get(i);
		          
		                String marksPercentage=cs[i].getMarksPercentage();
		                String marksObtained=cs[i].getMarksObtained();
		                String totalMarks=cs[i].getTotalMarks();
		                String score=cs[i].getScore();
		                
		                if(marksPercentage==null)
		                	marksPercentage="0";
		                
		                if(marksObtained==null)
		                	marksObtained="0";
		                
		                if(totalMarks==null)
		                	totalMarks="0";
		                
		                if(score==null)
		                	score="0";
		                
		                
		                cs[i].setMarksPercentage(marksPercentage);
		                cs[i].setMarksObtained(marksObtained);
		                cs[i].setTotalMarks(totalMarks);
		                cs[i].setScore(score);
		                }
		                
		                return cs;
		          
		        } catch (Exception ex) {
		        	 logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}

		@Override
		public String UpdatePersonalInfo(String[] student,String modifierId) throws Exception {
			 @SuppressWarnings("unused")
				Object li;
			  

			        try {
			        	 logObj.logger.info("update personal info");
			        	CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
			        	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
	                       .substring(0, 19);

			    		  
			        	   CMDG.setfirst_name(student[0]);
			    		   CMDG.setmiddle_name(student[1]);
			    		   CMDG.setlast_name(student[2]);
			    		   CMDG.setgender(student[3]);
			    		   CMDG.setcategory(student[4]);
			    		   CMDG.setdate_of_birth(student[5]);
			    		   CMDG.setfather_Fname(student[6]);
			    		   CMDG.setfather_Mname(student[7]);
			    		   CMDG.setfather_Lname(student[8]);
			    		   CMDG.setmother_Fname(student[9]);
			    		   CMDG.setmother_Mname(student[10]);
			    		   CMDG.setmother_Lname(student[11]);
			    		   CMDG.setForm_number(student[12]);
			    		   CMDG.setRegistrationNumber(student[13]);
			    		   CMDG.setModification_time(date);
			    		   CMDG.setModifier_id(modifierId);
			             
			                li = client.update("updatePersonalInfo", CMDG);
			               
			          
			        } catch (Exception ex) {
			        	 logObj.logger.error(ex);
			        	throw new Exception(ex);
			        }

			        return null;
		}

	
		@SuppressWarnings("unchecked")
		public String UpdateaddressInfo(String[] address,String modifierId) throws Exception {
			 @SuppressWarnings("unused")
				Object li;
			 List si;

			        try {
			        	 logObj.logger.info("in update address info");
			        	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
	                       .substring(0, 19);
			        	CM_entityInfoGetter CMDG = new  CM_entityInfoGetter();
			        	
			        	CM_StudentInfoGetter cs= new CM_StudentInfoGetter();
			        	cs.setRegistrationNumber(address[0]);
			        
		        	 
		                si = client.queryForList("GetStuId1", cs);
		                cs= (CM_StudentInfoGetter)si.get(0);
			        	  
			            String entity_id=cs.getUser_id();
			            CMDG.setEntity_id(entity_id);
			        	CMDG.setAddress_line1(address[1]);
			        	CMDG.setAddress_line2(address[5]);
			        	CMDG.setcity(address[2]);
			        	CMDG.setstate(address[3]);
			        	CMDG.setpincode(Integer.parseInt(address[4]));
			        	CMDG.setModification_time(date);
			    		CMDG.setModifier_id(modifierId);
			        
			                li = client.update("updateaddressInfo", CMDG);
			               
			          
			        }  catch (Exception ex) {
			        	 logObj.logger.error(ex);
			        	throw new Exception(ex);
			        }

			        //Return the Value of s in Function	
			        return null;
		}

		public String UpdatepaperCode(String RegNo, String OldPaperCode,String NewPaperCode,String modifierid,String PaperGroup) throws Exception {
			@SuppressWarnings("unused")
			Object li;

	        //Begin The Try-Catch Loop		
	        try {
	        	 logObj.logger.info("in update paper info");
	        	CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
	        	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                 .substring(0, 19);


	    
	    		    CMDG.setRegistrationNumber(RegNo);
	    		    CMDG.setPapercode(OldPaperCode);
	    		    CMDG.setNewpapercode(NewPaperCode);
	    			CMDG.setModification_time(date);
		    		CMDG.setModifier_id(modifierid);
		    		CMDG.setGrouping(PaperGroup);
		        	
	             
	                li = client.update("updatePaperCode", CMDG);
	               
	          
	        }  catch (Exception ex) {
	        	 logObj.logger.error(ex);
	        	throw new Exception(ex);
	        }

	        return null;
		}

	
		public String deleteStudentSpWt(String Wtid, String RegNo) throws Exception {
			@SuppressWarnings("unused")
			Object li;

	        //Begin The Try-Catch Loop		
	        try {
	        	 logObj.logger.info("in delete student spwt");
	        	
	        	CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
	        
	        	   CMDG.setWeightageID(Wtid);
	    		   CMDG.setRegistrationNumber(RegNo);
	    		   
	    		   
	             
	                li = client.delete("deleteStudentSpWt", CMDG);
	               
	          
	        }  catch (Exception ex) {
	        	 logObj.logger.error(ex);
	        	throw new Exception(ex);
	        }

	        return null;
		}

		

	
		@SuppressWarnings("unchecked")
		public CM_entityInfoGetter[] getStudentValue(String UnivId) throws Exception {
			 List li;
			 @SuppressWarnings("unused")
			Object newli;
		        //Begin The Try-Catch Loop		
		        try {
		        	 logObj.logger.info("in getstudentval");
		        	CM_entityInfoGetter CMDG = new  CM_entityInfoGetter();
		        	 

		        	    CMDG.setUniversity_id(UnivId.substring(1,5));
		        	    CMDG.setcode("STUDID");
		        	    
		        	 
		                li = client.queryForList("getStudentValue", CMDG);
		                
		                CMDG= (CM_entityInfoGetter)li.get(0);
		                
		                int value=Integer.parseInt(CMDG.getvalue())+1;
		              
		                String value1=null;        
		                        
		                       if(value/1000000000 == 0){
		                            value1 = (""+value);
		                                        
		                                   if(value/100000000 == 0){
		                                      value1 = ("0"+value);
		                                            
		                                         if(value/10000000 == 0){
		                                           value1 = ("00"+value);
		                                           
		                                           if(value/1000000 == 0){
			                                           value1 = ("000"+value);
			                                           
			                                           if(value/100000 == 0){
				                                           value1 = ("0000"+value);
				                                       
				                                           if(value/10000 == 0){
					                                           value1 = ("00000"+value);
					                                       
					                                           if(value/1000 == 0){
						                                           value1 = ("000000"+value);
						                                           
						                                           if(value/100 == 0){
							                                           value1 = ("0000000"+value);
							                                       
							                                           if(value/10 == 0){
								                                           value1 = ("00000000"+value);
							                                     
							                                           
							                                           }
						                                           }
		                                                       
					                                           }
				                                           }
			                                           }
		                                           }
		                                      }
		                              }
		                         }
		                     
		                       
		                   	CM_entityInfoGetter CMDG1 = new  CM_entityInfoGetter();
		                       
		                   	CMDG1.setvalue(value1);
		                    CMDG1.setcode("STUDID");
				        	    
				                newli = client.update("updateStudentValue", CMDG1);
		            
		                return ( CM_entityInfoGetter[]) li.toArray(new  CM_entityInfoGetter[li.size()]);
		          
		        }  catch (Exception ex) {
		        	 logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}

		
		@SuppressWarnings("unchecked")
		public CM_StudentInfoGetter checkDuplicacy(String[] checkData) throws Exception {
			List li,li1;
		        try {
		        	 logObj.logger.info("in check duplicacy");
		        	 CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
		        
		        	 
		                li = client.queryForList("CheckStudentPersonal", CMDG);
		            
		               for(int i=0;i<li.size();i++){
		              
		            	   CMDG= (CM_StudentInfoGetter)li.get(i);
		       
		               		if(	   (CMDG.getfirst_name().compareTo(checkData[1])==0) &&
		            		   (CMDG.getlast_name().compareTo(checkData[2])==0) &&
		            		   (CMDG.getdate_of_birth().compareTo(checkData[3])==0) &&
		            		   (CMDG.getfather_Fname().compareTo(checkData[4])==0) 
		            	
		            		)
		               		{
		                   	   String Stu_id=CMDG.getUser_id();
		                   	   CMDG.setUser_id(Stu_id);
			            	   li1 = client.queryForList("GetRegfromStuId", CMDG);
			            	   CMDG= (CM_StudentInfoGetter)li1.get(0);
			            	   CMDG.setFlag(true);
		                    }
		               
		               		else{
		            	
		               			CMDG.setRegistrationNumber(null);
		               			CMDG.setFlag(false);
		               		}
		               	}
		               return CMDG;
		            
		        }  catch (Exception ex) {
		        	 logObj.logger.error(ex);
		          	throw new Exception(ex);
		        }
		}

		public String StudentFD(String ProgId, String RegNo, String FormNo,
				String FirstDeg,String creatorId) throws Exception {
			
			 @SuppressWarnings("unused")
				Object li;
		
			 	try {
			 		 logObj.logger.info("in StudentFD");
			        	
			       	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                     .substring(0, 19);
			        	CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
			        	
			        	   CMDG.setProgramId(ProgId);  
			        	   CMDG.setForm_number(FormNo);
			    		   CMDG.setRegistrationNumber(RegNo);
			    		   CMDG.setFirstDegreeCode(FirstDeg);
			    		   CMDG.setInsert_time(date);
			    		   CMDG.setCreator_id(creatorId);
			    		 
			                li = client.insert("insertStudentFD", CMDG);
			               
			          
			        } catch (Exception ex) {
			        	 logObj.logger.error(ex);
			        	throw new Exception(ex);
			        }

			        return null;
		
		}

		@SuppressWarnings("unchecked")
		public CM_StudentInfoGetter[] getStudentFD(String RegNo,String progID,String ug_pg) throws Exception {
			
			List li;
			
	        try {
	        	 logObj.logger.info("in get studentfd");
	     
	        	 CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
	        	 
	        	 if(RegNo!=null){
	        	    CMDG.setRegistrationNumber(RegNo);
	        	    CMDG.setProgramId(progID);
	        	    CMDG.setUGorPG(ug_pg);
	        	 
	                li = client.queryForList("GetStudentFDInfo", CMDG);
	                return ( CM_StudentInfoGetter[]) li.toArray(new  CM_StudentInfoGetter[li.size()]);
	        	 }
	        }catch (Exception ex) {
	        	 logObj.logger.error(ex);
	        	throw new Exception(ex);
	        }

	        return null;
		}


		public String UpdateStudentFD(String RegNo,
				String FirstDegree,String type,String modifierid) throws Exception {
			@SuppressWarnings("unused")
			Object li;
			try {
				 logObj.logger.info("in updatestudentfd");
	        	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                 .substring(0, 19);

	        	CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
	        	
	        	   CMDG.setRegistrationNumber(RegNo);
	    		   CMDG.setFirstDegreeCode(FirstDegree);
	    		   CMDG.setUGorPG(type);
	    		   CMDG.setModification_time(date);
	    		   CMDG.setModifier_id(modifierid);
	    		   
	             
	                li = client.update("updateStudentFD", CMDG);
	               
	          
	        } catch (Exception ex) {
	        	 logObj.logger.error(ex);
	        	throw new Exception(ex);
	        }

	        return null;
		}

		@SuppressWarnings("unchecked")
		public String studentReg(String StuId, String RegNo, String FormNo,String Stu_Cos,String entity_id,String creatorId) throws Exception {
			
			 @SuppressWarnings("unused")
				Object li;
			 List li3;

			        try {
			        	 logObj.logger.info("in studentreg");
			        	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
		                 .substring(0, 19);

			        	CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
			        	
			        	if(StuId!=null){
			        
			    		   CMDG.setRegistrationNumber(RegNo);
			    		   CMDG.setUser_id(StuId);
			    		   CMDG.setForm_number(FormNo);
			    		   CMDG.setcos_value(Stu_Cos);
			    		   
			    		   CM_entityInfoGetter CMEG = new  CM_entityInfoGetter();
			    		   
			    		   
			    		   CMEG.setEntity_id(entity_id);
			    		   
			    		   li3=client.queryForList("Getsessiondate",CMEG);
			        	
			        	   CMEG= (CM_entityInfoGetter)li3.get(0);
			        	   
			        	   String Start_date=CMEG.getStartdate();
			        	   String end_date=CMEG.getEnddate();
			        	   
			        	   CMDG.setStartdate(Start_date);
			        	   CMDG.setEnddate(end_date);
			        	   CMDG.setInsert_time(date);
			        	   CMDG.setCreator_id(creatorId);
			             
			             
			                li = client.insert("insertStuReg", CMDG);
			        	}
			          
			        } catch (Exception ex) {
			        	 logObj.logger.error(ex);
			        	throw new Exception(ex);
			        }

			        return null;
		}

		@SuppressWarnings("unchecked")
		public CM_StudentInfoGetter checkProgIdRegNo(String ProgId,String branchId, String RegNo, String ExistingRegNo) throws Exception {

			 List li;
			 int c=0,d=0;
		        try {
		        	 logObj.logger.info("in checkProgIdRegNo");
		        	 CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
		        
		      			CMDG.setRegistrationNumber(ExistingRegNo);
		             
		      			li=client.queryForList("GetStuId1", CMDG);
		              
		      			CMDG= (CM_StudentInfoGetter)li.get(0);
		              
		             String userid=CMDG.getUser_id();
		              
		              CMDG.setUser_id(userid);
		              
		              li=client.queryForList("GetRegfromStuId", CMDG);
		           
		              for(int i=0;i<li.size();i++){
		            	
		              CMDG= (CM_StudentInfoGetter)li.get(i);
		              
		              String reg=CMDG.getRegistrationNumber();
		       
		             CMDG.setRegistrationNumber(reg);
		           
		             List li1 = client.queryForList("checkProgIdRegNo", CMDG);
		      
		     
		            	   CM_StudentInfoGetter CMDG1= (CM_StudentInfoGetter)li1.get(0);
		          
		                   if((CMDG.getBranch()!=null)|| (branchId!=null)){
		         
		            	   if(CMDG1.getProgramId().equals(ProgId)==false){
		            		  d=0;
		         
		            	   }
		            	   else if(CMDG1.getProgramId().equals(ProgId)&& (CMDG1.getBranch().equals(branchId) )){
		            		
		            	   c=c+1;
		            
		               }
		                   }
		                   else{  
		                	   
		                	   if(CMDG1.getProgramId().equals(ProgId)==false){
		                		  
			            		  d=0;
					         
					            	   }
		                	   else
		                		   d++;
		                   }
		              
		              }
		             
		              CM_StudentInfoGetter CMDGfinal = new  CM_StudentInfoGetter();
		        
		               if((c+d)==0){
			        
		            	   CMDGfinal.setUser_id(userid);
		            	   CMDGfinal.setFlag(true);
			                
		               }
		            
		               else
		            	  
		            	   CMDGfinal.setFlag(false);
		      
		          return CMDGfinal;
		        } catch (Exception ex) {
		        	 logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean[] checkRegNo(String RegNo,String FormNo) throws Exception {
			boolean[] flag = new boolean[2];
			
			flag[0]=false;
			flag[1]=false;
			
			List li;
	
		        try {

		        	 logObj.logger.info("in checkRegNo");
		        	 
		        	 CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
		        	
		                li = client.queryForList("checkRegNo", CMDG);
		  
		               for(int i=0;i<li.size();i++){
		              
		               CMDG= (CM_StudentInfoGetter)li.get(i);
		       
		               if(CMDG.getRegistrationNumber().equals(RegNo)){
		               
		            	   flag[0]=true;
		            	   
		               }
		        
		            	 /*  if(CMDG.getForm_number().equals(FormNo)){
				               
		            	    
			            	   flag[1]=true;
		            	   }*/
			                }
		   
		            	return flag;
		          
		        }  catch (Exception ex) {
		        	 logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}

	
		@SuppressWarnings("unchecked")
		public int checkReg(String RegNo) throws Exception {
			List li;
			
	        try {
	        	 logObj.logger.info("in checkReg");
	        	 int reg=0;
	        	 CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
	        	
	                li = client.queryForList("RegNoValid", CMDG);
	            
	               for(int i=0;i<li.size();i++){
	            
	            	   
	               CMDG= (CM_StudentInfoGetter)li.get(i);
	          
	               if(CMDG.getRegistrationNumber().equals(RegNo)){
	           
	            	   reg++;
	            	   
	               }
	               }
	       
	               return reg;
	               
	          
	        }  catch (Exception ex) {
	        	 logObj.logger.error(ex);
	        	throw new Exception(ex);
	        }
		
		}
		
		@SuppressWarnings("unchecked")
		public CM_ProgramInfoGetter[] getcos_value(String progId,String branch, String Entity_Id,String catCode) throws Exception {
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	 logObj.logger.info("in getcos_value");
		        	 CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();
		        	 
		        	    CMDG.setprogram_id(progId);
		        	    CMDG.setBranch(branch);
		        	    CMDG.setentity_id(Entity_Id);
		        	    CMDG.setcos_value(catCode);
		        	   
		                li = client.queryForList("GetCOS_value", CMDG);
		             
		                
		                return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
		          
		        }  catch (Exception ex) {
		        	 logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		}

		@SuppressWarnings("unchecked")
		public CM_StudentInfoGetter[] Category() {
			List li;
		
	        try {
	        	 logObj.logger.info("in Category");
	     
	        	 CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
	        	 
	                li = client.queryForList("Category", CMDG);
	                return ( CM_StudentInfoGetter[]) li.toArray(new  CM_StudentInfoGetter[li.size()]);
	          
	        } catch (Exception ex) {
	        	 logObj.logger.error(ex);
	            System.out.println(ex);
	        }

	        return null;
		}
		
		
		@SuppressWarnings("unchecked")
		public CM_StudentInfoGetter[] getEnrolledStuCategory(String category_code) {
			List li;
		
	        try {
	        	 logObj.logger.info("in getEnrolledStuCategory");
	     
	        	 CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
	        	 CMDG.setCategory_code(category_code);
	        	 
	                li = client.queryForList("getEnrolledStuCategory", CMDG);
	                return ( CM_StudentInfoGetter[]) li.toArray(new  CM_StudentInfoGetter[li.size()]);
	          
	        } catch (Exception ex) {
	        	 logObj.logger.error(ex);
	            System.out.println(ex);
	        }

	        return null;
		}

		
		@SuppressWarnings("unchecked")
		@Override
		public CM_ProgramInfoGetter[] getComponentId(String ComponentName) throws Exception {
			 List li;

		        //Begin The Try-Catch Loop		
		        try {
		        	 logObj.logger.info("in getComponentId");
		        	
		        	 CM_ProgramInfoGetter CMDG = new  CM_ProgramInfoGetter();
		        	 
		        	    CMDG.setComponent(ComponentName);
		        	
		                li = client.queryForList("GetComponent_id", CMDG);
		                
		                return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
		          
		        } catch (Exception ex) {
		        	 logObj.logger.error(ex);
		        	 throw new Exception(ex);
		        }
		}

		@SuppressWarnings("unchecked")
		public CM_StudentInfoGetter[] getBoard() throws Exception {
		
			List li;
		
	        try {
	        	 logObj.logger.info("in getBoard");
	        	 CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
	        	 
	        	 
	                li = client.queryForList("BoardNames", CMDG);
	                return ( CM_StudentInfoGetter[]) li.toArray(new  CM_StudentInfoGetter[li.size()]);
	          
	        } catch (Exception ex) {
	        	 logObj.logger.error(ex);
	        	 throw new Exception(ex);
	        }
		}

		@SuppressWarnings("unchecked")
		public int programEligibility(String[] details)  throws Exception {
			 @SuppressWarnings("unused")
				Object li;
			 	List si;
			 	int check=0;
			 

			        //Begin The Try-Catch Loop		
			        try {
			        	 logObj.logger.info("in programEligibility");
			       	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                     .substring(0, 19);
			        	CM_ProgramInfoGetter CMDG = new  CM_ProgramInfoGetter();
			        
			        	   si = client.queryForList("checkProgAgeElig", CMDG);
			        	   
			        	   for(int i=0;i<si.size();i++){
					              
				               CMDG= (CM_ProgramInfoGetter)si.get(i);
				        
				               if(
				                (CMDG.getentity_id().equalsIgnoreCase(details[0])) &&
				            	(CMDG.getprogram_id().equalsIgnoreCase(details[1])) &&
				            	(CMDG.getBranch_code().equalsIgnoreCase(details[2])) &&
				            	(CMDG.getcategory().equalsIgnoreCase(details[3]))
				            	)
				               {
				               check++;
				            	}
				               
				               }
			        	   
			               if(check==0){
			               
			        	CMDG.setentity_id(details[0]);
			        	CMDG.setprogram_id(details[1]);
			        	CMDG.setBranch_code(details[2]);
			        	CMDG.setcategory(details[3]);
			        	CMDG.setage_limit(details[4]);
			        	CMDG.setInsert_time(date);
			        	CMDG.setCreator_id(details[6]);
			        	
			            li = client.insert("progEligibilityInfo", CMDG);
			               }
			          
			               return check;
			        }  catch (Exception ex) {
			        	 logObj.logger.error(ex);
			        	throw new Exception(ex);
			        }
		}

		@SuppressWarnings("unchecked")
		@Override
		public CM_ProgramInfoGetter[] getprogAgeElig(String entity_id) throws Exception {

			List li;
			String program = null;
			String branchname = null;
			String category = null;
			String agelimit = null;
			String progId = null;
            String branchid = null;
			CM_ProgramInfoGetter[] CMPGF = null ;
		
	        try {
	        	 logObj.logger.info("in getprogAgeElig");
	        	CM_ProgramInfoGetter CMPG = new  CM_ProgramInfoGetter();
	        	 
	        	 
	                li = client.queryForList("checkProgAgeElig", CMPG);
	                CMPGF = new  CM_ProgramInfoGetter[li.size()];
	          
	                
	                for(int i=0;i<li.size();i++){
			           
	                	CMPG= (CM_ProgramInfoGetter)li.get(i);
			                progId=(CMPG.getprogram_id());
			                branchid=(CMPG.getBranch_code());
			               
			              category=(CMPG.getcategory());
			              agelimit=(CMPG.getage_limit());
			           
			        		CM_ProgramInfoGetter CMPG1 = new  CM_ProgramInfoGetter();
			        		CMPG1.setProgram_id(progId);
				        	   
			        		
			             List   pi = client.queryForList("GetprogramName", CMPG1);
			             CMPG1= (CM_ProgramInfoGetter)pi.get(0);
			         
			               program=CMPG1.getProgram_name();

			           	CM_ProgramInfoGetter CMPG2 = new  CM_ProgramInfoGetter();
			               
			          
			               if(branchid!=null){
			        	  
			        	    CMPG2.setBranch(branchid);
			        	  
				             List   bi = client.queryForList("Getbranch_name", CMPG2);
				             CMPG2= (CM_ProgramInfoGetter)bi.get(0);
				         
				              branchname=CMPG2.getBranch_name();
			               }
			               else
			            	  
			            	   branchname=null;
			             
			         
			               		CMPGF[i]= new CM_ProgramInfoGetter();
			            	  
			               		CMPGF[i].setProgram_name(program);
			            	
			            	    CMPGF[i].setBranch_name(branchname);
			            	  
			            	    CMPGF[i].setcategory(category);
			            	  
			            	    CMPGF[i].setage_limit(agelimit);
			            	  
		                
	                }
	     
	              
		               return CMPGF;
	             
	          
	        }  catch (Exception ex) {
	        	 logObj.logger.error(ex);
	        	throw new Exception(ex);
	        }
		}

		
		
		@SuppressWarnings("unchecked")
		public String editProgAgeLimit(String[] ProgAgeDetails, String modifierID) throws Exception {
			
				 String branchId;
					@SuppressWarnings("unused")
					Object li;  

			        //Begin The Try-Catch Loop
			        try {
			        	 logObj.logger.info("in editProgAgeElibility");
			       	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                     .substring(0, 19);

			        	CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

		        		CM_ProgramInfoGetter CMPG1 = new  CM_ProgramInfoGetter();
		        		
		        		CMPG1.setprogram_name(ProgAgeDetails[1]);
			        	   
		        		List   pi = client.queryForList("Getprogram_id", CMPG1);
		        		CMPG1= (CM_ProgramInfoGetter)pi.get(0);
		         
		        		String programid=CMPG1.getprogram_id();
		    
		        		
		        		if(ProgAgeDetails[2]!=null){
		        		
		        		CM_ProgramInfoGetter CMPG2 = new  CM_ProgramInfoGetter();
		        		
		        		CMPG2.setBranchName(ProgAgeDetails[2]);
			        	   
		        		List   pi2 = client.queryForList("GetBranchID", CMPG2);
		        		CMPG2= (CM_ProgramInfoGetter)pi2.get(0);
		         
		        		 branchId=CMPG2.getBranch();
		        		
		        		}
		        		else
		        			branchId=null;
		        	
			        	CMDG.setentity_id(ProgAgeDetails[0]);
			        	CMDG.setprogram_id(programid);
			        	CMDG.setBranch_code(branchId);
			        	CMDG.setcategory(ProgAgeDetails[3]);
			        	CMDG.setage_limit(ProgAgeDetails[4]);
			        
			        	CMDG.setModification_time(date);
			    		CMDG.setModifier_id(modifierID);
			        
			        	li = client.update("editProgAgeLimit",CMDG);
			         
			        }  catch (Exception ex) {
			        	 logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }
		        return null;
		}

		@SuppressWarnings("unchecked")
		public String deleteProgAge(String[] ProgAgeDetails) throws Exception {
			
				 String branchId;
					@SuppressWarnings("unused")
					Object li;  

			        //Begin The Try-Catch Loop
			        try {
			        	 logObj.logger.info("in deleteProgAge");
			        	CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

		        		CM_ProgramInfoGetter CMPG1 = new  CM_ProgramInfoGetter();
		        		
		        		CMPG1.setprogram_name(ProgAgeDetails[1]);
			        	   
		        		List   pi = client.queryForList("Getprogram_id", CMPG1);
		        		CMPG1= (CM_ProgramInfoGetter)pi.get(0);
		         
		        		String programid=CMPG1.getprogram_id();
		        		
		        		
		        		if(ProgAgeDetails[2]!=null){
		        		
		        		CM_ProgramInfoGetter CMPG2 = new  CM_ProgramInfoGetter();
		        		
		        		CMPG2.setBranchName(ProgAgeDetails[2]);
			        	   
		        		List   pi2 = client.queryForList("GetBranchID", CMPG2);
		        		CMPG2= (CM_ProgramInfoGetter)pi2.get(0);
		         
		        		 branchId=CMPG2.getBranch();
		        		
		        		}
		        		else
		        			branchId=null;

			        	CMDG.setentity_id(ProgAgeDetails[0]);
			        	CMDG.setprogram_id(programid);
			        	CMDG.setBranch_code(branchId);
			        	CMDG.setcategory(ProgAgeDetails[3]);
			        	CMDG.setage_limit(ProgAgeDetails[4]);
			           
			      
			            li = client.delete("deleteProgAgeLimit",CMDG);
			         
			        }  catch (Exception ex) {
			        	 logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }

		        return null;
		}


		@SuppressWarnings("unchecked")
		public int componentEligibility(String[] compdetails) throws Exception {
			 @SuppressWarnings("unused")
				Object li;
			 	List si, pi;
			 	int check=0;
			 	String component_id;
			 
			        //Begin The Try-Catch Loop		
			        try {
			        	 logObj.logger.info("in componentEligibility");
			        	String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
	                     .substring(0, 19);
			        	CM_ProgramInfoGetter CMDG = new  CM_ProgramInfoGetter();
			        	
			        	CMDG.setComponent(compdetails[3]);
			        	
			        	pi=client.queryForList("GetComponent_id", CMDG);
			        	
			        	  CMDG= (CM_ProgramInfoGetter)pi.get(0);
			        	  component_id=CMDG.getComponent_id();
			        	  
			        	   si = client.queryForList("checkProgCompElig", CMDG);
			        	   
			        	   
			        	   
			        	   for(int i=0;i<si.size();i++){
					              
				               CMDG= (CM_ProgramInfoGetter)si.get(i);
				               
				               if(
				                (CMDG.getentity_id().equalsIgnoreCase(compdetails[0])) &&
				            	(CMDG.getprogram_id().equalsIgnoreCase(compdetails[1])) &&
				            	(CMDG.getBranch_code().equalsIgnoreCase(compdetails[2])) &&
				            	(CMDG.getComponent_id().equalsIgnoreCase(component_id)) &&
				            	(CMDG.getcategory().equalsIgnoreCase(compdetails[4]))
				            	)
				               {
				               check++;
				            	}
				               
				               }
			        
			               if(check==0){
			            	   
			            	   
			               
			        	CMDG.setentity_id(compdetails[0]);
			        	CMDG.setprogram_id(compdetails[1]);
			        	CMDG.setBranch_code(compdetails[2]);
			        	CMDG.setComponent_id(component_id);
			        	CMDG.setcategory(compdetails[4]);
			        	CMDG.setCut_off_percentage(Float.parseFloat(compdetails[5]));
			        	CMDG.setInsert_time(date);
			        	CMDG.setCreator_id(compdetails[7]);
			        	
			            li = client.insert("CompAgeEligibility", CMDG);
			               }
			               return check;
			        }
			        catch (Exception e) {
			        	 logObj.logger.error(e);
			    		throw new Exception(e);
			    	}
			       
		}

		@SuppressWarnings("unchecked")
		public CM_ProgramInfoGetter[] getprogCompElig(String entity_id) throws Exception {
			
			List li;
			String program = null;
			String branchname = null;
			String category = null;
			Float cut_off;
			String progId=null;
			String component;
            String branchid=null;
			CM_ProgramInfoGetter[] CMPGF = null ;
		
	        try {
	        	 logObj.logger.info("in getprogCompElig");
	        	CM_ProgramInfoGetter CMPG = new  CM_ProgramInfoGetter();
	        	 
	        	 CMPG.setEntity_id(entity_id);
	                li = client.queryForList("getProgCompElig", CMPG);
	                CMPGF = new  CM_ProgramInfoGetter[li.size()];
	          
	                
	                for(int i=0;i<li.size();i++){
			           
	                	CMPG= (CM_ProgramInfoGetter)li.get(i);
			               progId=(CMPG.getprogram_id());
			                branchid=(CMPG.getBranch_code());
			               
			              category=(CMPG.getcategory());
			              cut_off=(CMPG.getCut_off_percentage());
			          
			              String ComponentID=CMPG.getComponent_id();
			              
			              
			              
			              CM_ProgramInfoGetter CMPGG = new  CM_ProgramInfoGetter();
			        		CMPGG.setComponent_id(ComponentID);
				        	   
			        		
			             List   pi1 = client.queryForList("GetComponent_name", CMPGG);
			             CMPGG= (CM_ProgramInfoGetter)pi1.get(0);
			         
			             component=CMPGG.getComponent();
			              
			             
			            CM_ProgramInfoGetter CMPG1 = new  CM_ProgramInfoGetter();
			        		
			            CMPG1.setProgram_id(progId);
				        	   
			        		
			             List   pi = client.queryForList("GetprogramName", CMPG1);
			             CMPG1= (CM_ProgramInfoGetter)pi.get(0);
			         
			               program=CMPG1.getProgram_name();

			           	   CM_ProgramInfoGetter CMPG2 = new  CM_ProgramInfoGetter();
			               
			          
			               if(branchid!=null){
			        	  
			        	    CMPG2.setBranch(branchid);
			        	  
				             List   bi = client.queryForList("Getbranch_name", CMPG2);
				             CMPG2= (CM_ProgramInfoGetter)bi.get(0);
				         
				              branchname=CMPG2.getBranch_name();
			               }
			               else
			            	   branchname=null;
			         
			               	   CMPGF[i]= new CM_ProgramInfoGetter();
			            	   CMPGF[i].setProgram_name(program);
			            	
			            	   CMPGF[i].setBranch_name(branchname);
			            	  
			            	   CMPGF[i].setcategory(category);
			            	  
			            	   CMPGF[i].setComponent_id(component);
			            	   
			            	   CMPGF[i].setCut_off_percentage(cut_off);
			            	  
		                
	                }
	     
	              
		               return CMPGF;
	             
	          
	        } catch (Exception ex) {
	        	 logObj.logger.error(ex);
	        	throw new Exception(ex);
	        }
		}

		@SuppressWarnings("unchecked")
		@Override
		public String editProgCompEligibility(String[] ProgAgeDetails,String modifierID) throws Exception {
			
				 String branchId;
					@SuppressWarnings("unused")
					Object li;  
					String componentId = null;
		          
			        //Begin The Try-Catch Loop
			        try {
			        	 logObj.logger.info("in editProgCompEligibility");
			          
			        	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
	                     .substring(0, 19);
			        	 
			        	CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

		        		CM_ProgramInfoGetter CMPG1 = new  CM_ProgramInfoGetter();
		        		
		        		CMPG1.setprogram_name(ProgAgeDetails[1]);
		        		
			        	   
		        		List   pi = client.queryForList("Getprogram_id", CMPG1);
		        		CMPG1= (CM_ProgramInfoGetter)pi.get(0);
		         
		        		String programid=CMPG1.getprogram_id();
		        

		        		CM_ProgramInfoGetter CMPG2 = new  CM_ProgramInfoGetter();
		        		
		        		CMPG2.setBranchName(ProgAgeDetails[2]);
			        	   
		        		List   pi2 = client.queryForList("GetBranchID", CMPG2);
		        		CMPG2= (CM_ProgramInfoGetter)pi2.get(0);
		         
		        		 branchId=CMPG2.getBranch();
		        		
		        		 CM_ProgramInfoGetter CMPGG = new  CM_ProgramInfoGetter();
			        	 CMPGG.setComponent(ProgAgeDetails[4]);
				        	   
			        		
			             List   pi1 = client.queryForList("GetComponent_id", CMPGG);
			             CMPGG= (CM_ProgramInfoGetter)pi1.get(0);
			         
			             componentId=CMPGG.getComponent_id();
			       
		        		
		        		CMDG.setentity_id(ProgAgeDetails[0]);
			        	CMDG.setprogram_id(programid);
			        	CMDG.setBranch_code(branchId);
			        	CMDG.setComponent_id(componentId);
			        	CMDG.setcategory(ProgAgeDetails[3]);
			        	CMDG.setCut_off_percentage(Float.parseFloat(ProgAgeDetails[5]));
			        	
			        	CMDG.setModification_time(date);
			    		CMDG.setModifier_id(modifierID);
			            li = client.update("editProgCompElig",CMDG);
			         
			        

		        } catch (Exception ex) {
		        	 logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }

		        return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public String deleteProgCompElig(String[] delProgComp) throws Exception {
			
				 String branchId;
					@SuppressWarnings("unused")
					Object li;  
					String componentId = null;
		          
			        //Begin The Try-Catch Loop
			        try {
			        	 logObj.logger.info("deleteProgCompElig");
			        	
			        	CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

		        		CM_ProgramInfoGetter CMPG1 = new  CM_ProgramInfoGetter();
		        		
		        		CMPG1.setprogram_name(delProgComp[1]);
		        		
			        	   
		        		List   pi = client.queryForList("Getprogram_id", CMPG1);
		        		CMPG1= (CM_ProgramInfoGetter)pi.get(0);
		         
		        		String programid=CMPG1.getprogram_id();
		        

		        		CM_ProgramInfoGetter CMPG2 = new  CM_ProgramInfoGetter();
		        		
		        		CMPG2.setBranchName(delProgComp[2]);
			        	   
		        		List   pi2 = client.queryForList("GetBranchID", CMPG2);
		        		CMPG2= (CM_ProgramInfoGetter)pi2.get(0);
		         
		        		branchId=CMPG2.getBranch();
		        		
			            CM_ProgramInfoGetter CMPGG = new  CM_ProgramInfoGetter();
			        	CMPGG.setComponent(delProgComp[4]);
				        	   
			        		
			             List   pi1 = client.queryForList("GetComponent_id", CMPGG);
			             CMPGG= (CM_ProgramInfoGetter)pi1.get(0);
			         
			             componentId=CMPGG.getComponent_id();
			    
		        		CMDG.setentity_id(delProgComp[0]);
			        	CMDG.setprogram_id(programid);
			        	CMDG.setBranch_code(branchId);
			        	CMDG.setComponent_id(componentId);
			        	CMDG.setcategory(delProgComp[3]);
			        	CMDG.setCut_off_percentage(Float.parseFloat(delProgComp[5]));
		        	
			            li = client.delete("deleteProgCompElig",CMDG);
			         
			       
		        } catch (Exception ex) {
		        	 logObj.logger.error(ex);
		        	throw new Exception(ex);
		        }

		        return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public CM_ProgramInfoGetter getStudentProgBranch(String RegNo) throws Exception {
			String program = null;
			String branchID=null;
			String flag=null;
			String entityID=null;
			String programID=null;
			String entity_name= null;
			
			  CM_ProgramInfoGetter CMDGB= new  CM_ProgramInfoGetter();
		        try {
		        	 logObj.logger.info("getStudentProgBranch");
		        
		        	 CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
		        	 
		      			CMDG.setRegistrationNumber(RegNo);
		          
		      		
		      			
		             List li1 = client.queryForList("checkProgIdRegNo", CMDG);
		             
		             if(li1.size()>0){
		             
		            	 flag="true";
		           
		            	   CM_StudentInfoGetter CMDG1= (CM_StudentInfoGetter)li1.get(0);
		            	   
		            	   programID=CMDG1.getProgramId();
		            	   branchID=CMDG1.getBranch();
		            	   entityID=CMDG1.getentity_id();
		            	   
		            	   CM_entityInfoGetter CMEI = new  CM_entityInfoGetter();
				        	 
		            	   CMEI.setEntity_id(entityID);
			          
			             List liEntity = client.queryForList("getEntityNamefromID", CMEI);
			             
			             CMEI= (CM_entityInfoGetter)liEntity.get(0);
		            	   
			              entity_name=CMEI.getEntity_name();
			             
		            	  
		            	   CM_ProgramInfoGetter CMDGProg = new  CM_ProgramInfoGetter();
				        	 
		            	   
		            	   if(programID!=null){
			        	   	
		            	 CMDGProg.setprogram_id(programID);
			          
			             List liProg = client.queryForList("GetprogramName", CMDGProg);
			             
			             CMDGProg= (CM_ProgramInfoGetter)liProg.get(0);
		            	   
			             program=CMDGProg.getprogram_name();
			             
		                   
		            	   }
		            	
				        
		            	   
		            	   if(branchID.equals("")){
		            		 	CMDGB.setBranch(null);
		            		 	CMDGB.setBranch_name(null);
		            		
		            	   }
		            	   else {
		            
			        	   	CMDGB.setBranch(branchID);
			          
			             List liB = client.queryForList("Getbranch_name", CMDGB);
			             CMDGB= (CM_ProgramInfoGetter)liB.get(0);
		                 
		                   
		            	   }
		            	   
		        }
		             
		             else
		            	 flag="false";
		         
		                 CMDGB.setBranch_code(branchID);
		                 CMDGB.setprogram_id(programID);
		                 CMDGB.setprogram_name(program);
		                 CMDGB.setentity_name(entity_name);
		                 CMDGB.setentity_id(entityID);
		         
		             CMDGB.setFlag(flag);
		          
		          return CMDGB;
		        }catch (Exception ex) {
		        	 logObj.logger.error(ex);
		         	throw new Exception(ex);
		        }
		}

	
		@SuppressWarnings("unchecked")
		@Override
		public String deleteStudentFD(String RegNo, String program_name) {
		
				@SuppressWarnings("unused")
				Object li;  

		        try {
		        	 logObj.logger.info("in deleteStudentFD");
           	 
		        	 CM_ProgramInfoGetter CMDGProg = new  CM_ProgramInfoGetter();
		        	 
	        	   	CMDGProg.setprogram_name(program_name);
	          
	             List liProg = client.queryForList("Getprogram_id", CMDGProg);
	             
	             CMDGProg= (CM_ProgramInfoGetter)liProg.get(0);
	             
	             String progId= CMDGProg.getprogram_id();
	           
		          
		        	 CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();
		           
		        	 CMDG.setRegistrationNumber(RegNo);
		        	 CMDG.setProgramId(progId);
		           
		            li = client.delete("deleteStudentFD",CMDG);
		         
		        } catch (Exception ex) {
		       	 logObj.logger.error(ex);
		            System.out.println(ex);
		        }

		      
		        return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean CheckComponents(String EntityID, String ProgramID,
				String BranchID) throws Exception {
			boolean flag=true;
			int count=0;

			@SuppressWarnings("unused")
			Object li;  

	        try {
	          
	       	 logObj.logger.info("CheckComponents");
    		  CM_ProgramInfoGetter CMP= new  CM_ProgramInfoGetter();
	        	 
    		  List liPrg= client.queryForList("checkComponents", CMP);
    		
    		  for(int i=0;i<liPrg.size();i++){
    			  CMP= (CM_ProgramInfoGetter)liPrg.get(i);
    	         
    			 if(CMP.getprogram_id().equals(ProgramID) && CMP.getentity_id().equals(EntityID) && CMP.getBranch_code().equals(BranchID))
    			 count++;
    		  }
    		  
    		  
    		  
    		  if(count==0)
    			  flag=false;
    		  else
    			  flag=true;
    		
    		  return flag;
    		
	        }  catch (Exception ex) {
	        	 logObj.logger.error(ex);
	        	throw new Exception(ex);
	        }
		}

		
		@Override
		public String UpdatecallListInfo(String RegNo, String[] data,
				String modifierid) throws Exception {
			 @SuppressWarnings("unused")
				Object li;
			  try {
				  logObj.logger.info("in UpdatecallListInfo");
			        	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
	                       .substring(0, 19);

			        	CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
			    
								for(int j=1;j<5;j++){
					
								if(Float.parseFloat(data[j])>0==false)
									data[j]=null;
							}
			        	   
			        	
			    		   CMDG.setRegistrationNumber(RegNo);
			    		   CMDG.setComponentID(data[0]);
			    		   CMDG.setMarksPercentage(data[1]);
			    		   CMDG.setMarksObtained(data[2]);
			    		   CMDG.setTotalMarks(data[3]);
			    		   CMDG.setScore(data[4]);
			    		   CMDG.setboard_id(data[5]);
			    	
			        	   CMDG.setModification_time(date);
			        	   CMDG.setModifier_id(modifierid);
			             
			                li = client.update("updateStuCallList", CMDG);
			               
			          
			        }  catch (Exception ex) {
			       	 logObj.logger.error(ex);
			        	throw new Exception(ex);
			        }

			        return null;
		}

		@Override
		public String UpdateStudentReg(String RegNo, String Stu_Cos,String modifierId) throws Exception {
			 @SuppressWarnings("unused")
				Object li;
				
			        try {
			       	 logObj.logger.info("in UpdateStudentReg");
			        	 String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
	                       .substring(0, 19);

			        	CM_StudentInfoGetter CMDG = new  CM_StudentInfoGetter();
			        	
			        	
			    		   CMDG.setRegistrationNumber(RegNo);
			    		   CMDG.setcos_value(Stu_Cos);
			        	   CMDG.setModification_time(date);
			        	   CMDG.setModifier_id(modifierId);
			             
			                li = client.update("updateStuReg", CMDG);
			               
			          
			        }  catch (Exception ex) {
			         	 logObj.logger.error(ex);
			        	throw new Exception(ex);
			        }

			        return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public String[] getProgRegNumber(String univID, String entity_id,
				String ProgId, String branchID) throws Exception {
			
			 List li;
			 @SuppressWarnings("unused")
			Object newli;
		     String value1=null;   
		     String year = (new java.sql.Timestamp(new java.util.Date().getTime())).toString().substring(2, 4);
	         
		        //Begin The Try-Catch Loop		
		        try {
		        	 logObj.logger.info("in GetProgRegValue");
		        	CM_ProgramInfoGetter CMDG = new  CM_ProgramInfoGetter();
		        	 
		        	
		        	    CMDG.setUniversity_id(univID.substring(1, 5));
		        	    CMDG.setentity_id(entity_id);
		        	    CMDG.setprogram_id(ProgId);
		        	    CMDG.setBranch_code(branchID);
		        	   
		        	    li = client.queryForList("GetProgRegValue", CMDG);
		                if(li.size()==0){
		                	setIntialRegNumber(univID, entity_id, ProgId, branchID);
		                }
		                
		                li = client.queryForList("GetProgRegValue", CMDG);
		                if(li.size()==0){
		                	value1=null;
		                }
		                else{
		                CMDG= (CM_ProgramInfoGetter)li.get(0);
		                
		                int value=Integer.parseInt(CMDG.getValue())+1;
		              
		                
		                                     
		                if(value/10000 == 0){
		                	value1 = (""+value);
					
		                	if(value/1000 == 0){
		                		value1 = ("0"+value);
					
		                		if(value/100 == 0){
		                			value1 = ("00"+value);
							                                       
		                			if(value/10 == 0){
		                				value1 = ("000"+value);
							                                              
		                			}
						                      
		                		}
		                        
		                	}
				            
		                }
			               
		                
//		                CM_ProgramInfoGetter CMDG1 = new  CM_ProgramInfoGetter();
//		                       
//		                   	CMDG1.setValue(value1);
//		                    CMDG1.setUniversity_id(univID.substring(1, 5));
//			        	    CMDG1.setentity_id(entity_id);
//			        	    CMDG1.setprogram_id(ProgId);
//			        	    CMDG1.setBranch_code(branchID);
//				        	    
//				            newli = client.update("updateProgRegValue", CMDG1);
		                }
		                String[] result={value1,year};
		                return (result);
		          
		        }  catch (Exception ex) {
		        	 logObj.logger.error(ex);
		       	   	throw new Exception(ex);
		        }
		}

		@Override
		public String updateProgRegNumber(String univID, String entity_id,
				String ProgId, String branchID, String value) throws Exception {
			@SuppressWarnings("unused")
			Object li;
			
	        try {
	       	 logObj.logger.info("in updateProgRegValue");

	        	 CM_ProgramInfoGetter CMDG1 = new  CM_ProgramInfoGetter();
                 
                	CMDG1.setValue(value);
                    CMDG1.setUniversity_id(univID.substring(1, 5));
	        	    CMDG1.setentity_id(entity_id);
	        	    CMDG1.setprogram_id(ProgId);
	        	    CMDG1.setBranch_code(branchID);
		        	    
		            li = client.update("updateProgRegValue", CMDG1);
	               
	          
	        }  catch (Exception ex) {
	         	 logObj.logger.error(ex);
	        	throw new Exception(ex);
	        }

	        return null;
		}
		
		public void setIntialRegNumber(String univID, String entity_id,
				String ProgId, String branchID) throws Exception{
			
	        try {
	       	 logObj.logger.info("in setIntialRegNumber");
	       	String initialValue="0000";
	        	 CM_ProgramInfoGetter CMDG1 = new  CM_ProgramInfoGetter();
	        	    CMDG1.setValue(initialValue);
	        	    CMDG1.setUniversity_id(univID.substring(1, 5));
	        	    CMDG1.setentity_id(entity_id);
	        	    CMDG1.setprogram_id(ProgId);
	        	    CMDG1.setBranch_code(branchID);
	        	    
		        	    
		             client.insert("insertIntialProgRegValue", CMDG1);
	          
	        }  catch (Exception ex) {
	         	 logObj.logger.error(ex);
	        	throw new Exception(ex);
	        }

			}

		@Override
		public CM_entityInfoGetter[] defaultEntityName(String univID, String entity_type) throws Exception {
			List li;
            //Begin The Try-Catch Loop		
	        try {
	        	
	        	  logObj.logger.info("in defaultEntityName");
	        	CM_entityInfoGetter CSIG = new CM_entityInfoGetter();
	        	CSIG.setUniversity_id(univID.substring(1,5));
	        	CSIG.setEntity_type(entity_type);
	        	
	     
	                li = client.queryForList("getDefaultEntityName", CSIG);
	            return (CM_entityInfoGetter[]) li.toArray(new  CM_entityInfoGetter[li.size()]);
	          
	        }  catch (Exception ex) {
	        	  logObj.logger.error(ex);
	        	throw new Exception(ex);
	        }
			
		}

		@Override
		public String checkForDefaultView() throws Exception {
			List li;
			String univId="0001";
            String code="DEFENT";
	        //Begin The Try-Catch Loop		
	        try {
	        	
	        	  logObj.logger.info("in checkForDefaultView");
	        	CM_entityInfoGetter CSIG = new CM_entityInfoGetter();
	        	CSIG.setUniversity_id(univId);
	        	CSIG.setcode(code);
	        	
	            li = client.queryForList("checkForDefaultEntity", CSIG);
	            CSIG= (CM_entityInfoGetter)li.get(0);
	            String check=CSIG.getvalue().substring(0, 1);
	            return check;
	            
	        }  catch (Exception ex) {
	        	  logObj.logger.error(ex);
	        	throw new Exception(ex);
	        }
			
		}

		@Override
		public CM_entityInfoGetter[] defaultEntityType(String univID)
				throws Exception {
			List li;
			try{
			  logObj.logger.info("in defaultEntityType");
		         
		     	CM_entityInfoGetter CSIG = new CM_entityInfoGetter();
		     	CSIG.setUniversity_id(univID.substring(1,5));	     
	            li = client.queryForList("getDefaultEntityDesc", CSIG);
		        
	            return (CM_entityInfoGetter[]) li.toArray(new  CM_entityInfoGetter[li.size()]);
	         }  catch (Exception ex) {
		    	  logObj.logger.error(ex);
		    	 throw new Exception(ex);
		     }

	}

		   		
	}
