/**
 * @(#) ProvisionalCertificateController.java
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
package in.ac.dei.edrp.cms.controller.reportgeneration;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import in.ac.dei.edrp.cms.dao.reportgeneration.ProvisionalCertificate;
import in.ac.dei.edrp.cms.daoimpl.reportgeneration.ProvisionalCertificateImpl;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProvisionalCertificateBean;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * This controller is designed for setting & retrieving
 * information used in Generate Provisional Certificate
 * @author Devendra Singhal
 * @date 09 Dec 2011
 * @version 1.0
 */
public class ProvisionalCertificateController extends MultiActionController{

	//Create object of ProvisionalCertificate interface
	private ProvisionalCertificate provisionalCertificate;
	
	/**
	 * @param provisionalCertificate the provisionalCertificate to set
	 */
	public void setProvisionalCertificate(
			ProvisionalCertificate provisionalCertificate) {
		this.provisionalCertificate = provisionalCertificate;
	}
	
	//Create Object of Logger class to maintain log file
	private Logger loggerObject = Logger.getLogger(ProvisionalCertificateImpl.class);
	/**
     * Method to get the Entity list
     * @param HttpServletRequest
     * @param HttpServletResponse
     * @return ModelAndView containing entity list
     * @throws Exception
     */
	public ModelAndView getEntity(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ProvisionalCertificateBean provisional=new ProvisionalCertificateBean();
		HttpSession session=request.getSession(true);
		String universityCode=(String)session.getAttribute("universityId");
		if(universityCode==null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		provisional.setUniversityId(universityCode);
		List<ProvisionalCertificateBean> entityList = provisionalCertificate.getEntity(provisional);
		return new ModelAndView("reportgeneration/entityList","entityList",entityList);
			
	}
	/**
     * Requested Method to set fields to Generate Provisional Certificate
     * @param HttpServletRequest
     * @param HttpServletResponse
     * @return ModelAndView containing String message
     * @throws Exception
     */
	public ModelAndView generateProvisional(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ProvisionalCertificateBean provisional=new ProvisionalCertificateBean();
		HttpSession session=request.getSession(true);
		String universityCode=(String)session.getAttribute("universityId");
		String universityName=(String)session.getAttribute("universityName");
		if(universityCode==null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		provisional.setUniversityId(universityCode);
		provisional.setUniversityName(universityName);
		provisional.setRollNo(request.getParameter("rollNumber"));
		provisional.setEntityId(request.getParameter("entity"));		
		String message =generateProvisionalCertificate(provisional);		
		return new ModelAndView("buildactivitymaster/Result","message",message);
			
	}
	
	/**
     * Method To Generate Provisional certificate
     * @param Object of ProvisionalCertificateBean
     * @return String message
     */
	public String generateProvisionalCertificate(ProvisionalCertificateBean provisional){
		String message="";
		ProvisionalCertificateBean provisionalBean=new ProvisionalCertificateBean();
		List<ProvisionalCertificateBean>studentDetail=provisionalCertificate.getStudentDetail(provisional);
		if(studentDetail.size()>0){
			try{
				for(int i=0;i<studentDetail.size();i++){
					provisionalBean.setSpecializationId(studentDetail.get(i).getSpecializationId());
					provisionalBean.setUniversityId(provisional.getUniversityId());
					provisionalBean.setCode(studentDetail.get(i).getBracnhId());
					provisionalBean.setGroupCode("BRNCOD");
					String branchName=provisionalCertificate.getDescription(provisionalBean);
					provisionalBean.setCode(studentDetail.get(i).getSpecializationId());
					provisionalBean.setGroupCode("SPCLCD");
					String specializationName=provisionalCertificate.getDescription(provisionalBean);
					String status=studentDetail.get(i).getStatus();
					if(status.equals("PAS")){
						String sep = System.getProperty("file.separator");
						String filePath=this.getServletContext().getRealPath("/")+"PDF"+sep+provisional.getUniversityName()+sep+"provisionalCertificate";
						File file=new File(filePath);
						if(!file.exists()){
							file.mkdirs();
						}
						File file1=new File(filePath+sep+studentDetail.get(i).getRollNo()+"-"+studentDetail.get(i).getEntityId()+".pdf");
						Document document = new Document(PageSize.A4);
						PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(file1));
						String space="\t";
						for(int j=0;j<50;j++){
							space=space+"\t";
						}
						Font detailFont = new Font(Font.TIMES_ROMAN,11,Font.NORMAL);
						Phrase datePhrase=new Phrase("\n\n\n\n"+"No.:Exam/"+space+space+"DATED:",new Font(Font.TIMES_ROMAN,9,Font.BOLD));
						Paragraph datePara=new Paragraph();
						datePara.add(datePhrase);
						datePara.setIndentationLeft(120);
						/**Set The Footer */
						String footerText="N.B. : Valid till the concerned convocation is held.";
						HeaderFooter footer=new HeaderFooter(new Phrase(footerText,detailFont),false);
						footer.setAlignment(HeaderFooter.ALIGN_CENTER);
						footer.setBorder(Rectangle.NO_BORDER);
						document.setFooter(footer);

						Chunk headerChunk=new Chunk("PROVISIONAL CERTIFICATE ----------------------------------------",detailFont);
						Paragraph headerPara=new Paragraph();
						headerPara.setAlignment(Paragraph.ALIGN_CENTER);
						headerPara.add(headerChunk);
						headerPara.setIndentationLeft(176);
						headerPara.setIndentationRight(196);
						Phrase phrase1=new Phrase("Certified that "+studentDetail.get(i).getStudentName().toUpperCase()+"\n",detailFont);
						Phrase phrase2=new Phrase("Roll Number "+studentDetail.get(i).getRollNo()+" having completed the course of study",detailFont);
						Phrase phrase3=new Phrase("approved by the Institute and passed the prescribed",detailFont);
						Phrase phrase4=new Phrase("examination has become eligible for the award of the \n",detailFont);
						Phrase phrase5=new Phrase(studentDetail.get(i).getProgramName(),detailFont);
						Phrase phrase6;
						if(!(branchName.equals("error") || branchName.equals("noDesc") || branchName.equals("None"))){
						
							if(!(specializationName.equals("error") || specializationName.equals("noDesc") || specializationName.equals("None"))){
								phrase6=new Phrase("("+branchName+")"+" Specialization in "+specializationName+"\n",detailFont);
							}
							else{
								phrase6=new Phrase("("+branchName+")"+"\n",detailFont);
							}
						
						}
						else{
							if(!(specializationName.equals("error") || specializationName.equals("noDesc") || specializationName.equals("None"))){
								phrase6=new Phrase(" Specialization in "+specializationName+"\n",detailFont);
							}
							else{
								phrase6=new Phrase(" \n",detailFont);
							}
						}
						Phrase phrase7=new Phrase("of the "+provisional.getUniversityName()+" at the end of ",detailFont);
						Phrase phrase8=new Phrase("the session "+studentDetail.get(i).getSesstionStartDate()+" - "+studentDetail.get(i).getSessionEndDate() +" with the following cumulative ",detailFont);
						Phrase phrase9=new Phrase("percentage and division :",detailFont); 
						
						Paragraph detailParagraph=new Paragraph();
						detailParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
						detailParagraph.add(phrase1);
						detailParagraph.add(phrase2);
						detailParagraph.add(phrase3);
						detailParagraph.add(phrase4);
						detailParagraph.add(phrase5);
						detailParagraph.add(phrase6);
						detailParagraph.add(phrase7);
						detailParagraph.add(phrase8);
						detailParagraph.add(phrase9);
						detailParagraph.setIndentationLeft(140);
						detailParagraph.setIndentationRight(120);
						
						PdfPTable table=new PdfPTable(new float[] {2.7f,2f,4f});
						table.setHorizontalAlignment(PdfTable.ALIGN_CENTER);
						table.setWidthPercentage(60);
						if(studentDetail.get(i).getPrintAggregate().toString().equals("TAP")){
							PdfPCell c1;
							c1=new PdfPCell(new Phrase("",detailFont));
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							c1=new PdfPCell(new Phrase("Percentage",detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							c1=new PdfPCell(new Phrase("Division",detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							
							c1=new PdfPCell(new Phrase("Theory",detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setPaddingLeft(40);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							c1=new PdfPCell(new Phrase(studentDetail.get(i).getTheoryPercentage(),detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							provisionalBean.setGroupCode("DVSCOD");
							provisionalBean.setCode(studentDetail.get(i).getTheoryDivision());
							String theoryDivision=provisionalCertificate.getDescription(provisionalBean);
							c1=new PdfPCell(new Phrase(theoryDivision.toUpperCase(),detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							
							c1=new PdfPCell(new Phrase("Practical",detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setPaddingLeft(40);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							c1=new PdfPCell(new Phrase(studentDetail.get(i).getPracticalPercentage(),detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							provisionalBean.setCode(studentDetail.get(i).getPracticalDivision());
							String practicalDivision=provisionalCertificate.getDescription(provisionalBean);
							c1=new PdfPCell(new Phrase(practicalDivision.toUpperCase(),detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							
							c1=new PdfPCell(new Phrase("Cumulative",detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setPaddingLeft(40);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							c1=new PdfPCell(new Phrase(studentDetail.get(i).getCumulative(),detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							provisionalBean.setCode(studentDetail.get(i).getDivision());
							String division=provisionalCertificate.getDescription(provisionalBean);
							c1=new PdfPCell(new Phrase(division.toUpperCase(),detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
						}
						else if(studentDetail.get(i).getPrintAggregate().toString().equals("SAG")){		
							PdfPCell c1;
							c1=new PdfPCell(new Phrase("",detailFont));
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							c1=new PdfPCell(new Phrase("Cumulative %",detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							c1=new PdfPCell(new Phrase("Division",detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							
							c1=new PdfPCell(new Phrase("",detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							c1=new PdfPCell(new Phrase(studentDetail.get(i).getCumulative(),detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
							provisionalBean.setCode(studentDetail.get(i).getDivision());
							provisionalBean.setGroupCode("DVSCOD");
							String division=provisionalCertificate.getDescription(provisionalBean);
							c1=new PdfPCell(new Phrase(division.toUpperCase(),detailFont));
							c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
							c1.setBorder(Rectangle.NO_BORDER);
							table.addCell(c1);
						
						}
						Phrase chackedBy=new Phrase(space+"  Checked by"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"ASSISTANT REGISTRAR \n"+space+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"(ACADEMIC) \n"+space+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"FOR REGISTRAR",detailFont);
						document.open();
						document.add(Chunk.NEWLINE);
						document.add(datePara);
						document.add(Chunk.NEWLINE);
						document.add(Chunk.NEWLINE);
						document.add(headerPara);
						document.add(Chunk.NEWLINE);
						document.add(detailParagraph);
						document.add(Chunk.NEWLINE);
						document.add(table);
						document.add(Chunk.NEWLINE);
						document.add(Chunk.NEWLINE);
						document.add(Chunk.NEWLINE);
						document.add(Chunk.NEWLINE);
						document.add(chackedBy);
						document.close();
						message="success";
					}
				
					else{
						message="NotPass";
					}
				}
			}
			catch(Exception e){
				message="error";
				loggerObject.error("Error in ProvisionalCertificateController inside Method generateProvisionalCertificate: "+e);
			}
		}
		else{
			message="NoRecord";
		}
		return message;
	}
	
}
