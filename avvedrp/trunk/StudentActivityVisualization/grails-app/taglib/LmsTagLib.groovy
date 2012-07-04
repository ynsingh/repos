/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahis
 */
class LmsTagLib {

    def Menu = {attrs ->
		  out << g.render(template: '/menu')
         }

    def Footer = {attrs ->
		  out << g.render(template: '/footer')
         }    
   
    def SideMenu = {attrs ->
                  out << g.render(template: '/sideMenu')   
         }
         
}

