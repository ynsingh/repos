package in.ac.dei.edrp.cms.daoimpl.report;

import java.util.ArrayList;
import java.util.List;

import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ReportDaoImpl extends SqlMapClientDaoSupport implements ReportDao {
	
	private Logger loggerObject = Logger.getLogger(ReportDaoImpl.class);
	
	public String insertReportLog(ReportLogBean reportLogBean) {
		String result = "true";
		try {
			System.out.println("inside insert report log ");
			List<ReportLogBean> listDuplicate = checkForDuplicate(reportLogBean);			
			if(listDuplicate.size()>0){					
				getSqlMapClientTemplate().update("report.updateReportControlLog",reportLogBean);
			}
			else{
				getSqlMapClientTemplate().insert("report.insertReportControlLog",reportLogBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result="false";
			loggerObject.error("in insert report log" + e);
		}		
		/*if(reportLogBean.getIsGenerated().equalsIgnoreCase("yes")){
			try {				
				result = String.valueOf(getSqlMapClientTemplate().update("report.updateReportControlLog",reportLogBean));
				loggerObject.info("in update report log");
			} catch (Exception e) {
				loggerObject.error("in update report log" + e);
			}	
		}
		else{
			try {				
				result = (String)getSqlMapClientTemplate().insert("report.insertReportControlLog",reportLogBean);
				result="true";
				loggerObject.info("in insert report log");
			} catch (Exception e) {
				loggerObject.error("in insert report log" + e);
			}	
		}*/
		return result;
		}

	public String insertReportErrorLog(ReportLogBean reportLogBean) {
		String result = "false";		
			try {				
				result = (String)getSqlMapClientTemplate().insert("report.insertReportErrorLog",reportLogBean);
				loggerObject.info("in insert report error log");
			} catch (Exception e) {
				loggerObject.error("in insert report error log" + e);
			}			
		return result;
		}
	
	
	private List<ReportLogBean> checkForDuplicate(ReportLogBean reportLogBean) {
		List<ReportLogBean> logList = new ArrayList<ReportLogBean>();
		try{
			logList = getSqlMapClientTemplate().queryForList("report.selectReportControlLog",reportLogBean);
		} catch (Exception e) {
			loggerObject.error("in check for duplicate" + e);
		}		
		return logList;
	}
	
	public String insertReportPrintLog(ReportLogBean reportLogBean) {
		String result = "true";
		try {			
				getSqlMapClientTemplate().insert("report.insertReportPrintLog",reportLogBean);
			} catch (Exception e) {
			result="false";
			loggerObject.error("in insert report log" + e);
		}
		return result;
	}
	
	public String updateReportControlLogForPrint(ReportLogBean reportLogBean) {
		String result = "true";
		String reportPrinted = reportLogBean.getReportPrinted();
		reportLogBean.setReportPrinted("");
		try {
			List<ReportLogBean> listDuplicate = checkForDuplicate(reportLogBean);			
			if(listDuplicate.size()>0){	
				reportLogBean.setReportPrinted(reportPrinted);
				getSqlMapClientTemplate().update("report.updateReportControlLogForPrint",reportLogBean);
			}
			else{
				result = "Entry Missing in control Log...";
//				getSqlMapClientTemplate().insert("report.insertReportControlLog",reportLogBean);
			}
		} catch (Exception e) {
			result="false";
			loggerObject.error("in insert report log" + e);
		}
		return result;
	}
}
