 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *  Copyright (c) 2011 eGyankosh, IGNOU, New Delhi.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  UserDocsListByUserId of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this UserDocsListByUserId of conditions and the following disclaimer in
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
import org.IGNOU.ePortfolio.Model.Userdocs;
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * FileDAO This is the function to List information of files.
 *
 * @author IGNOU Team
 */
public class FileDAO {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    @SuppressWarnings("unchecked")
    public List<Userdocs> UserDocsListByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Userdocs> filelist = null;
        try {
            Query q = s.createQuery("from Userdocs imagelist where imagelist.user_id='" + user_id + "'");
            filelist = q.list();
        } catch (HibernateException he) {
            System.out.println(he);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return filelist;
    }

    /**
     * UserDocsDeleteByFileId This is the function to UserDocsDeleteByFileId
     * information of files.
     *
     * @return imagelist
     * @author IGNOU Team
     */
    public Userdocs UserDocsDeleteByFileId(long fileid) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        Userdocs imagelist = (Userdocs) s.load(Userdocs.class, fileid);
        if (null != imagelist) {
            s.delete(imagelist);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return imagelist;
    }

    /**
     * UserDocsListByFileId This is the function to fetch information of files.
     *
     * @return imagelist
     * @author IGNOU Team
     */
    @SuppressWarnings("unchecked")
    public List<Userdocs> UserDocsListByFileId(long fileid) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Userdocs> imagelist = null;
        try {
            Query q = s.createQuery("from Userdocs imagelist where imagelist.fileid='" + fileid + "'");
            imagelist = q.list();

        } catch (HibernateException he) {
            System.out.println(he);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return imagelist;
    }

    /**
     * UserDocsUpdate This is the function to UserDocsUpdate information of
     * files.
     *
     * @return imagelist
     * @author IGNOU Team
     */
    public Userdocs UserDocsUpdate(long fileid, String filename, String description, String filedate) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        Userdocs imagelist = (Userdocs) s.load(Userdocs.class, fileid);
        imagelist.setFileid(fileid);
        imagelist.setFilename(filename);
        imagelist.setDescription(description);
        imagelist.setFiledate(filedate);
        if (null != imagelist) {
            s.update(imagelist);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return imagelist;
    }

}
