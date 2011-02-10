/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

/**
 *
 * @author System Administrator
 */
public class Faculty {
    private String faculty_id;
    private String faculty_name;
    public Faculty(String id,String name)
    {
        faculty_id=id;
        faculty_name=name;
    }
    public Faculty()
    {
        faculty_id=null;
        faculty_name=null;
    }

}
