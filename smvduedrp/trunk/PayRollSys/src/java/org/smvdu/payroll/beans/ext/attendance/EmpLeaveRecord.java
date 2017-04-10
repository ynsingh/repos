/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.ext.attendance;

import java.io.Serializable;

/**
 *  Manages Attendance in database.
 *  Copyright (c) 2010 - 2011 SMVDU, Katra.
 *  Copyright (c) 2014 - 2017 ETRG, IITK.
 *  All Rights Reserved.
 ** Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met: 
 ** Redistributions of source code must retain the above copyright 
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
 *  Contributors: Members of ERP Team @ SMVDU, Katra
 *
 * @author Om Prakash(omprakashkgp@gmail.com), IITK 
 */

public class EmpLeaveRecord implements Serializable {
 
    
    private int elrId;
    private String elrEmpCode;
    private int elrleaveid;
    private int elrcount;
    private String elrFyear;

    public EmpLeaveRecord() {
   
    }

    public int getElrId() {
        return elrId;
    }

    public void setElrId(int elrId) {
        this.elrId = elrId;
    }

    public String getElrEmpCode() {
        return elrEmpCode;
    }

    public void setElrEmpCode(String elrEmpCode) {
        this.elrEmpCode = elrEmpCode;
    }

    public int getElrleaveid() {
        return elrleaveid;
    }

    public void setElrleaveid(int elrleaveid) {
        this.elrleaveid = elrleaveid;
    }

    public int getElrcount() {
        return elrcount;
    }

    public void setElrcount(int elrcount) {
        this.elrcount = elrcount;
    }

    public String getElrFyear() {
        return elrFyear;
    }

    public void setElrFyear(String elrFyear) {
        this.elrFyear = elrFyear;
    }


}
