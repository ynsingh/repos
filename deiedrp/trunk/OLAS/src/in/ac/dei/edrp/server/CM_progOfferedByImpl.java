package in.ac.dei.edrp.server;

import java.util.List;

import in.ac.dei.edrp.client.CM_BranchSpecializationInfoGetter;
import in.ac.dei.edrp.client.CM_employeeInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectProgOfferedBy;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;


//import java.util.*;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CM_progOfferedByImpl extends RemoteServiceServlet implements CM_connectProgOfferedBy {
	
	   SqlMapClient client = SqlMapManager.getSqlMapClient();
	    Log4JInitServlet logObj = new Log4JInitServlet();
	    
	public CM_progMasterInfoGetter[] methodProgSpecList(CM_progMasterInfoGetter object)throws Exception
	{
		try
	{
			List<?> li = null;
	
		 li=client.queryForList("specList1",object);
		
		return li.toArray(new CM_progMasterInfoGetter[li.size()]);
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
	}
	
	
	public CM_progMasterInfoGetter[] methodBranchSpecList(CM_progMasterInfoGetter object, String spec)throws Exception
	{
		try
	{
			List<?> li = null;
		
			if(spec.equals("none")){
				 li=client.queryForList("specBranchNullList",object);
			}else{
		 li=client.queryForList("specBranchList",object);
			}
		return li.toArray(new CM_progMasterInfoGetter[li.size()]);
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
	}
	
	
	
	
	public CM_BranchSpecializationInfoGetter[] methodMentorPopulate(String user_id)
	throws Exception {
try {
   
	List<?> li = null;
	String university_id=user_id.substring(1,5);
	String string="E"+university_id+"%";
	
	//selectMentorName query is to be changed on basis of entity_id later (1 Sep,2010)
	li = client.queryForList("selectMentorName", string);
	
    return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);

} catch (Exception e) {
	logObj.logger.error(e);
    throw new Exception(e);
}

}
	
	

	
	public CM_employeeInfoGetter[] methodLocationPopulate()
	throws Exception {
try {
   
	List<?> li = null;
	//table to be altered and query to be changed (1 Sep,2010)
	li = client.queryForList("selectLocationName", null);
	
    return (CM_employeeInfoGetter[]) li.toArray(new CM_employeeInfoGetter[li.size()]);

} catch (Exception e) {
	logObj.logger.error(e);
    throw new Exception(e);
}

}
	
	

	
	
	public CM_BranchSpecializationInfoGetter[] methodProgramOfferedByPopulate()
	throws Exception {
try {
   
	List<?> li = null;
	
	li = client.queryForList("selectProgramOfferedByName", null);
	
    return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);

} catch (Exception e) {
	logObj.logger.error(e);
    throw new Exception(e);
}

}
	
	
	
	
	public CM_BranchSpecializationInfoGetter[] methodAssignProgramsToEntity(String programName, String[] branchName,
			String[] specializationName, String collegeCenter, String location,
			String seats, String mentor, String user_id) throws Exception {
	
    	List<?> l4;
        
    	try {
            	
            	            	
            	l4 = client.queryForList("selectEntityID", collegeCenter);
            	CM_BranchSpecializationInfoGetter[] offeringEntity =
            		(CM_BranchSpecializationInfoGetter[]) l4.toArray(new CM_BranchSpecializationInfoGetter[l4.size()]);
            	String offered_by = offeringEntity[0].getEntity_id();

            	
            	String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                        .substring(0,
    19));
            	
            	
            	CM_BranchSpecializationInfoGetter insert = new CM_BranchSpecializationInfoGetter();
            	
            	for(int i=0;i<branchName.length;i++){
            	
                insert.setProgram_id(programName);
                insert.setBranch(branchName[i]);
                insert.setSpecialization(specializationName[i]);
                insert.setOffered_by(offered_by);
                insert.setMentor(mentor);
                insert.setSeats(seats);
                insert.setLocation_id(location);
                
                insert.setInsert_time(date);
                insert.setCreator_id(user_id);
                insert.setModification_time(null);
                insert.setModifier_id(null);
                
                

                client.insert("insertNewProgramOfferedBy", insert);
            	}
              
            } catch (Exception ex) {
            	logObj.logger.error(ex);
                throw new Exception(ex);
            }
			return null;
            
        }
	
	
	
	public CM_BranchSpecializationInfoGetter[] methodProgramOfferedByProgramList(
			String programOfferedBy, String value) throws Exception {
        try {
            
//        	        	
        	List<?> li = null;
        	
        	CM_BranchSpecializationInfoGetter POB = new CM_BranchSpecializationInfoGetter();
        
        	POB.setOffered_by(programOfferedBy);
        	
        	POB.setProgram_name(value);
        	        	
        	try{
        	li = client.queryForList("selectProgramOfferedBy", POB);        	
        	}catch (Exception e) {
				throw new Exception (e);
			}
            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        
        } catch (Exception e) {
        	logObj.logger.error(e);
            throw new Exception(e);
        }
        
	}
	
    public CM_BranchSpecializationInfoGetter[] methodManageProgramList(String selectedProgramOfferedColumn)
    throws Exception {
    try {
        List<?> li = null;
        	 li = client.queryForList("selectProgramOfferingEntity", selectedProgramOfferedColumn);        
        return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
    } catch (Exception e) {
    	logObj.logger.error(e);
        throw new Exception(e);
    }
}
	
	
	
    public void methodEditProgramOfferedBy(CM_BranchSpecializationInfoGetter editProgramOfferedBy) throws Exception {
        try {    	
        	
        	client.update("editProgramOfferingEntity", editProgramOfferedBy);        	
       
        } catch (Exception e) {
        	logObj.logger.error(e);
            throw new Exception(e);
        }
        
	}	
	
	public void methodDeleteProgramOfferedBy(CM_BranchSpecializationInfoGetter deleteProgramOfferedBy) throws Exception 
	{
        try {
                    	
        	client.delete("deleteProgramOfferingEntity", deleteProgramOfferedBy);        	
       
        } catch (Exception e) {
        	logObj.logger.error(e);
            throw new Exception(e);
        }
        
	}
	
	
	
	
	
	
	
}