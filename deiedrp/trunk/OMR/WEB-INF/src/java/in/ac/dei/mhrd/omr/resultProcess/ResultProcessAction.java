/*
 * @(#) ResultProcessAction.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
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
 * 
 */

package in.ac.dei.mhrd.omr.resultProcess;

import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.img.FileClass;
import in.ac.dei.mhrd.omr.img.RotateImg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.log4j.Logger;

import java.sql.*;
/**
 * This class fetches the testname selected by the user to process the response
 * sheets, locates the corresponding zipped folder on the server , extracts the
 * files from the zipped file into a folder, delete the zipped file creates the
 * rejected folder and processed folder on the server if not created, process
 * the sheets extracted from the zipped folder, transfers the sheets into
 * rejected folder or processed folder accordingly.
 * 
 * MyEclipse Struts 
 * Creation date: 02-04-2012
 * 
 * @author Upasana Kulshrestha 
 * @version 1.0
 * 
 */

public class ResultProcessAction extends Action {

	File unzippFolder;
	public static String RejectedFolderPath;
	/**
	 * @param filename
	 *            This method extracts the files from the zipped folder
	 */
	public void getZipFiles(String filename) {
		try {
			try {
				String reqPath ="";
				ZipFile zipFile = new ZipFile(filename);
				System.out.println(" "+ (zipFile.getName()));
				boolean b = false;
				File file =null;
				b= filename.contains("/");
				if(b){
					reqPath = filename.substring(0, filename.lastIndexOf("/"));
				}else{
					 reqPath = filename.substring(0, filename.lastIndexOf("\\"));
				}
				Enumeration<?> enu = zipFile.entries();
				while (enu.hasMoreElements()) {
					ZipEntry zipEntry = (ZipEntry)enu.nextElement();
					String name = zipEntry.getName();
                    if(b){
                    	file = new File(reqPath+ "/"+ name);
                    	System.out.println("file path : " + file.getAbsolutePath());
                    	if (name.endsWith("/")) {
                    		//System.out.println("inside if" + name.endsWith("/") );
                    		file.getAbsoluteFile().mkdirs();
                    		continue;
                    	}
                    }else{
                    	file = new File(reqPath+ "\\"+ name);
                    	if (name.endsWith("/")) {
    						//System.out.println("inside if");
    						file.getAbsoluteFile().mkdirs();
    						continue;
    					}
                    }
					unzippFolder = file.getParentFile();
					
					if (unzippFolder != null) {
						unzippFolder.getAbsoluteFile().mkdirs();
					}
					InputStream is = zipFile.getInputStream(zipEntry);
					FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile());
					byte[] bytes = new byte[1024];
					int length;
					while ((length = is.read(bytes)) >= 0) {
						fos.write(bytes, 0, length);
					}
					is.close();
					fos.close();
				}
				zipFile.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		} 
		catch (Exception e) {
			log.error("Error while extracting files from the zipped folder : "+ e);
			e.printStackTrace();
		}
	}
	private static Logger log = Logger.getLogger(ResultProcessAction.class);
	/**
	 * This method process the response sheets of the candidate, move the
	 * rejected sheets to rejectedfolder and processed sheets to processedfolder
	 * on the server. Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ResultProcessForm processSheetForm = (ResultProcessForm) form;
		String path = "";
		File ProcessedFolder = null;
		File rejectedFolder = null;
		String[] countinputFolderSheet = null;
		int testid;
		int totalQues = 0;
		int totatSec = 0;
		String instructorTestNo="0"; //TestNo given by the instructor 
		String testName = processSheetForm.getTestName();
		Connection con = null;
		if (isCancelled(request)) {
			return mapping.findForward("home");
		}
		int no_of_sheets = 0;
		String pathFlag;
		pathFlag = processSheetForm.getProcessSheetPath();

		request.setAttribute("ProcessSheetMsg", " ");

		try {
			con = Connect.prepareConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("select  TestId, TestNo, Total_question, Total_section from testheader where Test_name = ?");
			ps.setString(1, testName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			testid = rs.getInt(1);
			instructorTestNo=rs.getString(2);
			totalQues = rs.getInt(3);
			totatSec = rs.getInt(4);
			log.info("test Id retrieved : " + testid);
			ArrayList<String> pathInfo = new ArrayList<String>();

			// To store the path of all images in the folder
			// String s = processSheetForm.getProcessSheetPath();
			String imgPath = getServlet().getServletContext().getRealPath("/")+ "inputFolder" + "/" + testName;
            ProcessedFolder = new File(getServlet().getServletContext().getRealPath("/")+ "processedFolder" + "/" + testid);

			RejectedFolderPath = getServlet().getServletContext().getRealPath("/")+ "RejectedFolder" + "/" + testid;

			rejectedFolder = new File(getServlet().getServletContext().getRealPath("/")+ "RejectedFolder" + "/" + testid);

			/* creates the rejected folder if it does not exists */
			if (!rejectedFolder.exists()) {
				log.info(" inside rejected ");
				rejectedFolder.mkdirs();
			}

			/* if process folder has not yet been created, then create it */
			if (!ProcessedFolder.exists()) {
				ProcessedFolder.mkdirs();
			}
			File testNameFolder = new File(imgPath);
			File[] zippFile = testNameFolder.listFiles();
			
			/*
			 * checking whether sheets from input folder has been extracted from
			 * zipped file or not
			 */
			if (zippFile[0].isFile()) {
				imgPath = zippFile[0].toString();
				// extract files from the zipped folder
				getZipFiles(imgPath);
				path = unzippFolder.getAbsolutePath();
				System.out.println("path : " + path);
				countinputFolderSheet = unzippFolder.list();
				System.out.println("sheets "+countinputFolderSheet.length);

				// Delete the zipped folder once the sheets has been
				// extracted
				File del = new File(imgPath);
				del.delete();
			} else {
				path = zippFile[0].getPath();
			}
			FileClass fc = new FileClass();
			// this function returns the list of bmp files in the input
			// folder and move other files to the rejected folder
			pathInfo = fc.pathfunc(path, testid, RejectedFolderPath);
			/*
			 * This section of code sets the date and time when processing of
			 * the sheets starts
			 */
			ps = con.prepareStatement("update testheader set ProcessStartDate=now(), Test_Status = 'S' where Test_name=?");
			ps.setString(1, testName);
		    ps.executeUpdate();
			con.commit();
			ps = con.prepareStatement("select  count(group_code) from group_table where TestId = ?");
			ps.setInt(1, testid);
			ResultSet rs1 = ps.executeQuery();
			rs1.next();
			int count_group = rs1.getInt(1);
			for (String filepath : pathInfo) { // Each file is extracted & pass to the system for further processing
				System.out.println("Executing:" + filepath);
				no_of_sheets++;
				RotateImg obj = new RotateImg();
				
				obj.processSheetDual(filepath, testid, totalQues, RejectedFolderPath, instructorTestNo,count_group); 
				// process the sheet further
				File source = new File(filepath);
				boolean flag = source.renameTo(new File(ProcessedFolder, source.getName()));
				log.info("move file status " + flag);
				System.gc();
			}
			String[] countRejectedFolder = rejectedFolder.list();
			String[] countProcessedSheet = ProcessedFolder.list();
			/*
			 * if all sheets in the input folder are processed, and transferred
			 * to rejected and processed folder accordingly, Test_status is set
			 * to 'P' and Default result date is inserted
			 */
			request.setAttribute("TotalSheets", countinputFolderSheet.length);
			request.setAttribute("ProcessedSheets", countProcessedSheet.length);
			request.setAttribute("RejectedSheets", countRejectedFolder.length);
			if (countinputFolderSheet.length == (countProcessedSheet.length + countRejectedFolder.length)) {
				try {
					ps = con.prepareStatement("update testheader set ProcessEndDate=now(), Test_Status = 'P', ResultDisplayedFrom=now(), ResultDisplayedTo = DATE_ADD(now() ,INTERVAL '7' Day) where TestId=?");
					ps.setInt(1, testid);
					int updateTestHeaderStatus = ps.executeUpdate();
					con.commit();
					if (updateTestHeaderStatus != 1) {
						log.error("test Header couldn't updated after processing completes");
					}
				} 
				catch (Exception e) {
					log.error("Error in update test header in Process Action : "+ e);
				}

				/*
				 * set total number of sheets in the input folder, rejected
				 * folder and
				 */
				ps = con.prepareStatement("insert into testlog (TotalSheets, ProcessedSheets, RejectedSheets, Testid) values(?,?,?,?)");
				ps.setInt(1, countinputFolderSheet.length);
				ps.setInt(2, countProcessedSheet.length);
				ps.setInt(3, countRejectedFolder.length);
				ps.setInt(4, testid);
				int updatetestLog = ps.executeUpdate();
				con.commit();
				if (updatetestLog != 1) {
					log.error("test log couldn't updated after processing completes");
				}
				// delete the input folder if all sheets are processed
				unzippFolder.delete();
				unzippFolder.getParentFile().delete();
			}
		} 
		catch (Exception e) {
			log.error("error in Process Action  : " + e);
			System.out.println("Error in Process Action " + e);
		} 
		finally {
			Connect.freeConnection(con);
		}
		return mapping.findForward("index");
	}
}
