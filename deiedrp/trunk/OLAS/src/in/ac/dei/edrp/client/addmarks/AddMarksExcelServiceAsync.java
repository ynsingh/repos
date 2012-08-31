package in.ac.dei.edrp.client.addmarks;



import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>AddMarksExcelService</code>.
 */
public interface AddMarksExcelServiceAsync {

	/**
	 * 
	 * @param filePath
	 * @param asyncCallback
	 */
	void uploadFile(String filePath,AsyncCallback<String> asyncCallback);
	
	
	/**
	 * 
	 * @param filename
	 * @param userId
	 * @param entityType
	 * @param entityId
	 * @param programId
	 * @param branchCode
	 * @param specializationCode
	 * @param asyncCallback
	 */
	//Update by Devendra May 5th
	void uploadFile(String filename, String userId, String entityType,
			String entityId, String programId,AsyncCallback<List<Integer>> asyncCallback);
	
	//Added by Devendra May 5th
	void uploadFileForStudentComponentMarks(String filename, String userId, String entityType,
			String entityId, String programId,String ComponentId,String ComponentDescription,AsyncCallback<List<Integer>> asyncCallback);

}
