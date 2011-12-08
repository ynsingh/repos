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
package org.IGNOU.ePortfolio.MyWorkspace
.Dao;

import java.util.List;
import org.IGNOU.ePortfolio.MyWorkspace.Model.Projects;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 * @version 1
 * @since 20-Oct-2011
 * @author Vinay
 */
public class ProjectDao {

    private SessionFactory sf = null;

    public Projects AddProjectInfo(Projects ProjectModel) throws Exception {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(ProjectModel);
            t.commit();
            return ProjectModel;
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
            getSf().close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Projects> ShowProjectInfo(String UserId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = getSf().openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<Projects> ProjectList = null;
            try {
                ProjectList = s.createQuery("from Projects where userId='" + UserId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return ProjectList;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            getSf().close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Projects> EditProjectInfo(long projectId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = getSf().openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<Projects> ProjectList = null;
            try {
                ProjectList = s.createQuery("from Projects where projectId='" + projectId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return ProjectList;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            getSf().close();
        }
    }

    public Projects UpdateProjectInfo(long projectId, String userId, String proName, Integer teamSize, String role, String proUrl, String startDate, String endDate, String description, String agency, Long budget) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Projects UpdateInfo = (Projects) s.load(Projects.class, projectId);
            UpdateInfo.setDescription(description);
            UpdateInfo.setEndDate(endDate);
            UpdateInfo.setProName(proName);
            UpdateInfo.setProUrl(proUrl);
            UpdateInfo.setRole(role);
            UpdateInfo.setAgency(agency);
            UpdateInfo.setBudget(budget);
            UpdateInfo.setStartDate(startDate);
            UpdateInfo.setTeamSize(teamSize);
            if (null != UpdateInfo) {
                s.update(UpdateInfo);
            }
            t.commit();
            return UpdateInfo;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            getSf().close();
        }
    }

    public Projects DeleteProjectInfo(long testId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Projects DeleteInfo = (Projects) s.load(Projects.class, testId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
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

    /*    
    
     */
    /**
     * @return the sf
     */
    public SessionFactory getSf() {
        return sf;
    }
}
