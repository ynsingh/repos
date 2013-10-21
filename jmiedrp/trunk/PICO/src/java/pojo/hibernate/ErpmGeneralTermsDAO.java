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
 * @author Tanvir Ahmed
 */
public class ErpmGeneralTermsDAO {

    public void save(ErpmGeneralTerms Gterms) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(Gterms);
            tx.commit();
        } catch (RuntimeException re) {
            if (Gterms != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmGeneralTerms Gterms) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(Gterms);
            tx.commit();
        } catch (RuntimeException re) {
            if (Gterms != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmGeneralTerms Gterms) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(Gterms);
            tx.commit();
        } catch (RuntimeException re) {
            if (Gterms != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();

        }
    }

    public ErpmGeneralTerms findPOtermsforInsituteByGenmasterID(Integer erpmgmEgmId, Short imId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmGeneralTerms gterms = (ErpmGeneralTerms) session.createQuery("Select u from ErpmGeneralTerms u where u.erpmGenMaster.erpmgmEgmId = :erpmgmEgmId and u.institutionmaster.imId = :imId").setParameter("erpmgmEgmId", erpmgmEgmId).setParameter("imId", imId).uniqueResult();
            Hibernate.initialize(gterms);

            return gterms;
        } finally {
            session.close();
        }
    }

//    public ErpmGeneralTerms findTestPOtermsforInsituteByGenmasterID(Integer gtGtid) {
//        Session session = HibernateUtil.getSession();
//        try {
//            session.beginTransaction();
//            List<ErpmGeneralTerms> gterms = session.createQuery("Select u from ErpmGeneralTerms u where u.gtGtid = :gtGtid").setParameter("gtGtid", gtGtid).list();
//            Hibernate.initialize(gterms); //.getImName());
//
//            return gterms.get(0);
//        } finally {
//            session.close();
//        }
//    }
    public ErpmGeneralTerms findTermsforInsituteByGenmasterID(Integer erpmgmEgmId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmGeneralTerms> gterms = session.createQuery("Select u from ErpmGeneralTerms u where u.erpmGenMaster.erpmgmEgmId = :erpmgmEgmId").setParameter("erpmgmEgmId", erpmgmEgmId).list();
            for (index = 0; index < gterms.size(); ++index) {
                Hibernate.initialize(gterms.get(index).getErpmGenMaster());


            }
            return gterms.get(0);
        } finally {
            session.close();
        }
    }

    public List<ErpmGeneralTerms> findAll() {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmGeneralTerms> list = session.createQuery("select u from ErpmGeneralTerms u").list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmGenMaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<ErpmGeneralTerms> findByErpmGmType(short erpmgmEgmType) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmGeneralTerms> erpmgmlist = session.createQuery("Select u from ErpmGeneralTerms u where u.erpmGenMaster.erpmgcGenType = :erpmgmEgmType").setParameter("erpmgmEgmType", erpmgmEgmType).list();
            for (index = 0; index < erpmgmlist.size(); ++index) {
                Hibernate.initialize(erpmgmlist.get(index).getInstitutionmaster());
                Hibernate.initialize(erpmgmlist.get(index).getErpmGenMaster());
            }
            return erpmgmlist;
        } finally {
            session.close();
        }
    }

    public List<ErpmGeneralTerms> findPOtermsforInsitute(Short imId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmGeneralTerms> gterms = session.createQuery("Select u from ErpmGeneralTerms u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
            for (index = 0; index < gterms.size(); ++index) {

                Hibernate.initialize(gterms.get(index).getInstitutionmaster());
            }
            return gterms;
        } finally {
            session.close();
        }
    }

    public List<ErpmGeneralTerms> findByErpmGmTypebyInsitute(Short imId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmGeneralTerms> erpmgmlist = session.createQuery("Select u from ErpmGeneralTerms u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
            for (index = 0; index < erpmgmlist.size(); ++index) {
                Hibernate.initialize(erpmgmlist.get(index).getInstitutionmaster());
                Hibernate.initialize(erpmgmlist.get(index).getErpmGenMaster());
            }
            return erpmgmlist;
        } finally {
            session.close();
        }
    }

    public ErpmGeneralTerms findBygtGtid(Integer gtGtid) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmGeneralTerms GTerms = (ErpmGeneralTerms) session.load(ErpmGeneralTerms.class, gtGtid);
            Hibernate.initialize(GTerms); //.getImName());

            return GTerms;
        } finally {
            session.close();
        }
    }
}
