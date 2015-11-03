/**
  *@author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>
  */

package utils;

//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class HibernateUtil {

    private static   SessionFactory sessionLoginFactory;
    private static   SessionFactory sessionPicoFactory;

    public static void createSessionFactory() {
        sessionLoginFactory = new Configuration().configure("login.cfg.xml").buildSessionFactory();
        sessionPicoFactory = new Configuration().configure("pico.cfg.xml").buildSessionFactory();
    }

    public static Session getSessionLoginFactory() {
        return sessionLoginFactory.openSession();
    }

    public static Session getSessionPicoFactory() {
        return sessionPicoFactory.openSession();
    }
}
