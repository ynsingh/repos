package in.ac.dei.edrp.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;


import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.SetupTRDataService;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

public class SetupTRDataServiceImpl extends RemoteServiceServlet implements SetupTRDataService
{
	 SqlMapClient client = SqlMapManager.getSqlMapClient();
	    Log4JInitServlet logObj = new Log4JInitServlet();

	
	public CM_ProgramInfoGetter[] getPrograms(String userID) {
		List<CM_ProgramInfoGetter[]> programList=new ArrayList<CM_ProgramInfoGetter[]>();
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		try
		{
		programList=client.queryForList("getPrograms_TR", cmpig);
		return (CM_ProgramInfoGetter[])programList.toArray(new CM_ProgramInfoGetter[programList.size()]);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in getting Program for Tie Rule" + ex);
		}
		return null;
	}

	
	public CM_ProgramInfoGetter[] getComponents(String userID, String programID) {
		List<CM_ProgramInfoGetter[]> componentList=new ArrayList<CM_ProgramInfoGetter[]>();
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		cmpig.setProgram_id(programID);
		try
		{
			componentList=client.queryForList("getComponents_TR", cmpig);
		return (CM_ProgramInfoGetter[])componentList.toArray(new CM_ProgramInfoGetter[componentList.size()]);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in getting Components for Tie Rule" + ex);
		}
		return null;
	}

	
	public CM_ProgramInfoGetter[] getSubComponents(String userID,
			String programID, String compID) {
		List<CM_ProgramInfoGetter[]> subComponentList=new ArrayList<CM_ProgramInfoGetter[]>();
	 	Integer i=null;
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		cmpig.setProgram_id(programID);
		cmpig.setComponent_id(compID);
		try
		{
			i=(Integer)client.queryForObject("getMRTCOM_TR", cmpig);
			if(i<=0)
			{
				return null;
			}
			else
			{
				subComponentList=client.queryForList("getSubComp_TR", cmpig);
				return (CM_ProgramInfoGetter[])subComponentList.toArray(new CM_ProgramInfoGetter[subComponentList.size()]);	
			}
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in getting Sub Components for Tie Rule" + ex);
		}
		return null;
	}

	
	public CM_ProgramInfoGetter[] getCalculationBasis(String userID) {
		List<CM_ProgramInfoGetter[]> calBaseList=new ArrayList<CM_ProgramInfoGetter[]>();
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		try
		{
			calBaseList=client.queryForList("getCalBase_TR", cmpig);
		return (CM_ProgramInfoGetter[])calBaseList.toArray(new CM_ProgramInfoGetter[calBaseList.size()]);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in getting Calculation Basis for Tie Rule" + ex);	
		}
		return null;
	
	}

	
	public CM_ProgramInfoGetter[] getLogic(String userID) {
		
		List<CM_ProgramInfoGetter[]> logicList=new ArrayList<CM_ProgramInfoGetter[]>();
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		try
		{
			logicList=client.queryForList("getLogics_TR", cmpig);
		return (CM_ProgramInfoGetter[])logicList.toArray(new CM_ProgramInfoGetter[logicList.size()]);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in getting Logic for Tie Rule" + ex);
		}
		return null;
	}


	
	public void insertTRDetails(String userID, String programID, String compID,
			String paperCode, String calBasis, String logic, String sequenceNo) {
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		cmpig.setProgram_id(programID);
		cmpig.setComponent(compID);
		cmpig.setPaper_code(paperCode);
		cmpig.setCal_basis(calBasis);
		cmpig.setLogic(logic);
		cmpig.setSequence(sequenceNo);
		cmpig.setCreator_id(userID);
		
		try
		{
			client.insert("insert_TR", cmpig);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in inserting Tie Rule Record" + ex);
		}
		
		
	}


	
	public Integer checkTR(String userID, String programID,
			String compID, String paperCode,String calBasis) {
		Integer i=null;
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		cmpig.setProgram_id(programID);
		cmpig.setComponent(compID);
		cmpig.setPaper_code(paperCode);
		cmpig.setCal_basis(calBasis);
		
		try
		{
			if(cmpig.getPaper_code()==null)
			{
				i=(Integer)client.queryForObject("getTR_WithPC_Null", cmpig);
			}
			else
			{
			i=(Integer)client.queryForObject("getTR", cmpig);
			}
		return i;
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in checking Tie Rule Records" + ex);	
		}
		return null;
	}


	
	public Integer checkSequenceNumber(String userID, String programID,
			String compID, String paperCode, String sequence,String calBasis) {
		Integer i=null;
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		cmpig.setProgram_id(programID);
		cmpig.setComponent(compID);
		cmpig.setPaper_code(paperCode);
		cmpig.setSequence(sequence);
		cmpig.setCal_basis(calBasis);
		
		try
		{
			if(cmpig.getPaper_code()==null)
			{
				i=(Integer)client.queryForObject("checkSequence_WithPC_Null", cmpig);
			}
			else
			{
			i=(Integer)client.queryForObject("checkSequence", cmpig);
			}
		return i;
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in checking Sequence Number" + ex);
		}
		return null;
	}


	
	public CM_ProgramInfoGetter[] getTR_DetailsWithoutProgram(String userID) {
		List<CM_ProgramInfoGetter[]> TR_List=new ArrayList<CM_ProgramInfoGetter[]>();
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		try
		{
			TR_List=client.queryForList("getTR_Details", cmpig);
		return (CM_ProgramInfoGetter[])TR_List.toArray(new CM_ProgramInfoGetter[TR_List.size()]);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in getting Tie Rule Details" + ex);
		}
		return null;
	}


	
	public Integer deleteTR(String userID, String[] param) {
		Integer i=null;
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		cmpig.setProgram_id(param[0]);
		cmpig.setComponent_id(param[1]);
		cmpig.setPaper_code(param[2]);	
		cmpig.setCal_basis(param[3]);
		cmpig.setLogic(param[4]);
		System.out.println(param[0]+param[1]+param[2]);
		try
		{
			if(cmpig.getPaper_code()==null)
			{
				i=client.delete("deleteTR_WithPC_Null", cmpig);	
			}
			else
			{
			i=client.delete("deleteTR", cmpig);
			}
		}
		catch(Exception ex)
		{
			logObj.logger.error(" Exception in deleting Tie Rule" + ex);
		}
		return i;
	}


	
	public CM_ProgramInfoGetter[] getPrograms_FromTR(String userID) {
		List<CM_ProgramInfoGetter[]> programList=new ArrayList<CM_ProgramInfoGetter[]>();
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		try
		{
		programList=client.queryForList("getPrograms_FromTR", cmpig);
		return (CM_ProgramInfoGetter[])programList.toArray(new CM_ProgramInfoGetter[programList.size()]);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in getting Program from Tie Rule" + ex);	
		}
		return null;
	}


	
	public CM_ProgramInfoGetter[] getTR_DetailsWithProgram(String userID,
			String programID) {
		List<CM_ProgramInfoGetter[]> TR_List=new ArrayList<CM_ProgramInfoGetter[]>();
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		cmpig.setProgram_id(programID);
		try
		{
			TR_List=client.queryForList("getTR_Details_With_Program", cmpig);
		return (CM_ProgramInfoGetter[])TR_List.toArray(new CM_ProgramInfoGetter[TR_List.size()]);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in getting Tie Rule with Program" + ex);	
		}
		return null;
	}

	
	
	public CM_ProgramInfoGetter[] getUserDetails(String userID) {
		List<CM_ProgramInfoGetter[]> userList=new ArrayList<CM_ProgramInfoGetter[]>();
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		
		try
		{
			userList=client.queryForList("User_List", cmpig);
		return (CM_ProgramInfoGetter[])userList.toArray(new CM_ProgramInfoGetter[userList.size()]);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in getting Users" + ex);	
		}
		return null;
	}


	
	public CM_ProgramInfoGetter[] getFormDetails(String userID,String user_id) {
		List<CM_ProgramInfoGetter[]> formList=new ArrayList<CM_ProgramInfoGetter[]>();
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		cmpig.setUserID(user_id);
		try
		{
			formList=client.queryForList("Form_List", cmpig);
		return (CM_ProgramInfoGetter[])formList.toArray(new CM_ProgramInfoGetter[formList.size()]);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in getting Forms" + ex);	
		}
		return null;
	}


	
	public void setFormAuthority(String userID, String formNumber) {
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUserID(userID);
		cmpig.setFormID(formNumber);
		
		try
		{
			client.insert("SetFRMAuthority", cmpig);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in Setting Authority" + ex);	
		}
		
	}


	
	public CM_ProgramInfoGetter[] getFormAuthorityDetails(String userID) {
		List<CM_ProgramInfoGetter[]> FRMAuthorityList=new ArrayList<CM_ProgramInfoGetter[]>();
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		
		try
		{
			FRMAuthorityList=client.queryForList("FRMAuthorityDetails", cmpig);
		return (CM_ProgramInfoGetter[])FRMAuthorityList.toArray(new CM_ProgramInfoGetter[FRMAuthorityList.size()]);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in getting Form Authority Details" + ex);	
		}
		return null;
	}


	
	public Integer deleteFRMAuthority(String[] param) {
		Integer i=null;
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUserID(param[0]);
		cmpig.setFormID(param[1]);
		try
		{
			
			
			i=client.delete("deleteFRMAuthority", cmpig);
		
		}
		catch(Exception ex)
		{
			logObj.logger.error(" Exception in deleting Form Authority" + ex);
		}
		return i;
	}
}
