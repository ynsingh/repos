/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.IGNOU.ePortfolio.Model.BookChapter;
import org.IGNOU.ePortfolio.Model.BookChapterAuthor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author IGNOU Team
 * @version 1
 * @since 23-02-2012 Last Modified on 24-02-2012 by IGNOU Team
 */
public class BookChapterDao implements Serializable {

    private static final long serialVersionUID = 1L;
    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    public BookChapter BookChapterSave(BookChapter BCModel) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(BCModel);

            BookChapterAuthor BCA = new BookChapterAuthor();
            for (int i = 0; i < BCModel.getFname().size(); i++) {
                BCA.setBookChapter(BCModel);
                BCA.setFname(BCModel.getFname().get(i));
                BCA.setLname(BCModel.getLname().get(i));
                s.save(BCA);
                s.flush();
                s.clear();
            }
            t.commit();
            return BCModel;
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

    public List<BookChapter> BookChapterListByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<BookChapter> BCList = null;

            try {
                BCList = s.createQuery("from BookChapter where userId='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return BCList;
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

    public List<BookChapter> BookChapterListByBookChapterId(long bookChapterId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<BookChapter> BCList = null;
            try {
                BCList = s.createQuery("from BookChapter where bookChapterId='" + bookChapterId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return BCList;
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

    public BookChapter BookChapterDeleteByBookChapterId(long bookChapterId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            BookChapter DeleteInfo = (BookChapter) s.load(BookChapter.class, bookChapterId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
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

    public BookChapter BookChapterUpdate(Long bookChapterId, String userId, String bcType, String role, String title, Integer noCoauthor, String publisher, String isbn, String publishedOn, Integer PFrom, Integer PTo, String language, String affiliation, String url, String summary, Set<BookChapterAuthor> bookChapterAuthors, ArrayList<String> fname, ArrayList<String> lname) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            BookChapter UpdateInfo = (BookChapter) s.load(BookChapter.class, bookChapterId);
            UpdateInfo.setAffiliation(affiliation);
            UpdateInfo.setBcType(bcType);
            UpdateInfo.setIsbn(isbn);
            UpdateInfo.setLanguage(language);
            UpdateInfo.setNoCoauthor(noCoauthor);
            UpdateInfo.setPFrom(PFrom);
            UpdateInfo.setPTo(PTo);
            UpdateInfo.setPublishedOn(publishedOn);
            UpdateInfo.setPublisher(publisher);
            UpdateInfo.setRole(role);
            UpdateInfo.setSummary(summary);
            UpdateInfo.setTitle(title);
            UpdateInfo.setUrl(url);
            if (null != UpdateInfo) {
                s.update(UpdateInfo);
            }
            t.commit();
            return UpdateInfo;
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
}
