/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 /**
 *
 * @author Sajid Aziz
 */
package PrePurchase;

import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.Suppliermaster;
import pojo.hibernate.SuppliermasterDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.StatemasterDAO;
import pojo.hibernate.CountrymasterDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.Countrymaster;
import pojo.hibernate.Statemaster;
import pojo.hibernate.SupplierAddress;
import pojo.hibernate.SupplierAddressDAO;
import utils.DevelopmentSupport;

import java.util.*;

public class ManageSupplier extends DevelopmentSupport {

    private Suppliermaster erpmsm;
    private Suppliermaster erpmsm1;
    private SupplierAddress supad;
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private ErpmGenMasterDao gmDao = new ErpmGenMasterDao();
    private SuppliermasterDAO erpmsmDao = new SuppliermasterDAO();
    private StatemasterDAO smDao = new StatemasterDAO();
    private CountrymasterDAO cmDao = new CountrymasterDAO();
    private SupplierAddressDAO adDao = new SupplierAddressDAO();
    private List<Institutionmaster> imIdList = new ArrayList<Institutionmaster>();
    private List<SupplierAddress> saIdList = new ArrayList<SupplierAddress>();
    private List<ErpmGenMaster> gmIdList = new ArrayList<ErpmGenMaster>();
    private List<ErpmGenMaster> gmIdList1 = new ArrayList<ErpmGenMaster>();
    private List<Suppliermaster> erpmsmList = new ArrayList<Suppliermaster>();
    private List<Statemaster> stList = new ArrayList<Statemaster>();
    private List<Countrymaster> ctList = new ArrayList<Countrymaster>();
    private List<SupplierAddress> adList = new ArrayList<SupplierAddress>();
    private Integer SMID;
    private String message;
    private String SMNAME;
    private Byte defaultCountry;
    short i1 = 1;
    short i2 = 2;

    public void setdefaultCountry(Byte defaultCountry) {
        this.defaultCountry = defaultCountry;
    }

    public Byte getdefaultCountry() {
        return this.defaultCountry;
    }

    public void setSMID(Integer SMID) {
        this.SMID = SMID;
    }

    public Integer getSMID() {
        return this.SMID;
    }

    public void setSMNAME(String SMNAME) {
        this.SMNAME = SMNAME;
    }

    public String getSMNAME() {
        return this.SMNAME;
    }

    public void seterpmsm(Suppliermaster erpmsm) {
        this.erpmsm = erpmsm;
    }

    public Suppliermaster geterpmsm() {
        return this.erpmsm;
    }

    public void setsupad(SupplierAddress supad) {
        this.supad = supad;
    }

    public SupplierAddress getsupad() {
        return this.supad;
    }

    public void setimIdList(List<Institutionmaster> imIdList) {
        this.imIdList = imIdList;
    }

    public List<Institutionmaster> getimIdList() {
        return this.imIdList;
    }

    public void setsaIdList(List<SupplierAddress> saIdList) {
        this.saIdList = saIdList;
    }

    public List<SupplierAddress> getsaIdList() {
        return this.saIdList;
    }

    public void seterpmsmList(List<Suppliermaster> erpmsmList) {
        this.erpmsmList = erpmsmList;
    }

    public List<Suppliermaster> geterpmsmList() {
        return this.erpmsmList;
    }

    public void setstList(List<Statemaster> stList) {
        this.stList = stList;
    }

    public List<Statemaster> getstList() {
        return this.stList;
    }

    public void setctList(List<Countrymaster> ctList) {
        this.ctList = ctList;
    }

    public List<Countrymaster> getctList() {
        return this.ctList;
    }

    public void setgmIdList(List<ErpmGenMaster> gmIdList) {
        this.gmIdList = gmIdList;
    }

    public List<ErpmGenMaster> getgmIdList() {
        return this.gmIdList;
    }

    public void setgmIdList1(List<ErpmGenMaster> gmIdList1) {
        this.gmIdList1 = gmIdList1;
    }

    public List<ErpmGenMaster> getgmIdList1() {
        return this.gmIdList1;
    }

        public void setadList(List<SupplierAddress> adList) {
        this.adList = adList;
    }

    public List<SupplierAddress> getadList() {
        return this.adList;
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

            //Prepare LOVs
            InitializeLOVs();

            // Clear Objects and Form data
            erpmsm = null;
            supad = null;

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ManageSuppleirlAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update
            if (erpmsm.getSmId() == null) {
                //Savee Supplier Record
                erpmsmDao.save(erpmsm);

                //Save Supplier's primary address
                supad.setSuppliermaster(erpmsm);
                adDao.save(supad);

                message = "Supplier record created. Supplier id is : " + erpmsm.getSmId();
            } else {

                erpmsm1 = erpmsmDao.findByErpmSMId(getSMID());
                erpmsm1 = erpmsm;
                erpmsmDao.update(erpmsm1);


                SupplierAddress supad1 = adDao.findErpmSMId(erpmsm1.getSmId());
                supad1 = supad;
                supad1.setSuppliermaster(erpmsm1);
                adDao.update(supad1);
                message = "Supplier record updated successfully";
            }

            return SUCCESS;
        } catch (Exception e) {
            NotifyDatabaseConstraintViolation(e);
            InitializeLOVs();
            return "ERROR_IN_SM";

        }
    }

/*        public String SaveandAddmoreAddresses() throws Exception {
        try {
            //Save Supplier
            erpmsmDao.save(erpmsm);

            //Set Supplier in Supplier_Address Obect
            supad.setSuppliermaster(erpmsm);

            //Save Supplier_Address Object
            adDao.save(supad);

            //Prepare LOV containing Countries
            ctList = cmDao.findAll();

            //Set default Country (Need to be setup as same as that of Institution at some stage
            defaultCountry = cmDao.findCountry("India");

            //Prepare LOV containing States
            stList = smDao.findByCountryName("India");

            message = "Supplier Id : " + erpmsm.getSmId() + "  Supplier Name : " + erpmsm.getSmName();
            
            SMID = erpmsm.getSmId();

            adList = adDao.findBySupplierId(erpmsm.getSmId());

            return SUCCESS;
        } catch (Exception e) {

          NotifyDatabaseConstraintViolation(e);
          InitializeLOVs();
          return "ERROR_IN_SM";
        }
    }
*/

    public String Browse() throws Exception {
        try {
        erpmsmList = erpmsmDao.findAll();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> MangesupplierAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Delete() throws Exception {
        try {

            //Retrieve Supplier's record
            erpmsm = erpmsmDao.findByErpmSMId(getSMID());         

           // adDao.deleteSupplierAddresses(getSMID());
            saIdList = adDao.findBySupplierId(getSMID());
            
            for (int i = 0; i < saIdList.size() ; i++)
               adDao.delete(saIdList.get(i));
            
            erpmsmDao.delete(erpmsm);

            message = "Supplier Record deleted successfully";
            Browse();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method ->MangesupplierAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Edit() throws Exception {
        try {
            InitializeLOVs();

            erpmsm = erpmsmDao.findByErpmSMId(getSMID());
            supad = adDao.findErpmSMId(erpmsm.getSmId());

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> MangesupplierAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public void InitializeLOVs() {
        //Prepare LOV containing User Institutions
        imIdList = imDao.findForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

        //Prepare List containing Supplier Type Types
        gmIdList = gmDao.findByErpmGmType(i2);

        //Prepare List containing Owner Types
        gmIdList1 = gmDao.findByErpmGmType(i1);

        //Prepare LOV containing Countries
        ctList = cmDao.findAll();

        //Set default Country (Need to be setup as same as that of Institution at some stage
        defaultCountry = cmDao.findCountry("India");

        //Prepare LOV containing States
        stList = smDao.findAll();


        return;
    }

    public void validate() {
        try {

            //Validations on Supplier data
            if (erpmsm.getInstitutionmaster().getImId() == null) {
                addFieldError("erpmsm.institutionmaster.imId", "Please select institution from the list");
            }
            if (erpmsm.getSmName().length() == 0) {
                addFieldError("erpmsm.smName", "Please enter Supplier Name ");
            }
            if (erpmsm.getErpmGenMasterBySmSupplierType().getErpmgmEgmId() == null) {
                addFieldError("erpmsm.erpmGenMasterBySmSupplierType.erpmgmEgmId", "Please enter Supplier Type");
            }
            if (erpmsm.getErpmGenMasterBySmOwnershipType().getErpmgmEgmId() == null) {
                addFieldError("erpmsm.erpmGenMasterBySmOwnershipType.erpmgmEgmId", "Please select Ownership Type from the list");
            }
            if (erpmsm.getSmPanNo().length() == 0 && erpmsm.getSmTanNo().length() == 0) {
                addFieldError("erpmsm.smPanNo", "Please provide value for either PAN No or TAN No");
                addFieldError("erpmsm.smTanNo", "Please provide value for either PAN No or TAN No");
            }
            if (erpmsm.getSmPanNo().length() == 0 && erpmsm.getSmTanNo().length() != 0) {
                erpmsm.setSmPanNo(null);
            }
            if (erpmsm.getSmPanNo().length() != 0 && erpmsm.getSmTanNo().length() == 0) {
                erpmsm.setSmTanNo(null);
            }

            //Validations on Address part
            if (supad.getAdLine1().length() == 0) {
                addFieldError("supad.adLine1", "Please enter primary contact address of supplier");
            }
            if (supad.getCountrymaster().getCountryId() == null) {
                addFieldError("supad.countrymaster.countryId", "Please select country from the list");
            }
            if (supad.getAdCity().length() == 0) {
                addFieldError("supad.adCity", "Please enter city");
            }
            if (supad.getStatemaster().getStateId() == null) {
                supad.setStatemaster(null);
            }

            //Initialize LOVs
            InitializeLOVs();

            message = "== In Validate ==";

        } catch (NullPointerException npe) {
        }
    }

    public String NotifyDatabaseConstraintViolation(Exception e) {
        if (e.getCause().toString().contains("Unique_SM_IM_ID_SM_PAN_No")) {
            message = "The PAN No '" + erpmsm.getSmPanNo() + "' is already registered with supplier :" + erpmsmDao.findByPANNo(erpmsm.getSmPanNo(), erpmsm.getInstitutionmaster().getImId());
        }
        else if (e.getCause().toString().contains("Unique_SM_IM_ID_SM_TAN_No")) {
            message = "The TAN No '" + erpmsm.getSmTanNo() + "' is already registered with supplier :" + erpmsmDao.findByTANNo(erpmsm.getSmTanNo(), erpmsm.getInstitutionmaster().getImId());
        } else if (e.getCause().toString().contains("SM_IM_ID_SM_Name_Unique")) {
            message = "The supplier '" + erpmsm.getSmName() + "' is already registered with " + imDao.findByImId(erpmsm.getInstitutionmaster().getImId()).getImName();
        } else {
            message = "Exception in Save method -> MangesupplierAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        }        
       return ERROR;

    }
}
