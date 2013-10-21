/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;


import java.util.List;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;

/**
 *
 * @author sknaqvi
 */

public class FileDetailDAO {

        public void save(FileDetail fd) {
            Session session = HibernateUtil.getSession();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.save(fd);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(fd != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

     public void update(FileDetail fd) {
       Session session = HibernateUtil.getSession();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
             session.update(fd);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(fd != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public void delete(FileDetail fd) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
             session.delete(fd);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(fd != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public List<FileDetail> findAll() {
         Session session = HibernateUtil.getSession();
         try {
       session.beginTransaction();
        List<FileDetail> list = session.createQuery("from FileDetail").list();
        
        return list;
         }
        finally {
            session.close();
            }
    }

    public Integer getDespatchNumber(Integer fileId) {
        Session session = HibernateUtil.getSession();
         try {
              Integer depatchNumber;
       session.beginTransaction();
       
        String SQL = "select if(count(u.Fm_Id), count(u.Fm_Id)+1, 1) " +
                     "from File_Detail u, File_master v " +
                     "where v.File_id = :fileId and " +
                     "v.File_DM_ID in (Select w.File_DM_ID from File_master w where w.file_id = :fileId)";
        depatchNumber = Integer.parseInt(session.createSQLQuery(SQL).setParameter("fileId", fileId).uniqueResult().toString());
        return depatchNumber;
         }
        finally {
            session.close();
            }
    }

    public List<FileDetail> getFiles(Integer fileId) {
        Session session = HibernateUtil.getSession();
         try {
             session.beginTransaction();


        String SQL = "select u from File_Detail u " +
                     "where u.fileMaster.fileId = :fileId ";
        
        List<FileDetail> list = session.createSQLQuery(SQL).setParameter("fileId", fileId).list();
       
        return list;
        }
        finally {
            session.close();
            }
    }



    
}

