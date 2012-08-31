package in.ac.dei.edrp.server.InternalSummarySheet;

import in.ac.dei.edrp.client.ProgramSearchInfoGetter;
import in.ac.dei.edrp.client.InternalSummarySheet.FormSearchInfoGetter;
import in.ac.dei.edrp.client.InternalSummarySheet.FormSearchService;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("serial")
public class FormSearchImpl extends RemoteServiceServlet implements FormSearchService{

	SqlMapClient sqlMapClient = SqlMapManager.getSqlMapClient();
    Log4JInitServlet logObj = new Log4JInitServlet();

	@SuppressWarnings("unchecked")
	public List<FormSearchInfoGetter> getEntityPrograms(String userId,String entityValue) {
		List<FormSearchInfoGetter> componentList = new ArrayList<FormSearchInfoGetter>();
		FormSearchInfoGetter form = new FormSearchInfoGetter();
		String uniId=userId.substring(1,5);
        try {
        	form.setUniversityId(uniId);
        	form.setEntityId(entityValue);
        	form.setUserId(userId);
            componentList = sqlMapClient.queryForList("getUniversityFormsToSearch",form);
        } catch (Exception ex) {
            logObj.logger.error(ex.getMessage());
        }

        return componentList;

	}

	@SuppressWarnings("unchecked")
	public List<FormSearchInfoGetter> getUniversitySessionDate(
			String universityId) {
		List<FormSearchInfoGetter> sessionDetails;
		FormSearchInfoGetter progInfo=new FormSearchInfoGetter();
		
		try{
			progInfo.setUniversityId(universityId);
			
			sessionDetails=sqlMapClient.queryForList("getUniversitySessionForm",progInfo);
			
			return sessionDetails;
		}
		catch(Exception ex){
			logObj.logger.error("exception in getUniversitySessionDate :" + ex);
			System.out.println("exception "+ex);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<FormSearchInfoGetter> getUnivEntity(String userId) {
		String universityId=userId.substring(1,5);
		List<FormSearchInfoGetter> locationList;
		try{
			locationList=sqlMapClient.queryForList("getUnivEntity", universityId);
			
			return locationList;
		}
		catch(Exception ex){
			logObj.logger.error("exception in getLocationDetails" + ex);
		}
		return null;
	}
    
	
}
