package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;
import utils.BaseDAO;

/**
 *
 * @author erp01
 */
public class ErpmPoTaxesDao extends BaseDAO{

    public List<ErpmPoTaxes> findByPOMasterID_ItemID(Integer pomPoMasterId , Integer erpmimId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();

            List<ErpmPoTaxes> potax  = session.createQuery("Select u from ErpmPoTaxes u where u.erpmPoDetails.erpmPoMaster.pomPoMasterId = :pomPoMasterId and u.erpmPoDetails.erpmItemMaster.erpmimId = :erpmimId").setParameter("pomPoMasterId", pomPoMasterId ).setParameter("erpmimId", erpmimId).list();
             if (potax.size()>0) {
                 Hibernate.initialize(potax.get(0).getPotTaxName());
                 Hibernate.initialize(potax.get(0).getPotTaxPercent());
                 Hibernate.initialize(potax.get(0).getPotTaxOnValuePercent());
                 Hibernate.initialize(potax.get(0).getPotSurchargePercent());
            }
            return potax;
        }
        finally {
            session.close();
            }
    }
}
