/** $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **********************************************************************************/
package in.ac.dei.edrp.server.addmarks;

import com.ibatis.sqlmap.client.SqlMapClient;

import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.client.addmarks.ReadXLBean;
import in.ac.dei.edrp.server.ExportService2;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import jxl.read.biff.BiffException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * File updated
 * @author On Demand Examination Team
 */
public class ReadXLSheet {
    SqlMapClient client = SqlMapManager.getSqlMapClient();
    ReadXLBean insertion = new ReadXLBean();
    Log4JInitServlet logObj = new Log4JInitServlet();

    public List<Integer> init(String filePath, ReportInfoGetter addMarksReport) {
        FileInputStream fs = null;

        List<Integer> list = new ArrayList<Integer>();

        try {
            fs = new FileInputStream(new File(filePath));
            list = contentReading(fs, addMarksReport);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    // Returns the Headings used inside the excel sheet
    public void getHeadingFromXlsFile(Sheet sheet) {
        int columnCount = sheet.getColumns();
    }

    /**
     * method updated
     */
    public List<Integer> contentReading(InputStream fileInputStream,
        ReportInfoGetter addMarksReport) {
        WorkbookSettings ws = null;
        Workbook workbook = null;
        Sheet s = null;
        Cell[] rowData = null;
        int rowCount ='0';
        @SuppressWarnings("unused")
        int columnCount = '0';
        int totalSheet = 0;

        int count = 0;
        List<Integer> list = new ArrayList<Integer>();
        int totalrecord = 0;

        try {
            ws = new WorkbookSettings();
            ws.setLocale(new Locale("en", "EN"));
            workbook = Workbook.getWorkbook(fileInputStream, ws);
            totalSheet = workbook.getNumberOfSheets();
            
            // Gets Default Sheet i.e. 0
            s = workbook.getSheet(0);
            // Reads Individual Cell
            getHeadingFromXlsFile(s);
            // Total no of Rows in Sheet, will return you no of rows that are
            // occupied with some data          
            rowCount = s.getRows();           
            columnCount = s.getColumns();

            ExportService2 exportService2 = new ExportService2();

            /**
             * method updated
             */
            String[] headings = exportService2.getHeadLines(addMarksReport);

            int totalColumn = (headings.length * 2) + 3;

            totalrecord = (rowCount - 8) * (headings.length);;
            if (totalColumn == s.getRow(7).length) {
                // Reads Individual Row Content
                ReportInfoGetter addMarksinDB = addMarksReport;

                for (int i = 8; i < rowCount; i++) {
                    int noOfComponent = 0;
                    // Gets Individual Row
                    rowData = s.getRow(i);

                    String registrationNumber = rowData[1].getContents();

                    String testNumber = rowData[2].getContents();

                    addMarksinDB.setRegistration_number(registrationNumber);

                    addMarksinDB.setTest_number(testNumber);

                    // boolean b=true;
                    boolean b = false;

                    /**
                     * method updated
                     */
                    for (ReportInfoGetter sessionDate : validateTestNumber(addMarksinDB)) {
                        addMarksinDB.setStart_date(sessionDate.getStart_date());
                        addMarksinDB.setEnd_date(sessionDate.getEnd_date());
                        addMarksinDB.setSum_actual_computed_marks(sessionDate.getSum_actual_computed_marks());
                        addMarksinDB.setCos_value(sessionDate.getCos_value());
                        b = true;
                    }
                    int temp = 3;
                    double sum = addMarksinDB.getSum_actual_computed_marks();                   
                    for (int length = 0; length < headings.length; length++) {
                        /**
                         * method updated
                         */
                        addMarksinDB.setComponent_id(getEvaluationId(
                                headings[length], addMarksReport));

                        /**
                         * method unchanged
                         */
                        if (checkIfDouble(rowData[temp].getContents()) &&
                                (Double.parseDouble(rowData[temp].getContents()) >= 0)) {
                            double marks = Double.parseDouble((rowData[temp].getContents()));

                            String attended = rowData[++temp].getContents();

                            addMarksinDB.setMarks_percentage(marks);
                            addMarksinDB.setAttendance(attended);
                            temp++;

                            /**
                             * method updated
                             */
                            if (validateExcelRecords(headings[length], marks,
                                        attended, addMarksinDB) && (b)) {
                                /**
                                 * method updated
                                 */
                                if (!checkDuplicacyForRegistrationNumber(
                                            addMarksinDB)) {
                                    /**
                                     * query updated
                                     */                                	
                                    client.update("insertStudentFinalMarks",
                                        addMarksinDB);                                   
                                    count++;
                                    sum = sum + marks;
                                    noOfComponent++;
                                } else {
                                    /**
                                     * query updated
                                     */
                                    client.update("updateStudentFinalMarks",
                                        addMarksinDB);
                                    count++;
                                    sum = sum + marks;
                                    noOfComponent++;
                                } // check duplicacy else ends
                            } // check validate if ends
                        } // checkIfDouble
                        else {
                            logObj.logger.info("Improper value for: " +
                                addMarksinDB.getRegistration_number());                            
                            temp = temp + 2;                           
                        } // checkIf Double ends
                    } // first if closed

                    if (b) {
                        /**
                         * method updated
                         */
                    	System.out.println("total marks is "+sum);
                        if (checkDuplicateTestNumberInStudentFinalList(
                                    addMarksinDB)) {
                            if (noOfComponent == headings.length) {
                                addMarksinDB.setTotal_marks(sum);

                                /**
                                 * query updated
                                 */
                                client.update("updateStudentFinalMeritList",
                                    addMarksinDB);
                            } // if no of components ends
                        } // if check duplicate ends
                        else {
                            addMarksinDB.setTotal_marks(sum);
                            /**
                             * query updated
                             */
                            client.update("insertStudentFinalMeritList",
                                addMarksinDB);
                        } // if check duplicate ends
                    } // if b ends
                } // for loop ends for row
            } // if total sheet length closed

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (Exception e) {
            logObj.logger.info("Problem while inserting in data base");
        } // end catch

        list.add(totalrecord);
        list.add(count);

        return list;
    } // end contentReading

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public String getEvaluationId(String evaluationComponent,
        ReportInfoGetter addMarksReport) {
        String evaluationId = "";

        try {
            addMarksReport.setComponent_description(evaluationComponent);

            /**
             * query updated
             */
            List<ReportInfoGetter> existReg = client.queryForList("getFinalMeritEvaluationId",
                    addMarksReport);

            for (ReportInfoGetter exist : existReg) {
                evaluationId = exist.getComponent_id();
            }
        } catch (Exception e) {
            logObj.logger.info(
                "Problem while getting evaluation id in data base");
        }

        return evaluationId;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public boolean checkDuplicacyForRegistrationNumber(
        ReportInfoGetter addMarksinDB) {
        boolean existRegistration = false;

        try {
            /**
             * query updated
             */
            List<ReportInfoGetter> existReg = client.queryForList("checkDuplicacyOfRegInStduentFinal",
                    addMarksinDB);

            for (ReportInfoGetter exist : existReg) {

                if (exist.getCount() >= 1) {
                    existRegistration = true;
                }
            }
        } catch (Exception e) {
            logObj.logger.info("Problem while inserting in data base" +
                addMarksinDB.getRegistration_number());
        }

        return existRegistration;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public boolean validateExcelRecords(String evaluationId, double marks,
        String attended, ReportInfoGetter validateMarks) {
        boolean validateExcel = true;

        try {
            if ((attended.length() == 1) &&
                    (attended.equalsIgnoreCase("P") ||
                    attended.equalsIgnoreCase("A"))) {
                /**
                 * query updated
                 */
                List<ReportInfoGetter> validateRecord = client.queryForList("getFinalMeritEvaluationId",
                        validateMarks);

                for (ReportInfoGetter validate : validateRecord) {
                    if (marks > validate.getTotal_marks()) {
                        validateExcel = false;
                    }
                }
            } else {
                validateExcel = false;
            }
        } catch (Exception e) {
            logObj.logger.info("Problem while inserting in data base" +
                validateMarks.getRegistration_number());
        }

        return validateExcel;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public List<ReportInfoGetter> validateTestNumber(
        ReportInfoGetter validateTest) {
        List<ReportInfoGetter> list = new ArrayList<ReportInfoGetter>();
        validateTest.setCalled("y");

        try {
            /**
             * query updated
             */
            list = client.queryForList("getStudentSessionDate", validateTest);
        } catch (Exception e) {
            logObj.logger.info("Problem while inserting in data base");
        }

        return list;
    }

    /**
     * method unchanged
     */
    public boolean checkIfDouble(String in) {
        try {
            Double.parseDouble(in);
        } catch (NumberFormatException ex) {
            logObj.logger.info("Enter number values");

            return false;
        }

        return true;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public boolean checkDuplicateTestNumberInStudentFinalList(
        ReportInfoGetter validateFinalList) {
        boolean b = false;

        try {
            /**
             * query updated
             */
            List<ReportInfoGetter> studentList = client.queryForList("getStudentinStudentFinal",
                    validateFinalList);

            for (ReportInfoGetter studentExist : studentList) {
                if (studentExist.getCount() >= 1) {
                    b = true;
                }
            }
        } catch (Exception e) {
            logObj.logger.info(
                "Problem while executing in checkDulicate in ReadXL");
        }

        return b;
    }
} // end class
