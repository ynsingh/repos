/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;


import java.util.List;
import org.IGNOU.ePortfolio.Model.Resume;
import org.IGNOU.ePortfolio.Model.ResumeList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author IGNOU Team
 */
public class ResumeDao {
    @SuppressWarnings("unchecked")
    public Resume saveResume(Resume res) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(res);               
        t.commit();
        s.close();
        sf.close();
        return res;
    }
    @SuppressWarnings("unchecked")
    public List<ResumeList> Resumelist(String user_id) {

        Configuration cfg = new Configuration() {}.configure();
        SessionFactory f = cfg.buildSessionFactory();
        Session session = f.openSession();
        Transaction t = session.beginTransaction();
        List<ResumeList> rslist = null;
        try {
             rslist = session.createQuery("from ResumeList  where user_id='" + user_id + "'").list();
            

        } catch (HibernateException he) {
            System.out.println(he);
        }
        t.commit();
        session.close();
        f.close();
        return rslist;
    }
    
    
    public ResumeList deleteResume(long idResume) {
        Configuration cfg = new Configuration().configure();
        SessionFactory f = cfg.buildSessionFactory();
        Session session = f.openSession();
        Transaction t = session.beginTransaction();
        ResumeList reslist = (ResumeList)session.load(ResumeList.class, idResume);
        if (null != reslist) {
            session.delete(reslist);
        }
        t.commit();
        session.close();
        f.close();
        return reslist;
    }
    
  
    public List<ResumeList> DetailResume(long idResume) {
       Configuration cfg = new Configuration() {}.configure();
        SessionFactory f = cfg.buildSessionFactory();
        Session session = f.openSession();
        Transaction t = session.beginTransaction();
        List<ResumeList> drslist = null;
        try {
             drslist = session.createQuery("from ResumeList  where idResume='" + idResume + "'").list();
            

        } catch (HibernateException he) {
            System.out.println(he);
        }
        t.commit();
        session.close();
        f.close();
        return drslist;
    }
    
}
