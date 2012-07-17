/**
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

import in.ac.dei.edrp.cms.dao.reportgeneration.SubjectWiseMeritListDAO;
import in.ac.dei.edrp.cms.domain.reportgeneration.SubjectWiseMeritList;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * This controller is designed for setting & retrieving
 * the activity master information 
 * @author Ankit Jain
 * @date 07 Apr 2011
 * @version 1.0
 */
public class SubjectWiseMeritListController extends MultiActionController{

	private SubjectWiseMeritListDAO subjectWiseMeritListDAO;
	
	/**
     * The setter method of the interface associated
     * with this controller
     * @param activityMaster
     */
	public void setSubjectWiseMeritListDAO(SubjectWiseMeritListDAO subjectWiseMeritListDao) {
		this.subjectWiseMeritListDAO = subjectWiseMeritListDao;
	}
	
	
	public ModelAndView getEntityList(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		SubjectWiseMeritList subjectWiseMeritList=new SubjectWiseMeritList();
		subjectWiseMeritList.setUniversityId(universityId);
		
		List<SubjectWiseMeritList> entityList = subjectWiseMeritListDAO.getEntityList(subjectWiseMeritList);
		
		return new ModelAndView("associatecoursewithinstructor/EntityList","entityList", entityList);
	}
	
	/**
     * Method for fetch the programCourse Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView getProgramList(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		SubjectWiseMeritList subjectWiseMeritList=new SubjectWiseMeritList();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		subjectWiseMeritList.setEntityId(request.getParameter("entityId"));
		List<SubjectWiseMeritList> programNameList = subjectWiseMeritListDAO.getProgramList(subjectWiseMeritList);
		return new ModelAndView("associatecoursewithinstructor/ProgramList","programNameList", programNameList);
			
	}
	
	/**
     * Method to get the Branch List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView branchList(HttpServletRequest request,
			HttpServletResponse response) {
		SubjectWiseMeritList subjectWiseMeritList=new SubjectWiseMeritList();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		subjectWiseMeritList.setUniversityId(universityId);
		subjectWiseMeritList.setEntityId(request.getParameter("entityId"));
		subjectWiseMeritList.setProgramId(request.getParameter("programId"));
		List<SubjectWiseMeritList> branchList = subjectWiseMeritListDAO.getBranchList(subjectWiseMeritList);
		return new ModelAndView("associatecoursewithinstructor/branchList","branchList", branchList);
	}
	
	/**
     * Method to get the Specialization List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView specializationList(HttpServletRequest request,
			HttpServletResponse response) {
		SubjectWiseMeritList subjectWiseMeritList=new SubjectWiseMeritList();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		subjectWiseMeritList.setUniversityId(universityId);
		subjectWiseMeritList.setEntityId(request.getParameter("entityId"));
		subjectWiseMeritList.setProgramId(request.getParameter("programId"));
		subjectWiseMeritList.setBranchId(request.getParameter("branchId"));
		List<SubjectWiseMeritList> specializationList = subjectWiseMeritListDAO.getSpecializationList(subjectWiseMeritList);
		return new ModelAndView("associatecoursewithinstructor/SpecializationList","specializationList", specializationList);
	}
	
	/**
     * Method to get the Specialization List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView semesterList(HttpServletRequest request,
			HttpServletResponse response) {
		SubjectWiseMeritList subjectWiseMeritList=new SubjectWiseMeritList();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		subjectWiseMeritList.setUniversityId(universityId);
		subjectWiseMeritList.setProgramId(request.getParameter("programId"));
		List<SubjectWiseMeritList> semesterList = subjectWiseMeritListDAO.getSemesterList(subjectWiseMeritList);
		return new ModelAndView("subjectwisemeritlist/SemesterList","semesterList", semesterList);
	}
	
	/**
     * Method to get the Specialization List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView courseGroupList(HttpServletRequest request,
			HttpServletResponse response) {
		SubjectWiseMeritList subjectWiseMeritList=new SubjectWiseMeritList();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		subjectWiseMeritList.setProgramId(request.getParameter("programId"));
		subjectWiseMeritList.setBranchId(request.getParameter("branchId"));
		subjectWiseMeritList.setSpecializationId(request.getParameter("specializationId"));
		String semesterTokens=request.getParameter("selectedSemester");
		List<SubjectWiseMeritList> courseGroupList = subjectWiseMeritListDAO.getCourseGroupList(subjectWiseMeritList, semesterTokens);
		return new ModelAndView("subjectwisemeritlist/CourseGroupList","courseGroupList", courseGroupList);
	}
	
	
	/**
     * Method for fetch the programCourse Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView generatePDF(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		SubjectWiseMeritList subjectWiseMeritList=new SubjectWiseMeritList();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		subjectWiseMeritList.setEntityId(request.getParameter("entityId"));
		subjectWiseMeritList.setProgramId(request.getParameter("programId"));
		subjectWiseMeritList.setBranchId(request.getParameter("branchId"));
		subjectWiseMeritList.setSpecializationId(request.getParameter("specializationId"));
		String semesterTokens=request.getParameter("selectedSemester");
		subjectWiseMeritList.setCourseGroup(request.getParameter("courseGroup"));
		subjectWiseMeritList.setUniversityId(universityId);
		List<SubjectWiseMeritList> StudentList = subjectWiseMeritListDAO.getProgramCourseKey(subjectWiseMeritList, semesterTokens);
		if(StudentList!=null && StudentList.size()>0){
//			return new ModelAndView("GroupWiseMeritListPDFView","StudentList", StudentList);
			String message = buildPdfDocument(StudentList,request,response);
			if(message.equalsIgnoreCase("success")){
				return new ModelAndView("associatecoursewithinstructor/Result","message",message);
			}
			else{
				return new ModelAndView("associatecoursewithinstructor/Result","message",message);
			}			 
		}
		else{
			return new ModelAndView("associatecoursewithinstructor/Result","message","studentsNotFound");
		}
	}
	
	protected String buildPdfDocument(List<SubjectWiseMeritList> studentDataList,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String sep = System.getProperty("file.separator");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("in" + sep + "ac"
				+ sep + "dei" + sep + "edrp" + sep + "cms" + sep
				+ "databasesetting" + sep + "MessageProperties", new Locale("en",
				"US"));
		
		HttpSession session=request.getSession(true);
		String filePath = "";
		File file;
		Document document = new Document();
		PdfWriter writer=null;
		String message="success";
//		List<SubjectWiseMeritList> studentDataList= (List<SubjectWiseMeritList>) model.get("StudentList");
		
		try{
			String programCode=studentDataList.get(0).getProgramCode();
			String branchId=studentDataList.get(0).getBranchId();
			String courseGroup=studentDataList.get(0).getCourseGroup();
			
			filePath = getServletContext().getRealPath("/")+resourceBundle.getString("directory");
			
			filePath = filePath+sep+session.getAttribute("universityName").toString()+sep+"Session-"+
			session.getAttribute("startDate").toString().substring(0, 4)+"-"+
			session.getAttribute("endDate").toString().substring(0, 4)+sep+"MajorGroupWiseMeritList"+sep+programCode;
			
			file = new File(filePath);
			file.mkdirs();
			
			writer = PdfWriter.getInstance(document,new FileOutputStream(filePath+sep+"Major_Group_Wise_Merit_List_"+programCode+"_"+branchId+"_"+courseGroup+".pdf"));
			
			
			Phrase header1= new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+
					session.getAttribute("universityName").toString().toUpperCase(),FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0)));
			Phrase header2= new Phrase("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+
					"Major-Group-Wise Merit List "+ session.getAttribute("startDate").toString().substring(0, 4)+"-"+
					session.getAttribute("endDate").toString().substring(0, 4),FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new Color(0, 0, 0)));
			
			Phrase headers=new Phrase();
	        headers.add(header1);
	        headers.add(header2);       
			HeaderFooter header = new HeaderFooter(headers,false);
	        header.setBorderWidth(0);
	        document.setHeader(header);
	        
	        Date printingDate = new Date();
	        String toDay = DateFormat.getDateTimeInstance().format(printingDate);
	        
	        Phrase date= new Phrase("Date : "+toDay,FontFactory.getFont(FontFactory.HELVETICA, 10, Font.ITALIC,new Color(0, 0, 0)));
	       
	        HeaderFooter footer = new HeaderFooter(date,false); 
	        footer.setBorderWidth(0);
	        document.setFooter(footer);
	        
			document.open();
			Font cellFont = new Font(Font.HELVETICA, 8);
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(25,25,112));
			PdfPTable studentTable = new PdfPTable(new float[] {2,5,3,3,4,6,2,3});
			studentTable.setWidthPercentage(100f);
			
			PdfPCell c1 = new PdfPCell(new Phrase("", cellFont));
			addCell(c1, headerFont, studentTable,"S.No.");
			addCell(c1, headerFont, studentTable,"CL");
			addCell(c1, headerFont, studentTable,"BR");
			addCell(c1, headerFont, studentTable,"Group");
			addCell(c1, headerFont, studentTable,"Roll Number");
			addCell(c1, headerFont, studentTable,"Name");
			addCell(c1, headerFont, studentTable,"M/F");
			addCell(c1, headerFont, studentTable,"Marks");		
			    
				for(int i=0;i<studentDataList.size();i++){
					String ii = Integer.toString(i+1);
					addCell(c1, cellFont, studentTable,ii);
					addCell(c1, cellFont, studentTable,studentDataList.get(i).getProgramCode());
					addCell(c1, cellFont, studentTable,studentDataList.get(i).getBranchId());
					addCell(c1, cellFont, studentTable,studentDataList.get(i).getCourseGroup());
					addCell(c1, cellFont, studentTable,studentDataList.get(i).getRollNumber());
					addCell(c1, cellFont, studentTable,studentDataList.get(i).getName());
					addCell(c1, cellFont, studentTable,studentDataList.get(i).getGender());
					addCell(c1, cellFont, studentTable,studentDataList.get(i).getMarks());
	
				}
		
				document.add(studentTable);
				document.close();
		}
		catch (Exception e) {
			logger.error("buildPdfDocument");
			message="failure";
		}
		
		return message;		
	}
	
	public static final void addCell(PdfPCell c1, Font cellFont, PdfPTable chartTable, String s) {
		try {
			c1 = new PdfPCell(new Phrase(s, cellFont));
		} catch (Exception e) {
			e.printStackTrace();
		}
//		c1.setBorderWidth(1);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setVerticalAlignment(Element.ALIGN_TOP);
		chartTable.addCell(c1);
	}
	
}	