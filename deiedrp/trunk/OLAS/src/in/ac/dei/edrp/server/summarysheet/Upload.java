/*
 * @(#) Upload.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */


package in.ac.dei.edrp.server.summarysheet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

@SuppressWarnings("serial")
public class Upload extends HttpServlet {
	Log4JInitServlet logObj = new Log4JInitServlet();
	public Upload() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String fileName = request.getParameter("fileName");
		String filePath = this.getServletContext().getRealPath("/") +
		request.getParameter("folderName");


		try {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();

			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();

				if (!item.isFormField()) {

					String recievedFileName = item.getName().toLowerCase();

					String fileType = recievedFileName.substring(recievedFileName.lastIndexOf(".") + 1);
					File file = new File(filePath);
					file.mkdirs();
					filePath = filePath + File.separator + fileName + "." +fileType;
					System.out.println("file path "+filePath);

					byte[] data = item.get();
					FileOutputStream fileOutSt = new FileOutputStream(filePath);
					fileOutSt.write(data);
					fileOutSt.close();
				} 
			}
		} catch (Exception e) {
			logObj.logger.error(e.getMessage());
			throw new IOException(e.getMessage());
		}
	}

	public void init() throws ServletException {
	}
}
