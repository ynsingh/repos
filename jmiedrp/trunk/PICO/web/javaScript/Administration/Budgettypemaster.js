/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function clearBudgettypemasterFields() {
    document.forms(0).action = "/pico/picoaction/Clear.action";
    document.forms[0].submit();
    return false;
}

