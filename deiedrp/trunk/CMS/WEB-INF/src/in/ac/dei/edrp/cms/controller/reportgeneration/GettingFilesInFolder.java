/**
 * @(#) GettingFilesInfFolder.java
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
 * Redistribution in binary form must reproducuce the above copyright
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

package in.ac.dei.edrp.cms.controller.reportgeneration;
import in.ac.dei.edrp.cms.dao.reportgeneration.ReportPrintDao;

import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


public class GettingFilesInFolder extends MultiActionController{
	
	private ReportPrintDao reportPrintDao;
	
	public void setReportPrintDao(ReportPrintDao reportPrintDao){
		this.reportPrintDao = reportPrintDao;		
	}
	/**
	 * This methods collects the files in a folder 
	 * @param request for getting the parameter from the flex side
	 * @param response 
	 * @return Model And view type of object
	 */
	public ModelAndView recursiveChild(HttpServletRequest request,HttpServletResponse response) {
	    final Collection<File> all = new ArrayList<File>();	    
	    System.out.println(getServletContext().getRealPath("/"));
	    System.out.println("in print pdf for getting the folders");
	    String reportType = null;
	    String programId = request.getParameter("programId");
	    String programName = request.getParameter("programName");
	    String entityId = request.getParameter("entityId");
	    String semesterSequence = request.getParameter("semesterSequence");
	    String semesterStartDate = request.getParameter("semesterStartDate");
	    String semesterEndDate = request.getParameter("semesterEndDate");
	    String semesterId = request.getParameter("semesterId");
	    String sessionStart = request.getParameter("sessionStart");
	    System.out.println(sessionStart);
	    int sessionEnd = Integer.parseInt(sessionStart.substring(2, 4))+1;
	    String session = sessionStart + "-" +sessionEnd;
	    
	    System.out.println(entityId+" "+ programId+" "+programName+" "+semesterEndDate+" "+semesterStartDate+" "+semesterId);
	    /*
	     * finding the report type
	     */
	    if(Integer.parseInt(semesterSequence)%2==0){
	    	reportType = "FC";
	    }
	    else 
	    	reportType = "PC";
	    String initialPath = getServletContext().getRealPath("/");
	    String path = null;
	    
	    /*
	     * traversing the files present in a folder on the server
	     * Reaching to the location of the folder and than listing all the files in the folder.
	     */
	    
	    path = initialPath+"PDF";
	    File fileVerify = new File(path);
	    if(fileVerify.exists())
	    {	    	    
	    	fileVerify  = new File(path+"\\"+"ProgressCard");	    	
	    	if(fileVerify.exists())
	    	{	    		
	    		path = path+"\\ProgressCard";
	    		fileVerify = new File(path);
	    			if(fileVerify.exists())
	    			{	    		    				
	    				path = path +"\\"+programName;
	    				fileVerify = new File(path);
		    				if(fileVerify.exists())
		    				{
		    				path = path +"\\"+ semesterSequence + "_" + semesterStartDate + "_" +semesterEndDate;		    				
		    				fileVerify = new File(path);
		    					if(fileVerify.exists())
		    					{
		    						addFilesRecursively(new File(path), all);
		    					}
		    					else System.out.println("semester start date end date folder not exists");
		    				}
		    				else System.out.println("program name not exists");		    				
	    			}
	    		
	    	}
	    	
	    	else
	    	{
	    		System.out.println("progress card folder not exist");
	    	}
	    }
	    else
	    {
	    	System.out.println("folder PDF not created");
	    }
	    	   

	    /*
	     * Creating object of PrintPdf Class And calling its method printPdfDoc 
	     * passing all the files present in the folder
	     */	    
	    PrintPdf printPdf = new PrintPdf();
	    try {
			printPdf.printPDFDoc(all,semesterStartDate,semesterEndDate,entityId,reportType,session,reportPrintDao);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PrinterException e) {
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	    return null;
	}
	/**
	 * adding the files recursively in a folder
	 * @param file 
	 * @param all bind up all the files
	 */
	private static void addFilesRecursively(File file, Collection<File> all) {
	    final File[] children = file.listFiles();
	    if (children != null) {
	        for (File child : children) {
	            all.add(child);
	            addFilesRecursively(child, all);
	        }
	    }
	}
}
