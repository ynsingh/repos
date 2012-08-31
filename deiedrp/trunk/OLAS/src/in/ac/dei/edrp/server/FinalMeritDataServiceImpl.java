package in.ac.dei.edrp.server;

import java.util.ArrayList;
import java.util.List;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.FinalMeritDataService;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;

public class FinalMeritDataServiceImpl extends RemoteServiceServlet implements FinalMeritDataService
{
	 SqlMapClient client = SqlMapManager.getSqlMapClient();
	    Log4JInitServlet logObj = new Log4JInitServlet();
	
	public CM_ProgramInfoGetter[] getEntityTypes(String userID) {
		List<CM_ProgramInfoGetter> entityTypeList=new ArrayList<CM_ProgramInfoGetter>();
		String university_id=userID.substring(1,5);
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(university_id);
		
		try
		{
			entityTypeList=client.queryForList("getEntityType_FMC",cmpig);
			return (CM_ProgramInfoGetter[]) entityTypeList.toArray(new CM_ProgramInfoGetter[entityTypeList.size()]);
		}
		catch(Exception e)
		{
			logObj.logger.error("Exception in getEntityTypes" + e);
		}
		return (CM_ProgramInfoGetter[]) entityTypeList.toArray(new CM_ProgramInfoGetter[entityTypeList.size()]);
	}

	
	public CM_ProgramInfoGetter[] getEntityNames(String userID,
			String entityType) {
		List<CM_ProgramInfoGetter> entityNameList=new ArrayList<CM_ProgramInfoGetter>();
		String university_id=userID.substring(1,5);
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(university_id);
		cmpig.setEntity_type(entityType);
		try
		{
			entityNameList=client.queryForList("getEntityName_FMC",cmpig);
			return (CM_ProgramInfoGetter[]) entityNameList.toArray(new CM_ProgramInfoGetter[entityNameList.size()]);
		}
		catch(Exception e)
		{
			logObj.logger.error("Exception in getEntityNames" + e);
		}
		return (CM_ProgramInfoGetter[]) entityNameList.toArray(new CM_ProgramInfoGetter[entityNameList.size()]);
	}


	
	public CM_ProgramInfoGetter[] getProgramNames(String userID, String entityID) {
		List<CM_ProgramInfoGetter> programList=new ArrayList<CM_ProgramInfoGetter>();
		String university_id=userID.substring(1,5);
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(university_id);
		cmpig.setEntity_id(entityID);
		try
		{
			programList=client.queryForList("getEntityProgram_FMC",cmpig);
			return (CM_ProgramInfoGetter[]) programList.toArray(new CM_ProgramInfoGetter[programList.size()]);
		}
		catch(Exception e)
		{
			logObj.logger.error("Exception in getEntityPrograms" + e);
		}
		return (CM_ProgramInfoGetter[])programList.toArray(new CM_ProgramInfoGetter[programList.size()]);
	}


	
	public CM_ProgramInfoGetter[] getFinalMeritComponents(String userID,String programID) {
		List<CM_ProgramInfoGetter> componentList=new ArrayList<CM_ProgramInfoGetter>();
		String university_id=userID.substring(1,5);
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(university_id);
		cmpig.setProgram_id(programID);
		try
		{
			componentList=client.queryForList("getComponent_FMC",cmpig);
			return (CM_ProgramInfoGetter[]) componentList.toArray(new CM_ProgramInfoGetter[componentList.size()]);
		}
		catch(Exception e)
		{
			logObj.logger.error("Exception in getComponents" + e);
		}
		return (CM_ProgramInfoGetter[]) componentList.toArray(new CM_ProgramInfoGetter[componentList.size()]);
	}


	
	public void insertFinalMeritComponent(String programID, String entityID,
			String componentID, String attendanceImpact, String total_marks,
			String userID,String weightagePercentage,String academicImpact) {
		String componentDescription=null;
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(userID.substring(1, 5));
		cmpig.setProgram_id(programID);
		cmpig.setEntity_id(entityID);
		cmpig.setComponent_id(componentID);
		cmpig.setAvailable(attendanceImpact);
		cmpig.setTotal_marks(Integer.parseInt(total_marks));
		
		try
		{
		componentDescription=client.queryForObject("getComponent_Name", cmpig).toString();
		cmpig.setComponentDescription(componentDescription);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in checking Description" + ex);	
		}
		
		if(componentID.trim().equalsIgnoreCase("AS"))
		{
		cmpig.setWeightage(0.0F);
		}
		else
		{
			cmpig.setWeightage(Float.parseFloat(weightagePercentage));
		}
		//cmpig.setAcademicImpact(academicImpact);
		cmpig.setCreator_id(userID);
		try
		{
		Integer i=(Integer)client.queryForObject("compareComponent", cmpig);	
		if(i==0)
		{
			cmpig.setAcademicImpact("Y");	
		}
		else
		{
			if(componentID.trim().equalsIgnoreCase("AS"))
			{
				cmpig.setAcademicImpact("Y");	
			}
			else
			{
				cmpig.setAcademicImpact("N");	
			}
			
		}
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in checking Component" + ex);
		}
		try
		{
			client.insert("insert_FMC", cmpig);
		}
		catch(Exception ex)
		{
			logObj.logger.error("Exception in inserting Final Merit Component" + ex);
		}
	}


	
	public Boolean checkFMCDetails(String programID, String entityID,
			String componentID) {
		Boolean valueExists=false;
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setProgram_id(programID);
		cmpig.setEntity_id(entityID);
		cmpig.setComponent_id(componentID);
		try
		{
			Integer size=(Integer)client.queryForObject("checkFMC", cmpig);
			if(size==0)
			{
				valueExists=false;
			}
			else
			{
				valueExists=true;	
			}
		}
		catch(Exception e)
		{
			logObj.logger.error("Exception in checking FMC" + e);
		}
		return valueExists;
	}


	
	public CM_ProgramInfoGetter[] getFMCDetails(String userID, String entityID) {
		List<CM_ProgramInfoGetter> FMCList=new ArrayList<CM_ProgramInfoGetter>();
		String university_id=userID.substring(1,5);
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(university_id);
		cmpig.setEntity_id(entityID);
		
		try
		{
			FMCList=client.queryForList("getFMC_Details",cmpig);
			return (CM_ProgramInfoGetter[]) FMCList.toArray(new CM_ProgramInfoGetter[FMCList.size()]);
		}
		catch(Exception e)
		{
			logObj.logger.error("Exception in get FMC Details" + e);
		}
		return (CM_ProgramInfoGetter[]) FMCList.toArray(new CM_ProgramInfoGetter[FMCList.size()]);
	}


	
	public Integer deleteFMCDetails(String[] param) {
		Integer i=null;
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		
		cmpig.setEntity_id(param[0]);
		cmpig.setProgram_id(param[1]);
		cmpig.setComponent_id(param[2]);
		System.out.println(param[0]+param[1]+param[2]);
		try
		{
			i=client.delete("deleteFMC", cmpig);
		}
		catch(Exception ex)
		{
			logObj.logger.error(" Exception in getCategories in callcutoff.xml id=getCategories" + ex);
		}
		return i;
	}


	
	public CM_ProgramInfoGetter[] getFMCDetailsWithoutEntityType(String userID) {
		List<CM_ProgramInfoGetter> FMCList=new ArrayList<CM_ProgramInfoGetter>();
		String university_id=userID.substring(1,5);
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(university_id);
		
		
		try
		{
			FMCList=client.queryForList("getFMC_DetailsWithoutET",cmpig);
			return (CM_ProgramInfoGetter[]) FMCList.toArray(new CM_ProgramInfoGetter[FMCList.size()]);
		}
		catch(Exception e)
		{
			logObj.logger.error("Exception in get FMC Details" + e);
		}
		return (CM_ProgramInfoGetter[]) FMCList.toArray(new CM_ProgramInfoGetter[FMCList.size()]);
	}


	
	public CM_ProgramInfoGetter[] getFMCDetailsWithEntityType(String userID,
			String entityType) {
		
		List<CM_ProgramInfoGetter> FMCList=new ArrayList<CM_ProgramInfoGetter>();
		String university_id=userID.substring(1,5);
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(university_id);
		cmpig.setEntity_type(entityType);
		
		try
		{
			FMCList=client.queryForList("getFMC_DetailsWithET",cmpig);
			return (CM_ProgramInfoGetter[]) FMCList.toArray(new CM_ProgramInfoGetter[FMCList.size()]);
		}
		catch(Exception e)
		{
			logObj.logger.error("Exception in get FMC Details" + e);
		}
		return (CM_ProgramInfoGetter[]) FMCList.toArray(new CM_ProgramInfoGetter[FMCList.size()]);
	}


	
	public Integer updateFMC(String entityID, String programID, String compID,
			String attendanceImpact, String totalMarks,
			String weightagePercentage) {
		Integer i=null;
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		
		cmpig.setEntity_id(entityID);
		cmpig.setProgram_id(programID);
		cmpig.setComponent_id(compID);
		cmpig.setAttendanceImpact(attendanceImpact);
		if(compID.trim().equalsIgnoreCase("AS"))
		{
			cmpig.setWeightage(0.0F);	
		}
		else
		{
			cmpig.setWeightage(Float.parseFloat(weightagePercentage));
		}
		cmpig.setTotal_marks(Integer.parseInt(totalMarks));
		
		try
		{
			client.update("updateFMC", cmpig);
		}
		catch(Exception ex)
		{
			logObj.logger.error(" Exception in Updating Final Merit Component" + ex);
		}
		return i;
	}

}
