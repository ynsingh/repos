
package org.iitk.brihaspati.modules.utils;
/*
 * @(#)usercourseinfr.java
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur.
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
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */

/**
  * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
  */

public class usercourseinfr {
        String groupname;
        String role;
        String cname;
        String active;
	String institute;

        public void setinstitute(String institute)
        {
                this.institute=institute;
        }
        public String getinstitute()
        {
                return institute;
        }

        public void setgrp(String groupname)
        {
                this.groupname=groupname;
        }
        public String getgrp()
        {
                return groupname;
        }

        public void setrole(String role)
        {
                this.role=role;
        }
        public String getrole()
        {
                return role;
        }
        public void setcname(String cname)
        {
                this.cname=cname;
        }
        public String getcname()
        {
                return cname;
        }

        public void setactive(String active)
        {
                this.active=active;
        }
        public String getactive()
        {
                return active;
        }
}

