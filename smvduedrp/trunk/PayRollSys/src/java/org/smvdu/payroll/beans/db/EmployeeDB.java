/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.api.BankDetails.BankDetailsSearch;
import org.smvdu.payroll.api.BankDetails.BankProfileDetails;
import org.smvdu.payroll.beans.setup.Department;
import org.smvdu.payroll.beans.setup.Designation;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.EmployeeSearchBean;
import org.smvdu.payroll.beans.GenderDetails;
import org.smvdu.payroll.beans.SimpleEmployee;
import org.smvdu.payroll.beans.setup.EmployeeType;
import org.smvdu.payroll.beans.setup.SalaryGrade;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.composite.ActivationDeactivationMessage;
import org.smvdu.payroll.beans.composite.NewSalaryProcessing;
import org.smvdu.payroll.beans.validator.AllValidator;
import org.smvdu.payroll.beans.validator.EmployeeNotification;
import org.smvdu.payroll.beans.validator.ValidationStatus;
import org.smvdu.payroll.beans.validator.ValidatorStatus;
import org.smvdu.payroll.user.ActiveProfile;
import org.smvdu.payroll.user.SalaryMessage;

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
 *  Contributors: Members of ERP Team @ SMVDU, Katra
 *
 */
public class EmployeeDB {

    private int orgCode = 0;
    private UserInfo uf = null;
    private int status;
    private String url;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public EmployeeDB() {
        uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        orgCode = uf.getUserOrgCode();
    }
    private PreparedStatement ps;
    private ResultSet rs;

   
    public ArrayList<SimpleEmployee> loadPendingEmployee() {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select emp_code,emp_name,emp_active from employee_master where "
                    + " emp_code not in (select es_code from employee_salary_summery where "
                    + "es_month=  ? and es_year=?) and emp_org_code=?");
            ps.setInt(1, uf.getCurrentMonth());
            ps.setInt(2, uf.getCurrentYear());
            ps.setInt(3, orgCode);
            rs = ps.executeQuery();
            ArrayList<SimpleEmployee> data = new ArrayList<SimpleEmployee>();
            //NewSalaryProcessing ns = new NewSalaryProcessing();
            while (rs.next())
            {
                SimpleEmployee se = new SimpleEmployee();
                se.setCode(rs.getString(1));
                se.setName(rs.getString(2));
                se.setSalaryStatus(rs.getBoolean(3));
                if(se.isSalaryStatus() == true)
                {
                    se.setImageStatus("/img/Active.png");
                }
                else
                {
                    se.setImageStatus("/img/InActive.png");
                }
                data.add(se);
            }
            rs.close();
            ps.close();
            c.close();
            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deactivate(String s) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("update employee_master set emp_active=0 where emp_code=?");
            ps.setString(1, s);
            ps.executeUpdate();
            ps.close();
            c.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getTypeCode(String empCode) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select emp_type_code from employee_master "
                    + "where emp_code=?");
            ps.setString(1, empCode);
            rs = ps.executeQuery();
            rs.next();
            int code = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Employee loadProfile(String empCode, int orgId) {
        try {
            Connection c = new CommonDB().getConnection();
            String q = "select emp_code,emp_name,dept_name,desig_name,"
                    + "emp_type_name,emp_id,emp_bank_accno,emp_pf_accno,emp_pan_no,"
                    + " emp_type_code,emp_salary_grade,grd_name,emp_email,emp_dob,"
                    + "emp_doj,emp_phone,emp_father,emp_dept_code,emp_desig_code,emp_title,"
                    + "emp_exp,emp_qual,emp_yop,emp_prev_emp,emp_address,emp_basic,grd_gp,emp_active,emp_gender,bank_ifsc_code,emp_bank_status,dor,emp_leaving,emp_noti_day "
                    + "from employee_master "
                    + "left join designation_master on desig_code= emp_desig_code  "
                    + "left join department_master on dept_code = emp_dept_code "
                    + " left join employee_type_master on emp_type_id = emp_type_code "
                    + "left join salary_grade_master on grd_code = emp_salary_grade "
                    + " where emp_code=? and emp_org_code=?";
            ps = c.prepareStatement(q);
            System.err.println(">>>>>>>  " + q + empCode + "Org ID " + orgCode);
            ps.setString(1, empCode.trim());
            ps.setInt(2, orgCode);
            rs = ps.executeQuery();
            Employee emp = null;
            SalaryMessage  sm = new SalaryMessage();
            if (rs.next())
            {
                emp = new Employee();
                emp.setCode(rs.getString(1));
                emp.setName(rs.getString(2));
                Department dept = new Department();
                dept.setName(rs.getString(3));
                emp.setDept(dept.getCode());
                emp.setDeptName(dept.getName());
                Designation desig = new Designation();
                desig.setName(rs.getString(4));
                emp.setDesig(desig.getCode());
                emp.setDesigName(desig.getName());
                EmployeeType et = new EmployeeType();
                et.setName(rs.getString(5));
                et.setCode(rs.getInt(10));
                emp.setType(et.getCode());
                emp.setTypeName(et.getName());
                emp.setEmpId(rs.getInt(6));
               // emp.setBankAccNo(rs.getString(7));
                if(rs.getString(7).equals("") == true)
                {
                    
                    emp.setBankAccNo("Enter Bank A/c Number");
                }
                else
                {
                    emp.setBankAccNo(rs.getString(7).trim());
                 }
                emp.setPfAccNo(rs.getString(8));
                emp.setPanNo(rs.getString(9));
                SalaryGrade sg = new SalaryGrade();
                sg.setCode(rs.getInt(11));
                sg.setName(rs.getString(12));
                emp.setGrade(sg.getCode());
                emp.setBandName(sg.toString());
                emp.setEmail(rs.getString(13));
                emp.setDob(rs.getString(14));
                emp.setDoj(rs.getString(15));
                emp.setPhone(rs.getString(16));
                emp.setFatherName(rs.getString(17));
                emp.setDept(rs.getInt(18));
                emp.setDesig(rs.getInt(19));
                emp.setTitle(rs.getString(20));
                emp.setExperience(rs.getInt(21));
                emp.setQualification(rs.getString(22));
                emp.setYearOfPassing(rs.getInt(23));
                emp.setPreviousEmployer(rs.getString(24));
                emp.setAddress(rs.getString(25));
                emp.setCurrentBasic(rs.getInt(26));
                emp.setGradePay(rs.getInt(27));
                if (rs.getInt(28) == 1)
                {
                    System.out.println("Status : " + rs.getInt(28));
                    emp.setStstus(true);
                }
                else
                {
                    emp.setStstus(false);
                }
                
                boolean b = new GenderDetails().gen(emp.getDob(), emp.getDoj());
                if(b == true)
                {
                    emp.setGenDetails("Employee is Senior Citizen");
                }
                else 
                {
                    emp.setGenDetails("");
                }
                
                
                //this.setStatus(rs.getInt(28));
                emp.setGender(rs.getInt(29));
                //emp.setBankName(rs.getString(30));
                //System.out.println("Bank Name  :"+rs.getString(29));
                //emp.setBankBranchName(rs.getString(31));
                //System.out.println("Bank Name  :"+rs.getString(30));
                if(rs.getString(30) == null) 
                {
                    emp.setBankIFSCcode("Enter Bank IFSC Code");
                }
                else
                {
                    emp.setBankIFSCcode(rs.getString(30).trim());
                }
                emp.setDateOfResig(rs.getString(32));
                //emp.setSalaryMessage(new ActivationDeactivationMessage().salaryMessage(rs.getString(33)));
                emp.setEmpNotDay(rs.getInt(34));
                //emp.setSeniorCitizen(false);
                /*if(rs.getBoolean(35) == false)
                {
                    emp.setSeniorCitizen(false);
                }
                else
                {
                    emp.setSeniorCitizen(true);
 }*/
            }
            rs.close();
            ps.close();
            c.close();
            return emp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public String getStatusImage(int status) {
        try {
            if (status == 1) {
                return "/img/success.png";
            } else {
                return "/img/err.png";
            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public boolean codeExist(String code) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select emp_name from employee_master where "
                    + "emp_code=? and emp_org_code=?");
            ps.setString(1, code);
            ps.setInt(2, orgCode);
            rs = ps.executeQuery();
            rs.next();
            String s = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Updating Employee Salary Status

    public boolean updateEmployeeSalaryStatus(ArrayList<SimpleEmployee> simpleEmployee)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst = null;
            for(SimpleEmployee sim:simpleEmployee)
            {
                if(sim.isSalaryStatus() == true)
                {
                    pst = cn.prepareStatement("update employee_master set emp_active = '"+1+"' where emp_code = '"+sim.getCode()+"' and emp_org_code = '"+orgCode+"'");
                }
                else
                {
                    pst = cn.prepareStatement("update employee_master set emp_active = '"+0+"' where emp_code = '"+sim.getCode()+"' and emp_org_code = '"+orgCode+"'");
                }
                pst.executeUpdate();
                pst.clearParameters();
            }
            pst.close();
            cn.close();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Employee> loadProfiles(String s) {
        try {
            Connection c = new CommonDB().getConnection();
            String q = "select emp_code,emp_name,"
                    + " dept_name,desig_name,emp_type_name,emp_id,grd_name,grd_max,"
                    + "grd_min,emp_phone,emp_email,emp_org_code,emp_father,emp_basic,"
                    + "EMP_TITLE,date_format(emp_doj,'%M-%y'),emp_active from employee_master left join "
                    + "designation_master on desig_code= emp_desig_code left join"
                    + " department_master on dept_code = emp_dept_code  left join"
                    + " employee_type_master on emp_type_id = emp_type_code left "
                    + "join salary_grade_master on grd_code=emp_salary_grade "
                    + "" + s + " ";
            System.out.println("QUARY : " + q);
            ps = c.prepareStatement(q);
            rs = ps.executeQuery();
            ArrayList<Employee> data = new ArrayList<Employee>();
            int k = 1;
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setCode(rs.getString(1));
                emp.setName(rs.getString(2));
                Department dept = new Department();
                dept.setName(rs.getString(3));
                emp.setDept(dept.getCode());
                emp.setDeptName(dept.getName());
                Designation desig = new Designation();
                desig.setName(rs.getString(4));
                emp.setDesig(desig.getCode());
                emp.setDesigName(desig.getName());
                EmployeeType et = new EmployeeType();
                et.setName(rs.getString(5));
                emp.setType(et.getCode());
                emp.setTypeName(et.getName());
                emp.setEmpId(rs.getInt(6));
                emp.setSrNo(k);
                SalaryGrade sg = new SalaryGrade();
                sg.setName(rs.getString(7));
                sg.setMaxValue(rs.getInt(8));
                sg.setMinValue(rs.getInt(9));
                emp.setBandName(sg.toString());
                emp.setPhone(rs.getString(10));
                emp.setEmail(rs.getString(11));
                emp.setFatherName(rs.getString(13));
                emp.setCurrentBasic(rs.getInt(14));
                emp.setTitle(rs.getString(15));
                emp.setDoj(rs.getString(16));
                if (rs.getInt(17) == 1) {
                    emp.setButtonValue("Active");
                    emp.setStatusI("/img/Active.png");
                    emp.setEvent(true);
                } else {
                    emp.setButtonValue("Delete");
                    emp.setStatusI("/img/InActive.png");
                    emp.setEvent(false);
                }
                data.add(emp);
                k++;
            }
            rs.close();
            ps.close();
            c.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean update(Employee emp) {
        try {
            Employee employee = new Employee().bankDetails(emp);
            Connection c = new CommonDB().getConnection();
            FacesContext fc=FacesContext.getCurrentInstance();
            System.out.println("DAta Should Be Write Here status " + emp.isUserNameStatus());
            int empstatus;
            if (emp.getStstus() == true) {
                empstatus = 1;
            } else {
                empstatus = 0;
            }
            if(emp.getEmail().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") == false)
            {
 
                 FacesMessage message = new FacesMessage();
                 message.setSeverity(FacesMessage.SEVERITY_ERROR);
                 message.setSummary("Plz Enter EmailID In Correct Format ");
                 //message.setDetail("First Name Must Be At Least Three Charecter ");
                 emp.setStatusI("/img/InActive.png");
                 fc.addMessage("", message);
                 return false;
            }
            if(emp.getName().matches("^[a-zA-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid First Name");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
                return false;
            }
            if(emp.getFatherName().matches("^[a-zA-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid Father Name");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
               fc.addMessage("", message);
                return false;
            }
            if(emp.getPhone().matches(".*[0-9]{10}.*") == false || emp.getPhone().length()!=10)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid Phone Number");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
               fc.addMessage("", message);
                return false;
            }
            if(emp.getBankAccNo().trim().matches(".*[0-9].*") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid Bank Acc. Number");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
               fc.addMessage("", message);
                return false;
            }
           /* if(emp.getEmpNotDay().matches(".*[0-9]") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid No. Notification Day");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
               fc.addMessage("", message);
                return false;
            }*/
            ps = c.prepareStatement("update employee_master set emp_name='" + emp.getName() + "',"
                    + "emp_dept_code='" + emp.getDept() + "',emp_desig_code='" + emp.getDesig() + "',emp_type_code='" + emp.getType() + "',emp_phone='" + emp.getPhone() + "',"
                    + "emp_email='" + emp.getEmail() + "',emp_dob='" + emp.getDob() + "',emp_doj='" + emp.getDoj() + "',emp_bank_accno='" + emp.getBankAccNo() + "',emp_pf_accno='" + emp.getPfAccNo() + "',emp_pan_no='" + emp.getPanNo() + "',"
                    + "emp_salary_grade='" + emp.getGrade() + "',emp_basic='" + emp.getCurrentBasic() + "',emp_father='" + emp.getFatherName() + "',emp_title='" + emp.getTitle() + "',emp_exp='" + emp.getExperience() + "',emp_qual='" + emp.getQualification() + "',"
                    + "emp_yop='" + emp.getYearOfPassing() + "',emp_prev_emp='" + emp.getYearOfPassing() + "',emp_address='" + emp.getAddress() + "',emp_active = '" + empstatus + "',"
                    + "bank_ifsc_code='" + employee.getBankIFSCcode().trim() + "',emp_bank_status='" + empstatus + "', dor = '"+emp.getDateOfResig()+"',emp_leaving = '"+emp.getEmpLeaDate()+"',emp_noti_day = '"+emp.getEmpNotDay()+"', citizen='"+emp.getGenDetailCode()+"' where emp_code='" + emp.getCode().trim() + "' and emp_org_code='" + orgCode + "'");
            ps.executeUpdate();
            System.out.println("DAta Should Be Write Here ................");
            ps.executeUpdate();
            ps.close();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public Exception delete(int empId) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("delete from employee_master where emp_id=?");
            ps.setInt(1, empId);
            ps.executeUpdate();
            ps.close();
            c.close();
            return new Exception("Record Deleted");
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }

    public void bankDetails(Employee emp) {
        try {
            Connection cn;
            cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            System.out.println("Code IFSC : " + emp.getBankIFSCcode());
            pst = cn.prepareStatement("select bank_name,branch_name,bank_ifsc_code from bankprofile where bank_ifsc_code = '" + emp.getBankIFSCcode() + "'");
            rst = pst.executeQuery();
            if (rst.next()) {
                emp.setBankName(rst.getString(1));
                emp.setBankBranchName(rst.getString(2));
                emp.setBankIFSCcode(rst.getString(3));
                System.out.println(emp.getBankIFSCcode());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Exception save(Employee emp) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("insert into employee_master(emp_code,emp_name,"
                    + "emp_dept_code,emp_desig_code,emp_type_code,emp_phone,"
                    + "emp_email,emp_dob,emp_doj,emp_bank_accno,emp_pf_accno,emp_pan_no,"
                    + "emp_salary_grade,emp_gender,emp_org_code,emp_father,emp_basic,emp_title,"
                    + "emp_exp,emp_qual,emp_yop,emp_prev_emp,emp_address,emp_active,bank_ifsc_code,emp_bank_status) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, emp.getCode());
            ps.setString(2, emp.getName());
            ps.setInt(3, emp.getDept());
            ps.setInt(4, emp.getDesig());
            ps.setInt(5, emp.getType());
            ps.setString(6, emp.getPhone());
            ps.setString(7, emp.getEmail());
            ps.setString(8, emp.getDob());
            ps.setString(9, emp.getDoj());
            ps.setString(10, emp.getBankAccNo());
            ps.setString(11, emp.getPfAccNo());
            ps.setString(12, emp.getPanNo());
            ps.setInt(13, emp.getGrade());
            ps.setBoolean(14, emp.isMale());
            ps.setInt(15, orgCode);
            ps.setString(16, emp.getFatherName());
            ps.setInt(17, emp.getCurrentBasic());
            ps.setString(18, emp.getTitle());
            ps.setInt(19, emp.getExperience());
            ps.setString(20, emp.getQualification());
            ps.setInt(21, emp.getYearOfPassing());
            ps.setString(22, emp.getPreviousEmployer());
            ps.setString(23, emp.getAddress());
            ps.setInt(24, 1);
            ps.setString(25, emp.getBankIFSCcode().trim());
            ps.setInt(26, 1);
            ps.executeUpdate();
            ps.close();
            ps = c.prepareStatement("insert into employee_login_master values(?,?,?,?)");
            ps.setString(1, emp.getCode());
            ps.setString(2, emp.getCode());
            ps.setString(3, emp.getCode());
            ps.setInt(4, orgCode);
            ps.executeUpdate();
            ps.close();
            c.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }

    public ArrayList<Employee> selectNonBankedEmployee() {
        try {
            ArrayList<Employee> employee = new ArrayList<Employee>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select emp_code,emp_name,emp_bank_status from employee_master where emp_active = '" + 1 + "' and emp_org_code = '" + orgCode + "' and emp_bank_status = '" + false + "'");
            rst = pst.executeQuery();
            while (rst.next()) {
                Employee empl = new Employee();
                empl.setCode(rst.getString(1));
                empl.setName(rst.getString(2));
                empl.setBankStatus(rst.getBoolean(3));
                System.out.println(empl.getName() + " : " + empl.getCode() + " : " + empl.getDesigName() + " : " + empl.getCurrentBasic());
                employee.add(empl);
            }
            pst.close();
            cn.close();
            return employee;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Updation For Non Employeed Bank
    public BankProfileDetails getParticularBankDetail(String bankIfsc) {
        try {
            BankProfileDetails bpd = new BankProfileDetails();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select bank_name,bank_ifsc_code,branch_name from bankprofile where bank_ifsc_code = '" + bankIfsc + "'");
            rst = pst.executeQuery();
            if (rst.next()) {
                bpd.setBankName(rst.getString(1));
                bpd.setBankIFSCCode(rst.getString(2));
                bpd.setBankBranch(rst.getString(3));
            }
            return bpd;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean updateNonBankedEmployee(ArrayList<Employee> noBankedEmployee, EmployeeSearchBean emps) {
        try {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            for (Employee emp : noBankedEmployee) {
                if (emp.isBankStatus() == true) {
                    //System.out.println("update employee_master set emp_bank_name = '" + bn + "',bank_branch_name = '" + bb + "',bank_ifsc_code = '" + emps.getBfsc() + "',emp_bank_status = '" + 1 + "' where emp_code = '" + emp.getCode() + "' and emp_org_code = '" + orgCode + "'");
                    pst = cn.prepareStatement("update employee_master set bank_ifsc_code = '" + emps.getBfsc() + "',emp_bank_status = '" + 1 + "' where emp_code = '" + emp.getCode() + "' and emp_org_code = '" + orgCode + "'");
                    pst.executeUpdate();
                    pst.clearParameters();
                }
            }
            cn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
