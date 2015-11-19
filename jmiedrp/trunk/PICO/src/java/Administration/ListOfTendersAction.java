/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;

/**
 *
 * @author Manauwar Alam
 */
//import pojo.hibernate.*;
import utils.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import pojo.hibernate.ErpmTenderMasterDAO;
import com.opensymphony.xwork2.ActionContext;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.ServletActionContext;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.ErpmTenderMaster;
import pojo.hibernate.ErpmTenderScheduleDetail;
import pojo.hibernate.ErpmTenderScheduleDetailDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Erpmprogram;
import pojo.hibernate.ErpmprogramDAO;

import utils.DevelopmentSupport;
import java.util.Locale;



public class ListOfTendersAction extends DevelopmentSupport {

   
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<ErpmGenMaster> genList = new ArrayList<ErpmGenMaster>();
    private List<ErpmTenderMaster> tenderList = new ArrayList<ErpmTenderMaster>();
    private List<ErpmTenderScheduleDetail> tenderScheduleList = new ArrayList<ErpmTenderScheduleDetail>();
    private ErpmTenderMasterDAO tenMasDao = new ErpmTenderMasterDAO();
    private ErpmTenderScheduleDetailDAO tenSchDao = new ErpmTenderScheduleDetailDAO();
    private ErpmGenMasterDao genMasDao = new ErpmGenMasterDao();
    private ErpmprogramDAO erpmProgDao = new ErpmprogramDAO();
    private static Short institutionId;
    //private static Short instId;
    private static Integer tenderType;
    //private static Integer tenType;
    private String message;
    private String message1;
    private String message2;
    private static Integer tenderState;
    //private static Integer tenState;
    private Integer SCHEDULEID;
    private InputStream inputStream;
    static String dataSourceURL = null;

   

    
    public List<Institutionmaster> getImList() {
        return imList;
    }

    public void setImList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getMessage1() {
        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    public List<ErpmTenderMaster> getTenderList() {
        return tenderList;
    }

    public void setTenderList(List<ErpmTenderMaster> tenderList) {
        this.tenderList = tenderList;
    }

    public List<ErpmTenderScheduleDetail> getTenderScheduleList() {
        return tenderScheduleList;
    }

    public void setTenderScheduleList(List<ErpmTenderScheduleDetail> tenderScheduleList) {
        this.tenderScheduleList = tenderScheduleList;
    }

    public Integer getTenderState() {
        return tenderState;
    }

    public void setTenderState(Integer tenderState) {
        ListOfTendersAction.tenderState = tenderState;
    }

    public List<ErpmGenMaster> getGenList() {
        return genList;
    }

    public void setGenList(List<ErpmGenMaster> genList) {
        this.genList = genList;
    }

    public Short getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Short institutionId) {
        ListOfTendersAction.institutionId = institutionId;
    }

    public Integer getTenderType() {
        return tenderType;
    }

    public void setTenderType(Integer tenderType) {
        ListOfTendersAction.tenderType = tenderType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSCHEDULEID() {
        return SCHEDULEID;
    }

    public void setSCHEDULEID(Integer SCHEDULEID) {
        this.SCHEDULEID = SCHEDULEID;
    }

    @Override
    public String execute() throws Exception {
        try {
            Initialize_Lovs();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ListofTendersAxn in execute method" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public void Initialize_Lovs() {
        imList = imDao.findAll();
        genList = genMasDao.findByErpmGmType(Short.parseShort("22"));
        
    }

    public String ListOfTenders() {
        try {

            Initialize_Lovs();

            message = "";
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ListofTenders method -> ListOfTendersAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String fetchTender() {

        try {

            tenderList = tenMasDao.findTender(getInstitutionId(),getTenderType(),getTenderState());
            //  tenderList = getParameters(instId, tenType, tenState);
            Initialize_Lovs();

            message1 = " NO TENDERS AVAILABLE FOR THE ABOVE SELECTION DONE";
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public String ExportTenderList() throws Exception {





        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator = pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Administration" + pathSeparator + "Reports" + pathSeparator + "TenderList.jasper";

        String fileName = getSession().getServletContext().getRealPath(repPath);

        String whereCondition = "";

        try {
//            Locale locale = ActionContext.getContext().getLocale();
//            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword"));

            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JNDI");
            }
            dataSourceURL = (String) ctx.lookup("java:comp/env/ReportURL").toString();
            Connection conn = DriverManager.getConnection(dataSourceURL);
//            String browser = System.getProperty("java.vendor");
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
//            if (browser.indexOf("Microsoft") != -1) {
//                response.setHeader("Content-Disposition", "attachment; filename=TenderList.pdf");
//            } else {
//                response.setHeader("", "attachment; filename=TenderList.pdf");
//            }

            response.setHeader("", "outline; filename=TenderList.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //Setup Where Condition Clause

            if (getInstitutionId() == 0) {
                if (getTenderType() != 0) {
                    if (getTenderState() == 1) {
                        whereCondition = "erpm_tender_master.`tm_type_id` =" + getTenderType();
                    } else if (getTenderState() == 2) {
                        whereCondition = "erpm_tender_master.`tm_type_id` =" + getTenderType() + " and (erpm_tender_master.`tm_type_id` <> 106 and erpm_tender_master.`tm_type_id` <> 107 and erpm_tender_master.`tm_type_id` <> 107)";
                    } else {
                        whereCondition = "erpm_tender_master.`tm_type_id` =" + getTenderType() + " and (erpm_tender_master.`tm_type_id` = 106 or erpm_tender_master.`tm_type_id` = 107 or erpm_tender_master.`tm_type_id` = 107)";
                    }

                } else {
                    if (getTenderState() == 1) {
                        whereCondition = "erpm_tender_master.`tm_status_id` <> 0";
                    } else if (getTenderState() == 2) {
                        whereCondition = "erpm_tender_master.`tm_status_id` <> 106 and erpm_tender_master.`tm_status_id` <> 107 and erpm_tender_master.`tm_status_id` <> 107";
                    } else {
                        whereCondition = "erpm_tender_master.`tm_status_id` = 106 or erpm_tender_master.`tm_status_id` = 107 or erpm_tender_master.`tm_status_id` = 107";
                    }

                }
            } else {
                if (getTenderType() != 0) {
                    if (getTenderState() == 1) {
                        whereCondition = "erpm_tender_master.`tm_type_id` =" + getTenderType() + " and institutionmaster.`im_id` =" + getInstitutionId();
                    } else if (getTenderState() == 2) {
                        whereCondition = "erpm_tender_master.`tm_type_id` =" + getTenderType() + " and institutionmaster.`IM_ID` =" + getInstitutionId() + " and (erpm_tender_master.`tm_type_id` <> 106 and erpm_tender_master.`tm_type_id` <> 107 and erpm_tender_master.`tm_type_id` <> 107)";
                    } else {
                        whereCondition = "erpm_tender_master.`tm_type_id` =" + getTenderType() + " and institutionmaster.`IM_ID` =" + getInstitutionId() + " and (erpm_tender_master.`tm_type_id` = 106 or erpm_tender_master.`tm_type_id` = 107 or erpm_tender_master.`tm_type_id` = 107)";
                    }

                } else {
                    if (getTenderState() == 1) {
                        whereCondition = "institutionmaster.`im_id` =" + getInstitutionId();
                    } else if (getTenderState() == 2) {
                        whereCondition = "institutionmaster.`im_id` =" + getInstitutionId() + " and (erpm_tender_master.`tm_status_id` <> 106 and erpm_tender_master.`tm_status_id` <> 107 and erpm_tender_master.`tm_status_id` <> 107)";
                    } else {
                        whereCondition = "institutionmaster.`im_id` =" + getInstitutionId() + " and (erpm_tender_master.`tm_status_id` = 106 or erpm_tender_master.`tm_status_id` = 107 or erpm_tender_master.`tm_status_id` = 107)";

                    }
                }
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

    public String getTenderSchedule() {

        try {

            ErpmTenderMaster tenMas = tenMasDao.findByTenderMasterId(getSCHEDULEID());
            //tenderScheduleList = tenSchDao.findByTenderId(getSCHEDULEID());
            ///////////////////////by shobhi
            Session session = HibernateUtil.getSessionPicoFactory();
        //try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderScheduleDetail> list = session.createQuery("Select u from ErpmTenderScheduleDetail u where u.erpmTenderSchedule.erpmTenderMaster.tmTmId = :tmTmId").setParameter("tmTmId", getSCHEDULEID()).list();
            for (index = 0; index < list.size(); ++index) {
                 Hibernate.initialize(list.get(index).getErpmTenderSchedule());
                 Hibernate.initialize(list.get(index).getErpmTenderSchedule().getErpmTenderMaster());
                Hibernate.initialize(list.get(index).getErpmGenMaster());

            }
            //return list;
        //} finally {
          //  session.close();
        //}
///////////////////////////////////////////
            Initialize_Lovs();
            tenderList = tenMasDao.findTender(getInstitutionId(), getTenderType(), getTenderState());
            //if (tenderScheduleList.size() > 0) {
            if (list.size() > 0) {
                message2 = "The Schedule Detail for the Tender \"" + tenMas.getTmName().toUpperCase() + "\" are :";
            } else {
                message2 = "NO SCHEDULE AVAILABLE FOR \"" + tenMas.getTmName().toUpperCase() + "\"";
            }

            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }
}
