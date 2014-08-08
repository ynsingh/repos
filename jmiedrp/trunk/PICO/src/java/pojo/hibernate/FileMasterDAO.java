/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

import java.math.BigDecimal;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;
//import utils.BaseDAO;

/**
 *
 * @author sknaqvi
 */
public class FileMasterDAO {

//        public void save(FileMaster fm) {
//        try {
//            beginTransaction();
//            getSession().save(fm);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }


    public void save(FileMaster fm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(fm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(fm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    
//     public void update(FileMaster fm) {
//        try {
//            beginTransaction();
//            getSession().update(fm);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }


    public void update(FileMaster fm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(fm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(fm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


    
//    public void delete(FileMaster fm) {
//        try {
//            beginTransaction();
//            getSession().delete(fm);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }



//    public List<FileMaster> findAll() {
//        beginTransaction();
//        List<FileMaster> list = getSession().createQuery("from FileMaster").list();
//        commitTransaction();
//        return list;
//    }

    //The function counts the number of files in a given department
//    public Integer countFiles(Integer dmId) {
//        String SQL = "select if(max(u.File_Id), max(u.File_Id), 0) from File_Master u where u.file_dm_id = :dmId";
//        Integer countFiles;
//        beginTransaction();
//
//         countFiles = Integer.parseInt(getSession().createSQLQuery(SQL).setParameter("dmId", dmId).uniqueResult().toString());
//        commitTransaction();
//        if (countFiles == null)
//                return 0;
//        else
//            return countFiles;
//    }


     public Integer countFiles(Integer dmId) {
        Session session = HibernateUtil.getSession();
        try {
            String SQL = "select if(max(u.File_Id), max(u.File_Id), 0) from File_Master u where u.file_dm_id = :dmId";
             Integer countFiles;
            session.beginTransaction();
            countFiles = Integer.parseInt(session.createSQLQuery(SQL).setParameter("dmId", dmId).uniqueResult().toString());
            Hibernate.initialize(countFiles);
if (countFiles == null)
                return 0;
        else
            return countFiles;
        }
        finally {
            session.close();
            }
    }


    //The function retrieve the file object with the given File Id
//    public FileMaster findFile(Integer fileId) {
//        beginTransaction();
//        FileMaster fm = (FileMaster) getSession().load(FileMaster.class, fileId);
//        commitTransaction();
//        return fm;
//    }

      public FileMaster findFile(Integer fileId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
           FileMaster fm = (FileMaster) session.load(FileMaster.class, fileId);
            Hibernate.initialize(fm.getDepartmentmaster());

            return fm;
        }
        finally {
            session.close();
            }
    }

   // public FileMaster findFileByFileNumber(String fileNumber) {
       // String SQL = "select u from File_Master u where u.file_number = :fileNumber";
        //beginTransaction();
        //FileMaster fm = (FileMaster) getSession().createSQLQuery(SQL).setParameter("fileNumber", fileNumber).uniqueResult().toString());
        //commitTransaction();
        //return fm;
   // }

}

