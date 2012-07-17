package in.ac.dei.edrp.cms.controller.report;

import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.net.URL;
import java.util.List;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


public class ReportController extends MultiActionController{

	public static Logger logObj=Logger.getLogger(ReportController.class);
	
	/**
	 * This method get list of entities from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing entity List
	 */
	public ModelAndView getReportPath(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String semType = null;
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		if(request.getParameter("reportType").equalsIgnoreCase("RSS")){
			 semType = Integer.parseInt(request.getParameter("semesterWise"))==0?"Even semester":"Odd semester";
		}
		else{
			 semType = request.getParameter("semesterType");
		}
		System.out.println("semester type is :"+semType);
		ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
				request.getParameter("reportType"),
				request.getParameter("fromSession"),request.getParameter("toSession"),semType);
				
				
		//reportPathBean.setReportCode(request.getParameter("reportCode"));
		String reportPath = ReportPath.getPath(reportPathBean);				
		System.out.println("here in controller after path generation "+reportPath);
		String printVal = request.getParameter("print")==null?"no":request.getParameter("print").toString();
		String sep=System.getProperty("file.separator");
		String initialPath = getServletContext().getRealPath(sep);
		System.out.println("full path  "+initialPath+reportPath);
		File fileVerify = new File(initialPath+reportPath);
	    if(fileVerify.exists()){
	    	if(printVal.equalsIgnoreCase("yes")){
				
			}
			else{
				if(Integer.parseInt(request.getParameter("reportCode"))==8){
					String path = this.getServletContext().getRealPath("/");
					path=path+reportPath;
					try {
						zipFolder(path,(path.substring(0,path.length()-1).concat(".zip")));
					} catch (Exception e) {
						System.out.println("exception in zipping the folder");
						e.printStackTrace();
					}
				}
			}
	    }
	    else{
	    	reportPath="false-Please Generate the report first to proceed with Download/Print";
	    }
		
		ReportLogBean reportControlBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("reportCode"),"","",
				request.getParameter("fromSession"),request.getParameter("toSession"),semType,
				session.getAttribute("userName").toString());
		
		ReportLogBean reportErrorBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
				request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("reportCode"),"",request.getParameter("rollNumber"),
				request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
		
		ReportLogBean reportPrintBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
				request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("reportCode"),"",request.getParameter("rollNumber"),
				request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4),"");
		
		
		//reportLogBean.setIsGenerated(request.getParameter("isGenerated")==null?"no":"yes");	
		request.setAttribute("reportPath", reportPath);
		request.setAttribute("reportControlBean", reportControlBean);
		request.setAttribute("reportErrorBean", reportErrorBean);
		request.setAttribute("reportPrintBean", reportPrintBean);
		
		/*try {
		URL url = new URL("http://localhost:8080/CMS/login/getLoginDetails.htm?userName=E11017&password=dheeraj");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

		FileOutputStream xml = new FileOutputStream(new File(this.getServletContext().getRealPath("/") + "new.xml"));
		String s = "";
		while ((s = reader.readLine()) != null) {
		System.out.println(s);
		xml.write(s.getBytes());
		}
		reader.close();
		xml.close();
		System.out.println("here in jsp");

		} catch (Exception e) {
		e.printStackTrace();
		}*/
		
		//request.getRequestDispatcher(reportPath);
//		List<ReportPathBean> entityList = consolidatedChartService
//				.getEntityList(courseMasterBean);
		return new ModelAndView("general/ReportPath", "path",reportPath);
		/*ModelAndView modelAndView = new ModelAndView("path");

		 modelAndView.addObject("path", reportPath); */
		//return new ModelAndView("forward:/REN_11/generatePDF.htm");
		
//		return new ModelAndView("forward:/consolidatedChart/getConsolidatedChartData.htm");
		
		//return null;
	}

	static public void zipFolder(String srcFolder, String destZipFile) throws Exception {
	    ZipOutputStream zip = null;
	    FileOutputStream fileWriter = null;
	    System.out.println("destination zip folder "+destZipFile);
	    fileWriter = new FileOutputStream(destZipFile);
	    zip = new ZipOutputStream(fileWriter);

	    addFolderToZip("", srcFolder, zip);
	    zip.flush();
	    zip.close();
	  }
	
	static private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws Exception {
	  File folder = new File(srcFolder);
	
	  for (String fileName : folder.list()) {
	    if (path.equals("")) {
	      addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
	    } else {
	      addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
	    }
	  }
	}
	
	static private void addFileToZip(String path, String srcFile, ZipOutputStream zip)throws Exception {
		  File folder = new File(srcFile);
		  if (folder.isDirectory()) {
		    addFolderToZip(path, srcFile, zip);
		  } 
		  else {
		    byte[] buf = new byte[1024];
		    int len;
		    FileInputStream in = new FileInputStream(srcFile);
		    zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
		    while ((len = in.read(buf)) > 0) {
		      zip.write(buf, 0, len);
		    }
		  }
}

}
