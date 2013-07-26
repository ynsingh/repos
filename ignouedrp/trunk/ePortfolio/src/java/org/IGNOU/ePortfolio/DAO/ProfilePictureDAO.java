
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.ProfilePicture;
import org.IGNOU.ePortfolio.Model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author IGNOU Team
 */
public class ProfilePictureDAO {
    
    private  SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;    
   
    @SuppressWarnings("unchecked")
    public ProfilePicture ProfilePictureSave(ProfilePicture ppll) {
        s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(ppll);               
        t.commit();
        s.close();
        sf.close();
        return ppll;
    }
     
    @SuppressWarnings({"unchecked"})
    public List<ProfilePicture> ProfilePictureListByUserId(String user_id) {
         s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            
            List<ProfilePicture> spp=null;
            try {
                spp = s.createQuery("from ProfilePicture  where user_id='" + user_id + "'").list();
                
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            if(spp!=null)
            {
            return spp;
            }
            else 
            {
                spp=null;
                return spp;
            }
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }
    
    public ProfilePicture ProfilePictureUpdate(Long picId, byte[] picture, String userId, String filetype) {
        s = sf.openSession();
        Transaction t = s.beginTransaction();
        ProfilePicture Ppl = (ProfilePicture) s.load(ProfilePicture.class, picId);
        Ppl.setPicId(picId);
        Ppl.setPicture(picture);
        Ppl.setFiletype(filetype);
        Ppl.setUserId(userId);
        s.update(Ppl);
        t.commit();
        s.close();
        return Ppl;
    }
}
