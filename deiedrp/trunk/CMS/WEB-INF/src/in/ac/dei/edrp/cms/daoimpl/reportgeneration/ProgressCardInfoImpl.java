package in.ac.dei.edrp.cms.daoimpl.reportgeneration;

import in.ac.dei.edrp.cms.dao.reportgeneration.ProgressCardInfoDao;
import in.ac.dei.edrp.cms.daoimpl.activitymaster.ActivityMasterDAOImpl;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ProgressCardInfoImpl extends SqlMapClientDaoSupport implements ProgressCardInfoDao {
	
	private Logger loggerObject = Logger.getLogger(ProgressCardInfoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> programList(ProgressCardInfo progressCardInfo) {
		List<ProgressCardInfo> programs=null;
		System.out.println("in impl");
		try{
			loggerObject.info("inside programList");
			programs = getSqlMapClientTemplate().queryForList("progressCard.programInEntity",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside programList: "+e);
		}
		return programs;
	}

	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> entityList(ProgressCardInfo progressCardInfo) {		
		List<ProgressCardInfo> entity=null;
		try{
			loggerObject.info("inside entityList: ");
			entity = getSqlMapClientTemplate().queryForList("progressCard.getEntityInfo",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside entityList: "+e);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> semesterList(ProgressCardInfo progressCardInfo) {
		List<ProgressCardInfo> semesters=null;
		try{
			loggerObject.info("inside semesterList: ");
			semesters = getSqlMapClientTemplate().queryForList("progressCard.semesterInProgram",progressCardInfo);
		}
		catch (Exception e) {
			loggerObject.error("inside semesterList: "+e);
		}
		return semesters;
	}

	public ProgressCardInfo semesterDate(ProgressCardInfo progresssCardInfo) {
		ProgressCardInfo semesterDate=null;
		try{
			loggerObject.info("inside semesterDate: ");
			String b=progresssCardInfo.getBranchId();
			if(b==null)
			{
				semesterDate =  (ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.semesterDate",progresssCardInfo);
			}
			else{
				semesterDate =  (ProgressCardInfo) getSqlMapClientTemplate().queryForObject("progressCard.semesterDateWithBranch",progresssCardInfo);
				}
			}
		catch (Exception e) {
			loggerObject.error("inside semesterDate: "+e);
		}
		return semesterDate;
	}

}
