/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import org.smvdu.payroll.beans.Employee;

/**
 *
 * @author Algox
 */
public class Main {

    public static void main(String args[])
    {
         Employee emp = new EmployeeDB().loadProfile("EMP003");
         System.out.println(emp.getName());
    }

}
