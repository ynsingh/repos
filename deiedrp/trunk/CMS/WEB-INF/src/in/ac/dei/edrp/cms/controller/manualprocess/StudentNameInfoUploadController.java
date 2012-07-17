/**
 * @(#) StudentNameInfoUploadController.java
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
package in.ac.dei.edrp.cms.controller.manualprocess;

import in.ac.dei.edrp.cms.dao.manualprocess.StudentNameInfoUploadDao;
import in.ac.dei.edrp.cms.domain.manualprocess.StudentNameInfoUploadBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


/**
 * @author Nupur Dixit
 * @date Dec/15/2011
 * @version 1.0
 */
public class StudentNameInfoUploadController extends MultiActionController{
	/** creating object of StudentNameInfoUploadDao interface */
	private StudentNameInfoUploadDao studentNameInfoUploadDao;

	/** defining setter method for object of StudentNameInfoUploadDao interface */
	public void setStudentNameInfoUploadDao(StudentNameInfoUploadDao studentNameInfoUploadDao) {
		this.studentNameInfoUploadDao = studentNameInfoUploadDao;
	}
	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(StudentNameInfoUploadController.class);

	/**
	 * This method upload the excel file name records into student_master and enrollment_personal_details tables of database
	 * and map the result info to a jsp
	 * @param request
	 * @param response
	 * @return ModelAndView containing success/failure Info
	 */
	public ModelAndView uploadStudentNameInfo(HttpServletRequest request,HttpServletResponse response) {		
		HttpSession session = request.getSession(true);
		String sep = System.getProperty("file.separator");				
		String entityId = request.getParameter("entityId");				
		//=================define local variables ==========================		
		String universityId = (String)session.getAttribute("universityId");
		String status = "false";
		String errorMessage = "False";
		int successRecords = 0;
		List<StudentNameInfoUploadBean> studentDetail = new ArrayList<StudentNameInfoUploadBean>();
		//================set variables in bean================
		StudentNameInfoUploadBean sub = new StudentNameInfoUploadBean();
		sub.setEntityId(entityId);
		sub.setUniversityId(universityId);
		String fileName = request.getParameter("fileName");
		String xlsPath = getServletContext().getRealPath(File.separator)+"WEB-INF"+sep+"input"+sep+fileName;
		System.out.println("file path "+xlsPath);
		InputStream inputStream = null;
		String extension = (fileName.indexOf(".")==-1?"invalid":fileName.substring(fileName.indexOf(".")+1));
		System.out.println("extension is :"+extension);
		if(!extension.equalsIgnoreCase("xls")){
			return new ModelAndView("ManualProcess/MyException","exception","File is invalid only .xls extension is supported");
		}
		try{
			inputStream = new FileInputStream (xlsPath);
		}
		catch (FileNotFoundException e){
			e.printStackTrace ();
			System.out.println ("File not found in the specified path.");
			return new ModelAndView("ManualProcess/MyException","exception","File not found in the specified path.");			
		}
		POIFSFileSystem fileSystem = null;
		try{
			fileSystem = new POIFSFileSystem (inputStream);
			HSSFWorkbook      workBook = new HSSFWorkbook (fileSystem);
			HSSFSheet         sheet    = workBook.getSheetAt (0);
			Iterator<HSSFRow> rows     = sheet.rowIterator ();	
			while (rows.hasNext ()){
				StudentNameInfoUploadBean bean = new StudentNameInfoUploadBean();
				bean.setEntityId(entityId);
				bean.setUniversityId(universityId);
				HSSFRow row = rows.next ();
				// display row number in the console.
				System.out.println ("Row No.: " + row.getRowNum ());
				// once get a row its time to iterate through cells.
				if(row.getRowNum()!=0){
					Iterator<HSSFCell> cells = row.cellIterator ();
					while (cells.hasNext ()){
						HSSFCell cell = cells.next ();			
						System.out.println ("Cell No.: " + cell.getCellNum ());						
						switch (cell.getCellNum ()){
						case 1 :{				
							// cell roll number.
							System.out.println ("roll value: " + String.valueOf(cell.getNumericCellValue()));	
							bean.setRollNumber( String.valueOf(cell.getNumericCellValue()));							
							break;
						}			
						case 2 :{				
							// cell student name
							HSSFRichTextString richTextString = cell.getRichStringCellValue ();							
							System.out.println ("studnet name: " + richTextString.getString ());							
							break;
						}	
						case 3 :{				
							// cell father name
							HSSFRichTextString richTextString = cell.getRichStringCellValue ();							
							System.out.println ("father name: " + richTextString.getString ());	
							bean.setFatherNameEnglish(richTextString.getString ());							
							break;
						}	
						case 4 :{				
							// cell mother name
							HSSFRichTextString richTextString = cell.getRichStringCellValue ();							
							System.out.println ("mother name: " + richTextString.getString ());
							bean.setMotherNameEnglish(richTextString.getString ());						
							break;
						}								
						case 5 :{				
							// cell dob name
							HSSFRichTextString richTextString = cell.getRichStringCellValue ();							
							System.out.println ("dob value:: " + richTextString.getString ());
							Date date = null ; 
							try {  
								String str_date=richTextString.getString ();
								DateFormat formatter ; 
								formatter = new SimpleDateFormat("dd.MM.yyyy");
								date = (Date)formatter.parse(str_date);  
								System.out.println("dob is " +date );
							} catch (ParseException e){
								System.out.println("Exception :"+e);  									    								 
							}
							bean.setDob(date);														
							break;
						}	
						case 6 :{				
							// cell enrollment number name
							System.out.println ("enrollment value: " + String.valueOf(cell.getNumericCellValue()));	
							bean.setEnrollmentNumber(String.format("%06d",(int)(cell.getNumericCellValue())));						
							break;
						}	
						case 7 :{				
							// cell student hindi name
							System.out.println ("student hindi name: " + cell.getRichStringCellValue ()+"\n"+URLEncoder.encode(cell.getRichStringCellValue().toString(),"utf-8").replace("+", " "));	
							bean.setStudentNameHindi(URLEncoder.encode(cell.getRichStringCellValue().toString(),"utf-8").replace("+", " "));						
							break;
						}	
						case 8 :{				
							// cell father hindi name
							System.out.println ("father hindi name: " + cell.getRichStringCellValue ()+"\n"+URLEncoder.encode(cell.getRichStringCellValue().toString(),"utf-8").replace("+", " "));	
							bean.setFatherNameHindi(URLEncoder.encode(cell.getRichStringCellValue().toString(),"utf-8").replace("+", " "));						
							break;
						}	
						case 9 :{				
							// cell mother hindi name
							System.out.println ("mother hindi name: " + cell.getRichStringCellValue ()+"\n"+URLEncoder.encode(cell.getRichStringCellValue().toString(),"utf-8").replace("+", " "));	
							bean.setMotherNameHindi(URLEncoder.encode(cell.getRichStringCellValue().toString(),"utf-8").replace("+", " "));						
							break;
						}	
						default :{				
							// types other than String and Numeric.
							System.out.println ("Type not supported.");								
							break;
						}
						}//end switch
					}	//end for cell
					studentDetail.add(bean);
				}//end if
			}//end outer while
			for(StudentNameInfoUploadBean upload:studentDetail){
				status = studentNameInfoUploadDao.uploadStudentNameInfo(upload);
				if(status.equalsIgnoreCase("true")){
					successRecords = successRecords+1;
				}
				else{
					logObj.error("\n Error in processing result with enrollment number: "+upload.getEnrollmentNumber());
				}
			}
			System.out.println("total records :"+studentDetail.size()+" successRecords "+successRecords);
			if(successRecords==0){
				errorMessage="No matching records to process";
			}
			if(successRecords==studentDetail.size()){
				errorMessage="All Records are processed successfully";
			}
			if(successRecords>0){
				errorMessage="Out of "+studentDetail.size()+" Records , "+successRecords+" Records are processed successfully";
			}
//			if(status.equalsIgnoreCase("false")){
//				errorMessage = "Some Error in the processing kindly view error log";
//			}
		}//end try
		catch (IOException e){
			e.printStackTrace ();
		}
		catch (Exception e){
			e.printStackTrace ();
		}
		return new ModelAndView("ManualProcess/MyException","exception",errorMessage);
	}//end method
}
