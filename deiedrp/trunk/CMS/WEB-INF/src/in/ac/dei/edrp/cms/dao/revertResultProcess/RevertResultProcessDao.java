package in.ac.dei.edrp.cms.dao.revertResultProcess;

import in.ac.dei.edrp.cms.domain.revertResultProcess.RevertResultProcessBean;
import java.util.List;

public interface RevertResultProcessDao {

	List<RevertResultProcessBean> getEntity(RevertResultProcessBean input);
	
	List<RevertResultProcessBean> getProgram(RevertResultProcessBean input);
	
	List<RevertResultProcessBean> getBranch(RevertResultProcessBean input);
	
	List<RevertResultProcessBean> getSpecialization(RevertResultProcessBean input);
	
	List<RevertResultProcessBean> getSemester(RevertResultProcessBean input);
	
	String revertProcess(RevertResultProcessBean input);
	
}
