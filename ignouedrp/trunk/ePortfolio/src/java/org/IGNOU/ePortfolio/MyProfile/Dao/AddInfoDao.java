

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
package org.IGNOU.ePortfolio.MyProfile.Dao;

import org.IGNOU.ePortfolio.MyProfile.Model.ProfileAcademic;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileBasic;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileCertification;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileContact;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileEmployment;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfilePersonal;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfilePublication;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileSkill;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileReferences;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *Added on 12-Sep-2011
 * @author Vinay
 * Add Academic Information.
 * This is the function to Insert  details 
 */
public class AddInfoDao {
    /*This is the function to Insert Academic Information into database */
    @SuppressWarnings("unchecked")
    public ProfileAcademic saveInfo(ProfileAcademic AcademicModel) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(AcademicModel);
        t.commit();
        s.close();
        sf.close();
        return AcademicModel;
    }

    /**
     * Added on 13-Sep-2011
     * @param EmploymentModel 
     * @return EmployementModel
     * @author Vinay
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
     * @param PubModel 
     * @return PubModel
     * @author Vinay
     * This is the function to Insert Publication Information into database
     **/
    @SuppressWarnings("unchecked")
    public ProfilePublication savePublicationInfo(ProfilePublication PubModel) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(PubModel);
        t.commit();
        s.close();
        sf.close();
        return PubModel;
    }
    /*This is the function to Insert Skills Information into database*/
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
     * Added on 16-Sep-2011
     * @param BasicModel 
     * @return BasicModel
     * @author Vinay
     * This is the function to Insert Basic Information into database.
     **/
    @SuppressWarnings("unchecked")
    public ProfileBasic saveBasicInfo(ProfileBasic BasicModel) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(BasicModel);
        t.commit();
        s.close();
        sf.close();
        return BasicModel;
    }
    /*This is the function to Insert Contact Information into database.*/
    @SuppressWarnings("unchecked")
    public ProfileContact saveContactInfo(ProfileContact ContactModel) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(ContactModel);
        t.commit();
        s.close();
        sf.close();
        return ContactModel;
    }
    /*This is the function to Insert Personal Information into database.*/
    @SuppressWarnings("unchecked")
    public ProfilePersonal savePersonalInfo(ProfilePersonal PersonalModel) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(PersonalModel);
        t.commit();
        s.close();
        sf.close();
        return PersonalModel;
    }

    /**
     * Added on 04-Oct-2011
     * @param CertificateModel 
     * @return CertificateModel
     * @author Vinay
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
     * @author Vinay
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
}