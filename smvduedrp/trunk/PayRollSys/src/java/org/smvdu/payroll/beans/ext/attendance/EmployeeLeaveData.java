/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.ext.attendance;




import java.util.ArrayList;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import org.smvdu.payroll.beans.ext.attendance.LeaveType;
import org.smvdu.payroll.beans.Employee;



/**
*
*  Copyright (c) 2010 - 2011 SMVDU, Katra.
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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
*  Modified Date: 7 AUG 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*/

 class EmployeeLeaveData extends EmployeeLeave implements ValueChangeListener{
  
        
    private int id;
    private String dateFrom;
    private String dateTo;
    private int count;
    private Employee employee;
    private String leaveTypeName;
    private String applieddate;
    private String activestatus;
    private String code;

     public EmployeeLeaveData()
    {

    }
    
    public EmployeeLeaveData(EmployeeLeave empl)
    {
            setId(empl.getId());
            setEmpCode(empl.getEmpCode());
            setDateFrom(empl.getDateFrom());
            setDateTo(empl.getDateTo());
            setCount(empl.getCount());
            setEmployee(empl.getEmployee());
            setLeaveTypeName(empl.getLeaveTypeName());
            setAppliedDate(empl.getAppliedDate());
            setActiveStatus(empl.getActiveStatus());
            
            
        
    }
    
   @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {        
        System.err.println(event.getOldValue()+" New value "+event.getNewValue());
    }
 
    
}
