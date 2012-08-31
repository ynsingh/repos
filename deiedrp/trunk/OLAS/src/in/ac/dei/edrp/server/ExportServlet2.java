package in.ac.dei.edrp.server;

import jxl.write.WriteException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ExportServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        try {            
            String university_id = request.getParameter("param");
            String entity_type = request.getParameter("param1");
            String entity_name = request.getParameter("param2");
            String program_name = request.getParameter("param3");
            String subject_code = request.getParameter("param4");
            String gender = request.getParameter("param5");
            String subject_name = request.getParameter("param6");           
            if(subject_code.equalsIgnoreCase("All")){
            	 /* Get Excel Data */
                ByteArrayOutputStream bytes = new ExportFinalListForAllSubject().generateExcelReport(university_id,entity_name, program_name, subject_code,
                        gender, subject_name);

                /* Initialize Http Response Headers */
                response.setHeader("Content-disposition",
                    "attachment; filename=Final_Call_List_" + university_id + "_" +
                    entity_name + "_" + program_name + "_" + subject_code + "_" +
                    gender + ".xls");
                response.setContentType("application/vnd.ms-excel");

                /* Write data on response output stream */
                if (bytes != null) {
                    response.getOutputStream().write(bytes.toByteArray());
                }
            }            
            else{
				
                /* Get Excel Data */
                ByteArrayOutputStream bytes = new ExportService2().generateExcelReport(university_id,entity_name, program_name, subject_code,
                        gender, subject_name);

                /* Initialize Http Response Headers */
                response.setHeader("Content-disposition",
                    "attachment; filename=Final_Call_List_" + university_id + "_" +
                    entity_name + "_" + program_name + "_" + subject_code + "_" +
                    gender + ".xls");
                response.setContentType("application/vnd.ms-excel");

                /* Write data on response output stream */
                if (bytes != null) {
                    response.getOutputStream().write(bytes.toByteArray());
                }
            }

        } catch (WriteException e) {
            response.setContentType("text/plain");
            response.getWriter().print("An error as occured");
        } // catch ends
    } // doget ends
} // class ends
