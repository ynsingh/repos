package in.ac.dei.edrp.cms.utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.controller.activitymaster.StartActivityController;
import in.ac.dei.edrp.cms.dao.activitymaster.StartActivityDao;
import in.ac.dei.edrp.cms.daoimpl.registration.prestagingdaoimpl.ReadyPreStagingDAOImpl;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;

import in.ac.dei.edrp.cms.domain.registration.prestaging.TransferNORInPSTBean;
import in.ac.dei.edrp.cms.domain.utility.ErrorLogs;
import in.ac.dei.edrp.cms.domain.utility.SystemValue;


public class RegistrationFunction{
		
	public static final Logger logger = Logger.getLogger(RegistrationFunction.class);
	
	private TransactionTemplate transactionTemplate=null;
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	protected SqlMapClient sqlMapClient = null;
	
    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }
	
//	public RegistrationFunction(TransactionTemplate transactionTemplate){
//		//System.out.println("Coming inside registration function");
//		//this.sqlMapClient=sqlMapClient;
//		this.transactionTemplate=transactionTemplate;
//		//System.out.println(sqlMapClient);
//	}
	
	public RegistrationFunction(SqlMapClient sqlMapClient,TransactionTemplate transactionTemplate){
		//System.out.println("Coming inside registration function");
		this.sqlMapClient=sqlMapClient;
		this.transactionTemplate=transactionTemplate;
		//System.out.println(sqlMapClient);
	}
	/**
	 * Default constructor
	 */
	public RegistrationFunction(){
		
	}
	/**
	 * It returns program course key
	 * @param programCoursekeyData
	 * @return
	 */
	public String getProgramCourseKey(TransferNORInPSTBean programCoursekeyData){
		//Connection con=DBConnection.getConnection();
		String programCourseKey="";
		try{
			
			List<TransferNORInPSTBean> programCourse=(List<TransferNORInPSTBean>)sqlMapClient.queryForList("TransferNORInPSTBean.getProgramCourseKey", programCoursekeyData);
				for(TransferNORInPSTBean programCourseValue:programCourse){
					programCourseKey=programCourseValue.getProgramCourseKey();
				}
			
		}catch(Exception e){
			//System.out.println("Exception in get program course key: "+e);
			logger.info("Exception in program course key"+e.getMessage());
		}
		return programCourseKey;
	}

	/**
	 * It returns registerDue date.
	 * @param registrationDueDate
	 * @return
	 */
	public String getRegisterDueDate(TransferNORInPSTBean registrationDueData){
		
		String registerDueDate="";
		try{
//			System.out.println(registrationDueData.getProgramCourseKey()+" and "+registrationDueData.getSemesterStartDate()+""
//					+" and "+registrationDueData.getSemesterEndDate());
			List<TransferNORInPSTBean> dueDateList=(List<TransferNORInPSTBean>)sqlMapClient.queryForList("TransferNORInPSTBean.getRegisterDueDate", registrationDueData);
			for(TransferNORInPSTBean registrationDueDateValue: dueDateList){
				registerDueDate=registrationDueDateValue.getRegisterDueDate();
				//System.out.println(registerDueDate+"inside coming");
			}
			
		}catch(Exception e){
			//System.out.println("Exception in register due date: "+e);
			logger.info("Exception in register due date"+e.getMessage());
		}
		return registerDueDate;
	}
	
	/**
	 * It returns minimum semester start date
	 * @param minimumSemester
	 * @return
	 */
	public String getMinimumSemester(TransferNORInPSTBean minimumSemesterData) {
		// TODO Auto-generated method stub
		String minSemester="";
		try{
		List<TransferNORInPSTBean> minimumSemesterList=(List<TransferNORInPSTBean>)sqlMapClient.queryForList("TransferNORInPSTBean.getMinimumSemester", minimumSemesterData);
			for(TransferNORInPSTBean minimumSemesterValue: minimumSemesterList){
				minSemester=minimumSemesterValue.getNextSemesterCode();
			}
		}catch(Exception e){
			//System.out.println("Exception in minimum semester: "+e.getMessage());
			logger.info("Exception in minimum semester detail"+e.getMessage());
		}
		return minSemester;
	}

	/**
	 * It returns maximum attempt allowed for that program
	 * @param maxAttemptDate
	 * @return int
	 */
	public int maximumAttempNumberAllowed(TransferNORInPSTBean maxAttemptData){

		int  maxAttemptAllow=0;
		try{
			List<TransferNORInPSTBean> maxAttemptAllowed=(List<TransferNORInPSTBean>)sqlMapClient.queryForList("TransferNORInPSTBean.getMaxAttempt", maxAttemptData);
			for(TransferNORInPSTBean maxAttemptAllowedValue: maxAttemptAllowed){
				maxAttemptAllow=maxAttemptAllowedValue.getMaxAttemptAllowed();
			}
		}catch(Exception e){
			//System.out.println("Exception is: "+e.getMessage());
			logger.info("Exception in max attempt allowed."+e.getMessage());
		}
		return maxAttemptAllow;
	}
	
	/**
	 * 
	 * @param previousSemesterDetailData
	 * @return List with previous semester details of student
	 */
	public List<PreviousSemesterDetail> getPreviousSessionDate(PreviousSemesterDetail previousSemesterDetailData) {
		// TODO Auto-generated method stub
		List<PreviousSemesterDetail> previousSemesterDetail=new ArrayList<PreviousSemesterDetail>();
		try{
//				System.out.println("previous Semes"+previousSemesterDetailData.getEntityId()+" and "+""
//						+" and "+previousSemesterDetailData.getRollNumber()+" and "+previousSemesterDetailData.getProgramCourseKey());
			List<PreviousSemesterDetail> previouSemesterDetailList=(List<PreviousSemesterDetail>)sqlMapClient.queryForList("TransferNORInPSTBean.getPreviousSemesterDetail", previousSemesterDetailData);
			for(PreviousSemesterDetail previouSemesterDetaileValue: previouSemesterDetailList){
				PreviousSemesterDetail preSemesterDetail=new PreviousSemesterDetail(previouSemesterDetaileValue.getPreviousSemesterStartDate(),
						previouSemesterDetaileValue.getPreviousSemesterEndDate());
				preSemesterDetail.setStatus(previouSemesterDetaileValue.getStatus());
				
					previousSemesterDetail.add(preSemesterDetail);

			}
			
		}catch(Exception e){
			//System.out.println("Exception in previous detail: "+e.getMessage());
			logger.info("Exception in previous semester for roll number"+previousSemesterDetailData.getRollNumber()+" and error "+e.getMessage());
		}
		return previousSemesterDetail;
	}
	
	/**
	 * It returns next year date
	 * @param date
	 * @return incremented date
	 */
	public String getNextYear(String date){
		String registerDueDate="";
		try{
			List<TransferNORInPSTBean> minimumRegisterDueDateList=(List<TransferNORInPSTBean>)sqlMapClient.queryForList("TransferNORInPSTBean.getNextRegisterDueDate", date);
			for(TransferNORInPSTBean minimumRegisterDueDateValue: minimumRegisterDueDateList){
				registerDueDate=minimumRegisterDueDateValue.getRegisterDueDate();
			}
			
		}catch(Exception e){
			//System.out.println("Exception in add next year"+e.getMessage());
			logger.info("Exception in get next year "+e.getMessage());
		}
		return registerDueDate;
	}



	public String getActualRegisterDate(String inputDate) {
		// TODO Auto-generated method stub
		String finalDate="";
		try{
			Calendar cal = Calendar.getInstance();
			
			int month = cal.get(Calendar.MONTH)+1;
			int year = cal.get(Calendar.YEAR);
//			System.out.println("year: "+year+" and moth"+month);
			StringTokenizer st = new StringTokenizer(inputDate,"/-");
			Map<Integer,Integer> monthMap=new HashMap<Integer,Integer>();
			int key=0;
		    while (st.hasMoreElements()) {
		        int token = Integer.parseInt(st.nextToken());
		        //System.out.println("token = " + token);
		        monthMap.put(key, token);
		        key++;
		    }//loop ends
		    int monthCompare=0;
		    int date=0;
		    for (Map.Entry<Integer, Integer> map : monthMap.entrySet()){
		    	if(map.getKey()==0){
		    	monthCompare=map.getValue();
//		    	System.out.println(monthCompare);
		        	if(monthCompare<month){
		        		year=year+1;
		        	}
		    	}
		    	if(map.getKey()==1){
		    		date=map.getValue();
		    	}
		    	
		    }
		    finalDate=year+"-"+String.format("%02d",monthCompare)+"-"+String.format("%02d",date);
		    return finalDate;
		}catch(Exception e){
			//System.out.println("Exception is: "+e.getMessage());
			logger.info("Exception in actual register date"+e.getMessage());
			
		}
		return finalDate;
	}

	/**
	 * Returns program, branch, specialization and semester id for program course key for start activity screen
	 * @param startActivityBean
	 * @return
	 */
	public List<StartActivityBean> getProgramForKey(StartActivityBean startActivityBean) {
		// TODO Auto-generated method stub
		List<StartActivityBean> valueList=new ArrayList<StartActivityBean>();
		try{
			
			valueList=sqlMapClient.queryForList("startActivity.getProgramIdValue",startActivityBean);
		}catch(Exception e){
			
		}
		
		return valueList;
	}

	
	

	public int getIncrementedId(SystemValue systemValue) {
		// TODO Auto-generated method stub
		int value=0;
		
		try{
		List<SystemValue> list=sqlMapClient.queryForList("systemValue.getSystemValue", systemValue);
			for(SystemValue sysValue:list){
				
				value=Integer.parseInt(sysValue.getValue());
//				System.out.println("incremented"+value);

			}
		}catch(Exception e){
//			System.out.println("Exception in getting system value:"+e.getMessage());
		}
		
		return value;
	}

	
}
