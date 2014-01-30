package org.IGNOU.ePortfolio.DAO;

/**
 *
 * Copyright (c) 2011 eGyankosh, IGNOU, New Delhi. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL eGyankosh,
 * IGNOU OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL,SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 * Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 *
 */
import java.util.List;
import org.IGNOU.ePortfolio.Model.MyNotes;
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
 */
public class MyNoteDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    /**
     * Added on 1-Dec-2011
     *
     * @author IGNOU Team This is the function to Insert Notes Information into
     * database.
     *
     */
    @SuppressWarnings("unchecked")
    public MyNotes MyNotesSave(MyNotes myNote) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        s.save(myNote);
        t.commit();
        s.close();
        sessionFactory.close();
        return myNote;
    }

    @SuppressWarnings({"unchecked"})
    public List<MyNotes> MyNotesListByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<MyNotes> MyNotesListList = null;
            try {
                MyNotesListList = s.createQuery("from MyNotes where user_id='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return MyNotesListList;
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
    public List<MyNotes> MyNotesListByNotesId(long notesId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<MyNotes> editNotelist = null;
            try {
                editNotelist = s.createQuery("from MyNotes where notesId='" + notesId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return editNotelist;
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
    public MyNotes MyNotesDelete(long notesId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            MyNotes DeleteNote = (MyNotes) s.load(MyNotes.class, notesId);
            if (DeleteNote != null) {
                s.delete(DeleteNote);
            }
            t.commit();
            return DeleteNote;
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
    public MyNotes MyNotesUpdate(long notesId, String userId, String note, String date, String topic) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            MyNotes UpdateNotesInfo = (MyNotes) s.load(MyNotes.class, notesId);
            UpdateNotesInfo.setNote(note);
            UpdateNotesInfo.setTopic(topic);
            UpdateNotesInfo.setDate(date);
            if (null != UpdateNotesInfo) {
                s.update(UpdateNotesInfo);
            }
            t.commit();
            return UpdateNotesInfo;
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
