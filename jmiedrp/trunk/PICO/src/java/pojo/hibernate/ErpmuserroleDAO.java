package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;

/**
 *@author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */ 

public class ErpmuserroleDAO {

    public void save(Erpmuserrole erpmur) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmur);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmur != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

     public void update(Erpmuserrole erpmur) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmur);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmur != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(Erpmuserrole erpmur) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmur);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmur != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Erpmuserrole> findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Erpmuserrole> list = session.createQuery("from Erpmuserrole").list();
            return list;
        }
        finally {
            session.close();
            }
        }


    public List<Erpmuserrole> findAllInactiveUsers() {
        Session session = HibernateUtil.getSessionPicoFactory();
        
        try {
            session.beginTransaction();
            List<Erpmuserrole> list = session.createQuery("from Erpmuserrole where erpmurActive='N'").list();
            for(int i=0; i< list.size(); ++i) {
                Hibernate.initialize(list.get(i).getInstitutionmaster());
                Hibernate.initialize(list.get(i).getSubinstitutionmaster());
                Hibernate.initialize(list.get(i).getDepartmentmaster());
                Hibernate.initialize(list.get(i).getInstitutionuserroles());
                Hibernate.initialize(list.get(i).getErpmusers());
            }
            return list;
        }
        finally {
            session.close();
            }
        }

    public List<Erpmuserrole> findByErpmUserId(Integer erpmurId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        
        try {
            session.beginTransaction();
            List<Erpmuserrole> list = session.createQuery("Select u from Erpmuserrole u where u.erpmusers.erpmuId = :erpmurId").setParameter("erpmurId",erpmurId).list();
            return list;
        }
        finally {
            session.close();
            }
        }


     public Erpmuserrole findByErpmUserRole(Integer erpmurId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        
        try {
            session.beginTransaction();
            List<Erpmuserrole> list = session.createQuery("Select u from Erpmuserrole u where u.erpmurId = :erpmurId").setParameter("erpmurId",erpmurId).list();
            Hibernate.initialize(list.get(0).getInstitutionmaster());
            Hibernate.initialize(list.get(0).getSubinstitutionmaster());
            Hibernate.initialize(list.get(0).getDepartmentmaster());
            Hibernate.initialize(list.get(0).getErpmusers());

                       
            return list.get(0);
        }
        finally {
            session.close();
            }
        }

     
     public Erpmuserrole findDefaultUserRole(Integer erpmurId) {
        Session session = HibernateUtil.getSessionPicoFactory();

        try {
            session.beginTransaction();

            List<Erpmuserrole> list = session.createQuery("Select u from Erpmuserrole u where u.erpmusers.erpmuId = :erpmuId and u.erpmurDefault = 'Y'").setParameter("erpmuId",erpmurId).list();

            if (list.isEmpty()) {
                List<Erpmuserrole> list2 = session.createQuery("Select u from Erpmuserrole u where u.erpmusers.erpmuId = :erpmurId").setParameter("erpmurId",erpmurId).list();
                return list2.get(0);
            }
            else
                return list.get(0);
        }
        finally {
            session.close();
            }
        }


    public List<Erpmuserrole> findActiveRolesByErpmUserId(Integer erpmurId) {
        Session session = HibernateUtil.getSessionPicoFactory();

        try {
            session.beginTransaction();
            List<Erpmuserrole> list = session.createQuery("Select u from Erpmuserrole u where u.erpmusers.erpmuId = :erpmurId and erpmurActive = 'Y'").setParameter("erpmurId",erpmurId).list();
            for (int index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmusers());
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
                Hibernate.initialize(list.get(index).getInstitutionuserroles());                
            }
            return list;
        }
        finally {
            session.close();
            }
        }


     public int  ClearDefaultProfile(Integer erpmurId) {

        Session session = HibernateUtil.getSessionPicoFactory();

        try {
            session.beginTransaction();
            int x = session.createQuery("Update Erpmuserrole u set u.erpmurDefault = 0 where u.erpmusers.erpmuId = :erpmurId").setParameter("erpmurId",erpmurId).executeUpdate();
            return x;
        }
        finally {
            session.close();
            }
        }

    public String isUserAdministrator(Integer erpmuId) {

        String SQLQuery =   "Select count(r.erpmurId) from Erpmuserrole r, Institutionuserroles i "
                            + "where r.institutionuserroles.iurId = i.iurId and "
                            + "upper(i.iurName) like '%ADMINISTRATOR%' and "
                            + "r.erpmusers.erpmuId = :erpmuId ";

        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            Integer numberofIndentItems  = Integer.parseInt(session.createQuery(SQLQuery).setParameter("erpmuId",erpmuId).uniqueResult().toString());
            if (numberofIndentItems > 0)
                return "Administrator";
            else
                return "Non-Admin";
        }
        finally {
            session.close();
            }
        }

}
