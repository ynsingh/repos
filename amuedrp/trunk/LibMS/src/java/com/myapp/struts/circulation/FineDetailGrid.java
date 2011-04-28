/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.hbm.*;

/**
 *
 * @author System Administrator
 */
public class FineDetailGrid {
    private DocumentCategory documentCategory;
    private EmployeeType employeeType;
    private SubEmployeeType subEmployeeType;
    private BookCategory bookCategory;

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    public DocumentCategory getDocumentCategory() {
        return documentCategory;
    }

    public void setDocumentCategory(DocumentCategory documentCategory) {
        this.documentCategory = documentCategory;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public SubEmployeeType getSubEmployeeType() {
        return subEmployeeType;
    }

    public void setSubEmployeeType(SubEmployeeType subEmployeeType) {
        this.subEmployeeType = subEmployeeType;
    }

    
}
