package in.ac.dei.edrp.cms.dao.reportgeneration;

import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;

import java.util.List;

public interface ProgressCardInfoDao {
	
	public List<ProgressCardInfo> programList(ProgressCardInfo progressCardInfo);
	public List<ProgressCardInfo> entityList(ProgressCardInfo progressCardInfo);
	public List<ProgressCardInfo> semesterList(ProgressCardInfo progressCardInfo);
	public ProgressCardInfo semesterDate(ProgressCardInfo progressCardInfo);
}
