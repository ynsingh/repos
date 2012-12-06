/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Dropdown;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.IGNOU.ePortfolio.DAO.InstituteDao;
import org.IGNOU.ePortfolio.Model.Department;

/**
 *
 * @author vinay
 */
public class DepartmentAction extends ActionSupport {

    private static final long serialVersionUID = -2223948287805083119L;
    private Map<String, String> departmentL;
    private String instituteId;
    private InstituteDao instDao = new InstituteDao();
    private List<Department> DeptList;
    private String DeptNotFound = getText("msg.departmentNotFound");

    public DepartmentAction() {
    }

    @Override
    public String execute() throws Exception {
        DeptList = instDao.DepartmentList(Integer.valueOf(instituteId));
        departmentL = new HashMap<String, String>();
        if (DeptList.isEmpty()) {
            departmentL.put("NULL", DeptNotFound);
        } else {
            for (int j = 0; j < DeptList.size(); j++) {
                departmentL.put(DeptList.get(j).getDepartmentId() + "", DeptList.get(j).getDepartmentName() + "(" + DeptList.get(j).getDepartmentCode() + ")");
            }
        }
        return SUCCESS;
    }

    public String getJSON() throws Exception {
        return execute();
    }

    /**
     * @return the DeptNotFound
     */
    public String getDeptNotFound() {
        return DeptNotFound;
    }

    /**
     * @param DeptNotFound the DeptNotFound to set
     */
    public void setDeptNotFound(String DeptNotFound) {
        this.DeptNotFound = DeptNotFound;
    }

    /**
     * @return the departmentL
     */
    public Map<String, String> getDepartmentL() {
        return departmentL;
    }

    /**
     * @return the DeptList
     */
    public List<Department> getDeptList() {
        return DeptList;
    }

    /**
     * @param instituteId the instituteId to set
     */
    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

    /**
     * @return the instDao
     */
    public InstituteDao getInstDao() {
        return instDao;
    }

    /**
     * @param instDao the instDao to set
     */
    public void setInstDao(InstituteDao instDao) {
        this.instDao = instDao;
    }
}
