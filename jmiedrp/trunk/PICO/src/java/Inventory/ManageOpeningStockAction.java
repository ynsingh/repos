/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

/**
 *
 * @author farah Updated By : Tanvir Ahmed
 */
import java.math.BigDecimal;
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.SuppliermasterDAO;
import pojo.hibernate.ErpmItemMasterDAO;
import pojo.hibernate.ErpmStockReceived;
import pojo.hibernate.ErpmStockReceivedDAO;
import pojo.hibernate.ErpmTempOpeningStock;
import pojo.hibernate.Suppliermaster;
import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.ErpmTOSDAO;
import pojo.hibernate.ErpmItemCategoryMaster;
import pojo.hibernate.ErpmItemCategoryMasterDao;
import pojo.hibernate.ErpmusersDAO;
import pojo.hibernate.Erpmusers;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import java.io.*;
import java.util.*;
import utils.DateUtilities;
import org.apache.struts2.interceptor.validation.SkipValidation;
import utils.DevelopmentSupport;
import pojo.hibernate.GfrProgramMappingDAO;

public class ManageOpeningStockAction extends DevelopmentSupport {

    private String message;
    private ErpmTempOpeningStock tos = new ErpmTempOpeningStock();
    private List<ErpmTempOpeningStock> tosList = new ArrayList<ErpmTempOpeningStock>();
    private List<ErpmTempOpeningStock> tosList2 = new ArrayList<ErpmTempOpeningStock>();
    private ErpmItemCategoryMaster erpmItemCategoryMaster = new ErpmItemCategoryMaster();
    private ErpmItemCategoryMasterDao erpmItemCategoryMasterDao = new ErpmItemCategoryMasterDao();
    private ErpmTOSDAO tosDAO = new ErpmTOSDAO();
    private Suppliermaster supplier = new Suppliermaster();
    private SuppliermasterDAO supplierdao = new SuppliermasterDAO();
    private ErpmItemMaster item = new ErpmItemMaster();
    private ErpmItemMasterDAO itemDao = new ErpmItemMasterDAO();
    private ErpmStockReceived esr = new ErpmStockReceived();
    private ErpmStockReceivedDAO esrDAO = new ErpmStockReceivedDAO();
    private List<ErpmStockReceived> esrList = new ArrayList<ErpmStockReceived>();
    private List<Institutionmaster> tosImIdList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Subinstitutionmaster> tosSimImIdList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<Erpmusers> ErpmuList = new ArrayList<Erpmusers>();
    private ErpmusersDAO erpmusersDao = new ErpmusersDAO();
    private Erpmusers erpmuser = new Erpmusers();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Departmentmaster> tosDmList = new ArrayList<Departmentmaster>();
    private List<Suppliermaster> tosSmList = new ArrayList<Suppliermaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList1 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList2 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList3 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemMaster> tosINList = new ArrayList<ErpmItemMaster>();
    private ErpmGenMasterDao egmDAO = new ErpmGenMasterDao();
    private List<ErpmGenMaster> tosWTList = new ArrayList<ErpmGenMaster>();
    private GfrProgramMappingDAO GfrProgramMappingDao = new GfrProgramMappingDAO();
    private BigDecimal quantity; //=BigDecimal.valueOf(0);
    private String fromDate;
    private String toDate;
    private String tosInStockSince;
    private String tosPoDate;
    private String tosChallanDate;
    private String tosInvoiceDate;
    private String tosWarrantyExpiryDate;
    private String varBatchID;
    private String remark;
    private Integer TosId;
    private Short i = 1;
    private Short j = 2;
    private Short k = 3;
    private Short l = 18;
    private InputStream inputStream;
    private static Boolean varShowGFR;

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

    public ErpmTempOpeningStock getTos() {
        return tos;
    }

    /**
     * @param tos the tos to set
     */
    public void setTos(ErpmTempOpeningStock tos) {
        this.tos = tos;
    }

    public List<ErpmTempOpeningStock> getTosList() {
        return tosList;
    }
    //getter setter for fromdate,todate

    public void setfromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String gettoDate() {
        return toDate;
    }

    public String getfromDate() {
        return fromDate;
    }

    public void settoDate(String toDate) {
        this.toDate = toDate;
    }

    /**
     * @param tosList the tosList to set
     */
    public void setTosList(List<ErpmTempOpeningStock> tosList) {
        this.tosList = tosList;
    }

    /**
     * @return the tosImIdList
     */
    public List<Institutionmaster> getTosImIdList() {
        return tosImIdList;
    }

    /**
     * @param tosImIdList the tosImIdList to set
     */
    public void setTosImIdList(List<Institutionmaster> tosImIdList) {
        this.tosImIdList = tosImIdList;
    }

    /**
     * @return the tosINList
     */
    /**
     * @return the tosWTList
     */
    public List<ErpmGenMaster> getTosWTList() {
        return tosWTList;
    }

    /**
     * @param tosWTList the tosWTList to set
     */
    public void setTosWTList(List<ErpmGenMaster> tosWTList) {
        this.tosWTList = tosWTList;
    }

    public List<ErpmItemMaster> getTosINList() {
        return tosINList;
    }

    /**
     * @param tosINList the tosINList to set
     */
    public void setTosINList(List<ErpmItemMaster> tosINList) {
        this.setTosINList(tosINList);
    }

    /**
     * @return the tosSimImIdList
     */
    public List<Subinstitutionmaster> getTosSimImIdList() {
        return tosSimImIdList;
    }

    /**
     * @param tosSimImIdList the tosSimImIdList to set
     */
    public void setTosSimImIdList(List<Subinstitutionmaster> tosSimImIdList) {
        this.tosSimImIdList = tosSimImIdList;
    }

    /**
     * @return the tosDmList
     */
    public List<Departmentmaster> getTosDmList() {
        return tosDmList;
    }

    /**
     * @param tosDmList the tosDmList to set
     */
    public void setTosDmList(List<Departmentmaster> tosDmList) {
        this.tosDmList = tosDmList;
    }

    /**
     * @return the tosSmList
     */
    public List<Suppliermaster> getTosSmList() {
        return tosSmList;
    }

    /**
     * @param tosSmList the tosSmList to set
     */
    public void setTosSmList(List<Suppliermaster> tosSmList) {
        this.tosSmList = tosSmList;
    }

    public List<ErpmItemCategoryMaster> getErpmIcmList1() {
        return erpmIcmList1;
    }

    /**
     * @param erpmIcmList1 the erpmIcmList1 to set
     */
    public void setErpmIcmList1(List<ErpmItemCategoryMaster> erpmIcmList1) {
        this.erpmIcmList1 = erpmIcmList1;
    }

    /**
     * @return the erpmIcmList2
     */
    public List<ErpmItemCategoryMaster> getErpmIcmList2() {
        return erpmIcmList2;
    }

    /**
     * @param erpmIcmList2 the erpmIcmList2 to set
     */
    public void setErpmIcmList2(List<ErpmItemCategoryMaster> erpmIcmList2) {
        this.erpmIcmList2 = erpmIcmList2;
    }

    /**
     * @return the erpmIcmList3
     */
    public List<ErpmItemCategoryMaster> getErpmIcmList3() {
        return erpmIcmList3;
    }

    /**
     * @param erpmIcmList3 the erpmIcmList3 to set
     */
    public void setErpmIcmList3(List<ErpmItemCategoryMaster> erpmIcmList3) {
        this.erpmIcmList3 = erpmIcmList3;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the supplier
     */
    public Suppliermaster getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(Suppliermaster supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the item
     */
    public ErpmItemMaster getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(ErpmItemMaster item) {
        this.item = item;
    }

    /**
     * @return the erpmItemCategoryMaster
     */
    public ErpmItemCategoryMaster getErpmItemCategoryMaster() {
        return erpmItemCategoryMaster;
    }

    /**
     * @param erpmItemCategoryMaster the erpmItemCategoryMaster to set
     */
    public void setErpmItemCategoryMaster(ErpmItemCategoryMaster erpmItemCategoryMaster) {
        this.erpmItemCategoryMaster = erpmItemCategoryMaster;
    }

    /**
     * @return the TosId
     */
    public Integer getTosId() {
        return TosId;
    }

    /**
     * @param TosId the TosId to set
     */
    public void setTosId(Integer TosId) {
        this.TosId = TosId;
    }

    /**
     * @return the esr
     */
    public ErpmStockReceived getEsr() {
        return esr;
    }

    /**
     * @param esr the esr to set
     */
    public void setEsr(ErpmStockReceived esr) {
        this.esr = esr;
    }

    public List<ErpmStockReceived> getEsrList() {
        return esrList;
    }

    /**
     * @param esrList the esrList to set
     */
    public void setEsrList(List<ErpmStockReceived> esrList) {
        this.esrList = esrList;

    }

    public List<Erpmusers> getErpmuList() {
        return ErpmuList;
    }

    /**
     * @param ErpmuList the ErpmuList to set
     */
    public void setErpmuList(List<Erpmusers> ErpmuList) {
        this.ErpmuList = ErpmuList;
    }

    /**
     * @return the erpmuser
     */
    public Erpmusers getErpmuser() {
        return erpmuser;
    }

    /**
     * @param erpmuser the erpmuser to set
     */
    public void setErpmuser(Erpmusers erpmuser) {
        this.erpmuser = erpmuser;
    }

    /**
     * @return the tosList2
     */
    public List<ErpmTempOpeningStock> getTosList2() {
        return tosList2;
    }

    /**
     * @param tosList2 the tosList2 to set
     */
    public void setTosList2(List<ErpmTempOpeningStock> tosList2) {
        this.tosList2 = tosList2;
    }

    /**
     * @return the varBatchID
     */
    public String getVarBatchID() {
        return varBatchID;
    }

    /**
     * @param varBatchID the varBatchID to set
     */
    public void setVarBatchID(String varBatchID) {
        this.varBatchID = varBatchID;
    }

    public String getTosInStockSince() {
        return tosInStockSince;
    }

    public void setTosInStockSince(String tosInStockSince) {
        this.tosInStockSince = tosInStockSince;
    }

    public String getTosChallanDate() {
        return tosChallanDate;
    }

    public void setTosChallanDate(String tosChallanDate) {
        this.tosChallanDate = tosChallanDate;
    }

    public String getTosInvoiceDate() {
        return tosInvoiceDate;
    }

    public void setTosInvoiceDate(String tosInvoiceDate) {
        this.tosInvoiceDate = tosInvoiceDate;
    }

    public String getTosPoDate() {
        return tosPoDate;
    }

    public void setTosPoDate(String tosPoDate) {
        this.tosPoDate = tosPoDate;
    }

    public String getTosWarrantyExpiryDate() {
        return tosWarrantyExpiryDate;
    }

    public void setTosWarrantyExpiryDate(String tosWarrantyExpiryDate) {
        this.tosWarrantyExpiryDate = tosWarrantyExpiryDate;
    }

    @Override
    public String execute() throws Exception {
        try {
            InitializeLOVs();

            //Set Indent Date
            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            setTosInStockSince(d1.convertDateToString(d, "dd-MM-yyyy"));
            setTosChallanDate(d1.convertDateToString(d, "dd-MM-yyyy"));
            setTosInvoiceDate(d1.convertDateToString(d, "dd-MM-yyyy"));
            setTosPoDate(d1.convertDateToString(d, "dd-MM-yyyy"));
            setTosWarrantyExpiryDate(d1.convertDateToString(d, "dd-MM-yyyy"));

            Short i = 30;
            Integer count = GfrProgramMappingDao.findCountByProgramId(i);


            if (count > 0) {
                setVarShowGFR(false);
            } else {
                setVarShowGFR(true);
            }
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ManageOpeningStockAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public void InitializeLOVs() {

        //Initialise List for Institution, Sub-Institution, Department,Supplier and Item.
        tosImIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        tosSimImIdList = simDao.findBysimImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        tosDmList = dmDao.findBydmSimId(Integer.valueOf(getSession().getAttribute("simId").toString()));
        tosSmList = supplierdao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        erpmIcmList1 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(i);
        erpmIcmList2 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(j);
        erpmIcmList3 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(k);
        tosINList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        tos.setTosBatchId(getSession().getAttribute("username").toString());
        tosWTList = egmDAO.findByExpiryType(l);
        
    }

    public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update
            if (tos.getTosId() == null) {
                // check here if item is serializable then
                item = itemDao.findByErpmimId(tos.getErpmItemMaster().getErpmimId());
                if (item.getErpmimSerialNoApplicable() == 'Y') {
                    for (int i = 0; i < getQuantity().intValue(); i++) {
                        // set quantity = 1
                        tos.setTosQuantity(BigDecimal.valueOf(1.00));

                        DateUtilities dt = new DateUtilities();

                        if (!getTosInStockSince().isEmpty()) {
                            tos.setTosInStockSince(dt.convertStringToDate(getTosInStockSince()));
                        }

                        if (!getTosPoDate().isEmpty()) {
                            tos.setTosPoDate(dt.convertStringToDate(getTosPoDate()));
                        }

                        if (!getTosChallanDate().isEmpty()) {
                            tos.setTosChallanDate(dt.convertStringToDate(getTosChallanDate()));
                        }

                        if (!getTosInvoiceDate().isEmpty()) {
                            tos.setTosInvoiceDate(dt.convertStringToDate(getTosInvoiceDate()));
                        }

                        if (!getTosWarrantyExpiryDate().isEmpty()) {
                            tos.setTosWarrantyExpiryDate(dt.convertStringToDate(getTosWarrantyExpiryDate()));
                        }

                        tosDAO.save(tos);
                    }
                } else {
                    // set quantity = getQuantity
                    tos.setTosQuantity(getQuantity());

                    DateUtilities dt = new DateUtilities();

                    if (!getTosInStockSince().isEmpty()) {
                        tos.setTosInStockSince(dt.convertStringToDate(getTosInStockSince()));
                    }

                    if (!getTosPoDate().isEmpty()) {
                        tos.setTosPoDate(dt.convertStringToDate(getTosPoDate()));
                    }

                    if (!getTosChallanDate().isEmpty()) {
                        tos.setTosChallanDate(dt.convertStringToDate(getTosChallanDate()));
                    }

                    if (!getTosInvoiceDate().isEmpty()) {
                        tos.setTosInvoiceDate(dt.convertStringToDate(getTosInvoiceDate()));
                    }

                    if (!getTosWarrantyExpiryDate().isEmpty()) {
                        tos.setTosWarrantyExpiryDate(dt.convertStringToDate(getTosWarrantyExpiryDate()));
                    }

                    tosDAO.save(tos);
                }
                message = " record saved successfully ......";
                ErpmuList = erpmusersDao.findUserNamebyimid((Integer.valueOf(getSession().getAttribute("userid").toString())), (Short.valueOf(getSession().getAttribute("imId").toString())));//. Employee Id is " + tos.getTosId();
                InitializeLOVs();
                tos = null;
            } else {
                ErpmTempOpeningStock tos1 = tosDAO.findBytosId(tos.getTosId());
                tos.setTosQuantity(getQuantity());

                DateUtilities dt = new DateUtilities();

                if (!getTosInStockSince().isEmpty()) {
                    tos.setTosInStockSince(dt.convertStringToDate(getTosInStockSince()));
                }

                if (!getTosPoDate().isEmpty()) {
                    tos.setTosPoDate(dt.convertStringToDate(getTosPoDate()));
                }

                if (!getTosChallanDate().isEmpty()) {
                    tos.setTosChallanDate(dt.convertStringToDate(getTosChallanDate()));
                }

                if (!getTosInvoiceDate().isEmpty()) {
                    tos.setTosInvoiceDate(dt.convertStringToDate(getTosInvoiceDate()));
                }

                if (!getTosWarrantyExpiryDate().isEmpty()) {
                    tos.setTosWarrantyExpiryDate(dt.convertStringToDate(getTosWarrantyExpiryDate()));
                }

                tos1 = tos;
                tosDAO.update(tos1);

                message = "stock record updated successfully....";
                tosDAO = null;
                ErpmuList = erpmusersDao.findUserNamebyimid((Integer.valueOf(getSession().getAttribute("userid").toString())), (Short.valueOf(getSession().getAttribute("imId").toString())));
            }

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in save method -> ManageOpeningStockAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;

        }



    }

// Transfering record from temporary table to permanent table
    @SkipValidation
    public String Transfer() throws Exception {

        try {
            tosList = tosDAO.findByUser_NamesOnly(getVarBatchID());
            Integer x = tosList.size();
            for (int i = 0; i < x; i++) {

                esr.setInstitutionmaster(tosList.get(i).getInstitutionmaster());
                esr.setSubinstitutionmaster(tosList.get(i).getSubinstitutionmaster());
                esr.setDepartmentmaster(tosList.get(i).getDepartmentmaster());
                esr.setSuppliermaster(tosList.get(i).getSuppliermaster());
                esr.setErpmItemMaster(tosList.get(i).getErpmItemMaster());
                esr.setStQuantity(tosList.get(i).getTosQuantity());
                esr.setStInStockSince(tosList.get(i).getTosInStockSince());
                esr.setStPoNo(tosList.get(i).getTosPoNo());
                esr.setStPoDate(tosList.get(i).getTosPoDate());
                esr.setStChallanNo(tosList.get(i).getTosChallanNo());
                esr.setStChallanDate(tosList.get(i).getTosChallanDate());
                esr.setStInvoiceNo(tosList.get(i).getTosInvoiceNo());
                esr.setStInvoiceDate(tosList.get(i).getTosInvoiceDate());
                esr.setStUnitRate(tosList.get(i).getTosUnitRate());
                esr.setStTaxValue(tosList.get(i).getTosTaxValue());
                esr.setStCsrNo(tosList.get(i).getTosCsrNo());
                esr.setStCsrPgNo(tosList.get(i).getTosCsrPgNo());
                esr.setStDeptSrNo(tosList.get(i).getTosDeptSrNo());
                esr.setStDeptSrPgNo(tosList.get(i).getTosDeptSrPgNo());
                esr.setStProductNo(tosList.get(i).getTosProductNo());
                esr.setStStockSerialNo(tosList.get(i).getTosStockSerialNo());
                esr.setStWarrantyExpiryDate(tosList.get(i).getTosWarrantyExpiryDate());
                esr.setErpmGenMaster(tosList.get(i).getErpmGenMaster());
                esrDAO.save(esr);
                tosDAO.delete(tosList.get(i));
            }
            message = "" + "Record(s) Transfered Successfully";
            //ErpmuList = erpmusersDao.findUserNamebyimid((Integer.valueOf(getSession().getAttribute("userid").toString())),(Short.valueOf(getSession().getAttribute("imId").toString())));
            tosList = null;
            ErpmuList = erpmusersDao.findUserNamebyimid((Integer.valueOf(getSession().getAttribute("userid").toString())),(Short.valueOf(getSession().getAttribute("imId").toString())));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Transfer method -> ManageOpeningStockAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String Clear() throws Exception {
        try {

            tos = new ErpmTempOpeningStock();
            //Initialise List for Institution, Sub-Institution and Department
            InitializeLOVs();
            setQuantity(BigDecimal.valueOf(0));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Clear method -> ManageOpeningStockAction" + e.getMessage();
            return ERROR;
        }
    }

    public String Browse() throws Exception {
        try {
            setVarBatchID(erpmuser.getErpmuName());
            tosList = tosDAO.findByUser_NamesOnly(erpmuser.getErpmuName());
            ErpmuList = erpmusersDao.findUserNamebyimid((Integer.valueOf(getSession().getAttribute("userid").toString())), (Short.valueOf(getSession().getAttribute("imId").toString())));

            return SUCCESS;

        } catch (Exception e) {
            message = "Exception in Browse method -> ManageOpeningStockAction" + e.getMessage();
            return ERROR;
        }
    }

    @SkipValidation
    public String Edit() throws Exception {
        try {
            //message = "TOS ID :" + getTosId();
            tos = tosDAO.findBytosId(getTosId());
            setQuantity(tos.getTosQuantity());
            //message = "Value of BatchId is " + tos.getTosBatchId();
            message = "Current BatchId is " + tos.getTosBatchId();
            tosImIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            tosSimImIdList = simDao.findBysimImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            tosDmList = dmDao.findBydmSimId(Integer.valueOf(getSession().getAttribute("simId").toString()));
            tosSmList = supplierdao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            erpmIcmList1 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(i);
            erpmIcmList2 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(j);
            erpmIcmList3 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(k);
            tosINList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            tosWTList = egmDAO.findByExpiryType(l);

            DateUtilities dt = new DateUtilities();
            if (tos.getTosInStockSince() != null) {
                tosInStockSince = dt.convertDateToString(tos.getTosInStockSince(), "dd-MM-yyyy");
            }
            if (tos.getTosPoDate() != null) {
                tosPoDate = dt.convertDateToString(tos.getTosPoDate(), "dd-MM-yyyy");
            }
            if (tos.getTosChallanDate() != null) {
                tosChallanDate = dt.convertDateToString(tos.getTosChallanDate(), "dd-MM-yyyy");
            }
            if (tos.getTosInvoiceDate() != null) {
                tosInvoiceDate = dt.convertDateToString(tos.getTosInvoiceDate(), "dd-MM-yyyy");
            }
            if (tos.getTosWarrantyExpiryDate() != null) {
                tosWarrantyExpiryDate = dt.convertDateToString(tos.getTosWarrantyExpiryDate(), "dd-MM-yyyy");
            }
            if (tos.getTosChallanDate() != null) {
                tosChallanDate = dt.convertDateToString(tos.getTosChallanDate(), "dd-MM-yyyy");
            }

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> ManageOpeningStockAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String export() throws Exception {
        try {
            ErpmuList = erpmusersDao.findUserNamebyimid((Integer.valueOf(getSession().getAttribute("userid").toString())), (Short.valueOf(getSession().getAttribute("imId").toString())));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Export method -> ManageOpeningStockAction" + e.getMessage() + e.getCause();
            return ERROR;
        }
    }

    public String Delete() {

        tos = tosDAO.findBytosId(getTosId());
        tosDAO.delete(tos);
//        tosList = tosDAO.findAll();
        tosList = tosDAO.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        message = "record is deleted";
        ErpmuList = erpmusersDao.findUserNamebyimid((Integer.valueOf(getSession().getAttribute("userid").toString())), (Short.valueOf(getSession().getAttribute("imId").toString())));

        return SUCCESS;
    }

    public void validate() {
        try {

            //Validations on Supplier data,Institution,subinstitution,department,item,unit rate,CsrNo,CsrPgNo,DeptSRno,DeptSRPgNo
            if (tos.getInstitutionmaster().getImId() == null) {
                addFieldError("tos.institutionmaster.imId", "Please Select Institution Name.");

            }
            if (tos.getSubinstitutionmaster().getSimId() == null) {
                addFieldError("tos.subinstitutionmaster.simId", "Please Select SubInstitution Name.");
            }
            if (tos.getDepartmentmaster().getDmId() == null) {
                addFieldError("tos.departmentmaster.dmId", "Please Select Department Name.");
            }
            if (tos.getSuppliermaster().getSmId() == null) {
                addFieldError("tos.suppliermaster.smId", "Please Select Supplier Name");
            }

            if (tos.getErpmItemMaster().getErpmimId() == null) {
                addFieldError("tos.erpmItemMaster.erpmimId", "Please Select Item Name");
            }
//
//            if (tos.getTosQuantity() == null) {
//                addFieldError("quantity", "Please Enter Quantity");
//            }

            if (tos.getTosUnitRate() == null) {
                addFieldError("tos.tosUnitRate", "Please Enter Unit Rate");
            }

            if (tos.getTosCsrNo().length() == 0) {
                addFieldError("tos.tosCsrNo", "Please Enter Central Stock Register No.");
            }

            if (tos.getTosCsrPgNo() == 0) {
                addFieldError("tos.tosCsrPgNo", "Please Enter Central Stock Register Page No.");
            }

            if (tos.getTosDeptSrNo().length() == 0) {
                addFieldError("tos.tosDeptSrNo", "Please Enter Departmental Stock Register No.");
            }

            if (tos.getTosDeptSrPgNo() == 0) {
                addFieldError("tos.tosDeptSrPgNo", "Please Enter Departmental Stock Register Page No.");
            }


            DateUtilities dt = new DateUtilities();

            if (getTosInStockSince().length() != 0) {
                if (dt.isValidDate(getTosInStockSince()) == false) {
                    addFieldError("tosInStockSince", "Please give valid In Stock Since Date [dd-mm-yyyy]");
                }
            }

            if (getTosPoDate().length() != 0) {
                if (dt.isValidDate(getTosPoDate()) == false) {
                    addFieldError("tosPoDate", "Please give valid Purchase Order Date [dd-mm-yyyy]");
                }
            }

            if (getTosChallanDate().length() != 0) {
                if (dt.isValidDate(getTosChallanDate()) == false) {
                    addFieldError("tosChallanDate", "Please give valid Challan Date [dd-mm-yyyy]");
                }
            }

            if (getTosInvoiceDate().length() != 0) {
                if (dt.isValidDate(getTosInvoiceDate()) == false) {
                    addFieldError("tosInvoiceDate", "Please give valid Invoice Date [dd-mm-yyyy]");
                }
            }

            if (getTosWarrantyExpiryDate().length() != 0) {
                if (dt.isValidDate(getTosWarrantyExpiryDate()) == false) {
                    addFieldError("tosWarrantyExpiryDate", "Please give valid Warranty Expiry Date [dd-mm-yyyy]");
                }
            }

            if (tos.getErpmGenMaster().getErpmgmEgmId()== null) {
               addFieldError("tos.erpmGenMaster.erpmgmEgmId", "Please give valid Warranty Type");   
            }

            InitializeLOVs();

        } catch (NullPointerException npe) {
        }
    }
/*
    @SkipValidation
    public String PrintStockSummary() throws Exception {
        HashMap hm = new HashMap();
        String frmDate = null;
        String toDate = null;
        //assign value to var frmDate ,toDate
        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }


        java.util.Date d1 = null;
        java.util.Date d2 = null;
        //check dates are empty or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());
        }
        if (frmDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            frmDate = "11-11-2000";
        }
        try {
            //change formate of date form string to Date ( dd-MM-yyyy)
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d1 = sdf.parse("" + frmDate);
            d2 = sdf.parse("" + toDate);

        } catch (Exception e) {
        }
//check fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {

            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\Stock Summary.jasper");


            String whereCondition;

            try {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

                HttpServletResponse response = ServletActionContext.getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=Stock Summary.pdf");
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();


                //Setup Where Condition Clause

                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = "erpm_stock_received.ST_Item_ID <> 0 ";
                } else {
                    whereCondition = "erpm_stock_received.ST_Item_ID =" + esr.getErpmItemMaster().getErpmimId();
                }



                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_IM_ID = " + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_IM_ID= " + esr.getInstitutionmaster().getImId();
                }



                if (esr.getSubinstitutionmaster().getSimId() == null)// || esr.getSubinstitutionmaster().getSimId()==0)
                {
                    whereCondition = whereCondition + " and  erpm_stock_received.ST_SIM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_SIM_ID= " + esr.getSubinstitutionmaster().getSimId();
                }



                if (esr.getDepartmentmaster().getDmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_DM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_DM_ID= " + esr.getDepartmentmaster().getDmId();
                }



                if (esr.getSuppliermaster().getSmId() != null) {
                    whereCondition = whereCondition + " and erpm_stock_received.st_sm_id= " + esr.getSuppliermaster().getSmId();
                }

                if (getfromDate().length() != 10) {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (gettoDate().length() != 10) {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since <= str_to_date('" + toDate + "','%d-%m-%Y')";
                }


                hm.put("condition", whereCondition);

                JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
                JasperExportManager.exportReportToPdfStream(jp, baos);
                response.setContentLength(baos.size());
                ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
                setInputStream(bis);



                return SUCCESS;
            } catch (JRException e) {
                message = "Error is : " + e.getMessage() + e.getCause();
                return ERROR;
            }
        }
        return "input";
    }

    //method to genrate report for item details
    @SkipValidation
    public String Printstockdetail() throws Exception {
        HashMap hm = new HashMap();
        // message=""+esr.getErpmItemMaster().getErpmimId();
        String frmDate = null;
        //String toDate = null;
        //assign value to var frmDate ,toDate
        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }


        java.util.Date d1 = null;
        java.util.Date d2 = null;
        //check dates are empty or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());
        }
        if (frmDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            frmDate = "11-11-2000";
        }
        try {
            //change formate of date form string to Date ( dd-MM-yyyy)
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d1 = sdf.parse("" + frmDate);
            d2 = sdf.parse("" + toDate);

        } catch (Exception e) {
        }
//chech fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {

            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\STOCK DETAILS.jasper");


            String whereCondition;

            try {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

                HttpServletResponse response = ServletActionContext.getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=STOCK DETAILS.pdf");
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                // message=""+esr.getErpmItemMaster().getErpmimId();
                //Setup Where Condition Clause  whereCondition = "erpm_stock_received.ST_Item_ID <> 0 ";


                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = "erpm_stock_received.ST_Item_ID <> 0 ";
                } else {
                    whereCondition = "erpm_stock_received.ST_Item_ID =" + esr.getErpmItemMaster().getErpmimId();
                }



                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_IM_ID =" + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_IM_ID= " + esr.getInstitutionmaster().getImId();
                }



                if (esr.getSubinstitutionmaster().getSimId() == null)// || esr.getSubinstitutionmaster().getSimId()==0)
                {
                    whereCondition = whereCondition + " and  erpm_stock_received.ST_SIM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_SIM_ID= " + esr.getSubinstitutionmaster().getSimId();
                }



                if (esr.getDepartmentmaster().getDmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_DM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_DM_ID= " + esr.getDepartmentmaster().getDmId();
                }


//
//                if(esr.getSuppliermaster().getSmId() == null)
//                 whereCondition = whereCondition + " and erpm_stock_received.ST_SM_ID= <>0";
//                 else
//                 whereCondition = whereCondition + " and erpm_stock_received.ST_SM_ID= " + esr.getSuppliermaster().getSmId();

                if (getfromDate().length() != 10) {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (gettoDate().length() != 10) {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since <= str_to_date('" + toDate + "','%d-%m-%Y')";
                }


                hm.put("condition", whereCondition);

                JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
                JasperExportManager.exportReportToPdfStream(jp, baos);
                response.setContentLength(baos.size());
                ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
                //setInputStream(bis);
                inputStream = bis;



                return SUCCESS;
            } catch (Exception e) {
                message = "Error is : " + e.getMessage() + e.getCause();
                return ERROR;
            }
        }
        return "input";
    }

    @SkipValidation
    public String PrintOpeningStock() throws Exception {
        String frmDate = null;
        String toDate = null;
        //assign value to var frmDate ,toDate
        HashMap hm = new HashMap();
        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }

        java.util.Date d1 = null;
        java.util.Date d2 = null;
        //check dates are empty  or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());
        }
        if (frmDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            frmDate = "11-11-2000";
        }
        try {
            //change formate of date form string to Date ( dd-MM-yyyy)
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d1 = sdf.parse("" + frmDate);
            d2 = sdf.parse("" + toDate);

        } catch (Exception e) {
        }
//check fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {


            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\ReturnIssuedItemReceiving.jasper");

            String whereCondition = "";


            try {

                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 


                HttpServletResponse response = ServletActionContext.getResponse();

                response.setHeader("Cache-Control", "no-cache");

                response.setHeader("Content-Disposition", "attachment; filename=Indent_Reports.pdf");

                response.setHeader("Expires", "0");

                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                response.setHeader("Pragma", "public");


                ByteArrayOutputStream baos = new ByteArrayOutputStream();


                //Setup Where Condition Clause

//            if (erpmirm.getIrmId() == null) {
//
//                whereCondition = "erpm_issue_return_master.IRM_ID = 0";//getSession().getAttribute("imId");
//            } else {
//                whereCondition = "erpm_issue_return_master.IRM_ID = " + erpmirm.getIrmId();
//            }
                if (getfromDate().length() != 10) {
                    whereCondition = " and erpm_purchasechallan_master.PCM_Recv_Date >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = " and erpm_purchasechallan_master.PCM_Recv_Date >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (gettoDate().length() != 10) {
                    whereCondition = whereCondition + " and erpm_purchasechallan_master.PCM_Recv_Date <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_purchasechallan_master.PCM_Recv_Date <= str_to_date('" + toDate + "','%d-%m-%Y')";
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
        return "input";
    }

    @SkipValidation
    public String IssuesPendingReport() throws Exception {
        HashMap hm = new HashMap();
        String frmDate = null;
        String toDate = null;
        //assign value to var frmDate ,toDate
        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }

        java.util.Date d1 = null;
        java.util.Date d2 = null;
        //check dates are empty or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());
        }
        if (frmDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            frmDate = "11-11-2000";
        }
        try {
            //change formate of date form string to Date ( dd-MM-yyyy)
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d1 = sdf.parse("" + frmDate);
            d2 = sdf.parse("" + toDate);

        } catch (Exception e) {
        }
//check fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {

            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\IssuesPendingToBeReceive.jasper");


            String whereCondition = "";

            try {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

                HttpServletResponse response = ServletActionContext.getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=IssuesPendingToBeReceive.pdf");
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                whereCondition = "";
//       Setup Where Condition Clause
                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_From_Institute_ID = " + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_From_Institute_ID = " + esr.getInstitutionmaster().getImId();
                }

                if (esr.getSubinstitutionmaster().getSimId() == null) {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_From_Subinstitute_ID = " + getSession().getAttribute("simId");
                } else {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_From_Subinstitute_ID = " + esr.getSubinstitutionmaster().getSimId();
                }

                if (esr.getDepartmentmaster().getDmId() == null) {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_From_Department_ID = " + getSession().getAttribute("dmId");
                } else {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_From_Department_ID = " + esr.getDepartmentmaster().getDmId();
                }


//        if(esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat1().getErpmicmItemId()==0)
//            whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat1 <> 0";
//        else
//            whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat1 = " + esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat1().getErpmicmItemId();

//        if(esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat2()==null)
//            whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat2 <> " + esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat2().getErpmicmItemId();
//        else
//            whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat2 = " + esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat2().getErpmicmItemId();
//
//        if(esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat3()==null)
//            whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat3 <> " + esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat3().getErpmicmItemId();
//        else
//            whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat3 = " + esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat3().getErpmicmItemId();

                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = whereCondition + " AND erpm_issue_detail.ISD_Item_ID <> 0";
                } else {
                    whereCondition = whereCondition + " AND erpm_issue_detail.ISD_Item_ID = " + esr.getErpmItemMaster().getErpmimId();
                }
                if (getfromDate().length() != 10) {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_Issue_Date >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_Issue_Date >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (gettoDate().length() != 10) {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_Issue_Date <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_Issue_Date <= str_to_date('" + toDate + "','%d-%m-%Y')";
                }


                hm.put("condition", whereCondition);

                JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
                JasperExportManager.exportReportToPdfStream(jp, baos);
                response.setContentLength(baos.size());
                ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
                inputStream = bis;

                return SUCCESS;
            } catch (Exception e) {
                message = "Exception -> " + e.getMessage() + e.getCause();
                return ERROR;
            }
        }
        return "input";
    }

    @SkipValidation
    public String PrintStockInHand() throws Exception {
        String frmDate = null;
        String toDate = null;
        //assign value to var frmDate ,toDate

        HashMap hm = new HashMap();

        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }

        java.util.Date d1 = null;
        java.util.Date d2 = null;
        //check dates are empty or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());
        }
        if (frmDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            frmDate = "11-11-2000";
        }
        try {
            //change formate of date form string to Date ( dd-MM-yyyy)
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d1 = sdf.parse("" + frmDate);
            d2 = sdf.parse("" + toDate);

        } catch (Exception e) {
        }
//check fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {

            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\Stock_In_Hand_Report.jasper");

            String whereCondition = "";

            try {

                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

                HttpServletResponse response = ServletActionContext.getResponse();

                response.setHeader("Cache-Control", "no-cache");

                response.setHeader("Content-Disposition", "attachment; filename=Stock_In_Hand_Report.pdf");

                response.setHeader("Expires", "0");

                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();


                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = " view_item_ledger.Item_ID <> 0 ";//
                } else {
                    whereCondition = "view_item_ledger.Item_ID =" + esr.getErpmItemMaster().getErpmimId();
                }



                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " and view_item_ledger.IM_ID =" + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " and view_item_ledger.IM_ID= " + esr.getInstitutionmaster().getImId();
                }



                if (esr.getSubinstitutionmaster().getSimId() == null)// || esr.getSubinstitutionmaster().getSimId()==0)
                {
                    whereCondition = whereCondition + " and  view_item_ledger.SIM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and  view_item_ledger.SIM_ID= " + esr.getSubinstitutionmaster().getSimId();
                }



                if (esr.getDepartmentmaster().getDmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " and  view_item_ledger.DM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and  view_item_ledger.DM_ID= " + esr.getDepartmentmaster().getDmId();
                }
                if (getfromDate().length() != 10) {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (gettoDate().length() != 10) {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated <= str_to_date('" + toDate + "','%d-%m-%Y')";
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
        return "input";
    }
    //this report used general finacial resourse information

    @SkipValidation
    public String PrintGFRReport40() throws Exception {
        String frmDate = null;
        String toDate = null;
        //assign value to var frmDate ,toDate

        HashMap hm = new HashMap();

        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }

        java.util.Date d1 = null;
        java.util.Date d2 = null;
        //check dates are empty or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());
        }
        if (frmDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            frmDate = "11-11-2000";
        }
        try {
            //change format of date form string to Date ( dd-MM-yyyy)
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d1 = sdf.parse("" + frmDate);
            d2 = sdf.parse("" + toDate);

        } catch (Exception e) {
        }
//check fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {

            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\GFRReport40.jasper");
            String whereCondition = "";

            try {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 
                * 
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=GFRReport40.pdf");
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //Setup Where Condition Clause

                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = " view_stock_received.ST_Item_ID <> 0 ";//
                } else {
                    whereCondition = "view_stock_received.ST_Item_ID =" + esr.getErpmItemMaster().getErpmimId();
                }



                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " and view_stock_received.ST_IM_ID =" + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " and view_stock_received.ST_IM_ID = " + esr.getInstitutionmaster().getImId();
                }



                if (esr.getSubinstitutionmaster().getSimId() == null)// || esr.getSubinstitutionmaster().getSimId()==0)
                {
                    whereCondition = whereCondition + " and   view_stock_received.ST_SIM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and  view_stock_received.ST_SIM_ID = " + esr.getSubinstitutionmaster().getSimId();
                }



                if (esr.getDepartmentmaster().getDmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " and  view_stock_received.ST_DM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and  view_stock_received.ST_DM_ID = " + esr.getDepartmentmaster().getDmId();
                }
                if (esr.getSuppliermaster().getSmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " and   view_stock_received.ST_SM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and   view_stock_received.ST_SM_ID = " + esr.getSuppliermaster().getSmId();
                }
                if (getfromDate().length() != 10) {
                    whereCondition = whereCondition + " and view_stock_received.ST_In_Stock_Since >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and  view_stock_received.ST_In_Stock_Since >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (gettoDate().length() != 10) {
                    whereCondition = whereCondition + " and  view_stock_received.ST_In_Stock_Since <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and  view_stock_received.ST_In_Stock_Since <= str_to_date('" + toDate + "','%d-%m-%Y')";
                }



                hm.put("Condition", whereCondition);
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
        return "input";
    }

    @SkipValidation
    public String PrintGFRReport41() throws Exception {
        String frmDate = null;
        String toDate = null;
        //assign value to var frmDate ,toDate

        HashMap hm = new HashMap();

        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }

        java.util.Date d1 = null;
        java.util.Date d2 = null;
        //check dates are empty or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());
        }
        if (frmDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            frmDate = "11-11-2000";
        }
        try {
            //change formate of date form string to Date ( dd-MM-yyyy)
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d1 = sdf.parse("" + frmDate);
            d2 = sdf.parse("" + toDate);
        } catch (Exception e) {
        }
//check fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {

            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\GFRReport41.jasper");
            String whereCondition;

            try {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 
                * 
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=GFRReport41.pdf");
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //Setup Where Condition Clause

                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = "view_item_ledger.Item_ID <> 0 ";//
                } else {
                    whereCondition = "view_item_ledger.Item_ID =" + esr.getErpmItemMaster().getErpmimId();
                }



                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " and view_item_ledger.IM_ID =" + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " andview_item_ledger.IM_ID = " + esr.getInstitutionmaster().getImId();
                }



                if (esr.getSubinstitutionmaster().getSimId() == null)// || esr.getSubinstitutionmaster().getSimId()==0)
                {
                    whereCondition = whereCondition + " and    view_item_ledger.SIM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and   view_item_ledger.SIM_ID = " + esr.getSubinstitutionmaster().getSimId();
                }



                if (esr.getDepartmentmaster().getDmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " and  view_item_ledger.DM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and view_item_ledger.DM_ID = " + esr.getDepartmentmaster().getDmId();
                }
//                 if(esr.getSuppliermaster().getSmId()!= null)
//                 whereCondition = whereCondition + " and  view_item_ledger.Supplier_Name = " + esr.getSuppliermaster().getSmName();
                if (getfromDate().length() != 10) {
                    whereCondition = whereCondition + " and view_item_ledger.Dated >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (gettoDate().length() != 10) {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated <= str_to_date('" + toDate + "','%d-%m-%Y')";
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
        return "input";
    }

    @SkipValidation
    public String PrintStockRegister() throws Exception {
        String frmDate = null;
        String toDate = null;
        //assign value to var frmDate ,toDate

        HashMap hm = new HashMap();

        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }

        java.util.Date d1 = null;
        java.util.Date d2 = null;
        //check dates are empty or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());
        }
        if (frmDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            //frmDate = "11-11-2000";
            frmDate = format.format(originalDate.getTime());
        }
        try {
            //change formate of date form string to Date ( dd-MM-yyyy)
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d1 = sdf.parse("" + frmDate);
            d2 = sdf.parse("" + toDate);

        } catch (Exception e) {
        }
//check fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {

            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\Stock_Register.jasper");
            String whereCondition;

            try {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 
                * 
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=Stock_Register.pdf");
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //Setup Where Condition Clause

                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = "view_item_ledger.Item_ID <> 0 ";
                } else {
                    whereCondition = "view_item_ledger.Item_ID =" + esr.getErpmItemMaster().getErpmimId();
                }


                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " and view_item_ledger.IM_ID =" + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " and view_item_ledger.IM_ID = " + esr.getInstitutionmaster().getImId();
                }


                if (esr.getSubinstitutionmaster().getSimId() == null) {
                    whereCondition = whereCondition + " and    view_item_ledger.SIM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and   view_item_ledger.SIM_ID = " + esr.getSubinstitutionmaster().getSimId();
                }



                if (esr.getDepartmentmaster().getDmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " and  view_item_ledger.DM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and view_item_ledger.DM_ID = " + esr.getDepartmentmaster().getDmId();
                }

                if (getfromDate().length() != 0) {
                    whereCondition = whereCondition + " and view_item_ledger.Dated >= str_to_date('01-01-1000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (gettoDate().length() != 0) {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated <= str_to_date('" + toDate + "','%d-%m-%Y')";
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
        return "input";
    }

    @SkipValidation
    public String showGFRreport() throws Exception {
        HashMap hm = new HashMap();

        String fileName = getSession().getServletContext().getRealPath("pico\\Administration\\Reports\\ShowGFRMappedinProgram.jasper");

        String whereCondition = "";

        try {
            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=ShowGfr.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            whereCondition = "gfr_program_mapping.`GPM_Program_ID` = 30";

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
*/
}
