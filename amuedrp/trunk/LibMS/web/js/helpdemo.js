/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

window.status="f1 for help";

function statwords(message)
{

document.onkeypress = keyHit
function keyHit(event) {
  if (event.keyCode == event.DOM_VK_F1) {
     JavaScript:window.status=message;

    event.stopPropagation()
    event.preventDefault()
  }
}
}
