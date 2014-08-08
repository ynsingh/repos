/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PrePurchase;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.ErpmTenderMaster;
import pojo.hibernate.ErpmTenderMasterDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import utils.DateUtilities;
import utils.DevelopmentSupport;

//import java.util.Locale;
//import java.util.ResourceBundle;
//import com.opensymphony.xwork2.ActionContext;

/**
 *
 * @author Faraz Ahmad, Tanvir Ahmed
 */
public class TenderMasterAction extends DevelopmentSupport {

    private List<Institutionmaster> ImIdList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Subinstitutionmaster> SimImIdList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<Departmentmaster> DmList = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private Character erpmimSerialNoApplicable;
    private ErpmTenderMaster ermptendermaster = new ErpmTenderMaster();
    private ErpmTenderMasterDAO tmDao = new ErpmTenderMasterDAO();
    private String message;
    private InputStream inputStream;
    private static Boolean varShowGFR;
    private List<ErpmTenderMaster> erpmTenderMasterList = new ArrayList<ErpmTenderMaster>();
    private ErpmTenderMasterDAO erpmTenderMasterDao = new ErpmTenderMasterDAO();
    private List<ErpmGenMaster> tendertypeList = new ArrayList<ErpmGenMaster>();
    private ErpmGenMasterDao tendertypedao = new ErpmGenMasterDao();
    private List<ErpmGenMaster> tendertypeList1 = new ArrayList<ErpmGenMaster>();
    private ErpmGenMasterDao tendertypedao1 = new ErpmGenMasterDao();
    private Short DefaultInsitute;
    private Integer DefaultSubInsitute;
    private Integer DefaultDepartment;
    private Integer STUDID;
    private Integer tmTmId;
    private String tenDate;
    private  String TenderNo;
    short sh1 = 26;
    short sh = 22;
    private Integer TMTMID;

    static String dataSourceURL=null;

    /*getter setter */
    public Integer getTMTMID() {
        return TMTMID;
    }

    public void setTMTMID(Integer TMTMID) {
        this.TMTMID = TMTMID;
    }

    public String gettenDate() {
        return tenDate;
    }

    public void settenDate(String tenDate) {
        this.tenDate = tenDate;
    }

     public String getTenderNo() {
        return TenderNo;
    }

    public void setTenderNo(String tmTenderNo) {
        this.TenderNo = TenderNo;
    }

    public Integer getTmTmId() {
        return tmTmId;
    }

    public void setTmTmId(Integer tmTmId) {
        this.tmTmId = tmTmId;
    }

    public Integer getSTUDID() {
        return STUDID;
    }

    public void setSTUDID(Integer STUDID) {
        this.STUDID = STUDID;
    }

    public Short getDefaultInsitute() {
        return DefaultInsitute;
    }

    public void setDefaultInsitute(Short DefaultInsitute) {
        this.DefaultInsitute = DefaultInsitute;
    }

    public Integer getDefaultSubInsitute() {
        return DefaultSubInsitute;
    }

    public void setDefaultSubInsitute(Integer DefaultSubInsitute) {
        this.DefaultSubInsitute = DefaultSubInsitute;
    }

    public Integer getDefaultDepartment() {
        return DefaultDepartment;
    }

    public void setDefaultDepartment(Integer DefaultDepartment) {
        this.DefaultDepartment = DefaultDepartment;
    }

    public List<Subinstitutionmaster> getSimImIdList() {
        return SimImIdList;
    }

    public void setSimImIdList(List<Subinstitutionmaster> SimImIdList) {
        this.SimImIdList = SimImIdList;
    }

    public List<Institutionmaster> getImIdList() {
        return ImIdList;
    }

    public void setImIdList(List<Institutionmaster> ImIdList) {
        this.ImIdList = ImIdList;
    }

    public InstitutionmasterDAO getImDao() {
        return imDao;
    }

    public void setImDao(InstitutionmasterDAO imDao) {
        this.imDao = imDao;
    }

    public SubinstitutionmasterDAO getSimDao() {
        return simDao;
    }

    public void setSimDao(SubinstitutionmasterDAO simDao) {
        this.simDao = simDao;
    }

    public List<Departmentmaster> getDmList() {
        return DmList;
    }

    public void setDmList(List<Departmentmaster> DmList) {
        this.DmList = DmList;
    }

    public DepartmentmasterDAO getDmDao() {
        return dmDao;
    }

    public void setDmDao(DepartmentmasterDAO dmDao) {
        this.dmDao = dmDao;
    }

    public List<ErpmTenderMaster> getErpmTenderMasterList() {
        return erpmTenderMasterList;
    }

    public void setErpmTenderMasterList(List<ErpmTenderMaster> erpmTenderMasterList) {
        this.erpmTenderMasterList = erpmTenderMasterList;
    }

    public ErpmTenderMasterDAO getErpmTenderMasterDao() {
        return erpmTenderMasterDao;
    }

    public void setErpmTenderMasterDao(ErpmTenderMasterDAO erpmTenderMasterDao) {
        this.erpmTenderMasterDao = erpmTenderMasterDao;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErpmGenMaster> getTendertypeList1() {
        return tendertypeList1;
    }

    public void setTendertypeList1(List<ErpmGenMaster> tendertypeList1) {
        this.tendertypeList1 = tendertypeList1;
    }

    public ErpmGenMasterDao getTendertypedao1() {
        return tendertypedao1;
    }

    public void setTendertypedao1(ErpmGenMasterDao tendertypedao1) {
        this.tendertypedao1 = tendertypedao1;
    }

    public List<ErpmGenMaster> getTendertypeList() {
        return tendertypeList;
    }

    public void setTendertypeList(List<ErpmGenMaster> tendertypeList) {
        this.tendertypeList = tendertypeList;
    }

    public ErpmTenderMaster getErmptendermaster() {
        return ermptendermaster;
    }

    public void setErmptendermaster(ErpmTenderMaster ermptendermaster) {
        this.ermptendermaster = ermptendermaster;
    }

    public ErpmTenderMasterDAO getTmDao() {
        return tmDao;
    }

    public void setTmDao(ErpmTenderMasterDAO tmDao) {
        this.tmDao = tmDao;
    }

    public Boolean getVarShowGFR() {
        return varShowGFR;
    }

    public void setVarShowGFR(Boolean varShowGFR) {
        this.varShowGFR = varShowGFR;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /* Method's */
    @Override
    public String execute() throws Exception {
        try {
            ermptendermaster = null;
            InitializeLOVs();
            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            settenDate(d1.convertDateToString(d, "dd-MM-yyyy"));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String BrowseTender() throws Exception {
        try {

            InitializeLOVs();
            erpmTenderMasterList = erpmTenderMasterDao.findByImId(Short.parseShort(getSession().getAttribute("imId").toString()));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> ManageItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String SaveTender() throws Exception {
        try {
            //InitializeLOVs();
            DateUtilities dt = new DateUtilities();
            if (ermptendermaster.getTmTmId() == null) {

                ermptendermaster.setTmDate(dt.convertStringToDate(gettenDate()));
                tmDao.save(ermptendermaster);
//                          
                message = "SaveTender record saved successfully. SaveTender Id is " + ermptendermaster.getTmTmId();
                // ClearTender();

            } else {

                InitializeLOVs();
                erpmTenderMasterList = erpmTenderMasterDao.findByImId(Short.parseShort(getSession().getAttribute("imId").toString()));
                ErpmTenderMaster em2 = erpmTenderMasterDao.findByTenderMasterId(ermptendermaster.getTmTmId());
                em2 = ermptendermaster;
                em2.setTmDate(dt.convertStringToDate(gettenDate()));

                erpmTenderMasterDao.update(em2);

                message = "Record updated successfully.";

            }
            // InitializeLOVs();
            ClearTender();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Save method -> " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String DeleteTender() throws Exception {
        try {
            //Retrieve the record to be deleted
            ermptendermaster = erpmTenderMasterDao.findByTenderMasterId(getSTUDID());
            erpmTenderMasterDao.delete(ermptendermaster);
            erpmTenderMasterList = erpmTenderMasterDao.findByImId(Short.parseShort(getSession().getAttribute("imId").toString()));
            message = "Item Record deleted successfully.";

            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("java.sql.BatchUpdateException: Cannot delete or update a parent row")) {
                message = "This record cannot be Deleted. It is being used in other Tables." ;
            } else {
            message = "Exception in Delete method -> IssueItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
	    }
            return ERROR;
        }
    }

    @SkipValidation
    public String EditTender() throws Exception {
        try {

            InitializeLOVs();
            DateUtilities d = new DateUtilities();
            ermptendermaster = erpmTenderMasterDao.findByTenderMasterId(getTmTmId());

            erpmTenderMasterList = erpmTenderMasterDao.findByImId(Short.parseShort(getSession().getAttribute("imId").toString()));
            DefaultInsitute = Short.valueOf(ermptendermaster.getInstitutionmaster().getImId().toString());
            DefaultDepartment = Integer.valueOf(ermptendermaster.getDepartmentmaster().getDmId().toString());
            DefaultSubInsitute = Integer.valueOf(ermptendermaster.getSubinstitutionmaster().getSimId().toString());
            settenDate(d.convertDateToString(ermptendermaster.getTmDate(), "dd-MM-yyyy"));

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method ->" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String ClearTender() throws Exception {
        try {
            ermptendermaster = null;
            InitializeLOVs();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Clear method -> TenderMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @Override
    public void validate() {
        try {
            InitializeLOVs();

            if (ermptendermaster.getInstitutionmaster().getImId() == 0) {
                addFieldError("ermptendermaster.institutionmaster.imId", "Please Select Institution Name.");
            }

            if (ermptendermaster.getDepartmentmaster().getDmId() == 0) {
                addFieldError("ermptendermaster.departmentmaster.dmId", "Please Select Department Name.");
            }

            if (ermptendermaster.getTmTenderNo().length() == 0) {
                addFieldError("ermptendermaster.tmTenderNo", "Please enter Tender Number.");
            }

            if (ermptendermaster.getSubinstitutionmaster().getSimId() == 0) {
                addFieldError("ermptendermaster.subinstitutionmaster.simId", "Please enter SubInstituion.");
            }

            if (ermptendermaster.getTmFee() == null) {
                addFieldError("ermptendermaster.tmFee", "Please enter Tender Fee.");
            }

            if (gettenDate().length() == 0) {
                addFieldError("tenDate", "Please enter Tender Fee.");
            } else {
                DateUtilities dt = new DateUtilities();
                if (dt.isValidDate(gettenDate()) == false) {
                    addFieldError("tenDate", "Enter Tender Date[dd/MM/yyyy]");
                }
            }

            if (ermptendermaster.getTmEmdAmount() == null) {
                addFieldError("ermptendermaster.tmEmdAmount", "Please enter Emd Amount.");
            }

            if (ermptendermaster.getTmEstimatedAmount() == null) {
                addFieldError("ermptendermaster.tmEstimatedAmount", "Please enter Estimated amount.");
            }
            if (ermptendermaster.getErpmGenMasterByTmTypeId().getErpmgmEgmId() == 0) {
                addFieldError("ermptendermaster.erpmGenMasterByTmTypeId.erpmgmEgmId", "Please select Tender Type.");
            }

            if (ermptendermaster.getTmName().isEmpty()) {
                addFieldError("ermptendermaster.tmName", "Please enter Tender Name.");
            }

            if (ermptendermaster.getErpmGenMasterByTmStatusId().getErpmgmEgmId() == 0) {
                addFieldError("ermptendermaster.erpmGenMasterByTmStatusId.erpmgmEgmId", "Please enter Status ID.");
            }

            DefaultInsitute = Short.valueOf(ermptendermaster.getInstitutionmaster().getImId().toString());
            DefaultDepartment = Integer.valueOf(ermptendermaster.getDepartmentmaster().getDmId().toString());
            DefaultSubInsitute = Integer.valueOf(ermptendermaster.getSubinstitutionmaster().getSimId().toString());

        } catch (NullPointerException npe) {
        }
    }

    private void InitializeLOVs() {

        //erpmTenderMasterList=erpmTenderMasterDao.findByImId(Short.parseShort(getSession().getAttribute("imId").toString()));
        ImIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        SimImIdList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        DmList = dmDao.findAllDepartmentsForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

        tendertypeList1 = tendertypedao1.findByErpmGmType(sh1);
        tendertypeList = tendertypedao.findByErpmGmType(sh);

    }

     @SkipValidation
    public String SubmittedTendersReportAction() throws Exception {

        HashMap hm = new HashMap();

        if(getTmTmId()==null){
   
           TenderNo=null;       
        } else {

            TenderNo =""+getTmTmId();
        }

     // Get System properties
       Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator = pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "PrePurchase" + pathSeparator + "Reports" + pathSeparator + "ListofTendersSubmitted.jasper";

        String fileName = getSession().getServletContext().getRealPath(repPath);

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
            response.setHeader("Content-Disposition", "attachment; filename=ListofTendersSubmitted.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //Setup Where Condition Clause
         if (TenderNo== null) {
                whereCondition = "erpm_tender_submission.TSB_TM_ID <> 0";
            } else {
                whereCondition = "erpm_tender_submission.TSB_TM_ID= "+TenderNo;
            }

            hm.put("condition", whereCondition);        

            JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
            JasperExportManager.exportReportToPdfStream(jp, baos);
            response.setContentLength(baos.size());
            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            inputStream = bis;

            return "success";
        } catch (JRException e) {
            message = "Error is : " + e.getMessage() + e.getCause();
            return ERROR;
        }           
  
    }

    @SkipValidation
    public String showGFRreport() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator = pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Administration" + pathSeparator + "Reports" + pathSeparator + "ShowGFRMappedinProgram.jasper";

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\Administration\\Reports\\ShowGFRMappedinProgram.jasper");

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
            response.setHeader("Content-Disposition", "attachment; filename=ShowGfr.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            whereCondition = "gfr_program_mapping.`GPM_Program_ID` = 16";

            hm.put("condition", whereCondition);
            hm.put("screen_name", "TENDER MASTER");
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
}
