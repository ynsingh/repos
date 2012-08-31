package in.ac.dei.edrp.server;

import jxl.write.WriteException;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.client.Window;
import com.sun.java.swing.plaf.windows.resources.windows;



@SuppressWarnings("unused")
public class ExportServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

    public ExportServlet()
    {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        try
        {
            System.out.println("Coming here in export servlet");
            String university_id = request.getParameter("param");
            String entity_type = request.getParameter("param1");
            String entity_name = request.getParameter("param2");
            String program_name = request.getParameter("param3");
            //String branch_name = request.getParameter("param4");
            String subject_code=request.getParameter("param4");
            String genderDep= request.getParameter("param5");
            //String specialization_id = request.getParameter("param5");
            String subject_name = request.getParameter("param6");
            System.out.println((new StringBuilder("inside servlet")).append(university_id).append(" type: ").append(entity_type).append(" name: ").append(entity_name).append(" program: ").append(program_name).append(" subject: ").append(subject_code).append(" genderDep : ").append(genderDep));
            System.out.println("subject namen is "+subject_name);
            ByteArrayOutputStream bytes = null;
            if(subject_code.equalsIgnoreCase("All"))
            {
            	if(genderDep.equals("Y")){
            		//In format for B.Ed/Gender dependency 'YES'
            	bytes = (new ExportListForEducation()).generateExcelReport(university_id,entity_name, program_name);
            	}
            	else{
                bytes = (new ExportListForProgram()).generateExcelReport(university_id, entity_name, program_name);
            	}
            } else
            {
                bytes = (new ExportService()).generateExcelReport(university_id, entity_type, entity_name, program_name, subject_code,genderDep,subject_name);
            }
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", (new StringBuilder("attachment; filename=Internal_call_list_")).append(entity_type).append("_").append(entity_name).append("_").append(program_name).append("_").append(subject_code).append("_").append(genderDep).append(".xls").toString());
            response.setContentType("application/vnd.ms-excel");
            if(bytes != null)
            {
                response.getOutputStream().write(bytes.toByteArray());
            }
        }
        catch(WriteException e)
        {
            response.setContentType("text/plain");
            response.getWriter().print("An error as occured");
        }
    }
}
