package utils;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDAO implements IBaseHibernateDAO {
    private Session session;
    private Transaction trans;

    public BaseDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
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