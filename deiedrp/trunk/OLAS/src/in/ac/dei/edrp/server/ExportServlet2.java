package in.ac.dei.edrp.server;



	import java.io.ByteArrayOutputStream;
	import java.io.IOException;

	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

import jxl.write.WriteException;

	public class ExportServlet2 extends HttpServlet {

		private static final long serialVersionUID = 1L;

		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
			try {
				System.out.println("Coming here 2");
				
				String university_id=request.getParameter("param");
				String entity_type=request.getParameter("param1");
				String entity_name=request.getParameter("param2");
				String program_name=request.getParameter("param3");
				String branch_name=request.getParameter("param4");
				
				System.out.println("inside servlet2"+university_id+" type: "+entity_type+" name: "+entity_name+" program: "+program_name+" branch: "+branch_name);
				
				/* Get Excel Data */
				ByteArrayOutputStream bytes = new ExportService2().generateExcelReport(university_id,entity_type,entity_name,program_name,branch_name);
				
				/* Initialize Http Response Headers */
				response.setHeader("Content-disposition", "attachment; filename=Selected_Candidate_"+entity_type+"_"+program_name+"_"+branch_name+".xls");
				response.setContentType("application/vnd.ms-excel");
				
				/* Write data on response output stream */
				if (bytes != null) {
					response.getOutputStream().write(bytes.toByteArray());
				}
			} catch (WriteException e) {
				response.setContentType("text/plain");
				response.getWriter().print("An error as occured");
			}//catch ends
		}//doget ends
}//class ends

