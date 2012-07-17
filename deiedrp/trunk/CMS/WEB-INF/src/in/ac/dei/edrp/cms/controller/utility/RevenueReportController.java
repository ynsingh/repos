/*
 * @(#) RevenueReportController.java
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
package in.ac.dei.edrp.cms.controller.utility;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RevenueReportController extends AbstractController {
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
            	
    	String output = ServletRequestUtils.getStringParameter(request, "output");
        StringTokenizer header = new StringTokenizer(ServletRequestUtils.getStringParameter(request, "header"), "|");
        StringTokenizer data = new StringTokenizer(request.getParameter("data"),";");
        String entity = ServletRequestUtils.getStringParameter(request, "entityName");
        String program = ServletRequestUtils.getStringParameter(request,"programName");
        String branch = ServletRequestUtils.getStringParameter(request, "branchName");
        String specialization = ServletRequestUtils.getStringParameter(request,"specializationName");
        String semester = ServletRequestUtils.getStringParameter(request,"semesterName");
        String courseCode = ServletRequestUtils.getStringParameter(request,"courseCode");
        String courseName = ServletRequestUtils.getStringParameter(request,"courseName");
        String displayType= ServletRequestUtils.getStringParameter(request,"displayType");
        String sessionStartDate = ServletRequestUtils.getStringParameter(request,"sessionStartDate");
        String sessionEndDate = ServletRequestUtils.getStringParameter(request,"sessionEndDate");
        String employeeName = ServletRequestUtils.getStringParameter(request,"employeeName");
        String path = "OLD";
        request.setAttribute("path", path);
        System.out.println("Attribute : " + request.getAttribute("path").toString());
        
        HttpSession session = request.getSession(true);
        String universityName = (String)session.getAttribute("universityName");
         
        

        //		receive program-course details also
        List<String> docHeaders = new ArrayList<String>();
        docHeaders.add(entity);
        docHeaders.add(program);
        docHeaders.add(branch);
        docHeaders.add(specialization);
        docHeaders.add(semester);
        docHeaders.add(courseCode);
        docHeaders.add(courseName);
        docHeaders.add(displayType);
        docHeaders.add(sessionStartDate);
        docHeaders.add(sessionEndDate);
        docHeaders.add(employeeName);
        docHeaders.add(universityName);

        List<String> sheetHeaders = new ArrayList<String>();

        sheetHeaders.add("Sl No.");
        while (header.hasMoreTokens()) {
            sheetHeaders.add(header.nextToken());
        }

        
        List<String> dataList = new ArrayList<String>();
       // int ss=1;
        while (data.hasMoreTokens()) {
        	
        	//dataList.add(String.valueOf(ss++));
            dataList.add(data.nextToken());
        }

        List<List<String>> outputData = new ArrayList<List<String>>();
        outputData.add(docHeaders);
        outputData.add(sheetHeaders);
        outputData.add(dataList);

        if ((output == null) || "".equals(output)) {
            //return normal view
            return new ModelAndView("utility/RevenueSummary", "revenueData",
                outputData);
        } else if ("PDF".equals(output.toUpperCase())) {
            //return excel view
            return new ModelAndView("PdfRevenueSummary", "revenueData",
                outputData);
        } else {
            //return normal view
            return new ModelAndView("utility/RevenueSummary", "revenueData",
                outputData);
        }
    }
}
