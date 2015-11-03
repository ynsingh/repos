/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

/**
 *
 * @author erp03
 */
public class ErpmIssueReturnDetailDAO {

    public void save(ErpmIssueReturnDetail erpmird) {
        //Session session = HibernateUtil.getSession();
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmird);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmird != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmIssueReturnDetail erpmird) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmird);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmird != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmIssueReturnDetail erpmird) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmird);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmird != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

//    public List<ErpmIssueReturnDetail> findListByirmId(Integer irmId) {
   //method to get list of ErpmIssueReturnDetail with formatted serial no    
     public List<ErpmIssueReturnDetail> findListByirmIdwith_editedserialno(Integer irmId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<ErpmIssueReturnDetail> list = session.createQuery("Select u from ErpmIssueReturnDetail u where u.erpmIssueReturnMaster.irmId = :irmId").setParameter("irmId", irmId).list();
            for (int index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmStockReceived());
                Hibernate.initialize(list.get(index).getErpmIssueMaster());
                Hibernate.initialize(list.get(index).getErpmItemMaster());
                Hibernate.initialize(list.get(index).getErpmIssueReturnMaster().getInstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmIssueReturnMaster().getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmIssueReturnMaster().getDepartmentmaster());


            }
             //for generating serial no from ErpmIssueReturnDetail Table
             
             //generating serial no for list stock receive
              for (int l = 0; l < list.size(); l++) {
              try{
                        Integer ItemIdIength = list.get(l).getErpmItemMaster().getErpmimId().toString().length();
                        String itemSubString = "";
                  //add zeroes according itemlength in to make string of 5 numerical digit

                       if (ItemIdIength <= 9) {
                            itemSubString = "0000";
                      } else if (ItemIdIength > 9 && ItemIdIength <= 99) {
                            itemSubString = "000";
                     } else if (ItemIdIength > 99 && ItemIdIength <= 999) {
                            itemSubString = "00";
                     } else if (ItemIdIength > 999 && ItemIdIength <= 9999) {
                            itemSubString = "0";
                     }
 /* to add department subins,intitute item id in serial no*/
                     itemSubString = itemSubString +  list.get(l).getErpmItemMaster().getErpmimId().toString();
                     String serialNoFull = list.get(l).getErpmIssueReturnMaster().getInstitutionmaster().getImShortName() + "/";
                     serialNoFull = serialNoFull + list.get(l).getErpmIssueReturnMaster().getSubinstitutionmaster().getSimShortName() + "/";
                     serialNoFull = serialNoFull + list.get(l).getErpmIssueReturnMaster().getDepartmentmaster().getDmShortName() + "/";
                     serialNoFull = serialNoFull + itemSubString + "/";
                     //set this serialNoFull var to StStockSerialNo in this list
                     serialNoFull = serialNoFull + list.get(l).getErpmStockReceived().getStStockSerialNo();
                     list.get(l).getErpmStockReceived().setStStockSerialNo(serialNoFull);/**/
         }catch(Exception e){//this catch execute when stock serialno is null
                         //   message = "Exception in ReturnIssuedItemsAction save method-> " + e.getMessage() + " Reported Cause is: " + e.getCause();

            }
              }

                                                           
            return list;
        } finally {
            session.close();
        }
    }

  public List<ErpmIssueReturnDetail> findListByirmId(Integer irmId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<ErpmIssueReturnDetail> list = session.createQuery("Select u from ErpmIssueReturnDetail u where u.erpmIssueReturnMaster.irmId = :irmId").setParameter("irmId", irmId).list();
            for (int index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmStockReceived());
                Hibernate.initialize(list.get(index).getErpmIssueMaster());
                Hibernate.initialize(list.get(index).getErpmItemMaster());
                Hibernate.initialize(list.get(index).getErpmIssueReturnMaster().getInstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmIssueReturnMaster().getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmIssueReturnMaster().getDepartmentmaster());
            }

            return list;
        } finally {
            session.close();
        }
    }
}
