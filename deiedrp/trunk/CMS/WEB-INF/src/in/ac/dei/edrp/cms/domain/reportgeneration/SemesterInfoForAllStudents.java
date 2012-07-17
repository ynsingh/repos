package in.ac.dei.edrp.cms.domain.reportgeneration;

import java.util.List;

public class SemesterInfoForAllStudents {
	
	private ProgressCardInfo progressCardInfo;
	private List<ProgressCardInfo> marksList;
	
	public SemesterInfoForAllStudents(ProgressCardInfo progressCardInfo,List<ProgressCardInfo> marksList) {
		
		this.progressCardInfo = progressCardInfo;
		this.marksList = marksList;
	}
	
	public ProgressCardInfo getProgressCardInfo() {
		return progressCardInfo;
	}
	public void setProgressCardInfo(ProgressCardInfo progressCardInfo) {
		this.progressCardInfo = progressCardInfo;
	}
	public List<ProgressCardInfo> getMarksList() {
		return marksList;
	}
	public void setMarksList(List<ProgressCardInfo> marksList) {
		this.marksList = marksList;
	}
	
	
	
}
