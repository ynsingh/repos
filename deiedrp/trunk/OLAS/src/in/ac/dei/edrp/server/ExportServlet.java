package in.ac.dei.edrp.server;

/*
 * Copyright 2010 Jeremy Wallez
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WriteException;

/**
 * 
 * @author <a href="mailto:jeremy.wallez@z80.fr>Jeremy Wallez</a>
 * @version 1.0
 */
public class ExportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			System.out.println("Coming here");
			String university_id=request.getParameter("param");
			String entity_type=request.getParameter("param1");
			String entity_name=request.getParameter("param2");
			String program_name=request.getParameter("param3");
			String branch_name=request.getParameter("param4");
			
			System.out.println("inside servlet"+university_id+" type: "+entity_type+" name: "+entity_name+" program: "+program_name+" branch: "+branch_name);
			
			/* Get Excel Data */
			ByteArrayOutputStream bytes = new ExportService().generateExcelReport(university_id,entity_type,entity_name,program_name,branch_name);
			
			/* Initialize Http Response Headers */
			response.setHeader("Content-disposition", "attachment; filename=Internal_call_list_"+entity_type+"_"+program_name+"_"+branch_name+".xls");
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