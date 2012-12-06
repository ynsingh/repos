/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import com.opensymphony.xwork2.ActionSupport;
import org.IGNOU.ePortfolio.DAO.DepartmentDao;
import org.IGNOU.ePortfolio.Model.Department;
import org.IGNOU.ePortfolio.Model.Institute;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 23-04-2012
 */
public class DepartmentAction extends ActionSupport {

    //private String user_id = new UserSession().getUserInSession();
    private Department dept = new Department();
    private DepartmentDao dao = new DepartmentDao();
    private String instituteId, univCode;
    private Institute inst = new Institute();
    private String departmentName;
    private String departmentCode;
    private String introduction;
    private String postalAddress;
    private Integer phoneCode;
    private Integer phoneNo;
    private long mobileNo;
    private Integer fax;
    private String deptEmailId;

    public DepartmentAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.RegDept(Integer.valueOf(instituteId), departmentName, departmentCode, introduction, postalAddress, phoneCode, phoneNo, mobileNo, fax, deptEmailId);
        return SUCCESS;
    }

    /**
     * @return the dept
     */
    public Department getDept() {
        return dept;
    }

    /**
     * @param dept the dept to set
     */
    public void setDept(Department dept) {
        this.dept = dept;
    }

    /**
     * @return the dao
     */
    public DepartmentDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(DepartmentDao dao) {
        this.dao = dao;
    }

    /**
     * @return the instituteId
     */
    public String getInstituteId() {
        return instituteId;
    }

    /**
     * @param instituteId the instituteId to set
     */
    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

    /**
     * @return the univCode
     */
    public String getUnivCode() {
        return univCode;
    }

    /**
     * @param univCode the univCode to set
     */
    public void setUnivCode(String univCode) {
        this.univCode = univCode;
    }

    /**
     * @return the inst
     */
    public Institute getInst() {
        return inst;
    }

    /**
     * @param inst the inst to set
     */
    public void setInst(Institute inst) {
        this.inst = inst;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the departmentCode
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * @param departmentCode the departmentCode to set
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    /**
     * @return the introduction
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction the introduction to set
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * @return the postalAddress
     */
    public String getPostalAddress() {
        return postalAddress;
    }

    /**
     * @param postalAddress the postalAddress to set
     */
    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    /**
     * @return the phoneCode
     */
    public Integer getPhoneCode() {
        return phoneCode;
    }

    /**
     * @param phoneCode the phoneCode to set
     */
    public void setPhoneCode(Integer phoneCode) {
        this.phoneCode = phoneCode;
    }

    /**
     * @return the phoneNo
     */
    public Integer getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo the phoneNo to set
     */
    public void setPhoneNo(Integer phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * @return the fax
     */
    public Integer getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(Integer fax) {
        this.fax = fax;
    }

    /**
     * @return the deptEmailId
     */
    public String getDeptEmailId() {
        return deptEmailId;
    }

    /**
     * @param deptEmailId the deptEmailId to set
     */
    public void setDeptEmailId(String deptEmailId) {
        this.deptEmailId = deptEmailId;
    }

    /**
     * @return the mobileNo
     */
    public long getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo the mobileNo to set
     */
    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }
}
