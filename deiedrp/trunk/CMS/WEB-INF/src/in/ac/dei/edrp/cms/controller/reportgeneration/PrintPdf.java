/**
 * @(#) SystemTableTwoController.java
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
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collection;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFRenderer;

/**
 * Converts the PDF content into printable format
 */
public class PrintPdf extends MultiActionController{

	private PrinterJob pjob = null;
//	private DocPrintJob docJob = null;
//	private ReportPrintDao reportPrintDao;
	String fileName = null;
	PrintService selectedService = null;
	static ProgressCardInfo progressCardInfo = new ProgressCardInfo();		
	
	public PrintPdf(){
		System.out.println("in constructors");		
	}
	
	public ModelAndView printPDFDoc(Collection<File> file,String semesterStartDate,String semesterEndDate,String entityId,String reportType,String session,ReportPrintDao reportPrintDao) throws IOException, PrinterException, PrintException {		
		String location = null;
		/*
		 * setting the values in bean 
		 */
		progressCardInfo.setSemesterStartDate(semesterStartDate);
		progressCardInfo.setSemesterEndDate(semesterEndDate);
		progressCardInfo.setEntityId(entityId);
		progressCardInfo.setReportType(reportType);
		progressCardInfo.setSession(session);				
		Collection<File> fileCollection = file;	
		/*
		 * traversing through the collection of files through location..
		 */
		for(File fileLocation : fileCollection){
			location = fileLocation.getPath();
			fileName = fileLocation.getPath();
			progressCardInfo.setFileName(fileName);
			System.out.println("length of the location "+location.length());
			/*
			 * if location of the file is not found
			 */
			if (location.length() <=0) {
				System.err.println("The first parameter must have the location of the PDF file to be printed");
			}		
		
			// Create a PDFFile from a File reference
			FileInputStream fis = new FileInputStream(location);
			PrintPdf printPDFFile = new PrintPdf(fis, fileLocation.getName());
			printPDFFile.print();
			/*
			 * Calling the impl file
			 */
			reportPrintDao.progressCardPrint(progressCardInfo);
		}
		return null;
	}

	public ModelAndView printPDFDoc(Collection<File> file,ReportPrintDao reportPrintDao, ReportLogBean reportPrintBean) throws IOException, PrinterException, PrintException {	
		String location = null;						
		Collection<File> fileCollection = file;	
		String result = "false";
		/*
		 * traversing through the collection of files through location..
		 */
		for(File fileLocation : fileCollection){
			location = fileLocation.getPath();
			fileName = fileLocation.getPath();
			System.out.println("location "+location +"file name :"+fileName);
			progressCardInfo.setFileName(fileName);
			System.out.println("length of the location "+location.length());
			/*
			 * if location of the file is not found
			 */
			if (location.length() <=0) {
				System.err.println("The first parameter must have the location of the PDF file to be printed");
			}		
			// Create a PDFFile from a File reference
			FileInputStream fis = new FileInputStream(location);
			PrintPdf printPDFFile = new PrintPdf(fis, fileLocation.getName());
			try {
				printPDFFile.print();
				result = "true";
			} catch (Exception e) {
				result = "false";
				break;
				// TODO: handle exception
			}
			
			/*
			 * Calling the impl file
			 */
//			reportPrintDao.progressCardPrint(progressCardInfo);
		}
		if(result.equalsIgnoreCase("false")){
			String message = "false-There is some error in report printing kindly view the log table!!";
	    	return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);	
		}
		else{
			return new ModelAndView("activitymaster/SubmitSuccesful", "message", "true");	
		}		
	}

	/**
	 * Constructs the print job based on the input stream
	 * 
	 * @param inputStream
	 * @param jobName
	 * @throws IOException
	 * @throws PrinterException
	 */
	public PrintPdf(InputStream inputStream, String jobName) throws IOException, PrinterException {
		byte[] pdfContent = new byte[inputStream.available()];
		inputStream.read(pdfContent,0, inputStream.available());		
		initialize(pdfContent,jobName);
	}

	/**
	 * Constructs the print job based on the byte array content
	 * 
	 * @param content
	 * @param jobName
	 * @throws IOException
	 * @throws PrinterException
	 */
	public PrintPdf(byte[] content, String jobName) throws IOException, PrinterException {
		initialize(content, jobName);
	}

	/**
	 * Initializes the job
	 * 
	 * @param pdfContent
	 * @param jobName
	 * @throws IOException
	 * @throws PrinterException
	 */
	private void initialize(byte[] pdfContent, String jobName) throws IOException, PrinterException {		
		ByteBuffer bb = ByteBuffer.wrap(pdfContent);
		// Create PDF Print Page		
		PDFFile pdfFile = new PDFFile(bb);
		PDFPrintPage pages = new PDFPrintPage(pdfFile);		
		// Create Print Job
		pjob = PrinterJob.getPrinterJob();				
		PageFormat pf = PrinterJob.getPrinterJob().defaultPage();		
		pjob.setJobName(jobName);
		Book book = new Book();
		book.append(pages, pf, pdfFile.getNumPages());
		pjob.setPageable(book);		
		// to remove margins
		Paper paper = new Paper();
		paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
		pf.setPaper(paper);
	}

	public void print() throws PrinterException, PrintException {
		// Send print job to default printer
		String rollNumber = null ;
		int index = 0;
		String acknowledge;
		System.out.println("in printing the job for service "+progressCardInfo.getSelectedService());
		System.out.println(pjob.getJobName());		
		/*
		 * extracting the roll number from the job name which is as per the saved file.
		 */
		if(pjob.getJobName() != null){
			index = pjob.getJobName().toString().lastIndexOf("_");
			rollNumber = pjob.getJobName().toString().substring(index+1, pjob.getJobName().toString().lastIndexOf("."));
			System.out.println("roll number is "+rollNumber);		
		}
		
		DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet(); 
		PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);
		
		if(progressCardInfo.getSelectedService() == null){
			pjob.printDialog();
			selectedService = pjob.getPrintService();		
			progressCardInfo.setSelectedService(selectedService);
		}
		 // Open the image file	    
		try {
			InputStream  is = new BufferedInputStream(new FileInputStream(progressCardInfo.getFileName()));		
		
	    // Create the print job
	    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
	    DocPrintJob job = progressCardInfo.getSelectedService().createPrintJob();
	    Doc doc = new SimpleDoc(is, flavor, null);
	    PrintJobWatcher watcher = new PrintJobWatcher(job);
	    
	    // Print it
	    System.out.println("printing the job for doc");	    
	    job.print(doc, null);
	    System.out.println("waiting for the job");
		acknowledge = watcher.waitForDone();
		progressCardInfo.setRollNumber(rollNumber);
		System.out.println("acknowledge "+acknowledge);
		if(acknowledge.equalsIgnoreCase("jobDone")){		
			progressCardInfo.setErrorCode("");
			progressCardInfo.setStatus("Y");
		}
		else {
			progressCardInfo.setErrorCode(acknowledge);			
			progressCardInfo.setStatus("N");
		}
		System.out.println("b4 sending to impl");		
		} catch (FileNotFoundException e) {
			System.out.println("Excption"+e.getMessage());
		}
	    // Monitor print job events	

	//	docJob = progressCardInfo.getSelectedService().createPrintJob();
		
			
		// end of docPrintJob	
		System.out.println("getting the service "+progressCardInfo.getSelectedService());			
		pjob.setPrintService(progressCardInfo.getSelectedService());		
//		   PrintService myPrinter = null;  
//		         for (int i = 0; i < services.length; i++){  
//		        	 String svcName = services[i].toString(); 
//		             System.out.println("service found: "+svcName); 
//		             
//		             if (svcName.contains("printer closest to me")){		            	 
//		                 myPrinter = services[i];  
//		                 System.out.println("my printer found: "+svcName);  
//		                 break;  
//		             }  
//		         }  
//		         System.out.println("myprinter "+myPrinter);
					
	
//		pjob.setCopies(1);		
	//	pjob.print();
			
		
	}
}

/**
 * Class that actually converts the PDF file into Printable format
 */
class PDFPrintPage implements Printable {

	private PDFFile file;

	PDFPrintPage(PDFFile file) {
		this.file = file;
	}

	public int print(Graphics g, PageFormat format, int index) throws PrinterException {
		int pagenum = index + 1;
		if ((pagenum >= 1) && (pagenum <= file.getNumPages())) {
			Graphics2D g2 = (Graphics2D) g;
			PDFPage page = file.getPage(pagenum);

			// fit the PDFPage into the printing area
			Rectangle imageArea = new Rectangle((int) format.getImageableX(), (int) format.getImageableY(),
					(int) format.getImageableWidth(), (int) format.getImageableHeight());
			g2.translate(0, 0);
			PDFRenderer pgs = new PDFRenderer(page, g2, imageArea, null, null);
			try {
				page.waitForFinish();
				pgs.run();
			} catch (InterruptedException ie) {
					System.out.println(ie.getMessage());
			}
			return PAGE_EXISTS;
		} else {
			return NO_SUCH_PAGE;
		}
	}
	
}
