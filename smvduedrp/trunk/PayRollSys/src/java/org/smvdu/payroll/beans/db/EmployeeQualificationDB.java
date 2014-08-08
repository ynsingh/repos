/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.api.Administrator.CollegeList;
import org.smvdu.payroll.beans.EmployeeQualification;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.module.attendance.LoggedEmployee;


/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
*  Modified Date: 7 AUG 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*/

public class EmployeeQualificationDB {
    
     private int orgCode = 0;
    //private UserInfo uf = null;
    private UserInfo uf;
    //private int status;
    //private String url;

    /*public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }*/

    public EmployeeQualificationDB() {
        //changes for the orgcode because by uf object not get the orgcode
        uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        orgCode = uf.getUserOrgCode();
        if(orgCode==0){
        LoggedEmployee le =(LoggedEmployee)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        orgCode = le.getUserOrgCode();
        }
        
    }
    private PreparedStatement ps;
    private ResultSet rs;
    
         
    public Exception saveEmpQualification(EmployeeQualification empqual) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("insert into employee_education_detail(edu_emp_code, edu_exam_passed,"
                    + "edu_board_university, edu_marksobtained, edu_passingYear, edu_grade, edu_org_id)"
                    + "values(?,?,?,?,?,?,?)");
            ps.setString(1, empqual.getCode());
            ps.setString(2, empqual.getExamPassed());
            if(empqual.getBoard().equals("CBSE board")||empqual.getBoard().equals("ICSE board"))
            ps.setString(3, empqual.getBoard());
            else
            ps.setString(3, empqual.getBoardName());
            ps.setInt(4, empqual.getMarksObtained());
            ps.setInt(5, empqual.getYearOfPassing());
            ps.setString(6, empqual.getDivGrade());
            ps.setInt(7, orgCode);
            //System.out.println("\n in save history R empdb==desig==="+empqual.getYearOfPassing());
            //System.out.println("\n in save history R empdb==desig==="+empqual.getMarksObtained());
            ps.executeUpdate();
            ps.close();
            c.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }
    
     public ArrayList<EmployeeQualification> loadEmpQualification(String empCode) {
        try {
            
            Connection c = new CommonDB().getConnection();
            String q ="select * from employee_education_detail"
                     +" where edu_emp_code='"+empCode+"' and edu_org_id='" +orgCode+ "'";
                               
            //System.out.println("QUARY : " + q);
            ps = c.prepareStatement(q);
            rs = ps.executeQuery();
            ArrayList<EmployeeQualification> data = new ArrayList<EmployeeQualification>();
            int k = 1;
            while (rs.next()) {
                
                EmployeeQualification empqual = new EmployeeQualification();
                
                empqual.setRecordId(rs.getInt(1));
                empqual.setCode(rs.getString(2).trim());
                empqual.setExamPassed(rs.getString(3).trim());
                empqual.setBoard(rs.getString(4).trim());
                empqual.setMarksObtained(rs.getInt(5));
                empqual.setYearOfPassing(rs.getInt(6));
                empqual.setDivGrade(rs.getString(7));
                empqual.setSrNo(k);
                data.add(empqual);
                k++;
                                   
            }
            rs.close();
            ps.close();
            c.close();
            return data;
        }catch (Exception e) {
            e.printStackTrace();
            return null;

        }
     }    
   
       
       public Exception DeleteEmpQualificationRecord(int currentIndex, ArrayList<EmployeeQualification> servicerecord){
          try{
                Connection connection = new CommonDB().getConnection();
                
                for(EmployeeQualification esh : servicerecord)
                {
                    if(esh.getRecordId()== currentIndex){
                                          
                                   
                        ps = connection.prepareStatement("delete from employee_education_detail where edu_emp_id= '"+esh.getRecordId()+"' and edu_emp_code = '"+esh.getCode()+"' and edu_org_id='" +orgCode+ "' ");
                        ps.executeUpdate();
                        ps.clearParameters();
                    }
                }    
               
                ps.close();
                connection.close(); 
                return null;   
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
            return ex;
        } 
       }      
          
      
       
        public Exception UpdateEmpQualificationRecord(EmployeeQualification editRec){
          try{
                Connection connection = new CommonDB().getConnection();
                ps = connection.prepareStatement("update  employee_education_detail set edu_exam_passed =?,"
                            + "edu_board_university= ?, edu_marksobtained=?, edu_passingYear= ?, edu_grade= ?"
                            + " where edu_emp_code= ? and edu_emp_id=? and edu_org_id='" +orgCode+ "'");
                          
                ps.setString(1, editRec.getExamPassed());
                if(editRec.getBoard().equals("CBSE board")||editRec.getBoard().equals("ICSE board"))
                ps.setString(2, editRec.getBoard());
                else
                ps.setString(2, editRec.getBoardName());
                //ps.setString(2, editRec.getBoardName());
                ps.setInt(3, editRec.getMarksObtained());
                ps.setInt(4, editRec.getYearOfPassing());
                ps.setString(5, editRec.getDivGrade());
                ps.setString(6, editRec.getCode());
                ps.setInt(7, editRec.getRecordId());
                ps.executeUpdate();
                ps.clearParameters();
                   
                ps.close();
                connection.close(); 
                return null;   
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
            return ex;
        } 
    }  
        
        public Exception saveTrainingData(EmployeeQualification empt) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("insert into employee_training_detail (etd_emp_code, etd_trainingtype,"
                    + "etd_topicname, etd_institutename, etd_sponsoredby, etd_datefrom, etd_dateto, etd_org_id)"
                    + "values(?,?,?,?,?,?,?,?)");
            ps.setString(1, empt.getCode());
            ps.setString(2, empt.getTrainingType());
            ps.setString(3, empt.getTopicName());
            ps.setString(4, empt.getInstituteName());
            ps.setString(5, empt.getSponsoredBy());
            ps.setString(6, empt.getDateFrom());
            ps.setString(7, empt.getDateTo());
            ps.setInt(8, orgCode);
            //System.out.println("\n in save history R empdb==desig==="+emp.getDesig()+"\ndept===="+emp.getDept()+"\ngrade==="+emp.getGrade());
            ps.executeUpdate();
            ps.close();
            c.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }
    
     public ArrayList<EmployeeQualification> loadTrainingData(String empCode) {
        try {
            
            Connection c = new CommonDB().getConnection();
            String q ="select * from employee_training_detail"
                     +" where etd_emp_code='"+empCode+"' and etd_org_id='" +orgCode+ "'";
                               
            //System.out.println("QUARY : " + q);
            ps = c.prepareStatement(q);
            rs = ps.executeQuery();
            ArrayList<EmployeeQualification> data = new ArrayList<EmployeeQualification>();
            int k = 1;
            while (rs.next()) {
                
                EmployeeQualification empt = new EmployeeQualification();
                
                empt.setRecordId(rs.getInt(1));
                empt.setCode(rs.getString(2).trim());
                empt.setTrainingType(rs.getString(3).trim());
                empt.setTopicName(rs.getString(4).trim());
                empt.setInstituteName(rs.getString(5));
                empt.setSponsoredBy(rs.getString(6));
                empt.setDateFrom(rs.getString(7));
                empt.setDateTo(rs.getString(8));
                empt.setSrNo(k);
                data.add(empt);
                k++;
                
                    
            }
            rs.close();
            ps.close();
            c.close();
            return data;
        }catch (Exception e) {
            e.printStackTrace();
            return null;

        }
     }    
   
       
       public Exception DeleteTrainingRecord(int currentIndex, ArrayList<EmployeeQualification> trainingrecord){
          try{
                Connection connection = new CommonDB().getConnection();
                
                for(EmployeeQualification etd : trainingrecord)
                {
                    if(etd.getRecordId()== currentIndex){
                                          
                                      
                        ps = connection.prepareStatement("delete from employee_training_detail where etd_emp_id='"+etd.getRecordId()+"' and etd_emp_code = '"+etd.getCode()+"' and etd_org_id='" +orgCode+ "' ");
                        ps.executeUpdate();
                        ps.clearParameters();
                    }
                }    
               
                ps.close();
                connection.close(); 
                return null;   
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
            return ex;
        } 
       }      
          
      
       
        public Exception UpdateTrainingRecord(EmployeeQualification editRec){
          try{
                Connection connection = new CommonDB().getConnection();
                ps = connection.prepareStatement("update  employee_training_detail set etd_trainingtype=?,"
                     + " etd_topicname=?, etd_institutename=?, etd_sponsoredby=?, etd_datefrom=?, etd_dateto=?"
                     + " where etd_emp_code= ? and etd_emp_id=? and etd_org_id='" +orgCode+ "'");
                
                                
                ps.setString(1, editRec.getTrainingType());
                ps.setString(2, editRec.getTopicName());
                ps.setString(3, editRec.getInstituteName());
                ps.setString(4, editRec.getSponsoredBy());
                ps.setString(5, editRec.getDateFrom());
                ps.setString(6, editRec.getDateTo());
                ps.setString(7, editRec.getCode());
                ps.setInt(8, editRec.getRecordId());
                ps.executeUpdate();
                //ps.clearParameters();
                ps.close();
                connection.close(); 
                return null;   
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
            return ex;
        } 
    }    
     
   
}
