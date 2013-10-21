



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;
import java.math.BigDecimal;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;
//import utils.BaseDAO;

/**
 *
 * @author Tanvir Ahmed, Saeed
 */
public class ErpmIndentDetailDAO { //extends BaseDAO {

    public void save(ErpmIndentDetail erpmindtdet) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmindtdet);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmindtdet != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

     public void update(ErpmIndentDetail erpmindtdet) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmindtdet);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmindtdet != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

     public ErpmIndentDetail  findByindtDetailId(short indtDetailId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmIndentDetail erpmindtdet = (ErpmIndentDetail) session.load(ErpmIndentDetail.class, indtDetailId);
            Hibernate.initialize(erpmindtdet.getErpmIndentMaster()); //.getImName());
            Hibernate.initialize(erpmindtdet.getErpmItemMaster());
            Hibernate.initialize(erpmindtdet.getErpmItemMaster().getErpmGenMaster());
            Hibernate.initialize(erpmindtdet.getErpmItemRate());
            return erpmindtdet;
        } finally {
            session.close();
        }
    }

     public ErpmIndentDetail  findByindtDetailByID(short indtDetailId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmIndentDetail> pod  = session.createQuery("Select u from ErpmIndentDetail u where u.indtDetailId = :indtDetailId").setParameter("indtDetailId",indtDetailId).list();
            Hibernate.initialize(pod.get(0).getErpmItemMaster());
            Hibernate.initialize(pod.get(0).getErpmIndentMaster().getErpmGenMasterByIndtCurrencyId());
            return pod.get(0);
        } finally {
            session.close();
        }
    }

      public List<ErpmIndentDetail> findByindtIndentId(Short indtIndentId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmIndentDetail> indentList  = session.createQuery("Select u from ErpmIndentDetail u where u.erpmIndentMaster.indtIndentId = :indtIndentId").setParameter("indtIndentId", indtIndentId).list();
            for (index=0; index < indentList.size(); ++index) {
                Hibernate.initialize(indentList.get(index).getErpmIndentMaster());
                Hibernate.initialize(indentList.get(index).getErpmItemMaster());
                Hibernate.initialize(indentList.get(index).getErpmItemRate());
                Hibernate.initialize(indentList.get(index).getErpmItemMaster().getErpmGenMaster());
            }

            return indentList;
        }
        finally {
            session.close();
            }
       }

      public List<ErpmIndentDetail>  findByIndt_indt_mst_Indent_Id(Short Indt_indt_mst_Indent_Id) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmIndentDetail> Browselist  = session.createQuery("Select u from ErpmIndentDetail u where u.erpmIndentMaster.indtIndentId = :Indt_indt_mst_Indent_Id").setParameter("Indt_indt_mst_Indent_Id", Indt_indt_mst_Indent_Id).list();
            for (index=0; index < Browselist.size(); ++index) {
                Hibernate.initialize(Browselist.get(index).getErpmIndentMaster());
                Hibernate.initialize(Browselist.get(index).getErpmItemMaster());
                Hibernate.initialize(Browselist.get(index).getErpmItemMaster());
                Hibernate.initialize(Browselist.get(index).getErpmItemMaster().getErpmGenMaster());
            }

            return Browselist;
        }
        finally {
            session.close();
            }
       }

      public Integer CountIndentItemsByItemId(Short indtIndentId, Integer erpmimId) {
        Session session = HibernateUtil.getSession();
        try {
           
	        String Query =   "Select count(u) from ErpmIndentDetail u " +
                        "where u.erpmIndentMaster.indtIndentId = :indtIndentId and " +
                        "u.erpmItemMaster.erpmimId = :erpmimId";
                 session.beginTransaction();
                 Integer countRecords = Integer.parseInt(session.createQuery(Query).
                                                             setParameter("indtIndentId", indtIndentId).
                                                             setParameter("erpmimId", erpmimId).
                                                             uniqueResult().toString());
	        return countRecords;
        }
        finally {
            session.close();
            }
        }

      
      //The method below returs the number of Items in the Given Indent
       public Integer  countIndentItems(Short indtentId) {
        Session session = HibernateUtil.getSession();
        try {

	        String SQL = "Select count(u) from ErpmIndentDetail u where u.erpmIndentMaster.indtIndentId = :indtentId";
                 session.beginTransaction();
                 Integer numberofIndentItems  = Integer.parseInt(session.createQuery(SQL).setParameter("indtentId", indtentId).uniqueResult().toString());
	        return numberofIndentItems;
        }
        finally {
            session.close();
            }
        }

      //The method below returns the Total Estimated Amount Required for Purchase of Items
       public BigDecimal indentValue(Short indtentId) {
        Session session = HibernateUtil.getSession();
        try {

	        String SQL = "Select sum(u.indtApproxcost*u.indtQuantity) from ErpmIndentDetail u where u.erpmIndentMaster.indtIndentId = :indtentId";
                BigDecimal indentAmount;
                session.beginTransaction();
                 indentAmount  = new BigDecimal(session.createQuery(SQL).setParameter("indtentId", indtentId).uniqueResult().toString());
	        return indentAmount;
        }
        finally {
            session.close();
            }
        }

       //The method below prepares a list of Indemgt Items which are not present in the PO
       public List<ErpmIndentDetail> findRemainingIndentItems(Short indtIndentId, Integer pomPoMasterId) throws Exception {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            String SQL = "Select u from ErpmIndentDetail u "
                    + " where u.erpmIndentMaster.indtIndentId = :indtIndentId and "
                    + " u.erpmItemMaster.erpmimId not in "
                    + " (Select v.erpmItemMaster.erpmimId from ErpmPoDetails v where v.erpmPoMaster.pomPoMasterId = :pomPoMasterId "
                    + "  and v.erpmIndentDetail.erpmIndentMaster.indtIndentId = :indtIndentId)";
            session.beginTransaction();
            List<ErpmIndentDetail> indentList = session.createQuery(SQL)
                                                             .setParameter("indtIndentId", indtIndentId)
                                                             .setParameter("pomPoMasterId", pomPoMasterId)
                                                             .list();
            for (index=0; index < indentList.size(); ++index) {
                Hibernate.initialize(indentList.get(index));
                Hibernate.initialize(indentList.get(index).getErpmIndentMaster());
                Hibernate.initialize(indentList.get(index).getErpmItemMaster());
                Hibernate.initialize(indentList.get(index).getErpmItemRate());
                Hibernate.initialize(indentList.get(index).getErpmItemMaster().getErpmGenMaster());
                Hibernate.initialize(indentList.get(index).getErpmItemRate().getErpmGenMasterByIrCurrencyId());
                Hibernate.initialize(indentList.get(index).getErpmItemRate().getSuppliermaster());
            }
        return indentList;
        }
        finally {
            session.close();
            }
    }
}


