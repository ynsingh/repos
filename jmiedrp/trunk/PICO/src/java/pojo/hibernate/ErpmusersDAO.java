/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

/**
 * @author kazim
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ErpmusersDAO  {


		//SessionFactory sessionFactory1 = new  Configuration().configure("login.cfg.xml").buildSessionFactory();
        	//SessionFactory sessionFactory2 = new Configuration().configure("pico.cfg.xml").buildSessionFactory();


    
    public void save(Erpmusers erpmuser) {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmuser);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmuser != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    } 

    public void update(Erpmusers erpmuser) {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmuser);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmuser != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


    public void delete(Erpmusers erpmuser) {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session=sessionp.openSession();
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmuser);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmuser != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


    public List<Erpmusers> findAll() {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Erpmusers> list = session.createQuery("select u from Erpmusers u").list();
            return list;
        }
        catch (RuntimeException re) {
                throw re;
        }
        finally {
            session.close();
            }
        }

    public List<Erpmusers> findGUser() {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Erpmusers> list = session.createQuery("from Erpmusers where erpmuId >'2'").list();
            return list;
        }
        catch (RuntimeException re) {
                throw re;
        }
        finally {
            session.close();
            }
        }

    public Erpmusers findByUserName(String ERPMU_Name) {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Erpmusers> erpmuserList  =  session.createQuery("select u from Erpmusers u where u.erpmuName=:username ").
                                                      setParameter("username",ERPMU_Name).list();
            Erpmusers erpmuser = erpmuserList.get(0);
            return erpmuser;
        }
        catch (RuntimeException re) {
                throw re;
        }
        finally {
            session.close();
            }
        }
        


     public Erpmusers findByUserId(Integer erpmuId) {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
            Session session = HibernateUtil.getSessionPicoFactory();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Erpmusers> erpmuserList  =  session.createQuery("select u from Erpmusers u where u.erpmuId=:erpmuId").
                                                      setParameter("erpmuId",erpmuId).list();
                Erpmusers erpmuser = erpmuserList.get(0);

                return erpmuser;
            }
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
            }
     }

     
     public List<Institutionmaster> findForUser(Integer erpmuId)    {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
            Session session = HibernateUtil.getSessionPicoFactory();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Institutionmaster> imList = session.createQuery("select distinct(u) from Institutionmaster u, Erpmuserrole r where r.institutionmaster.imId = u.imId and r.erpmusers.erpmuId = :erpmuId").setParameter("erpmuId",erpmuId).list();

                return imList;
            }
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
            }
     }


      public List <Erpmusers> findByforwarededUserId(Integer erpmuId) {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
            Session session = HibernateUtil.getSessionPicoFactory();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Erpmusers> erpmuserList  =  session.createQuery("select u from Erpmusers u where u.erpmuId=:erpmuId ").
                                                         setParameter("erpmuId",erpmuId).list();

                return erpmuserList;
            }
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
            }
     }
//List<Erpmusers>

      public List<Erpmusers> RetrieveUser(String ERPMU_Name, String ERPMU_Password)    {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
            Session session = HibernateUtil.getSessionPicoFactory();
            try {
                session.beginTransaction();
			  List<Erpmusers> list  = session.createQuery("select u from Erpmusers u, Erpmuserrole r where u.erpmuId = r.erpmusers.erpmuId and r.erpmurActive = 'Y' and u.erpmuName=:username and u.erpmuPassword=:password ").
                                               setParameter("username",ERPMU_Name).setParameter("password",ERPMU_Password).list();

                return list;
            }
            finally {
                session.close();
            }
     }

 /*   public String RetrieveSecretQuestion(String ERPMU_Name, String ERPMU_DOB)    {
            Session session = HibernateUtil.getSession();
            try {
                session.beginTransaction();
                List<Erpmusers> list  = session.createQuery("select u from Erpmusers u where u.erpmuName=:username and u.erpmuDob =:userdob").
                                setString("username",ERPMU_Name).setString("userdob", ERPMU_DOB).list();

                return list.get(0).getErpmuSecretQuestion();
            }
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
            }
     }

*/
    public Erpmusers FindByUserNameSecretAnswer(String ERPMU_Name, String Secret_Answer) {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
            Session session = HibernateUtil.getSessionPicoFactory();            
            try {
                session.beginTransaction();
                List<Erpmusers> list  = session.createQuery("select u from Erpmusers u where u.erpmuName=:username and u.erpmuSecretAnswer =:useranswer").
                                setString("username",ERPMU_Name).setString("useranswer", Secret_Answer).list();

                if (list.size() > 0)
                    return list.get(0);
                else
                    return null;
            }
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
            }
     }
        
  public Erpmusers findByUserFullNames(String ERPMU_FullName) {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
            Session session = HibernateUtil.getSessionPicoFactory();
            try {
                session.beginTransaction();
                List <Erpmusers> erpmuserList  =  session.createQuery("select u from Erpmusers u where u.erpmuFullName=:ERPMU_FullName ").
                                                      setParameter("ERPMU_FullName",ERPMU_FullName).list();
                return erpmuserList.get(0);
            }
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
            }
     }
      
      

    public Erpmusers findByUser_Names(String erpmuName) {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
            Session session = HibernateUtil.getSessionPicoFactory();
            try {
                session.beginTransaction();
                List <Erpmusers> erpmuserList  =  session.createQuery("select u from Erpmusers u where u.erpmuName=:erpmuName ").
                                                      setParameter("erpmuName",erpmuName).list();

		if(erpmuserList.size()>0)
                return erpmuserList.get(0);
		else
                return null;
            }
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
            }
     }
            

     public List<Erpmusers> findUserCollegues(Integer erpmuId) { 
       //  String SQL =   "Select u.erpmusers from Erpmuserrole u where u.institutionmaster.imId in "
           String SQL =   "Select distinct(u.erpmusers) from Erpmuserrole u where u.institutionmaster.imId in "         
                        + " (Select v.institutionmaster.imId from Erpmuserrole v where v.erpmusers.erpmuId = :erpmuId)";

	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session=sessionp.openSession();
         Session session = HibernateUtil.getSessionPicoFactory();
            try {
                session.beginTransaction();
                List<Erpmusers> erpmuList = session.createQuery(SQL).setParameter("erpmuId",erpmuId).list();
                return erpmuList;
            }
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
            }
     }
    public List<Erpmusers> findByErpmUserName(String erpmuName) {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
         Session session = HibernateUtil.getSessionPicoFactory();
            try {
        session.beginTransaction();
        List <Erpmusers> erpmuserList  =  session.createQuery("select u from Erpmusers u where u.erpmuName=:erpmuName ").
                                                      setParameter("erpmuName",erpmuName).list();
        return erpmuserList;
            }
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
        }
    }

 public List<Erpmusers> findUserNamebyimid(Integer erpmuId,Short imId) {
	//SessionFactory sessionp = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
        //Session session = sessionp.openSession();
         Session session = HibernateUtil.getSessionPicoFactory();
            try {
        session.beginTransaction();
        List<Erpmusers> erpmuserList  =  session.createQuery("select distinct(u) from Erpmusers u,Erpmuserrole v,Institutionmaster r where v.erpmusers.erpmuId =:erpmuId and v.institutionmaster.imId=:imId").
                                            setParameter("erpmuId",erpmuId).setParameter("imId",imId).list();

        Hibernate.initialize(erpmuserList);
        return erpmuserList;
            }
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
            }

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package pojo.hibernate;
//
///**
// *
// * @author kazim
// */
//import utils.BaseDAO;
//import java.util.List;
//import java.util.Date;
//
//
//
//public class ErpmusersDAO  extends BaseDAO {
//
//    public void save(Erpmusers erpmuser) {
//        try {
//            beginTransaction();
//            getSession().save(erpmuser);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }
//
//    public void update(Erpmusers erpmuser) {
//        try {
//            beginTransaction();
//            getSession().update(erpmuser);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }
//
//    public void delete(Erpmusers erpmuser) {
//        try {
//            beginTransaction();
//            getSession().delete(erpmuser);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }
//
//    public List<Erpmusers> findAll() {
//        beginTransaction();
//        List<Erpmusers> list = getSession().createQuery("select u from Erpmusers u").list();
//        commitTransaction();
//        return list;
//    }
//
//    public Erpmusers findByUserName(String ERPMU_Name) {
//        beginTransaction();
//        List<Erpmusers> erpmuserList  =  getSession().createQuery("select u from Erpmusers u where u.erpmuName=:username ").
//                                                      setParameter("username",ERPMU_Name).list();
//        commitTransaction();
//        Erpmusers erpmuser = erpmuserList.get(0);
//        return erpmuser;
//    }
//
//
//     public Erpmusers findByUserId(Integer erpmuId) {
//        beginTransaction();
//        List<Erpmusers> erpmuserList  =  getSession().createQuery("select u from Erpmusers u where u.erpmuId=:erpmuId ").
//                                                      setParameter("erpmuId",erpmuId).list();
//        commitTransaction();
//        Erpmusers erpmuser = erpmuserList.get(0);
//        return erpmuser;
//    }
//
//     public List<Institutionmaster> findForUser(Integer erpmuId)
//    {
//        beginTransaction();
//        List<Institutionmaster> imList = getSession().createQuery("select distinct(u) from Institutionmaster u, Erpmuserrole r where r.institutionmaster.imId = u.imId and r.erpmusers.erpmuId = :erpmuId").setParameter("erpmuId",erpmuId).list();
//        commitTransaction();
//        return imList;
//    }
//
//      public List <Erpmusers> findByforwarededUserId(Integer erpmuId) {
//        beginTransaction();
//        List<Erpmusers> erpmuserList  =  getSession().createQuery("select u from Erpmusers u where u.erpmuId=:erpmuId ").
//                                                      setParameter("erpmuId",erpmuId).list();
//        commitTransaction();
//       // Erpmusers erpmuser = erpmuserList.get(0);
//        return erpmuserList;
//    }
//    public List<Erpmusers> RetrieveUser(String ERPMU_Name, String ERPMU_Password)
//    {
//        beginTransaction();
//        List<Erpmusers> list  = getSession().createQuery("select u from Erpmusers u, Erpmuserrole r where u.erpmuId = r.erpmusers.erpmuId and r.erpmurActive = 'Y' and u.erpmuName=:username and u.erpmuPassword=:password ").
//                                setParameter("username",ERPMU_Name).setParameter("password",ERPMU_Password).list();
//
//        commitTransaction();
//
//        return list;
//    }
//
//    public String RetrieveSecretQuestion(String ERPMU_Name, String ERPMU_DOB)
//    {
//        beginTransaction();
//        List<Erpmusers> list  = getSession().createQuery("select u from Erpmusers u where u.erpmuName=:username and u.erpmuDob =:userdob").
//                                setString("username",ERPMU_Name).setString("userdob", ERPMU_DOB).list();
//        commitTransaction();
//        return list.get(0).getErpmuSecretQuestion();
//    }
//
//    public Erpmusers FindByUserNameSecretAnswer(String ERPMU_Name, String Secret_Answer)
//    {
//        beginTransaction();
//        List<Erpmusers> list  = getSession().createQuery("select u from Erpmusers u where u.erpmuName=:username and u.erpmuSecretAnswer =:useranswer").
//                                setString("username",ERPMU_Name).setString("useranswer", Secret_Answer).list();
//        commitTransaction();
//        if (list.size() > 0)
//            return list.get(0);
//        else
//            return null;
//    }
//  public Erpmusers findByUserFullNames(String ERPMU_FullName) {
//        beginTransaction();
//        List <Erpmusers> erpmuserList  =  getSession().createQuery("select u from Erpmusers u where u.erpmuFullName=:ERPMU_FullName ").
//                                                      setParameter("ERPMU_FullName",ERPMU_FullName).list();
//        commitTransaction();
//        //Erpmusers erpmuser = erpmuserList.get(0);
//        return erpmuserList.get(0);
//    }
//
//    public Erpmusers findByUser_Names(String erpmuName) {
//        beginTransaction();
//        List <Erpmusers> erpmuserList  =  getSession().createQuery("select u from Erpmusers u where u.erpmuName=:erpmuName ").
//                                                      setParameter("erpmuName",erpmuName).list();
//        commitTransaction();
//        return erpmuserList.get(0);
//    }
//
//     public List<Erpmusers> findUserCollegues(Integer erpmuId) {
//         String SQL =   "Select u.erpmusers from Erpmuserrole u where u.institutionmaster.imId in "
//                        + " (Select v.institutionmaster.imId from Erpmuserrole v where v.erpmusers.erpmuId = :erpmuId)";
//
//        beginTransaction();
//        List<Erpmusers> erpmuList = getSession().createQuery(SQL).setParameter("erpmuId",erpmuId).list();
//        commitTransaction();
//        return erpmuList;
//    }
//
//
//
//}
