/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.*;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author Dushyant
 */
public class SystemSetupAjaxDAO {

public String getSubEmpTypeID (String library_id,String emptype_id) {

StringBuffer emp_ids = new StringBuffer();
MemberCategoryDAO memberDAO = new MemberCategoryDAO();
try {
List<SubEmployeeType> subemp = (List<SubEmployeeType>)memberDAO.searchSubEmpTypeByEmpId(library_id, emptype_id);
Iterator it = subemp.iterator();
int tcount=0;

emp_ids.append("<emp_ids>");
while (it.hasNext())
{

//construct the xml string.
SubEmployeeType subemppojo = subemp.get(tcount);


   emp_ids.append("<emp_id>"+subemppojo.getId().getSubEmptypeId()+"</emp_id>");
   emp_ids.append("<emp_name>"+subemppojo.getSubEmptypeFullName()+"</emp_name>");
tcount++;
it.next();
}
emp_ids.append("</emp_ids>");
}

catch(Exception se) {
se.printStackTrace();
}
finally {
try {

}
catch(Exception e) {
e.printStackTrace();
}
}
return emp_ids.toString();
}
}