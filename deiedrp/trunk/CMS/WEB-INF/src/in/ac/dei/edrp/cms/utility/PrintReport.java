package in.ac.dei.edrp.cms.utility;

import in.ac.dei.edrp.cms.controller.reportgeneration.PrintJobWatcher;
import in.ac.dei.edrp.cms.controller.reportgeneration.PrintPdf;
import in.ac.dei.edrp.cms.dao.report.ReportDao;
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
import javax.print.attribute.standard.OrientationRequested;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.web.servlet.ModelAndView;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFRenderer;

public class PrintReport extends SqlMapClientDaoSupport{
	private ReportDao reportDao;
	public void setReportDao(ReportDao reportDao){
		this.reportDao = reportDao;		
	}
	private PrinterJob pjob = null;
	String fileName = null;
	PrintService selectedService = null;
	static ProgressCardInfo progressCardInfo = new ProgressCardInfo();
	PageFormat pf = null;
	Paper paper = new Paper();
	
	public PrintReport(){
		System.out.println("in constructors");		
	}
	/**
	 * Constructs the print job based on the input stream
	 * 
	 * @param inputStream
	 * @param jobName
	 * @throws IOException
	 * @throws PrinterException
	 */
	public PrintReport(InputStream inputStream, String jobName) throws IOException, PrinterException {
		byte[] pdfContent = new byte[inputStream.available()];
		inputStream.read(pdfContent,0, inputStream.available());		
		initialize(pdfContent,jobName);
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
//		PageFormat pf = new PageFormat();
//		pf.setOrientation(PageFormat.LANDSCAPE);
		pjob = PrinterJob.getPrinterJob();	
//		pjob.defaultPage(pf);
//		PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
		pf = PrinterJob.getPrinterJob().defaultPage();
		pf.setOrientation(PageFormat.LANDSCAPE);
		pjob.setJobName(jobName);
		Book book = new Book();
		book.append(pages, pf, pdfFile.getNumPages());
		pjob.setPageable(book);		
		// to remove margins
		
//		paper.setImageableArea(0,0,paper.getWidth() * 2,paper.getHeight());
//		pf.setPaper(paper);
		paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
//		pf.setPaper(paper);
	}
	
	public String printPDFDoc(Collection<File> file, ReportLogBean reportPrintBean,ReportLogBean reportControlBean) 
				throws IOException, PrinterException, PrintException {
		String location = null;						
		Collection<File> fileCollection = file;	
		String result = "false";
		reportControlBean.setReportPrinted("Y");
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
			System.out.println("\n location for input stream "+location);
			if(fileName.endsWith(".pdf")){
				FileInputStream fis = new FileInputStream(location);
				PrintReport printPDFFile = new PrintReport(fis, fileLocation.getName());
				try {
					result = printPDFFile.print(reportPrintBean);				
				} catch (Exception e) {
					System.out.println("inside printPDFDoc exception");
                    e.printStackTrace();
					result = "false";
					//break;
				}
			}else if(fileName.endsWith(".doc")){
				try {
					Runtime.getRuntime().exec("start /min winword \"" + location + "\" /q /n /f /mFilePrint /mFileExit");	
					result = "true";
				} catch (Exception e) {
					System.out.println("inside printPDFDoc exception of doc");
                    e.printStackTrace();
					result = "false";
				}					
				//System.getRuntime().exec("start /min winword \"" + fileName + "\" /q /n /f /mFilePrint /mFileExit");								
			}
			else{
				result = "true";
			}
			
			if(result.equalsIgnoreCase("false")){
				reportControlBean.setReportPrinted("N");
			}
			/*
			 * Calling the impl file
			 */
//			reportPrintDao.progressCardPrint(progressCardInfo);
		}
		reportDao.updateReportControlLogForPrint(reportControlBean);
		//calling the impl to do updation in control log
		/*if(result.equalsIgnoreCase("false")){
			String message = "false-There is some error in report printing kindly view the log table!!";
//	    	return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);	
		}
		else{
//			return new ModelAndView("activitymaster/SubmitSuccesful", "message", "true");
			String message = "true";
		}*/
		return result;
	}

	public String print(ReportLogBean reportPrintBean) throws PrinterException, PrintException {
		// Send print job to default printer
		String rollNumber = null ;
		int index = 0;
		String acknowledge;
		String result = "false";
		System.out.println("in printing the job for service "+progressCardInfo.getSelectedService());
		System.out.println(pjob.getJobName());		
		/*
		 * extracting the roll number from the job name which is as per the saved file.
		 */
		if(pjob.getJobName() != null){
			if(Integer.parseInt(reportPrintBean.getReportCode())==8){
				index = pjob.getJobName().toString().lastIndexOf("-");
				rollNumber = pjob.getJobName().toString().substring(index+1, pjob.getJobName().toString().lastIndexOf("."));
				System.out.println("roll number is "+rollNumber);
				reportPrintBean.setStudentRollNumber(rollNumber);
			}					
		}
		System.out.println("after getjobname null if ");
		DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		if(Integer.parseInt(reportPrintBean.getReportCode())==8){
			pf.setOrientation(PageFormat.PORTRAIT);			
		}
		pf.setPaper(paper);
		PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);
		
		if(progressCardInfo.getSelectedService() == null){
			pjob.printDialog();
			selectedService = pjob.getPrintService();	
            System.out.println("selected service after dialog box "+selectedService);
			progressCardInfo.setSelectedService(selectedService);
		}
		System.out.println("after getselectedservice null if selective service si "+selectedService);
		 // Open the image file	    
		try {
			/*InputStream  is = new BufferedInputStream(new FileInputStream(progressCardInfo.getFileName()));		
			System.out.println("before creating print job ");
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
//		progressCardInfo.setRollNumber(rollNumber);
		System.out.println("acknowledge "+acknowledge);
		if(acknowledge.equalsIgnoreCase("jobDone")){		
			progressCardInfo.setErrorCode("");
			progressCardInfo.setStatus("Y");
			result = "true";
		}
		else {
			result = "false";
			reportPrintBean.setErrorCode("501");
			reportPrintBean.setPrintStatus("N");
			progressCardInfo.setErrorCode(acknowledge);			
			progressCardInfo.setStatus("N");
		}
		System.out.println("b4 sending to impl");*/		
			pjob.print();
			result="true";
		} catch (Exception e) {
			result="false";
			reportPrintBean.setErrorCode("502");
			reportPrintBean.setPrintStatus("N");
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
		if(result.equalsIgnoreCase("false")){
			reportDao.insertReportPrintLog(reportPrintBean);
			//do entry in print log
		}
		return result;
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

	public int print123(Graphics g, PageFormat format, int index) throws PrinterException {
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
	
	
	public int print(Graphics g, PageFormat format, int index) throws PrinterException {
    int pagenum = index + 1;
 
    // don't bother if the page number is out of range.
    if ((pagenum >= 1) && (pagenum <= file.getNumPages())) {
      // fit the PDFPage into the printing area
      Graphics2D g2 = (Graphics2D)g;
      PDFPage page = file.getPage(pagenum);
      double pwidth = format.getImageableWidth();
      double pheight = format.getImageableHeight();
 
      double aspect = page.getAspectRatio();
      double paperaspect = pwidth / pheight;
 
      Rectangle imgbounds;
 
      if (aspect>paperaspect) {
    	  System.out.println("inside aspect>paperaspect");
        // paper is too tall / pdfpage is too wide
        int height= (int)(pwidth / aspect);
        imgbounds= new Rectangle(
          (int)format.getImageableX(),
          (int)(format.getImageableY() + ((pheight - height) / 2)),
          (int)pwidth,
          height
        );
      } else {
    	  System.out.println("inside else");
        // paper is too wide / pdfpage is too tall
        int width = (int)(pheight * aspect);
        imgbounds = new Rectangle(
          (int)(format.getImageableX() + ((pwidth - width) / 2)),
          (int)format.getImageableY(),
          width,
          (int)pheight
        );
      }
 
      // render the page
      PDFRenderer pgs = new PDFRenderer(page, g2, imgbounds, null, null);
      try {
        page.waitForFinish();
        pgs.run();
      } catch (InterruptedException ie) {}
 
      return PAGE_EXISTS;
    } else {
      return NO_SUCH_PAGE;
    }
  }

	
}
