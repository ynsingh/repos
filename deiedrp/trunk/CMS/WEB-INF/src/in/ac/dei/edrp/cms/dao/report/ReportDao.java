







package in.ac.dei.edrp.cms.dao.report;

import in.ac.dei.edrp.cms.domain.report.ReportLogBean;

public interface ReportDao {	
	String insertReportLog(ReportLogBean reportLogBean);

	String insertReportErrorLog(ReportLogBean reportErrorBean);
	String updateReportControlLogForPrint(ReportLogBean reportLogBean);
	String insertReportPrintLog(ReportLogBean reportLogBean);
}
