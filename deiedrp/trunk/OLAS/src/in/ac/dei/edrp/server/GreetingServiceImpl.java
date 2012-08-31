package in.ac.dei.edrp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.ibatis.sqlmap.client.SqlMapClient;

import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.RPCFiles.GreetingService;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;
import in.ac.dei.edrp.shared.FieldVerifier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


/**
 * The server side implementation of the RPC service.
 */

/**
 * file updated
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet
    implements GreetingService {
    Log4JInitServlet logObj = new Log4JInitServlet();
    ReportingFunction reportfunction = new ReportingFunction();
    SqlMapClient client = SqlMapManager.getSqlMapClient();

    public String greetServer(String input) throws IllegalArgumentException {
        // Verify that the input is valid.
        if (!FieldVerifier.isValidName(input)) {
            // If the input is not valid, throw an IllegalArgumentException back
            // to the client.
            throw new IllegalArgumentException(
                "Name must be at least 4 characters long");
        }

        String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");

        return "Hello, " + input + "!<br><br>I am running " + serverInfo +
        ".<br><br>It looks like you are using:<br>" + userAgent;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public List<DataBean> updateMarks(String university_id, String entity_type,
        String entity_id, String program_id, String subject_code,
        String gender) {
         System.out.println("------------update marks running----------");
         List<DataBean> list2 = new ArrayList<DataBean>();
        String comp = null;
        String[] subjectCode = null;
        String eligibility = "Eligible";
        String errRegistrationNumber = "";
        ReportInfoGetter entityInfo = new ReportInfoGetter();
        try {   
        	subjectCode = getSubjectCode(entity_id, program_id, subject_code,university_id);
            String[] udate = reportfunction.getSessionDate(university_id);
            for (int len = 0; len < subjectCode.length; len++) {
            	 List<DataBean> list1 = new ArrayList<DataBean>();
                //for (int j = len; j < specialization_code.length; j++) {
                    List<ReportInfoGetter> list = new ArrayList<ReportInfoGetter>();
                    /**
                     * method updated by devendra May 3rd
                     */
                    comp = reportfunction.isUpdated(entity_id, program_id,udate);
                    if (comp.equalsIgnoreCase("R") ||
                            comp.equalsIgnoreCase("N") ||
                            comp.equalsIgnoreCase("C") ||
                            comp.equalsIgnoreCase("I")) {                                              
                        entityInfo.setProgram_id(program_id);
                        entityInfo.setEntity_id(entity_id);
                        entityInfo.setType("S");
                        entityInfo.setOffered_by(entity_id);
                        entityInfo.setSubject_code("%"+subjectCode[len]);
                        entityInfo.setStart_date(udate[0]);
                        entityInfo.setEnd_date(udate[1]);                        
                        List<ReportInfoGetter> li = null;
                        try{
                        	li = client.queryForList("updateMarksList",entityInfo);                        	
                        }
                        catch(Exception e)
                        {                        	
                        	System.out.println("error " + e);
                        }
	                    if(li.size()>0){
	                        String registrationNumber = null;
	                        String temp = "abc";
	                        String temp1 = "";
	                        String eligibility1 = "";
	                        String message1 = "";
	                        String cos = null;
	                        String message = "You are eligible";
	                        for (ReportInfoGetter entity : li) {                          
	                            registrationNumber = entity.getRegistration_number();	
	                            errRegistrationNumber = registrationNumber;	
	                            logObj.logger.info("Computed Marks for registration number" +registrationNumber);	                           	                            
	                            if ((!registrationNumber.equalsIgnoreCase(temp1)) &&(!temp1.equalsIgnoreCase(""))) {	                                
	                                list.add(new ReportInfoGetter(program_id,entity_id, subjectCode[len], temp1,cos, comp, eligibility1, message1));	                            
	                            }	
	                            if (!registrationNumber.equalsIgnoreCase(temp)) {
	                                message = "you are eligible";
	                                eligibility = "Eligible";
	                                temp = entity.getRegistration_number();
	                                eligibility = getAgeEligibility(entityInfo, registrationNumber);	                                
	                                if (eligibility.equalsIgnoreCase("InEligible")) {
	                                    message = "Overage";
	                                }
	                            }
	                            System.out.println("------------update marks running starts ----------"+entity.getRegistration_number());
	                            double boardPercentage = 0.0;
	                            if (checkBoardFlag(entityInfo,entity.getComponent_id())) {	                                
	                                boardPercentage = boardPercentage(entityInfo,entity.getComponent_id(),entity.getMarks_percentage(), entity.getBoard_id());
	                            } 
	                            else {	                            	
	                                boardPercentage = entity.getMarks_percentage();
	                            }
	                            updateComputedMarks(entityInfo, registrationNumber,entity.getComponent_id(), boardPercentage,entity.getBoard_id(), udate);	
	                            ReportInfoGetter getcategory = new ReportInfoGetter();	
	                            getcategory.setRegistration_number(registrationNumber);
	                            // setting session start_date and end_date
	                            getcategory.setStart_date(udate[0]);
	                            getcategory.setEnd_date(udate[1]);
	                            
	                            List<ReportInfoGetter> getLi = client.queryForList("getCategoryList", getcategory);
	                            for (ReportInfoGetter getE : getLi) {
	                            	cos = getE.getCos_value();
	                            }
	                            if (eligibility.equalsIgnoreCase("Eligible")) {                         	
	                                eligibility = getEligibility(entityInfo,registrationNumber,entity.getComponent_id(),entity.getMarks_percentage(), udate);
	
	                                if (eligibility.equalsIgnoreCase("InEligible")) {
	                                    message = reportfunction.getComponentDescription(entity.getComponent_id(), registrationNumber, entityInfo) +" < " +
	                                        reportfunction.getComponetEligibleMarks(entity.getComponent_id(),registrationNumber, entityInfo);
	                                }
	                            }	
	                            temp1 = registrationNumber;
	                            eligibility1 = eligibility;
	                            message1 = message;
	                        }		                       
	                        list.add(new ReportInfoGetter(program_id, entity_id,subjectCode[len], temp1, cos, comp,eligibility, message));	
	                        Iterator i = list.iterator();
	                        while (i.hasNext()) {
	                            ReportInfoGetter ceig = (ReportInfoGetter) i.next();	                            	                           
	                            updateSumComputedMarks(ceig.getProgram_id(),ceig.getEntity_id(),ceig.getRegistration_number(),ceig.getCos_value(), ceig.getComp(),ceig.getReason_code(), ceig.getMessage(),udate);	                            
	                        }	
	                    } else {
	                        System.out.println("Already Computed !!");
	                    } // if already computed i.e flag is T,E,F	                    
	            	}            
            } // for loop ends for branch
            reportfunction.updateControlReport(entityInfo, "C", comp);
            list2 = getmeritStudent(university_id, entity_type, entity_id,program_id, subject_code);
        } catch (Exception e) {
            logObj.logger.error("Registration number not successfully computed: " +errRegistrationNumber+" : "+e);
        }
      
        return list2;
    } // updateMarks ends here

    /**
     * Method updated
     */
    @SuppressWarnings("unchecked")
    public String getAgeEligibility(ReportInfoGetter entityInfo,
        String registrationNumber) {
        String eligibility = "Eligible";
        try {
            ReportInfoGetter getEligibleList = entityInfo;
            getEligibleList.setRegistration_number(registrationNumber);
            getEligibleList.setCategory(reportfunction.getCategoryId(registrationNumber, entityInfo));            
            Calendar cal = Calendar.getInstance();
            List<ReportInfoGetter> age = client.queryForList("getEligibleAge",getEligibleList);
            int num = 0;
            for (ReportInfoGetter getAge : age) {
                num = getAge.getAge_eligibility();                
            }
            int year = cal.get(Calendar.YEAR);
            num = year - num;
            String compare_date = num + "-07-01";
            getEligibleList.setCompare_date(compare_date);
            List<ReportInfoGetter> agediff = client.queryForList("getEligibleDate",getEligibleList);
            for (ReportInfoGetter ageDiff : agediff) {
                if (ageDiff.getDiff() > 0) {     
                    eligibility = "InEligible";
                } else {                   
                    eligibility = "Eligible";
                }
            }
        } catch (Exception e) {
            logObj.logger.error("Error in age Eligibilty for registration: " +registrationNumber +" : "+e);
        }

        return eligibility;
    }

    /*
     * For getting String eligible or ineligible
     */
    @SuppressWarnings("unchecked")
    public String getEligibility(ReportInfoGetter entityInfo,
        String registrationNumber, String component, double percentage,
        String[] udate) {
        String eligibility = "Eligible";
        try {          
            if (isEligibleFlag(entityInfo, component)) {
                ReportInfoGetter getEligibleList = entityInfo;
                getEligibleList.setComponent_id(component);
                getEligibleList.setCategory(reportfunction.getCategoryId(registrationNumber, entityInfo));
                List<ReportInfoGetter> li = client.queryForList("getEligible",getEligibleList);
                for (ReportInfoGetter getE : li) {
                    if (percentage < getE.getComponent_eligiblity()) {
                        eligibility = "InEligible";
                    } 
                } 
            } 
        } catch (Exception e) {
            logObj.logger.error("Error in Component Eligibilty for registration: " +registrationNumber+" :: "+e);
        }
        return eligibility;
    }
    
    @SuppressWarnings("unchecked")
    public boolean isEligibleFlag(ReportInfoGetter entityInfo, String component) {
        boolean flag = false;
        try {
            ReportInfoGetter cmeig_Eligibility = entityInfo;
            cmeig_Eligibility.setComponent_id(component);
            List<ReportInfoGetter> li = client.queryForList("getEligibleFlag", cmeig_Eligibility);
            for (ReportInfoGetter getEFlag : li) {
                if (getEFlag.getComponent_criteria_flag().equalsIgnoreCase("y")) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            logObj.logger.error("Flag is not set for component crieteria eligibility. : "+e);
        }
        return flag;
    }

    public void updateComputedMarks(ReportInfoGetter entityInfo,
        String registration, String component, double percentage,
        String board_id, String[] udate) {
        double[] marks = new double[2];
        double boardPercentage = percentage;
        try {
            marks = getComputedMarks(entityInfo, registration, component,boardPercentage, udate);
            ReportInfoGetter updateEntity = entityInfo;
            updateEntity.setComponent_id(component);
            updateEntity.setRegistration_number(registration);
            updateEntity.setComputed_Marks(marks[0]);
            updateEntity.setActual_computed_Marks(marks[1]);
            updateEntity.setStart_date(udate[0]);
            updateEntity.setEnd_date(udate[1]);
            client.update("updateCallList", updateEntity);
        } catch (Exception e) {
            logObj.logger.info("Error while updating computing marks for registation number=: " +registration);
            logObj.logger.info("Exception is :" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public boolean checkComputeFlag(ReportInfoGetter entityInfo,
        String component) {
        boolean compute = false;
        try {
            ReportInfoGetter checkCompute = entityInfo;
            checkCompute.setComponent_id(component);
            List<ReportInfoGetter> li = client.queryForList("getCheckCompute",checkCompute);
            for (ReportInfoGetter check : li) {
                if (check.getWeightage_flag().equalsIgnoreCase("y")) {
                    compute = true;
                }
            }
        } catch (Exception e) {
            logObj.logger.info( "Flag Value is not set for compute flag or weightage flag" +e.getMessage());
        }
        return compute;
    }

    @SuppressWarnings("unchecked")
    public boolean checkSpecialComputeFlag(ReportInfoGetter entityInfo,
        String component) {
        boolean compute = false;
        try {
            ReportInfoGetter checkCompute = entityInfo;
            checkCompute.setComponent_id(component);
            List<ReportInfoGetter> li = client.queryForList("getCheckCompute", checkCompute);
            for (ReportInfoGetter check : li) {
                if (check.getSpecial_weightage_flag().equalsIgnoreCase("y")) {
                    compute = true;
                }
            }
        } catch (Exception e) {
            logObj.logger.info("Flag Value is not set for special compute flag." +e.getMessage());
        }
        return compute;
    }

    @SuppressWarnings("unchecked")
    public boolean checkBoardFlag(ReportInfoGetter entityInfo, String component) {
        boolean board = false;
        try {
            ReportInfoGetter checkBoard = entityInfo;
            checkBoard.setComponent_id(component);
            List<ReportInfoGetter> li = client.queryForList("getcheckBoard",checkBoard);
            for (ReportInfoGetter check : li) {
                if (check.getBoard_flag().equalsIgnoreCase("y")) {
                    board = true;
                }
            }
        } catch (Exception e) {
            logObj.logger.info("Flag Value is not set for board flag." +e.getMessage());
        }
        return board;
    }

    @SuppressWarnings("unchecked")
    public double boardPercentage(ReportInfoGetter entityInfo,
        String component, double percentage, String board_id) {
        double boardPercentage = 0.0;
        try {
            ReportInfoGetter boardEntity = entityInfo;
            boardEntity.setComponent_id(component);
            boardEntity.setBoard_id(board_id);
            List<ReportInfoGetter> li = client.queryForList("getBoardPercentage",boardEntity);
            for (ReportInfoGetter board : li) {
                boardPercentage = percentage * board.getNormalization_factor();
            }
        } catch (Exception e) {
            logObj.logger.info("Error while normalizing board percentage" +e.getMessage());          
        }
        return boardPercentage;
    }

    @SuppressWarnings("unchecked")
    public double[] getComputedMarks(ReportInfoGetter entityInfo,
        String regnum, String component, double percentage, String[] udate) {
        double[] umarks = new double[2];
        double x = 0;
        double ax = 0;
        try {     
            ReportInfoGetter getM = entityInfo;
            getM.setComponent_id(component);
            getM.setType("S");
            List<ReportInfoGetter> getMarks = client.queryForList("getPercentageMarks",getM);
            for (ReportInfoGetter getCM : getMarks) {
                x = (double) (percentage * getCM.getComponent_Weightage()) / 100;
            }
            if (checkComputeFlag(entityInfo, component)) {
                ax = x;            
                if (checkSpecialComputeFlag(entityInfo, component)) {
                    x = getSpecialWeightage(regnum, entityInfo, component, x,
                            udate);
                } // check Special compute
                else {
                    x = x;
                }
            } // if check compute flag
            else {             
                if (checkSpecialComputeFlag(entityInfo, component)) {
                    double y = getSpecialWeightage(regnum, entityInfo,
                            component, x, udate);
                    x = y - x;
                    ax = 0.0;
                } // check Special compute
                else {
                    x = 0.0;
                    ax = 0.0;                   
                }
            } // else for compute flag ends
        } catch (Exception e) {
            logObj.logger.info("Error while giving weightage and special weightage." +e.getMessage());
        }
        umarks = new double[] { x, ax };
        return umarks;
    }

    @SuppressWarnings("unchecked")
    public double getSpecialWeightage(String regnum,
        ReportInfoGetter entityInfo, String component, double x, String[] udate) {
        boolean b = checkStaff(regnum, udate,component);
        try {
            ReportInfoGetter getM = entityInfo;
            getM.setStart_date(udate[0]);
            getM.setEnd_date(udate[1]);
			getM.setWeightage_id(component);           
            getM.setFlag("S");
            if (b == true) {
                boolean csw = checkStaffWard(regnum, udate);
                double sum = x;
                double sum1 = x;
                if (csw == true) {                   
                    getM.setWeightage_id("SW");
                    List<ReportInfoGetter> getStaff = client.queryForList("getStaffPercentageMarks",getM);
                    for (ReportInfoGetter getCM : getStaff) {
                        sum = sum +
                            ((x * getCM.getWeightage_percentage()) / 100);
                    }
                } else {                    
                    getM.setRegistration_number(regnum);
                    getM.setWeight_id(component);
                    List<ReportInfoGetter> getStaff = client.queryForList("getExtraPercentageMarks",getM);
                    for (ReportInfoGetter getCM : getStaff) {                       
                        sum1 = sum1 +
                            ((x * getCM.getWeightage_percentage()) / 100);
                    }
                }               
                if (sum > sum1) {
                    x = sum;
                } else {
                    x = sum1;
                }
            } // if checkstaff closed
        } catch (Exception e) {
            logObj.logger.info("Error while calculating special weightage for " +regnum);
            logObj.logger.info("Exception is :" + e.getMessage());
        }
        return x;
    }

    @SuppressWarnings("unchecked")
    public boolean checkStaffWard(String regnum, String[] udate) {
        boolean csw = false;
        try {
            ReportInfoGetter check = new ReportInfoGetter();
            check.setRegistration_number(regnum);
            check.setStart_date(udate[0]);
            check.setEnd_date(udate[1]);
            List<ReportInfoGetter> checkStaffWard = client.queryForList("getCheckStaffWard",check);
            for (ReportInfoGetter getCM : checkStaffWard) {
                if (getCM.getWeightage_id().equalsIgnoreCase("SW")) {
                    csw = true;
                    break;
                }
            }
        } catch (Exception e) {
            logObj.logger.info("Error while checking staff ward for " + regnum);
            logObj.logger.info("Exception is :" + e.getMessage());
        }
        return csw;
    }
    
    @SuppressWarnings("unchecked")
    public boolean checkStaff(String regnum, String[] udate,String component) {
        boolean b = false;
        try {
            ReportInfoGetter check = new ReportInfoGetter();
            check.setRegistration_number(regnum);
            check.setStart_date(udate[0]);
            check.setEnd_date(udate[1]);           
            List<ReportInfoGetter> checkStaff = client.queryForList("getCheckStaff",check);  
            for (ReportInfoGetter getStaff : checkStaff) {            
            	if(getStaff.getWeightage_id().equals(component)){
            		b = true;
            	}                        
            }
        } catch (Exception e) {
            logObj.logger.info("Error while checking DEI Staff for " + regnum);
            logObj.logger.info("Exception is :" + e.getMessage());
        }
        return b;
    }

    /**
     * for updating sum of computed marks and sum of actual computed marks
     * method updated
     */
    @SuppressWarnings("unchecked")
    public void updateSumComputedMarks(String program_id, String entity_id,String regnum, String cos, String flag,String message, String reason_code, String[] udate) {
        try {            
            ReportInfoGetter updateSum = new ReportInfoGetter();
            updateSum.setProgram_id(program_id);
            updateSum.setEntity_id(entity_id);
            updateSum.setRegistration_number(regnum);
            updateSum.setCos_value(cos);
            updateSum.setReason_code(reason_code);
            updateSum.setMessage(message);
            // setting start_date and end_date
            updateSum.setStart_date(udate[0]);
            updateSum.setEnd_date(udate[1]);
            double sum_computed = 0.0;
            double sum_actual_computed = 0.0;
           
            List<ReportInfoGetter> getMarks = client.queryForList("getComputeMarks",updateSum);
            for (ReportInfoGetter marks : getMarks) {                
                sum_computed = marks.getSum_computed_marks();
                sum_actual_computed = marks.getSum_actual_computed_marks();
                if (message.equalsIgnoreCase("InEligible")) {
                    updateSum.setCalled("n");
                } else {
                    updateSum.setCalled("-");
                }
                updateSum.setSum_computed_marks(sum_computed);
                updateSum.setSum_actual_computed_marks(sum_actual_computed);
            }
            //For Check Duplicate records 
            //Add by Devendra June 13
            Integer count=(Integer)client.queryForObject("checkDuplicateForComputeMarks", updateSum);            
            if (count>0) {
                client.update("updateTestNumber", updateSum);                
            } else {
                client.insert("insertTestNumber", updateSum);                
            }
        } catch (Exception e) {
            logObj.logger.error("Error while inserting or updating data into STUDENT_TEST_NUMBER for " +regnum);
            logObj.logger.info("Exception is :" + e.getMessage());     
        }
    }

    @SuppressWarnings("unchecked")
    public List<DataBean> getmeritStudent(String university_id,
        String entity_type, String entity_id, String program_id,
        String subject_code) {
        List<DataBean> list = new ArrayList<DataBean>();       
        String[] subjectCode;
        try {           
            subjectCode=getSubjectCode(entity_id, program_id, subject_code, university_id);
            String[] udate = reportfunction.getSessionDate(university_id);
            for (int i = 0; i < subjectCode.length; i++) {               
                    ReportInfoGetter meritStudent = new ReportInfoGetter();
                    meritStudent.setUniversity_id(university_id);
                    meritStudent.setEntity_id(entity_id);
                    meritStudent.setOffered_by(entity_id);
                    meritStudent.setProgram_id(program_id);
                    meritStudent.setStart_date(udate[0]);
                    meritStudent.setEnd_date(udate[1]);
                    meritStudent.setSubject_code("%"+subjectCode[i]); 
                    
                    List<ReportInfoGetter> getStudents = client.queryForList("getInEligibleStudentMarks",meritStudent);
                    for (ReportInfoGetter student : getStudents) {                        
                        String registrationNumber = student.getRegistration_number();
                        String[] udetail = reportfunction.getUserDetail(registrationNumber,meritStudent);
                        String[][] studentmarks = reportfunction.getFullyComputedmarks(registrationNumber,meritStudent, udate);
                        double sum_cm = student.getSum_computed_marks();
                        String status = student.getStatus();
                        String reason = student.getReason_code();
                        list.add(new DataBean(registrationNumber, udetail,studentmarks, sum_cm, status, reason));
                    }
            } // i=0,branch_code ends
        } catch (Exception e) {
            logObj.logger.info("Error while getting student's list");
            logObj.logger.info("Exception is :" + e.getMessage());
        }
        return list;
    }

/*    @SuppressWarnings("unchecked")
    public boolean generateTestNumber(String university_id, String entity_type,
        String entity_id, String program_id, String subject_code,String gender) {
        List<String[]> list = new ArrayList<String[]>();
        String[] category = null;
        String[] cos=null;
        String comp = null;
        boolean compute = false;
        String[]subjectCode=null;
        try {        	
        	subjectCode=getSubjectCode(entity_id, program_id, subject_code, university_id);
            String[] udate = reportfunction.getSessionDate(university_id);
            ReportInfoGetter test_number=null;
            for (int len = 0; len < subjectCode.length; len++) {            	
            	comp=reportfunction.isUpdated(entity_id, program_id, udate);
                    if (comp.equalsIgnoreCase("I")) {
                    	test_number = new ReportInfoGetter();
                        test_number.setEntity_id(entity_id);
                        test_number.setOffered_by(entity_id);
                        test_number.setProgram_id(program_id);
                        test_number.setSubject_code("%"+subjectCode[len]);
                        test_number.setStart_date(udate[0]);
                        test_number.setEnd_date(udate[1]);
                        List<ReportInfoGetter> li = client.queryForList("countCategory",test_number);
                        for (ReportInfoGetter test : li) {
                            category = new String[test.getCount()];
                            cos=new String[test.getCount()];
                        }
                        List<ReportInfoGetter> li1 = client.queryForList("getCosValueForTestNumber",test_number);
                        int catlen = 0;
                        for (ReportInfoGetter test : li1) {            
                            category[catlen] = test.getCos_value().substring(0,2);
                            cos[catlen]=test.getCos_value();
                            catlen++;                        	
                        }
                        for (int i = 0; i < category.length; i++) {
                            System.out.println("----------inside for loop--------------");
                            int count = 0;
                            String x = "";
                            list = new ArrayList();
                            List<ReportInfoGetter> li2 = client.queryForList("getMaxNumber",test_number);
                            for (ReportInfoGetter max : li2) {
                                x = max.getMax_test_number();
                            }
                            if ((x == null) || x.equals("")) {                           
                                x = "100";
                            } else {
                                int y = Integer.parseInt(x) + 1;
                                x = String.valueOf(y);
                            }
                            test_number.setCategory(category[i]);
                            test_number.setCalled("y");
                            test_number.setCos_value(cos[i]);
                            String suf = "00001";
                            List<ReportInfoGetter> li3 = client.queryForList("getRegistrationTestNumber", test_number);
                            for (ReportInfoGetter reg_test_number : li3) {
                                boolean b = lookupcity(university_id,reg_test_number.getCity());
                                if (b == true) {                                   
                                    updateTest(String.valueOf(gettingCode(x,Long.valueOf(suf), 5)),program_id, entity_id,subjectCode[len],reg_test_number.getRegistration_number(),udate,cos[i]);
                                    suf = String.valueOf(Integer.parseInt(suf) +1);
                                    count++;
                                } else {                                	
                                    list.add(new String[] {x, program_id, entity_id,subjectCode[len],reg_test_number.getRegistration_number()});
                                }
                            }
                            Iterator itr = list.iterator();
                            String[] data = null;
                            String pre = "100";
                            String su = "00001";
                            while (itr.hasNext()) {
                                data = (String[]) itr.next();
                                pre = data[0];
                                if (count > 0) {
                                    pre = String.valueOf(Integer.parseInt(pre) +1);
                                }
                            }
                            Iterator itr1 = list.iterator();
                            while (itr1.hasNext()) {
                                data = (String[]) itr1.next();
                                updateTest(String.valueOf(gettingCode(pre,Long.valueOf(su), 5)), program_id,entity_id, subjectCode[len], data[4],udate,cos[i]);
                                su = String.valueOf(Integer.parseInt(su) + 1);
                            }
                        } 
                    }
                    else {
                        compute = true;
                    }
            }
            reportfunction.updateControlReport(test_number, "T",comp);
        } catch (Exception e) {
            logObj.logger.info("Error while generating test number");
            logObj.logger.info("Exception is :" + e.getMessage());
        }
        return compute;
    }*/
    
    @SuppressWarnings("unchecked")
    public boolean generateTestNumber(String university_id, String entity_type,
        String entity_id, String program_id, String subject_code,String gender) {       
        String[] category = null;
        String[] cos=null;
        String comp = null;
        boolean compute = false;
        String[]subjectCode=null;
        try {        	
        	subjectCode=getSubjectCode(entity_id, program_id, subject_code, university_id);
            String[] udate = reportfunction.getSessionDate(university_id);
            ReportInfoGetter test_number=null;
            for (int len = 0; len < subjectCode.length; len++) {            	
            	comp=reportfunction.isUpdated(entity_id, program_id, udate);
                    if (comp.equalsIgnoreCase("I")) {                                   	
                    	test_number = new ReportInfoGetter();
                        test_number.setEntity_id(entity_id);
                        test_number.setOffered_by(entity_id);
                        test_number.setProgram_id(program_id);
                        test_number.setSubject_code("%"+subjectCode[len]);
                        test_number.setStart_date(udate[0]);
                        test_number.setEnd_date(udate[1]);
                        test_number.setUniversity_id(university_id.substring(1, 5));
                        test_number.setUser_id(university_id);                        
                        List<ReportInfoGetter> li = client.queryForList("countCategory",test_number);
                        for (ReportInfoGetter test : li) {
                            category = new String[test.getCount()];
                            cos=new String[test.getCount()];
                        }
                        List<ReportInfoGetter> li1 = client.queryForList("getCosValueForTestNumber",test_number);
                        int catlen = 0;
                        for (ReportInfoGetter test : li1) {            
                            category[catlen] = test.getCos_value().substring(0,2);
                            cos[catlen]=test.getCos_value();
                            catlen++;                        	
                        }
                        for (int i = 0; i < category.length; i++) {                           
                            test_number.setCategory(category[i]);  
                            test_number.setCalled("y");
                            test_number.setCos_value(cos[i]);                            
                            ReportInfoGetter nums=(ReportInfoGetter) client.queryForObject("getTestNumber", test_number);
                            ReportInfoGetter startNumber=(ReportInfoGetter)client.queryForObject("getStartRollNumber", test_number);
                            if(nums==null){                            	                            	
                            	test_number.setGn_number(startNumber.getGn_number());
                            	test_number.setBc_number(startNumber.getBc_number());
                            	test_number.setSc_number(startNumber.getSc_number());
                            	test_number.setSt_number(startNumber.getSt_number());
                            	client.insert("insertEntranceRollNumber", test_number);
                            }
                            else{                            	
                            	test_number.setGn_number(nums.getGn_number());
                            	test_number.setBc_number(nums.getBc_number());
                            	test_number.setSc_number(nums.getSc_number());
                            	test_number.setSt_number(nums.getSt_number());
                            }
                            //Get Student List
                            List<ReportInfoGetter> li3 = client.queryForList("getRegistrationTestNumber", test_number);                                  
                            for (ReportInfoGetter reg_test_number : li3) {
                            	String roll="";
                            	if(category[i].equals("GN")){
                            		//Generate 6 digit Test number
	                            	roll=generateRollNum(test_number.getGn_number(),6);	
	                            	if(Long.parseLong(roll)<Long.parseLong(startNumber.getBc_number())){
	                            		//Update test number
		                            	updateTest(roll, program_id, entity_id,reg_test_number.getRegistration_number(), udate,cos[i]);	                            		                            		                           
		                            	test_number.setGn_number(roll);
	                            	}
	                            	else{
	                            		logObj.logger.info("General Category Test number is greater than or equal to BC category Starting test number so test number is not generated for registration number "+reg_test_number.getRegistration_number());
	                            	}	                            	
	                            }
	                            else if(category[i].equals("BC")){	   
	                            	//Generate 6 digit Test number
	                            	roll=generateRollNum(test_number.getBc_number(),6);
	                            	if(Long.parseLong(roll)<Long.parseLong(startNumber.getSc_number())){
	                            		//Update test number
		                            	updateTest(roll, program_id, entity_id,reg_test_number.getRegistration_number(), udate,cos[i]);	                            	
		                            	test_number.setBc_number(roll);
	                            	}
	                            	else{
	                            		logObj.logger.info("BC Category Test number is greater than or equal to SC category Starting test number so test number is not generated for registration number "+reg_test_number.getRegistration_number());
	                            	}	 	                            	
	                            }
	                            else if(category[i].equals("SC")){
	                            	//Generate 6 digit Test number
	                            	roll=generateRollNum(test_number.getSc_number(),6);
	                            	if(Long.parseLong(roll)<Long.parseLong(startNumber.getSt_number())){
	                            		//Update test number
		                            	updateTest(roll, program_id, entity_id,reg_test_number.getRegistration_number(), udate,cos[i]);	                            	
		                            	test_number.setSc_number(roll);
	                            	}
	                            	else{
	                            		logObj.logger.info("SC Category Test number is greater than or equal to ST category Starting test number so test number is not generated for registration number "+reg_test_number.getRegistration_number());
	                            	}	 	                            
	                            }
	                            else if(category[i].equals("ST")){	 
	                            	//Generate 6 digit Test number
	                            	roll=generateRollNum(test_number.getSt_number(),6);	 
	                            	//Update test number
	                            	updateTest(roll, program_id, entity_id,reg_test_number.getRegistration_number(), udate,cos[i]);	                            	
	                            	test_number.setSt_number(roll);
	                            }                            	
                            }                                                      
                           client.update("updateEntranceTestRollNumber", test_number);                           
                        } 
                    }
                    else {
                        compute = true;
                    }
            }
            reportfunction.updateControlReport(test_number, "T",comp);
        } catch (Exception e) {
            logObj.logger.info("Error while generating test number");
            logObj.logger.info("Exception is :" + e.getMessage());
        }
        return compute;
    }
    public String generateRollNum(String number,int length){
    	String testNum="";    	
    	int len=String.valueOf(Long.parseLong(number)).length();
    	if(len<length){
    		String pre=number.substring(0, length-len);
    		String suf=number.substring(length-len, length);    		
    		String roll="";
    		if((pre+String.valueOf(Long.parseLong(suf)+1l)).length()>length){
    			roll=pre.substring(0,pre.length()-1)+String.valueOf(Long.parseLong(suf)+1l);
    		}
    		else{
    			roll=pre+String.valueOf(Long.parseLong(suf)+1l);
    		}
    		testNum=roll;
    	}
    	else {
    		System.out.println("inside length equql");    		
    		String roll=String.valueOf(Long.parseLong(number)+1l);  	    	
    		if(String.valueOf(Long.parseLong(number)+1l).length()>length){
    			logObj.logger.info("Test number series is out of range (length greater than 6) roll number is "+roll);    			
    		}    		 	
    		testNum=roll;
    	}    	
    	return testNum;    	
    }
    public void updateTest(String num, String program_id, String entity_id,String regnum, String[] udate,String cos_value) {
        try {
            ReportInfoGetter updatetest = new ReportInfoGetter();
            updatetest.setTest_number(num);
            updatetest.setProgram_id(program_id);
            updatetest.setEntity_id(entity_id);           
            updatetest.setRegistration_number(regnum);
            updatetest.setCos_value(cos_value);
            updatetest.setStart_date(udate[0]);
            updatetest.setEnd_date(udate[1]);
            client.insert("updateRegTestNumber", updatetest);
        } catch (Exception e) {
            logObj.logger.info("Error while inserting test number for " +regnum);
            logObj.logger.info("Exception is :" + e.getMessage());
        }
    } // updateTest ends

    public static String gettingCode(String str, long maxvalue,
        int int_value_length) {
        int y = Integer.parseInt(str);
        String no_zero = "0";
        String valid_code = "";
        if (String.valueOf(maxvalue).length() <= int_value_length) {
            while (no_zero.length() <= (int_value_length - 1 - String.valueOf(maxvalue).length()))
                no_zero = no_zero + "0";
            if (String.valueOf(maxvalue).length() < int_value_length) {                
                valid_code = String.valueOf(y) + no_zero +String.valueOf(maxvalue);
            } else {             
                valid_code = String.valueOf(y) + String.valueOf(maxvalue);
            }
        } else {            
            valid_code = String.valueOf(y) + String.valueOf(maxvalue);
        }       
        return valid_code;
    } // getting_code ends
    
    @SuppressWarnings("unchecked")
    public boolean lookupcity(String university_id, String city) {
        boolean b = false;
        String maincityName = "Agra";
        try {
            ReportInfoGetter ceigLookup = new ReportInfoGetter();
            ceigLookup.setUniversity_id(university_id.substring(1, 5));
            ceigLookup.setCode("MAINCT");
            List<ReportInfoGetter> mainCity = client.queryForList("getMainCity",ceigLookup);
            for (ReportInfoGetter maincitylist : mainCity) {
                maincityName = maincitylist.getValue();
            }
            ceigLookup.setMaincity(maincityName);
            List<ReportInfoGetter> li = client.queryForList("getCity",ceigLookup);
            for (ReportInfoGetter citylist : li) {
                if (citylist.getCity().equalsIgnoreCase(city) ||
                        city.equalsIgnoreCase("AGRA")) {
                    b = true;                   
                    break;
                }
            }
        } catch (Exception e) {
            logObj.logger.info("Error while checking near city" +e.getMessage());
        }
        return b;
    } // look up city ends
    
    public String validateGenerate(String university_id, String entity_type,
        String entity_id, String program_id, String subject_code,
        String gender) {
        String result = null;
        try {
            String[] udate = reportfunction.getSessionDate(university_id);
            result = reportfunction.isUpdated(entity_id, program_id,udate);
        } catch (Exception e) {
            logObj.logger.info("Error while validating flag for " + entity_id +", " + program_id);
            logObj.logger.error("Exception is :" + e.getMessage());
        }        
        return result;
    } // validateGenerate ends here

    @SuppressWarnings("unchecked")
   
    /**
     ** method updated
     */
    public String getResetFlag(String university_id, String entity_type,
        String entity_id, String program_id, String branch_id,
        String specialization_id) {
        String[] branch_code = null;
        String[] specialization_code = null;
        String comp = null;
        try {           
            branch_code = getBranch_code(entity_id, program_id, branch_id);
            /**
             * method added
             */
            specialization_code = getSpecialization_code(entity_id, program_id,
                    branch_id, specialization_id);

            /**
             * method updated
             */
            String[] udate = reportfunction.getSessionDate(university_id);

            ReportInfoGetter reset = new ReportInfoGetter();
            reset.setProgram_id(program_id);
            reset.setOffered_by(entity_id);
            reset.setEntity_id(entity_id);
            // setting session start date and end date
            reset.setStart_date(udate[0]);
            reset.setEnd_date(udate[1]);

            for (int len = 0; len < branch_code.length; len++) {
                for (int j = len; j < specialization_code.length; j++) {
                    reset.setBranch_code(branch_code[len]);
                    reset.setSpecializationCode(specialization_code[j]);

                    /**
                     * query updated
                     */
                    List<ReportInfoGetter> li = client.queryForList("getResetFlag",
                            reset);

                    for (ReportInfoGetter resetflag : li) {
                        if (!resetflag.getFlag_status().equalsIgnoreCase(null)) {
                            if (resetflag.getFlag_status().equalsIgnoreCase("T") ||
                                    resetflag.getFlag_status()
                                                 .equalsIgnoreCase("E") ||
                                    resetflag.getFlag_status()
                                                 .equalsIgnoreCase("F")) {
                                /**
                                 * method updated
                                 */
                                reportfunction.updateControlReport(reset, "E",
                                    resetflag.getFlag_status());
                                comp = "E";
                            } else {
                                /**
                                 * method updated
                                 */
                            	System.out.println("Coming here");
                            	client.delete("deleteStudentTestNumber",
                                        reset);
                            	client.delete("deleteControlReport",
                                        reset);
                            	
//                                reportfunction.updateControlReport(reset, "R",
//                                    resetflag.getFlag_status());
//                                comp = "R";
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logObj.logger.info("Error while resetting flag value for " +
                entity_id + ", " + program_id + ", " + branch_id);
            logObj.logger.info("Exception is :" + e.getMessage());
        }

        return comp;
    } // get reset flag ends

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    private String[] getBranch_code(String entity_id, String program_id,
        String branch_id) {
        String[] branch_code = null;

        try {
            ReportInfoGetter cbranch = new ReportInfoGetter();
            // System.out.println("Program_id "+program_id+" entity_id "+entity_id+" inside branch_code");
            cbranch.setProgram_id(program_id);
            cbranch.setOffered_by(entity_id);

            if (branch_id.equalsIgnoreCase("All")) {
                /**
                 * query updated
                 */
                List<ReportInfoGetter> branchcount = client.queryForList("getBranchCount",
                        cbranch);

                for (ReportInfoGetter countbranch : branchcount) {
                    branch_code = new String[countbranch.getCount()];
                }

                /**
                 * query updated
                 */
                List<ReportInfoGetter> branchlist = client.queryForList("getBranchList",
                        cbranch);
                int len = 0;

                for (ReportInfoGetter listbranch : branchlist) {
                    System.out.println("Inside server " +
                        listbranch.getBranch_code());
                    branch_code[len] = listbranch.getBranch_code();
                    len++;
                }
            } else {
                System.out.println("Inside else of branch_code ");

                branch_code = new String[] { branch_id };
            }
        } catch (Exception e) {
            logObj.logger.info("Error while resetting flag value for " +
                e.getMessage());
        }
        return branch_code;
    }

        /**
         * method added by Devendra
         * This method get all subject code from database
         * 
         */
        @SuppressWarnings("unchecked")
        private String[] getSubjectCode(String entity_id, String program_id,
            String subject_code,String universityId) {
            String[] subjectCode =null;

            try {            	
                ReportInfoGetter reportInfo = new ReportInfoGetter();
                reportInfo.setProgram_id(program_id);
                reportInfo.setOffered_by(entity_id);
                reportInfo.setUniversity_id(universityId.substring(1, 5));
                
                if (subject_code.equalsIgnoreCase("All")) {
                    List<ReportInfoGetter> subjectCount = client.queryForList("getSubjectListCount",
                    		reportInfo);

                    for (ReportInfoGetter count : subjectCount) {                    	
                    	subjectCode = new String[count.getCount()];
                    }
                	
                    List<ReportInfoGetter> subjectList = client.queryForList("getSubjectList",reportInfo);
                    int len = 0;                   
                    for (ReportInfoGetter list : subjectList) {                    	                                              	
                        subjectCode[len] =list.getSubject_code();;
                        len++;
                    }
                } else {                   

                    subjectCode = new String[] { subject_code };
                }
            } catch (Exception e) {            	
                logObj.logger.info("Error inside GreetingServiceImpl: in method getSubjectCode::" +
                    e.getMessage());
            }            
        return subjectCode;
    } // getSubjectCode ends here

    /**
     * method added
     */
    @SuppressWarnings("unchecked")
    private String[] getSpecialization_code(String entity_id,
        String program_id, String branch_id, String specialization_id) {
        String[] specialization_code = null;

        try {
            ReportInfoGetter cspecialization = new ReportInfoGetter();
            // System.out.println("Program_id "+program_id+" entity_id "+entity_id+" inside branch_code");
            cspecialization.setProgram_id(program_id);
            cspecialization.setOffered_by(entity_id);
            cspecialization.setBranch_code(branch_id);

            if (specialization_id.equalsIgnoreCase("All")) {
                // System.out.println("If all is selected ");

                /**
                     * query added
                     */
                List<ReportInfoGetter> specializationcount = client.queryForList("getSpecializationCount",
                        cspecialization);

                for (ReportInfoGetter countspecialization : specializationcount) {
                    specialization_code = new String[countspecialization.getCountSpecialization()];
                }

                /**
                 * query added
                 */
                List<ReportInfoGetter> speciallist = client.queryForList("getSpecializationList",
                        cspecialization);
                int len = 0;

                for (ReportInfoGetter listSpec : speciallist) {
                    System.out.println("Inside server " +
                        listSpec.getSpecializationCode());
                    specialization_code[len] = listSpec.getSpecializationCode();
                    len++;
                }
            } else {
                System.out.println("Inside else of branch_code ");
                // branch_code=new
                // String[]{reportfunction.getBranch_Id(branch_name)};
                specialization_code = new String[] { specialization_id };
            }
        } catch (Exception e) {
            logObj.logger.info("Error while resetting flag value for " +
                e.getMessage());
        }

        // System.out.println("branches are "+branch_code[0]+" and "+branch_code.length);
        return specialization_code;
    } // getbranch_code ends here

    //    /**
    //     * method updated
    //     */
    //    public List<DataBean> getUpdateMarks(String university_id,
    //        String entity_type, String entity_name, String program_name,
    //        String branch_name) {
    //        return null;
    //    }

    //    @SuppressWarnings("unchecked")
    //    public List<DataBean> getAllComputedStudentList(String university_id,
    //        String entity_type, String entity_id, String program_id,
    //        String branch_id) {
    //        List<DataBean> list = new ArrayList<DataBean>();
    //        String[] branch_code = null;
    //
    //        try {
    //            branch_code = getBranch_code(entity_id, program_id, branch_id);
    //
    //            /**
    //             * method updated
    //             */
    //            String[] udate = reportfunction.getSessionDate(university_id);
    //
    //            ReportInfoGetter meritStudent = new ReportInfoGetter();
    //            meritStudent.setUniversity_id(university_id);
    //            meritStudent.setEntity_id(entity_id);
    //            meritStudent.setOffered_by(entity_id);
    //            meritStudent.setProgram_id(program_id);
    //            meritStudent.setUniversity_start_date(udate[0]);
    //            meritStudent.setUniversity_end_date(udate[1]);
    //
    //            for (int i = 0; i < branch_code.length; i++) {
    //                meritStudent.setBranch_code(branch_code[i]);
    //
    //                List<ReportInfoGetter> getStudents = client.queryForList("getInEligibleStudentMarks",
    //                        meritStudent);
    //
    //                for (ReportInfoGetter student : getStudents) {
    //                    String registrationNumber = student.getRegistration_number();
    //                    String[] udetail = reportfunction.getUserDetail(registrationNumber,
    //                            meritStudent);
    //                    String[][] studentmarks = reportfunction.getFullyComputedmarks(registrationNumber,
    //                            meritStudent, udate);
    //                    double sum_cm = student.getSum_computed_marks();
    //                    String status = student.getStatus();
    //                    String reason = student.getReason_code();
    //                    list.add(new DataBean(registrationNumber, udetail,
    //                            studentmarks, sum_cm, status, reason));
    //                }
    //            } // i=0,branch_code ends
    //        } catch (Exception e) {
    //            logObj.logger.info("Error while getting student's list");
    //            logObj.logger.info("Exception is :" + e.getMessage());
    //        }
    //
    //        return list;
    //    }
    public String uploadFile(String file) {
        ReadXLSheet xlReader = new ReadXLSheet();
        xlReader.init(file);

        return "";
    }

} // class ends
