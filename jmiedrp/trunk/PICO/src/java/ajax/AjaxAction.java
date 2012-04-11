package ajax;

import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import java.util.Locale;
import java.util.ResourceBundle;
import com.opensymphony.xwork2.ActionContext;

import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Institutionuserroles;
import pojo.hibernate.InstitutionuserroleDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Erpmusers;
import pojo.hibernate.ErpmItemCategoryMaster;
import pojo.hibernate.ErpmItemCategoryMasterDao;
import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.ErpmCapitalCategory;
import pojo.hibernate.ErpmCapitalCategoryDao;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.Statemaster;
import pojo.hibernate.StatemasterDAO;
import pojo.hibernate.UserMessage;
import pojo.hibernate.UserMessageDAO;
import pojo.hibernate.ErpmusersDAO;
import pojo.hibernate.DepartmentalBudgetAllocation;
import pojo.hibernate.DepartmentalBudgetAllocationDAO;
import pojo.hibernate.Genericuserroles;
import pojo.hibernate.GenericuserrolesDAO;
import pojo.hibernate.ErpmItemMasterDAO;
import pojo.hibernate.Erpmprogram;
import pojo.hibernate.ErpmprogramDAO;
import pojo.hibernate.Countrymaster;
import pojo.hibernate.CountrymasterDAO;
import pojo.hibernate.ErpmItemRate;
import pojo.hibernate.ErpmItemRateDAO;
import pojo.hibernate.ErpmItemRateDetails;
import pojo.hibernate.ErpmItemRateDetailsDAO;
import pojo.hibernate.ErpmGeneralTerms;
import pojo.hibernate.ErpmGeneralTermsDAO;
import pojo.hibernate.Suppliermaster;
import pojo.hibernate.SuppliermasterDAO;
import pojo.hibernate.SupplierAddress;
import pojo.hibernate.SupplierAddressDAO;
import pojo.hibernate.Budgetheadmaster;
import pojo.hibernate.BudgetheadmasterDAO;
import pojo.hibernate.Employeemaster;
import pojo.hibernate.EmployeemasterDAO;

import utils.DevelopmentSupport;
import com.opensymphony.xwork2.Action;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;
import java.util.*;
import java.util.Locale;
import java.text.*;
import pojo.hibernate.ErpmPurchaseChallanDetailDAO;
import pojo.hibernate.ErpmPurchaseChallanMasterDAO;
import pojo.hibernate.ErpmPurchasechallanDetail;
import pojo.hibernate.ErpmPurchasechallanMaster;
//import sun.util.resources.LocaleData;
//import sun.util.resources.LocaleNamesBundle;

public class AjaxAction extends DevelopmentSupport   {

    private String outstr = "";
     private String outstr1 = "";
    private String searchValue;
    private String searchValue2;
    private String InstitutionRole;

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

    public String getInstitutionRole() {
        return InstitutionRole;
    }

    public void setInstitutionRole(String InstitutionRole) {
        this.InstitutionRole = InstitutionRole;
    }


    public String setStatus() throws Exception {
       PrintWriter out = getResponse().getWriter();
       //searchValue contains user name; searchValue2 contains user's date of birth
            //outstr =searchValue+searchValue+"hello";
         //outstr ="hellooo"+searchValue+searchValue2;
     try {
       ErpmGenMaster erpmGmId = new ErpmGenMaster();
    ErpmGenMasterDao erpmGmDao = new ErpmGenMasterDao();
      erpmGmId=erpmGmDao.findByErpmGmDesc(searchValue);
    outstr= erpmGmId.getErpmgmEgmDesc()+"hellooo"+searchValue+searchValue2;
      UserMessage user = new  UserMessage();
      UserMessageDAO userDao = new  UserMessageDAO();
    user=userDao.findByumId(Integer.parseInt(searchValue2));
      outstr=outstr+user.getUmId();
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

                simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
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
                ErpmusersDAO userDao = new  ErpmusersDAO();
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
                GenericuserrolesDAO gurDao = new  GenericuserrolesDAO();
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
                ErpmItemMaster erpmim =  UOPDao.findByErpmimId(Integer.parseInt(searchValue));
                outstr = erpmim.getErpmGenMaster().getErpmgmEgmDesc();
                 //outstr1=erpmim.getErpmIndentDetails().getErpmItemRates().getErpmIndentDetails().
                if (outstr.isEmpty())
                outstr = "No Purchase Unit Defined";
                }
            catch(Exception
            e)
            {
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
        DateFormat formatter ;
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date Today = Calendar.getInstance().getTime();

        if (searchValue.length() > 0) {
            ErpmItemRateDAO itemRateDao = new ErpmItemRateDAO();
            List<ErpmItemRate> itemRateList = null;

            try {              

                itemRateList = itemRateDao.findItemApprovedItemsForToday(Integer.parseInt(searchValue), Today, Integer.parseInt(searchValue2));

                //outstr = "Query is =" + itemRateList1;
                if (itemRateList != null && !itemRateList.isEmpty()) {
                    for (ErpmItemRate itemRate : itemRateList) {
                        Integer val = itemRate.getIrItemRateId();
                        
                        String FrmDate = formatter.format(itemRate.getIrdWefDate());
                        String ToDate = formatter.format(itemRate.getIrdWetDate());
                        
                        String str = String.format("%-25s %20s %20s %10d", itemRate.getSuppliermaster().getSmName() ,
                                                                             FrmDate,
                                                                             ToDate,
                                                                             (itemRate.getIrdRate().intValue()));
                        if (outstr.length() == 0) {
                            outstr = val + "|" + str;
                        } else {
                            outstr += "," + val + "|" + str;
                        }
                    }
                }
                else
                    outstr = "No rate is approved for the item. Please add on your own, if required" ;
                 
                 
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
        DateFormat formatter ;
        formatter = new SimpleDateFormat("dd-MMM-yyyy");

        if (searchValue.length() > 0) {
            ErpmItemRateDAO itemRateDao = new ErpmItemRateDAO();
            try {
                ErpmItemRate erpmItemRate =  itemRateDao.findByirItemRateId(Integer.parseInt(searchValue));
                String FrmDate = formatter.format(erpmItemRate.getIrdWefDate());
                String ToDate = formatter.format(erpmItemRate.getIrdWetDate());
                outstr = erpmItemRate.getIrdRate().toString() + "|" +
                         FrmDate + "|" + ToDate + "|" +
                         erpmItemRate.getErpmGenMasterByIrCurrencyId().getErpmgmEgmDesc();
                 
                if (outstr.isEmpty())
                    outstr = "No Rate Defined";
                }
            catch(Exception e)
            {
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
                ErpmItemRate erpmItemRate =  itemrateDao.findByirItemRateId(Integer.parseInt(searchValue));
                 outstr = erpmItemRate.getIrdRate().toString() ;//+ "|" +
                      if (outstr.isEmpty())
                    outstr = "No Rate Defined";
                }
            catch(Exception e)
            {
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

        outstr ="hellooo"+searchValue;

       try {

         if(searchValue=="France"){

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

    
   

    public String getAllocatedAmount() throws Exception {
        PrintWriter out = getResponse().getWriter();
        if (searchValue.length() > 0) {
            DepartmentalBudgetAllocationDAO dbaDao = new DepartmentalBudgetAllocationDAO();
            try {
               /* if(dba.getDbaAmount().toString()==null) {
                    //int i=0;
                outstr =(Integer.valueOf(0).toString());
                out.print(outstr);
                out.flush();
                out.close();

                }
                else {*/
                //Budgetheadmaster bhm=new Budgetheadmaster();
                DepartmentalBudgetAllocation dba1 =  dbaDao.findForDepartmentBybhmId(Short.parseShort(searchValue), Integer.parseInt(searchValue2));
                outstr = dba1.getDbaAmount().toString();
               //  if (outstr.isEmpty());
                 //outstr = (Integer.valueOf(0).toString());


                //outstr = (Integer.valueOf(0).toString());
                }               
            catch (Exception e) {
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

                  ErpmGeneralTerms gterms =  gtermsDao.findTermsforInsituteByGenmasterID(Integer.parseInt(searchValue));
                  // ErpmGeneralTerms gterms =  gtermsDao.findTestPOtermsforInsituteByGenmasterID(Integer.parseInt(searchValue));
                      outstr = gterms.getGtTermsDescription();//getErpmGenMaster().getErpmgmEgmDesc();//getGtTermsDescription();
                   if (outstr.isEmpty())
                outstr = "Please Write Your Description here For Your Purchase Order:";
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
                //eimList = eirDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
                eimList = eirDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
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

   public String getsupplieraftervalidation() throws Exception {
       PrintWriter out = getResponse().getWriter();
 //SuppliermasterDAO smDao = new SuppliermasterDAO();
            try {
                //List<Suppliermaster> smList =  smDao.findByImId(Short.parseShort(searchValue));

            List<Suppliermaster> smList = null;
            SuppliermasterDAO smDao = new SuppliermasterDAO();
            
                //eimList = eirDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
               // eimList = eirDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
                smList =  smDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
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
                imList =  imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
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
                dmList =  dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
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
                List<ErpmItemMaster> eimList =  eirDao.findByImId(Short.parseShort(searchValue));
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
             }catch (Exception e) {
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
               egmList=  erpmGmDao.findByErpmGmType(Short.parseShort("6"));
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
                 egmList=  erpmGmDao.findByErpmGmType(Short.parseShort("9"));
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
                List<Suppliermaster> smList =  smDao.findByImId(Short.parseShort(searchValue));
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
             }catch (Exception e) {
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

                  ErpmGeneralTerms gterms =  gtermsDao.findTermsforInsituteByGenmasterID(Integer.parseInt(searchValue));
                  // ErpmGeneralTerms gterms =  gtermsDao.findTestPOtermsforInsituteByGenmasterID(Integer.parseInt(searchValue));
                      outstr = gterms.getGtTermsDescription();//getErpmGenMaster().getErpmgmEgmDesc();//getGtTermsDescription();
                   if (outstr.isEmpty())
                outstr = "Please Write Your Description here For Your Purchase Order:";
            } catch (Exception e) {
                outstr = "No Terms Unit Defined" +e.getCause();

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
            bhmList =  bhmDao.findforInsitutetBudgetHeadId(Integer.valueOf(getSession().getAttribute("userid").toString()));
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
            UserList =  UsersDao.findAll();//.findforInsitutetBudgetHeadId(Integer.valueOf(getSession().getAttribute("userid").toString()));
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
                Employeemaster empm =  empDao.findByempId(Integer.parseInt(searchValue));
                outstr = empm.getEmpEmail().toString();//.getErpmgmEgmDesc();
                 //outstr1=erpmim.getErpmIndentDetails().getErpmItemRates().getErpmIndentDetails().
                if (outstr.isEmpty())
                outstr = "No Purchase Unit Defined";
                }
            catch(Exception
            e)
            {
                e.printStackTrace();
                }
             }
            out.print(outstr);
            out.flush();
            out.close();

        return Action.SUCCESS;

    }

   public String getaddressforSupplier() throws Exception {
       PrintWriter out = getResponse().getWriter();
            try {

            List<SupplierAddress> saList = null;
            SupplierAddressDAO saDao = new SupplierAddressDAO();

                saList = saDao.findBySupplierId(Integer.parseInt(searchValue));
                if (saList != null && !saList.isEmpty()) {
                    for (SupplierAddress sa : saList) {
                        Integer val = Integer.parseInt(sa.getSupAdId().toString());
                        String str = sa.getAdLine1(); //+", "+ sa.getAdLine2()+", "+ sa.getAdCity();

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

}
    


