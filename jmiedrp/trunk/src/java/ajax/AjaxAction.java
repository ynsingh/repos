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
import pojo.hibernate.ErpmGeneralTerms;
import pojo.hibernate.ErpmGeneralTermsDAO;
import pojo.hibernate.Suppliermaster;
import pojo.hibernate.SuppliermasterDAO;

import utils.DevelopmentSupport;
import com.opensymphony.xwork2.Action;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;
import java.util.*;
import java.util.Locale;
import java.text.*;
//import sun.util.resources.LocaleData;
//import sun.util.resources.LocaleNamesBundle;

public class AjaxAction extends DevelopmentSupport   {

    private String outstr = "";
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
               // simList = simDao.findBysimImId(Short.parseShort(searchValue));
           simList=simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()),Short.parseShort(searchValue));
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
               // dmList = dmDao.findBydmSimId(Integer.parseInt(searchValue));
               //  dmList = dmDao.findDepartmentForUser(Integer.valueOf("326"), Integer.valueOf("1"));
            dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));;
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



public void setLanguage() throws Exception {
       PrintWriter out = getResponse().getWriter();

        outstr ="hellooo"+searchValue;

       try {

         if(searchValue=="France"){

                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("global_zh_CN", locale);
             //    getSession().setAttribute("com.opensymphony.xwork2.LocaleProvider", Locale.FRANCE);
      //LocaleAction l=new LocaleAction();
       // l.France();
       //com.opensymphony.xwork2.LocaleProvider
         }
                    } catch (Exception e) {
              e.printStackTrace();
          }





          out.print(outstr);
          out.flush();
          out.close();

        //return Action.SUCCESS;
    }

    public String getUOP() throws Exception {
        PrintWriter out = getResponse().getWriter();

        if (searchValue.length() > 0) {
            ErpmItemMasterDAO UOPDao = new ErpmItemMasterDAO();
            try {
                ErpmItemMaster erpmim =  UOPDao.findByErpmimId(Integer.parseInt(searchValue));
                outstr = erpmim.getErpmGenMaster().getErpmgmEgmDesc();
                 if (outstr.isEmpty())
                outstr = "No Purchase Unit Defined";
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
                //Budgetheadmaster bhm=new Budgetheadmaster();
                DepartmentalBudgetAllocation dba =  dbaDao.findForDepartmentBybhmId(Short.parseShort(searchValue), Integer.parseInt(searchValue2));
                outstr = dba.getDbaAmount().toString();
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
    public String getitemList() throws Exception {
       PrintWriter out = getResponse().getWriter();


            List<ErpmItemMaster> eimList = null;
            ErpmItemMasterDAO eirDao = new ErpmItemMasterDAO();
            try {
                eimList = eirDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
                if (eimList != null && !eimList.isEmpty()) {
                    for (ErpmItemMaster eim : eimList) {
                        Integer val = Integer.parseInt(eim.getErpmimId().toString());//.getCountryId().toString());
                        String str = eim.getErpmimItemBriefDesc();//.getCountryName();
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


       public String getItemsforInsitute() throws Exception {
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
}