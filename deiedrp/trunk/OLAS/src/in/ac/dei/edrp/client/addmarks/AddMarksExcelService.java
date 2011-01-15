package in.ac.dei.edrp.client.addmarks;


import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.ReportInfoGetter;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("addmarksservice")
public interface AddMarksExcelService extends RemoteService{

	String uploadFile(String filePath);
	
	List<Integer> uploadFile(String filename, String userId, String entityType,
			String entityId, String programId, String branchCode);

}
