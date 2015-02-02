/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

/**
 * @author Saeed
 */
//import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import pojo.hibernate.Committeemaster;
import pojo.hibernate.CommitteemasterDAO;
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Employeemaster;
import pojo.hibernate.EmployeemasterDAO;
import pojo.hibernate.ErpmIssueDetail;
import pojo.hibernate.ErpmIssueDetailDAO;
import pojo.hibernate.ErpmIssueMaster;
import pojo.hibernate.ErpmIssueMasterDAO;
import pojo.hibernate.ErpmIssueReceive;
import pojo.hibernate.ErpmIssueReceiveDAO;
import pojo.hibernate.ErpmIssueSerialDetail;
import pojo.hibernate.ErpmIssueSerialDetailDAO;
import pojo.hibernate.ErpmStockReceived;
import pojo.hibernate.GfrProgramMappingDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import utils.DateUtilities;
import utils.DevelopmentSupport;

//import java.util.Locale;
//import java.util.ResourceBundle;
//import com.opensymphony.xwork2.ActionContext;

public class ReceiveItemsAction extends DevelopmentSupport {

    private ErpmIssueReceive ir;
    private ErpmIssueDetail isueDet;
    private ErpmIssueSerialDetail isueSerialDet;
    private ErpmStockReceived erpmStockReceived;
    private Integer isdId;
    private Integer isrId;
    private Integer issdId;
    private boolean issdReceived;
    private BigDecimal isdReceivedQuantity;
    private String dsbl;
    private String ToLockRecdQty;
    private GfrProgramMappingDAO GfrProgramMappingDao = new GfrProgramMappingDAO();
    private List<ErpmIssueReceive> irList = new ArrayList<ErpmIssueReceive>();
    private ErpmIssueReceiveDAO irDAO = new ErpmIssueReceiveDAO();
    private List<Employeemaster> empList = new ArrayList<Employeemaster>();
    private EmployeemasterDAO empDao = new EmployeemasterDAO();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Subinstitutionmaster> simImList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private Departmentmaster dm;
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private CommitteemasterDAO comDAO = new CommitteemasterDAO();
    private List<Committeemaster> comList = new ArrayList<Committeemaster>();
    private ErpmIssueMasterDAO isueDAO = new ErpmIssueMasterDAO();
    private List<ErpmIssueMaster> isueList = new ArrayList<ErpmIssueMaster>();
    private ErpmIssueDetailDAO isueDetDAO = new ErpmIssueDetailDAO();
    private List<ErpmIssueDetail> isueDetList = new ArrayList<ErpmIssueDetail>();
    private ErpmIssueSerialDetailDAO issSerialDetDAO = new ErpmIssueSerialDetailDAO();
    private List<ErpmIssueSerialDetail> issSerialDetList = new ArrayList<ErpmIssueSerialDetail>();
    private Integer Default_Item_Name;
    private Integer Default_IssueNo;
    private Integer Default_Committee_Name;
    private Integer Default_Employee_Name;
    private String message;
    private String fileForExport;
    private InputStream inputStream;
    private String varchar_Item_Name;
    private String varchar_IssueNo;
    private BigDecimal varcharIsdReceivedQuantity;
    private Integer varIssueDetailID;
    private static Integer varIssueReceiveID;
    private String receiptDate;
    private static Boolean varShowGFR;

    static String dataSourceURL=null;


    public Boolean getVarShowGFR() {
        return varShowGFR;
    }

    public void setVarShowGFR(Boolean varShowGFR) {
        this.varShowGFR = varShowGFR;
    }
    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getDsbl() {
        return dsbl;
    }

    public void setDsbl(String dsbl) {
        this.dsbl = dsbl;
    }

    public String getToLockRecdQty() {
        return ToLockRecdQty;
    }

    public void setToLockRecdQty(String ToLockRecdQty) {
        this.ToLockRecdQty = ToLockRecdQty;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getMessage() {
        return message;
    }

    public String getfileForExport() {
        return fileForExport;
    }

    public Integer getisrId() {
        return isrId;
    }

    public void setisrId(Integer isrId) {
        this.isrId = isrId;
    }

    public ErpmIssueReceive getir() {
        return ir;
    }

    public void setir(ErpmIssueReceive ir) {
        this.ir = ir;
    }

    public List<ErpmIssueReceive> getirList() {
        return irList;
    }

    public void setirList(List<ErpmIssueReceive> irList) {
        this.irList = irList;
    }

    public void setempList(List<Employeemaster> empList) {
        this.empList = empList;
    }

    public List<Employeemaster> getempList() {
        return this.empList;
    }

    public void setimList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public List<Institutionmaster> getimList() {
        return this.imList;
    }

    public void setsimImList(List<Subinstitutionmaster> simImList) {
        this.simImList = simImList;
    }

    public List<Subinstitutionmaster> getsimImList() {
        return this.simImList;
    }

    public void setdmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<Departmentmaster> getdmList() {
        return this.dmList;
    }

    public void setcomList(List<Committeemaster> comList) {
        this.comList = comList;
    }

    public List<Committeemaster> getcomList() {
        return this.comList;
    }

    public void setisueList(List<ErpmIssueMaster> isueList) {
        this.isueList = isueList;
    }

    public List<ErpmIssueMaster> getisueList() {
        return this.isueList;
    }

    public Integer getDefault_Employee_Name() {
        return Default_Employee_Name;
    }

    public void setDefault_Employee_Name(Integer Default_Employee_Name) {
        this.Default_Employee_Name = Default_Employee_Name;
    }

    public Integer getDefault_Committee_Name() {
        return Default_Committee_Name;
    }

    public void setDefault_Committee_Name(Integer Default_Committee_Name) {
        this.Default_Committee_Name = Default_Committee_Name;
    }

    public List<ErpmIssueDetail> getIsueDetList() {
        return isueDetList;
    }

    public void setIsueDetList(List<ErpmIssueDetail> isueDetList) {
        this.isueDetList = isueDetList;
    }

    public Integer getIsdId() {
        return isdId;
    }

    public void setIsdId(Integer isdId) {
        this.isdId = isdId;
    }

    public String getVarchar_Item_Name() {
        return varchar_Item_Name;
    }

    public void setVarchar_Item_Name(String varchar_Item_Name) {
        this.varchar_Item_Name = varchar_Item_Name;
    }

    public String getVarchar_IssueNo() {
        return varchar_IssueNo;
    }

    public void setVarchar_IssueNo(String varchar_IssueNo) {
        this.varchar_IssueNo = varchar_IssueNo;
    }

    public BigDecimal getVarcharIsdReceivedQuantity() {
        return varcharIsdReceivedQuantity;
    }

    public void setVarcharIsdReceivedQuantity(BigDecimal varcharIsdReceivedQuantity) {
        this.varcharIsdReceivedQuantity = varcharIsdReceivedQuantity;
    }

    public Integer getVarIssueReceiveID() {
        return varIssueReceiveID;
    }

    public void setVarIssueReceiveID(Integer varIssueReceiveID) {
        this.varIssueReceiveID = varIssueReceiveID;
    }

    public void setIssSerialDetList(List<ErpmIssueSerialDetail> issSerialDetList) {
        this.issSerialDetList = issSerialDetList;
    }

    public List<ErpmIssueSerialDetail> getIssSerialDetList() {
        return issSerialDetList;
    }

    public Integer getIssdId() {
        return issdId;
    }

    public void setIssdId(Integer issdId) {
        this.issdId = issdId;
    }

    public boolean isIssdReceived() {
        return issdReceived;
    }

    public void setIssdReceived(boolean issdReceived) {
        this.issdReceived = issdReceived;
    }

    public ErpmStockReceived getErpmStockReceived() {
        return erpmStockReceived;
    }

    public void setErpmStockReceived(ErpmStockReceived erpmStockReceived) {
        this.erpmStockReceived = erpmStockReceived;
    }

    public Integer getVarIssueDetailID() {
        return varIssueDetailID;
    }

    public void setVarIssueDetailID(Integer varIssueDetailID) {
        this.varIssueDetailID = varIssueDetailID;
    }

    @Override
    public String execute() throws Exception {
        try {
            prepare_lovs();

            Date d = new Date();
            DateUtilities dt = new DateUtilities();
            setReceiptDate(dt.convertDateToString(d, "dd-MM-yyyy"));

            Short i=29;
            Integer count = GfrProgramMappingDao.findCountByProgramId(i);

          
            if(count > 0){
             setVarShowGFR(false);
            }
            else{
             setVarShowGFR(true);
            }
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in execute method" + e.getMessage();
            return ERROR;
        }
    }

    //This method is for populating the Receive items page (ReceiveItems.jsp)
    private void prepare_lovs() {


        //Prepare Institution Type List
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        //Prepare SubInstitute List
        simImList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()),
                Short.valueOf(getSession().getAttribute("imId").toString()));
        //Prepare Department List
        dmList = dmDao.findAllDepartmentsForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        //Prepare Employee List
        empList = empDao.findByDmId(Integer.valueOf(getSession().getAttribute("dmId").toString()));
        //Prepare Committee List
        comList = comDAO.findCommitteeByInstitution(Short.valueOf(getSession().getAttribute("imId").toString()));
	//Prepare Issue No List
	isueList = isueDAO.findIssueNo(Integer.valueOf(getSession().getAttribute("dmId").toString()));
    }

    //This method is for help
    public String Help() {
	return SUCCESS;
    }

    @SkipValidation
    public String Browse() throws Exception {
        try {
            irList = irDAO.findReceiveItemsForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method" + e.getMessage();
            return ERROR;
        }
    }

    public String Edit() throws Exception {
        try {

            ir = irDAO.findByErpmisrId(getisrId());
            prepare_lovs();
            empList = empDao.findByDmId(Integer.valueOf(ir.getDepartmentmaster().getDmId()));

            DateUtilities dt = new DateUtilities();

            receiptDate = dt.convertDateToString(ir.getIsrReceiptDate(), "dd/MM/yyyy");
            if (ir.getCommitteemaster() == null) {
                isueList = isueDAO.findByEmpId(ir.getEmployeemaster().getEmpId());
            } else if (ir.getEmployeemaster() == null) {
                isueList = isueDAO.findByCompId(ir.getCommitteemaster().getCommitteeId());
            }
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Delete() throws Exception {
        try {
            //Retrieve the record to be deleted
            ir = irDAO.findByErpmisrId(getisrId());
            irDAO.delete(ir);
            irList = irDAO.findReceiveItemsForUserDepartments(Integer.valueOf(getSession().getAttribute("userid").toString()));
            message = "Item deleted successfully.";

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @Override
    public void validate() {
        try {
            //Validations on Issue No
            if (ir.getInstitutionmaster().getImId()== null) {
                addFieldError("ir.institutionmaster.imId", "Please select Institutionmaster ");
            }
              if (ir.getSubinstitutionmaster().getSimId() == null) {
                addFieldError("ir.subinstitutionmaster.simId", "Please select Subinstitutionmaster.");
              }
           if (ir.getDepartmentmaster().getDmId() == null) {
                addFieldError("ir.departmentmaster.dmId", "Please select Department.");
            } 
           if (ir.getErpmIssueMaster().getIsmId() == null) {
                addFieldError("ir.erpmIssueMaster.ismId", "Please enter Issue No.");
            }

            if ((ir.getEmployeemaster().getEmpId() == null || ir.getEmployeemaster().getEmpId() == 0) && (ir.getCommitteemaster().getCommitteeId() == null || ir.getCommitteemaster().getCommitteeId() == 0)) {
                addFieldError("ir.employeemaster.empId", "Please enter Employee Name or Committee Name");
                addFieldError("ir.committeemaster.committeeId", "Please enter Employee Name or Committee Name");
            }

            if (ir.getIsrReceiptNo().isEmpty()) {
                addFieldError("ir.isrReceiptNo", "Please enter Receipt No");
            }
             if (receiptDate.isEmpty()) {
                addFieldError("receiptDate", "Please enter Receipt Date");
            }

        } catch (NullPointerException npe) {
        }
    }

    //this save is for saving Receive items (save button of Receiveitems.jsp page)
    public String Save() throws Exception {
        try {

            //if part is for saving new record and else part is for updating existing record
            DateUtilities dt = new DateUtilities();
            if (ir.getIsrId() == null) {

                ir.setIsrReceiptDate(dt.convertStringToDate(getReceiptDate()));
                //Following Condition Save Receive Items in two way either taken Commitee Master or Employee Maste
                if (ir.getCommitteemaster().getCommitteeId() == 0) {
                    ir.setCommitteemaster(null);
                }
                if (ir.getEmployeemaster().getEmpId() == null) {
                    ir.setEmployeemaster(null);
                }

                irDAO.save(ir);

                message = "Record saved successfully.";
            } else {

                ir.setIsrReceiptDate(dt.convertStringToDate(getReceiptDate()));
                if (ir.getCommitteemaster().getCommitteeId() == 0) {
                    ir.setCommitteemaster(null);
                } else //                if(ir.getEmployeemaster()==null)
                {
                    ir.setEmployeemaster(null);
                }

                ErpmIssueReceive ir2 = irDAO.findByErpmisrId(ir.getIsrId());
                ir2 = ir;
                irDAO.update(ir2);
                message = "Record saved successfully.";
            }

            setVarIssueReceiveID(ir.getIsrId());

            //This Default_IssueNo & ReceiptDate is used to take Issue No from RECEIVE ISSUED ITEMS Master Page RECEIVE ISSUED ITEMS DETAIL Page
            Default_IssueNo = ir.getIsrId();
            ir = irDAO.findByErpmisrId(Default_IssueNo);
            
            receiptDate =""+dt.convertDateToString(ir.getIsrReceiptDate(), "dd-MM-yyyy");
            // List to show Item Name ,U.O.M , Issue Qty , Receive Qty on RECEIVE ISSUED ITEMS DETAIL

            isueDetList = isueDetDAO.findByIssueMastId(ir.getErpmIssueMaster().getIsmId());

            prepare_lovs();
            return SUCCESS;
        } catch (Exception e) {

            message = "Exception in Save Method " + e.getMessage() + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String Clear() throws Exception {
        try {
            ir = null;
            receiptDate = "";
            irList = irDAO.findReceiveItemsForUserDepartments(Integer.valueOf(getSession().getAttribute("userid").toString()));
            prepare_lovs();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Clear method" + e.getMessage() + e.getCause();
            return ERROR;
        }

    }

    //This Method is used in RECEIVE ISSUED ITEMS DETAIL Page to Save RECEIVE Items
    public String Accept() throws Exception {
        try {

            // sumRecvQty variable count Receive Qty From IssueSerialDetail Table
            Integer sumRecvQty = issSerialDetDAO.CountReceiveQty(getIsdId());
            issSerialDetList = issSerialDetDAO.findByIssueDetID(getIsdId());
            Integer x = issSerialDetList.size();

            // This If Condition Check the Size of IssueSerialDetail Table in Database
            //                 //If Size is greater than zero than List will Pupolate On Page Else nothing will be pupolate..
            if (x > 0) {
                //Condition to check Quantity
                //If Quantity is null than set issdReceived True For Each Row in Table
                //Than update Table
                if (sumRecvQty == 0) {
                    // if (x>0) {
                    for (int i = 0; i < x; i++) {
                        isueSerialDet = issSerialDetDAO.findByErpmissdId(issSerialDetList.get(i).getIssdId());
                        isueSerialDet.setIssdReceived(true);
                        issSerialDetDAO.update(isueSerialDet);
                    }
                    // }
                }
                isueSerialDet = issSerialDetDAO.findByErpmissdId(issSerialDetList.get(0).getIssdId());
                isueDet = isueDetDAO.findisdId(getIsdId());
                //Set value for RECEIVE ISSUED ITEM SERIAL DETAILS page
                setVarchar_IssueNo(isueDet.getErpmIssueMaster().getIsmIssueNo());
                setVarchar_Item_Name(isueDet.getErpmItemMaster().getErpmimItemBriefDesc());

                Integer countValue = issSerialDetDAO.CountReceiveQty(isueSerialDet.getErpmIssueDetail().getIsdId());

                setVarcharIsdReceivedQuantity(new BigDecimal(countValue));

                setVarIssueDetailID(isueSerialDet.getErpmIssueDetail().getIsdId());

                getVarIssueReceiveID();

                // List to show Serial No
                issSerialDetList = issSerialDetDAO.findSerialNoByIssueDetID(getIsdId());

                Integer ItemIdIength = issSerialDetList.get(0).getErpmStockReceived().getErpmItemMaster().getErpmimId().toString().length();
                String itemSubString = "";
                if (ItemIdIength <= 9) {
                    itemSubString = "0000";
                } else if (ItemIdIength > 9 && ItemIdIength <= 99) {
                    itemSubString = "000";
                } else if (ItemIdIength > 99 && ItemIdIength <= 999) {
                    itemSubString = "00";
                } else if (ItemIdIength > 999 && ItemIdIength <= 9999) {
                    itemSubString = "0";
                }

                itemSubString = itemSubString + issSerialDetList.get(0).getErpmStockReceived().getErpmItemMaster().getErpmimId().toString();

                //Loop to show Serial No with Instituion , SubInstituion , Department Name
                for (int k = 0; k < issSerialDetList.size(); k++) {
                    String serialNoFull = issSerialDetList.get(k).getErpmStockReceived().getInstitutionmaster().getImShortName() + "/";
                    serialNoFull = serialNoFull + issSerialDetList.get(k).getErpmStockReceived().getSubinstitutionmaster().getSimShortName() + "/";
                    serialNoFull = serialNoFull + issSerialDetList.get(k).getErpmStockReceived().getDepartmentmaster().getDmShortName() + "/";
                    serialNoFull = serialNoFull + itemSubString + "/";
                    serialNoFull = serialNoFull + issSerialDetList.get(k).getErpmStockReceived().getStStockSerialNo();
                    issSerialDetList.get(k).getErpmStockReceived().setStStockSerialNo(serialNoFull);
                }

                setToLockRecdQty("true");
            }
            // Else Condition when quantity is mot null
            else {
                isueDet = isueDetDAO.findisdId(getIsdId());
                setVarchar_IssueNo(isueDet.getErpmIssueMaster().getIsmIssueNo());
                setVarchar_Item_Name(isueDet.getErpmItemMaster().getErpmimItemBriefDesc());
                setVarcharIsdReceivedQuantity(isueDet.getIsdReceivedQuantity());

                setVarIssueDetailID(getIsdId());
                getVarIssueReceiveID();

                setDsbl("true");

            }

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Accept method -> " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    // This method is used to save RECEIVE ISSUED ITEM SERIAL DETAILS data..
    @SkipValidation
    public String SaveReceiveDetails() throws Exception {

            DateUtilities dt = new DateUtilities();

        try {

            ErpmIssueDetail isueDet2 = isueDetDAO.findisdId(getVarIssueDetailID());

            //Checking the receive qty and Issued Qty
            if (varcharIsdReceivedQuantity.intValue() > isueDet2.getIsdIssuedQuantity().intValue()) {
                addFieldError("varcharIsdReceivedQuantity", "You cannot Receive more than the Issued Quantity, i.e. " + isueDet2.getIsdIssuedQuantity());

                return INPUT;
            } else {
                isueDet2.setIsdReceivedQuantity(varcharIsdReceivedQuantity);
                isueDetDAO.update(isueDet2);
                getVarIssueReceiveID();


                ir = irDAO.findByErpmisrId(varIssueReceiveID);
                receiptDate =""+dt.convertDateToString(ir.getIsrReceiptDate(), "dd-MM-yyyy");
                setVarchar_IssueNo(ir.getErpmIssueMaster().getIsmIssueNo());
                isueDetList = isueDetDAO.findByIssueMastId(isueDet2.getErpmIssueMaster().getIsmId());

                return SUCCESS;
            }

            //  return SUCCESS;
        } catch (Exception e) {
            message = "Error. " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

    }

    // This method is used to Delete Issue Serial Detail List data from List on RECEIVE ISSUED ITEM SERIAL DETAILS Page
    @SkipValidation
    public String Exclude() throws Exception {
        try {

            isueSerialDet = issSerialDetDAO.findByErpmissdId(getIssdId());

            //To Exclude Items set IssdReceived false And Update Table
            isueSerialDet.setIssdReceived(false);

            issSerialDetDAO.update(isueSerialDet);

            isueDet = isueDetDAO.findisdId(isueSerialDet.getErpmIssueDetail().getIsdId());


            setVarchar_IssueNo(isueDet.getErpmIssueMaster().getIsmIssueNo());
            setVarchar_Item_Name(isueDet.getErpmItemMaster().getErpmimItemBriefDesc());
            setVarIssueDetailID(isueSerialDet.getErpmIssueDetail().getIsdId());
            Integer countValue = issSerialDetDAO.CountReceiveQty(isueSerialDet.getErpmIssueDetail().getIsdId());

            setVarcharIsdReceivedQuantity(new BigDecimal(countValue));

            issSerialDetList = issSerialDetDAO.findSerialNoByIssueDetID(isueSerialDet.getErpmIssueDetail().getIsdId());

            Integer ItemIdIength = issSerialDetList.get(0).getErpmStockReceived().getErpmItemMaster().getErpmimId().toString().length();
            String itemSubString = "";
            if (ItemIdIength <= 9) {
                itemSubString = "0000";
            } else if (ItemIdIength > 9 && ItemIdIength <= 99) {
                itemSubString = "000";
            } else if (ItemIdIength > 99 && ItemIdIength <= 999) {
                itemSubString = "00";
            } else if (ItemIdIength > 999 && ItemIdIength <= 9999) {
                itemSubString = "0";
            }

            itemSubString = itemSubString + issSerialDetList.get(0).getErpmStockReceived().getErpmItemMaster().getErpmimId().toString();

            for (int k = 0; k < issSerialDetList.size(); k++) {
                String serialNoFull = issSerialDetList.get(k).getErpmStockReceived().getInstitutionmaster().getImShortName() + "/";
                serialNoFull = serialNoFull + issSerialDetList.get(k).getErpmStockReceived().getSubinstitutionmaster().getSimShortName() + "/";
                serialNoFull = serialNoFull + issSerialDetList.get(k).getErpmStockReceived().getDepartmentmaster().getDmShortName() + "/";
                serialNoFull = serialNoFull + itemSubString + "/";
                serialNoFull = serialNoFull + issSerialDetList.get(k).getErpmStockReceived().getStStockSerialNo();
                issSerialDetList.get(k).getErpmStockReceived().setStStockSerialNo(serialNoFull);
            }
            setToLockRecdQty("true");

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Exclude method" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    // This method Reset issSerialDetList on RECEIVE ISSUED ITEM SERIAL DETAILS page
    public String ResetReceiveDetails() throws Exception {

        try {
            issSerialDetList = issSerialDetDAO.findByIssueDetID(varIssueDetailID);

            Integer x = issSerialDetList.size();

                //set issdReceived True For Each Row in Table
                //Than update Table
            for (int i = 0; i < x; i++) {
                isueSerialDet = issSerialDetDAO.findByErpmissdId(issSerialDetList.get(i).getIssdId());
                isueSerialDet.setIssdReceived(true);
                issSerialDetDAO.update(isueSerialDet);
            }
            isueSerialDet = issSerialDetDAO.findByErpmissdId(issSerialDetList.get(0).getIssdId());

            //Set value for RECEIVE ISSUED ITEM SERIAL DETAILS page
            isueDet = isueDetDAO.findisdId(varIssueDetailID);
            setVarchar_IssueNo(isueDet.getErpmIssueMaster().getIsmIssueNo());
            setVarchar_Item_Name(isueDet.getErpmItemMaster().getErpmimItemBriefDesc());

            Integer countValue = issSerialDetDAO.CountReceiveQty(varIssueDetailID);

            setVarcharIsdReceivedQuantity(new BigDecimal(countValue));

            setVarIssueDetailID(varIssueDetailID);

            getVarIssueReceiveID();

            // List to show Serial No
            issSerialDetList = issSerialDetDAO.findSerialNoByIssueDetID(varIssueDetailID);
            Integer ItemIdIength = issSerialDetList.get(0).getErpmStockReceived().getErpmItemMaster().getErpmimId().toString().length();
            String itemSubString = "";
            if (ItemIdIength <= 9) {
                itemSubString = "0000";
            } else if (ItemIdIength > 9 && ItemIdIength <= 99) {
                itemSubString = "000";
            } else if (ItemIdIength > 99 && ItemIdIength <= 999) {
                itemSubString = "00";
            } else if (ItemIdIength > 999 && ItemIdIength <= 9999) {
                itemSubString = "0";
            }

            itemSubString = itemSubString + issSerialDetList.get(0).getErpmStockReceived().getErpmItemMaster().getErpmimId().toString();

            //Loop to show Serial No with Instituion , SubInstituion , Department Name
            for (int k = 0; k < issSerialDetList.size(); k++) {
                String serialNoFull = issSerialDetList.get(k).getErpmStockReceived().getInstitutionmaster().getImShortName() + "/";
                serialNoFull = serialNoFull + issSerialDetList.get(k).getErpmStockReceived().getSubinstitutionmaster().getSimShortName() + "/";
                serialNoFull = serialNoFull + issSerialDetList.get(k).getErpmStockReceived().getDepartmentmaster().getDmShortName() + "/";
                serialNoFull = serialNoFull + itemSubString + "/";
                serialNoFull = serialNoFull + issSerialDetList.get(k).getErpmStockReceived().getStStockSerialNo();
                issSerialDetList.get(k).getErpmStockReceived().setStStockSerialNo(serialNoFull);
            }
            setToLockRecdQty("true");

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ResetReceiveDetails method -> " + e.getMessage() + " Reported Cause is: " + e.getCause();
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

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Administration"  + pathSeparator + "Reports" + pathSeparator + "ShowGFRMappedinProgram.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\Administration\\Reports\\ShowGFRMappedinProgram.jasper");

        String whereCondition = "";

        try {
    //        Locale locale = ActionContext.getContext().getLocale();
    //        ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
    //        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

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


            whereCondition = "gfr_program_mapping.`GPM_Program_ID` = 29";

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
}
