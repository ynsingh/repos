/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.IGNOU.ePortfolio.Model.ProfileSocial;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 * @version 1.2
 * @author IGNOU Team
 */
public class MySocialInfoDao {

    private SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;
    /*This function is used to insert social information in to Data base*/
    @SuppressWarnings("unchecked")
    public ProfileSocial ProfileSocialSave(ProfileSocial SocialModel) {
        s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(SocialModel);
        t.commit();
        s.close();
        sf.close();
        return SocialModel;
    }
    /*This function is used to Fetch social information */
    @SuppressWarnings("unchecked")
    public List<ProfileSocial> ProfileSocialListByUserId(String user_id) {
         s = sf.openSession();
        Transaction t = s.beginTransaction();

        List<ProfileSocial> sociallist = null;
        try {
            sociallist = s.createQuery("from ProfileSocial where user_id='" + user_id + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return sociallist;
    }
    /*This function is used to Update social information in to Database*/
    @SuppressWarnings("unchecked")
    public ProfileSocial ProfileSocialUpdate(long socialInfo_id, String user_id, String gtalk, String skype, String msn, String aim, String yahoo, String facebook, String orkut, String twitter, String blog) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            ProfileSocial UpdateSocialInfo = (ProfileSocial) s.load(ProfileSocial.class, socialInfo_id);
            UpdateSocialInfo.setSocialInfoId(socialInfo_id);
            UpdateSocialInfo.setAim(aim);
            UpdateSocialInfo.setBlog(blog);
            UpdateSocialInfo.setFacebook(facebook);
            UpdateSocialInfo.setGtalk(gtalk);
            UpdateSocialInfo.setMsn(msn);
            UpdateSocialInfo.setOrkut(orkut);
            UpdateSocialInfo.setSkype(skype);
            UpdateSocialInfo.setTwitter(twitter);
            UpdateSocialInfo.setYahoo(yahoo);
            if (null != UpdateSocialInfo) {
                s.update(UpdateSocialInfo);
            }
            t.commit();
            return UpdateSocialInfo;
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
