package in.ac.dei.edrp.client.addmarks;



import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>AddMarksExcelService</code>.
 */
public interface AddMarksExcelServiceAsync {

	void uploadFile(String filePath,AsyncCallback<String> asyncCallback);

	void uploadFile(String filename, String userId, String entityType,
			String entityId, String programId, String branchCode,
			AsyncCallback<List<Integer>> asyncCallback);
}
