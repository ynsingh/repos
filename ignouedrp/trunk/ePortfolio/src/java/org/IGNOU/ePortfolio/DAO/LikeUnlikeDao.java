/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;


import java.util.List;
import org.IGNOU.ePortfolio.Model.LikeDislike;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Amit
 */
public class LikeUnlikeDao {
     private SessionFactory sessionFactory;

    public LikeDislike saveLikeUnlike(LikeDislike ldModel) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t =  s.beginTransaction();
            s.save(ldModel);
            t.commit();
            return ldModel;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }
     @SuppressWarnings("unchecked")
    public List<LikeDislike> LikeDislikeCheck(Integer evidenceSubId,Integer commentId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
         List<LikeDislike> LikeDislikelist = null;
        try {
            LikeDislikelist = s.createQuery("from LikeDislike where evidenceSubId='" + evidenceSubId + "' and commentId='" + commentId + "' ").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return LikeDislikelist;
    }
     
      public LikeDislike UpdateLikeDislike(int likeDislikeId, Integer evidenceSubId, Integer commentId, Integer likeCount, Integer dislikeCount, String userDetail) {
        Configuration cfg = new Configuration().configure();
        SessionFactory f = cfg.buildSessionFactory();
        Session session = f.openSession();
        Transaction t = session.beginTransaction();
        LikeDislike ldlist = (LikeDislike) session.load(LikeDislike.class, likeDislikeId);
        ldlist.setLikeCount(likeCount);
        ldlist.setDislikeCount(dislikeCount);
        ldlist.setUserDetail(userDetail);
        if (null != ldlist) {
            session.update(ldlist);
        }
        t.commit();
        session.close();
        f.close();
        return ldlist;
    }
}
