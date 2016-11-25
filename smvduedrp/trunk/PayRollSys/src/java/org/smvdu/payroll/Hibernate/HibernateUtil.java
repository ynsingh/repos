/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.Hibernate;


import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
*
*  Copyright (c) 2010 - 2011.2014 SMVDU, Katra.
*  Copyright (c) 2014 - 2016 ETRG, IITK.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
* Hibernate Utility class with a convenient method to get Session Factory
* object.
*
* @author yuvraj
* Modification for multiple database: Om Prakash (omprakashkgp@gmail.com), IITK
* Last Modification: November, 2016 	
*/

public class HibernateUtil {
    
 	private static SessionFactory sessionFactory ;
	private static SessionFactory loginSF;
       
      static{
            
            try{
                    sessionFactory = new AnnotationConfiguration().configure("payrolldb.cfg.xml").buildSessionFactory();
                    loginSF = new AnnotationConfiguration().configure("logindb.cfg.xml").buildSessionFactory();
               }
               catch(Throwable ex)
               {
                    throw new ExceptionInInitializerError(ex);  
               }
        }

        
        public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
        
        public static SessionFactory getLoginSF(){
		return loginSF;
	}

/*    private static final SessionFactory sessionFactory;
     
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }*/

}
