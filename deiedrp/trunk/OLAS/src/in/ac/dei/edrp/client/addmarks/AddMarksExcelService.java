package in.ac.dei.edrp.client.addmarks;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("addmarksservice")
public interface AddMarksExcelService extends RemoteService {

	/**
	 * 
	 * @param filePath
	 * @return
	 */
	String uploadFile(String filePath);

	/**
	 * 
	 * @param filename
	 * @param userId
	 * @param entityType
	 * @param entityId
	 * @param programId
	 * @param branchCode
	 * @param specializationCode
	 * @return
	 */
	//Update By Devendra May 5th
	List<Integer> uploadFile(String filename, String userId, String entityType,
			String entityId, String programId);
	
	//Added By Devendra May 5th
	List<Integer> uploadFileForStudentComponentMarks(String filename, String userId, String entityType,
			String entityId, String programId,String ComponentId,String ComponentDescription);

}
