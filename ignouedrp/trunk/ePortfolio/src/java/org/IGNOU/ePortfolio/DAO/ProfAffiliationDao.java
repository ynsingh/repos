


/*
 * 
 *  Copyright (c) 2011 eGyankosh, IGNOU, New Delhi.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL eGyankosh, IGNOU OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.ProfileProAffiliation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 * @version 1
 * @since 14-Oct-2011
 * @author IGNOU Team
 * Modified and Upgrade Edit, Update, Delete Functionality by IGNOU Team on 17-Oct-2011.
 */
public class ProfAffiliationDao {

    private SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;   

    public ProfileProAffiliation ProfileProAffiliationSave(ProfileProAffiliation AffiliationModel) throws Exception {
       s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(AffiliationModel);
            t.commit();
            return AffiliationModel;
        } catch (Exception e) {
            System.err.println("Exception Occure" + e);
            throw new Exception(e.toString());
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

    @SuppressWarnings("unchecked")
    public List<ProfileProAffiliation> ProfileProAffiliationList(String UserId) throws Exception {
      s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ProfileProAffiliation> AffiliationList = null;
            try {
                AffiliationList = s.createQuery("from ProfileProAffiliation where user_id='" + UserId + "'").list();
            } catch (HibernateException HE) {
                System.err.println("Hibernate Exception Occure" + HE);
                throw new HibernateException(HE.toString());
            }
            t.commit();
            return AffiliationList;

        } catch (Exception e) {
            System.err.println("Exception Occure" + e);
            throw new Exception(e.toString());
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

    @SuppressWarnings("unchecked")
    public List<ProfileProAffiliation> ProfileProAffiliationEdit(long proAffiliationId) throws Exception {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ProfileProAffiliation> EditList = null;
            try {
                EditList = s.createQuery("from ProfileProAffiliation where proAffiliationId='" + proAffiliationId + "'").list();

            } catch (HibernateException HE) {
                System.err.println("Hibernate Exception Occure" + HE);
                throw new HibernateException(HE.toString());
            }
            t.commit();
            return EditList;
        } catch (Exception e) {
            System.err.println("Exception Occure" + e);
            throw new Exception(e.toString());
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

    public ProfileProAffiliation ProfileProAffiliationUpdate(long proAffiliationId, String userId, String role, String orgBody, String vfrom, String vupto, String place, String country, String summary) {
       s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileProAffiliation UpdateAffiliation = (ProfileProAffiliation) s.load(ProfileProAffiliation.class, proAffiliationId);
            UpdateAffiliation.setCountry(country);
            UpdateAffiliation.setOrgBody(orgBody);
            UpdateAffiliation.setPlace(place);
            UpdateAffiliation.setRole(role);
            UpdateAffiliation.setSummary(summary);
            UpdateAffiliation.setVfrom(vfrom);
            UpdateAffiliation.setVupto(vupto);
            if (null != UpdateAffiliation) {
                s.update(UpdateAffiliation);
            }
            t.commit();
            return UpdateAffiliation;
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

    public ProfileProAffiliation ProfileProAffiliationDelete(long proAffiliationId) {
       s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileProAffiliation DeleteInfo = (ProfileProAffiliation) s.load(ProfileProAffiliation.class, proAffiliationId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
        } catch (HibernateException HE) {
            System.err.println("Hibernate Exception Occure" + HE);
            throw new HibernateException(HE.toString());
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

}
