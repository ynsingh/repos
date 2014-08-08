/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;

/**
 *
 * @author kazimClear
 */
import java.io.*;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.Departmentmaster;
import java.io.InputStream;
import pojo.hibernate.Statemaster;
import pojo.hibernate.StatemasterDAO;

import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;



import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.Countrymaster;
import pojo.hibernate.CountrymasterDAO;
import pojo.hibernate.Employeemaster;
import pojo.hibernate.EmployeemasterDAO;
import utils.DevelopmentSupport;

import pojo.hibernate.Committeemaster;
import pojo.hibernate.CommitteemasterDAO;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import java.sql.Connection;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

//import java.util.Locale;
//import java.util.ResourceBundle;
//import com.opensymphony.xwork2.ActionContext;

public class SubInstitutionAction extends DevelopmentSupport {

    private Institutionmaster im;
    //  private Subinstitutionmaster sim;
    private Departmentmaster dm;
    private Committeemaster cm;
    private ErpmGenMaster egm;
    private InputStream inputStream;
    private Subinstitutionmaster sim;// = new Subinstitutionmaster();
    private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<Institutionmaster> simImIdList = new ArrayList<Institutionmaster>();
    private List<ErpmGenMaster> simTypeList = new ArrayList<ErpmGenMaster>();
    private List<Statemaster> statemasterList = new ArrayList<Statemaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private ErpmGenMasterDao simTypeDao = new ErpmGenMasterDao();
    private StatemasterDAO stDao = new StatemasterDAO();
    private List<Countrymaster> ctList = new ArrayList<Countrymaster>();
    private CountrymasterDAO cmDao = new CountrymasterDAO();
    private EmployeemasterDAO empDao = new EmployeemasterDAO();
    private List<Employeemaster> empList = new ArrayList<Employeemaster>();
    private CommitteemasterDAO comDAO = new CommitteemasterDAO();
    private ErpmGenMasterDao GMDao = new ErpmGenMasterDao();
    private String message;
    private Integer SIMID;
	
    static String dataSourceURL=null;

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

    public void setdm(Departmentmaster dm) {
        this.dm = dm;
    }

    public Departmentmaster getdm() {
        return this.dm;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Subinstitutionmaster getsim() {
        return sim;
    }

    public void setsim(Subinstitutionmaster sim) {
        this.sim = sim;
    }

    public List<Institutionmaster> getsimImIdList() {
        return simImIdList;
    }

    public void setsimImIdList(List<Institutionmaster> simImIdList) {
        this.simImIdList = simImIdList;
    }

    public List<ErpmGenMaster> getsimTypeList() {
        return simTypeList;
    }

    public void setsimTypeList(List<ErpmGenMaster> simTypeList) {
        this.simTypeList = simTypeList;
    }

    public List<Statemaster> getStatemasterList() {
        return statemasterList;
    }

    public void setStatemasterList(List<Statemaster> statemasterList) {
        this.statemasterList = statemasterList;
    }

    public List<Subinstitutionmaster> getsimList() {
        return simList;
    }

    public void setsimList(List<Subinstitutionmaster> simList) {
        this.simList = simList;
    }

    public Integer getSIMID() {
        return SIMID;
    }

    public void setSIMID(Integer SIMID) {
        this.SIMID = SIMID;
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

    @Override
    @SkipValidation
    public String execute() throws Exception {
        try {
            InitializeLOVs();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Execute method -> SubInstitutionAxn" + e.getMessage() + e.getCause();
            return ERROR;
        }
    }

    public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update            
            if (sim.getSimId() == null) {

                if (sim.getEmployeemaster().getEmpId() == 0) {
                    sim.setEmployeemaster(null);
                }
                simDao.save(sim);

                egm = GMDao.findByErpmGmId(Short.parseShort("64"));
                cm.setDepartmentmaster(null);
                cm.setInstitutionmaster(sim.getInstitutionmaster());
                cm.setSubinstitutionmaster(sim);
                cm.setErpmGenMaster(egm);
                String stremail = sim.getSimEmailId().toString();
                cm.setCommitteeConvener(stremail);
                String str = sim.getSimName() + ", " + sim.getSimHeadDesignation();
                cm.setCommitteeName(str);
                cm.setCommitteeActive('t');
                cm.setCommitteeLevel('I');
                String strCommPurpose = "Head of The SubInstitution";
                cm.setCommmitteePurpose(strCommPurpose);

                comDAO.save(cm);

                message = "Sub Institution record saved successfully. Sub-Institution Id is " + sim.getSimId();
            } else {
                message = "" + sim.getSimId();
                Subinstitutionmaster sim1 = simDao.findBySimId(sim.getSimId());
                sim1 = sim;
                simDao.update(sim1);
                message = "Sub Institution record saved successfully.";
            }
            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("UNIQUE_SIM_IM_ID_SIM_Name")) {
                message = "A subinstiutution record with the name '" + sim.getSimName() + "' already exists for the institution.";
            } else {
                message = "Exception in Save method -> ManageItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            }
            return ERROR;
        }
    }

    @SkipValidation
    public String Browse() throws Exception {
        try {
            if (getSession().getAttribute("isAdministrator").toString().compareTo("Administrator") == 0) {
                simList = simDao.findSubInstForAdmin(Short.valueOf(getSession().getAttribute("imId").toString()));
            } else {
                simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
            }
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> SubInstitutionAxn" + e.getMessage();
            return ERROR;
        }
    }

    public String Edit() throws Exception {
        try {

            sim = simDao.findBySimId(getSIMID());

            //Prepare List of Employees workinf for the Institution 
            empList = empDao.findByImId(sim.getInstitutionmaster().getImId());

            //Prepate List of Institutions for the User
            simImIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            //Prepate List of Sub-Institutions Type
            simTypeList = simTypeDao.findByErpmGmType(Short.parseShort("5"));

            //Prepate Country List
            ctList = cmDao.findAll();

            //Prepate State List
            statemasterList = stDao.findAll();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> SubInstitutionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String Delete() {

	try {
            sim = simDao.findBySimId(getSIMID());
            simDao.delete(sim);
            simList = simDao.findAll();
            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("java.sql.BatchUpdateException: Cannot delete or update a parent row")) {
                message = "Cannot delete record as related record(s) exist(s). Reported cause is         :" + e.getCause();
            }else{
            message = "Exception in Delete method -> SubInstitutionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            }
            return ERROR;
        }
    }

    @SkipValidation
    public String Clear() throws Exception {
        try {
            sim = null;
            InitializeLOVs();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Clear method -> SubInstitutionAxn" + e.getMessage() + e.getCause();
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
        String repPath = "pico" + pathSeparator + "Administration"  + pathSeparator + "Reports" + pathSeparator + "SubInstitution.jasper" ;
        
        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\Administration\\Reports\\SubInstitution.jasper");


        String whereCondition;

        try {
  //          Locale locale = ActionContext.getContext().getLocale();
  //          ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
  //          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 
  //
            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JNDI");
            }
            dataSourceURL = (String) ctx.lookup("java:comp/env/ReportURL").toString();
            Connection conn = DriverManager.getConnection(dataSourceURL);

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=SubInstitutions.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            //Setup Where Condition Clause
            if (sim.getSimName().toUpperCase().isEmpty()) {
                whereCondition = " upper(subinstitutionmaster.sim_name) like '%'";
            } else {
                whereCondition = " upper(subinstitutionmaster.sim_name) like '%" + sim.getSimName().toUpperCase() + "%'";
            }

            if (sim.getErpmGenMaster().getErpmgmEgmId() == null || sim.getErpmGenMaster().getErpmgmEgmId() == 0) {
                whereCondition = whereCondition + " and subinstitutionmaster.sim_type <> 0 ";
            } else {
                whereCondition = whereCondition + " and subinstitutionmaster.sim_type = " + sim.getErpmGenMaster().getErpmgmEgmId() + "";
            }

            if (sim.getStatemaster().getStateId() == null || sim.getStatemaster().getStateId() == 0) {
                whereCondition = whereCondition + " and subinstitutionmaster.sim_state_id <> 0 ";
            } else {
                whereCondition = whereCondition + " and subinstitutionmaster.sim_state_id = " + sim.getStatemaster().getStateId() + "";
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

    public void validate() {
        try {

            if (sim.getInstitutionmaster().getImId() == 0) {
                addFieldError("sim.institutionmaster.imId", "Please select institution from the list");
            }
            if (sim.getSimName().length() == 0) {
                addFieldError("sim.simName", "Please enter College/Faculty/School Name");
            }
            if (sim.getErpmGenMaster().getErpmgmEgmId() == 0) {
                addFieldError("sim.erpmGenMaster.erpmgmEgmId", "Please select College/Faculty/School Type");
            }
            if (sim.getSimShortName().length() == 0) {
                addFieldError("sim.simShortName", "Please enter Short Name for the College/Faculty/School");
            }
            if (sim.getSimAddressLine1().length() == 0) {
                addFieldError("sim.simAddressLine1", "Please enter address for the College/Faculty/School");
            }
            if (sim.getCountrymaster().getCountryId() == 0) {
                addFieldError("sim.countrymaster.countryId", "Please select country for the College/Faculty/School");
            }
            if (sim.getStatemaster().getStateId() == 0) {
                addFieldError("sim.statemaster.stateId", "Please select state for the College/Faculty/School");
            }
            if (sim.getSimHeadDesignation().length() == 0) {
                addFieldError("sim.simHeadDesignation", "Please enter Head's designation for the College/Faculty/School");
            }
            if (sim.getSimEmailId().length() == 0) {
                addFieldError("sim.simEmailId", "Please enter E-Mail Id for the College/Faculty/School");
            }
            //Initialize LOVs
            InitializeLOVs();

        } catch (NullPointerException npe) {
        };
    }

    public void InitializeLOVs() {
        simImIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        simTypeList = simTypeDao.findByErpmGmType(Short.parseShort("5"));
        statemasterList = stDao.findAll();
        ctList = cmDao.findAll();
        empList = empDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

    }
}
