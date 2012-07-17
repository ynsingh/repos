package in.ac.dei.edrp.cms.daoimpl.coursegradelimit;

import java.util.ArrayList;
import java.util.List;
import in.ac.dei.edrp.cms.dao.coursegradelimit.GradeLimitDao;
import in.ac.dei.edrp.cms.domain.coursegradelimit.GradeLimitDomain;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * file to implements GradeLimit Interface methods
 * @author Ashish ohan
 * @date 27 feb 2012
 * @version 1.0
 */
public class GradeLimitDaoImpl  extends SqlMapClientDaoSupport implements GradeLimitDao  {
	
	/** Creating object of Logger for log Maintenance */
	private Logger loggerObject = Logger.getLogger(GradeLimitDaoImpl.class);

	/**
	 * method to get course details
	 * @author ashish mohan
	 * @date 27 feb 2012
	 * @version 1.0
	 */
	public List<GradeLimitDomain>  getCourseDetails(GradeLimitDomain input) {
		List<GradeLimitDomain> courseList=new ArrayList<GradeLimitDomain>();
		if(input.getEmployeeId().equalsIgnoreCase("external")){
			 if(input.getDisplayType().equalsIgnoreCase("R")){
			courseList = getSqlMapClientTemplate()
		         .queryForList("coursegradelimit.getCourseDetailsRemedial",input);
			 }
			 else if(input.getDisplayType().equalsIgnoreCase("E")){
			 courseList = getSqlMapClientTemplate()
	         .queryForList("coursegradelimit.getCourseDetailsExternal",input);
			 }
			 else{
			courseList = getSqlMapClientTemplate()
		         .queryForList("coursegradelimit.getCourseDetailsCore",input); 
			 }
		}
		else{
		 courseList = getSqlMapClientTemplate()
         .queryForList("coursegradelimit.getCourseDetails",input);
		}
		 return courseList;
	}
	
	/**
	 * method to get grade details of course
	 * @author ashish mohan
	 * @date 29 feb 2012
	 * @version 1.0
	 */
	public  List<GradeLimitDomain> getCourseGradeDetails(GradeLimitDomain input) {
		List<GradeLimitDomain> gradeList=new ArrayList<GradeLimitDomain>();
		if(input.getEmployeeId().equalsIgnoreCase("external")){
			 if(input.getDisplayType().equalsIgnoreCase("R")){
					gradeList = getSqlMapClientTemplate()
				         .queryForList("coursegradelimit.getCourseGradeDetailsRemedial",input);
					 }
					 else if(input.getDisplayType().equalsIgnoreCase("E")){
							gradeList = getSqlMapClientTemplate()
						      .queryForList("coursegradelimit.getCourseGradeDetailsExternal",input);
					 }
					 else{
						 gradeList = getSqlMapClientTemplate()
					      .queryForList("coursegradelimit.getCourseGradeDetailsCore",input);
					 }
	
		}
		else{
		 gradeList = getSqlMapClientTemplate()
         .queryForList("coursegradelimit.getCourseGradeDetails",input);
		}
		 return gradeList;
	}
	


	/**
	 * method to get grade and exam type
	 * @author ashish mohan
	 * @date 29 feb 2012
	 * @version 1.0
	 */
	public List<GradeLimitDomain> getExamDetails(GradeLimitDomain input) {
		List<GradeLimitDomain> examList = getSqlMapClientTemplate().queryForList("coursegradelimit.getExamDetails",input);
		return examList;
	}
	
	
	/**
	 * method to set course grade 
	 * @author ashish mohan
	 * @date 29 feb 2012
	 * @version 1.0
	 */
	public String setCourseGradeDetails(GradeLimitDomain input) {
		String result = null;
		
		String ifRecordExist="";
		
		//check if already exists
		ifRecordExist=getSqlMapClientTemplate().queryForList("coursegradelimit.getCount",input).toString();
		
		//for insert
		if(Integer.parseInt(ifRecordExist.substring(1,ifRecordExist.length()-1))==0){
			try{
				getSqlMapClientTemplate().insert("coursegradelimit.setCourseGradeDetails",input);
				result="insert";
			}
			catch(Exception e){
				loggerObject.error(e.getMessage());
				result="error"+e;
			}
		}
		
		//for update
		else{
			try{
				getSqlMapClientTemplate().update("coursegradelimit.updateCourseGradeDetails",input);
				result="update";
			}
			catch(Exception e){
				loggerObject.error(e.getMessage());
				result="error"+e;
			}
			
		}
		return result;
	}
	
	/**
	 * method to delete course grade 
	 * @author ashish mohan
	 * @date 3 mar 2012
	 * @version 1.0
	 */
	public String deleteCourseGradeDetails(GradeLimitDomain input) {
		 String result = null;
		 int i=0;
		 try{ 	
			 i+=getSqlMapClientTemplate().update("coursegradelimit.deleteCourseGradeDetails",input);
			 result=Integer.toString(i);
		    }
		catch(Exception e){
			loggerObject.error(e.getMessage());
			 result="error"+e;
		}
		 return result;
	}
	
		//Methods For Course Cancellation Interface Added By Mandeep Sodhi
	/**
	 * method to get Approved Course Code 
	 * @author Mandeep Sodhi
	 * @date 7 May 2012
	 * @version 1.0
	 */
	
	@SuppressWarnings("unchecked")
	public List<GradeLimitDomain> getApprovedCourseCodes(GradeLimitDomain input) {
		List<GradeLimitDomain>courseList=new ArrayList<GradeLimitDomain>();
		try{
			 if(input.getDisplayType().equalsIgnoreCase("teacher")){

				 if(input.getGrade()!=null){
					courseList=getSqlMapClientTemplate().queryForList("coursegradelimit.getAprCoursesDetailOfTeacher",input);
				 }
				 else{
					 courseList=getSqlMapClientTemplate().queryForList("coursegradelimit.getAprCoursesOfTeacher",input);
				 	}
			 	}	
			 else if(!input.getDisplayType().equalsIgnoreCase("teacher")){

				 	if(input.getGrade()!=null){
							courseList=getSqlMapClientTemplate().queryForList("coursegradelimit.getApprovedCourseDetail",input);
						}	
					 else{
							courseList=getSqlMapClientTemplate().queryForList("coursegradelimit.getApprovedCourses",input);
					 }
			 }

		}
		catch (Exception e) {
			loggerObject.error(e.getMessage());
			System.out.println("exception is"+e);
		}
		return courseList;
	}
	
	/**
	 * method to update the status of course code to WDW
	 * @author Mandeep Sodhi
	 * @date 7 May 2012
	 * @version 1.0
	 */
	public String CancelApprovedCourse(GradeLimitDomain input) {
		int courseList=1;
		try{
			courseList=getSqlMapClientTemplate().update("coursegradelimit.CancelApprovedCourses",input);
		}
		catch (Exception e) {
			loggerObject.error(e.getMessage());
			System.out.println("exception is"+e);
		}
		String result=Integer.toString(courseList);
		return result;
	}

	//Methods For Delay In Component Marks Interface
	/**
	 * method to get All the Component Details 
	 * @author Mandeep Sodhi
	 * @date 22 May 2012
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<GradeLimitDomain> getAllComponentDetails(GradeLimitDomain input) {
		List<GradeLimitDomain>componentDetail=new ArrayList<GradeLimitDomain>();
		try{
			componentDetail=getSqlMapClientTemplate().queryForList("coursegradelimit.getComponentDetails",input);
		}
		catch (Exception e) {
			loggerObject.error(e.getMessage());
			System.out.println("exception is"+e);
		}
		return componentDetail;
	}
	/**
	 * method to get All Course Code 
	 * @author Mandeep Sodhi
	 * @date 22 May 2012
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<GradeLimitDomain> getAllCourseCode(GradeLimitDomain input) {
		List<GradeLimitDomain>courseList=new ArrayList<GradeLimitDomain>();
		try{
			System.out.println("impl");
			courseList=getSqlMapClientTemplate().queryForList("coursegradelimit.getAllCourseCode",input);
		}
		catch (Exception e) {
			loggerObject.error(e.getMessage());
			System.out.println("exception is"+e);
		}
		return courseList;
	}
	/**
	 * method to get Details of all Courses 
	 * @author Mandeep Sodhi
	 * @date 22 May 2012
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<GradeLimitDomain> getAllCourseDetails(GradeLimitDomain input) {
		List<GradeLimitDomain>courseDetail=new ArrayList<GradeLimitDomain>();
		try{

			courseDetail=getSqlMapClientTemplate().queryForList("coursegradelimit.getAllCourseDetail",input);
		}
		catch (Exception e) {
			loggerObject.error(e.getMessage());
			System.out.println("exception is"+e);
		}
		return courseDetail;
	}	
	
}
