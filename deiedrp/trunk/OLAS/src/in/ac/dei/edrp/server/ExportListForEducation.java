package in.ac.dei.edrp.server;

import com.ibatis.sqlmap.client.SqlMapClient;

import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import jxl.Workbook;

import jxl.format.Colour;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExportListForEducation {
    @SuppressWarnings("unchecked")
	private static List list = new ArrayList();
    static String[] category = null;
    Log4JInitServlet logObj;
    SqlMapClient client;
    ReportingFunction reportfunction;

    public ExportListForEducation() {
        logObj = new Log4JInitServlet();
        client = SqlMapManager.getSqlMapClient();
        reportfunction = new ReportingFunction();
    }

    @SuppressWarnings("unchecked")
	public List getListAccordingToProgram(String university_id,String entity_id, String program_id) {
        System.out.println("------------At server side----------");
        List<DataBean> list = new ArrayList<DataBean>();
        double cm = 0.0D;
        String[][] s = (String[][]) null;
        String[] detail = (String[]) null;
        String[] paperCodes = (String[]) null;

        try {
            String[] udate = reportfunction.getSessionDate(university_id);
            ReportInfoGetter export = new ReportInfoGetter();
            export.setProgram_id(program_id);
            export.setEntity_id(entity_id);
            export.setOffered_by(entity_id);
            export.setStart_date(udate[0]);
            export.setEnd_date(udate[1]);
            export.setUniversity_id(university_id);            
            category = reportfunction.getCategoryForProgram(export);

            for (int catlen = 0; catlen < category.length; catlen++) {        
                int slnum = 0;          
                System.out.println("cosvalue is "+category[catlen]);
                export.setCos_value((new StringBuilder(String.valueOf(
                            category[catlen]))).append("%").toString());               
                export.setStatus("Eligible");

                List li2 = client.queryForList("getRegistrationNumberForProgram",
                        export);
              
                double call = 0.0D;
                export.setOffered_by(entity_id);         
               
                for (Iterator iterator = li2.iterator(); iterator.hasNext();) {
                    ReportInfoGetter reg_excel = (ReportInfoGetter) iterator.next();
                    String name = reg_excel.getRegistration_number();
                    cm = reg_excel.getSum_computed_marks();

                    String cos_value = reg_excel.getCos_value();                   
                    export.setCos_value(cos_value);

                    List li3 = client.queryForList("getCallMerit", export);                   
                    for (Iterator iterator1 = li3.iterator();
                            iterator1.hasNext();) {
                        ReportInfoGetter callmarks = (ReportInfoGetter) iterator1.next();
                        call = callmarks.getCut_off_number();                       
                    }                   
                    if (cm >= call) {
                        String[][] data = reportfunction.getFullyExcelComputedmarks(name,
                                export, udate);
                        String[] detailData = reportfunction.getPersonalDetail(name,
                                export);
                        export.setFormNumber(detailData[0]);

                        String[] paperCode = reportfunction.getPaperCode(name,export);
                        
                        s = data;
                        detail = detailData;
                        paperCodes = paperCode;                     
                        slnum++;

                        if (cos_value.substring(0, 2).equalsIgnoreCase("XX")) {
                            reportfunction.updateCalledStudentTest(name,
                                export, "n", udate);
                        } else {                           
                            reportfunction.updateCalledStudentTest(name,
                                export, "y", udate);
                        }

                        list.add(new DataBean(slnum, name, cos_value, detail,
                                s, cm, paperCodes));
                    } else {
                        reportfunction.updateCalledStudentTest(name, export,
                            "n", udate);
                    }
                }           
            }
           
            if (list.size() > 0) {
                updateControlReportForProgram(entity_id,
                    program_id, "I", "C", udate);
            }
        } catch (Exception e) {
            logObj.logger.info((new StringBuilder(
                    "Exception in getting list for internal call list: ")).append(
                    e.getMessage()).toString());
        }
      
        return list;
    }

    private void updateControlReportForProgram(
        String entity_id, String program_id, String updatedProgram,String computedProgram, String[] udate) {
        try {
            ReportInfoGetter reportInfo = new ReportInfoGetter();
            reportInfo.setEntity_id(entity_id);
            reportInfo.setProgram_id(program_id);
            reportInfo.setStart_date(udate[0]);
            reportInfo.setEnd_date(udate[1]);
            reportInfo.setFlag_status(updatedProgram);
            client.update("updateControlReportForProgram", reportInfo);
        } catch (Exception e) {
        	 logObj.logger.info((new StringBuilder(
                     "Exception in getting list for internal call list:updateControlReportForProgram :: ")).append(
                     e.getMessage()).toString());
        }
    }

    @SuppressWarnings("unchecked")
	public ByteArrayOutputStream generateExcelReport(String university_id,String entity_id, String program_id)
        throws IOException, WriteException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ReportingFunction reportFunction = new ReportingFunction();
        WritableWorkbook workBook = Workbook.createWorkbook(outputStream);
        WritableSheet sheet = workBook.createSheet("User List", 0);
        WritableFont headerFont = new WritableFont(WritableFont.TAHOMA, 8,
                WritableFont.BOLD);
        WritableCellFormat headerCellFormat = new WritableCellFormat(headerFont);
        headerCellFormat.setBackground(Colour.WHITE);
        String entity_name = reportFunction.getEntity_Name(entity_id,
                university_id);
        String program_name = reportFunction.getProgram_Name(entity_id,
                program_id);
        String[] udate = reportfunction.getSessionDate(university_id);
        list = getUsers(university_id,entity_id, program_id);

        Iterator i1 = list.iterator();
        int damaxLen = 0;
        sheet.addCell(new Label(5, 1,
                "DayalBagh Education Institute- Agra(282110)", headerCellFormat));
        sheet.addCell(new Label(5, 3, "Institute Name", headerCellFormat));
        sheet.addCell(new Label(6, 3, entity_name, headerCellFormat));
        sheet.addCell(new Label(5, 4, "Program Name", headerCellFormat));
        sheet.addCell(new Label(6, 4, program_name, headerCellFormat));
        sheet.addCell(new Label(5, 5, "Session", headerCellFormat));    
        sheet.addCell(new Label(6, 5, udate[0].split("-")[0]+"-"+udate[1].split("-")[0], headerCellFormat));

        while (i1.hasNext()) {
            DataBean db = (DataBean) i1.next();           
            String[][] datalen = db.getCompData();

            if (damaxLen < datalen.length) {
                damaxLen = datalen.length;
            }
        }

        if (list.size() != 0) {        	
            i1 = list.iterator();
            i1.hasNext();

            DataBean db = (DataBean) i1.next();

            sheet.addCell(new Label(1, 7, "Serial Number", headerCellFormat));
            sheet.addCell(new Label(2, 7, "Registration Number",
                    headerCellFormat));
            sheet.addCell(new Label(3, 7, "Cos Value", headerCellFormat));

            String[] det = db.getDetail();           
            int dlen = 4;

            for (int l = 0; l < det.length; l++) {
                switch (l) {
                case 0: {
                    sheet.addCell(new Label(dlen, 7, "Faculty Registration Number", headerCellFormat));
                    dlen++;

                    break;
                }

                case 1: {
                    sheet.addCell(new Label(dlen, 7, "Name",
                            headerCellFormat));
                    dlen++;

                    break;
                }

                case 2: {
                    sheet.addCell(new Label(dlen, 7, "Father Name",
                            headerCellFormat));
                    dlen++;

                    break;
                }
                case 6: {
                    sheet.addCell(new Label(dlen, 7, "City", headerCellFormat));
                    dlen++;

                    break;
                }
                }
            }

            String[][] datalen = db.getCompData();            
            int x = 10;

            for (int component = 0; component < datalen.length; component++) {            
                sheet.addCell(new Label(x, 6, datalen[component][0],
                        headerCellFormat));
                x = x + 7;
            }

            int len = dlen;            
            for (int i2 = 0; i2 < damaxLen; i2++) {
                for (int j = 0; j < 9; j++) {
                    switch (j) {             
                    case 2: {
                        sheet.addCell(new Label(len, 7, "Marks Obtained", headerCellFormat));
                        len++;

                        break;
                    }

                    case 3: {
                        sheet.addCell(new Label(len, 7, "Maximum Marks", headerCellFormat));
                        len++;

                        break;
                    }

                    case 4: {
                        sheet.addCell(new Label(len, 7, "%", headerCellFormat));
                        len++;

                        break;
                    }

                    case 5: {
                        sheet.addCell(new Label(len, 7, "Weightage", headerCellFormat));
                        len++;

                        break;
                    }

                    case 6: {
                        sheet.addCell(new Label(len, 7, "DEI Student",
                                headerCellFormat));
                        len++;

                        break;
                    }

                    case 7: {
                        sheet.addCell(new Label(len, 7, "Special Weightage",
                                headerCellFormat));
                        len++;

                        break;
                    }

                    case 8: {
                        sheet.addCell(new Label(len, 7, "Component Weightage",
                                headerCellFormat));
                        len++;

                        break;
                    }
                    }
                }
            }
          
            sheet.addCell(new Label(len, 7, "Complete Weightage", headerCellFormat));
            len++;
            
            ReportInfoGetter been=new ReportInfoGetter();
            been.setProgram_id(program_id);
            been.setUniversity_id(university_id);
            been.setEntity_id(entity_id);
            try {
				List<ReportInfoGetter> paperGroupList=client.queryForList("getProgramGroup", been);
				for(int i2=0;i2<paperGroupList.size();i2++){
					 sheet.addCell(new Label(len, 7, paperGroupList.get(i2).getDescription(), headerCellFormat));
					 len++;
				}
			} catch (SQLException e) {
				logObj.logger.error("Error during get program group inside ExportListForEducation class : "+e);
			}       
            WritableFont dataFont = new WritableFont(WritableFont.TAHOMA, 8);
            WritableCellFormat dataCellFormat = new WritableCellFormat(dataFont);
            int currentRow = 8;
            Iterator i = list.iterator();
            String[][] data1 = new String[damaxLen][];
            String[] det1 = (String[]) null;
            int catval = 0;

            while (i.hasNext()) {
                DataBean db1 = (DataBean) i.next();               
                if (db1.getSlnum() == 1) {
                    sheet.addCell(new Label(1, currentRow, category[catval],
                            dataCellFormat));                    
                    currentRow++;
                    catval++;
                }                
                sheet.addCell(new Label(1, currentRow,
                        String.valueOf(db1.getSlnum()), dataCellFormat));
                sheet.addCell(new Label(2, currentRow,
                        db1.getRegistrationNumber(), dataCellFormat));
                sheet.addCell(new Label(3, currentRow, db1.getCategory(),
                        dataCellFormat));
                data1 = db1.getCompData();
                det1 = db1.getDetail();

                int dlen1 = 4;

                for (int l1 = 0; l1 < det.length; l1++) {
                    if ((l1 == 0) || (l1 == 1) || (l1 == 2) || (l1 == 6)) {                    	
                        sheet.addCell(new Label(dlen1, currentRow, det1[l1],
                                dataCellFormat));
                        dlen1++;
                    }
                }

                int len1 = dlen1;

                for (int i3 = 0; i3 < data1.length; i3++) {
                    for (int j = 0; j < 9; j++) {
                        if (j > 1) {                        	
                            sheet.addCell(new Label(len1, currentRow,
                                    data1[i3][j], dataCellFormat));
                            len1++;
                        }
                    }
                }

                if (data1.length < damaxLen) {
                    for (int i3 = data1.length; i3 < damaxLen; i3++) {
                        for (int j = 0; j < 9; j++) {
                            if (j > 1) {
                            	
                                sheet.addCell(new Label(len1, currentRow, "",
                                        dataCellFormat));
                                len1++;
                            }
                        }
                    }
                }

                double comp1 = db1.getTotalMarks();                
                sheet.addCell(new Label(len1, currentRow,
                        String.valueOf(comp1), dataCellFormat));
                len1++;
                been.setRegistration_number(db1.getRegistrationNumber());
                try {
					List<ReportInfoGetter> paperGroupList=client.queryForList("getProgramPaperDesc", been);
					for(int i2=0;i2<paperGroupList.size();i2++){
						sheet.addCell(new Label(len1, currentRow, paperGroupList.get(i2).getDescription(),dataCellFormat));
						len1++;
					}
				} catch (SQLException e) {
					logObj.logger.error("Error during get program paper code inside ExportListForEducation class : "+e);
				}

                currentRow++;
            }
        }

        workBook.write();
        workBook.close();

        return outputStream;
    }

    @SuppressWarnings("unchecked")
	public List getUsers(String university_id,String entity_name, String program_name) {
        return getListAccordingToProgram(university_id,entity_name, program_name);
    }
}
