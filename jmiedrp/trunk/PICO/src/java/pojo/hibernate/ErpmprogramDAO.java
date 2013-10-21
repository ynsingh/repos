
package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

public class ErpmprogramDAO {
    public void save(Erpmprogram erpmp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmp);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmp != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void update(Erpmprogram erpmp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmp);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmp != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


        public void delete(Erpmprogram erpmp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmp);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmp != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public Erpmprogram findByErpmId(Short erpmId) {
           Session session = HibernateUtil.getSession();
            try {
                session.beginTransaction();
                Erpmprogram erpmp  =  (Erpmprogram) session.load(Erpmprogram.class , erpmId);
                Hibernate.initialize(erpmp.getErpmsubmodule());
                
                return erpmp;
            }
            finally {
                session.close();
            }
}


        public List<Erpmprogram> findByErpmmId(Integer erpmSubModuleId) {
           Session session = HibernateUtil.getSession();
            
            try {
                session.beginTransaction();
                List<Erpmprogram> list = session.createQuery("select u from Erpmprogram u where u.erpmsubmodule.erpmSubModuleId = :erpmSubModuleId").setParameter("erpmSubModuleId", erpmSubModuleId).list();
                return list;
            }
            finally {
                session.close();
            }
        }




    //The following method prepares a list of programs which have not yet been assigned to a given Instititution Role
    public List<Erpmprogram> findRemainingProgramsForInstitute(Byte erpmmId, Integer InstitutionRole) {
            Session session = HibernateUtil.getSession();           
            try {
                session.beginTransaction();
                List<Erpmprogram> list = session.createQuery("select u from Erpmprogram u where u.erpmsubmodule.erpmmodule.erpmmId = :erpmmId "
                                                        +    "and u.erpmpId not in (select v.erpmprogram.erpmpId from Institutionroleprivileges v "
                                                        +    "where v.institutionuserroles.iurId = :InstitutionRole)")
                                                    .setParameter("erpmmId", erpmmId)
                                                    .setParameter("InstitutionRole", InstitutionRole)
                                                    .list();
                return list;
            }
            finally {
                session.close();
            }
        }



    public List<Erpmprogram> findFirstLevelItemsBySubModuleId(Integer erpmSubModuleId, Integer erpmuId) {
            String SQL =    "Select a from Erpmprogram a, Institutionroleprivileges b, Institutionuserroles c, Erpmuserrole d "
                        +   "where  b.erpmprogram.erpmpId = a.erpmpId and "
                        +   "b.institutionuserroles.iurId = c.iurId and "
                        +   "c.iurId = d.institutionuserroles.iurId and "
                        +   "a.erpmsubmodule.erpmSubModuleId = :erpmSubModuleId and "
                        +   "d.erpmusers.erpmuId = :erpmuId and "
                        +   "(d.erpmurDefault = 't' or d.erpmurDefault = 'Y') and "
                        +   "(a.erpmprogram.erpmpId is null or 'None' = "
                        +   "  (Select f.erpmpDisplayName from Erpmprogram f " 
                        +   "   where a.erpmprogram.erpmpId = f.erpmpId) ) "
                        +   "   order by a.erpmpOrder";

            Session session = HibernateUtil.getSession();
            try {
                session.beginTransaction();
                List<Erpmprogram> list = session.createQuery(SQL).
                                                setParameter("erpmSubModuleId", erpmSubModuleId).
                                                setParameter("erpmuId",erpmuId).list();
                return list;
            }
            finally {
                session.close();
            }
        }

    public List<Erpmprogram> findSecondLevelItemsBySubModuleId(Integer erpmSubModuleId, Short erpmpId, Integer erpmuId) {
        String query = "Select a from Erpmprogram a, Institutionroleprivileges b, Institutionuserroles c, Erpmuserrole d "
                        +   "where  b.erpmprogram.erpmpId = a.erpmpId and "
                        +          "b.institutionuserroles.iurId = c.iurId and "
                        +          "c.iurId = d.institutionuserroles.iurId and "
                        +          "a.erpmsubmodule.erpmSubModuleId = :erpmSubModuleId and "
                        +          "a.erpmprogram.erpmpId = :erpmpId and "
                        +          "d.erpmusers.erpmuId = :erpmuId and "
                        +          "(d.erpmurDefault = 't' or d.erpmurDefault = 'Y') "
                        +          "   order by a.erpmpOrder";

        //older query = "select u from Erpmprogram u "
        //+ "where u.erpmsubmodule.erpmSubModuleId = :erpmSubModuleId and u.erpmprogram.erpmpId = :erpmpId order by erpmpOrder"
        
        Session session = HibernateUtil.getSession();
            
            try {
                session.beginTransaction();
                List<Erpmprogram> list = session.createQuery(query).setParameter("erpmSubModuleId", erpmSubModuleId)
                                                                   .setParameter("erpmpId", erpmpId)
                                                                   .setParameter("erpmuId",erpmuId)
                                                                   .list();
                return list;
            }
            finally {
                session.close();
            }
        }


    
    public List<Erpmprogram> findItemsBySubModuleId(Integer erpmSubModuleId) {
        Session session = HibernateUtil.getSession();
            
            try {
                session.beginTransaction();
                List<Erpmprogram> list = session.createQuery("select u from Erpmprogram u where u.erpmsubmodule.erpmSubModuleId = :erpmSubModuleId ").
                                                 setParameter("erpmSubModuleId", erpmSubModuleId).list();
                return list;
            }
            finally {
                session.close();
            }
    }

 public List<Erpmprogram> findAll() {
        Session session = HibernateUtil.getSession();
            
            try {
                session.beginTransaction();
                List<Erpmprogram> list = session.createQuery("from Erpmprogram").list();
                for (int index=0; index < list.size(); ++index)
                        Hibernate.initialize(list.get(index).getErpmsubmodule());


                return list;
            }
            finally {
                session.close();
            }
    }
}
