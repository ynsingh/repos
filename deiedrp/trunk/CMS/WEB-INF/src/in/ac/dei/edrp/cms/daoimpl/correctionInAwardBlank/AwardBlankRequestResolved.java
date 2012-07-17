package in.ac.dei.edrp.cms.daoimpl.correctionInAwardBlank;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import java.util.StringTokenizer;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramMasterInfoGetter;

import in.ac.dei.edrp.cms.domain.studentmarkssummary.StudentMarksSummaryBean;
public class AwardBlankRequestResolved extends SqlMapClientDaoSupport implements in.ac.dei.edrp.cms.dao.correctionInAwardBlank.AwardBlankRequestResolvedDao
	{
	private static Logger logObj = Logger.getLogger(AwardBlankRequestResolved.class);

	public List<StudentMarksSummaryBean> getStudentRequestData(
			StudentMarksSummaryBean input) {
		List<StudentMarksSummaryBean> dataList = new ArrayList<StudentMarksSummaryBean>();
		try{
			dataList = getSqlMapClientTemplate().queryForList("awardBlankRequestResolved.getDataOfCorrectionRequest", input);			
		}
		catch (Exception e){
			logObj.error(e.getMessage());	
		}
		return dataList;
	}

	public String solveRequestedIssue(StudentMarksSummaryBean input) {
		try{
			int i=getSqlMapClientTemplate().update("awardBlankRequestResolved.solveIssue", input);			
			if(i==1){
				return "true";
			}
			else{
				return "No record Updated";
			}
			
		}
		catch (Exception e){
			logObj.error(e.getMessage());	
			return "false"+e;
		}
	}
	
		/**
     * Method for getting course list 
     * @param inputObj
    * @return List of courses
     */
    @SuppressWarnings("unchecked")
    public List<AwardSheetInfoGetter> getCourseList(AwardSheetInfoGetter inputObj) {
    	List<AwardSheetInfoGetter> courseList=null;
    	try{
    		logObj.info("getCourseList");
    	

    			courseList = getSqlMapClientTemplate().queryForList("AwardSheetForCollation.getCourseListForRemedialAndExternal", inputObj);
    		
    	}
    	catch (Exception e) {
			logObj.error("getCourseList");
		}
        return courseList;
   }

    
    
  
public String saveStudentMarks(AwardSheetInfoGetter input,StringTokenizer data) {
String total = "0";
	String grade = "";

	try{
		while(data.hasMoreTokens()){
			StringTokenizer rowData = new StringTokenizer(data.nextToken(),"|");
			input.setRollNumber(rowData.nextToken());
            input.setEvaluationId(rowData.nextToken());

            String idType = rowData.nextToken();
            String updatedMarks = rowData.nextToken();
            if(isInteger(updatedMarks)){
            	input.setAttendence("P");
            }else{
            	updatedMarks = "0" ;
            	input.setAttendence("ABS");
            }

            if(rowData.hasMoreTokens()){
              	total=rowData.nextToken();
            }

            if(rowData.hasMoreTokens()){
            	grade=rowData.nextToken();
            }

           
            if(idType.equalsIgnoreCase("MK")){
            			
            		input.setMarks(updatedMarks);
            		if(input.getDisplayType().equalsIgnoreCase("R")){
            			input.setGrades(grade);
            		}else{
            			input.setGrades(null);
            		}
            		int updateCountStudentMarks = getSqlMapClientTemplate().update("AwardSheetForCollation.updateStudentMarks", input);
            		if(updateCountStudentMarks < 1){
            			getSqlMapClientTemplate().insert("AwardSheetForCollation.insertIntoStudentMarks", input);
            		}
            }
            
            if (input.getDisplayType().equals("E")) {
                input.setTotalExternal(total);
                input.setExternalGrade(grade);
                int rowChanged = getSqlMapClientTemplate().update("AwardSheetForCollation.updateStudentMarksSummaryExternal",input);

               if (rowChanged < 1) {
                    getSqlMapClientTemplate().insert("AwardSheetForCollation.insertIntoStudentMarksSummaryExternal",input);
                }
            } 
            else if (input.getDisplayType().equals("R")) {
            	input.setTotal(total);
              input.setFinalGrade(grade);
                int rowChanged = getSqlMapClientTemplate().update("AwardSheetForCollation.updateStudentMarksSummaryRemedial",input);

                if (rowChanged < 1) {
                    getSqlMapClientTemplate().insert("AwardSheetForCollation.insertIntoStudentMarksSummaryRemedial",input);
                }
            }
		}
		

		}catch(Exception ex){
		System.out.println(ex);
			logObj.error("saveStudentMarks: "+ex);
            throw new MyException("E");
		}

		return null;
	}
	
	/**
     * Method for checking datatype of a value
     * @param s1
     * @return boolean value
     */
	public boolean isInteger(String s1){
	   try{
	      Double.parseDouble(s1);
	      return true;
	   }
	   catch(Exception ex){
	      return false;
	   }
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
	  	
	        		progList = getSqlMapClientTemplate().queryForList("AwardSheetForCollation.getStudentMarks", inputObj);
	        			if(progList.size()==0){
	        				return null;
	        			}
	        	
	            return progList;
	        } catch (Exception e) {
	        	logObj.error("getStudentMarks " + e);
	        }

	        return progList;
	    }
	



}
