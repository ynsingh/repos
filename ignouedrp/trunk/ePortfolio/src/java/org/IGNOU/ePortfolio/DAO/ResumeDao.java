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
    
    private SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;
    
    @SuppressWarnings("unchecked")
    public Resume ResumeSave(Resume res) {
         s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(res);               
        t.commit();
        s.close();
        sf.close();
        return res;
    }
    
    @SuppressWarnings("unchecked")
    public List<ResumeList> ResumeListByUserId(String user_id) {

       
        s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<ResumeList> rslist = null;
        try {
             rslist = s.createQuery("from ResumeList  where user_id='" + user_id + "'").list();
            

        } catch (HibernateException he) {
            System.out.println(he);
        }
        t.commit();
        s.close();
        sf.close();
        return rslist;
    }
    
    
    public ResumeList ResumeDelete(long idResume) {
        s = sf.openSession();
        Transaction t = s.beginTransaction();
        ResumeList reslist = (ResumeList)s.load(ResumeList.class, idResume);
        if (null != reslist) {
            s.delete(reslist);
        }
        t.commit();
        s.close();
        sf.close();
        return reslist;
    }
    
  
    public List<ResumeList> ResumeDetailByIdResume(long idResume) {
      s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<ResumeList> drslist = null;
        try {
             drslist = s.createQuery("from ResumeList  where idResume='" + idResume + "'").list();
            

        } catch (HibernateException he) {
            System.out.println(he);
        }
        t.commit();
        s.close();
        sf.close();
        return drslist;
    }
    
}
