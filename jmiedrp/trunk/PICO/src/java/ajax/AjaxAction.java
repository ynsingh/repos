package ajax;

import pojo.hibernate.ErpmTenderMasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import com.opensymphony.xwork2.ActionContext;

import pojo.hibernate.*;

import utils.DevelopmentSupport;
import com.opensymphony.xwork2.Action;
import java.io.PrintWriter;
import java.util.*;
import java.util.Locale;
import java.text.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import utils.DateUtilities;
//import sun.util.resources.LocaleData;
//import sun.util.resources.LocaleNamesBundle;

public class AjaxAction extends DevelopmentSupport {

    private String outstr = "";
    private String searchValue;
    private String searchValue2;
    private String searchValue3;
    private String InstitutionRole;
    private String message;
    private List<ErpmIndentDetail> indentItemList = new ArrayList<ErpmIndentDetail>();

    public List<ErpmIndentDetail> getindentItemList() {
        return this.indentItemList;
    }

    public void setindentItemList(List<ErpmIndentDetail> indentItemList) {
        this.indentItemList = indentItemList;
    }

    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }

    public String getOutstr() {
        return outstr;
    }

    public void setOutstr(String outstr) {
        this.outstr = outstr;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getSearchValue2() {
        return searchValue2;
    }

    public void setSearchValue2(String searchValue2) {
        this.searchValue2 = searchValue2;
    }

    public String getSearchValue3() {
        return searchValue3;
    }

    public void setSearchValue3(String searchValue3) {
        this.searchValue3 = searchValue3;
    }

    public String getInstitutionRole() {
        return InstitutionRole;
    }

    public void setInstitutionRole(String InstitutionRole) {
        this.InstitutionRole = InstitutionRole;
    }

    public String setStatus() throws Exception {
        PrintWriter out = getResponse().getWriter();
        try {
            ErpmGenMaster erpmGmId = new ErpmGenMaster();
            ErpmGenMasterDao erpmGmDao = new ErpmGenMasterDao();
            erpmGmId = erpmGmDao.findByErpmGmDesc(searchValue);
            outstr = erpmGmId.getErpmgmEgmDesc() + "hellooo" + searchValue + searchValue2;
            UserMessage user = new UserMessage();
            UserMessageDAO userDao = new UserMessageDAO();
            user = userDao.findByumId(Integer.parseInt(searchValue2));
            outstr = outstr + user.getUmId();
            user.setErpmGenMaster(erpmGmId);

            userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();
        return Action.SUCCESS;
    }

    public String getSubinstitutionList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {

            List<Subinstitutionmaster> simList = null;
            SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
            try {
                 //If Logged in user role is Administrator                            
                    if (getSession().getAttribute("isAdministrator").toString().compareTo("Administrator") == 0) {
                        simList = simDao.findSubInstForAdmin(Short.valueOf(getSession().getAttribute("imId").toString()));
                    } else {
                        simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
                    }
                if (simList != null && !simList.isEmpty()) {
                    for (Subinstitutionmaster sim : simList) {
                        Integer val = sim.getSimId();
                        String str = sim.getSimName();

                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //  outstr = "UserId is: " + getSession().getAttribute("userid").toString();
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getDepartmentList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<Departmentmaster> dmList = null;
            DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
            try {
                //If Logged in user role is Administrator                            
                    if(getSession().getAttribute("isAdministrator").toString().compareTo("Administrator") == 0)
                        dmList = dmDao.findBydmSimId(Integer.valueOf(searchValue));
                    else
                        dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(searchValue));
                
                    if (dmList != null && !dmList.isEmpty()) {
                    for (Departmentmaster dm : dmList) {
                        Integer val = dm.getDmId();
                        String str = dm.getDmName();
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(outstr);
            out.flush();
            out.close();
        }
        return Action.SUCCESS;
    }

    public String getDepartmentForAdminList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<Departmentmaster> dmList = null;
            DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
            try {

                dmList = dmDao.findBydmSimId(Integer.valueOf(searchValue));
                if (dmList != null && !dmList.isEmpty()) {
                    for (Departmentmaster dm : dmList) {
                        Integer val = dm.getDmId();
                        String str = dm.getDmName();
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(outstr);
            out.flush();
            out.close();
        }
        return Action.SUCCESS;
    }

    public String getInstitutionUserRoleList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<Institutionuserroles> iurList = null;
            InstitutionuserroleDAO iurDao = new InstitutionuserroleDAO();
            try {
                iurList = iurDao.findByInstitutionId(Short.parseShort(searchValue));
                if (iurList != null && !iurList.isEmpty()) {
                    for (Institutionuserroles iur : iurList) {
                        Integer val = iur.getIurId();
                        String str = iur.getIurName();
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(outstr);
            out.flush();
            out.close();
        }
        return Action.SUCCESS;
    }

    public String getInstitutions() throws Exception {
        PrintWriter out = getResponse().getWriter();

        List<Institutionmaster> imList = null;
        InstitutionmasterDAO imDao = new InstitutionmasterDAO();
        try {
            imList = imDao.findAll();
            if (imList != null && !imList.isEmpty()) {
                for (Institutionmaster im : imList) {
                    Short val = im.getImId();
                    String str = im.getImName();
                    if (outstr.length() == 0) {
                        outstr = val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();
        return Action.SUCCESS;
    }

    public String getSubCategory1List() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<ErpmItemCategoryMaster> erpmIcmList = null;
            ErpmItemCategoryMasterDao erpmIcmDao = new ErpmItemCategoryMasterDao();
            try {
                erpmIcmList = erpmIcmDao.findByerpmItemCategoryMaster(Integer.parseInt(searchValue));
                if (erpmIcmList != null && !erpmIcmList.isEmpty()) {
                    for (ErpmItemCategoryMaster erpmIcm : erpmIcmList) {
                        Integer val = erpmIcm.getErpmicmItemId();
                        String str = erpmIcm.getErpmicmCatDesc();
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(outstr);
            out.flush();
            out.close();
        }
        return Action.SUCCESS;
    }

    public String CapitalCategoryList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<ErpmCapitalCategory> erpmccList = null;
            ErpmCapitalCategoryDao erpmccDao = new ErpmCapitalCategoryDao();
            try {
                erpmccList = erpmccDao.findByImId(Short.parseShort(searchValue));
                if (erpmccList != null && !erpmccList.isEmpty()) {
                    for (ErpmCapitalCategory erpmIcc : erpmccList) {
                        Integer val = erpmIcc.getErpmccId();
                        String str = erpmIcc.getErmccDesc();
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getStateList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<Statemaster> smList = null;
            StatemasterDAO smDao = new StatemasterDAO();
            try {
                smList = smDao.findByCountryId(Byte.parseByte(searchValue));
                if (smList != null && !smList.isEmpty()) {
                    for (Statemaster smIcc : smList) {
                        Integer val = Integer.parseInt(smIcc.getStateId().toString());
                        String str = smIcc.getStateName();
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getCountryList() throws Exception {
        PrintWriter out = getResponse().getWriter();


        List<Countrymaster> cmList = null;
        CountrymasterDAO cmDao = new CountrymasterDAO();
        try {
            cmList = cmDao.findAll();
            if (cmList != null && !cmList.isEmpty()) {
                for (Countrymaster cmIcc : cmList) {
                    Integer val = Integer.parseInt(cmIcc.getCountryId().toString());
                    String str = cmIcc.getCountryName();
                    if (outstr.length() == 0) {
                        outstr = val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getSecretQuestion() throws Exception {
        PrintWriter out = getResponse().getWriter();

        //searchValue contains user name; searchValue2 contains user's date of birth

        outstr = "Invalid user name or date of birth";
        try {
            ErpmusersDAO userDao = new ErpmusersDAO();
            outstr = userDao.RetrieveSecretQuestion(searchValue, searchValue2);

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getGURDescription() throws Exception {
        PrintWriter out = getResponse().getWriter();

        //searchValue contains generic Role ID

        try {
            GenericuserrolesDAO gurDao = new GenericuserrolesDAO();
            outstr = gurDao.RetrieveRoleDescription(Byte.parseByte(searchValue));

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getProgramList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<Erpmprogram> erpmpList = null;
            ErpmprogramDAO erpmpDao = new ErpmprogramDAO();
            try {
                erpmpList = erpmpDao.findRemainingProgramsForInstitute(Byte.parseByte(searchValue), Integer.parseInt(InstitutionRole));
                if (erpmpList != null && !erpmpList.isEmpty()) {
                    for (Erpmprogram erpmpIcc : erpmpList) {
                        Short val = erpmpIcc.getErpmpId();
                        String str = erpmpIcc.getErpmpDisplayName();
                        if (outstr.length() == 0) {
                            outstr += val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getUOP() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            ErpmItemMasterDAO UOPDao = new ErpmItemMasterDAO();
            try {
                ErpmItemMaster erpmim = UOPDao.findByErpmimId(Integer.parseInt(searchValue));
                outstr = erpmim.getErpmGenMaster().getErpmgmEgmDesc();
                //outstr1=erpmim.getErpmIndentDetails().getErpmItemRates().getErpmIndentDetails().
                if (outstr.isEmpty()) {
                    outstr = "No Purchase Unit Defined";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }

    public String getItemRateListItem() throws Exception {
        PrintWriter out = getResponse().getWriter();
        DateFormat formatter;
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date Today = Calendar.getInstance().getTime();

        if (searchValue.length() > 0) {
            ErpmItemRateDAO itemRateDao = new ErpmItemRateDAO();
            List<ErpmItemRate> itemRateList = null;

            try {


                itemRateList = itemRateDao.findApprovedItemRatesForToday(Integer.parseInt(searchValue), Today, searchValue2, Integer.parseInt(searchValue3));

                if (itemRateList != null && !itemRateList.isEmpty()) {
                    for (ErpmItemRate itemRate : itemRateList) {
                        Integer val = itemRate.getIrItemRateId();

                        String FrmDate = formatter.format(itemRate.getIrdWefDate());
                        String ToDate = formatter.format(itemRate.getIrdWetDate());

                        String str = String.format("%-25s %20s %20s %10d", itemRate.getSuppliermaster().getSmName(),
                                FrmDate,
                                ToDate,
                                (itemRate.getIrdRate().intValue()));
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                } else {
                    outstr = outstr + "No rate is approved for the item. Please add on your own, if required";
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }

    public String getItemRateForItemRateId() throws Exception {
        PrintWriter out = getResponse().getWriter();
        DateFormat formatter;
        formatter = new SimpleDateFormat("dd-MMM-yyyy");

        if (searchValue.length() > 0) {
            ErpmItemRateDAO itemRateDao = new ErpmItemRateDAO();
            try {
                ErpmItemRate erpmItemRate = itemRateDao.findByirItemRateId(Integer.parseInt(searchValue));
                String FrmDate = formatter.format(erpmItemRate.getIrdWefDate());
                String ToDate = formatter.format(erpmItemRate.getIrdWetDate());
                outstr = erpmItemRate.getIrdRate().toString() + "|"
                        + FrmDate + "|" + ToDate + "|"
                        + erpmItemRate.getErpmGenMasterByIrCurrencyId().getErpmgmEgmDesc() + "|"
                        + erpmItemRate.getIrMinQty().toString() + "|" + erpmItemRate.getIrMaxQty().toString();

                if (outstr.isEmpty()) {
                    outstr = "No Rate Defined";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }

    public String getRateForItem() throws Exception {
        PrintWriter out = getResponse().getWriter();
        //DateFormat formatter ;
        // formatter = new SimpleDateFormat("dd-MMM-yyyy");
        if (searchValue.length() > 0) {
            ErpmItemRateDAO itemrateDao = new ErpmItemRateDAO();
            try {
                ErpmItemRate erpmItemRate = itemrateDao.findByirItemRateId(Integer.parseInt(searchValue));
                outstr = erpmItemRate.getIrdRate().toString();//+ "|" +
                if (outstr.isEmpty()) {
                    outstr = "No Rate Defined";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }

    public void setLanguage() throws Exception {
        PrintWriter out = getResponse().getWriter();

        outstr = "hellooo" + searchValue;

        try {

            if (searchValue == "France") {

                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("global_zh_CN", locale);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        //return Action.SUCCESS;
    }

    public String getSuppliersName() throws Exception {
        PrintWriter out = getResponse().getWriter();
        if (searchValue.length() > 0) {

            ErpmPoMasterDAO epomDao = new ErpmPoMasterDAO();
            try {


                ErpmPoMaster epom = epomDao.findByPoMasterId(Integer.parseInt(searchValue));
                outstr = epom.getSuppliermaster().getSmName().toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();
        return Action.SUCCESS;
    }

    public String getAllocatedAmount() throws Exception {
        PrintWriter out = getResponse().getWriter();
        if (searchValue.length() > 0) {
            DepartmentalBudgetAllocationDAO dbaDao = new DepartmentalBudgetAllocationDAO();
            try {

                DepartmentalBudgetAllocation dba1 = dbaDao.findForDepartmentBybhmId(Short.parseShort(searchValue), Integer.parseInt(searchValue2));
                outstr = dba1.getDbaAmount().toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();
        return Action.SUCCESS;
    }

    public String getpoterm() throws Exception {
        PrintWriter out = getResponse().getWriter();
        if (searchValue.length() > 0) {
            ErpmGeneralTermsDAO gtermsDao = new ErpmGeneralTermsDAO();
            try {

                ErpmGeneralTerms gterms = gtermsDao.findTermsforInsituteByGenmasterID(Integer.parseInt(searchValue));
                outstr = gterms.getGtTermsDescription();
                if (outstr.isEmpty()) {
                    outstr = "Please Write Your Description here For Your Purchase Order:";
                }
            } catch (Exception e) {
                outstr = "No Terms Unit Defined";

                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();
        return Action.SUCCESS;
    }

    public String getitemList() throws Exception {
        PrintWriter out = getResponse().getWriter();


        List<ErpmItemMaster> eimList = null;
        ErpmItemMasterDAO eirDao = new ErpmItemMasterDAO();
        try {

            eimList = eirDao.findItemsForSupplier(Short.valueOf(getSession().getAttribute("imId").toString()), searchValue);
            if (eimList != null && !eimList.isEmpty()) {
                for (ErpmItemMaster eim : eimList) {
                    Integer val = Integer.parseInt(eim.getErpmimId().toString());
                    String str = eim.getErpmimItemBriefDesc();
                    if (outstr.length() == 0) {
                        outstr = val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            outstr = "here" + searchValue;
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getsupplieraftervalidation() throws Exception {
        PrintWriter out = getResponse().getWriter();
        //SuppliermasterDAO smDao = new SuppliermasterDAO();
        try {
            //List<Suppliermaster> smList =  smDao.findByImId(Short.parseShort(searchValue));

            List<Suppliermaster> smList = null;
            SuppliermasterDAO smDao = new SuppliermasterDAO();

            //eimList = eirDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            // eimList = eirDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            smList = smDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            if (smList != null && !smList.isEmpty()) {
                for (Suppliermaster sm : smList) {
                    Integer val = Integer.parseInt(sm.getSmId().toString());//.getCountryId().toString());
                    String str = sm.getSmName();//.getCountryName();
                    if (outstr.length() == 0) {
                        outstr = val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getInsituteaftervalidation() throws Exception {
        PrintWriter out = getResponse().getWriter();
        try {
            //List<Suppliermaster> smList =  smDao.findByImId(Short.parseShort(searchValue));

            List<Institutionmaster> imList = null;
            InstitutionmasterDAO imDao = new InstitutionmasterDAO();

            //eimList = eirDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            // eimList = eirDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            if (imList != null && !imList.isEmpty()) {
                for (Institutionmaster im : imList) {
                    Integer val = Integer.parseInt(im.getImId().toString());
                    String str = im.getImName();
                    if (outstr.length() == 0) {
                        outstr = val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getDepartmentAfterValidation() throws Exception {
        PrintWriter out = getResponse().getWriter();
        try {
            //List<Suppliermaster> smList =  smDao.findByImId(Short.parseShort(searchValue));

            List<Departmentmaster> dmList = null;
            DepartmentmasterDAO dmDao = new DepartmentmasterDAO();

            //eimList = eirDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            // eimList = eirDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
            if (dmList != null && !dmList.isEmpty()) {
                for (Departmentmaster dm : dmList) {
                    Integer val = Integer.parseInt(dm.getDmId().toString());
                    String str = dm.getDmName();
                    if (outstr.length() == 0) {
                        outstr = val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    //getItemsforInsitute
    public String getItemforInsituteList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            ErpmItemMasterDAO eirDao = new ErpmItemMasterDAO();
            try {
                List<ErpmItemMaster> eimList = eirDao.findByImId(Short.parseShort(searchValue));
                if (eimList != null && !eimList.isEmpty()) {
                    for (ErpmItemMaster eim : eimList) {
                        Integer val = eim.getErpmimId();
                        String str = eim.getErpmimItemBriefDesc();
                        if (outstr.length() == 0) {
                            outstr += val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();
        return Action.SUCCESS;
    }

    public String getCurrencyAfterValidation() throws Exception {
        PrintWriter out = getResponse().getWriter();
        List<ErpmGenMaster> egmList = null;
        ErpmGenMasterDao erpmGmDao = new ErpmGenMasterDao();
        try {
            egmList = erpmGmDao.findByErpmGmType(Short.parseShort("6"));
            if (egmList != null && !egmList.isEmpty()) {
                for (ErpmGenMaster egm : egmList) {
                    Integer val = egm.getErpmgmEgmId();
                    String str = egm.getErpmgmEgmDesc();
                    if (outstr.length() == 0) {
                        outstr += val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getWarrantyaftervalidation() throws Exception {
        PrintWriter out = getResponse().getWriter();

        // if (searchValue.length() > 0) {


        List<ErpmGenMaster> egmList = null;
        ErpmGenMasterDao erpmGmDao = new ErpmGenMasterDao();
        try {
            egmList = erpmGmDao.findByErpmGmType(Short.parseShort("9"));
            if (egmList != null && !egmList.isEmpty()) {
                for (ErpmGenMaster egm : egmList) {
                    Integer val = egm.getErpmgmEgmId();
                    String str = egm.getErpmgmEgmDesc();
                    if (outstr.length() == 0) {
                        outstr += val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getsupplierforInsitute() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            SuppliermasterDAO smDao = new SuppliermasterDAO();
            try {
                List<Suppliermaster> smList = smDao.findByImId(Short.parseShort(searchValue));
                if (smList != null && !smList.isEmpty()) {
                    for (Suppliermaster sm : smList) {
                        Integer val = sm.getSmId();
                        String str = sm.getSmName();
                        if (outstr.length() == 0) {
                            outstr += val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();
        return Action.SUCCESS;
    }

    public String getpoterms() throws Exception {
        PrintWriter out = getResponse().getWriter();
        if (searchValue.length() > 0) {
            ErpmGeneralTermsDAO gtermsDao = new ErpmGeneralTermsDAO();
            try {

                ErpmGeneralTerms gterms = gtermsDao.findTermsforInsituteByGenmasterID(Integer.parseInt(searchValue));
                outstr = gterms.getGtTermsDescription();
                if (outstr.isEmpty()) {
                    outstr = "Please Write Your Description here For Your Purchase Order:";
                }
            } catch (Exception e) {
                outstr = "No Terms Unit Defined" + e.getCause();

                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();
        return Action.SUCCESS;
    }

    public String getBudgetAfterValidation() throws Exception {
        PrintWriter out = getResponse().getWriter();
        try {
            List<Budgetheadmaster> bhmList = null;
            BudgetheadmasterDAO bhmDao = new BudgetheadmasterDAO();
            bhmList = bhmDao.findforInsitutetBudgetHeadId(Integer.valueOf(getSession().getAttribute("userid").toString()));
            if (bhmList != null && !bhmList.isEmpty()) {
                for (Budgetheadmaster bhm : bhmList) {
                    Integer val = Integer.parseInt(bhm.getBhmId().toString());
                    String str = bhm.getBhmName();
                    if (outstr.length() == 0) {
                        outstr = val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getForwardedToUserAfterValidation() throws Exception {
        PrintWriter out = getResponse().getWriter();
        try {
            //Budgetheadmaster;ErpmusersDAO userDao = new  ErpmusersDAO();
//BudgetheadmasterDAO;List<Suppliermaster> smList =  smDao.findByImId(Short.parseShort(searchValue));

            List<Erpmusers> UserList = null;
            ErpmusersDAO UsersDao = new ErpmusersDAO();
            UserList = UsersDao.findAll();//.findforInsitutetBudgetHeadId(Integer.valueOf(getSession().getAttribute("userid").toString()));
            if (UserList != null && !UserList.isEmpty()) {
                for (Erpmusers users : UserList) {
                    Integer val = Integer.parseInt(users.getErpmuId().toString());
                    String str = users.getErpmuFullName();
                    if (outstr.length() == 0) {
                        outstr = val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getEmployeeList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {

            List<Employeemaster> emList = null;
            EmployeemasterDAO emDao = new EmployeemasterDAO();
            try {

                emList = emDao.findByImId(Short.parseShort(searchValue));
                if (emList != null && !emList.isEmpty()) {
                    for (Employeemaster em : emList) {
                        Integer val = em.getEmpId();
                        String str = em.getEmpFname() + " " + em.getEmpMname() + " " + em.getEmpLname();

                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getEmployeeEmail() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            EmployeemasterDAO empDao = new EmployeemasterDAO();
            try {
                Employeemaster empm = empDao.findByempId(Integer.parseInt(searchValue));
                outstr = empm.getEmpEmail().toString();//.getErpmgmEgmDesc();
                //outstr1=erpmim.getErpmIndentDetails().getErpmItemRates().getErpmIndentDetails().
                if (outstr.isEmpty()) {
                    outstr = "No Purchase Unit Defined";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }

    public String getAddressForSupplier() throws Exception {
        PrintWriter out = getResponse().getWriter();
        try {

            List<SupplierAddress> saList = null;
            SupplierAddressDAO saDao = new SupplierAddressDAO();

            saList = saDao.findBySupplierId(Integer.parseInt(searchValue));

            if (saList != null && !saList.isEmpty()) {
                for (SupplierAddress sa : saList) {
                    Integer val = Integer.parseInt(sa.getSupAdId().toString());
                    String str = sa.getAdLine1() + ", " + sa.getAdLine2() + ", " + sa.getAdCity() + "?";

                    if (outstr.length() == 0) {
                        outstr = val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String findCurrentWFTStage() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            WorkflowtransactionDAO wftDao = new WorkflowtransactionDAO();
            WorkflowdetailDAO wfdDao = new WorkflowdetailDAO();

            try {
                Integer stage = wftDao.findCurrentWFTStage(Integer.parseInt(searchValue), Integer.parseInt(searchValue2));
                outstr = stage.toString();

                if (wfdDao.findSourceCommittee(stage, Integer.parseInt(searchValue)) == null) {
                    outstr = outstr + "|0,Self";
                } else {
                    outstr = outstr + "|" + wfdDao.findSourceCommittee(stage, Integer.parseInt(searchValue)).getCommitteeId() + ","
                            + wfdDao.findSourceCommittee(stage, Integer.parseInt(searchValue)).getCommitteeName();
                }

                if (wfdDao.findDestinationCommittee(stage, Integer.parseInt(searchValue)) == null) {
                    outstr = outstr + "|0,None Exists";
                } else {
                    outstr = outstr + "|" + wfdDao.findDestinationCommittee(stage, Integer.parseInt(searchValue)).getCommitteeId() + ","
                            + wfdDao.findDestinationCommittee(stage, Integer.parseInt(searchValue)).getCommitteeName();
                }

                outstr = outstr + "|" + wfdDao.findDestinationCommittee(stage, Integer.parseInt(searchValue)).getCommitteeConvener();

                if (outstr.isEmpty()) {
                    outstr = "No Stage";
                }
            } catch (Exception e) {
                e.printStackTrace();
                message = "Here";
                return ERROR;
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }

    public String findStageWorkFlowActions() throws Exception {

        PrintWriter out = getResponse().getWriter();

        List<Workflowactions> wfaList = null;
        WorkflowactionsDAO wfaDao = new WorkflowactionsDAO();
        try {

            wfaList = wfaDao.getStageWorkFlowActions(Integer.parseInt(searchValue), Integer.parseInt(searchValue2));
            if (wfaList != null && !wfaList.isEmpty()) {
                for (Workflowactions index : wfaList) {
                    Integer val = index.getErpmGenMaster().getErpmgmEgmId();
                    String str = index.getErpmGenMaster().getErpmgmEgmDesc();

                    if (outstr.length() == 0) {
                        outstr = val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }

    public String getCommitteesForWorkFlowStage() throws Exception {

        PrintWriter out = getResponse().getWriter();

        List<Workflowactions> wfaList = null;
        WorkflowactionsDAO wfaDao = new WorkflowactionsDAO();
        try {

            wfaList = wfaDao.getStageWorkFlowActions(Integer.parseInt(searchValue), Integer.parseInt(searchValue2));
            if (wfaList != null && !wfaList.isEmpty()) {
                for (Workflowactions index : wfaList) {
                    Integer val = index.getWfaId();
                    String str = index.getErpmGenMaster().getErpmgmEgmDesc();

                    if (outstr.length() == 0) {
                        outstr = val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }

    public String getApprovedIndentList() throws Exception {

        PrintWriter out = getResponse().getWriter();

        List<ErpmIndentMaster> approvedIndentList = null;
        ErpmIndentMasterDAO approvedIndentListDao = new ErpmIndentMasterDAO();
        try {
            approvedIndentList = approvedIndentListDao.findApprovedIndents(searchValue, searchValue2,
                    Integer.parseInt(getSession().getAttribute("userid").toString()),
                    searchValue3);
            if (approvedIndentList != null && !approvedIndentList.isEmpty()) {
                for (ErpmIndentMaster index : approvedIndentList) {
                    Short val = index.getIndtIndentId();
                    String str = index.getIndtTitle();

                    if (outstr.length() == 0) {
                        outstr = val + "|" + str;
                    } else {
                        outstr += "," + val + "|" + str;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }
    /* public String getitemListforPO() throws Exception {
     PrintWriter out = getResponse().getWriter();


     List<ErpmPurchasechallanDetail> eimList = null;
     ErpmPurchaseChallanDetailDAO eirDao = new ErpmPurchaseChallanDetailDAO();
     try {
     //eimList = eirDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
     eimList = eirDao.findAll();//findAll();//findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
     if (eimList != null && !eimList.isEmpty()) {
     for (ErpmPurchasechallanDetail eim : eimList) {
     Integer val = Integer.parseInt(eim.getPcdPcdId().toString());
     String str = eim.getErpmItemMaster().getErpmimItemBriefDesc();
     if (outstr.length() == 0) {
     outstr = val + "|" + str;
     } else {
     outstr += "," + val + "|" + str;
     }
     }
     }
     } catch (Exception e) {
     e.printStackTrace();
     }
     out.print(outstr);
     out.flush();
     out.close();

     return Action.SUCCESS;
     }
     */

    public String getProgramBySubmoduleList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<Erpmprogram> erpmpList = null;
            ErpmprogramDAO erpmpDao = new ErpmprogramDAO();
            try {

                erpmpList = erpmpDao.findByErpmmId(Integer.parseInt(searchValue));
                if (erpmpList != null && !erpmpList.isEmpty()) {
                    for (Erpmprogram erpmpIcc : erpmpList) {
                        Short val = erpmpIcc.getErpmpId();
                        String str = erpmpIcc.getErpmpDisplayName();
                        if (outstr.length() == 0) {
                            outstr += val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getItemListTOS() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<ErpmItemMaster> tosINList = null;
            ErpmItemMasterDAO erpmIMDao = new ErpmItemMasterDAO();
            try {
                tosINList = erpmIMDao.findByerpmItemCategoryMaster(Integer.parseInt(searchValue));
                if (tosINList != null && !tosINList.isEmpty()) {
                    for (ErpmItemMaster erpmIcm : tosINList) {
                        Integer val = erpmIcm.getErpmimId();
                        String str = erpmIcm.getErpmimItemBriefDesc();
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(outstr);
            out.flush();
            out.close();
        }
        return Action.SUCCESS;
    }

    public String getEmployeeListByDmID() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {

            List<Employeemaster> emList = null;
            EmployeemasterDAO emDao = new EmployeemasterDAO();
            try {

                emList = emDao.findByDmId(Integer.parseInt(searchValue));
                if (emList != null && !emList.isEmpty()) {
                    for (Employeemaster em : emList) {
                        Integer val = em.getEmpId();
                        String str = em.getEmpFname() + " " + em.getEmpMname() + " " + em.getEmpLname();

                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getIssueNoByEmpId() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {

            List<ErpmIssueMaster> isuelist = null;
            ErpmIssueMasterDAO isueDAO = new ErpmIssueMasterDAO();
            try {

                isuelist = isueDAO.findByEmpId(Integer.parseInt(searchValue));
                if (isuelist != null && !isuelist.isEmpty()) {
                    for (ErpmIssueMaster im : isuelist) {
                        Integer val = im.getIsmId();
                        String str = im.getIsmIssueNo();

                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getIssueNoByComId() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {

            List<ErpmIssueMaster> isuelist = null;
            ErpmIssueMasterDAO isueDAO = new ErpmIssueMasterDAO();
            try {

                isuelist = isueDAO.findByCompId(Integer.parseInt(searchValue));
                if (isuelist != null && !isuelist.isEmpty()) {
                    for (ErpmIssueMaster im : isuelist) {
                        Integer val = im.getIsmId();
                        String str = im.getIsmIssueNo();

                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getIssueMasterList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            char c = searchValue.charAt(0);
            List<ErpmIssueSerialDetail> issueserialnoList = new ArrayList<ErpmIssueSerialDetail>();
            List<ErpmIssueMaster> issuemasterList = new ArrayList<ErpmIssueMaster>();
            ErpmIssueReturnMasterDAO erpmirmDAO = new ErpmIssueReturnMasterDAO();
            try {
                if (getSession().getAttribute("userid") == null) {
                    //issuemasterList = erpmirmDAO.findIssuemasterlist(c);
                } else {
                    issueserialnoList = erpmirmDAO.findItemserialnoForUser(c, Integer.valueOf(getSession().getAttribute("dmId").toString()));
                }
                if (issueserialnoList != null && !issueserialnoList.isEmpty()) {
                    for (ErpmIssueSerialDetail eisd : issueserialnoList) {
                        Integer val = eisd.getIssdId();
                        String str = eisd.getErpmStockReceived().toString();
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(outstr);
            out.flush();
            out.close();
        }
        return Action.SUCCESS;
    }

    public String getIssueSerialList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            char c = searchValue.charAt(0);

            List<ErpmIssueSerialDetail> issueserialnoList = new ArrayList<ErpmIssueSerialDetail>();
            List<ErpmIssueMaster> issuemasterList = new ArrayList<ErpmIssueMaster>();
            ErpmIssueReturnMasterDAO erpmirmDAO = new ErpmIssueReturnMasterDAO();
            try {
                if (getSession().getAttribute("userid") == null) {
                    //issuemasterList = erpmirmDAO.findIssuemasterlist(c);
                } else {
                    issuemasterList = erpmirmDAO.findIssuemasterlist(c, Integer.valueOf(getSession().getAttribute("dmId").toString()));
                }
                if (issuemasterList != null && !issuemasterList.isEmpty()) {
                    for (ErpmIssueMaster eim : issuemasterList) {
                        Integer val = eim.getIsmId();
                        String str = eim.getIsmIssueNo();
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(outstr);
            out.flush();
            out.close();
        }
        return Action.SUCCESS;
    }

    public String getIssueMasterListfromDmId() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {

            List<ErpmIssueSerialDetail> issueserialnoList = new ArrayList<ErpmIssueSerialDetail>();
            List<ErpmIssueMaster> issuemasterList = new ArrayList<ErpmIssueMaster>();
            ErpmIssueReturnMasterDAO erpmirmDAO = new ErpmIssueReturnMasterDAO();
            try {
                if (getSession().getAttribute("userid") == null) {
                    //issuemasterList = erpmirmDAO.findIssuemasterlist(c);
                } else {
                    issueserialnoList = erpmirmDAO.findItemserialnoForUserfromDmId(Integer.parseInt(searchValue));
                }
                if (issueserialnoList != null && !issueserialnoList.isEmpty()) {
                    for (ErpmIssueSerialDetail eisd : issueserialnoList) {
                        Integer val = eisd.getIssdId();
                        String str = eisd.getErpmStockReceived().toString();
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(outstr);
            out.flush();
            out.close();
        }
        return Action.SUCCESS;
    }

    public String getIssueSerialListfromDmId() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {


            List<ErpmIssueSerialDetail> issueserialnoList = new ArrayList<ErpmIssueSerialDetail>();
            List<ErpmIssueMaster> issuemasterList = new ArrayList<ErpmIssueMaster>();
            ErpmIssueReturnMasterDAO erpmirmDAO = new ErpmIssueReturnMasterDAO();
            try {
                if (getSession().getAttribute("userid") == null) {
                    //issuemasterList = erpmirmDAO.findIssuemasterlist(c);
                } else {
                    issuemasterList = erpmirmDAO.findIssuemasterlistfromDmId(Integer.parseInt(searchValue));
                }
                if (issuemasterList != null && !issuemasterList.isEmpty()) {
                    for (ErpmIssueMaster eim : issuemasterList) {
                        Integer val = eim.getIsmId();
                        String str = eim.getIsmIssueNo();
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(outstr);
            out.flush();
            out.close();
        }
        return Action.SUCCESS;
    }

    public String getIndentItems() throws Exception {

        PrintWriter out = getResponse().getWriter();

        List<ErpmIndentDetail> indentItemList = null;
        ErpmIndentDetailDAO indentItemListDao = new ErpmIndentDetailDAO();
        try {
            indentItemList = indentItemListDao.findByindtIndentId(Short.parseShort(searchValue));
            message = "Here it goes" + indentItemList.get(0).getErpmItemMaster().getErpmimItemBriefDesc();
            outstr = indentItemList.toString();
        } catch (Exception e) {
            out.print(outstr);
            e.printStackTrace();
            return Action.ERROR;
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }

    public String getClause() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            ErpmGeneralTermsDAO clauseDao = new ErpmGeneralTermsDAO();
            ErpmGeneralTerms clause = new ErpmGeneralTerms();
            try {

                clause = clauseDao.findPOtermsforInsituteByGenmasterID(Integer.parseInt(searchValue.toString()), Short.parseShort("1"));
                outstr = clause.getGtTermsDescription();

                if (outstr.isEmpty()) {
                    outstr = "No Purchase Unit Defined";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }

    public String getTaxNarration() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {

            List<ErpmItemRateTaxes> taxes = new ArrayList<ErpmItemRateTaxes>();
            ErpmItemRateTaxesDAO taxesDao = new ErpmItemRateTaxesDAO();

            String taxNames = "";
            taxes = taxesDao.findByirItemRateId(Integer.parseInt(searchValue));
            for (int i = 0; i < taxes.size(); ++i) {
                taxNames = taxNames + taxes.get(i).getErpmGenMaster().getErpmgmEgmDesc() + " ("
                        + taxes.get(i).getIrtTaxPercent() + "% on "
                        + taxes.get(i).getIrtTaxOnValuePercent() + "%";
                if (taxes.get(i).getIrtSurchargePercent() != null) {
                    taxNames = taxNames + "; Surcharge ( " + taxes.get(i).getIrtSurchargePercent() + "%";
                }
                taxNames = taxNames + ") ";
            }
            outstr = taxNames;
        }

        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }

    public String getTaxValue() throws Exception {
        PrintWriter out = getResponse().getWriter();

        try {
            if (searchValue.length() > 0) {

                List<ErpmItemRateTaxes> taxes = new ArrayList<ErpmItemRateTaxes>();
                ErpmItemRateTaxesDAO taxesDao = new ErpmItemRateTaxesDAO();

                BigDecimal taxValue = new BigDecimal(0);
                BigDecimal totalTaxValue = new BigDecimal(0);
                BigDecimal surCharge = new BigDecimal(0);
                BigDecimal percent = new BigDecimal(100);

                BigDecimal unitRate = new BigDecimal(searchValue2);
                BigDecimal qty = new BigDecimal(searchValue3);

                taxes = taxesDao.findByirItemRateId(Integer.parseInt(searchValue));

                for (int i = 0; i < taxes.size(); ++i) {
                    taxValue = unitRate.multiply(qty).multiply(taxes.get(i).getIrtTaxOnValuePercent()).divide(percent).
                            multiply(taxes.get(i).getIrtTaxPercent()).divide(percent);

                    if (taxes.get(i).getIrtSurchargePercent() != null) {
                        surCharge = taxValue.multiply(taxes.get(i).getIrtSurchargePercent()).divide(percent);
                        taxValue = taxValue.add(surCharge);
                    }
                    totalTaxValue = totalTaxValue.add(taxValue);
                }
                outstr = (totalTaxValue.stripTrailingZeros()).setScale(2, RoundingMode.HALF_UP).toString();
            } else {
                outstr = "0.00";
            }
        } catch (Exception e) {
            e.printStackTrace();
            outstr = e.getMessage();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }

    public String getSupplierList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {

            List<Suppliermaster> suppList = null;
            SuppliermasterDAO suppDao = new SuppliermasterDAO();
            try {
                if (getSession().getAttribute("userid") == null) {
                    // suppList = suppDao.findBysimImId(Short.valueOf(searchValue.toString()));
                    suppList = suppDao.findByImId(Short.valueOf(searchValue.toString()));

                } else {
                    suppList = suppDao.findByImId(Short.valueOf(searchValue.toString()));

                }
                if (suppList != null && !suppList.isEmpty()) {
                    for (Suppliermaster supp : suppList) {
                        // Integer val = supp.getSimId();
                        // String str = supp.getSimName();
                        Integer val = supp.getSmId();
                        String str = supp.getSmName();

                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //  outstr = "UserId is: " + getSession().getAttribute("userid").toString();
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String findMaxDistributableQty() throws Exception {
        PrintWriter out = getResponse().getWriter();

        try {
            if (searchValue.length() > 0) {

                ErpmPoDetailsDAO erpmPoDetailsDao = new ErpmPoDetailsDAO();
                BigDecimal qtyOfItems = erpmPoDetailsDao.findQtyOfItemInPO(Integer.parseInt(searchValue), Integer.parseInt(searchValue2));

                ErpmPoLocationsDAO erpmPoLocationDao = new ErpmPoLocationsDAO();
                BigDecimal qtyDistributed;

                if (searchValue3.length() == 0) {
                    qtyDistributed = erpmPoLocationDao.findDistributedQty(Integer.parseInt(searchValue), Integer.parseInt(searchValue2));
                } else {
                    qtyDistributed = erpmPoLocationDao.findDistributedQtyMinusCurrent(Integer.parseInt(searchValue), Integer.parseInt(searchValue2), Integer.parseInt(searchValue3));
                }

                outstr = (qtyOfItems.subtract(qtyDistributed).stripTrailingZeros()).setScale(2, RoundingMode.HALF_UP).toString();
            } else {
                outstr = "0.00";
            }
        } catch (Exception e) {
            e.printStackTrace();
            outstr = e.getMessage();
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;

    }

    public String getEmailAddressForCommittee() throws Exception {
        PrintWriter out = getResponse().getWriter();
        if (searchValue.length() > 0) {

            CommitteemasterDAO cmDao = new CommitteemasterDAO();
            try {

                Committeemaster cm = cmDao.findCommitteeById(Integer.parseInt(searchValue));
                outstr = cm.getCommitteeConvener().toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();
        return Action.SUCCESS;
    }

    public String getEmailAddressForEmployee() throws Exception {
        PrintWriter out = getResponse().getWriter();
        if (searchValue.length() > 0) {


            EmployeemasterDAO empDao = new EmployeemasterDAO();
            try {

                Employeemaster emp = empDao.findByEmp_Id(Integer.parseInt(searchValue));
                outstr = emp.getEmpEmail().toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();
        return Action.SUCCESS;
    }

    public String getEmailAddressForSupplier() throws Exception {
        PrintWriter out = getResponse().getWriter();
        if (searchValue.length() > 0) {


            SupplierAddressDAO supAddDao = new SupplierAddressDAO();
            try {

                SupplierAddress supAdd = supAddDao.findErpmSMId(Integer.parseInt(searchValue));
                outstr = supAdd.getAdEmail().toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();
        return Action.SUCCESS;
    }

    public String getSubCategoryList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<ErpmItemCategoryMaster> erpmIcmList = null;
            ErpmItemCategoryMasterDao erpmIcmDao = new ErpmItemCategoryMasterDao();
            try {
                erpmIcmList = erpmIcmDao.findByerpmItemCategoryMaster(Integer.parseInt(searchValue));
                if (erpmIcmList != null && !erpmIcmList.isEmpty()) {
                    for (ErpmItemCategoryMaster erpmIcm : erpmIcmList) {
                        Integer val = erpmIcm.getErpmicmItemId();
                        String str = erpmIcm.getErpmicmCatDesc();
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(outstr);
            out.flush();
            out.close();
        }
        return Action.SUCCESS;
    }

    public String getParentCategoryList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<ErpmItemCategoryMaster> erpmIcmList = null;
            ErpmItemCategoryMasterDao erpmIcmDao = new ErpmItemCategoryMasterDao();
            try {
                // erpmIcmList = erpmIcmDao.findParentCategoryMaster(Integer.parseInt(searchValue));
                erpmIcmList = erpmIcmDao.findParentCategoryMaster(Short.parseShort(searchValue));
                if (erpmIcmList != null && !erpmIcmList.isEmpty()) {
                    for (ErpmItemCategoryMaster erpmIcm : erpmIcmList) {
                        Integer val = erpmIcm.getErpmicmItemId();
                        String str = erpmIcm.getErpmicmCatDesc();
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(outstr);
            out.flush();
            out.close();
        }
        return Action.SUCCESS;
    }

    public String getPOList() throws Exception {
        PrintWriter out = getResponse().getWriter();
        DateUtilities dt = new DateUtilities();

        if (searchValue.length() > 0) {

            List<ErpmPoMaster> poList = null;
            ErpmPoMasterDAO pomDao = new ErpmPoMasterDAO();
            try {
                poList = pomDao.findBySupplierId(Integer.valueOf(searchValue));
                if (poList != null && !poList.isEmpty()) {
                    for (ErpmPoMaster pom : poList) {
                        String FullPONo;
                        Integer val = pom.getPomPoMasterId();
                        Integer num = pom.getPomPoNo();
                        FullPONo = pom.getDepartmentmaster().getDmShortName() + "/" + dt.convertDateToString(pom.getPomPoDate(), "dd-MM-yyyy").substring(6) + "/" + num;
                        if (outstr.length() == 0) {
                            outstr = val + "|" + FullPONo;
                        } else {
                            outstr += "," + val + "|" + FullPONo;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //  outstr = "UserId is: " + getSession().getAttribute("userid").toString();
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getChallanList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {

            List<ErpmPurchasechallanMaster> challanList = null;
            ErpmPurchaseChallanMasterDAO challanDao = new ErpmPurchaseChallanMasterDAO();
            try {


                challanList = challanDao.findBySupplierId(Integer.valueOf(searchValue));

                if (challanList != null && !challanList.isEmpty()) {
                    for (ErpmPurchasechallanMaster chln : challanList) {
                        Integer val = chln.getPcmPcmId();
                        String str = chln.getPcmChallanNo();

                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //  outstr = "UserId is: " + getSession().getAttribute("userid").toString();
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getProgramListForSubModule() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<Erpmprogram> erpmpList = null;
            ErpmprogramDAO erpmpDao = new ErpmprogramDAO();
            try {
                //erpmpList = erpmpDao.findRemainingProgramsForInstitute(Byte.parseByte(searchValue), Integer.parseInt(InstitutionRole));
                erpmpList = erpmpDao.findByErpmmId(Integer.valueOf(searchValue));
                if (erpmpList != null && !erpmpList.isEmpty()) {
                    for (Erpmprogram erpmpIcc : erpmpList) {
                        Short val = erpmpIcc.getErpmpId();
                        String str = erpmpIcc.getErpmpDisplayName();
                        if (outstr.length() == 0) {
                            outstr += val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getItemName() throws Exception {
        PrintWriter out = getResponse().getWriter();


        if (searchValue.length() > 0) {
            ErpmItemMasterDAO itemMasterDao = new ErpmItemMasterDAO();
            try {
                outstr = itemMasterDao.findByErpmimId(Integer.valueOf(searchValue)).getErpmimItemBriefDesc().toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.print(outstr);
        out.flush();
        out.close();

        return Action.SUCCESS;
    }

    public String getDepreciationMethodandPercentageList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<ErpmItemCategoryMaster> erpmIcmList = null;
            ErpmItemCategoryMasterDao erpmIcmDao = new ErpmItemCategoryMasterDao();
            try {
                erpmIcmList = erpmIcmDao.findByerpmItemCategoryMasterforDepreciationMethod(Integer.parseInt(searchValue));
                if (erpmIcmList != null && !erpmIcmList.isEmpty()) {
                    for (ErpmItemCategoryMaster erpmIcm : erpmIcmList) {
                        Integer val = erpmIcm.getErpmicmItemId();
                        String str = Character.toString(erpmIcm.getErpmicmDepreciationMethod());

                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(outstr);
            out.flush();
            out.close();
        }
        return Action.SUCCESS;
    }

//this is use to getting percantage in manageitem.jsp
    public String getPercentageList() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            List<ErpmItemCategoryMaster> erpmIcmList = null;
            ErpmItemCategoryMasterDao erpmIcmDao = new ErpmItemCategoryMasterDao();
            try {
                //getting list from ermpm item catagory master
                erpmIcmList = erpmIcmDao.findByerpmItemCategoryMasterforDepreciationMethod(Integer.parseInt(searchValue));
                if (erpmIcmList != null && !erpmIcmList.isEmpty()) {
                    for (ErpmItemCategoryMaster erpmIcm : erpmIcmList) {
                        Integer val = erpmIcm.getErpmicmItemId();
                        String str = Integer.toString(erpmIcm.getErpmicmDepreciationPercentage());

                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(outstr);
            out.flush();
            out.close();
        }
        return Action.SUCCESS;
    }

      public String getTenderName() throws Exception {
          message="A :" + searchValue ;
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {

            ErpmTenderMasterDAO erpmTenderMasterDao = new  ErpmTenderMasterDAO();
            try {
                 ErpmTenderMaster erpmTenderMaster = erpmTenderMasterDao.findByTenderMasterId(Integer.parseInt(searchValue));
                outstr = erpmTenderMaster.getTmName().toString();
//outstr = searchValue.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        out.print(outstr);
        out.flush();
        out.close();
        return Action.SUCCESS;
    }

}
