/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author Algox
 */
public class Main {


    public static void main(String[] args)
    {
        String interest = "select (800+200)*4";
        System.out.println(new CommonDB().eval(interest));
    }
}
