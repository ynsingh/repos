package utils;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDAO implements IBaseHibernateDAO {
    private Session session;
    private Transaction trans;

    public BaseDAO() {
        //The ndxt line was in use till 06-sep-2012
        //session = HibernateUtil.getSessionFactory().openSession();
        //g  getSessionFactory().openSession(); //HibernateUtil.getSession(); //.openSession();   //getSessionFactory().openSession();
        session = HibernateUtil.getSessionPicoFactory();
        trans = session.beginTransaction();
    }

    public Session getSession() {
        session.clear();
        return session;
    }

    public void beginTransaction() {
        trans.begin();
    }

    public void commitTransaction() {
        trans.commit();
    }
}
