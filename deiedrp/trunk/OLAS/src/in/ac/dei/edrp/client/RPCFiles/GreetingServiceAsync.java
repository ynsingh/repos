package in.ac.dei.edrp.client.RPCFiles;

import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.ReportInfoGetter;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void updateMarks(String university_id, String entity_type,
			String entity_name, String program_name, String branch_name,
			AsyncCallback<List<DataBean>> callback);

	void getUpdateMarks(String university_id,String entity_type,String entity_name,String program_name,String branch_name,
			AsyncCallback<List<DataBean>> asyncCallback);

	void getmeritStudent(String university_id,String entity_type,String entity_name,String program_name,String branch_name,
			AsyncCallback<List<DataBean>> asyncCallback);

	void generateTestNumber(String university_id,String entity_type,String entity_name,String program_name,String branch_name,
			AsyncCallback<Boolean> asyncCallback);

	void methodEntityList(String university_id,
			AsyncCallback<ReportInfoGetter[]> callback);

	void methodEntityNameList(String university_id, String entity_description,
			AsyncCallback<ReportInfoGetter[]> asyncCallback);

	void methodProgramNameList(String university_id, String entity_name,
			AsyncCallback<ReportInfoGetter[]> asyncCallback);

	void methodBranchNameList(String userid, String program_name, String entity_name,
			AsyncCallback<ReportInfoGetter[]> asyncCallback);

	void validateGenerate(String userid, String entity_type,
			String entity_name, String program_name, String branch_name,
			AsyncCallback<String> asyncCallback);

	void getResetFlag(String userid, String entity_type, String entity_name,
			String program_name, String branch_name,
			AsyncCallback<String> asyncCallback);

	void uploadFile(String file, AsyncCallback<String> asyncCallback);

	
}
