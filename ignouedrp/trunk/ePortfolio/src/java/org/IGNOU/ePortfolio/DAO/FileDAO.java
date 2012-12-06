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

/**
 *
 * @author IGNOU Team
 */
import java.util.List;
import org.IGNOU.ePortfolio.Model.UserDocsList;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
/**
     * FileDAO This is the function to List information of files.
     * @author IGNOU Team
     */
public class FileDAO {

    @SuppressWarnings("unchecked")
    public List<UserDocsList> list(String user_id) {

        Configuration cfg = new Configuration().configure();
        SessionFactory f = cfg.buildSessionFactory();
        Session session = f.openSession();
        Transaction t = session.beginTransaction();
        List<UserDocsList> filelist = null;
        try {
            Query q = session.createQuery("from UserDocsList imagelist where imagelist.user_id='" + user_id + "'");
            filelist = q.list();

        } catch (HibernateException he) {
            System.out.println(he);
        }
        t.commit();
        session.close();
        f.close();
        return filelist;
    }
/**
     * delete This is the function to delete information of files.
     * @return imagelist
     * @author IGNOU Team
     */
    public UserDocsList delete(int fileid) {
        Configuration cfg = new Configuration().configure();
        SessionFactory f = cfg.buildSessionFactory();
        Session session = f.openSession();
        Transaction t = session.beginTransaction();
        UserDocsList imagelist = (UserDocsList) session.load(UserDocsList.class, fileid);
        if (null != imagelist) {
            session.delete(imagelist);
        }
        t.commit();
        session.close();
        f.close();
        return imagelist;
    }
/**
     * DetailFetch This is the function to fetch information of files.
     * @return imagelist
     * @author IGNOU Team
     */
    @SuppressWarnings("unchecked")
    public List<UserDocsList> DetailFetch(int fileid) {
        Configuration cfg = new Configuration().configure();
        SessionFactory f = cfg.buildSessionFactory();
        Session session = f.openSession();
        Transaction t = session.beginTransaction();
        List<UserDocsList> imagelist = null;
        try {
            Query q = session.createQuery("from UserDocsList imagelist where imagelist.fileid='" + fileid + "'");
            imagelist = q.list();

        } catch (HibernateException he) {
            System.out.println(he);
        }
        t.commit();
        session.close();
        f.close();
        return imagelist;
    }
/**
     * Update This is the function to Update information of files.
     * @return imagelist
     * @author IGNOU Team
     */
    public UserDocsList Update(int fileid, String filename, String description, String filedate) {
        Configuration cfg = new Configuration().configure();
        SessionFactory f = cfg.buildSessionFactory();
        Session session = f.openSession();
        Transaction t = session.beginTransaction();
        UserDocsList imagelist = (UserDocsList) session.load(UserDocsList.class, fileid);
        imagelist.setFileid(fileid);
        imagelist.setFilename(filename);
        imagelist.setDescription(description);
        imagelist.setFiledate(filedate);
        if (null != imagelist) {
            session.update(imagelist);
        }
        t.commit();
        session.close();
        f.close();
        return imagelist;
    }
}
