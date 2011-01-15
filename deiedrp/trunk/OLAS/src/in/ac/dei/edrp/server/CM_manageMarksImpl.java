package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.CMaddMarksInfoGetter;
import in.ac.dei.edrp.client.GridDataBean;
import in.ac.dei.edrp.client.RPCFiles.CM_manageMarks;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("serial")
public class CM_manageMarksImpl extends RemoteServiceServlet implements
CM_manageMarks{
	Connection con=null;
	SqlMapClient client = SqlMapManager.getSqlMapClient();

Log4JInitServlet logObj=new Log4JInitServlet();
	

Idgetter id=new Idgetter();
	
	public List<GridDataBean> methodProgramList(String university_id,
			String entity_type, String entity_id, String program_id,String branch_code)throws Exception {

		String[] s=null; Integer[] total_marks = null;
	
		List<GridDataBean> list = new ArrayList<GridDataBean>();
		try {
								
			String cm = null;
			CMaddMarksInfoGetter getDataObject[] = null;
			CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();
			addMarksObject.setProgram_id(program_id);
			addMarksObject.setBranch_id(branch_code);
			addMarksObject.setEntity_id(entity_id);
			
			List<?> flagStatus = client.queryForList("checkFlagStatus",addMarksObject);			
			getDataObject=flagStatus.toArray(new CMaddMarksInfoGetter[flagStatus.size()]);
			

			if(getDataObject[0].getFlag_status().equalsIgnoreCase("E")){
				//getting number of final merit components
				List<?> noOfDescriptions = client.queryForList("countDescription",addMarksObject);
				getDataObject=noOfDescriptions.toArray(new CMaddMarksInfoGetter[noOfDescriptions.size()]);
			
				s = new String[Integer.parseInt(getDataObject[0].getDescription())];
				total_marks=new Integer[Integer.parseInt(getDataObject[0].getDescription())];
			
				//getting description and maximum marks for final merit components
				List<?> getDescriptions = client.queryForList("getCompDescription",addMarksObject);
				getDataObject=getDescriptions.toArray(new CMaddMarksInfoGetter[getDescriptions.size()]);
				
				for(int i=0;i<getDataObject.length;i++){
					s[i] = getDataObject[i].getDescription();
					total_marks[i]=getDataObject[i].getTotal_marks();
				}

				list.add(new GridDataBean("Registration","Test Number" ,"call Merit", s,total_marks,
						"TotalMarks"));
				
				
				List<?> AllStudents = client.queryForList("getAllStudentCallMeritMarks",addMarksObject);
				getDataObject=AllStudents.toArray(new CMaddMarksInfoGetter[AllStudents.size()]);
				
				for(int i=0;i<getDataObject.length;i++){

					String name = getDataObject[i].getRegistration_number();
					
					
					/*
					 * to be changed to ibatis
					 */
					
					if(id.validateRegistrationNumber(entity_id,program_id,branch_code,getDataObject[i].getRegistration_number())){
					String lens[] = new String[s.length];
					int sum = 0;

						
					cm=getDataObject[i].getSum_actual_computed_marks();
				
					if(cm==null){
						cm="0";
					}
					list.add(new GridDataBean(name, getDataObject[i].getTest_number(),cm, lens, String
							.valueOf((sum + Double.parseDouble(cm)))));
					}
					
				
				}
				
				
			}else if(getDataObject[0].getFlag_status().equalsIgnoreCase("F")){
				throw new Exception("Final list has already been generated");
			}else if(getDataObject[0].getFlag_status().equalsIgnoreCase("T"))
			{
				throw new Exception("List with test numbers not generated yet");
			}
			else
			{
				throw new Exception("Test numbers have not been generated yet");
			}
			
	

		} catch (Exception e) {
			logObj.logger.error(e);
			throw new Exception(e);
		}// catch ends
		
		
		return list;
	}
	
	
	
	/*
	 * for getting total edit marks
	 */
	public String[][] addMarks(String regnumber,String program_id ,String entity_id,int len,Connection con) {

		String[][] call = new String[len][2];
		try {
			CMaddMarksInfoGetter getDataObject[] = null;
			CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();
			addMarksObject.setProgram_id(program_id);
			addMarksObject.setRegistration_number(regnumber);
			addMarksObject.setEntity_id(entity_id);
			
			List<?> marksAttandance = client.queryForList("getMarksAndAttandanceForEdit",addMarksObject);
			getDataObject=marksAttandance.toArray(new CMaddMarksInfoGetter[marksAttandance.size()]);
//			PreparedStatement ps = con
//					.prepareStatement("select marks,attended from STUDENT_FINAL_MARKS where program_id=? AND entity_id=? and registration_number=?");
//			ps.setString(1, program_id);
//			ps.setString(2, entity_id);
//			ps.setString(3, regnumber);
//			ResultSet rs = ps.executeQuery();
			
			
			
			int j=0;
			
			for(int i=0;i<getDataObject.length;i++){
				if(getDataObject[i].getMarks()==null){
					call[i][0]="0";
					call[i][1]="A";
					}
				else{
					call[i][0]=getDataObject[i].getMarks();
					call[i][1]=getDataObject[i].getAttended();
				}
				
				j++;
				
			}
			if(j<len){
				while(j<len){
					call[j][0]="0";
					call[j][1]="A";
					j++;
				}
			}
		} catch (Exception e) {
			logObj.logger.error("Error message in addMarks" + e.getMessage());
		}

		return call;

	}
	
	/*
	 * Method for getting component ids
	 */
	public String getComponent(String description,String entity_id,String program_id,String branch_code,Connection con){
		String s=null;
		try{

			CMaddMarksInfoGetter getDataObject[] = null;
			CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();
			addMarksObject.setProgram_id(program_id);
			addMarksObject.setBranch_id(branch_code);
			addMarksObject.setEntity_id(entity_id);
			
			List<?> compId = client.queryForList("getCompId",addMarksObject);
			getDataObject=compId.toArray(new CMaddMarksInfoGetter[compId.size()]);
			
		s=getDataObject[0].getComponent_id();
		}catch(Exception e){
			logObj.logger.error("Error mesage is "+e.getMessage());
		}
		return s;
	}
	
	
	
	
	public void methodAddFinalMarks(String user_id,String entity_id,String prog_id,String branch_id,
			String reg_no,String testnumber,String callMerit,String[] evalComp,String[] markslist, String[] attList){
//		String message="Marks have already been added!!!";
		Idgetter id=new Idgetter();
		Float total=Float.parseFloat(callMerit);
   		
		String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                .substring(0,
19));
		try {
			
			CMaddMarksInfoGetter getDataObject[] = null;
			CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();
			addMarksObject.setUniversity_id( prog_id.substring(0,4));
			
			List<?> sessiondates = client.queryForList("getSessionDates",addMarksObject);
			getDataObject=sessiondates.toArray(new CMaddMarksInfoGetter[sessiondates.size()]);
			
					
			addMarksObject.setProgram_id(prog_id);
			addMarksObject.setBranch_id(branch_id);
			addMarksObject.setEntity_id(entity_id);
			addMarksObject.setRegistration_number(reg_no);
			addMarksObject.setInsert_time(date);
			addMarksObject.setCreator_id(user_id);
			addMarksObject.setStart_date(getDataObject[0].getStart_date());
			addMarksObject.setEnd_date(getDataObject[0].getEnd_date());
			addMarksObject.setModification_time(null);
			addMarksObject.setModifier_id(null);
			
			for (int i=0;i<markslist.length;i++){
			addMarksObject.setComponent_id(getComponent(evalComp[i],entity_id,prog_id,branch_id, con));
			addMarksObject.setMarks(markslist[i]);
			addMarksObject.setAttended(attList[i]);		
			client.insert("insertStudentFinalMarks1",addMarksObject);				
			total=total+Float.parseFloat(markslist[i]);
			
			}
			/*
			 * to be changed to iBatis
			 */
			String[] values=id.getTestCosForReg(entity_id,prog_id,branch_id,reg_no,con);
			
			addMarksObject.setTest_number(testnumber);
			
			
			/**
			 * to be checked for error as total_marks was going as 0
			 */
			
			addMarksObject.setFinal_marks(total);
			addMarksObject.setCos_value(values[1]);
			client.insert("insertStudentFinalMeritList1",addMarksObject);
			
			
			
		}catch(Exception e){
			logObj.logger.error("Coming Exception in methodAddFinalMarks on server "+e);
		}
		
	}
	
	
	
	

	public List<GridDataBean> methodEditGridDataList(String university_id,
			String entity_id, String program_id,String branch_id, String crieteria_value, String search_value) throws Exception{
		
		List<GridDataBean> list=new ArrayList<GridDataBean>();
		String s[]=null;
		Integer max[]=null;
//		Idgetter id=new Idgetter();
		try{
//			con=id.createConnection();
			
			CMaddMarksInfoGetter getDataObject[] = null;
			CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();
			addMarksObject.setProgram_id(program_id);
			addMarksObject.setBranch_id(branch_id);
			addMarksObject.setEntity_id(entity_id);
			
			List<?> flagStatus = client.queryForList("checkFlagStatus",addMarksObject);			
			getDataObject=flagStatus.toArray(new CMaddMarksInfoGetter[flagStatus.size()]);

			if(getDataObject[0].getFlag_status().equalsIgnoreCase("E")){
			
				List<?> noOfDescriptions = client.queryForList("countDescription",addMarksObject);
				getDataObject=noOfDescriptions.toArray(new CMaddMarksInfoGetter[noOfDescriptions.size()]);
			
				s = new String[Integer.parseInt(getDataObject[0].getDescription())];
			max = new Integer[Integer.parseInt(getDataObject[0].getDescription())];
			
			
			List<?> getDescriptions = client.queryForList("getCompDescription",addMarksObject);
			getDataObject=getDescriptions.toArray(new CMaddMarksInfoGetter[getDescriptions.size()]);
			
			for(int i=0;i<getDataObject.length;i++){
				s[i] = getDataObject[i].getDescription();
				max[i]=getDataObject[i].getTotal_marks();
			}

			list.add(new GridDataBean("Registration","Test Number" ,"call Merit", s,max,
					"Total Marks"));
	
		
		if(crieteria_value==null){
			crieteria_value="no";
		}
		
		List<?> studentData=null;
		
		if(crieteria_value.equalsIgnoreCase("Registration Number")){
			
			addMarksObject.setRegistration_number(search_value+"%");
		studentData=client.queryForList("finalMarksDetailsWithRegNo",addMarksObject);
		}else if(crieteria_value.equalsIgnoreCase("Test Number")){
			addMarksObject.setTest_number(search_value+"%");
			studentData=client.queryForList("finalMarksDetailsWithTestNo",addMarksObject);
		}else{
			addMarksObject.setRegistration_number("%");
			studentData=client.queryForList("finalMarksDetailsWithRegNo",addMarksObject);
		}
		
		getDataObject=studentData.toArray(new CMaddMarksInfoGetter[studentData.size()]);
//		s.registration_number,st.sum_actual_computed_marks,s.test_number,s.total_marks 
		for(int i=0;i<getDataObject.length;i++){
			String[][] sam=addMarks(getDataObject[0].getRegistration_number(),program_id,entity_id,s.length,con);
					
			list.add(new GridDataBean(getDataObject[0].getRegistration_number(),getDataObject[0].getTest_number(),getDataObject[0].getSum_actual_computed_marks(), sam, getDataObject[0].getMarks()));
		}
			}else if(getDataObject[0].getFlag_status().equalsIgnoreCase("F")){
				throw new Exception("Final list has already been generated");
			}else
			{
				throw new Exception("Test numbers have not been generated yet");
			}
		}catch(Exception e){
			logObj.logger.error(e);
			throw new Exception(e);
		}
		return list;
	}
	
	
	
	
	
	public void methodEditFinalMarks(String user_id,String entity_id,String prog_id,String branch_id,
			String reg_no,String testnumber,String callMerit,String[] evalComp,String[] markslist, String[] attList){
//		String message="Marks have already been added!!!";
//		Idgetter id=new Idgetter();
		Float total=Float.parseFloat(callMerit);

		String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                .substring(0,
19));
		try {			
			CMaddMarksInfoGetter editMarksObject = new CMaddMarksInfoGetter();
			
			editMarksObject.setProgram_id(prog_id);
			editMarksObject.setBranch_id(branch_id);
			editMarksObject.setEntity_id(entity_id);
			editMarksObject.setRegistration_number(reg_no);
			editMarksObject.setModification_time(date);
			editMarksObject.setModifier_id(user_id);
			
			for (int i=0;i<markslist.length;i++){
			editMarksObject.setComponent_id(getComponent(evalComp[i],entity_id,prog_id,branch_id, con));
			editMarksObject.setMarks(markslist[i]);
			editMarksObject.setAttended(attList[i]);		
			client.update("updateStudentFinalMarks1",editMarksObject);				
			total=total+Float.parseFloat(markslist[i]);
			
			}
			
			editMarksObject.setTest_number(testnumber);			
			
			/**
			 * to be checked for error as total_marks was going as 0
			 */
			
			editMarksObject.setFinal_marks(total);

			client.update("updateStudentFinalMeritList1",editMarksObject);	
			
		

			
		}catch(Exception e){
			logObj.logger.error("Coming Exception in methodAddFinalMarks on server "+e);
		}
		
	}
	
	
	
	
	public String[] methodPopulateSuggestion(String criteria,String program_id,String branch_id,String entity_id)
	{
		String[] arr = null;
		try{
			CMaddMarksInfoGetter getDataObject[] = null;	
	CMaddMarksInfoGetter editMarksObject = new CMaddMarksInfoGetter();
			
			editMarksObject.setProgram_id(program_id);
			editMarksObject.setBranch_id(branch_id);
			editMarksObject.setEntity_id(entity_id);
			List<?> getList=null;
			if(criteria.equalsIgnoreCase("Registration Number")){
				getList=client.queryForList("distinctRegNo", editMarksObject);
			}
			else{
				getList=client.queryForList("distinctTestNo", editMarksObject);
				}
		
			getDataObject=getList.toArray(new CMaddMarksInfoGetter[getList.size()]);
			
			
			arr=new String[getList.size()];
			
						
			for (int i=0;i<getDataObject.length;i++){
				arr[i]=new String();
				arr[i]=getDataObject[i].getRegistration_number();				
			}
			
		}catch (Exception e) {
			logObj.logger.error("Exception in methodPopulateSuggestion on server "+e);
		}
		
		return arr;
	}
	
	
	
	public CM_entityInfoGetter[] methodEntityListEdit(String user_id,String entity_type)throws Exception
	{
		try
	{
			CM_entityInfoGetter em=new CM_entityInfoGetter();
			
		em.setEntity_id(user_id.substring(1,5)+"%");
		em.setEntity_type(entity_type);

		List<?> li = null;

	 li=client.queryForList("entityListmarks",em);

	return li.toArray(new CM_entityInfoGetter[li.size()]);
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	} 
	}
	
	
	public CM_progMasterInfoGetter[] methodProgListEdit(String entity_id)throws Exception
	{
		try
	{

			CM_progMasterInfoGetter pm=new CM_progMasterInfoGetter();
			pm.setProgram_id(entity_id);
		List<?> li = null;

	 li=client.queryForList("programListmarks",pm);

	return li.toArray(new CM_progMasterInfoGetter[li.size()]);
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	} 
	}

	public CM_progMasterInfoGetter[] methodBranchListEdit(String entity_id,String prog_id)throws Exception
	{
		try
	{
			CM_progMasterInfoGetter pm=new CM_progMasterInfoGetter();
			pm.setProgram_id(prog_id);
			pm.setProgram_code(entity_id);

		List<?> li = null;

	 li=client.queryForList("branchListmarks",pm);

	return li.toArray(new CM_progMasterInfoGetter[li.size()]);
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	} 
	}
	
	
	public CM_progMasterInfoGetter[] methodBranchListAdd(String entity_id,String prog_id)throws Exception
	{
		try
	{
			CM_progMasterInfoGetter pm=new CM_progMasterInfoGetter();
			pm.setProgram_id(prog_id);
			pm.setProgram_code(entity_id);

		List<?> li = null;

	 li=client.queryForList("branchListmarksforadd",pm);

	return li.toArray(new CM_progMasterInfoGetter[li.size()]);
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	} 
	}

	public CM_progMasterInfoGetter[] methodProgListAdd(String entity_id)throws Exception
	{
		try
	{

			CM_progMasterInfoGetter pm=new CM_progMasterInfoGetter();
			pm.setProgram_id(entity_id);
		List<?> li = null;

	 li=client.queryForList("programListmarksforadd",pm);

	return li.toArray(new CM_progMasterInfoGetter[li.size()]);
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	} 
	}
	
	
	
}
