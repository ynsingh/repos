package in.ac.dei.edrp.cms.controller.report;

import in.ac.dei.edrp.cms.controller.reportgeneration.PrintPdf;
import in.ac.dei.edrp.cms.dao.reportgeneration.ReportPrintDao;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.utility.PrintReport;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.print.PrintException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


public class PrintController extends MultiActionController{

	public static Logger logObj=Logger.getLogger(PrintController.class);
	private PrintReport printReport;
	
	public void setPrintReport(PrintReport printReport){
		this.printReport = printReport;		
	}
	
	/**
	 * This method get list of entities from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing entity List
	 */
	public ModelAndView printReport(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String message = "";
		String result = "true";
		ModelAndView printPdfModel = null;
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		ReportLogBean reportControlBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("reportCode"),"","",
				request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"),
				session.getAttribute("userName").toString());
		
		ReportLogBean reportPrintBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
				request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("reportCode"),"",request.getParameter("rollNumber"),
				request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4),"");
		
		String reportPath = request.getParameter("reportPath");
		System.out.println("report path is :"+reportPath);
		String fileName = request.getParameter("fileName");
		System.out.println("File Name is :"+ fileName);
		String reportCode = request.getParameter("reportCode");
		String reportType = request.getParameter("reportType");
		final Collection<File> all = new ArrayList<File>();	   
	    System.out.println(getServletContext().getRealPath("/"));
	    System.out.println("in print pdf for getting the folders");
	    String initialPath = getServletContext().getRealPath("/");
	    reportPath = initialPath+reportPath;
	    System.out.println("Report Path : " + reportPath);
	    File fileVerify = new File(reportPath);
	    if(fileVerify.exists()){
//	    	if(Integer.parseInt(request.getParameter("reportCode"))==8){
//	    		addFilesRecursively(new File(reportPath), all);
//	    	}
//	    	else{
	    		if(reportType.equalsIgnoreCase("ABR")){
	    			System.out.println("Inside overloaded method");
	    			addFilesRecursively(new File(reportPath), all, fileName);
	    		}else{
	    		System.out.println("inside else of report code 8");
	    		addFilesRecursively(new File(reportPath), all);
	    		}
//	    	}	
	    	
	    	 /*
		     * Creating object of PrintPdf Class And calling its method printPdfDoc 
		     * passing all the files present in the folder
		     */	    
//		    PrintReport printPdf = new PrintReport();
		    try {
		    	result = printReport.printPDFDoc(all,reportPrintBean,reportControlBean);
			} catch (IOException e) {
				result="false";
				e.printStackTrace();
			} catch (PrinterException e) {
				result="false";
				e.printStackTrace();
			} catch(Exception e){
				result="false";
				System.out.println(e.getMessage());
			}	
	    }  		
	    else{
	    	System.out.println("The Report is not yet Generated....");
	    	message = "The Report is not yet Generated......";
	    	return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);	
	    }
		//reportLogBean.setIsGenerated(request.getParameter("isGenerated")==null?"no":"yes");	
		request.setAttribute("reportPath", reportPath);
		request.setAttribute("reportPrintBean", reportPrintBean);
		
		//request.getRequestDispatcher(reportPath);
//		List<ReportPathBean> entityList = consolidatedChartService
//				.getEntityList(courseMasterBean);
		return new ModelAndView("activitymaster/SubmitSuccesful", "message", result);
		/*ModelAndView modelAndView = new ModelAndView("path");

		 modelAndView.addObject("path", reportPath); */
		//return new ModelAndView("forward:/REN_11/generatePDF.htm");
		
//		return new ModelAndView("forward:/consolidatedChart/getConsolidatedChartData.htm");
		
		//return null;
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
	    System.out.println("all "+all);
	}
	
	private static void addFilesRecursively(File file, Collection<File> all, String fileName) {
	    final File[] children = file.listFiles();
	    if (children != null) {
	        for (File child : children) {
	        	System.out.println("File Name : " + child.getName());
	        	if(fileName.equalsIgnoreCase(child.getName())){
	            all.add(child);
	            addFilesRecursively(child, all);
	        	}
	        }
	    }
	    System.out.println("all "+all);
	}
}
