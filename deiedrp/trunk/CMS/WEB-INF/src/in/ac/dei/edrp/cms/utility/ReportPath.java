
/*
 * @(#) ReportPath.java
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
package in.ac.dei.edrp.cms.utility;


/**
 * Creation date: 23/01/2012
 * This class is used for getting path of any report.
 * @author Nupur Dixit
 * @version 1.0
 */
public class ReportPath{	
	public enum ReportType{EPS,RES,REN,RCL,REP,PSS,PBS, REE, RUN, RST, RSS, RGN,DMR};
	static ReportType reportType;	
	
	public static String getPath(ReportPathBean inputBean){		
		String path = null;
		String sep=System.getProperty("file.separator");
		reportType = ReportType.valueOf(inputBean.getReportType());
		String session = inputBean.getSessionStartDate().substring(0, 4)+"-"+inputBean.getSessionEndDate().substring(2,4);
		 switch (reportType) {
	         case REP:	        	
	        	 path = "REPORTS"+sep+inputBean.getUniversityId()+sep+session+sep+inputBean.getEntityId()+sep+inputBean.getProgramId()+sep
	        	 +inputBean.getReportCode()+sep;	        	 	        	
	             break;	                 
	         case REN: case RUN: case REE: case RST:	        	 
	        	 path = "REPORTS"+sep+inputBean.getUniversityId()+sep+session+sep+inputBean.getEntityId()+sep+inputBean.getReportCode()+sep;	             
	             break;                          
	         case PBS:	        	
	        	 path = "REPORTS"+sep+inputBean.getUniversityId()+sep+session+sep+inputBean.getEntityId()+sep+inputBean.getProgramId()+sep+
	        	 inputBean.getBranchId()+sep+inputBean.getSpecializationId()+sep+inputBean.getReportCode()+sep;	             
	             break;
	         case PSS:	        	
	        	 path = "REPORTS"+sep+inputBean.getUniversityId()+sep+session+sep+inputBean.getEntityId()+sep+inputBean.getProgramId()+sep+
	        	 inputBean.getSemesterCode()+"_"+inputBean.getSemesterStartDate()+"_"+inputBean.getSemesterEndDate()+sep+inputBean.getReportCode()+sep;	             
	             break;
	         case RCL:	  	        	
	        	 path = "REPORTS"+sep+inputBean.getUniversityId()+sep+session+sep+inputBean.getEntityId()+sep+inputBean.getProgramId()+sep+
	        	 inputBean.getBranchId()+sep+inputBean.getSpecializationId()+sep+
	        	 inputBean.getSemesterCode()+"_"+inputBean.getSemesterStartDate()+"_"+inputBean.getSemesterEndDate()+sep+inputBean.getReportCode()+sep;	             
	             break;
	         case EPS:
	        	 path = "REPORTS"+sep+inputBean.getUniversityId()+sep+session+sep+inputBean.getEntityId()+sep+inputBean.getProgramId()+sep+
	        	 inputBean.getSemesterType()+sep+inputBean.getReportCode()+sep;	        	 	        	
	             break;
	         case RES:	        	 
	        	 path = "REPORTS"+sep+inputBean.getUniversityId()+sep+session+sep+inputBean.getEntityId()+sep+inputBean.getSemesterType()+sep+
	        	 inputBean.getReportCode()+sep;	             
	             break;
	         case RSS:	        	 
	        	 path = "REPORTS"+sep+inputBean.getUniversityId()+sep+session+sep+inputBean.getSemesterType()+sep+
	        	 inputBean.getReportCode()+sep;	             
	             break;
	         case RGN:	        	 
	        	 path = "REPORTS"+sep+inputBean.getUniversityId()+sep+session+sep+inputBean.getReportCode()+sep;	             
	             break;
	         case DMR:
	        	 path= "REPORTS"+sep+inputBean.getUniversityId()+sep+session+sep+inputBean.getEntityId()+sep+inputBean.getProgramId()+sep+
	        	 inputBean.getBranchId()+sep+inputBean.getSpecializationId()+sep+
	        	 inputBean.getSemesterCode()+sep+inputBean.getReportCode()+sep;	
	        	 break;
	         default:
	        	 path = null;	            
	             break;
		 }
		 return path;	
	}
}
