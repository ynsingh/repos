/*
 * @(#) UploadResponseAction.java
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
 */

package in.ac.dei.mhrd.omr.uploadSheet;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Locale;
import java.util.ResourceBundle;

import in.ac.dei.mhrd.omr.dbConnection.Connect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import org.apache.log4j.Logger;

/** 
 * 
 * This class fetches the test name for which response sheets has been uploaded, creates a folder 
 * on the server with the name that of the test and uploads the zipped folder containing
 * the response sheets of the candidate in that folder.
 * After successfully uploading the zipped folder the upload_status in the testheader is set to 'UPD' indicating that sheets 
 * has been uploaded for the selected test. 

 * MyEclipse Struts
 * Creation date: 11-18-2010
 * @version 1.0
 * @author Anshul Agarwal
 *   XDoclet definition:
 * @struts.action path="/uploadzip" name="uploadResponseSheet" input="/uploadResponse.jsp" scope="request" validate="true"
 * @struts.action-forward name="home" path="/Home.jsp"
 * 
 * 
 */
public class UploadResponseAction extends Action {
	/*
	 * Generated Methods
	 */

	private static Logger log = Logger.getLogger(UploadResponseAction.class);

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * This method creates a folder with the name as that of the testid of the selected testname on the server 
	 * and  uploads the zipped file in that folder 
	 */                                        

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UploadResponseSheetForm uploadResponseSheet = (UploadResponseSheetForm) form;// TODO Auto-generated method stub
		
		Locale obj = new Locale("en", "US");
		ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);
        Connection con =null; 
		
		try{
			if(isCancelled(request)){
				return mapping.findForward("home");
			}
			//get the uploaded ziped folder
			FormFile responseZipFile  = uploadResponseSheet.getZippedFolder();
			String ContentType = responseZipFile.getContentType();
			String fileName = responseZipFile.getFileName();
           //get the file data			
			byte[] fileData = responseZipFile.getFileData();
			//create input folder
			File fol = new File(getServlet().getServletContext().getRealPath("/"),"inputFolder");
			fol.mkdir();
			//create folder with the name as that of the test name
			new File(getServlet().getServletContext().getRealPath("/")+"inputFolder"+"/"+uploadResponseSheet.getTestName()).mkdir();
			String filePath = getServlet().getServletContext().getRealPath("/")+"inputFolder"+"/"+uploadResponseSheet.getTestName();
			
			//initial message
			request.setAttribute("fileName", "File couldn't uploaded");

			if(!fileName.equals("")){
			     
			    File fileToCreate = new File(filePath, fileName);
			    
			    //if file does not exist upload the file on the server
			    
			    if(!fileToCreate.exists()){
			    	FileOutputStream fo = new FileOutputStream(fileToCreate);
			    	fo.write(responseZipFile.getFileData());
			    	
			    	fo.flush();
			    	fo.close();
			    }
			}  
			
	/*
	 * This section of code updates the upload_status in the testheader
	 */		
			
			//get database connection
			 con = Connect.prepareConnection();
			
			con.setAutoCommit(false);
			PreparedStatement uploadStatus = con.prepareStatement("update testheader set upload_status = 'UPD' where Test_name = ?");
			uploadStatus.setString(1, uploadResponseSheet.getTestName());
			int countUpload= uploadStatus.executeUpdate();
			con.commit();
            //check if upload_status in the testheader table updated successfully 
			if(countUpload==1){
				request.setAttribute("ProcessSheetMsg", "Sheets successfully uploaded for the test "+uploadResponseSheet.getTestName());
				log.info("File uploaded sucessfully and upload_status updated");
			}
			
			request.setAttribute("fileName", fileName);
			fol.delete();
			}catch(Exception e){
				log.error("Error while uploading file for the test : " +uploadResponseSheet.getTestName());
				e.printStackTrace();
			}finally{
				Connect.freeConnection(con);
			}
		return mapping.findForward("index");
		}
	}
