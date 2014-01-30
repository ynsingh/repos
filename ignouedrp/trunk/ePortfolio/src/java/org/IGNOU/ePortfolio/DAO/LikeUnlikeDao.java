/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.io.Serializable;
import java.util.List;
import org.IGNOU.ePortfolio.Model.LikeDislike;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Amit
 */
public class LikeUnlikeDao implements Serializable {

    private static final long serialVersionUID = 1L;
    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    public LikeDislike LikeDislikeSave(LikeDislike ldModel) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
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
    public List<LikeDislike> LikeDislikeListByEvidenceIdCommentId(Integer evidenceSubId, Integer commentId) {
        s = sessionFactory.openSession();
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

    public LikeDislike LikeDislikeUpdate(int likeDislikeId, Integer evidenceSubId, Integer commentId, Integer likeCount, Integer dislikeCount, String userDetail) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        LikeDislike ldlist = (LikeDislike) s.load(LikeDislike.class, likeDislikeId);
        ldlist.setLikeCount(likeCount);
        ldlist.setDislikeCount(dislikeCount);
        ldlist.setUserDetail(userDetail);
        if (null != ldlist) {
            s.update(ldlist);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return ldlist;
    }
}
