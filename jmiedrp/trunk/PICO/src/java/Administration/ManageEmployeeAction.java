/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;

import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import utils.DevelopmentSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import pojo.hibernate.Employeemaster;
import pojo.hibernate.EmployeemasterDAO;


/**
 *
 * @author erp05
 */
public class ManageEmployeeAction extends DevelopmentSupport{
     private String message;
      private Integer dmId;
    private short empId;
     public Employeemaster em;
     private List<Employeemaster> emList = new ArrayList<Employeemaster>();
     private EmployeemasterDAO emDao = new EmployeemasterDAO();

      private List<Institutionmaster> imIdList = new ArrayList<Institutionmaster>();
      private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Subinstitutionmaster> simImIdList = new ArrayList<Subinstitutionmaster>();
        private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();

    private Departmentmaster dm;
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();


    public Short getEmpId() {
        return empId;
    }

    public void setEmpId(Short empId) {
        this.empId = empId;
    }


    public List<Employeemaster> getemList() {
        return emList;
    }

    public void setdmId(Integer dmId) {
        this.dmId = dmId;
    }

    public Integer getdmId() {
        return this.dmId;
    }

    public void setimIdList(List<Institutionmaster> imIdList) {
        this.imIdList = imIdList;
    }

    public List<Institutionmaster> getimIdList() {
        return this.imIdList;
    }

    public void setsimImIdList(List<Subinstitutionmaster> simImIdList) {
        this.simImIdList = simImIdList;
    }

    public List<Subinstitutionmaster> getsimImIdList() {
        return this.simImIdList;
    }

      public void setdm(Departmentmaster dm) {
        this.dm = dm;
    }

    public Departmentmaster getdm() {
        return this.dm;
    }

    public void setem(Employeemaster em) {
        this.em = em;
    }

    public Employeemaster getem() {
        return this.em;
    }
    public void setdmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<Departmentmaster> getdmList() {
        return this.dmList;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String execute() throws Exception {
        try {
            //Initialise List for Institution, Sub-Institution and Department
            InitializeLOVs();
              return SUCCESS;
            }
        catch (Exception e)
            {
                message = "Exception in -> EmployeeAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
                return ERROR;
            }
        }

    public void InitializeLOVs() {

        //Prepare LOV containing User Institutions
        imIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        simImIdList = simDao.findBysimImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));

        return;
    }

    public String SaveEmployee() throws Exception {
        try {
            message = "Employee ID is  " + getEmpId();

            //If part saves record for the first time; else parts is for record update
            if (em.getEmpId() == null)  {
               if(em.getInstitutionmaster().getImId()==null)
                message = "Please select institution";
                else if(em.getDepartmentmaster().getDmId() == null)
                message = "Please select department";
               else if(em.getEmpFname().length()==0)
               message = "Please enter employee's First Name ";
               else if(em.getEmpEmail().length()==0)
               message = "Please enter E-Mail Address ";
               else{
                emDao.save(em);
                message = "Employee record saved successfully. Employee Id is " + em.getEmpId();
               }
               InitializeLOVs();
               em=null;
               }
            else {
                Employeemaster em2 = emDao.findByempId(em.getEmpId());
                em2 = em;
                emDao.update(em2);
                message = "Employee record updated successfully.";
                InitializeLOVs();
               }
            
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Save method -> EmployeeAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;

        }
    }


    public String Clear() throws Exception {
        try {
            em = null;

            //Initialise List for Institution, Sub-Institution and Department
            InitializeLOVs();

            return SUCCESS;
            }
        catch (Exception e)
            {
            message = "Exception in Clear method -> EmployeeAxn" + e.getMessage();
            return ERROR;
            }
        }


    public String Browse() throws Exception {
        try {
            emList = emDao.findAll();
            return SUCCESS;
            }
        catch (Exception e)
            {
            message = "Exception in Browse method -> EmployeeAxn" + e.getMessage();
            return ERROR;
            }
        }


public String Edit() throws Exception {
        try {
            //Initialise List for Institution, Sub-Institution and Department
 //           InitializeLOVs(getEmpId());

            //Get the record having specified Institution ID              (getempId())
            em = emDao.findByempId(getEmpId());

            imIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            simImIdList = simDao.findBysimImId(em.getInstitutionmaster().getImId());
            dmList = dmDao.findBydmSimId(em.getSubinstitutionmaster().getSimId());

            message = "Employee ID is  " + em.getEmpId();

            return SUCCESS;
        }   catch (Exception e) {
            message = "Exception in Edit method -> EmployeeAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


public String Delete() {
    em = emDao.findByempId(getEmpId());
    emDao.delete(em);
    emList = emDao.findAll();
    return SUCCESS;
    }

}