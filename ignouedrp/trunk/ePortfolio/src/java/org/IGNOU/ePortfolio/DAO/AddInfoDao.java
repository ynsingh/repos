package org.IGNOU.ePortfolio.DAO;

/**
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
import java.util.List;
import org.IGNOU.ePortfolio.Model.ProfileAcademic;
import org.IGNOU.ePortfolio.Model.ProfileCertification;
import org.IGNOU.ePortfolio.Model.ProfileEmployment;
import org.IGNOU.ePortfolio.Model.ProfileReferences;
import org.IGNOU.ePortfolio.Model.ProfileSkill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *Added on 12-Sep-2011
 * @author IGNOU Team
 * Add Academic Information.
 * This is the function to Insert  details 
 */
public class AddInfoDao {
    /*This is the function to Insert Academic Information into database */

    @SuppressWarnings("unchecked")
    public ProfileAcademic saveInfo(String user_id, List<String> degree, List<String> university, List<String> location, List<String> fstudy, List<String> pyear, List<Integer> percent, List<String> division) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileAcademic pa = new ProfileAcademic();
            for (int i = 0; i < degree.size(); i++) {
                pa.setUserId(user_id);
                pa.setDegree(degree.get(i));
                pa.setDivision(division.get(i));
                pa.setFstudy(fstudy.get(i));
                pa.setLocation(location.get(i));
                pa.setPercent(percent.get(i));
                pa.setPyear(pyear.get(i));
                pa.setUniversity(university.get(i));
                s.save(pa);
                s.flush();
                s.clear();
            }
            t.commit();
            return pa;
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

    /**
     * Added on 13-Sep-2011
     * @param EmploymentModel 
     * @return EmployementModel
     * @author IGNOU Team
     * Add Employment Information.
     * This is the function to Insert Employement Information into database 
     **/
    @SuppressWarnings("unchecked")
    public ProfileEmployment saveEmpInfo(ProfileEmployment EmploymentModel) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(EmploymentModel);
        t.commit();
        s.close();
        sf.close();
        return EmploymentModel;
    }

    /**
     * Added on 13-Sep-2011
     * @author IGNOU Team
     * This is the function to Insert Skills Information into database
     **/
    @SuppressWarnings("unchecked")
    public ProfileSkill saveSkillInfo(ProfileSkill SkillModel) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(SkillModel);
        t.commit();
        s.close();
        sf.close();
        return SkillModel;
    }

    /**
     * Added on 04-Oct-2011
     * @param CertificateModel 
     * @return CertificateModel
     * @author IGNOU Team
     * This is the function to Insert Certification Information into database.
     **/
    @SuppressWarnings("unchecked")
    public ProfileCertification saveCertificationInfo(ProfileCertification CertificateModel) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(CertificateModel);
        t.commit();
        s.close();
        sf.close();
        return CertificateModel;
    }

    /**
     * Added on 11-Oct-2011
     * @param ReferenceModel 
     * @return RefereneceModel
     * @author IGNOU Team
     * This is the function to Insert Reference Information into database.
     **/
    public ProfileReferences SaveReferenceInfo(ProfileReferences ReferenceModel) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(ReferenceModel);
        t.commit();
        s.close();
        sf.close();
        return ReferenceModel;
    }
    /**
     * Added on 11-Oct-2011
     * @param ReferenceModel 
     * @return RefereneceModel
     * @author IGNOU Team
     * This is the function to Insert Reference Information into database.
     **/
}
