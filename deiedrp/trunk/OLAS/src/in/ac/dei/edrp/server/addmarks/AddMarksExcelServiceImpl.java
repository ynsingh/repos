package in.ac.dei.edrp.server.addmarks;

import in.ac.dei.edrp.client.addmarks.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.sql.*;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.RPCFiles.*;
import in.ac.dei.edrp.client.Shared.*;
import in.ac.dei.edrp.server.ReportingFunction;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;
import in.ac.dei.edrp.shared.FieldVerifier;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("serial")
public class AddMarksExcelServiceImpl extends RemoteServiceServlet implements
AddMarksExcelService{

	

	@Override
	public List<Integer> uploadFile(String filename, String universityId,
			String entityType, String entityId, String programId,
			String branchCode) {
		List<Integer> list=new ArrayList<Integer>();
		// TODO Auto-generated method stub
		ReadXLSheet xlReader = new ReadXLSheet();
		ReportInfoGetter addMarksReport=new ReportInfoGetter();
		ReportingFunction reportingFunction=new ReportingFunction();
		
		String[] sessionDate=reportingFunction.getSessionDate(universityId);
		
		addMarksReport.setUniversity_id(universityId);
		addMarksReport.setEntity_id(entityId);
		addMarksReport.setProgram_id(programId);
		addMarksReport.setBranch_code(branchCode);
		addMarksReport.setOffered_by(entityId);
		addMarksReport.setUniversity_start_date(sessionDate[0]);
		addMarksReport.setUniversity_end_date(sessionDate[1]);
		
		list=xlReader.init(filename,addMarksReport);
		
		return list;
	}

	@Override
	public String uploadFile(String filePath) {
		// TODO Auto-generated method stub
		return null;
	}

}
