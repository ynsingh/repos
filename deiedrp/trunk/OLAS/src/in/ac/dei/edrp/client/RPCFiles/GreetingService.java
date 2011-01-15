package in.ac.dei.edrp.client.RPCFiles;

import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.ReportInfoGetter;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	
	List<DataBean> updateMarks(String university_id,String entity_type,String entity_name,String program_name,String branch_name);

	List<DataBean> getUpdateMarks(String university_id,String entity_type,String entity_name,String program_name,String branch_name);

	List<DataBean> getmeritStudent(String university_id,String entity_type,String entity_name,String program_name,String branch_name);

	boolean generateTestNumber(String university_id,String entity_type,String entity_name,String program_name,String branch_name);
	
	ReportInfoGetter[] methodEntityList(String university_id);

	ReportInfoGetter[] methodEntityNameList(String university_id,
			String entity_name);

	ReportInfoGetter[] methodProgramNameList(String university_id,
			String entity_name);

	ReportInfoGetter[] methodBranchNameList(String userid,
			String program_name, String entity_name);

	/*
	 * For validation
	 */
	String validateGenerate(String userid, String entity_type,
			String entity_name, String program_name, String branch_name);

	String getResetFlag(String userid, String entity_type, String entity_name,
			String program_name, String branch_name);

	
	String uploadFile(String file);	
}
