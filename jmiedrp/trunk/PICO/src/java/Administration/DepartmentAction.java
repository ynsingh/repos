package Administration;

import java.io.*;

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
import pojo.hibernate.Statemaster;
import pojo.hibernate.StatemasterDAO;
import pojo.hibernate.Countrymaster;
import pojo.hibernate.CountrymasterDAO;
import pojo.hibernate.Employeemaster;
import pojo.hibernate.EmployeemasterDAO;
import utils.DevelopmentSupport;

import pojo.hibernate.Committeemaster;
import pojo.hibernate.CommitteemasterDAO;

import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import java.util.Locale;
import java.util.ResourceBundle;
import com.opensymphony.xwork2.ActionContext;

/**
 *
 * @author kazim
 */
public class DepartmentAction extends DevelopmentSupport {

    private Institutionmaster im;
    private Subinstitutionmaster sim;
    private Departmentmaster dm;
    private Committeemaster cm;
    private ErpmGenMaster egm;
    private InputStream inputStream;
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private List<Institutionmaster> imIdList = new ArrayList<Institutionmaster>();
    private List<Subinstitutionmaster> simImIdList = new ArrayList<Subinstitutionmaster>();
    private List<Statemaster> statemasterList = new ArrayList<Statemaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private StatemasterDAO statemasterDao = new StatemasterDAO();
    private List<Countrymaster> ctList = new ArrayList<Countrymaster>();
    private CountrymasterDAO cmDao = new CountrymasterDAO();
    private EmployeemasterDAO empDao = new EmployeemasterDAO();
    private List<Employeemaster> empList = new ArrayList<Employeemaster>();
    private String message;
    private Integer dmId;
    private CommitteemasterDAO comDAO = new CommitteemasterDAO();
    private ErpmGenMasterDao GMDao = new ErpmGenMasterDao();

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setegm(ErpmGenMaster egm) {
        this.egm = egm;
    }

    public ErpmGenMaster getegm() {
        return this.egm;
    }

    public void setcm(Committeemaster cm) {
        this.cm = cm;
    }

    public Committeemaster cm() {
        return this.cm;
    }

    public void setim(Institutionmaster im) {
        this.im = im;
    }

    public Institutionmaster getim() {
        return this.im;
    }

    public void setsim(Subinstitutionmaster sim) {
        this.sim = sim;
    }

    public Subinstitutionmaster getsim() {
        return this.sim;
    }

    public void setdm(Departmentmaster dm) {
        this.dm = dm;
    }

    public Departmentmaster getdm() {
        return this.dm;
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

    public void setstatemasterList(List<Statemaster> statemasterList) {
        this.statemasterList = statemasterList;
    }

    public List<Statemaster> getstatemasterList() {
        return this.statemasterList;
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

    public void setctList(List<Countrymaster> ctList) {
        this.ctList = ctList;
    }

    public List<Countrymaster> getctList() {
        return this.ctList;
    }

    public void setempList(List<Employeemaster> empList) {
        this.empList = empList;
    }

    public List<Employeemaster> getempList() {
        return this.empList;
    }

    public void InitializeLOVs() {
        //Prepare LOV containing User Institutions
        imIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

        //Prepare LOV containing the SubInstitutions forthe selected Institution
        if (getSession().getAttribute("isAdministrator").toString().compareTo("Administrator") == 0) {
            simImIdList = simDao.findSubInstForAdmin(Short.valueOf(getSession().getAttribute("imId").toString()));
        } else {
            simImIdList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        }

        //Prepare LOVS for Country and State
        statemasterList = statemasterDao.findAll();
        ctList = cmDao.findAll();

        return;
    }

    public void InitializeLOVsForEdit() {

        //Prepare Institution LOV
        imIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

        //Prepare Sub-Institution LOV
        simImIdList = simDao.findBysimImId(dm.getInstitutionmaster().getImId());

        //Prepare Employees LOV
        empList = empDao.findByImId(dm.getInstitutionmaster().getImId());

        //Prepare LOVS for Country and State
        statemasterList = statemasterDao.findAll();
        ctList = cmDao.findAll();

        return;
    }

    @Override
    public String execute() throws Exception {
        try {

            InitializeLOVs();
            return SUCCESS;

        } catch (Exception e) {
            message = "Exception in -> DepartmentAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String Browse() throws Exception {
        try {

            dmList = dmDao.findDepartmentForAdmin(Short.valueOf(getSession().getAttribute("imId").toString()));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> DepartmentAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update
            if (dm.getDmId() == null) {
                if (dm.getEmployeemaster().getEmpId() == null) {
                    dm.setEmployeemaster(null);
                }
                dmDao.save(dm);
                egm = GMDao.findByErpmGmId(Short.parseShort("64"));
                cm.setDepartmentmaster(dm);
                cm.setInstitutionmaster(dm.getInstitutionmaster());
                cm.setSubinstitutionmaster(dm.getSubinstitutionmaster());
                cm.setErpmGenMaster(egm);
                String stremail = dm.getDmEmailId().toString();
                cm.setCommitteeConvener(stremail);
                String str = dm.getDmName() + ", " + dm.getDmHeadDesignation();
                cm.setCommitteeName(str);
                cm.setCommitteeActive('t');
                cm.setCommitteeLevel('D');
                String strCommPurpose = "Head of The Department";
                cm.setCommmitteePurpose(strCommPurpose);

                comDAO.save(cm);
                message = "Department record saved successfully. Departmenyt Id is " + dm.getDmId();
                InitializeLOVs();
                dm = null;
            } else {
                Departmentmaster dm2 = dmDao.findByDmId(dm.getDmId());
                dm2 = dm;

                if (dm2.getEmployeemaster().getEmpId() == 0) {
                    dm2.setEmployeemaster(null);
                }

                dmDao.update(dm2);
                message = "Department record updated successfully.";
                InitializeLOVsForEdit();
            }
            dmList = dmDao.findAll();


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Save method -> DepartmentAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;

        }
    }

    public String Edit() throws Exception {
        try {

            dm = dmDao.findByDmId(getdmId());
            InitializeLOVsForEdit();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> DepartmentAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Delete() throws Exception {
        try {
            dm = dmDao.findByDmId(getdmId());
            dmDao.delete(dm);
            dmList = dmDao.findAll();
            return SUCCESS;
        } catch (Exception e) {
            if (e.getMessage().toUpperCase().contains("NOT EXECUTE JDBC BATCH") == true) {
                message = "Related record(s) exists. Cann't delete";
            } else {
                message = "Exception in Delete method -> DepartmentAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            }
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
        String repPath = "pico" + pathSeparator + "Administration"  + pathSeparator + "Reports" + pathSeparator + "Department.jasper" ;
        
//        String fileName = getSession().getServletContext().getRealPath("pico\\Administration\\Reports\\ShowGFRMappedinProgram.jasper");
        
        String fileName = getSession().getServletContext().getRealPath(repPath);

//        String fileName = getSession().getServletContext().getRealPath("pico\\Administration\\Reports\\Department.jasper");
        String whereCondition;

        try {
            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 
            
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=Department.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //Setup Where Condition Clause
            if (dm.getInstitutionmaster().getImId() == null || dm.getInstitutionmaster().getImId() == 0) {
                whereCondition = "departmentmaster.DM_im_id = " + Short.valueOf(getSession().getAttribute("imId").toString());
            } else {
                whereCondition = "departmentmaster.DM_im_id = " + dm.getInstitutionmaster().getImId();
            }

            if (dm.getSubinstitutionmaster().getSimId() == null || dm.getSubinstitutionmaster().getSimId() == 0) {
                whereCondition = whereCondition + " and departmentmaster.DM_sim_id <> 0 ";
            } else {
                whereCondition = whereCondition + " and departmentmaster.DM_sim_id = " + dm.getSubinstitutionmaster().getSimId();
            }
            
            if (dm.getDmName().toUpperCase().isEmpty()) {
                whereCondition = whereCondition + " and upper(departmentmaster.dm_name) like '%'";
            } else {
                whereCondition = whereCondition + " and upper(departmentmaster.dm_name) like '%" + dm.getDmName().toUpperCase() + "%'";
            }
            if (dm.getStatemaster().getStateId() == null || dm.getStatemaster().getStateId() == 0) {
                whereCondition = whereCondition + " and departmentmaster.DM_state_id <> 0 ";
            } else {
                whereCondition = whereCondition + " and departmentmaster.DM_state_id = " + dm.getStatemaster().getStateId();
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

    public String Clear() throws Exception {
        try {

            dm = null;

            imIdList = imDao.findAll();
            simImIdList = simDao.findAll();
            statemasterList = statemasterDao.findAll();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> DepartmentAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public void validate() {
        try {

            if (dm.getInstitutionmaster().getImId() == 0) {
                addFieldError("dm.institutionmaster.imId", "Please select institution from the list");
            }
            if (dm.getSubinstitutionmaster().getSimId() == 0) {
                addFieldError("dm.subinstitutionmaster.simId", "Please select College/Faculty/School from the list");
            }
            if (dm.getDmName().length() == 0) {
                addFieldError("dm.dmName", "Please enter Department's Name");
            }
            if (dm.getDmShortName().length() == 0) {
                addFieldError("dm.dmShortName", "Please enter Short Name for the Department");
            }
            if (dm.getDmHeadDesignation().length() == 0) {
                addFieldError("dm.dmHeadDesignation", "Please enter designation (Head/Director etc.)");
            }
            if (dm.getDmAddressLine1().length() == 0) {
                addFieldError("dm.dmAddressLine1", "Please enter address of the Department");
            }
            if (dm.getCountrymaster().getCountryId() == 0) {
                addFieldError("dm.countrymaster.countryId", "Please select the country");
            }
            if (dm.getStatemaster().getStateId() == 0) {
                addFieldError("dm.statemaster.stateId", "Please select the state");
            }
            if (dm.getDmPinNo().length() == 0) {
                addFieldError("dm.dmPinNo", "Please enter the Pin No");
            }
            if (dm.getDmEmailId().length() == 0) {
                addFieldError("dm.dmEmailId", "Please enter E-Mail Id for the Department");
            }
            //Initialize LOVs
            InitializeLOVs();

        } catch (NullPointerException npe) {
        };
    }
}
