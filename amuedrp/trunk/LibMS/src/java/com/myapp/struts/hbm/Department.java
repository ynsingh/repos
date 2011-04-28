package com.myapp.struts.hbm;
// Generated May 2, 2011 12:00:18 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Department generated by hbm2java
 */
public class Department  implements java.io.Serializable {


     private DepartmentId id;
     private Faculty faculty;
     private Library library;
     private String deptName;
     private Set courseses = new HashSet(0);

    public Department() {
    }

	
    public Department(DepartmentId id, Faculty faculty, Library library) {
        this.id = id;
        this.faculty = faculty;
        this.library = library;
    }
    public Department(DepartmentId id, Faculty faculty, Library library, String deptName, Set courseses) {
       this.id = id;
       this.faculty = faculty;
       this.library = library;
       this.deptName = deptName;
       this.courseses = courseses;
    }
   
    public DepartmentId getId() {
        return this.id;
    }
    
    public void setId(DepartmentId id) {
        this.id = id;
    }
    public Faculty getFaculty() {
        return this.faculty;
    }
    
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    public Library getLibrary() {
        return this.library;
    }
    
    public void setLibrary(Library library) {
        this.library = library;
    }
    public String getDeptName() {
        return this.deptName;
    }
    
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public Set getCourseses() {
        return this.courseses;
    }
    
    public void setCourseses(Set courseses) {
        this.courseses = courseses;
    }




}


