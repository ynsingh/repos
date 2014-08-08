/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;

import java.io.*;
import java.io.InputStream;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.Employeemaster;
import pojo.hibernate.EmployeemasterDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;

import utils.DevelopmentSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;

//import java.util.Locale;
//import java.util.ResourceBundle;
//import com.opensymphony.xwork2.ActionContext;

/**
 *
 * @author erp05
 */
public class ManageEmployeeAction extends DevelopmentSupport {

    private String message;
    private Integer dmId;
    private short empId;
    public Employeemaster em;
    private InputStream inputStream;
    private List<Employeemaster> emList = new ArrayList<Employeemaster>();
    private EmployeemasterDAO emDao = new EmployeemasterDAO();
    private List<Institutionmaster> imIdList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Subinstitutionmaster> simImIdList = new ArrayList<Subinstitutionmaster>();
    private List<ErpmGenMaster> designationList = new ArrayList<ErpmGenMaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private ErpmGenMasterDao designationDao = new ErpmGenMasterDao();
    private Departmentmaster dm;
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    static String dataSourceURL=null;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

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

    public void setdesignationList(List<ErpmGenMaster> designationList) {
        this.designationList = designationList;
    }

    public List<ErpmGenMaster> getdesignationList() {
        return this.designationList;
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
        } catch (Exception e) {
            message = "Exception in -> EmployeeAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public void InitializeLOVs() {
        //Prepare LOV containing User Institutions
        imIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        simImIdList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
        designationList = designationDao.findByErpmGmType(Short.parseShort("19"));
        return;
    }

    public String SaveEmployee() throws Exception {
        try {
            message = "Employee ID is  " + getEmpId();

            //If part saves record for the first time; else parts is for record update
            if (em.getEmpId() == null) {
                if (em.getInstitutionmaster().getImId() == null) {
                    message = "Please select institution";
                } else if (em.getDepartmentmaster().getDmId() == null) {
                    message = "Please select department";
                } else if (em.getEmpFname().length() == 0) {
                    message = "Please enter employee's First Name ";
                } else if (em.getEmpEmail().length() == 0) {
                    message = "Please enter E-Mail Address ";
                } else {
                    emDao.save(em);
                    message = "Employee record saved successfully. Employee Id is " + em.getEmpId();
                }
                InitializeLOVs();
                em = null;
            } else {
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
        } catch (Exception e) {
            message = "Exception in Clear method -> EmployeeAxn" + e.getMessage();
            return ERROR;
        }
    }

    public String Browse() throws Exception {
        try {
            emList = emDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> EmployeeAxn" + e.getMessage();
            return ERROR;
        }
    }

    public String Edit() throws Exception {
        try {
            //Initialise List for Institution, Sub-Institution and Department
            InitializeLOVs();

            //Get the record having specified Employee ID              (getempId())
            em = emDao.findByempId(getEmpId());

            /*imIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            simImIdList = simDao.findBysimImId(em.getInstitutionmaster().getImId());
            dmList = dmDao.findBydmSimId(em.getSubinstitutionmaster().getSimId());
*/
            message = "Employee ID is  " + em.getEmpId();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> EmployeeAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String Print() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");
       
        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Administration"  + pathSeparator + "Reports" + pathSeparator + "Employee.jasper" ;
        
        String fileName = getSession().getServletContext().getRealPath(repPath);

        //String fileName = getSession().getServletContext().getRealPath("pico\\Administration\\Reports\\Employee.jasper");


        String whereCondition = "";

        try {
//            Locale locale = ActionContext.getContext().getLocale();
//            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JNDI");
            }
            dataSourceURL = (String) ctx.lookup("java:comp/env/ReportURL").toString();
            Connection conn = DriverManager.getConnection(dataSourceURL);

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=Employee.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //Setup Where Condition Clause
            if (em.getEmpFname().toUpperCase().isEmpty()) {
                whereCondition = " upper(employeemaster.EMP_FName) like '%'";
            } else {
                whereCondition = " upper(employeemaster.EMP_FName) like '%" + em.getEmpFname().toUpperCase() + "%'";
            }

            if (em.getEmpMname().toUpperCase().isEmpty()) {
                whereCondition = whereCondition + " and upper(employeemaster.EMP_MName) like '%'";
            } else {
                whereCondition = whereCondition + " and upper(employeemaster.EMP_MName) like '%" + em.getEmpMname().toUpperCase() + "%'";
            }

            if (em.getEmpLname().toUpperCase().isEmpty()) {
                whereCondition = whereCondition + " and upper(employeemaster.EMP_LName) like '%'";
            } else {
                whereCondition = whereCondition + " and upper(employeemaster.EMP_LName) like '%" + em.getEmpLname().toUpperCase() + "%'";
            }

            if (em.getInstitutionmaster().getImId() == null) {
                whereCondition = whereCondition + " and employeemaster.EMP_im_id <> 0 ";
            } else {
                whereCondition = whereCondition + " and employeemaster.EMP_im_id = " + em.getInstitutionmaster().getImId();
            }

            if (em.getSubinstitutionmaster().getSimId() == null) {
                whereCondition = whereCondition + " and employeemaster.EMP_sim_id <> 0 ";
            } else {
                whereCondition = whereCondition + " and employeemaster.EMP_sim_id = " + em.getSubinstitutionmaster().getSimId();
            }

            if (em.getDepartmentmaster().getDmId() == null) {
                whereCondition = whereCondition + " and employeemaster.EMP_dm_id <> 0 ";
            } else {
                whereCondition = whereCondition + " and employeemaster.EMP_dm_id = " + em.getDepartmentmaster().getDmId();
            }

            hm.put("condition", whereCondition);

            JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
            JasperExportManager.exportReportToPdfStream(jp, baos);
            response.setContentLength(baos.size());
            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            inputStream = bis;

            return SUCCESS;
        } catch (JRException e) {
            message = "Error is : " + e.getMessage() + e.getCause();
            return ERROR;
        }
    }

    public String Delete() {
        try {
            em = emDao.findByempId(getEmpId());
            emDao.delete(em);
            emList = emDao.findAll();
            return SUCCESS;
            }catch (Exception e) {

            if (e.getCause().toString().contains("java.sql.BatchUpdateException: Cannot delete or update a parent row")) {
                message = "Cannot delete record as related record(s) exist(s). Reported cause is         :" + e.getCause();
            }else{
            message = "Error is : " + e.getMessage() + " Reported Cause is: " +  e.getCause();
            }
            return ERROR;
        }

    }
}
