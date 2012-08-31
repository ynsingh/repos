package in.ac.dei.edrp.server;

import jxl.write.WriteException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ExportServlet1 extends HttpServlet {
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

            System.out.println("inside servlet 1" + university_id + " type: " +
                entity_type + " name: " + entity_name + " program: " +
                program_name + " subject_code: " + subject_code + "_" +
                " gender: " + gender+"_ gender Name: "+subject_name);

            /* Get Excel Data */
            ByteArrayOutputStream bytes = new ExportService1().generateExcelReport(university_id,entity_name, program_name, subject_code,
                    gender, subject_name);

            /* Initialize Http Response Headers */
            response.setHeader("Content-disposition",
                "attachment; filename=ExternalCallList_" + university_id + "_" +
                entity_name + "_" + program_name + "_" + subject_code + "_" +
                gender + ".xls");
            response.setContentType("application/vnd.ms-excel");

            /* Write data on response output stream */
            if (bytes != null) {
                response.getOutputStream().write(bytes.toByteArray());
            }
        } catch (WriteException e) {
            response.setContentType("text/plain");
            response.getWriter().print("An error as occured");
        }
    }
}
