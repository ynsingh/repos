/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Model.Resume;
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

    public Resume ResumeSave(String userId, byte[] resume, String resumeName, Long resumeSize, String resumeType, Date uploadDate) {
        s = sf.openSession();
        Transaction t = s.beginTransaction();
        Resume res = new Resume();
        res.setUserId(userId);
        res.setResume(resume);
        res.setResumeName(resumeName);
        res.setResumeSize(resumeSize);
        res.setResumeType(resumeType);
        res.setUploadDate(uploadDate);
        s.save(res);
        t.commit();
        s.close();
        sf.close();
        return res;
    }

    @SuppressWarnings("unchecked")
    public List<Resume> ResumeByUserId(String user_id) {
        s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<Resume> rslist = null;
        try {
            rslist = s.createQuery("from Resume  where user_id='" + user_id + "'").list();
        } catch (HibernateException he) {
            System.out.println(he);
        }
        t.commit();
        s.close();
        sf.close();
        return rslist;
    }

    public Resume ResumeDelete(long idResume) {
        s = sf.openSession();
        Transaction t = s.beginTransaction();
        Resume reslist = (Resume) s.load(Resume.class, idResume);
        if (null != reslist) {
            s.delete(reslist);
        }
        t.commit();
        s.close();
        sf.close();
        return reslist;
    }

    public List<Resume> ResumeDetailByIdResume(long idResume) {
        s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<Resume> drslist = null;
        try {
            drslist = s.createQuery("from Resume  where idResume='" + idResume + "'").list();


        } catch (HibernateException he) {
            System.out.println(he);
        }
        t.commit();
        s.close();
        sf.close();
        return drslist;
    }
}
