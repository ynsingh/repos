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
import org.IGNOU.ePortfolio.Model.UserPlan;
import org.IGNOU.ePortfolio.Model.UserPlanTask;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 ** Hibernate Utility class with a convenient method to get Session Factory object.
 * 
 * @author IGNOU Team
 * @version 2
 * Last Modified on 12-07-2012 by IGNOU Team
 */
public class PlanTaskDAO {

    /**
     * UserPlanTaskSave This is the function to save Plan information into DB.
     * @author IGNOU Team
     * This function is used to insert Plan information in to Database
     * 
     */
    private   SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session session;
    
    @SuppressWarnings("unchecked")
    public UserPlan UserPlanSave(UserPlan upModel) {
        session = sf.openSession();
        session.beginTransaction();
        session.save(upModel);
        session.getTransaction().commit();
        session.close();
        sf.close();
        return upModel;
    }

    /**
     * PlanList This is the function to show list of Plan as per user.
     * @param user_id 
     * @return planlistlist
     * @author IGNOU Team
     * This function is used to Fetch Plan List 
     */
    @SuppressWarnings("unchecked")
    public List<UserPlan> UserPlanListByUserId(String user_id) {
         session = sf.openSession();
        Transaction t = session.beginTransaction();
        List<UserPlan> planlistlist = null;
        try {
            planlistlist = session.createQuery("from UserPlan where userId='" + user_id + "'").list();
        } catch (HibernateException e) {
            System.out.println(e);
        }
        t.commit();
        session.close();
        sf.close();
        return planlistlist;
    }

    /**
     * UserPlanlistByPlanId This is the function to show Plan details in Editor formate.
     * Added on 23/08/2011
     * @param plan_id 
     * @return editplanlist
     * @author IGNOU Team
     * This function is used to Edit Plan information in to Database 
     */
    @SuppressWarnings("unchecked")
    public List<UserPlan> UserPlanlistByPlanId(long plan_id) {
        session = sf.openSession();
        Transaction t = session.beginTransaction();

        List<UserPlan> editplanlist = null;
        try {
            editplanlist = session.createQuery("from UserPlan where planId='" + plan_id + "'").list();
        } catch (HibernateException e) {
            System.out.println(e);
        }
        t.commit();
        session.close();
        sf.close();
        return editplanlist;
    }
    /*
     * This function is used to Update Plan information in to Database
     */

    @SuppressWarnings("unchecked")
    public UserPlan UserPlanUpdate(long planId, String userId, String PTitle, String PDescription) {
         session =sf.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            UserPlan UpdateInfo = (UserPlan) session.load(UserPlan.class, planId);
            UpdateInfo.setPTitle(PTitle);
            UpdateInfo.setPDescription(PDescription);
            if (null != UpdateInfo) {
                session.update(UpdateInfo);
            }
            t.commit();
            return UpdateInfo;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            session.close();
            sf.close();
        }
    }
    /*
     * This function is used to Delete Plan information in to Database
     */

    @SuppressWarnings("unchecked")
    public UserPlan UserPlanDelete(long planId) {
        session = sf.openSession();
        Transaction t = session.beginTransaction();
        UserPlan deletelist = (UserPlan) session.load(UserPlan.class, planId);
        if (null != deletelist) {
            session.delete(deletelist);
        }
        t.commit();
        session.close();
        sf.close();
        return deletelist;
    }

    /**
     * UserPlanTaskSave This is the function to save Task information into DB.
     * @param nt 
     * @return SUCCESS
     * @author IGNOU Team
     * This function is used to Save  Plan Task  information in to Database
     */
    public UserPlanTask UserPlanTaskSave(UserPlanTask nt) {
         session = sf.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.save(nt);
            t.commit();
            return nt;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            session.close();
            sf.close();
        }
    }

    /**
     * UserPlanTaskListByPlanId This is the function to show list of Task as per Plan_id.
     * @param plan_id 
     * @return TaskListList
     * @author IGNOU Team
     * This function is used to Fetch  Plan's Task  List information 
     */
    @SuppressWarnings("unchecked")
    public List<UserPlanTask> UserPlanTaskListByPlanId(long planId) {
        session = sf.openSession();
        Transaction t = session.beginTransaction();
        List<UserPlanTask> TaskListList = null;
        try {
            TaskListList = session.createQuery("from UserPlanTask where userPlan.planId='" + planId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        session.close();
        sf.close();
        return TaskListList;
    }
   
    /**
     * EditTask This is the function to Fetch Task details in editable formate.
     * author IGNOU Team
     * @param task_id 
     * @return EditTask  List
     * Added on 24th Aug 2011
     */
    @SuppressWarnings("unchecked")
    public List<UserPlanTask> UserPlanTaskEdit(long task_id) {
         session = sf.openSession();
        Transaction t = session.beginTransaction();

        List<UserPlanTask> EditTask = null;
        try {
            EditTask = session.createQuery("from UserPlanTask where taskId='" + task_id + "'").list();
           } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        session.close();
       sf.close();
        return EditTask;
    }
    /*This is the function to Update Task details */

    @SuppressWarnings("unchecked")
    public UserPlanTask UserPlanTaskUpdate(long taskId, String TTitle, String TDescription, String TDate, Integer status) {
        session = sf.openSession();
        Transaction t = session.beginTransaction();
        UserPlanTask UpdateTaskList = (UserPlanTask) session.load(UserPlanTask.class, taskId);
        UpdateTaskList.setTTitle(TTitle);
        UpdateTaskList.setTDescription(TDescription);
        UpdateTaskList.setTDate(TDate);
        UpdateTaskList.setStatus(status);
        if (null != UpdateTaskList) {
            session.update(UpdateTaskList);
        }
        t.commit();
        session.close();
        sf.close();
        return UpdateTaskList;
    }
    /*This is the function to Delete Plan's Task details */

    @SuppressWarnings("unchecked")
    public UserPlanTask UserPlanTaskDelete(Long taskId) {
        session = sf.openSession();
        Transaction t = session.beginTransaction();

        UserPlanTask deleteList = (UserPlanTask) session.load(UserPlanTask.class, taskId);
        if (null != deleteList) {
            session.delete(deleteList);
        }
        t.commit();
        session.close();
        sf.close();
        return deleteList;
    }

}