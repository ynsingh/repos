/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.hbm.ElectionManagerDAO;
import com.myapp.struts.utility.AppPath;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.io.*;
import jxl.*;
import java.util.*;
import jxl.Workbook;
import jxl.write.*;
import org.apache.log4j.Logger;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edrp01
 */
public class XLSExportAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
                HttpSession session=request.getSession();
                 String login=(String)session.getAttribute("user_id");
                 String login_role=(String)session.getAttribute("login_role");
                 String institute_id=(String)session.getAttribute("institute_id");
                 String election_id=request.getParameter("election");
                 ArrayList columns=new ArrayList();
             ElectionManagerDAO dao=new ElectionManagerDAO();

        String path=AppPath.getProjectExportPath();
        
        if(login.indexOf(".")>0)
        login=login.substring(0,login.indexOf("."))+election_id;
       
       try
    	{
                String   filename=path+login+".xls";
                    WorkbookSettings ws = new WorkbookSettings();
                    ws.setLocale(new Locale("en", "EN"));
                    WritableWorkbook workbook =
                    Workbook.createWorkbook(new File(filename), ws);
                    WritableSheet s = workbook.createSheet("Sheet1", 0);
                    writeDataSheet1(s, election_id,institute_id);
                    workbook.write();
                    workbook.close();
                    request.setAttribute("msgxls", "The Data has been successfully exported and saved");
                    session.setAttribute("filename", login+".xls");
		   // log4j.error("Write:"+filename);
        	    return mapping.findForward("success");
        }
        catch (IOException e)
        {
                e.printStackTrace();
		//log4j.error(e.getMessage());
                request.setAttribute("msg1", "Unable to read Data from Database11"+e);
                return mapping.findForward("success");
        }             }
 private  void writeDataSheet1(WritableSheet s, String election_id,String institute_id)
            throws WriteException
	{
       ElectionManagerDAO dao=new ElectionManagerDAO();
        WritableFont wf = new WritableFont(WritableFont.TIMES,
                10, WritableFont.NO_BOLD);
        WritableCellFormat cf = new WritableCellFormat(wf);
        cf.setWrap(true);
        WritableFont wf1 = new WritableFont(WritableFont.ARIAL,
                10, WritableFont.BOLD);
        WritableCellFormat cf1 = new WritableCellFormat(wf1);
        cf.setWrap(true);
        Label l;

        ArrayList columns=new ArrayList();
   	columns.add(0, "Institute_Name");
        columns.add(1, "Election_Id");
        columns.add(2, "Enrollment");
        columns.add(3, "Email");
        columns.add(4, "VoterName");
        columns.add(5, "Status");
        columns.add(6, "MobileNo");
        columns.add(7, "Nomination Start Date");
        columns.add(8, "Nomination End Date");
        columns.add(9, "Election Start Date");
        columns.add(10, "Election End Date");
        columns.add(11, "Position Name");

            for (int k = 0; k < columns.size(); k++)
            {
               l = new Label(k, 0, columns.get(k).toString(), cf1);
                s.addCell(l);
               System.out.println("this is table columns ::::::::::::::::" + columns.get(k).toString());

             }



        List<CandidateReg1> lst=dao.VotedVoterListXML(institute_id,election_id);

        for (int row = 0; row < lst.size(); row++)
        { 
               CandidateReg1  rowdata = (CandidateReg1) lst.get(row);

                l = new Label(0, row +1, String.valueOf(rowdata.getI_institute_name()), cf);
                    s.addCell(l);


                l = new Label(1, row +1, String.valueOf(rowdata.getE_election_name()==null?"NA":rowdata.getE_election_name()), cf);
                    s.addCell(l);
                l = new Label(2, row +1, String.valueOf(rowdata.getV_enrollment()==null?"NA":rowdata.getV_enrollment()), cf);
                    s.addCell(l);
                l = new Label(3, row +1, String.valueOf(rowdata.getV_email()==null?"NA":rowdata.getV_email()), cf);
                    s.addCell(l);
                l = new Label(4, row +1, String.valueOf(rowdata.getV_voter_name()==null?"NA":rowdata.getV_voter_name()), cf);
                    s.addCell(l);
                l = new Label(5, row +1, String.valueOf(rowdata.getStatus()==null?"NA":rowdata.getStatus()), cf);
                    s.addCell(l);
                l = new Label(6, row +1, String.valueOf(rowdata.getV_mobile_number()==null?"NA":rowdata.getV_mobile_number()), cf);
                    s.addCell(l);
                l = new Label(7, row +1, String.valueOf(rowdata.getE_nomistart()==null?"NA":rowdata.getE_nomistart()), cf);
                    s.addCell(l);
                l = new Label(8, row +1, String.valueOf(rowdata.getE_nomiend()==null?"NA":rowdata.getE_nomiend()), cf);
                    s.addCell(l);
                l = new Label(9, row +1, String.valueOf(rowdata.getE_start()==null?"NA":rowdata.getE_start()), cf);
                    s.addCell(l);
                l = new Label(10, row +1, String.valueOf(rowdata.getE_end()==null?"NA":rowdata.getE_end()), cf);
                    s.addCell(l);
               l = new Label(11, row +1, String.valueOf(rowdata.getP_position_name()==null?"NA":rowdata.getP_position_name()), cf);
                    s.addCell(l);
        }
    }
}
